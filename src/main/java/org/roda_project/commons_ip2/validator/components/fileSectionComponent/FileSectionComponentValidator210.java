package org.roda_project.commons_ip2.validator.components.fileSectionComponent;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;

import org.roda_project.commons_ip2.validator.common.ControlledVocabularyParser;
import org.roda_project.commons_ip2.validator.components.MetsValidatorImpl;
import org.roda_project.commons_ip2.validator.constants.Constants;
import org.roda_project.commons_ip2.validator.constants.ConstantsCSIPspec;
import org.roda_project.commons_ip2.validator.constants.ConstantsSIPspec;
import org.roda_project.commons_ip2.validator.reporter.ReporterDetails;
import org.roda_project.commons_ip2.validator.state.MetsValidatorState;
import org.roda_project.commons_ip2.validator.state.StructureValidatorState;
import org.roda_project.commons_ip2.validator.utils.Message;
import org.roda_project.commons_ip2.validator.utils.ResultsUtils;
import org.xml.sax.SAXException;

/** {@author João Gomes <jgomes@keep.pt>}. */
public class FileSectionComponentValidator210 extends MetsValidatorImpl {
  /**
   * {@link String} module name.
   */
  private final String moduleName;
  /**
   * {@link List} of content information type.
   */
  private final List<String> contentInformationType;

  /**
   * Initializes Validation component.
   *
   * @throws IOException
   *           if some I/O error occurs.
   * @throws ParserConfigurationException
   *           if some error occurs.
   * @throws SAXException
   *           if some error occurs.
   */
  public FileSectionComponentValidator210() throws IOException, ParserConfigurationException, SAXException {
    moduleName = Constants.CSIP_MODULE_NAME_5;
    this.contentInformationType = ControlledVocabularyParser
      .parse(Constants.PATH_RESOURCES_CSIP_VOCABULARY_CONTENT_INFORMATION_TYPE);
  }

  @Override
  public Map<String, ReporterDetails> validate(final StructureValidatorState structureValidatorState,
                                               final MetsValidatorState metsValidatorState) throws IOException {
    ReporterDetails csip;
    final Map<String, ReporterDetails> results = new HashMap<>();

    FileSecValidatorFactory fileSecValidatorFactory = new FileSecValidatorFactory();
    FileSecValidator generator = fileSecValidatorFactory.getGenerator("2.1.0");

    /* CSIP58 */
    notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP58_ID);
    ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP58_ID,
      generator.validateCSIP58(metsValidatorState).setSpecification(generator.getCSIPVersion()));

    if (ResultsUtils.isResultValid(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP58_ID)) {

      /* CSIP59 */
      notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP59_ID);
      ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP59_ID,
        generator.validateCSIP59(metsValidatorState).setSpecification(generator.getCSIPVersion()));

      /* CSIP60 */
      notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP60_ID);
      ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP60_ID, generator
        .validateCSIP60(structureValidatorState, metsValidatorState).setSpecification(generator.getCSIPVersion()));

      /* CSIP113 */
      notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP113_ID);
      ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP113_ID, generator
        .validateCSIP113(structureValidatorState, metsValidatorState).setSpecification(generator.getCSIPVersion()));

      /* CSIP114 */
      notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP114_ID);
      ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP114_ID, generator
        .validateCSIP114(structureValidatorState, metsValidatorState).setSpecification(generator.getCSIPVersion()));

      /* CSIP61 */
      notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP61_ID);
      ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP61_ID,
        generator.validateCSIP61(metsValidatorState).setSpecification(generator.getCSIPVersion()));

      /* CSIP62 */
      notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP62_ID);
      ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP62_ID,
        generator.validateCSIP62(metsValidatorState, contentInformationType).setSpecification(generator.getCSIPVersion()));

      /* CSIP63 */
      notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP63_ID);
      ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP63_ID,
        generator.validateCSIP63(metsValidatorState).setSpecification(generator.getCSIPVersion()));

      /* CSIP64 */
      notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP64_ID);
      ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP64_ID, generator
        .validateCSIP64(structureValidatorState, metsValidatorState).setSpecification(generator.getCSIPVersion()));

      /* CSIP65 */
      notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP65_ID);
      ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP65_ID,
        generator.validateCSIP65(metsValidatorState).setSpecification(generator.getCSIPVersion()));

      /* CSIP66 */
      notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP66_ID);
      ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP66_ID, generator
        .validateCSIP66(structureValidatorState, metsValidatorState).setSpecification(generator.getCSIPVersion()));

      if (ResultsUtils.isResultValid(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP66_ID)) {

        /* CSIP67 */
        notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP67_ID);
        ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP67_ID,
          generator.validateCSIP67(metsValidatorState).setSpecification(generator.getCSIPVersion()));

        /* CSIP68 */
        notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP68_ID);
        ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP68_ID,
          generator.validateCSIP68(metsValidatorState).setSpecification(generator.getCSIPVersion()));

        /* CSIP69 */
        notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP69_ID);
        ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP69_ID, generator
          .validateCSIP69(structureValidatorState, metsValidatorState).setSpecification(generator.getCSIPVersion()));

        /* CSIP70 */
        notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP70_ID);
        ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP70_ID,
          generator.validateCSIP70(metsValidatorState).setSpecification(generator.getCSIPVersion()));

        /* CSIP71 */
        notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP71_ID);
        try {
          csip = generator.validateCSIP71(structureValidatorState, metsValidatorState)
            .setSpecification(generator.getCSIPVersion());
        } catch (final Exception e) {
          csip = new ReporterDetails(generator.getCSIPVersion(),
            Message.createErrorMessage("Can't calculate checksum of file %1$s", metsValidatorState.getMetsName(),
              metsValidatorState.isRootMets()),
            false, false);
        }
        ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP71_ID, csip);

        /* CSIP72 */
        notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP72_ID);
        ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP72_ID,
          generator.validateCSIP72(metsValidatorState).setSpecification(generator.getCSIPVersion()));

        /* CSIP73 */
        notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP73_ID);
        ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP73_ID,
          generator.validateCSIP73(metsValidatorState).setSpecification(generator.getCSIPVersion()));

        /* CSIP74 */
        notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP74_ID);
        ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP74_ID,
          generator.validateCSIP74(metsValidatorState).setSpecification(generator.getCSIPVersion()));

        /* CSIP75 */
        notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP75_ID);
        ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP75_ID,
          generator.validateCSIP75(metsValidatorState).setSpecification(generator.getCSIPVersion()));

        /* CSIP76 */
        notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP76_ID);
        ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP76_ID,
          generator.validateCSIP76(metsValidatorState).setSpecification(generator.getCSIPVersion()));

        if (ResultsUtils.isResultValid(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP76_ID)) {

          /* CSIP77 */
          notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP77_ID);
          ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP77_ID,
            generator.validateCSIP77(metsValidatorState).setSpecification(generator.getCSIPVersion()));

          /* CSIP78 */
          notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP78_ID);
          ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP78_ID, generator
            .validateCSIP78(structureValidatorState, metsValidatorState).setSpecification(generator.getCSIPVersion()));

          /* CSIP79 */
          notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP79_ID);
          ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP79_ID, generator
            .validateCSIP79(structureValidatorState, metsValidatorState).setSpecification(generator.getCSIPVersion()));

        } else {
          final String message = Message.createErrorMessage(
            "SKIPPED in %1$s because mets/fileSec/fileGrp/file/FLocat doesn't exist", metsValidatorState.getMetsName(),
            metsValidatorState.isRootMets());

          ResultsUtils.addResults(results, new ReporterDetails(generator.getCSIPVersion(), message, true, true),
            ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP77_ID,
            ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP78_ID,
            ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP79_ID);
        }

      } else {
        final String message = Message.createErrorMessage(
          "SKIPPED in %1$s because mets/fileSec/fileGrp/file/ doesn't exist", metsValidatorState.getMetsName(),
          metsValidatorState.isRootMets());

        ResultsUtils.addResults(results, new ReporterDetails(generator.getCSIPVersion(), message, true, true),
          ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP67_ID,
          ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP68_ID,
          ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP69_ID,
          ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP70_ID,
          ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP71_ID,
          ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP72_ID,
          ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP73_ID,
          ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP74_ID,
          ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP75_ID,
          ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP76_ID,
          ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP77_ID,
          ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP78_ID,
          ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP79_ID,
          ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP32_ID,
          ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP33_ID,
          ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP34_ID,
          ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP35_ID);
      }
    } else {
      final String message = Message.createErrorMessage("SKIPPED in %1$s because mets/fileSec doesn't exist",
        metsValidatorState.getMetsName(), metsValidatorState.isRootMets());

      ResultsUtils.addResults(results, new ReporterDetails(generator.getCSIPVersion(), message, true, true),
        ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP59_ID,
        ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP60_ID,
        ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP113_ID,
        ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP114_ID,
        ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP61_ID,
        ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP62_ID,
        ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP63_ID,
        ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP64_ID,
        ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP65_ID,
        ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP66_ID,
        ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP67_ID,
        ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP68_ID,
        ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP69_ID,
        ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP70_ID,
        ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP71_ID,
        ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP72_ID,
        ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP73_ID,
        ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP74_ID,
        ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP75_ID,
        ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP76_ID,
        ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP77_ID,
        ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP78_ID,
        ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP79_ID);
    }
    notifyObserversFinishModule(moduleName);
    return results;
  }

}
