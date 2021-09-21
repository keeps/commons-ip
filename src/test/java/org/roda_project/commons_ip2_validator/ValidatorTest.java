package org.roda_project.commons_ip2_validator;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.roda_project.commons_ip2.utils.Utils;
import org.roda_project.commons_ip2.validator.EARKSIPValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;

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

//  /* Simple SIP */
//  @Test
//  public void validateSimpleSipZIP() throws IOException, URISyntaxException, ParserConfigurationException, SAXException {
//    LOGGER.info("Validate - Simple-EARK-SIP");
//
//    URI resource = getClass().getResource("/").toURI();
//    Path earkSIPath = Paths.get(resource).resolve("validation").resolve("Simple-EARK-SIP.zip");
//    Path reportPath = Files.createTempDirectory("reports").resolve("Simple-EARK-SIP.json");
//
//
//    EARKSIPValidator earksipValidator = new EARKSIPValidator(earkSIPath, reportPath);
//    boolean validate = earksipValidator.validate();
//    LOGGER.info("Done validate - Simple-EARK-SIP");
//
//    Assert.assertFalse(validate);
//  }

  /* Full SIP */
  @Test
  public void validateFullSipZIP() throws IOException, URISyntaxException, ParserConfigurationException, SAXException {
    LOGGER.info("Validate - Full-EARK-SIP");

    URI resource = getClass().getResource("/").toURI();
    Path earkSIPath = Paths.get(resource).resolve("validation").resolve("Full-EARK-SIP.zip");
    Path reportPath = Files.createTempDirectory("reports").resolve("Full-EARK-SIP.json");

    EARKSIPValidator earksipValidator = new EARKSIPValidator(earkSIPath, reportPath);
    boolean validate = earksipValidator.validate();
    LOGGER.info("Done validate - Full-EARK-SIP");

    Assert.assertFalse(validate);
  }
//
//  /* Simple SIP */
//  @Test
//  public void validateSimpleSipFolder() throws IOException, URISyntaxException, ParserConfigurationException, SAXException {
//    LOGGER.info("Validate - Simple-EARK-SIP");
//
//    URI resource = getClass().getResource("/").toURI();
//    Path earkSIPath = Paths.get(resource).resolve("validation").resolve("Simple-EARK-SIP");
//    Path reportPath = Files.createTempDirectory("reports").resolve("Simple-EARK-SIP.json");
//
//
//    EARKSIPValidator earksipValidator = new EARKSIPValidator(earkSIPath, reportPath);
//    boolean validate = earksipValidator.validate();
//    LOGGER.info("Done validate - Simple-EARK-SIP");
//
//    Assert.assertFalse(validate);
//  }
//
//  /* Full SIP */
//  @Test
//  public void validateFullSipFolder() throws IOException, URISyntaxException, ParserConfigurationException, SAXException {
//    LOGGER.info("Validate - Full-EARK-SIP");
//
//    URI resource = getClass().getResource("/").toURI();
//    Path earkSIPath = Paths.get(resource).resolve("validation").resolve("Full-EARK-SIP");
//    Path reportPath = Files.createTempDirectory("reports").resolve("Full-EARK-SIP.json");
//
//    EARKSIPValidator earksipValidator = new EARKSIPValidator(earkSIPath, reportPath);
//    boolean validate = earksipValidator.validate();
//    LOGGER.info("Done validate - Full-EARK-SIP");
//
//    Assert.assertFalse(validate);
//  }
}
