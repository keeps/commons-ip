package org.roda_project.commons_ip2.validator.model.pyip;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.roda_project.commons_ip2.validator.constants.Constants;

import com.fasterxml.jackson.annotation.JsonProperty;

/** MetadataChecks. */
public class MetadataChecks {
  /**
   * {@link MetadataChecks}.
   */
  @JsonProperty("status")
  private MetadataStatus status = null;

  /**
   * {@link List} of {@link TestResult}.
   */
  @JsonProperty("messages")
  private List<TestResult> messages = null;

  /**
   * Set the {@link MetadataStatus}.
   * 
   * @param status
   *          {@link MetadataStatus}.
   * @return {@link MetadataChecks}.
   */
  public MetadataChecks status(final MetadataStatus status) {
    this.status = status;
    return this;
  }

  /**
   * Get status.
   *
   * @return status
   */
  public MetadataStatus getStatus() {
    return status;
  }

  public void setStatus(final MetadataStatus status) {
    this.status = status;
  }

  /**
   * Set the {@link List} of {@link TestResult}.
   * 
   * @param messages
   *          {@link List} of {@link TestResult}.
   * @return {@link MetadataChecks}.
   */
  public MetadataChecks messages(final List<TestResult> messages) {
    this.messages = messages;
    return this;
  }

  /**
   * Add a new {@link TestResult} to the {@link List}.
   * 
   * @param messagesItem
   *          {@link TestResult}.
   * @return {@link MetadataChecks}.
   */
  public MetadataChecks addMessagesItem(final TestResult messagesItem) {
    if (this.messages == null) {
      this.messages = new ArrayList<TestResult>();
    }
    this.messages.add(messagesItem);
    return this;
  }

  /**
   * Get messages.
   *
   * @return messages
   */
  public List<TestResult> getMessages() {
    return messages;
  }

  public void setMessages(final List<TestResult> messages) {
    this.messages = messages;
  }

  @Override
  public boolean equals(final java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    final MetadataChecks metadataChecks = (MetadataChecks) o;
    return Objects.equals(this.status, metadataChecks.status) && Objects.equals(this.messages, metadataChecks.messages);
  }

  @Override
  public int hashCode() {
    return Objects.hash(status, messages);
  }

  @Override
  public String toString() {

      String sb = "class MetadataChecks {\n" +
              "    status: " + toIndentedString(status) + Constants.END_OF_LINE +
              "    messages: " + toIndentedString(messages) + Constants.END_OF_LINE +
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
    return o.toString().replace("\n", "\n    ");
  }
}
