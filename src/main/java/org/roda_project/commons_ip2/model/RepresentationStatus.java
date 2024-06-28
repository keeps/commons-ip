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

public class RepresentationStatus implements Serializable {
  private static final long serialVersionUID = 886952889995803542L;

  public enum RepresentationStatusEnum {
    ORIGINAL, OTHER
  }

  private RepresentationStatusEnum status;
  private String otherStatus;

  /**
   * Constructs a new object, trying to use 'status' parameter as the 'status'
   * value and if it does not match any of the enum values, 'otherStatus' will
   * be set to 'status' parameter
   */
  public RepresentationStatus(final String status) {
    try {
      this.status = RepresentationStatusEnum.valueOf(status);
      this.otherStatus = "";
    } catch (IllegalArgumentException | NullPointerException e) {
      this.status = RepresentationStatusEnum.OTHER;
      this.otherStatus = status;
    }
  }

  public RepresentationStatus(final RepresentationStatusEnum status) {
    this.status = status;
    this.otherStatus = "";
  }

  private RepresentationStatusEnum getStatus() {
    return status;
  }

  private String getOtherStatus() {
    return otherStatus;
  }

  public String asString() {
    String ret = status.toString();

    if (status == RepresentationStatusEnum.OTHER && StringUtils.isNotBlank(otherStatus)) {
      ret = otherStatus;
    }

    return ret;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("status: ").append(status);
    if (StringUtils.isNotBlank(otherStatus)) {
      sb.append("; otherStatus: ").append(otherStatus);
    }

    return sb.toString();
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((otherStatus == null) ? 0 : otherStatus.hashCode());
    result = prime * result + ((status == null) ? 0 : status.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (!(obj instanceof RepresentationStatus other)) {
      return false;
    }
      return this.status == other.getStatus() && this.otherStatus.equals(other.getOtherStatus());
  }

  public static RepresentationStatus getORIGINAL() {
    return new RepresentationStatus(RepresentationStatusEnum.ORIGINAL);
  }

  public static RepresentationStatus getOTHER() {
    return new RepresentationStatus(RepresentationStatusEnum.OTHER);
  }

}
