package org.roda_project.commons_ip2.validator.components.sipMetsRootComponent.sipMetsHdrComponent;

import org.roda_project.commons_ip2.validator.constants.Constants;

/**
 * @author Carlos Afonso <cafonso@keep.pt>
 */
public class SipMetsHdrValidator204 extends SipMetsHdrValidator {
  @Override
  protected String getSIPVersion() {
    return Constants.VALIDATION_REPORT_HEADER_SIP_VERSION;
  }
}
