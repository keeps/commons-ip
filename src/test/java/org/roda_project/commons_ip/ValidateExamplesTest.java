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

import org.junit.AfterClass;
import org.junit.Test;
import org.roda_project.commons_ip.model.MigrationException;
import org.roda_project.commons_ip.model.SIP;
import org.roda_project.commons_ip.model.SIPAgent;
import org.roda_project.commons_ip.model.SIPDescriptiveMetadata;
import org.roda_project.commons_ip.model.SIPMetadata;
import org.roda_project.commons_ip.model.SIPRepresentation;
import org.roda_project.commons_ip.model.ValidationIssue;
import org.roda_project.commons_ip.model.ValidationReport;
import org.roda_project.commons_ip.model.impl.eark.EARKSIP;
import org.roda_project.commons_ip.parse.Parser;
import org.roda_project.commons_ip.parse.impl.eark.EARKParser;
import org.roda_project.commons_ip.utils.EARKEnums.ContentType;
import org.roda_project.commons_ip.utils.METSEnums.CreatorType;
import org.roda_project.commons_ip.utils.METSEnums.MetadataType;
import org.roda_project.commons_ip.utils.SIPException;
import org.roda_project.commons_ip.validation.Validator;
import org.roda_project.commons_ip.validation.impl.eark.EARKValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Unit test for simple App.
 */
public class ValidateExamplesTest {
  private static final Logger LOGGER = LoggerFactory.getLogger(ValidateExamplesTest.class);

  @Test
  public void buildEARKSIP() throws Exception {

    // validateAndConvertExample1();

    // validateAndConvertExample2();

    createValidateAndConvert();

  }

  private void validateAndConvertExample2() throws MigrationException {
    Validator validator = new EARKValidator();
    Path example = Paths.get("examples/SIP1");
    System.out.println("Validating and converting " + example.toString());
    ValidationReport report = validator.isSIPValid(example);
    LOGGER.info("Valid: " + report.isValid());
    if (report.getIssues() != null && report.getIssues().size() > 0) {
      for (ValidationIssue issue : report.getIssues()) {
        LOGGER.info(issue.getLevel().toString() + " - " + issue.getMessage() + " - " + issue.getDescription());
      }
    }

    Parser toRoda = new EARKParser();
    SIP converted2 = toRoda.parse(example);
    LOGGER.info("CONVERTED: " + converted2.toString());

  }

  private void validateAndConvertExample1() throws MigrationException {
    Validator validator = new EARKValidator();
    Path example = Paths.get("examples/76198202-38d8-4044-b72e-ae8623bf1655/submission");
    System.out.println("Validating and converting " + example.toString());
    ValidationReport report = validator.isSIPValid(example);
    LOGGER.info("Valid: " + report.isValid());
    if (report.getIssues() != null && report.getIssues().size() > 0) {
      for (ValidationIssue issue : report.getIssues()) {
        LOGGER.info(issue.getLevel().toString() + " - " + issue.getMessage() + " - " + issue.getDescription());
      }
    }
    Parser toRoda = new EARKParser();
    SIP converted1 = toRoda.parse(example);
    LOGGER.info("CONVERTED: " + converted1.toString());
  }

  private void createValidateAndConvert() {
    try {
      SIP sip = new EARKSIP("ID_INVALID", ContentType.mixed, "RODA");

      SIPMetadata metadata1 = new SIPMetadata(Paths.get("src/test/resources/data/earkweb.log"), null);
      SIPDescriptiveMetadata descriptiveMetadata1 = new SIPDescriptiveMetadata(
        Paths.get("src/test/resources/data/dc.xml"), Paths.get("src/test/resources/data/dc.xsd"), MetadataType.DC,
        null);
      SIPDescriptiveMetadata descriptiveMetadata2 = new SIPDescriptiveMetadata(
        Paths.get("src/test/resources/data/descriptive.txt"), null, MetadataType.TEXTMD, null);
      SIPMetadata metadata3 = new SIPMetadata(Paths.get("src/test/resources/data/premis.xml"),
        Paths.get("src/test/resources/data/premis-v2-2.xsd"));
      SIPAgent agent1 = new SIPAgent("AgentName", "ROLE", CreatorType.INDIVIDUAL, "OTHER ROLE", "OTHER TYPE");
      SIPAgent agent2 = new SIPAgent("AgentName2", "ROLE2", CreatorType.INDIVIDUAL, "OTHER ROLE2", "OTHER TYPE2");

      sip.addAdministrativeMetadata(metadata1);
      sip.addAgent(agent1);
      sip.addAgent(agent2);
      sip.addDescriptiveMetadata(descriptiveMetadata1);
      sip.addDocumentation(Paths.get("src/test/resources/data/eark.pdf"));
      // sip.addOtherMetadata(metadata3);

      SIPRepresentation representation1 = new SIPRepresentation("rep1");
      sip.addRepresentation(representation1);
      sip.addAdministrativeMetadataToRepresentation("rep1", metadata1);
      sip.addAgentToRepresentation("rep1", agent1);
      sip.addDataToRepresentation("rep1", Paths.get("src/test/resources/data/eark.pdf"));
      sip.addDescriptiveMetadataToRepresentation("rep1", descriptiveMetadata1);
      sip.addOtherMetadataToRepresentation("rep1", metadata3);

      SIPRepresentation representation2 = new SIPRepresentation("rep2");
      sip.addRepresentation(representation2);
      sip.addAdministrativeMetadataToRepresentation("rep2", metadata3);
      sip.addAgentToRepresentation("rep2", agent2);
      sip.addDataToRepresentation("rep2", Paths.get("src/test/resources/data/eark.pdf"));
      sip.addDescriptiveMetadataToRepresentation("rep2", descriptiveMetadata2);
      sip.addOtherMetadataToRepresentation("rep2", metadata1);

      sip.setParent("b6f24059-8973-4582-932d-eb0b2cb48f28");

      Path zip = sip.build();

      // validate SIP
      Validator validator = new EARKValidator();
      ValidationReport report = validator.isSIPValid(zip);
      // Assert.assertTrue(report.isValid());

      LOGGER.info("Valid: " + report.isValid());
      if (report.getIssues() != null && report.getIssues().size() > 0) {
        for (ValidationIssue issue : report.getIssues()) {
          LOGGER.info(issue.getLevel().toString() + " - " + issue.getMessage());
        }
      }

      Parser toRoda = new EARKParser();
      SIP converted = toRoda.parse(zip);

      LOGGER.info("CONVERTED: " + converted.toString());
    } catch (SIPException | MigrationException s) {
      s.printStackTrace();
    }

  }

  @AfterClass
  public static void cleanup() throws Exception {

  }
}
