package org.eark.validation.utils;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import org.eark.validation.model.ValidationIssue;
import org.eark.validation.model.ValidationReport;
import org.eark.validation.model.ValidationIssue.LEVEL;

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
		if(level.toString().equalsIgnoreCase(ValidationIssue.LEVEL.ERROR.toString())){
			report.setValid(false);
		}
		return report;
	}
}
