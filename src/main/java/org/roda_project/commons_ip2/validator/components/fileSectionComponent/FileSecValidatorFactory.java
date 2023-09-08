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
    return new FileSecValidator210();
  }

}
