package org.roda_project.commons_ip2.validator.components.sipMetsRootComponent.sipMetsComponent;

import org.roda_project.commons_ip2.validator.components.sipFileSectionComponent.SipFileValidator;
import org.roda_project.commons_ip2.validator.components.sipFileSectionComponent.SipFileValidator204;
import org.roda_project.commons_ip2.validator.components.sipFileSectionComponent.SipFileValidator210;

/**
 * @author Carlos Afonso <cafonso@keep.pt>
 */
public class SipMetsValidatorFactory {
  public SipMetsValidatorFactory() {
    // empty constructor
  }

  public SipMetsValidator getGenerator(String version) {
    if (version.equals("2.0.4")) {
      return new SipMetsValidator204();
    }
    else if (version.equals("2.1.0")) {
      return new SipMetsValidator210();
    }
    return new SipMetsValidator220();
  }
}
