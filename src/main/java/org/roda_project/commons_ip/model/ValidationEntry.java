/**
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE file at the root of the source
 * tree and available online at
 * <p>
 * https://github.com/keeps/commons-ip
 */
package org.roda_project.commons_ip.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import org.roda_project.commons_ip2.validator.model.pyip.StructStatus;

import java.nio.file.Path;
import java.util.List;

public class ValidationEntry {
  public enum LEVEL {
    ERROR("Error"), WARN("Warning"), INFO("Information");

    private final String value;

    LEVEL(final String value) {
      this.value = value;
    }

    @JsonCreator
    public static LEVEL fromValue(final String text) {
      for (LEVEL b : LEVEL.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }
  }

  private LEVEL level;
  private String message;
  private String description;
  private List<Path> relatedItem;

  public LEVEL getLevel() {
    return level;
  }

  public ValidationEntry setLevel(LEVEL level) {
    this.level = level;
    return this;
  }

  public String getMessage() {
    return message;
  }

  public ValidationEntry setMessage(String message) {
    this.message = message;
    return this;
  }

  public String getDescription() {
    return description;
  }

  public ValidationEntry setDescription(String description) {
    this.description = description;
    return this;
  }

  public List<Path> getRelatedItem() {
    return relatedItem;
  }

  public ValidationEntry setRelatedItem(List<Path> relatedItem) {
    this.relatedItem = relatedItem;
    return this;
  }

  @Override
  public String toString() {
    return "ValidationIssue [level=" + level + ", message=" + message + ", description=" + description
      + ", relatedItem=" + relatedItem + "]";
  }

}
