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

/** Zip entry info for METS MdRef elements. */
public class METSMdRefZipEntryInfo extends FileZipEntryInfo {
  /** The METS MdRef element. */
  private MdRef metsMdRef;

  /**
   * Constructor.
   *
   * @param name
   *          the zip entry name
   * @param filePath
   *          the file path
   */
  public METSMdRefZipEntryInfo(final String name, final Path filePath) {
    super(name, filePath);
  }

  /**
   * Constructor with MdRef.
   *
   * @param name
   *          the zip entry name
   * @param filePath
   *          the file path
   * @param metsMdRef
   *          the METS MdRef
   */
  public METSMdRefZipEntryInfo(final String name, final Path filePath,
      final MdRef metsMdRef) {
    super(name, filePath);
    this.setMetsMdRef(metsMdRef);
  }

  /**
   * Constructor with pre-calculated checksum support.
   *
   * @param name
   *          the zip entry name
   * @param filePath
   *          the file path
   * @param metsMdRef
   *          the METS MdRef
   * @param preCalculatedChecksum
   *          the pre-calculated checksum (may be null or empty)
   * @param checksumAlgorithm
   *          the algorithm used for the pre-calculated checksum
   */
  public METSMdRefZipEntryInfo(final String name, final Path filePath,
      final MdRef metsMdRef, final String preCalculatedChecksum,
      final String checksumAlgorithm) {
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

  /**
   * Gets the METS MdRef.
   *
   * @return the METS MdRef
   */
  public final MdRef getMetsMdRef() {
    return metsMdRef;
  }

  /**
   * Sets the METS MdRef.
   *
   * @param metsMdRef
   *          the METS MdRef to set
   */
  public final void setMetsMdRef(final MdRef metsMdRef) {
    this.metsMdRef = metsMdRef;
  }

}
