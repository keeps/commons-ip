/**
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE file at the root of the source
 * tree and available online at
 *
 * https://github.com/keeps/commons-ip
 */
package org.roda_project.commons_ip2.utils;

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
import java.util.HexFormat;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;
import java.util.zip.Deflater;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;


import org.apache.commons.io.IOUtils;
import org.roda_project.commons_ip.model.ParseException;
import org.roda_project.commons_ip.utils.IPException;
import org.roda_project.commons_ip.utils.ZipEntryInfo;
import org.roda_project.commons_ip2.mets_v1_12.beans.FileType;
import org.roda_project.commons_ip2.mets_v1_12.beans.MdSecType.MdRef;
import org.roda_project.commons_ip2.mets_v1_12.beans.Mets;
import org.roda_project.commons_ip2.model.IPConstants;
import org.roda_project.commons_ip2.model.SIP;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class ZIPUtils {
  private static final Logger LOGGER = LoggerFactory.getLogger(ZIPUtils.class);
  private static final int BUFFER_SIZE = 4096;

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
    if (Files.isDirectory(source)) {
      return source;
    }

    Path ipFolderPath = destinationDirectory;
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

    return ipFolderPath;
  }

  public static Map<String, ZipEntryInfo> addMdRefFileToZip(Map<String, ZipEntryInfo> zipEntries, Path filePath,
    String zipPath, MdRef mdRef) throws IPException {
    zipEntries.put(zipPath, new METSMdRefZipEntryInfo(zipPath, filePath, mdRef));
    return zipEntries;
  }

  /**
   * Add a metadata file to the zip entries with an optional pre-calculated checksum.
   * When a pre-calculated checksum is provided and matches the SIP's checksum algorithm,
   * the checksum will not be recalculated during zip creation.
   *
   * @param zipEntries the map of zip entries
   * @param filePath the file path
   * @param zipPath the path within the zip
   * @param mdRef the METS MdRef
   * @param preCalculatedChecksum the pre-calculated checksum (may be null or empty)
   * @param checksumAlgorithm the algorithm used for the pre-calculated checksum (may be null or empty)
   * @return the updated map of zip entries
   */
  public static Map<String, ZipEntryInfo> addMdRefFileToZip(final Map<String, ZipEntryInfo> zipEntries,
    final Path filePath, final String zipPath, final MdRef mdRef, final String preCalculatedChecksum,
    final String checksumAlgorithm) throws IPException {
    zipEntries.put(zipPath, new METSMdRefZipEntryInfo(zipPath, filePath, mdRef,
      preCalculatedChecksum, checksumAlgorithm));
    return zipEntries;
  }

  public static Map<String, ZipEntryInfo> addFileTypeFileToZip(Map<String, ZipEntryInfo> zipEntries, Path filePath,
    String zipPath, FileType fileType) throws IPException {
    zipEntries.put(zipPath, new METSFileTypeZipEntryInfo(zipPath, filePath, fileType));
    return zipEntries;
  }

  /**
   * Add a file to the zip entries with an optional pre-calculated checksum.
   * When a pre-calculated checksum is provided and matches the SIP's checksum algorithm,
   * the checksum will not be recalculated during zip creation.
   *
   * @param zipEntries the map of zip entries
   * @param filePath the file path
   * @param zipPath the path within the zip
   * @param fileType the METS file type
   * @param preCalculatedChecksum the pre-calculated checksum (may be null or empty)
   * @param checksumAlgorithm the algorithm used for the pre-calculated checksum (may be null or empty)
   * @return the updated map of zip entries
   */
  public static Map<String, ZipEntryInfo> addFileTypeFileToZip(final Map<String, ZipEntryInfo> zipEntries,
    final Path filePath, final String zipPath, final FileType fileType, final String preCalculatedChecksum,
    final String checksumAlgorithm) throws IPException {
    zipEntries.put(zipPath, new METSFileTypeZipEntryInfo(zipPath, filePath, fileType,
      preCalculatedChecksum, checksumAlgorithm));
    return zipEntries;
  }

  public static Map<String, ZipEntryInfo> addMETSFileToZip(Map<String, ZipEntryInfo> zipEntries, Path filePath,
    String zipPath, Mets mets, boolean rootMETS, FileType fileType) throws IPException {
    zipEntries.put(zipPath, new METSZipEntryInfo(zipPath, filePath, mets, rootMETS, fileType));
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
    nonMetsChecksumAlgorithms.add(sip.getChecksum());
    Set<String> metsChecksumAlgorithms = new TreeSet<>();
    metsChecksumAlgorithms.addAll(nonMetsChecksumAlgorithms);
    metsChecksumAlgorithms.addAll(sip.getExtraChecksumAlgorithms());

    int i = 0;
    for (ZipEntryInfo file : files.values()) {
      if (Thread.interrupted()) {
        throw new InterruptedException();
      }

      // Save pre-calculated checksum BEFORE it gets overwritten
      final String preCalculatedChecksum = file.getChecksum();
      final String preCalculatedAlgorithm = file.getChecksumAlgorithm();

      file.setChecksum(sip.getChecksum());
      file.prepareEntryForZipping();


      LOGGER.debug("Zipping file {}", file.getFilePath());
      ZipEntry entry;
      if (createSipIdFolder) {
        entry = new ZipEntry(sip.getId() + "/" + file.getName());
      } else {
        entry = new ZipEntry(file.getName());
      }

      zos.putNextEntry(entry);

      try (InputStream inputStream = Files.newInputStream(file.getFilePath());) {
        // Check if file already has a pre-calculated checksum matching the SIP's algorithm
        final boolean hasValidPreCalculatedChecksum = preCalculatedChecksum != null
          && !preCalculatedChecksum.isEmpty()
          && preCalculatedAlgorithm != null
          && preCalculatedAlgorithm.equalsIgnoreCase(sip.getChecksum());

        Map<String, String> checksums;
        if (file instanceof METSZipEntryInfo metsEntry) {
          // METS files always need checksum calculation (they are generated)
          checksums = calculateChecksums(Optional.of(zos), inputStream, metsChecksumAlgorithms);
          metsEntry.setChecksums(checksums);
          metsEntry.setSize(metsEntry.getFilePath().toFile().length());
        } else if (hasValidPreCalculatedChecksum) {
          // File has pre-calculated checksum - just copy data without calculating
          LOGGER.debug("Using pre-calculated checksum for file {}", file.getFilePath());
          copyWithoutChecksum(zos, inputStream);
          checksums = new HashMap<>();
          checksums.put(sip.getChecksum(), preCalculatedChecksum);
        } else {
          checksums = calculateChecksums(Optional.of(zos), inputStream, nonMetsChecksumAlgorithms);
        }

        LOGGER.debug("Done zipping file");
        String checksum = checksums.get(sip.getChecksum());
        String checksumType = sip.getChecksum();
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
    final HexFormat hexFormat = HexFormat.of().withUpperCase();
    algorithms.forEach((alg, dig) -> values.put(alg, hexFormat.formatHex(dig.digest())));

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

        if (!newFile.startsWith(dest.normalize())) {
          throw new IOException("Bad zip entry: " + entryName);
        }

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

  private static void copyWithoutChecksum(final ZipOutputStream zos, final InputStream inputStream)
    throws IOException {
    final byte[] buffer = new byte[BUFFER_SIZE];
    int numRead;
    do {
      numRead = inputStream.read(buffer);
      if (numRead > 0) {
        zos.write(buffer, 0, numRead);
      }
    } while (numRead != -1);
  }

}
