/**
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE file at the root of the source
 * tree and available online at
 *
 * https://github.com/keeps/commons-ip
 */
package org.roda_project.commons_ip.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ValidationReport {
  private boolean valid;
  private List<ValidationEntry> validations;
  private List<ValidationEntry> issues;
  private Date date;

  public ValidationReport() {
    valid = true;
    validations = new ArrayList<>();
    issues = new ArrayList<>();
    date = new Date();
  }

  public boolean isValid() {
    return valid;
  }

  public void setValid(boolean valid) {
    this.valid = valid;
  }

  public List<ValidationEntry> getValidations() {
    return validations;
  }

  public List<ValidationEntry> getIssues() {
    return issues;
  }

  public Date getDate() {
    return date;
  }

  public void addIssue(ValidationEntry issue) {
    issues.add(issue);
  }

  @Override
  public String toString() {
    return "ValidationReport [valid=" + valid + ", validations=" + validations + ", issues=" + issues + ", date=" + date
      + "]";
  }

}
