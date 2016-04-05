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

import javax.xml.bind.JAXBException;

import org.roda_project.commons_ip.mets_v1_11.beans.Mets;

public class METSZipEntryInfo extends FileZipEntryInfo {
  private Mets mets;
  private boolean rootMETS;

  public METSZipEntryInfo(String name, Path filePath) {
    super(name, filePath);
  }

  public METSZipEntryInfo(String name, Path filePath, Mets mets, boolean rootMETS) {
    super(name, filePath);
    this.mets = mets;
    this.rootMETS = rootMETS;
  }

  @Override
  public void prepareEntryforZipping() throws IPException {
    try {
      METSUtils.marshallMETS(mets, getFilePath(), rootMETS);
    } catch (JAXBException | IOException e) {
      throw new IPException("Error marshalling METS", e);
    }
  }

}
