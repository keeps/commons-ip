package org.roda_project.commons_ip2.validator.sipComponents.sipMetsRootComponent;

import java.util.HashMap;
import java.util.Map;

import org.roda_project.commons_ip2.model.IPConstants;
import org.roda_project.commons_ip2.validator.component.MetsValidatorImpl;
import org.roda_project.commons_ip2.validator.constants.Constants;
import org.roda_project.commons_ip2.validator.constants.ConstantsSIPspec;
import org.roda_project.commons_ip2.validator.reporter.ReporterDetails;
import org.roda_project.commons_ip2.validator.state.MetsValidatorState;
import org.roda_project.commons_ip2.validator.state.StructureValidatorState;
import org.roda_project.commons_ip2.validator.utils.Message;
import org.roda_project.commons_ip2.validator.utils.ResultsUtils;

/** {@author João Gomes <jgomes@keep.pt>}. */
public class SipMetsComponent extends MetsValidatorImpl {
  /**
   * The module name of the specification.
   */
  private final String moduleName;

  /**
   * Empty constructor.
   */
  public SipMetsComponent() {
    this.moduleName = Constants.SIP_MODULE_NAME_1;
  }

  @Override
  public Map<String, ReporterDetails> validate(final StructureValidatorState structureValidatorState,
    final MetsValidatorState metsValidatorState) {
    final Map<String, ReporterDetails> results = new HashMap<>();

    /* SIP1 */
    notifyObserversValidationStarted(moduleName, ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP1_ID);
    ResultsUtils.addResult(results, ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP1_ID,
      validateSIP1(metsValidatorState).setSpecification(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION));

    /* SIP2 */
    notifyObserversValidationStarted(moduleName, ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP2_ID);
    ResultsUtils.addResult(results, ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP2_ID,
      validateSIP2(metsValidatorState).setSpecification(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION));

    notifyObserversFinishModule(moduleName);

    return results;
  }

  /*
   * mets/@LABEL An optional short text describing the contents of the package,
   * e.g. “Accounting records of 2017”.
   */
  private ReporterDetails validateSIP1(final MetsValidatorState metsValidatorState) {
    final String label = metsValidatorState.getMets().getLABEL();
    if (label == null) {
      return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION,
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
  private ReporterDetails validateSIP2(final MetsValidatorState metsValidatorState) {
    final String profile = metsValidatorState.getMets().getPROFILE();
    if (profile != null) {
      if (!profile.equals(IPConstants.COMMON_SPEC_PROFILE)) {
        final StringBuilder message = new StringBuilder();
        message.append("mets/@PROFILE value isn't ").append(IPConstants.COMMON_SPEC_PROFILE).append(" %1$s");
        return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION, Message.createErrorMessage(
          message.toString(), metsValidatorState.getMetsName(), metsValidatorState.isRootMets()), false, false);
      }
    } else {
      return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION,
        Message.createErrorMessage("mets/@PROFILE can't be null, in %1$s is null", metsValidatorState.getMetsName(),
          metsValidatorState.isRootMets()),
        false, false);
    }
    return new ReporterDetails();
  }
}
