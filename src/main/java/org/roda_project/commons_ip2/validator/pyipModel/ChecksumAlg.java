package org.roda_project.commons_ip2.validator.pyipModel;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Gets or Sets checksumAlg
 */
public enum ChecksumAlg {
  MD5("MD5"), SHA1("SHA1"), SHA256("SHA256"), SHA512("SHA512");

  private String value;

  ChecksumAlg(String value) {
    this.value = value;
  }

  @JsonCreator
  public static ChecksumAlg fromValue(String text) {
    for (ChecksumAlg b : ChecksumAlg.values()) {
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
