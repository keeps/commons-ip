package org.roda_project.commons_ip2.validator.model.pyip;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

/** TestResult. */
public class TestResult {
  @JsonProperty("ruleId")
  private String ruleId = null;

  @JsonProperty("location")
  private String location = null;

  @JsonProperty("message")
  private String message = null;

  @JsonProperty("severity")
  private Severity severity = null;

  public TestResult ruleId(String ruleId) {
    this.ruleId = ruleId;
    return this;
  }

  /**
   * Get ruleId.
   *
   * @return ruleId
   */
  public String getRuleId() {
    return ruleId;
  }

  public void setRuleId(String ruleId) {
    this.ruleId = ruleId;
  }

  public TestResult location(String location) {
    this.location = location;
    return this;
  }

  /**
   * Get location.
   *
   * @return location
   */
  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  public TestResult message(String message) {
    this.message = message;
    return this;
  }

  /**
   * Get message.
   *
   * @return message
   */
  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public TestResult severity(Severity severity) {
    this.severity = severity;
    return this;
  }

  /**
   * Get severity.
   *
   * @return severity
   */
  public Severity getSeverity() {
    return severity;
  }

  public void setSeverity(Severity severity) {
    this.severity = severity;
  }

  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TestResult testResult = (TestResult) o;
    return Objects.equals(this.ruleId, testResult.ruleId)
        && Objects.equals(this.location, testResult.location)
        && Objects.equals(this.message, testResult.message)
        && Objects.equals(this.severity, testResult.severity);
  }

  @Override
  public int hashCode() {
    return Objects.hash(ruleId, location, message, severity);
  }

  @Override
  public String toString() {

      String sb = "class TestResult {\n" +
              "    ruleId: " + toIndentedString(ruleId) + "\n" +
              "    location: " + toIndentedString(location) + "\n" +
              "    message: " + toIndentedString(message) + "\n" +
              "    severity: " + toIndentedString(severity) + "\n" +
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
