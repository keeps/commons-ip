package org.roda_project.commons_ip2.validator.components.metsRootComponent.metsValidator;

import org.roda_project.commons_ip2.validator.constants.Constants;

/**
 * @author Carlos Afonso <cafonso@keep.pt>
 */
public class MetsValidator204 extends MetsValidator {

  @Override
  protected String getCSIPVersion() {
    return Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION;
  }
}
