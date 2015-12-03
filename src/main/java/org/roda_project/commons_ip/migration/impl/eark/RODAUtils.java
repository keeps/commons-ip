package org.roda_project.commons_ip.migration.impl.eark;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class RODAUtils {
  private static final Logger LOGGER = LoggerFactory.getLogger(RODAUtils.class);

  private RODAUtils() {
  }

  public static void addDescriptiveMetadata(Path sipPath, Path relativePath, InputStream metadata) throws IOException {
    // TODO IF SUBFOLDER ALLOWED, REMOVE REPLACE...
    Path metadataPath = sipPath.resolve("metadata").resolve("descriptive")
      .resolve(relativePath.toString().replace('/', '_'));
    Files.createDirectories(metadataPath.getParent());
    Files.copy(metadata, metadataPath, StandardCopyOption.REPLACE_EXISTING);
  }

  public static void addPreservationMetadata(Path sipPath, Path relativePath, InputStream metadata) throws IOException {
    // TODO IF SUBFOLDER ALLOWED, REMOVE REPLACE...
    Path metadataPath = sipPath.resolve("metadata").resolve("preservation")
      .resolve(relativePath.toString().replace('/', '_'));
    Files.createDirectories(metadataPath.getParent());
    Files.copy(metadata, metadataPath, StandardCopyOption.REPLACE_EXISTING);
  }

  public static void addOtherMetadata(Path sipPath, Path relativePath, InputStream metadata) throws IOException {
    // TODO IF SUBFOLDER ALLOWED, REMOVE REPLACE...
    Path metadataPath = sipPath.resolve("metadata").resolve("other").resolve(relativePath.toString().replace('/', '_'));
    Files.createDirectories(metadataPath.getParent());
    Files.copy(metadata, metadataPath, StandardCopyOption.REPLACE_EXISTING);
  }

  public static void addDataToRepresentation(Path sipPath, String representationID, Path relativePath, InputStream data)
    throws IOException {
    // TODO IF SUBFOLDER ALLOWED, REMOVE REPLACE...
    Path datapath = sipPath.resolve("data").resolve(representationID)
      .resolve(relativePath.toString().replace('/', '_'));
    Files.createDirectories(datapath.getParent());
    Files.copy(data, datapath, StandardCopyOption.REPLACE_EXISTING);
  }

  public static void addDescriptiveMetadataToRepresentation(Path sipPath, String representationID, Path relativePath,
    InputStream metadata) throws IOException {
    // TODO IF SUBFOLDER ALLOWED, REMOVE REPLACE...
    Path metadataPath = sipPath.resolve("metadata").resolve("representations").resolve(representationID)
      .resolve("descriptive").resolve(relativePath.toString().replace('/', '_'));
    Files.createDirectories(metadataPath.getParent());
    Files.copy(metadata, metadataPath, StandardCopyOption.REPLACE_EXISTING);
  }

  public static void addPreservationMetadataToRepresentation(Path sipPath, String representationID, Path relativePath,
    InputStream metadata) throws IOException {
    // TODO IF SUBFOLDER ALLOWED, REMOVE REPLACE...
    Path metadataPath = sipPath.resolve("metadata").resolve("representations").resolve(representationID)
      .resolve("preservation").resolve(relativePath.toString().replace('/', '_'));
    Files.createDirectories(metadataPath.getParent());
    Files.copy(metadata, metadataPath, StandardCopyOption.REPLACE_EXISTING);
  }

  public static void addOtherMetadataToRepresentation(Path sipPath, String representationID, Path relativePath,
    InputStream metadata) throws IOException {
    // TODO IF SUBFOLDER ALLOWED, REMOVE REPLACE...
    Path metadataPath = sipPath.resolve("metadata").resolve("representations").resolve(representationID)
      .resolve("other").resolve(relativePath.toString().replace('/', '_'));
    Files.createDirectories(metadataPath.getParent());
    Files.copy(metadata, metadataPath, StandardCopyOption.REPLACE_EXISTING);
  }
}
