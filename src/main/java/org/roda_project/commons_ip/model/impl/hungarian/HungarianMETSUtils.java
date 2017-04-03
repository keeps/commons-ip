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
import org.roda_project.commons_ip.mets_v1_11.beans.MetsType.MetsHdr.AltRecordID;
import org.roda_project.commons_ip.mets_v1_11.beans.StructMapType;
import org.roda_project.commons_ip.model.IPAgent;
import org.roda_project.commons_ip.model.IPAltRecordID;
import org.roda_project.commons_ip.model.IPConstants;
import org.roda_project.commons_ip.model.IPDescriptiveMetadata;
import org.roda_project.commons_ip.model.IPHeader;
import org.roda_project.commons_ip.model.MetadataType.MetadataTypeEnum;
import org.roda_project.commons_ip.model.MetsWrapper;
import org.roda_project.commons_ip.utils.IPEnums;
import org.roda_project.commons_ip.utils.IPException;
import org.roda_project.commons_ip.utils.METSUtils;
import org.roda_project.commons_ip.utils.Utils;
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
      header.getAgent().add(METSUtils.createMETSAgent(sipAgent));
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

  public static void addAggregationDescriptiveMetadataToMETS(MetsWrapper metsWrapper, IPDescriptiveMetadata metadata)
    throws IPException, InterruptedException {

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
    mdWrap.setID(metadata.getId());
    mdWrap.setMDTYPE(metadata.getMetadataType().getType().getType());
    mdWrap.setMDTYPEVERSION(metadata.getMetadataVersion());

    String mdOtherType = metadata.getMetadataType().getOtherType();
    if (StringUtils.isNotBlank(mdOtherType)) {
      mdWrap.setOTHERMDTYPE(mdOtherType);
    }

    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
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

    dmdSec.setMdWrap(mdWrap);
    metsWrapper.getMets().getDmdSec().add(dmdSec);
    // FIXME 20170330 nvieira this code line is needed but causes an error
    // marshalling mets
    // metsWrapper.getMainDiv().getDMDID().add(dmdSec.getID());
  }

  public static void addDocumentationDescriptiveMetadataToMETS(MetsWrapper metsWrapper, IPDescriptiveMetadata metadata)
    throws IPException, InterruptedException {
    // documentation dmdSec
    MdSecType dmdSec = new MdSecType();
    dmdSec.setID(Utils.generateRandomAndPrefixedUUID());
    dmdSec.setGROUPID(IPConstants.METS_GROUP_ID);
    dmdSec.setSTATUS(IPConstants.METS_STATUS_CURRENT);

    MdWrap mdWrap = new MdWrap();
    mdWrap.setID(Utils.generateRandomAndPrefixedUUID());
    mdWrap.setMDTYPE(MetadataTypeEnum.OTHER.getType());

    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    try (InputStream inputStream = Files.newInputStream(metadata.getMetadata().getPath())) {
      Document xmlDataContent = factory.newDocumentBuilder().parse(inputStream);
      XmlData metadataContent = new XmlData();
      metadataContent.getAny().add(xmlDataContent.getDocumentElement());
      mdWrap.setXmlData(metadataContent);
    } catch (SAXException | ParserConfigurationException | IOException e) {
      LOGGER.error("Error adding EAD xml to xmlData", e);
    }

    dmdSec.setMdWrap(mdWrap);
    metsWrapper.getMets().getDmdSec().add(dmdSec);
    // FIXME 20170330 nvieira this code line is needed but causes an error
    // marshalling mets
    // metsWrapper.getDocumentationDiv().getDMDID().add(dmdSec.getID());
  }

  public static FileType addDataFileToMETS(MetsWrapper mainMETS, String dataFilePath, Path dataFile)
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
    mainMETS.getMainDiv().getFptr().add(fptr);
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
