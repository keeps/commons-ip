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
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;
import java.util.zip.Deflater;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import jakarta.xml.bind.DatatypeConverter;

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
    // do nothing
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
        if (Files.exists(destinationDirectory) && !Files.exists(destinationDirectory.resolve(IPConstants.METS_FILE))) {
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
        throw new ParseException("Error unzipping file", e);
      }
    }

    return ipFolderPath;
  }

  public static Map<String, ZipEntryInfo> addMdRefFileToZip(Map<String, ZipEntryInfo> zipEntries, Path filePath,
    String zipPath, MdRef mdRef) throws IPException {
    zipEntries.put(zipPath, new METSMdRefZipEntryInfo(zipPath, filePath, mdRef));
    return zipEntries;
  }

  public static Map<String, ZipEntryInfo> addFileTypeFileToZip(Map<String, ZipEntryInfo> zipEntries, Path filePath,
    String zipPath, FileType fileType) throws IPException {
    zipEntries.put(zipPath, new METSFileTypeZipEntryInfo(zipPath, filePath, fileType));
    return zipEntries;
  }

  public static Map<String, ZipEntryInfo> addMETSFileToZip(Map<String, ZipEntryInfo> zipEntries, Path filePath,
    String zipPath, Mets mets, boolean rootMETS) throws IPException {
    zipEntries.put(zipPath, new METSZipEntryInfo(zipPath, filePath, mets, rootMETS));
    return zipEntries;
  }

  public static void zip(Map<String, ZipEntryInfo> files, OutputStream out, SIP sip, boolean isCompressed)
    throws IOException, InterruptedException, IPException {
    zip(files, out, sip, true, isCompressed);
  }

  public static void zip(Map<String, ZipEntryInfo> files, OutputStream out, SIP sip, boolean createSipIdFolder,
    boolean isCompressed) throws IOException, InterruptedException, IPException {
    ZipOutputStream zos = new ZipOutputStream(out);
    if (isCompressed) {
      zos.setLevel(Deflater.DEFAULT_COMPRESSION);
    } else {
      zos.setLevel(Deflater.NO_COMPRESSION);
    }

    Set<String> nonMetsChecksumAlgorithms = new TreeSet<>();
    nonMetsChecksumAlgorithms.add(sip.getChecksumAlgorithm());
    Set<String> metsChecksumAlgorithms = new TreeSet<>();
    metsChecksumAlgorithms.addAll(nonMetsChecksumAlgorithms);
    metsChecksumAlgorithms.addAll(sip.getExtraChecksumAlgorithms());

    int i = 0;
    for (ZipEntryInfo file : files.values()) {
      if (Thread.interrupted()) {
        throw new InterruptedException();
      }

      file.setChecksum(sip.getChecksumAlgorithm());
      file.prepareEntryforZipping();

      LOGGER.debug("Zipping file {}", file.getFilePath());
      ZipEntry entry;
      if (createSipIdFolder) {
        entry = new ZipEntry(sip.getId() + "/" + file.getName());
      } else {
        entry = new ZipEntry(file.getName());
      }

      zos.putNextEntry(entry);

      try (InputStream inputStream = Files.newInputStream(file.getFilePath())) {
        Map<String, String> checksums;
        if (file instanceof METSZipEntryInfo metsEntry) {
          checksums = calculateChecksums(Optional.of(zos), inputStream, metsChecksumAlgorithms);
            metsEntry.setChecksums(checksums);
          metsEntry.setSize(metsEntry.getFilePath().toFile().length());
        } else {
          checksums = calculateChecksums(Optional.of(zos), inputStream, nonMetsChecksumAlgorithms);
        }

        LOGGER.debug("Done zipping file");
        String checksum = checksums.get(sip.getChecksumAlgorithm());
        String checksumType = sip.getChecksumAlgorithm();
        file.setChecksum(checksum);
        file.setChecksumAlgorithm(checksumType);
        if (file instanceof METSFileTypeZipEntryInfo f) {
            f.getMetsFileType().setCHECKSUM(checksum);
          f.getMetsFileType().setCHECKSUMTYPE(checksumType);
        } else if (file instanceof METSMdRefZipEntryInfo f) {
            f.getMetsMdRef().setCHECKSUM(checksum);
          f.getMetsMdRef().setCHECKSUMTYPE(checksumType);
        }
      } catch (NoSuchAlgorithmException e) {
        LOGGER.error("Error while zipping files", e);
      }
      zos.closeEntry();
      i++;

      sip.notifySipBuildPackagingCurrentStatus(i);
    }

    zos.close();
    out.close();
  }

  public static Map<String, String> calculateChecksums(Optional<ZipOutputStream> zos, InputStream inputStream,
    Set<String> checksumAlgorithms) throws NoSuchAlgorithmException, IOException {
    byte[] buffer = new byte[4096];
    Map<String, String> values = new HashMap<>();

    // instantiate different checksum algorithms
    Map<String, MessageDigest> algorithms = new HashMap<>();
    for (String alg : checksumAlgorithms) {
      algorithms.put(alg, MessageDigest.getInstance(alg));
    }

    // calculate value for each one of the algorithms
    int numRead;
    do {
      numRead = inputStream.read(buffer);
      if (numRead > 0) {
        for (Entry<String, MessageDigest> alg : algorithms.entrySet()) {
          alg.getValue().update(buffer, 0, numRead);
        }

        if (zos.isPresent()) {
          zos.get().write(buffer, 0, numRead);
        }
      }
    } while (numRead != -1);

    // generate hex versions of the digests
    algorithms.forEach((alg, dig) -> values.put(alg, DatatypeConverter.printHexBinary(dig.digest())));

    return values;
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
        if (Utils.systemIsWindows()) {
          entryName = entryName.replaceAll("/", "\\\\");
        }
        Path newFile = dest.resolve(entryName).normalize();

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
