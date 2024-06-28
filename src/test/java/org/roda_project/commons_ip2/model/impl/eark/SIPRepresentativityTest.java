package org.roda_project.commons_ip2.model.impl.eark;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.roda_project.commons_ip.utils.IPException;
import org.roda_project.commons_ip.utils.METSEnums;
import org.roda_project.commons_ip2.model.IPAgent;
import org.roda_project.commons_ip2.model.IPAgentNoteTypeEnum;
import org.roda_project.commons_ip2.model.IPContentInformationType;
import org.roda_project.commons_ip2.model.IPContentType;
import org.roda_project.commons_ip2.model.IPDescriptiveMetadata;
import org.roda_project.commons_ip2.model.IPFile;
import org.roda_project.commons_ip2.model.IPMetadata;
import org.roda_project.commons_ip2.model.IPRepresentation;
import org.roda_project.commons_ip2.model.MetadataType;
import org.roda_project.commons_ip2.model.SIP;
import org.roda_project.commons_ip2.utils.RepresentationUtils;
import org.roda_project.commons_ip2.utils.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * {@author João Gomes <jgomes@keep.pt>}.
 */
public class SIPRepresentativityTest {
  private static final String REPRESENTATION_STATUS_NORMALIZED = "NORMALIZED";
  private static final Logger LOGGER = LoggerFactory.getLogger(SIPRepresentativityTest.class);

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
  public void buildEARKSip2withFolders() throws IPException, InterruptedException, IOException {
    LOGGER.info("Creating EARK SIP 2");
    Path zipSIPS = createEARKSip2withFolders();
    LOGGER.info("Done creating full E-ARK SIP-S");
  }

  private Path createEARKSip2withFolders() throws IPException, InterruptedException, IOException {
    // 1) instantiate E-ARK SIP object
    SIP sip = new EARKSIP("SIP_1", IPContentType.getMIXED(), IPContentInformationType.getMIXED(), "2.1.0");
    sip.addCreatorSoftwareAgent("RODA Commons IP", "2.0.0");

    // 1.1) set optional human-readable description
    sip.setDescription("A full E-ARK SIP");

    // 1.2) add descriptive metadata (SIP level)
    IPDescriptiveMetadata metadataDescriptiveDC = new IPDescriptiveMetadata(
      new IPFile(Paths.get("src/test/resources/eark/metadata_descriptive_dc.xml")),
      new MetadataType(MetadataType.MetadataTypeEnum.DC), null);
    sip.addDescriptiveMetadata(metadataDescriptiveDC);

    // 1.3) add preservation metadata (SIP level)
    IPMetadata metadataPreservation = new IPMetadata(
      new IPFile(Paths.get("src/test/resources/eark/metadata_preservation_premis.xml")))
        .setMetadataType(MetadataType.MetadataTypeEnum.PREMIS);
    sip.addPreservationMetadata(metadataPreservation);

    // 1.4) add other metadata (SIP level)
    IPFile metadataOtherFile = new IPFile(Paths.get("src/test/resources/eark/metadata_other.txt"));
    // 1.4.1) optionally one may rename file final name
    metadataOtherFile.setRenameTo("metadata_other_renamed.txt");
    IPMetadata metadataOther = new IPMetadata(metadataOtherFile);
    sip.addOtherMetadata(metadataOther);
    metadataOtherFile = new IPFile(Paths.get("src/test/resources/eark/metadata_other.txt"));
    // 1.4.1) optionally one may rename file final name
    metadataOtherFile.setRenameTo("metadata_other_renamed2.txt");
    metadataOther = new IPMetadata(metadataOtherFile);
    sip.addOtherMetadata(metadataOther);

    // 1.5) add xml schema (SIP level)
    sip.addSchema(new IPFile(Paths.get("src/test/resources/eark/schema.xsd")));

    // 1.6) add documentation (SIP level)
    sip.addDocumentation(new IPFile(Paths.get("src/test/resources/eark/documentation.pdf")));

    // 1.7) set optional RODA related information about ancestors
    sip.setAncestors(List.of("b6f24059-8973-4582-932d-eb0b2cb48f28"));

    // 1.8) add an agent (SIP level)
    IPAgent agent = new IPAgent("Agent Name", "OTHER", "OTHER ROLE", METSEnums.CreatorType.INDIVIDUAL, "OTHER TYPE", "",
      IPAgentNoteTypeEnum.SOFTWARE_VERSION);
    sip.addAgent(agent);

    // 1.9) add a representation (status will be set to the default value, i.e.,
    // ORIGINAL)
    IPRepresentation representation1 = new IPRepresentation("representation 1");
    sip.addRepresentation(representation1);
    try {
      RepresentationUtils.includeInRepresentation(Paths.get("src/test/resources/Representative"), representation1);
    } catch (IOException e) {
      // do nothing
    }

    // 2) build SIP, providing an output directory
    final Path zipSIP = sip.build(tempFolder);

    return zipSIP;
  }
}
