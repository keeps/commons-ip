/**
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE file at the root of the source
 * tree and available online at
 *
 * https://github.com/keeps/commons-ip
 */
package org.roda_project.commons_ip.validation.impl.eark;

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
import org.roda_project.commons_ip.model.IPConstants;
import org.roda_project.commons_ip.model.ValidationIssue;
import org.roda_project.commons_ip.model.ValidationReport;
import org.roda_project.commons_ip.model.impl.eark.EARKMETSUtils;
import org.roda_project.commons_ip.utils.METSEnums;
import org.roda_project.commons_ip.utils.Utils;
import org.roda_project.commons_ip.utils.ValidationErrors;
import org.roda_project.commons_ip.utils.ValidationUtils;
import org.roda_project.commons_ip.utils.ZIPUtils;
import org.roda_project.commons_ip.validation.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EARKValidator implements Validator {
  private static final Logger LOGGER = LoggerFactory.getLogger(EARKValidator.class);

  @Override
  public ValidationReport isSIPValid(Path sip) {
    ValidationReport report = new ValidationReport();
    report.setValid(true);

    if (!Files.isDirectory(sip)) {
      try {
        Path uncompressed = Files.createTempDirectory("unzipped");
        ZIPUtils.unzip(sip, uncompressed);
        sip = uncompressed;
      } catch (IOException e) {
        report = ValidationUtils.addIssue(report, ValidationErrors.UNABLE_TO_UNZIP_SIP, ValidationIssue.LEVEL.ERROR,
          null, Arrays.asList(sip));
      }
    }
    Path mainMETSFile = sip.resolve(IPConstants.METS_FILE);
    if (!Files.exists(mainMETSFile)) {
      report = ValidationUtils.addIssue(report, ValidationErrors.NO_MAIN_METS_FILE, ValidationIssue.LEVEL.ERROR, null,
        Arrays.asList(mainMETSFile));
    } else {
      try {
        report = validateMets(mainMETSFile, report);

        Path representationsPath = sip.resolve(IPConstants.REPRESENTATIONS);

        if (Files.exists(representationsPath)) {
          DirectoryStream.Filter<Path> filter = new DirectoryStream.Filter<Path>() {
            @Override
            public boolean accept(Path file) throws IOException {
              return Files.isDirectory(file);
            }
          };
          try (DirectoryStream<Path> stream = Files.newDirectoryStream(representationsPath, filter)) {
            for (Path path : stream) {
              Path representationMets = path.resolve(IPConstants.METS_FILE);
              if (Files.exists(representationMets)) {
                ValidationReport representationReport = new ValidationReport();
                representationReport.setValid(true);
                representationReport = validateMets(representationMets, representationReport);

                if (!representationReport.isValid()) {
                  report.setValid(false);
                }
                if (representationReport.getIssues() != null && !representationReport.getIssues().isEmpty()) {
                  for (ValidationIssue issue : representationReport.getIssues()) {
                    report.addIssue(issue);
                  }
                }
              }
            }
          } catch (IOException e) {
            // TODO add error message
            LOGGER.error("", e);
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
    Mets mets = EARKMETSUtils.instantiateMETSFromFile(metsPath);
    if (mets.getAmdSec() != null && !mets.getAmdSec().isEmpty()) {
      for (AmdSecType amdsec : mets.getAmdSec()) {
        report = validateAmdSec(amdsec, metsPath.getParent(), report);
      }
    }
    if (mets.getDmdSec() != null && !mets.getDmdSec().isEmpty()) {
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
    if (fileSec.getFileGrp() != null && !fileSec.getFileGrp().isEmpty()) {
      for (FileGrp fileGrp : fileSec.getFileGrp()) {
        report = validateFileGrpType(fileGrp, base, report);
      }
    }
    return report;
  }

  private ValidationReport validateFileGrpType(FileGrpType fileGrp, Path base, ValidationReport report) {
    if (fileGrp.getFile() != null && !fileGrp.getFile().isEmpty()) {
      for (FileType file : fileGrp.getFile()) {
        report = validateFileType(file, base, report);
      }
    }
    if (fileGrp.getFileGrp() != null && !fileGrp.getFileGrp().isEmpty()) {
      for (FileGrpType fg2 : fileGrp.getFileGrp()) {
        report = validateFileGrpType(fg2, base, report);
      }
    }
    return report;
  }

  private ValidationReport validateFileType(FileType file, Path base, ValidationReport report) {
    String checksumType = file.getCHECKSUMTYPE();
    String checksum = file.getCHECKSUM();
    if (file.getFLocat() != null && !file.getFLocat().isEmpty()) {
      boolean locatFound = false;
      for (FLocat locat : file.getFLocat()) {
        if (locat.getType() != null && locat.getType().equalsIgnoreCase(IPConstants.METS_TYPE_SIMPLE)
          && locat.getLOCTYPE() != null && locat.getLOCTYPE().equalsIgnoreCase(METSEnums.LocType.URL.toString())) {
          locatFound = true;
          if (locat.getHref() != null && locat.getHref().startsWith(IPConstants.METS_FILE_URI_PREFIX)) {
            try {
              Path filePath = base.resolve(locat.getHref().replace(IPConstants.METS_FILE_URI_PREFIX, ""));
              String fileChecksum = Utils.calculateChecksum(Files.newInputStream(filePath), checksumType);
              if (!fileChecksum.equalsIgnoreCase(checksum)) {
                report = ValidationUtils.addIssue(report, ValidationErrors.BAD_CHECKSUM, ValidationIssue.LEVEL.ERROR,
                  "FILE_ID:" + file.getID() + " METS_CHECKSUM:" + checksum + " COMPUTED_CHECKSUM:" + fileChecksum,
                  null);
              }
            } catch (NoSuchAlgorithmException | IOException e) {
              e.printStackTrace();
              report = ValidationUtils.addIssue(report,
                ValidationErrors.ERROR_COMPUTING_CHECKSUM + ": " + e.getMessage(), ValidationIssue.LEVEL.ERROR,
                "FILE_ID: " + file.getID(), null);
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
      if (mdref.getHref() != null && mdref.getHref().startsWith(IPConstants.METS_FILE_URI_PREFIX)) {
        try {
          Path filePath = base.resolve(mdref.getHref().replace(IPConstants.METS_FILE_URI_PREFIX, ""));
          String fileChecksum = Utils.calculateChecksum(Files.newInputStream(filePath), checksumType);
          if (!fileChecksum.equalsIgnoreCase(checksum)) {
            report = ValidationUtils.addIssue(report, ValidationErrors.BAD_CHECKSUM, ValidationIssue.LEVEL.ERROR,
              "MDREF_ID:" + mdref.getID() + " METS_CHECKSUM:" + checksum + " COMPUTED_CHECKSUM:" + fileChecksum, null);
          }
        } catch (NoSuchAlgorithmException | IOException e) {
          e.printStackTrace();
          report = ValidationUtils.addIssue(report, ValidationErrors.ERROR_COMPUTING_CHECKSUM + ": " + e.getMessage(),
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
    if (amdsec.getDigiprovMD() != null && !amdsec.getDigiprovMD().isEmpty()) {
      for (MdSecType digiprov : amdsec.getDigiprovMD()) {
        report = validateMdSecType(digiprov, base, report);
      }
    }
    return report;
  }

}
