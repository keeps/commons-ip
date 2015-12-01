/**
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE file at the root of the source
 * tree and available online at
 *
 * https://github.com/keeps/commons-ip
 */
package org.roda_project.commons_ip;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.junit.AfterClass;
import org.junit.Test;
import org.roda_project.commons_ip.migration.Migrator;
import org.roda_project.commons_ip.migration.impl.EARKSIPToRODAAIP;
import org.roda_project.commons_ip.model.SIP;
import org.roda_project.commons_ip.model.SIPAgent;
import org.roda_project.commons_ip.model.SIPDescriptiveMetadata;
import org.roda_project.commons_ip.model.SIPMetadata;
import org.roda_project.commons_ip.model.SIPRepresentation;
import org.roda_project.commons_ip.model.impl.EARKSIP;
import org.roda_project.commons_ip.utils.EARKEnums.ContentType;
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
  private static Path zip;

  @Test
  public void buildEARKSIP() throws Exception {
    // build SIP
    SIP sip = new EARKSIP("ID", ContentType.mixed, "RODA");

    SIPMetadata metadata1 = new SIPMetadata(Paths.get("src/test/resources/data/earkweb.log"), null);
    SIPDescriptiveMetadata descriptiveMetadata1 = new SIPDescriptiveMetadata(
      Paths.get("src/test/resources/data/dc.xml"), Paths.get("src/test/resources/data/dc.xsd"), MetadataType.DC);
    SIPDescriptiveMetadata descriptiveMetadata2 = new SIPDescriptiveMetadata(
      Paths.get("src/test/resources/data/descriptive.txt"), null, MetadataType.TEXTMD);
    SIPMetadata metadata3 = new SIPMetadata(Paths.get("src/test/resources/data/premis.xml"),
      Paths.get("src/test/resources/data/premis-v2-2.xsd"));
    SIPAgent agent1 = new SIPAgent("AgentName", "ROLE", CreatorType.INDIVIDUAL, "OTHER ROLE", "OTHER TYPE");
    SIPAgent agent2 = new SIPAgent("AgentName2", "ROLE2", CreatorType.INDIVIDUAL, "OTHER ROLE2", "OTHER TYPE2");

    sip.addAdministrativeMetadata(metadata1);
    sip.addAgent(agent1);
    sip.addAgent(agent2);
    sip.addDescriptiveMetadata(descriptiveMetadata1);
    sip.addDocumentation(Paths.get("src/test/resources/data/eark.pdf"));
    sip.addOtherMetadata(metadata3);

    SIPRepresentation representation1 = new SIPRepresentation("rep1");
    sip.addRepresentation(representation1);
    sip.addAdministrativeMetadataToRepresentation("rep1", metadata1);
    sip.addAgentToRepresentation("rep1", agent1);
    sip.addDataToRepresentation("rep1", Paths.get("src/test/resources/data/bike.gif"));
    sip.addDescriptiveMetadataToRepresentation("rep1", descriptiveMetadata1);
    sip.addOtherMetadataToRepresentation("rep1", metadata3);

    SIPRepresentation representation2 = new SIPRepresentation("rep2");
    sip.addRepresentation(representation2);
    sip.addAdministrativeMetadataToRepresentation("rep2", metadata3);
    sip.addAgentToRepresentation("rep2", agent2);
    sip.addDataToRepresentation("rep2", Paths.get("src/test/resources/data/bike.gif"));
    sip.addDescriptiveMetadataToRepresentation("rep2", descriptiveMetadata2);
    sip.addOtherMetadataToRepresentation("rep2", metadata1);

    zip = sip.build();


    // validate SIP
    Validator validator = new EARKValidator();
    ValidationReport report = validator.isSIPValid(zip);
    // Assert.assertTrue(report.isValid());

    System.out.println("Valid: " + report.isValid());
    if (report.getIssues() != null && report.getIssues().size() > 0) {
      for (ValidationIssue issue : report.getIssues()) {
        System.out.println(issue.getLevel().toString() + " - " + issue.getMessage());
      }
    }

    Migrator toRoda = new EARKSIPToRODAAIP();
    Path converted = toRoda.convert(zip);

    System.out.println("CONVERTED: " + converted.toString());

  }

  @AfterClass
  public static void cleanup() throws Exception {
    // clean up
    if (Files.exists(zip)) {
      Files.delete(zip);
    }
  }
}
