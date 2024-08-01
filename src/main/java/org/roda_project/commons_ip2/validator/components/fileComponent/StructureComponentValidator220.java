package org.roda_project.commons_ip2.validator.components.fileComponent;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.roda_project.commons_ip2.validator.components.StructureValidatorImpl;
import org.roda_project.commons_ip2.validator.constants220.Constants;
import org.roda_project.commons_ip2.validator.constants220.ConstantsCSIPspec;
import org.roda_project.commons_ip2.validator.reporter.ReporterDetails;
import org.roda_project.commons_ip2.validator.state.StructureValidatorState;
import org.roda_project.commons_ip2.validator.utils.ResultsUtils;

/**
 * @author Carlos Afonso <cafonso@keep.pt>
 */
public class StructureComponentValidator220 extends StructureValidatorImpl {
  private static final byte[] ZIP_MAGIC_NUMBER = {'P', 'K', 0x3, 0x4};
  private final String moduleName;

  public StructureComponentValidator220() {
    this.moduleName = Constants.CSIP_MODULE_NAME_0;
  }

  /**
   * Validates all Structural Checks CSIPSTR1 to CSIPSTR16.
   *
   * @param structureValidatorState
   *          the contextual {@link StructureValidatorState}
   * @return {@link Map} with all results
   * @throws IOException
   *           if some I/O error occurs
   */
  @Override
  public Map<String, ReporterDetails> validate(final StructureValidatorState structureValidatorState)
    throws IOException {
    Map<String, ReporterDetails> results = new HashMap<>();

    StructValidatorFactory svf = new StructValidatorFactory();
    StructValidator generator = svf.getGenerator("2.2.0");

    /* CSIPSTR1 */
    notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIPSTR1_ID);
    ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIPSTR1_ID,
      generator.validateCSIPSTR1(structureValidatorState, ZIP_MAGIC_NUMBER, moduleName)
        .setSpecification(generator.getCSIPVersion()));
    if (ResultsUtils.isResultValid(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIPSTR1_ID)) {

      /* CSIPSTR2 */
      ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIPSTR2_ID,
        new ReporterDetails(generator.getCSIPVersion(),
          "Requirement check was skipped as it will be checked under CSIP1", true, true));

      /* CSIPSTR3 */
      notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIPSTR3_ID);
      ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIPSTR3_ID,
        generator.validateCSIPSTR3(structureValidatorState).setSpecification(generator.getCSIPVersion()));

      /* CSIPSTR4 */
      notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIPSTR4_ID);
      ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIPSTR4_ID,
        generator.validateCSIPSTR4(structureValidatorState).setSpecification(generator.getCSIPVersion()));

      /* CSIPSTR5 */
      notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIPSTR5_ID);
      ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIPSTR5_ID,
        generator.validateCSIPSTR5(structureValidatorState).setSpecification(generator.getCSIPVersion()));

      /* CSIPSTR6 */
      notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIPSTR6_ID);
      ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIPSTR6_ID,
        generator.validateCSIPSTR6(structureValidatorState).setSpecification(generator.getCSIPVersion()));

      /* CSIPSTR7 */
      notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIPSTR7_ID);
      ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIPSTR7_ID,
        generator.validateCSIPSTR7(structureValidatorState).setSpecification(generator.getCSIPVersion()));

      /* CSIPSTR8 */
      notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIPSTR8_ID);
      ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIPSTR8_ID,
        generator.validateCSIPSTR8(structureValidatorState).setSpecification(generator.getCSIPVersion()));

      /* CSIPSTR9 */
      notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIPSTR9_ID);
      ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIPSTR9_ID,
        generator.validateCSIPSTR9(structureValidatorState).setSpecification(generator.getCSIPVersion()));

      /* CSIPSTR10 */
      notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIPSTR10_ID);
      ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIPSTR10_ID,
        generator.validateCSIPSTR10(structureValidatorState).setSpecification(generator.getCSIPVersion()));

      /* CSIPSTR11 */
      notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIPSTR11_ID);
      ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIPSTR11_ID,
        generator.validateCSIPSTR11(structureValidatorState).setSpecification(generator.getCSIPVersion()));

      /* CSIPSTR12 */
      notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIPSTR12_ID);
      ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIPSTR12_ID,
        generator.validateCSIPSTR12(structureValidatorState).setSpecification(generator.getCSIPVersion()));

      /* CSIPSTR13 */
      notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIPSTR13_ID);
      ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIPSTR13_ID,
        generator.validateCSIPSTR13(structureValidatorState).setSpecification(generator.getCSIPVersion()));

      /* CSIPSTR14 */
      notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIPSTR14_ID);
      ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIPSTR14_ID,
        generator.validateCSIPSTR14(structureValidatorState).setSpecification(generator.getCSIPVersion()));

      /* CSIPSTR15 */
      notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIPSTR15_ID);
      ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIPSTR15_ID,
        generator.validateCSIPSTR15(structureValidatorState).setSpecification(generator.getCSIPVersion()));

      /* CSIPSTR16 */
      notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIPSTR16_ID);
      ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIPSTR16_ID,
        generator.validateCSIPSTR16(structureValidatorState).setSpecification(generator.getCSIPVersion()));

    } else {
      String message;
      if (structureValidatorState.isZipFileFlag()) {
        message = "SKIPPED because must unpack to a single root folder";
      } else {
        message = "Root must be a single directory";
      }

      ResultsUtils.addResults(results, new ReporterDetails(generator.getCSIPVersion(), message, true, true),
        ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIPSTR2_ID,
        ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIPSTR3_ID,
        ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIPSTR4_ID,
        ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIPSTR5_ID,
        ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIPSTR6_ID,
        ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIPSTR7_ID,
        ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIPSTR8_ID,
        ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIPSTR9_ID,
        ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIPSTR10_ID,
        ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIPSTR11_ID,
        ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIPSTR12_ID,
        ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIPSTR13_ID,
        ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIPSTR14_ID,
        ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIPSTR15_ID,
        ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIPSTR16_ID);
    }
    notifyObserversFinishModule(moduleName);

    return results;
  }
}
