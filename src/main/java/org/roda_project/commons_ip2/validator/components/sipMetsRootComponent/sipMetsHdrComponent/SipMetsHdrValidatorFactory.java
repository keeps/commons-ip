package org.roda_project.commons_ip2.validator.components.sipMetsRootComponent.sipMetsHdrComponent;

import org.roda_project.commons_ip2.validator.components.sipMetsRootComponent.sipMetsComponent.SipMetsValidator;
import org.roda_project.commons_ip2.validator.components.sipMetsRootComponent.sipMetsComponent.SipMetsValidator204;
import org.roda_project.commons_ip2.validator.components.sipMetsRootComponent.sipMetsComponent.SipMetsValidator210;

/**
 * @author Carlos Afonso <cafonso@keep.pt>
 */
public class SipMetsHdrValidatorFactory {
  public SipMetsHdrValidatorFactory() {
    // empty constructor
  }

  public SipMetsHdrValidator getGenerator(String version) {
    if (version.equals("2.0.4")) {
      return new SipMetsHdrValidator204();
    }
    return new SipMetsHdrValidator210();
  }
}
