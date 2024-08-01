package org.roda_project.commons_ip2.validator.components.administritiveMetadataComponent;

import org.roda_project.commons_ip2.model.impl.eark.EARKMETSCreator;
import org.roda_project.commons_ip2.model.impl.eark.EARKMETSCreator204;
import org.roda_project.commons_ip2.model.impl.eark.EARKMETSCreator210;

/**
 * @author Carlos Afonso <cafonso@keep.pt>
 */
public class AmdSecValidatorFactory {

  // FActory pattern

  public AmdSecValidatorFactory() {
    // empty constructor
  }

  public AmdSecValidator getGenerator(String version) {
    if (version.equals("2.0.4")) {
      return new AmdSecValidator204();
    }
    else if (version.equals("2.1.0")) {
      return new AmdSecValidator210();
    }
    return new AmdSecValidator220();
  }
}
