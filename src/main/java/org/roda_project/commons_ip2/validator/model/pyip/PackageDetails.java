package org.roda_project.commons_ip2.validator.model.pyip;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.roda_project.commons_ip2.validator.constants.Constants;

import com.fasterxml.jackson.annotation.JsonProperty;

/** PackageDetails. */
public class PackageDetails {
  /**
   * {@link String}.
   */
  @JsonProperty("name")
  private String name = null;

  /**
   * {@link List} of {@link Checksum}.
   */
  @JsonProperty("checksums")
  private List<Checksum> checksums = null;

  /**
   * Set the name.
   * 
   * @param name
   *          {@link String}.
   * @return {@link PackageDetails}.
   */
  public PackageDetails name(final String name) {
    this.name = name;
    return this;
  }

  /**
   * Get name.
   *
   * @return name
   */
  public String getName() {
    return name;
  }

  public void setName(final String name) {
    this.name = name;
  }

  /**
   * Set the {@link List} of {@link Checksum}.
   * 
   * @param checksums
   *          {@link List} of {@link Checksum}.
   * @return {@link PackageDetails}.
   */
  public PackageDetails checksums(final List<Checksum> checksums) {
    this.checksums = checksums;
    return this;
  }

  /**
   * Add a new {@link Checksum} to the {@link List}.
   * 
   * @param checksumsItem
   *          {@link Checksum}.
   * @return {@link PackageDetails}.
   */
  public PackageDetails addChecksumsItem(final Checksum checksumsItem) {
    if (this.checksums == null) {
      this.checksums = new ArrayList<Checksum>();
    }
    this.checksums.add(checksumsItem);
    return this;
  }

  /**
   * Get checksums.
   *
   * @return checksums
   */
  public List<Checksum> getChecksums() {
    return checksums;
  }

  public void setChecksums(final List<Checksum> checksums) {
    this.checksums = checksums;
  }

  @Override
  public boolean equals(final java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    final PackageDetails packageDetails = (PackageDetails) o;
    return Objects.equals(this.name, packageDetails.name) && Objects.equals(this.checksums, packageDetails.checksums);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, checksums);
  }

  @Override
  public String toString() {

      String sb = "class PackageDetails {\n" +
              "    name: " + toIndentedString(name) + Constants.END_OF_LINE +
              "    checksums: " + toIndentedString(checksums) + Constants.END_OF_LINE +
              "}";
    return sb;
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(final java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}
