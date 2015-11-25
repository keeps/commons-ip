/**
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE file at the root of the source
 * tree and available online at
 *
 * https://github.com/keeps/commons-ip
 */
package org.roda_project.commons_ip.validation.utils;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import org.roda_project.commons_ip.validation.model.ValidationIssue;
import org.roda_project.commons_ip.validation.model.ValidationIssue.LEVEL;
import org.roda_project.commons_ip.validation.model.ValidationReport;

public class ValidationUtils {
  public static ValidationReport addIssue(ValidationReport report, String message, LEVEL level, String description,
    List<Path> relatedPath) {
    ValidationIssue issue = new ValidationIssue();
    issue.setDescription(description);
    issue.setLevel(level);
    issue.setMessage(message);
    issue.setRelatedItem(relatedPath == null ? (new ArrayList<Path>()) : relatedPath);
    List<ValidationIssue> issues = report.getIssues();
    if (issues == null) {
      issues = new ArrayList<ValidationIssue>();
    }
    issues.add(issue);
    report.setIssues(issues);
    if (level.toString().equalsIgnoreCase(ValidationIssue.LEVEL.ERROR.toString())) {
      report.setValid(false);
    }
    return report;
  }
}
