/**
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE file at the root of the source
 * tree and available online at
 *
 * https://github.com/keeps/commons-ip
 */
package org.roda_project.commons_ip;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.roda_project.commons_ip.model.IPAgent;
import org.roda_project.commons_ip.model.IPDescriptiveMetadata;
import org.roda_project.commons_ip.model.IPFile;
import org.roda_project.commons_ip.model.IPMetadata;
import org.roda_project.commons_ip.model.IPRepresentation;
import org.roda_project.commons_ip.model.ParseException;
import org.roda_project.commons_ip.model.SIP;
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
public class EARKTest {
  private static final Logger LOGGER = LoggerFactory.getLogger(EARKTest.class);

  private static Path tempFolder;

  @Test
  public void buildAndParseEARKSIP() throws SIPException, ParseException {

    Path zipSIP = createFullEARKSIP();

    parseFullEARKSIP(zipSIP);

  }

  private Path createFullEARKSIP() throws SIPException {

    // 1) instantiate E-ARK SIP object
    SIP sip = new EARKSIP("SIP_1", ContentType.mixed, "RODA Commons IP");

    // 1.1) set optional human-readable description
    sip.setDescription("A full E-ARK SIP");

    // 1.2) add descriptive metadata (SIP level)
    IPDescriptiveMetadata metadataDescriptiveDC = new IPDescriptiveMetadata(
      new IPFile(Paths.get("src/test/resources/eark/metadata_descriptive_dc.xml")), MetadataType.DC, null);
    sip.addDescriptiveMetadata(metadataDescriptiveDC);

    // 1.3) add preservation metadata (SIP level)
    IPMetadata metadataPreservation = new IPMetadata(
      new IPFile(Paths.get("src/test/resources/eark/metadata_preservation_premis.xml")));
    sip.addPreservationMetadata(metadataPreservation);

    // 1.4) add other metadata (SIP level)
    IPFile metadataOtherFile = new IPFile(Paths.get("src/test/resources/eark/metadata_other.txt"));
    // 1.4.1) optionally one may rename file final name
    metadataOtherFile.setRenameTo("metadata_other_renamed.txt");
    IPMetadata metadataOther = new IPMetadata(metadataOtherFile);
    sip.addOtherMetadata(metadataOther);

    // 1.5) add xml schema (SIP level)
    sip.addSchema(new IPFile(Paths.get("src/test/resources/eark/schema.xsd")));

    // 1.6) add documentation (SIP level)
    sip.addDocumentation(new IPFile(Paths.get("src/test/resources/eark/documentation.pdf")));

    // 1.7) set optional RODA related information parent id
    sip.setParent("b6f24059-8973-4582-932d-eb0b2cb48f28");

    // 1.8) add an agent (SIP level)
    IPAgent agent = new IPAgent("Agent Name", "ROLE", "OTHER ROLE", CreatorType.INDIVIDUAL, "OTHER TYPE");
    sip.addAgent(agent);

    // 1.9) add a representation
    IPRepresentation representation1 = new IPRepresentation("representation 1");
    sip.addRepresentation(representation1);

    // 1.9.1) add a file to the representation
    IPFile representationFile = new IPFile(Paths.get("src/test/resources/eark/documentation.pdf"));
    representationFile.setRenameTo("data.pdf");
    representation1.addFile(representationFile);

    // 1.9.2) add a file to the representation and put it inside a folder
    // called 'abc' which has a folder inside called 'def'
    IPFile representationFile2 = new IPFile(Paths.get("src/test/resources/eark/documentation.pdf"));
    representationFile2.setRelativeFolders(Arrays.asList("abc", "def"));
    representation1.addFile(representationFile2);

    // 2) build SIP, providing an output directory
    Path zipSIP = sip.build(tempFolder);

    return zipSIP;
  }

  private void parseFullEARKSIP(Path zipSIP) throws ParseException {

    // 1) instantiate E-ARK parser
    Parser earkParser = new EARKParser();
    // 2) parse zip file to obtain an SIP
    SIP sip = earkParser.parse(zipSIP);

    LOGGER.info("SIP with id '{}' parsed with success!", sip);
  }

  private void validateAndConvertExample2() throws ParseException {
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

  private void validateAndConvertExample1() throws ParseException {
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

      IPMetadata metadata1 = new IPMetadata(new IPFile(Paths.get("src/test/resources/data/earkweb.log")));
      IPDescriptiveMetadata descriptiveMetadata1 = new IPDescriptiveMetadata(
        new IPFile(Paths.get("src/test/resources/data/dc.xml")), MetadataType.DC, null);
      IPDescriptiveMetadata descriptiveMetadata2 = new IPDescriptiveMetadata(
        new IPFile(Paths.get("src/test/resources/data/descriptive.txt")), MetadataType.TEXTMD, null);
      IPMetadata metadata3 = new IPMetadata(new IPFile(Paths.get("src/test/resources/data/premis.xml")));
      IPAgent agent1 = new IPAgent("AgentName", "ROLE", "OTHER ROLE", CreatorType.INDIVIDUAL, "OTHER TYPE");
      IPAgent agent2 = new IPAgent("AgentName2", "ROLE2", "OTHER ROLE2", CreatorType.INDIVIDUAL, "OTHER TYPE2");

      IPMetadata otherMetadata = new IPMetadata(
        new IPFile(Paths.get("src/test/resources/data/data.txt"), Arrays.asList("src", "test", "resources", "data")));

      sip.addPreservationMetadata(metadata1);
      sip.addOtherMetadata(otherMetadata);
      sip.addAgent(agent1);
      sip.addAgent(agent2);
      sip.addDescriptiveMetadata(descriptiveMetadata1);
      sip.addDocumentation(new IPFile(Paths.get("src/test/resources/data/eark.pdf")));

      IPRepresentation representation1 = new IPRepresentation("rep1");
      sip.addRepresentation(representation1);
      IPMetadata metadata11 = new IPMetadata(new IPFile(Paths.get("src/test/resources/data/earkweb.log"),
        Arrays.asList("src", "test", "resources", "data")));
      sip.addPreservationMetadataToRepresentation("rep1", metadata11);
      sip.addAgentToRepresentation("rep1", agent1);
      sip.addFileToRepresentation("rep1", new IPFile(Paths.get("src/test/resources/data/eark.pdf")));
      sip.addDescriptiveMetadataToRepresentation("rep1", descriptiveMetadata1);
      sip.addOtherMetadataToRepresentation("rep1", metadata3);

      IPRepresentation representation2 = new IPRepresentation("rep2");
      sip.addRepresentation(representation2);
      // sip.addAdministrativeMetadataToRepresentation("rep2", metadata3);
      sip.addAgentToRepresentation("rep2", agent2);
      sip.addFileToRepresentation("rep2", new IPFile(Paths.get("src/test/resources/data/eark.pdf")));
      // sip.addDescriptiveMetadataToRepresentation("rep2",
      // descriptiveMetadata2);
      // sip.addOtherMetadataToRepresentation("rep2", metadata1);

      sip.addSchema(new IPFile(Paths.get("src/main/resources/schemas/mets1_11.xsd")));

      sip.setParent("b6f24059-8973-4582-932d-eb0b2cb48f28");

      Path zip = sip.build(tempFolder);

      // validate SIP
      Validator validator = new EARKValidator();
      ValidationReport report = validator.isSIPValid(zip);
      // Assert.assertTrue(report.isValid());

      LOGGER.info("Valid: " + report.isValid());
      if (report.getIssues() != null && !report.getIssues().isEmpty()) {
        for (ValidationIssue issue : report.getIssues()) {
          LOGGER.info(issue.getLevel().toString() + " - " + issue.getMessage());
        }
      }

      Parser toRoda = new EARKParser();
      SIP converted = toRoda.parse(zip);

      LOGGER.info("CONVERTED: " + converted.toString());
    } catch (SIPException | ParseException e) {
      LOGGER.error("", e);
    }

  }

  @BeforeClass
  public static void setup() throws IOException {
    tempFolder = Files.createTempDirectory("temp");
  }

  @AfterClass
  public static void cleanup() throws Exception {
    // Utils.deletePath(tempFolder);
  }
}
