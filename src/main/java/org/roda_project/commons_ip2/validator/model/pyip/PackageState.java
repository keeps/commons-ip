package org.roda_project.commons_ip2.validator.model.pyip;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/** Gets or Sets packageState. */
public enum PackageState {
  /**
   * {@link Enum} UPLOADED.
   */
  UPLOADED("UPLOADED"),
  /**
   * {@link Enum} CHECKSUMMED.
   */
  CHECKSUMMED("CHECKSUMMED"),
  /**
   * {@link Enum} VALIDATED.
   */
  VALIDATED("VALIDATED");

  /**
   * The {@link String}.
   */
  private final String value;

  PackageState(final String value) {
    this.value = value;
  }

  /**
   * Gets the {@link PackageState} from the {@link String}.
   * 
   * @param text
   *          {@link String}.
   * @return {@link PackageState}.
   */
  @JsonCreator
  public static PackageState fromValue(final String text) {
    for (PackageState b : PackageState.values()) {
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
