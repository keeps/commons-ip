package org.roda_project.commons_ip2.validator.pyipModel;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

/** ManifestSummary. */
public class ManifestSummary {
  @JsonProperty("fileCount")
  private Integer fileCount = 0;

  @JsonProperty("totalSize")
  private Integer totalSize = null;

  public ManifestSummary fileCount(Integer fileCount) {
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

  public void setFileCount(Integer fileCount) {
    this.fileCount = fileCount;
  }

  public ManifestSummary totalSize(Integer totalSize) {
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

  public void setTotalSize(Integer totalSize) {
    this.totalSize = totalSize;
  }

  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ManifestSummary manifestSummary = (ManifestSummary) o;
    return Objects.equals(this.fileCount, manifestSummary.fileCount)
        && Objects.equals(this.totalSize, manifestSummary.totalSize);
  }

  @Override
  public int hashCode() {
    return Objects.hash(fileCount, totalSize);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ManifestSummary {\n");

    sb.append("    fileCount: ").append(toIndentedString(fileCount)).append("\n");
    sb.append("    totalSize: ").append(toIndentedString(totalSize)).append("\n");
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
