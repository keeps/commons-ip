package org.roda_project.commons_ip2.cli.model.enums;

/**
 * @author Miguel Guimarães <mguimaraes@keep.pt>
 */
public enum WriteStrategyEnum {
  ZIP("Zip"), FOLDER("Folder");

  private final String type;

  WriteStrategyEnum(String type) {
    this.type = type;
  }

  @Override
  public String toString() {
    return type;
  }
}
