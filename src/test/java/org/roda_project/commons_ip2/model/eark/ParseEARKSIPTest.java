package org.roda_project.commons_ip2.model.impl.eark;

import org.junit.Assert;
import org.junit.Test;
import org.roda_project.commons_ip.model.ParseException;
import org.roda_project.commons_ip2.model.IPRepresentation;
import org.roda_project.commons_ip2.model.SIP;

import java.nio.file.Path;
import java.nio.file.Paths;

public class ParseEARKSIPTest {

  @Test
  public void parseEARKSIPTest() throws ParseException {
    Path earkSIPPath = Paths.get("src/test/resources/6ab98a90-1686-4c74-9855-b2793a6d164d.zip");

    SIP sip = new EARKSIP().parse(earkSIPPath);

    Assert.assertEquals(1, sip.getDescriptiveMetadata().size());
    Assert.assertEquals(2, sip.getPreservationMetadata().size());
    Assert.assertEquals(0, sip.getRightsMetadata().size());
    Assert.assertEquals(0, sip.getSourceMetadata().size());
    Assert.assertEquals(1, sip.getRepresentations().size());

    IPRepresentation first = sip.getRepresentations().get(0);
    Assert.assertEquals(1, first.getData().size());
    Assert.assertEquals(0, first.getOtherMetadata().size());
    Assert.assertEquals(1, first.getTechnicalMetadata().size());

  }
}
