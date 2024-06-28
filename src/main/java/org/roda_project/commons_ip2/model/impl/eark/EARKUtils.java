/**
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE file at the root of the source
 * tree and available online at
 *
 * https://github.com/keeps/commons-ip
 */
package org.roda_project.commons_ip2.model.impl.eark;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
import org.roda_project.commons_ip2.model.IPFileInterface;
import org.roda_project.commons_ip2.model.IPFileShallow;
import org.roda_project.commons_ip2.model.IPHeader;
import org.roda_project.commons_ip2.model.IPInterface;
import org.roda_project.commons_ip2.model.IPMetadata;
import org.roda_project.commons_ip2.model.IPRepresentation;
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

import jakarta.xml.bind.JAXBException;

public class EARKUtils {

  private final EARKMETSCreator metsGenerator;

  public EARKUtils(EARKMETSCreator metsGenerator) {
    this.metsGenerator = metsGenerator;
  }

  protected void addDescriptiveMetadataToZipAndMETS(Map<String, ZipEntryInfo> zipEntries, MetsWrapper metsWrapper,
    List<IPDescriptiveMetadata> descriptiveMetadata, String representationId) throws IPException, InterruptedException {
    if (descriptiveMetadata != null && !descriptiveMetadata.isEmpty()) {
      for (IPDescriptiveMetadata dm : descriptiveMetadata) {
        if (Thread.interrupted()) {
          throw new InterruptedException();
        }
        IPFileInterface file = dm.getMetadata();

        String descriptiveFilePath = IPConstants.DESCRIPTIVE_FOLDER
          + ModelUtils.getFoldersFromList(file.getRelativeFolders()) + file.getFileName();
        MdRef mdRef = metsGenerator.addDescriptiveMetadataToMETS(metsWrapper, dm, descriptiveFilePath);

        if (representationId != null) {
          descriptiveFilePath = IPConstants.REPRESENTATIONS_FOLDER + representationId + IPConstants.ZIP_PATH_SEPARATOR
            + descriptiveFilePath;
        }
        ZIPUtils.addMdRefFileToZip(zipEntries, file, descriptiveFilePath, mdRef);
      }
    }
  }

  protected void addPreservationMetadataToZipAndMETS(Map<String, ZipEntryInfo> zipEntries, MetsWrapper metsWrapper,
    List<IPMetadata> preservationMetadata, String representationId) throws IPException, InterruptedException {
    if (preservationMetadata != null && !preservationMetadata.isEmpty()) {
      for (IPMetadata pm : preservationMetadata) {
        if (Thread.interrupted()) {
          throw new InterruptedException();
        }
        IPFileInterface file = pm.getMetadata();

        String preservationMetadataPath = IPConstants.PRESERVATION_FOLDER
          + ModelUtils.getFoldersFromList(file.getRelativeFolders()) + file.getFileName();
        MdRef mdRef = metsGenerator.addPreservationMetadataToMETS(metsWrapper, pm, preservationMetadataPath);

        if (representationId != null) {
          preservationMetadataPath = IPConstants.REPRESENTATIONS_FOLDER + representationId
            + IPConstants.ZIP_PATH_SEPARATOR + preservationMetadataPath;
        }
        ZIPUtils.addMdRefFileToZip(zipEntries, file, preservationMetadataPath, mdRef);
      }
    }
  }

  protected void addOtherMetadataToZipAndMETS(Map<String, ZipEntryInfo> zipEntries, MetsWrapper metsWrapper,
    List<IPMetadata> otherMetadata, String representationId) throws IPException, InterruptedException {
    if (otherMetadata != null && !otherMetadata.isEmpty()) {
      for (IPMetadata om : otherMetadata) {
        if (Thread.interrupted()) {
          throw new InterruptedException();
        }
        IPFileInterface file = om.getMetadata();

        String otherMetadataPath = IPConstants.OTHER_FOLDER + ModelUtils.getFoldersFromList(file.getRelativeFolders())
          + file.getFileName();
        MdRef mdRef = metsGenerator.addOtherMetadataToMETS(metsWrapper, om, otherMetadataPath);

        if (representationId != null) {
          otherMetadataPath = IPConstants.REPRESENTATIONS_FOLDER + representationId + IPConstants.ZIP_PATH_SEPARATOR
            + otherMetadataPath;
        }
        ZIPUtils.addMdRefFileToZip(zipEntries, file, otherMetadataPath, mdRef);
      }
    }
  }

  protected void addRepresentationsToZipAndMETS(IPInterface ip, List<IPRepresentation> representations,
    Map<String, ZipEntryInfo> zipEntries, MetsWrapper mainMETSWrapper, Path buildDir, IPEnums.SipType sipType)
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
        final boolean isRepresentationMetadataOther = (representation.getOtherMetadata() != null
          && !representation.getOtherMetadata().isEmpty());
        final boolean isRepresentationMetadata = ((representation.getDescriptiveMetadata() != null
          && !representation.getDescriptiveMetadata().isEmpty())
          || (representation.getPreservationMetadata() != null && !representation.getPreservationMetadata().isEmpty()));
        final boolean isRepresentationDocumentation = (representation.getDocumentation() != null
          && !representation.getDocumentation().isEmpty());
        final boolean isRepresentationSchemas = (representation.getSchemas() != null
          && !representation.getSchemas().isEmpty());
        final boolean isRepresentationsData = (representation.getData() != null && !representation.getData().isEmpty());
        final IPHeader header = new IPHeader(IPEnums.IPStatus.NEW).setAgents(representation.getAgents());
        final MetsWrapper representationMETSWrapper;
        if (!IPEnums.SipType.EARK2S.equals(sipType)) {
          representationMETSWrapper = metsGenerator.generateMETS(representationId, representation.getDescription(),
            ip.getProfile(), false, Optional.empty(), null, header,
            mainMETSWrapper.getMets().getMetsHdr().getOAISPACKAGETYPE(), representation.getContentType(),
            representation.getContentInformationType(), isRepresentationMetadata, isRepresentationMetadataOther,
            isRepresentationSchemas, isRepresentationDocumentation, false, false, isRepresentationsData);
        } else {
          representationMETSWrapper = metsGenerator.generateMetsShallow(representation, ip.getProfile(), false,
            Optional.empty(), null, header, mainMETSWrapper.getMets().getMetsHdr().getOAISPACKAGETYPE(),
            isRepresentationMetadata, isRepresentationMetadataOther, isRepresentationSchemas,
            isRepresentationDocumentation, false, false, isRepresentationsData);
        }

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
        metsGenerator.addRepresentationMETSToZipAndToMainMETS(zipEntries, mainMETSWrapper, representationId,
          representationMETSWrapper, IPConstants.REPRESENTATIONS_FOLDER + representationId
            + IPConstants.ZIP_PATH_SEPARATOR + IPConstants.METS_FILE,
          buildDir);

        metsGenerator.cleanFileGrpStructure();
      }
      if (ip instanceof SIP) {
        ((SIP) ip).notifySipBuildRepresentationsProcessingEnded();
      }
    }
  }

  protected void addRepresentationDataFilesToZipAndMETS(IPInterface ip, Map<String, ZipEntryInfo> zipEntries,
    MetsWrapper representationMETSWrapper, IPRepresentation representation, String representationId)
    throws IPException, InterruptedException {
    if (representation.getData() != null && !representation.getData().isEmpty()) {
      if (ip instanceof SIP sip) {
        sip.notifySipBuildRepresentationProcessingStarted(representation.getData().size());
      }
      int i = 0;
      for (IPFileInterface file : representation.getData()) {
        if (Thread.interrupted()) {
          throw new InterruptedException();
        }

        if (file instanceof IPFile) {
          String dataFilePath = IPConstants.DATA_FOLDER + ModelUtils.getFoldersFromList(file.getRelativeFolders())
            + file.getFileName();
          FileType fileType = metsGenerator.addDataFileToMETS(representationMETSWrapper, dataFilePath, file.getPath());

          dataFilePath = IPConstants.REPRESENTATIONS_FOLDER + representationId + IPConstants.ZIP_PATH_SEPARATOR
            + dataFilePath;
          ZIPUtils.addFileTypeFileToZip(zipEntries, file, dataFilePath, fileType);
        } else if (file instanceof IPFileShallow shallow && (shallow.getFileLocation() != null)) {
          metsGenerator.addDataFileToMETS(representationMETSWrapper, shallow);
        }

        i++;
        if (ip instanceof SIP sip) {
          sip.notifySipBuildRepresentationProcessingCurrentStatus(i);
        }
      }
      if (ip instanceof SIP sip) {
        sip.notifySipBuildRepresentationProcessingEnded();
      }
    }
  }

  protected void addSchemasToZipAndMETS(Map<String, ZipEntryInfo> zipEntries, MetsWrapper metsWrapper,
    List<IPFileInterface> schemas, String representationId) throws IPException, InterruptedException {
    if (schemas != null && !schemas.isEmpty()) {
      for (IPFileInterface schema : schemas) {
        if (Thread.interrupted()) {
          throw new InterruptedException();
        }

        String schemaFilePath = IPConstants.SCHEMAS_FOLDER + ModelUtils.getFoldersFromList(schema.getRelativeFolders())
          + schema.getFileName();
        FileType fileType = metsGenerator.addSchemaFileToMETS(metsWrapper, schemaFilePath, schema.getPath());
        fileType.setCHECKSUM(schema.getChecksum());
        fileType.setCHECKSUMTYPE(schema.getChecksumAlgorithm());

        if (representationId != null) {
          schemaFilePath = IPConstants.REPRESENTATIONS_FOLDER + representationId + IPConstants.ZIP_PATH_SEPARATOR
            + schemaFilePath;
        }
        ZIPUtils.addFileTypeFileToZip(zipEntries, schema, schemaFilePath, fileType);
      }
    }
  }

  protected void addDocumentationToZipAndMETS(Map<String, ZipEntryInfo> zipEntries, MetsWrapper metsWrapper,
    List<IPFileInterface> documentation, String representationId) throws IPException, InterruptedException {
    if (documentation != null && !documentation.isEmpty()) {
      for (IPFileInterface doc : documentation) {
        if (Thread.interrupted()) {
          throw new InterruptedException();
        }

        String documentationFilePath = IPConstants.DOCUMENTATION_FOLDER
          + ModelUtils.getFoldersFromList(doc.getRelativeFolders()) + doc.getFileName();
        FileType fileType = metsGenerator.addDocumentationFileToMETS(metsWrapper, documentationFilePath, doc.getPath());

        if (representationId != null) {
          documentationFilePath = IPConstants.REPRESENTATIONS_FOLDER + representationId + IPConstants.ZIP_PATH_SEPARATOR
            + documentationFilePath;
        }
        ZIPUtils.addFileTypeFileToZip(zipEntries, doc, documentationFilePath, fileType);
      }
    }
  }

  protected void addDefaultSchemas(Logger logger, List<IPFileInterface> schemas, Path buildDir, Boolean override)
    throws InterruptedException {
    try {
      if (Thread.interrupted()) {
        throw new InterruptedException();
      }

      String tempSchema = "";
      if (schemas.size() != 0) {

        tempSchema = schemas.get(0).getFileName();

        if (!override) {
          if (tempSchema.equals(IPConstants.SCHEMA_EARK_CSIP_FILENAME)
            || tempSchema.equals(IPConstants.SCHEMA_EARK_SIP_FILENAME)
            || tempSchema.equals(IPConstants.SCHEMA_METS_FILENAME_WITH_VERSION)
            || tempSchema.equals(IPConstants.SCHEMA_XLINK_FILENAME)) {
            schemas.remove(0);
            tempSchema = "";
          }
        }
      }

      Path earkCsipSchema = Utils.copyResourceFromClasspathToDir(EARKSIP.class, buildDir,
        IPConstants.SCHEMA_EARK_CSIP_FILENAME, IPConstants.SCHEMA_EARK_CSIP_RELATIVE_PATH_FROM_RESOURCES);
      if (!tempSchema.equals(IPConstants.SCHEMA_EARK_CSIP_FILENAME))
        schemas.add(new IPFile(earkCsipSchema, IPConstants.SCHEMA_EARK_CSIP_FILENAME));
      Path earkSipSchema = Utils.copyResourceFromClasspathToDir(EARKSIP.class, buildDir,
        IPConstants.SCHEMA_EARK_SIP_FILENAME, IPConstants.SCHEMA_EARK_SIP_RELATIVE_PATH_FROM_RESOURCES);
      if (!tempSchema.equals(IPConstants.SCHEMA_EARK_SIP_FILENAME))
        schemas.add(new IPFile(earkSipSchema, IPConstants.SCHEMA_EARK_SIP_FILENAME));
      Path metsSchema = Utils.copyResourceFromClasspathToDir(EARKSIP.class, buildDir,
        IPConstants.SCHEMA_METS_FILENAME_WITH_VERSION, IPConstants.SCHEMA_METS_RELATIVE_PATH_FROM_RESOURCES);
      if (!tempSchema.equals(IPConstants.SCHEMA_METS_FILENAME_WITH_VERSION))
        schemas.add(new IPFile(metsSchema, IPConstants.SCHEMA_METS_FILENAME_WITH_VERSION));
      Path xlinkSchema = Utils.copyResourceFromClasspathToDir(EARKSIP.class, buildDir,
        IPConstants.SCHEMA_XLINK_FILENAME, IPConstants.SCHEMA_XLINK_RELATIVE_PATH_FROM_RESOURCES);
      if (!tempSchema.equals(IPConstants.SCHEMA_XLINK_FILENAME))
        schemas.add(new IPFile(xlinkSchema, IPConstants.SCHEMA_XLINK_FILENAME));

    } catch (IOException e) {
      logger.error("Error while trying to add default schemas", e);
    }
  }

  protected void addSubmissionsToZipAndMETS(final Map<String, ZipEntryInfo> zipEntries, final MetsWrapper metsWrapper,
    final List<IPFileInterface> submissions) throws IPException, InterruptedException {
    if (submissions != null && !submissions.isEmpty()) {
      for (IPFileInterface submission : submissions) {
        if (Thread.interrupted()) {
          throw new InterruptedException();
        }
        final String submissionFilePath = IPConstants.SUBMISSION_FOLDER
          + ModelUtils.getFoldersFromList(submission.getRelativeFolders()) + submission.getFileName();
        final FileType fileType = metsGenerator.addSubmissionFileToMETS(metsWrapper, submissionFilePath,
          submission.getPath());
        ZIPUtils.addFileTypeFileToZip(zipEntries, submission, submissionFilePath, fileType);
      }
    }
  }

  protected MetsWrapper processMainMets(IPInterface ip, Path ipPath) {
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
      } catch (JAXBException | ParseException | SAXException | IOException e) {
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

  protected void setIPContentType(Mets mets, IPInterface ip) throws ParseException {
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

  protected void addAgentsToMETS(Mets mets, IPInterface ip, IPRepresentation representation) {
    if (mets.getMetsHdr() != null && mets.getMetsHdr().getAgent() != null) {
      for (Agent agent : mets.getMetsHdr().getAgent()) {
        if (representation == null) {
          ip.addAgent(metsGenerator.createIPAgent(ip, agent));
        } else {
          representation.addAgent(metsGenerator.createIPAgent(ip, agent));
        }
      }
    }
  }

  protected MetsWrapper processRepresentationMets(IPInterface ip, Path representationMetsFile,
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
      } catch (JAXBException | ParseException | SAXException | IOException e) {
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
  protected void setRepresentationContentType(Mets mets, IPRepresentation representation) throws ParseException {
    String contentType = mets.getCONTENTINFORMATIONTYPE();
    if (StringUtils.isBlank(contentType)) {
      throw new ParseException("METS 'CONTENTINFORMATIONTYPE' attribute does not contain any value");
    }

    if (!"".equals(mets.getOTHERCONTENTINFORMATIONTYPE())) {
      contentType = mets.getOTHERCONTENTINFORMATIONTYPE();
    }

    representation.setContentType(new IPContentType(contentType));
  }

  protected IPInterface processRepresentations(MetsWrapper metsWrapper, IPInterface ip, Logger logger)
    throws IPException {

    if (metsWrapper.getMainDiv() != null && metsWrapper.getMainDiv().getDiv() != null) {
      for (DivType div : metsWrapper.getMainDiv().getDiv()) {
        if (div.getLABEL().startsWith(IPConstants.REPRESENTATIONS_WITH_FIRST_LETTER_CAPITAL)
          && (div.getMptr() != null && !div.getMptr().isEmpty())) {
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
          ValidationEntry.LEVEL.WARN, metsWrapper.getMainDiv(), ip.getBasePath(), metsWrapper.getMetsPath());
      }
    }

    return ip;

  }

  protected StructMapType getEARKStructMap(MetsWrapper metsWrapper, IPInterface ip, boolean mainMets) {
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

  protected void preProcessStructMap(MetsWrapper metsWrapper, StructMapType structMap) {

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

  protected void processDescriptiveMetadata(MetsWrapper metsWrapper, IPInterface ip, Logger logger,
    IPRepresentation representation, Path basePath) throws IPException {

    processMetadata(ip, logger, representation, metsWrapper.getMetadataDiv(), IPConstants.DESCRIPTIVE, basePath);
  }

  protected void processOtherMetadata(MetsWrapper metsWrapper, IPInterface ip, Logger logger,
    IPRepresentation representation, Path basePath) throws IPException {

    processMetadata(ip, logger, representation, metsWrapper.getOtherMetadataDiv(), IPConstants.OTHER, basePath);
  }

  protected void processPreservationMetadata(MetsWrapper metsWrapper, IPInterface ip, Logger logger,
    IPRepresentation representation, Path basePath) throws IPException {

    processMetadata(ip, logger, representation, metsWrapper.getMetadataDiv(), IPConstants.PRESERVATION, basePath);
  }

  protected void processMetadata(IPInterface ip, Logger logger, IPRepresentation representation, DivType div,
    String metadataType, Path basePath) throws IPException {
    if (div != null) {
      List<Object> objects = null;
      if (IPConstants.DESCRIPTIVE.equals(metadataType) || IPConstants.OTHER.equals(metadataType)) {
        objects = div.getDMDID();
      } else if (IPConstants.PRESERVATION.equals(metadataType)) {
        objects = div.getADMID();
      }

      if (objects != null) {
        for (Object obj : objects) {
          if (obj instanceof MdSecType mdSecType) {
            MdRef mdRef = mdSecType.getMdRef();
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
  }

  protected void processMetadataFile(IPInterface ip, Logger logger, IPRepresentation representation,
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
        preservationMetadata.setMetadataType(MetadataType.MetadataTypeEnum.valueOf(mdRef.getMDTYPE()));
        preservationMetadata.setId(mdRef.getID());
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

  protected Optional<IPFileInterface> validateFile(IPInterface ip, Path filePath, FileType fileType,
    List<String> fileRelativeFolders) {
    return Utils.validateFile(ip, filePath, fileRelativeFolders, fileType.getCHECKSUM(), fileType.getCHECKSUMTYPE(),
      fileType.getID());
  }

  protected Optional<IPFileInterface> validateMetadataFile(IPInterface ip, Path filePath, MdRef mdRef,
    List<String> fileRelativeFolders) {
    return Utils.validateFile(ip, filePath, fileRelativeFolders, mdRef.getCHECKSUM(), mdRef.getCHECKSUMTYPE(),
      mdRef.getID());
  }

  protected IPInterface processFile(IPInterface ip, DivType div, String folder, Path basePath) {
    if (div != null && div.getFptr() != null) {
      for (Fptr fptr : div.getFptr()) {
        Object object = fptr.getFILEID();
        if (object instanceof FileGrpType fileGrp) {
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
                  } else if (IPConstants.SUBMISSION.equalsIgnoreCase(folder) && ip instanceof AIP aip) {
                    ValidationUtils.addInfo(ip.getValidationReport(),
                      ValidationConstants.SUBMISSION_FILE_FOUND_WITH_MATCHING_CHECKSUMS, ip.getBasePath(), filePath);
                    aip.addSubmission(file.get());
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

  protected void processRepresentationAgents(MetsWrapper representationMetsWrapper, IPRepresentation representation) {
    addAgentsToMETS(representationMetsWrapper.getMets(), null, representation);
  }

  protected void processRepresentationFiles(IPInterface ip, MetsWrapper representationMetsWrapper,
    IPRepresentation representation, Path representationBasePath) throws IPException {

    if (representationMetsWrapper.getDataDiv() != null && representationMetsWrapper.getDataDiv().getFptr() != null) {
      for (Fptr fptr : representationMetsWrapper.getDataDiv().getFptr()) {
        Object object = fptr.getFILEID();
        if (object instanceof FileGrpType fileGrp) {
          for (FileType fileType : fileGrp.getFile()) {
            if (fileType != null && fileType.getFLocat() != null) {
              FLocat fLocat = fileType.getFLocat().get(0);
              String href = Utils.extractedRelativePathFromHref(fLocat.getHref());
              Path filePath = representationBasePath.resolve(href);

              // Verify that when protocol is file:/// the file is inside the SIP or not
              if (filePath.startsWith(representationBasePath)) {
                // treat as a SIP (generic behaviour)
                if (Files.exists(filePath)) {
                  List<String> fileRelativeFolders = Utils
                    .getFileRelativeFolders(representationBasePath.resolve(IPConstants.DATA), filePath);
                  Optional<IPFileInterface> file = validateFile(ip, filePath, fileType, fileRelativeFolders);

                  if (file.isPresent()) {
                    representation.addFile(file.get());
                    ValidationUtils.addInfo(ip.getValidationReport(),
                      ValidationConstants.REPRESENTATION_FILE_FOUND_WITH_MATCHING_CHECKSUMS, ip.getBasePath(),
                      filePath);
                  }
                } else {
                  // treat as a SIP shallow
                  Optional<IPFileInterface> ipFileInterface = validateFileShallow(ip, fLocat, filePath, fileType,
                    Collections.emptyList());
                  ipFileInterface.ifPresent(representation::addFile);
                }
              } else {
                // treat as a SIP shallow
                Optional<IPFileInterface> ipFileInterface = validateFileShallow(ip, fLocat, filePath, fileType,
                  Collections.emptyList());
                ipFileInterface.ifPresent(representation::addFile);
              }
            } else {
              ValidationUtils.addIssue(ip.getValidationReport(), ValidationConstants.REPRESENTATION_FILE_HAS_NO_FLOCAT,
                ValidationEntry.LEVEL.ERROR, fileType, ip.getBasePath(), representationMetsWrapper.getMetsPath());
            }
          }
        }
      }

      for (DivType subDiv : representationMetsWrapper.getDataDiv().getDiv()) {
        final List<String> subDivRelativePath = new ArrayList<>();
        subDivRelativePath.add(subDiv.getLABEL());
        processRepresentationFilesSubDivs(ip, representationMetsWrapper, representation, representationBasePath, subDiv,
          subDivRelativePath);
      }

      // post-process validations
      if (representation.getData().isEmpty()) {
        ValidationUtils.addIssue(ip.getValidationReport(), ValidationConstants.REPRESENTATION_HAS_NO_FILES,
          ValidationEntry.LEVEL.WARN, representationMetsWrapper.getDataDiv(), ip.getBasePath(),
          representationMetsWrapper.getMetsPath());
      }
    }
  }

  protected void processRepresentationFilesSubDivs(IPInterface ip, MetsWrapper representationMetsWrapper,
    IPRepresentation representation, Path representationBasePath, DivType div, List<String> relativePath)
    throws IPException {

    final List<Fptr> fptrs = div.getFptr();
    if (fptrs != null && !fptrs.isEmpty()) {
      for (Fptr fptr : fptrs) {
        final Object object = fptr.getFILEID();
        if (object instanceof FileGrpType fileGrp) {
          for (FileType fileType : fileGrp.getFile()) {
            if (fileType != null && fileType.getFLocat() != null) {
              final FLocat fLocat = fileType.getFLocat().get(0);
              final String href = Utils.extractedRelativePathFromHref(fLocat.getHref());
              final Path filePath = representationBasePath.resolve(href);

              // Verify that when protocol is file:/// the file is inside the SIP or not
              if (filePath.startsWith(representationBasePath)) {
                // treat as a SIP (generic behaviour)
                if (Files.exists(filePath)) {
                  final List<String> fileRelativeFolders = Utils
                    .getFileRelativeFolders(representationBasePath.resolve(IPConstants.DATA), filePath);
                  final Optional<IPFileInterface> file = validateFile(ip, filePath, fileType, fileRelativeFolders);

                  if (file.isPresent()) {
                    representation.addFile(file.get());
                    ValidationUtils.addInfo(ip.getValidationReport(),
                      ValidationConstants.REPRESENTATION_FILE_FOUND_WITH_MATCHING_CHECKSUMS, ip.getBasePath(),
                      filePath);
                  }
                } else {
                  // treat as a SIP shallow
                  final Optional<IPFileInterface> ipFileInterface = validateFileShallow(ip, fLocat, filePath, fileType,
                    relativePath);
                  ipFileInterface.ifPresent(representation::addFile);
                }
              } else {
                // treat as a SIP shallow
                final Optional<IPFileInterface> ipFileInterface = validateFileShallow(ip, fLocat, filePath, fileType,
                  relativePath);
                ipFileInterface.ifPresent(representation::addFile);
              }
            } else {
              ValidationUtils.addIssue(ip.getValidationReport(), ValidationConstants.REPRESENTATION_FILE_HAS_NO_FLOCAT,
                ValidationEntry.LEVEL.ERROR, fileType, ip.getBasePath(), representationMetsWrapper.getMetsPath());
            }
          }
        }
      }
    } else if (div.getDiv().isEmpty()) {
      // This is a empty folder, add an empty folder representation in form of a
      // IPFileShallow
      representation.addFile(IPFileShallow.createEmptyFolder(relativePath));
    }

    for (DivType subDiv : div.getDiv()) {
      final List<String> subDivRelativePath = new ArrayList<>(relativePath);
      subDivRelativePath.add(subDiv.getLABEL());
      processRepresentationFilesSubDivs(ip, representationMetsWrapper, representation, representationBasePath, subDiv,
        subDivRelativePath);
    }

  }

  private Optional<IPFileInterface> validateFileShallow(IPInterface ip, FLocat fLocat, Path filePath, FileType fileType,
    List<String> relativeFolders) {
    Optional<IPFileInterface> file = Optional.empty();

    if (URI.create(fLocat.getHref()).getScheme() != null) {
      file = Optional.of(new IPFileShallow(URI.create(fLocat.getHref()), fileType, relativeFolders));
    } else {
      ValidationUtils.addIssue(ip.getValidationReport(), ValidationConstants.REPRESENTATION_SCHEME_NOT_FOUND,
        ValidationEntry.LEVEL.ERROR, ip.getBasePath(), filePath);
    }

    return file;
  }

  protected IPInterface processSchemasMetadata(MetsWrapper metsWrapper, IPInterface ip, Path basePath) {
    return processFile(ip, metsWrapper.getSchemasDiv(), IPConstants.SCHEMAS, basePath);
  }

  protected IPInterface processDocumentationMetadata(MetsWrapper metsWrapper, IPInterface ip, Path basePath) {
    return processFile(ip, metsWrapper.getDocumentationDiv(), IPConstants.DOCUMENTATION, basePath);
  }

  protected IPInterface processAncestors(MetsWrapper metsWrapper, IPInterface ip) {
    Mets mets = metsWrapper.getMets();

    if (mets.getStructMap() != null && !mets.getStructMap().isEmpty()) {
      ip.setAncestors(metsGenerator.extractAncestorsFromStructMap(mets));
    }

    return ip;
  }

  protected IPInterface processSubmissionMetadata(final MetsWrapper metsWrapper, final IPInterface ip,
    final Path basePath) {
    return processFile(ip, metsWrapper.getSubmissionsDiv(), IPConstants.SUBMISSION, basePath);
  }

}
