package org.roda_project.commons_ip2.validator.components.metsRootComponent.metsHeaderValidator;

import org.roda_project.commons_ip2.validator.constants.Constants;

/**
 * @author Carlos Afonso <cafonso@keep.pt>
 */
public class MetsHeaderValidator204 extends MetsHeaderValidator{
  @Override
  protected String getCSIPVersion() {
    return Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION;
  }
}
