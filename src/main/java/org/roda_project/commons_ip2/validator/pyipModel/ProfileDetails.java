package org.roda_project.commons_ip2.validator.pyipModel;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * ProfileDetails
 */
public class ProfileDetails {
  @JsonProperty("type")
  private IpType type = null;

  @JsonProperty("name")
  private String name = null;

  @JsonProperty("version")
  private String version = null;

  public ProfileDetails type(IpType type) {
    this.type = type;
    return this;
  }

  /**
   * Get type
   * 
   * @return type
   **/
  public IpType getType() {
    return type;
  }

  public void setType(IpType type) {
    this.type = type;
  }

  public ProfileDetails name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Get name
   * 
   * @return name
   **/
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public ProfileDetails version(String version) {
    this.version = version;
    return this;
  }

  /**
   * Get version
   * 
   * @return version
   **/
  public String getVersion() {
    return version;
  }

  public void setVersion(String version) {
    this.version = version;
  }

  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ProfileDetails profileDetails = (ProfileDetails) o;
    return Objects.equals(this.type, profileDetails.type) && Objects.equals(this.name, profileDetails.name)
      && Objects.equals(this.version, profileDetails.version);
  }

  @Override
  public int hashCode() {
    return Objects.hash(type, name, version);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ProfileDetails {\n");

    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    version: ").append(toIndentedString(version)).append("\n");
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
