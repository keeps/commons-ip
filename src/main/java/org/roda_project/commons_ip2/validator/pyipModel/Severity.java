package org.roda_project.commons_ip2.validator.pyipModel;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/** Gets or Sets severity. */
public enum Severity {
  INFO("Info"),
  WARN("Warn"),
  ERROR("Error");

  private String value;

  Severity(String value) {
    this.value = value;
  }

  @JsonCreator
  public static Severity fromValue(String text) {
    for (Severity b : Severity.values()) {
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
