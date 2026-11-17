package org.roda_project.commons_ip2.validator.utils;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.roda_project.commons_ip2.validator.reporter.ReporterDetails;

public class ResultsUtilsTest {

  private static final String SPEC = "CSIP104";

  @Test
  public void skippedIsAndMergedSoAnEvaluationClearsASkip() {
    Map<String, ReporterDetails> results = new HashMap<>();
    // Skipped in the root METS, evaluated (not skipped) in a representation METS.
    ResultsUtils.addResult(results, SPEC, new ReporterDetails(SPEC, "skipped at root", true, true));
    ResultsUtils.addResult(results, SPEC, new ReporterDetails(SPEC, "evaluated", true, false));

    ReporterDetails merged = results.get(SPEC);
    Assert.assertFalse("skipped must be AND-merged so gating is preserved", merged.isSkipped());
    Assert.assertTrue(merged.isValid());
    Assert.assertTrue(ResultsUtils.isResultValid(results, SPEC));
  }

  @Test
  public void skippedEverywhereKeepsSkippedTrue() {
    Map<String, ReporterDetails> results = new HashMap<>();
    ResultsUtils.addResult(results, SPEC, new ReporterDetails(SPEC, "skipped root", true, true));
    ResultsUtils.addResult(results, SPEC, new ReporterDetails(SPEC, "skipped rep", true, true));

    Assert.assertTrue(results.get(SPEC).isSkipped());
    Assert.assertFalse(ResultsUtils.isResultValid(results, SPEC));
  }

  @Test
  public void invalidEvaluationMakesTheRequirementInvalid() {
    Map<String, ReporterDetails> results = new HashMap<>();
    ResultsUtils.addResult(results, SPEC, new ReporterDetails(SPEC, "valid root", true, false));
    ResultsUtils.addResult(results, SPEC, new ReporterDetails(SPEC, "invalid rep", false, false));

    Assert.assertFalse(results.get(SPEC).isValid());
    Assert.assertFalse(ResultsUtils.isResultValid(results, SPEC));
  }

  @Test
  public void issuesAreAccumulatedAcrossMerges() {
    Map<String, ReporterDetails> results = new HashMap<>();
    ResultsUtils.addResult(results, SPEC, new ReporterDetails(SPEC, "issue root", true, false));
    ResultsUtils.addResult(results, SPEC, new ReporterDetails(SPEC, "issue rep", true, false));

    Assert.assertTrue(results.get(SPEC).getIssues().contains("issue root"));
    Assert.assertTrue(results.get(SPEC).getIssues().contains("issue rep"));
  }
}
