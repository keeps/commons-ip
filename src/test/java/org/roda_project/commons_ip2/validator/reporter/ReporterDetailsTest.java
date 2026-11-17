package org.roda_project.commons_ip2.validator.reporter;

import org.junit.Assert;
import org.junit.Test;

public class ReporterDetailsTest {

  private static final String SPEC = "CSIP104";
  private static final String ISSUE = "an issue";

  @Test
  public void cloningPreservesTheDetail() {
    final ReporterDetails original = new ReporterDetails(SPEC, ISSUE, true, false);
    final String detail = "a human readable detail";
    original.setDetail(detail);

    final ReporterDetails copy = original.clone();

    Assert.assertEquals(detail, copy.getDetail());
  }

  @Test
  public void cloningPreservesValidAndSkippedAndIssues() {
    final ReporterDetails original = new ReporterDetails(SPEC, ISSUE, false, true);

    final ReporterDetails copy = original.clone();

    Assert.assertFalse(copy.isValid());
    Assert.assertTrue(copy.isSkipped());
    Assert.assertTrue(copy.getIssues().contains(ISSUE));
    Assert.assertEquals(SPEC, copy.getSpecification());
  }
}
