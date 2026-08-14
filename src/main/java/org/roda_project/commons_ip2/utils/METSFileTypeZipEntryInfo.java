/**
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE file at the root of the source
 * tree and available online at
 *
 * https://github.com/keeps/commons-ip
 */
package org.roda_project.commons_ip2.utils;

import java.nio.file.Path;

import org.roda_project.commons_ip.utils.FileZipEntryInfo;
import org.roda_project.commons_ip2.mets_v1_12.beans.FileType;

public class METSFileTypeZipEntryInfo extends FileZipEntryInfo {
  private FileType metsFileType;

  /**
   * Constructor with name and file path.
   *
   * @param name the entry name
   * @param filePath the file path
   */
  public METSFileTypeZipEntryInfo(final String name, final Path filePath) {
    super(name, filePath);
  }

  /**
   * Constructor with name, file path, and METS file type.
   *
   * @param name the entry name
   * @param filePath the file path
   * @param metsFileType the METS file type
   */
  public METSFileTypeZipEntryInfo(final String name, final Path filePath, final FileType metsFileType) {
    super(name, filePath);
    this.setMetsFileType(metsFileType);
  }

  /**
   * Constructor with name, file path, METS file type, and pre-calculated checksum.
   *
   * @param name the entry name
   * @param filePath the file path
   * @param metsFileType the METS file type
   * @param preCalculatedChecksum the pre-calculated checksum value
   * @param checksumAlgorithm the checksum algorithm used
   */
  public METSFileTypeZipEntryInfo(final String name, final Path filePath, final FileType metsFileType,
    final String preCalculatedChecksum, final String checksumAlgorithm) {
    super(name, filePath);
    this.setMetsFileType(metsFileType);
    if (preCalculatedChecksum != null && !preCalculatedChecksum.isEmpty()) {
      this.setChecksum(preCalculatedChecksum);
      this.setChecksumAlgorithm(checksumAlgorithm);
    }
  }

  @Override
  public void prepareEntryForZipping() {
    // do nothing
  }

  public FileType getMetsFileType() {
    return metsFileType;
  }

  public void setMetsFileType(FileType metsFileType) {
    this.metsFileType = metsFileType;
  }

}
