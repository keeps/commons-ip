/**
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE file at the root of the source
 * tree and available online at
 *
 * https://github.com/keeps/commons-ip
 */
package org.roda_project.commons_ip.model.impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import org.roda_project.commons_ip.model.IPConstants;
import org.roda_project.commons_ip.utils.IPException;
import org.roda_project.commons_ip.utils.Utils;
import org.slf4j.Logger;

public class ModelUtils {

  private ModelUtils() {
    // do nothing
  }

  public static void deleteBuildDir(Path buildDir) throws IPException {
    try {
      Utils.deletePath(buildDir);
    } catch (IOException e) {
      throw new IPException("Error while deleting temporary directory that was created to hold IP files", e);
    }
  }

  public static Path createBuildDir(String prefix) throws IPException {
    try {
      return Files.createTempDirectory(prefix);
    } catch (IOException e) {
      throw new IPException("Unable to create temporary directory to hold IP files with prefix '" + prefix + "'", e);
    }
  }

  public static String getFoldersFromList(List<String> folders) {
    StringBuilder sb = new StringBuilder();
    for (String folder : folders) {
      sb.append(folder);
      if (sb.length() > 0) {
        sb.append(IPConstants.ZIP_PATH_SEPARATOR);
      }
    }
    return sb.toString();
  }

  public static void cleanUpUponInterrupt(Logger logger, Path path) {
    if (path != null && Files.exists(path)) {
      try {
        Utils.deletePath(path);
      } catch (IOException e) {
        logger.error("Error while cleaning up unneeded files", e);
      }
    }
  }
}
