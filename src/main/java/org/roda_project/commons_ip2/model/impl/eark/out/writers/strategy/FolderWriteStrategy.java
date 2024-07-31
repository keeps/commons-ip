package org.roda_project.commons_ip2.model.impl.eark.out.writers.strategy;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.roda_project.commons_ip.utils.IPException;
import org.roda_project.commons_ip.utils.ZipEntryInfo;
import org.roda_project.commons_ip2.model.SIP;
import org.roda_project.commons_ip2.utils.METSFileTypeZipEntryInfo;
import org.roda_project.commons_ip2.utils.METSMdRefZipEntryInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.xml.bind.DatatypeConverter;

/**
 * @author Miguel Guimarães <mguimaraes@keep.pt>
 */
public class FolderWriteStrategy implements WriteStrategy {
  private static final Logger LOGGER = LoggerFactory.getLogger(FolderWriteStrategy.class);
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

  private void writeToPath(final Map<String, ZipEntryInfo> entries, final Path path, String checksumAlgorithm)
    throws IPException, InterruptedException {
    try {
      Files.createDirectories(path);
      for (ZipEntryInfo zipEntryInfo : entries.values()) {
        if (Thread.interrupted()) {
          throw new InterruptedException();
        }
        zipEntryInfo.setChecksum(checksumAlgorithm);
        zipEntryInfo.prepareEntryForZipping();
        LOGGER.debug("Writing file {}", zipEntryInfo.getFilePath());
        final Path outputPath = Paths.get(path.toString(), zipEntryInfo.getName());
        writeFileToPath(zipEntryInfo, outputPath, checksumAlgorithm);
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

  private void writeFileToPath(final ZipEntryInfo zipEntryInfo, final Path outputPath, String checksumAlgorithm)
    throws IOException, NoSuchAlgorithmException {
    InputStream is = null;
    OutputStream os = null;
    try {

      is = Files.newInputStream(zipEntryInfo.getFilePath());

      Files.createDirectories(outputPath.getParent());
      os = Files.newOutputStream(outputPath);

      final byte[] buffer = new byte[4096];
      final MessageDigest complete = MessageDigest.getInstance(checksumAlgorithm);
      int numRead;
      do {
        numRead = is.read(buffer);
        if (numRead > 0) {
          complete.update(buffer, 0, numRead);
          os.write(buffer, 0, numRead);
        }
      } while (numRead != -1);

      setChecksum(zipEntryInfo, DatatypeConverter.printHexBinary(complete.digest()), checksumAlgorithm);
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
