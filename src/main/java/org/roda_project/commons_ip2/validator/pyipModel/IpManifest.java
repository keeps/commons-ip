package org.roda_project.commons_ip2.validator.pyipModel;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;
import java.util.UUID;

/** IpManifest. */
public class IpManifest {
  @JsonProperty("uid")
  private UUID uid = null;

  @JsonProperty("manifest")
  private Manifest manifest = null;

  public IpManifest uid(UUID uid) {
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

  public void setUid(UUID uid) {
    this.uid = uid;
  }

  public IpManifest manifest(Manifest manifest) {
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

  public void setManifest(Manifest manifest) {
    this.manifest = manifest;
  }

  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    IpManifest ipManifest = (IpManifest) o;
    return Objects.equals(this.uid, ipManifest.uid)
        && Objects.equals(this.manifest, ipManifest.manifest);
  }

  @Override
  public int hashCode() {
    return Objects.hash(uid, manifest);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class IpManifest {\n");

    sb.append("    uid: ").append(toIndentedString(uid)).append("\n");
    sb.append("    manifest: ").append(toIndentedString(manifest)).append("\n");
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
