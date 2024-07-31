/**
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE file at the root of the source
 * tree and available online at
 *
 * https://github.com/keeps/commons-ip
 */
package org.roda_project.commons_ip.utils;

import java.nio.file.Path;

public class FileZipEntryInfo implements ZipEntryInfo {
  private final String name;
  private final Path filePath;
  private String checksum;
  private String checksumAlgorithm;

  public FileZipEntryInfo(String name, Path filePath) {
    this.name = name;
    this.filePath = filePath;
  }

  /**
   * Get zip entry name
   * 
   * @return the name of the zip entry
   */
  @Override
  public String getName() {
    return name;
  }

  @Override
  public Path getFilePath() {
    return filePath;
  }

  @Override
  public void prepareEntryForZipping() throws IPException {
    // do nothing
  }

  @Override
  public String toString() {
    return "FileZipEntryInfo [name=" + name + ", filePath=" + filePath + "]";
  }

  public String getChecksum() {
    return checksum;
  }

  public void setChecksum(String checksum) {
    this.checksum = checksum;
  }

  public String getChecksumAlgorithm() {
    return checksumAlgorithm;
  }

  public void setChecksumAlgorithm(String checksumAlgorithm) {
    this.checksumAlgorithm = checksumAlgorithm;
  }

}
