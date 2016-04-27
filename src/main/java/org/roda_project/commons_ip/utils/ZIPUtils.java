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
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import javax.xml.bind.DatatypeConverter;

import org.apache.commons.io.IOUtils;
import org.roda_project.commons_ip.mets_v1_11.beans.FileType;
import org.roda_project.commons_ip.mets_v1_11.beans.MdSecType.MdRef;
import org.roda_project.commons_ip.mets_v1_11.beans.Mets;
import org.roda_project.commons_ip.model.IPConstants;
import org.roda_project.commons_ip.model.ParseException;
import org.roda_project.commons_ip.model.SIP;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class ZIPUtils {
  private static final Logger LOGGER = LoggerFactory.getLogger(ZIPUtils.class);

  private ZIPUtils() {
  }

  /**
   * @param source
   *          SIP
   * @param destinationDirectory
   *          this path is only used if unzipping the SIP, otherwise source will
   *          be used
   * @param sipFileExtension
   *          file extension (e.g. .zip)
   * @throws SIPException
   */
  public static Path extractSIPIfInZipFormat(final Path source, Path destinationDirectory, String sipFileExtension)
    throws ParseException {
    Path sipFolderPath = source;
    if (!Files.isDirectory(source)) {
      try {
        sipFolderPath = destinationDirectory
          .resolve(source.getFileName().toString().replaceFirst(sipFileExtension + "$", ""));
        ZIPUtils.unzip(source, sipFolderPath);
      } catch (IOException e) {
        LOGGER.error("Error unzipping file", e);
        throw new ParseException("Error unzipping file", e);
      }
    }

    return sipFolderPath;
  }

  public static List<ZipEntryInfo> addMdRefFileToZip(List<ZipEntryInfo> zipEntries, Path filePath, String zipPath,
    MdRef mdRef) throws SIPException {

    zipEntries.add(new METSMdRefZipEntryInfo(zipPath, filePath, mdRef));

    return zipEntries;
  }

  public static List<ZipEntryInfo> addFileTypeFileToZip(List<ZipEntryInfo> zipEntries, Path filePath, String zipPath,
    FileType fileType) throws SIPException {

    zipEntries.add(new METSFileTypeZipEntryInfo(zipPath, filePath, fileType));

    return zipEntries;
  }

  public static List<ZipEntryInfo> addMETSFileToZip(List<ZipEntryInfo> zipEntries, Path filePath, String zipPath,
    Mets mets, boolean rootMETS) throws SIPException {

    zipEntries.add(new METSZipEntryInfo(zipPath, filePath, mets, rootMETS));

    return zipEntries;
  }

  public static void zip(List<ZipEntryInfo> files, OutputStream out, SIP sip, boolean calculateChecksumDuringProcessing)
    throws IOException, InterruptedException, IPException {
    if (calculateChecksumDuringProcessing) {
      zip(files, out, sip);
    } else {
      zipWhileCalculatingChecksum(files, out, sip);
    }
  }

  private static void zipWhileCalculatingChecksum(List<ZipEntryInfo> files, OutputStream out, SIP sip)
    throws IOException, InterruptedException, IPException {
    ZipOutputStream zos = new ZipOutputStream(out);

    int i = 0;
    for (ZipEntryInfo file : files) {
      if (Thread.interrupted()) {
        throw new InterruptedException();
      }

      file.prepareEntryforZipping();

      LOGGER.debug("Zipping file {}", file.getFilePath());
      ZipEntry entry = new ZipEntry(file.getName());
      zos.putNextEntry(entry);
      InputStream inputStream = Files.newInputStream(file.getFilePath());

      byte[] buffer = new byte[4096];
      MessageDigest complete;
      try {
        complete = MessageDigest.getInstance(IPConstants.CHECKSUM_ALGORITHM);
        int numRead;
        do {
          numRead = inputStream.read(buffer);
          if (numRead > 0) {
            complete.update(buffer, 0, numRead);
            zos.write(buffer, 0, numRead);
          }
        } while (numRead != -1);
        LOGGER.debug("Done zipping file");
        if (file instanceof METSFileTypeZipEntryInfo) {
          METSFileTypeZipEntryInfo f = (METSFileTypeZipEntryInfo) file;
          f.getMetsFileType().setCHECKSUM(DatatypeConverter.printHexBinary(complete.digest()));
          f.getMetsFileType().setCHECKSUMTYPE(IPConstants.CHECKSUM_ALGORITHM);
        } else if (file instanceof METSMdRefZipEntryInfo) {
          METSMdRefZipEntryInfo f = (METSMdRefZipEntryInfo) file;
          f.getMetsMdRef().setCHECKSUM(DatatypeConverter.printHexBinary(complete.digest()));
          f.getMetsMdRef().setCHECKSUMTYPE(IPConstants.CHECKSUM_ALGORITHM);
        }
      } catch (NoSuchAlgorithmException e) {
        LOGGER.error("Error while zipping files", e);
      }
      zos.closeEntry();
      inputStream.close();
      i++;

      sip.notifySipBuildPackagingCurrentStatus(i);
    }

    zos.close();
    out.close();
  }

  private static void zip(List<ZipEntryInfo> files, OutputStream out, SIP sip)
    throws IOException, InterruptedException, IPException {
    ZipOutputStream zos = new ZipOutputStream(out);

    int i = 0;
    for (ZipEntryInfo file : files) {
      if (Thread.interrupted()) {
        throw new InterruptedException();
      }

      file.prepareEntryforZipping();

      LOGGER.debug("Zipping file {}", file.getFilePath());
      ZipEntry entry = new ZipEntry(file.getName());
      zos.putNextEntry(entry);
      InputStream inputStream = Files.newInputStream(file.getFilePath());
      IOUtils.copyLarge(inputStream, zos);
      zos.closeEntry();
      inputStream.close();
      LOGGER.debug("Done zipping file");
      i++;
      sip.notifySipBuildPackagingCurrentStatus(i);
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
