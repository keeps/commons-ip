package org.roda_project.commons_ip2.validator.model.pyip;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

import org.roda_project.commons_ip2.validator.constants.Constants;



/** Checksum. */
public class Checksum {
  /**
   * {@link ChecksumAlg}.
   */
  @JsonProperty("algorithm")
  private ChecksumAlg algorithm = null;

  /**
   * the checksum value.
   */
  @JsonProperty("value")
  private String value = null;

  /**
   * Set the checksum algorithm.
   * 
   * @param algorithm
   *          {@link ChecksumAlg}.
   * @return {@link Checksum}
   */
  public Checksum algorithm(final ChecksumAlg algorithm) {
    this.algorithm = algorithm;
    return this;
  }

  /**
   * Get algorithm.
   *
   * @return algorithm.
   */
  public ChecksumAlg getAlgorithm() {
    return algorithm;
  }

  public void setAlgorithm(final ChecksumAlg algorithm) {
    this.algorithm = algorithm;
  }

  /**
   * Set the value of the checksum.
   * 
   * @param value
   *          the value of the checksum.
   * @return {@link Checksum}.
   */
  public Checksum value(final String value) {
    this.value = value;
    return this;
  }

  /**
   * Get value.
   *
   * @return value
   */
  public String getValue() {
    return value;
  }

  public void setValue(final String value) {
    this.value = value;
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    final Checksum checksum = (Checksum) o;
    return Objects.equals(this.algorithm, checksum.algorithm) && Objects.equals(this.value, checksum.value);
  }

  @Override
  public int hashCode() {
    return Objects.hash(algorithm, value);
  }

  @Override
  public String toString() {

      String sb = "class Checksum {\n" +
              "    algorithm: " + toIndentedString(algorithm) + Constants.END_OF_LINE +
              "    value: " + toIndentedString(value) + Constants.END_OF_LINE +
              "}";
    return sb;
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(final Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}
