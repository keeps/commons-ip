/**
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE file at the root of the source
 * tree and available online at
 *
 * https://github.com/keeps/commons-ip
 */
package org.roda_project.commons_ip2.model.eark;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.parsers.ParserConfigurationException;

import org.hamcrest.core.Is;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.roda_project.commons_ip.model.ParseException;
import org.roda_project.commons_ip.utils.IPEnums;
import org.roda_project.commons_ip.utils.IPException;
import org.roda_project.commons_ip.utils.METSEnums.CreatorType;
import org.roda_project.commons_ip2.cli.model.enums.WriteStrategyEnum;
import org.roda_project.commons_ip2.cli.utils.SIPBuilderUtils;
import org.roda_project.commons_ip2.mets_v1_12.beans.FileType;
import org.roda_project.commons_ip2.model.IPAgent;
import org.roda_project.commons_ip2.model.IPAgentNoteTypeEnum;
import org.roda_project.commons_ip2.model.IPConstants;
import org.roda_project.commons_ip2.model.IPContentInformationType;
import org.roda_project.commons_ip2.model.IPContentType;
import org.roda_project.commons_ip2.model.IPDescriptiveMetadata;
import org.roda_project.commons_ip2.model.IPFile;
import org.roda_project.commons_ip2.model.IPFileInterface;
import org.roda_project.commons_ip2.model.IPFileShallow;
import org.roda_project.commons_ip2.model.IPMetadata;
import org.roda_project.commons_ip2.model.IPRepresentation;
import org.roda_project.commons_ip2.model.MetadataType;
import org.roda_project.commons_ip2.model.MetadataType.MetadataTypeEnum;
import org.roda_project.commons_ip2.model.RepresentationStatus;
import org.roda_project.commons_ip2.model.SIP;
import org.roda_project.commons_ip2.model.ValidationEntry.LEVEL;
import org.roda_project.commons_ip2.model.impl.eark.EARKSIP;
import org.roda_project.commons_ip2.model.impl.eark.out.writers.strategy.WriteStrategy;
import org.roda_project.commons_ip2.utils.Utils;
import org.roda_project.commons_ip2.validator.EARKSIPValidator;
import org.roda_project.commons_ip2.validator.constants.Constants;
import org.roda_project.commons_ip2.validator.reporter.ValidationReportOutputJson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;

/**
 * Unit tests for EARK Information Packages (SIP, AIP and DIP)
 */
public class EARKSIPTest {
  private static final String REPRESENTATION_STATUS_NORMALIZED = "NORMALIZED";
  private static final String SIP_CHECKSUM_TEST_ID = "SIP_CHECKSUM_TEST";
  private static final String REPRESENTATION_WITH_CHECKSUM = "representation_with_checksum";

  private static final Logger LOGGER = LoggerFactory.getLogger(EARKSIPTest.class);

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
  public void buildParseAndValidateEARKSIP() throws IPException, ParseException, InterruptedException, IOException,
    ParserConfigurationException, SAXException, NoSuchAlgorithmException {
    LOGGER.info("Creating full E-ARK SIP");
    Path zipSIP = createFullEARKSIP_For_Test_Compliance();
    LOGGER.info("Done creating full E-ARK SIP");
    LOGGER.info("Parsing (and validating) full E-ARK SIP");
    parseAndValidateFullEARKSIP(zipSIP);

    Path reportPath = Files.createTempDirectory("reports").resolve("Full-EARK-SIP.json");
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
    ValidationReportOutputJson reportOutputJson = new ValidationReportOutputJson(zipSIP, outputStream);
    EARKSIPValidator earksipValidator = new EARKSIPValidator(reportOutputJson, "2.1.0");
    boolean validate = earksipValidator.validate("2.1.0");
    LOGGER.info("Done parsing (and validating) full E-ARK SIP");
    Assert.assertTrue(validate);
  }

  @Test
  public void buildParseAndValidateEARKSIP220() throws IPException, ParseException, InterruptedException, IOException,
    ParserConfigurationException, SAXException, NoSuchAlgorithmException {
    LOGGER.info("Creating full E-ARK SIP");
    Path zipSIP = createFullEARKSIP_For_Test_Compliance220();
    LOGGER.info("Done creating full E-ARK SIP");
    LOGGER.info("Parsing (and validating) full E-ARK SIP");
    parseAndValidateFullEARKSIP(zipSIP);

    Path reportPath = Files.createTempDirectory("reports").resolve("Full-EARK-SIP.json");
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
    ValidationReportOutputJson reportOutputJson = new ValidationReportOutputJson(zipSIP, outputStream);
    EARKSIPValidator earksipValidator = new EARKSIPValidator(reportOutputJson, "2.2.0");
    boolean validate = earksipValidator.validate("2.2.0");
    LOGGER.info("Done parsing (and validating) full E-ARK SIP");
    Assert.assertTrue(validate);
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

  @Test
  public void buildEARKSIPShallow()
    throws IPException, InterruptedException, DatatypeConfigurationException, ParseException, URISyntaxException {
    LOGGER.info("Creating full E-ARK SIP-S");
    Path zipSIPS = createFullEARKSIPS();
    LOGGER.info("Done creating full E-ARK SIP-S");

    LOGGER.info("Parsing (and validating) full E-ARK SIP");
    // parseAndValidateFullEARKSIPS(zipSIPS);
    LOGGER.info("Done parsing (and validating) full E-ARK SIP");
  }

  private Path createFullEARKSIPS()
    throws IPException, InterruptedException, DatatypeConfigurationException, URISyntaxException {
    // 1) instantiate E-ARK SIP object
    SIP sip = new EARKSIP("SIP_S_1", IPContentType.getMIXED(), IPContentInformationType.getMIXED(), "2.1.0");
    sip.addCreatorSoftwareAgent("RODA Commons IP", "2.0.0");

    // 1.1) set optional human-readable description
    sip.setDescription("A full E-ARK SIP-S");

    // 1.2) add descriptive metadata (SIP level)
    IPDescriptiveMetadata metadataDescriptiveDC = new IPDescriptiveMetadata(
      new IPFile(Paths.get("src/test/resources/eark/metadata_descriptive_dc.xml")),
      new MetadataType(MetadataTypeEnum.DC), null);
    sip.addDescriptiveMetadata(metadataDescriptiveDC);

    // 1.3) add preservation metadata (SIP level)
    IPMetadata metadataPreservation = new IPMetadata(
      new IPFile(Paths.get("src/test/resources/eark/metadata_preservation_premis.xml")))
        .setMetadataType(MetadataTypeEnum.PREMIS);
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
    sip.setAncestors(Arrays.asList("b6f24059-8973-4582-932d-eb0b2cb48f28"));

    // 1.8) add an agent (SIP level)
    IPAgent agent = new IPAgent("Agent Name", "OTHER", "OTHER ROLE", CreatorType.INDIVIDUAL, "OTHER TYPE", "",
      IPAgentNoteTypeEnum.SOFTWARE_VERSION);
    sip.addAgent(agent);

    // 1.9) add a representation (status will be set to the default value, i.e.,
    // ORIGINAL)
    IPRepresentation representation1 = new IPRepresentation("representation 1");
    sip.addRepresentation(representation1);

    // 1.9.1) add a file to the representation
    URI url = Paths.get("src/test/resources/data/data.txt").toUri();
    URI url2 =Paths.get("src/test/resources/data/descriptive.txt").toUri();
    URI url3 = Paths.get("src/test/resources/data/eark.pdf").toUri();
    // setting basic information about the remote file
    FileType filetype = new FileType();
    filetype.setMIMETYPE("application/pdf");
    filetype.setSIZE(784099L);
    filetype.setCREATED(Utils.getCurrentCalendar());
    filetype.setCHECKSUM("3df79d34abbca99308e79cb94461c1893582604d68329a41fd4bec1885e6adb4");
    filetype.setCHECKSUMTYPE(IPConstants.CHECKSUM_ALGORITHM);

    FileType filetype2 = new FileType();
    filetype.setMIMETYPE("application/pdf");
    filetype.setSIZE(784099L);
    filetype.setCREATED(Utils.getCurrentCalendar());
    filetype.setCHECKSUM("3df79d34abbca99308e79cb54461c1893582604d68329a41fd4bec1885e6adb4");
    filetype.setCHECKSUMTYPE(IPConstants.CHECKSUM_ALGORITHM);

    FileType filetype3 = new FileType();
    filetype.setMIMETYPE("application/pdf");
    filetype.setSIZE(784099L);
    filetype.setCREATED(Utils.getCurrentCalendar());
    filetype.setCHECKSUM("3df79d34abbca99308e78cb94461c1893582604d68329a41fd4bec1885e6adb4");
    filetype.setCHECKSUMTYPE(IPConstants.CHECKSUM_ALGORITHM);
    IPFileInterface representationFile = new IPFileShallow(url, filetype);
    List<String> relativeFolders = new ArrayList<>();
    relativeFolders.add("abc");
    relativeFolders.add("def");
    ((IPFileShallow) representationFile).setRelativeFolders(relativeFolders);

    // New fil

    IPFileInterface representationFile2 = new IPFileShallow(url2, filetype2);
    List<String> relativeFolders2 = new ArrayList<>();
    relativeFolders2.add("abc");
    relativeFolders2.add("fgh");
    ((IPFileShallow) representationFile2).setRelativeFolders(relativeFolders2);

    IPFileInterface representationFile3 = new IPFileShallow(url3, filetype3);
    List<String> relativeFolders3 = new ArrayList<>();
    ((IPFileShallow) representationFile3).setRelativeFolders(relativeFolders3);


    List<String> relativeFolders4 = new ArrayList<>();
    relativeFolders4.add("uuu");
    relativeFolders4.add("fff");
    IPFileInterface representationFile4 = new IPFileShallow(relativeFolders4);

    representation1.addFile(representationFile);
    representation1.addFile(representationFile2);
    representation1.addFile(representationFile3);
    representation1.addFile(representationFile4);

    // 2) build SIP, providing an output directory
    WriteStrategy writeStrategy = SIPBuilderUtils.getWriteStrategy(WriteStrategyEnum.ZIP, tempFolder);
    Path zipSIP = sip.build(writeStrategy, "okok", IPEnums.SipType.EARK2S);

    return zipSIP;
  }

  private Path createFullEARKSIPS220()
    throws IPException, InterruptedException, DatatypeConfigurationException, URISyntaxException {
    // 1) instantiate E-ARK SIP object
    SIP sip = new EARKSIP("SIP_S_1", IPContentType.getMIXED(), IPContentInformationType.getMIXED(), "2.2.0");
    sip.addCreatorSoftwareAgent("RODA Commons IP", "2.0.0");

    // 1.1) set optional human-readable description
    sip.setDescription("A full E-ARK SIP-S");

    // 1.2) add descriptive metadata (SIP level)
    IPDescriptiveMetadata metadataDescriptiveDC = new IPDescriptiveMetadata(
      new IPFile(Paths.get("src/test/resources/eark/metadata_descriptive_dc.xml")),
      new MetadataType(MetadataTypeEnum.DC), null);
    sip.addDescriptiveMetadata(metadataDescriptiveDC);

    // 1.3) add preservation metadata (SIP level)
    IPMetadata metadataPreservation = new IPMetadata(
      new IPFile(Paths.get("src/test/resources/eark/metadata_preservation_premis.xml")))
      .setMetadataType(MetadataTypeEnum.PREMIS);
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
    sip.setAncestors(Arrays.asList("b6f24059-8973-4582-932d-eb0b2cb48f28"));

    // 1.8) add an agent (SIP level)
    IPAgent agent = new IPAgent("Agent Name", "OTHER", "OTHER ROLE", CreatorType.INDIVIDUAL, "OTHER TYPE", "",
      IPAgentNoteTypeEnum.SOFTWARE_VERSION);
    sip.addAgent(agent);

    // 1.9) add a representation (status will be set to the default value, i.e.,
    // ORIGINAL)
    IPRepresentation representation1 = new IPRepresentation("representation 1");
    sip.addRepresentation(representation1);

    // 1.9.1) add a file to the representation
    URI url = Paths.get("src/test/resources/data/data.txt").toUri();
    URI url2 =Paths.get("src/test/resources/data/descriptive.txt").toUri();
    URI url3 = Paths.get("src/test/resources/data/eark.pdf").toUri();
    // setting basic information about the remote file
    FileType filetype = new FileType();
    filetype.setMIMETYPE("application/pdf");
    filetype.setSIZE(784099L);
    filetype.setCREATED(Utils.getCurrentCalendar());
    filetype.setCHECKSUM("3df79d34abbca99308e79cb94461c1893582604d68329a41fd4bec1885e6adb4");
    filetype.setCHECKSUMTYPE(IPConstants.CHECKSUM_ALGORITHM);

    FileType filetype2 = new FileType();
    filetype.setMIMETYPE("application/pdf");
    filetype.setSIZE(784099L);
    filetype.setCREATED(Utils.getCurrentCalendar());
    filetype.setCHECKSUM("3df79d34abbca99308e79cb54461c1893582604d68329a41fd4bec1885e6adb4");
    filetype.setCHECKSUMTYPE(IPConstants.CHECKSUM_ALGORITHM);

    FileType filetype3 = new FileType();
    filetype.setMIMETYPE("application/pdf");
    filetype.setSIZE(784099L);
    filetype.setCREATED(Utils.getCurrentCalendar());
    filetype.setCHECKSUM("3df79d34abbca99308e78cb94461c1893582604d68329a41fd4bec1885e6adb4");
    filetype.setCHECKSUMTYPE(IPConstants.CHECKSUM_ALGORITHM);
    IPFileInterface representationFile = new IPFileShallow(url, filetype);
    List<String> relativeFolders = new ArrayList<>();
    relativeFolders.add("abc");
    relativeFolders.add("def");
    ((IPFileShallow) representationFile).setRelativeFolders(relativeFolders);

    // New fil

    IPFileInterface representationFile2 = new IPFileShallow(url2, filetype2);
    List<String> relativeFolders2 = new ArrayList<>();
    relativeFolders2.add("abc");
    relativeFolders2.add("fgh");
    ((IPFileShallow) representationFile2).setRelativeFolders(relativeFolders2);

    IPFileInterface representationFile3 = new IPFileShallow(url3, filetype3);
    List<String> relativeFolders3 = new ArrayList<>();
    ((IPFileShallow) representationFile3).setRelativeFolders(relativeFolders3);


    List<String> relativeFolders4 = new ArrayList<>();
    relativeFolders4.add("uuu");
    relativeFolders4.add("fff");
    IPFileInterface representationFile4 = new IPFileShallow(relativeFolders4);

    representation1.addFile(representationFile);
    representation1.addFile(representationFile2);
    representation1.addFile(representationFile3);
    representation1.addFile(representationFile4);

    // 2) build SIP, providing an output directory
    WriteStrategy writeStrategy = SIPBuilderUtils.getWriteStrategy(WriteStrategyEnum.ZIP, tempFolder);
    Path zipSIP = sip.build(writeStrategy, "okok", IPEnums.SipType.EARK2S);

    return zipSIP;
  }


  private Path createFullEARKSIP() throws IPException, InterruptedException {

    // 1) instantiate E-ARK SIP object
    SIP sip = new EARKSIP("SIP_1", IPContentType.getMIXED(), IPContentInformationType.getMIXED(), "2.1.0");
    sip.addCreatorSoftwareAgent("RODA Commons IP", "2.0.0");

    // 1.1) set optional human-readable description
    sip.setDescription("A full E-ARK SIP");

    // 1.2) add descriptive metadata (SIP level)
    IPDescriptiveMetadata metadataDescriptiveDC = new IPDescriptiveMetadata(
      new IPFile(Paths.get("src/test/resources/eark/metadata_descriptive_dc.xml")),
      new MetadataType(MetadataTypeEnum.DC), null);
    sip.addDescriptiveMetadata(metadataDescriptiveDC);

    // 1.3) add preservation metadata (SIP level)
    IPMetadata metadataPreservation = new IPMetadata(
      new IPFile(Paths.get("src/test/resources/eark/metadata_preservation_premis.xml")))
        .setMetadataType(MetadataTypeEnum.PREMIS);
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
    sip.setAncestors(Arrays.asList("b6f24059-8973-4582-932d-eb0b2cb48f28"));

    // 1.8) add an agent (SIP level)
    IPAgent agent = new IPAgent("Agent Name", "OTHER", "OTHER ROLE", CreatorType.INDIVIDUAL, "OTHER TYPE", "",
      IPAgentNoteTypeEnum.SOFTWARE_VERSION);
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
    if (!Utils.systemIsWindows()) {
      IPFile representationFileEnc1 = new IPFile(Paths.get("src/test/resources/eark/documentation.pdf"));
      representationFileEnc1.setRenameTo("enc1_\u0001\u001F.pdf");
      representation1.addFile(representationFileEnc1);
    }

    IPFile representationFileEnc2 = new IPFile(Paths.get("src/test/resources/eark/documentation.pdf"));
    representationFileEnc2.setRenameTo("enc2_\u0080\u0081\u0090\u00FF.pdf");
    representation1.addFile(representationFileEnc2);

    IPFile representationFileEnc3 = new IPFile(Paths.get("src/test/resources/eark/documentation.pdf"));
    representationFileEnc3.setRenameTo(Utils.systemIsWindows() ? "enc3_;@=&.pdf" : "enc3_;?:@=&.pdf");
    representation1.addFile(representationFileEnc3);

    IPFile representationFileEnc4 = new IPFile(Paths.get("src/test/resources/eark/documentation.pdf"));
    representationFileEnc4
      .setRenameTo(Utils.systemIsWindows() ? "enc4_#%{}\\^~[ ]`.pdf" : "enc4_\"<>#%{}|\\^~[ ]`.pdf");
    representation1.addFile(representationFileEnc4);

    IPFile representationFileEnc5 = new IPFile(Paths.get("src/test/resources/eark/documentation.pdf"));
    representationFileEnc5
      .setRenameTo(Utils.systemIsWindows() ? "enc4_#+%{}\\^~[ ]`.pdf" : "enc4_\"<>+#%{}|\\^~[ ]`.pdf");
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
    WriteStrategy writeStrategy = SIPBuilderUtils.getWriteStrategy(WriteStrategyEnum.ZIP, tempFolder);
    Path zipSIP = sip.build(writeStrategy);

    return zipSIP;
  }

  private Path createFullEARKSIP220() throws IPException, InterruptedException {

    // 1) instantiate E-ARK SIP object
    SIP sip = new EARKSIP("SIP_1", IPContentType.getMIXED(), IPContentInformationType.getMIXED(), "2.2.0");
    sip.addCreatorSoftwareAgent("RODA Commons IP", "2.0.0");

    // 1.1) set optional human-readable description
    sip.setDescription("A full E-ARK SIP");

    // 1.2) add descriptive metadata (SIP level)
    IPDescriptiveMetadata metadataDescriptiveDC = new IPDescriptiveMetadata(
      new IPFile(Paths.get("src/test/resources/eark/metadata_descriptive_dc.xml")),
      new MetadataType(MetadataTypeEnum.DC), null);
    sip.addDescriptiveMetadata(metadataDescriptiveDC);

    // 1.3) add preservation metadata (SIP level)
    IPMetadata metadataPreservation = new IPMetadata(
      new IPFile(Paths.get("src/test/resources/eark/metadata_preservation_premis.xml")))
      .setMetadataType(MetadataTypeEnum.PREMIS);
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
    sip.setAncestors(Arrays.asList("b6f24059-8973-4582-932d-eb0b2cb48f28"));

    // 1.8) add an agent (SIP level)
    IPAgent agent = new IPAgent("Agent Name", "OTHER", "OTHER ROLE", CreatorType.INDIVIDUAL, "OTHER TYPE", "",
      IPAgentNoteTypeEnum.SOFTWARE_VERSION);
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
    if (!Utils.systemIsWindows()) {
      IPFile representationFileEnc1 = new IPFile(Paths.get("src/test/resources/eark/documentation.pdf"));
      representationFileEnc1.setRenameTo("enc1_\u0001\u001F.pdf");
      representation1.addFile(representationFileEnc1);
    }

    IPFile representationFileEnc2 = new IPFile(Paths.get("src/test/resources/eark/documentation.pdf"));
    representationFileEnc2.setRenameTo("enc2_\u0080\u0081\u0090\u00FF.pdf");
    representation1.addFile(representationFileEnc2);

    IPFile representationFileEnc3 = new IPFile(Paths.get("src/test/resources/eark/documentation.pdf"));
    representationFileEnc3.setRenameTo(Utils.systemIsWindows() ? "enc3_;@=&.pdf" : "enc3_;?:@=&.pdf");
    representation1.addFile(representationFileEnc3);

    IPFile representationFileEnc4 = new IPFile(Paths.get("src/test/resources/eark/documentation.pdf"));
    representationFileEnc4
      .setRenameTo(Utils.systemIsWindows() ? "enc4_#%{}\\^~[ ]`.pdf" : "enc4_\"<>#%{}|\\^~[ ]`.pdf");
    representation1.addFile(representationFileEnc4);

    IPFile representationFileEnc5 = new IPFile(Paths.get("src/test/resources/eark/documentation.pdf"));
    representationFileEnc5
      .setRenameTo(Utils.systemIsWindows() ? "enc4_#+%{}\\^~[ ]`.pdf" : "enc4_\"<>+#%{}|\\^~[ ]`.pdf");
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
    WriteStrategy writeStrategy = SIPBuilderUtils.getWriteStrategy(WriteStrategyEnum.ZIP, tempFolder);
    Path zipSIP = sip.build(writeStrategy);

    return zipSIP;
  }

  private void parseAndValidateFullEARKSIPS(Path zipSIPS) throws ParseException {
    EARKSIP earksip = new EARKSIP();
    // 1) invoke static method parse and that's it
    SIP earkSIP = earksip.parse(zipSIPS, tempFolder);

    // general assessment
    earkSIP.getValidationReport().getValidationEntries().stream().filter(e -> e.getLevel() == LEVEL.ERROR)
      .forEach(e -> LOGGER.error("Validation report entry: {}", e));
    Assert.assertTrue(earkSIP.getValidationReport().isValid());

    // assess # of representations
    List<IPRepresentation> representations = earkSIP.getRepresentations();
    Assert.assertThat(representations.size(), Is.is(1));

    // assess representations status
    Assert.assertThat(representations.get(0).getStatus().asString(),
      Is.is(RepresentationStatus.getORIGINAL().asString()));

    LOGGER.info("SIP with id '{}' parsed with success (valid? {})!", earkSIP.getId(),
      earkSIP.getValidationReport().isValid());
  }

  private void parseAndValidateFullEARKSIP(Path zipSIP) throws ParseException {

    EARKSIP earksip = new EARKSIP();

    // 1) invoke static method parse and that's it
    SIP earkSIP = earksip.parse(zipSIP, tempFolder);

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

  private Path createFullEARKSIP_For_Test_Compliance() throws IPException, InterruptedException {

    // 1) instantiate E-ARK SIP object
    SIP sip = new EARKSIP("SIP_1", IPContentType.getMIXED(), IPContentInformationType.getMIXED(), "2.1.0");
    sip.addCreatorSoftwareAgent("RODA Commons IP", "2.0.0");

    // 1.1) set optional human-readable description
    sip.setDescription("A full E-ARK SIP");

    // 1.2) add descriptive metadata (SIP level)
    IPDescriptiveMetadata metadataDescriptiveDC = new IPDescriptiveMetadata(
      new IPFile(Paths.get("src/test/resources/eark/metadata_descriptive_dc.xml")),
      new MetadataType(MetadataTypeEnum.DC), null);
    sip.addDescriptiveMetadata(metadataDescriptiveDC);

    // 1.3) add preservation metadata (SIP level)
    IPMetadata metadataPreservation = new IPMetadata(
      new IPFile(Paths.get("src/test/resources/eark/metadata_preservation_premis.xml")))
        .setMetadataType(MetadataTypeEnum.PREMIS);
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
    sip.setAncestors(Arrays.asList("b6f24059-8973-4582-932d-eb0b2cb48f28"));

    // 1.8) add an agent (SIP level)
    IPAgent agent = new IPAgent("Agent Name", "CREATOR", "", CreatorType.INDIVIDUAL, "OTHER TYPE", "",
      IPAgentNoteTypeEnum.IDENTIFICATIONCODE);
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
    if (!Utils.systemIsWindows()) {
      IPFile representationFileEnc1 = new IPFile(Paths.get("src/test/resources/eark/documentation.pdf"));
      representationFileEnc1.setRenameTo("enc1_\u0001\u001F.pdf");
      representation1.addFile(representationFileEnc1);
    }

    IPFile representationFileEnc2 = new IPFile(Paths.get("src/test/resources/eark/documentation.pdf"));
    representationFileEnc2.setRenameTo("enc2_\u0080\u0081\u0090\u00FF.pdf");
    representation1.addFile(representationFileEnc2);

    IPFile representationFileEnc3 = new IPFile(Paths.get("src/test/resources/eark/documentation.pdf"));
    representationFileEnc3.setRenameTo(Utils.systemIsWindows() ? "enc3_;@=&.pdf" : "enc3_;?:@=&.pdf");
    representation1.addFile(representationFileEnc3);

    IPFile representationFileEnc4 = new IPFile(Paths.get("src/test/resources/eark/documentation.pdf"));
    representationFileEnc4
      .setRenameTo(Utils.systemIsWindows() ? "enc4_#%{}\\^~[ ]`.pdf" : "enc4_\"<>#%{}|\\^~[ ]`.pdf");
    representation1.addFile(representationFileEnc4);

    IPFile representationFileEnc5 = new IPFile(Paths.get("src/test/resources/eark/documentation.pdf"));
    representationFileEnc5
      .setRenameTo(Utils.systemIsWindows() ? "enc4_#+%{}\\^~[ ]`.pdf" : "enc4_\"<>+#%{}|\\^~[ ]`.pdf");
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
    WriteStrategy writeStrategy = SIPBuilderUtils.getWriteStrategy(WriteStrategyEnum.ZIP, tempFolder);
    Path zipSIP = sip.build(writeStrategy);

    return zipSIP;
  }

  private Path createFullEARKSIP_For_Test_Compliance220() throws IPException, InterruptedException {

    // 1) instantiate E-ARK SIP object
    SIP sip = new EARKSIP("SIP_1", IPContentType.getMIXED(), IPContentInformationType.getMIXED(), "2.2.0");
    sip.addCreatorSoftwareAgent("RODA Commons IP", "2.0.0");

    // 1.1) set optional human-readable description
    sip.setDescription("A full E-ARK SIP");

    // 1.2) add descriptive metadata (SIP level)
    IPDescriptiveMetadata metadataDescriptiveDC = new IPDescriptiveMetadata(
      new IPFile(Paths.get("src/test/resources/eark/metadata_descriptive_dc.xml")),
      new MetadataType(MetadataTypeEnum.DC), null);
    sip.addDescriptiveMetadata(metadataDescriptiveDC);

    // 1.3) add preservation metadata (SIP level)
    IPMetadata metadataPreservation = new IPMetadata(
      new IPFile(Paths.get("src/test/resources/eark/metadata_preservation_premis.xml")))
      .setMetadataType(MetadataTypeEnum.PREMIS);
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
    sip.setAncestors(Arrays.asList("b6f24059-8973-4582-932d-eb0b2cb48f28"));

    // 1.8) add an agent (SIP level)
    IPAgent agent = new IPAgent("Agent Name", "CREATOR", "", CreatorType.INDIVIDUAL, "OTHER TYPE", "",
      IPAgentNoteTypeEnum.IDENTIFICATIONCODE);
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
    if (!Utils.systemIsWindows()) {
      IPFile representationFileEnc1 = new IPFile(Paths.get("src/test/resources/eark/documentation.pdf"));
      representationFileEnc1.setRenameTo("enc1_\u0001\u001F.pdf");
      representation1.addFile(representationFileEnc1);
    }

    IPFile representationFileEnc2 = new IPFile(Paths.get("src/test/resources/eark/documentation.pdf"));
    representationFileEnc2.setRenameTo("enc2_\u0080\u0081\u0090\u00FF.pdf");
    representation1.addFile(representationFileEnc2);

    IPFile representationFileEnc3 = new IPFile(Paths.get("src/test/resources/eark/documentation.pdf"));
    representationFileEnc3.setRenameTo(Utils.systemIsWindows() ? "enc3_;@=&.pdf" : "enc3_;?:@=&.pdf");
    representation1.addFile(representationFileEnc3);

    IPFile representationFileEnc4 = new IPFile(Paths.get("src/test/resources/eark/documentation.pdf"));
    representationFileEnc4
      .setRenameTo(Utils.systemIsWindows() ? "enc4_#%{}\\^~[ ]`.pdf" : "enc4_\"<>#%{}|\\^~[ ]`.pdf");
    representation1.addFile(representationFileEnc4);

    IPFile representationFileEnc5 = new IPFile(Paths.get("src/test/resources/eark/documentation.pdf"));
    representationFileEnc5
      .setRenameTo(Utils.systemIsWindows() ? "enc4_#+%{}\\^~[ ]`.pdf" : "enc4_\"<>+#%{}|\\^~[ ]`.pdf");
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
    WriteStrategy writeStrategy = SIPBuilderUtils.getWriteStrategy(WriteStrategyEnum.ZIP, tempFolder);
    Path zipSIP = sip.build(writeStrategy);

    return zipSIP;
  }

  /**
   * Test that pre-calculated checksums are properly used during SIP generation.
   * When a file has its checksum set before SIP creation, that checksum should be
   * used instead of recalculating it, which is important for large files.
   *
   * <p>This test uses a FAKE checksum to prove the library uses the pre-calculated
   * value rather than calculating it. If the library were to calculate the checksum,
   * it would not match our fake value.</p>
   *
   * @throws IPException if an error occurs during SIP creation
   * @throws InterruptedException if the thread is interrupted
   * @throws IOException if an I/O error occurs
   * @throws ParserConfigurationException if a parser configuration error occurs
   * @throws SAXException if a SAX parsing error occurs
   * @throws ParseException if a parsing error occurs
   * @throws NoSuchAlgorithmException if the checksum algorithm is not available
   */
  @Test
  public void testPreCalculatedChecksumSupport() throws IPException, InterruptedException, IOException,
    ParserConfigurationException, SAXException, ParseException, NoSuchAlgorithmException {
    LOGGER.info("Testing pre-calculated checksum support");

    // 1) Create SIP with pre-calculated checksum
    final SIP sip = new EARKSIP(SIP_CHECKSUM_TEST_ID, IPContentType.getMIXED(), IPContentInformationType.getMIXED(),
      "2.1.0");
    sip.addCreatorSoftwareAgent("RODA Commons IP", "2.0.0");
    sip.setDescription("SIP with pre-calculated checksums");

    // 2) Add descriptive metadata
    final IPDescriptiveMetadata metadataDescriptiveDC = new IPDescriptiveMetadata(
      new IPFile(Paths.get("src/test/resources/eark/metadata_descriptive_dc.xml")),
      new MetadataType(MetadataTypeEnum.DC), null);
    sip.addDescriptiveMetadata(metadataDescriptiveDC);

    // 3) Create a representation with a file that has a pre-calculated checksum
    final IPRepresentation representation = new IPRepresentation(REPRESENTATION_WITH_CHECKSUM);
    sip.addRepresentation(representation);

    // Use a FAKE checksum - this proves the library uses our value instead of calculating
    // If the library calculated the checksum, it would NOT match this fake value
    final String fakeChecksum = "AABBCCDD11223344556677889900AABBCCDD11223344556677889900AABBCCDD";

    final Path testFilePath = Paths.get("src/test/resources/eark/documentation.pdf");
    final IPFile representationFile = new IPFile(testFilePath);
    representationFile.setRenameTo("data_with_checksum.pdf");
    // Set the FAKE pre-calculated checksum
    representationFile.setChecksum(fakeChecksum);
    representationFile.setChecksumAlgorithm("SHA-256");
    representation.addFile(representationFile);

    // 4) Build SIP
    final WriteStrategy writeStrategy = SIPBuilderUtils.getWriteStrategy(WriteStrategyEnum.ZIP, tempFolder);
    final Path zipSIP = sip.build(writeStrategy);

    LOGGER.info("SIP built successfully with pre-calculated checksum at: {}", zipSIP);

    // 5) Extract and verify the fake checksum appears in the representation METS file
    final Path extractDir = tempFolder.resolve("extracted_sip");
    Files.createDirectories(extractDir);

    // Extract the ZIP
    try (java.util.zip.ZipInputStream zis = new java.util.zip.ZipInputStream(Files.newInputStream(zipSIP))) {
      java.util.zip.ZipEntry entry;
      while ((entry = zis.getNextEntry()) != null) {
        final Path targetPath = extractDir.resolve(entry.getName());
        if (entry.isDirectory()) {
          Files.createDirectories(targetPath);
        } else {
          Files.createDirectories(targetPath.getParent());
          Files.copy(zis, targetPath);
        }
        zis.closeEntry();
      }
    }

    // Find and read the representation METS file
    final Path representationMetsPath = extractDir.resolve(SIP_CHECKSUM_TEST_ID)
      .resolve("representations")
      .resolve(REPRESENTATION_WITH_CHECKSUM)
      .resolve("METS.xml");

    Assert.assertTrue("Representation METS file should exist", Files.exists(representationMetsPath));

    final String metsContent = Files.readString(representationMetsPath);

    // Verify our FAKE checksum appears in the METS file
    Assert.assertTrue(
      "The METS file should contain our pre-calculated fake checksum, proving it was used instead of being calculated",
      metsContent.contains(fakeChecksum));

    LOGGER.info(
      "SUCCESS: The fake checksum '{}' was found in the METS file, proving pre-calculated checksums work correctly",
      fakeChecksum);
  }

}
