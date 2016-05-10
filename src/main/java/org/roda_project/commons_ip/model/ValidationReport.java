/**
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE file at the root of the source
 * tree and available online at
 *
 * https://github.com/keeps/commons-ip
 */
package org.roda_project.commons_ip.model;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

public class ValidationReport {
  private boolean valid;
  private List<ValidationEntry> entries;
  private Date date;

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

  public String toHtml(boolean showInfo, boolean showWarnings, boolean showError, boolean fullHtml,
    boolean addDefaultCss) {

    StringBuilder sb = new StringBuilder();
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

    // open report
    sb.append(getDivBeginning("report"));

    // is it valid?
    getValidationEntryAttribute(sb, "valid", "Is the package valid?", isValid() ? "yes" : "no");

    // add validation entries
    sb.append(getDivBeginning("entries"));
    for (ValidationEntry validationEntry : entries) {
      if ((validationEntry.getLevel() == ValidationEntry.LEVEL.INFO && showInfo)
        || (validationEntry.getLevel() == ValidationEntry.LEVEL.WARNING && showWarnings)
        || (validationEntry.getLevel() == ValidationEntry.LEVEL.ERROR && showError)) {
        sb.append(getValidationEntryDiv(validationEntry));
      }
    }
    sb.append(getDivEnding());

    // close report
    sb.append(getDivEnding());

    if (fullHtml) {
      // wrap up
      sb.append("</body>");
      sb.append("</html>");
    }
    return sb.toString();
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
    StringBuilder sb = new StringBuilder();
    sb.append("<style>").append(IPConstants.SYSTEM_LINE_SEP);
    sb.append(
      ".valid {border-bottom: 1px solid black; border-left: 2px solid black;; margin-bottom: 10px; padding: 5px;}")
      .append(IPConstants.SYSTEM_LINE_SEP);
    sb.append(".entry {display: block; margin-bottom: 10px;}").append(IPConstants.SYSTEM_LINE_SEP);
    sb.append(".entry div, .valid div {padding-left: 5px; padding-right: 5px;}").append(IPConstants.SYSTEM_LINE_SEP);
    sb.append(".level_info {border-bottom: 1px solid blue; border-left: 2px solid blue;}")
      .append(IPConstants.SYSTEM_LINE_SEP);
    sb.append(".level_warning {border-bottom: 1px solid orange; border-left: 2px solid orange;}")
      .append(IPConstants.SYSTEM_LINE_SEP);
    sb.append(".level_error {border-bottom: 1px solid red; border-left: 2px solid red;}")
      .append(IPConstants.SYSTEM_LINE_SEP);
    sb.append(".entry .label, .valid .label {font-weight: bold;}").append(IPConstants.SYSTEM_LINE_SEP);
    sb.append(".entry .label, .entry .value, .valid .label, .valid .value {display: inline; }")
      .append(IPConstants.SYSTEM_LINE_SEP);
    sb.append("</style>");
    return sb.toString();
  }

}
