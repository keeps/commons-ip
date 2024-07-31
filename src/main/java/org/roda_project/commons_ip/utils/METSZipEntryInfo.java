/**
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE file at the root of the source
 * tree and available online at
 *
 * https://github.com/keeps/commons-ip
 */
package org.roda_project.commons_ip.utils;

import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

import org.roda_project.commons_ip.mets_v1_11.beans.Mets;

import jakarta.xml.bind.JAXBException;

public class METSZipEntryInfo extends FileZipEntryInfo {
  private Mets mets;
  private boolean rootMETS;
  private Map<String, String> checksums;
  private long size;

  public METSZipEntryInfo(String name, Path filePath) {
    super(name, filePath);
    checksums = new HashMap<>();
    size = 0;
  }

  public METSZipEntryInfo(String name, Path filePath, Mets mets, boolean rootMETS) {
    super(name, filePath);
    this.mets = mets;
    this.rootMETS = rootMETS;
    checksums = new HashMap<>();
    size = 0;
  }

  public Map<String, String> getChecksums() {
    return checksums;
  }

  public void setChecksums(Map<String, String> checksums) {
    this.checksums = checksums;
  }

  public long getSize() {
    return size;
  }

  public void setSize(long size) {
    this.size = size;
  }

  @Override
  public void prepareEntryForZipping() throws IPException {
    try {
      METSUtils.marshallMETS(mets, getFilePath(), rootMETS);
    } catch (JAXBException | IOException e) {
      throw new IPException("Error marshalling METS", e);
    }
  }

}
