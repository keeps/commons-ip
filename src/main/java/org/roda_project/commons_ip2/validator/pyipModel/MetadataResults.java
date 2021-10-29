package org.roda_project.commons_ip2.validator.pyipModel;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * MetadataResults
 */
public class MetadataResults {
  @JsonProperty("schemaResults")
  private MetadataChecks schemaResults = null;

  @JsonProperty("schematronResults")
  private MetadataChecks schematronResults = null;

  public MetadataResults schemaResults(MetadataChecks schemaResults) {
    this.schemaResults = schemaResults;
    return this;
  }

  /**
   * Get schemaResults
   * 
   * @return schemaResults
   **/
  public MetadataChecks getSchemaResults() {
    return schemaResults;
  }

  public void setSchemaResults(MetadataChecks schemaResults) {
    this.schemaResults = schemaResults;
  }

  public MetadataResults schematronResults(MetadataChecks schematronResults) {
    this.schematronResults = schematronResults;
    return this;
  }

  /**
   * Get schematronResults
   * 
   * @return schematronResults
   **/
  public MetadataChecks getSchematronResults() {
    return schematronResults;
  }

  public void setSchematronResults(MetadataChecks schematronResults) {
    this.schematronResults = schematronResults;
  }

  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    MetadataResults metadataResults = (MetadataResults) o;
    return Objects.equals(this.schemaResults, metadataResults.schemaResults)
      && Objects.equals(this.schematronResults, metadataResults.schematronResults);
  }

  @Override
  public int hashCode() {
    return Objects.hash(schemaResults, schematronResults);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class MetadataResults {\n");

    sb.append("    schemaResults: ").append(toIndentedString(schemaResults)).append("\n");
    sb.append("    schematronResults: ").append(toIndentedString(schematronResults)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}
