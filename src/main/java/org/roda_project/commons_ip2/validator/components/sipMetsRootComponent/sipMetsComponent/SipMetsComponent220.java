package org.roda_project.commons_ip2.validator.components.sipMetsRootComponent.sipMetsComponent;

import org.roda_project.commons_ip2.validator.components.MetsValidatorImpl;
import org.roda_project.commons_ip2.validator.constants220.Constants;
import org.roda_project.commons_ip2.validator.constants220.ConstantsSIPspec;
import org.roda_project.commons_ip2.validator.reporter.ReporterDetails;
import org.roda_project.commons_ip2.validator.state.MetsValidatorState;
import org.roda_project.commons_ip2.validator.state.StructureValidatorState;
import org.roda_project.commons_ip2.validator.utils.ResultsUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Carlos Afonso <cafonso@keep.pt>
 */
public class SipMetsComponent220 extends MetsValidatorImpl {
  /**
   * The module name of the specification.
   */
  private final String moduleName;

  /**
   * Empty constructor.
   */
  public SipMetsComponent220() {
    this.moduleName = Constants.SIP_MODULE_NAME_1;
  }

  @Override
  public Map<String, ReporterDetails> validate(final StructureValidatorState structureValidatorState,
                                               final MetsValidatorState metsValidatorState) {
    final Map<String, ReporterDetails> results = new HashMap<>();

    SipMetsValidatorFactory sipMetsValidatorFactory = new SipMetsValidatorFactory();
    SipMetsValidator generator = sipMetsValidatorFactory.getGenerator("2.2.0");

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
