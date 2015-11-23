package org.eark.validation.model;

import java.nio.file.Path;
import java.util.List;

public class ValidationIssue {
  public enum LEVEL {
    ERROR, WARNING
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
}
