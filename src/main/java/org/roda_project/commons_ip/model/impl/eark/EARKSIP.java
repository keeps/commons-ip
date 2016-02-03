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
  private static final String DATA_PATH = "/data/";
  private static final String REPRESENTATIONS_PATH = "/representations/";

  private String parentID;
  private String objectID;
  private String profile;
  private EARKEnums.Type type;
  private String label;
  private EARKEnums.ContentType contentType;
  private Map<String, SIPRepresentation> representations;
  private List<SIPAgent> agents;
  private List<SIPDescriptiveMetadata> descriptiveMetadata;
  private List<SIPMetadata> administrativeMetadata;
  private List<SIPMetadata> otherMetadata;
  private List<Path> documentation;

  public EARKSIP(String sipName, EARKEnums.ContentType contentType, String creator) throws SIPException {
    this.objectID = sipName;
    this.profile = "UNDEFINED";
    this.type = EARKEnums.Type.SIP;
    this.contentType = contentType;

    this.representations = new HashMap<String, SIPRepresentation>();
    this.agents = new ArrayList<SIPAgent>();

    this.descriptiveMetadata = new ArrayList<SIPDescriptiveMetadata>();
    this.administrativeMetadata = new ArrayList<SIPMetadata>();
    this.otherMetadata = new ArrayList<SIPMetadata>();
    this.documentation = new ArrayList<Path>();

    SIPAgent creatorAgent = new SIPAgent(creator, "CREATOR", CreatorType.OTHER, null, "SOFTWARE");
    this.agents.add(creatorAgent);
  }

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
      throw new SIPException("Representation already exists", null);
    } else {
      representations.put(sipRepresentation.getRepresentationID(), sipRepresentation);
      return this;
    }

  }

  @Override
  public SIP addAgentToRepresentation(String representationID, SIPAgent agent) throws SIPException {
    if (!representations.containsKey(representationID)) {
      throw new SIPException("Representation doesn't exist", null);
    }
    SIPRepresentation rep = representations.get(representationID);
    rep.addAgent(agent);
    representations.put(representationID, rep);
    return this;
  }

  @Override
  public SIP addDataToRepresentation(String representationID, Path data, List<String> folders) throws SIPException {
    if (!representations.containsKey(representationID)) {
      throw new SIPException("Representation doesn't exist", null);
    }
    SIPRepresentation rep = representations.get(representationID);
    rep.addData(data, folders);
    representations.put(representationID, rep);
    return this;
  }

  @Override
  public SIP addAdministrativeMetadataToRepresentation(String representationID, SIPMetadata administrativeMetadata)
    throws SIPException {
    if (!representations.containsKey(representationID)) {
      throw new SIPException("Representation doesn't exist", null);
    }
    SIPRepresentation rep = representations.get(representationID);
    rep.addAdministrativeMetadata(administrativeMetadata);
    representations.put(representationID, rep);
    return this;
  }

  @Override
  public Path build(Path destinationDirectory) throws SIPException {

    Path zipPath = destinationDirectory.resolve(objectID + FILE_EXTENSION);
    try {
      if (Files.exists(zipPath)) {
        Files.delete(zipPath);
      }
    } catch (IOException ioe) {
      throw new SIPException("Error deleting existing zip", ioe);
    }
    Mets mainMets = EARKMETSUtils.getMetsFromSIP(this);

    List<ZipEntryInfo> zipEntries = new ArrayList<ZipEntryInfo>();

    // descriptive metadata
    if (descriptiveMetadata != null && !descriptiveMetadata.isEmpty()) {
      for (SIPDescriptiveMetadata dm : descriptiveMetadata) {
        String descriptiveMetadataPath = METADATA_DESCRIPTIVE_PATH + dm.getMetadata().getFileName().toString();
        zipEntries = ZIPUtils.addMetadataToZip(zipEntries, dm, descriptiveMetadataPath);
        mainMets = EARKMETSUtils.addDescriptiveMetadataToMets(mainMets, dm, descriptiveMetadataPath);
      }
    }

    // administrative metadata
    if (administrativeMetadata != null && !administrativeMetadata.isEmpty()) {
      for (SIPMetadata am : administrativeMetadata) {
        String administrativeMetadataPath = METADATA_ADMINISTRATIVE_PATH + am.getMetadata().getFileName().toString();
        zipEntries = ZIPUtils.addMetadataToZip(zipEntries, am, administrativeMetadataPath);
        administrativeMetadataPath = METADATA_ADMINISTRATIVE_PATH + am.getMetadata().getFileName().toString();
        mainMets = EARKMETSUtils.addPreservationToMets(mainMets, administrativeMetadataPath, am);
      }
    }

    // other metadata
    if (otherMetadata != null && !otherMetadata.isEmpty()) {
      for (SIPMetadata om : otherMetadata) {
        String otherMetadataPath = METADATA_OTHER_PATH + om.getMetadata().getFileName().toString();
        zipEntries = ZIPUtils.addMetadataToZip(zipEntries, om, otherMetadataPath);
        mainMets = EARKMETSUtils.addOtherMetadataToMets(mainMets, otherMetadataPath, om);
      }
    }

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
          mainMets.getStructMap().get(0).getDiv().getDiv().get(0).getMptr().add(mptr);
        } catch (JAXBException | IOException e) {
          throw new SIPException("Error saving representation METS", e);
        }
      }
    }

    try {
      JAXBContext context = JAXBContext.newInstance(Mets.class);
      Marshaller m = context.createMarshaller();
      Path temp = Files.createTempFile("METS", ".xml");
      m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
      m.marshal(mainMets, Files.newOutputStream(temp));
      ZIPUtils.addFileToZip(zipEntries, temp, METS_PATH);
    } catch (JAXBException | IOException e) {
      throw new SIPException(e.getMessage(), e);
    }

    try {
      ZIPUtils.zip(zipEntries, Files.newOutputStream(zipPath));
    } catch (IOException e) {
      throw new SIPException("Error generating E-ARK SIP ZIP file", e);
    }

    return zipPath;
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
      throw new SIPException("Representation doesn't exist", null);
    }
    SIPRepresentation rep = representations.get(representationID);
    rep.addDescriptiveMetadata(descriptiveMetadata);
    representations.put(representationID, rep);
    return this;
  }

  @Override
  public SIP addOtherMetadataToRepresentation(String representationID, SIPMetadata otherMetadata) throws SIPException {
    if (!representations.containsKey(representationID)) {
      throw new SIPException("Representation doesn't exist", null);
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
}
