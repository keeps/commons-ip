/**
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE file at the root of the source
 * tree and available online at
 *
 * https://github.com/keeps/commons-ip
 */
package org.roda_project.commons_ip.model.impl.eark;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import org.hamcrest.core.Is;
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
import org.roda_project.commons_ip.model.RepresentationStatus;
import org.roda_project.commons_ip.model.SIP;
import org.roda_project.commons_ip.model.ValidationEntry.LEVEL;
import org.roda_project.commons_ip.utils.IPException;
import org.roda_project.commons_ip.utils.METSEnums.CreatorType;
import org.roda_project.commons_ip.utils.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Unit tests for EARK Information Packages (SIP, AIP and DIP)
 */
public class EARKTest {
  private static final String REPRESENTATION_STATUS_NORMALIZED = "NORMALIZED";

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
  public void buildAndParseEARKSIP() throws IPException, ParseException, InterruptedException {
    LOGGER.info("Creating full E-ARK SIP");
    Path zipSIP = createFullEARKSIP();
    LOGGER.info("Done creating full E-ARK SIP");

    LOGGER.info("Parsing (and validating) full E-ARK SIP");
    parseAndValidateFullEARKSIP(zipSIP);
    LOGGER.info("Done parsing (and validating) full E-ARK SIP");

  }

  private Path createFullEARKSIP() throws IPException, InterruptedException {

    // 1) instantiate E-ARK SIP object
    SIP sip = new EARKSIP("SIP_1", IPContentType.getMIXED());
    sip.addCreatorSoftwareAgent("RODA Commons IP");

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
    sip.setAncestors(List.of("b6f24059-8973-4582-932d-eb0b2cb48f28"));

    // 1.8) add an agent (SIP level)
    IPAgent agent = new IPAgent("Agent Name", "OTHER", "OTHER ROLE", CreatorType.INDIVIDUAL, "OTHER TYPE");
    sip.addAgent(agent);

    // 1.9) add a representation (status will be set to the default value, i.e.,
    // ORIGINAL)
    IPRepresentation representation1 = new IPRepresentation("representation 1");
    sip.addRepresentation(representation1);

    // 1.9.1) add a file to the representation
    IPFile representationFile = new IPFile(Paths.get("src/test/resources/eark/documentation.pdf"));
    representationFile.setRenameTo("data_.pdf");
    representation1.addFile(representationFile);

    // SIDE TEST: encoding
    if(!Utils.systemIsWindows()){
    IPFile representationFileEnc1 = new IPFile(Paths.get("src/test/resources/eark/documentation.pdf"));
    representationFileEnc1.setRenameTo("enc1_\u0001\u001F.pdf");
    representation1.addFile(representationFileEnc1);
    }

    IPFile representationFileEnc2 = new IPFile(Paths.get("src/test/resources/eark/documentation.pdf"));
    representationFileEnc2.setRenameTo("enc2_\u0080\u0081\u0090\u00FF.pdf");
    representation1.addFile(representationFileEnc2);

    IPFile representationFileEnc3 = new IPFile(Paths.get("src/test/resources/eark/documentation.pdf"));
    representationFileEnc3.setRenameTo(Utils.systemIsWindows()?"enc3_;@=&.pdf":"enc3_;?:@=&.pdf");
    representation1.addFile(representationFileEnc3);

    IPFile representationFileEnc4 = new IPFile(Paths.get("src/test/resources/eark/documentation.pdf"));
    representationFileEnc4.setRenameTo(Utils.systemIsWindows()?"enc4_#%{}\\^~[ ]`.pdf":"enc4_\"<>#%{}|\\^~[ ]`.pdf");
    representation1.addFile(representationFileEnc4);

    IPFile representationFileEnc5 = new IPFile(Paths.get("src/test/resources/eark/documentation.pdf"));
    representationFileEnc5.setRenameTo(Utils.systemIsWindows()?"enc4_+#%{}\\^~[ ]`.pdf":"enc4_\"<>#+%{}|\\^~[ ]`.pdf");
    representation1.addFile(representationFileEnc5);

    // 1.9.2) add a file to the representation and put it inside a folder
    // called 'abc' which has a folder inside called 'def'
    IPFile representationFile2 = new IPFile(Paths.get("src/test/resources/eark/documentation.pdf"));
    representationFile2.setRelativeFolders(Arrays.asList("abc", "def"));
    representation1.addFile(representationFile2);

    // 1.10) add a representation & define its status
    IPRepresentation representation2 = new IPRepresentation("representation 2");
    representation2.setStatus(new RepresentationStatus(REPRESENTATION_STATUS_NORMALIZED));
    sip.addRepresentation(representation2);

    // 1.10.1) add a file to the representation
    IPFile representationFile3 = new IPFile(Paths.get("src/test/resources/eark/documentation.pdf"));
    representationFile3.setRenameTo("data3.pdf");
    representation2.addFile(representationFile3);

    // 2) build SIP, providing an output directory
    Path zipSIP = sip.build(tempFolder);

    return zipSIP;
  }

  private void parseAndValidateFullEARKSIP(Path zipSIP) throws ParseException {

    // 1) invoke static method parse and that's it
    SIP earkSIP = EARKSIP.parse(zipSIP, tempFolder);

    // general assessment
    earkSIP.getValidationReport().getValidationEntries().stream().filter(e -> e.getLevel() == LEVEL.ERROR)
      .forEach(e -> LOGGER.error("Validation report entry: {}", e));
    Assert.assertTrue(earkSIP.getValidationReport().isValid());

    // assess # of representations
    List<IPRepresentation> representations = earkSIP.getRepresentations();
    Assert.assertThat(representations.size(), Is.is(2));

    // assess representations status
    Assert.assertThat(representations.get(0).getStatus().asString(),
      Is.is(RepresentationStatus.getORIGINAL().asString()));
    Assert.assertThat(representations.get(1).getStatus().asString(), Is.is(REPRESENTATION_STATUS_NORMALIZED));

    LOGGER.info("SIP with id '{}' parsed with success (valid? {})!", earkSIP.getId(),
      earkSIP.getValidationReport().isValid());
  }

}
