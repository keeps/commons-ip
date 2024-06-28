package org.roda_project.commons_ip2.validator.model.pyip;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.roda_project.commons_ip2.validator.constants.Constants;

import com.fasterxml.jackson.annotation.JsonProperty;

/** Manifest. */
public class Manifest {
  /**
   * The manifest source.
   */
  @JsonProperty("source")
  private String source = null;

  /**
   * {@link ManifestSummary}.
   */
  @JsonProperty("summary")
  private ManifestSummary summary = null;

  /**
   * {@link List} of {@link ManifestEntry}.
   */
  @JsonProperty("entries")
  private List<ManifestEntry> entries = null;

  /**
   * Set the source of {@link Manifest}.
   * 
   * @param source
   *          {@link String}.
   * @return {@link Manifest}
   */
  public Manifest source(final String source) {
    this.source = source;
    return this;
  }

  /**
   * Some class to cover different types of manifest source (archive, filesystem,
   * METS).
   *
   * @return source
   */
  public String getSource() {
    return source;
  }

  public void setSource(final String source) {
    this.source = source;
  }

  /**
   * Set the {@link ManifestSummary}.
   * 
   * @param summary
   *          {@link ManifestSummary}
   * @return {@link Manifest}
   */
  public Manifest summary(final ManifestSummary summary) {
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

  public void setSummary(final ManifestSummary summary) {
    this.summary = summary;
  }

  /**
   * Set the {@link List} of {@link ManifestEntry}.
   * 
   * @param entries
   *          {@link List} of {@link ManifestEntry}
   * @return {@link Manifest}
   */
  public Manifest entries(final List<ManifestEntry> entries) {
    this.entries = entries;
    return this;
  }

  /**
   * Add entry to the {@link ManifestEntry}.
   * 
   * @param entriesItem
   *          {@link ManifestEntry}.
   * @return {@link Manifest}.
   */
  public Manifest addEntriesItem(final ManifestEntry entriesItem) {
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

  public void setEntries(final List<ManifestEntry> entries) {
    this.entries = entries;
  }

  @Override
  public boolean equals(final java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    final Manifest manifest = (Manifest) o;
    return Objects.equals(this.source, manifest.source) && Objects.equals(this.summary, manifest.summary)
      && Objects.equals(this.entries, manifest.entries);
  }

  @Override
  public int hashCode() {
    return Objects.hash(source, summary, entries);
  }

  @Override
  public String toString() {

      String sb = "class Manifest {\n" +
              "    source: " + toIndentedString(source) + Constants.END_OF_LINE +
              "    summary: " + toIndentedString(summary) + Constants.END_OF_LINE +
              "    entries: " + toIndentedString(entries) + Constants.END_OF_LINE +
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
