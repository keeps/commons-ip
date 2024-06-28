package org.roda_project.commons_ip2.validator.model.pyip;

import java.util.Objects;

import org.roda_project.commons_ip2.validator.constants.Constants;

import com.fasterxml.jackson.annotation.JsonProperty;

/** ManifestSummary. */
public class ManifestSummary {
  /**
   * the number of files.
   */
  @JsonProperty("fileCount")
  private Integer fileCount = 0;

  /**
   * the total size.
   */
  @JsonProperty("totalSize")
  private Integer totalSize = null;

  /**
   * Set the fileCount.
   * 
   * @param fileCount
   *          {@link Integer}.
   * @return {@link ManifestSummary}
   */
  public ManifestSummary fileCount(final Integer fileCount) {
    this.fileCount = fileCount;
    return this;
  }

  /**
   * Get fileCount.
   *
   * @return fileCount
   */
  public Integer getFileCount() {
    return fileCount;
  }

  public void setFileCount(final Integer fileCount) {
    this.fileCount = fileCount;
  }

  /**
   * Set the total size.
   * 
   * @param totalSize
   *          {@link Integer}.
   * @return {@link ManifestSummary}.
   */
  public ManifestSummary totalSize(final Integer totalSize) {
    this.totalSize = totalSize;
    return this;
  }

  /**
   * Get totalSize.
   *
   * @return totalSize
   */
  public Integer getTotalSize() {
    return totalSize;
  }

  public void setTotalSize(final Integer totalSize) {
    this.totalSize = totalSize;
  }

  @Override
  public boolean equals(final java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    final ManifestSummary manifestSummary = (ManifestSummary) o;
    return Objects.equals(this.fileCount, manifestSummary.fileCount)
      && Objects.equals(this.totalSize, manifestSummary.totalSize);
  }

  @Override
  public int hashCode() {
    return Objects.hash(fileCount, totalSize);
  }

  @Override
  public String toString() {

      String sb = "class ManifestSummary {\n" +
              "    fileCount: " + toIndentedString(fileCount) + Constants.END_OF_LINE +
              "    totalSize: " + toIndentedString(totalSize) + Constants.END_OF_LINE +
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
