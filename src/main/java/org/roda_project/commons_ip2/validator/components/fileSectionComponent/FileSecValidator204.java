package org.roda_project.commons_ip2.validator.components.fileSectionComponent;

import org.roda_project.commons_ip2.validator.constants.Constants;

/**
 * @author Carlos Afonso <cafonso@keep.pt>
 */
public class FileSecValidator204 extends FileSecValidator {
  @Override
  protected String getCSIPVersion() {
    return Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION;
  }
}
