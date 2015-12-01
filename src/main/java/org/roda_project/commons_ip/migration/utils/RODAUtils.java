package org.roda_project.commons_ip.migration.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class RODAUtils {
  public static void addDescriptiveMetadata(Path base, Path metadata) throws IOException {
    Path metadataDirectoryPath = base.resolve("metadata/descriptive");
    if (!Files.exists(metadataDirectoryPath)) {
      Files.createDirectories(metadataDirectoryPath);
    }
    Path metadataPath = metadataDirectoryPath.resolve(metadata.getFileName());
    Files.copy(metadata, metadataPath, StandardCopyOption.REPLACE_EXISTING);
  }

  public static void addPreservationMetadata(Path base, Path metadata) throws IOException {
    Path metadataDirectoryPath = base.resolve("metadata/preservation");
    if (!Files.exists(metadataDirectoryPath)) {
      Files.createDirectories(metadataDirectoryPath);
    }
    Path metadataPath = metadataDirectoryPath.resolve(metadata.getFileName());
    Files.copy(metadata, metadataPath, StandardCopyOption.REPLACE_EXISTING);
  }

  public static void addOtherMetadata(Path base, Path metadata) throws IOException {
    Path metadataDirectoryPath = base.resolve("metadata/other");
    if (!Files.exists(metadataDirectoryPath)) {
      Files.createDirectories(metadataDirectoryPath);
    }
    Path metadataPath = metadataDirectoryPath.resolve(metadata.getFileName());
    Files.copy(metadata, metadataPath, StandardCopyOption.REPLACE_EXISTING);
  }

  public static void addRepresentationData(Path dst, Path path, String representationID) throws IOException {
    System.out.println("Add data " + path + " to representation " + representationID);
    Path representationDirectoryPath = dst.resolve("data/" + representationID);
    if (!Files.exists(representationDirectoryPath)) {
      Files.createDirectories(representationDirectoryPath);
    }
    Files.copy(path, representationDirectoryPath.resolve(path.getFileName()), StandardCopyOption.REPLACE_EXISTING);
  }

  public static void addRepresentationSchema(Path dst, Path path, String representationID) {
    System.out.println("Add schema " + path + " to representation " + representationID);

  }

  public static void addRepresentationMetadata(Path dst, Path path, String representationID) throws IOException {
    System.out.println("Add data " + path + " to representation " + representationID);
    Path representationDirectoryPath = dst.resolve("metadata/preservation/" + representationID);
    if (!Files.exists(representationDirectoryPath)) {
      Files.createDirectories(representationDirectoryPath);
    }
    Files.copy(path, representationDirectoryPath.resolve(path.getFileName()), StandardCopyOption.REPLACE_EXISTING);

  }
}
