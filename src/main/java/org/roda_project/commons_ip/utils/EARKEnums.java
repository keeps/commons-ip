package org.roda_project.commons_ip.utils;

public class EARKEnums {
  public static enum Type {
    SIP("SIP"), DIP("DIP"), AIP("AIP");
    private final String stringValue;

    private Type(final String s) {
      stringValue = s;
    }

    public String toString() {
      return stringValue;
    }
  }

  public static enum ContentType {
    ERMS("ERMS"), SIARD("SIARD"), SMURF("SMURF"), other("other"), mixed("mixed");
    private final String stringValue;

    private ContentType(final String s) {
      stringValue = s;
    }

    public String toString() {
      return stringValue;
    }
  }

  public static enum ContentTypeRepresentation {
    ERMS("ERMS"), SIARD("SIARD"), SMURF("SMURF"), other("other");
    private final String stringValue;

    private ContentTypeRepresentation(final String s) {
      stringValue = s;
    }

    public String toString() {
      return stringValue;
    }
  }
}
