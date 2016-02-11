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
import java.util.ArrayList;
import java.util.Iterator;
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
import org.roda_project.commons_ip.model.IPDescriptiveMetadata;
import org.roda_project.commons_ip.model.IPFile;
import org.roda_project.commons_ip.model.IPMetadata;
import org.roda_project.commons_ip.model.IPRepresentation;
import org.roda_project.commons_ip.model.MetsWrapper;
import org.roda_project.commons_ip.model.ParseException;
import org.roda_project.commons_ip.model.SIP;
import org.roda_project.commons_ip.utils.EARKEnums.ContentType;
import org.roda_project.commons_ip.utils.METSEnums.MetadataType;
import org.roda_project.commons_ip.utils.SIPException;
import org.roda_project.commons_ip.utils.Utils;
import org.roda_project.commons_ip.utils.ZIPUtils;
import org.roda_project.commons_ip.utils.ZipEntryInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EARKSIP extends SIP {
  private static final Logger LOGGER = LoggerFactory.getLogger(EARKSIP.class);

  private static final String SIP_TEMP_DIR = "EARKSIP";
  private static final String SIP_FILE_EXTENSION = ".zip";

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
  public EARKSIP(String sipName, ContentType contentType, String creator) {
    super(sipName, contentType, creator);
  }

  /**
   * 
   * build and all build related methods
   * _________________________________________________________________________
   */
  @Override
  public Path build(Path destinationDirectory) throws SIPException {
    Path buildDir = createBuildDir();
    try {
      Path zipPath = getZipPath(destinationDirectory);
      List<ZipEntryInfo> zipEntries = new ArrayList<ZipEntryInfo>();
      MetsWrapper mainMETSWrapper = EARKMETSUtils.generateMETS(this.getId(), this.getDescription(),
        this.getType() + ":" + this.getContentType(), this.getProfile(), this.getAgents(), true, this.getParentID());

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
    List<IPDescriptiveMetadata> descriptiveMetadata, String representationId) throws SIPException {
    if (descriptiveMetadata != null && !descriptiveMetadata.isEmpty()) {
      for (IPDescriptiveMetadata dm : descriptiveMetadata) {
        IPFile file = dm.getMetadata();

        String descriptiveFilePath = IPConstants.DESCRIPTIVE_FOLDER + getFoldersFromList(file.getRelativeFolders())
          + file.getFileName();
        EARKMETSUtils.addDescriptiveMetadataToMETS(metsWrapper, dm, descriptiveFilePath);

        if (representationId != null) {
          descriptiveFilePath = IPConstants.REPRESENTATIONS_FOLDER + representationId + IPConstants.ZIP_PATH_SEPARATOR
            + descriptiveFilePath;
        }
        ZIPUtils.addFileToZip(zipEntries, file.getPath(), descriptiveFilePath);
      }
    }
  }

  public void addPreservationMetadataToZipAndMETS(List<ZipEntryInfo> zipEntries, MetsWrapper metsWrapper,
    List<IPMetadata> preservationMetadata, String representationId) throws SIPException {
    if (preservationMetadata != null && !preservationMetadata.isEmpty()) {
      for (IPMetadata pm : preservationMetadata) {
        IPFile file = pm.getMetadata();

        String preservationMetadataPath = IPConstants.PRESERVATION_FOLDER
          + getFoldersFromList(file.getRelativeFolders()) + file.getFileName();
        EARKMETSUtils.addPreservationMetadataToMETS(metsWrapper, pm, preservationMetadataPath);

        if (representationId != null) {
          preservationMetadataPath = IPConstants.REPRESENTATIONS_FOLDER + representationId
            + IPConstants.ZIP_PATH_SEPARATOR + preservationMetadataPath;
        }
        ZIPUtils.addFileToZip(zipEntries, file.getPath(), preservationMetadataPath);
      }
    }
  }

  public void addOtherMetadataToZipAndMETS(List<ZipEntryInfo> zipEntries, MetsWrapper metsWrapper,
    List<IPMetadata> otherMetadata, String representationId) throws SIPException {
    if (otherMetadata != null && !otherMetadata.isEmpty()) {
      for (IPMetadata om : otherMetadata) {
        IPFile file = om.getMetadata();

        String otherMetadataPath = IPConstants.OTHER_FOLDER + getFoldersFromList(file.getRelativeFolders())
          + file.getFileName();
        EARKMETSUtils.addOtherMetadataToMETS(metsWrapper, om, otherMetadataPath);

        if (representationId != null) {
          otherMetadataPath = IPConstants.REPRESENTATIONS_FOLDER + representationId + IPConstants.ZIP_PATH_SEPARATOR
            + otherMetadataPath;
        }
        ZIPUtils.addFileToZip(zipEntries, file.getPath(), otherMetadataPath);
      }
    }
  }

  public void addRepresentationsToZipAndMETS(List<ZipEntryInfo> zipEntries, MetsWrapper mainMETSWrapper, Path buildDir)
    throws SIPException {
    // representations
    if (getRepresentations() != null && !getRepresentations().isEmpty()) {
      this.notifySipBuildRepresentationsProcessingStarted(getRepresentations().size());
      for (IPRepresentation representation : getRepresentations()) {
        String representationId = representation.getObjectID();

        // FIXME how to set label and profile for the representations
        String representationLabel = "";
        String representationProfile = "";
        String representationType = "representation";
        MetsWrapper representationMETSWrapper = EARKMETSUtils.generateMETS(representationId, representationLabel,
          representationType, representationProfile, representation.getAgents(), false, null);

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
      throws SIPException {
    if (representation.getData() != null && !representation.getData().isEmpty()) {
      this.notifySipBuildRepresentationProcessingStarted(representation.getData().size());
      int i = 0;
      for (IPFile file : representation.getData()) {

        String dataFilePath = IPConstants.DATA_FOLDER + getFoldersFromList(file.getRelativeFolders())
          + file.getFileName();
        EARKMETSUtils.addDataFileToMETS(representationMETSWrapper, dataFilePath, file.getPath());

        dataFilePath = IPConstants.REPRESENTATIONS_FOLDER + representationId + IPConstants.ZIP_PATH_SEPARATOR
          + dataFilePath;
        ZIPUtils.addFileToZip(zipEntries, file.getPath(), dataFilePath);

        i++;
        this.notifySipBuildRepresentationProcessingCurrentStatus(i);
      }
      this.notifySipBuildRepresentationProcessingEnded();
    }
  }

  private void addDefaultSchemas(Path buildDir) {
    try {
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
    String representationId) throws SIPException {
    if (schemas != null && !schemas.isEmpty()) {
      for (IPFile schema : schemas) {

        String schemaFilePath = IPConstants.SCHEMAS_FOLDER + getFoldersFromList(schema.getRelativeFolders())
          + schema.getFileName();
        EARKMETSUtils.addSchemaFileToMETS(metsWrapper, schemaFilePath, schema.getPath());

        if (representationId != null) {
          schemaFilePath = IPConstants.REPRESENTATIONS_FOLDER + representationId + IPConstants.ZIP_PATH_SEPARATOR
            + schemaFilePath;
        }
        ZIPUtils.addFileToZip(zipEntries, schema.getPath(), schemaFilePath);
      }
    }
  }

  public void addDocumentationToZipAndMETS(List<ZipEntryInfo> zipEntries, MetsWrapper metsWrapper,
    List<IPFile> documentation, String representationId) throws SIPException {
    if (documentation != null && !documentation.isEmpty()) {
      for (IPFile doc : documentation) {

        String documentationFilePath = IPConstants.DOCUMENTATION_FOLDER + getFoldersFromList(doc.getRelativeFolders())
          + doc.getFileName();
        EARKMETSUtils.addDocumentationFileToMETS(metsWrapper, documentationFilePath, doc.getPath());

        if (representationId != null) {
          documentationFilePath = IPConstants.REPRESENTATIONS_FOLDER + representationId + IPConstants.ZIP_PATH_SEPARATOR
            + documentationFilePath;
        }
        ZIPUtils.addFileToZip(zipEntries, doc.getPath(), documentationFilePath);
      }
    }
  }

  private void addMainMETSToZip(List<ZipEntryInfo> zipEntries, MetsWrapper mainMETSWrapper, Path buildDir)
    throws SIPException {
    EARKMETSUtils.addMainMETSToZip(zipEntries, mainMETSWrapper, IPConstants.METS_FILE, buildDir);
  }

  private void createZipFile(List<ZipEntryInfo> zipEntries, Path zipPath) throws SIPException {
    try {
      notifySipBuildPackagingStarted(zipEntries.size());
      ZIPUtils.zip(zipEntries, Files.newOutputStream(zipPath), this);
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
   * parse and all parse related methods
   * _________________________________________________________________________
   */

  public static SIP parse(Path source) throws ParseException {
    try {
      return parseEARKSIP(source);
    } catch (JAXBException | IOException | SIPException e) {
      throw new ParseException("Error parsing E-ARK SIP", e);
    }
  }

  private static SIP parseEARKSIP(final Path source) throws JAXBException, IOException, SIPException, ParseException {
    Path sipPath = extractSIPIfInZipFormat(source);

    Path mainMETSFile = sipPath.resolve(IPConstants.METS_FILE);
    Mets mainMets = EARKMETSUtils.instantiateMETSFromFile(mainMETSFile);

    SIP sip = instantiateSIP(sipPath, mainMets);

    StructMapType structMap = getEARKStructMap(mainMets);

    MetsWrapper metsWrapper = preProcessStructMap(mainMets, structMap);

    processDescriptiveMetadata(metsWrapper, sip, null, sip.getBasePath());

    processOtherMetadata(metsWrapper, sip, null, sip.getBasePath());

    processPreservationMetadata(metsWrapper, sip, null, sip.getBasePath());

    processRepresentations(metsWrapper, sip, sip.getBasePath());

    processSchemasMetadata(metsWrapper, sip, sip.getBasePath());

    processDocumentationMetadata(metsWrapper, sip, sip.getBasePath());

    processParentId(metsWrapper, sip);

    return sip;
  }

  private static Path extractSIPIfInZipFormat(final Path source) {
    Path sipPath = source;
    if (!Files.isDirectory(source)) {
      try {
        Path tempDir = Files.createTempDirectory("unzipped");
        sipPath = tempDir.resolve(source.getFileName().toString().replaceFirst(SIP_FILE_EXTENSION + "$", ""));
        ZIPUtils.unzip(source, sipPath);
      } catch (IOException e) {
        LOGGER.error("Error unzipping file", e);
      }
    }

    return sipPath;
  }

  private static SIP instantiateSIP(Path sipPath, Mets mets) throws SIPException {
    SIP sip = new EARKSIP(mets.getOBJID());
    addAgentsToMETS(mets, sip, null);
    sip.setBasePath(sipPath);
    return sip;
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

  private static StructMapType getEARKStructMap(Mets mets) throws ParseException {
    StructMapType res = null;
    for (StructMapType structMap : mets.getStructMap()) {
      if (StringUtils.equals(structMap.getLABEL(), IPConstants.E_ARK_STRUCTURAL_MAP)) {
        res = structMap;
        break;
      }
    }
    if (res == null) {
      throw new ParseException("Cannot find EARK structural map");
    }
    return res;
  }

  private static MetsWrapper preProcessStructMap(Mets mets, StructMapType structMap) throws SIPException {
    MetsWrapper metsWrapper = new MetsWrapper(mets);

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

    return metsWrapper;
  }

  private static SIP processDescriptiveMetadata(MetsWrapper metsWrapper, SIP sip, IPRepresentation representation,
    Path basePath) throws SIPException {

    return processMetadata(sip, representation, metsWrapper.getDescriptiveMetadataDiv(), IPConstants.DESCRIPTIVE,
      basePath);
  }

  private static SIP processOtherMetadata(MetsWrapper metsWrapper, SIP sip, IPRepresentation representation,
    Path basePath) throws SIPException {

    return processMetadata(sip, representation, metsWrapper.getOtherMetadataDiv(), IPConstants.OTHER, basePath);
  }

  private static SIP processPreservationMetadata(MetsWrapper metsWrapper, SIP sip, IPRepresentation representation,
    Path basePath) throws SIPException {

    return processMetadata(sip, representation, metsWrapper.getPreservationMetadataDiv(), IPConstants.PRESERVATION,
      basePath);
  }

  private static SIP processMetadata(SIP sip, IPRepresentation representation, DivType div, String metadataType,
    Path basePath) throws SIPException {
    if (div != null && div.getFptr() != null) {
      for (Fptr fptr : div.getFptr()) {
        MdRef mdRef = (MdRef) fptr.getFILEID();
        String href = Utils.extractedRelativePathFromHref(mdRef);
        Path filePath = basePath.resolve(href);
        List<String> fileRelativeFolders = getFileRelativeFolders(
          basePath.resolve(IPConstants.METADATA).resolve(metadataType), filePath);

        if (IPConstants.DESCRIPTIVE.equalsIgnoreCase(metadataType)) {
          MetadataType dmdType = MetadataType.OTHER;
          String dmdVersion = null;
          try {
            dmdType = MetadataType.valueOf(mdRef.getMDTYPE().toUpperCase());
            dmdVersion = mdRef.getMDTYPEVERSION();
            LOGGER.debug("Metadata type valid: " + dmdType.toString());
          } catch (NullPointerException | IllegalArgumentException e) {
            // do nothing and use already defined values for metadataType &
            // metadataVersion
            LOGGER.debug("Setting metadata type to {}", dmdType);
          }

          IPDescriptiveMetadata descriptiveMetadata = new IPDescriptiveMetadata(
            new IPFile(filePath, fileRelativeFolders), dmdType, dmdVersion);
          if (representation == null) {
            sip.addDescriptiveMetadata(descriptiveMetadata);
          } else {
            representation.addDescriptiveMetadata(descriptiveMetadata);
          }
        } else if (IPConstants.PRESERVATION.equalsIgnoreCase(metadataType)) {
          IPMetadata preservationMetadata = new IPMetadata(new IPFile(filePath, fileRelativeFolders));
          if (representation == null) {
            sip.addPreservationMetadata(preservationMetadata);
          } else {
            representation.addPreservationMetadata(preservationMetadata);
          }
        } else if (IPConstants.OTHER.equalsIgnoreCase(metadataType)) {
          IPMetadata otherMetadata = new IPMetadata(new IPFile(filePath, fileRelativeFolders));
          if (representation == null) {
            sip.addOtherMetadata(otherMetadata);
          } else {
            representation.addOtherMetadata(otherMetadata);
          }
        }
      }
    }

    return sip;
  }

  private static SIP processFile(SIP sip, DivType div, String folder, Path basePath) throws SIPException {
    if (div != null && div.getFptr() != null) {
      for (Fptr fptr : div.getFptr()) {
        FileType fileType = (FileType) fptr.getFILEID();

        if (fileType.getFLocat() != null) {
          FLocat fLocat = fileType.getFLocat().get(0);
          String href = Utils.extractedRelativePathFromHref(fLocat.getHref());
          Path filePath = basePath.resolve(href);
          List<String> fileRelativeFolders = getFileRelativeFolders(basePath.resolve(folder), filePath);

          if (IPConstants.SCHEMAS.equalsIgnoreCase(folder)) {
            sip.addSchema(new IPFile(filePath, fileRelativeFolders));
          } else if (IPConstants.DOCUMENTATION.equalsIgnoreCase(folder)) {
            sip.addDocumentation(new IPFile(filePath, fileRelativeFolders));
          }
        }
      }
    }

    return sip;
  }

  private static SIP processRepresentations(MetsWrapper metsWrapper, SIP sip, Path basePath)
    throws JAXBException, SIPException, ParseException {

    if (metsWrapper.getRepresentationsDiv() != null && metsWrapper.getRepresentationsDiv().getDiv() != null) {
      for (DivType representationDiv : metsWrapper.getRepresentationsDiv().getDiv()) {
        if (representationDiv.getMptr() != null) {
          // we can assume one and only one mets for each representation div
          Mptr mptr = representationDiv.getMptr().get(0);
          String href = Utils.extractedRelativePathFromHref(mptr.getHref());
          Path metsFilePath = basePath.resolve(href);
          Mets representationMets = EARKMETSUtils.instantiateMETSFromFile(metsFilePath);
          Path representationBasePath = metsFilePath.getParent();

          StructMapType representationStructMap = getEARKStructMap(representationMets);
          MetsWrapper representationMETSWrapper = preProcessStructMap(representationMets, representationStructMap);

          IPRepresentation representation = new IPRepresentation(representationDiv.getLABEL());
          sip.addRepresentation(representation);

          // process representation agents
          processRepresentationAgents(representationMETSWrapper, representation);

          // process files
          processRepresentationFiles(representationMETSWrapper, representation, representationBasePath);

          // process descriptive metadata
          processDescriptiveMetadata(representationMETSWrapper, sip, representation, representationBasePath);

          // process preservation metadata
          processPreservationMetadata(representationMETSWrapper, sip, representation, representationBasePath);

          // process other metadata
          processOtherMetadata(representationMETSWrapper, sip, representation, representationBasePath);

          // process schemas
          processSchemasMetadata(representationMETSWrapper, sip, representationBasePath);

          // process documentation
          processDocumentationMetadata(representationMETSWrapper, sip, representationBasePath);

        }
      }
    }

    return sip;

  }

  private static void processRepresentationAgents(MetsWrapper representationMETSWrapper,
    IPRepresentation representation) {

    addAgentsToMETS(representationMETSWrapper.getMets(), null, representation);
  }

  private static void processRepresentationFiles(MetsWrapper representationMETSWrapper, IPRepresentation representation,
    Path representationBasePath) throws SIPException {

    if (representationMETSWrapper.getDataDiv() != null && representationMETSWrapper.getDataDiv().getFptr() != null) {
      for (Fptr fptr : representationMETSWrapper.getDataDiv().getFptr()) {
        FileType fileType = (FileType) fptr.getFILEID();

        if (fileType.getFLocat() != null) {
          FLocat fLocat = fileType.getFLocat().get(0);
          String href = Utils.extractedRelativePathFromHref(fLocat.getHref());
          Path filePath = representationBasePath.resolve(href);
          List<String> fileRelativeFolders = getFileRelativeFolders(representationBasePath.resolve(IPConstants.DATA),
            filePath);

          representation.addFile(new IPFile(filePath, fileRelativeFolders));
        }
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
      String parentID = EARKMETSUtils.extractParentIDFromStructMap(mets);
      if (parentID != null) {
        sip.setParent(parentID);
      }
    }

    return sip;
  }

  private static List<String> getFileRelativeFolders(Path basePath, Path filePath) {
    List<String> res = new ArrayList<>();
    Path relativize = basePath.relativize(filePath).getParent();
    if (relativize != null) {
      Iterator<Path> iterator = relativize.iterator();
      while (iterator.hasNext()) {
        res.add(iterator.next().toString());
      }
    }
    return res;
  }

}
