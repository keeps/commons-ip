package org.roda_project.commons_ip2.validator.components.sipMetsRootComponent.sipMetsComponent;

import org.roda_project.commons_ip2.model.IPConstants;
import org.roda_project.commons_ip2.validator.constants.Constants;
import org.roda_project.commons_ip2.validator.reporter.ReporterDetails;
import org.roda_project.commons_ip2.validator.state.MetsValidatorState;
import org.roda_project.commons_ip2.validator.utils.Message;

/**
 * @author Carlos Afonso <cafonso@keep.pt>
 */
public abstract class SipMetsValidator {

  protected abstract String getSIPVersion();
  /*
   * mets/@LABEL An optional short text describing the contents of the package,
   * e.g. “Accounting records of 2017”.
   */
  protected ReporterDetails validateSIP1(final MetsValidatorState metsValidatorState) {
    final String label = metsValidatorState.getMets().getLABEL();
    if (label == null) {
      return new ReporterDetails(getSIPVersion(),
        Message.createErrorMessage("Doesn't have an mets/@LABEL in %1$s", metsValidatorState.getMetsName(),
          metsValidatorState.isRootMets()),
        false, false);
    }
    return new ReporterDetails();
  }

  /*
   * mets/@PROFILE An optional short text describing the contents of the package,
   * e.g. “Accounting records of 2017”.
   */
  protected ReporterDetails validateSIP2(final MetsValidatorState metsValidatorState) {
    final String profile = metsValidatorState.getMets().getPROFILE();
    if (profile != null) {
      if (!profile.equals(IPConstants.SIP_SPEC_PROFILE)) {
          String message = "mets/@PROFILE value isn't " + IPConstants.SIP_SPEC_PROFILE + " %1$s";
        return new ReporterDetails(getSIPVersion(), Message.createErrorMessage(
                message, metsValidatorState.getMetsName(), metsValidatorState.isRootMets()), false, false);
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
