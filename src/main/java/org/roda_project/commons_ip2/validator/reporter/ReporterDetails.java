package org.roda_project.commons_ip2.validator.reporter;

import java.util.ArrayList;
import java.util.List;

/** {@author João Gomes <jgomes@keep.pt>}. */
public class ReporterDetails {
  private String detail;
  private boolean valid;
  private final List<String> issues;
  private String specification;
  private boolean skipped;
  private int errors;

  /** Empty Constructor of {@link ReporterDetails}. */
  public ReporterDetails() {
    this.detail = "";
    this.valid = true;
    this.issues = new ArrayList<>();
    this.errors = 0;
  }

  /**
   * Constructor {@link ReporterDetails}.
   *
   * @param specification the requirement specification {@link String}.
   * @param issue the issue of the requirement {@link String}
   * @param valid flag if the requirement is valid or not
   * @param skipped flag if the requirement is skipped or not
   */
  public ReporterDetails(String specification, String issue, boolean valid, boolean skipped) {
    this.detail = "";
    this.valid = valid;
    this.issues = new ArrayList<>();
    this.issues.add(issue);
    this.errors = 0;
    this.specification = specification;
    this.skipped = skipped;
  }

  /**
   * Constructor of {@link ReporterDetails}.
   *
   * @param specification the requirement specification {@link String}
   * @param issues {@link List} of all issues of the result
   * @param valid flag if the requirement is valid or not
   * @param skipped flag if the requirement is skipped or not
   */
  public ReporterDetails(
      String specification, List<String> issues, boolean valid, boolean skipped) {
    this.detail = "";
    this.specification = specification;
    this.issues = new ArrayList<>(issues);
    this.errors = 0;
    this.valid = valid;
    this.skipped = skipped;
  }

  /**
   * Copy Constructor {@link ReporterDetails}.
   *
   * @param clone {@link ReporterDetails}
   */
  public ReporterDetails(ReporterDetails clone) {
    this(clone.getSpecification(), clone.getIssues(), clone.isValid(), clone.isSkipped());
  }

  public ReporterDetails clone() {
    return new ReporterDetails(this);
  }

  public String getDetail() {
    return detail;
  }

  public void setDetail(String message) {
    this.detail = message;
  }

  public String getSpecification() {
    return specification;
  }

  public ReporterDetails setSpecification(String specification) {
    this.specification = specification;
    return this;
  }

  public List<String> getIssues() {
    return this.issues;
  }

  public boolean isValid() {
    return valid;
  }

  public void setValid(boolean valid) {
    this.valid = valid;
  }

  public boolean isSkipped() {
    return skipped;
  }

  public void setSkipped(boolean skipped) {
    this.skipped = skipped;
  }

  public void addIssue(String issue) {
    this.issues.add(issue);
  }

  public void addIssues(List<String> issues) {
    this.issues.addAll(issues);
  }
}
