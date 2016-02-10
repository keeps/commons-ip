/**
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE file at the root of the source
 * tree and available online at
 *
 * https://github.com/keeps/commons-ip
 */
package org.roda_project.commons_ip.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import org.apache.commons.io.IOUtils;
import org.roda_project.commons_ip.model.SIP;

public final class ZIPUtils {

  private ZIPUtils() {
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
   * @param earksip
   * @throws IOException
   */
  public static void zip(List<ZipEntryInfo> files, OutputStream out, SIP earksip) throws IOException {
    ZipOutputStream zos = new ZipOutputStream(out);

    int i = 0;
    for (ZipEntryInfo file : files) {
      ZipEntry entry = new ZipEntry(file.getName());
      zos.putNextEntry(entry);
      InputStream inputStream = Files.newInputStream(file.getFilePath());
      IOUtils.copyLarge(inputStream, zos);
      zos.closeEntry();
      inputStream.close();
      i++;
      earksip.notifySipBuildUpdated(i);
    }

    zos.close();
    out.close();
  }

  public static void unzip(Path zip, final Path dest) throws IOException {

    ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(zip.toFile()));

    ZipEntry zipEntry = zipInputStream.getNextEntry();

    if (zipEntry == null) {
      // No entries in ZIP

      zipInputStream.close();

      throw new IOException("No files inside ZIP");

    } else {

      while (zipEntry != null) {

        // for each entry to be extracted
        String entryName = zipEntry.getName();

        Path newFile = dest.resolve(entryName);

        if (zipEntry.isDirectory()) {

          Files.createDirectories(newFile);

        } else {

          if (!Files.exists(newFile.getParent())) {
            Files.createDirectories(newFile.getParent());
          }

          OutputStream newFileOutputStream = Files.newOutputStream(newFile);

          IOUtils.copyLarge(zipInputStream, newFileOutputStream);

          newFileOutputStream.close();
          zipInputStream.closeEntry();

        }

        zipEntry = zipInputStream.getNextEntry();

      } // end while

      zipInputStream.close();
    }

  }

}
