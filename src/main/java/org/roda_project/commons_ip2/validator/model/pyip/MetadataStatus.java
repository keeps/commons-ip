package org.roda_project.commons_ip2.validator.model.pyip;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * Gets or Sets metadataStatus.
 */
public enum MetadataStatus {
  /**
   * Enum Unknown.
   */
  UNKNOWN("Unknown"),
  /**
   * Enum NotValid.
   */
  NOTVALID("NotValid"),
  /**
   * Enum Valid.
   */
  VALID("Valid");

  /**
   * {@link String}.
   */
  private final String value;

  MetadataStatus(final String value) {
    this.value = value;
  }

  /**
   * Get the {@link MetadataStatus} from the {@link String}.
   * 
   * @param text
   *          {@link String}.
   * @return {@link MetadataStatus}.
   */
  @JsonCreator
  public static MetadataStatus fromValue(final String text) {
    for (MetadataStatus b : MetadataStatus.values()) {
      if (String.valueOf(b.value).equals(text)) {
        return b;
      }
    }
    return null;
  }

  /**
   * Convert enum to {@link String}.
   * 
   * @return {@link String}.
   */
  public String toString() {
    return String.valueOf(value);
  }
}
