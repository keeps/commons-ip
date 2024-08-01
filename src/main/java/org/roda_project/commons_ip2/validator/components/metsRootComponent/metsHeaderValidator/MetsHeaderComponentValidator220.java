package org.roda_project.commons_ip2.validator.components.metsRootComponent.metsHeaderValidator;

import org.roda_project.commons_ip2.mets_v1_12.beans.MetsType;
import org.roda_project.commons_ip2.validator.common.ControlledVocabularyParser;
import org.roda_project.commons_ip2.validator.components.MetsValidatorImpl;
import org.roda_project.commons_ip2.validator.constants220.Constants;
import org.roda_project.commons_ip2.validator.constants220.ConstantsCSIPspec;
import org.roda_project.commons_ip2.validator.reporter.ReporterDetails;
import org.roda_project.commons_ip2.validator.state.MetsValidatorState;
import org.roda_project.commons_ip2.validator.state.StructureValidatorState;
import org.roda_project.commons_ip2.validator.utils.Message;
import org.roda_project.commons_ip2.validator.utils.ResultsUtils;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Carlos Afonso <cafonso@keep.pt>
 */
public class MetsHeaderComponentValidator220 extends MetsValidatorImpl {
  /**
   * {@link String} moduleName.
   */
  private final String moduleName;

  /**
   * {@link List} of {@link String} with OAIS package types.
   */
  private final List<String> oaisPackageTypes;

  /**
   * {@link MetsType.MetsHdr}.
   */
  private MetsType.MetsHdr metsHdr;

  /**
   * {@link List} of {@link MetsType.MetsHdr.Agent}.
   */
  private List<MetsType.MetsHdr.Agent> agents;

  /**
   * Initialize all objects needed to validation of this component.
   *
   * @throws IOException
   *           if some I/O error occurs
   * @throws ParserConfigurationException
   *           if some error occurs
   * @throws SAXException
   *           if some error occurs
   */
  public MetsHeaderComponentValidator220() throws IOException, ParserConfigurationException, SAXException {
    this.moduleName = Constants.CSIP_MODULE_NAME_2;
    this.oaisPackageTypes = ControlledVocabularyParser
      .parse(Constants.PATH_RESOURCES_CSIP_VOCABULARY_OAIS_PACKAGE_TYPE);
  }

  @Override
  public Map<String, ReporterDetails> validate(final StructureValidatorState structureValidatorState,
                                               final MetsValidatorState metsValidatorState) throws IOException {
    metsHdr = metsValidatorState.getMets().getMetsHdr();

    MetsHeaderValidatorFactory metsHeaderValidatorFactory = new MetsHeaderValidatorFactory();
    MetsHeaderValidator generator = metsHeaderValidatorFactory.getGenerator("2.2.0");

    if (metsHdr != null) {
      agents = metsHdr.getAgent();
    }

    final Map<String, ReporterDetails> results = new HashMap<>();

    final ReporterDetails skippedCSIP;
    /* CSIP117 */
    notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP117_ID);
    ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP117_ID,
      generator.validateCSIP117(metsValidatorState, metsHdr).setSpecification(generator.getCSIPVersion()));

    if (ResultsUtils.isResultValid(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP117_ID)) {
      /* CSIP7 */
      notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP7_ID);
      ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP7_ID,
        generator.validateCSIP7(metsValidatorState, metsHdr).setSpecification(generator.getCSIPVersion()));

      /* CSIP8 */
      skippedCSIP = new ReporterDetails(generator.getCSIPVersion(),
        Message.createErrorMessage("SKIPPED in  %1$s  can't validate", metsValidatorState.getMetsName(),
          metsValidatorState.isRootMets()),
        true, true);
      ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP8_ID, skippedCSIP);

      /* CSIP9 */
      notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP9_ID);
      ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP9_ID, generator
        .validateCSIP9(metsValidatorState, metsHdr, oaisPackageTypes).setSpecification(generator.getCSIPVersion()));

      if (metsValidatorState.isRootMets()) {
        /* CSIP10 */
        notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP10_ID);
        ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP10_ID,
          generator.validateCSIP10(metsValidatorState, agents).setSpecification(generator.getCSIPVersion()));

        if (ResultsUtils.isResultValid(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP10_ID)) {
          /* CSIP11 */
          notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP11_ID);
          ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP11_ID,
            generator.validateCSIP11(metsValidatorState, agents).setSpecification(generator.getCSIPVersion()));

          /* CSIP12 */
          notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP12_ID);
          ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP12_ID,
            generator.validateCSIP12(metsValidatorState, agents).setSpecification(generator.getCSIPVersion()));

          /* CSIP13 */
          notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP13_ID);
          ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP13_ID,
            generator.validateCSIP13(metsValidatorState, agents).setSpecification(generator.getCSIPVersion()));

          /* CSIP14 */
          notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP14_ID);
          ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP14_ID,
            generator.validateCSIP14(metsValidatorState, agents).setSpecification(generator.getCSIPVersion()));

          /* CSIP15 */
          notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP15_ID);
          ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP15_ID,
            generator.validateCSIP15(metsValidatorState, agents).setSpecification(generator.getCSIPVersion()));

          /* CSIP16 */
          notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP16_ID);
          ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP16_ID,
            generator.validateCSIP16(metsValidatorState, agents).setSpecification(generator.getCSIPVersion()));
        } else {
          final String message = Message.createErrorMessage(
            "SKIPPED in %1$s because it does not contain a mets/metsHdr/agent element",
            metsValidatorState.getMetsName(), metsValidatorState.isRootMets());

          ResultsUtils.addResults(results, new ReporterDetails(generator.getCSIPVersion(), message, true, true),
            ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP11_ID,
            ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP12_ID,
            ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP13_ID,
            ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP14_ID,
            ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP15_ID,
            ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP16_ID);
        }
      }
    } else {
      final String message = Message.createErrorMessage("SKIPPED because mets/metsHdr doesn't exist ",
        metsValidatorState.getMetsName(), metsValidatorState.isRootMets());

      ResultsUtils.addResults(results, new ReporterDetails(generator.getCSIPVersion(), message, true, true),
        ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP7_ID,
        ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP8_ID,
        ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP9_ID,
        ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP10_ID,
        ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP11_ID,
        ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP12_ID,
        ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP13_ID,
        ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP14_ID,
        ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP15_ID,
        ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP16_ID);
    }
    notifyObserversFinishModule(moduleName);
    return results;
  }
}
