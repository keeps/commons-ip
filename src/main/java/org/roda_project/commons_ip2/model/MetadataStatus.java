/**
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE file at the root of the source
 * tree and available online at
 *
 * https://github.com/keeps/commons-ip
 */
package org.roda_project.commons_ip2.model;

public enum MetadataStatus {
  CURRENT, SUPERSEDED;

  public static MetadataStatus parse(String value, MetadataStatus defaultValue) {
    MetadataStatus ret = defaultValue;
    try {
      if (value != null) {
        ret = MetadataStatus.valueOf(value);
      }
    } catch (IllegalArgumentException | NullPointerException e) {
      // do nothing & return default value
    }
    return ret;
  }

  public static MetadataStatus parse(String value) {
    return parse(value, CURRENT);
  }
}
