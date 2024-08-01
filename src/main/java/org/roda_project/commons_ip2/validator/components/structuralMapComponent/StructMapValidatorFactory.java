package org.roda_project.commons_ip2.validator.components.structuralMapComponent;

import org.roda_project.commons_ip2.validator.components.fileSectionComponent.FileSecValidator;
import org.roda_project.commons_ip2.validator.components.fileSectionComponent.FileSecValidator204;
import org.roda_project.commons_ip2.validator.components.fileSectionComponent.FileSecValidator210;

/**
 * @author Carlos Afonso <cafonso@keep.pt>
 */
public class StructMapValidatorFactory {
  public StructMapValidatorFactory() {
    // empty constructor
  }

  public StructMapValidator getGenerator(String version) {
    if (version.equals("2.0.4")) {
      return new StructMapValidator204();
    }
    else if (version.equals("2.1.0")) {
      return new StructMapValidator210();
    }
    return new StructMapValidator220();
  }
}
