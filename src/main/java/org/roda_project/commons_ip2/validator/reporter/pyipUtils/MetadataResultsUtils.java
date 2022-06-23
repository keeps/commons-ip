package org.roda_project.commons_ip2.validator.reporter.pyipUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import org.roda_project.commons_ip2.validator.constants.ConstantsAIPspec;
import org.roda_project.commons_ip2.validator.constants.ConstantsCSIPspec;
import org.roda_project.commons_ip2.validator.constants.ConstantsSIPspec;
import org.roda_project.commons_ip2.validator.pyipModel.MetadataChecks;
import org.roda_project.commons_ip2.validator.pyipModel.MetadataStatus;
import org.roda_project.commons_ip2.validator.pyipModel.Severity;
import org.roda_project.commons_ip2.validator.pyipModel.TestResult;
import org.roda_project.commons_ip2.validator.reporter.ReporterDetails;
import org.roda_project.commons_ip2.validator.reporter.RequirementsComparator;

/** {@author João Gomes <jgomes@keep.pt>}. */
public final class MetadataResultsUtils {

  private MetadataResultsUtils() {
    // do nothing
  }

  /**
   * Creates the Schema result to the report.
   *
   * @param results
   *          {@link Map} with the results.
   * @return {@link MetadataChecks}
   */
  public static MetadataChecks createSchemaResult(final Map<String, ReporterDetails> results) {
    final MetadataChecks metadataChecks = new MetadataChecks();
    final ReporterDetails schemaResult = results.get("CSIP0");
    final List<TestResult> testResults = new ArrayList<>();

    if (schemaResult != null && schemaResult.isValid()) {
      metadataChecks.setStatus(MetadataStatus.VALID);
    } else {
      metadataChecks.setStatus(MetadataStatus.NOTVALID);
      testResults.add(createTestResult("CSIP0", schemaResult));
    }
    metadataChecks.setMessages(testResults);

    return metadataChecks;
  }

  /**
   * Creates the other results in the report.
   *
   * @param results
   *          {@link Map} with the results.
   * @return {@link MetadataChecks}
   */
  public static MetadataChecks createSchematronResult(final Map<String, ReporterDetails> results) {
    final Map<String, ReporterDetails> specificationResults = results.entrySet().stream()
      .filter(result -> !result.getKey().startsWith("CSIPSTR") && !result.getKey().equals("CSIP0")
        && !result.getValue().isValid())
      .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    final TreeMap<String, ReporterDetails> sortedSpecificationResults = new TreeMap<>(new RequirementsComparator());
    sortedSpecificationResults.putAll(specificationResults);
    final List<TestResult> testResults = new ArrayList<>();
    for (Map.Entry<String, ReporterDetails> result : sortedSpecificationResults.entrySet()) {
      testResults.add(createTestResult(result.getKey(), result.getValue()));
    }
    final MetadataChecks schematronResuts = new MetadataChecks();
    schematronResuts.setStatus(calculateStatus(specificationResults));
    schematronResuts.setMessages(testResults);
    return schematronResuts;
  }

  private static TestResult createTestResult(final String id, final ReporterDetails reporterDetails) {
    final TestResult testResult = new TestResult();
    testResult.setRuleId(id);
    if (id.startsWith("CSIP")) {
      testResult.setLocation(ConstantsCSIPspec.getSpecificationLocation(id));
    } else if (id.startsWith("SIP")) {
      testResult.setLocation(ConstantsSIPspec.getSpecificationLocation(id));
    } else if (id.startsWith("AIP")) {
      testResult.setLocation(ConstantsAIPspec.getSpecificationLocation(id));
    }
    final StringBuilder message = new StringBuilder();
    if (reporterDetails != null && !reporterDetails.getIssues().isEmpty()) {
      for (String issue : reporterDetails.getIssues()) {
        message.append(issue);
        message.append(" ");
      }
    }
    testResult.message(message.toString());
    Severity severity = null;
    if (reporterDetails != null) {
      if (id.startsWith("CSIP")) {
        severity = calculateSeverity(ConstantsCSIPspec.getSpecificationLevel(id), reporterDetails.isValid());
      } else if (id.startsWith("SIP")) {
        severity = calculateSeverity(ConstantsSIPspec.getSpecificationLevel(id), reporterDetails.isValid());
      } else if (id.startsWith("AIP")) {
        severity = calculateSeverity(ConstantsAIPspec.getSpecificationLevel(id), reporterDetails.isValid());
      }
    }
    testResult.setSeverity(severity);
    return testResult;
  }

  private static Severity calculateSeverity(final String level, final boolean valid) {
    if (!valid) {
      if (level.equals("MUST")) {
        return Severity.ERROR;
      } else if (level.equals("SHOULD")) {
        return Severity.WARN;
      }
      return Severity.INFO;
    }
    return Severity.fromValue("");
  }

  private static MetadataStatus calculateStatus(final Map<String, ReporterDetails> results) {
    final Map<String, ReporterDetails> failedResults = results.entrySet().stream()
      .filter(result -> !result.getValue().isValid()).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    for (Map.Entry<String, ReporterDetails> result : failedResults.entrySet()) {
      String level = null;
      if (result.getKey().startsWith("CSIP")) {
        level = ConstantsCSIPspec.getSpecificationLevel(result.getKey());
      } else if (result.getKey().startsWith("SIP")) {
        level = ConstantsSIPspec.getSpecificationLevel(result.getKey());
      } else if (result.getKey().startsWith("AIP")) {
        level = ConstantsAIPspec.getSpecificationLevel(result.getKey());
      }
      if (level != null && level.equals("MUST")) {
        return MetadataStatus.NOTVALID;
      }
    }
    return MetadataStatus.VALID;
  }
}
