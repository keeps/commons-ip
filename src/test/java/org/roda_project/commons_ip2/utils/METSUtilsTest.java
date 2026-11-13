package org.roda_project.commons_ip2.utils;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.roda_project.commons_ip2.model.IPConstants;

public class METSUtilsTest {

  private boolean previousEncodeFlag;

  @Before
  public void enableHrefEncoding() {
    previousEncodeFlag = IPConstants.METS_ENCODE_AND_DECODE_HREF;
    IPConstants.METS_ENCODE_AND_DECODE_HREF = true;
  }

  @After
  public void restoreHrefEncoding() {
    IPConstants.METS_ENCODE_AND_DECODE_HREF = previousEncodeFlag;
  }

  @Test
  public void encodesCharacterOutsideBmpAsSingleUtf8Sequence() {
    Assert.assertEquals("data/bok-%F0%9F%93%9A.txt", METSUtils.encodeHref("data/bok-📚.txt"));
  }

  @Test
  public void roundTripsCharactersOutsideBmp() {
    String[] names = {
      "data/bok-📚.txt", // emoji 📚
      "data/👩‍💻.txt", // ZWJ sequence 👩‍💻
      "data/🇳🇴.txt", // flag 🇳🇴
      "data/𝔘𝔫𝔦.txt" // mathematical letters 𝔘𝔫𝔦
    };
    for (String name : names) {
      String encoded = METSUtils.encodeHref(name);
      Assert.assertFalse("encoded href must not contain '?' for " + name, encoded.contains("%3F"));
      Assert.assertEquals(name, METSUtils.decodeHref(encoded));
    }
  }

  @Test
  public void keepsOutputUnchangedForBmpNames() {
    Assert.assertEquals("bl%C3%A5b%C3%A6r", METSUtils.encodeHref("blåbær"));
    Assert.assertEquals("a%CC%8A", METSUtils.encodeHref("å")); // NFD å
    Assert.assertEquals("100%25-%2541-%2B+x", METSUtils.encodeHref("100%-%41-+ x"));
    Assert.assertEquals("a+%2Bb%2B+c", METSUtils.encodeHref("a +b+ c"));
  }

  @Test
  public void roundTripsBmpNames() {
    String[] names = {"blåbær", "å", "100%-%41-+ x", "a +b+ c"};
    for (String name : names) {
      Assert.assertEquals(name, METSUtils.decodeHref(METSUtils.encodeHref(name)));
    }
  }
}
