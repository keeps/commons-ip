/**
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE file at the root of the source
 * tree and available online at
 *
 * https://github.com/keeps/commons-ip
 */
package org.roda_project.commons_ip.model.impl.hungarian;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.roda_project.commons_ip.mets_v1_11.beans.DivType;
import org.roda_project.commons_ip.mets_v1_11.beans.FileType;
import org.roda_project.commons_ip.mets_v1_11.beans.MdSecType;
import org.roda_project.commons_ip.mets_v1_11.beans.MdSecType.MdWrap;
import org.roda_project.commons_ip.mets_v1_11.beans.MdSecType.MdWrap.XmlData;
import org.roda_project.commons_ip.model.IPConstants;
import org.roda_project.commons_ip.model.IPDescriptiveMetadata;
import org.roda_project.commons_ip.model.IPFile;
import org.roda_project.commons_ip.model.IPInterface;
import org.roda_project.commons_ip.model.IPRepresentation;
import org.roda_project.commons_ip.model.MetsWrapper;
import org.roda_project.commons_ip.model.SIP;
import org.roda_project.commons_ip.model.impl.ModelUtils;
import org.roda_project.commons_ip.utils.IPException;
import org.roda_project.commons_ip.utils.METSUtils;
import org.roda_project.commons_ip.utils.Utils;
import org.roda_project.commons_ip.utils.ZIPUtils;
import org.roda_project.commons_ip.utils.ZipEntryInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public final class HungarianUtils {
  private static final Logger LOGGER = LoggerFactory.getLogger(HungarianUtils.class);

  private HungarianUtils() {
    // do nothing
  }

  static void addMetadataToMETS(MetsWrapper metsWrapper, List<IPDescriptiveMetadata> descriptiveMetadata)
    throws IPException, InterruptedException {
    if (descriptiveMetadata != null && !descriptiveMetadata.isEmpty()) {
      HungarianMETSUtils.addDescriptiveMetadataSections(metsWrapper);

      for (IPDescriptiveMetadata dm : descriptiveMetadata) {
        if (Thread.interrupted()) {
          throw new InterruptedException();
        }

        if (dm.getMetadata().getRelatedTags().contains(IPConstants.HUNGARIAN_DOCUMENTATION_TAG)) {
          HungarianMETSUtils.addDocumentationDescriptiveMetadataToMETS(metsWrapper, dm);
        } else {
          HungarianMETSUtils.addAggregationDescriptiveMetadataToMETS(metsWrapper, dm);
        }
      }
    }
  }

  static void addRepresentationsToZipAndMETS(IPInterface ip, List<IPRepresentation> representations,
                                             Map<String, ZipEntryInfo> zipEntries, MetsWrapper mainMETSWrapper, String folderTemplate)
    throws IPException, InterruptedException {
    // representations
    if (representations != null && !representations.isEmpty()) {
      if (ip instanceof SIP) {
        ((SIP) ip).notifySipBuildRepresentationsProcessingStarted(representations.size());
      }

      for (IPRepresentation representation : representations) {
        if (Thread.interrupted()) {
          throw new InterruptedException();
        }

        // representation data
        addRepresentationDataFilesToZipAndMETS(ip, zipEntries, mainMETSWrapper, representation, folderTemplate);
      }

      if (ip instanceof SIP) {
        ((SIP) ip).notifySipBuildRepresentationsProcessingEnded();
      }
    }
  }

  private static void addRepresentationDataFilesToZipAndMETS(IPInterface ip, Map<String, ZipEntryInfo> zipEntries,
                                                             MetsWrapper mainMETSWrapper, IPRepresentation representation, String folderTemplate)
    throws IPException, InterruptedException {
    if (representation.getData() != null && !representation.getData().isEmpty()) {
      if (ip instanceof SIP) {
        ((SIP) ip).notifySipBuildRepresentationProcessingStarted(representation.getData().size());
      }

      int i = 0;
      Map<String, DivType> divs = new HashMap<>();
      divs.put("", mainMETSWrapper.getMainDiv());

      for (IPFile file : representation.getData()) {
        if (Thread.interrupted()) {
          throw new InterruptedException();
        }

        String dataFilePath = IPConstants.DATA_FOLDER + representation.getRepresentationID() + "/"
          + ModelUtils.getFoldersFromList(file.getRelativeFolders()) + file.getFileName();

        divs = addFolderStructure(divs, mainMETSWrapper, file, folderTemplate);

        FileType fileType = HungarianMETSUtils.addDataFileToMETS(mainMETSWrapper, dataFilePath, file.getPath(),
          divs.get(ModelUtils.getFoldersFromList(file.getRelativeFolders())));

        dataFilePath = IPConstants.CONTENT_FOLDER + IPConstants.ZIP_PATH_SEPARATOR + dataFilePath;
        ZIPUtils.addFileTypeFileToZip(zipEntries, file.getPath(), dataFilePath, fileType);

        i++;
        if (ip instanceof SIP) {
          ((SIP) ip).notifySipBuildRepresentationProcessingCurrentStatus(i);
        }
      }

      if (ip instanceof SIP) {
        ((SIP) ip).notifySipBuildRepresentationProcessingEnded();
      }
    }
  }

  private static Map<String, DivType> addFolderStructure(Map<String, DivType> divs, MetsWrapper mainMETSWrapper,
    IPFile file, String folderTemplate) {
    StringBuilder actualFolder = new StringBuilder();
    for (String folder : file.getRelativeFolders()) {
      String parentFolder = actualFolder.toString();

      actualFolder.append(folder);
      if (actualFolder.length() > 0) {
        actualFolder.append(IPConstants.ZIP_PATH_SEPARATOR);
      }

      String newFolder = actualFolder.toString();
      if (!divs.containsKey(newFolder)) {
        DivType newDiv = new DivType();
        newDiv.setID(Utils.generateRandomAndPrefixedUUID());
        newDiv.setTYPE(IPConstants.METS_TYPE_RECORDGRP);

        if (folderTemplate != null) {
          String dmdUUID = Utils.generateRandomAndPrefixedUUID();

          MdSecType folderDmdSec = new MdSecType();
          folderDmdSec.setID(dmdUUID);
          folderDmdSec.setGROUPID(IPConstants.METS_GROUP_ID);
          folderDmdSec.setSTATUS(IPConstants.METS_STATUS_CURRENT);

          try {
            folderDmdSec.setCREATED(Utils.getCurrentCalendar());
          } catch (DatatypeConfigurationException e) {
            LOGGER.error("Error getting current calendar", e);
          }

          MdWrap wrap = new MdWrap();
          wrap.setID(Utils.generateRandomAndPrefixedUUID());
          wrap.setMDTYPE(IPConstants.METS_EAD_TYPE);
          wrap.setMDTYPEVERSION(IPConstants.METS_EAD_VERSION);

          DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

          String content = folderTemplate;
          content = content.replaceAll("\\{\\{" + IPConstants.FOLDER_TEMPLATE_ID_FIELD + "\\}\\}",
            folderDmdSec.getID());
          content = content.replaceAll("\\{\\{" + IPConstants.FOLDER_TEMPLATE_FOLDER_FIELD + "\\}\\}", folder);

          try (InputStream stream = new ByteArrayInputStream(content.getBytes(StandardCharsets.UTF_8))) {
            Document xmlDataContent = factory.newDocumentBuilder().parse(stream);
            XmlData metadataContent = new XmlData();
            metadataContent.getAny().add(xmlDataContent.getDocumentElement());
            wrap.setXmlData(metadataContent);
          } catch (IOException | SAXException | ParserConfigurationException e) {
            LOGGER.warn("Could not create folder template metadata section", e);
          }

          folderDmdSec.setMdWrap(wrap);
          mainMETSWrapper.getMets().getDmdSec().add(folderDmdSec);

          newDiv.getDMDID().add(folderDmdSec);
        }

        divs.get(parentFolder).getDiv().add(newDiv);
        divs.put(newFolder, newDiv);
      }
    }

    return divs;
  }

  static void addDocumentationToZipAndMETS(Map<String, ZipEntryInfo> zipEntries, MetsWrapper metsWrapper,
                                           List<IPFile> documentation) throws IPException, InterruptedException {
    if (documentation != null && !documentation.isEmpty()) {
      for (IPFile doc : documentation) {
        if (Thread.interrupted()) {
          throw new InterruptedException();
        }

        String docPath = IPConstants.DOCUMENTATION_FOLDER + ModelUtils.getFoldersFromList(doc.getRelativeFolders())
          + doc.getFileName();
        FileType fileType = HungarianMETSUtils.addDocumentationFileToMETS(metsWrapper, docPath, doc.getPath());

        String documentationFilePath = IPConstants.CONTENT_FOLDER + IPConstants.ZIP_PATH_SEPARATOR
          + IPConstants.DOCUMENTATION_FOLDER + ModelUtils.getFoldersFromList(doc.getRelativeFolders())
          + doc.getFileName();
        ZIPUtils.addFileTypeFileToZip(zipEntries, doc.getPath(), documentationFilePath, fileType);
      }
    }
  }

  static void addMainMETSToZip(Map<String, ZipEntryInfo> zipEntries, MetsWrapper mainMETSWrapper,
                               Path buildDir) throws IPException {
    METSUtils.addMainMETSToZip(zipEntries, mainMETSWrapper, IPConstants.HUNGARIAN_METADATA_FILE, buildDir);
  }

}
