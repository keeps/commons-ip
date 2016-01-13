/**
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE file at the root of the source
 * tree and available online at
 *
 * https://github.com/keeps/commons-ip
 */
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
