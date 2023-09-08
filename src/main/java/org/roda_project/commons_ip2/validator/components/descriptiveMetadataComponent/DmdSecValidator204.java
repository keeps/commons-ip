package org.roda_project.commons_ip2.validator.components.descriptiveMetadataComponent;

import org.roda_project.commons_ip2.validator.constants.Constants;

/**
 * @author Carlos Afonso <cafonso@keep.pt>
 */
public class DmdSecValidator204 extends DmdSecValidator{

  public DmdSecValidator204() {
    // empty
  }


  @Override
  protected String getCSIPVersion() {
    return Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION;
  }
  @Override
  protected String getSIPVersion() {
    return Constants.VALIDATION_REPORT_HEADER_SIP_VERSION;
  }


}
