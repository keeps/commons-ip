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
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.roda_project.commons_ip.model.IPAgent;
import org.roda_project.commons_ip.model.IPContentType;
import org.roda_project.commons_ip.model.IPDescriptiveMetadata;
import org.roda_project.commons_ip.model.IPFile;
import org.roda_project.commons_ip.model.IPMetadata;
import org.roda_project.commons_ip.model.IPRepresentation;
import org.roda_project.commons_ip.model.MetadataType;
import org.roda_project.commons_ip.model.MetadataType.MetadataTypeEnum;
import org.roda_project.commons_ip.model.ParseException;
import org.roda_project.commons_ip.model.SIP;
import org.roda_project.commons_ip.model.impl.eark.EARKSIP;
import org.roda_project.commons_ip.utils.METSEnums.CreatorType;
import org.roda_project.commons_ip.utils.SIPException;
import org.roda_project.commons_ip.utils.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Unit tests for EARK Information Packages (SIP, AIP and DIP)
 */
public class EARKTest {
  private static final Logger LOGGER = LoggerFactory.getLogger(EARKTest.class);

  private static Path tempFolder;

  @BeforeClass
  public static void setup() throws IOException {
    tempFolder = Files.createTempDirectory("temp");
  }

  @AfterClass
  public static void cleanup() throws Exception {
    Utils.deletePath(tempFolder);
  }

  @Test
  public void buildAndParseEARKSIP() throws SIPException, ParseException, InterruptedException {
    LOGGER.info("Creating full E-ARK SIP");
    Path zipSIP = createFullEARKSIP();
    LOGGER.info("Done creating full E-ARK SIP");

    LOGGER.info("Parsing (and validating) full E-ARK SIP");
    parseAndValidateFullEARKSIP(zipSIP);
    LOGGER.info("Done parsing (and validating) full E-ARK SIP");

  }

  private Path createFullEARKSIP() throws SIPException, InterruptedException {

    // 1) instantiate E-ARK SIP object
    SIP sip = new EARKSIP("SIP_1", IPContentType.getMIXED(), "RODA Commons IP");

    // 1.1) set optional human-readable description
    sip.setDescription("A full E-ARK SIP");

    // 1.2) add descriptive metadata (SIP level)
    IPDescriptiveMetadata metadataDescriptiveDC = new IPDescriptiveMetadata(
      new IPFile(Paths.get("src/test/resources/eark/metadata_descriptive_dc.xml")),
      new MetadataType(MetadataTypeEnum.DC), null);
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

    // 1.7) set optional RODA related information about ancestors
    sip.setAncestors(Arrays.asList("b6f24059-8973-4582-932d-eb0b2cb48f28"));

    // 1.8) add an agent (SIP level)
    IPAgent agent = new IPAgent("Agent Name", "OTHER", "OTHER ROLE", CreatorType.INDIVIDUAL, "OTHER TYPE");
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

  private void parseAndValidateFullEARKSIP(Path zipSIP) throws ParseException {

    // 1) invoke static method parse and that's it
    SIP earkSIP = EARKSIP.parse(zipSIP, tempFolder);

    Assert.assertTrue(earkSIP.getValidationReport().isValid());

    LOGGER.info("SIP with id '{}' parsed with success (valid? {})!", earkSIP.getId(),
      earkSIP.getValidationReport().isValid());
  }

}
