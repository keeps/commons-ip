package org.roda_project.commons_ip2.validator.components.descriptiveMetadataComponent;

import org.roda_project.commons_ip2.validator.components.administritiveMetadataComponent.AmdSecValidator;
import org.roda_project.commons_ip2.validator.components.administritiveMetadataComponent.AmdSecValidator204;
import org.roda_project.commons_ip2.validator.components.administritiveMetadataComponent.AmdSecValidator210;

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
    return new DmdSecValidator210();
  }
}
