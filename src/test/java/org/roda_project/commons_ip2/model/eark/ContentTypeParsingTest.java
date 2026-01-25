package org.roda_project.commons_ip2.model.impl.eark;

import org.junit.Assert;
import org.junit.Test;
import org.roda_project.commons_ip.model.ParseException;
import org.roda_project.commons_ip2.mets_v1_12.beans.Mets;
import org.roda_project.commons_ip2.mets_v1_12.beans.MetsType;
import org.roda_project.commons_ip2.model.SIP;

public class ContentTypeParsingTest {


  @Test
  public void contentTypeUsesType() throws ParseException {
    Mets mets = new Mets();
    MetsType.MetsHdr metsHdr = new MetsType.MetsHdr();
    metsHdr.setOAISPACKAGETYPE("SIP");
    mets.setMetsHdr(metsHdr);

    mets.setTYPE("Text");
    mets.setOTHERTYPE("Moving images - on tangible media");

    EARKUtils utils = new EARKUtils(new METSGeneratorFactory().getGenerator("2.1.0"));
    SIP sip = new EARKSIP();

    utils.setIPContentType(mets, sip);

    Assert.assertEquals("Text", sip.getContentType().asString());
  }

  @Test
  public void contentTypeUsesOtherType() throws ParseException {
    Mets mets = new Mets();
    MetsType.MetsHdr metsHdr = new MetsType.MetsHdr();
    metsHdr.setOAISPACKAGETYPE("SIP");
    mets.setMetsHdr(metsHdr);

    mets.setTYPE("Other");
    mets.setOTHERTYPE("Moving images - on tangible media");

    EARKUtils utils = new EARKUtils(new METSGeneratorFactory().getGenerator("2.1.0"));
    SIP sip = new EARKSIP();

    utils.setIPContentType(mets, sip);

    Assert.assertEquals("Moving images - on tangible media", sip.getContentType().asString());
  }

  @Test
  public void contentInfoUsesType() throws ParseException {
    final String CONTENT_INFORMATION_TYPE = "ERMS";

    Mets mets = new Mets();
    mets.setCONTENTINFORMATIONTYPE(CONTENT_INFORMATION_TYPE);
    mets.setOTHERCONTENTINFORMATIONTYPE("Test of other CITS");

    EARKUtils utils = new EARKUtils(new METSGeneratorFactory().getGenerator("2.1.0"));
    SIP sip = new EARKSIP();

    utils.setIPContentInformationType(mets, sip);

    Assert.assertEquals(CONTENT_INFORMATION_TYPE, sip.getContentInformationType().asString());
  }

  @Test
  public void contentInfoUsesOtherType() throws ParseException {
    final String OTHER_CONTENT_INFORMATION_TYPE = "Test of other CITS";

    Mets mets = new Mets();
    mets.setCONTENTINFORMATIONTYPE("OTHER");
    mets.setOTHERCONTENTINFORMATIONTYPE(OTHER_CONTENT_INFORMATION_TYPE);

    EARKUtils utils = new EARKUtils(new METSGeneratorFactory().getGenerator("2.1.0"));
    SIP sip = new EARKSIP();

    utils.setIPContentInformationType(mets, sip);

    Assert.assertEquals(OTHER_CONTENT_INFORMATION_TYPE, sip.getContentInformationType().asString());
  }

  @Test
  public void contentTypeOtherRequiresOtherType() {
    Mets mets = new Mets();
    MetsType.MetsHdr metsHdr = new MetsType.MetsHdr();
    metsHdr.setOAISPACKAGETYPE("SIP");
    mets.setMetsHdr(metsHdr);

    mets.setTYPE("Other");
    mets.setOTHERTYPE("");

    EARKUtils utils = new EARKUtils(new METSGeneratorFactory().getGenerator("2.1.0"));
    SIP sip = new EARKSIP();

    try {
      utils.setIPContentType(mets, sip);
      Assert.fail("Expected ParseException");
    } catch (ParseException e) {
      Assert.assertTrue(e.getMessage().contains("OTHERTYPE"));
    }
  }

  @Test
  public void contentInfoOtherRequiresOtherType() {
    Mets mets = new Mets();
    mets.setCONTENTINFORMATIONTYPE("OTHER");
    mets.setOTHERCONTENTINFORMATIONTYPE("");

    EARKUtils utils = new EARKUtils(new METSGeneratorFactory().getGenerator("2.1.0"));
    SIP sip = new EARKSIP();

    try {
      utils.setIPContentInformationType(mets, sip);
      Assert.fail("Expected ParseException");
    } catch (ParseException e) {
      Assert.assertTrue(e.getMessage().contains("OTHERCONTENTINFORMATIONTYPE"));
    }
  }

  @Test
  public void contentInfoBlankKeepsDefault() throws ParseException {
    Mets mets = new Mets();
    mets.setCONTENTINFORMATIONTYPE("");

    EARKUtils utils = new EARKUtils(new METSGeneratorFactory().getGenerator("2.1.0"));
    SIP sip = new EARKSIP();

    utils.setIPContentInformationType(mets, sip);

    Assert.assertEquals("MIXED", sip.getContentInformationType().asString());
  }
}
