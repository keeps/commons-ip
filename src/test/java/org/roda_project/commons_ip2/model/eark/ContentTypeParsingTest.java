/**
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE file at the root of the source
 * tree and available online at
 *
 * https://github.com/keeps/commons-ip
 */
package org.roda_project.commons_ip2.model.impl.eark;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.roda_project.commons_ip.model.ParseException;
import org.roda_project.commons_ip.utils.METSEnums.CreatorType;
import org.roda_project.commons_ip2.cli.model.enums.WriteStrategyEnum;
import org.roda_project.commons_ip2.cli.utils.SIPBuilderUtils;
import org.roda_project.commons_ip2.mets_v1_12.beans.Mets;
import org.roda_project.commons_ip2.mets_v1_12.beans.MetsType;
import org.roda_project.commons_ip2.model.IPAgent;
import org.roda_project.commons_ip2.model.IPAgentNoteTypeEnum;
import org.roda_project.commons_ip2.model.IPContentInformationType;
import org.roda_project.commons_ip2.model.IPContentInformationType.IPContentInformationTypeEnum;
import org.roda_project.commons_ip2.model.IPContentType;
import org.roda_project.commons_ip2.model.IPContentType.IPContentTypeEnum;
import org.roda_project.commons_ip2.model.IPRepresentation;
import org.roda_project.commons_ip2.model.SIP;
import org.roda_project.commons_ip2.utils.Utils;

/**
 * Tests content type parsing from METS attributes.
 */
public class ContentTypeParsingTest {

  /** OAIS package type value for SIP. */
  private static final String SIP_TYPE = "SIP";
  /** SIP version used for generator selection. */
  private static final String SIP_VERSION = "2.1.0";
  /** Example non-OTHER content type. */
  private static final String TYPE_TEXT = "Text";
  /** Example OTHER content type value. */
  private static final String TYPE_OTHER = "Other";
  /** Uppercase OTHER marker used in METS attributes. */
  private static final String TYPE_OTHER_UPPER = "OTHER";
  /** Example OTHERTYPE value. */
  private static final String OTHER_TYPE_VALUE = "Moving images - on tangible media";
  /** Example content information type. */
  private static final String CONTENT_INFO_TYPE = "ERMS";
  /** Example OTHERCONTENTINFORMATIONTYPE value. */
  private static final String OTHER_CONTENT_INFO_VALUE = "Test of other CITS";
  /** Default content information type string. */
  private static final String MIXED = "MIXED";
  /** Id of the SIPs built for the round-trip tests. */
  private static final String SIP_ID = "SIP_1";
  /** Id of the representation in the round-trip tests. */
  private static final String REPRESENTATION_ID = "rep1";

  /** Folder that built and parsed SIPs are written to. */
  private static Path tempFolder;

  /**
   * Creates the folder for built and parsed SIPs.
   */
  @BeforeClass
  public static void setup() throws IOException {
    tempFolder = Files.createTempDirectory("temp");
  }

  /**
   * Deletes the folder for built and parsed SIPs.
   */
  @AfterClass
  public static void cleanup() throws Exception {
    Utils.deletePath(tempFolder);
  }

  /**
   * Uses METS TYPE when TYPE is not OTHER.
   */
  @Test
  public void contentTypeUsesType() throws ParseException {
    final Mets mets = newSipMets();
    mets.setTYPE(TYPE_TEXT);
    mets.setOTHERTYPE(OTHER_TYPE_VALUE);

    final SIP sip = new EARKSIP();
    newUtils().setIPContentType(mets, sip);

    Assert.assertEquals(IPContentTypeEnum.TEXT, sip.getContentType().getType());
  }

  /**
   * Uses METS OTHERTYPE when TYPE is OTHER.
   */
  @Test
  public void contentTypeUsesOtherType() throws ParseException {
    final Mets mets = newSipMets();
    mets.setTYPE(TYPE_OTHER);
    mets.setOTHERTYPE(OTHER_TYPE_VALUE);

    final SIP sip = new EARKSIP();
    newUtils().setIPContentType(mets, sip);

    Assert.assertEquals(IPContentTypeEnum.OTHER, sip.getContentType().getType());
    Assert.assertEquals(OTHER_TYPE_VALUE, sip.getContentType().asString());
  }

  /**
   * Keeps TYPE OTHER when OTHERTYPE is missing, as written by the METS creator.
   */
  @Test
  public void contentTypeOtherWithoutOtherTypeIsAccepted() throws ParseException {
    final Mets mets = newSipMets();
    mets.setTYPE(TYPE_OTHER);

    final SIP sip = new EARKSIP();
    newUtils().setIPContentType(mets, sip);

    Assert.assertEquals(IPContentTypeEnum.OTHER, sip.getContentType().getType());
  }

  /**
   * Leaves content type default when TYPE is blank.
   */
  @Test
  public void contentTypeBlankKeepsDefault() throws ParseException {
    final Mets mets = newSipMets();
    mets.setTYPE("");

    final SIP sip = new EARKSIP();
    newUtils().setIPContentType(mets, sip);

    Assert.assertEquals(IPContentTypeEnum.MIXED, sip.getContentType().getType());
  }

  /**
   * Uses CONTENTINFORMATIONTYPE when not OTHER.
   */
  @Test
  public void contentInfoUsesType() throws ParseException {
    final Mets mets = newSipMets();
    mets.setCONTENTINFORMATIONTYPE(CONTENT_INFO_TYPE);
    mets.setOTHERCONTENTINFORMATIONTYPE(OTHER_CONTENT_INFO_VALUE);

    final SIP sip = new EARKSIP();
    newUtils().setIPContentType(mets, sip);

    Assert.assertEquals(CONTENT_INFO_TYPE, sip.getContentInformationType().asString());
  }

  /**
   * Uses OTHERCONTENTINFORMATIONTYPE when CONTENTINFORMATIONTYPE is OTHER.
   */
  @Test
  public void contentInfoUsesOtherType() throws ParseException {
    final Mets mets = newSipMets();
    mets.setCONTENTINFORMATIONTYPE(TYPE_OTHER_UPPER);
    mets.setOTHERCONTENTINFORMATIONTYPE(OTHER_CONTENT_INFO_VALUE);

    final SIP sip = new EARKSIP();
    newUtils().setIPContentType(mets, sip);

    Assert.assertEquals(OTHER_CONTENT_INFO_VALUE, sip.getContentInformationType().asString());
  }

  /**
   * Keeps CONTENTINFORMATIONTYPE OTHER when OTHERCONTENTINFORMATIONTYPE is missing.
   */
  @Test
  public void contentInfoOtherWithoutOtherTypeIsAccepted() throws ParseException {
    final Mets mets = newSipMets();
    mets.setCONTENTINFORMATIONTYPE(TYPE_OTHER_UPPER);
    mets.setOTHERCONTENTINFORMATIONTYPE("");

    final SIP sip = new EARKSIP();
    newUtils().setIPContentType(mets, sip);

    Assert.assertEquals(IPContentInformationTypeEnum.OTHER, sip.getContentInformationType().getType());
  }

  /**
   * Leaves content information type default when blank.
   */
  @Test
  public void contentInfoBlankKeepsDefault() throws ParseException {
    final Mets mets = newSipMets();
    mets.setCONTENTINFORMATIONTYPE("");

    final SIP sip = new EARKSIP();
    newUtils().setIPContentType(mets, sip);

    Assert.assertEquals(MIXED, sip.getContentInformationType().asString());
  }

  /**
   * Rejects a representation METS without CONTENTINFORMATIONTYPE, as before the fix.
   */
  @Test
  public void representationRequiresContentInfoType() {
    final Mets mets = new Mets();
    mets.setTYPE(TYPE_TEXT);

    final IPRepresentation representation = new IPRepresentation(REPRESENTATION_ID);
    Assert.assertThrows(ParseException.class, () -> newUtils().setRepresentationContentType(mets, representation));
  }

  /**
   * Parses back the content types of a built SIP that uses predefined values.
   */
  @Test
  public void parseKeepsPredefinedTypes() throws Exception {
    final SIP parsed = buildAndParse(new IPContentType(IPContentTypeEnum.TEXT),
        new IPContentInformationType(IPContentInformationTypeEnum.ERMS));

    for (final IPRepresentation representation : parsed.getRepresentations()) {
      Assert.assertEquals(IPContentTypeEnum.TEXT, representation.getContentType().getType());
      Assert.assertEquals(IPContentInformationTypeEnum.ERMS, representation.getContentInformationType().getType());
    }
    Assert.assertEquals(IPContentTypeEnum.TEXT, parsed.getContentType().getType());
    Assert.assertEquals(IPContentInformationTypeEnum.ERMS, parsed.getContentInformationType().getType());
  }

  /**
   * Parses back the OTHER values of a built SIP, as in the issue report.
   */
  @Test
  public void parseKeepsOtherTypes() throws Exception {
    final SIP parsed = buildAndParse(new IPContentType(IPContentTypeEnum.OTHER).setOtherType(OTHER_TYPE_VALUE),
        new IPContentInformationType(IPContentInformationTypeEnum.OTHER).setOtherType(OTHER_CONTENT_INFO_VALUE));

    for (final IPRepresentation representation : parsed.getRepresentations()) {
      Assert.assertEquals(OTHER_TYPE_VALUE, representation.getContentType().asString());
      Assert.assertEquals(OTHER_CONTENT_INFO_VALUE, representation.getContentInformationType().asString());
    }
    Assert.assertEquals(OTHER_TYPE_VALUE, parsed.getContentType().asString());
    Assert.assertEquals(OTHER_CONTENT_INFO_VALUE, parsed.getContentInformationType().asString());
  }

  /**
   * Parses a built SIP that uses OTHER without the corresponding other values.
   */
  @Test
  public void parseAcceptsOtherWithoutOtherValues() throws Exception {
    final SIP parsed = buildAndParse(new IPContentType(IPContentTypeEnum.OTHER),
        new IPContentInformationType(IPContentInformationTypeEnum.OTHER));

    Assert.assertEquals(IPContentTypeEnum.OTHER, parsed.getContentType().getType());
    Assert.assertEquals(IPContentInformationTypeEnum.OTHER, parsed.getContentInformationType().getType());
  }

  private static Mets newSipMets() {
    final Mets mets = new Mets();
    final MetsType.MetsHdr metsHdr = new MetsType.MetsHdr();
    metsHdr.setOAISPACKAGETYPE(SIP_TYPE);
    mets.setMetsHdr(metsHdr);
    return mets;
  }

  private static EARKUtils newUtils() {
    return new EARKUtils(new METSGeneratorFactory().getGenerator(SIP_VERSION));
  }

  private static SIP buildAndParse(final IPContentType contentType,
                                   final IPContentInformationType contentInformationType)
      throws Exception {
    final SIP sip = new EARKSIP(SIP_ID, contentType, contentInformationType, SIP_VERSION);
    sip.addAgent(new IPAgent("Agent Name", "CREATOR", "", CreatorType.INDIVIDUAL, "", "",
        IPAgentNoteTypeEnum.SOFTWARE_VERSION));
    final IPRepresentation representation = new IPRepresentation(REPRESENTATION_ID);
    representation.setContentType(contentType);
    representation.setContentInformationType(contentInformationType);
    sip.addRepresentation(representation);

    final Path sipFolder = Files.createTempDirectory(tempFolder, SIP_ID);
    final Path zipSIP = sip.build(SIPBuilderUtils.getWriteStrategy(WriteStrategyEnum.ZIP, sipFolder));
    final SIP parsed = new EARKSIP().parse(zipSIP, sipFolder);

    Assert.assertTrue(parsed.getValidationReport().isValid());
    Assert.assertEquals(1, parsed.getRepresentations().size());
    return parsed;
  }
}
