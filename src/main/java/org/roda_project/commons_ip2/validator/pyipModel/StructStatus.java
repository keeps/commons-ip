package org.roda_project.commons_ip2.validator.pyipModel;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Gets or Sets structStatus
 */
public enum StructStatus {
  UNKNOWN("Unknown"), NOTWELLFORMED("NotWellFormed"), WELLFORMED("WellFormed");

  private String value;

  StructStatus(String value) {
    this.value = value;
  }

  @JsonCreator
  public static StructStatus fromValue(String text) {
    for (StructStatus b : StructStatus.values()) {
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
