/**
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE file at the root of the source
 * tree and available online at
 *
 * https://github.com/keeps/commons-ip
 */
package org.roda_project.commons_ip.utils;

public class METSEnums {

  public static final String ID_PREFIX = "uuid-";
  public static final String FILE_ID_PREFIX = "ID-";

  public enum LocType {
    ARK("ARK"), URN("URN"), URL("URL"), PURL("PURL"), HANDLE("HANDLE"), DOI("DOI"), OTHER("OTHER");
    private final String stringValue;

    LocType(final String s) {
      stringValue = s;
    }

    @Override
    public String toString() {
      return stringValue;
    }
  }

  public enum CreatorType {
    INDIVIDUAL("INDIVIDUAL"), ORGANIZATION("ORGANIZATION"), OTHER("OTHER");
    private final String stringValue;

    CreatorType(final String s) {
      stringValue = s;
    }

    @Override
    public String toString() {
      return stringValue;
    }
  }

}
