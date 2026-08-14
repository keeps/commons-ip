package org.roda_project.commons_ip2.model.impl.eark.out.writers.strategy;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HexFormat;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.roda_project.commons_ip.utils.IPException;
import org.roda_project.commons_ip.utils.ZipEntryInfo;
import org.roda_project.commons_ip2.model.SIP;
import org.roda_project.commons_ip2.utils.METSFileTypeZipEntryInfo;
import org.roda_project.commons_ip2.utils.METSMdRefZipEntryInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @author Miguel Guimarães <mguimaraes@keep.pt>
 */
public class FolderWriteStrategy implements WriteStrategy {
  private static final Logger LOGGER = LoggerFactory.getLogger(FolderWriteStrategy.class);
  private static final int BUFFER_SIZE = 4096;
  private Path destinationPath;

  @Override
  public void setup(Path destinationPath) {
    this.destinationPath = destinationPath;
  }

  @Override
  public Path getDestinationPath() {
    return this.destinationPath;
  }

  @Override
  public Path write(Map<String, ZipEntryInfo> entries, SIP sip, String fileNameWithoutExtension, String fallbackName,
    boolean deleteExisting) throws IPException, InterruptedException {
    Path dirPath = getDirPath(destinationPath, fileNameWithoutExtension, fallbackName, deleteExisting);

    writeToPath(entries, dirPath, sip.getChecksum());

    return dirPath;
  }

  @Override
  public Path write(Map<String, ZipEntryInfo> entries, SIP sip, String fileNameWithoutExtension, String fallbackName,
    boolean createSipIdFolder, boolean deleteExisting) throws IPException, InterruptedException {
    throw new UnsupportedOperationException("Method not implemented");
  }

  private void writeToPath(final Map<String, ZipEntryInfo> entries, final Path path, final String checksumAlgorithm)
    throws IPException, InterruptedException {
    try {
      Files.createDirectories(path);
      for (ZipEntryInfo zipEntryInfo : entries.values()) {
        if (Thread.interrupted()) {
          throw new InterruptedException();
        }
        // Save pre-calculated checksum BEFORE it gets overwritten
        final String preCalculatedChecksum = zipEntryInfo.getChecksum();
        final String preCalculatedAlgorithm = zipEntryInfo.getChecksumAlgorithm();

        zipEntryInfo.setChecksum(checksumAlgorithm);
        zipEntryInfo.prepareEntryForZipping();
        LOGGER.debug("Writing file {}", zipEntryInfo.getFilePath());
        final Path outputPath = Paths.get(path.toString(), zipEntryInfo.getName());
        writeFileToPath(zipEntryInfo, outputPath, checksumAlgorithm, preCalculatedChecksum, preCalculatedAlgorithm);
      }
    } catch (final IOException | NoSuchAlgorithmException e) {
      LOGGER.debug("Error in write method", e);
      throw new IPException(e.getMessage(), e);
    }
  }

  private Path getDirPath(final Path targetPath, final String name, String fallbackName, final boolean deleteExisting)
    throws IPException {
    final Path path;
    if (name != null) {
      path = targetPath.resolve(name);
    } else {
      path = targetPath.resolve(fallbackName);
    }

    try {
      if (deleteExisting && Files.exists(path)) {
        Files.delete(path);
      }
    } catch (final IOException e) {
      throw new IPException("Error deleting existing path - " + e.getMessage(), e);
    }
    return path;
  }

  private void writeFileToPath(final ZipEntryInfo zipEntryInfo, final Path outputPath, final String checksumAlgorithm,
    final String preCalculatedChecksum, final String preCalculatedAlgorithm)
    throws IOException, NoSuchAlgorithmException {
    InputStream is = null;
    OutputStream os = null;
    try {
      is = Files.newInputStream(zipEntryInfo.getFilePath());

      Files.createDirectories(outputPath.getParent());
      os = Files.newOutputStream(outputPath);

      // Check if file already has a pre-calculated checksum matching the requested algorithm
      final boolean hasValidPreCalculatedChecksum = preCalculatedChecksum != null
        && !preCalculatedChecksum.isEmpty()
        && preCalculatedAlgorithm != null
        && preCalculatedAlgorithm.equalsIgnoreCase(checksumAlgorithm);

      if (hasValidPreCalculatedChecksum) {
        // File has pre-calculated checksum - just copy data without calculating
        LOGGER.debug("Using pre-calculated checksum for file {}", zipEntryInfo.getFilePath());
        final byte[] buffer = new byte[BUFFER_SIZE];
        int numRead;
        do {
          numRead = is.read(buffer);
          if (numRead > 0) {
            os.write(buffer, 0, numRead);
          }
        } while (numRead != -1);
        setChecksum(zipEntryInfo, preCalculatedChecksum, preCalculatedAlgorithm);
      } else {
        // Calculate checksum while copying
        final byte[] buffer = new byte[BUFFER_SIZE];
        final MessageDigest complete = MessageDigest.getInstance(checksumAlgorithm);
        int numRead;
        do {
          numRead = is.read(buffer);
          if (numRead > 0) {
            complete.update(buffer, 0, numRead);
            os.write(buffer, 0, numRead);
          }
        } while (numRead != -1);

        setChecksum(zipEntryInfo, HexFormat.of().withUpperCase().formatHex(complete.digest()), checksumAlgorithm);
      }
    } finally {
      IOUtils.closeQuietly(is);
      IOUtils.closeQuietly(os);
    }
  }

  private void setChecksum(final ZipEntryInfo zipEntryInfo, final String checksum, final String checksumType) {
    zipEntryInfo.setChecksum(checksum);
    zipEntryInfo.setChecksumAlgorithm(checksumType);
    if (zipEntryInfo instanceof METSFileTypeZipEntryInfo f) {
      f.getMetsFileType().setCHECKSUM(checksum);
      f.getMetsFileType().setCHECKSUMTYPE(checksumType);
    } else if (zipEntryInfo instanceof METSMdRefZipEntryInfo f) {
      f.getMetsMdRef().setCHECKSUM(checksum);
      f.getMetsMdRef().setCHECKSUMTYPE(checksumType);
    }
  }
}
