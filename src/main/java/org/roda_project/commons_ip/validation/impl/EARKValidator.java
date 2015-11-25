/**
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE file at the root of the source
 * tree and available online at
 *
 * https://github.com/keeps/commons-ip
 */
package org.roda_project.commons_ip.validation.impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.roda_project.commons_ip.mets_v1_11.beans.DivType;
import org.roda_project.commons_ip.mets_v1_11.beans.DivType.Mptr;
import org.roda_project.commons_ip.mets_v1_11.beans.FileType;
import org.roda_project.commons_ip.mets_v1_11.beans.FileType.FLocat;
import org.roda_project.commons_ip.mets_v1_11.beans.MdSecType;
import org.roda_project.commons_ip.mets_v1_11.beans.MdSecType.MdRef;
import org.roda_project.commons_ip.mets_v1_11.beans.Mets;
import org.roda_project.commons_ip.mets_v1_11.beans.MetsType.FileSec;
import org.roda_project.commons_ip.mets_v1_11.beans.MetsType.FileSec.FileGrp;
import org.roda_project.commons_ip.mets_v1_11.beans.StructMapType;
import org.roda_project.commons_ip.utils.Utils;
import org.roda_project.commons_ip.validation.Validator;
import org.roda_project.commons_ip.validation.model.ValidationIssue;
import org.roda_project.commons_ip.validation.model.ValidationReport;
import org.roda_project.commons_ip.validation.utils.ValidationErrors;
import org.roda_project.commons_ip.validation.utils.ValidationUtils;

public class EARKValidator implements Validator {

  @Override
  public ValidationReport isSIPValid(Path sip) {
    ValidationReport report = new ValidationReport();
    report.setValid(true);

    if (!Files.isDirectory(sip)) {
      try {
        Path uncompressed = Files.createTempDirectory("unzipped");
        Utils.unzip(sip, uncompressed);
        sip = uncompressed;
      } catch (IOException e) {
        report = ValidationUtils.addIssue(report, ValidationErrors.UNABLE_TO_UNZIP_SIP, ValidationIssue.LEVEL.ERROR,
          null, Arrays.asList(sip));
      }
    }
    Path mainMETSFile = sip.resolve("METS.xml");
    if (!Files.exists(mainMETSFile)) {
      report = ValidationUtils.addIssue(report, ValidationErrors.NO_MAIN_METS_FILE, ValidationIssue.LEVEL.ERROR, null,
        Arrays.asList(mainMETSFile));
    } else {
      try {
        Mets mainMets = processMetsXML(mainMETSFile);
        if (mainMets.getStructMap() == null || mainMets.getStructMap().size() == 0) {
          report = ValidationUtils.addIssue(report, ValidationErrors.NO_STRUCT_MAP, ValidationIssue.LEVEL.ERROR, null,
            Arrays.asList(mainMETSFile));
        } else {
          StructMapType structMap = mainMets.getStructMap().get(0);
          DivType packageDiv = structMap.getDiv();
          if (packageDiv.getDiv() != null) {
            DivType representationDiv = packageDiv.getDiv().get(0);
            if (representationDiv.getMptr() != null) {
              for (Mptr m : representationDiv.getMptr()) {
                if (m.getHref().startsWith("file://./")) {
                  Path representationMets = sip.resolve(m.getHref().replace("file://./", ""));
                  if (Files.exists(representationMets)) {
                    ValidationReport representationReport = isRepresentationValid(representationMets.getParent());
                    if (!representationReport.isValid()) {
                      report.setValid(false);
                    }
                    if (representationReport.getIssues() != null && representationReport.getIssues().size() > 0) {
                      for (ValidationIssue vi : representationReport.getIssues()) {
                        report = ValidationUtils.addIssue(report, vi.getMessage(), vi.getLevel(), vi.getDescription(),
                          vi.getRelatedItem());
                      }
                    }
                  } else {
                    System.out.println("Doesn't exist...");
                  }
                } else {
                  System.out.println("Doesn't start...");
                }

              }
            }
          }
          if (mainMets.getDmdSec() != null && mainMets.getDmdSec().size() > 0) {
            for (MdSecType mdsec : mainMets.getDmdSec()) {
              MdRef mdref = mdsec.getMdRef();
              if (mdref != null) {
                String checksum = mdref.getCHECKSUM();
                String checksumType = mdref.getCHECKSUMTYPE();
                if (mdref.getHref() == null) {
                  report = ValidationUtils.addIssue(report, ValidationErrors.NO_HREF_IN_MDREF,
                    ValidationIssue.LEVEL.ERROR, "MDREF ID: " + mdref.getID(), null);
                } else {
                  String href = mdref.getHref();
                  if (href.startsWith("/")) {
                    href = href.substring(1);
                  }
                  Path filePath = sip.resolve(href);
                  try {
                    String fileChecksum = Utils.calculateChecksum(Files.newInputStream(filePath), checksumType);
                    if (!fileChecksum.equalsIgnoreCase(checksum)) {
                      report = ValidationUtils.addIssue(report,
                        ValidationErrors.BAD_CHECKSUM, ValidationIssue.LEVEL.ERROR, "File: " + filePath.toString()
                          + " Mets checksum:" + checksum + "; calculated checksum:" + fileChecksum,
                        Arrays.asList(filePath));
                    }
                  } catch (NoSuchAlgorithmException nsae) {
                    report = ValidationUtils.addIssue(report,
                      ValidationErrors.ERROR_COMPUTING_CHECKSUM_NO_SUCH_ALGORYTHM, ValidationIssue.LEVEL.ERROR, null,
                      Arrays.asList(filePath));
                  } catch (IOException e) {
                    e.printStackTrace();
                    report = ValidationUtils.addIssue(report, ValidationErrors.ERROR_COMPUTING_CHECKSUM,
                      ValidationIssue.LEVEL.ERROR, e.getMessage(), Arrays.asList(filePath));
                  }
                }
              }
            }
          }

        }
      } catch (JAXBException jax) {
        report = ValidationUtils.addIssue(report, ValidationErrors.MAIN_METS_NOT_VALID, ValidationIssue.LEVEL.ERROR,
          jax.getMessage(), Arrays.asList(mainMETSFile));
      }

    }
    return report;
  }

  private Mets processMetsXML(Path mainMETSFile) throws JAXBException {
    JAXBContext jaxbContext = JAXBContext.newInstance(Mets.class);
    Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
    return (Mets) jaxbUnmarshaller.unmarshal(mainMETSFile.toFile());
  }

  private ValidationReport isRepresentationValid(Path representationPath) {
    ValidationReport report = new ValidationReport();
    report.setValid(true);
    Path representationMetsPath = representationPath.resolve("METS.xml");
    try {
      Mets representationMets = processMetsXML(representationMetsPath);
      FileSec filesec = representationMets.getFileSec();
      if (filesec.getFileGrp() != null && filesec.getFileGrp().size() > 0) {
        for (FileGrp fileGrp : filesec.getFileGrp()) {
          if (fileGrp.getFile() != null && fileGrp.getFile().size() > 0) {
            for (FileType ft : fileGrp.getFile()) {
              String checksum = ft.getCHECKSUM();
              String checksumType = ft.getCHECKSUMTYPE();
              if (ft.getFLocat() == null || ft.getFLocat().size() == 0) {
                System.out.println("No FLocat");
                report = ValidationUtils.addIssue(report, ValidationErrors.NO_LOCAT_FOR_FILE,
                  ValidationIssue.LEVEL.ERROR, "ID: " + ft.getID(), null);
              } else {
                for (FLocat locat : ft.getFLocat()) {
                  if (locat.getHref().startsWith("file://./")) {
                    Path filePath = representationPath.resolve(locat.getHref().replace("file://./", ""));
                    try {
                      String fileChecksum = Utils.calculateChecksum(Files.newInputStream(filePath), checksumType);
                      if (!fileChecksum.equalsIgnoreCase(checksum)) {
                        report = ValidationUtils.addIssue(report,
                          ValidationErrors.BAD_CHECKSUM, ValidationIssue.LEVEL.ERROR, "File: " + filePath.toString()
                            + " Mets checksum:" + checksum + "; calculated checksum:" + fileChecksum,
                          Arrays.asList(filePath));
                      }
                    } catch (NoSuchAlgorithmException nsae) {
                      report = ValidationUtils.addIssue(report,
                        ValidationErrors.ERROR_COMPUTING_CHECKSUM_NO_SUCH_ALGORYTHM, ValidationIssue.LEVEL.ERROR, null,
                        Arrays.asList(filePath));
                    } catch (IOException e) {
                      e.printStackTrace();
                      report = ValidationUtils.addIssue(report, ValidationErrors.ERROR_COMPUTING_CHECKSUM,
                        ValidationIssue.LEVEL.ERROR, e.getMessage(), Arrays.asList(filePath));
                    }
                  }
                }
              }

            }
          }
        }
      }
    } catch (JAXBException jax) {
      report = ValidationUtils.addIssue(report, ValidationErrors.REPRESENTATION_METS_NOT_VALID,
        ValidationIssue.LEVEL.ERROR, "", Arrays.asList(representationMetsPath));
    }
    return report;
  }

}
