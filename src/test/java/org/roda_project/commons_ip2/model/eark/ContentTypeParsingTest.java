package org.roda_project.commons_ip2.model.impl.eark;

import org.junit.Assert;
import org.junit.Test;
import org.roda_project.commons_ip.model.ParseException;
import org.roda_project.commons_ip2.mets_v1_12.beans.Mets;
import org.roda_project.commons_ip2.mets_v1_12.beans.MetsType;
import org.roda_project.commons_ip2.model.SIP;

public class ContentTypeParsingTest {

  @Test
  public void parseContentTypeUsesTypeWhenNotOther() throws ParseException {
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
  public void parseContentTypeUsesOtherTypeWhenTypeIsOther() throws ParseException {
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
  public void parseContentInformationTypeUsesTypeWhenNotOther() throws ParseException {
    Mets mets = new Mets();
    mets.setCONTENTINFORMATIONTYPE("GeoData");
    mets.setOTHERCONTENTINFORMATIONTYPE("Test of other CITS");

    EARKUtils utils = new EARKUtils(new METSGeneratorFactory().getGenerator("2.1.0"));
    SIP sip = new EARKSIP();

    utils.setIPContentInformationType(mets, sip);

    Assert.assertEquals("GeoData", sip.getContentInformationType().asString());
  }

  @Test
  public void parseContentInformationTypeUsesOtherWhenTypeIsOther() throws ParseException {
    Mets mets = new Mets();
    mets.setCONTENTINFORMATIONTYPE("OTHER");
    mets.setOTHERCONTENTINFORMATIONTYPE("Test of other CITS");

    EARKUtils utils = new EARKUtils(new METSGeneratorFactory().getGenerator("2.1.0"));
    SIP sip = new EARKSIP();

    utils.setIPContentInformationType(mets, sip);

    Assert.assertEquals("Test of other CITS", sip.getContentInformationType().asString());
  }

  @Test
  public void parseContentTypeOtherWithoutOtherTypeThrows() {
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
  public void parseContentInformationTypeOtherWithoutOtherTypeThrows() {
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
  public void parseContentInformationTypeBlankLeavesDefault() throws ParseException {
    Mets mets = new Mets();
    mets.setCONTENTINFORMATIONTYPE("");

    EARKUtils utils = new EARKUtils(new METSGeneratorFactory().getGenerator("2.1.0"));
    SIP sip = new EARKSIP();

    utils.setIPContentInformationType(mets, sip);

    Assert.assertEquals("MIXED", sip.getContentInformationType().asString());
  }
}
