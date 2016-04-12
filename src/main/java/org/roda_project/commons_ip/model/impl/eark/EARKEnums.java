/**
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE file at the root of the source
 * tree and available online at
 *
 * https://github.com/keeps/commons-ip
 */
package org.roda_project.commons_ip.model.impl.eark;

import org.apache.commons.lang3.StringUtils;

public final class EARKEnums {

  /** Private empty constructor */
  private EARKEnums() {

  }

  public enum Type {
    SIP("SIP"), AIP("AIP"), DIP("DIP");
    private final String type;

    private Type(final String type) {
      this.type = type;
    }

    @Override
    public String toString() {
      return type;
    }
  }

  public enum IPContentType {
    ERMS("ERMS"), RDBMS("RDBMS"), SMURF("SMURF"), mixed("mixed"), OTHER("OTHER");
    private final String type;
    private String otherType;

    private IPContentType(final String type) {
      this.type = type;
      this.otherType = "";
    }

    public String getType() {
      return type;
    }

    public String getOtherType() {
      return otherType;
    }

    public IPContentType setOtherType(final String otherType) {
      this.otherType = otherType;
      return this;
    }

    public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("type: ").append(type);
      if (StringUtils.isNotBlank(otherType)) {
        sb.append("; othertype: ").append(otherType);
      }

      return sb.toString();
    }
  }

  public enum RepresentationContentType {
    MOREQ("MOREQ"), SIARD("SIARD"), SIARD2("SIARD2"), SMURF("SMURF"), OTHER("OTHER"), mixed("mixed");
    private final String type;
    private String otherType;

    private RepresentationContentType(final String type) {
      this.type = type;
      this.otherType = "";
    }

    public String getType() {
      return type;
    }

    public String getOtherType() {
      return otherType;
    }

    public RepresentationContentType setOtherType(final String otherType) {
      this.otherType = otherType;
      return this;
    }

    public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("type: ").append(type);
      if (StringUtils.isNotBlank(otherType)) {
        sb.append("; othertype: ").append(otherType);
      }

      return sb.toString();
    }
  }
}
