/**
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE file at the root of the source
 * tree and available online at
 *
 * https://github.com/keeps/commons-ip
 */
package org.roda_project.commons_ip.model;

import java.io.Serializable;

import org.apache.commons.lang3.StringUtils;

public class RepresentationContentType implements Serializable {
  private static final long serialVersionUID = -5292855152678206771L;

  public enum RepresentationContentTypeEnum {
    MOREQ, SIARD, SIARD2, SMURF, SMURFERMS, SMURFSFSB, SIARDDK, GeoVectorGML, GeoRasterGeotiff, OTHER, MIXED
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
    } catch (IllegalArgumentException | NullPointerException e) {
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

  public String asString() {
    String ret = type.toString();

    if (type == RepresentationContentTypeEnum.OTHER && StringUtils.isNotBlank(otherType)) {
      ret = otherType;
    }

    return ret;
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

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((otherType == null) ? 0 : otherType.hashCode());
    result = prime * result + ((type == null) ? 0 : type.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (!(obj instanceof RepresentationContentType other))
      return false;
      return this.type == other.getType() && this.otherType.equals(other.getOtherType());
  }

  public static RepresentationContentType getMIXED() {
    return new RepresentationContentType(RepresentationContentTypeEnum.MIXED);
  }

  public static RepresentationContentType getOTHER() {
    return new RepresentationContentType(RepresentationContentTypeEnum.OTHER);
  }

}
