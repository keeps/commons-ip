/**
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE file at the root of the source
 * tree and available online at
 *
 * https://github.com/keeps/commons-ip
 */
package org.roda_project.commons_ip2.model;

import java.io.Serializable;

import org.apache.commons.lang3.StringUtils;

public class IPContentInformationType implements Serializable {
  private static final long serialVersionUID = 1191075605637022551L;

  public enum IPContentInformationTypeEnum {
    ERMS, SIARD1, SIARD2, SIARDDK, GEODATA, MIXED, OTHER
  }

  private IPContentInformationTypeEnum type;
  private String otherType;

  /**
   * Constructs a new object, trying to use 'type' parameter as the 'type' value
   * and if it does not match any of the enum values, 'othertype' will be set to
   * 'type' parameter
   */
  public IPContentInformationType(final String type) {
    try {
      this.type = IPContentInformationTypeEnum.valueOf(type);
      this.otherType = "";
    } catch (IllegalArgumentException | NullPointerException e) {
      this.type = IPContentInformationTypeEnum.OTHER;
      this.otherType = type;
    }
  }

  public IPContentInformationType(final IPContentInformationTypeEnum type) {
    this.type = type;
    this.otherType = "";
  }

  public IPContentInformationTypeEnum getType() {
    return type;
  }

  public String getOtherType() {
    return otherType;
  }

  public IPContentInformationType setOtherType(final String otherType) {
    this.otherType = otherType;
    return this;
  }

  @Override
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

    if (type == IPContentInformationTypeEnum.OTHER && StringUtils.isNotBlank(otherType)) {
      ret = otherType;
    }

    return ret;
  }

  public boolean isOtherAndOtherTypeIsDefined() {
    return type == IPContentInformationTypeEnum.OTHER && otherType != null && !"".equals(otherType);
  }

  public static IPContentInformationType getMIXED() {
    return new IPContentInformationType(IPContentInformationTypeEnum.MIXED);
  }
}
