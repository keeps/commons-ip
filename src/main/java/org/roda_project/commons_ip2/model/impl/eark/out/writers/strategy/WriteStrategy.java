package org.roda_project.commons_ip2.model.impl.eark.out.writers.strategy;

import java.nio.file.Path;
import java.util.Map;

import org.roda_project.commons_ip.utils.IPException;
import org.roda_project.commons_ip.utils.ZipEntryInfo;
import org.roda_project.commons_ip2.model.SIP;

/**
 * @author Miguel Guimarães <mguimaraes@keep.pt>
 */
public interface WriteStrategy {

  void setup(Path destinationPath);

  Path write(Map<String, ZipEntryInfo> entries, SIP sip, String fileNameWithoutExtension, String fallbackName,
    boolean deleteExisting) throws IPException, InterruptedException;

  Path getDestinationPath();
}
