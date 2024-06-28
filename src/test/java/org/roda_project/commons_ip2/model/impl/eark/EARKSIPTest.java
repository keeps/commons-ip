/**
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE file at the root of the source
 * tree and available online at
 *
 * https://github.com/keeps/commons-ip
 */
package org.roda_project.commons_ip2.model.impl.eark;

import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Test;
import org.roda_project.commons_ip.model.ParseException;
import org.roda_project.commons_ip.utils.IPEnums;
import org.roda_project.commons_ip.utils.IPException;
import org.roda_project.commons_ip.utils.METSEnums.CreatorType;
import org.roda_project.commons_ip2.mets_v1_12.beans.FileType;
import org.roda_project.commons_ip2.model.*;
import org.roda_project.commons_ip2.model.MetadataType.MetadataTypeEnum;
import org.roda_project.commons_ip2.model.ValidationEntry.LEVEL;
import org.roda_project.commons_ip2.utils.Utils;
import org.roda_project.commons_ip2.validator.EARKSIPValidator;
import org.roda_project.commons_ip2.validator.constants.Constants;
import org.roda_project.commons_ip2.validator.reporter.ValidationReportOutputJson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.roda_project.commons_ip.model.IPConstants.CHECKSUM_MD5_ALGORITHM;

/**
 * Unit tests for EARK Information Packages (SIP, AIP and DIP)
 */
public class EARKSIPTest {
  private static final String REPRESENTATION_STATUS_NORMALIZED = "NORMALIZED";

  private static final Logger LOGGER = LoggerFactory.getLogger(EARKSIPTest.class);

  @Test
  public void test_buildParseAndValidateEARKSIP_Zipped() throws IPException, ParseException, IOException, ParserConfigurationException, NoSuchAlgorithmException, InterruptedException, SAXException {
    Path tempFolder = Files.createTempDirectory("temp" + UUID.randomUUID());
    try {
      buildParseAndValidateEARKSIP(true, false, tempFolder);
    }
    finally {
      Utils.deletePath(tempFolder);
    }
  }

  @Test
  public void test_buildParseAndValidateEARKSIP_Zipped_WithPregeneratedChecksums() throws IPException, ParseException, IOException, ParserConfigurationException, NoSuchAlgorithmException, InterruptedException, SAXException {
    Path tempFolder = Files.createTempDirectory("temp" + UUID.randomUUID());
    try {
      buildParseAndValidateEARKSIP(true, true, tempFolder);
    }
    finally {
      Utils.deletePath(tempFolder);
    }
  }

  @Test
  public void test_buildParseAndValidateEARKSIP_Unzipped() throws IPException, ParseException, IOException, ParserConfigurationException, NoSuchAlgorithmException, InterruptedException, SAXException {
    Path tempFolder = Files.createTempDirectory("temp" + UUID.randomUUID());
    try {
      buildParseAndValidateEARKSIP(false, false, tempFolder);
    }
    finally {
      Utils.deletePath(tempFolder);
    }
  }

  @Test
  public void test_buildParseAndValidateEARKSIP_Unzipped_WithExistingChecksum() throws IPException, ParseException, IOException, ParserConfigurationException, NoSuchAlgorithmException, InterruptedException, SAXException {
    Path tempFolder = Files.createTempDirectory("temp" + UUID.randomUUID());
    try {
      buildParseAndValidateEARKSIP(false, true, tempFolder);
    }
    finally {
      Utils.deletePath(tempFolder);
    }
  }

  @Test
  public void buildAndParseEARKSIP() throws IPException, ParseException, InterruptedException, IOException, NoSuchAlgorithmException {
    Path tempFolder = Files.createTempDirectory("temp" + UUID.randomUUID());
    try {
      Path zipSIP = createFullEARKSIP(true, false, false, tempFolder);
      parseAndValidateFullEARKSIP(zipSIP, true, tempFolder);
    }
    finally {
      Utils.deletePath(tempFolder);
    }
  }

  @Test
  public void buildEARKSIPShallow()
          throws IPException, InterruptedException, DatatypeConfigurationException, ParseException, URISyntaxException, IOException, NoSuchAlgorithmException {
    // TODO: parseAndValidateFullEARKSIPS(zipSIPS); ?
    Path tempFolder = Files.createTempDirectory("temp" + UUID.randomUUID());
    try {
      createFullEARKSIPS(false, tempFolder);
    }
    finally {
      Utils.deletePath(tempFolder);
    }
  }

  private void buildParseAndValidateEARKSIP(boolean zipIt, boolean hasPregeneratedChecksums, Path tempFolder) throws IPException, ParseException, InterruptedException, IOException,
          ParserConfigurationException, SAXException, NoSuchAlgorithmException {

    Path sipPath = createFullEARKSIP(zipIt, true, hasPregeneratedChecksums, tempFolder);

    parseAndValidateFullEARKSIP(sipPath, zipIt, tempFolder);

    Path reportPath = Files.createTempDirectory("reports").resolve("Full-EARK-SIP.json");
    Files.deleteIfExists(reportPath);
    try {
      Files.createFile(reportPath);
    } catch (IOException e) {
      reportPath = Files.createTempFile(Constants.VALIDATION_REPORT_PREFIX, ".json");
    }

    OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(reportPath.toFile()));
    ValidationReportOutputJson reportOutputJson = new ValidationReportOutputJson(sipPath, outputStream);
    EARKSIPValidator earksipValidator = new EARKSIPValidator(reportOutputJson, "2.1.0");
    boolean validate = earksipValidator.validate("2.1.0");

    LOGGER.info("Done parsing (and validating) full E-ARK SIP");
    Assert.assertTrue(validate);
  }

  private Path createFullEARKSIPS(boolean hasPregeneratedChecksums, Path tempFolder)
          throws IPException, InterruptedException, DatatypeConfigurationException, URISyntaxException, IOException, NoSuchAlgorithmException {
    // 1) instantiate E-ARK SIP object
    SIP sip = new EARKSIP("SIP_S_1", IPContentType.getMIXED(), IPContentInformationType.getMIXED(), "2.1.0");
    sip.setChecksumAlgorithm(CHECKSUM_MD5_ALGORITHM);
    sip.setHasPregeneratedChecksums(hasPregeneratedChecksums);

    sip.addCreatorSoftwareAgent("RODA Commons IP", "2.0.0");

    // 1.1) set optional human-readable description
    sip.setDescription("A full E-ARK SIP-S");

    // 1.2) add descriptive metadata (SIP level)
    addDescriptiveMetadata(sip, "src/test/resources/eark", "metadata_descriptive_dc.xml", hasPregeneratedChecksums);

    // 1.3) add preservation metadata (SIP level)
    addPreservationMetadata(sip,"src/test/resources/eark", "metadata_preservation_premis.xml", hasPregeneratedChecksums);

    // 1.4) add other metadata (SIP level)
    addOtherMetadata(sip,"src/test/resources/eark", "metadata_other.txt", "metadata_other_renamed.txt", hasPregeneratedChecksums);
    addOtherMetadata(sip,"src/test/resources/eark", "metadata_other.txt","metadata_other_renamed2.txt", hasPregeneratedChecksums);

    // 1.5) add xml schema (SIP level)
    addSchema(sip,"src/test/resources/eark", "schema.xsd", hasPregeneratedChecksums);

    // 1.6) add documentation (SIP level)
    addDocumentation(sip,"src/test/resources/eark", "documentation.pdf", hasPregeneratedChecksums);

    // 1.7) set optional RODA related information about ancestors
    sip.setAncestors(List.of("b6f24059-8973-4582-932d-eb0b2cb48f28"));

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
    Path zipSIP = sip.build(tempFolder, "okok", IPEnums.SipType.EARK2S);

    return zipSIP;
  }

  private void parseAndValidateFullEARKSIP(Path sipPath, boolean isZipped, Path tempFolder) throws ParseException {
    LOGGER.info("Parsing (and validating) full E-ARK SIP");

    EARKSIP earksip = new EARKSIP();

    // 1) invoke static method parse and that's it
    SIP earkSIP = isZipped ? earksip.parse(sipPath, tempFolder) : earksip.parseUnzipped(sipPath);

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

  private Path createFullEARKSIP(boolean zipIt, boolean isTestCompilation, boolean hasPregeneratedChecksums, Path tempFolder) throws IPException, InterruptedException, IOException, NoSuchAlgorithmException {
    LOGGER.info("Creating full E-ARK SIP");

    // 1) instantiate E-ARK SIP object
    SIP sip = new EARKSIP("SIP_" + UUID.randomUUID(), IPContentType.getMIXED(), IPContentInformationType.getMIXED(), "2.1.0");
    sip.addCreatorSoftwareAgent("RODA Commons IP", "2.0.0");
    sip.setShouldOutputInZip(zipIt);
    sip.setChecksumAlgorithm(CHECKSUM_MD5_ALGORITHM);
    sip.setHasPregeneratedChecksums(hasPregeneratedChecksums);

    // 1.1) set optional human-readable description
    sip.setDescription("A full E-ARK SIP");

    // 1.2) add descriptive metadata (SIP level)
    addDescriptiveMetadata(sip,"src/test/resources/eark", "metadata_descriptive_dc.xml", hasPregeneratedChecksums);

    // 1.3) add preservation metadata (SIP level)
    addPreservationMetadata(sip, "src/test/resources/eark", "metadata_preservation_premis.xml", hasPregeneratedChecksums);

    // 1.4) add other metadata (SIP level)
    addOtherMetadata(sip, "src/test/resources/eark", "metadata_other.txt", "metadata_other_renamed.txt", hasPregeneratedChecksums);
    addOtherMetadata(sip, "src/test/resources/eark", "metadata_other.txt","metadata_other_renamed2.txt", hasPregeneratedChecksums);

    // 1.5) add xml schema (SIP level)
    addSchema(sip,"src/test/resources/eark", "schema.xsd", hasPregeneratedChecksums);

    // 1.6) add documentation (SIP level)
    addDocumentation(sip, "src/test/resources/eark", "documentation.pdf", hasPregeneratedChecksums);

    // 1.7) set optional RODA related information about ancestors
    sip.setAncestors(List.of("b6f24059-8973-4582-932d-eb0b2cb48f28"));

    // 1.8) add an agent (SIP level)
    if(isTestCompilation){
      IPAgent agent = new IPAgent("Agent Name", "CREATOR", "", CreatorType.INDIVIDUAL, "OTHER TYPE", "",
              IPAgentNoteTypeEnum.IDENTIFICATIONCODE);
      sip.addAgent(agent);
    }
    else{
      IPAgent agent = new IPAgent("Agent Name", "OTHER", "OTHER ROLE", CreatorType.INDIVIDUAL, "OTHER TYPE", "",
              IPAgentNoteTypeEnum.SOFTWARE_VERSION);
      sip.addAgent(agent);
    }

    // 1.9) add a representation (status will be set to the default value, i.e.,
    // ORIGINAL)
    IPRepresentation representation1 = new IPRepresentation("representation 1");
    sip.addRepresentation(representation1);

    // 1.9.1) add a file to the representation
    addRepresentation("src/test/resources/eark", "documentation.pdf", "data_.pdf", representation1, null, hasPregeneratedChecksums);

    // SIDE TEST: encoding
    if (!Utils.systemIsWindows()) {
      addRepresentation("src/test/resources/eark", "documentation.pdf", "enc1_\u0001\u001F.pdf", representation1, null, hasPregeneratedChecksums);
    }

    addRepresentation("src/test/resources/eark", "documentation.pdf", "enc2_\u0080\u0081\u0090\u00FF.pdf", representation1, null, hasPregeneratedChecksums);
    addRepresentation("src/test/resources/eark", "documentation.pdf", Utils.systemIsWindows() ? "enc3_;@=&.pdf" : "enc3_;?:@=&.pdf", representation1, null, hasPregeneratedChecksums);
    addRepresentation("src/test/resources/eark", "documentation.pdf", Utils.systemIsWindows() ? "enc4_#%{}\\^~[ ]`.pdf" : "enc4_\"<>#%{}|\\^~[ ]`.pdf", representation1, null, hasPregeneratedChecksums);
    addRepresentation("src/test/resources/eark", "documentation.pdf", Utils.systemIsWindows() ? "enc4_#+%{}\\^~[ ]`.pdf" : "enc4_\"<>+#%{}|\\^~[ ]`.pdf", representation1, null, hasPregeneratedChecksums);

    // 1.9.2) add a file to the representation and put it inside a folder
    // called 'abc' which has a folder inside called 'def'
    addRepresentation("src/test/resources/eark", "documentation.pdf", Utils.systemIsWindows() ? "enc4_#+%{}\\^~[ ]`.pdf" : "enc4_\"<>+#%{}|\\^~[ ]`.pdf", representation1, Arrays.asList("abc", "def"), hasPregeneratedChecksums);

    // 1.10) add a representation & define its status
    IPRepresentation representation2 = new IPRepresentation("representation 2");
    representation2.setStatus(new RepresentationStatus(REPRESENTATION_STATUS_NORMALIZED));
    sip.addRepresentation(representation2);

    // 1.10.1) add a file to the representation
    addRepresentation("src/test/resources/eark", "documentation.pdf", "data3.pdf", representation2, null, hasPregeneratedChecksums);

    // 2) build SIP, providing an output directory
    Path outputPath = sip.build(tempFolder);

    LOGGER.info("Done creating full E-ARK SIP");
    return outputPath;
  }

  private static void addRepresentation(String originalFilePath, String originalFilename, String filenameRename, IPRepresentation ipRepresentation, List<String> relativeFolders, boolean hasPregeneratedChecksums) throws IPException, IOException, NoSuchAlgorithmException {
    Path representationFilePath = Paths.get(originalFilePath).resolve(originalFilename);

    IPFile representationFile = new IPFile(representationFilePath);
    representationFile.setRenameTo(filenameRename);

    if(hasPregeneratedChecksums){
      generateAndSetChecksum(representationFile, representationFilePath);
    }

    if(relativeFolders != null && !relativeFolders.isEmpty()){
      representationFile.setRelativeFolders(relativeFolders);
    }

    ipRepresentation.addFile(representationFile);
  }

  private static void addDocumentation(SIP sip, String filePath, String originalFileName, boolean hasPregeneratedChecksums) throws NoSuchAlgorithmException, IOException {
    Path documentationFilePath = Paths.get(filePath).resolve(originalFileName);
    IPFile documentationFile = new IPFile(documentationFilePath);
    if(hasPregeneratedChecksums){
      generateAndSetChecksum(documentationFile, documentationFilePath);
    }

    sip.addDocumentation(documentationFile);
  }

  private static void addSchema(SIP sip, String filePath, String originalFileName, boolean hasPregeneratedChecksums) throws NoSuchAlgorithmException, IOException {
    Path schemaFilePath = Paths.get(filePath).resolve(originalFileName);
    IPFile schemaFile = new IPFile(schemaFilePath);
    if(hasPregeneratedChecksums){
      generateAndSetChecksum(schemaFile, schemaFilePath);
    }

    sip.addSchema(schemaFile);
  }

  private static void addOtherMetadata(SIP sip, String originalFilePath, String originalFilename, String filenameRename, boolean hasPregeneratedChecksums) throws IPException, NoSuchAlgorithmException, IOException {
    Path otherMetadataPath = Paths.get(originalFilePath).resolve(originalFilename);
    IPFile metadataOtherFile = new IPFile(otherMetadataPath);
    if(hasPregeneratedChecksums){
      generateAndSetChecksum(metadataOtherFile, otherMetadataPath);
    }

    metadataOtherFile.setRenameTo(filenameRename);
    IPMetadata metadataOther = new IPMetadata(metadataOtherFile);
    sip.addOtherMetadata(metadataOther);
  }

  private static void addPreservationMetadata(SIP sip, String originalFilePath, String originalFileName, boolean hasPregeneratedChecksums) throws IPException, NoSuchAlgorithmException, IOException {
    Path premisFilePath = Paths.get(originalFilePath).resolve(originalFileName);
    IPFile ipFile = new IPFile(premisFilePath);
    if(hasPregeneratedChecksums){
      generateAndSetChecksum(ipFile, premisFilePath);
    }

    IPMetadata metadataPreservation = new IPMetadata(
      ipFile
    ).setMetadataType(MetadataTypeEnum.PREMIS);

    sip.addPreservationMetadata(metadataPreservation);
  }

  private static void addDescriptiveMetadata(SIP sip, String originalFilePath, String originalFileName, boolean hasPregeneratedChecksums) throws IPException, NoSuchAlgorithmException, IOException {
    Path descriptiveFilePath = Paths.get(originalFilePath).resolve(originalFileName);

    IPFile ipFile = new IPFile(descriptiveFilePath);
    if(hasPregeneratedChecksums){
      generateAndSetChecksum(ipFile, descriptiveFilePath);
    }

    IPDescriptiveMetadata metadataDescriptiveDC = new IPDescriptiveMetadata(
      ipFile,
      new MetadataType(MetadataTypeEnum.DC), null);
    sip.addDescriptiveMetadata(metadataDescriptiveDC);
  }

  private static IPFile generateAndSetChecksum(IPFile ipFile, Path filePath) throws NoSuchAlgorithmException, IOException {
    String checksum = generateChecksum(filePath, CHECKSUM_MD5_ALGORITHM, 1024);
    ipFile.setChecksum(checksum);
    ipFile.setChecksumAlgorithm(CHECKSUM_MD5_ALGORITHM);

    return ipFile;
  }

  public static String generateChecksum(Path filePath, String digestAlgorithm, Integer bufferSize) throws NoSuchAlgorithmException, IOException {

    MessageDigest md = MessageDigest.getInstance(digestAlgorithm);
    try (FileInputStream fis = new FileInputStream(filePath.toFile())) {
      byte[] buffer = new byte[bufferSize];
      int bytesRead;
      while ((bytesRead = fis.read(buffer)) != -1) {
        md.update(buffer, 0, bytesRead);
      }
    }
    catch (IOException e) {
      throw e;
    }

    return bytesToHex(md.digest());
  }

  private static String bytesToHex(byte[] bytes) {
    StringBuilder sb = new StringBuilder();
    for (byte b : bytes) {
      sb.append(String.format("%02x", b));
    }
    return sb.toString();
  }
}
