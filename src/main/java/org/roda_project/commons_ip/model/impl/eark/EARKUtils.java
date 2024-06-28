/**
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE file at the root of the source
 * tree and available online at
 *
 * https://github.com/keeps/commons-ip
 */
package org.roda_project.commons_ip.model.impl.eark;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.roda_project.commons_ip.mets_v1_11.beans.DivType;
import org.roda_project.commons_ip.mets_v1_11.beans.DivType.Fptr;
import org.roda_project.commons_ip.mets_v1_11.beans.DivType.Mptr;
import org.roda_project.commons_ip.mets_v1_11.beans.FileType;
import org.roda_project.commons_ip.mets_v1_11.beans.FileType.FLocat;
import org.roda_project.commons_ip.mets_v1_11.beans.MdSecType.MdRef;
import org.roda_project.commons_ip.mets_v1_11.beans.Mets;
import org.roda_project.commons_ip.mets_v1_11.beans.MetsType.MetsHdr.Agent;
import org.roda_project.commons_ip.mets_v1_11.beans.StructMapType;
import org.roda_project.commons_ip.model.AIP;
import org.roda_project.commons_ip.model.IPConstants;
import org.roda_project.commons_ip.model.IPContentType;
import org.roda_project.commons_ip.model.IPDescriptiveMetadata;
import org.roda_project.commons_ip.model.IPFile;
import org.roda_project.commons_ip.model.IPHeader;
import org.roda_project.commons_ip.model.IPInterface;
import org.roda_project.commons_ip.model.IPMetadata;
import org.roda_project.commons_ip.model.IPRepresentation;
import org.roda_project.commons_ip.model.MetadataType;
import org.roda_project.commons_ip.model.MetsWrapper;
import org.roda_project.commons_ip.model.ParseException;
import org.roda_project.commons_ip.model.RepresentationContentType;
import org.roda_project.commons_ip.model.RepresentationStatus;
import org.roda_project.commons_ip.model.SIP;
import org.roda_project.commons_ip.model.ValidationEntry;
import org.roda_project.commons_ip.model.impl.ModelUtils;
import org.roda_project.commons_ip.utils.IPEnums;
import org.roda_project.commons_ip.utils.IPEnums.IPStatus;
import org.roda_project.commons_ip.utils.IPException;
import org.roda_project.commons_ip.utils.METSUtils;
import org.roda_project.commons_ip.utils.Utils;
import org.roda_project.commons_ip.utils.ValidationConstants;
import org.roda_project.commons_ip.utils.ValidationUtils;
import org.roda_project.commons_ip.utils.ZIPUtils;
import org.roda_project.commons_ip.utils.ZipEntryInfo;
import org.slf4j.Logger;
import org.xml.sax.SAXException;

import jakarta.xml.bind.JAXBException;

public final class EARKUtils {
  private static final boolean VALIDATION_FAIL_IF_REPRESENTATION_METS_DOES_NOT_HAVE_TWO_PARTS = false;

  private EARKUtils() {
    // do nothing
  }

  static void addDescriptiveMetadataToZipAndMETS(Map<String, ZipEntryInfo> zipEntries,
                                                 MetsWrapper metsWrapper, List<IPDescriptiveMetadata> descriptiveMetadata, String representationId)
    throws IPException, InterruptedException {
    if (descriptiveMetadata != null && !descriptiveMetadata.isEmpty()) {
      for (IPDescriptiveMetadata dm : descriptiveMetadata) {
        if (Thread.interrupted()) {
          throw new InterruptedException();
        }
        IPFile file = dm.getMetadata();

        String descriptiveFilePath = IPConstants.DESCRIPTIVE_FOLDER
          + ModelUtils.getFoldersFromList(file.getRelativeFolders()) + file.getFileName();
        MdRef mdRef = EARKMETSUtils.addDescriptiveMetadataToMETS(metsWrapper, dm, descriptiveFilePath);

        if (representationId != null) {
          descriptiveFilePath = IPConstants.REPRESENTATIONS_FOLDER + representationId + IPConstants.ZIP_PATH_SEPARATOR
            + descriptiveFilePath;
        }
        ZIPUtils.addMdRefFileToZip(zipEntries, file.getPath(), descriptiveFilePath, mdRef);
      }
    }
  }

  static void addPreservationMetadataToZipAndMETS(Map<String, ZipEntryInfo> zipEntries,
                                                  MetsWrapper metsWrapper, List<IPMetadata> preservationMetadata, String representationId)
    throws IPException, InterruptedException {
    if (preservationMetadata != null && !preservationMetadata.isEmpty()) {
      for (IPMetadata pm : preservationMetadata) {
        if (Thread.interrupted()) {
          throw new InterruptedException();
        }
        IPFile file = pm.getMetadata();

        String preservationMetadataPath = IPConstants.PRESERVATION_FOLDER
          + ModelUtils.getFoldersFromList(file.getRelativeFolders()) + file.getFileName();
        MdRef mdRef = EARKMETSUtils.addPreservationMetadataToMETS(metsWrapper, pm, preservationMetadataPath);

        if (representationId != null) {
          preservationMetadataPath = IPConstants.REPRESENTATIONS_FOLDER + representationId
            + IPConstants.ZIP_PATH_SEPARATOR + preservationMetadataPath;
        }
        ZIPUtils.addMdRefFileToZip(zipEntries, file.getPath(), preservationMetadataPath, mdRef);
      }
    }
  }

  static void addOtherMetadataToZipAndMETS(Map<String, ZipEntryInfo> zipEntries, MetsWrapper metsWrapper,
                                           List<IPMetadata> otherMetadata, String representationId) throws IPException, InterruptedException {
    if (otherMetadata != null && !otherMetadata.isEmpty()) {
      for (IPMetadata om : otherMetadata) {
        if (Thread.interrupted()) {
          throw new InterruptedException();
        }
        IPFile file = om.getMetadata();

        String otherMetadataPath = IPConstants.OTHER_FOLDER + ModelUtils.getFoldersFromList(file.getRelativeFolders())
          + file.getFileName();
        MdRef mdRef = EARKMETSUtils.addOtherMetadataToMETS(metsWrapper, om, otherMetadataPath);

        if (representationId != null) {
          otherMetadataPath = IPConstants.REPRESENTATIONS_FOLDER + representationId + IPConstants.ZIP_PATH_SEPARATOR
            + otherMetadataPath;
        }
        ZIPUtils.addMdRefFileToZip(zipEntries, file.getPath(), otherMetadataPath, mdRef);
      }
    }
  }

  static void addRepresentationsToZipAndMETS(IPInterface ip, List<IPRepresentation> representations,
                                             Map<String, ZipEntryInfo> zipEntries, MetsWrapper mainMETSWrapper, Path buildDir)
    throws IPException, InterruptedException {
    // representations
    if (representations != null && !representations.isEmpty()) {
      if (ip instanceof SIP) {
        ((SIP) ip).notifySipBuildRepresentationsProcessingStarted(representations.size());
      }
      for (IPRepresentation representation : representations) {
        if (Thread.interrupted()) {
          throw new InterruptedException();
        }
        String representationId = representation.getObjectID();
        // 20160407 hsilva: not being used by Common Specification v0.13
        String representationProfile = "";
        String representationContentType = representation.getContentType().asString();

        IPHeader header = new IPHeader(IPEnums.IPStatus.NEW).setAgents(representation.getAgents());
        MetsWrapper representationMETSWrapper = EARKMETSUtils.generateMETS(representationId,
          representation.getDescription(),
          IPConstants.METS_REPRESENTATION_TYPE_PART_1 + ":" + representationContentType, representationProfile, false,
          Optional.empty(), null, header);
        representationMETSWrapper.getMainDiv().setTYPE(representation.getStatus().asString());

        // representation data
        addRepresentationDataFilesToZipAndMETS(ip, zipEntries, representationMETSWrapper, representation,
          representationId);

        // representation descriptive metadata
        addDescriptiveMetadataToZipAndMETS(zipEntries, representationMETSWrapper,
          representation.getDescriptiveMetadata(), representationId);

        // representation preservation metadata
        addPreservationMetadataToZipAndMETS(zipEntries, representationMETSWrapper,
          representation.getPreservationMetadata(), representationId);

        // representation other metadata
        addOtherMetadataToZipAndMETS(zipEntries, representationMETSWrapper, representation.getOtherMetadata(),
          representationId);

        // representation schemas
        addSchemasToZipAndMETS(zipEntries, representationMETSWrapper, representation.getSchemas(), representationId);

        // representation documentation
        addDocumentationToZipAndMETS(zipEntries, representationMETSWrapper, representation.getDocumentation(),
          representationId);

        // add representation METS to Zip file and to main METS file
        EARKMETSUtils.addRepresentationMETSToZipAndToMainMETS(zipEntries, mainMETSWrapper, representationId,
          representationMETSWrapper, IPConstants.REPRESENTATIONS_FOLDER + representationId
            + IPConstants.ZIP_PATH_SEPARATOR + IPConstants.METS_FILE,
          buildDir);
      }
      if (ip instanceof SIP) {
        ((SIP) ip).notifySipBuildRepresentationsProcessingEnded();
      }
    }
  }

  private static void addRepresentationDataFilesToZipAndMETS(IPInterface ip, Map<String, ZipEntryInfo> zipEntries,
                                                             MetsWrapper representationMETSWrapper, IPRepresentation representation, String representationId)
    throws IPException, InterruptedException {
    if (representation.getData() != null && !representation.getData().isEmpty()) {
      if (ip instanceof SIP) {
        ((SIP) ip).notifySipBuildRepresentationProcessingStarted(representation.getData().size());
      }
      int i = 0;
      for (IPFile file : representation.getData()) {
        if (Thread.interrupted()) {
          throw new InterruptedException();
        }

        String dataFilePath = IPConstants.DATA_FOLDER + ModelUtils.getFoldersFromList(file.getRelativeFolders())
          + file.getFileName();
        FileType fileType = EARKMETSUtils.addDataFileToMETS(representationMETSWrapper, dataFilePath, file.getPath());

        dataFilePath = IPConstants.REPRESENTATIONS_FOLDER + representationId + IPConstants.ZIP_PATH_SEPARATOR
          + dataFilePath;
        ZIPUtils.addFileTypeFileToZip(zipEntries, file.getPath(), dataFilePath, fileType);

        i++;
        if (ip instanceof SIP) {
          ((SIP) ip).notifySipBuildRepresentationProcessingCurrentStatus(i);
        }
      }
      if (ip instanceof SIP) {
        ((SIP) ip).notifySipBuildRepresentationProcessingEnded();
      }
    }
  }

  static void addSchemasToZipAndMETS(Map<String, ZipEntryInfo> zipEntries, MetsWrapper metsWrapper,
                                     List<IPFile> schemas, String representationId) throws IPException, InterruptedException {
    if (schemas != null && !schemas.isEmpty()) {
      for (IPFile schema : schemas) {
        if (Thread.interrupted()) {
          throw new InterruptedException();
        }

        String schemaFilePath = IPConstants.SCHEMAS_FOLDER + ModelUtils.getFoldersFromList(schema.getRelativeFolders())
          + schema.getFileName();
        FileType fileType = EARKMETSUtils.addSchemaFileToMETS(metsWrapper, schemaFilePath, schema.getPath());

        if (representationId != null) {
          schemaFilePath = IPConstants.REPRESENTATIONS_FOLDER + representationId + IPConstants.ZIP_PATH_SEPARATOR
            + schemaFilePath;
        }
        ZIPUtils.addFileTypeFileToZip(zipEntries, schema.getPath(), schemaFilePath, fileType);
      }
    }
  }

  static void addDocumentationToZipAndMETS(Map<String, ZipEntryInfo> zipEntries, MetsWrapper metsWrapper,
                                           List<IPFile> documentation, String representationId) throws IPException, InterruptedException {
    if (documentation != null && !documentation.isEmpty()) {
      for (IPFile doc : documentation) {
        if (Thread.interrupted()) {
          throw new InterruptedException();
        }

        String documentationFilePath = IPConstants.DOCUMENTATION_FOLDER
          + ModelUtils.getFoldersFromList(doc.getRelativeFolders()) + doc.getFileName();
        FileType fileType = EARKMETSUtils.addDocumentationFileToMETS(metsWrapper, documentationFilePath, doc.getPath());

        if (representationId != null) {
          documentationFilePath = IPConstants.REPRESENTATIONS_FOLDER + representationId + IPConstants.ZIP_PATH_SEPARATOR
            + documentationFilePath;
        }
        ZIPUtils.addFileTypeFileToZip(zipEntries, doc.getPath(), documentationFilePath, fileType);
      }
    }
  }

  static void addDefaultSchemas(Logger logger, List<IPFile> schemas, Path buildDir)
    throws InterruptedException {
    try {
      if (Thread.interrupted()) {
        throw new InterruptedException();
      }
      Path metsSchema = Utils.copyResourceFromClasspathToDir(EARKSIP.class, buildDir, "mets.xsd",
        "/schemas/mets1_11.xsd");
      schemas.add(new IPFile(metsSchema, "mets.xsd"));
      Path xlinkSchema = Utils.copyResourceFromClasspathToDir(EARKSIP.class, buildDir, "xlink.xsd",
        "/schemas/xlink.xsd");
      schemas.add(new IPFile(xlinkSchema, "xlink.xsd"));
    } catch (IOException e) {
      logger.error("Error while trying to add default schemas", e);
    }
  }

  static void addSubmissionsToZipAndMETS(final Map<String, ZipEntryInfo> zipEntries,
                                         final MetsWrapper metsWrapper, final List<IPFile> submissions) throws IPException, InterruptedException {
    if (submissions != null && !submissions.isEmpty()) {
      for (IPFile submission : submissions) {
        if (Thread.interrupted()) {
          throw new InterruptedException();
        }
        final String submissionFilePath = IPConstants.SUBMISSION_FOLDER
          + ModelUtils.getFoldersFromList(submission.getRelativeFolders()) + submission.getFileName();
        final FileType fileType = EARKMETSUtils.addSubmissionFileToMETS(metsWrapper, submissionFilePath,
          submission.getPath());
        ZIPUtils.addFileTypeFileToZip(zipEntries, submission.getPath(), submissionFilePath, fileType);
      }
    }
  }

  static MetsWrapper processMainMets(IPInterface ip, Path ipPath) {
    Path mainMETSFile = ipPath.resolve(IPConstants.METS_FILE);
    Mets mainMets = null;
    if (Files.exists(mainMETSFile)) {
      ValidationUtils.addInfo(ip.getValidationReport(), ValidationConstants.MAIN_METS_FILE_FOUND, ipPath, mainMETSFile);
      try {
        mainMets = METSUtils.instantiateMETSFromFile(mainMETSFile);
        ip.setIds(Arrays.asList(mainMets.getOBJID().split(" ")));
        ip.setCreateDate(mainMets.getMetsHdr().getCREATEDATE());
        ip.setModificationDate(mainMets.getMetsHdr().getLASTMODDATE());
        ip.setStatus(IPStatus.parse(mainMets.getMetsHdr().getRECORDSTATUS()));
        setIPContentType(mainMets, ip);
        addAgentsToMETS(mainMets, ip, null);

        ValidationUtils.addInfo(ip.getValidationReport(), ValidationConstants.MAIN_METS_IS_VALID, ipPath, mainMETSFile);
      } catch (JAXBException | ParseException | SAXException e) {
        mainMets = null;
        ValidationUtils.addIssue(ip.getValidationReport(), ValidationConstants.MAIN_METS_NOT_VALID,
          ValidationEntry.LEVEL.ERROR, e, ip.getBasePath(), mainMETSFile);
      }
    } else {
      ValidationUtils.addIssue(ip.getValidationReport(), ValidationConstants.MAIN_METS_FILE_NOT_FOUND,
        ValidationEntry.LEVEL.ERROR, ip.getBasePath(), mainMETSFile);
    }
    return new MetsWrapper(mainMets, mainMETSFile);
  }

  private static void setIPContentType(Mets mets, IPInterface ip) throws ParseException {
    String metsType = mets.getTYPE();

    if (StringUtils.isBlank(metsType)) {
      throw new ParseException("METS 'TYPE' attribute does not contain any value");
    }

    String[] contentTypeParts = metsType.split(":");
    if (contentTypeParts.length != 2 || StringUtils.isBlank(contentTypeParts[0])
      || StringUtils.isBlank(contentTypeParts[1])) {
      throw new ParseException("METS 'TYPE' attribute does not contain a valid value");
    }

    try {
      IPEnums.IPType packageType = IPEnums.IPType.valueOf(contentTypeParts[0]);

      if (ip instanceof SIP && IPEnums.IPType.SIP != packageType) {
        throw new ParseException("METS 'TYPE' attribute should start with 'SIP:'");
      } else if (ip instanceof AIP && IPEnums.IPType.AIP != packageType) {
        throw new ParseException("METS 'TYPE' attribute should start with 'AIP:'");
      }
    } catch (IllegalArgumentException e) {
      throw new ParseException("METS 'TYPE' attribute does not contain a valid package type");
    }

    ip.setContentType(new IPContentType(contentTypeParts[1]));
  }

  private static Mets addAgentsToMETS(Mets mets, IPInterface ip, IPRepresentation representation) {
    if (mets.getMetsHdr() != null && mets.getMetsHdr().getAgent() != null) {
      for (Agent agent : mets.getMetsHdr().getAgent()) {
        if (representation == null) {
          ip.addAgent(EARKMETSUtils.createIPAgent(agent));
        } else {
          representation.addAgent(EARKMETSUtils.createIPAgent(agent));
        }
      }
    }

    return mets;
  }

  private static MetsWrapper processRepresentationMets(IPInterface ip, Path representationMetsFile,
                                                       IPRepresentation representation) {
    Mets representationMets = null;
    if (Files.exists(representationMetsFile)) {
      ValidationUtils.addInfo(ip.getValidationReport(), ValidationConstants.REPRESENTATION_METS_FILE_FOUND,
        ip.getBasePath(), representationMetsFile);
      try {
        representationMets = METSUtils.instantiateMETSFromFile(representationMetsFile);
        setRepresentationContentType(representationMets, representation);
        ValidationUtils.addInfo(ip.getValidationReport(), ValidationConstants.REPRESENTATION_METS_IS_VALID,
          ip.getBasePath(), representationMetsFile);
      } catch (JAXBException | ParseException | SAXException e) {
        representationMets = null;
        ValidationUtils.addIssue(ip.getValidationReport(), ValidationConstants.REPRESENTATION_METS_NOT_VALID,
          ValidationEntry.LEVEL.ERROR, e, ip.getBasePath(), representationMetsFile);
      }
    } else {
      ValidationUtils.addIssue(ip.getValidationReport(), ValidationConstants.REPRESENTATION_METS_FILE_NOT_FOUND,
        ValidationEntry.LEVEL.ERROR, ip.getBasePath(), representationMetsFile);
    }
    return new MetsWrapper(representationMets, representationMetsFile);
  }

  private static void setRepresentationContentType(Mets mets, IPRepresentation representation) throws ParseException {
    String metsType = mets.getTYPE();

    if (StringUtils.isBlank(metsType)) {
      throw new ParseException("METS 'TYPE' attribute does not contain any value");
    }

    if ("representation".equals(metsType)) {
      if (VALIDATION_FAIL_IF_REPRESENTATION_METS_DOES_NOT_HAVE_TWO_PARTS) {
        throw new ParseException(
          "METS 'TYPE' attribute is not valid as it should be 'representation:REPRESENTATION_TYPE'");
      } else {
        return;
      }
    }

    String[] contentTypeParts = metsType.split(":");
    if (contentTypeParts.length != 2 || StringUtils.isBlank(contentTypeParts[0])
      || !"representation".equals(contentTypeParts[0]) || StringUtils.isBlank(contentTypeParts[1])) {
      throw new ParseException("METS 'TYPE' attribute does not contain a valid value");
    }

    representation.setContentType(new RepresentationContentType(contentTypeParts[1]));
  }

  static IPInterface processRepresentations(MetsWrapper metsWrapper, IPInterface ip, Logger logger)
    throws IPException {

    if (metsWrapper.getRepresentationsDiv() != null && metsWrapper.getRepresentationsDiv().getDiv() != null) {
      for (DivType representationDiv : metsWrapper.getRepresentationsDiv().getDiv()) {
        if (representationDiv.getMptr() != null && !representationDiv.getMptr().isEmpty()) {
          // we can assume one and only one mets for each representation div
          Mptr mptr = representationDiv.getMptr().get(0);
          String href = Utils.extractedRelativePathFromHref(mptr.getHref());
          Path metsFilePath = ip.getBasePath().resolve(href);
          IPRepresentation representation = new IPRepresentation(representationDiv.getLABEL());
          MetsWrapper representationMetsWrapper = processRepresentationMets(ip, metsFilePath, representation);

          if (representationMetsWrapper.getMets() != null) {
            Path representationBasePath = metsFilePath.getParent();

            StructMapType representationStructMap = getEARKStructMap(representationMetsWrapper, ip, false);
            if (representationStructMap != null) {

              preProcessStructMap(representationMetsWrapper, representationStructMap);
              representation.setStatus(new RepresentationStatus(representationMetsWrapper.getMainDiv().getTYPE()));
              ip.addRepresentation(representation);

              // process representation agents
              processRepresentationAgents(representationMetsWrapper, representation);

              // process files
              processRepresentationFiles(ip, representationMetsWrapper, representation, representationBasePath);

              // process descriptive metadata
              processDescriptiveMetadata(representationMetsWrapper, ip, logger, representation, representationBasePath);

              // process preservation metadata
              processPreservationMetadata(representationMetsWrapper, ip, logger, representation,
                representationBasePath);

              // process other metadata
              processOtherMetadata(representationMetsWrapper, ip, logger, representation, representationBasePath);

              // process schemas
              processSchemasMetadata(representationMetsWrapper, ip, representationBasePath);

              // process documentation
              processDocumentationMetadata(representationMetsWrapper, ip, representationBasePath);
            }
          }
        }
      }

      // post-process validations
      if (ip.getRepresentations().isEmpty()) {
        ValidationUtils.addIssue(ip.getValidationReport(), ValidationConstants.MAIN_METS_NO_REPRESENTATIONS_FOUND,
          ValidationEntry.LEVEL.WARN, metsWrapper.getRepresentationsDiv(), ip.getBasePath(), metsWrapper.getMetsPath());
      }
    }

    return ip;

  }

  static StructMapType getEARKStructMap(MetsWrapper metsWrapper, IPInterface ip, boolean mainMets) {
    Mets mets = metsWrapper.getMets();
    StructMapType res = null;
    for (StructMapType structMap : mets.getStructMap()) {
      if (StringUtils.equals(structMap.getLABEL(), IPConstants.COMMON_SPEC_STRUCTURAL_MAP)
        || StringUtils.equals(structMap.getLABEL(), IPConstants.E_ARK_STRUCTURAL_MAP)) {
        res = structMap;
        break;
      }
    }
    if (res == null) {
      ValidationUtils.addIssue(ip.getValidationReport(),
        mainMets ? ValidationConstants.MAIN_METS_HAS_NO_E_ARK_STRUCT_MAP
          : ValidationConstants.REPRESENTATION_METS_HAS_NO_E_ARK_STRUCT_MAP,
        ValidationEntry.LEVEL.ERROR, res, ip.getBasePath(), metsWrapper.getMetsPath());
    } else {
      ValidationUtils.addInfo(ip.getValidationReport(),
        mainMets ? ValidationConstants.MAIN_METS_HAS_E_ARK_STRUCT_MAP
          : ValidationConstants.REPRESENTATION_METS_HAS_E_ARK_STRUCT_MAP,
        res, ip.getBasePath(), metsWrapper.getMetsPath());
    }
    return res;
  }

  static void preProcessStructMap(MetsWrapper metsWrapper, StructMapType structMap) {

    DivType aipDiv = structMap.getDiv();
    if (aipDiv.getDiv() != null) {
      metsWrapper.setMainDiv(aipDiv);
      for (DivType firstLevel : aipDiv.getDiv()) {
        if (IPConstants.METADATA.equalsIgnoreCase(firstLevel.getLABEL()) && firstLevel.getDiv() != null) {
          for (DivType secondLevel : firstLevel.getDiv()) {
            if (IPConstants.DESCRIPTIVE.equalsIgnoreCase(secondLevel.getLABEL())) {
              metsWrapper.setDescriptiveMetadataDiv(secondLevel);
            } else if (IPConstants.PRESERVATION.equalsIgnoreCase(secondLevel.getLABEL())) {
              metsWrapper.setPreservationMetadataDiv(secondLevel);
            } else if (IPConstants.OTHER.equalsIgnoreCase(secondLevel.getLABEL())) {
              metsWrapper.setOtherMetadataDiv(secondLevel);
            }
          }
        } else if (IPConstants.REPRESENTATIONS.equalsIgnoreCase(firstLevel.getLABEL())) {
          metsWrapper.setRepresentationsDiv(firstLevel);
        } else if (IPConstants.DATA.equalsIgnoreCase(firstLevel.getLABEL())) {
          metsWrapper.setDataDiv(firstLevel);
        } else if (IPConstants.SCHEMAS.equalsIgnoreCase(firstLevel.getLABEL())) {
          metsWrapper.setSchemasDiv(firstLevel);
        } else if (IPConstants.DOCUMENTATION.equalsIgnoreCase(firstLevel.getLABEL())) {
          metsWrapper.setDocumentationDiv(firstLevel);
        } else if (IPConstants.SUBMISSION.equalsIgnoreCase(firstLevel.getLABEL())) {
          metsWrapper.setSubmissionsDiv(firstLevel);
        }
      }
    }
  }

  static IPInterface processDescriptiveMetadata(MetsWrapper metsWrapper, IPInterface ip, Logger logger,
                                                IPRepresentation representation, Path basePath) throws IPException {

    return processMetadata(ip, logger, metsWrapper, representation, metsWrapper.getDescriptiveMetadataDiv(),
      IPConstants.DESCRIPTIVE, basePath);
  }

  static IPInterface processOtherMetadata(MetsWrapper metsWrapper, IPInterface ip, Logger logger,
                                          IPRepresentation representation, Path basePath) throws IPException {

    return processMetadata(ip, logger, metsWrapper, representation, metsWrapper.getOtherMetadataDiv(),
      IPConstants.OTHER, basePath);
  }

  static IPInterface processPreservationMetadata(MetsWrapper metsWrapper, IPInterface ip, Logger logger,
                                                 IPRepresentation representation, Path basePath) throws IPException {

    return processMetadata(ip, logger, metsWrapper, representation, metsWrapper.getPreservationMetadataDiv(),
      IPConstants.PRESERVATION, basePath);
  }

  private static IPInterface processMetadata(IPInterface ip, Logger logger, MetsWrapper representationMetsWrapper,
                                             IPRepresentation representation, DivType div, String metadataType, Path basePath) throws IPException {
    if (div != null && div.getFptr() != null) {
      for (Fptr fptr : div.getFptr()) {
        MdRef mdRef = (MdRef) fptr.getFILEID();
        String href = Utils.extractedRelativePathFromHref(mdRef);
        Path filePath = basePath.resolve(href);
        if (Files.exists(filePath)) {
          List<String> fileRelativeFolders = Utils
            .getFileRelativeFolders(basePath.resolve(IPConstants.METADATA).resolve(metadataType), filePath);

          processMetadataFile(ip, logger, representation, metadataType, mdRef, filePath, fileRelativeFolders);
        } else {
          ValidationUtils.addIssue(ip.getValidationReport(),
            ValidationConstants.getMetadataFileNotFoundString(metadataType), ValidationEntry.LEVEL.ERROR,
            ip.getBasePath(), filePath);
        }
      }
    } else {
      ValidationUtils.addIssue(ip.getValidationReport(),
        ValidationConstants.getMetadataFileFptrNotFoundString(metadataType), ValidationEntry.LEVEL.ERROR,
        ip.getBasePath(), representationMetsWrapper.getMetsPath());
    }

    return ip;
  }

  private static void processMetadataFile(IPInterface ip, Logger logger, IPRepresentation representation,
                                          String metadataType, MdRef mdRef, Path filePath, List<String> fileRelativeFolders) throws IPException {
    Optional<IPFile> metadataFile = validateMetadataFile(ip, filePath, mdRef, fileRelativeFolders);
    if (metadataFile.isPresent()) {
      ValidationUtils.addInfo(ip.getValidationReport(),
        ValidationConstants.getMetadataFileFoundWithMatchingChecksumString(metadataType), ip.getBasePath(), filePath);

      if (IPConstants.DESCRIPTIVE.equalsIgnoreCase(metadataType)) {
        MetadataType dmdType = new MetadataType(mdRef.getMDTYPE().toUpperCase());
        String dmdVersion = null;
        try {
          dmdVersion = mdRef.getMDTYPEVERSION();
          if (StringUtils.isNotBlank(mdRef.getOTHERMDTYPE())) {
            dmdType.setOtherType(mdRef.getOTHERMDTYPE());
          }
          logger.debug("Metadata type valid: {}", dmdType);
        } catch (NullPointerException | IllegalArgumentException e) {
          // do nothing and use already defined values for metadataType &
          // metadataVersion
          logger.debug("Setting metadata type to {}", dmdType);
          ValidationUtils.addEntry(ip.getValidationReport(), ValidationConstants.UNKNOWN_DESCRIPTIVE_METADATA_TYPE,
            ValidationEntry.LEVEL.WARN, "Setting metadata type to " + dmdType, ip.getBasePath(), filePath);
        }

        IPDescriptiveMetadata descriptiveMetadata = new IPDescriptiveMetadata(mdRef.getID(), metadataFile.get(),
          dmdType, dmdVersion);
        descriptiveMetadata.setCreateDate(mdRef.getCREATED());
        if (representation == null) {
          ip.addDescriptiveMetadata(descriptiveMetadata);
        } else {
          representation.addDescriptiveMetadata(descriptiveMetadata);
        }
      } else if (IPConstants.PRESERVATION.equalsIgnoreCase(metadataType)) {
        IPMetadata preservationMetadata = new IPMetadata(metadataFile.get());
        preservationMetadata.setCreateDate(mdRef.getCREATED());
        if (representation == null) {
          ip.addPreservationMetadata(preservationMetadata);
        } else {
          representation.addPreservationMetadata(preservationMetadata);
        }
      } else if (IPConstants.OTHER.equalsIgnoreCase(metadataType)) {
        IPMetadata otherMetadata = new IPMetadata(metadataFile.get());
        otherMetadata.setCreateDate(mdRef.getCREATED());
        if (representation == null) {
          ip.addOtherMetadata(otherMetadata);
        } else {
          representation.addOtherMetadata(otherMetadata);
        }
      }
    }
  }

  private static Optional<IPFile> validateFile(IPInterface ip, Path filePath, FileType fileType,
                                               List<String> fileRelativeFolders) {
    return Utils.validateFile(ip, filePath, fileRelativeFolders, fileType.getCHECKSUM(), fileType.getCHECKSUMTYPE(),
      fileType.getID());
  }

  private static Optional<IPFile> validateMetadataFile(IPInterface ip, Path filePath, MdRef mdRef,
                                                       List<String> fileRelativeFolders) {
    return Utils.validateFile(ip, filePath, fileRelativeFolders, mdRef.getCHECKSUM(), mdRef.getCHECKSUMTYPE(),
      mdRef.getID());
  }

  private static IPInterface processFile(IPInterface ip, DivType div, String folder, Path basePath)
    throws IPException {
    if (div != null && div.getFptr() != null) {
      for (Fptr fptr : div.getFptr()) {
        FileType fileType = (FileType) fptr.getFILEID();

        if (fileType.getFLocat() != null) {
          FLocat fLocat = fileType.getFLocat().get(0);
          String href = Utils.extractedRelativePathFromHref(fLocat.getHref());
          Path filePath = basePath.resolve(href);

          if (Files.exists(filePath)) {
            List<String> fileRelativeFolders = Utils.getFileRelativeFolders(basePath.resolve(folder), filePath);
            Optional<IPFile> file = validateFile(ip, filePath, fileType, fileRelativeFolders);

            if (file.isPresent()) {
              if (IPConstants.SCHEMAS.equalsIgnoreCase(folder)) {
                ValidationUtils.addInfo(ip.getValidationReport(),
                  ValidationConstants.SCHEMA_FILE_FOUND_WITH_MATCHING_CHECKSUMS, ip.getBasePath(), filePath);
                ip.addSchema(file.get());
              } else if (IPConstants.DOCUMENTATION.equalsIgnoreCase(folder)) {
                ValidationUtils.addInfo(ip.getValidationReport(),
                  ValidationConstants.DOCUMENTATION_FILE_FOUND_WITH_MATCHING_CHECKSUMS, ip.getBasePath(), filePath);
                ip.addDocumentation(file.get());
              } else if (IPConstants.SUBMISSION.equalsIgnoreCase(folder) && ip instanceof AIP) {
                ValidationUtils.addInfo(ip.getValidationReport(),
                  ValidationConstants.SUBMISSION_FILE_FOUND_WITH_MATCHING_CHECKSUMS, ip.getBasePath(), filePath);
                ((AIP) ip).addSubmission(file.get());
              }
            }
          } else {
            if (IPConstants.SCHEMAS.equalsIgnoreCase(folder)) {
              ValidationUtils.addIssue(ip.getValidationReport(), ValidationConstants.SCHEMA_FILE_NOT_FOUND,
                ValidationEntry.LEVEL.ERROR, div, ip.getBasePath(), filePath);
            } else if (IPConstants.DOCUMENTATION.equalsIgnoreCase(folder)) {
              ValidationUtils.addIssue(ip.getValidationReport(), ValidationConstants.DOCUMENTATION_FILE_NOT_FOUND,
                ValidationEntry.LEVEL.ERROR, div, ip.getBasePath(), filePath);
            } else if (IPConstants.SUBMISSION.equalsIgnoreCase(folder)) {
              ValidationUtils.addIssue(ip.getValidationReport(), ValidationConstants.SUBMISSION_FILE_NOT_FOUND,
                ValidationEntry.LEVEL.ERROR, div, ip.getBasePath(), filePath);
            }
          }
        }
      }
    }

    return ip;
  }

  private static void processRepresentationAgents(MetsWrapper representationMetsWrapper,
                                                  IPRepresentation representation) {
    addAgentsToMETS(representationMetsWrapper.getMets(), null, representation);
  }

  private static void processRepresentationFiles(IPInterface ip, MetsWrapper representationMetsWrapper,
                                                 IPRepresentation representation, Path representationBasePath) throws IPException {

    if (representationMetsWrapper.getDataDiv() != null && representationMetsWrapper.getDataDiv().getFptr() != null) {
      for (Fptr fptr : representationMetsWrapper.getDataDiv().getFptr()) {
        FileType fileType = (FileType) fptr.getFILEID();

        if (fileType != null && fileType.getFLocat() != null) {
          FLocat fLocat = fileType.getFLocat().get(0);
          String href = Utils.extractedRelativePathFromHref(fLocat.getHref());
          Path filePath = representationBasePath.resolve(href);
          if (Files.exists(filePath)) {
            List<String> fileRelativeFolders = Utils
              .getFileRelativeFolders(representationBasePath.resolve(IPConstants.DATA), filePath);
            Optional<IPFile> file = validateFile(ip, filePath, fileType, fileRelativeFolders);

            if (file.isPresent()) {
              representation.addFile(file.get());
              ValidationUtils.addInfo(ip.getValidationReport(),
                ValidationConstants.REPRESENTATION_FILE_FOUND_WITH_MATCHING_CHECKSUMS, ip.getBasePath(), filePath);
            }
          } else {
            ValidationUtils.addIssue(ip.getValidationReport(), ValidationConstants.REPRESENTATION_FILE_NOT_FOUND,
              ValidationEntry.LEVEL.ERROR, ip.getBasePath(), filePath);
          }
        } else {
          ValidationUtils.addIssue(ip.getValidationReport(), ValidationConstants.REPRESENTATION_FILE_HAS_NO_FLOCAT,
            ValidationEntry.LEVEL.ERROR, fileType, ip.getBasePath(), representationMetsWrapper.getMetsPath());
        }
      }

      // post-process validations
      if (representation.getData().isEmpty()) {
        ValidationUtils.addIssue(ip.getValidationReport(), ValidationConstants.REPRESENTATION_HAS_NO_FILES,
          ValidationEntry.LEVEL.WARN, representationMetsWrapper.getDataDiv(), ip.getBasePath(),
          representationMetsWrapper.getMetsPath());
      }
    }

  }

  static IPInterface processSchemasMetadata(MetsWrapper metsWrapper, IPInterface ip, Path basePath)
    throws IPException {
    return processFile(ip, metsWrapper.getSchemasDiv(), IPConstants.SCHEMAS, basePath);
  }

  static IPInterface processDocumentationMetadata(MetsWrapper metsWrapper, IPInterface ip, Path basePath)
    throws IPException {
    return processFile(ip, metsWrapper.getDocumentationDiv(), IPConstants.DOCUMENTATION, basePath);
  }

  static IPInterface processAncestors(MetsWrapper metsWrapper, IPInterface ip) {
    Mets mets = metsWrapper.getMets();

    if (mets.getStructMap() != null && !mets.getStructMap().isEmpty()) {
      ip.setAncestors(EARKMETSUtils.extractAncestorsFromStructMap(mets));
    }

    return ip;
  }

  static IPInterface processSubmissionMetadata(final MetsWrapper metsWrapper, final IPInterface ip,
                                               final Path basePath) throws IPException {
    return processFile(ip, metsWrapper.getSubmissionsDiv(), IPConstants.SUBMISSION, basePath);
  }

}
