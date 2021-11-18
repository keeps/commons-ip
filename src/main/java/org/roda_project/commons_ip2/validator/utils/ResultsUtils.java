package org.roda_project.commons_ip2.validator.utils;

import java.util.Map;
import org.roda_project.commons_ip2.validator.reporter.ReporterDetails;

/** {@author João Gomes <jgomes@keep.pt>}. */
public class ResultsUtils {

  private ResultsUtils() {
    // do nothing
  }

  /**
   * Add the {@link ReporterDetails} result to the {@link Map} results.
   *
   * @param results the {@link Map} with all results
   * @param specification the {@link String} with the specification of the result.
   * @param details the {@link ReporterDetails}
   */
  public static void addResult(
      Map<String, ReporterDetails> results, String specification, ReporterDetails details) {
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

  public static void mergeResults(
      Map<String, ReporterDetails> results, Map<String, ReporterDetails> componentResults) {
    componentResults.forEach((s, d) -> addResult(results, s, d));
  }

  /**
   * Add the same {@link ReporterDetails} to different specifications in the results {@link Map}.
   *
   * @param results the {@link Map} with all results
   * @param details the {@link ReporterDetails}
   * @param specifications 1 or more specifications {@link String}.
   */
  public static void addResults(
      Map<String, ReporterDetails> results, ReporterDetails details, String... specifications) {
    for (String specification : specifications) {
      addResult(results, specification, details);
    }
  }
}
