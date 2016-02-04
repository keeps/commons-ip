/**
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE file at the root of the source
 * tree and available online at
 *
 * https://github.com/keeps/commons-ip
 */
package org.roda_project.commons_ip.model;

import java.io.Serializable;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class IPFile implements Serializable {
  private static final long serialVersionUID = -8653651803476080935L;

  private Path path;
  private String renameTo;
  private List<String> relativeFolders;

  public IPFile(Path path) {
    super();
    this.path = path;
    this.renameTo = null;
    this.relativeFolders = new ArrayList<>();
  }

  public IPFile(Path path, List<String> relativeFolders) {
    super();
    this.path = path;
    this.renameTo = null;
    this.relativeFolders = relativeFolders;
  }

  public IPFile(Path path, List<String> relativeFolders, String renameTo) {
    super();
    this.path = path;
    this.renameTo = renameTo;
    this.relativeFolders = relativeFolders;
  }

  public Path getPath() {
    return path;
  }

  public void setPath(Path path) {
    this.path = path;
  }

  public List<String> getRelativeFolders() {
    return relativeFolders;
  }

  public void setRelativeFolders(List<String> relativeFolders) {
    this.relativeFolders = relativeFolders;
  }

  public String getRenameTo() {
    return renameTo;
  }

  public void setRenameTo(String renameTo) {
    this.renameTo = renameTo;
  }

  public String getFileName() {
    final String filename;
    if (renameTo != null) {
      filename = renameTo;
    } else {
      filename = path.getFileName().toString();
    }
    return filename;
  }

  @Override
  public String toString() {
    return "IPFile [path=" + path + ", renameTo=" + renameTo + ", relativeFolders=" + relativeFolders + "]";
  }

}
