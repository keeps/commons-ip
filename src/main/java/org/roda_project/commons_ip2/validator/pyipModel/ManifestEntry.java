package org.roda_project.commons_ip2.validator.pyipModel;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/** ManifestEntry. */
public class ManifestEntry {
  @JsonProperty("path")
  private String path = null;

  @JsonProperty("size")
  private Integer size = 0;

  @JsonProperty("checksums")
  private List<Checksum> checksums = null;

  public ManifestEntry path(String path) {
    this.path = path;
    return this;
  }

  /**
   * Get path.
   *
   * @return path
   */
  public String getPath() {
    return path;
  }

  public void setPath(String path) {
    this.path = path;
  }

  public ManifestEntry size(Integer size) {
    this.size = size;
    return this;
  }

  /**
   * Get size.
   *
   * @return size
   */
  public Integer getSize() {
    return size;
  }

  public void setSize(Integer size) {
    this.size = size;
  }

  public ManifestEntry checksums(List<Checksum> checksums) {
    this.checksums = checksums;
    return this;
  }

  public ManifestEntry addChecksumsItem(Checksum checksumsItem) {
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
    ManifestEntry manifestEntry = (ManifestEntry) o;
    return Objects.equals(this.path, manifestEntry.path)
        && Objects.equals(this.size, manifestEntry.size)
        && Objects.equals(this.checksums, manifestEntry.checksums);
  }

  @Override
  public int hashCode() {
    return Objects.hash(path, size, checksums);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ManifestEntry {\n");

    sb.append("    path: ").append(toIndentedString(path)).append("\n");
    sb.append("    size: ").append(toIndentedString(size)).append("\n");
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
