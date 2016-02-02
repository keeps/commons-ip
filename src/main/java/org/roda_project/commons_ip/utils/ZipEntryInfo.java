/**
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE file at the root of the source
 * tree and available online at
 *
 * https://github.com/keeps/commons-ip
 */
package org.roda_project.commons_ip.utils;

import java.nio.file.Path;

/**
 * Information for zipping
 * 
 * @author Luis Faria
 * 
 */
public class ZipEntryInfo {
  private final String name;

  private final Path filePath;

  public ZipEntryInfo(String name, Path filePath) {
    this.name = name;
    this.filePath = filePath;
  }

  /**
   * Get zip entry name
   * 
   * @return the name of the zip entry
   */
  public String getName() {
    return name;
  }

  public Path getFilePath() {
    return filePath;
  }

  @Override
  public String toString() {
    return "ZipEntryInfo [name=" + name + ", filePath=" + filePath + "]";
  }

}
