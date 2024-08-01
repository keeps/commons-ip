package org.roda_project.commons_ip2.cli.model.enums;

/**
 * @author Miguel Guimarães <mguimaraes@keep.pt>
 */
public enum CSIPVersion {
  V204("2.0.4"), V210("2.1.0"), V220("2.2.0");

  private final String version;

  CSIPVersion(String version) {
    this.version = version;
  }

  @Override
  public String toString() {
    return version;
  }
}
