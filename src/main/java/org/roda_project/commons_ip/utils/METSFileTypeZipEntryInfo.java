/**
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE file at the root of the source
 * tree and available online at
 *
 * https://github.com/keeps/commons-ip
 */
package org.roda_project.commons_ip.utils;

import java.nio.file.Path;

import org.roda_project.commons_ip.mets_v1_11.beans.FileType;

public class METSFileTypeZipEntryInfo extends FileZipEntryInfo {
  private FileType metsFileType;

  public METSFileTypeZipEntryInfo(String name, Path filePath) {
    super(name, filePath);
  }

  public METSFileTypeZipEntryInfo(String name, Path filePath, FileType metsFileType) {
    super(name, filePath);
    this.setMetsFileType(metsFileType);
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
