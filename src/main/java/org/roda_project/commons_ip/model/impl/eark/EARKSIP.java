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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.roda_project.commons_ip.model.IPAgent;
import org.roda_project.commons_ip.model.IPConstants;
import org.roda_project.commons_ip.model.IPDescriptiveMetadata;
import org.roda_project.commons_ip.model.IPFile;
import org.roda_project.commons_ip.model.IPMetadata;
import org.roda_project.commons_ip.model.IPRepresentation;
import org.roda_project.commons_ip.model.MetsWrapper;
import org.roda_project.commons_ip.model.SIP;
import org.roda_project.commons_ip.utils.EARKEnums.ContentType;
import org.roda_project.commons_ip.utils.EARKEnums.Type;
import org.roda_project.commons_ip.utils.METSEnums.CreatorType;
import org.roda_project.commons_ip.utils.SIPException;
import org.roda_project.commons_ip.utils.Utils;
import org.roda_project.commons_ip.utils.ZIPUtils;
import org.roda_project.commons_ip.utils.ZipEntryInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EARKSIP implements SIP {
  private static final Logger LOGGER = LoggerFactory.getLogger(EARKSIP.class);

  private static final String SIP_TEMP_DIR = "EARKSIP";
  private static final String SIP_FILE_EXTENSION = ".zip";

  private Path basePath;

  private String parentID;
  private String objectID;
  private String profile;
  private Type type;
  private ContentType contentType;
  private String description;

  private List<IPAgent> agents;
  private List<IPDescriptiveMetadata> descriptiveMetadata;
  private List<IPMetadata> preservationMetadata;
  private List<IPMetadata> otherMetadata;
  private Map<String, IPRepresentation> representations;
  private List<IPFile> schemas;
  private List<IPFile> documentation;

  /**
   * @param sipName
   *          will be used as OBJID in METS (/mets[@OBJID])
   */
  public EARKSIP(String sipName) throws SIPException {
    this.objectID = sipName;
    this.profile = "http://www.eark-project.com/METS/IP.xml";
    this.type = Type.SIP;
    this.contentType = ContentType.mixed;

    this.agents = new ArrayList<IPAgent>();
    this.descriptiveMetadata = new ArrayList<IPDescriptiveMetadata>();
    this.preservationMetadata = new ArrayList<IPMetadata>();
    this.otherMetadata = new ArrayList<IPMetadata>();
    this.representations = new HashMap<String, IPRepresentation>();
    this.schemas = new ArrayList<IPFile>();
    this.documentation = new ArrayList<IPFile>();

  }

  /**
   * @param sipName
   *          will be used as OBJID in METS (/mets[@OBJID])
   */
  public EARKSIP(String sipName, ContentType contentType, String creator) throws SIPException {
    this(sipName);

    this.setContentType(contentType);
    IPAgent creatorAgent = new IPAgent(creator, "CREATOR", null, CreatorType.OTHER, "SOFTWARE");
    this.agents.add(creatorAgent);
  }

  @Override
  public Path getBasePath() {
    return basePath;
  }

  @Override
  public SIP setBasePath(Path basePath) {
    this.basePath = basePath;
    return this;
  }

  public ContentType getContentType() {
    return contentType;
  }

  public void setContentType(ContentType contentType) {
    this.contentType = contentType;
  }

  @Override
  public SIP addAgent(IPAgent sipAgent) {
    agents.add(sipAgent);
    return this;
  }

  @Override
  public SIP addPreservationMetadata(IPMetadata sipMetadata) throws SIPException {
    preservationMetadata.add(sipMetadata);
    return this;
  }

  @Override
  public SIP addOtherMetadata(IPMetadata sipMetadata) throws SIPException {
    otherMetadata.add(sipMetadata);
    return this;
  }

  @Override
  public SIP addDescriptiveMetadata(IPDescriptiveMetadata metadata) throws SIPException {
    descriptiveMetadata.add(metadata);
    return this;
  }

  @Override
  public SIP addRepresentation(IPRepresentation sipRepresentation) throws SIPException {
    if (representations.containsKey(sipRepresentation.getRepresentationID())) {
      throw new SIPException("Representation already exists");
    } else {
      representations.put(sipRepresentation.getRepresentationID(), sipRepresentation);
      return this;
    }

  }

  @Override
  public SIP addAgentToRepresentation(String representationID, IPAgent agent) throws SIPException {
    checkIfRepresentationExists(representationID);
    IPRepresentation rep = representations.get(representationID);
    rep.addAgent(agent);
    representations.put(representationID, rep);
    return this;
  }

  @Override
  public SIP addFileToRepresentation(String representationID, IPFile file) throws SIPException {
    checkIfRepresentationExists(representationID);
    IPRepresentation rep = representations.get(representationID);
    rep.addFile(file);
    representations.put(representationID, rep);
    return this;
  }

  @Override
  public SIP addPreservationMetadataToRepresentation(String representationID, IPMetadata preservationMetadata)
    throws SIPException {
    checkIfRepresentationExists(representationID);
    IPRepresentation rep = representations.get(representationID);
    rep.addPreservationMetadata(preservationMetadata);
    representations.put(representationID, rep);
    return this;
  }

  @Override
  public SIP addDescriptiveMetadataToRepresentation(String representationID, IPDescriptiveMetadata descriptiveMetadata)
    throws SIPException {
    checkIfRepresentationExists(representationID);
    IPRepresentation rep = representations.get(representationID);
    rep.addDescriptiveMetadata(descriptiveMetadata);
    representations.put(representationID, rep);
    return this;
  }

  @Override
  public SIP addOtherMetadataToRepresentation(String representationID, IPMetadata otherMetadata) throws SIPException {
    checkIfRepresentationExists(representationID);
    IPRepresentation rep = representations.get(representationID);
    rep.addOtherMetadata(otherMetadata);
    representations.put(representationID, rep);
    return this;
  }

  private void checkIfRepresentationExists(String representationID) throws SIPException {
    if (!representations.containsKey(representationID)) {
      throw new SIPException("Representation doesn't exist");
    }
  }

  @Override
  public SIP addDocumentation(IPFile documentationPath) {
    documentation.add(documentationPath);
    return this;
  }

  public String getObjectID() {
    return objectID;
  }

  public String getProfile() {
    return profile;
  }

  public String getType() {
    return type.toString();
  }

  @Override
  public String getDescription() {
    return description;
  }

  @Override
  public SIP setDescription(String description) {
    this.description = description;
    return this;
  }

  @Override
  public List<IPAgent> getAgents() {
    return agents;
  }

  @Override
  public List<IPDescriptiveMetadata> getDescriptiveMetadata() {
    return descriptiveMetadata;
  }

  @Override
  public List<IPMetadata> getPreservationMetadata() {
    return preservationMetadata;
  }

  @Override
  public List<IPMetadata> getOtherMetadata() {
    return otherMetadata;
  }

  @Override
  public List<IPRepresentation> getRepresentations() {
    return new ArrayList<IPRepresentation>(representations.values());
  }

  @Override
  public SIP setParent(String parentID) {
    this.parentID = parentID;
    return this;
  }

  @Override
  public String getParentID() {
    return this.parentID;
  }

  @Override
  public SIP addSchema(IPFile schema) {
    schemas.add(schema);
    return this;
  }

  @Override
  public SIP addDocumentationToRepresentation(String representationID, IPFile documentation) throws SIPException {
    checkIfRepresentationExists(representationID);
    IPRepresentation rep = representations.get(representationID);
    rep.addDocumentation(documentation);
    representations.put(representationID, rep);
    return this;
  }

  @Override
  public SIP addSchemaToRepresentation(String representationID, IPFile schema) throws SIPException {
    checkIfRepresentationExists(representationID);
    IPRepresentation rep = representations.get(representationID);
    rep.addSchema(schema);
    representations.put(representationID, rep);
    return this;
  }

  @Override
  public List<IPFile> getSchemas() {
    return schemas;
  }

  @Override
  public List<IPFile> getDocumentation() {
    return documentation;
  }

  @Override
  public Path build(Path destinationDirectory) throws SIPException {
    Path buildDir = createBuildDir();
    try {
      Path zipPath = getZipPath(destinationDirectory);
      List<ZipEntryInfo> zipEntries = new ArrayList<ZipEntryInfo>();
      MetsWrapper mainMETSWrapper = EARKMETSUtils.generateMETS(this.getObjectID(), this.getDescription(),
        this.getType() + ":" + this.getContentType(), this.getProfile(), this.getAgents(), true, this.getParentID());

      addDescriptiveMetadataToZipAndMETS(zipEntries, mainMETSWrapper, descriptiveMetadata, null);

      addPreservationMetadataToZipAndMETS(zipEntries, mainMETSWrapper, preservationMetadata, null);

      addOtherMetadataToZipAndMETS(zipEntries, mainMETSWrapper, otherMetadata, null);

      addRepresentationsToZipAndMETS(zipEntries, mainMETSWrapper, buildDir);

      addDefaultSchemas(buildDir);

      addSchemasToZipAndMETS(zipEntries, mainMETSWrapper, schemas, null);

      addDocumentationToZipAndMETS(zipEntries, mainMETSWrapper, documentation, null);

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
    Path zipPath = destinationDirectory.resolve(objectID + SIP_FILE_EXTENSION);
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
    if (representations != null && !representations.isEmpty()) {
      for (Map.Entry<String, IPRepresentation> entry : representations.entrySet()) {
        String representationId = entry.getKey();
        IPRepresentation representation = entry.getValue();

        // FIXME how to set label and profile for the representations
        String representationLabel = "";
        String representationProfile = "";
        String representationType = "representation";
        MetsWrapper representationMETSWrapper = EARKMETSUtils.generateMETS(representation.getObjectID(),
          representationLabel, representationType, representationProfile, representation.getAgents(), false, null);

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
    }
  }

  private void addRepresentationDataFilesToZipAndMETS(List<ZipEntryInfo> zipEntries,
    MetsWrapper representationMETSWrapper, IPRepresentation representation, String representationId)
      throws SIPException {
    if (representation.getData() != null && !representation.getData().isEmpty()) {
      for (IPFile file : representation.getData()) {

        String dataFilePath = IPConstants.DATA_FOLDER + getFoldersFromList(file.getRelativeFolders())
          + file.getFileName();
        EARKMETSUtils.addDataFileToMETS(representationMETSWrapper, dataFilePath, file.getPath());

        dataFilePath = IPConstants.REPRESENTATIONS_FOLDER + representationId + IPConstants.ZIP_PATH_SEPARATOR
          + dataFilePath;
        ZIPUtils.addFileToZip(zipEntries, file.getPath(), dataFilePath);
      }
    }
  }

  private void addDefaultSchemas(Path buildDir) {
    try {
      Path metsSchema = Utils.copyResourceFromClasspathToDir(EARKSIP.class, buildDir, "mets.xsd",
        "/schemas/mets1_11.xsd");
      schemas.add(new IPFile(metsSchema, "mets.xsd"));
      Path xlinkSchema = Utils.copyResourceFromClasspathToDir(EARKSIP.class, buildDir, "xlink.xsd",
        "/schemas/xlink.xsd");
      schemas.add(new IPFile(xlinkSchema, "xlink.xsd"));
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
      ZIPUtils.zip(zipEntries, Files.newOutputStream(zipPath));
    } catch (IOException e) {
      throw new SIPException("Error generating E-ARK SIP ZIP file. Reason: " + e.getMessage(), e);
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

}
