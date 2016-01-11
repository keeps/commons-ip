package org.roda_project.commons_ip.parse.impl.eark;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;

import javax.xml.bind.JAXBException;

import org.roda_project.commons_ip.mets_v1_11.beans.AmdSecType;
import org.roda_project.commons_ip.mets_v1_11.beans.FileGrpType;
import org.roda_project.commons_ip.mets_v1_11.beans.FileType;
import org.roda_project.commons_ip.mets_v1_11.beans.FileType.FLocat;
import org.roda_project.commons_ip.mets_v1_11.beans.MdSecType;
import org.roda_project.commons_ip.mets_v1_11.beans.MdSecType.MdRef;
import org.roda_project.commons_ip.mets_v1_11.beans.Mets;
import org.roda_project.commons_ip.mets_v1_11.beans.MetsType.FileSec;
import org.roda_project.commons_ip.mets_v1_11.beans.StructMapType;
import org.roda_project.commons_ip.model.MigrationException;
import org.roda_project.commons_ip.model.SIP;
import org.roda_project.commons_ip.model.SIPDescriptiveMetadata;
import org.roda_project.commons_ip.model.SIPMetadata;
import org.roda_project.commons_ip.model.SIPRepresentation;
import org.roda_project.commons_ip.model.ValidationReport;
import org.roda_project.commons_ip.model.impl.eark.EARKMETSUtils;
import org.roda_project.commons_ip.model.impl.eark.EARKSIP;
import org.roda_project.commons_ip.parse.Parser;
import org.roda_project.commons_ip.utils.EARKEnums.ContentType;
import org.roda_project.commons_ip.utils.METSEnums;
import org.roda_project.commons_ip.utils.METSEnums.MetadataType;
import org.roda_project.commons_ip.utils.SIPException;
import org.roda_project.commons_ip.utils.Utils;
import org.roda_project.commons_ip.validation.impl.eark.EARKValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EARKParser implements Parser {
  private static final Logger LOGGER = LoggerFactory.getLogger(EARKParser.class);

  public SIP parse(Path source) throws MigrationException {
    EARKValidator validator = new EARKValidator();
    ValidationReport report = validator.isSIPValid(source);
    // if (report.isValid()) {
    try {
      SIP converted = earkSipToRodaAip(source);
      return converted;
    } catch (JAXBException | IOException | SIPException e) {
      throw new MigrationException("Error converting E-ARK SIP to RODA AIP", e);
    }
    // } else {
    // throw new MigrationException();
    // }

  }

  private SIP earkSipToRodaAip(Path source) throws JAXBException, IOException, SIPException {
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
    Mets mainMets = EARKMETSUtils.processMetsXML(mainMETSFile);

    SIP sip = new EARKSIP("ID", ContentType.mixed, "RODA");

    if (mainMets.getDmdSec() != null && mainMets.getDmdSec().size() > 0) {
      for (MdSecType mdsec : mainMets.getDmdSec()) {
        if (mdsec.getMdRef() != null) {
          MdRef mdref = mdsec.getMdRef();
          String href = mdref.getHref();
          if (href.startsWith("file://./")) {
            href = href.replace("file://./", "");
          }
          Path filePath = source.resolve(href);
          SIPDescriptiveMetadata sdm;
          try{
            MetadataType mt = MetadataType.valueOf(mdref.getMDTYPE().toUpperCase());
            LOGGER.debug("Metadata type valid: "+mt.toString());
            sdm = new SIPDescriptiveMetadata(filePath, null, mt);
          }catch(NullPointerException | IllegalArgumentException t){
            sdm = new SIPDescriptiveMetadata(filePath, null, MetadataType.OTHER);
          }
          
          sip.addDescriptiveMetadata(sdm);
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
            SIPMetadata sdm = new SIPMetadata(filePath, null);
            sip.addAdministrativeMetadata(sdm);
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
          Path representationMets = path.resolve("METS.xml");
          sip = processRepresentation(sip, representationMets, path.getFileName().toString());
        }
      } catch (IOException e) {
        LOGGER.error("Error opening directory stream", e);
      }
    }

    if (mainMets.getStructMap() != null && mainMets.getStructMap().size() > 0) {
      for (StructMapType smt : mainMets.getStructMap()) {
        if (smt.getStructMapTypeLabel() != null && smt.getStructMapTypeLabel().equalsIgnoreCase("Parent")) {
          String parentID = EARKMETSUtils.extractParentID(smt);
          if (parentID != null) {
            sip.setParent(parentID);
          }
        }
      }
    }
    return sip;
  }

  private SIP processRepresentation(SIP sip, Path representationMetsPath, String representationID)
    throws JAXBException, SIPException {
    Path representationPath = representationMetsPath.getParent();
    sip.addRepresentation(new SIPRepresentation(representationID));
    try {
      Mets representationMets = EARKMETSUtils.processMetsXML(representationMetsPath);
      if (representationMets.getDmdSec() != null && representationMets.getDmdSec().size() > 0) {
        for (MdSecType mdsec : representationMets.getDmdSec()) {
          if (mdsec != null && mdsec.getMdRef() != null) {
            MdRef mdref = mdsec.getMdRef();
            String href = mdref.getHref();
            if (href.startsWith("file://./")) {
              href = href.replace("file://./", "");
            }
            Path filePath = representationPath.resolve(href);
            
            SIPDescriptiveMetadata sdm;
            try{
              MetadataType mt = MetadataType.valueOf(mdref.getMDTYPE().toUpperCase());
              LOGGER.debug("Metadata type valid: "+mt.toString());
              sdm = new SIPDescriptiveMetadata(filePath, null, mt);
            }catch(NullPointerException | IllegalArgumentException t){
              sdm = new SIPDescriptiveMetadata(filePath, null, MetadataType.OTHER);
            }
            sip.addDescriptiveMetadataToRepresentation(representationID, sdm);
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
                SIPMetadata sm = new SIPMetadata(filePath, null);
                sip.addAdministrativeMetadataToRepresentation(representationID, sm);
              }
            }
          }
        }
      }
      if (representationMets.getFileSec() != null) {
        FileSec representationFileSec = representationMets.getFileSec();
        if (representationFileSec.getFileGrp() != null && representationFileSec.getFileGrp().size() > 0) {
          for (FileGrpType gr : representationFileSec.getFileGrp()) {
            sip = addFilesFromFileGrpToRepresentation(sip, representationID, representationPath, gr);
          }
        }
      }
    } catch (IOException | SIPException e) {
      LOGGER.error("Error processing representation", e);
    }
    return sip;
  }

  private SIP addFilesFromFileGrpToRepresentation(SIP sip, String representationID, Path representationPath,
    FileGrpType fileGroup) throws IOException, SIPException {
    if (fileGroup.getFile() != null && fileGroup.getFile().size() > 0) {
      for (FileType ft : fileGroup.getFile()) {
        sip = addFilesFromFileToRepresentation(sip, representationID, representationPath, ft);
      }
    }
    if (fileGroup.getFileGrp() != null && fileGroup.getFileGrp().size() > 0) {
      for (FileGrpType g : fileGroup.getFileGrp()) {
        sip = addFilesFromFileGrpToRepresentation(sip, representationID, representationPath, g);
      }
    }
    return sip;
  }

  private SIP addFilesFromFileToRepresentation(SIP sip, String representationID, Path representationPath, FileType file)
    throws IOException, SIPException {
    if (file.getFLocat() != null && !file.getFLocat().isEmpty()) {
      for (FLocat locat : file.getFLocat()) {
        if (locat.getType() != null && locat.getType().equalsIgnoreCase("simple") && locat.getLOCTYPE() != null
          && locat.getLOCTYPE().equalsIgnoreCase(METSEnums.LocType.URL.toString())) {
          if (locat.getHref() != null && locat.getHref().startsWith("file://./")) {
            String relativePath = locat.getHref().replace("file://./", "");
            Path filePath = representationPath.resolve(relativePath);
            sip.addDataToRepresentation(representationID, filePath);
          }
        }
      }
    }
    return sip;
  }

}
