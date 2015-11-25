/**
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE file at the root of the source
 * tree and available online at
 *
 * https://github.com/keeps/commons-ip
 */
package org.roda_project.commons_ip.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.UUID;

import javax.xml.datatype.DatatypeConfigurationException;

import org.roda_project.commons_ip.mets_v1_11.beans.AmdSecType;
import org.roda_project.commons_ip.mets_v1_11.beans.DivType;
import org.roda_project.commons_ip.mets_v1_11.beans.DivType.Fptr;
import org.roda_project.commons_ip.mets_v1_11.beans.FileType;
import org.roda_project.commons_ip.mets_v1_11.beans.FileType.FLocat;
import org.roda_project.commons_ip.mets_v1_11.beans.MdSecType;
import org.roda_project.commons_ip.mets_v1_11.beans.MdSecType.MdRef;
import org.roda_project.commons_ip.mets_v1_11.beans.Mets;
import org.roda_project.commons_ip.mets_v1_11.beans.MetsType.FileSec;
import org.roda_project.commons_ip.mets_v1_11.beans.MetsType.FileSec.FileGrp;
import org.roda_project.commons_ip.mets_v1_11.beans.MetsType.MetsHdr;
import org.roda_project.commons_ip.mets_v1_11.beans.MetsType.MetsHdr.Agent;
import org.roda_project.commons_ip.mets_v1_11.beans.StructMapType;
import org.roda_project.commons_ip.model.SIPAgent;
import org.roda_project.commons_ip.model.SIPDescriptiveMetadata;
import org.roda_project.commons_ip.model.SIPMetadata;
import org.roda_project.commons_ip.model.SIPRepresentation;
import org.roda_project.commons_ip.utils.METSEnums.LocType;

public class METSUtils {
  public static Mets addAgentsToMets(Mets mets, List<SIPAgent> agents) {
    MetsHdr hdr = mets.getMetsHdr();
    if (hdr == null) {
      hdr = new MetsHdr();
    }
    for (SIPAgent sipAgent : agents) {
      Agent agent = new Agent();
      agent.setROLE(sipAgent.getRole());
      agent.setTYPE(sipAgent.getType().toString());
      agent.setName(sipAgent.getName());
      agent.setOTHERROLE(sipAgent.getOtherRole());
      agent.setOTHERTYPE(sipAgent.getOtherType());
      hdr.getAgent().add(agent);
    }
    mets.setMetsHdr(hdr);
    return mets;
  }

  public static Mets addDescriptiveMetadataToMets(Mets mainMets, SIPDescriptiveMetadata dm,
    String descriptiveMetadataPath) throws SIPException {
    MdRef mdref = new MdRef();
    try {
      mdref.setCHECKSUM(Utils.calculateChecksum(Files.newInputStream(dm.getMetadata()), "SHA-256"));
    } catch (NoSuchAlgorithmException nsae) {
      throw new SIPException("Error calculating checskum: the algorithm provided is not recognized", nsae);
    } catch (IOException ioe) {
      throw new SIPException("Error calculation checksum", ioe);
    }
    mdref.setCHECKSUMTYPE("SHA-256");
    try {
      mdref.setCREATED(Utils.getCurrentCalendar());
    } catch (DatatypeConfigurationException dce) {
      throw new SIPException("Error getting current calendar", dce);
    }
    mdref.setLOCTYPE(LocType.URL.toString());
    mdref.setMDTYPE(dm.getMetadataType().toString());
    mdref.setMIMETYPE("text/xml");
    try {
      mdref.setSIZE(Files.size(dm.getMetadata()));
    } catch (IOException e) {
      throw new SIPException("Error calculating file size", e);
    }
    mdref.setHref(descriptiveMetadataPath);
    mdref.setType("simple");

    MdSecType dmdsec = new MdSecType();
    dmdsec.setID("DMD" + mainMets.getDmdSec().size());
    dmdsec.setMdRef(mdref);
    mainMets.getDmdSec().add(dmdsec);
    return mainMets;
  }

  public static Mets getMetsFromRepresentation(String representationID, SIPRepresentation representation)
    throws SIPException {
    Mets representationMETS = new Mets();
    representationMETS.setOBJID(representation.getObjectID());
    representationMETS.setPROFILE(representation.getProfile());
    representationMETS.setTYPE(representation.getType());
    MetsHdr representationHeader = new MetsHdr();
    try {
      representationHeader.setCREATEDATE(Utils.getCurrentCalendar());
      representationHeader.setLASTMODDATE(Utils.getCurrentCalendar());
    } catch (DatatypeConfigurationException dce) {
      throw new SIPException("Error getting current calendar", dce);
    }
    representationMETS.setMetsHdr(representationHeader);

    AmdSecType amdsecRep = new AmdSecType();
    amdsecRep.setID(UUID.randomUUID().toString());
    representationMETS.getAmdSec().add(amdsecRep);

    FileSec filesecRep = new FileSec();
    filesecRep.setID(UUID.randomUUID().toString());

    FileGrp generalFileGroup = new FileGrp();
    generalFileGroup.setUSE("general filegroup");
    generalFileGroup.setID(UUID.randomUUID().toString());
    filesecRep.getFileGrp().add(generalFileGroup);
    FileGrp schemaFileGroup = new FileGrp();
    schemaFileGroup.setUSE("schema group");
    schemaFileGroup.setID(UUID.randomUUID().toString());
    filesecRep.getFileGrp().add(schemaFileGroup);

    representationMETS.setFileSec(filesecRep);

    StructMapType structMapRep = new StructMapType();
    DivType packageDivRep = new DivType();
    packageDivRep.setLabel("Package");
    packageDivRep.setID("packageDiv");
    DivType contentDiv = new DivType();
    contentDiv.setID("contentDiv");
    contentDiv.setLabel("Content");
    packageDivRep.getDiv().add(contentDiv);
    DivType metadataDiv = new DivType();
    metadataDiv.setID("metadataDiv");
    metadataDiv.setLabel("Metadata");
    packageDivRep.getDiv().add(metadataDiv);
    DivType schemasDiv = new DivType();
    schemasDiv.setID("schemasDiv");
    schemasDiv.setLabel("Schemas");
    packageDivRep.getDiv().add(schemasDiv);
    structMapRep.setDiv(packageDivRep);
    representationMETS.getStructMap().add(structMapRep);
    return representationMETS;
  }

  public static Mets addDataToMets(Mets representationMETS, String dataFilePath, Path dataFile) throws SIPException {
    FileType ft = new FileType();
    try {
      ft.setCHECKSUM(Utils.calculateChecksum(Files.newInputStream(dataFile), "SHA-256"));
    } catch (IOException e) {
      throw new SIPException("Error calculating checksum for file" + dataFile.toString(), e);
    } catch (NoSuchAlgorithmException e) {
      throw new SIPException("Error calculating checksum for file " + dataFile.toString() + " (no such algorithm)", e);
    }
    ft.setCHECKSUMTYPE("SHA-256");
    try {
      ft.setMIMETYPE(Files.probeContentType(dataFile));
    } catch (IOException e) {
      throw new SIPException("Error probing content-type(" + dataFile.toString() + ")", e);
    }
    try {
      ft.setCREATED(Utils.getCurrentCalendar());
    } catch (DatatypeConfigurationException e) {
      throw new SIPException("Error getting curent calendar (" + dataFile.toString() + ")", e);
    }
    try {
      ft.setSIZE(Files.size(dataFile));
    } catch (IOException e) {
      throw new SIPException("Error getting file size (" + dataFile.toString() + ")", e);
    }
    ft.setID(UUID.randomUUID().toString());
    FLocat locat = new FLocat();
    locat.setType("simple");
    locat.setLOCTYPE("URL");
    locat.setHref("file://./data/" + dataFile.getFileName().toString());
    ft.getFLocat().add(locat);
    representationMETS.getFileSec().getFileGrp().get(0).getFile().add(ft);

    Fptr fptr = new Fptr();
    fptr.setFILEID(ft);
    representationMETS.getStructMap().get(0).getDiv().getDiv().get(0).getFptr().add(fptr);
    return representationMETS;
  }

  public static Mets addPreservationToMets(Mets representationMETS, String preservationFilePath, SIPMetadata metadata)
    throws SIPException {
    FileType ft = new FileType();
    try {
      ft.setCHECKSUM(Utils.calculateChecksum(Files.newInputStream(metadata.getMetadata()), "SHA-256"));
    } catch (IOException e) {
      throw new SIPException("Error calculating checksum for representation preservation metadata", e);
    } catch (NoSuchAlgorithmException e) {
      throw new SIPException("Error calculating checksum for representation preservation metadata (no such algorithm)",
        e);

    }
    ft.setCHECKSUMTYPE("SHA-256");
    try {
      ft.setMIMETYPE(Files.probeContentType(metadata.getMetadata()));
    } catch (IOException e) {
      throw new SIPException("Error probing file content (" + metadata.getMetadata().toString() + ")", e);
    }
    try {
      ft.setCREATED(Utils.getCurrentCalendar());
    } catch (DatatypeConfigurationException dce) {
      throw new SIPException("Error getting current calendar", dce);
    }
    try {
      ft.setSIZE(Files.size(metadata.getMetadata()));
    } catch (IOException e) {
      throw new SIPException("Error getting file size (" + metadata.getMetadata().toString() + ")", e);
    }
    ft.setID(UUID.randomUUID().toString());
    FLocat locat = new FLocat();
    locat.setType("URL");
    locat.setHref("file://./metadata/preservation/" + metadata.getMetadata().getFileName().toString());
    ft.getFLocat().add(locat);
    representationMETS.getFileSec().getFileGrp().get(0).getFile().add(ft);

    Fptr fptr = new Fptr();
    fptr.setFILEID(ft);
    representationMETS.getStructMap().get(0).getDiv().getDiv().get(1).getFptr().add(fptr);
    return representationMETS;
  }
}
