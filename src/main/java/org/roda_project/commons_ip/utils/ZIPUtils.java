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
import java.nio.file.DirectoryStream;
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
import org.roda_project.commons_ip.model.AIP;
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
   *          IP
   * @param destinationDirectory
   *          this path is only used if unzipping the SIP, otherwise source will
   *          be used
   * @param ipFileExtension
   *          file extension (e.g. .zip)
   */
  public static Path extractIPIfInZipFormat(final Path source, Path destinationDirectory) throws ParseException {
    Path ipFolderPath = destinationDirectory;
    if (!Files.isDirectory(source)) {
      try {
        ZIPUtils.unzip(source, destinationDirectory);

        // 20161111 hsilva: see if the IP extracted has a folder which contains
        // the content of the IP (for being compliant with previous way of
        // creating SIP in ZIP format, this test/adjustment is needed)
        if (!Files.exists(destinationDirectory.resolve(IPConstants.METS_FILE))) {
          try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(destinationDirectory)) {
            for (Path path : directoryStream) {
              if (Files.isDirectory(path) && Files.exists(path.resolve(IPConstants.METS_FILE))) {
                ipFolderPath = path;
                break;
              }
            }
          }
        }
      } catch (IOException e) {
        LOGGER.error("Error unzipping file", e);
        throw new ParseException("Error unzipping file", e);
      }
    }

    return ipFolderPath;
  }

  public static List<ZipEntryInfo> addMdRefFileToZip(List<ZipEntryInfo> zipEntries, Path filePath, String zipPath,
    MdRef mdRef) throws IPException {

    zipEntries.add(new METSMdRefZipEntryInfo(zipPath, filePath, mdRef));

    return zipEntries;
  }

  public static List<ZipEntryInfo> addFileTypeFileToZip(List<ZipEntryInfo> zipEntries, Path filePath, String zipPath,
    FileType fileType) throws IPException {

    zipEntries.add(new METSFileTypeZipEntryInfo(zipPath, filePath, fileType));

    return zipEntries;
  }

  public static List<ZipEntryInfo> addMETSFileToZip(List<ZipEntryInfo> zipEntries, Path filePath, String zipPath,
    Mets mets, boolean rootMETS) throws IPException {

    zipEntries.add(new METSZipEntryInfo(zipPath, filePath, mets, rootMETS));

    return zipEntries;
  }

  public static void zip(List<ZipEntryInfo> files, OutputStream out, SIP sip)
    throws IOException, InterruptedException, IPException {
    zip(files, out, sip, true);
  }

  public static void zip(List<ZipEntryInfo> files, OutputStream out, SIP sip, boolean createSipIdFolder)
    throws IOException, InterruptedException, IPException {
    ZipOutputStream zos = new ZipOutputStream(out);

    int i = 0;
    for (ZipEntryInfo file : files) {
      if (Thread.interrupted()) {
        throw new InterruptedException();
      }

      file.prepareEntryforZipping();

      LOGGER.debug("Zipping file {}", file.getFilePath());
      ZipEntry entry;
      if (createSipIdFolder) {
        entry = new ZipEntry(sip.getId() + "/" + file.getName());
      } else {
        entry = new ZipEntry(file.getName());
      }

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
        String checksum = DatatypeConverter.printHexBinary(complete.digest());
        String checksumType = IPConstants.CHECKSUM_ALGORITHM;
        file.setChecksum(checksum);
        file.setChecksumAlgorithm(checksumType);
        if (file instanceof METSFileTypeZipEntryInfo) {
          METSFileTypeZipEntryInfo f = (METSFileTypeZipEntryInfo) file;
          f.getMetsFileType().setCHECKSUM(checksum);
          f.getMetsFileType().setCHECKSUMTYPE(checksumType);
        } else if (file instanceof METSMdRefZipEntryInfo) {
          METSMdRefZipEntryInfo f = (METSMdRefZipEntryInfo) file;
          f.getMetsMdRef().setCHECKSUM(checksum);
          f.getMetsMdRef().setCHECKSUMTYPE(checksumType);
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

  public static void zip(List<ZipEntryInfo> files, OutputStream out, AIP aip)
    throws IOException, InterruptedException, IPException {
    ZipOutputStream zos = new ZipOutputStream(out);

    for (ZipEntryInfo file : files) {
      if (Thread.interrupted()) {
        throw new InterruptedException();
      }

      file.prepareEntryforZipping();

      LOGGER.debug("Zipping file {}", file.getFilePath());
      ZipEntry entry = new ZipEntry(aip.getId() + "/" + file.getName());
      zos.putNextEntry(entry);

      try (InputStream inputStream = Files.newInputStream(file.getFilePath())) {

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
          String checksum = DatatypeConverter.printHexBinary(complete.digest());
          String checksumType = IPConstants.CHECKSUM_ALGORITHM;
          file.setChecksum(checksum);
          file.setChecksumAlgorithm(checksumType);
          if (file instanceof METSFileTypeZipEntryInfo) {
            METSFileTypeZipEntryInfo f = (METSFileTypeZipEntryInfo) file;
            f.getMetsFileType().setCHECKSUM(checksum);
            f.getMetsFileType().setCHECKSUMTYPE(checksumType);
          } else if (file instanceof METSMdRefZipEntryInfo) {
            METSMdRefZipEntryInfo f = (METSMdRefZipEntryInfo) file;
            f.getMetsMdRef().setCHECKSUM(checksum);
            f.getMetsMdRef().setCHECKSUMTYPE(checksumType);
          }
        } catch (NoSuchAlgorithmException e) {
          LOGGER.error("Error while zipping files", e);
        }
        zos.closeEntry();
        inputStream.close();
      }
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
