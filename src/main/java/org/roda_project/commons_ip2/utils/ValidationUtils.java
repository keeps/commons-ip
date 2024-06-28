/**
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE file at the root of the source
 * tree and available online at
 *
 * https://github.com/keeps/commons-ip
 */
package org.roda_project.commons_ip2.utils;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.roda_project.commons_ip2.mets_v1_12.beans.DivType;
import org.roda_project.commons_ip2.mets_v1_12.beans.FileType;
import org.roda_project.commons_ip2.mets_v1_12.beans.StructMapType;
import org.roda_project.commons_ip2.model.ValidationEntry;
import org.roda_project.commons_ip2.model.ValidationEntry.LEVEL;
import org.roda_project.commons_ip2.model.ValidationReport;

public final class ValidationUtils {
  private static final String UNKNOWN_ID = "UNKNOWN_ID";

  private ValidationUtils() {
  }

  public static ValidationReport addInfo(ValidationReport report, String message, StructMapType structMap, Path ipPath,
    Path relatedFilePath) {
    return addInfo(report, message, getDescription(structMap), ipPath, relatedFilePath);
  }

  public static ValidationReport addInfo(ValidationReport report, String message, DivType div, Path ipPath,
    Path relatedFilePath) {
    return addInfo(report, message, getDescription(div), ipPath, relatedFilePath);
  }

  public static ValidationReport addInfo(ValidationReport report, String message, Path ipPath, Path relatedPath) {
    return addInfo(report, message, "", ipPath, relatedPath);
  }

  private static ValidationReport addInfo(ValidationReport report, String message, String description, Path ipPath,
    Path relatedFilePath) {
    ValidationEntry validation = new ValidationEntry();
    validation.setDescription(description);
    validation.setLevel(LEVEL.INFO);
    validation.setMessage(message);
    validation.setRelatedItem(
      relatedFilePath == null ? (new ArrayList<Path>()) : List.of(ipPath.relativize(relatedFilePath)));
    report.getValidationEntries().add(validation);
    return report;
  }

  public static ValidationReport addIssue(ValidationReport report, String message, LEVEL level, StructMapType structMap,
    Path ipPath, Path relatedFilePath) {
    return addEntry(report, message, level, getDescription(structMap), ipPath, relatedFilePath);
  }

  public static ValidationReport addIssue(ValidationReport report, String message, LEVEL level, DivType div,
    Path ipPath, Path relatedFilePath) {
    return addEntry(report, message, level, getDescription(div), ipPath, relatedFilePath);
  }

  public static ValidationReport addIssue(ValidationReport report, String message, LEVEL level, FileType fptr,
    Path ipPath, Path relatedFilePath) {
    return addEntry(report, message, level, getDescription(fptr), ipPath, relatedFilePath);
  }

  public static ValidationReport addIssue(ValidationReport report, String message, LEVEL level, Path ipPath,
    Path relatedFilePath) {
    return addEntry(report, message, level, "", ipPath, relatedFilePath);
  }

  public static ValidationReport addIssue(ValidationReport report, String message, LEVEL level, Exception exception,
    Path ipPath, Path relatedFilePath) {
    return addEntry(report, message, level, getDescription(exception), ipPath, relatedFilePath);
  }

  public static ValidationReport addIssue(ValidationReport report, String message, LEVEL level, String metsElementId,
    String metsChecksum, String metsChecksumAlgorithm, String computedChecksum, Path ipPath, Path relatedFilePath) {
    return addEntry(report, message, level,
      getDescription(metsElementId, metsChecksum, metsChecksumAlgorithm, computedChecksum), ipPath, relatedFilePath);
  }

  public static ValidationReport addIssue(ValidationReport report, String message, LEVEL level, String metsElementId) {
    return addEntry(report, message, level, getDescription(metsElementId), null, null);
  }

  public static ValidationReport addEntry(ValidationReport report, String message, LEVEL level, String description,
    Path ipPath, Path relatedFilePath) {
    ValidationEntry entry = new ValidationEntry();
    entry.setDescription(description);
    entry.setLevel(level);
    entry.setMessage(message);
    entry.setRelatedItem(
      relatedFilePath == null ? (new ArrayList<Path>()) : List.of(ipPath.relativize(relatedFilePath)));
    report.addEntry(entry);
    return report;
  }

  private static String getDescription(StructMapType structMap) {
    return String.format("structMap with id '%s'", structMap != null ? structMap.getID() : UNKNOWN_ID);
  }

  private static String getDescription(DivType div) {
    return String.format("div with id '%s'", div != null ? div.getID() : UNKNOWN_ID);
  }

  private static String getDescription(FileType file) {
    return String.format("file with id '%s'", file != null ? file.getID() : UNKNOWN_ID);
  }

  private static String getDescription(Exception exception) {
    String message = exception.getMessage();
    if (message == null) {
      message = exception.toString();
    }
    return message;
  }

  private static String getDescription(String metsElementId) {
    return String.format("METS element with id '%s'", metsElementId);
  }

  private static String getDescription(String metsElementId, String metsChecksum, String metsChecksumAlgorithm,
    String computedChecksum) {
    return String.format(
      "METS element with id '%s', METS checksum '%s', METS checksum algorithm '%s', computed checksum '%s'",
      metsElementId, metsChecksum, metsChecksumAlgorithm, computedChecksum);
  }
}
