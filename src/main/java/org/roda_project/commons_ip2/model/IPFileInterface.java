package org.roda_project.commons_ip2.model;

import java.io.Serializable;
import java.nio.file.Path;
import java.util.List;

/**
 * @author Miguel Guimarães <mguimaraes@keep.pt>
 */
public interface IPFileInterface extends Serializable {

  /**
   * Gets the relative folders for this file.
   *
   * @return list of relative folder names
   */
  List<String> getRelativeFolders();

  /**
   * Gets the file name.
   *
   * @return the file name
   */
  String getFileName();

  /**
   * Gets the path to the file.
   *
   * @return the file path
   */
  Path getPath();

  /**
   * Gets the pre-calculated checksum of the file, if available.
   *
   * @return the checksum value, or null if not set
   */
  String getChecksum();

  /**
   * Gets the algorithm used for the pre-calculated checksum.
   *
   * @return the checksum algorithm (e.g., "SHA-256"), or null if not set
   */
  String getChecksumAlgorithm();
}
