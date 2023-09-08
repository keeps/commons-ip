package org.roda_project.commons_ip2.validator.components.administritiveMetadataComponent;

import org.roda_project.commons_ip2.validator.constants.Constants;

/**
 * @author Carlos Afonso <cafonso@keep.pt>
 */
public class AmdSecValidator204  extends AmdSecValidator {



  public AmdSecValidator204() {
    // empty
  }

  @Override
  protected String getVersion() {
    return Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION;
  }
}
