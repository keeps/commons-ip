/**
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE file at the root of the source
 * tree and available online at
 *
 * https://github.com/keeps/commons-ip
 */
package org.roda_project.commons_ip.utils;

import java.nio.file.Path;

public interface ZipEntryInfo {

  String getName();

  Path getFilePath();

  void prepareEntryForZipping() throws IPException;

  String getChecksum();

  void setChecksum(String checksum);

  String getChecksumAlgorithm();

  void setChecksumAlgorithm(String checksumAlgorithm);

}
