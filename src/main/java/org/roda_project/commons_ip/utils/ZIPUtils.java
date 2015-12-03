/**
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE file at the root of the source
 * tree and available online at
 *
 * https://github.com/keeps/commons-ip
 */
package org.roda_project.commons_ip.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

import org.roda_project.commons_ip.model.SIPMetadata;

public final class ZIPUtils {

  private ZIPUtils() {
  }

  public static void addMetadataToZip(Path zipPath, SIPMetadata dm, String metadataPath) throws SIPException {
    try {

      if (dm.getSchema() != null) {
        Path source = dm.getMetadata();
        String schemaPath = "/schemas/" + dm.getSchema().getFileName().toString();
        Utils.addFileToZip(zipPath, dm.getSchema(), schemaPath);
        Path temp = Files.createTempFile("temp", ".xml");
        Files.copy(dm.getMetadata(), temp, StandardCopyOption.REPLACE_EXISTING);
        // Utils.addSchemaLocationToPath(temp, ".." + schemaPath);
        Files.copy(temp, source, StandardCopyOption.REPLACE_EXISTING);
        dm.setMetadata(source);
      }
      Utils.addFileToZip(zipPath, dm.getMetadata(), metadataPath);
    } catch (IOException e) {
      throw new SIPException("Error adding metadata to SIP", e);
    }

  }

  public static void createRepresentationFolder(Path zipPath, String representationID) throws SIPException {
    try {
      Path temp = Files.createTempDirectory("rep");
      String representationPath = "/representations/" + representationID;
      Utils.addFileToZip(zipPath, temp, representationPath);
    } catch (IOException e) {
      throw new SIPException("Error creating representation folder in zip", e);
    }

  }

  public static void addDataToRepresentation(Path zipPath, Path dataFile, String dataFilePath) throws SIPException {
    try {
      Utils.addFileToZip(zipPath, dataFile, dataFilePath);
    } catch (IOException e) {
      throw new SIPException("Error adding file to zip (" + dataFile.toString() + ")", e);
    }

  }

}
