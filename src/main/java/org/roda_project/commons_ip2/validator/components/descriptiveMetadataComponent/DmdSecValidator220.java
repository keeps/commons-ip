package org.roda_project.commons_ip2.validator.components.descriptiveMetadataComponent;

/**
 * @author Carlos Afonso <cafonso@keep.pt>
 */
public class DmdSecValidator220 extends DmdSecValidator {

  public DmdSecValidator220() {
    // empty
  }

  @Override
  protected String getCSIPVersion() {
    return "CSIP-2.2.0";
  }

  @Override
  protected String getSIPVersion() {
    return "SIP-2.2.0";
  }
}
