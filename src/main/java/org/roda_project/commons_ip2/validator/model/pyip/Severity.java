package org.roda_project.commons_ip2.validator.model.pyip;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/** Gets or Sets severity. */
public enum Severity {
  /**
   * {@link Enum} with the value Info.
   */
  INFO("Info"),
  /**
   * {@link Enum} with the value Warn.
   */
  WARN("Warn"),
  /**
   * {@link Enum} with the value Error.
   */
  ERROR("Error");

  /**
   * {@link String}.
   */
  private final String value;

  Severity(final String value) {
    this.value = value;
  }

  /**
   * Get the {@link Severity} for the {@link String}.
   * 
   * @param text
   *          {@link String}.
   * @return {@link Severity}.
   */
  @JsonCreator
  public static Severity fromValue(final String text) {
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
