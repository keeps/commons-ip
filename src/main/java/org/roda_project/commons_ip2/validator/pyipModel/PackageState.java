package org.roda_project.commons_ip2.validator.pyipModel;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/** Gets or Sets packageState. */
public enum PackageState {
  UPLOADED("UPLOADED"),
  CHECKSUMMED("CHECKSUMMED"),
  VALIDATED("VALIDATED");

  private String value;

  PackageState(String value) {
    this.value = value;
  }

  @JsonCreator
  public static PackageState fromValue(String text) {
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
