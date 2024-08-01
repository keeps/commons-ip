package org.roda_project.commons_ip2.validator.components.aipFileSectionComponent;

/**
 * @author Carlos Afonso <cafonso@keep.pt>
 */
public class AipFileValidatorFactory {

  public AipFileValidatorFactory() {
    // empty constructor
  }

  public AipFileValidator getGenerator(String version) {
    if (version.equals("2.0.4")) {
      return new AipFileValidator204();
    }
    else if (version.equals("2.1.0")) {
      return new AipFileValidator210();
    }
    return new AipFileValidator220();
  }
}
