package org.roda_project.commons_ip.migration.impl.eark;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.xml.bind.JAXBException;

import org.roda_project.commons_ip.mets_v1_11.beans.AmdSecType;
import org.roda_project.commons_ip.mets_v1_11.beans.FileGrpType;
import org.roda_project.commons_ip.mets_v1_11.beans.FileType;
import org.roda_project.commons_ip.mets_v1_11.beans.FileType.FLocat;
import org.roda_project.commons_ip.mets_v1_11.beans.MdSecType;
import org.roda_project.commons_ip.mets_v1_11.beans.MdSecType.MdRef;
import org.roda_project.commons_ip.mets_v1_11.beans.Mets;
import org.roda_project.commons_ip.mets_v1_11.beans.MetsType.FileSec;
import org.roda_project.commons_ip.migration.Migrator;
import org.roda_project.commons_ip.model.MigrationException;
import org.roda_project.commons_ip.model.ValidationReport;
import org.roda_project.commons_ip.model.impl.eark.METSUtils;
import org.roda_project.commons_ip.utils.METSEnums;
import org.roda_project.commons_ip.utils.Utils;
import org.roda_project.commons_ip.validation.impl.eark.EARKValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EARKSIPToRODAAIP implements Migrator {
  private static final Logger LOGGER = LoggerFactory.getLogger(EARKSIPToRODAAIP.class);

  public Path convert(Path source) throws MigrationException {
    EARKValidator validator = new EARKValidator();
    ValidationReport report = validator.isSIPValid(source);
    // if (report.isValid()) {
    try {
      Path converted = earkSipToRodaAip(source);
      return converted;
    } catch (JAXBException | IOException e) {
      throw new MigrationException("Error converting E-ARK SIP to RODA AIP", e);
    }
    // } else {
    // throw new MigrationException();
    // }

  }

  private Path earkSipToRodaAip(Path source) throws JAXBException, IOException {

    if (!Files.isDirectory(source)) {
      try {
        Path uncompressed = Files.createTempDirectory("unzipped");
        Utils.unzip(source, uncompressed);
        source = uncompressed;
      } catch (IOException e) {
        LOGGER.error("Error unzipping file", e);
      }
    }
    Path mainMETSFile = source.resolve("METS.xml");
    Mets mainMets = METSUtils.processMetsXML(mainMETSFile);
    Path dst = Files.createTempDirectory(mainMets.getOBJID());

    if (mainMets.getDmdSec() != null && mainMets.getDmdSec().size() > 0) {
      for (MdSecType mdsec : mainMets.getDmdSec()) {
        if (mdsec.getMdRef() != null) {
          MdRef mdref = mdsec.getMdRef();
          String href = mdref.getHref();
          if (href.startsWith("file://./")) {
            href = href.replace("file://./", "");
          }
          Path filePath = source.resolve(href);
          RODAUtils.addDescriptiveMetadata(dst, Paths.get(href), Files.newInputStream(filePath));
        }
      }
    }
    if (mainMets.getAmdSec() != null && mainMets.getAmdSec().size() > 0) {
      for (AmdSecType amdsec : mainMets.getAmdSec()) {
        if (amdsec.getDigiprovMD() != null && amdsec.getDigiprovMD().size() > 0) {
          for (MdSecType digiProv : amdsec.getDigiprovMD()) {
            MdRef mdref = digiProv.getMdRef();
            String href = mdref.getHref();
            if (href.startsWith("file://./")) {
              href = href.replace("file://./", "");
            }
            Path filePath = source.resolve(href);
            RODAUtils.addPreservationMetadata(dst, Paths.get(href), Files.newInputStream(filePath));
          }
        }
      }
    }
    Path representationsPath = source.resolve("representations");
    if (Files.exists(representationsPath)) {
      DirectoryStream.Filter<Path> filter = new DirectoryStream.Filter<Path>() {
        @Override
        public boolean accept(Path file) throws IOException {
          return (Files.isDirectory(file));
        }
      };
      try (DirectoryStream<Path> stream = Files.newDirectoryStream(representationsPath, filter)) {
        for (Path path : stream) {
          LOGGER.info("Processing: " + path.toString());
          Path representationMets = path.resolve("METS.xml");
          processRepresentation(dst, representationMets, path.getFileName().toString());
        }
      } catch (IOException e) {
        LOGGER.error("Error opening directory stream", e);
      }
    }
    return dst;
  }

  private void processRepresentation(Path dst, Path representationMetsPath, String representationID)
    throws JAXBException {
    Path representationPath = representationMetsPath.getParent();
    try {
      Mets representationMets = METSUtils.processMetsXML(representationMetsPath);
      if (representationMets.getDmdSec() != null && representationMets.getDmdSec().size() > 0) {
        for (MdSecType mdsec : representationMets.getDmdSec()) {
          if (mdsec != null && mdsec.getMdRef() != null) {
            MdRef mdref = mdsec.getMdRef();
            String href = mdref.getHref();
            if (href.startsWith("file://./")) {
              href = href.replace("file://./", "");
            }
            Path filePath = representationPath.resolve(href);
            RODAUtils.addDescriptiveMetadataToRepresentation(dst, representationID, Paths.get(href),
              Files.newInputStream(filePath));
          }
        }
      }
      if (representationMets.getAmdSec() != null && representationMets.getAmdSec().size() > 0) {
        for (AmdSecType amdsec : representationMets.getAmdSec()) {
          if (amdsec != null) {
            if (amdsec.getDigiprovMD() != null && amdsec.getDigiprovMD().size() > 0) {
              for (MdSecType digiProv : amdsec.getDigiprovMD()) {
                MdRef mdref = digiProv.getMdRef();
                String href = mdref.getHref();
                if (href.startsWith("file://./")) {
                  href = href.replace("file://./", "");
                }
                Path filePath = representationPath.resolve(href);
                RODAUtils.addPreservationMetadataToRepresentation(dst, representationID, Paths.get(href),
                  Files.newInputStream(filePath));
              }
            }
          }
        }
      }
      if (representationMets.getFileSec() != null) {
        FileSec representationFileSec = representationMets.getFileSec();
        if (representationFileSec.getFileGrp() != null && representationFileSec.getFileGrp().size() > 0) {
          for (FileGrpType gr : representationFileSec.getFileGrp()) {
            addFilesFromFileGrpToRepresentation(dst, representationID, representationPath, gr);
          }
        }
      }
    } catch (IOException e) {
      LOGGER.error("Error processing representation", e);
    }
  }

  private void addFilesFromFileGrpToRepresentation(Path dst, String representationID, Path representationPath,
    FileGrpType fileGroup) throws IOException {
    if (fileGroup.getFile() != null && fileGroup.getFile().size() > 0) {
      for (FileType ft : fileGroup.getFile()) {
        addFilesFromFileToRepresentation(dst, representationID, representationPath, ft);
      }
    }
    if (fileGroup.getFileGrp() != null && fileGroup.getFileGrp().size() > 0) {
      for (FileGrpType g : fileGroup.getFileGrp()) {
        addFilesFromFileGrpToRepresentation(dst, representationID, representationPath, g);
      }
    }
  }

  private void addFilesFromFileToRepresentation(Path dst, String representationID, Path representationPath,
    FileType file) throws IOException {
    if (file.getFLocat() != null && !file.getFLocat().isEmpty()) {
      for (FLocat locat : file.getFLocat()) {
        if (locat.getType() != null && locat.getType().equalsIgnoreCase("simple") && locat.getLOCTYPE() != null
          && locat.getLOCTYPE().equalsIgnoreCase(METSEnums.LocType.URL.toString())) {
          if (locat.getHref() != null && locat.getHref().startsWith("file://./")) {
            String relativePath = locat.getHref().replace("file://./", "");
            Path filePath = representationPath.resolve(relativePath);
            RODAUtils.addDataToRepresentation(dst, representationID, Paths.get(relativePath),
              Files.newInputStream(filePath));
          }
        }
      }
    }
  }

}
