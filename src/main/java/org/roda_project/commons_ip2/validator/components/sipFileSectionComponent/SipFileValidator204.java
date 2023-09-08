package org.roda_project.commons_ip2.validator.components.sipFileSectionComponent;

import org.roda_project.commons_ip2.validator.constants.Constants;

/**
 * @author Carlos Afonso <cafonso@keep.pt>
 */
public class SipFileValidator204 extends SipFileValidator{
  @Override
  protected String getSIPVersion() {
    return Constants.VALIDATION_REPORT_HEADER_SIP_VERSION;
  }
}
