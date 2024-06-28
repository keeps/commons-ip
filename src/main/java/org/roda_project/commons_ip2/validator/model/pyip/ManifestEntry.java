package org.roda_project.commons_ip2.validator.model.pyip;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.roda_project.commons_ip2.validator.constants.Constants;

/** ManifestEntry. */
public class ManifestEntry {
  /**
   * {@link String}.
   */
  @JsonProperty("path")
  private String path = null;

  /**
   * {@link Integer}.
   */
  @JsonProperty("size")
  private Integer size = 0;

  /**
   * {@link List}.
   */
  @JsonProperty("checksums")
  private List<Checksum> checksums = null;

  /**
   * Set the path of {@link ManifestEntry}.
   * 
   * @param path
   *          {@link String}.
   * @return {@link ManifestEntry}
   */
  public ManifestEntry path(final String path) {
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

  public void setPath(final String path) {
    this.path = path;
  }

  public ManifestEntry size(final Integer size) {
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

  public void setSize(final Integer size) {
    this.size = size;
  }

  /**
   * Set the {@link List} of {@link Checksum}.
   * @param checksums
   *        {@link List} of {@link  Checksum}.
   * @return {@link ManifestEntry}.
   */
  public ManifestEntry checksums(final List<Checksum> checksums) {
    this.checksums = checksums;
    return this;
  }

  /**
   * Add a new {@link Checksum} to the {@link List}.
   * @param checksumsItem
   *      {@link Checksum}.
   * @return {@link ManifestEntry}.
   */
  public ManifestEntry addChecksumsItem(final Checksum checksumsItem) {
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
    final ManifestEntry manifestEntry = (ManifestEntry) o;
    return Objects.equals(this.path, manifestEntry.path) && Objects.equals(this.size, manifestEntry.size)
      && Objects.equals(this.checksums, manifestEntry.checksums);
  }

  @Override
  public int hashCode() {
    return Objects.hash(path, size, checksums);
  }

  @Override
  public String toString() {

      String sb = "class ManifestEntry {\n" +
              "    path: " + toIndentedString(path) + Constants.END_OF_LINE +
              "    size: " + toIndentedString(size) + Constants.END_OF_LINE +
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
    return o.toString().replace(Constants.END_OF_LINE, "\n    ");
  }
}
