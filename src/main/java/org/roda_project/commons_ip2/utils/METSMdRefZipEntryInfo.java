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
import org.roda_project.commons_ip2.mets_v1_12.beans.MdSecType.MdRef;

public class METSMdRefZipEntryInfo extends FileZipEntryInfo {
  private MdRef metsMdRef;

  public METSMdRefZipEntryInfo(String name, Path filePath) {
    super(name, filePath);
  }

  public METSMdRefZipEntryInfo(String name, Path filePath, MdRef metsMdRef) {
    super(name, filePath);
    this.setMetsMdRef(metsMdRef);
  }

  /**
   * Constructor with pre-calculated checksum support.
   *
   * @param name the zip entry name
   * @param filePath the file path
   * @param metsMdRef the METS MdRef
   * @param preCalculatedChecksum the pre-calculated checksum (may be null or empty)
   * @param checksumAlgorithm the algorithm used for the pre-calculated checksum (may be null or empty)
   */
  public METSMdRefZipEntryInfo(String name, Path filePath, final MdRef metsMdRef,
      String preCalculatedChecksum, String checksumAlgorithm) {
    super(name, filePath);
    this.setMetsMdRef(metsMdRef);
    if (preCalculatedChecksum != null && !preCalculatedChecksum.isEmpty()) {
      this.setChecksum(preCalculatedChecksum);
      this.setChecksumAlgorithm(checksumAlgorithm);
    }
  }

  @Override
  public void prepareEntryForZipping() {
    // do nothing
  }

  public MdRef getMetsMdRef() {
    return metsMdRef;
  }

  public void setMetsMdRef(MdRef metsMdRef) {
    this.metsMdRef = metsMdRef;
  }

}
