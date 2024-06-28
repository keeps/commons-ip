package org.roda_project.commons_ip2.validator.model.pyip;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;
import java.util.UUID;

/** ValidationReport. */
public class ValidationReport {
  @JsonProperty("uid")
  private UUID uid = null;

  @JsonProperty("package")
  private InformationPackage _package = null;

  @JsonProperty("structure")
  private StructResults structure = null;

  @JsonProperty("metadata")
  private MetadataResults metadata = null;

  public ValidationReport uid(UUID uid) {
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

  public ValidationReport _package(InformationPackage _package) {
    this._package = _package;
    return this;
  }

  /**
   * Get _package.
   *
   * @return _package
   */
  public InformationPackage getPackage() {
    return _package;
  }

  public void setPackage(InformationPackage _package) {
    this._package = _package;
  }

  public ValidationReport structure(StructResults structure) {
    this.structure = structure;
    return this;
  }

  /**
   * Get structure.
   *
   * @return structure
   */
  public StructResults getStructure() {
    return structure;
  }

  public void setStructure(StructResults structure) {
    this.structure = structure;
  }

  public ValidationReport metadata(MetadataResults metadata) {
    this.metadata = metadata;
    return this;
  }

  /**
   * Get metadata.
   *
   * @return metadata
   */
  public MetadataResults getMetadata() {
    return metadata;
  }

  public void setMetadata(MetadataResults metadata) {
    this.metadata = metadata;
  }

  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ValidationReport validationReport = (ValidationReport) o;
    return Objects.equals(this.uid, validationReport.uid)
        && Objects.equals(this._package, validationReport._package)
        && Objects.equals(this.structure, validationReport.structure)
        && Objects.equals(this.metadata, validationReport.metadata);
  }

  @Override
  public int hashCode() {
    return Objects.hash(uid, _package, structure, metadata);
  }

  @Override
  public String toString() {

      String sb = "class ValidationReport {\n" +
              "    uid: " + toIndentedString(uid) + "\n" +
              "    _package: " + toIndentedString(_package) + "\n" +
              "    structure: " + toIndentedString(structure) + "\n" +
              "    metadata: " + toIndentedString(metadata) + "\n" +
              "}";
    return sb;
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
