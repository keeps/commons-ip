package org.roda_project.commons_ip2.validator.pyipModel;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * Gets or Sets metadataStatus
 */
public enum MetadataStatus {
  UNKNOWN("Unknown"), NOTVALID("NotValid"), VALID("Valid");

  private String value;

  MetadataStatus(String value) {
    this.value = value;
  }

  @JsonCreator
  public static MetadataStatus fromValue(String text) {
    for (MetadataStatus b : MetadataStatus.values()) {
      if (String.valueOf(b.value).equals(text)) {
        return b;
      }
    }
    return null;
  }

  public String toString() {
    return String.valueOf(value);
  }
}
