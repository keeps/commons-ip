package org.roda_project.commons_ip2.validator.pyipModel;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

/** InformationPackage. */
public class InformationPackage {
  @JsonProperty("details")
  private PackageDetails details = null;

  @JsonProperty("profile")
  private ProfileDetails profile = null;

  @JsonProperty("representations")
  private Representation representations = null;

  public InformationPackage details(PackageDetails details) {
    this.details = details;
    return this;
  }

  /**
   * Get details.
   *
   * @return details
   */
  public PackageDetails getDetails() {
    return details;
  }

  public void setDetails(PackageDetails details) {
    this.details = details;
  }

  public InformationPackage profile(ProfileDetails profile) {
    this.profile = profile;
    return this;
  }

  /**
   * Get profile.
   *
   * @return profile
   */
  public ProfileDetails getProfile() {
    return profile;
  }

  public void setProfile(ProfileDetails profile) {
    this.profile = profile;
  }

  public InformationPackage representations(Representation representations) {
    this.representations = representations;
    return this;
  }

  /**
   * Get representations.
   *
   * @return representations
   */
  public Representation getRepresentations() {
    return representations;
  }

  public void setRepresentations(Representation representations) {
    this.representations = representations;
  }

  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    InformationPackage informationPackage = (InformationPackage) o;
    return Objects.equals(this.details, informationPackage.details)
        && Objects.equals(this.profile, informationPackage.profile)
        && Objects.equals(this.representations, informationPackage.representations);
  }

  @Override
  public int hashCode() {
    return Objects.hash(details, profile, representations);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class InformationPackage {\n");

    sb.append("    details: ").append(toIndentedString(details)).append("\n");
    sb.append("    profile: ").append(toIndentedString(profile)).append("\n");
    sb.append("    representations: ").append(toIndentedString(representations)).append("\n");
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
