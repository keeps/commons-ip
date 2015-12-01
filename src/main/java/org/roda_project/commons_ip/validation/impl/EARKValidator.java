/**
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE file at the root of the source
 * tree and available online at
 *
 * https://github.com/keeps/commons-ip
 */
package org.roda_project.commons_ip.validation.impl;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import javax.xml.bind.JAXBException;

import org.roda_project.commons_ip.mets_v1_11.beans.AmdSecType;
import org.roda_project.commons_ip.mets_v1_11.beans.FileGrpType;
import org.roda_project.commons_ip.mets_v1_11.beans.FileType;
import org.roda_project.commons_ip.mets_v1_11.beans.FileType.FLocat;
import org.roda_project.commons_ip.mets_v1_11.beans.MdSecType;
import org.roda_project.commons_ip.mets_v1_11.beans.MdSecType.MdRef;
import org.roda_project.commons_ip.mets_v1_11.beans.Mets;
import org.roda_project.commons_ip.mets_v1_11.beans.MetsType.FileSec;
import org.roda_project.commons_ip.mets_v1_11.beans.MetsType.FileSec.FileGrp;
import org.roda_project.commons_ip.model.impl.METSUtils;
import org.roda_project.commons_ip.utils.METSEnums;
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
        report = validateMets(mainMETSFile, report);

        Path representationsPath = sip.resolve("representations");

        if (Files.exists(representationsPath)) {
          DirectoryStream.Filter<Path> filter = new DirectoryStream.Filter<Path>() {
            @Override
            public boolean accept(Path file) throws IOException {
              return (Files.isDirectory(file));
            }
          };
          try (DirectoryStream<Path> stream = Files.newDirectoryStream(representationsPath, filter)) {
            for (Path path : stream) {
              Path representationMets = path.resolve("METS.xml");
              if (Files.exists(representationMets)) {
                ValidationReport representationReport = new ValidationReport();
                representationReport.setValid(true);
                representationReport = validateMets(representationMets, representationReport);

                if (!representationReport.isValid()) {
                  report.setValid(false);
                }
                if (representationReport.getIssues() != null && representationReport.getIssues().size() > 0) {
                  for (ValidationIssue issue : representationReport.getIssues()) {
                    report.addIssue(issue);
                  }
                }
              }
            }
          } catch (IOException e) {
            e.printStackTrace();
          }
        }

      } catch (JAXBException je) {
        report = ValidationUtils.addIssue(report, ValidationErrors.MAIN_METS_NOT_VALID, ValidationIssue.LEVEL.ERROR,
          null, Arrays.asList(mainMETSFile));
      }

    }
    return report;
  }

  private ValidationReport validateMets(Path metsPath, ValidationReport report) throws JAXBException {
    Mets mets = METSUtils.processMetsXML(metsPath);
    if (mets.getAmdSec() != null && mets.getAmdSec().size() > 0) {
      for (AmdSecType amdsec : mets.getAmdSec()) {
        report = validateAmdSec(amdsec, metsPath.getParent(), report);
      }
    }
    if (mets.getDmdSec() != null && mets.getDmdSec().size() > 0) {
      for (MdSecType dmdsec : mets.getDmdSec()) {
        report = validateMdSecType(dmdsec, metsPath.getParent(), report);
      }
    }
    if (mets.getFileSec() != null) {
      report = validateFilesec(mets.getFileSec(), metsPath.getParent(), report);
    }
    return report;
  }

  private ValidationReport validateFilesec(FileSec fileSec, Path base, ValidationReport report) {
    if (fileSec.getFileGrp() != null && fileSec.getFileGrp().size() > 0) {
      for (FileGrp fileGrp : fileSec.getFileGrp()) {
        report = validateFileGrpType(fileGrp, base, report);
      }
    }
    return report;
  }

  private ValidationReport validateFileGrpType(FileGrpType fileGrp, Path base, ValidationReport report) {
    if (fileGrp.getFile() != null && fileGrp.getFile().size() > 0) {
      for (FileType file : fileGrp.getFile()) {
        report = validateFileType(file, base, report);
      }
    }
    if (fileGrp.getFileGrp() != null && fileGrp.getFileGrp().size() > 0) {
      for (FileGrpType fg2 : fileGrp.getFileGrp()) {
        report = validateFileGrpType(fg2, base, report);
      }
    }
    return report;
  }

  private ValidationReport validateFileType(FileType file, Path base, ValidationReport report) {
    String checksumType = file.getCHECKSUMTYPE();
    String checksum = file.getCHECKSUM();
    if (file.getFLocat() != null && file.getFLocat().size() > 0) {
      boolean locatFound = false;
      for (FLocat locat : file.getFLocat()) {
        if (locat.getType() != null && locat.getType().equalsIgnoreCase("simple") && locat.getLOCTYPE() != null
          && locat.getLOCTYPE().equalsIgnoreCase(METSEnums.LocType.URL.toString())) {
          locatFound = true;
          if (locat.getHref() != null && locat.getHref().startsWith("file://.")) {
            try {
              Path filePath = base.resolve(locat.getHref().replace("file://.", ""));
              String fileChecksum = Utils.calculateChecksum(Files.newInputStream(filePath), checksumType);
              if (!fileChecksum.equalsIgnoreCase(checksum)) {
                report = ValidationUtils.addIssue(report, ValidationErrors.BAD_CHECKSUM, ValidationIssue.LEVEL.ERROR,
                  "FILE_ID: " + file.getID(), null);
              }
            } catch (NoSuchAlgorithmException | IOException e) {
              report = ValidationUtils.addIssue(report, ValidationErrors.ERROR_COMPUTING_CHECKSUM,
                ValidationIssue.LEVEL.ERROR, "FILE_ID: " + file.getID(), null);
            }

          } else {
            report = ValidationUtils.addIssue(report, ValidationErrors.BAD_HREF, ValidationIssue.LEVEL.ERROR,
              "FILE_ID: " + file.getID(), null);
          }
        }
      }
      if (!locatFound) {
        report = ValidationUtils.addIssue(report, ValidationErrors.NO_VALID_LOCAT, ValidationIssue.LEVEL.ERROR,
          "FILE_ID: " + file.getID(), null);
      }
    } else {
      report = ValidationUtils.addIssue(report, ValidationErrors.NO_LOCAT_FOR_FILE, ValidationIssue.LEVEL.ERROR,
        "FILE_ID: " + file.getID(), null);
    }
    return report;
  }

  private ValidationReport validateMdSecType(MdSecType dmdsec, Path base, ValidationReport report) {
    MdRef mdref = dmdsec.getMdRef();
    if (mdref != null) {
      String checksumType = mdref.getCHECKSUMTYPE();
      String checksum = mdref.getCHECKSUM();
      if (mdref.getHref() != null && mdref.getHref().startsWith("file://.")) {
        try {
          Path filePath = base.resolve(mdref.getHref().replace("file://.", ""));
          String fileChecksum = Utils.calculateChecksum(Files.newInputStream(filePath), checksumType);
          if (!fileChecksum.equalsIgnoreCase(checksum)) {
            report = ValidationUtils.addIssue(report, ValidationErrors.BAD_CHECKSUM, ValidationIssue.LEVEL.ERROR,
              "MDREF_ID: " + mdref.getID(), null);
          }
        } catch (NoSuchAlgorithmException | IOException e) {
          report = ValidationUtils.addIssue(report, ValidationErrors.ERROR_COMPUTING_CHECKSUM,
            ValidationIssue.LEVEL.ERROR, "MDREF_ID: " + mdref.getID(), null);
        }

      } else {
        report = ValidationUtils.addIssue(report, ValidationErrors.BAD_HREF, ValidationIssue.LEVEL.ERROR,
          "MDREF_ID: " + mdref.getID(), null);
      }

    }
    return report;
  }

  private ValidationReport validateAmdSec(AmdSecType amdsec, Path base, ValidationReport report) {
    if (amdsec.getDigiprovMD() != null && amdsec.getDigiprovMD().size() > 0) {
      for (MdSecType digiprov : amdsec.getDigiprovMD()) {
        report = validateMdSecType(digiprov, base, report);
      }
    }
    return report;
  }

}
