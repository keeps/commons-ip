package org.roda_project.commons_ip2.validator.pyipModel;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/** Manifest. */
public class Manifest {
  @JsonProperty("source")
  private String source = null;

  @JsonProperty("summary")
  private ManifestSummary summary = null;

  @JsonProperty("entries")
  private List<ManifestEntry> entries = null;

  public Manifest source(String source) {
    this.source = source;
    return this;
  }

  /**
   * Some class to cover different types of manifest source (archive, filesystem, METS).
   *
   * @return source
   */
  public String getSource() {
    return source;
  }

  public void setSource(String source) {
    this.source = source;
  }

  public Manifest summary(ManifestSummary summary) {
    this.summary = summary;
    return this;
  }

  /**
   * Get summary.
   *
   * @return summary
   */
  public ManifestSummary getSummary() {
    return summary;
  }

  public void setSummary(ManifestSummary summary) {
    this.summary = summary;
  }

  public Manifest entries(List<ManifestEntry> entries) {
    this.entries = entries;
    return this;
  }

  public Manifest addEntriesItem(ManifestEntry entriesItem) {
    if (this.entries == null) {
      this.entries = new ArrayList<ManifestEntry>();
    }
    this.entries.add(entriesItem);
    return this;
  }

  /**
   * Get entries.
   *
   * @return entries
   */
  public List<ManifestEntry> getEntries() {
    return entries;
  }

  public void setEntries(List<ManifestEntry> entries) {
    this.entries = entries;
  }

  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Manifest manifest = (Manifest) o;
    return Objects.equals(this.source, manifest.source)
        && Objects.equals(this.summary, manifest.summary)
        && Objects.equals(this.entries, manifest.entries);
  }

  @Override
  public int hashCode() {
    return Objects.hash(source, summary, entries);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Manifest {\n");

    sb.append("    source: ").append(toIndentedString(source)).append("\n");
    sb.append("    summary: ").append(toIndentedString(summary)).append("\n");
    sb.append("    entries: ").append(toIndentedString(entries)).append("\n");
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
