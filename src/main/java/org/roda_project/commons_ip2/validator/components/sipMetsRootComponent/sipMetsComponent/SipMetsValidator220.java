package org.roda_project.commons_ip2.validator.components.sipMetsRootComponent.sipMetsComponent;

import org.roda_project.commons_ip2.model.IPConstants;
import org.roda_project.commons_ip2.validator.reporter.ReporterDetails;
import org.roda_project.commons_ip2.validator.state.MetsValidatorState;
import org.roda_project.commons_ip2.validator.utils.Message;

/**
 * @author Carlos Afonso <cafonso@keep.pt>
 */
public class SipMetsValidator220 extends SipMetsValidator {
  @Override
  protected String getSIPVersion() {
    return "SIP-2.2.0";
  }

  /*
   * mets/@PROFILE An optional short text describing the contents of the package,
   * e.g. “Accounting records of 2017”.
   */
  @Override
  protected ReporterDetails validateSIP2(final MetsValidatorState metsValidatorState) {
    final String profile = metsValidatorState.getMets().getPROFILE();
    if (profile != null) {
      if (!profile.equals("https://earksip.dilcis.eu/profile/E-ARK-SIP-v2-2-0.xml")) {
        final StringBuilder message = new StringBuilder();
        message.append("mets/@PROFILE value isn't ").append(IPConstants.SIP_SPEC_PROFILE).append(" %1$s");
        return new ReporterDetails(getSIPVersion(), Message.createErrorMessage(message.toString(),
          metsValidatorState.getMetsName(), metsValidatorState.isRootMets()), false, false);
      }
    } else {
      return new ReporterDetails(getSIPVersion(),
        Message.createErrorMessage("mets/@PROFILE can't be null, in %1$s is null", metsValidatorState.getMetsName(),
          metsValidatorState.isRootMets()),
        false, false);
    }
    return new ReporterDetails();
  }

}
