package org.roda_project.commons_ip2_validator;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.roda_project.commons_ip.model.ParseException;
import org.roda_project.commons_ip.utils.IPException;
import org.roda_project.commons_ip2.utils.Utils;
import org.roda_project.commons_ip2.validator.EARKSIPValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * @author João Gomes <jgomes@keep.pt>
 */
public class ValidatorTest {
  private static final Logger LOGGER = LoggerFactory.getLogger(ValidatorTest.class);

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
  public void validateSimpleSip() throws IPException, ParseException, InterruptedException, JAXBException, IOException, SAXException {
    LOGGER.info("Validate Simple SIP");
    Path reportPath = Paths.get("/home/jgomes/Github/test/output/test.json");
    Path earksipPath = Paths.get("./src/main/resources/sips/Simple-EARK-SIP");
    // Path earksipPath = Paths.get("./src/main/resources/sips/mets-xml_mets_OBJID_attribute_not_exist.zip");
    // Path earksipPath = Paths.get("./src/main/resources/sips/minimal_IP_with_1_representation.zip");

    EARKSIPValidator earksipValidator = new EARKSIPValidator(earksipPath,reportPath);
    earksipValidator.validate();
    LOGGER.info("Done validate simple sip");
  }
}
