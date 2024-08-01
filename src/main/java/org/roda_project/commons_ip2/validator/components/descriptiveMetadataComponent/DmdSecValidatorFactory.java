package org.roda_project.commons_ip2.validator.components.descriptiveMetadataComponent;

/**
 * @author Carlos Afonso <cafonso@keep.pt>
 */
public class DmdSecValidatorFactory {

  public DmdSecValidatorFactory() {
    // empty constructor
  }

  public DmdSecValidator getGenerator(String version) {
    if (version.equals("2.0.4")) {
      return new DmdSecValidator204();
    }
    else if (version.equals("2.2.0")) {
      return new DmdSecValidator220();
    }
    return new DmdSecValidator220();
  }
}
