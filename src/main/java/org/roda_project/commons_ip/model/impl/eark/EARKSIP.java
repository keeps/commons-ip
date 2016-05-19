/**
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE file at the root of the source
 * tree and available online at
 *
 * https://github.com/keeps/commons-ip
 */
package org.roda_project.commons_ip.model.impl.eark;

import java.io.IOException;
import java.nio.channels.ClosedByInterruptException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBException;

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
import org.roda_project.commons_ip.model.IPConstants;
import org.roda_project.commons_ip.model.IPContentType;
import org.roda_project.commons_ip.model.IPDescriptiveMetadata;
import org.roda_project.commons_ip.model.IPFile;
import org.roda_project.commons_ip.model.IPMetadata;
import org.roda_project.commons_ip.model.IPRepresentation;
import org.roda_project.commons_ip.model.MetadataType;
import org.roda_project.commons_ip.model.MetsWrapper;
import org.roda_project.commons_ip.model.ParseException;
import org.roda_project.commons_ip.model.RepresentationContentType;
import org.roda_project.commons_ip.model.SIP;
import org.roda_project.commons_ip.model.ValidationEntry;
import org.roda_project.commons_ip.utils.IPEnums;
import org.roda_project.commons_ip.utils.IPException;
import org.roda_project.commons_ip.utils.METSUtils;
import org.roda_project.commons_ip.utils.SIPException;
import org.roda_project.commons_ip.utils.Utils;
import org.roda_project.commons_ip.utils.ValidationConstants;
import org.roda_project.commons_ip.utils.ValidationUtils;
import org.roda_project.commons_ip.utils.ZIPUtils;
import org.roda_project.commons_ip.utils.ZipEntryInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EARKSIP extends SIP {
  private static final Logger LOGGER = LoggerFactory.getLogger(EARKSIP.class);

  private static final String SIP_TEMP_DIR = "EARKSIP";
  private static final String SIP_FILE_EXTENSION = ".zip";

  // controls if checksum is calculated during processing or in a latter moment
  // (e.g. zipping files)
  private boolean calculateChecksumDuringProcessing = false;
  private static boolean VALIDATION_FAIL_IF_REPRESENTATION_METS_DOES_NOT_HAVE_TWO_PARTS = false;

  public EARKSIP() {
    super();
  }

  /**
   * @param sipName
   *          will be used as OBJID in METS (/mets[@OBJID])
   */
  public EARKSIP(String sipName) {
    super(sipName);
  }

  /**
   * @param sipName
   *          will be used as OBJID in METS (/mets[@OBJID])
   */
  public EARKSIP(String sipName, IPContentType contentType, String creator) {
    super(sipName, contentType, creator);
  }

  /**
   * 
   * build and all build related methods
   * _________________________________________________________________________
   */
  @Override
  public Path build(Path destinationDirectory) throws SIPException, InterruptedException {
    Path buildDir = createBuildDir();
    Path zipPath = null;
    try {
      zipPath = getZipPath(destinationDirectory);
      List<ZipEntryInfo> zipEntries = new ArrayList<ZipEntryInfo>();
      // 20160407 hsilva: as METS does not have an attribute 'otherType', the
      // other type must be put in the 'type' attribute allowing this way other
      // values besides the ones in the Enum
      String contentType = this.getContentType().asString();

      MetsWrapper mainMETSWrapper = EARKMETSUtils.generateMETS(this.getId(), this.getDescription(),
        this.getType() + ":" + contentType, this.getProfile(), this.getAgents(), true, this.getParentID(), null);

      addDescriptiveMetadataToZipAndMETS(zipEntries, mainMETSWrapper, getDescriptiveMetadata(), null);

      addPreservationMetadataToZipAndMETS(zipEntries, mainMETSWrapper, getPreservationMetadata(), null);

      addOtherMetadataToZipAndMETS(zipEntries, mainMETSWrapper, getOtherMetadata(), null);

      addRepresentationsToZipAndMETS(zipEntries, mainMETSWrapper, buildDir);

      addDefaultSchemas(buildDir);

      addSchemasToZipAndMETS(zipEntries, mainMETSWrapper, getSchemas(), null);

      addDocumentationToZipAndMETS(zipEntries, mainMETSWrapper, getDocumentation(), null);

      addMainMETSToZip(zipEntries, mainMETSWrapper, buildDir);

      createZipFile(zipEntries, zipPath);

      return zipPath;
    } catch (InterruptedException e) {
      cleanUpUponInterrupt(zipPath);
      throw e;
    } catch (IPException e) {
      throw new SIPException(e.getMessage(), e);
    } finally {
      deleteBuildDir(buildDir);
    }
  }

  private Path createBuildDir() throws SIPException {
    try {
      return Files.createTempDirectory(SIP_TEMP_DIR);
    } catch (IOException e) {
      throw new SIPException("Unable to create temporary directory to hold SIP files", e);
    }
  }

  private void cleanUpUponInterrupt(Path zipPath) {
    if (zipPath != null && Files.exists(zipPath)) {
      try {
        Utils.deletePath(zipPath);
      } catch (IOException e) {
        LOGGER.error("Error while cleaning up unneeded files", e);
      }
    }
  }

  private void deleteBuildDir(Path buildDir) throws SIPException {
    try {
      Utils.deletePath(buildDir);
    } catch (IOException e) {
      throw new SIPException("Error while deleting temporary directory that was created to hold SIP files", e);
    }
  }

  private Path getZipPath(Path destinationDirectory) throws SIPException {
    Path zipPath = destinationDirectory.resolve(getId() + SIP_FILE_EXTENSION);
    try {
      if (Files.exists(zipPath)) {
        Files.delete(zipPath);
      }
    } catch (IOException e) {
      throw new SIPException("Error deleting already existing zip", e);
    }
    return zipPath;
  }

  public void addDescriptiveMetadataToZipAndMETS(List<ZipEntryInfo> zipEntries, MetsWrapper metsWrapper,
    List<IPDescriptiveMetadata> descriptiveMetadata, String representationId)
    throws SIPException, InterruptedException {
    if (descriptiveMetadata != null && !descriptiveMetadata.isEmpty()) {
      for (IPDescriptiveMetadata dm : descriptiveMetadata) {
        if (Thread.interrupted()) {
          throw new InterruptedException();
        }
        IPFile file = dm.getMetadata();

        String descriptiveFilePath = IPConstants.DESCRIPTIVE_FOLDER + getFoldersFromList(file.getRelativeFolders())
          + file.getFileName();
        MdRef mdRef = EARKMETSUtils.addDescriptiveMetadataToMETS(metsWrapper, dm, descriptiveFilePath,
          calculateChecksumDuringProcessing);

        if (representationId != null) {
          descriptiveFilePath = IPConstants.REPRESENTATIONS_FOLDER + representationId + IPConstants.ZIP_PATH_SEPARATOR
            + descriptiveFilePath;
        }
        ZIPUtils.addMdRefFileToZip(zipEntries, file.getPath(), descriptiveFilePath, mdRef);
      }
    }
  }

  public void addPreservationMetadataToZipAndMETS(List<ZipEntryInfo> zipEntries, MetsWrapper metsWrapper,
    List<IPMetadata> preservationMetadata, String representationId) throws SIPException, InterruptedException {
    if (preservationMetadata != null && !preservationMetadata.isEmpty()) {
      for (IPMetadata pm : preservationMetadata) {
        if (Thread.interrupted()) {
          throw new InterruptedException();
        }
        IPFile file = pm.getMetadata();

        String preservationMetadataPath = IPConstants.PRESERVATION_FOLDER
          + getFoldersFromList(file.getRelativeFolders()) + file.getFileName();
        MdRef mdRef = EARKMETSUtils.addPreservationMetadataToMETS(metsWrapper, pm, preservationMetadataPath,
          calculateChecksumDuringProcessing);

        if (representationId != null) {
          preservationMetadataPath = IPConstants.REPRESENTATIONS_FOLDER + representationId
            + IPConstants.ZIP_PATH_SEPARATOR + preservationMetadataPath;
        }
        ZIPUtils.addMdRefFileToZip(zipEntries, file.getPath(), preservationMetadataPath, mdRef);
      }
    }
  }

  public void addOtherMetadataToZipAndMETS(List<ZipEntryInfo> zipEntries, MetsWrapper metsWrapper,
    List<IPMetadata> otherMetadata, String representationId) throws SIPException, InterruptedException {
    if (otherMetadata != null && !otherMetadata.isEmpty()) {
      for (IPMetadata om : otherMetadata) {
        if (Thread.interrupted()) {
          throw new InterruptedException();
        }
        IPFile file = om.getMetadata();

        String otherMetadataPath = IPConstants.OTHER_FOLDER + getFoldersFromList(file.getRelativeFolders())
          + file.getFileName();
        MdRef mdRef = EARKMETSUtils.addOtherMetadataToMETS(metsWrapper, om, otherMetadataPath,
          calculateChecksumDuringProcessing);

        if (representationId != null) {
          otherMetadataPath = IPConstants.REPRESENTATIONS_FOLDER + representationId + IPConstants.ZIP_PATH_SEPARATOR
            + otherMetadataPath;
        }
        ZIPUtils.addMdRefFileToZip(zipEntries, file.getPath(), otherMetadataPath, mdRef);
      }
    }
  }

  public void addRepresentationsToZipAndMETS(List<ZipEntryInfo> zipEntries, MetsWrapper mainMETSWrapper, Path buildDir)
    throws SIPException, InterruptedException {
    // representations
    if (getRepresentations() != null && !getRepresentations().isEmpty()) {
      this.notifySipBuildRepresentationsProcessingStarted(getRepresentations().size());
      for (IPRepresentation representation : getRepresentations()) {
        if (Thread.interrupted()) {
          throw new InterruptedException();
        }
        String representationId = representation.getObjectID();
        // 20160407 hsilva: not being used by Common Specification v0.13
        String representationProfile = "";
        String representationContentType = representation.getContentType().asString();

        MetsWrapper representationMETSWrapper = EARKMETSUtils.generateMETS(representationId,
          representation.getDescription(),
          IPConstants.METS_REPRESENTATION_TYPE_PART_1 + ":" + representationContentType, representationProfile,
          representation.getAgents(), false, null, null);

        // representation data
        addRepresentationDataFilesToZipAndMETS(zipEntries, representationMETSWrapper, representation, representationId);

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
      this.notifySipBuildRepresentationsProcessingEnded();
    }
  }

  private void addRepresentationDataFilesToZipAndMETS(List<ZipEntryInfo> zipEntries,
    MetsWrapper representationMETSWrapper, IPRepresentation representation, String representationId)
    throws SIPException, InterruptedException {
    if (representation.getData() != null && !representation.getData().isEmpty()) {
      this.notifySipBuildRepresentationProcessingStarted(representation.getData().size());
      int i = 0;
      for (IPFile file : representation.getData()) {
        if (Thread.interrupted()) {
          throw new InterruptedException();
        }

        String dataFilePath = IPConstants.DATA_FOLDER + getFoldersFromList(file.getRelativeFolders())
          + file.getFileName();
        FileType fileType = EARKMETSUtils.addDataFileToMETS(representationMETSWrapper, dataFilePath, file.getPath(),
          calculateChecksumDuringProcessing);

        dataFilePath = IPConstants.REPRESENTATIONS_FOLDER + representationId + IPConstants.ZIP_PATH_SEPARATOR
          + dataFilePath;
        ZIPUtils.addFileTypeFileToZip(zipEntries, file.getPath(), dataFilePath, fileType);

        i++;
        this.notifySipBuildRepresentationProcessingCurrentStatus(i);
      }
      this.notifySipBuildRepresentationProcessingEnded();
    }
  }

  private void addDefaultSchemas(Path buildDir) throws InterruptedException {
    try {
      if (Thread.interrupted()) {
        throw new InterruptedException();
      }
      Path metsSchema = Utils.copyResourceFromClasspathToDir(EARKSIP.class, buildDir, "mets.xsd",
        "/schemas/mets1_11.xsd");
      getSchemas().add(new IPFile(metsSchema, "mets.xsd"));
      Path xlinkSchema = Utils.copyResourceFromClasspathToDir(EARKSIP.class, buildDir, "xlink.xsd",
        "/schemas/xlink.xsd");
      getSchemas().add(new IPFile(xlinkSchema, "xlink.xsd"));
    } catch (IOException e) {
      LOGGER.error("Error while trying to add default schemas", e);
    }
  }

  public void addSchemasToZipAndMETS(List<ZipEntryInfo> zipEntries, MetsWrapper metsWrapper, List<IPFile> schemas,
    String representationId) throws SIPException, InterruptedException {
    if (schemas != null && !schemas.isEmpty()) {
      for (IPFile schema : schemas) {
        if (Thread.interrupted()) {
          throw new InterruptedException();
        }

        String schemaFilePath = IPConstants.SCHEMAS_FOLDER + getFoldersFromList(schema.getRelativeFolders())
          + schema.getFileName();
        FileType fileType = EARKMETSUtils.addSchemaFileToMETS(metsWrapper, schemaFilePath, schema.getPath(),
          calculateChecksumDuringProcessing);

        if (representationId != null) {
          schemaFilePath = IPConstants.REPRESENTATIONS_FOLDER + representationId + IPConstants.ZIP_PATH_SEPARATOR
            + schemaFilePath;
        }
        ZIPUtils.addFileTypeFileToZip(zipEntries, schema.getPath(), schemaFilePath, fileType);
      }
    }
  }

  public void addDocumentationToZipAndMETS(List<ZipEntryInfo> zipEntries, MetsWrapper metsWrapper,
    List<IPFile> documentation, String representationId) throws SIPException, InterruptedException {
    if (documentation != null && !documentation.isEmpty()) {
      for (IPFile doc : documentation) {
        if (Thread.interrupted()) {
          throw new InterruptedException();
        }

        String documentationFilePath = IPConstants.DOCUMENTATION_FOLDER + getFoldersFromList(doc.getRelativeFolders())
          + doc.getFileName();
        FileType fileType = EARKMETSUtils.addDocumentationFileToMETS(metsWrapper, documentationFilePath, doc.getPath(),
          calculateChecksumDuringProcessing);

        if (representationId != null) {
          documentationFilePath = IPConstants.REPRESENTATIONS_FOLDER + representationId + IPConstants.ZIP_PATH_SEPARATOR
            + documentationFilePath;
        }
        ZIPUtils.addFileTypeFileToZip(zipEntries, doc.getPath(), documentationFilePath, fileType);
      }
    }
  }

  private void addMainMETSToZip(List<ZipEntryInfo> zipEntries, MetsWrapper mainMETSWrapper, Path buildDir)
    throws SIPException {
    EARKMETSUtils.addMainMETSToZip(zipEntries, mainMETSWrapper, IPConstants.METS_FILE, buildDir);
  }

  private void createZipFile(List<ZipEntryInfo> zipEntries, Path zipPath) throws IPException, InterruptedException {
    try {
      notifySipBuildPackagingStarted(zipEntries.size());
      ZIPUtils.zip(zipEntries, Files.newOutputStream(zipPath), this, calculateChecksumDuringProcessing);
    } catch (ClosedByInterruptException e) {
      throw new InterruptedException();
    } catch (IOException e) {
      throw new SIPException("Error generating E-ARK SIP ZIP file. Reason: " + e.getMessage(), e);
    } finally {
      notifySipBuildPackagingEnded();
    }
  }

  private String getFoldersFromList(List<String> folders) {
    StringBuilder sb = new StringBuilder();
    for (String folder : folders) {
      sb.append(folder);
      if (sb.length() > 0) {
        sb.append(IPConstants.ZIP_PATH_SEPARATOR);
      }
    }
    return sb.toString();
  }

  /**
   * 
   * parse and all parse related methods; during parse, validation is also
   * conducted and stored inside the SIP
   * _________________________________________________________________________
   */

  public static SIP parse(Path source, Path destinationDirectory) throws ParseException {
    return parseEARKSIP(source, destinationDirectory);
  }

  public static SIP parse(Path source) throws ParseException {
    try {
      return parse(source, Files.createTempDirectory("unzipped"));
    } catch (IOException e) {
      throw new ParseException("Error creating temporary directory for E-ARK SIP parse", e);
    }
  }

  private static SIP parseEARKSIP(final Path source, final Path destinationDirectory) throws ParseException {
    try {
      SIP sip = new EARKSIP();

      Path sipPath = ZIPUtils.extractSIPIfInZipFormat(source, destinationDirectory, SIP_FILE_EXTENSION);
      sip.setBasePath(sipPath);

      MetsWrapper metsWrapper = processMainMets(sip, sipPath);

      if (sip.isValid()) {

        StructMapType structMap = getEARKStructMap(metsWrapper, sip, true);

        if (structMap != null) {
          preProcessStructMap(metsWrapper, structMap);

          processDescriptiveMetadata(metsWrapper, sip, null, sip.getBasePath());

          processOtherMetadata(metsWrapper, sip, null, sip.getBasePath());

          processPreservationMetadata(metsWrapper, sip, null, sip.getBasePath());

          processRepresentations(metsWrapper, sip);

          processSchemasMetadata(metsWrapper, sip, sip.getBasePath());

          processDocumentationMetadata(metsWrapper, sip, sip.getBasePath());

          processParentId(metsWrapper, sip);
        }
      }

      return sip;
    } catch (SIPException e) {
      throw new ParseException("Error parsing E-ARK SIP", e);
    }
  }

  private static MetsWrapper processMainMets(SIP sip, Path sipPath) {
    Path mainMETSFile = sipPath.resolve(IPConstants.METS_FILE);
    Mets mainMets = null;
    if (Files.exists(mainMETSFile)) {
      ValidationUtils.addInfo(sip.getValidationReport(), ValidationConstants.MAIN_METS_FILE_FOUND, sipPath,
        mainMETSFile);
      try {
        mainMets = METSUtils.instantiateMETSFromFile(mainMETSFile);
        sip.setId(mainMets.getOBJID());
        sip.setCreateDate(mainMets.getMetsHdr().getCREATEDATE());
        sip.setModificationDate(mainMets.getMetsHdr().getLASTMODDATE());
        setSIPContentType(mainMets, sip);
        addAgentsToMETS(mainMets, sip, null);

        ValidationUtils.addInfo(sip.getValidationReport(), ValidationConstants.MAIN_METS_IS_VALID, sipPath,
          mainMETSFile);
      } catch (JAXBException | ParseException e) {
        mainMets = null;
        ValidationUtils.addIssue(sip.getValidationReport(), ValidationConstants.MAIN_METS_NOT_VALID,
          ValidationEntry.LEVEL.ERROR, e, sip.getBasePath(), mainMETSFile);
      }
    } else {
      ValidationUtils.addIssue(sip.getValidationReport(), ValidationConstants.MAIN_METS_FILE_NOT_FOUND,
        ValidationEntry.LEVEL.ERROR, sip.getBasePath(), mainMETSFile);
    }
    return new MetsWrapper(mainMets, mainMETSFile);
  }

  private static MetsWrapper processRepresentationMets(SIP sip, Path representationMetsFile,
    IPRepresentation representation) {
    Mets representationMets = null;
    if (Files.exists(representationMetsFile)) {
      ValidationUtils.addInfo(sip.getValidationReport(), ValidationConstants.REPRESENTATION_METS_FILE_FOUND,
        sip.getBasePath(), representationMetsFile);
      try {
        representationMets = METSUtils.instantiateMETSFromFile(representationMetsFile);
        setRepresentationContentType(representationMets, representation);
        ValidationUtils.addInfo(sip.getValidationReport(), ValidationConstants.REPRESENTATION_METS_IS_VALID,
          sip.getBasePath(), representationMetsFile);
      } catch (JAXBException | ParseException e) {
        representationMets = null;
        ValidationUtils.addIssue(sip.getValidationReport(), ValidationConstants.REPRESENTATION_METS_NOT_VALID,
          ValidationEntry.LEVEL.ERROR, e, sip.getBasePath(), representationMetsFile);
      }
    } else {
      ValidationUtils.addIssue(sip.getValidationReport(), ValidationConstants.REPRESENTATION_METS_FILE_NOT_FOUND,
        ValidationEntry.LEVEL.ERROR, sip.getBasePath(), representationMetsFile);
    }
    return new MetsWrapper(representationMets, representationMetsFile);
  }

  private static void setSIPContentType(Mets mets, SIP sip) throws ParseException {
    String metsType = mets.getTYPE();

    if (StringUtils.isBlank(metsType)) {
      throw new ParseException("METS 'TYPE' attribute does not contain any value");
    }

    String[] contentTypeParts = metsType.split(":");
    if (contentTypeParts.length != 2 || StringUtils.isBlank(contentTypeParts[0])
      || StringUtils.isBlank(contentTypeParts[1])) {
      throw new ParseException("METS 'TYPE' attribute does not contain a valid value");
    }

    IPEnums.IPType packageType;
    try {
      packageType = IPEnums.IPType.valueOf(contentTypeParts[0]);
      if (IPEnums.IPType.SIP != packageType) {
        throw new ParseException("METS 'TYPE' attribute should start with 'SIP:'");
      }
    } catch (IllegalArgumentException e) {
      throw new ParseException("METS 'TYPE' attribute does not contain a valid package type");
    }

    sip.setContentType(new IPContentType(contentTypeParts[1]));
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

  private static Mets addAgentsToMETS(Mets mets, SIP sip, IPRepresentation representation) {
    if (mets.getMetsHdr() != null && mets.getMetsHdr().getAgent() != null) {
      for (Agent agent : mets.getMetsHdr().getAgent()) {
        if (representation == null) {
          sip.addAgent(EARKMETSUtils.createIPAgent(agent));
        } else {
          representation.addAgent(EARKMETSUtils.createIPAgent(agent));
        }
      }
    }

    return mets;
  }

  private static StructMapType getEARKStructMap(MetsWrapper metsWrapper, SIP sip, boolean mainMets) {
    Mets mets = metsWrapper.getMets();
    StructMapType res = null;
    for (StructMapType structMap : mets.getStructMap()) {
      if (StringUtils.equals(structMap.getLABEL(), IPConstants.E_ARK_STRUCTURAL_MAP)) {
        res = structMap;
        break;
      }
    }
    if (res == null) {
      ValidationUtils.addIssue(sip.getValidationReport(),
        mainMets ? ValidationConstants.MAIN_METS_HAS_NO_E_ARK_STRUCT_MAP
          : ValidationConstants.REPRESENTATION_METS_HAS_NO_E_ARK_STRUCT_MAP,
        ValidationEntry.LEVEL.ERROR, res, sip.getBasePath(), metsWrapper.getMetsPath());
    } else {
      ValidationUtils.addInfo(sip.getValidationReport(),
        mainMets ? ValidationConstants.MAIN_METS_HAS_E_ARK_STRUCT_MAP
          : ValidationConstants.REPRESENTATION_METS_HAS_E_ARK_STRUCT_MAP,
        res, sip.getBasePath(), metsWrapper.getMetsPath());
    }
    return res;
  }

  private static void preProcessStructMap(MetsWrapper metsWrapper, StructMapType structMap) {

    DivType sipDiv = structMap.getDiv();
    if (sipDiv.getDiv() != null) {
      for (DivType firstLevel : sipDiv.getDiv()) {
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
        }
      }
    }
  }

  private static SIP processDescriptiveMetadata(MetsWrapper metsWrapper, SIP sip, IPRepresentation representation,
    Path basePath) throws SIPException {

    return processMetadata(sip, metsWrapper, representation, metsWrapper.getDescriptiveMetadataDiv(),
      IPConstants.DESCRIPTIVE, basePath);
  }

  private static SIP processOtherMetadata(MetsWrapper metsWrapper, SIP sip, IPRepresentation representation,
    Path basePath) throws SIPException {

    return processMetadata(sip, metsWrapper, representation, metsWrapper.getOtherMetadataDiv(), IPConstants.OTHER,
      basePath);
  }

  private static SIP processPreservationMetadata(MetsWrapper metsWrapper, SIP sip, IPRepresentation representation,
    Path basePath) throws SIPException {

    return processMetadata(sip, metsWrapper, representation, metsWrapper.getPreservationMetadataDiv(),
      IPConstants.PRESERVATION, basePath);
  }

  private static SIP processMetadata(SIP sip, MetsWrapper representationMetsWrapper, IPRepresentation representation,
    DivType div, String metadataType, Path basePath) throws SIPException {
    if (div != null && div.getFptr() != null) {
      for (Fptr fptr : div.getFptr()) {
        MdRef mdRef = (MdRef) fptr.getFILEID();
        String href = Utils.extractedRelativePathFromHref(mdRef);
        Path filePath = basePath.resolve(href);
        if (Files.exists(filePath)) {
          List<String> fileRelativeFolders = Utils
            .getFileRelativeFolders(basePath.resolve(IPConstants.METADATA).resolve(metadataType), filePath);

          processMetadataFile(sip, representation, metadataType, mdRef, filePath, fileRelativeFolders);
        } else {
          ValidationUtils.addIssue(sip.getValidationReport(),
            ValidationConstants.getMetadataFileNotFoundString(metadataType), ValidationEntry.LEVEL.ERROR,
            sip.getBasePath(), filePath);
        }
      }
    } else {
      ValidationUtils.addIssue(sip.getValidationReport(),
        ValidationConstants.getMetadataFileFptrNotFoundString(metadataType), ValidationEntry.LEVEL.ERROR,
        sip.getBasePath(), representationMetsWrapper.getMetsPath());
    }

    return sip;
  }

  private static void processMetadataFile(SIP sip, IPRepresentation representation, String metadataType, MdRef mdRef,
    Path filePath, List<String> fileRelativeFolders) throws SIPException {
    IPFile metadataFile = validateMetadataFile(sip, filePath, mdRef, fileRelativeFolders);
    if (metadataFile != null) {
      ValidationUtils.addInfo(sip.getValidationReport(),
        ValidationConstants.getMetadataFileFoundWithMatchingChecksumString(metadataType), sip.getBasePath(), filePath);

      if (IPConstants.DESCRIPTIVE.equalsIgnoreCase(metadataType)) {
        MetadataType dmdType = new MetadataType(mdRef.getMDTYPE().toUpperCase());
        String dmdVersion = null;
        try {
          dmdVersion = mdRef.getMDTYPEVERSION();
          if (StringUtils.isNotBlank(mdRef.getOTHERMDTYPE())) {
            dmdType.setOtherType(mdRef.getOTHERMDTYPE());
          }
          LOGGER.debug("Metadata type valid: {}", dmdType);
        } catch (NullPointerException | IllegalArgumentException e) {
          // do nothing and use already defined values for metadataType &
          // metadataVersion
          LOGGER.debug("Setting metadata type to {}", dmdType);
          ValidationUtils.addEntry(sip.getValidationReport(), ValidationConstants.UNKNOWN_DESCRIPTIVE_METADATA_TYPE,
            ValidationEntry.LEVEL.WARN, "Setting metadata type to " + dmdType, sip.getBasePath(), filePath);
        }

        IPDescriptiveMetadata descriptiveMetadata = new IPDescriptiveMetadata(metadataFile, dmdType, dmdVersion);
        if (representation == null) {
          sip.addDescriptiveMetadata(descriptiveMetadata);
        } else {
          representation.addDescriptiveMetadata(descriptiveMetadata);
        }
      } else if (IPConstants.PRESERVATION.equalsIgnoreCase(metadataType)) {
        IPMetadata preservationMetadata = new IPMetadata(metadataFile);
        if (representation == null) {
          sip.addPreservationMetadata(preservationMetadata);
        } else {
          representation.addPreservationMetadata(preservationMetadata);
        }
      } else if (IPConstants.OTHER.equalsIgnoreCase(metadataType)) {
        IPMetadata otherMetadata = new IPMetadata(metadataFile);
        if (representation == null) {
          sip.addOtherMetadata(otherMetadata);
        } else {
          representation.addOtherMetadata(otherMetadata);
        }
      }
    }
  }

  private static IPFile validateFile(SIP sip, Path filePath, FileType fileType, List<String> fileRelativeFolders) {
    return Utils.validateFile(sip, filePath, fileRelativeFolders, fileType.getCHECKSUM(), fileType.getCHECKSUMTYPE(),
      fileType.getID());
  }

  private static IPFile validateMetadataFile(SIP sip, Path filePath, MdRef mdRef, List<String> fileRelativeFolders) {
    return Utils.validateFile(sip, filePath, fileRelativeFolders, mdRef.getCHECKSUM(), mdRef.getCHECKSUMTYPE(),
      mdRef.getID());
  }

  private static SIP processFile(SIP sip, DivType div, String folder, Path basePath) throws SIPException {
    if (div != null && div.getFptr() != null) {
      for (Fptr fptr : div.getFptr()) {
        FileType fileType = (FileType) fptr.getFILEID();

        if (fileType.getFLocat() != null) {
          FLocat fLocat = fileType.getFLocat().get(0);
          String href = Utils.extractedRelativePathFromHref(fLocat.getHref());
          Path filePath = basePath.resolve(href);

          if (Files.exists(filePath)) {
            List<String> fileRelativeFolders = Utils.getFileRelativeFolders(basePath.resolve(folder), filePath);
            IPFile file = validateFile(sip, filePath, fileType, fileRelativeFolders);

            if (file != null) {
              if (IPConstants.SCHEMAS.equalsIgnoreCase(folder)) {
                ValidationUtils.addInfo(sip.getValidationReport(),
                  ValidationConstants.SCHEMA_FILE_FOUND_WITH_MATCHING_CHECKSUMS, sip.getBasePath(), filePath);
                sip.addSchema(new IPFile(filePath, fileRelativeFolders));
              } else if (IPConstants.DOCUMENTATION.equalsIgnoreCase(folder)) {
                ValidationUtils.addInfo(sip.getValidationReport(),
                  ValidationConstants.DOCUMENTATION_FILE_FOUND_WITH_MATCHING_CHECKSUMS, sip.getBasePath(), filePath);
                sip.addDocumentation(new IPFile(filePath, fileRelativeFolders));
              }
            }
          } else {
            ValidationUtils.addIssue(sip.getValidationReport(),
              IPConstants.SCHEMAS.equalsIgnoreCase(folder) ? ValidationConstants.SCHEMA_FILE_NOT_FOUND
                : ValidationConstants.DOCUMENTATION_FILE_NOT_FOUND,
              ValidationEntry.LEVEL.ERROR, div, sip.getBasePath(), filePath);
          }
        }
      }
    }

    return sip;
  }

  private static SIP processRepresentations(MetsWrapper metsWrapper, SIP sip) throws SIPException {

    if (metsWrapper.getRepresentationsDiv() != null && metsWrapper.getRepresentationsDiv().getDiv() != null) {
      for (DivType representationDiv : metsWrapper.getRepresentationsDiv().getDiv()) {
        if (representationDiv.getMptr() != null && !representationDiv.getMptr().isEmpty()) {
          // we can assume one and only one mets for each representation div
          Mptr mptr = representationDiv.getMptr().get(0);
          String href = Utils.extractedRelativePathFromHref(mptr.getHref());
          Path metsFilePath = sip.getBasePath().resolve(href);
          IPRepresentation representation = new IPRepresentation(representationDiv.getLABEL());
          MetsWrapper representationMetsWrapper = processRepresentationMets(sip, metsFilePath, representation);

          if (representationMetsWrapper.getMets() != null) {
            Path representationBasePath = metsFilePath.getParent();

            StructMapType representationStructMap = getEARKStructMap(representationMetsWrapper, sip, false);
            if (representationStructMap != null) {

              preProcessStructMap(representationMetsWrapper, representationStructMap);
              sip.addRepresentation(representation);

              // process representation agents
              processRepresentationAgents(representationMetsWrapper, representation);

              // process files
              processRepresentationFiles(sip, representationMetsWrapper, representation, representationBasePath);

              // process descriptive metadata
              processDescriptiveMetadata(representationMetsWrapper, sip, representation, representationBasePath);

              // process preservation metadata
              processPreservationMetadata(representationMetsWrapper, sip, representation, representationBasePath);

              // process other metadata
              processOtherMetadata(representationMetsWrapper, sip, representation, representationBasePath);

              // process schemas
              processSchemasMetadata(representationMetsWrapper, sip, representationBasePath);

              // process documentation
              processDocumentationMetadata(representationMetsWrapper, sip, representationBasePath);
            }
          }
        }
      }

      // post-process validations
      if (sip.getRepresentations().isEmpty()) {
        ValidationUtils.addIssue(sip.getValidationReport(), ValidationConstants.MAIN_METS_NO_REPRESENTATIONS_FOUND,
          ValidationEntry.LEVEL.WARN, metsWrapper.getRepresentationsDiv(), sip.getBasePath(),
          metsWrapper.getMetsPath());
      }
    }

    return sip;

  }

  private static void processRepresentationAgents(MetsWrapper representationMetsWrapper,
    IPRepresentation representation) {

    addAgentsToMETS(representationMetsWrapper.getMets(), null, representation);
  }

  private static void processRepresentationFiles(SIP sip, MetsWrapper representationMetsWrapper,
    IPRepresentation representation, Path representationBasePath) throws SIPException {

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
            IPFile file = validateFile(sip, filePath, fileType, fileRelativeFolders);

            if (file != null) {
              representation.addFile(file);
              ValidationUtils.addInfo(sip.getValidationReport(),
                ValidationConstants.REPRESENTATION_FILE_FOUND_WITH_MATCHING_CHECKSUMS, sip.getBasePath(), filePath);
            }
          } else {
            ValidationUtils.addIssue(sip.getValidationReport(), ValidationConstants.REPRESENTATION_FILE_NOT_FOUND,
              ValidationEntry.LEVEL.ERROR, sip.getBasePath(), filePath);
          }
        } else {
          ValidationUtils.addIssue(sip.getValidationReport(), ValidationConstants.REPRESENTATION_FILE_HAS_NO_FLOCAT,
            ValidationEntry.LEVEL.ERROR, fileType, sip.getBasePath(), representationMetsWrapper.getMetsPath());
        }
      }

      // post-process validations
      if (representation.getData().isEmpty()) {
        ValidationUtils.addIssue(sip.getValidationReport(), ValidationConstants.REPRESENTATION_HAS_NO_FILES,
          ValidationEntry.LEVEL.WARN, representationMetsWrapper.getDataDiv(), sip.getBasePath(),
          representationMetsWrapper.getMetsPath());
      }
    }

  }

  private static SIP processSchemasMetadata(MetsWrapper metsWrapper, SIP sip, Path basePath) throws SIPException {

    return processFile(sip, metsWrapper.getSchemasDiv(), IPConstants.SCHEMAS, basePath);
  }

  private static SIP processDocumentationMetadata(MetsWrapper metsWrapper, SIP sip, Path basePath) throws SIPException {

    return processFile(sip, metsWrapper.getDocumentationDiv(), IPConstants.DOCUMENTATION, basePath);
  }

  private static SIP processParentId(MetsWrapper metsWrapper, SIP sip) {
    Mets mets = metsWrapper.getMets();

    if (mets.getStructMap() != null && !mets.getStructMap().isEmpty()) {
      sip.setParent(EARKMETSUtils.extractParentIDFromStructMap(mets));
    }

    return sip;
  }

}
