package org.roda_project.commons_ip.model.bagit;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.hamcrest.core.Is;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.roda_project.commons_ip.model.IPContentType;
import org.roda_project.commons_ip.model.IPDescriptiveMetadata;
import org.roda_project.commons_ip.model.IPFile;
import org.roda_project.commons_ip.model.IPMetadata;
import org.roda_project.commons_ip.model.IPRepresentation;
import org.roda_project.commons_ip.model.MetadataType;
import org.roda_project.commons_ip.model.ParseException;
import org.roda_project.commons_ip.model.SIP;
import org.roda_project.commons_ip.model.ValidationEntry;
import org.roda_project.commons_ip.model.impl.bagit.BagitSIP;
import org.roda_project.commons_ip.utils.IPException;
import org.roda_project.commons_ip.utils.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Gabriel Barros <gbarros@keep.pt>
 */
public class BagitTest {
  private static final Logger LOGGER = LoggerFactory.getLogger(BagitTest.class);
  private static Path tempFolder;

  @BeforeClass
  public static void setup() throws IOException {
    tempFolder = Files.createTempDirectory("temp");
  }

  @AfterClass
  public static void cleanup() throws Exception {
    //Utils.deletePath(tempFolder);
  }

  @Test
  public void buildAndParseBagitSIP() throws IPException, InterruptedException, ParseException {
    LOGGER.info("Creating Bagit SIP");
    Path bagitSIP = createBagitSIP();
    LOGGER.info("Done creating Bagit SIP");

    LOGGER.info("Parsing (and validating) Bagit SIP");
    parseAndValidateBagitSIP(bagitSIP);
    LOGGER.info("Done parsing (and validating) Bagit SIP");
  }

  public Path createBagitSIP() throws IPException, InterruptedException {
    // 1) instantiate E-ARK SIP object
    BagitSIP sip = new BagitSIP("SIP_1", IPContentType.getMIXED());
    sip.addCreatorSoftwareAgent("RODA Commons IP");

    // 1.1) set optional human-readable description
    sip.setDescription("A Bagit SIP");

    // 1.2) add descriptive metadata (SIP level)
    IPDescriptiveMetadata metadataDescriptiveDC = new IPDescriptiveMetadata(
      new IPFile(Paths.get("src/test/resources/bagit/bag-info.txt")),
      new MetadataType(MetadataType.MetadataTypeEnum.OTHER), null);
    sip.addDescriptiveMetadata(metadataDescriptiveDC);

    IPRepresentation representation1 = new IPRepresentation("representation 1");
    sip.addRepresentation(representation1);

    IPFile representationFile = new IPFile(Paths.get("src/test/resources/eark/documentation.pdf"));
    representationFile.setRenameTo("data.pdf");
    representation1.addFile(representationFile);

    Path zipSIP = sip.build(tempFolder);
    return zipSIP;
  }

  private void parseAndValidateBagitSIP(Path zipSIP) throws ParseException {

    // 1) invoke static method parse and that's it
    SIP bagitSIP = BagitSIP.parse(zipSIP, tempFolder);

    // general assessment
    bagitSIP.getValidationReport().getValidationEntries().stream()
      .filter(e -> e.getLevel() == ValidationEntry.LEVEL.ERROR)
      .forEach(e -> LOGGER.error("Validation report entry: {}", e));
    Assert.assertTrue(bagitSIP.getValidationReport().isValid());

    // assess # of representations
    List<IPRepresentation> representations = bagitSIP.getRepresentations();
    Assert.assertThat(representations.size(), Is.is(1));

    LOGGER.info("SIP with id '{}' parsed with success (valid? {})!", bagitSIP.getId(),
      bagitSIP.getValidationReport().isValid());
  }
}
