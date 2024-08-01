package org.roda_project.commons_ip2.validator.components.sipMetsRootComponent.sipMetsHdrComponent;

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
    else if (version.equals("2.1.0")) {
      return new SipMetsHdrValidator210();
    }
    return new SipMetsHdrValidator220();
  }
}
