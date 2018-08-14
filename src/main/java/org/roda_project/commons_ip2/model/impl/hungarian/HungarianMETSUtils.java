/**
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE file at the root of the source
 * tree and available online at
 *
 * https://github.com/keeps/commons-ip
 */
package org.roda_project.commons_ip2.model.impl.hungarian;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.apache.commons.lang3.StringUtils;
import org.roda_project.commons_ip2.mets_v1_11.beans.DivType;
import org.roda_project.commons_ip2.mets_v1_11.beans.FileType;
import org.roda_project.commons_ip2.mets_v1_11.beans.MdSecType;
import org.roda_project.commons_ip2.mets_v1_11.beans.Mets;
import org.roda_project.commons_ip2.mets_v1_11.beans.StructMapType;
import org.roda_project.commons_ip2.mets_v1_11.beans.DivType.Fptr;
import org.roda_project.commons_ip2.mets_v1_11.beans.FileType.FLocat;
import org.roda_project.commons_ip2.mets_v1_11.beans.MdSecType.MdWrap;
import org.roda_project.commons_ip2.mets_v1_11.beans.MdSecType.MdWrap.XmlData;
import org.roda_project.commons_ip2.mets_v1_11.beans.OriginalMetsType.FileSec;
import org.roda_project.commons_ip2.mets_v1_11.beans.OriginalMetsType.MetsHdr;
import org.roda_project.commons_ip2.mets_v1_11.beans.OriginalMetsType.FileSec.FileGrp;
import org.roda_project.commons_ip2.mets_v1_11.beans.OriginalMetsType.MetsHdr.AltRecordID;
import org.roda_project.commons_ip2.model.IPAgent;
import org.roda_project.commons_ip2.model.IPAltRecordID;
import org.roda_project.commons_ip2.model.IPConstants;
import org.roda_project.commons_ip2.model.IPDescriptiveMetadata;
import org.roda_project.commons_ip2.model.IPHeader;
import org.roda_project.commons_ip2.model.MetsWrapper;
import org.roda_project.commons_ip2.model.MetadataType.MetadataTypeEnum;
import org.roda_project.commons_ip2.utils.IPException;
import org.roda_project.commons_ip2.utils.METSUtils;
import org.roda_project.commons_ip2.utils.Utils;
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
    IPHeader ipHeader) throws IPException {
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
      header.setRECORDSTATUS(ipHeader.getStatus().toString());
    } catch (DatatypeConfigurationException e) {
      throw new IPException("Error getting current calendar", e);
    }

    // header/agent
    for (IPAgent sipAgent : ipHeader.getAgents()) {
      header.getAgent().add(METSUtils.createMETSAgent(sipAgent));
    }

    // records
    for (IPAltRecordID iprecord : ipHeader.getAltRecordIDs()) {
      AltRecordID recordId = new AltRecordID();
      recordId.setTYPE(iprecord.getType());
      recordId.setValue(iprecord.getValue());
      header.getAltRecordID().add(recordId);
    }

    mets.setMetsHdr(header);

    // file section
    FileSec fileSec = new FileSec();
    FileGrp mainFileGroup = new FileGrp();
    mainFileGroup.setUSE(IPConstants.METS_ORIGINAL);
    mainFileGroup.setID(IPConstants.METS_ORIGINAL);
    metsWrapper.setMainFileGroup(mainFileGroup);
    fileSec.getFileGrp().add(mainFileGroup);
    mets.setFileSec(fileSec);

    // Aggregation struct map
    StructMapType aggregationMap = new StructMapType();
    aggregationMap.setTYPE(IPConstants.METS_TYPE_AGGREGATION);

    DivType mainAggregationDiv = new DivType();
    mainAggregationDiv.setID(Utils.generateRandomAndPrefixedUUID());
    metsWrapper.setMainDiv(mainAggregationDiv);

    aggregationMap.setDiv(mainAggregationDiv);
    mets.getStructMap().add(aggregationMap);

    // Aggregation struct map
    StructMapType docMap = new StructMapType();
    docMap.setTYPE(IPConstants.METS_TYPE_DOCUMENTATION);

    DivType mainDocDiv = new DivType();
    mainDocDiv.setID(Utils.generateRandomAndPrefixedUUID());
    mainDocDiv.setLABEL(IPConstants.METS_LABEL_DOKU);
    mainDocDiv.setTYPE(IPConstants.METS_TYPE_DOCUMENTATION);
    metsWrapper.setDocumentationDiv(mainDocDiv);

    docMap.setDiv(mainDocDiv);
    mets.getStructMap().add(docMap);

    return metsWrapper;
  }

  public static void addDescriptiveMetadataSections(MetsWrapper metsWrapper) throws IPException {
    MdSecType dmdSec = new MdSecType();
    dmdSec.setID(Utils.generateRandomAndPrefixedUUID());
    dmdSec.setGROUPID(IPConstants.METS_GROUP_ID);
    dmdSec.setSTATUS(IPConstants.METS_STATUS_CURRENT);

    try {
      dmdSec.setCREATED(Utils.getCurrentCalendar());
    } catch (DatatypeConfigurationException e) {
      throw new IPException("Error getting current calendar", e);
    }

    MdWrap mdWrap = new MdWrap();
    dmdSec.setMdWrap(mdWrap);
    metsWrapper.getMets().getDmdSec().add(dmdSec);
    metsWrapper.getMainDiv().getDMDID().add(dmdSec);
    metsWrapper.setMainDmdSec(dmdSec);

    MdSecType dmdDocSec = new MdSecType();
    dmdDocSec.setID(Utils.generateRandomAndPrefixedUUID());
    dmdDocSec.setGROUPID(IPConstants.METS_GROUP_ID);
    dmdDocSec.setSTATUS(IPConstants.METS_STATUS_CURRENT);

    try {
      dmdDocSec.setCREATED(Utils.getCurrentCalendar());
    } catch (DatatypeConfigurationException e) {
      throw new IPException("Error getting current calendar", e);
    }

    MdWrap mdDocWrap = new MdWrap();
    mdDocWrap.setID(Utils.generateRandomAndPrefixedUUID());
    mdDocWrap.setMDTYPE(MetadataTypeEnum.OTHER.getType());

    dmdDocSec.setMdWrap(mdDocWrap);
    metsWrapper.getMets().getDmdSec().add(dmdDocSec);
    metsWrapper.getDocumentationDiv().getDMDID().add(dmdDocSec);
    metsWrapper.setDocumentationDmdSec(dmdDocSec);
  }

  public static void addAggregationDescriptiveMetadataToMETS(MetsWrapper metsWrapper, IPDescriptiveMetadata metadata)
    throws IPException, InterruptedException {
    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

    MdSecType dmdSec = metsWrapper.getMainDmdSec();
    MdWrap mdWrap = dmdSec.getMdWrap();

    mdWrap.setID(Utils.generateRandomAndPrefixedUUID());
    mdWrap.setMDTYPE(metadata.getMetadataType().getType().getType());
    mdWrap.setMDTYPEVERSION(metadata.getMetadataVersion());

    String mdOtherType = metadata.getMetadataType().getOtherType();
    if (StringUtils.isNotBlank(mdOtherType)) {
      mdWrap.setOTHERMDTYPE(mdOtherType);
    }

    try (InputStream inputStream = Files.newInputStream(metadata.getMetadata().getPath())) {
      Document xmlDataContent = factory.newDocumentBuilder().parse(inputStream);

      try {
        XPathFactory xPathfactory = XPathFactory.newInstance();
        XPath xpath = xPathfactory.newXPath();
        XPathExpression levelExpression = xpath.compile("//archdesc/@level");
        String level = (String) levelExpression.evaluate(xmlDataContent, XPathConstants.STRING);
        if (IPConstants.METS_LEVEL_OTHER.equalsIgnoreCase(level)) {
          XPathExpression otherExpression = xpath.compile("//archdesc/@otherlevel");
          level = (String) otherExpression.evaluate(xmlDataContent, XPathConstants.STRING);
        }
        metsWrapper.getMainDiv().setTYPE(level);
      } catch (XPathExpressionException e) {
        LOGGER.error("Error getting archdesc level or otherlevel", e);
      }

      XmlData metadataContent = new XmlData();
      metadataContent.getAny().add(xmlDataContent.getDocumentElement());
      mdWrap.setXmlData(metadataContent);
    } catch (SAXException | ParserConfigurationException | IOException e) {
      LOGGER.error("Error adding EAD xml to xmlData", e);
    }
  }

  public static void addDocumentationDescriptiveMetadataToMETS(MetsWrapper metsWrapper, IPDescriptiveMetadata metadata)
    throws IPException, InterruptedException {
    // documentation dmdSec
    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    try (InputStream inputStream = Files.newInputStream(metadata.getMetadata().getPath())) {
      Document xmlDataContent = factory.newDocumentBuilder().parse(inputStream);
      XmlData metadataContent = new XmlData();
      metadataContent.getAny().add(xmlDataContent.getDocumentElement());
      MdSecType dmdSec = metsWrapper.getDocumentationDmdSec();
      dmdSec.getMdWrap().setXmlData(metadataContent);
    } catch (SAXException | ParserConfigurationException | IOException e) {
      LOGGER.error("Error adding EAD xml to xmlData", e);
    }
  }

  public static FileType addDataFileToMETS(MetsWrapper mainMETS, String dataFilePath, Path dataFile, DivType folderDiv)
    throws IPException, InterruptedException {
    FileType file = new FileType();
    file.setID(Utils.generateRandomAndPrefixedUUID());

    // set mimetype, date creation, etc.
    METSUtils.setFileBasicInformation(LOGGER, dataFile, file);

    // add to file section
    FLocat fileLocation = METSUtils.createFileLocation(dataFilePath);
    file.getFLocat().add(fileLocation);
    mainMETS.getMainFileGroup().getFile().add(file);

    // add to struct map
    Fptr fptr = new Fptr();
    fptr.setFILEID(file);
    folderDiv.getFptr().add(fptr);
    return file;
  }

  public static FileType addDocumentationFileToMETS(MetsWrapper metsWrapper, String documentationFilePath,
    Path documentationFile) throws IPException, InterruptedException {
    FileType file = new FileType();
    file.setID(Utils.generateRandomAndPrefixedUUID());

    // set mimetype, date creation, etc.
    METSUtils.setFileBasicInformation(LOGGER, documentationFile, file);

    // add to file section
    FLocat fileLocation = METSUtils.createFileLocation(documentationFilePath);
    file.getFLocat().add(fileLocation);
    metsWrapper.getMainFileGroup().getFile().add(file);

    // add to struct map
    Fptr fptr = new Fptr();
    fptr.setFILEID(file);
    metsWrapper.getDocumentationDiv().getFptr().add(fptr);

    return file;
  }

}
