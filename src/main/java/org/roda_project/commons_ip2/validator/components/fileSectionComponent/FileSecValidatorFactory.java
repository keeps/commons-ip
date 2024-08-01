package org.roda_project.commons_ip2.validator.components.fileSectionComponent;

/**
 * @author Carlos Afonso <cafonso@keep.pt>
 */
public class FileSecValidatorFactory {

  public FileSecValidatorFactory() {
    // empty constructor
  }

  public FileSecValidator getGenerator(String version) {
    if (version.equals("2.0.4")) {
      return new FileSecValidator204();
    }
    else if (version.equals("2.1.0")) {
      return new FileSecValidator210();
    }
    return new FileSecValidator220();
  }

}
