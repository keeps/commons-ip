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

/**
 * @author João Gomes <jgomes@keep.pt>
 */
public class StructureResultsUtils {

  public static StructStatus calculateStatus(Map<String, ReporterDetails> results) {
    for (Map.Entry<String, ReporterDetails> result : results.entrySet()) {
      String strCsip = result.getKey();
      if ((strCsip.equals("CSIPSTR1") || strCsip.equals("CSIPSTR4")) && !result.getValue().isValid()) {
        return StructStatus.NOTWELLFORMED;
      }
    }
    return StructStatus.WELLFORMED;
  }

  public static List<TestResult> createStructureResults(Map<String, ReporterDetails> results) {
    Map<String, ReporterDetails> structureResults = results.entrySet().stream()
      .filter(result -> result.getKey().startsWith("CSIPSTR") && !result.getValue().isValid())
      .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    TreeMap<String,ReporterDetails> sortedStructureResults = new TreeMap<>(new RequirementsComparator());
    sortedStructureResults.putAll(structureResults);
    List<TestResult> testResults = new ArrayList<>();
    for (Map.Entry<String, ReporterDetails> result : sortedStructureResults.entrySet()) {
      testResults.add(createTestResult(result.getKey(), result.getValue()));
    }
    return testResults;
  }

  private static TestResult createTestResult(String id, ReporterDetails reporterDetails) {
    TestResult testResult = new TestResult();
    testResult.setRuleId(id);
    testResult.setLocation(ConstantsCSIPspec.getSpecificationLocation(id));
    StringBuilder message = new StringBuilder();
    for (String issue : reporterDetails.getIssues()) {
      message.append(issue);
      message.append(" ");
    }
    testResult.message(message.toString());
    testResult.setSeverity(calculateSeverity(ConstantsCSIPspec.getSpecificationLevel(id), reporterDetails.isValid()));
    return testResult;
  }

  private static Severity calculateSeverity(String level, boolean valid) {
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
