package org.roda_project.commons_ip2.validator.components.administritiveMetadataComponent;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;

import org.roda_project.commons_ip2.mets_v1_12.beans.AmdSecType;
import org.roda_project.commons_ip2.validator.common.ControlledVocabularyParser;
import org.roda_project.commons_ip2.validator.components.MetsValidatorImpl;
import org.roda_project.commons_ip2.validator.constants.Constants;
import org.roda_project.commons_ip2.validator.constants.ConstantsCSIPspec;
import org.roda_project.commons_ip2.validator.reporter.ReporterDetails;
import org.roda_project.commons_ip2.validator.state.MetsValidatorState;
import org.roda_project.commons_ip2.validator.state.StructureValidatorState;
import org.roda_project.commons_ip2.validator.utils.Message;
import org.roda_project.commons_ip2.validator.utils.ResultsUtils;
import org.xml.sax.SAXException;

/** {@author João Gomes <jgomes@keep.pt>}. */
public class AdministritiveMetadataComponentValidator210 extends MetsValidatorImpl {
  /**
   * Constant with the {@link String} "mets/@OBJECTID can't be null".
   */
  private static final String METS_OBJECTID_CAN_T_BE_NULL = "mets/@OBJECTID can't be null";

  /**
   * Constant with the {@link String} "UTF-8".
   */
  private static final String UTF_8 = "UTF-8";

  /**
   * {@link String}.
   */
  private final String moduleName;
  /**
   * The {@link List} of {@link AmdSecType}.
   */
  private List<AmdSecType> amdSec;
  /**
   * The {@link List} of {@link String}.
   */
  private final List<String> dmdSecStatus;

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
  public AdministritiveMetadataComponentValidator210() throws IOException, ParserConfigurationException, SAXException {
    this.moduleName = Constants.CSIP_MODULE_NAME_4;
    this.dmdSecStatus = ControlledVocabularyParser.parse(Constants.PATH_RESOURCES_CSIP_VOCABULARY_DMD_SEC_STATUS);
  }

  @Override
  public Map<String, ReporterDetails> validate(final StructureValidatorState structureValidatorState,
    final MetsValidatorState metsValidatorState) throws IOException {
    AmdSecValidatorFactory amdSecValidatorFactory = new AmdSecValidatorFactory();
    AmdSecValidator generator = amdSecValidatorFactory.getGenerator("2.1.0");
    final Map<String, ReporterDetails> results = new HashMap<>();
    amdSec = metsValidatorState.getMets().getAmdSec();
    ReporterDetails csip;
    /* CSIP31 */
    notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP31_ID);
    ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP31_ID,
      generator.validateCSIP31(structureValidatorState, metsValidatorState, amdSec)
        .setSpecification(generator.getVersion()));

    /* CSIP32 */
    notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP32_ID);
    ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP32_ID, generator
      .validateCSIP32(metsValidatorState, amdSec).setSpecification(generator.getVersion()));

    if (ResultsUtils.isResultValid(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP32_ID)) {

      /* CSIP33 */
      notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP33_ID);
      ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP33_ID, generator
        .validateCSIP33(metsValidatorState, amdSec).setSpecification(generator.getVersion()));

      /* CSIP34 */
      notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP34_ID);
      ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP34_ID,
        generator.validateCSIP34(metsValidatorState, amdSec, dmdSecStatus)
          .setSpecification(generator.getVersion()));

      /* CSIP35 */
      notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP35_ID);
      ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP35_ID, generator
        .validateCSIP35(metsValidatorState, amdSec).setSpecification(generator.getVersion()));

      if (ResultsUtils.isResultValid(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP35_ID)) {

        /* CSIP36 */
        notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP36_ID);
        ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP36_ID,
          generator.validateCSIP36(metsValidatorState, amdSec)
            .setSpecification(generator.getVersion()));

        /* CSIP37 */
        notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP37_ID);
        ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP37_ID,
          generator.validateCSIP37(structureValidatorState, metsValidatorState, amdSec)
            .setSpecification(generator.getVersion()));

        /* CSIP38 */
        notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP38_ID);
        ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP38_ID,
          generator.validateCSIP38(structureValidatorState, metsValidatorState, amdSec)
            .setSpecification(generator.getVersion()));

        /* CSIP39 */
        notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP39_ID);
        ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP39_ID,
          generator.validateCSIP39(metsValidatorState, amdSec)
            .setSpecification(generator.getVersion()));

        /* CSIP40 */
        notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP40_ID);
        ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP40_ID,
          generator.validateCSIP40(metsValidatorState, amdSec)
            .setSpecification(generator.getVersion()));

        /* CSIP41 */
        notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP41_ID);
        ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP41_ID,
          generator.validateCSIP41(structureValidatorState, metsValidatorState, amdSec)
            .setSpecification(generator.getVersion()));

        /* CSIP42 */
        notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP42_ID);
        ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP42_ID,
          generator.validateCSIP42(metsValidatorState, amdSec)
            .setSpecification(generator.getVersion()));

        /* CSIP43 */
        notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP43_ID);
        try {
          csip = generator.validateCSIP43(structureValidatorState, metsValidatorState, amdSec)
            .setSpecification(generator.getVersion());
        } catch (final Exception e) {
          csip = new ReporterDetails(generator.getVersion(),
            Message.createErrorMessage("Can't calculate checksum of file %1$s", metsValidatorState.getMetsName(),
              metsValidatorState.isRootMets()),
            false, false);
        }
        ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP43_ID, csip);

        /* CSIP44 */
        notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP44_ID);
        ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP44_ID,
          generator.validateCSIP44(metsValidatorState, amdSec)
            .setSpecification(generator.getVersion()));

      } else {
        final String message = Message.createErrorMessage(
          "SKIPPED in %1$s because mets/amdSec/digiprovMD/mdRef doesn't exist", metsValidatorState.getMetsName(),
          metsValidatorState.isRootMets());

        ResultsUtils.addResults(results,
          new ReporterDetails(generator.getVersion(), message, true, true),
          ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP36_ID,
          ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP37_ID,
          ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP38_ID,
          ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP39_ID,
          ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP40_ID,
          ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP41_ID,
          ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP42_ID,
          ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP43_ID,
          ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP44_ID);
      }

    } else {
      final String message = Message.createErrorMessage("SKIPPED in %1$s because mets/amdSec/digiprovMD doesn't exist",
        metsValidatorState.getMetsName(), metsValidatorState.isRootMets());

      ResultsUtils.addResults(results,
        new ReporterDetails(generator.getVersion(), message, true, true),
        ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP33_ID,
        ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP34_ID,
        ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP35_ID,
        ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP36_ID,
        ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP37_ID,
        ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP38_ID,
        ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP39_ID,
        ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP40_ID,
        ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP41_ID,
        ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP42_ID,
        ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP43_ID,
        ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP44_ID);
    }

    /* CSIP45 */
    notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP45_ID);
    ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP45_ID, generator
      .validateCSIP45(metsValidatorState, amdSec).setSpecification(generator.getVersion()));

    if (ResultsUtils.isResultValid(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP45_ID)) {
      /* CSIP46 */
      notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP46_ID);
      ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP46_ID, generator
        .validateCSIP46(metsValidatorState, amdSec).setSpecification(generator.getVersion()));

      /* CSIP47 */
      notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP47_ID);
      ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP47_ID, generator
        .validateCSIP47(metsValidatorState, amdSec, dmdSecStatus).setSpecification(generator.getVersion()));

      /* CSIP48 */
      notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP48_ID);
      ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP48_ID, generator
        .validateCSIP48(metsValidatorState, amdSec).setSpecification(generator.getVersion()));

      if (ResultsUtils.isResultValid(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP48_ID)) {

        /* CSIP49 */
        notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP49_ID);
        ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP49_ID,
          generator.validateCSIP49(metsValidatorState, amdSec)
            .setSpecification(generator.getVersion()));

        /* CSIP50 */
        notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP50_ID);
        ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP50_ID,
          generator.validateCSIP50(structureValidatorState, metsValidatorState, amdSec)
            .setSpecification(generator.getVersion()));

        /* CSIP51 */
        notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP51_ID);
        ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP51_ID,
          generator.validateCSIP51(structureValidatorState, metsValidatorState, amdSec)
            .setSpecification(generator.getVersion()));

        /* CSIP52 */
        notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP52_ID);
        ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP52_ID,
          generator.validateCSIP52(metsValidatorState, amdSec)
            .setSpecification(generator.getVersion()));

        /* CSIP53 */
        notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP53_ID);
        ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP53_ID,
          generator.validateCSIP53(metsValidatorState, amdSec)
            .setSpecification(generator.getVersion()));

        /* CSIP54 */
        notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP54_ID);
        ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP54_ID,
          generator.validateCSIP54(structureValidatorState, metsValidatorState, amdSec)
            .setSpecification(generator.getVersion()));

        /* CSIP55 */
        notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP55_ID);
        ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP55_ID,
          generator.validateCSIP55(metsValidatorState, amdSec)
            .setSpecification(generator.getVersion()));

        /* CSIP56 */
        notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP56_ID);
        try {
          csip = generator.validateCSIP56(structureValidatorState, metsValidatorState, amdSec)
            .setSpecification(generator.getVersion());
        } catch (final Exception e) {
          csip = new ReporterDetails(generator.getVersion(),
            Message.createErrorMessage("Can't calculate checksum of file %1$s", metsValidatorState.getMetsName(),
              metsValidatorState.isRootMets()),
            false, false);
        }
        ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP56_ID, csip);

        /* CSIP57 */
        notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP57_ID);
        ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP57_ID,
          generator.validateCSIP57(metsValidatorState, amdSec)
            .setSpecification(generator.getVersion()));

      } else {
        final String message = Message.createErrorMessage(
          "SKIPPED in %1$s because mets/amdSec/rightsMD/mdRef doesn't exist", metsValidatorState.getMetsName(),
          metsValidatorState.isRootMets());

        ResultsUtils.addResults(results,
          new ReporterDetails(generator.getVersion(), message, true, true),
          ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP49_ID,
          ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP50_ID,
          ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP51_ID,
          ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP52_ID,
          ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP53_ID,
          ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP54_ID,
          ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP55_ID,
          ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP56_ID,
          ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP57_ID);
      }
    } else {
      final ReporterDetails skippedCSIP = new ReporterDetails(generator.getVersion(),
        Message.createErrorMessage("SKIPPED in %1$s because mets/amdSec/rightsMD doesn't exist",
          metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
        true, true);
      ResultsUtils.addResults(results, skippedCSIP, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP46_ID,
        ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP47_ID,
        ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP48_ID,
        ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP49_ID,
        ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP50_ID,
        ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP51_ID,
        ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP52_ID,
        ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP53_ID,
        ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP54_ID,
        ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP55_ID,
        ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP56_ID,
        ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP57_ID);
    }
    notifyObserversFinishModule(moduleName);
    return results;
  }
}
