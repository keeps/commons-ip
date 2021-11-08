package org.roda_project.commons_ip2_validator;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;

import javax.xml.parsers.ParserConfigurationException;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.roda_project.commons_ip2.utils.Utils;
import org.roda_project.commons_ip2.validator.EARKSIPValidator;
import org.roda_project.commons_ip2.validator.constants.Constants;
import org.roda_project.commons_ip2.validator.observer.ProgressValidationLoggerObserver;
import org.roda_project.commons_ip2.validator.observer.ValidationObserver;
import org.roda_project.commons_ip2.validator.reporter.ValidationReportOutputJson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;

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

  // /* Simple SIP */
  // @Test
  // public void validateSimpleSipZIP() throws IOException, URISyntaxException,
  // ParserConfigurationException, SAXException {
  // LOGGER.info("Validate - Simple-EARK-SIP");
  //
  // URI resource = getClass().getResource("/").toURI();
  // Path earkSIPath =
  // Paths.get(resource).resolve("validation").resolve("Simple-EARK-SIP.zip");
  // Path reportPath =
  // Files.createTempDirectory("reports").resolve("Simple-EARK-SIP.json");
  //
  //
  // EARKSIPValidator earksipValidator = new EARKSIPValidator(earkSIPath,
  // reportPath);
  // boolean validate = earksipValidator.validate();
  // LOGGER.info("Done validate - Simple-EARK-SIP");
  //
  // Assert.assertFalse(validate);
  // }

  /* Full SIP */
  //@Test
  public void validateFullSipZIP()
    throws IOException, URISyntaxException, ParserConfigurationException, SAXException, NoSuchAlgorithmException {
    LOGGER.info("Validate - Full-EARK-SIP");

    // URI resource = getClass().getResource("/").toURI();
    Path earkSIPath = Paths.get("/home/jgomes/.roda/data/storage/aip")
      .resolve("7dd09f80-f341-4a27-86be-08d93e2adabb.zip");
    Path reportPath = Paths.get("/home/jgomes/Desktop/Compliance")
      .resolve("7dd09f80-f341-4a27-86be-08d93e2adabb-zip.json");
    ;
    Path reportPathPyIp = Paths.get("/home/jgomes/Desktop/Compliance")
      .resolve("uuid-5b7be427-9889-4f25-b36f-0b36f63db67f-PYIP.json");
    if (!reportPath.toFile().exists()) {
      try {
        Files.createFile(reportPath);
      } catch (IOException e) {
        reportPath = Files.createTempFile(Constants.VALIDATION_REPORT_PREFIX, ".json");
      }
    } else {
      Files.deleteIfExists(reportPath);
      try {
        Files.createFile(reportPath);
      } catch (IOException e) {
        reportPath = Files.createTempFile(Constants.VALIDATION_REPORT_PREFIX, ".json");
      }
    }
    OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(reportPath.toFile()));
    ValidationReportOutputJson reportOutputJson = new ValidationReportOutputJson(earkSIPath,outputStream);
    EARKSIPValidator earksipValidator = new EARKSIPValidator(reportOutputJson);
    //ValidationObserver observer = new ProgressValidationLoggerObserver();
    //earksipValidator.addObserver(observer);
    boolean validate = earksipValidator.validate();
    LOGGER.info("Done validate - Full-EARK-SIP");

    Assert.assertTrue(validate);
  }
  //
  // /* Simple SIP */
  // @Test
  // public void validateSimpleSipFolder() throws IOException, URISyntaxException,
  // ParserConfigurationException, SAXException {
  // LOGGER.info("Validate - Simple-EARK-SIP");
  //
  // URI resource = getClass().getResource("/").toURI();
  // Path earkSIPath =
  // Paths.get(resource).resolve("validation").resolve("Simple-EARK-SIP");
  // Path reportPath =
  // Files.createTempDirectory("reports").resolve("Simple-EARK-SIP.json");
  //
  //
  // EARKSIPValidator earksipValidator = new EARKSIPValidator(earkSIPath,
  // reportPath);
  // boolean validate = earksipValidator.validate();
  // LOGGER.info("Done validate - Simple-EARK-SIP");
  //
  // Assert.assertFalse(validate);
  // }
  //
  // /* Full SIP */
  // @Test
  // public void validateFullSipFolder() throws IOException, URISyntaxException,
  // ParserConfigurationException, SAXException {
  // LOGGER.info("Validate - Full-EARK-SIP");
  //
  // URI resource = getClass().getResource("/").toURI();
  // Path earkSIPath =
  // Paths.get(resource).resolve("validation").resolve("Full-EARK-SIP");
  // Path reportPath =
  // Files.createTempDirectory("reports").resolve("Full-EARK-SIP.json");
  //
  // EARKSIPValidator earksipValidator = new EARKSIPValidator(earkSIPath,
  // reportPath);
  // boolean validate = earksipValidator.validate();
  // LOGGER.info("Done validate - Full-EARK-SIP");
  //
  // Assert.assertFalse(validate);
  // }
}
