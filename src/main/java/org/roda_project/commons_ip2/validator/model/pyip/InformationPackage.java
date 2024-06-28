package org.roda_project.commons_ip2.validator.model.pyip;

import java.util.Objects;

import org.roda_project.commons_ip2.validator.constants.Constants;

import com.fasterxml.jackson.annotation.JsonProperty;

/** InformationPackage. */
public class InformationPackage {
  /**
   * {@link PackageDetails}.
   */
  @JsonProperty("details")
  private PackageDetails details = null;

  /**
   * {@link ProfileDetails}.
   */
  @JsonProperty("profile")
  private ProfileDetails profile = null;

  /**
   * {@link Representation}.
   */
  @JsonProperty("representations")
  private Representation representations = null;

  public InformationPackage details(final PackageDetails details) {
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

  public void setDetails(final PackageDetails details) {
    this.details = details;
  }

  public InformationPackage profile(final ProfileDetails profile) {
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

  public void setProfile(final ProfileDetails profile) {
    this.profile = profile;
  }

  public InformationPackage representations(final Representation representations) {
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

  public void setRepresentations(final Representation representations) {
    this.representations = representations;
  }

  @Override
  public boolean equals(final java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    final InformationPackage informationPackage = (InformationPackage) o;
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

      String sb = "class InformationPackage {\n" +
              "    details: " + toIndentedString(details) + Constants.END_OF_LINE +
              "    profile: " + toIndentedString(profile) + Constants.END_OF_LINE +
              "    representations: " + toIndentedString(representations) + Constants.END_OF_LINE +
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
