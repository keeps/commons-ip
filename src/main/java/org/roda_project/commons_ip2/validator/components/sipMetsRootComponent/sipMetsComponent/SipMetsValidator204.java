package org.roda_project.commons_ip2.validator.components.sipMetsRootComponent.sipMetsComponent;

import org.roda_project.commons_ip2.validator.constants.Constants;

/**
 * @author Carlos Afonso <cafonso@keep.pt>
 */
public class SipMetsValidator204 extends SipMetsValidator {
  @Override
  protected String getSIPVersion() {
    return Constants.VALIDATION_REPORT_HEADER_SIP_VERSION;
  }
}
