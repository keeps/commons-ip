package org.roda_project.commons_ip2.validator.pyipModel;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/** PackageDetails. */
public class PackageDetails {
  @JsonProperty("name")
  private String name = null;

  @JsonProperty("checksums")
  private List<Checksum> checksums = null;

  public PackageDetails name(String name) {
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

  public void setName(String name) {
    this.name = name;
  }

  public PackageDetails checksums(List<Checksum> checksums) {
    this.checksums = checksums;
    return this;
  }

  public PackageDetails addChecksumsItem(Checksum checksumsItem) {
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

  public void setChecksums(List<Checksum> checksums) {
    this.checksums = checksums;
  }

  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PackageDetails packageDetails = (PackageDetails) o;
    return Objects.equals(this.name, packageDetails.name)
        && Objects.equals(this.checksums, packageDetails.checksums);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, checksums);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PackageDetails {\n");

    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    checksums: ").append(toIndentedString(checksums)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}
