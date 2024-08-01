package org.roda_project.commons_ip2.validator.components.sipMetsRootComponent.sipMetsHdrComponent;

import org.roda_project.commons_ip2.mets_v1_12.beans.MetsType;
import org.roda_project.commons_ip2.validator.common.ControlledVocabularyParser;
import org.roda_project.commons_ip2.validator.components.MetsValidatorImpl;
import org.roda_project.commons_ip2.validator.constants220.Constants;
import org.roda_project.commons_ip2.validator.constants220.ConstantsSIPspec;
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
public class SipMetsHdrComponent220 extends MetsValidatorImpl {
  /**
   * Module name of the specification.
   */
  private final String moduleName;
  /**
   * Flag if is to validate agents section or not.
   */
  private boolean isToValidateAgents = true;
  /**
   * Flag if is to validate this section or not.
   */
  private boolean isToValidateMetsHdr = true;
  /**
   * {@link MetsType.MetsHdr}.
   */
  private MetsType.MetsHdr metsHdr;

  /**
   * {@link List} of all {@link MetsType.MetsHdr.Agent }.
   */
  private List<MetsType.MetsHdr.Agent> agents;
  /**
   * {@link List}.
   */
  private final List<String> recordsStatus;

  /**
   * Initializes Object.
   *
   * @throws IOException
   *           if some I/O error occurs
   * @throws ParserConfigurationException
   *           if some error occurs.
   * @throws SAXException
   *           if some error occurs.
   */
  public SipMetsHdrComponent220() throws IOException, ParserConfigurationException, SAXException {
    this.moduleName = Constants.SIP_MODULE_NAME_2;
    this.recordsStatus = ControlledVocabularyParser.parse(Constants.PATH_RESOURCES_CSIP_VOCABULARY_RECORD_STATUS);
  }

  public void setIsToValidateMetsHdr(final boolean isToValidateMetsHdr) {
    this.isToValidateMetsHdr = isToValidateMetsHdr;
  }

  public void setIsToValidateAgents(final boolean isToValidateAgents) {
    this.isToValidateAgents = isToValidateAgents;
  }

  @Override
  public Map<String, ReporterDetails> validate(final StructureValidatorState structureValidatorState,
                                               final MetsValidatorState metsValidatorState) {
    final Map<String, ReporterDetails> results = new HashMap<>();

    SipMetsHdrValidatorFactory sipMetsHdrValidatorFactory = new SipMetsHdrValidatorFactory();
    SipMetsHdrValidator generator = sipMetsHdrValidatorFactory.getGenerator("2.2.0");

    metsHdr = metsValidatorState.getMets().getMetsHdr();
    agents = metsValidatorState.getMets().getMetsHdr().getAgent();
    if (isToValidateMetsHdr) {
      /* SIP3 */
      notifyObserversValidationStarted(moduleName, ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP3_ID);
      ResultsUtils.addResult(results, ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP3_ID,
        generator.validateSIP3(metsValidatorState, metsHdr, recordsStatus).setSpecification(generator.getSIPVersion()));

      /* SIP4 */
      notifyObserversValidationStarted(moduleName, ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP4_ID);
      ResultsUtils.addResult(results, ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP4_ID,
        generator.validateSIP4(metsValidatorState, metsHdr).setSpecification(generator.getSIPVersion()));

      /* SIP5 */
      notifyObserversValidationStarted(moduleName, ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP5_ID);
      ResultsUtils.addResult(results, ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP5_ID,
        generator.validateSIP5(metsValidatorState, metsHdr).setSpecification(generator.getSIPVersion()));

      /* SIP6 */
      notifyObserversValidationStarted(moduleName, ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP6_ID);
      ResultsUtils.addResult(results, ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP6_ID,
        generator.validateSIP6(metsValidatorState, metsHdr).setSpecification(generator.getSIPVersion()));

      /* SIP7 */
      notifyObserversValidationStarted(moduleName, ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP7_ID);
      ResultsUtils.addResult(results, ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP7_ID,
        generator.validateSIP7(metsValidatorState, metsHdr).setSpecification(generator.getSIPVersion()));

      /* SIP8 */
      notifyObserversValidationStarted(moduleName, ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP8_ID);
      ResultsUtils.addResult(results, ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP8_ID,
        generator.validateSIP8(metsValidatorState, metsHdr).setSpecification(generator.getSIPVersion()));

      if (isToValidateAgents && metsValidatorState.isRootMets()) {
        /* SIP9 */
        notifyObserversValidationStarted(moduleName, ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP9_ID);
        ResultsUtils.addResult(results, ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP9_ID,
          generator.validateSIP9(metsValidatorState, agents).setSpecification(generator.getSIPVersion()));

        if (ResultsUtils.isResultValid(results, ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP9_ID)) {
          /* SIP10 */
          notifyObserversValidationStarted(moduleName, ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP10_ID);
          ResultsUtils.addResult(results, ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP10_ID,
            generator.validateSIP10(metsValidatorState, agents).setSpecification(generator.getSIPVersion()));

          /* SIP11 */
          notifyObserversValidationStarted(moduleName, ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP11_ID);
          ResultsUtils.addResult(results, ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP11_ID,
            generator.validateSIP11(metsValidatorState, agents).setSpecification(generator.getSIPVersion()));

          /* SIP12 */
          notifyObserversValidationStarted(moduleName, ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP12_ID);
          ResultsUtils.addResult(results, ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP12_ID,
            generator.validateSIP12(metsValidatorState, agents).setSpecification(generator.getSIPVersion()));

          /* SIP13 */
          notifyObserversValidationStarted(moduleName, ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP13_ID);
          ResultsUtils.addResult(results, ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP13_ID,
            generator.validateSIP13(metsValidatorState, agents).setSpecification(generator.getSIPVersion()));

          /* SIP14 */
          notifyObserversValidationStarted(moduleName, ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP14_ID);
          ResultsUtils.addResult(results, ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP14_ID,
            generator.validateSIP14(metsValidatorState, agents).setSpecification(generator.getSIPVersion()));
        } else {
          final String message = Message.createErrorMessage(
            "SKIPPED in %1$s because mets/metsHdr/agent with the role ARCHIVIST doesn't exist",
            metsValidatorState.getMetsName(), metsValidatorState.isRootMets());

          ResultsUtils.addResults(results,
            new ReporterDetails(generator.getSIPVersion(), message, true, true),
            ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP10_ID,
            ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP11_ID,
            ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP12_ID,
            ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP13_ID,
            ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP14_ID);
        }

        /* SIP15 */
        notifyObserversValidationStarted(moduleName, ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP15_ID);
        ResultsUtils.addResult(results, ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP15_ID,
          generator.validateSIP15(metsValidatorState, agents).setSpecification(generator.getSIPVersion()));

        if (ResultsUtils.isResultValid(results, ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP15_ID)) {
          /* SIP16 */
          notifyObserversValidationStarted(moduleName, ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP16_ID);
          ResultsUtils.addResult(results, ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP16_ID,
            generator.validateSIP16(metsValidatorState, agents).setSpecification(generator.getSIPVersion()));

          /* SIP17 */
          notifyObserversValidationStarted(moduleName, ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP17_ID);
          ResultsUtils.addResult(results, ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP17_ID,
            generator.validateSIP17(metsValidatorState, agents).setSpecification(generator.getSIPVersion()));

          /* SIP18 */
          notifyObserversValidationStarted(moduleName, ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP18_ID);
          ResultsUtils.addResult(results, ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP18_ID,
            generator.validateSIP18(metsValidatorState, agents).setSpecification(generator.getSIPVersion()));

          /* SIP19 */
          notifyObserversValidationStarted(moduleName, ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP19_ID);
          ResultsUtils.addResult(results, ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP19_ID,
            generator.validateSIP19(metsValidatorState, agents).setSpecification(generator.getSIPVersion()));

          /* SIP20 */
          notifyObserversValidationStarted(moduleName, ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP20_ID);
          ResultsUtils.addResult(results, ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP20_ID,
            generator.validateSIP20(metsValidatorState, agents).setSpecification(generator.getSIPVersion()));

        } else {
          final String message = Message.createErrorMessage(
            "SKIPPED because mets/metsHdr/agent with the role CREATOR doesn't exist", metsValidatorState.getMetsName(),
            metsValidatorState.isRootMets());

          ResultsUtils.addResults(results,
            new ReporterDetails(generator.getSIPVersion(), message, true, true),
            ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP16_ID,
            ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP17_ID,
            ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP18_ID,
            ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP19_ID,
            ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP20_ID);
        }

        /* SIP21 */
        notifyObserversValidationStarted(moduleName, ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP21_ID);
        ResultsUtils.addResult(results, ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP21_ID,
          generator.validateSIP21(metsValidatorState, agents).setSpecification(generator.getSIPVersion()));

        if (ResultsUtils.isResultValid(results, ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP21_ID)) {
          /* SIP22 */
          notifyObserversValidationStarted(moduleName, ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP22_ID);
          ResultsUtils.addResult(results, ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP22_ID,
            generator.validateSIP22(metsValidatorState, agents).setSpecification(generator.getSIPVersion()));

          /* SIP23 */
          notifyObserversValidationStarted(moduleName, ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP20_ID);
          ResultsUtils.addResult(results, ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP23_ID,
            generator.validateSIP23(metsValidatorState, agents).setSpecification(generator.getSIPVersion()));

          /* SIP24 */
          notifyObserversValidationStarted(moduleName, ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP24_ID);
          ResultsUtils.addResult(results, ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP24_ID,
            generator.validateSIP24(metsValidatorState, agents).setSpecification(generator.getSIPVersion()));

          /* SIP25 */
          notifyObserversValidationStarted(moduleName, ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP25_ID);
          ResultsUtils.addResult(results, ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP25_ID,
            generator.validateSIP25(metsValidatorState, agents).setSpecification(generator.getSIPVersion()));

        } else {
          final String message = Message.createErrorMessage(
            "SKIPPED in %1$s because mets/metsHdr/agent with the role OTHER doesn't exist",
            metsValidatorState.getMetsName(), metsValidatorState.isRootMets());

          ResultsUtils.addResults(results,
            new ReporterDetails(generator.getSIPVersion(), message, true, true),
            ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP22_ID,
            ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP23_ID,
            ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP24_ID,
            ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP25_ID);
        }

        /* SIP26 */
        notifyObserversValidationStarted(moduleName, ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP26_ID);
        ResultsUtils.addResult(results, ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP26_ID,
          generator.validateSIP26(metsValidatorState, agents).setSpecification(generator.getSIPVersion()));

        if (ResultsUtils.isResultValid(results, ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP26_ID)) {
          /* SIP27 */
          notifyObserversValidationStarted(moduleName, ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP27_ID);
          ResultsUtils.addResult(results, ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP27_ID,
            generator.validateSIP27(metsValidatorState, agents).setSpecification(generator.getSIPVersion()));

          /* SIP28 */
          notifyObserversValidationStarted(moduleName, ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP28_ID);
          ResultsUtils.addResult(results, ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP28_ID,
            generator.validateSIP28(metsValidatorState, agents).setSpecification(generator.getSIPVersion()));

          /* SIP29 */
          notifyObserversValidationStarted(moduleName, ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP29_ID);
          ResultsUtils.addResult(results, ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP29_ID,
            generator.validateSIP29(metsValidatorState, agents).setSpecification(generator.getSIPVersion()));

          /* SIP30 */
          notifyObserversValidationStarted(moduleName, ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP30_ID);
          ResultsUtils.addResult(results, ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP30_ID,
            generator.validateSIP30(metsValidatorState, agents).setSpecification(generator.getSIPVersion()));

          /* SIP31 */
          notifyObserversValidationStarted(moduleName, ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP31_ID);
          ResultsUtils.addResult(results, ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP31_ID,
            generator.validateSIP31(metsValidatorState, agents).setSpecification(generator.getSIPVersion()));

        } else {
          final String message = Message.createErrorMessage(
            "SKIPPED in %1$s because mets/metsHdr/agent with the role PRESERVATION " + "doesn't exist",
            metsValidatorState.getMetsName(), metsValidatorState.isRootMets());

          ResultsUtils.addResults(results,
            new ReporterDetails(generator.getSIPVersion(), message, true, true),
            ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP27_ID,
            ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP28_ID,
            ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP29_ID,
            ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP30_ID,
            ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP31_ID);
        }
      }
    } else {
      final String message = Message.createErrorMessage("SKIPPED because mets/metsHdr doesn't exist ",
        metsValidatorState.getMetsName(), metsValidatorState.isRootMets());

      ResultsUtils.addResults(results,
        new ReporterDetails(generator.getSIPVersion(), message, true, true),
        ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP3_ID,
        ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP4_ID,
        ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP5_ID,
        ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP6_ID,
        ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP7_ID,
        ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP8_ID,
        ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP9_ID,
        ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP10_ID,
        ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP11_ID,
        ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP12_ID,
        ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP13_ID,
        ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP14_ID,
        ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP15_ID,
        ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP16_ID,
        ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP17_ID,
        ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP18_ID,
        ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP19_ID,
        ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP20_ID,
        ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP21_ID,
        ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP22_ID,
        ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP23_ID,
        ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP24_ID,
        ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP25_ID,
        ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP26_ID,
        ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP27_ID,
        ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP28_ID,
        ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP29_ID,
        ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP30_ID,
        ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP31_ID);
    }
    notifyObserversFinishModule(moduleName);
    return results;
  }
}

