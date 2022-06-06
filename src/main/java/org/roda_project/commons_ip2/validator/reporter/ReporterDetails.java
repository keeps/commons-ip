package org.roda_project.commons_ip2.validator.reporter;

import java.util.ArrayList;
import java.util.List;

/** {@author João Gomes <jgomes@keep.pt>}. */
public class ReporterDetails {
  /**
   * {@link String}.
   */
  private String detail;
  /**
   * the flag if is valid or not.
   */
  private boolean valid;
  /**
   * {@link List}.
   */
  private final List<String> issues;
  /**
   * The specification.
   */
  private String specification;
  /**
   * flag if was skipped or not.
   */
  private boolean skipped;

  /** Empty Constructor of {@link ReporterDetails}. */
  public ReporterDetails() {
    this.detail = "";
    this.valid = true;
    this.issues = new ArrayList<>();
  }

  /**
   * Constructor {@link ReporterDetails}.
   *
   * @param specification
   *          the requirement specification {@link String}.
   * @param issue
   *          the issue of the requirement {@link String}
   * @param valid
   *          flag if the requirement is valid or not
   * @param skipped
   *          flag if the requirement is skipped or not
   */
  public ReporterDetails(final String specification, final String issue, final boolean valid, final boolean skipped) {
    this.detail = "";
    this.valid = valid;
    this.issues = new ArrayList<>();
    this.issues.add(issue);
    this.specification = specification;
    this.skipped = skipped;
  }

  /**
   * Constructor of {@link ReporterDetails}.
   *
   * @param specification
   *          the requirement specification {@link String}
   * @param issues
   *          {@link List} of all issues of the result
   * @param valid
   *          flag if the requirement is valid or not
   * @param skipped
   *          flag if the requirement is skipped or not
   */
  public ReporterDetails(final String specification, final List<String> issues, final boolean valid,
    final boolean skipped) {
    this.detail = "";
    this.specification = specification;
    this.issues = new ArrayList<>(issues);
    this.valid = valid;
    this.skipped = skipped;
  }

  /**
   * Copy Constructor {@link ReporterDetails}.
   *
   * @param clone
   *          {@link ReporterDetails}
   */
  public ReporterDetails(final ReporterDetails clone) {
    this(clone.getSpecification(), clone.getIssues(), clone.isValid(), clone.isSkipped());
  }

  /**
   * Clone this constructor.
   * 
   * @return {@link ReporterDetails}.
   */
  public ReporterDetails clone() {
    return new ReporterDetails(this);
  }

  public String getDetail() {
    return detail;
  }

  public void setDetail(final String message) {
    this.detail = message;
  }

  public String getSpecification() {
    return specification;
  }

  /**
   * Sets the specification.
   * 
   * @param specification
   *          {@link String}.
   * @return {@link ReporterDetails}.
   */
  public ReporterDetails setSpecification(final String specification) {
    this.specification = specification;
    return this;
  }

  public List<String> getIssues() {
    return this.issues;
  }

  public boolean isValid() {
    return valid;
  }

  public void setValid(final boolean valid) {
    this.valid = valid;
  }

  public boolean isSkipped() {
    return skipped;
  }

  public void setSkipped(final boolean skipped) {
    this.skipped = skipped;
  }

  /**
   * Adds a new issue to the {@link List}.
   * 
   * @param issue
   *          {@link String}.
   */
  public void addIssue(final String issue) {
    this.issues.add(issue);
  }

  /**
   * Adds a {@link List} of issues.
   * 
   * @param issues
   *          {@link List}.
   */
  public void addIssues(final List<String> issues) {
    this.issues.addAll(issues);
  }
}
