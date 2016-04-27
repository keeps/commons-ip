/**
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE file at the root of the source
 * tree and available online at
 *
 * https://github.com/keeps/commons-ip
 */
package org.roda_project.commons_ip.model;

import java.nio.file.Path;
import java.util.List;

public class ValidationEntry {
  public enum LEVEL {
    ERROR, WARNING, INFO
  }

  private LEVEL level;
  private String message;
  private String description;
  private List<Path> relatedItem;

  public LEVEL getLevel() {
    return level;
  }

  public void setLevel(LEVEL level) {
    this.level = level;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public List<Path> getRelatedItem() {
    return relatedItem;
  }

  public void setRelatedItem(List<Path> relatedItem) {
    this.relatedItem = relatedItem;
  }

  @Override
  public String toString() {
    return "ValidationIssue [level=" + level + ", message=" + message + ", description=" + description
      + ", relatedItem=" + relatedItem + "]";
  }

}
