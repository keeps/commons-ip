package org.roda_project.commons_ip2.validator.model.pyip;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/** Gets or Sets ipType. */
public enum IpType {
  /**
   * IP type CSIP.
   */
  CSIP("CSIP"),

  /**
   * IP type SIP.
   */
  SIP("SIP"),

  /**
   * IP type DIP.
   */
  DIP("DIP");

  /**
   * the value of IpType.
   */
  private final String value;

  IpType(final String value) {
    this.value = value;
  }

  /**
   * Get the enum value from a string.
   * 
   * @param text
   *          {@link String}.
   * @return {@link IpType}.
   */
  @JsonCreator
  public static IpType fromValue(final String text) {
    for (IpType b : IpType.values()) {
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
