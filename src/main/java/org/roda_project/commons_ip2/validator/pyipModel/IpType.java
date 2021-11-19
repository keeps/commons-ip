package org.roda_project.commons_ip2.validator.pyipModel;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/** Gets or Sets ipType. */
public enum IpType {
  CSIP("CSIP"),
  SIP("SIP"),
  DIP("DIP");

  private String value;

  IpType(String value) {
    this.value = value;
  }

  @JsonCreator
  public static IpType fromValue(String text) {
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
