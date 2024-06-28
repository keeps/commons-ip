/**
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE file at the root of the source
 * tree and available online at
 *
 * https://github.com/keeps/commons-ip
 */
package org.roda_project.commons_ip2.model;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

public class ValidationReport {
  private boolean valid;
  private final List<ValidationEntry> entries;
  private final Date date;

  public ValidationReport() {
    valid = true;
    entries = new ArrayList<>();
    date = new Date();
  }

  public boolean isValid() {
    return valid;
  }

  public void setValid(boolean valid) {
    this.valid = valid;
  }

  public List<ValidationEntry> getValidationEntries() {
    return entries;
  }

  public Date getDate() {
    return date;
  }

  public void addEntry(ValidationEntry entry) {
    if (entry.getLevel() == ValidationEntry.LEVEL.ERROR) {
      setValid(false);
    }
    this.entries.add(entry);
  }

  @Override
  public String toString() {
    return "ValidationReport [valid=" + valid + ", entries=" + entries + ", date=" + date + "]";
  }

  public String toHtml() {
    return toHtml(true, true, true, true, true);
  }

  public String toHtml(boolean showInfo, boolean showWarn, boolean showError, boolean fullHtml, boolean addDefaultCss) {
    StringBuilder sb = new StringBuilder();

    // start html (if is to do so)
    getHtmlStart(sb, fullHtml, addDefaultCss);

    // open report
    sb.append(getDivBeginning("report"));

    // is it valid?
    getValidationEntryAttribute(sb, "valid", "Is the package valid?", isValid() ? "yes" : "no");

    // add validation entries
    getValidationEntries(sb, showInfo, showWarn, showError);

    // close report
    sb.append(getDivEnding());

    // end html (if is to do so)
    getHtmlEnd(sb, fullHtml);
    return sb.toString();
  }

  private void getHtmlStart(StringBuilder sb, boolean fullHtml, boolean addDefaultCss) {
    if (fullHtml) {
      sb.append("<html>");
      sb.append("<head>");
      sb.append("<title>Validation report (").append(getDate()).append(")</title>");
      sb.append("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />");
      if (addDefaultCss) {
        sb.append(getDefaultCss());
      }
      sb.append("</head>");
      sb.append("<body>");
      sb.append("<h1>Validation report (").append(getDate()).append(")</h1>");
    }
  }

  private void getValidationEntries(StringBuilder sb, boolean showInfo, boolean showWarnings, boolean showError) {
    sb.append(getDivBeginning("entries"));
    for (ValidationEntry validationEntry : entries) {
      if (isToAddEntry(validationEntry, ValidationEntry.LEVEL.INFO, showInfo)
        || isToAddEntry(validationEntry, ValidationEntry.LEVEL.WARN, showWarnings)
        || isToAddEntry(validationEntry, ValidationEntry.LEVEL.ERROR, showError)) {
        sb.append(getValidationEntryDiv(validationEntry));
      }
    }
    sb.append(getDivEnding());
  }

  private boolean isToAddEntry(ValidationEntry validationEntry, ValidationEntry.LEVEL level, boolean isToShow) {
    return validationEntry.getLevel() == level && isToShow;
  }

  private void getHtmlEnd(StringBuilder sb, boolean fullHtml) {
    if (fullHtml) {
      // wrap up
      sb.append("</body>");
      sb.append("</html>");
    }
  }

  private String getDivBeginning(String classString) {
    return String.format("<div class=\"%s\">", classString);
  }

  private String getDivEnding() {
    return "</div>";
  }

  private String getValidationEntryDiv(ValidationEntry validationEntry) {
    StringBuilder sb = new StringBuilder();
    sb.append(getDivBeginning("entry " + "level_" + validationEntry.getLevel().toString().toLowerCase()));

    // level
    getValidationEntryAttribute(sb, "level", "Level", validationEntry.getLevel().toString());

    // related
    getValidationEntryAttribute(sb, "related", "Related files", validationEntry.getRelatedItem());

    // message
    getValidationEntryAttribute(sb, "message", "Message", validationEntry.getMessage());

    // description
    if (StringUtils.isNotBlank(validationEntry.getDescription())) {
      getValidationEntryAttribute(sb, "description", "Description", validationEntry.getDescription());
    }

    sb.append(getDivEnding());
    return sb.toString();
  }

  private void getValidationEntryAttribute(StringBuilder sb, String attrClass, String label, List<Path> values) {
    sb.append(getDivBeginning("entry_attr " + attrClass));
    sb.append(getDivBeginning("label"));
    sb.append(label);
    sb.append(getDivEnding());
    sb.append(getDivBeginning("value"));
    boolean first = true;
    for (Path path : values) {
      if (!first) {
        sb.append(" ; ");
        first = false;
      }
      sb.append(path);
    }
    sb.append(getDivEnding());
    sb.append(getDivEnding());
  }

  private void getValidationEntryAttribute(StringBuilder sb, String attrClass, String label, String value) {
    sb.append(getDivBeginning("entry_attr " + attrClass));
    sb.append(getDivBeginning("label"));
    sb.append(label);
    sb.append(getDivEnding());
    sb.append(getDivBeginning("value"));
    sb.append(value);
    sb.append(getDivEnding());
    sb.append(getDivEnding());
  }

  private String getDefaultCss() {
      String sb = "<style>" + IPConstants.SYSTEM_LINE_SEP +
              ".valid {border-bottom: 1px solid black; border-left: 2px solid black;; margin-bottom: 10px; padding: 5px;}" +
              IPConstants.SYSTEM_LINE_SEP +
              ".entry {display: block; margin-bottom: 10px;}" + IPConstants.SYSTEM_LINE_SEP +
              ".entry div, .valid div {padding-left: 5px; padding-right: 5px;}" + IPConstants.SYSTEM_LINE_SEP +
              ".level_info {border-bottom: 1px solid blue; border-left: 2px solid blue;}" +
              IPConstants.SYSTEM_LINE_SEP +
              ".level_warning {border-bottom: 1px solid orange; border-left: 2px solid orange;}" +
              IPConstants.SYSTEM_LINE_SEP +
              ".level_error {border-bottom: 1px solid red; border-left: 2px solid red;}" +
              IPConstants.SYSTEM_LINE_SEP +
              ".entry .label, .valid .label {font-weight: bold;}" + IPConstants.SYSTEM_LINE_SEP +
              ".entry .label, .entry .value, .valid .label, .valid .value {display: inline; }" +
              IPConstants.SYSTEM_LINE_SEP +
              "</style>";
    return sb;
  }

}
