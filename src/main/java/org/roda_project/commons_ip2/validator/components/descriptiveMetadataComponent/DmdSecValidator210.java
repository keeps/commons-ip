package org.roda_project.commons_ip2.validator.components.descriptiveMetadataComponent;

import org.roda_project.commons_ip2.validator.constants.Constants;

/**
 * @author Carlos Afonso <cafonso@keep.pt>
 */
public class DmdSecValidator210 extends DmdSecValidator{

  public DmdSecValidator210() {
    // empty
  }

  @Override
  protected String getCSIPVersion() {
    return "CSIP-2.1.0";
  }

  @Override
  protected String getSIPVersion() {
    return "SIP-2.1.0";
  }
}
