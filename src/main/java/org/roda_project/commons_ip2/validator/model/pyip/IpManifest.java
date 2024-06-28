package org.roda_project.commons_ip2.validator.model.pyip;

import java.util.Objects;
import java.util.UUID;

import org.roda_project.commons_ip2.validator.constants.Constants;

import com.fasterxml.jackson.annotation.JsonProperty;

/** IpManifest. */
public class IpManifest {
  /**
   * {@link UUID}.
   */
  @JsonProperty("uid")
  private UUID uid = null;

  /**
   * {@link Manifest}.
   */
  @JsonProperty("manifest")
  private Manifest manifest = null;

  /**
   * Constructor of {@link IpManifest}.
   * 
   * @param uid
   *          {@link UUID}.
   * @return {@link IpManifest}.
   */
  public IpManifest IpManifest(final UUID uid) {
    this.uid = uid;
    return this;
  }

  /**
   * Get uid.
   *
   * @return uid
   */
  public UUID getUid() {
    return uid;
  }

  public void setUid(final UUID uid) {
    this.uid = uid;
  }

  /**
   * Set the {@link Manifest}.
   * 
   * @param manifest
   *          {@link Manifest}
   * @return {@link IpManifest}.
   */
  public IpManifest manifest(final Manifest manifest) {
    this.manifest = manifest;
    return this;
  }

  /**
   * Get manifest.
   *
   * @return manifest
   */
  public Manifest getManifest() {
    return manifest;
  }

  public void setManifest(final Manifest manifest) {
    this.manifest = manifest;
  }

  @Override
  public boolean equals(final java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    final IpManifest ipManifest = (IpManifest) o;
    return Objects.equals(this.uid, ipManifest.uid) && Objects.equals(this.manifest, ipManifest.manifest);
  }

  @Override
  public int hashCode() {
    return Objects.hash(uid, manifest);
  }

  @Override
  public String toString() {

      String sb = "class IpManifest {\n" +
              "    uid: " + toIndentedString(uid) + Constants.END_OF_LINE +
              "    manifest: " + toIndentedString(manifest) + Constants.END_OF_LINE +
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
    return o.toString().replace(Constants.END_OF_LINE, "\n    ");
  }
}
