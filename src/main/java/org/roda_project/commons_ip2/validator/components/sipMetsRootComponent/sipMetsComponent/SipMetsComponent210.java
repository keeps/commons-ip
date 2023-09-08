package org.roda_project.commons_ip2.validator.components.sipMetsRootComponent.sipMetsComponent;

import java.util.HashMap;
import java.util.Map;

import org.roda_project.commons_ip2.model.IPConstants;
import org.roda_project.commons_ip2.validator.components.MetsValidatorImpl;
import org.roda_project.commons_ip2.validator.components.sipFileSectionComponent.SipFileValidator;
import org.roda_project.commons_ip2.validator.components.sipFileSectionComponent.SipFileValidatorFactory;
import org.roda_project.commons_ip2.validator.constants.Constants;
import org.roda_project.commons_ip2.validator.constants.ConstantsSIPspec;
import org.roda_project.commons_ip2.validator.reporter.ReporterDetails;
import org.roda_project.commons_ip2.validator.state.MetsValidatorState;
import org.roda_project.commons_ip2.validator.state.StructureValidatorState;
import org.roda_project.commons_ip2.validator.utils.Message;
import org.roda_project.commons_ip2.validator.utils.ResultsUtils;

/** {@author João Gomes <jgomes@keep.pt>}. */
public class SipMetsComponent210 extends MetsValidatorImpl {
  /**
   * The module name of the specification.
   */
  private final String moduleName;

  /**
   * Empty constructor.
   */
  public SipMetsComponent210() {
    this.moduleName = Constants.SIP_MODULE_NAME_1;
  }

  @Override
  public Map<String, ReporterDetails> validate(final StructureValidatorState structureValidatorState,
                                               final MetsValidatorState metsValidatorState) {
    final Map<String, ReporterDetails> results = new HashMap<>();

    SipMetsValidatorFactory sipMetsValidatorFactory = new SipMetsValidatorFactory();
    SipMetsValidator generator = sipMetsValidatorFactory.getGenerator("2.1.0");

    /* SIP1 */
    notifyObserversValidationStarted(moduleName, ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP1_ID);
    ResultsUtils.addResult(results, ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP1_ID,
      generator.validateSIP1(metsValidatorState).setSpecification(generator.getSIPVersion()));

    /* SIP2 */
    notifyObserversValidationStarted(moduleName, ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP2_ID);
    ResultsUtils.addResult(results, ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP2_ID,
      generator.validateSIP2(metsValidatorState).setSpecification(generator.getSIPVersion()));

    notifyObserversFinishModule(moduleName);

    return results;
  }
}
