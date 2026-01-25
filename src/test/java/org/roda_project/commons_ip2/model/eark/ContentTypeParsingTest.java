package org.roda_project.commons_ip2.model.impl.eark;

import org.junit.Assert;
import org.junit.Test;
import org.roda_project.commons_ip.model.ParseException;
import org.roda_project.commons_ip2.mets_v1_12.beans.Mets;
import org.roda_project.commons_ip2.mets_v1_12.beans.MetsType;
import org.roda_project.commons_ip2.model.SIP;

/**
 * Tests content type parsing from METS attributes.
 */
public class ContentTypeParsingTest {

  private static final String SIP_TYPE = "SIP";
  private static final String SIP_VERSION = "2.1.0";
  private static final String TYPE_TEXT = "Text";
  private static final String TYPE_OTHER = "Other";
  private static final String TYPE_OTHER_UPPER = "OTHER";
  private static final String OTHER_TYPE_VALUE = "Moving images - on tangible media";
  private static final String CONTENT_INFO_TYPE = "ERMS";
  private static final String OTHER_CONTENT_INFO_VALUE = "Test of other CITS";
  private static final String EXPECTED_PARSE_EXCEPTION = "Expected ParseException";
  private static final String MIXED = "MIXED";

  /**
   * Uses METS TYPE when TYPE is not OTHER.
   */
  @Test
  public void contentTypeUsesType() throws ParseException {
    final Mets mets = new Mets();
    final MetsType.MetsHdr metsHdr = new MetsType.MetsHdr();
    metsHdr.setOAISPACKAGETYPE(SIP_TYPE);
    mets.setMetsHdr(metsHdr);

    mets.setTYPE(TYPE_TEXT);
    mets.setOTHERTYPE(OTHER_TYPE_VALUE);

    final EARKUtils utils = new EARKUtils(new METSGeneratorFactory().getGenerator(SIP_VERSION));
    final SIP sip = new EARKSIP();

    utils.setIPContentType(mets, sip);

    Assert.assertEquals(TYPE_TEXT, sip.getContentType().asString());
  }

  /**
   * Uses METS OTHERTYPE when TYPE is OTHER.
   */
  @Test
  public void contentTypeUsesOtherType() throws ParseException {
    final Mets mets = new Mets();
    final MetsType.MetsHdr metsHdr = new MetsType.MetsHdr();
    metsHdr.setOAISPACKAGETYPE(SIP_TYPE);
    mets.setMetsHdr(metsHdr);

    mets.setTYPE(TYPE_OTHER);
    mets.setOTHERTYPE(OTHER_TYPE_VALUE);

    final EARKUtils utils = new EARKUtils(new METSGeneratorFactory().getGenerator(SIP_VERSION));
    final SIP sip = new EARKSIP();

    utils.setIPContentType(mets, sip);

    Assert.assertEquals(OTHER_TYPE_VALUE, sip.getContentType().asString());
  }

  /**
   * Uses CONTENTINFORMATIONTYPE when not OTHER.
   */
  @Test
  public void contentInfoUsesType() throws ParseException {
    final Mets mets = new Mets();
    mets.setCONTENTINFORMATIONTYPE(CONTENT_INFO_TYPE);
    mets.setOTHERCONTENTINFORMATIONTYPE(OTHER_CONTENT_INFO_VALUE);

    final EARKUtils utils = new EARKUtils(new METSGeneratorFactory().getGenerator(SIP_VERSION));
    final SIP sip = new EARKSIP();

    utils.setIPContentInformationType(mets, sip);

    Assert.assertEquals(CONTENT_INFO_TYPE, sip.getContentInformationType().asString());
  }

  /**
   * Uses OTHERCONTENTINFORMATIONTYPE when CONTENTINFORMATIONTYPE is OTHER.
   */
  @Test
  public void contentInfoUsesOtherType() throws ParseException {
    final Mets mets = new Mets();
    mets.setCONTENTINFORMATIONTYPE(TYPE_OTHER_UPPER);
    mets.setOTHERCONTENTINFORMATIONTYPE(OTHER_CONTENT_INFO_VALUE);

    final EARKUtils utils = new EARKUtils(new METSGeneratorFactory().getGenerator(SIP_VERSION));
    final SIP sip = new EARKSIP();

    utils.setIPContentInformationType(mets, sip);

    Assert.assertEquals(OTHER_CONTENT_INFO_VALUE, sip.getContentInformationType().asString());
  }

  /**
   * Requires OTHERTYPE when TYPE is OTHER.
   */
  @Test
  public void contentTypeOtherRequiresOtherType() {
    final Mets mets = new Mets();
    final MetsType.MetsHdr metsHdr = new MetsType.MetsHdr();
    metsHdr.setOAISPACKAGETYPE(SIP_TYPE);
    mets.setMetsHdr(metsHdr);

    mets.setTYPE(TYPE_OTHER);
    mets.setOTHERTYPE("");

    final EARKUtils utils = new EARKUtils(new METSGeneratorFactory().getGenerator(SIP_VERSION));
    final SIP sip = new EARKSIP();

    try {
      utils.setIPContentType(mets, sip);
      Assert.fail(EXPECTED_PARSE_EXCEPTION);
    } catch (final ParseException e) {
      Assert.assertTrue(e.getMessage().contains("OTHERTYPE"));
    }
  }

  /**
   * Requires OTHERCONTENTINFORMATIONTYPE when CONTENTINFORMATIONTYPE is OTHER.
   */
  @Test
  public void contentInfoOtherRequiresOtherType() {
    final Mets mets = new Mets();
    mets.setCONTENTINFORMATIONTYPE(TYPE_OTHER_UPPER);
    mets.setOTHERCONTENTINFORMATIONTYPE("");

    final EARKUtils utils = new EARKUtils(new METSGeneratorFactory().getGenerator(SIP_VERSION));
    final SIP sip = new EARKSIP();

    try {
      utils.setIPContentInformationType(mets, sip);
      Assert.fail(EXPECTED_PARSE_EXCEPTION);
    } catch (final ParseException e) {
      Assert.assertTrue(e.getMessage().contains("OTHERCONTENTINFORMATIONTYPE"));
    }
  }

  /**
   * Leaves content information type default when blank.
   */
  @Test
  public void contentInfoBlankKeepsDefault() throws ParseException {
    final Mets mets = new Mets();
    mets.setCONTENTINFORMATIONTYPE("");

    final EARKUtils utils = new EARKUtils(new METSGeneratorFactory().getGenerator(SIP_VERSION));
    final SIP sip = new EARKSIP();

    utils.setIPContentInformationType(mets, sip);

    Assert.assertEquals(MIXED, sip.getContentInformationType().asString());
  }
}
