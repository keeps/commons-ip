/**
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE file at the root of the source
 * tree and available online at
 *
 * https://github.com/keeps/commons-ip
 */
package org.roda_project.commons_ip.utils;

public class IPEnums {
  public enum IPType {
    SIP, AIP, DIP
  }

  public enum IPStatus {
    NEW, UPDATE, REPLACEMENT, TEST;

    public static IPStatus parse(String value, IPStatus defaultValue) {
      IPStatus ret = defaultValue;
      try {
        if (value != null) {
          ret = IPStatus.valueOf(value);
        }
      } catch (IllegalArgumentException | NullPointerException e) {
        // do nothing & return default value
      }
      return ret;
    }

    public static IPStatus parse(String value) {
      return parse(value, NEW);
    }
  }

  public enum SipType {
    EARK2S, EARK2
  }

}
