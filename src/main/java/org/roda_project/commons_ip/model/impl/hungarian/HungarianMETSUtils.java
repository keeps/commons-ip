/**
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE file at the root of the source
 * tree and available online at
 *
 * https://github.com/keeps/commons-ip
 */
package org.roda_project.commons_ip.model.impl.hungarian;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBException;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.lang3.StringUtils;
import org.roda_project.commons_ip.mets_v1_11.beans.DivType;
import org.roda_project.commons_ip.mets_v1_11.beans.DivType.Fptr;
import org.roda_project.commons_ip.mets_v1_11.beans.FileType;
import org.roda_project.commons_ip.mets_v1_11.beans.FileType.FLocat;
import org.roda_project.commons_ip.mets_v1_11.beans.MdSecType;
import org.roda_project.commons_ip.mets_v1_11.beans.MdSecType.MdWrap;
import org.roda_project.commons_ip.mets_v1_11.beans.MdSecType.MdWrap.XmlData;
import org.roda_project.commons_ip.mets_v1_11.beans.Mets;
import org.roda_project.commons_ip.mets_v1_11.beans.MetsType.FileSec;
import org.roda_project.commons_ip.mets_v1_11.beans.MetsType.FileSec.FileGrp;
import org.roda_project.commons_ip.mets_v1_11.beans.MetsType.MetsHdr;
import org.roda_project.commons_ip.mets_v1_11.beans.MetsType.MetsHdr.Agent;
import org.roda_project.commons_ip.mets_v1_11.beans.MetsType.MetsHdr.AltRecordID;
import org.roda_project.commons_ip.mets_v1_11.beans.StructMapType;
import org.roda_project.commons_ip.model.IPAgent;
import org.roda_project.commons_ip.model.IPAltRecordID;
import org.roda_project.commons_ip.model.IPConstants;
import org.roda_project.commons_ip.model.IPDescriptiveMetadata;
import org.roda_project.commons_ip.model.IPHeader;
import org.roda_project.commons_ip.model.MetsWrapper;
import org.roda_project.commons_ip.utils.IPEnums;
import org.roda_project.commons_ip.utils.IPException;
import org.roda_project.commons_ip.utils.METSEnums.LocType;
import org.roda_project.commons_ip.utils.Utils;
import org.roda_project.commons_ip.utils.ZIPUtils;
import org.roda_project.commons_ip.utils.ZipEntryInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public final class HungarianMETSUtils {
  private static final Logger LOGGER = LoggerFactory.getLogger(HungarianMETSUtils.class);

  private HungarianMETSUtils() {
    // do nothing
  }

  public static MetsWrapper generateMETS(String id, String label, String type, String profile, Path metsPath,
    IPHeader header) throws IPException {
    return generateMETS(id, label, type, profile, header.getAgents(), metsPath, header.getStatus(),
      header.getAltRecordIDs());
  }

  public static MetsWrapper generateMETS(String id, String label, String type, String profile, List<IPAgent> ipAgents,
    Path metsPath, IPEnums.IPStatus status, List<IPAltRecordID> recordList) throws IPException {
    // FIXME There are missing attributes on some elements

    Mets mets = new Mets();
    MetsWrapper metsWrapper = new MetsWrapper(mets, metsPath);

    // basic attributes
    mets.setOBJID(id);
    mets.setPROFILE(profile);
    mets.setTYPE(type);
    mets.setLABEL(label);

    // header
    MetsHdr header = new MetsHdr();
    try {
      XMLGregorianCalendar currentDate = Utils.getCurrentCalendar();
      header.setCREATEDATE(currentDate);
      header.setLASTMODDATE(currentDate);
      header.setRECORDSTATUS(status.toString());
    } catch (DatatypeConfigurationException e) {
      throw new IPException("Error getting current calendar", e);
    }

    // header/agent
    for (IPAgent sipAgent : ipAgents) {
      header.getAgent().add(createMETSAgent(sipAgent));
    }

    // records
    if (recordList != null) {
      for (IPAltRecordID iprecord : recordList) {
        AltRecordID recordId = new AltRecordID();
        recordId.setTYPE(iprecord.getType());
        recordId.setValue(iprecord.getValue());
        header.getAltRecordID().add(recordId);
      }
    }

    mets.setMetsHdr(header);

    // file section
    FileSec fileSec = new FileSec();
    FileGrp mainFileGroup = new FileGrp();
    mainFileGroup.setUSE(IPConstants.METS_ORIGINAL);
    mainFileGroup.setID(Utils.generateRandomAndPrefixedUUID());
    metsWrapper.setMainFileGroup(mainFileGroup);
    fileSec.getFileGrp().add(mainFileGroup);
    mets.setFileSec(fileSec);

    // Aggregation struct map
    StructMapType aggregationMap = new StructMapType();
    aggregationMap.setTYPE(IPConstants.METS_TYPE_AGGREGATION);

    DivType mainAggregationDiv = new DivType();
    mainAggregationDiv.setID(Utils.generateRandomAndPrefixedUUID());
    mainAggregationDiv.setTYPE(IPConstants.METS_TYPE_RECORDGRP);
    metsWrapper.setMainDiv(mainAggregationDiv);

    aggregationMap.setDiv(mainAggregationDiv);
    mets.getStructMap().add(aggregationMap);

    // Aggregation struct map
    StructMapType docMap = new StructMapType();
    docMap.setTYPE(IPConstants.METS_TYPE_DOCUMENTATION);

    DivType mainDocDiv = new DivType();
    mainDocDiv.setID(Utils.generateRandomAndPrefixedUUID());
    mainDocDiv.setLABEL(IPConstants.METS_LABEL_DOKU);
    metsWrapper.setDocumentationDiv(mainDocDiv);

    docMap.setDiv(mainDocDiv);
    mets.getStructMap().add(docMap);

    return metsWrapper;
  }

  public static void addMainMETSToZip(Map<String, ZipEntryInfo> zipEntries, MetsWrapper metsWrapper, String metsPath,
    Path buildDir) throws IPException {
    try {
      addMETSToZip(zipEntries, metsWrapper, metsPath, buildDir, true);
    } catch (JAXBException | IOException e) {
      throw new IPException(e.getMessage(), e);
    }
  }

  private static void addMETSToZip(Map<String, ZipEntryInfo> zipEntries, MetsWrapper metsWrapper, String metsPath,
    Path buildDir, boolean mainMets) throws JAXBException, IOException, IPException {
    Path temp = Files.createTempFile(buildDir, IPConstants.METS_FILE_NAME, IPConstants.METS_FILE_EXTENSION);
    ZIPUtils.addMETSFileToZip(zipEntries, temp, metsPath, metsWrapper.getMets(), mainMets);
  }

  private static Agent createMETSAgent(IPAgent ipAgent) {
    Agent agent = new Agent();
    agent.setName(ipAgent.getName());
    agent.setROLE(ipAgent.getRole());
    agent.setOTHERROLE(ipAgent.getOtherRole());
    agent.setTYPE(ipAgent.getType().toString());
    agent.setOTHERTYPE(ipAgent.getOtherType());
    return agent;
  }

  public static void addDescriptiveMetadataToMETS(MetsWrapper metsWrapper, IPDescriptiveMetadata metadata)
    throws IPException, InterruptedException {
    // FIXME There are missing attributes on some elements

    MdSecType dmdSec = new MdSecType();
    dmdSec.setID(Utils.generateRandomAndPrefixedUUID());

    try {
      dmdSec.setCREATED(Utils.getCurrentCalendar());
    } catch (DatatypeConfigurationException e) {
      throw new IPException("Error getting current calendar", e);
    }

    MdWrap mdWrap = new MdWrap();
    mdWrap.setID(metadata.getId());
    mdWrap.setMDTYPE(metadata.getMetadataType().getType().getType());
    mdWrap.setMDTYPEVERSION(metadata.getMetadataVersion());

    String mdOtherType = metadata.getMetadataType().getOtherType();
    if (StringUtils.isNotBlank(mdOtherType)) {
      mdWrap.setOTHERMDTYPE(mdOtherType);
    }

    try {
      DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
      InputStream inputStream = Files.newInputStream(metadata.getMetadata().getPath());
      Document xmlDataContent = factory.newDocumentBuilder().parse(inputStream);

      XmlData metadataContent = new XmlData();
      metadataContent.getAny().add(xmlDataContent.getDocumentElement());
      mdWrap.setXmlData(metadataContent);
    } catch (SAXException | ParserConfigurationException | IOException e) {
      LOGGER.error("Error adding EAD xml to xmlData");
    }

    dmdSec.setMdWrap(mdWrap);
    metsWrapper.getMets().getDmdSec().add(dmdSec);
  }

  public static FileType addDataFileToMETS(MetsWrapper mainMETS, String dataFilePath, Path dataFile)
    throws IPException, InterruptedException {
    FileType file = new FileType();
    file.setID(Utils.generateRandomAndPrefixedUUID());

    // set mimetype, date creation, etc.
    setFileBasicInformation(dataFile, file);

    // add to file section
    FLocat fileLocation = createFileLocation(dataFilePath);
    file.getFLocat().add(fileLocation);
    mainMETS.getMainFileGroup().getFile().add(file);

    // add to struct map
    Fptr fptr = new Fptr();
    fptr.setFILEID(file);
    mainMETS.getMainDiv().getFptr().add(fptr);
    return file;
  }

  private static void setFileBasicInformation(Path file, FileType fileType) throws IPException, InterruptedException {
    // mimetype info.
    try {
      LOGGER.debug("Setting mimetype {}", file);
      fileType.setMIMETYPE(Files.probeContentType(file));
      LOGGER.debug("Done setting mimetype");
    } catch (IOException e) {
      throw new IPException("Error probing content-type (" + file.toString() + ")", e);
    }

    // date creation info.
    try {
      fileType.setCREATED(Utils.getCurrentCalendar());
    } catch (DatatypeConfigurationException e) {
      throw new IPException("Error getting curent calendar (" + file.toString() + ")", e);
    }

    // size info.
    try {
      LOGGER.debug("Setting file size {}", file);
      fileType.setSIZE(Files.size(file));
      LOGGER.debug("Done setting file size");
    } catch (IOException e) {
      throw new IPException("Error getting file size (" + file.toString() + ")", e);
    }
  }

  public static FileType addDocumentationFileToMETS(MetsWrapper metsWrapper, String documentationFilePath,
    Path documentationFile) throws IPException, InterruptedException {
    FileType file = new FileType();
    file.setID(Utils.generateRandomAndPrefixedUUID());

    // set mimetype, date creation, etc.
    setFileBasicInformation(documentationFile, file);

    // add to file section
    FLocat fileLocation = createFileLocation(documentationFilePath);
    file.getFLocat().add(fileLocation);
    metsWrapper.getMainFileGroup().getFile().add(file);

    // add to struct map
    Fptr fptr = new Fptr();
    fptr.setFILEID(file);
    metsWrapper.getDocumentationDiv().getFptr().add(fptr);

    return file;
  }

  private static FLocat createFileLocation(String filePath) {
    FLocat fileLocation = new FLocat();
    fileLocation.setType(IPConstants.METS_TYPE_SIMPLE);
    fileLocation.setLOCTYPE(LocType.URL.toString());
    fileLocation.setHref(Utils.encode(filePath));
    return fileLocation;
  }

}
