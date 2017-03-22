package org.roda_project.commons_ip.model;

public class IPAltRecordID {
  private String value;
  private String type;

  public IPAltRecordID() {
    this.value = "";
    this.type = "";
  }

  public String getValue() {
    return value;
  }

  public IPAltRecordID setValue(String value) {
    this.value = value;
    return this;
  }

  public String getType() {
    return type;
  }

  public IPAltRecordID setType(String type) {
    this.type = type;
    return this;
  }

  @Override
  public String toString() {
    return "IPAltRecordID [value=" + value + ", type=" + type + "]";
  }
}
