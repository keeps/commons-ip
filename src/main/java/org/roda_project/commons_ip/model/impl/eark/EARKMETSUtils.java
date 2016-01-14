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
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.UUID;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.datatype.DatatypeConfigurationException;

import org.roda_project.commons_ip.mets_v1_11.beans.AmdSecType;
import org.roda_project.commons_ip.mets_v1_11.beans.DivType;
import org.roda_project.commons_ip.mets_v1_11.beans.DivType.Fptr;
import org.roda_project.commons_ip.mets_v1_11.beans.DivType.Mptr;
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
import org.roda_project.commons_ip.utils.METSEnums;
import org.roda_project.commons_ip.utils.METSEnums.LocType;
import org.roda_project.commons_ip.utils.SIPException;
import org.roda_project.commons_ip.utils.Utils;

public final class EARKMETSUtils {

  private EARKMETSUtils() {

  }

  public static Mets processMetsXML(Path mainMETSFile) throws JAXBException {
    JAXBContext jaxbContext = JAXBContext.newInstance(Mets.class);
    Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
    return (Mets) jaxbUnmarshaller.unmarshal(mainMETSFile.toFile());
  }

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
    } catch (NoSuchAlgorithmException e) {
      throw new SIPException("Error calculating checskum: the algorithm provided is not recognized", e);
    } catch (IOException e) {
      throw new SIPException("Error calculating checksum", e);
    }
    mdref.setCHECKSUMTYPE("SHA-256");
    try {
      mdref.setCREATED(Utils.getCurrentCalendar());
    } catch (DatatypeConfigurationException dce) {
      throw new SIPException("Error getting current calendar", dce);
    }
    mdref.setLOCTYPE(LocType.URL.toString());
    mdref.setMDTYPE(dm.getMetadataType().toString());
    mdref.setMDTYPEVERSION(dm.getMetadataVersion());
    mdref.setMIMETYPE("text/xml");
    try {
      mdref.setSIZE(Files.size(dm.getMetadata()));
    } catch (IOException e) {
      throw new SIPException("Error calculating file size", e);
    }
    mdref.setHref("file://./" + descriptiveMetadataPath);
    mdref.setType("simple");

    MdSecType dmdsec = new MdSecType();
    dmdsec.setID("DMD" + mainMets.getDmdSec().size());
    dmdsec.setMdRef(mdref);
    mainMets.getDmdSec().add(dmdsec);
    return mainMets;
  }

  public static Mets getMetsFromSIP(EARKSIP sip) throws SIPException {
    Mets sipMets = new Mets();
    sipMets.setOBJID(sip.getObjectID());
    sipMets.setPROFILE(sip.getProfile());
    sipMets.setTYPE(sip.getType());
    sipMets.setMetsTypeLabel(sip.getLabel());
    MetsHdr representationHeader = new MetsHdr();
    try {
      representationHeader.setCREATEDATE(Utils.getCurrentCalendar());
      representationHeader.setLASTMODDATE(Utils.getCurrentCalendar());
    } catch (DatatypeConfigurationException dce) {
      throw new SIPException("Error getting current calendar", dce);
    }
    sipMets.setMetsHdr(representationHeader);

    AmdSecType amdsecRep = new AmdSecType();
    amdsecRep.setID(UUID.randomUUID().toString());
    sipMets.getAmdSec().add(amdsecRep);

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

    sipMets.setFileSec(filesecRep);

    StructMapType structMapRep = new StructMapType();
    DivType packageDivRep = new DivType();
    packageDivRep.setLabel("Package");
    packageDivRep.setID("packageDiv");
    DivType contentDiv = new DivType();
    contentDiv.setID("contentDiv");
    contentDiv.setDivTypeLabel("Content");
    packageDivRep.getDiv().add(contentDiv);
    DivType metadataDiv = new DivType();
    metadataDiv.setID("metadataDiv");
    metadataDiv.setDivTypeLabel("Metadata");
    DivType descriptiveMetadataDiv = new DivType();
    descriptiveMetadataDiv.setID("descriptive");
    descriptiveMetadataDiv.setDivTypeLabel("Descriptive");
    metadataDiv.getDiv().add(descriptiveMetadataDiv);
    DivType administrativeMetadataDiv = new DivType();
    administrativeMetadataDiv.setID("administrative");
    administrativeMetadataDiv.setDivTypeLabel("Administrative");
    metadataDiv.getDiv().add(administrativeMetadataDiv);
    DivType otherMetadataDiv = new DivType();
    otherMetadataDiv.setID("other");
    otherMetadataDiv.setDivTypeLabel("Other");
    metadataDiv.getDiv().add(otherMetadataDiv);
    packageDivRep.getDiv().add(metadataDiv);
    DivType schemasDiv = new DivType();
    schemasDiv.setID("schemasDiv");
    schemasDiv.setDivTypeLabel("Schemas");
    packageDivRep.getDiv().add(schemasDiv);
    DivType documentationDiv = new DivType();
    documentationDiv.setID("documentationDiv");
    documentationDiv.setDivTypeLabel("Documentation");
    packageDivRep.getDiv().add(documentationDiv);
    structMapRep.setDiv(packageDivRep);
    sipMets.getStructMap().add(structMapRep);

    if (sip.getParentID() != null) {
      StructMapType structMapParent = new StructMapType();
      structMapParent.setStructMapTypeLabel("Parent");
      DivType divParent = new DivType();
      divParent.setLabel("Parent");
      divParent.setTYPE("AIP Parent Link");
      Mptr mptrParent = new Mptr();
      mptrParent.setLOCTYPE("HANDLE");
      mptrParent.setType("simple");
      mptrParent.setHref(sip.getParentID());
      mptrParent.setLOCTYPE(LocType.HANDLE.toString());
      divParent.getMptr().add(mptrParent);
      structMapParent.setDiv(divParent);
      sipMets.getStructMap().add(structMapParent);
    }
    return sipMets;
  }

  public static Mets getMetsFromRepresentation(String representationID, SIPRepresentation representation)
    throws SIPException {
    Mets representationMETS = new Mets();
    representationMETS.setOBJID(representation.getObjectID());
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

    MdSecType mdSec = new MdSecType();
    mdSec.setID(UUID.randomUUID().toString());
    representationMETS.getDmdSec().add(mdSec);

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
    contentDiv.setDivTypeLabel("Content");
    packageDivRep.getDiv().add(contentDiv);
    DivType metadataDiv = new DivType();
    metadataDiv.setID("metadataDiv");
    metadataDiv.setDivTypeLabel("Metadata");
    DivType descriptiveMetadataDiv = new DivType();
    descriptiveMetadataDiv.setID("descriptive");
    descriptiveMetadataDiv.setDivTypeLabel("Descriptive");
    metadataDiv.getDiv().add(descriptiveMetadataDiv);
    DivType administrativeMetadataDiv = new DivType();
    administrativeMetadataDiv.setID("administrative");
    administrativeMetadataDiv.setDivTypeLabel("Administrative");
    metadataDiv.getDiv().add(administrativeMetadataDiv);
    DivType otherMetadataDiv = new DivType();
    otherMetadataDiv.setID("other");
    otherMetadataDiv.setDivTypeLabel("Other");
    metadataDiv.getDiv().add(otherMetadataDiv);
    packageDivRep.getDiv().add(metadataDiv);
    DivType schemasDiv = new DivType();
    schemasDiv.setID("schemasDiv");
    schemasDiv.setDivTypeLabel("Schemas");
    packageDivRep.getDiv().add(schemasDiv);
    DivType documentationDiv = new DivType();
    documentationDiv.setID("documentationDiv");
    documentationDiv.setDivTypeLabel("Documentation");
    packageDivRep.getDiv().add(documentationDiv);
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
      throw new SIPException("Error probing content-type (" + dataFile.toString() + ")", e);
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
    locat.setLOCTYPE(METSEnums.LocType.URL.toString());
    locat.setHref("file://./" + dataFilePath);
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
    locat.setType("simple");
    locat.setLOCTYPE(METSEnums.LocType.URL.toString());
    locat.setHref("file://./" + preservationFilePath);
    ft.getFLocat().add(locat);
    representationMETS.getFileSec().getFileGrp().get(0).getFile().add(ft);

    Fptr fptr = new Fptr();
    fptr.setFILEID(ft);
    representationMETS.getStructMap().get(0).getDiv().getDiv().get(1).getDiv().get(2).getFptr().add(fptr);
    return representationMETS;
  }

  public static Mets addOtherMetadataToMets(Mets mainMETS, String otherMetadataPath, SIPMetadata om)
    throws SIPException {
    MdRef mdref = new MdRef();
    try {
      mdref.setCHECKSUM(Utils.calculateChecksum(Files.newInputStream(om.getMetadata()), "SHA-256"));
    } catch (NoSuchAlgorithmException e) {
      throw new SIPException("Error calculating checskum: the algorithm provided is not recognized", e);
    } catch (IOException e) {
      throw new SIPException("Error calculating checksum", e);
    }
    mdref.setCHECKSUMTYPE("SHA-256");
    try {
      mdref.setCREATED(Utils.getCurrentCalendar());
    } catch (DatatypeConfigurationException dce) {
      throw new SIPException("Error getting current calendar", dce);
    }
    mdref.setLOCTYPE(LocType.URL.toString());
    mdref.setMIMETYPE("text/xml");
    try {
      mdref.setSIZE(Files.size(om.getMetadata()));
    } catch (IOException e) {
      throw new SIPException("Error calculating file size", e);
    }
    mdref.setHref("file://./" + otherMetadataPath);
    mdref.setType("simple");

    MdSecType dmdsec = new MdSecType();
    dmdsec.setID("DMD" + mainMETS.getDmdSec().size());
    dmdsec.setMdRef(mdref);
    mainMETS.getDmdSec().add(dmdsec);
    return mainMETS;
  }

  public static String extractParentID(StructMapType smt) {
    String parentID = null;
    if (smt.getDiv() != null) {
      DivType dt = smt.getDiv();
      if (dt.getMptr() != null && dt.getMptr().size() > 0) {
        for (Mptr m : dt.getMptr()) {
          if (m.getHref() != null) {
            parentID = m.getHref();
            break;
          }
        }
      }
    }
    return parentID;
  }
}
