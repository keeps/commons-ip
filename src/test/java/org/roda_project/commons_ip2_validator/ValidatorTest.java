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

  /* CSIPSTR4*/
//  @Test
//  public void validateCSIPSTR4_1() {
//    LOGGER.info("Validate CSIPSTR4 - IP_18000_CSIPSTR4_1");
//    Path reportPath = Paths.get("/home/jgomes/Desktop/Demo/Reports/CSIPSTR/CSIPSTR4/IP_18000_CSIPSTR4_1.json");
//    Path earksipPath = Paths.get("/home/jgomes/Desktop/Demo/SIPS/CSIPSTR/CSIPSTR4/IP_18000_CSIPSTR4_1.zip");
//
//    EARKSIPValidator earksipValidator = new EARKSIPValidator(earksipPath,reportPath);
//    earksipValidator.validate();
//    LOGGER.info("Done validate CSIPSTR4 - IP_18000_CSIPSTR4_1");
//  }
//
//  @Test
//  public void validateCSIPSTR4_2() {
//    LOGGER.info("Validate CSIPSTR4 - IP_18000_CSIPSTR4_2");
//    Path reportPath = Paths.get("/home/jgomes/Desktop/Demo/Reports/CSIPSTR/CSIPSTR4/IP_18000_CSIPSTR4_2.json");
//    Path earksipPath = Paths.get("/home/jgomes/Desktop/Demo/SIPS/CSIPSTR/CSIPSTR4/IP_18000_CSIPSTR4_2.zip");
//
//    EARKSIPValidator earksipValidator = new EARKSIPValidator(earksipPath,reportPath);
//    earksipValidator.validate();
//    LOGGER.info("Done validate CSIPSTR4 - IP_18000_CSIPSTR4_2");
//  }
//
//  @Test
//  public void validateCSIPSTR4_3() {
//    LOGGER.info("Validate CSIPSTR4 - IP_18000_CSIPSTR4_3");
//    Path reportPath = Paths.get("/home/jgomes/Desktop/Demo/Reports/CSIPSTR/CSIPSTR4/IP_18000_CSIPSTR4_3.json");
//    Path earksipPath = Paths.get("/home/jgomes/Desktop/Demo/SIPS/CSIPSTR/CSIPSTR4/IP_18000_CSIPSTR4_3.zip");
//
//    EARKSIPValidator earksipValidator = new EARKSIPValidator(earksipPath,reportPath);
//    earksipValidator.validate();
//    LOGGER.info("Done validate CSIPSTR4 - IP_18000_CSIPSTR4_3");
//  }
//
//  @Test
//  public void validateCSIPSTR4_4() {
//    LOGGER.info("Validate CSIPSTR4 - IP_18000_CSIPSTR4_4");
//    Path reportPath = Paths.get("/home/jgomes/Desktop/Demo/Reports/CSIPSTR/CSIPSTR4/IP_18000_CSIPSTR4_4.json");
//    Path earksipPath = Paths.get("/home/jgomes/Desktop/Demo/SIPS/CSIPSTR/CSIPSTR4/IP_18000_CSIPSTR4_4.zip");
//
//    EARKSIPValidator earksipValidator = new EARKSIPValidator(earksipPath,reportPath);
//    earksipValidator.validate();
//    LOGGER.info("Done validate CSIPSTR4 - IP_18000_CSIPSTR4_4");
//  }
//
//  @Test
//  public void validateCSIPSTR4_5() {
//    LOGGER.info("Validate CSIPSTR4 - IP_18000_CSIPSTR4_5");
//    Path reportPath = Paths.get("/home/jgomes/Desktop/Demo/Reports/CSIPSTR/CSIPSTR4/IP_18000_CSIPSTR4_5.json");
//    Path earksipPath = Paths.get("/home/jgomes/Desktop/Demo/SIPS/CSIPSTR/CSIPSTR4/IP_18000_CSIPSTR4_5.zip");
//
//    EARKSIPValidator earksipValidator = new EARKSIPValidator(earksipPath,reportPath);
//    earksipValidator.validate();
//    LOGGER.info("Done validate CSIPSTR4 - IP_18000_CSIPSTR4_5");
//  }
//
//  @Test
//  public void validateCSIPSTR4_6() {
//    LOGGER.info("Validate CSIPSTR4 - IP_18000_CSIPSTR4_6");
//    Path reportPath = Paths.get("/home/jgomes/Desktop/Demo/Reports/CSIPSTR/CSIPSTR4/IP_18000_CSIPSTR4_6.json");
//    Path earksipPath = Paths.get("/home/jgomes/Desktop/Demo/SIPS/CSIPSTR/CSIPSTR4/IP_18000_CSIPSTR4_6.zip");
//
//    EARKSIPValidator earksipValidator = new EARKSIPValidator(earksipPath,reportPath);
//    earksipValidator.validate();
//    LOGGER.info("Done validate CSIPSTR4 - IP_18000_CSIPSTR4_6");
//  }
//
//  @Test
//  public void validateCSIPSTR4_7() {
//    LOGGER.info("Validate CSIPSTR4 - IP_18000_CSIPSTR4_7");
//    Path reportPath = Paths.get("/home/jgomes/Desktop/Demo/Reports/CSIPSTR/CSIPSTR4/IP_18000_CSIPSTR4_7.json");
//    Path earksipPath = Paths.get("/home/jgomes/Desktop/Demo/SIPS/CSIPSTR/CSIPSTR4/IP_18000_CSIPSTR4_7.zip");
//
//    EARKSIPValidator earksipValidator = new EARKSIPValidator(earksipPath,reportPath);
//    earksipValidator.validate();
//    LOGGER.info("Done validate CSIPSTR4 - IP_18000_CSIPSTR4_7");
//  }
//
//  @Test
//  public void validateCSIPSTR4_8() {
//    LOGGER.info("Validate CSIPSTR4 - IP_18000_CSIPSTR4_8");
//    Path reportPath = Paths.get("/home/jgomes/Desktop/Demo/Reports/CSIPSTR/CSIPSTR4/IP_18000_CSIPSTR4_8.json");
//    Path earksipPath = Paths.get("/home/jgomes/Desktop/Demo/SIPS/CSIPSTR/CSIPSTR4/IP_18000_CSIPSTR4_8.zip");
//
//    EARKSIPValidator earksipValidator = new EARKSIPValidator(earksipPath,reportPath);
//    earksipValidator.validate();
//    LOGGER.info("Done validate CSIPSTR4 - IP_18000_CSIPSTR4_8");
//  }
//
//  @Test
//  public void validateCSIPSTR4_9() {
//    LOGGER.info("Validate CSIPSTR4 - IP_18000_CSIPSTR4_9");
//    Path reportPath = Paths.get("/home/jgomes/Desktop/Demo/Reports/CSIPSTR/CSIPSTR4/IP_18000_CSIPSTR4_9.json");
//    Path earksipPath = Paths.get("/home/jgomes/Desktop/Demo/SIPS/CSIPSTR/CSIPSTR4/IP_18000_CSIPSTR4_9.zip");
//
//    EARKSIPValidator earksipValidator = new EARKSIPValidator(earksipPath,reportPath);
//    earksipValidator.validate();
//    LOGGER.info("Done validate CSIPSTR4 - IP_18000_CSIPSTR4_9");
//  }
//
//  @Test
//  public void validateCSIPSTR4_10() {
//    LOGGER.info("Validate CSIPSTR4 - IP_18000_CSIPSTR4_10");
//    Path reportPath = Paths.get("/home/jgomes/Desktop/Demo/Reports/CSIPSTR/CSIPSTR4/IP_18000_CSIPSTR4_10.json");
//    Path earksipPath = Paths.get("/home/jgomes/Desktop/Demo/SIPS/CSIPSTR/CSIPSTR4/IP_18000_CSIPSTR4_10.zip");
//
//    EARKSIPValidator earksipValidator = new EARKSIPValidator(earksipPath,reportPath);
//    earksipValidator.validate();
//    LOGGER.info("Done validate CSIPSTR4 - IP_18000_CSIPSTR4_10");
//  }
//
//  @Test
//  public void validateCSIPSTR4_11() {
//    LOGGER.info("Validate CSIPSTR4 - IP_18000_CSIPSTR4_11");
//    Path reportPath = Paths.get("/home/jgomes/Desktop/Demo/Reports/CSIPSTR/CSIPSTR4/IP_18000_CSIPSTR4_11.json");
//    Path earksipPath = Paths.get("/home/jgomes/Desktop/Demo/SIPS/CSIPSTR/CSIPSTR4/IP_18000_CSIPSTR4_11.zip");
//
//    EARKSIPValidator earksipValidator = new EARKSIPValidator(earksipPath,reportPath);
//    earksipValidator.validate();
//    LOGGER.info("Done validate CSIPSTR4 - IP_18000_CSIPSTR4_11");
//  }
//
//  @Test
//  public void validateCSIPSTR4_12() {
//    LOGGER.info("Validate CSIPSTR4 - IP_18000_CSIPSTR4_12");
//    Path reportPath = Paths.get("/home/jgomes/Desktop/Demo/Reports/CSIPSTR/CSIPSTR4/IP_18000_CSIPSTR4_12.json");
//    Path earksipPath = Paths.get("/home/jgomes/Desktop/Demo/SIPS/CSIPSTR/CSIPSTR4/IP_18000_CSIPSTR4_12.zip");
//
//    EARKSIPValidator earksipValidator = new EARKSIPValidator(earksipPath,reportPath);
//    earksipValidator.validate();
//    LOGGER.info("Done validate CSIPSTR4 - IP_18000_CSIPSTR4_12");
//  }
//
//  @Test
//  public void validateCSIPSTR4_13() {
//    LOGGER.info("Validate CSIPSTR4 - IP_18000_CSIPSTR4_13");
//    Path reportPath = Paths.get("/home/jgomes/Desktop/Demo/Reports/CSIPSTR/CSIPSTR4/IP_18000_CSIPSTR4_13.json");
//    Path earksipPath = Paths.get("/home/jgomes/Desktop/Demo/SIPS/CSIPSTR/CSIPSTR4/IP_18000_CSIPSTR4_13.zip");
//
//    EARKSIPValidator earksipValidator = new EARKSIPValidator(earksipPath,reportPath);
//    earksipValidator.validate();
//    LOGGER.info("Done validate CSIPSTR4 - IP_18000_CSIPSTR4_13");
//  }
//
//  @Test
//  public void validateCSIPSTR4_14() {
//    LOGGER.info("Validate CSIPSTR4 - IP_18000_CSIPSTR4_14");
//    Path reportPath = Paths.get("/home/jgomes/Desktop/Demo/Reports/CSIPSTR/CSIPSTR4/IP_18000_CSIPSTR4_14.json");
//    Path earksipPath = Paths.get("/home/jgomes/Desktop/Demo/SIPS/CSIPSTR/CSIPSTR4/IP_18000_CSIPSTR4_14.zip");
//
//    EARKSIPValidator earksipValidator = new EARKSIPValidator(earksipPath,reportPath);
//    earksipValidator.validate();
//    LOGGER.info("Done validate CSIPSTR4 - IP_18000_CSIPSTR4_14");
//  }
//
//  @Test
//  public void validateCSIPSTR4_15() {
//    LOGGER.info("Validate CSIPSTR4 - IP_18000_CSIPSTR4_15");
//    Path reportPath = Paths.get("/home/jgomes/Desktop/Demo/Reports/CSIPSTR/CSIPSTR4/IP_18000_CSIPSTR4_15.json");
//    Path earksipPath = Paths.get("/home/jgomes/Desktop/Demo/SIPS/CSIPSTR/CSIPSTR4/IP_18000_CSIPSTR4_15.zip");
//
//    EARKSIPValidator earksipValidator = new EARKSIPValidator(earksipPath,reportPath);
//    earksipValidator.validate();
//    LOGGER.info("Done validate CSIPSTR4 - IP_18000_CSIPSTR4_15");
//  }
//
//  @Test
//  public void validateCSIPSTR4_16() {
//    LOGGER.info("Validate CSIPSTR4 - IP_18000_CSIPSTR4_16");
//    Path reportPath = Paths.get("/home/jgomes/Desktop/Demo/Reports/CSIPSTR/CSIPSTR4/IP_18000_CSIPSTR4_16.json");
//    Path earksipPath = Paths.get("/home/jgomes/Desktop/Demo/SIPS/CSIPSTR/CSIPSTR4/IP_18000_CSIPSTR4_16.zip");
//
//    EARKSIPValidator earksipValidator = new EARKSIPValidator(earksipPath,reportPath);
//    earksipValidator.validate();
//    LOGGER.info("Done validate CSIPSTR4 - IP_18000_CSIPSTR4_16");
//  }

  /* CSIPSTR5 */

//  @Test
//  public void validateCSIPSTR5_1() {
//    LOGGER.info("Validate CSIPSTR5 - IP_18000_CSIPSTR5_1");
//    Path reportPath = Paths.get("/home/jgomes/Desktop/Demo/Reports/CSIPSTR/CSIPSTR5/IP_18000_CSIPSTR5_1.json");
//    Path earksipPath = Paths.get("/home/jgomes/Desktop/Demo/SIPS/CSIPSTR/CSIPSTR5/IP_18000_CSIPSTR5_1.zip");
//
//    EARKSIPValidator earksipValidator = new EARKSIPValidator(earksipPath,reportPath);
//    earksipValidator.validate();
//    LOGGER.info("Done validate CSIPSTR5 - IP_18000_CSIPSTR5_1");
//  }

//  @Test
//  public void validateCSIPSTR5_2() {
//    LOGGER.info("Validate CSIPSTR5 - IP_18000_CSIPSTR5_2");
//    Path reportPath = Paths.get("/home/jgomes/Desktop/Demo/Reports/CSIPSTR/CSIPSTR5/IP_18000_CSIPSTR5_2.json");
//    Path earksipPath = Paths.get("/home/jgomes/Desktop/Demo/SIPS/CSIPSTR/CSIPSTR5/IP_18000_CSIPSTR5_2.zip");
//
//    EARKSIPValidator earksipValidator = new EARKSIPValidator(earksipPath,reportPath);
//    earksipValidator.validate();
//    LOGGER.info("Done validate CSIPSTR5 - IP_18000_CSIPSTR5_2");
//  }
//
//  @Test
//  public void validateCSIPSTR5_3() {
//    LOGGER.info("Validate CSIPSTR5 - IP_18000_CSIPSTR5_3");
//    Path reportPath = Paths.get("/home/jgomes/Desktop/Demo/Reports/CSIPSTR/CSIPSTR5/IP_18000_CSIPSTR5_3.json");
//    Path earksipPath = Paths.get("/home/jgomes/Desktop/Demo/SIPS/CSIPSTR/CSIPSTR5/IP_18000_CSIPSTR5_3.zip");
//
//    EARKSIPValidator earksipValidator = new EARKSIPValidator(earksipPath,reportPath);
//    earksipValidator.validate();
//    LOGGER.info("Done validate CSIPSTR5 - IP_18000_CSIPSTR5_3");
//  }
//
//  @Test
//  public void validateCSIPSTR5_4() {
//    LOGGER.info("Validate CSIPSTR5 - IP_18000_CSIPSTR5_4");
//    Path reportPath = Paths.get("/home/jgomes/Desktop/Demo/Reports/CSIPSTR/CSIPSTR5/IP_18000_CSIPSTR5_4.json");
//    Path earksipPath = Paths.get("/home/jgomes/Desktop/Demo/SIPS/CSIPSTR/CSIPSTR5/IP_18000_CSIPSTR5_4.zip");
//
//    EARKSIPValidator earksipValidator = new EARKSIPValidator(earksipPath,reportPath);
//    earksipValidator.validate();
//    LOGGER.info("Done validate CSIPSTR5 - IP_18000_CSIPSTR5_4");
//  }
//
//  @Test
//  public void validateCSIPSTR5_5() {
//    LOGGER.info("Validate CSIPSTR5 - IP_18000_CSIPSTR5_5");
//    Path reportPath = Paths.get("/home/jgomes/Desktop/Demo/Reports/CSIPSTR/CSIPSTR5/IP_18000_CSIPSTR5_5.json");
//    Path earksipPath = Paths.get("/home/jgomes/Desktop/Demo/SIPS/CSIPSTR/CSIPSTR5/IP_18000_CSIPSTR5_5.zip");
//
//    EARKSIPValidator earksipValidator = new EARKSIPValidator(earksipPath,reportPath);
//    earksipValidator.validate();
//    LOGGER.info("Done validate CSIPSTR5 - IP_18000_CSIPSTR5_5");
//  }

//  @Test
//  public void validateCSIPSTR5_6() {
//    LOGGER.info("Validate CSIPSTR5 - IP_18000_CSIPSTR5_6");
//    Path reportPath = Paths.get("/home/jgomes/Desktop/Demo/Reports/CSIPSTR/CSIPSTR5/IP_18000_CSIPSTR5_6.json");
//    Path earksipPath = Paths.get("/home/jgomes/Desktop/Demo/SIPS/CSIPSTR/CSIPSTR5/IP_18000_CSIPSTR5_6.zip");
//
//    EARKSIPValidator earksipValidator = new EARKSIPValidator(earksipPath,reportPath);
//    earksipValidator.validate();
//    LOGGER.info("Done validate CSIPSTR5 - IP_18000_CSIPSTR5_6");
//  }
//
//  @Test
//  public void validateCSIPSTR5_7() {
//    LOGGER.info("Validate CSIPSTR5 - IP_18000_CSIPSTR5_7");
//    Path reportPath = Paths.get("/home/jgomes/Desktop/Demo/Reports/CSIPSTR/CSIPSTR5/IP_18000_CSIPSTR5_7.json");
//    Path earksipPath = Paths.get("/home/jgomes/Desktop/Demo/SIPS/CSIPSTR/CSIPSTR5/IP_18000_CSIPSTR5_7.zip");
//
//    EARKSIPValidator earksipValidator = new EARKSIPValidator(earksipPath,reportPath);
//    earksipValidator.validate();
//    LOGGER.info("Done validate CSIPSTR5 - IP_18000_CSIPSTR5_7");
//  }
//
//  @Test
//  public void validateCSIPSTR5_8() {
//    LOGGER.info("Validate CSIPSTR5 - IP_18000_CSIPSTR5_8");
//    Path reportPath = Paths.get("/home/jgomes/Desktop/Demo/Reports/CSIPSTR/CSIPSTR5/IP_18000_CSIPSTR5_8.json");
//    Path earksipPath = Paths.get("/home/jgomes/Desktop/Demo/SIPS/CSIPSTR/CSIPSTR5/IP_18000_CSIPSTR5_8.zip");
//
//    EARKSIPValidator earksipValidator = new EARKSIPValidator(earksipPath,reportPath);
//    earksipValidator.validate();
//    LOGGER.info("Done validate CSIPSTR5 - IP_18000_CSIPSTR5_8");
//  }
//
//  @Test
//  public void validateCSIPSTR5_9() {
//    LOGGER.info("Validate CSIPSTR5 - IP_18000_CSIPSTR5_9");
//    Path reportPath = Paths.get("/home/jgomes/Desktop/Demo/Reports/CSIPSTR/CSIPSTR5/IP_18000_CSIPSTR5_9.json");
//    Path earksipPath = Paths.get("/home/jgomes/Desktop/Demo/SIPS/CSIPSTR/CSIPSTR5/IP_18000_CSIPSTR5_9.zip");
//
//    EARKSIPValidator earksipValidator = new EARKSIPValidator(earksipPath,reportPath);
//    earksipValidator.validate();
//    LOGGER.info("Done validate CSIPSTR5 - IP_18000_CSIPSTR5_9");
//  }
////
//  @Test
//  public void validateCSIPSTR5_10() {
//    LOGGER.info("Validate CSIPSTR5 - IP_18000_CSIPSTR5_10");
//    Path reportPath = Paths.get("/home/jgomes/Desktop/Demo/Reports/CSIPSTR/CSIPSTR5/IP_18000_CSIPSTR5_10.json");
//    Path earksipPath = Paths.get("/home/jgomes/Desktop/Demo/SIPS/CSIPSTR/CSIPSTR5/IP_18000_CSIPSTR5_10.zip");
//
//    EARKSIPValidator earksipValidator = new EARKSIPValidator(earksipPath,reportPath);
//    earksipValidator.validate();
//    LOGGER.info("Done validate CSIPSTR5 - IP_18000_CSIPSTR5_10");
//  }
//
//  @Test
//  public void validateCSIPSTR5_11() {
//    LOGGER.info("Validate CSIPSTR5 - IP_18000_CSIPSTR5_11");
//    Path reportPath = Paths.get("/home/jgomes/Desktop/Demo/Reports/CSIPSTR/CSIPSTR5/IP_18000_CSIPSTR5_11.json");
//    Path earksipPath = Paths.get("/home/jgomes/Desktop/Demo/SIPS/CSIPSTR/CSIPSTR5/IP_18000_CSIPSTR5_11.zip");
//
//    EARKSIPValidator earksipValidator = new EARKSIPValidator(earksipPath,reportPath);
//    earksipValidator.validate();
//    LOGGER.info("Done validate CSIPSTR5 - IP_18000_CSIPSTR5_11");
//  }
//
//  @Test
//  public void validateCSIPSTR5_12() {
//    LOGGER.info("Validate CSIPSTR5 - IP_18000_CSIPSTR5_12");
//    Path reportPath = Paths.get("/home/jgomes/Desktop/Demo/Reports/CSIPSTR/CSIPSTR5/IP_18000_CSIPSTR5_12.json");
//    Path earksipPath = Paths.get("/home/jgomes/Desktop/Demo/SIPS/CSIPSTR/CSIPSTR5/IP_18000_CSIPSTR5_12.zip");
//
//    EARKSIPValidator earksipValidator = new EARKSIPValidator(earksipPath,reportPath);
//    earksipValidator.validate();
//    LOGGER.info("Done validate CSIPSTR5 - IP_18000_CSIPSTR5_12");
//  }
//
//  @Test
//  public void validateCSIPSTR5_13() {
//    LOGGER.info("Validate CSIPSTR5 - IP_18000_CSIPSTR5_13");
//    Path reportPath = Paths.get("/home/jgomes/Desktop/Demo/Reports/CSIPSTR/CSIPSTR5/IP_18000_CSIPSTR5_13.json");
//    Path earksipPath = Paths.get("/home/jgomes/Desktop/Demo/SIPS/CSIPSTR/CSIPSTR5/IP_18000_CSIPSTR5_13.zip");
//
//    EARKSIPValidator earksipValidator = new EARKSIPValidator(earksipPath,reportPath);
//    earksipValidator.validate();
//    LOGGER.info("Done validate CSIPSTR5 - IP_18000_CSIPSTR5_13");
//  }
//
//  @Test
//  public void validateCSIPSTR5_14() {
//    LOGGER.info("Validate CSIPSTR5 - IP_18000_CSIPSTR5_14");
//    Path reportPath = Paths.get("/home/jgomes/Desktop/Demo/Reports/CSIPSTR/CSIPSTR5/IP_18000_CSIPSTR5_14.json");
//    Path earksipPath = Paths.get("/home/jgomes/Desktop/Demo/SIPS/CSIPSTR/CSIPSTR5/IP_18000_CSIPSTR5_14.zip");
//
//    EARKSIPValidator earksipValidator = new EARKSIPValidator(earksipPath,reportPath);
//    earksipValidator.validate();
//    LOGGER.info("Done validate CSIPSTR5 - IP_18000_CSIPSTR5_14");
//  }
//
//  @Test
//  public void validateCSIPSTR5_15() {
//    LOGGER.info("Validate CSIPSTR5 - IP_18000_CSIPSTR5_15");
//    Path reportPath = Paths.get("/home/jgomes/Desktop/Demo/Reports/CSIPSTR/CSIPSTR5/IP_18000_CSIPSTR5_15.json");
//    Path earksipPath = Paths.get("/home/jgomes/Desktop/Demo/SIPS/CSIPSTR/CSIPSTR5/IP_18000_CSIPSTR5_15.zip");
//
//    EARKSIPValidator earksipValidator = new EARKSIPValidator(earksipPath,reportPath);
//    earksipValidator.validate();
//    LOGGER.info("Done validate CSIPSTR5 - IP_18000_CSIPSTR5_15");
//  }

//  /* CSIPSTR9 */
//  @Test
//  public void validateCSIPSTR9_1() {
//    LOGGER.info("Validate CSIPSTR9 - IP_18000_CSIPSTR9_1");
//    Path reportPath = Paths.get("/home/jgomes/Desktop/Demo/Reports/CSIPSTR/CSIPSTR9/IP_18000_CSIPSTR9_1.json");
//    Path earksipPath = Paths.get("/home/jgomes/Desktop/Demo/SIPS/CSIPSTR/CSIPSTR9/IP_18000_CSIPSTR9_1.zip");
//
//    EARKSIPValidator earksipValidator = new EARKSIPValidator(earksipPath,reportPath);
//    earksipValidator.validate();
//    LOGGER.info("Done validate CSIPSTR9 - IP_18000_CSIPSTR9_1");
//  }
//
//  @Test
//  public void validateCSIPSTR9_2() {
//    LOGGER.info("Validate CSIPSTR9 - IP_18000_CSIPSTR9_2");
//    Path reportPath = Paths.get("/home/jgomes/Desktop/Demo/Reports/CSIPSTR/CSIPSTR9/IP_18000_CSIPSTR9_2.json");
//    Path earksipPath = Paths.get("/home/jgomes/Desktop/Demo/SIPS/CSIPSTR/CSIPSTR9/IP_18000_CSIPSTR9_2.zip");
//
//    EARKSIPValidator earksipValidator = new EARKSIPValidator(earksipPath,reportPath);
//    earksipValidator.validate();
//    LOGGER.info("Done validate CSIPSTR9 - IP_18000_CSIPSTR9_2");
//  }
//
//  @Test
//  public void validateCSIPSTR9_3() {
//    LOGGER.info("Validate CSIPSTR9 - IP_18000_CSIPSTR9_3");
//    Path reportPath = Paths.get("/home/jgomes/Desktop/Demo/Reports/CSIPSTR/CSIPSTR9/IP_18000_CSIPSTR9_3.json");
//    Path earksipPath = Paths.get("/home/jgomes/Desktop/Demo/SIPS/CSIPSTR/CSIPSTR9/IP_18000_CSIPSTR9_3.zip");
//
//    EARKSIPValidator earksipValidator = new EARKSIPValidator(earksipPath,reportPath);
//    earksipValidator.validate();
//    LOGGER.info("Done validate CSIPSTR9 - IP_18000_CSIPSTR9_3");
//  }
//
//  @Test
//  public void validateCSIPSTR9_4() {
//    LOGGER.info("Validate CSIPSTR9 - IP_18000_CSIPSTR9_4");
//    Path reportPath = Paths.get("/home/jgomes/Desktop/Demo/Reports/CSIPSTR/CSIPSTR9/IP_18000_CSIPSTR9_4.json");
//    Path earksipPath = Paths.get("/home/jgomes/Desktop/Demo/SIPS/CSIPSTR/CSIPSTR9/IP_18000_CSIPSTR9_4.zip");
//
//    EARKSIPValidator earksipValidator = new EARKSIPValidator(earksipPath,reportPath);
//    earksipValidator.validate();
//    LOGGER.info("Done validate CSIPSTR9 - IP_18000_CSIPSTR9_4");
//  }
//
//  @Test
//  public void validateCSIPSTR9_5() {
//    LOGGER.info("Validate CSIPSTR9 - IP_18000_CSIPSTR9_5");
//    Path reportPath = Paths.get("/home/jgomes/Desktop/Demo/Reports/CSIPSTR/CSIPSTR9/IP_18000_CSIPSTR9_5.json");
//    Path earksipPath = Paths.get("/home/jgomes/Desktop/Demo/SIPS/CSIPSTR/CSIPSTR9/IP_18000_CSIPSTR9_5.zip");
//
//    EARKSIPValidator earksipValidator = new EARKSIPValidator(earksipPath,reportPath);
//    earksipValidator.validate();
//    LOGGER.info("Done validate CSIPSTR9 - IP_18000_CSIPSTR9_5");
//  }
//
//  @Test
//  public void validateCSIPSTR9_6() {
//    LOGGER.info("Validate CSIPSTR9 - IP_18000_CSIPSTR9_6");
//    Path reportPath = Paths.get("/home/jgomes/Desktop/Demo/Reports/CSIPSTR/CSIPSTR9/IP_18000_CSIPSTR9_6.json");
//    Path earksipPath = Paths.get("/home/jgomes/Desktop/Demo/SIPS/CSIPSTR/CSIPSTR9/IP_18000_CSIPSTR9_6.zip");
//
//    EARKSIPValidator earksipValidator = new EARKSIPValidator(earksipPath,reportPath);
//    earksipValidator.validate();
//    LOGGER.info("Done validate CSIPSTR9 - IP_18000_CSIPSTR9_6");
//  }
//
//  @Test
//  public void validateCSIPSTR9_7() {
//    LOGGER.info("Validate CSIPSTR9 - IP_18000_CSIPSTR9_7");
//    Path reportPath = Paths.get("/home/jgomes/Desktop/Demo/Reports/CSIPSTR/CSIPSTR9/IP_18000_CSIPSTR9_7.json");
//    Path earksipPath = Paths.get("/home/jgomes/Desktop/Demo/SIPS/CSIPSTR/CSIPSTR9/IP_18000_CSIPSTR9_7.zip");
//
//    EARKSIPValidator earksipValidator = new EARKSIPValidator(earksipPath,reportPath);
//    earksipValidator.validate();
//    LOGGER.info("Done validate CSIPSTR9 - IP_18000_CSIPSTR9_7");
//  }
//
//  @Test
//  public void validateCSIPSTR9_8() {
//    LOGGER.info("Validate CSIPSTR9 - IP_18000_CSIPSTR9_8");
//    Path reportPath = Paths.get("/home/jgomes/Desktop/Demo/Reports/CSIPSTR/CSIPSTR9/IP_18000_CSIPSTR9_8.json");
//    Path earksipPath = Paths.get("/home/jgomes/Desktop/Demo/SIPS/CSIPSTR/CSIPSTR9/IP_18000_CSIPSTR9_8.zip");
//
//    EARKSIPValidator earksipValidator = new EARKSIPValidator(earksipPath,reportPath);
//    earksipValidator.validate();
//    LOGGER.info("Done validate CSIPSTR9 - IP_18000_CSIPSTR9_8");
//  }
//
//  @Test
//  public void validateCSIPSTR9_9() {
//    LOGGER.info("Validate CSIPSTR9 - IP_18000_CSIPSTR9_9");
//    Path reportPath = Paths.get("/home/jgomes/Desktop/Demo/Reports/CSIPSTR/CSIPSTR9/IP_18000_CSIPSTR9_9.json");
//    Path earksipPath = Paths.get("/home/jgomes/Desktop/Demo/SIPS/CSIPSTR/CSIPSTR9/IP_18000_CSIPSTR9_9.zip");
//
//    EARKSIPValidator earksipValidator = new EARKSIPValidator(earksipPath,reportPath);
//    earksipValidator.validate();
//    LOGGER.info("Done validate CSIPSTR9 - IP_18000_CSIPSTR9_9");
//  }
//
//  @Test
//  public void validateCSIPSTR9_10() {
//    LOGGER.info("Validate CSIPSTR9 - IP_18000_CSIPSTR9_10");
//    Path reportPath = Paths.get("/home/jgomes/Desktop/Demo/Reports/CSIPSTR/CSIPSTR9/IP_18000_CSIPSTR9_10.json");
//    Path earksipPath = Paths.get("/home/jgomes/Desktop/Demo/SIPS/CSIPSTR/CSIPSTR9/IP_18000_CSIPSTR9_10.zip");
//
//    EARKSIPValidator earksipValidator = new EARKSIPValidator(earksipPath,reportPath);
//    earksipValidator.validate();
//    LOGGER.info("Done validate CSIPSTR9 - IP_18000_CSIPSTR9_10");
//  }
//
//  @Test
//  public void validateCSIPSTR9_11() {
//    LOGGER.info("Validate CSIPSTR9 - IP_18000_CSIPSTR9_11");
//    Path reportPath = Paths.get("/home/jgomes/Desktop/Demo/Reports/CSIPSTR/CSIPSTR9/IP_18000_CSIPSTR9_11.json");
//    Path earksipPath = Paths.get("/home/jgomes/Desktop/Demo/SIPS/CSIPSTR/CSIPSTR9/IP_18000_CSIPSTR9_11.zip");
//
//    EARKSIPValidator earksipValidator = new EARKSIPValidator(earksipPath,reportPath);
//    earksipValidator.validate();
//    LOGGER.info("Done validate CSIPSTR9 - IP_18000_CSIPSTR9_11");
//  }
//
//  @Test
//  public void validateCSIPSTR9_12() {
//    LOGGER.info("Validate CSIPSTR9 - IP_18000_CSIPSTR9_12");
//    Path reportPath = Paths.get("/home/jgomes/Desktop/Demo/Reports/CSIPSTR/CSIPSTR9/IP_18000_CSIPSTR9_12.json");
//    Path earksipPath = Paths.get("/home/jgomes/Desktop/Demo/SIPS/CSIPSTR/CSIPSTR9/IP_18000_CSIPSTR9_12.zip");
//
//    EARKSIPValidator earksipValidator = new EARKSIPValidator(earksipPath,reportPath);
//    earksipValidator.validate();
//    LOGGER.info("Done validate CSIPSTR9 - IP_18000_CSIPSTR9_12");
//  }
//
//  @Test
//  public void validateCSIPSTR9_13() {
//    LOGGER.info("Validate CSIPSTR9 - IP_18000_CSIPSTR9_13");
//    Path reportPath = Paths.get("/home/jgomes/Desktop/Demo/Reports/CSIPSTR/CSIPSTR9/IP_18000_CSIPSTR9_13.json");
//    Path earksipPath = Paths.get("/home/jgomes/Desktop/Demo/SIPS/CSIPSTR/CSIPSTR9/IP_18000_CSIPSTR9_13.zip");
//
//    EARKSIPValidator earksipValidator = new EARKSIPValidator(earksipPath,reportPath);
//    earksipValidator.validate();
//    LOGGER.info("Done validate CSIPSTR9 - IP_18000_CSIPSTR9_13");
//  }
//
//  @Test
//  public void validateCSIPSTR9_14() {
//    LOGGER.info("Validate CSIPSTR9 - IP_18000_CSIPSTR9_14");
//    Path reportPath = Paths.get("/home/jgomes/Desktop/Demo/Reports/CSIPSTR/CSIPSTR9/IP_18000_CSIPSTR9_14.json");
//    Path earksipPath = Paths.get("/home/jgomes/Desktop/Demo/SIPS/CSIPSTR/CSIPSTR9/IP_18000_CSIPSTR9_14.zip");
//
//    EARKSIPValidator earksipValidator = new EARKSIPValidator(earksipPath,reportPath);
//    earksipValidator.validate();
//    LOGGER.info("Done validate CSIPSTR9 - IP_18000_CSIPSTR9_14");
//  }
//
//  @Test
//  public void validateCSIPSTR9_15() {
//    LOGGER.info("Validate CSIPSTR9 - IP_18000_CSIPSTR9_15");
//    Path reportPath = Paths.get("/home/jgomes/Desktop/Demo/Reports/CSIPSTR/CSIPSTR9/IP_18000_CSIPSTR9_15.json");
//    Path earksipPath = Paths.get("/home/jgomes/Desktop/Demo/SIPS/CSIPSTR/CSIPSTR9/IP_18000_CSIPSTR9_15.zip");
//
//    EARKSIPValidator earksipValidator = new EARKSIPValidator(earksipPath,reportPath);
//    earksipValidator.validate();
//    LOGGER.info("Done validate CSIPSTR9 - IP_18000_CSIPSTR9_15");
//  }
//
//  /* CSIPSTR14 */
//
  /* CSIPSTR15 */
//  @Test
//  public void validateCSIPSTR15_1() {
//    LOGGER.info("Validate CSIPSTR15 - subfolder_schemas_in_IP_folder");
//    Path reportPath = Paths.get("/home/jgomes/Desktop/Demo/Reports/CSIPSTR/CSIPSTR15/subfolder_schemas_in_IP_folder.json");
//    Path earksipPath = Paths.get("/home/jgomes/Desktop/Demo/SIPS/CSIPSTR/CSIPSTR15/subfolder_schemas_in_IP_folder.zip");
//
//    EARKSIPValidator earksipValidator = new EARKSIPValidator(earksipPath,reportPath);
//    earksipValidator.validate();
//    LOGGER.info("Done validate CSIPSTR15 - subfolder_schemas_in_IP_folder");
//  }
//
//  /* CSIPSTR16 */
//  @Test
//  public void validateCSIPSTR16_1() {
//    LOGGER.info("Validate CSIPSTR16 - subfolder_documentation_in_IP_folder");
//    Path reportPath = Paths.get("/home/jgomes/Desktop/Demo/Reports/CSIPSTR/CSIPSTR16/subfolder_documentation_in_IP_folder.json");
//    Path earksipPath = Paths.get("/home/jgomes/Desktop/Demo/SIPS/CSIPSTR/CSIPSTR16/subfolder_documentation_in_IP_folder.zip");
//
//    EARKSIPValidator earksipValidator = new EARKSIPValidator(earksipPath,reportPath);
//    earksipValidator.validate();
//    LOGGER.info("Done validate CSIPSTR16 - subfolder_documentation_in_IP_folder");
//  }

  /* Simple SIP */
  @Test
  public void validateSimpleSip() {
    LOGGER.info("Validate - Simple-EARK-SIP");
    Path reportPath = Paths.get("/home/jgomes/Desktop/Demo/Reports/Simple-EARK-SIP.json");
    Path earksipPath = Paths.get("/home/jgomes/Desktop/Demo/SIPS/Other/Simple-EARK-SIP.zip");

    EARKSIPValidator earksipValidator = new EARKSIPValidator(earksipPath,reportPath);
    earksipValidator.validate();
    LOGGER.info("Done validate - Simple-EARK-SIP");
  }

//  /* Full-EARK-SIP */
//  @Test
//  public void validateFullEarkSip() {
//    LOGGER.info("Validate - Full-EARK-SIP");
//    Path reportPath = Paths.get("/home/jgomes/Desktop/Demo/Reports/Full-EARK-SIP.json");
//    Path earksipPath = Paths.get("/home/jgomes/Desktop/Demo/SIPS/Other/Full-EARK-SIP");
//
//    EARKSIPValidator earksipValidator = new EARKSIPValidator(earksipPath,reportPath);
//    earksipValidator.validate();
//    LOGGER.info("Done validate - Full-EARK-SIP");
//  }


  @Test
  public void validateSimpleSip_2() {
    LOGGER.info("Validate CSIP1 - Simple-EARK-SIP");
    Path reportPath = Paths.get("/home/jgomes/Desktop/Demo/Reports/Simple-EARK-SIP_2.json");
    Path earksipPath = Paths.get("/home/jgomes/Desktop/Demo/SIPS/Other/Simple-EARK-SIP");

    EARKSIPValidator earksipValidator = new EARKSIPValidator(earksipPath,reportPath);
    earksipValidator.validate();
    LOGGER.info("Done validate CSIP1 - Simple-EARK-SIP");
  }

//  /* CSIP1*/
//  @Test
//  public void validateCSIP1_1() {
//    LOGGER.info("Validate CSIP1 - mets-xml_mets_OBJID_attribute_not_exist");
//    Path reportPath = Paths.get("/home/jgomes/Desktop/Demo/Reports/CSIP1/mets-xml_mets_OBJID_attribute_not_exist.json");
//    Path earksipPath = Paths.get("/home/jgomes/Desktop/Demo/SIPS/CSIP1/mets-xml_mets_OBJID_attribute_not_exist.zip");
//
//    EARKSIPValidator earksipValidator = new EARKSIPValidator(earksipPath,reportPath);
//    earksipValidator.validate();
//    LOGGER.info("Done validate CSIP1 - mets-xml_mets_OBJID_attribute_not_exist");
//  }

//  @Test
//  public void validateCSIP1_2() {
//    LOGGER.info("Validate CSIP1 - minimal_IP_with_1_representation");
//    Path reportPath = Paths.get("/home/jgomes/Desktop/Demo/Reports/CSIP1/minimal_IP_with_1_representation.json");
//    Path earksipPath = Paths.get("/home/jgomes/Desktop/Demo/SIPS/CSIP1/minimal_IP_with_1_representation.zip");
//
//    EARKSIPValidator earksipValidator = new EARKSIPValidator(earksipPath,reportPath);
//    earksipValidator.validate();
//    LOGGER.info("Done validate CSIP1 - minimal_IP_with_1_representation");
//  }
//
//  /* CSIP7*/
//  @Test
//  public void validateCSIP7_1() {
//    LOGGER.info("Validate CSIP7 - mets-xml_metsHdr_CREATEDATE_not_exist");
//    Path reportPath = Paths.get("/home/jgomes/Desktop/Demo/Reports/CSIP7/mets-xml_metsHdr_CREATEDATE_not_exist.json");
//    Path earksipPath = Paths.get("/home/jgomes/Desktop/Demo/SIPS/CSIP7/mets-xml_metsHdr_CREATEDATE_not_exist.zip");
//
//    EARKSIPValidator earksipValidator = new EARKSIPValidator(earksipPath,reportPath);
//    earksipValidator.validate();
//    LOGGER.info("Done validate CSIP7 - mets-xml_metsHdr_CREATEDATE_not_exist");
//  }
//
//  /* CSIP9 */
//  @Test
//  public void validateCSIP9_1() {
//    LOGGER.info("Validate CSIP9 - mets-xml_metsHdr_OAISPACKAGETYPE_attribute_not_exist");
//    Path reportPath = Paths.get("/home/jgomes/Desktop/Demo/Reports/CSIP9/mets-xml_metsHdr_OAISPACKAGETYPE_attribute_not_exist.json");
//    Path earksipPath = Paths.get("/home/jgomes/Desktop/Demo/SIPS/CSIP9/mets-xml_metsHdr_OAISPACKAGETYPE_attribute_not_exist.zip");
//
//    EARKSIPValidator earksipValidator = new EARKSIPValidator(earksipPath,reportPath);
//    earksipValidator.validate();
//    LOGGER.info("Done validate CSIP9 - mets-xml_metsHdr_OAISPACKAGETYPE_attribute_not_exist");
//  }
//
//  @Test
//  public void validateCSIP9_2() {
//    LOGGER.info("Validate CSIP9 - mets-xml_metsHdr_OAISPACKAGETYPE_attribute_value_incorrect");
//    Path reportPath = Paths.get("/home/jgomes/Desktop/Demo/Reports/CSIP9/mets-xml_metsHdr_OAISPACKAGETYPE_attribute_value_incorrect.json");
//    Path earksipPath = Paths.get("/home/jgomes/Desktop/Demo/SIPS/CSIP9/mets-xml_metsHdr_OAISPACKAGETYPE_attribute_value_incorrect.zip");
//
//    EARKSIPValidator earksipValidator = new EARKSIPValidator(earksipPath,reportPath);
//    earksipValidator.validate();
//    LOGGER.info("Done validate CSIP9 - mets-xml_metsHdr_OAISPACKAGETYPE_attribute_value_incorrect");
//  }
//
//  /* CSIP10 */
//  @Test
//  public void validateCSIP10_1() {
//    LOGGER.info("Validate CSIP10 - mets-xml_metsHdr_agent_not_exist");
//    Path reportPath = Paths.get("/home/jgomes/Desktop/Demo/Reports/CSIP10/mets-xml_metsHdr_agent_not_exist.json");
//    Path earksipPath = Paths.get("/home/jgomes/Desktop/Demo/SIPS/CSIP10/mets-xml_metsHdr_agent_not_exist.zip");
//
//    EARKSIPValidator earksipValidator = new EARKSIPValidator(earksipPath,reportPath);
//    earksipValidator.validate();
//    LOGGER.info("Done validate CSIP10 - mets-xml_metsHdr_agent_not_exist");
//  }
//
//  /* CSIP11 */
//  @Test
//  public void validateCSIP11_1() {
//    LOGGER.info("Validate CSIP11 - mets-xml_metsHdr_agent_ROLE_EDITOR");
//    Path reportPath = Paths.get("/home/jgomes/Desktop/Demo/Reports/CSIP11/mets-xml_metsHdr_agent_ROLE_EDITOR.json");
//    Path earksipPath = Paths.get("/home/jgomes/Desktop/Demo/SIPS/CSIP11/mets-xml_metsHdr_agent_ROLE_EDITOR.zip");
//
//    EARKSIPValidator earksipValidator = new EARKSIPValidator(earksipPath,reportPath);
//    earksipValidator.validate();
//    LOGGER.info("Done validate CSIP11 - mets-xml_metsHdr_agent_ROLE_EDITOR");
//  }
//
//  /* CSIP12 */
//  @Test
//  public void validateCSIP12_1() {
//    LOGGER.info("Validate CSIP12 - mets-xml_metsHdr_agent_TYPE_not_exist");
//    Path reportPath = Paths.get("/home/jgomes/Desktop/Demo/Reports/CSIP12/mets-xml_metsHdr_agent_TYPE_not_exist.json");
//    Path earksipPath = Paths.get("/home/jgomes/Desktop/Demo/SIPS/CSIP12/mets-xml_metsHdr_agent_TYPE_not_exist.zip");
//
//    EARKSIPValidator earksipValidator = new EARKSIPValidator(earksipPath,reportPath);
//    earksipValidator.validate();
//    LOGGER.info("Done validate CSIP12 - mets-xml_metsHdr_agent_TYPE_not_exist");
//  }
//
//  @Test
//  public void validateCSIP12_2() {
//    LOGGER.info("Validate CSIP12 - mets-xml_metsHdr_agent_TYPE_INDIVIDUAL");
//    Path reportPath = Paths.get("/home/jgomes/Desktop/Demo/Reports/CSIP12/mets-xml_metsHdr_agent_TYPE_INDIVIDUAL.json");
//    Path earksipPath = Paths.get("/home/jgomes/Desktop/Demo/SIPS/CSIP12/mets-xml_metsHdr_agent_TYPE_INDIVIDUAL.zip");
//
//    EARKSIPValidator earksipValidator = new EARKSIPValidator(earksipPath,reportPath);
//    earksipValidator.validate();
//    LOGGER.info("Done validate CSIP12 - mets-xml_metsHdr_agent_TYPE_INDIVIDUAL");
//  }
//
//  /* CSIP13 */
//  @Test
//  public void validateCSIP13_1() {
//    LOGGER.info("Validate CSIP13 - mets-xml_metsHdr_agent_OTHERTYPE_not_exist");
//    Path reportPath = Paths.get("/home/jgomes/Desktop/Demo/Reports/CSIP13/mets-xml_metsHdr_agent_OTHERTYPE_not_exist.json");
//    Path earksipPath = Paths.get("/home/jgomes/Desktop/Demo/SIPS/CSIP13/mets-xml_metsHdr_agent_OTHERTYPE_not_exist.zip");
//
//    EARKSIPValidator earksipValidator = new EARKSIPValidator(earksipPath,reportPath);
//    earksipValidator.validate();
//    LOGGER.info("Done validate CSIP13 - mets-xml_metsHdr_agent_OTHERTYPE_not_exist");
//  }
//
//  @Test
//  public void validateCSIP13_2() {
//    LOGGER.info("Validate CSIP13 - mets-xml_metsHdr_agent_OTHERTYPE_incorrect");
//    Path reportPath = Paths.get("/home/jgomes/Desktop/Demo/Reports/CSIP13/mets-xml_metsHdr_agent_OTHERTYPE_incorrect.json");
//    Path earksipPath = Paths.get("/home/jgomes/Desktop/Demo/SIPS/CSIP13/mets-xml_metsHdr_agent_OTHERTYPE_incorrect.zip");
//
//    EARKSIPValidator earksipValidator = new EARKSIPValidator(earksipPath,reportPath);
//    earksipValidator.validate();
//    LOGGER.info("Done validate CSIP13 - mets-xml_metsHdr_agent_OTHERTYPE_incorrect");
//  }
//
//  /* CSIP14 */
//  @Test
//  public void validateCSIP14_1() {
//    LOGGER.info("Validate CSIP14 - mets-xml_metsHdr_agent_name_empty");
//    Path reportPath = Paths.get("/home/jgomes/Desktop/Demo/Reports/CSIP14/mets-xml_metsHdr_agent_name_empty.json");
//    Path earksipPath = Paths.get("/home/jgomes/Desktop/Demo/SIPS/CSIP14/mets-xml_metsHdr_agent_name_empty.zip");
//
//    EARKSIPValidator earksipValidator = new EARKSIPValidator(earksipPath,reportPath);
//    earksipValidator.validate();
//    LOGGER.info("Done validate CSIP14 - mets-xml_metsHdr_agent_name_empty");
//  }
//
//  /* CSIP15 */
//  @Test
//  public void validateCSIP15_1() {
//    LOGGER.info("Validate CSIP15 - mets-xml_metsHdr_agent_note_not_exist");
//    Path reportPath = Paths.get("/home/jgomes/Desktop/Demo/Reports/CSIP15/mets-xml_metsHdr_agent_note_not_exist.json");
//    Path earksipPath = Paths.get("/home/jgomes/Desktop/Demo/SIPS/CSIP15/mets-xml_metsHdr_agent_note_not_exist.zip");
//
//    EARKSIPValidator earksipValidator = new EARKSIPValidator(earksipPath,reportPath);
//    earksipValidator.validate();
//    LOGGER.info("Done validate CSIP15 - mets-xml_metsHdr_agent_note_not_exist");
//  }
//
//  @Test
//  public void validateCSIP15_2() {
//    LOGGER.info("Validate CSIP15 - mets-xml_metsHdr_agent_note_2_instances");
//    Path reportPath = Paths.get("/home/jgomes/Desktop/Demo/Reports/CSIP15/mets-xml_metsHdr_agent_note_2_instances.json");
//    Path earksipPath = Paths.get("/home/jgomes/Desktop/Demo/SIPS/CSIP15/mets-xml_metsHdr_agent_note_2_instances.zip");
//
//    EARKSIPValidator earksipValidator = new EARKSIPValidator(earksipPath,reportPath);
//    earksipValidator.validate();
//    LOGGER.info("Done validate CSIP15 - mets-xml_metsHdr_agent_note_2_instances");
//  }
//
//  @Test
//  public void validateCSIP15_3() {
//    LOGGER.info("Validate CSIP15 - mets-xml_metsHdr_agent_note_empty");
//    Path reportPath = Paths.get("/home/jgomes/Desktop/Demo/Reports/CSIP15/mets-xml_metsHdr_agent_note_empty.json");
//    Path earksipPath = Paths.get("/home/jgomes/Desktop/Demo/SIPS/CSIP15/mets-xml_metsHdr_agent_note_empty.zip");
//
//    EARKSIPValidator earksipValidator = new EARKSIPValidator(earksipPath,reportPath);
//    earksipValidator.validate();
//    LOGGER.info("Done validate CSIP15 - mets-xml_metsHdr_agent_note_empty");
//  }
//
//  /* CSIP16 */
//  @Test
//  public void validateCSIP16_1() {
//    LOGGER.info("Validate CSIP16 - mets-xml_metsHdr_agent_note_NOTETYPE_not_exist");
//    Path reportPath = Paths.get("/home/jgomes/Desktop/Demo/Reports/CSIP16/mets-xml_metsHdr_agent_note_NOTETYPE_not_exist.json");
//    Path earksipPath = Paths.get("/home/jgomes/Desktop/Demo/SIPS/CSIP16/mets-xml_metsHdr_agent_note_NOTETYPE_not_exist.zip");
//
//    EARKSIPValidator earksipValidator = new EARKSIPValidator(earksipPath,reportPath);
//    earksipValidator.validate();
//    LOGGER.info("Done validate CSIP16 - mets-xml_metsHdr_agent_note_NOTETYPE_not_exist");
//  }
//
//  @Test
//  public void validateCSIP16_2() {
//    LOGGER.info("Validate CSIP16 - mets-xml_metsHdr_agent_note_NOTETYPE_incorrect");
//    Path reportPath = Paths.get("/home/jgomes/Desktop/Demo/Reports/CSIP16/mets-xml_metsHdr_agent_note_NOTETYPE_incorrect.json");
//    Path earksipPath = Paths.get("/home/jgomes/Desktop/Demo/SIPS/CSIP16/mets-xml_metsHdr_agent_note_NOTETYPE_incorrect.zip");
//
//    EARKSIPValidator earksipValidator = new EARKSIPValidator(earksipPath,reportPath);
//    earksipValidator.validate();
//    LOGGER.info("Done validate CSIP16 - mets-xml_metsHdr_agent_note_NOTETYPE_incorrect");
//  }

//  /* CSIP17 */
//  @Test
//  public void validateCSIP17_2() {
//    LOGGER.info("Validate CSIP17 - IP_18000_CSIP17_2");
//    Path reportPath = Paths.get("/home/jgomes/Desktop/Demo/Reports/CSIP17/IP_18000_CSIP17_2.json");
//    Path earksipPath = Paths.get("/home/jgomes/Desktop/Demo/SIPS/CSIP17/IP_18000_CSIP17_2.zip");
//
//    EARKSIPValidator earksipValidator = new EARKSIPValidator(earksipPath,reportPath);
//    earksipValidator.validate();
//    LOGGER.info("Done validate CSIP17 - IP_18000_CSIP17_2");
//  }
//
//  /* CSIP19 */
//  @Test
//  public void validateCSIP19_1() {
//    LOGGER.info("Validate CSIP19 - IP_18000_CSIP19_1");
//    Path reportPath = Paths.get("/home/jgomes/Desktop/Demo/Reports/CSIP19/IP_18000_CSIP19_1.json");
//    Path earksipPath = Paths.get("/home/jgomes/Desktop/Demo/SIPS/CSIP19/IP_18000_CSIP19_1.zip");
//
//    EARKSIPValidator earksipValidator = new EARKSIPValidator(earksipPath,reportPath);
//    earksipValidator.validate();
//    LOGGER.info("Done validate CSIP19 - IP_18000_CSIP19_1");
//  }
//
  /* CSIP20 */
//  @Test
//  public void validateCSIP20_1() {
//    LOGGER.info("Validate CSIP20 - IP_18000_CSIP20_1");
//    Path reportPath = Paths.get("/home/jgomes/Desktop/Demo/Reports/CSIP20/IP_18000_CSIP20_1.json");
//    Path earksipPath = Paths.get("/home/jgomes/Desktop/Demo/SIPS/CSIP20/IP_18000_CSIP20_1.zip");
//
//    EARKSIPValidator earksipValidator = new EARKSIPValidator(earksipPath,reportPath);
//    earksipValidator.validate();
//    LOGGER.info("Done validate CSIP20 - IP_18000_CSIP20_1");
//  }
////
//  @Test
//  public void validateCSIP20_2() {
//    LOGGER.info("Validate CSIP20 - IP_18000_CSIP20_2");
//    Path reportPath = Paths.get("/home/jgomes/Desktop/Demo/Reports/CSIP20/IP_18000_CSIP20_2.json");
//    Path earksipPath = Paths.get("/home/jgomes/Desktop/Demo/SIPS/CSIP20/IP_18000_CSIP20_2.zip");
//
//    EARKSIPValidator earksipValidator = new EARKSIPValidator(earksipPath,reportPath);
//    earksipValidator.validate();
//    LOGGER.info("Done validate CSIP20 - IP_18000_CSIP20_2");
//  }
//
//  @Test
//  public void validateCSIP20_3() {
//    LOGGER.info("Validate CSIP20 - IP_18000_CSIP20_3");
//    Path reportPath = Paths.get("/home/jgomes/Desktop/Demo/Reports/CSIP20/IP_18000_CSIP20_3.json");
//    Path earksipPath = Paths.get("/home/jgomes/Desktop/Demo/SIPS/CSIP20/IP_18000_CSIP20_3.zip");
//
//    EARKSIPValidator earksipValidator = new EARKSIPValidator(earksipPath,reportPath);
//    earksipValidator.validate();
//    LOGGER.info("Done validate CSIP20 - IP_18000_CSIP20_3");
//  }
//
//  @Test
//  public void validateCSIP20_4() {
//    LOGGER.info("Validate CSIP20 - IP_18000_CSIP20_4");
//    Path reportPath = Paths.get("/home/jgomes/Desktop/Demo/Reports/CSIP20/IP_18000_CSIP20_4.json");
//    Path earksipPath = Paths.get("/home/jgomes/Desktop/Demo/SIPS/CSIP20/IP_18000_CSIP20_4.zip");
//
//    EARKSIPValidator earksipValidator = new EARKSIPValidator(earksipPath,reportPath);
//    earksipValidator.validate();
//    LOGGER.info("Done validate CSIP20 - IP_18000_CSIP20_4");
//  }
////
//  @Test
//  public void validateCSIP20_5() {
//    LOGGER.info("Validate CSIP20 - IP_18000_CSIP20_5");
//    Path reportPath = Paths.get("/home/jgomes/Desktop/Demo/Reports/CSIP20/IP_18000_CSIP20_5.json");
//    Path earksipPath = Paths.get("/home/jgomes/Desktop/Demo/SIPS/CSIP20/IP_18000_CSIP20_5.zip");
//
//    EARKSIPValidator earksipValidator = new EARKSIPValidator(earksipPath,reportPath);
//    earksipValidator.validate();
//    LOGGER.info("Done validate CSIP20 - IP_18000_CSIP20_5");
//  }

  /* CSIP21 */
//  @Test
//  public void validateCSIP21_1() {
//    LOGGER.info("Validate CSIP21 - IP_18000_CSIP21_1");
//    Path reportPath = Paths.get("/home/jgomes/Desktop/Demo/Reports/CSIP21/IP_18000_CSIP21_1.json");
//    Path earksipPath = Paths.get("/home/jgomes/Desktop/Demo/SIPS/CSIP21/IP_18000_CSIP21_1.zip");
//
//    EARKSIPValidator earksipValidator = new EARKSIPValidator(earksipPath,reportPath);
//    earksipValidator.validate();
//    LOGGER.info("Done validate CSIP21 - IP_18000_CSIP21_1");
//  }
//
//  @Test
//  public void validateCSIP21_2() {
//    LOGGER.info("Validate CSIP21 - IP_18000_CSIP21_2");
//    Path reportPath = Paths.get("/home/jgomes/Desktop/Demo/Reports/CSIP21/IP_18000_CSIP21_2.json");
//    Path earksipPath = Paths.get("/home/jgomes/Desktop/Demo/SIPS/CSIP21/IP_18000_CSIP21_2.zip");
//
//    EARKSIPValidator earksipValidator = new EARKSIPValidator(earksipPath,reportPath);
//    earksipValidator.validate();
//    LOGGER.info("Done validate CSIP21 - IP_18000_CSIP21_2");
//  }

//  /* CSIP23 */
//  @Test
//  public void validateCSIP23_1() {
//    LOGGER.info("Validate CSIP23 - IP_18000_CSIP23_1");
//    Path reportPath = Paths.get("/home/jgomes/Desktop/Demo/Reports/CSIP23/IP_18000_CSIP23_1.json");
//    Path earksipPath = Paths.get("/home/jgomes/Desktop/Demo/SIPS/CSIP23/IP_18000_CSIP23_1.zip");
//
//    EARKSIPValidator earksipValidator = new EARKSIPValidator(earksipPath,reportPath);
//    earksipValidator.validate();
//    LOGGER.info("Done validate CSIP23 - IP_18000_CSIP23_1");
//  }
//
//  /* CSIP24 */
//  @Test
//  public void validateCSIP24_1() {
//    LOGGER.info("Validate CSIP24 - IP_18000_CSIP24_1");
//    Path reportPath = Paths.get("/home/jgomes/Desktop/Demo/Reports/CSIP24/IP_18000_CSIP24_1.json");
//    Path earksipPath = Paths.get("/home/jgomes/Desktop/Demo/SIPS/CSIP24/IP_18000_CSIP24_1.zip");
//
//    EARKSIPValidator earksipValidator = new EARKSIPValidator(earksipPath,reportPath);
//    earksipValidator.validate();
//    LOGGER.info("Done validate CSIP24 - IP_18000_CSIP24_1");
//  }
//
//  @Test
//  public void validateCSIP24_2() {
//    LOGGER.info("Validate CSIP 24 - IP_18000_CSIP24_2");
//    Path reportPath = Paths.get("/home/jgomes/Desktop/Demo/Reports/CSIP24/IP_18000_CSIP24_2.json");
//    Path earksipPath = Paths.get("/home/jgomes/Desktop/Demo/SIPS/CSIP24/IP_18000_CSIP24_2.zip");
//
//    EARKSIPValidator earksipValidator = new EARKSIPValidator(earksipPath,reportPath);
//    earksipValidator.validate();
//    LOGGER.info("Done validate CSIP24 - IP_18000_CSIP24_2");
//  }
//
//  /* CSIP26 */
//  @Test
//  public void validateCSIP26_1() {
//    LOGGER.info("Validate CSIP26 - IP_18000_CSIP26_1");
//    Path reportPath = Paths.get("/home/jgomes/Desktop/Demo/Reports/CSIP26/IP_18000_CSIP26_1.json");
//    Path earksipPath = Paths.get("/home/jgomes/Desktop/Demo/SIPS/CSIP26/IP_18000_CSIP26_1.zip");
//
//    EARKSIPValidator earksipValidator = new EARKSIPValidator(earksipPath,reportPath);
//    earksipValidator.validate();
//    LOGGER.info("Done validate CSIP26 - IP_18000_CSIP26_1");
//  }
//
//  @Test
//  public void validateCSIP26_2() {
//    LOGGER.info("Validate CSIP26 - IP_18000_CSIP26_2");
//    Path reportPath = Paths.get("/home/jgomes/Desktop/Demo/Reports/CSIP26/IP_18000_CSIP26_2.json");
//    Path earksipPath = Paths.get("/home/jgomes/Desktop/Demo/SIPS/CSIP26/IP_18000_CSIP26_2.zip");
//
//    EARKSIPValidator earksipValidator = new EARKSIPValidator(earksipPath,reportPath);
//    earksipValidator.validate();
//    LOGGER.info("Done validate CSIP26 - IP_18000_CSIP26_2");
//  }
//
//  /* CSIP27 */
//  @Test
//  public void validateCSIP27_1() {
//    LOGGER.info("Validate CSIP27 - IP_18000_CSIP27_1");
//    Path reportPath = Paths.get("/home/jgomes/Desktop/Demo/Reports/CSIP27/IP_18000_CSIP27_1.json");
//    Path earksipPath = Paths.get("/home/jgomes/Desktop/Demo/SIPS/CSIP27/IP_18000_CSIP27_1.zip");
//
//    EARKSIPValidator earksipValidator = new EARKSIPValidator(earksipPath,reportPath);
//    earksipValidator.validate();
//    LOGGER.info("Done validate CSIP27 - IP_18000_CSIP27_1");
//  }
//
//  @Test
//  public void validateCSIP27_2() {
//    LOGGER.info("Validate CSIP27 - IP_18000_CSIP27_2");
//    Path reportPath = Paths.get("/home/jgomes/Desktop/Demo/Reports/CSIP27/IP_18000_CSIP27_2.json");
//    Path earksipPath = Paths.get("/home/jgomes/Desktop/Demo/SIPS/CSIP27/IP_18000_CSIP27_2.zip");
//
//    EARKSIPValidator earksipValidator = new EARKSIPValidator(earksipPath,reportPath);
//    earksipValidator.validate();
//    LOGGER.info("Done validate CSIP27 - IP_18000_CSIP27_2");
//  }
//
//  /* CSIP28 */
//  @Test
//  public void validateCSIP28_1() {
//    LOGGER.info("Validate CSIP28 - IP_18000_CSIP28_1");
//    Path reportPath = Paths.get("/home/jgomes/Desktop/Demo/Reports/CSIP28/IP_18000_CSIP28_1.json");
//    Path earksipPath = Paths.get("/home/jgomes/Desktop/Demo/SIPS/CSIP28/IP_18000_CSIP28_1.zip");
//
//    EARKSIPValidator earksipValidator = new EARKSIPValidator(earksipPath,reportPath);
//    earksipValidator.validate();
//    LOGGER.info("Done validate CSIP28 - IP_18000_CSIP28_1");
//  }
//
//  /* CSIP29 */
//  @Test
//  public void validateCSIP29_1() {
//    LOGGER.info("Validate CSIP29 - IP_18000_CSIP29_1");
//    Path reportPath = Paths.get("/home/jgomes/Desktop/Demo/Reports/CSIP29/IP_18000_CSIP29_1.json");
//    Path earksipPath = Paths.get("/home/jgomes/Desktop/Demo/SIPS/CSIP29/IP_18000_CSIP29_1.zip");
//
//    EARKSIPValidator earksipValidator = new EARKSIPValidator(earksipPath,reportPath);
//    earksipValidator.validate();
//    LOGGER.info("Done validate CSIP29 - IP_18000_CSIP29_1");
//  }
//
//  @Test
//  public void validateCSIP29_2() {
//    LOGGER.info("Validate CSIP29 - IP_18000_CSIP29_2");
//    Path reportPath = Paths.get("/home/jgomes/Desktop/Demo/Reports/CSIP29/IP_18000_CSIP29_2.json");
//    Path earksipPath = Paths.get("/home/jgomes/Desktop/Demo/SIPS/CSIP29/IP_18000_CSIP29_2.zip");
//
//    EARKSIPValidator earksipValidator = new EARKSIPValidator(earksipPath,reportPath);
//    earksipValidator.validate();
//    LOGGER.info("Done validate CSIP29 - IP_18000_CSIP29_2");
//  }
//
//
//  @Test
//  public void validateCSIP29_3() {
//    LOGGER.info("Validate CSIP29 - IP_18000_CSIP29_3");
//    Path reportPath = Paths.get("/home/jgomes/Desktop/Demo/Reports/CSIP29/IP_18000_CSIP29_3.json");
//    Path earksipPath = Paths.get("/home/jgomes/Desktop/Demo/SIPS/CSIP29/IP_18000_CSIP29_3.zip");
//
//    EARKSIPValidator earksipValidator = new EARKSIPValidator(earksipPath,reportPath);
//    earksipValidator.validate();
//    LOGGER.info("Done validate CSIP29 - IP_18000_CSIP29_3");
//  }
//
//  @Test
//  public void validateCSIP29_4() {
//    LOGGER.info("Validate CSIP29 - IP_18000_CSIP29_4");
//    Path reportPath = Paths.get("/home/jgomes/Desktop/Demo/Reports/CSIP29/IP_18000_CSIP29_4.json");
//    Path earksipPath = Paths.get("/home/jgomes/Desktop/Demo/SIPS/CSIP29/IP_18000_CSIP29_4.zip");
//
//    EARKSIPValidator earksipValidator = new EARKSIPValidator(earksipPath,reportPath);
//    earksipValidator.validate();
//    LOGGER.info("Done validate CSIP29 - IP_18000_CSIP29_4");
//  }
//
//  /* CSIP30 */
//  @Test
//  public void validateCSIP30_1() {
//    LOGGER.info("Validate CSIP30 - IP_18000_CSIP30_1");
//    Path reportPath = Paths.get("/home/jgomes/Desktop/Demo/Reports/CSIP30/IP_18000_CSIP30_1.json");
//    Path earksipPath = Paths.get("/home/jgomes/Desktop/Demo/SIPS/CSIP30/IP_18000_CSIP30_1.zip");
//
//    EARKSIPValidator earksipValidator = new EARKSIPValidator(earksipPath,reportPath);
//    earksipValidator.validate();
//    LOGGER.info("Done validate CSIP30 - IP_18000_CSIP30_1");
//  }
//
//
//  /* CSIP32 */
//  @Test
//  public void validateCSIP32_1() {
//    LOGGER.info("Validate CSIP32 - IP_18000_CSIP32_1");
//    Path reportPath = Paths.get("/home/jgomes/Desktop/Demo/Reports/CSIP32/IP_18000_CSIP32_1.json");
//    Path earksipPath = Paths.get("/home/jgomes/Desktop/Demo/SIPS/CSIP32/IP_18000_CSIP32_1.zip");
//
//    EARKSIPValidator earksipValidator = new EARKSIPValidator(earksipPath,reportPath);
//    earksipValidator.validate();
//    LOGGER.info("Done validate CSIP32 - IP_18000_CSIP32_1");
//  }
//
//  @Test
//  public void validateCSIP32_2() {
//    LOGGER.info("Validate CSIP32 - IP_18000_CSIP32_2");
//    Path reportPath = Paths.get("/home/jgomes/Desktop/Demo/Reports/CSIP32/IP_18000_CSIP32_2.json");
//    Path earksipPath = Paths.get("/home/jgomes/Desktop/Demo/SIPS/CSIP32/IP_18000_CSIP32_2.zip");
//
//    EARKSIPValidator earksipValidator = new EARKSIPValidator(earksipPath,reportPath);
//    earksipValidator.validate();
//    LOGGER.info("Done validate CSIP32 - IP_18000_CSIP32_2");
//  }
////
//  @Test
//  public void validateCSIP32_3() {
//    LOGGER.info("Validate CSIP32 - IP_18000_CSIP32_3");
//    Path reportPath = Paths.get("/home/jgomes/Desktop/Demo/Reports/CSIP32/IP_18000_CSIP32_3.json");
//    Path earksipPath = Paths.get("/home/jgomes/Desktop/Demo/SIPS/CSIP32/IP_18000_CSIP32_3.zip");
//
//    EARKSIPValidator earksipValidator = new EARKSIPValidator(earksipPath,reportPath);
//    earksipValidator.validate();
//    LOGGER.info("Done validate CSIP32 - IP_18000_CSIP32_3");
//  }

//  /* CSIP60 */
//  @Test
//  public void validateCSIP60_1() {
//    LOGGER.info("Validate CSIP60 - no_doc_file_grp");
//    Path reportPath = Paths.get("/home/jgomes/Desktop/Demo/Reports/CSIP60/no_doc_file_grp.json");
//    Path earksipPath = Paths.get("/home/jgomes/Desktop/Demo/SIPS/CSIP60/no_doc_file_grp.zip");
//
//    EARKSIPValidator earksipValidator = new EARKSIPValidator(earksipPath,reportPath);
//    earksipValidator.validate();
//    LOGGER.info("Done validate CSIP60 - no_doc_file_grp");
//  }

//  @Test
//  public void validateCSIP60_2() {
//    LOGGER.info("Validate CSIP60 - minimal_IP_with_1_representation");
//    Path reportPath = Paths.get("/home/jgomes/Desktop/Demo/Reports/CSIP60/minimal_IP_with_1_representation.json");
//    Path earksipPath = Paths.get("/home/jgomes/Desktop/Demo/SIPS/CSIP60/minimal_IP_with_1_representation.zip");
//
//    EARKSIPValidator earksipValidator = new EARKSIPValidator(earksipPath,reportPath);
//    earksipValidator.validate();
//    LOGGER.info("Done validate CSIP60 - minimal_IP_with_1_representation");
//  }

//  @Test
//  public void validateCSIP60_3() {
//    LOGGER.info("Validate CSIP60 - multi_doc_file_grp");
//    Path reportPath = Paths.get("/home/jgomes/Desktop/Demo/Reports/CSIP60/multi_doc_file_grp.json");
//    Path earksipPath = Paths.get("/home/jgomes/Desktop/Demo/SIPS/CSIP60/multi_doc_file_grp.zip");
//
//    EARKSIPValidator earksipValidator = new EARKSIPValidator(earksipPath,reportPath);
//    earksipValidator.validate();
//    LOGGER.info("Done validate CSIP60 - multi_doc_file_grp");
//  }
//
//  /* CSIP80 */
//  @Test
//  public void validateCSIP80_1() {
//    LOGGER.info("Validate CSIP80 - IP_missing_strucMap_label_attribue_value");
//    Path reportPath = Paths.get("/home/jgomes/Desktop/Demo/Reports/CSIP80/IP_missing_strucMap_label_attribue_value.json");
//    Path earksipPath = Paths.get("/home/jgomes/Desktop/Demo/SIPS/CSIP80/IP_missing_strucMap_label_attribue_value.zip");
//
//    EARKSIPValidator earksipValidator = new EARKSIPValidator(earksipPath,reportPath);
//    earksipValidator.validate();
//    LOGGER.info("Done validate CSIP80 - IP_missing_strucMap_label_attribue_value");
//  }
//
//  @Test
//  public void validateCSIP80_2() {
//    LOGGER.info("Validate CSIP80 - IP_two_strucMap_label_attribue_value");
//    Path reportPath = Paths.get("/home/jgomes/Desktop/Demo/Reports/CSIP80/IP_two_strucMap_label_attribue_value.json");
//    Path earksipPath = Paths.get("/home/jgomes/Desktop/Demo/SIPS/CSIP80/IP_two_strucMap_label_attribue_value.zip");
//
//    EARKSIPValidator earksipValidator = new EARKSIPValidator(earksipPath,reportPath);
//    earksipValidator.validate();
//    LOGGER.info("Done validate CSIP80 - IP_two_strucMap_label_attribue_value");
//  }
}
