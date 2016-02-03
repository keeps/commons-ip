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

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.roda_project.commons_ip.mets_v1_11.beans.DivType.Mptr;
import org.roda_project.commons_ip.mets_v1_11.beans.Mets;
import org.roda_project.commons_ip.model.SIP;
import org.roda_project.commons_ip.model.SIPAgent;
import org.roda_project.commons_ip.model.SIPDescriptiveMetadata;
import org.roda_project.commons_ip.model.SIPMetadata;
import org.roda_project.commons_ip.model.SIPRepresentation;
import org.roda_project.commons_ip.utils.EARKEnums;
import org.roda_project.commons_ip.utils.METSEnums.CreatorType;
import org.roda_project.commons_ip.utils.METSEnums.LocType;
import org.roda_project.commons_ip.utils.Pair;
import org.roda_project.commons_ip.utils.SIPException;
import org.roda_project.commons_ip.utils.ZIPUtils;
import org.roda_project.commons_ip.utils.ZipEntryInfo;

public class EARKSIP implements SIP {
  public static final String URI_BASE_PATH = "file://.";
  private static final String METS_PATH = "/METS.xml";
  private static final String FILE_EXTENSION = ".zip";
  private static final String METADATA_DESCRIPTIVE_PATH = "/metadata/descriptive/";
  private static final String METADATA_ADMINISTRATIVE_PATH = "/metadata/administrative/";
  private static final String METADATA_OTHER_PATH = "/metadata/other/";
  private static final String REPRESENTATIONS_PATH = "/representations/";
  private static final String DATA_PATH = "/data/";
  private static final String SCHEMAS_PATH = "/schemas/";
  private static final String DOCUMENTATION_PATH = "/documentation/";

  private String parentID;
  private String objectID;
  private String profile;
  private EARKEnums.Type type;
  private String label;
  private EARKEnums.ContentType contentType;

  private List<SIPAgent> agents;
  private List<SIPDescriptiveMetadata> descriptiveMetadata;
  private List<SIPMetadata> administrativeMetadata;
  private List<SIPMetadata> otherMetadata;
  private Map<String, SIPRepresentation> representations;
  private List<Path> schemas;
  private List<Path> documentation;

  /**
   * @param sipName
   *          will be used as OBJID in METS (/mets[@OBJID])
   */
  public EARKSIP(String sipName, EARKEnums.ContentType contentType, String creator) throws SIPException {
    this.objectID = sipName;
    this.profile = "http://www.eark-project.com/METS/IP.xml";
    this.type = EARKEnums.Type.SIP;
    this.contentType = contentType;

    this.agents = new ArrayList<SIPAgent>();
    this.descriptiveMetadata = new ArrayList<SIPDescriptiveMetadata>();
    this.administrativeMetadata = new ArrayList<SIPMetadata>();
    this.otherMetadata = new ArrayList<SIPMetadata>();
    this.representations = new HashMap<String, SIPRepresentation>();
    this.schemas = new ArrayList<Path>();
    this.documentation = new ArrayList<Path>();

    SIPAgent creatorAgent = new SIPAgent(creator, "CREATOR", null, CreatorType.OTHER, "SOFTWARE");
    this.agents.add(creatorAgent);
  }

  /**
   * @param description
   *          will be used as LABEL in METS (/mets[@label])
   */
  public SIP setDescription(String description) {
    this.label = description;
    return this;
  }

  @Override
  public SIP addAgent(SIPAgent sipAgent) {
    agents.add(sipAgent);
    return this;
  }

  @Override
  public SIP addAdministrativeMetadata(SIPMetadata sipMetadata) throws SIPException {
    administrativeMetadata.add(sipMetadata);
    return this;
  }

  @Override
  public SIP addOtherMetadata(SIPMetadata sipMetadata) throws SIPException {
    otherMetadata.add(sipMetadata);
    return this;
  }

  @Override
  public SIP addDescriptiveMetadata(SIPDescriptiveMetadata metadata) throws SIPException {
    descriptiveMetadata.add(metadata);
    return this;
  }

  @Override
  public SIP addRepresentation(SIPRepresentation sipRepresentation) throws SIPException {
    if (representations.containsKey(sipRepresentation.getRepresentationID())) {
      throw new SIPException("Representation already exists");
    } else {
      representations.put(sipRepresentation.getRepresentationID(), sipRepresentation);
      return this;
    }

  }

  @Override
  public SIP addAgentToRepresentation(String representationID, SIPAgent agent) throws SIPException {
    if (!representations.containsKey(representationID)) {
      throw new SIPException("Representation doesn't exist");
    }
    SIPRepresentation rep = representations.get(representationID);
    rep.addAgent(agent);
    representations.put(representationID, rep);
    return this;
  }

  @Override
  public SIP addFileToRepresentation(String representationID, Path data, List<String> folders) throws SIPException {
    if (!representations.containsKey(representationID)) {
      throw new SIPException("Representation doesn't exist");
    }
    SIPRepresentation rep = representations.get(representationID);
    rep.addFile(data, folders);
    representations.put(representationID, rep);
    return this;
  }

  @Override
  public SIP addAdministrativeMetadataToRepresentation(String representationID, SIPMetadata administrativeMetadata)
    throws SIPException {
    if (!representations.containsKey(representationID)) {
      throw new SIPException("Representation doesn't exist");
    }
    SIPRepresentation rep = representations.get(representationID);
    rep.addAdministrativeMetadata(administrativeMetadata);
    representations.put(representationID, rep);
    return this;
  }

  @Override
  public Path build(Path destinationDirectory) throws SIPException {
    Path zipPath = getZipPath(destinationDirectory);
    List<ZipEntryInfo> zipEntries = new ArrayList<ZipEntryInfo>();
    Mets mainMETS = EARKMETSUtils.getMETSFromSIP(this);

    addDescriptiveMetadataToZipAndMETS(zipEntries, mainMETS);

    addAdministrativeMetadataToZipAndMETS(zipEntries, mainMETS);

    addOtherMetadataToZipAndMETS(zipEntries, mainMETS);

    addRepresentationsToZipAndMETS(zipEntries, mainMETS);

    addMainMETSToZip(zipEntries, mainMETS);

    createZipFile(zipPath, zipEntries);

    return zipPath;
  }

  /*
   * ---------------------------------------------------------------------------
   * ---------------------------------------------------------------------------
   */

  private Path getZipPath(Path destinationDirectory) throws SIPException {
    Path zipPath = destinationDirectory.resolve(objectID + FILE_EXTENSION);
    try {
      if (Files.exists(zipPath)) {
        Files.delete(zipPath);
      }
    } catch (IOException e) {
      throw new SIPException("Error deleting already existing zip", e);
    }
    return zipPath;
  }

  public void addDescriptiveMetadataToZipAndMETS(List<ZipEntryInfo> zipEntries, Mets mainMETS) throws SIPException {
    if (descriptiveMetadata != null && !descriptiveMetadata.isEmpty()) {
      for (SIPDescriptiveMetadata dm : descriptiveMetadata) {
        String descriptiveMetadataPath = METADATA_DESCRIPTIVE_PATH + dm.getMetadata().getFileName();
        zipEntries = ZIPUtils.addMetadataToZip(zipEntries, dm, descriptiveMetadataPath);
        mainMETS = EARKMETSUtils.addDescriptiveMetadataToMets(mainMETS, dm, descriptiveMetadataPath);
      }
    }
  }

  public void addAdministrativeMetadataToZipAndMETS(List<ZipEntryInfo> zipEntries, Mets mainMETS) throws SIPException {
    if (administrativeMetadata != null && !administrativeMetadata.isEmpty()) {
      for (SIPMetadata am : administrativeMetadata) {
        String administrativeMetadataPath = METADATA_ADMINISTRATIVE_PATH + am.getMetadata().getFileName();
        zipEntries = ZIPUtils.addMetadataToZip(zipEntries, am, administrativeMetadataPath);
        administrativeMetadataPath = METADATA_ADMINISTRATIVE_PATH + am.getMetadata().getFileName();
        // FIXME 20160203 hsilva: this should be added to
        // /mets/amdSec/digiprovMD/mdRef (and it is not being; be aware that the
        // following method is being used in other parts of the SIP "digest")
        mainMETS = EARKMETSUtils.addPreservationToMets(mainMETS, administrativeMetadataPath, am);
      }
    }
  }

  public void addOtherMetadataToZipAndMETS(List<ZipEntryInfo> zipEntries, Mets mainMETS) throws SIPException {
    if (otherMetadata != null && !otherMetadata.isEmpty()) {
      for (SIPMetadata om : otherMetadata) {
        String otherMetadataPath = METADATA_OTHER_PATH + om.getMetadata().getFileName();
        zipEntries = ZIPUtils.addMetadataToZip(zipEntries, om, otherMetadataPath);
        mainMETS = EARKMETSUtils.addOtherMetadataToMets(mainMETS, otherMetadataPath, om);
      }
    }
  }

  public void addRepresentationsToZipAndMETS(List<ZipEntryInfo> zipEntries, Mets mainMETS) throws SIPException {
    // representations
    if (representations != null && !representations.isEmpty()) {
      for (Map.Entry<String, SIPRepresentation> entry : representations.entrySet()) {
        String representationId = entry.getKey();
        SIPRepresentation representation = entry.getValue();

        Mets representationMETS = EARKMETSUtils.getMetsFromRepresentation(representationId, representation);
        if (representation.getAgents() != null && !representation.getAgents().isEmpty()) {
          EARKMETSUtils.addAgentsToMets(representationMETS, representation.getAgents());
        }
        // representation data
        if (representation.getData() != null && !representation.getData().isEmpty()) {
          for (Pair<Path, List<String>> data : representation.getData()) {
            Path dataFile = data.getFirst();

            String dataFilePath = DATA_PATH + getFoldersFromList(data.getSecond()) + "/"
              + dataFile.getFileName().toString();
            representationMETS = EARKMETSUtils.addDataToMets(representationMETS, dataFilePath, dataFile);

            dataFilePath = REPRESENTATIONS_PATH + representationId + dataFilePath;
            zipEntries = ZIPUtils.addDataToRepresentation(zipEntries, dataFile, dataFilePath);
          }
        }

        // representation administrative metadata
        if (representation.getAdministrativeMetadata() != null
          && !representation.getAdministrativeMetadata().isEmpty()) {
          for (SIPMetadata metadata : representation.getAdministrativeMetadata()) {
            String administrativeFilePath = REPRESENTATIONS_PATH + representationId + METADATA_ADMINISTRATIVE_PATH
              + metadata.getMetadata().getFileName().toString();
            ZIPUtils.addMetadataToZip(zipEntries, metadata, administrativeFilePath);
            administrativeFilePath = METADATA_ADMINISTRATIVE_PATH + metadata.getMetadata().getFileName().toString();
            representationMETS = EARKMETSUtils.addPreservationToMets(representationMETS, administrativeFilePath,
              metadata);

          }
        }

        // representation other metadata
        if (representation.getOtherMetadata() != null && !representation.getOtherMetadata().isEmpty()) {
          for (SIPMetadata metadata : representation.getOtherMetadata()) {
            String otherMetadataPath = REPRESENTATIONS_PATH + representationId + METADATA_OTHER_PATH
              + metadata.getMetadata().getFileName().toString();
            ZIPUtils.addMetadataToZip(zipEntries, metadata, otherMetadataPath);
            otherMetadataPath = METADATA_OTHER_PATH + metadata.getMetadata().getFileName().toString();
            representationMETS = EARKMETSUtils.addOtherMetadataToMets(representationMETS, otherMetadataPath, metadata);
          }
        }

        // representation descriptive metadata
        if (representation.getDescriptiveMetadata() != null && !representation.getDescriptiveMetadata().isEmpty()) {
          for (SIPDescriptiveMetadata metadata : representation.getDescriptiveMetadata()) {

            String descriptiveFilePath = REPRESENTATIONS_PATH + representationId + METADATA_DESCRIPTIVE_PATH
              + metadata.getMetadata().getFileName().toString();
            ZIPUtils.addMetadataToZip(zipEntries, metadata, descriptiveFilePath);
            descriptiveFilePath = METADATA_DESCRIPTIVE_PATH + metadata.getMetadata().getFileName().toString();
            representationMETS = EARKMETSUtils.addDescriptiveMetadataToMets(representationMETS, metadata,
              descriptiveFilePath);

          }
        }

        try {
          JAXBContext context = JAXBContext.newInstance(Mets.class);
          Marshaller m = context.createMarshaller();
          String representationMetsPath = REPRESENTATIONS_PATH + representationId + METS_PATH;
          Path temp = Files.createTempFile("METS", ".xml");
          m.marshal(representationMETS, Files.newOutputStream(temp));
          ZIPUtils.addFileToZip(zipEntries, temp, representationMetsPath);
          Mptr mptr = new Mptr();
          mptr.setLOCTYPE(LocType.URL.toString());
          mptr.setHref(URI_BASE_PATH + representationMetsPath);
          mainMETS.getStructMap().get(0).getDiv().getDiv().get(0).getMptr().add(mptr);
        } catch (JAXBException | IOException e) {
          throw new SIPException("Error saving representation METS", e);
        }
      }
    }
  }

  private void addMainMETSToZip(List<ZipEntryInfo> zipEntries, Mets mainMETS) throws SIPException {
    try {
      JAXBContext context = JAXBContext.newInstance(Mets.class);
      Marshaller m = context.createMarshaller();
      Path temp = Files.createTempFile("METS", ".xml");
      m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
      m.marshal(mainMETS, Files.newOutputStream(temp));
      ZIPUtils.addFileToZip(zipEntries, temp, METS_PATH);
    } catch (JAXBException | IOException e) {
      throw new SIPException(e.getMessage(), e);
    }
  }

  private void createZipFile(Path zipPath, List<ZipEntryInfo> zipEntries) throws SIPException {
    try {
      ZIPUtils.zip(zipEntries, Files.newOutputStream(zipPath));
    } catch (IOException e) {
      throw new SIPException("Error generating E-ARK SIP ZIP file", e);
    }
  }

  private String getFoldersFromList(List<String> folders) {
    StringBuilder sb = new StringBuilder();
    for (String folder : folders) {
      if (sb.length() > 0) {
        sb.append("/");
      }
      sb.append(folder);
    }
    return sb.toString();
  }

  @Override
  public SIP addDescriptiveMetadataToRepresentation(String representationID, SIPDescriptiveMetadata descriptiveMetadata)
    throws SIPException {
    if (!representations.containsKey(representationID)) {
      throw new SIPException("Representation doesn't exist");
    }
    SIPRepresentation rep = representations.get(representationID);
    rep.addDescriptiveMetadata(descriptiveMetadata);
    representations.put(representationID, rep);
    return this;
  }

  @Override
  public SIP addOtherMetadataToRepresentation(String representationID, SIPMetadata otherMetadata) throws SIPException {
    if (!representations.containsKey(representationID)) {
      throw new SIPException("Representation doesn't exist");
    }
    SIPRepresentation rep = representations.get(representationID);
    rep.addOtherMetadata(otherMetadata);
    representations.put(representationID, rep);
    return this;
  }

  @Override
  public SIP addDocumentation(Path documentationPath) {
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

  public String getLabel() {
    return label;
  }

  @Override
  public List<SIPAgent> getAgents() {
    return agents;
  }

  @Override
  public List<SIPDescriptiveMetadata> getDescriptiveMetadata() {
    return descriptiveMetadata;
  }

  @Override
  public List<SIPMetadata> getAdministrativeMetadata() {
    return administrativeMetadata;
  }

  @Override
  public List<SIPMetadata> getOtherMetadata() {
    return otherMetadata;
  }

  @Override
  public List<SIPRepresentation> getRepresentations() {
    return new ArrayList<SIPRepresentation>(representations.values());
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
  public SIP addSchema(Path schemaPath) {
    schemas.add(schemaPath);
    return this;
  }

  @Override
  public SIP addDocumentationToRepresentation(String representationID, Path documentationPath) throws SIPException {
    if (!representations.containsKey(representationID)) {
      throw new SIPException("Representation doesn't exist");
    }
    SIPRepresentation rep = representations.get(representationID);
    rep.addDocumentation(documentationPath);
    representations.put(representationID, rep);
    return this;
  }

  @Override
  public SIP addSchemaToRepresentation(String representationID, Path schemaPath) throws SIPException {
    if (!representations.containsKey(representationID)) {
      throw new SIPException("Representation doesn't exist");
    }
    SIPRepresentation rep = representations.get(representationID);
    rep.addSchema(schemaPath);
    representations.put(representationID, rep);
    return this;
  }

  @Override
  public List<Path> getSchemas() {
    return schemas;
  }

  @Override
  public List<Path> getDocumentation() {
    return documentation;
  }
}
