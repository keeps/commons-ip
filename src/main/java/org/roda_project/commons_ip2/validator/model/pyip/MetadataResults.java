package org.roda_project.commons_ip2.validator.model.pyip;

import java.util.Objects;

import org.roda_project.commons_ip2.validator.constants.Constants;

import com.fasterxml.jackson.annotation.JsonProperty;

/** MetadataResults. */
public class MetadataResults {
  /**
   * {@link MetadataChecks}.
   */
  @JsonProperty("schemaResults")
  private MetadataChecks schemaResults = null;

  /**
   * {@link MetadataChecks}.
   */
  @JsonProperty("schematronResults")
  private MetadataChecks schematronResults = null;

  /**
   * Set the schema results.
   * 
   * @param schemaResults
   *          {@link MetadataChecks}.
   * @return {@link MetadataResults}.
   */
  public MetadataResults schemaResults(final MetadataChecks schemaResults) {
    this.schemaResults = schemaResults;
    return this;
  }

  /**
   * Get schemaResults.
   *
   * @return schemaResults
   */
  public MetadataChecks getSchemaResults() {
    return schemaResults;
  }

  public void setSchemaResults(final MetadataChecks schemaResults) {
    this.schemaResults = schemaResults;
  }

  /**
   * Set the shematron results.
   * 
   * @param schematronResults
   *          {@link MetadataChecks}.
   * @return {@link MetadataResults}.
   */
  public MetadataResults schematronResults(final MetadataChecks schematronResults) {
    this.schematronResults = schematronResults;
    return this;
  }

  /**
   * Get schematronResults.
   *
   * @return schematronResults
   */
  public MetadataChecks getSchematronResults() {
    return schematronResults;
  }

  public void setSchematronResults(final MetadataChecks schematronResults) {
    this.schematronResults = schematronResults;
  }

  @Override
  public boolean equals(final java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    final MetadataResults metadataResults = (MetadataResults) o;
    return Objects.equals(this.schemaResults, metadataResults.schemaResults)
      && Objects.equals(this.schematronResults, metadataResults.schematronResults);
  }

  @Override
  public int hashCode() {
    return Objects.hash(schemaResults, schematronResults);
  }

  @Override
  public String toString() {

      String sb = "class MetadataResults {\n" +
              "    schemaResults: " + toIndentedString(schemaResults) + Constants.END_OF_LINE +
              "    schematronResults: " + toIndentedString(schematronResults) + Constants.END_OF_LINE +
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
