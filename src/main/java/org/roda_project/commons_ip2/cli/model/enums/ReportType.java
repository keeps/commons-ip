package org.roda_project.commons_ip2.cli.model.enums;

/**
 * @author Miguel Guimarães <mguimaraes@keep.pt>
 */
public enum ReportType {
  COMMONS_IP("commons-ip"), PYIP("eark-validator");

  private final String type;

  ReportType(String type) {
    this.type = type;
  }

  @Override
  public String toString() {
    return type;
  }
}
