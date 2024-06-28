/**
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE file at the root of the source
 * tree and available online at
 *
 * https://github.com/keeps/commons-ip
 */
package org.roda_project.commons_ip.model.impl.bagit;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.DirectoryStream;
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
import org.apache.commons.io.IOUtils;
import org.apache.commons.text.StringEscapeUtils;
import org.jdom2.Element;
import org.jdom2.IllegalDataException;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import org.roda_project.commons_ip.model.IPConstants;
import org.roda_project.commons_ip.model.IPDescriptiveMetadata;
import org.roda_project.commons_ip.model.IPFile;
import org.roda_project.commons_ip.model.IPInterface;
import org.roda_project.commons_ip.model.IPRepresentation;
import org.roda_project.commons_ip.model.MetadataType;
import org.roda_project.commons_ip.model.ParseException;
import org.roda_project.commons_ip.model.SIP;
import org.roda_project.commons_ip.model.impl.ModelUtils;
import org.roda_project.commons_ip.utils.FileZipEntryInfo;
import org.roda_project.commons_ip.utils.ZIPUtils;
import org.roda_project.commons_ip.utils.ZipEntryInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class BagitUtils {
  private static final Logger LOGGER = LoggerFactory.getLogger(BagitUtils.class);
  private static final String BAGIT = "key-value";
  static final String BAGIT_FILE_NAME = "bagit";
  static final String BAGIT_INFO_FILE_NAME = "bag-info";
  static final String BAGIT_MANIFEST_FILE_NAME = "manifest-";
  static final String BAGIT_TAG_MANIFEST_FILE_NAME = "tagmanifest-";
  private static final String BAGIT_FILE_EXTENSION = ".txt";

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
        child.setAttribute(IPConstants.BAGIT_NAME, StringEscapeUtils.escapeXml11(entry.getKey()));
        child.addContent(entry.getValue());
        root.addContent(child);
      }
    }

    doc.setRootElement(root);
    XMLOutputter outter = new XMLOutputter();
    outter.setFormat(Format.getPrettyFormat());
    return outter.outputString(doc);
  }

  static void addRepresentationToZipAndBagit(IPInterface ip, List<IPRepresentation> representations,
                                             Map<String, ZipEntryInfo> zipEntries, Path buildDir) {
    if (representations != null && !representations.isEmpty()) {
      if (ip instanceof SIP) {
        ((SIP) ip).notifySipBuildRepresentationsProcessingStarted(representations.size());
      }
      for (IPRepresentation representation : representations) {
        if (representation.getData() != null && !representation.getData().isEmpty()) {
          if (ip instanceof SIP) {
            ((SIP) ip).notifySipBuildRepresentationProcessingStarted(representations.size());
          }
          String representationID = representation.getRepresentationID();
          Path representationPath = buildDir.resolve(representationID);

          for (IPFile file : representation.getData()) {
            String relativeFilePath = ModelUtils.getFoldersFromList(file.getRelativeFolders()) + file.getFileName();
            Path destination = representationPath.resolve(relativeFilePath);
            try {
              Files.createDirectories(destination.getParent());
              try (InputStream input = Files.newInputStream(file.getPath());
                OutputStream output = Files.newOutputStream(destination)) {
                IOUtils.copyLarge(input, output);
                String dataFilePath = IPConstants.DATA_FOLDER
                  + buildDir.relativize(representationPath).resolve(file.getFileName());
                zipEntries.put(dataFilePath, new FileZipEntryInfo(dataFilePath, file.getPath()));
              }
            } catch (IOException e) {
              LOGGER.error("Error creating file {} on bagit data folder", file.getFileName(), e);
            }
          }

          if (ip instanceof SIP) {
            ((SIP) ip).notifySipBuildRepresentationProcessingEnded();
          }
        }
      }

      if (ip instanceof SIP) {
        ((SIP) ip).notifySipBuildRepresentationsProcessingEnded();
      }
    }
  }

  static void addBagFileToZip(Map<String, ZipEntryInfo> zipEntries, Path buildDir, String target) {
    Path targetFile = buildDir.resolve(target + BAGIT_FILE_EXTENSION);
    if (Files.exists(targetFile)) {
      zipEntries.put(targetFile.toString(), new FileZipEntryInfo(targetFile.getFileName().toString(), targetFile));
    } else {
      LOGGER.error("Unable to find file to add to zip entry", targetFile);
    }
  }

  static Path extractBagitIPIfInZipFormat(final Path source, Path destinationDirectory)
    throws ParseException {
    Path bagitFolderPath = destinationDirectory;
    if (!Files.isDirectory(source)) {
      try {
        ZIPUtils.unzip(source, destinationDirectory);

        if (Files.exists(destinationDirectory)
          && !Files.exists(destinationDirectory.resolve(BAGIT_FILE_NAME + BAGIT_FILE_EXTENSION))) {
          try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(destinationDirectory)) {
            for (Path path : directoryStream) {
              if (Files.isDirectory(path) && Files.exists(path.resolve(BAGIT_FILE_NAME + BAGIT_FILE_EXTENSION))) {
                bagitFolderPath = path;
                break;
              }
            }
          }
        }
      } catch (IOException e) {
        throw new ParseException("Error unzipping file", e);
      }
    }

    return bagitFolderPath;
  }
}
