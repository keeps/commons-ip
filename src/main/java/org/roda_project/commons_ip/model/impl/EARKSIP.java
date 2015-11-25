/**
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE file at the root of the source
 * tree and available online at
 *
 * https://github.com/keeps/commons-ip
 */
package org.roda_project.commons_ip.model.impl;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.XMLGregorianCalendar;

import org.roda_project.commons_ip.mets_v1_11.beans.AmdSecType;
import org.roda_project.commons_ip.mets_v1_11.beans.DivType;
import org.roda_project.commons_ip.mets_v1_11.beans.DivType.Mptr;
import org.roda_project.commons_ip.mets_v1_11.beans.Mets;
import org.roda_project.commons_ip.mets_v1_11.beans.MetsType.FileSec;
import org.roda_project.commons_ip.mets_v1_11.beans.MetsType.MetsHdr;
import org.roda_project.commons_ip.mets_v1_11.beans.MetsType.MetsHdr.MetsDocumentID;
import org.roda_project.commons_ip.mets_v1_11.beans.StructMapType;
import org.roda_project.commons_ip.model.SIP;
import org.roda_project.commons_ip.model.SIPAgent;
import org.roda_project.commons_ip.model.SIPDescriptiveMetadata;
import org.roda_project.commons_ip.model.SIPMetadata;
import org.roda_project.commons_ip.model.SIPRepresentation;
import org.roda_project.commons_ip.utils.METSEnums.LocType;
import org.roda_project.commons_ip.utils.METSUtils;
import org.roda_project.commons_ip.utils.SIPException;
import org.roda_project.commons_ip.utils.Utils;
import org.roda_project.commons_ip.utils.ZIPUtils;

public class EARKSIP implements SIP {
  private String objectID;
  private String profile;
  private String type;
  private Map<String, SIPRepresentation> representations;
  private List<SIPAgent> agents;
  private List<SIPMetadata> metadata;
  private List<SIPDescriptiveMetadata> descriptiveMetadata;

  public EARKSIP(String objectID, String profile, String type) throws SIPException {
    this.objectID = objectID;
    this.profile = profile;
    this.type = type;
    representations = new HashMap<String, SIPRepresentation>();
    agents = new ArrayList<SIPAgent>();
    metadata = new ArrayList<SIPMetadata>();
    descriptiveMetadata = new ArrayList<SIPDescriptiveMetadata>();
  }

  @Override
  public SIP addAgent(SIPAgent sipAgent) {
    agents.add(sipAgent);
    return this;
  }

  @Override
  public SIP addMetadata(SIPMetadata sipMetadata) throws SIPException {
    metadata.add(sipMetadata);
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
  public SIP addPreservationToRepresentation(String representationID, SIPMetadata preservationMetadata)
    throws SIPException {
    if (!representations.containsKey(representationID)) {
      throw new SIPException("Representation doesn't exist", null);
    }
    SIPRepresentation rep = representations.get(representationID);
    rep.addPreservationMetadata(preservationMetadata);
    representations.put(representationID, rep);
    return this;
  }

  @Override
  public Path build() throws SIPException {

    Path zipPath = Paths.get(UUID.randomUUID().toString() + ".zip");

    Mets mainMets = new Mets();
    mainMets.setOBJID(objectID);
    mainMets.setPROFILE(profile);
    mainMets.setTYPE(type);
    MetsHdr header = new MetsHdr();
    try {
      XMLGregorianCalendar cal = Utils.getCurrentCalendar();
      header.setCREATEDATE(cal);
      header.setLASTMODDATE(cal);
    } catch (DatatypeConfigurationException dce) {
      throw new SIPException("Error getting current calendar", dce);
    }
    MetsDocumentID metsID = new MetsDocumentID();
    metsID.setValue("METS.xml");
    header.setMetsDocumentID(metsID);

    mainMets.setMetsHdr(header);
    if (agents != null && agents.size() > 0) {
      mainMets = METSUtils.addAgentsToMets(mainMets, agents);
    }

    // empty amdsec
    AmdSecType amdsec = new AmdSecType();
    amdsec.setID(UUID.randomUUID().toString());
    mainMets.getAmdSec().add(amdsec);

    // empty filesec
    FileSec filesec = new FileSec();
    filesec.setID(UUID.randomUUID().toString());
    mainMets.setFileSec(filesec);

    StructMapType structMap = new StructMapType();
    DivType packageDiv = new DivType();
    packageDiv.setLabel("Package");
    packageDiv.setID("packageDiv");
    DivType representationMetadataDiv = new DivType();
    representationMetadataDiv.setID("representationMetadataDiv");
    representationMetadataDiv.setLabel("Representation Metadata");
    packageDiv.getDiv().add(representationMetadataDiv);
    structMap.setDiv(packageDiv);
    mainMets.getStructMap().add(structMap);

    if (metadata != null && metadata.size() > 0) {
      for (SIPMetadata dm : metadata) {
        ZIPUtils.addMetadataToZip(zipPath, dm);
      }
    }
    if (descriptiveMetadata != null && descriptiveMetadata.size() > 0) {
      for (SIPDescriptiveMetadata dm : descriptiveMetadata) {
        String descriptiveMetadataPath = "/metadata/descriptive/" + dm.getMetadata().getFileName().toString();
        ZIPUtils.addDescriptiveMetadataToZip(zipPath, dm, descriptiveMetadataPath);
        mainMets = METSUtils.addDescriptiveMetadataToMets(mainMets, dm, descriptiveMetadataPath);

      }
    }
    if (representations != null && representations.size() > 0) {
      for (Map.Entry<String, SIPRepresentation> entry : representations.entrySet()) {
        ZIPUtils.createRepresentationFolder(zipPath, entry.getKey());
        Mets representationMETS = METSUtils.getMetsFromRepresentation(entry.getKey(), entry.getValue());
        if (entry.getValue().getAgents() != null && entry.getValue().getAgents().size() > 0) {
          METSUtils.addAgentsToMets(representationMETS, entry.getValue().getAgents());
        }
        if (entry.getValue().getData() != null && entry.getValue().getData().size() > 0) {

          for (Path dataFile : entry.getValue().getData()) {
            String dataFilePath = "/representations/" + entry.getKey() + "/data/" + dataFile.getFileName().toString();
            ZIPUtils.addDataToRepresentation(zipPath, dataFile, dataFilePath);
            representationMETS = METSUtils.addDataToMets(representationMETS, dataFilePath, dataFile);

          }
        }
        if (entry.getValue().getPreservationMetadata() != null
          && entry.getValue().getPreservationMetadata().size() > 0) {
          for (SIPMetadata metadata : entry.getValue().getPreservationMetadata()) {

            String preservationFilePath = "/representations/" + entry.getKey() + "/metadata/preservation/"
              + metadata.getMetadata().getFileName().toString();
            ZIPUtils.addPreservationMetadataToRepresentation(zipPath, preservationFilePath, metadata);
            representationMETS = METSUtils.addPreservationToMets(representationMETS, preservationFilePath, metadata);

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
          mptr.setHref("file://." + representationMetsPath);
          mainMets.getStructMap().get(0).getDiv().getDiv().get(0).getMptr().add(mptr);
        } catch (Throwable t) {
          throw new SIPException("Error saving representation METS: " + t.getMessage(), t);
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
    } catch (Throwable e) {
      throw new SIPException(e.getMessage(), e);
    }
    return zipPath;
  }
}
