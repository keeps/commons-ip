package org.roda_project.commons_ip2.validator.components.fileComponent;

import org.roda_project.commons_ip2.validator.components.administritiveMetadataComponent.AmdSecValidator;
import org.roda_project.commons_ip2.validator.components.administritiveMetadataComponent.AmdSecValidator204;
import org.roda_project.commons_ip2.validator.components.administritiveMetadataComponent.AmdSecValidator210;

/**
 * @author Carlos Afonso <cafonso@keep.pt>
 */
public class StructValidatorFactory {

  public StructValidatorFactory() {
    // empty constructor
  }

  public StructValidator getGenerator(String version) {
    if (version.equals("2.0.4")) {
      return new StructValidator204();
    }
    else if (version.equals("2.1.0")) {
      return new StructValidator210();
    }
    return new StructValidator220();
  }

}
