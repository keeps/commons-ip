package org.roda_project.commons_ip2.validator.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * @author João Gomes <jgomes@keep.pt>
 */

public class ZipManager {
  private static final Logger LOGGER = LoggerFactory.getLogger(ZipManager.class);
  private ZipFile zipFile = null;

  public InputStream getZipInputStream(Path path, String entry) throws IOException {
    if (zipFile == null) {
      zipFile = new ZipFile(path.toFile());
    }
    ZipEntry zipArchiveEntry = zipFile.getEntry(entry);

    return zipFile.getInputStream(zipArchiveEntry);
  }

  public ZipEntry getZipEntry(Path path, String entry){
    try {
      if (zipFile == null) {
        zipFile = new ZipFile(path.toFile());
      }

      return zipFile.getEntry(entry);
    } catch (IOException e) {
      LOGGER.debug("Failed to retrieve the entry: {} from {}", entry, path.toString(), e);
      return null;
    }
  }

  public void closeZipFile() {
    if (zipFile != null) {
      try {
        zipFile.close();
        zipFile = null;
      } catch (IOException e) {
        LOGGER.debug("Failed to close the ZipFile after an error occurred", e);
      }
    }
  }
}
