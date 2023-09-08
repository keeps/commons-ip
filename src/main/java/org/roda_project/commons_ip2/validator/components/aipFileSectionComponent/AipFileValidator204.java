package org.roda_project.commons_ip2.validator.components.aipFileSectionComponent;

import org.roda_project.commons_ip2.validator.constants.Constants;

/**
 * @author Carlos Afonso <cafonso@keep.pt>
 */
public class AipFileValidator204 extends AipFileValidator{
  @Override
  protected String getAIPVersion() {
    return Constants.VALIDATION_REPORT_HEADER_AIP_VERSION;
  }
}
