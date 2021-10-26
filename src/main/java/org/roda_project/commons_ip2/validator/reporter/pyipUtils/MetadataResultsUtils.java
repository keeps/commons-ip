package org.roda_project.commons_ip2.validator.reporter.pyipUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.roda_project.commons_ip2.validator.constants.ConstantsAIPspec;
import org.roda_project.commons_ip2.validator.constants.ConstantsCSIPspec;
import org.roda_project.commons_ip2.validator.constants.ConstantsSIPspec;
import org.roda_project.commons_ip2.validator.pyipModel.MetadataChecks;
import org.roda_project.commons_ip2.validator.pyipModel.MetadataStatus;
import org.roda_project.commons_ip2.validator.pyipModel.Severity;
import org.roda_project.commons_ip2.validator.pyipModel.TestResult;
import org.roda_project.commons_ip2.validator.reporter.ReporterDetails;

/**
 * @author João Gomes <jgomes@keep.pt>
 */
public class MetadataResultsUtils {

  public static MetadataChecks createSchemaResult(Map<String, ReporterDetails> results) {
    MetadataChecks metadataChecks = new MetadataChecks();
    ReporterDetails schemaResult = results.get("CSIP0");
    List<TestResult> testResults = new ArrayList<>();
    if (schemaResult.isValid()) {
      metadataChecks.setStatus(MetadataStatus.VALID);
    } else {
      metadataChecks.setStatus(MetadataStatus.NOTVALID);
      testResults.add(createTestResult("CSIP0", schemaResult));
    }
    metadataChecks.setMessages(testResults);

    return metadataChecks;
  }

  public static MetadataChecks createSchematronResult(Map<String, ReporterDetails> results) {
    Map<String, ReporterDetails> specificationResults = results.entrySet().stream()
      .filter(result -> !result.getKey().startsWith("CSIPSTR") && !result.getKey().equals("CSIP0")
        && !result.getValue().isValid())
      .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

    List<TestResult> testResults = new ArrayList<>();
    for (Map.Entry<String, ReporterDetails> result : specificationResults.entrySet()) {
      testResults.add(createTestResult(result.getKey(), result.getValue()));
    }
    MetadataChecks schematronResuts = new MetadataChecks();
    schematronResuts.setStatus(calculateStatus(specificationResults));
    schematronResuts.setMessages(testResults);
    return schematronResuts;
  }

  private static TestResult createTestResult(String id, ReporterDetails reporterDetails) {
    TestResult testResult = new TestResult();
    testResult.setRuleId(id);
    if (id.startsWith("CSIP")) {
      testResult.setLocation(ConstantsCSIPspec.getSpecificationLocation(id));
    } else if (id.startsWith("SIP")) {
      testResult.setLocation(ConstantsSIPspec.getSpecificationLocation(id));
    } else if (id.startsWith("AIP")) {
      testResult.setLocation(ConstantsAIPspec.getSpecificationLocation(id));
    }
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

  private static MetadataStatus calculateStatus(Map<String, ReporterDetails> results) {
    Map<String, ReporterDetails> failedResults = results.entrySet().stream()
      .filter(result -> !result.getValue().isValid()).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    for (Map.Entry<String, ReporterDetails> result : failedResults.entrySet()) {
      if (result.getKey().startsWith("CSIP")
        && ConstantsCSIPspec.getSpecificationLevel(result.getKey()).equals("MUST")) {
        return MetadataStatus.NOTVALID;
      } else if (result.getKey().startsWith("SIP")
        && ConstantsSIPspec.getSpecificationLevel(result.getKey()).equals("MUST")) {
        return MetadataStatus.NOTVALID;
      }
    }
    return MetadataStatus.VALID;
  }
}
