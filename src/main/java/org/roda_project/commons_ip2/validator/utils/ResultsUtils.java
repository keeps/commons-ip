package org.roda_project.commons_ip2.validator.utils;

import java.util.Map;

import org.roda_project.commons_ip2.validator.reporter.ReporterDetails;

/** {@author João Gomes <jgomes@keep.pt>}. */
public final class ResultsUtils {

  private ResultsUtils() {
    // do nothing
  }

  /**
   * Add the {@link ReporterDetails} result to the {@link Map} results.
   *
   * @param results
   *          the {@link Map} with all results
   * @param specification
   *          the {@link String} with the specification of the result.
   * @param details
   *          the {@link ReporterDetails}
   */
  public static void addResult(final Map<String, ReporterDetails> results, final String specification,
    final ReporterDetails details) {
    if (results.containsKey(specification)) {
      final ReporterDetails currentResult = results.get(specification);
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

  /**
   * Check if the result is valid.
   * 
   * @param results
   *          {@link Map} with all specification results.
   * @param specification
   *          the specification.
   * @return if is valid or not.
   */
  public static boolean isResultValid(final Map<String, ReporterDetails> results, final String specification) {
    return results.get(specification).isValid() && !results.get(specification).isSkipped();
  }

  /**
   * Merge the results for all component requirements.
   * 
   * @param results
   *          {@link Map} global results.
   * @param componentResults
   *          {@link Map} component results.
   */
  public static void mergeResults(final Map<String, ReporterDetails> results,
    final Map<String, ReporterDetails> componentResults) {
    componentResults.forEach((s, d) -> addResult(results, s, d));
  }

  /**
   * Add the same {@link ReporterDetails} to different specifications in the
   * results {@link Map}.
   *
   * @param results
   *          the {@link Map} with all results
   * @param details
   *          the {@link ReporterDetails}
   * @param specifications
   *          1 or more specifications {@link String}.
   */
  public static void addResults(final Map<String, ReporterDetails> results, final ReporterDetails details,
    final String... specifications) {
    for (String specification : specifications) {
      addResult(results, specification, details);
    }
  }
}
