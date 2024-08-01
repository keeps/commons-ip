package org.roda_project.commons_ip2.validator.components.descriptiveMetadataComponent;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;

import org.roda_project.commons_ip2.mets_v1_12.beans.MdSecType;
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

/**
 * @author Carlos Afonso <cafonso@keep.pt>
 */
public class DescriptiveMetadataComponentValidator220 extends MetsValidatorImpl {

  /**
   * {@link String}.
   */
  private final String moduleName;
  /**
   * {@link List}.
   */
  private List<MdSecType> dmdSec;
  /**
   * {@link List}.
   */
  private List<String> dmdSecStatus;

  /**
   * {@link Map}.
   */

  /**
   * Initialize all objects needed to validation of this component.
   *
   * @throws IOException
   *           if some I/O error occurs.
   * @throws ParserConfigurationException
   *           if some error occurs.
   * @throws SAXException
   *           if some error occurs.
   */
  public DescriptiveMetadataComponentValidator220() throws IOException, ParserConfigurationException, SAXException {
    moduleName = Constants.CSIP_MODULE_NAME_3;
    this.dmdSecStatus = ControlledVocabularyParser.parse(Constants.PATH_RESOURCES_CSIP_VOCABULARY_DMD_SEC_STATUS);
  }

  @Override
  public Map<String, ReporterDetails> validate(final StructureValidatorState structureValidatorState,
    final MetsValidatorState metsValidatorState) throws IOException {
    this.dmdSec = metsValidatorState.getMets().getDmdSec();
    ReporterDetails csip;
    final Map<String, ReporterDetails> results = new HashMap<>();

    DmdSecValidatorFactory dmdSecValidatorFactory = new DmdSecValidatorFactory();
    DmdSecValidator generator = dmdSecValidatorFactory.getGenerator("2.2.0");

    /* CSIP17 */
    notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP17_ID);
    ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP17_ID,
      generator.validateCSIP17(structureValidatorState, metsValidatorState, dmdSec)
        .setSpecification(generator.getCSIPVersion()));

    /* CSIP18 */
    notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP18_ID);
    ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP18_ID,
      generator.validateCSIP18(metsValidatorState, dmdSec).setSpecification(generator.getCSIPVersion()));

    /* CSIP19 */
    notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP19_ID);
    ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP19_ID,
      generator.validateCSIP19(metsValidatorState, dmdSec).setSpecification(generator.getCSIPVersion()));

    /* CSIP20 */
    notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP20_ID);
    ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP20_ID,
      generator.validateCSIP20(metsValidatorState, dmdSec, dmdSecStatus).setSpecification(generator.getCSIPVersion()));

    /* CSIP21 */
    notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP21_ID);
    ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP21_ID,
      generator.validateCSIP21(metsValidatorState, dmdSec).setSpecification(generator.getCSIPVersion()));

    if (ResultsUtils.isResultValid(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP21_ID)) {
      /* CSIP22 */
      notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP22_ID);
      ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP22_ID,
        generator.validateCSIP22(metsValidatorState, dmdSec).setSpecification(generator.getCSIPVersion()));

      /* CSIP23 */
      notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP23_ID);
      ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP23_ID,
        generator.validateCSIP23(structureValidatorState, metsValidatorState, dmdSec)
          .setSpecification(generator.getCSIPVersion()));

      /* CSIP24 */
      notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP24_ID);
      ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP24_ID,
        generator.validateCSIP24(structureValidatorState, metsValidatorState, dmdSec)
          .setSpecification(generator.getCSIPVersion()));

      /* CSIP25 */
      notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP25_ID);
      ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP25_ID,
        generator.validateCSIP25(metsValidatorState, dmdSec).setSpecification(generator.getCSIPVersion()));

      /* CSIP26 */
      notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP26_ID);
      ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP26_ID,
        generator.validateCSIP26(metsValidatorState, dmdSec).setSpecification(generator.getCSIPVersion()));

      /* CSIP27 */
      notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP27_ID);
      ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP27_ID,
        generator.validateCSIP27(structureValidatorState, metsValidatorState, dmdSec)
          .setSpecification(generator.getCSIPVersion()));

      /* CSIP28 */
      notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP28_ID);
      ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP28_ID,
        generator.validateCSIP28(metsValidatorState, dmdSec).setSpecification(generator.getCSIPVersion()));

      /* CSIP29 */
      notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP29_ID);
      try {
        csip = generator.validateCSIP29(structureValidatorState, metsValidatorState, dmdSec)
          .setSpecification(generator.getCSIPVersion());
      } catch (final Exception e) {
        csip = new ReporterDetails(generator.getCSIPVersion(),
          Message.createErrorMessage("Can't calculate checksum of file %1$s", metsValidatorState.getMetsName(),
            metsValidatorState.isRootMets()),
          false, false);
      }
      ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP29_ID, csip);

      /* CSIP30 */
      notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP30_ID);
      ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP30_ID,
        generator.validateCSIP30(metsValidatorState, dmdSec).setSpecification(generator.getCSIPVersion()));

    } else {
      final String message = Message.createErrorMessage("SKIPPED in %1$s because mets/dmdSec/mdRef doesn't exist",
        metsValidatorState.getMetsName(), metsValidatorState.isRootMets());

      ResultsUtils.addResults(results,
        new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, message, true, true),
        ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP22_ID,
        ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP23_ID,
        ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP24_ID,
        ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP25_ID,
        ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP26_ID,
        ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP27_ID,
        ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP28_ID,
        ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP29_ID,
        ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP30_ID);
    }

    notifyObserversFinishModule(moduleName);
    return results;
  }
}
