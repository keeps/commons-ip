package org.roda_project.commons_ip2.validator.model.pyip;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/** Gets or Sets structStatus. */
public enum StructStatus {
  /**
   * Enum for value Unknown.
   */
  UNKNOWN("Unknown"),
  /**
   * Enum for value NotWellFormed.
   */
  NOTWELLFORMED("NotWellFormed"),
  /**
   * Enum for value WellFormed.
   */
  WELLFORMED("WellFormed");

  /**
   * {@link String}.
   */
  private final String value;

  StructStatus(final String value) {
    this.value = value;
  }

  /**
   * Get the {@link StructStatus} for the {@link String}.
   * 
   * @param text
   *          {@link String}.
   * @return {@link StructStatus}.
   */
  @JsonCreator
  public static StructStatus fromValue(final String text) {
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
