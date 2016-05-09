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
    StringBuilder sb = new StringBuilder();
    sb.append("<html>");
    sb.append("<head>");
    sb.append("<title>Validation report (").append(getDate()).append(")</title>");
    sb.append("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />");
    sb.append(getDefaultCss());
    sb.append("</head>");
    sb.append("<body>");
    sb.append("<h1>Validation report (").append(getDate()).append(")</h1>");
    // is it valid?
    sb.append(getDivBeginning("section summary"));
    sb.append("Is the package valid? ").append(isValid() ? "yes" : "no");
    sb.append(getDivEnding());

    // print validation entries
    sb.append(getDivBeginning("section validation_entries"));
    for (ValidationEntry validationEntry : entries) {
      sb.append(getValidationEntryDiv(validationEntry));
    }
    sb.append(getDivEnding());

    // wrap up
    sb.append("</body>");
    sb.append("</html>");
    return sb.toString();
  }

  private String getDivBeginning(String classString) {
    return String.format("<div class=\"%s\">", classString);
  }

  private String getSpanBeginning(String classString) {
    return String.format("<span class=\"%s\">", classString);
  }

  private String getDivEnding() {
    return "</div>";
  }

  private String getSpanEnding() {
    return "</span>";
  }

  private String getValidationEntryDiv(ValidationEntry validationEntry) {
    StringBuilder sb = new StringBuilder();
    sb.append(getSpanBeginning("issue " + validationEntry.getLevel().toString().toLowerCase()));

    // level
    sb.append(getSpanBeginning("level label"));
    sb.append("Level");
    sb.append(getSpanEnding());
    sb.append(getSpanBeginning("level value"));
    sb.append(validationEntry.getLevel().toString());
    sb.append(getSpanEnding());

    // related
    sb.append(getSpanBeginning("level label"));
    sb.append("Related files");
    sb.append(getSpanEnding());
    sb.append(getSpanBeginning("related value"));
    boolean first = true;
    for (Path path : validationEntry.getRelatedItem()) {
      if (!first) {
        sb.append(" ; ");
        first = false;
      }
      sb.append(path);
    }
    sb.append(getSpanEnding());
    sb.append(getSpanBeginning("break"));
    sb.append(getSpanEnding());

    // message
    sb.append(getSpanBeginning("level label"));
    sb.append("Message");
    sb.append(getSpanEnding());
    sb.append(getSpanBeginning("message value"));
    sb.append(validationEntry.getMessage());
    sb.append(getSpanEnding());
    sb.append(getSpanBeginning("break"));
    sb.append(getSpanEnding());

    // description
    if (StringUtils.isNotBlank(validationEntry.getDescription())) {
      sb.append(getSpanBeginning("level label"));
      sb.append("Description");
      sb.append(getSpanEnding());
      sb.append(getSpanBeginning("description value"));
      sb.append(validationEntry.getDescription());
      sb.append(getSpanEnding());
      sb.append(getSpanBeginning("break"));
      sb.append(getSpanEnding());
    }

    sb.append(getSpanEnding());
    return sb.toString();
  }

  private String getDefaultCss() {
    StringBuilder sb = new StringBuilder();
    sb.append("<style>").append(IPConstants.SYSTEM_LINE_SEP);
    sb.append(
      ".summary {border-bottom: 1px solid black; border-left: 2px solid black;; margin-bottom: 10px; padding: 5px;}")
      .append(IPConstants.SYSTEM_LINE_SEP);
    sb.append(".issue {display: block; margin-bottom: 10px;}").append(IPConstants.SYSTEM_LINE_SEP);
    sb.append(".issue span {padding-left: 5px; padding-right: 5px;}").append(IPConstants.SYSTEM_LINE_SEP);
    sb.append(".info {border-bottom: 1px solid blue; border-left: 2px solid blue;}")
      .append(IPConstants.SYSTEM_LINE_SEP);
    sb.append(".warning {border-bottom: 1px solid orange; border-left: 2px solid orange;}")
      .append(IPConstants.SYSTEM_LINE_SEP);
    sb.append(".error {border-bottom: 1px solid red; border-left: 2px solid red;}").append(IPConstants.SYSTEM_LINE_SEP);
    sb.append(".issue .label {display: inline; font-weight: bold;}").append(IPConstants.SYSTEM_LINE_SEP);
    sb.append(".break {display: block; height: 1px; width: 100%;}").append(IPConstants.SYSTEM_LINE_SEP);
    sb.append("</style>");
    return sb.toString();
  }

}
