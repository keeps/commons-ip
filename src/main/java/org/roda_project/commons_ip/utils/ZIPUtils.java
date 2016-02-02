/**
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE file at the root of the source
 * tree and available online at
 *
 * https://github.com/keeps/commons-ip
 */
package org.roda_project.commons_ip.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.roda_project.commons_ip.model.SIPMetadata;

public final class ZIPUtils {

  private ZIPUtils() {
  }

  public static List<ZipEntryInfo> addMetadataToZip(List<ZipEntryInfo> zipEntries, SIPMetadata dm, String metadataPath)
    throws SIPException {

    if (dm.getSchema() != null) {
      // FIXME this is not right!!!
      addFileToZip(zipEntries, dm.getSchema(), metadataPath);
    }

    addFileToZip(zipEntries, dm.getMetadata(), metadataPath);

    return zipEntries;
  }

  public static List<ZipEntryInfo> addDataToRepresentation(List<ZipEntryInfo> zipEntries, Path dataFile,
    String dataFilePath) throws SIPException {

    addFileToZip(zipEntries, dataFile, dataFilePath);

    return zipEntries;
  }

  public static List<ZipEntryInfo> addFileToZip(List<ZipEntryInfo> zipEntries, Path filePath, String zipPath)
    throws SIPException {

    zipEntries.add(new ZipEntryInfo(zipPath, filePath));

    return zipEntries;
  }

  /**
   * Zip a list of files into an output stream
   * 
   * @param files
   * @param out
   * @throws IOException
   */
  public static void zip(List<ZipEntryInfo> files, OutputStream out) throws IOException {
    ZipOutputStream zos = new ZipOutputStream(out);

    for (ZipEntryInfo file : files) {
      ZipEntry entry = new ZipEntry(file.getName());
      zos.putNextEntry(entry);
      sendToZip(Files.newInputStream(file.getFilePath()), zos);
      zos.closeEntry();
    }

    zos.close();
    out.close();
  }

  private static void sendToZip(InputStream in, ZipOutputStream zos) throws IOException {
    byte[] buffer = new byte[4096];
    int retval;

    do {
      retval = in.read(buffer, 0, 4096);
      if (retval != -1) {
        zos.write(buffer, 0, retval);
      }
    } while (retval != -1);

    in.close();

  }

}
