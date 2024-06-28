/**
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE file at the root of the source
 * tree and available online at
 *
 * https://github.com/keeps/commons-ip
 */
package org.roda_project.commons_ip2.model;

import java.util.HashMap;
import java.util.Map;

public enum IPAgentNoteTypeEnum {
  SOFTWARE_VERSION("SOFTWARE VERSION"), IDENTIFICATIONCODE("IDENTIFICATIONCODE"), NOT_SET("Not set");

  public static final Map<String, IPAgentNoteTypeEnum> typeToEnum = new HashMap<>();
  static {
    for (IPAgentNoteTypeEnum ipAgentNoteTypeEnum : IPAgentNoteTypeEnum.values()) {
      typeToEnum.put(ipAgentNoteTypeEnum.getType(), ipAgentNoteTypeEnum);
    }
  }

  private final String type;

  IPAgentNoteTypeEnum(String type) {
    this.type = type;
  }

  public String getType() {
    return type;
  }

  public String asString() {
    IPAgentNoteTypeEnum ipAgentNoteType = typeToEnum.get(type);
    return ipAgentNoteType != null ? ipAgentNoteType.getType() : this.toString();
  }

  public static IPAgentNoteTypeEnum parse(final String type) {
    IPAgentNoteTypeEnum ipAgentNoteType = NOT_SET;
    try {
      ipAgentNoteType = IPAgentNoteTypeEnum.valueOf(type);
    } catch (IllegalArgumentException | NullPointerException e) {
      IPAgentNoteTypeEnum ipAgentNoteTypeFromMap = typeToEnum.get(type);
      if (ipAgentNoteTypeFromMap != null) {
        ipAgentNoteType = ipAgentNoteTypeFromMap;
      }
    }
    return ipAgentNoteType;
  }

}
