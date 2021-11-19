package org.roda_project.commons_ip2.validator.sipComponents.sipMetsRootComponent;

import java.util.HashMap;
import java.util.Map;
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
  private final String moduleName;

  public SipMetsComponent() {
    this.moduleName = Constants.SIP_MODULE_NAME_1;
  }

  @Override
  public Map<String, ReporterDetails> validate(
      StructureValidatorState structureValidatorState, MetsValidatorState metsValidatorState) {
    Map<String, ReporterDetails> results = new HashMap<>();

    /* SIP1 */
    notifyObserversValidationStarted(
        moduleName, ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP1_ID);
    ResultsUtils.addResult(
        results,
        ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP1_ID,
        validateSIP1(metsValidatorState)
            .setSpecification(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION));

    /* SIP2 */
    notifyObserversValidationStarted(
        moduleName, ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP2_ID);
    ResultsUtils.addResult(
        results,
        ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP2_ID,
        validateSIP2(metsValidatorState)
            .setSpecification(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION));

    notifyObserversFinishModule(moduleName);

    return results;
  }

  /*
   * mets/@LABEL An optional short text describing the contents of the package,
   * e.g. “Accounting records of 2017”.
   */
  private ReporterDetails validateSIP1(MetsValidatorState metsValidatorState) {
    String label = metsValidatorState.getMets().getLABEL();
    if (label == null) {
      return new ReporterDetails(
          Constants.VALIDATION_REPORT_HEADER_SIP_VERSION,
          Message.createErrorMessage(
              "Doesn't have an mets/@LABEL in %1$s",
              metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
          false,
          false);
    }
    return new ReporterDetails();
  }

  /*
   * mets/@PROFILE An optional short text describing the contents of the package,
   * e.g. “Accounting records of 2017”.
   */
  private ReporterDetails validateSIP2(MetsValidatorState metsValidatorState) {
    String profile = metsValidatorState.getMets().getPROFILE();
    String profileValue = "https://earkcsip.dilcis.eu/profile/E-ARK-CSIP.xml";
    if (profile != null) {
      if (!profile.equals(profileValue)) {
        StringBuilder message = new StringBuilder();
        message.append("mets/@PROFILE value isn't ").append(profileValue).append(" %1$s");
        return new ReporterDetails(
            Constants.VALIDATION_REPORT_HEADER_SIP_VERSION,
            Message.createErrorMessage(
                message.toString(),
                metsValidatorState.getMetsName(),
                metsValidatorState.isRootMets()),
            false,
            false);
      }
    } else {
      return new ReporterDetails(
          Constants.VALIDATION_REPORT_HEADER_SIP_VERSION,
          Message.createErrorMessage(
              "mets/@PROFILE can't be null, in %1$s is null",
              metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
          false,
          false);
    }
    return new ReporterDetails();
  }
}
