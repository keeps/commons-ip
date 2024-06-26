package org.roda_project.commons_ip2.model;

import java.io.Serializable;
import java.nio.file.Path;
import java.util.List;

/**
 * @author Miguel Guimarães <mguimaraes@keep.pt>
 */
public interface IPFileInterface extends Serializable {

  List<String> getRelativeFolders();

  String getFileName();

  Path getPath();

  String getChecksum();
  String getChecksumAlgorithm();
}
