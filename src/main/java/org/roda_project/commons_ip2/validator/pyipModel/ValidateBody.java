package org.roda_project.commons_ip2.validator.pyipModel;

import java.util.Objects;

import org.springframework.core.io.Resource;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * ValidateBody
 */
public class ValidateBody {
  @JsonProperty("sha1")
  private String sha1 = null;

  @JsonProperty("fileName")
  private Resource fileName = null;

  public ValidateBody sha1(String sha1) {
    this.sha1 = sha1;
    return this;
  }

  /**
   * Get sha1
   * 
   * @return sha1
   **/
  public String getSha1() {
    return sha1;
  }

  public void setSha1(String sha1) {
    this.sha1 = sha1;
  }

  public ValidateBody fileName(Resource fileName) {
    this.fileName = fileName;
    return this;
  }

  /**
   * Get fileName
   * 
   * @return fileName
   **/
  public Resource getFileName() {
    return fileName;
  }

  public void setFileName(Resource fileName) {
    this.fileName = fileName;
  }

  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ValidateBody validateBody = (ValidateBody) o;
    return Objects.equals(this.sha1, validateBody.sha1) && Objects.equals(this.fileName, validateBody.fileName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(sha1, fileName);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ValidateBody {\n");

    sb.append("    sha1: ").append(toIndentedString(sha1)).append("\n");
    sb.append("    fileName: ").append(toIndentedString(fileName)).append("\n");
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
