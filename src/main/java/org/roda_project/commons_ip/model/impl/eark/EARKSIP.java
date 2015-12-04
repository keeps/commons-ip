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
import java.nio.file.Paths;
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
import org.roda_project.commons_ip.utils.SIPException;
import org.roda_project.commons_ip.utils.Utils;
import org.roda_project.commons_ip.utils.ZIPUtils;

public class EARKSIP implements SIP {
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
  public SIP addDataToRepresentation(String representationID, Path data) throws SIPException {
    if (!representations.containsKey(representationID)) {
      throw new SIPException("Representation doesn't exist", null);
    }
    SIPRepresentation rep = representations.get(representationID);
    rep.addData(data);
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
  public Path build() throws SIPException {

    Path zipPath = Paths.get(objectID + ".zip");
    try {
      if (Files.exists(zipPath)) {
        Files.delete(zipPath);
      }
    } catch (IOException ioe) {
      throw new SIPException("Error deleting existing zip", ioe);
    }
    Mets mainMets = EARKMETSUtils.getMetsFromSIP(this);

    if (otherMetadata != null && !otherMetadata.isEmpty()) {
      for (SIPMetadata om : otherMetadata) {
        String otherMetadataPath = "/metadata/other/" + om.getMetadata().getFileName().toString();
        ZIPUtils.addMetadataToZip(zipPath, om, otherMetadataPath);
        otherMetadataPath = "metadata/other/" + om.getMetadata().getFileName().toString();
        mainMets = EARKMETSUtils.addOtherMetadataToMets(mainMets, otherMetadataPath, om);
      }
    }
    if (administrativeMetadata != null && !administrativeMetadata.isEmpty()) {
      for (SIPMetadata am : administrativeMetadata) {
        String path = "/metadata/administrative/" + am.getMetadata().getFileName().toString();
        ZIPUtils.addMetadataToZip(zipPath, am, path);
        path = "metadata/administrative/" + am.getMetadata().getFileName().toString();
        mainMets = EARKMETSUtils.addPreservationToMets(mainMets, path, am);
      }
    }

    if (descriptiveMetadata != null && !descriptiveMetadata.isEmpty()) {
      for (SIPDescriptiveMetadata dm : descriptiveMetadata) {
        String descriptiveMetadataPath = "/metadata/descriptive/" + dm.getMetadata().getFileName().toString();
        ZIPUtils.addMetadataToZip(zipPath, dm, descriptiveMetadataPath);
        descriptiveMetadataPath = "metadata/descriptive/" + dm.getMetadata().getFileName().toString();
        mainMets = EARKMETSUtils.addDescriptiveMetadataToMets(mainMets, dm, descriptiveMetadataPath);
      }
    }
    if (representations != null && !representations.isEmpty()) {
      for (Map.Entry<String, SIPRepresentation> entry : representations.entrySet()) {
        ZIPUtils.createRepresentationFolder(zipPath, entry.getKey());
        Mets representationMETS = EARKMETSUtils.getMetsFromRepresentation(entry.getKey(), entry.getValue());
        if (entry.getValue().getAgents() != null && !entry.getValue().getAgents().isEmpty()) {
          EARKMETSUtils.addAgentsToMets(representationMETS, entry.getValue().getAgents());
        }
        if (entry.getValue().getData() != null && !entry.getValue().getData().isEmpty()) {
          for (Path dataFile : entry.getValue().getData()) {
            String dataFilePath = "/representations/" + entry.getKey() + "/data/" + dataFile.getFileName().toString();
            ZIPUtils.addDataToRepresentation(zipPath, dataFile, dataFilePath);
            dataFilePath = "data/" + dataFile.getFileName().toString();
            representationMETS = EARKMETSUtils.addDataToMets(representationMETS, dataFilePath, dataFile);

          }
        }
        if (entry.getValue().getAdministrativeMetadata() != null
          && !entry.getValue().getAdministrativeMetadata().isEmpty()) {
          for (SIPMetadata metadata : entry.getValue().getAdministrativeMetadata()) {
            String administrativeFilePath = "/representations/" + entry.getKey() + "/metadata/administrative/"
              + metadata.getMetadata().getFileName().toString();
            ZIPUtils.addMetadataToZip(zipPath, metadata, administrativeFilePath);
            administrativeFilePath = "metadata/administrative/" + metadata.getMetadata().getFileName().toString();
            representationMETS = EARKMETSUtils.addPreservationToMets(representationMETS, administrativeFilePath,
              metadata);

          }
        }
        if (entry.getValue().getOtherMetadata() != null && !entry.getValue().getOtherMetadata().isEmpty()) {
          for (SIPMetadata metadata : entry.getValue().getOtherMetadata()) {
            String otherMetadataPath = "/representations/" + entry.getKey() + "/metadata/other/"
              + metadata.getMetadata().getFileName().toString();
            ZIPUtils.addMetadataToZip(zipPath, metadata, otherMetadataPath);
            otherMetadataPath = "metadata/other/" + metadata.getMetadata().getFileName().toString();
            representationMETS = EARKMETSUtils.addOtherMetadataToMets(representationMETS, otherMetadataPath, metadata);
          }
        }
        if (entry.getValue().getDescriptiveMetadata() != null && !entry.getValue().getDescriptiveMetadata().isEmpty()) {
          for (SIPDescriptiveMetadata metadata : entry.getValue().getDescriptiveMetadata()) {

            String descriptiveFilePath = "/representations/" + entry.getKey() + "/metadata/descriptive/"
              + metadata.getMetadata().getFileName().toString();
            ZIPUtils.addMetadataToZip(zipPath, metadata, descriptiveFilePath);
            descriptiveFilePath = "metadata/descriptive/" + metadata.getMetadata().getFileName().toString();
            representationMETS = EARKMETSUtils.addDescriptiveMetadataToMets(representationMETS, metadata,
              descriptiveFilePath);

          }
        }
        try {
          JAXBContext context = JAXBContext.newInstance(Mets.class);
          Marshaller m = context.createMarshaller();
          String representationMetsPath = "/representations/" + entry.getKey() + "/METS.xml";
          Path temp = Files.createTempFile("METS", ".xml");
          m.marshal(representationMETS, Files.newOutputStream(temp));
          Utils.addFileToZip(zipPath, temp, representationMetsPath);
          Mptr mptr = new Mptr();
          mptr.setLOCTYPE(LocType.URL.toString());
          mptr.setHref("file://./" + representationMetsPath);
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
      Utils.addFileToZip(zipPath, temp, "/METS.xml");
    } catch (JAXBException | IOException e) {
      throw new SIPException(e.getMessage(), e);
    }
    return zipPath;
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

}
