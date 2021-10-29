package org.roda_project.commons_ip2.validator.pyipModel;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Checksum
 */
public class Checksum {
  @JsonProperty("algorithm")
  private ChecksumAlg algorithm = null;

  @JsonProperty("value")
  private String value = null;

  public Checksum algorithm(ChecksumAlg algorithm) {
    this.algorithm = algorithm;
    return this;
  }

  /**
   * Get algorithm
   * 
   * @return algorithm
   **/

  public ChecksumAlg getAlgorithm() {
    return algorithm;
  }

  public void setAlgorithm(ChecksumAlg algorithm) {
    this.algorithm = algorithm;
  }

  public Checksum value(String value) {
    this.value = value;
    return this;
  }

  /**
   * Get value
   * 
   * @return value
   **/

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }

  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Checksum checksum = (Checksum) o;
    return Objects.equals(this.algorithm, checksum.algorithm) && Objects.equals(this.value, checksum.value);
  }

  @Override
  public int hashCode() {
    return Objects.hash(algorithm, value);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Checksum {\n");

    sb.append("    algorithm: ").append(toIndentedString(algorithm)).append("\n");
    sb.append("    value: ").append(toIndentedString(value)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}
