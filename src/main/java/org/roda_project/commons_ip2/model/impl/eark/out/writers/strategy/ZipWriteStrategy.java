package org.roda_project.commons_ip2.model.impl.eark.out.writers.strategy;

import static org.roda_project.commons_ip2.model.IPConstants.SIP_FILE_EXTENSION;

import java.io.IOException;
import java.nio.channels.ClosedByInterruptException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

import org.roda_project.commons_ip.utils.IPException;
import org.roda_project.commons_ip.utils.ZipEntryInfo;
import org.roda_project.commons_ip2.model.SIP;
import org.roda_project.commons_ip2.utils.ZIPUtils;

/**
 * @author Miguel Guimarães <mguimaraes@keep.pt>
 */
public class ZipWriteStrategy implements WriteStrategy {

  private Path destinationPath;

  @Override
  public void setup(Path destinationPath) {
    this.destinationPath = destinationPath;
  }

  @Override
  public Path write(Map<String, ZipEntryInfo> entries, SIP sip, String fileNameWithoutExtension, String fallbackName,
                    boolean deleteExisting) throws IPException, InterruptedException {
    Path zipPath = getZipPath(destinationPath, fileNameWithoutExtension, fallbackName);

    try {
      ZIPUtils.zip(entries, Files.newOutputStream(zipPath), sip, true, true);
    } catch (ClosedByInterruptException e) {
      throw new InterruptedException();
    } catch (IOException e) {
      throw new IPException("Error generating E-ARK SIP ZIP file. Reason: " + e.getMessage(), e);
    }

    return zipPath;
  }

  @Override
  public Path getDestinationPath() {
    return this.destinationPath;
  }

  private Path getZipPath(Path destinationDirectory, String fileNameWithoutExtension, String fallbackName)
    throws IPException {
    Path zipPath;
    if (fileNameWithoutExtension != null) {
      zipPath = destinationDirectory.resolve(fileNameWithoutExtension + SIP_FILE_EXTENSION);
    } else {
      zipPath = destinationDirectory.resolve(fallbackName + SIP_FILE_EXTENSION);
    }

    try {
      if (Files.exists(zipPath)) {
        Files.delete(zipPath);
      }
    } catch (IOException e) {
      throw new IPException("Error deleting already existing zip", e);
    }
    return zipPath;
  }
}
