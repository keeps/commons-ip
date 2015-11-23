package org.eark.validation.model;

import java.util.Date;
import java.util.List;

public class ValidationReport {
  private boolean valid;
  private List<ValidationIssue> issues;
  private Date date;
  public boolean isValid() {
    return valid;
  }
  public void setValid(boolean valid) {
    this.valid = valid;
  }
  public List<ValidationIssue> getIssues() {
    return issues;
  }
  public void setIssues(List<ValidationIssue> issues) {
    this.issues = issues;
  }
  public Date getDate() {
    return date;
  }
  public void setDate(Date date) {
    this.date = date;
  }
  
  
  
  
}
