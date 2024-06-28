package org.roda_project.commons_ip2.validator.model.pyip;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/** Gets or Sets checksumAlg. */
public enum ChecksumAlg {
  /**
   * MD5 algorithm.
   */
  MD5("MD5"),
  /**
   * SHA1 algorithm.
   */
  SHA1("SHA1"),
  /**
   * SHA256 algorithm.
   */
  SHA256("SHA256"),
  /**
   * SHA512 algorithm.
   */
  SHA512("SHA512");

  /**
   * Value of checksum.
   */
  private final String value;

  ChecksumAlg(final String value) {
    this.value = value;
  }

  /**
   * Get the respective checksumAlg.
   * 
   * @param text
   *          {@link String}.
   * @return {@link ChecksumAlg}
   */
  @JsonCreator
  public static ChecksumAlg fromValue(final String text) {
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
