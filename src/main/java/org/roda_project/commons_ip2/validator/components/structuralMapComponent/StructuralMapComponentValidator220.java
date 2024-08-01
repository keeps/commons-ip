package org.roda_project.commons_ip2.validator.components.structuralMapComponent;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.roda_project.commons_ip2.validator.components.MetsValidatorImpl;
import org.roda_project.commons_ip2.validator.constants220.Constants;
import org.roda_project.commons_ip2.validator.constants220.ConstantsCSIPspec;
import org.roda_project.commons_ip2.validator.reporter.ReporterDetails;
import org.roda_project.commons_ip2.validator.state.MetsValidatorState;
import org.roda_project.commons_ip2.validator.state.StructureValidatorState;
import org.roda_project.commons_ip2.validator.utils.Message;
import org.roda_project.commons_ip2.validator.utils.ResultsUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** {@author João Gomes <jgomes@keep.pt>}. */
public class StructuralMapComponentValidator220 extends MetsValidatorImpl {
  /**
   * {@link Logger}.
   */
  private static final Logger LOGGER = LoggerFactory.getLogger(StructuralMapComponentValidator220.class);

  /**
   * The module name {@link String}.
   */
  private final String moduleName;

  /**
   * Empty constructor of the component.
   */
  public StructuralMapComponentValidator220() {
    moduleName = Constants.CSIP_MODULE_NAME_6;
  }

  @Override
  public Map<String, ReporterDetails> validate(final StructureValidatorState structureValidatorState,
                                               final MetsValidatorState metsValidatorState) throws IOException {
    ReporterDetails csip;
    final Map<String, ReporterDetails> results = new HashMap<>();

    StructMapValidatorFactory structMapValidatorFactory = new StructMapValidatorFactory();
    StructMapValidator generator = structMapValidatorFactory.getGenerator("2.2.0");

    /* CSIP80 */
    notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP80_ID);
    ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP80_ID,
      generator.validateCSIP80(metsValidatorState).setSpecification(generator.getCSIPVersion()));

    if (ResultsUtils.isResultValid(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP80_ID)) {

      /* CSIP81 */
      notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP81_ID);
      ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP81_ID,
        generator.validateCSIP81(metsValidatorState).setSpecification(generator.getCSIPVersion()));

      /* CSIP82 */
      notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP82_ID);
      ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP82_ID,
        generator.validateCSIP82(metsValidatorState).setSpecification(generator.getCSIPVersion()));

      if (ResultsUtils.isResultValid(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP82_ID)) {
        /* CSIP83 */
        notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP83_ID);
        ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP83_ID,
          generator.validateCSIP83(metsValidatorState).setSpecification(generator.getCSIPVersion()));

        /* CSIP84 */
        notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP84_ID);
        ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP84_ID,
          generator.validateCSIP84(metsValidatorState).setSpecification(generator.getCSIPVersion()));

        if (ResultsUtils.isResultValid(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP84_ID)) {
          /* CSIP85 */
          notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP85_ID);
          ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP85_ID,
            generator.validateCSIP85(metsValidatorState).setSpecification(generator.getCSIPVersion()));



          /* CSIP88 */
          notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP88_ID);
          ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP88_ID,
            generator.validateCSIP88(metsValidatorState).setSpecification(generator.getCSIPVersion()));

          if (ResultsUtils.isResultValid(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP88_ID)) {
            /* CSIP89 */
            csip = new ReporterDetails(generator.getCSIPVersion(),
              Message.createErrorMessage("Skipped in %1$s because is validated later in CSIP106",
                metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
              true, true);
            ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP89_ID, csip);

            /* CSIP90 */
            notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP90_ID);
            ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP90_ID,
              new ReporterDetails().setSpecification(generator.getCSIPVersion()));

            /* CSIP91 */
            notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP91_ID);
            ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP91_ID,
              generator.validateCSIP91(metsValidatorState).setSpecification(generator.getCSIPVersion()));

            /* CSIP92 */
            notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP92_ID);
            ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP92_ID,
              generator.validateCSIP92(metsValidatorState).setSpecification(generator.getCSIPVersion()));

          } else {
            final String message = Message.createErrorMessage(
              "SKIPPED in %1$s because mets/structMap[@LABEL='CSIP']" + "/div/div[@LABEL='Metadata'] isn't valid",
              metsValidatorState.getMetsName(), metsValidatorState.isRootMets());

            ResultsUtils.addResults(results,
              new ReporterDetails(generator.getCSIPVersion(), message, true, true),
              ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP89_ID,
              ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP90_ID,
              ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP91_ID,
              ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP92_ID);
          }

          /* CSIP93 */
          notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP93_ID);
          ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP93_ID,
            generator.validateCSIP93(metsValidatorState).setSpecification(generator.getCSIPVersion()));

          if (ResultsUtils.isResultValid(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP93_ID)) {
            /* CSIP94 */
            csip = new ReporterDetails(generator.getCSIPVersion(),
              Message.createErrorMessage("Skipped in %1$s because is validated later in CSIP106",
                metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
              true, true);
            ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP94_ID, csip);

            /* CSIP95 */
            notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP95_ID);
            ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP95_ID,
              new ReporterDetails().setSpecification(generator.getCSIPVersion()));

            /* CSIP96 */
            notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP96_ID);
            ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP96_ID,
              generator.validateCSIP96(metsValidatorState).setSpecification(generator.getCSIPVersion()));

            /* CSIP116 */
            notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP116_ID);
            ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP116_ID,
              generator.validateCSIP116(metsValidatorState).setSpecification(generator.getCSIPVersion()));

          } else {
            final String message = Message.createErrorMessage(
              "SKIPPED in %1$s because mets/structMap[@LABEL='CSIP']" + "/div/div[@LABEL='Documentation'] isn't valid",
              metsValidatorState.getMetsName(), metsValidatorState.isRootMets());

            ResultsUtils.addResults(results,
              new ReporterDetails(generator.getCSIPVersion(), message, true, true),
              ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP94_ID,
              ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP95_ID,
              ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP96_ID,
              ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP116_ID);
          }

          /* CSIP97 */
          notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP97_ID);
          ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP97_ID,
            generator.validateCSIP97(metsValidatorState).setSpecification(generator.getCSIPVersion()));

          if (ResultsUtils.isResultValid(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP97_ID)) {
            /* CSIP98 */
            csip = new ReporterDetails(generator.getCSIPVersion(),
              Message.createErrorMessage("Skipped in %1$s because is validated later in CSIP106",
                metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
              true, true);
            ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP98_ID, csip);

            /* CSIP99 */
            notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP99_ID);
            ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP99_ID,
              new ReporterDetails().setSpecification(generator.getCSIPVersion()));

            /* CSIP100 */
            notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP100_ID);
            ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP100_ID,
              generator.validateCSIP100(metsValidatorState).setSpecification(generator.getCSIPVersion()));

            /* CSIP118 */
            notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP118_ID);
            ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP118_ID,
              generator.validateCSIP118(metsValidatorState).setSpecification(generator.getCSIPVersion()));

          } else {
            final String message = Message.createErrorMessage(
              "SKIPPED in %1$s because mets/structMap[@LABEL='CSIP']" + "/div/div[@LABEL='Schemas'] isn't valid",
              metsValidatorState.getMetsName(), metsValidatorState.isRootMets());

            ResultsUtils.addResults(results,
              new ReporterDetails(generator.getCSIPVersion(), message, true, true),
              ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP98_ID,
              ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP99_ID,
              ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP100_ID,
              ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP118_ID);
          }

          /* CSIP101 */
          notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP101_ID);
          ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP101_ID,
            generator.validateCSIP101(metsValidatorState).setSpecification(generator.getCSIPVersion()));

          if (ResultsUtils.isResultValid(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP101_ID)) {
            /* CSIP102 */
            csip = new ReporterDetails(generator.getCSIPVersion(),
              Message.createErrorMessage("Skipped in %1$s because is validated later in CSIP106",
                metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
              true, true);
            ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP102_ID, csip);

            /* CSIP103 */
            notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP103_ID);
            ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP103_ID,
              generator.validateCSIP103(structureValidatorState, metsValidatorState)
                .setSpecification(generator.getCSIPVersion()));

            /* CSIP104 */
            notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP104_ID);
            ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP104_ID,
              generator.validateCSIP104(metsValidatorState).setSpecification(generator.getCSIPVersion()));

            /* CSIP119 */
            notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP119_ID);
            ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP119_ID,
              generator.validateCSIP119(metsValidatorState).setSpecification(generator.getCSIPVersion()));

          } else {
            final String message = Message.createErrorMessage(
              "SKIPPED in %1$s because mets/structMap[@LABEL='CSIP']"
                + "/div/div[@LABEL='Representations'] isn't valid",
              metsValidatorState.getMetsName(), metsValidatorState.isRootMets());

            ResultsUtils.addResults(results,
              new ReporterDetails(generator.getCSIPVersion(), message, true, true),
              ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP102_ID,
              ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP103_ID,
              ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP104_ID,
              ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP119_ID);
          }

          /* CSIP105 */
          notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP105_ID);
          ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP105_ID,
            generator.validateCSIP105(metsValidatorState).setSpecification(generator.getCSIPVersion()));

          /* CSIP106 */
          notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP106_ID);
          ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP106_ID,
            generator.validateCSIP106(metsValidatorState).setSpecification(generator.getCSIPVersion()));

          /* CSIP107 */
          notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP107_ID);
          ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP107_ID,
            generator.validateCSIP107(structureValidatorState, metsValidatorState)
              .setSpecification(generator.getCSIPVersion()));

          /* CSIP108 */
          notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP108_ID);
          ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP108_ID,
            generator.validateCSIP108(metsValidatorState).setSpecification(generator.getCSIPVersion()));

          /* CSIP109 */
          notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP109_ID);
          ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP109_ID,
            generator.validateCSIP109(metsValidatorState).setSpecification(generator.getCSIPVersion()));

          /* CSIP110 */
          notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP110_ID);
          ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP110_ID,
            generator.validateCSIP110(structureValidatorState, metsValidatorState)
              .setSpecification(generator.getCSIPVersion()));

          /* CSIP111 */
          notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP111_ID);
          ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP111_ID,
            generator.validateCSIP111(structureValidatorState, metsValidatorState)
              .setSpecification(generator.getCSIPVersion()));

          /* CSIP112 */
          notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP112_ID);
          ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP112_ID,
            generator.validateCSIP112(metsValidatorState).setSpecification(generator.getCSIPVersion()));

        } else {
          final String message = Message.createErrorMessage(
            "SKIPPED in %1$s because mets/structMap[@LABEL='CSIP']/div isn't valid", metsValidatorState.getMetsName(),
            metsValidatorState.isRootMets());

          ResultsUtils.addResults(results,
            new ReporterDetails(generator.getCSIPVersion(), message, true, true),
            ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP85_ID,
            ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP88_ID,
            ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP89_ID,
            ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP90_ID,
            ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP91_ID,
            ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP92_ID,
            ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP93_ID,
            ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP94_ID,
            ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP95_ID,
            ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP96_ID,
            ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP116_ID,
            ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP97_ID,
            ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP98_ID,
            ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP99_ID,
            ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP100_ID,
            ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP101_ID,
            ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP102_ID,
            ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP103_ID,
            ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP104_ID,
            ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP119_ID,
            ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP105_ID,
            ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP106_ID,
            ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP107_ID,
            ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP108_ID,
            ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP109_ID,
            ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP110_ID,
            ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP111_ID,
            ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP112_ID);
        }
      } else {
        final String message = Message.createErrorMessage(
          "SKIPPED in %1$s because mets/structMap[@LABEL='CSIP'] isn't valid", metsValidatorState.getMetsName(),
          metsValidatorState.isRootMets());

        ResultsUtils.addResults(results,
          new ReporterDetails(generator.getCSIPVersion(), message, true, true),
          ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP83_ID,
          ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP84_ID,
          ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP85_ID,
          ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP88_ID,
          ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP89_ID,
          ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP90_ID,
          ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP91_ID,
          ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP92_ID,
          ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP93_ID,
          ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP94_ID,
          ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP95_ID,
          ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP96_ID,
          ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP116_ID,
          ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP97_ID,
          ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP98_ID,
          ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP99_ID,
          ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP100_ID,
          ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP101_ID,
          ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP102_ID,
          ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP103_ID,
          ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP104_ID,
          ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP119_ID,
          ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP105_ID,
          ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP106_ID,
          ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP107_ID,
          ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP108_ID,
          ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP109_ID,
          ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP110_ID,
          ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP111_ID,
          ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP112_ID);
      }
    } else {
      final String message = Message.createErrorMessage("SKIPPED in %1$s because mets/structMap doesn't exist",
        metsValidatorState.getMetsName(), metsValidatorState.isRootMets());

      ResultsUtils.addResults(results,
        new ReporterDetails(generator.getCSIPVersion(), message, true, true),
        ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP81_ID,
        ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP82_ID,
        ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP83_ID,
        ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP84_ID,
        ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP85_ID,
        ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP88_ID,
        ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP89_ID,
        ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP90_ID,
        ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP91_ID,
        ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP92_ID,
        ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP93_ID,
        ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP94_ID,
        ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP95_ID,
        ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP96_ID,
        ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP116_ID,
        ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP97_ID,
        ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP98_ID,
        ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP99_ID,
        ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP100_ID,
        ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP101_ID,
        ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP102_ID,
        ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP103_ID,
        ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP104_ID,
        ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP119_ID,
        ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP105_ID,
        ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP106_ID,
        ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP107_ID,
        ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP108_ID,
        ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP109_ID,
        ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP110_ID,
        ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP111_ID,
        ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP112_ID);
    }
    notifyObserversFinishModule(moduleName);
    return results;
  }
}
