package org.roda_project.commons_ip2.validator.reporter.pyipUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import org.roda_project.commons_ip2.validator.constants.ConstantsCSIPspec;
import org.roda_project.commons_ip2.validator.pyipModel.Severity;
import org.roda_project.commons_ip2.validator.pyipModel.StructStatus;
import org.roda_project.commons_ip2.validator.pyipModel.TestResult;
import org.roda_project.commons_ip2.validator.reporter.ReporterDetails;
import org.roda_project.commons_ip2.validator.reporter.RequirementsComparator;

/** {@author João Gomes <jgomes@keep.pt>}. */
public final class StructureResultsUtils {

  private StructureResultsUtils() {
    // do nothing
  }

  /**
   * Calculate if the structure of the IP is valid.
   *
   * @param results
   *          {@link Map} with all validation results.
   * @return {@link StructStatus}
   */
  public static StructStatus calculateStatus(final Map<String, ReporterDetails> results) {
    for (Map.Entry<String, ReporterDetails> result : results.entrySet()) {
      final String strCsip = result.getKey();
      if ((strCsip.equals("CSIPSTR1") || strCsip.equals("CSIPSTR4")) && !result.getValue().isValid()) {
        return StructStatus.NOTWELLFORMED;
      }
    }
    return StructStatus.WELLFORMED;
  }

  /**
   * Create {@link List} with all test results {@link TestResult}.
   *
   * @param results
   *          {@link Map} with all validation results.
   * @return {@link List} of {@link TestResult}
   */
  public static List<TestResult> createStructureResults(final Map<String, ReporterDetails> results) {
    final Map<String, ReporterDetails> structureResults = results.entrySet().stream()
      .filter(result -> result.getKey().startsWith("CSIPSTR") && !result.getValue().isValid())
      .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    final TreeMap<String, ReporterDetails> sortedStructureResults = new TreeMap<>(new RequirementsComparator());
    sortedStructureResults.putAll(structureResults);
    final List<TestResult> testResults = new ArrayList<>();
    for (Map.Entry<String, ReporterDetails> result : sortedStructureResults.entrySet()) {
      testResults.add(createTestResult(result.getKey(), result.getValue()));
    }
    return testResults;
  }

  private static TestResult createTestResult(final String id, final ReporterDetails reporterDetails) {
    final TestResult testResult = new TestResult();
    testResult.setRuleId(id);
    testResult.setLocation(ConstantsCSIPspec.getSpecificationLocation(id));
    final StringBuilder message = new StringBuilder();
    for (String issue : reporterDetails.getIssues()) {
      message.append(issue);
      message.append(" ");
    }
    testResult.message(message.toString());
    testResult.setSeverity(calculateSeverity(ConstantsCSIPspec.getSpecificationLevel(id), reporterDetails.isValid()));
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
}
