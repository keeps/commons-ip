package org.roda_project.commons_ip2.validator.pyipModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * StructResults
 */
public class StructResults {
  @JsonProperty("status")
  private StructStatus status = null;

  @JsonProperty("messages")
  private List<TestResult> messages = null;

  public StructResults status(StructStatus status) {
    this.status = status;
    return this;
  }

  /**
   * Get status
   * 
   * @return status
   **/
  public StructStatus getStatus() {
    return status;
  }

  public void setStatus(StructStatus status) {
    this.status = status;
  }

  public StructResults messages(List<TestResult> messages) {
    this.messages = messages;
    return this;
  }

  public StructResults addMessagesItem(TestResult messagesItem) {
    if (this.messages == null) {
      this.messages = new ArrayList<TestResult>();
    }
    this.messages.add(messagesItem);
    return this;
  }

  /**
   * Get messages
   * 
   * @return messages
   **/
  public List<TestResult> getMessages() {
    return messages;
  }

  public void setMessages(List<TestResult> messages) {
    this.messages = messages;
  }

  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    StructResults structResults = (StructResults) o;
    return Objects.equals(this.status, structResults.status) && Objects.equals(this.messages, structResults.messages);
  }

  @Override
  public int hashCode() {
    return Objects.hash(status, messages);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class StructResults {\n");

    sb.append("    status: ").append(toIndentedString(status)).append("\n");
    sb.append("    messages: ").append(toIndentedString(messages)).append("\n");
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
