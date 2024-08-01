package org.roda_project.commons_ip2.validator.components.sipFileSectionComponent;

import java.util.HashMap;
import java.util.Map;

import org.roda_project.commons_ip2.validator.components.MetsValidatorImpl;
import org.roda_project.commons_ip2.validator.constants220.Constants;
import org.roda_project.commons_ip2.validator.constants220.ConstantsSIPspec;
import org.roda_project.commons_ip2.validator.reporter.ReporterDetails;
import org.roda_project.commons_ip2.validator.state.MetsValidatorState;
import org.roda_project.commons_ip2.validator.state.StructureValidatorState;
import org.roda_project.commons_ip2.validator.utils.Message;
import org.roda_project.commons_ip2.validator.utils.ResultsUtils;

/**
 * @author Carlos Afonso <cafonso@keep.pt>
 */
public class SipFileSectionComponent220 extends MetsValidatorImpl {
  /**
   * Module name of the specification.
   */
  private final String moduleName;
  /**
   * Flag if is to validate or not.
   */
  private boolean isToValidate = true;

  /**
   * Empty constructor.
   */
  public SipFileSectionComponent220() {
    this.moduleName = Constants.SIP_MODULE_NAME_3;
  }

  /**
   * Set the flag isToValidate.
   *
   * @param isToValidate
   *          flag if is to validate or not
   */
  public void setIsToValidate(final boolean isToValidate) {
    this.isToValidate = isToValidate;
  }

  @Override
  public Map<String, ReporterDetails> validate(final StructureValidatorState structureValidatorState,
    final MetsValidatorState metsValidatorState) {
    final Map<String, ReporterDetails> results = new HashMap<>();

    SipFileValidatorFactory sipFileValidatorFactory = new SipFileValidatorFactory();
    SipFileValidator generator = sipFileValidatorFactory.getGenerator("2.2.0");

    if (isToValidate) {
      /* SIP32 */
      notifyObserversValidationStarted(moduleName, ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP32_ID);
      ResultsUtils.addResult(results, ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP32_ID,
        generator.validateSIP32(metsValidatorState).setSpecification(generator.getSIPVersion()));

      /* SIP33 */
      notifyObserversValidationStarted(moduleName, ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP33_ID);
      ResultsUtils.addResult(results, ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP33_ID,
        generator.validateSIP33(metsValidatorState).setSpecification(generator.getSIPVersion()));

      /* SIP34 */
      notifyObserversValidationStarted(moduleName, ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP34_ID);
      ResultsUtils.addResult(results, ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP34_ID,
        generator.validateSIP34(metsValidatorState).setSpecification(generator.getSIPVersion()));

      /* SIP35 */
      notifyObserversValidationStarted(moduleName, ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP35_ID);
      ResultsUtils.addResult(results, ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP35_ID,
        generator.validateSIP35(metsValidatorState).setSpecification(generator.getSIPVersion()));
    } else {
      final String message = Message.createErrorMessage("SKIPPED in %1$s because mets/fileSec doesn't exist",
        metsValidatorState.getMetsName(), metsValidatorState.isRootMets());

      ResultsUtils.addResults(results,
        new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_AIP_VERSION, message, true, true),
        ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP32_ID,
        ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP33_ID,
        ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP34_ID,
        ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP35_ID);
    }
    notifyObserversFinishModule(moduleName);
    return results;
  }
}
