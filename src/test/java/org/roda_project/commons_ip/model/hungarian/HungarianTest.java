/**
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE file at the root of the source
 * tree and available online at
 *
 * https://github.com/keeps/commons-ip
 */
package org.roda_project.commons_ip.model.hungarian;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.roda_project.commons_ip.model.IPContentType;
import org.roda_project.commons_ip.model.IPDescriptiveMetadata;
import org.roda_project.commons_ip.model.IPFile;
import org.roda_project.commons_ip.model.IPRepresentation;
import org.roda_project.commons_ip.model.MetadataType;
import org.roda_project.commons_ip.model.MetadataType.MetadataTypeEnum;
import org.roda_project.commons_ip.model.ParseException;
import org.roda_project.commons_ip.model.RepresentationStatus;
import org.roda_project.commons_ip.model.SIP;
import org.roda_project.commons_ip.model.impl.hungarian.HungarianSIP;
import org.roda_project.commons_ip.utils.IPException;
import org.roda_project.commons_ip.utils.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Unit tests for EARK Information Packages (SIP, AIP and DIP)
 */
public class HungarianTest {
  private static final Logger LOGGER = LoggerFactory.getLogger(HungarianTest.class);
  private static final String REPRESENTATION_STATUS_NORMALIZED = "NORMALIZED";
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
  public void buildAndParseHungarianSIP() throws IPException, ParseException, InterruptedException {
    LOGGER.info("Creating full Hungarian SIP");
    createFullHungarianSIP();
    LOGGER.info("Done creating full Hungarian SIP");
  }

  private Path createFullHungarianSIP() throws IPException, InterruptedException {
    // 1) instantiate Hungarian SIP object
    SIP sip = new HungarianSIP("SIP_1", IPContentType.getMIXED(), "RODA Commons IP");

    // 1.1) set optional human-readable description
    sip.setDescription("A full Hungarian SIP");

    // 1.2) add descriptive metadata (SIP level)
    IPDescriptiveMetadata metadataDescriptiveEAD = new IPDescriptiveMetadata(
      new IPFile(Paths.get("src/test/resources/eark/metadata_descriptive_ead2002.xml")),
      new MetadataType(MetadataTypeEnum.EAD), null);
    sip.addDescriptiveMetadata(metadataDescriptiveEAD);

    // 1.6) add documentation (SIP level)
    sip.addDocumentation(new IPFile(Paths.get("src/test/resources/eark/documentation.pdf")));

    // 1.9) add a representation (status will be set to the default value, i.e.,
    // ORIGINAL)
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

    // 1.10) add a representation & define its status
    IPRepresentation representation2 = new IPRepresentation("representation 2");
    representation2.setStatus(new RepresentationStatus(REPRESENTATION_STATUS_NORMALIZED));
    sip.addRepresentation(representation2);

    // 1.10.1) add a file to the representation
    IPFile representationFile3 = new IPFile(Paths.get("src/test/resources/eark/documentation.pdf"));
    representationFile3.setRenameTo("data3.pdf");
    representation2.addFile(representationFile3);

    // 2) build SIP, providing an output directory
    return sip.build(tempFolder);
  }

}
