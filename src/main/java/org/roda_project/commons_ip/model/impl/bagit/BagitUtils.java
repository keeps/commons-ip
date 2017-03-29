/**
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE file at the root of the source
 * tree and available online at
 *
 * https://github.com/keeps/commons-ip
 */
package org.roda_project.commons_ip.model.impl.bagit;

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.roda_project.commons_ip.model.IPConstants;
import org.roda_project.commons_ip.model.IPDescriptiveMetadata;
import org.roda_project.commons_ip.model.IPFile;
import org.roda_project.commons_ip.model.MetadataType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
    PropertiesConfiguration config = new PropertiesConfiguration();
    for (Entry<String, String> entry : metadata.entrySet()) {
      config.setProperty(entry.getKey(), entry.getValue());
    }

    for (String ancestor : ancestors) {
      config.addProperty(IPConstants.BAGIT_PARENT, ancestor);
    }

    try {
      config.save(metadataPath.toFile());
    } catch (ConfigurationException e) {
      LOGGER.error("Could not save bagit metadata content on file");
    }

    IPFile metadataFile = new IPFile(metadataPath);
    return new IPDescriptiveMetadata(metadataPath.getFileName().toString(), metadataFile, new MetadataType(BAGIT), "");
  }

  public static Map<String, String> getBagitInfo(Path metadataPath) {
    Map<String, String> metadataList = new HashMap<>();
    File metadataFile = metadataPath.toFile();
    PropertiesConfiguration config = new PropertiesConfiguration();

    try {
      config.load(metadataFile);
      Iterator<String> keys = config.getKeys();
      while (keys.hasNext()) {
        String key = keys.next();
        metadataList.put(key, config.getString(key));
      }
    } catch (ConfigurationException e) {
      LOGGER.error("Could not load properties with bagit metadata");
    }

    return metadataList;
  }
}
