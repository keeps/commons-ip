package org.eark.utils;

public class METSEnums {
  public static enum LocType {
    ARK("ARK"), URN("URN"),URL("URL"), PURL("PURL"), HANDLE("HANDLE"), DOI("DOI"), OTHER("OTHER");
    private final String stringValue;
    private LocType(final String s) { stringValue = s; }
    public String toString() { return stringValue; }
 }
  public static enum MetadataType {
    MARC("MARC"), MODS("MODS"),EAD("EAD"), DC("DC"), NISOIMG("NISOIMG"), LCAV("LC-AV"), VRA("VRA"), TEIHDR("TEIHDR"),DDI("DDI"),FGDC("FGDC"),LOM("LOM"),PREMIS("PREMIS"),PREMISOBJECT("PREMIS:OBJECT"),PREMISAGENT("PREMIS:AGENT"),PREMISRIGHTS("PREMIS:RIGHTS"),PREMISEVENT("PREMIS:EVENT"),TEXTMD("TEXTMD"),METSRIGHTS("METSRIGHTS"),ISO191152003("ISO 19115:2003"),NAP("NAP"),EACCPF("EAC-CPF"),LIDO("LIDO"),OTHER("OTHER");
    private final String stringValue;
    private MetadataType(final String s) { stringValue = s; }
    public String toString() { return stringValue; }
 }
  public static enum CreatorType {
    INDIVIDUAL("INDIVIDUAL"), ORGANIZATION("ORGANIZATION"),OTHER("OTHER");
    private final String stringValue;
    private CreatorType(final String s) { stringValue = s; }
    public String toString() { return stringValue; }
  }
  
}
