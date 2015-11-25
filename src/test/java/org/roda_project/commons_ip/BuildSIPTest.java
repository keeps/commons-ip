/**
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE file at the root of the source
 * tree and available online at
 *
 * https://github.com/keeps/commons-ip
 */
package org.roda_project.commons_ip;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;
import org.roda_project.commons_ip.model.SIP;
import org.roda_project.commons_ip.model.SIPAgent;
import org.roda_project.commons_ip.model.SIPDescriptiveMetadata;
import org.roda_project.commons_ip.model.SIPMetadata;
import org.roda_project.commons_ip.model.SIPRepresentation;
import org.roda_project.commons_ip.model.impl.EARKSIP;
import org.roda_project.commons_ip.utils.METSEnums.CreatorType;
import org.roda_project.commons_ip.utils.METSEnums.MetadataType;
import org.roda_project.commons_ip.validation.Validator;
import org.roda_project.commons_ip.validation.impl.EARKValidator;
import org.roda_project.commons_ip.validation.model.ValidationIssue;
import org.roda_project.commons_ip.validation.model.ValidationReport;

/**
 * Unit test for simple App.
 */
public class BuildSIPTest {

  @Test
  public void buildEARKSIP() throws Exception {
    SIP sip = new EARKSIP("objectID", "profile", "type");

    SIPMetadata logMetadata = new SIPMetadata(Paths.get("src/test/resources/data/earkweb.log"), null);
    sip.addMetadata(logMetadata);

    SIPDescriptiveMetadata dcMetadata = new SIPDescriptiveMetadata(Paths.get("src/test/resources/data/dc.xml"),
      Paths.get("src/test/resources/data/dc.xsd"), MetadataType.DC);
    SIPDescriptiveMetadata textMetadata = new SIPDescriptiveMetadata(
      Paths.get("src/test/resources/data/descriptive.txt"), null, MetadataType.TEXTMD);
    sip.addDescriptiveMetadata(textMetadata);
    sip.addDescriptiveMetadata(dcMetadata);

    SIPAgent agent1 = new SIPAgent("AgentName", "ROLE", CreatorType.INDIVIDUAL, "OTHER ROLE", "OTHER TYPE");
    SIPAgent agent2 = new SIPAgent("AgentName2", "ROLE2", CreatorType.INDIVIDUAL, "OTHER ROLE2", "OTHER TYPE2");
    sip.addAgent(agent1);
    sip.addAgent(agent2);

    SIPRepresentation representation1 = new SIPRepresentation("rep1", "repId1", "profile", "repType");
    sip.addRepresentation(representation1);
    sip.addDataToRepresentation("rep1", Paths.get("src/test/resources/data/bike.gif"));
    sip.addDataToRepresentation("rep1", Paths.get("src/test/resources/data/data.txt"));
    SIPRepresentation representation2 = new SIPRepresentation("rep2", "repId2", "profile", "repType");
    representation2.setData(
      Arrays.asList(Paths.get("src/test/resources/data/bike.gif"), Paths.get("src/test/resources/data/data.txt")));
    sip.addRepresentation(representation2);

    SIPMetadata premis = new SIPMetadata(Paths.get("src/test/resources/data/premis.xml"),
      Paths.get("src/test/resources/data/premis-v2-2.xsd"));
    sip.addPreservationToRepresentation("rep1", premis);
    SIPAgent agent = new SIPAgent("AgentName2", "ROLE2", CreatorType.INDIVIDUAL, "OTHER ROLE2", "OTHER TYPE2");
    sip.addAgentToRepresentation("rep1", agent);
    Path zip = sip.build();

    Validator validator = new EARKValidator();
    ValidationReport report = validator.isSIPValid(zip);
    Assert.assertTrue(report.isValid());

    System.out.println("Valid: " + report.isValid());
    if (report.getIssues() != null && report.getIssues().size() > 0) {
      for (ValidationIssue issue : report.getIssues()) {
        System.out.println(issue.getLevel().toString() + " - " + issue.getMessage());
      }
    }
  }
}
