package org.roda_project.commons_ip2.validator.components.sipFileSectionComponent;

import org.roda_project.commons_ip2.validator.components.metsRootComponent.metsValidator.MetsValidator;
import org.roda_project.commons_ip2.validator.components.metsRootComponent.metsValidator.MetsValidator204;
import org.roda_project.commons_ip2.validator.components.metsRootComponent.metsValidator.MetsValidator210;

/**
 * @author Carlos Afonso <cafonso@keep.pt>
 */
public class SipFileValidatorFactory {
  public SipFileValidatorFactory() {
    // empty constructor
  }

  public SipFileValidator getGenerator(String version) {
    if (version.equals("2.0.4")) {
      return new SipFileValidator204();
    }
    else if (version.equals("2.1.0")) {
      return new SipFileValidator210();
    }
    return new SipFileValidator220();
  }
}
