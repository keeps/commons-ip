/**
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE file at the root of the source
 * tree and available online at
 *
 * https://github.com/keeps/commons-ip
 */
package org.roda_project.commons_ip.model;

import org.apache.commons.lang3.StringUtils;

public class RepresentationContentType {
  public enum RepresentationContentTypeEnum {
    MOREQ, SIARD, SIARD2, SMURF, OTHER, MIXED;
  }

  private RepresentationContentTypeEnum type;
  private String otherType;

  /**
   * Constructs a new object, trying to use 'type' parameter as the 'type' value
   * and if it does not match any of the enum values, 'othertype' will be set to
   * 'type' parameter
   */
  public RepresentationContentType(final String type) {
    try {
      this.type = RepresentationContentTypeEnum.valueOf(type);
      this.otherType = "";
    } catch (IllegalArgumentException e) {
      this.type = RepresentationContentTypeEnum.OTHER;
      this.otherType = type;
    }
  }

  public RepresentationContentType(final RepresentationContentTypeEnum type) {
    this.type = type;
    this.otherType = "";
  }

  public RepresentationContentTypeEnum getType() {
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

  public String asString() {
    String ret = type.toString();

    if (type == RepresentationContentTypeEnum.OTHER && StringUtils.isNotBlank(otherType)) {
      ret = otherType;
    }

    return ret;
  }

  public static RepresentationContentType getMIXED() {
    return new RepresentationContentType(RepresentationContentTypeEnum.MIXED);
  }

  public static RepresentationContentType getOTHER() {
    return new RepresentationContentType(RepresentationContentTypeEnum.OTHER);
  }
}
