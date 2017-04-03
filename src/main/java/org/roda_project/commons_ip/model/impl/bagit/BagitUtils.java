/**
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE file at the root of the source
 * tree and available online at
 *
 * https://github.com/keeps/commons-ip
 */
package org.roda_project.commons_ip.model.impl.bagit;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.builder.FileBasedConfigurationBuilder;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.ex.ConfigurationException;
import org.jdom2.Element;
import org.jdom2.IllegalDataException;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import org.roda_project.commons_ip.model.IPConstants;
import org.roda_project.commons_ip.model.IPDescriptiveMetadata;
import org.roda_project.commons_ip.model.IPFile;
import org.roda_project.commons_ip.model.MetadataType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.xml.XmlEscapers;

public final class BagitUtils {
  private static final Logger LOGGER = LoggerFactory.getLogger(BagitUtils.class);
  private static final String BAGIT = "key-value";

  private BagitUtils() {
    // do nothing
  }

  public static IPDescriptiveMetadata createBagitMetadata(Map<String, String> metadata, Path metadataPath) {
    return createBagitMetadata(metadata, new ArrayList<>(), metadataPath);
  }

  public static IPDescriptiveMetadata createBagitMetadata(Map<String, String> metadata, List<String> ancestors,
    Path metadataPath) {
    try {
      FileBasedConfigurationBuilder<PropertiesConfiguration> builder = new Configurations()
        .propertiesBuilder(metadataPath.toFile());
      Files.createFile(metadataPath);
      PropertiesConfiguration config = builder.getConfiguration();

      for (Entry<String, String> entry : metadata.entrySet()) {
        config.setProperty(entry.getKey(), entry.getValue());
      }

      for (String ancestor : ancestors) {
        config.addProperty(IPConstants.BAGIT_PARENT, ancestor);
      }

      builder.save();
    } catch (IOException | ConfigurationException e) {
      LOGGER.error("Could not save bagit metadata content on file", e);
    }

    return new IPDescriptiveMetadata(metadataPath.getFileName().toString(), new IPFile(metadataPath),
      new MetadataType(BAGIT), "");
  }

  public static Map<String, String> getBagitInfo(Path metadataPath) {
    Map<String, String> metadataList = new HashMap<>();
    try {
      PropertiesConfiguration config = new Configurations().properties(metadataPath.toFile());
      Iterator<String> keys = config.getKeys();
      while (keys.hasNext()) {
        String key = keys.next();
        metadataList.put(key, config.getString(key));
      }
    } catch (ConfigurationException e) {
      LOGGER.error("Could not load properties with bagit metadata", e);
    }

    return metadataList;
  }

  public static String generateMetadataFile(Path metadataPath) throws IllegalDataException {
    Map<String, String> bagInfo = getBagitInfo(metadataPath);
    Element root = new Element(IPConstants.BAGIT_METADATA);
    org.jdom2.Document doc = new org.jdom2.Document();

    for (Map.Entry<String, String> entry : bagInfo.entrySet()) {
      if (!IPConstants.BAGIT_PARENT.equalsIgnoreCase(entry.getKey())) {
        Element child = new Element(IPConstants.BAGIT_FIELD);
        child.setAttribute(IPConstants.BAGIT_NAME, XmlEscapers.xmlAttributeEscaper().escape(entry.getKey()));
        child.addContent(XmlEscapers.xmlContentEscaper().escape(entry.getValue()));
        root.addContent(child);
      }
    }

    doc.setRootElement(root);
    XMLOutputter outter = new XMLOutputter();
    outter.setFormat(Format.getPrettyFormat());
    return outter.outputString(doc);
  }
}
