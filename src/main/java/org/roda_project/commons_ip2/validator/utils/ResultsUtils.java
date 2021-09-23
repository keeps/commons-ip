package org.roda_project.commons_ip2.validator.utils;

import java.util.Map;

import org.roda_project.commons_ip2.validator.reporter.ReporterDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author João Gomes <jgomes@keep.pt>
 */
public class ResultsUtils {

  private static final Logger LOGGER = LoggerFactory.getLogger(ResultsUtils.class);

  public static void addResult(Map<String, ReporterDetails> results, String specification, ReporterDetails details) {
    if (results.containsKey(specification)) {
      ReporterDetails currentResult = results.get(specification);
      // Merge current result with new test case validation outcome
      if (!details.getIssues().isEmpty()) {
        currentResult.addIssues(details.getIssues());
      }
      currentResult.setSkipped(currentResult.isSkipped() && details.isSkipped());
      currentResult.setValid(currentResult.isValid() && details.isValid());
    } else {
      results.put(specification, details.clone());
    }

  }

  public static boolean isResultValid(Map<String, ReporterDetails> results, String specification) {
    return results.get(specification).isValid() && !results.get(specification).isSkipped();
  }

  public static void mergeResults(Map<String, ReporterDetails> results, Map<String, ReporterDetails> componentResults) {
    componentResults.forEach((s, d) -> addResult(results, s, d));
  }

  public static void addResults(Map<String, ReporterDetails> results, ReporterDetails details,
    String... specifications) {
    for (String specification : specifications) {
      addResult(results, specification, details);
    }
  }
}
