package org.roda_project.commons_ip2.validator.pyipModel;

import java.util.Objects;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Upload
 */
public class Upload {
  @JsonProperty("uid")
  private UUID uid = null;

  @JsonProperty("state")
  private PackageState state = null;

  @JsonProperty("details")
  private PackageDetails details = null;

  public Upload uid(UUID uid) {
    this.uid = uid;
    return this;
  }

  /**
   * Get uid
   * 
   * @return uid
   **/
  public UUID getUid() {
    return uid;
  }

  public void setUid(UUID uid) {
    this.uid = uid;
  }

  public Upload state(PackageState state) {
    this.state = state;
    return this;
  }

  /**
   * Get state
   * 
   * @return state
   **/
  public PackageState getState() {
    return state;
  }

  public void setState(PackageState state) {
    this.state = state;
  }

  public Upload details(PackageDetails details) {
    this.details = details;
    return this;
  }

  /**
   * Get details
   * 
   * @return details
   **/
  public PackageDetails getDetails() {
    return details;
  }

  public void setDetails(PackageDetails details) {
    this.details = details;
  }

  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Upload upload = (Upload) o;
    return Objects.equals(this.uid, upload.uid) && Objects.equals(this.state, upload.state)
      && Objects.equals(this.details, upload.details);
  }

  @Override
  public int hashCode() {
    return Objects.hash(uid, state, details);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Upload {\n");

    sb.append("    uid: ").append(toIndentedString(uid)).append("\n");
    sb.append("    state: ").append(toIndentedString(state)).append("\n");
    sb.append("    details: ").append(toIndentedString(details)).append("\n");
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
