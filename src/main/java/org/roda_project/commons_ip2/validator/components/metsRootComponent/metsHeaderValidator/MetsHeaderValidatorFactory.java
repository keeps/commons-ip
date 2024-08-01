package org.roda_project.commons_ip2.validator.components.metsRootComponent.metsHeaderValidator;

import org.roda_project.commons_ip2.validator.components.fileSectionComponent.FileSecValidator;
import org.roda_project.commons_ip2.validator.components.fileSectionComponent.FileSecValidator204;
import org.roda_project.commons_ip2.validator.components.fileSectionComponent.FileSecValidator210;

/**
 * @author Carlos Afonso <cafonso@keep.pt>
 */
public class MetsHeaderValidatorFactory {
  public MetsHeaderValidatorFactory() {
    // empty constructor
  }

  public MetsHeaderValidator getGenerator(String version) {
    if (version.equals("2.0.4")) {
      return new MetsHeaderValidator204();
    }
    else if (version.equals("2.1.0")) {
      return new MetsHeaderValidator210();
    }
    return new MetsHeaderValidator220();
  }
}
