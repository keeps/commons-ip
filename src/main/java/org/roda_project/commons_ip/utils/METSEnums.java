/**
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE file at the root of the source
 * tree and available online at
 *
 * https://github.com/keeps/commons-ip
 */
package org.roda_project.commons_ip.utils;

public class METSEnums {

  public enum LocType {
    ARK("ARK"), URN("URN"), URL("URL"), PURL("PURL"), HANDLE("HANDLE"), DOI("DOI"), OTHER("OTHER");
    private final String stringValue;

    private LocType(final String s) {
      stringValue = s;
    }

    public String toString() {
      return stringValue;
    }
  }

  public enum MetadataType {
    MARC("MARC"), MODS("MODS"), EAD("EAD"), DC("DC"), NISOIMG("NISOIMG"), LCAV("LC-AV"), VRA("VRA"), TEIHDR("TEIHDR"),
    DDI("DDI"), FGDC("FGDC"), LOM("LOM"), PREMIS("PREMIS"), PREMISOBJECT("PREMIS:OBJECT"), PREMISAGENT("PREMIS:AGENT"),
    PREMISRIGHTS("PREMIS:RIGHTS"), PREMISEVENT("PREMIS:EVENT"), TEXTMD("TEXTMD"), METSRIGHTS("METSRIGHTS"),
    ISO191152003("ISO 19115:2003"), NAP("NAP"), EACCPF("EAC-CPF"), LIDO("LIDO"), OTHER("OTHER");
    private final String stringValue;

    private MetadataType(final String s) {
      stringValue = s;
    }

    public String toString() {
      return stringValue;
    }
  }

  public enum CreatorType {
    INDIVIDUAL("INDIVIDUAL"), ORGANIZATION("ORGANIZATION"), OTHER("OTHER");
    private final String stringValue;

    private CreatorType(final String s) {
      stringValue = s;
    }

    public String toString() {
      return stringValue;
    }
  }

}
