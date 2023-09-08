package org.roda_project.commons_ip2.validator.components.metsRootComponent.metsValidator;

/**
 * @author Carlos Afonso <cafonso@keep.pt>
 */
public class MetsValidatorFactory {
  public MetsValidatorFactory() {
    // empty constructor
  }

  public MetsValidator getGenerator(String version) {
    if (version.equals("2.0.4")) {
      return new MetsValidator204();
    }
    return new MetsValidator210();
  }
}
