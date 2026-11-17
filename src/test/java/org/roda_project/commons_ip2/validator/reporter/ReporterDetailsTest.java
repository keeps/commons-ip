package org.roda_project.commons_ip2.validator.reporter;

import org.junit.Assert;
import org.junit.Test;

public class ReporterDetailsTest {

  @Test
  public void cloningPreservesTheDetail() {
    ReporterDetails original = new ReporterDetails("CSIP104", "an issue", true, false);
    original.setDetail("a human readable detail");

    ReporterDetails copy = original.clone();

    Assert.assertEquals("a human readable detail", copy.getDetail());
  }

  @Test
  public void cloningPreservesValidAndSkippedAndIssues() {
    ReporterDetails original = new ReporterDetails("CSIP104", "an issue", false, true);

    ReporterDetails copy = original.clone();

    Assert.assertFalse(copy.isValid());
    Assert.assertTrue(copy.isSkipped());
    Assert.assertTrue(copy.getIssues().contains("an issue"));
    Assert.assertEquals("CSIP104", copy.getSpecification());
  }
}
