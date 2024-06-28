/**
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE file at the root of the source
 * tree and available online at
 *
 * https://github.com/keeps/commons-ip
 */
package org.roda_project.commons_ip2.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

public class IPContentType implements Serializable {
  private static final long serialVersionUID = 1191075605637022551L;

  public enum IPContentTypeEnum {
    TEXTUAL_WORKS_PRINT("Textual works – Print"), TEXTUAL_WORKS_DIGITAL("Textual works – Digital"),
    TEXTUAL_WORKS_ELECTRONIC_SERIALS("Textual works – Electronic Serials"),
    DIGITAL_MUSICAL_COMPOSITION("Digital Musical Composition (score-based representations)"),
    PHOTOGRAPHS_PRINT("Photographs – Print"), PHOTOGRAPHS_DIGITAL("Photographs – Digital"),
    OTHER_GRAPHIC_IMAGES_PRINT("Other Graphic Images – Print"),
    OTHER_GRAPHIC_IMAGES_DIGITAL("Other Graphic Images – Digital"), MICROFORMS("Microforms"),
    AUDIO_ON_TANGIBLE_MEDIUM("Audio – On Tangible Medium (digital or analog)"),
    AUDIO_MEDIA_INDEPENDENT("Audio – Media-independent (digital)"),
    MOTION_PICTURES("Motion Pictures – Digital and Physical Media"), VIDEO("Video – File-based and Physical Media"),
    SOFTWARE("Software"), DATASETS("Datasets"), GEOSPATIAL_DATA("Geospatial Data"), DATABASES("Databases"),
    WEBSITES("Websites"), COLLECTION("Collection"), EVENT("Event"), INTERACTIVE_RESOURCE("Interactive resource"),
    PHYSICAL_OBJECT("Physical object"), SERVICE("Service"), MIXED("Mixed"), OTHER("Other");

    private static final Map<String, IPContentTypeEnum> typeToEnum = new HashMap<>();
    static {
      for (IPContentTypeEnum ipContentTypeEnum : IPContentTypeEnum.values()) {
        typeToEnum.put(ipContentTypeEnum.getType(), ipContentTypeEnum);
      }
    }

    private final String type;

    IPContentTypeEnum(String type) {
      this.type = type;
    }

    public String getType() {
      return type;
    }

    public String asString() {
      IPContentTypeEnum ipContentTypeEnum = typeToEnum.get(type);
      return ipContentTypeEnum != null ? ipContentTypeEnum.getType() : this.toString();
    }

    public static IPContentTypeEnum parse(final String type) {
      IPContentTypeEnum ipContentTypeEnum = OTHER;
      try {
        ipContentTypeEnum = IPContentTypeEnum.valueOf(type);
      } catch (IllegalArgumentException | NullPointerException e) {
        IPContentTypeEnum ipContentTypeEnumFromMap = typeToEnum.get(type);
        if (ipContentTypeEnumFromMap != null) {
          ipContentTypeEnum = ipContentTypeEnumFromMap;
        }
      }
      return ipContentTypeEnum;
    }

  }

  private IPContentTypeEnum type;
  private String otherType;

  /**
   * Constructs a new object, trying to use 'type' parameter as the 'type' value
   * and if it does not match any of the enum values, 'othertype' will be set to
   * 'type' parameter
   */
  public IPContentType(final String type) {
    try {
      this.type = IPContentTypeEnum.valueOf(type);
      this.otherType = "";
    } catch (IllegalArgumentException | NullPointerException e) {
      this.type = IPContentTypeEnum.OTHER;
      this.otherType = type;
    }
  }

  public IPContentType(final IPContentTypeEnum type) {
    this.type = type;
    this.otherType = "";
  }

  public IPContentTypeEnum getType() {
    return type;
  }

  public String getOtherType() {
    return otherType;
  }

  public IPContentType setOtherType(final String otherType) {
    this.otherType = otherType;
    return this;
  }

  public boolean isOtherAndOtherTypeIsDefined() {
    return type == IPContentTypeEnum.OTHER && otherType != null && !"".equals(otherType);
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

    if (type == IPContentTypeEnum.OTHER && StringUtils.isNotBlank(otherType)) {
      ret = otherType;
    }

    return ret;
  }

  public static IPContentType getMIXED() {
    return new IPContentType(IPContentTypeEnum.MIXED);
  }
}
