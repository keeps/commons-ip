/**
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE file at the root of the source
 * tree and available online at
 *
 * https://github.com/keeps/commons-ip
 */
package org.roda_project.commons_ip2.model.impl.eark;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.InvalidPropertiesFormatException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.xml.bind.JAXBException;

import org.apache.commons.lang3.StringUtils;
import org.roda_project.commons_ip.model.ParseException;
import org.roda_project.commons_ip.utils.IPEnums;
import org.roda_project.commons_ip.utils.IPEnums.IPStatus;
import org.roda_project.commons_ip.utils.IPException;
import org.roda_project.commons_ip.utils.ValidationConstants;
import org.roda_project.commons_ip.utils.ZipEntryInfo;
import org.roda_project.commons_ip2.mets_v1_12.beans.DivType;
import org.roda_project.commons_ip2.mets_v1_12.beans.DivType.Fptr;
import org.roda_project.commons_ip2.mets_v1_12.beans.DivType.Mptr;
import org.roda_project.commons_ip2.mets_v1_12.beans.FileGrpType;
import org.roda_project.commons_ip2.mets_v1_12.beans.FileType;
import org.roda_project.commons_ip2.mets_v1_12.beans.FileType.FLocat;
import org.roda_project.commons_ip2.mets_v1_12.beans.MdSecType;
import org.roda_project.commons_ip2.mets_v1_12.beans.MdSecType.MdRef;
import org.roda_project.commons_ip2.mets_v1_12.beans.Mets;
import org.roda_project.commons_ip2.mets_v1_12.beans.MetsType.MetsHdr.Agent;
import org.roda_project.commons_ip2.mets_v1_12.beans.StructMapType;
import org.roda_project.commons_ip2.model.AIP;
import org.roda_project.commons_ip2.model.IPConstants;
import org.roda_project.commons_ip2.model.IPContentType;
import org.roda_project.commons_ip2.model.IPDescriptiveMetadata;
import org.roda_project.commons_ip2.model.IPFile;
import org.roda_project.commons_ip2.model.IPFileShallow;
import org.roda_project.commons_ip2.model.IPHeader;
import org.roda_project.commons_ip2.model.IPInterface;
import org.roda_project.commons_ip2.model.IPMetadata;
import org.roda_project.commons_ip2.model.IPRepresentation;
import org.roda_project.commons_ip2.model.IPFileInterface;
import org.roda_project.commons_ip2.model.MetadataType;
import org.roda_project.commons_ip2.model.MetsWrapper;
import org.roda_project.commons_ip2.model.RepresentationStatus;
import org.roda_project.commons_ip2.model.SIP;
import org.roda_project.commons_ip2.model.ValidationEntry;
import org.roda_project.commons_ip2.model.impl.ModelUtils;
import org.roda_project.commons_ip2.utils.METSUtils;
import org.roda_project.commons_ip2.utils.Utils;
import org.roda_project.commons_ip2.utils.ValidationUtils;
import org.roda_project.commons_ip2.utils.ZIPUtils;
import org.slf4j.Logger;
import org.xml.sax.SAXException;

public final class EARKUtils {
  protected static boolean VALIDATION_FAIL_IF_REPRESENTATION_METS_DOES_NOT_HAVE_TWO_PARTS = false;

  private EARKUtils() {
    // do nothing
  }

  protected static void addDescriptiveMetadataToZipAndMETS(Map<String, ZipEntryInfo> zipEntries,
    MetsWrapper metsWrapper, List<IPDescriptiveMetadata> descriptiveMetadata, String representationId)
    throws IPException, InterruptedException {
    if (descriptiveMetadata != null && !descriptiveMetadata.isEmpty()) {
      for (IPDescriptiveMetadata dm : descriptiveMetadata) {
        if (Thread.interrupted()) {
          throw new InterruptedException();
        }
        IPFileInterface file = dm.getMetadata();

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

  protected static void addPreservationMetadataToZipAndMETS(Map<String, ZipEntryInfo> zipEntries,
    MetsWrapper metsWrapper, List<IPMetadata> preservationMetadata, String representationId)
    throws IPException, InterruptedException {
    if (preservationMetadata != null && !preservationMetadata.isEmpty()) {
      for (IPMetadata pm : preservationMetadata) {
        if (Thread.interrupted()) {
          throw new InterruptedException();
        }
        IPFileInterface file = pm.getMetadata();

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

  protected static void addOtherMetadataToZipAndMETS(Map<String, ZipEntryInfo> zipEntries, MetsWrapper metsWrapper,
    List<IPMetadata> otherMetadata, String representationId) throws IPException, InterruptedException {
    if (otherMetadata != null && !otherMetadata.isEmpty()) {
      for (IPMetadata om : otherMetadata) {
        if (Thread.interrupted()) {
          throw new InterruptedException();
        }
        IPFileInterface file = om.getMetadata();

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

  protected static void addRepresentationsToZipAndMETS(IPInterface ip, List<IPRepresentation> representations,
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

        IPHeader header = new IPHeader(IPEnums.IPStatus.NEW).setAgents(representation.getAgents());
        MetsWrapper representationMETSWrapper = EARKMETSUtils.generateMETS(representationId,
          representation.getDescription(), ip.getProfile(), false, Optional.empty(), null, header,
          mainMETSWrapper.getMets().getMetsHdr().getOAISPACKAGETYPE(), representation.getContentType(),
          representation.getContentInformationType());
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

  protected static void addRepresentationDataFilesToZipAndMETS(IPInterface ip, Map<String, ZipEntryInfo> zipEntries,
    MetsWrapper representationMETSWrapper, IPRepresentation representation, String representationId)
    throws IPException, InterruptedException {
    if (representation.getData() != null && !representation.getData().isEmpty()) {
      if (ip instanceof SIP) {
        ((SIP) ip).notifySipBuildRepresentationProcessingStarted(representation.getData().size());
      }
      int i = 0;
      for (IPFileInterface file : representation.getData()) {
        if (Thread.interrupted()) {
          throw new InterruptedException();
        }

        if (file instanceof IPFile) {
          String dataFilePath = IPConstants.DATA_FOLDER + ModelUtils.getFoldersFromList(file.getRelativeFolders())
              + file.getFileName();
          FileType fileType = EARKMETSUtils.addDataFileToMETS(representationMETSWrapper, dataFilePath, file.getPath());

          dataFilePath = IPConstants.REPRESENTATIONS_FOLDER + representationId + IPConstants.ZIP_PATH_SEPARATOR
              + dataFilePath;
          ZIPUtils.addFileTypeFileToZip(zipEntries, file.getPath(), dataFilePath, fileType);
        } else if (file instanceof IPFileShallow) {
          IPFileShallow shallow = (IPFileShallow) file;
          EARKMETSUtils.addDataFileToMETS(representationMETSWrapper, shallow);
        }

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

  protected static void addSchemasToZipAndMETS(Map<String, ZipEntryInfo> zipEntries, MetsWrapper metsWrapper,
                                               List<IPFileInterface> schemas, String representationId) throws IPException, InterruptedException {
    if (schemas != null && !schemas.isEmpty()) {
      for (IPFileInterface schema : schemas) {
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

  protected static void addDocumentationToZipAndMETS(Map<String, ZipEntryInfo> zipEntries, MetsWrapper metsWrapper,
                                                     List<IPFileInterface> documentation, String representationId) throws IPException, InterruptedException {
    if (documentation != null && !documentation.isEmpty()) {
      for (IPFileInterface doc : documentation) {
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

  protected static void addDefaultSchemas(Logger logger, List<IPFileInterface> schemas, Path buildDir)
    throws InterruptedException {
    try {
      if (Thread.interrupted()) {
        throw new InterruptedException();
      }
      Path earkCsipSchema = Utils.copyResourceFromClasspathToDir(EARKSIP.class, buildDir,
        IPConstants.SCHEMA_EARK_CSIP_FILENAME, IPConstants.SCHEMA_EARK_CSIP_RELATIVE_PATH_FROM_RESOURCES);
      schemas.add(new IPFile(earkCsipSchema, IPConstants.SCHEMA_EARK_CSIP_FILENAME));
      Path earkSipSchema = Utils.copyResourceFromClasspathToDir(EARKSIP.class, buildDir,
        IPConstants.SCHEMA_EARK_SIP_FILENAME, IPConstants.SCHEMA_EARK_SIP_RELATIVE_PATH_FROM_RESOURCES);
      schemas.add(new IPFile(earkSipSchema, IPConstants.SCHEMA_EARK_SIP_FILENAME));
      Path metsSchema = Utils.copyResourceFromClasspathToDir(EARKSIP.class, buildDir,
        IPConstants.SCHEMA_METS_FILENAME_WITH_VERSION, IPConstants.SCHEMA_METS_RELATIVE_PATH_FROM_RESOURCES);
      schemas.add(new IPFile(metsSchema, IPConstants.SCHEMA_METS_FILENAME_WITH_VERSION));
      Path xlinkSchema = Utils.copyResourceFromClasspathToDir(EARKSIP.class, buildDir,
        IPConstants.SCHEMA_XLINK_FILENAME, IPConstants.SCHEMA_XLINK_RELATIVE_PATH_FROM_RESOURCES);
      schemas.add(new IPFile(xlinkSchema, IPConstants.SCHEMA_XLINK_FILENAME));
    } catch (IOException e) {
      logger.error("Error while trying to add default schemas", e);
    }
  }

  protected static void addSubmissionsToZipAndMETS(final Map<String, ZipEntryInfo> zipEntries,
    final MetsWrapper metsWrapper, final List<IPFileInterface> submissions) throws IPException, InterruptedException {
    if (submissions != null && !submissions.isEmpty()) {
      for (IPFileInterface submission : submissions) {
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

  protected static MetsWrapper processMainMets(IPInterface ip, Path ipPath) {
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

  protected static void setIPContentType(Mets mets, IPInterface ip) throws ParseException {
    String oaisPackageType = mets.getMetsHdr().getOAISPACKAGETYPE();

    if (StringUtils.isBlank(oaisPackageType)) {
      throw new ParseException("METS 'OAISPACKAGETYPE' attribute does not contain any value");
    }

    try {
      IPEnums.IPType packageType = IPEnums.IPType.valueOf(oaisPackageType);

      if (ip instanceof SIP && IPEnums.IPType.SIP != packageType) {
        throw new ParseException(
          "METS 'OAISPACKAGETYPE' should have the value 'SIP' but it doesn't. Instead, it has '" + packageType + "'.");
      } else if (ip instanceof AIP && IPEnums.IPType.AIP != packageType) {
        throw new ParseException(
          "METS 'OAISPACKAGETYPE' should have the value 'AIP' but it doesn't. Instead, it has '" + packageType + "'.");
      }
    } catch (IllegalArgumentException e) {
      throw new ParseException("METS 'OAISPACKAGETYPE' attribute does not contain a valid package type");
    }

    String type = "".equals(mets.getOTHERCONTENTINFORMATIONTYPE()) ? mets.getCONTENTINFORMATIONTYPE()
      : mets.getOTHERCONTENTINFORMATIONTYPE();
    ip.setContentType(new IPContentType(type));
  }

  protected static Mets addAgentsToMETS(Mets mets, IPInterface ip, IPRepresentation representation) {
    if (mets.getMetsHdr() != null && mets.getMetsHdr().getAgent() != null) {
      for (Agent agent : mets.getMetsHdr().getAgent()) {
        if (representation == null) {
          ip.addAgent(EARKMETSUtils.createIPAgent(ip, agent));
        } else {
          representation.addAgent(EARKMETSUtils.createIPAgent(ip, agent));
        }
      }
    }

    return mets;
  }

  protected static MetsWrapper processRepresentationMets(IPInterface ip, Path representationMetsFile,
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

  // FIXME review this
  protected static void setRepresentationContentType(Mets mets, IPRepresentation representation) throws ParseException {
    String contentType = mets.getCONTENTINFORMATIONTYPE();
    if (StringUtils.isBlank(contentType)) {
      throw new ParseException("METS 'CONTENTINFORMATIONTYPE' attribute does not contain any value");
    }

    if (!"".equals(mets.getOTHERCONTENTINFORMATIONTYPE())) {
      contentType = mets.getOTHERCONTENTINFORMATIONTYPE();
    }

    representation.setContentType(new IPContentType(contentType));
  }

  protected static IPInterface processRepresentations(MetsWrapper metsWrapper, IPInterface ip, Logger logger)
    throws IPException {

    if (metsWrapper.getMainDiv() != null && metsWrapper.getMainDiv().getDiv() != null) {
      for (DivType div : metsWrapper.getMainDiv().getDiv()) {
        if (div.getLABEL().startsWith(IPConstants.REPRESENTATIONS_WITH_FIRST_LETTER_CAPITAL)) {
          if (div.getMptr() != null && !div.getMptr().isEmpty()) {
            // we can assume one and only one mets for each representation div
            Mptr mptr = div.getMptr().get(0);
            String href = Utils.extractedRelativePathFromHref(mptr.getHref());
            Path metsFilePath = ip.getBasePath().resolve(href);
            IPRepresentation representation = new IPRepresentation(
              div.getLABEL().replaceFirst(IPConstants.REPRESENTATIONS_WITH_FIRST_LETTER_CAPITAL + "/", ""));
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
                processDescriptiveMetadata(representationMetsWrapper, ip, logger, representation,
                  representationBasePath);

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
      }

      // post-process validations
      if (ip.getRepresentations().isEmpty()) {
        ValidationUtils.addIssue(ip.getValidationReport(), ValidationConstants.MAIN_METS_NO_REPRESENTATIONS_FOUND,
          ValidationEntry.LEVEL.WARN, metsWrapper.getMainDiv(), ip.getBasePath(), metsWrapper.getMetsPath());
      }
    }

    return ip;

  }

  protected static StructMapType getEARKStructMap(MetsWrapper metsWrapper, IPInterface ip, boolean mainMets) {
    Mets mets = metsWrapper.getMets();
    StructMapType res = null;
    for (StructMapType structMap : mets.getStructMap()) {
      if (StringUtils.equals(structMap.getLABEL(), IPConstants.COMMON_SPEC_STRUCTURAL_MAP)) {
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

  protected static void preProcessStructMap(MetsWrapper metsWrapper, StructMapType structMap) {

    DivType ipDiv = structMap.getDiv();
    if (ipDiv.getDiv() != null) {
      metsWrapper.setMainDiv(ipDiv);
      for (DivType firstLevel : ipDiv.getDiv()) {
        if (IPConstants.METADATA.equalsIgnoreCase(firstLevel.getLABEL()) && firstLevel.getDiv() != null) {
          metsWrapper.setMetadataDiv(firstLevel);
        } else if ((IPConstants.METADATA + "/" + IPConstants.OTHER).equalsIgnoreCase(firstLevel.getLABEL())) {
          metsWrapper.setOtherMetadataDiv(firstLevel);
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

  protected static IPInterface processDescriptiveMetadata(MetsWrapper metsWrapper, IPInterface ip, Logger logger,
    IPRepresentation representation, Path basePath) throws IPException {

    return processMetadata(ip, logger, metsWrapper, representation, metsWrapper.getMetadataDiv(),
      IPConstants.DESCRIPTIVE, basePath);
  }

  protected static IPInterface processOtherMetadata(MetsWrapper metsWrapper, IPInterface ip, Logger logger,
    IPRepresentation representation, Path basePath) throws IPException {

    return processMetadata(ip, logger, metsWrapper, representation, metsWrapper.getOtherMetadataDiv(),
      IPConstants.OTHER, basePath);
  }

  protected static IPInterface processPreservationMetadata(MetsWrapper metsWrapper, IPInterface ip, Logger logger,
    IPRepresentation representation, Path basePath) throws IPException {

    return processMetadata(ip, logger, metsWrapper, representation, metsWrapper.getMetadataDiv(),
      IPConstants.PRESERVATION, basePath);
  }

  protected static IPInterface processMetadata(IPInterface ip, Logger logger, MetsWrapper representationMetsWrapper,
    IPRepresentation representation, DivType div, String metadataType, Path basePath) throws IPException {
    if (div != null) {
      List<Object> objects = null;
      if (IPConstants.DESCRIPTIVE.equals(metadataType) || IPConstants.OTHER.equals(metadataType)) {
        objects = div.getDMDID();
      } else if (IPConstants.PRESERVATION.equals(metadataType)) {
        objects = div.getADMID();
      }

      if (objects != null) {
        for (Object obj : objects) {
          if (obj instanceof MdSecType) {
            MdRef mdRef = ((MdSecType) obj).getMdRef();
            if (mdRef != null) {
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
          }
        }
      }
    }

    return ip;
  }

  protected static void processMetadataFile(IPInterface ip, Logger logger, IPRepresentation representation,
    String metadataType, MdRef mdRef, Path filePath, List<String> fileRelativeFolders) throws IPException {
    Optional<IPFileInterface> metadataFile = validateMetadataFile(ip, filePath, mdRef, fileRelativeFolders);
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

  protected static Optional<IPFileInterface> validateFile(IPInterface ip, Path filePath, FileType fileType,
    List<String> fileRelativeFolders) {
    return Utils.validateFile(ip, filePath, fileRelativeFolders, fileType.getCHECKSUM(), fileType.getCHECKSUMTYPE(),
      fileType.getID());
  }

  protected static Optional<IPFileInterface> validateMetadataFile(IPInterface ip, Path filePath, MdRef mdRef,
    List<String> fileRelativeFolders) {
    return Utils.validateFile(ip, filePath, fileRelativeFolders, mdRef.getCHECKSUM(), mdRef.getCHECKSUMTYPE(),
      mdRef.getID());
  }

  protected static IPInterface processFile(IPInterface ip, DivType div, String folder, Path basePath)
    throws IPException {
    if (div != null && div.getFptr() != null) {
      for (Fptr fptr : div.getFptr()) {
        Object object = fptr.getFILEID();
        if (object instanceof FileGrpType) {
          FileGrpType fileGrp = ((FileGrpType) object);
          for (FileType fileType : fileGrp.getFile()) {
            if (fileType.getFLocat() != null) {
              FLocat fLocat = fileType.getFLocat().get(0);
              String href = Utils.extractedRelativePathFromHref(fLocat.getHref());
              Path filePath = basePath.resolve(href);

              if (Files.exists(filePath)) {
                List<String> fileRelativeFolders = Utils.getFileRelativeFolders(basePath.resolve(folder), filePath);
                Optional<IPFileInterface> file = validateFile(ip, filePath, fileType, fileRelativeFolders);

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

      }
    }

    return ip;
  }

  protected static void processRepresentationAgents(MetsWrapper representationMetsWrapper,
    IPRepresentation representation) {
    addAgentsToMETS(representationMetsWrapper.getMets(), null, representation);
  }

  protected static void processRepresentationFiles(IPInterface ip, MetsWrapper representationMetsWrapper,
    IPRepresentation representation, Path representationBasePath) throws IPException {

    if (representationMetsWrapper.getDataDiv() != null && representationMetsWrapper.getDataDiv().getFptr() != null) {
      for (Fptr fptr : representationMetsWrapper.getDataDiv().getFptr()) {
        Object object = fptr.getFILEID();
        if (object instanceof FileGrpType) {
          FileGrpType fileGrp = ((FileGrpType) object);
          for (FileType fileType : fileGrp.getFile()) {
            if (fileType != null && fileType.getFLocat() != null) {
              FLocat fLocat = fileType.getFLocat().get(0);
              String href = Utils.extractedRelativePathFromHref(fLocat.getHref());
              Path filePath = representationBasePath.resolve(href);
              if (Files.exists(filePath)) {
                List<String> fileRelativeFolders = Utils
                  .getFileRelativeFolders(representationBasePath.resolve(IPConstants.DATA), filePath);
                Optional<IPFileInterface> file = validateFile(ip, filePath, fileType, fileRelativeFolders);

                if (file.isPresent()) {
                  representation.addFile(file.get());
                  ValidationUtils.addInfo(ip.getValidationReport(),
                    ValidationConstants.REPRESENTATION_FILE_FOUND_WITH_MATCHING_CHECKSUMS, ip.getBasePath(), filePath);
                }
              } else {
                //TODO:Verify if path has a protocol
                if(URI.create(href).getScheme() == null){
                  ValidationUtils.addIssue(ip.getValidationReport(), ValidationConstants.REPRESENTATION_FILE_NOT_FOUND,
                      ValidationEntry.LEVEL.ERROR, ip.getBasePath(), filePath);
                } else {
                  try {
                    IPFileShallow ipFile = new IPFileShallow(URI.create(href).toURL(), fileType);
                    representation.addFile(ipFile);
                  } catch ( MalformedURLException e) {
                    ValidationUtils.addIssue(ip.getValidationReport(), ValidationConstants.REPRESENTATION_FILE_NOT_FOUND,
                        ValidationEntry.LEVEL.ERROR, ip.getBasePath(), filePath);
                  }
                }
              }
            } else {
              ValidationUtils.addIssue(ip.getValidationReport(), ValidationConstants.REPRESENTATION_FILE_HAS_NO_FLOCAT,
                ValidationEntry.LEVEL.ERROR, fileType, ip.getBasePath(), representationMetsWrapper.getMetsPath());
            }
          }
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

  protected static IPInterface processSchemasMetadata(MetsWrapper metsWrapper, IPInterface ip, Path basePath)
    throws IPException {
    return processFile(ip, metsWrapper.getSchemasDiv(), IPConstants.SCHEMAS, basePath);
  }

  protected static IPInterface processDocumentationMetadata(MetsWrapper metsWrapper, IPInterface ip, Path basePath)
    throws IPException {
    return processFile(ip, metsWrapper.getDocumentationDiv(), IPConstants.DOCUMENTATION, basePath);
  }

  protected static IPInterface processAncestors(MetsWrapper metsWrapper, IPInterface ip) {
    Mets mets = metsWrapper.getMets();

    if (mets.getStructMap() != null && !mets.getStructMap().isEmpty()) {
      ip.setAncestors(EARKMETSUtils.extractAncestorsFromStructMap(mets));
    }

    return ip;
  }

  protected static IPInterface processSubmissionMetadata(final MetsWrapper metsWrapper, final IPInterface ip,
    final Path basePath) throws IPException {
    return processFile(ip, metsWrapper.getSubmissionsDiv(), IPConstants.SUBMISSION, basePath);
  }

}
