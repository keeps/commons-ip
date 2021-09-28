package org.roda_project.commons_ip2.validator.component.structuralMapComponent;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLDecoder;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.roda_project.commons_ip2.mets_v1_12.beans.AmdSecType;
import org.roda_project.commons_ip2.mets_v1_12.beans.DivType;
import org.roda_project.commons_ip2.mets_v1_12.beans.MdSecType;
import org.roda_project.commons_ip2.mets_v1_12.beans.MetsType;
import org.roda_project.commons_ip2.mets_v1_12.beans.StructMapType;
import org.roda_project.commons_ip2.validator.common.MetsParser;
import org.roda_project.commons_ip2.validator.component.ValidatorComponentImpl;
import org.roda_project.commons_ip2.validator.constants.Constants;
import org.roda_project.commons_ip2.validator.constants.ConstantsCSIPspec;
import org.roda_project.commons_ip2.validator.handlers.MetsHandler;
import org.roda_project.commons_ip2.validator.reporter.ReporterDetails;
import org.roda_project.commons_ip2.validator.utils.Message;
import org.roda_project.commons_ip2.validator.utils.ResultsUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author João Gomes <jgomes@keep.pt>
 */
public class StructuralMapComponentValidator extends ValidatorComponentImpl {
  private static final Logger LOGGER = LoggerFactory.getLogger(StructuralMapComponentValidator.class);

  private final String moduleName;

  public StructuralMapComponentValidator() {
    moduleName = Constants.CSIP_MODULE_NAME_6;
  }

  @Override
  public Map<String, ReporterDetails> validate() throws IOException {
    ReporterDetails csip;
    Map<String, ReporterDetails> results = new HashMap<>();

    /* CSIP80 */
    notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP80_ID);
    ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP80_ID,
      validateCSIP80().setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION));

    if (ResultsUtils.isResultValid(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP80_ID)) {

      /* CSIP81 */
      notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP81_ID);
      ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP81_ID,
        validateCSIP81().setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION));

      /* CSIP82 */
      notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP82_ID);
      ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP82_ID,
        validateCSIP82().setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION));

      if (ResultsUtils.isResultValid(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP82_ID)) {
        /* CSIP83 */
        notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP83_ID);
        ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP83_ID,
          validateCSIP83().setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION));

        /* CSIP84 */
        notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP84_ID);
        ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP84_ID,
          validateCSIP84().setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION));

        if (ResultsUtils.isResultValid(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP84_ID)) {
          /* CSIP85 */
          notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP85_ID);
          ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP85_ID,
            validateCSIP85().setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION));

          /* CSIP86 */
          notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP86_ID);
          ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP86_ID,
            validateCSIP86().setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION));

          /* CSIP88 */
          notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP88_ID);
          ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP88_ID,
            validateCSIP88().setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION));

          if (ResultsUtils.isResultValid(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP88_ID)) {
            /* CSIP89 */
            csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, Message.createErrorMessage(
              "Skipped in %1$s because is validated later in CSIP106", metsName, isRootMets()), true, true);
            ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP89_ID, csip);

            /* CSIP90 */
            notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP90_ID);
            ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP90_ID,
              validateCSIP90().setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION));

            /* CSIP91 */
            notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP91_ID);
            ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP91_ID,
              validateCSIP91().setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION));

            /* CSIP92 */
            notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP92_ID);
            ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP92_ID,
              validateCSIP92().setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION));

          } else {
            String message = Message.createErrorMessage(
              "SKIPPED in %1$s because mets/structMap[@LABEL='CSIP']/div/div[@LABEL='Metadata'] isn't valid", metsName,
              isRootMets());

            ResultsUtils.addResults(results,
              new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, message, true, true),
              ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP89_ID,
              ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP90_ID,
              ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP91_ID,
              ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP92_ID);
          }

          /* CSIP93 */
          notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP93_ID);
          ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP93_ID,
            validateCSIP93().setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION));

          if (ResultsUtils.isResultValid(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP93_ID)) {
            /* CSIP94 */
            csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, Message.createErrorMessage(
              "Skipped in %1$s because is validated later in CSIP106", metsName, isRootMets()), true, true);
            ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP94_ID, csip);

            /* CSIP95 */
            notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP95_ID);
            ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP95_ID,
              validateCSIP95().setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION));

            /* CSIP96 */
            notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP96_ID);
            ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP96_ID,
              validateCSIP96().setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION));

            /* CSIP116 */
            notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP116_ID);
            ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP116_ID,
              validateCSIP116().setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION));

          } else {
            String message = Message.createErrorMessage(
              "SKIPPED in %1$s because mets/structMap[@LABEL='CSIP']/div/div[@LABEL='Documentation'] isn't valid",
              metsName, isRootMets());

            ResultsUtils.addResults(results,
              new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, message, true, true),
              ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP94_ID,
              ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP95_ID,
              ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP96_ID,
              ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP116_ID);
          }

          /* CSIP97 */
          notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP97_ID);
          ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP97_ID,
            validateCSIP97().setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION));

          if (ResultsUtils.isResultValid(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP97_ID)) {
            /* CSIP98 */
            csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, Message.createErrorMessage(
              "Skipped in %1$s because is validated later in CSIP106", metsName, isRootMets()), true, true);
            ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP98_ID, csip);

            /* CSIP99 */
            notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP99_ID);
            ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP99_ID,
              validateCSIP99().setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION));

            /* CSIP100 */
            notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP100_ID);
            ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP100_ID,
              validateCSIP100().setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION));

            /* CSIP118 */
            notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP118_ID);
            ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP118_ID,
              validateCSIP118().setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION));

          } else {
            String message = Message.createErrorMessage(
              "SKIPPED in %1$s because mets/structMap[@LABEL='CSIP']/div/div[@LABEL='Schemas'] isn't valid", metsName,
              isRootMets());

            ResultsUtils.addResults(results,
              new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, message, true, true),
              ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP98_ID,
              ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP99_ID,
              ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP100_ID,
              ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP118_ID);
          }

          /* CSIP101 */
          notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP101_ID);
          ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP101_ID,
            validateCSIP101().setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION));

          if (ResultsUtils.isResultValid(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP101_ID)) {
            /* CSIP102 */
            csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, Message.createErrorMessage(
              "Skipped in %1$s because is validated later in CSIP106", metsName, isRootMets()), true, true);
            ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP102_ID, csip);

            /* CSIP103 */
            notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP103_ID);
            ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP103_ID,
              validateCSIP103().setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION));

            /* CSIP104 */
            notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP104_ID);
            ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP104_ID,
              validateCSIP104().setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION));

            /* CSIP119 */
            notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP119_ID);
            ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP119_ID,
              validateCSIP119().setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION));

          } else {
            String message = Message.createErrorMessage(
              "SKIPPED in %1$s because mets/structMap[@LABEL='CSIP']/div/div[@LABEL='Representations'] isn't valid",
              metsName, isRootMets());

            ResultsUtils.addResults(results,
              new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, message, true, true),
              ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP102_ID,
              ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP103_ID,
              ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP104_ID,
              ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP119_ID);
          }

          /* CSIP105 */
          notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP105_ID);
          ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP105_ID,
            validateCSIP105().setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION));

          /* CSIP106 */
          notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP106_ID);
          ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP106_ID,
            validateCSIP106().setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION));

          /* CSIP107 */
          notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP107_ID);
          ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP107_ID,
            validateCSIP107().setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION));

          /* CSIP108 */
          notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP108_ID);
          ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP108_ID,
            validateCSIP108().setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION));

          /* CSIP109 */
          notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP109_ID);
          ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP109_ID,
            validateCSIP109().setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION));

          /* CSIP110 */
          notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP110_ID);
          ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP110_ID,
            validateCSIP110().setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION));

          /* CSIP111 */
          notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP111_ID);
          ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP111_ID,
            validateCSIP111().setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION));

          /* CSIP112 */
          notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP112_ID);
          ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP112_ID,
            validateCSIP112().setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION));

        } else {
          String message = Message.createErrorMessage(
            "SKIPPED in %1$s because mets/structMap[@LABEL='CSIP']/div isn't valid", metsName, isRootMets());

          ResultsUtils.addResults(results,
            new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, message, true, true),
            ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP85_ID,
            ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP86_ID,
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
        String message = Message.createErrorMessage("SKIPPED in %1$s because mets/structMap[@LABEL='CSIP'] isn't valid",
          metsName, isRootMets());

        ResultsUtils.addResults(results,
          new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, message, true, true),
          ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP83_ID,
          ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP84_ID,
          ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP85_ID,
          ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP86_ID,
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
      String message = Message.createErrorMessage("SKIPPED in %1$s because mets/structMap doesn't exist", metsName,
        isRootMets());

      ResultsUtils.addResults(results,
        new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, message, true, true),
        ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP81_ID,
        ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP82_ID,
        ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP83_ID,
        ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP84_ID,
        ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP85_ID,
        ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP86_ID,
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

  /*
   * mets/structMap The structural map <structMap> element is the only mandatory
   * element in the METS. The <structMap> in the CSIP describes the highest
   * logical structure of the IP. Each METS file must include ONE structural map
   * <structMap> element used exactly as described here. Institutions can add
   * their own additional custom structural maps as separate <structMap> sections.
   */
  private ReporterDetails validateCSIP80() {
    List<StructMapType> structMap = mets.getStructMap();
    if (structMap == null) {
      return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
        Message.createErrorMessage("mets/structMap in %1$s can't be null", metsName, isRootMets()), false, false);
    } else {
      int numberOfCSIPstructMaps = 0;
      for (StructMapType struct : structMap) {
        if (struct.getLABEL().equals("CSIP")) {
          numberOfCSIPstructMaps++;
        }
      }
      if (numberOfCSIPstructMaps != 1) {
        String message = numberOfCSIPstructMaps == 0
          ? "Must have one structMap with the mets/structMap[@LABEL='CSIP'] in %1$s doens't appear mets/structMap[@LABEL='CSIP']."
          : "Only one structMap with the mets/structMap/@LABEL value CSIP is allowed. See %1$s";
        return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
          Message.createErrorMessage(
            message, metsName,
            isRootMets()),
          false, false);
      }
    }
    return new ReporterDetails();
  }

  /*
   * mets/structMap[@TYPE='PHYSICAL'] The mets/structMap/@TYPE attribute must take
   * the value “PHYSICAL” from the vocabulary. See also: Structural map typing
   */
  private ReporterDetails validateCSIP81() {
    List<StructMapType> structMap = mets.getStructMap();
    if (structMap != null) {
      for (StructMapType struct : structMap) {
        String type = struct.getTYPE();
        String label = struct.getLABEL();
        if (type == null) {
          return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, Message.createErrorMessage(
            "mets/structMap[@TYPE='PHYSICAL'] in %1$s can't be null", metsName, isRootMets()), false, false);
        } else {
          if (label.equals("CSIP") && !type.equals("PHYSICAL")) {
            return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, Message.createErrorMessage(
              "mets/structMap[@TYPE='PHYSICAL'] value in %1$s must be PHYSICAL", metsName, isRootMets()), false, false);
          }
        }
      }
    }
    return new ReporterDetails();
  }

  /*
   * mets/structMap[@LABEL='CSIP'] The mets/structMap/@LABEL attribute value is
   * set to “CSIP” from the vocabulary. See also: Structural map label
   */
  private ReporterDetails validateCSIP82() {
    List<StructMapType> structMap = mets.getStructMap();
    if (structMap != null) {
      for (StructMapType struct : structMap) {
        String label = struct.getLABEL();
        if (label == null) {
          return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
            Message.createErrorMessage("mets/structMap[@LABEL='CSIP'] in %1$s can't be null", metsName, isRootMets()),
            false, false);
        } else {
          if (!label.equals("CSIP")) {
            return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, Message.createErrorMessage(
              "mets/structMap[@LABEL='CSIP'] value in %1$s must be CSIP", metsName, isRootMets()), false, false);
          }
        }
      }
    }
    return new ReporterDetails();
  }

  /*
   * mets/structMap[@LABEL='CSIP']/@ID An xml:id identifier for the structural
   * description (structMap) used for internal package references. It must be
   * unique within the package.
   */
  private ReporterDetails validateCSIP83() {
    List<StructMapType> structMap = mets.getStructMap();
    if (structMap != null) {
      for (StructMapType struct : structMap) {
        String id = struct.getID();
        if (id == null) {
          return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, Message.createErrorMessage(
            "mets/structMap[@LABEL='CSIP']/@ID in %1$s can't be null", metsName, isRootMets()), false, false);
        } else {
          if (checkId(id)) {
            StringBuilder message = new StringBuilder();
            message.append("Value ").append(id)
              .append(" in %1$s for mets/structMap[@LABEL='CSIP']/@ID isn't unique in the package");
            return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
              Message.createErrorMessage(message.toString(), metsName, isRootMets()), false, false);
          } else {
            addId(id);
          }
        }
      }
    }
    return new ReporterDetails();
  }

  /*
   * mets/structMap[@LABEL='CSIP']/div The structural map comprises a single
   * division.
   */
  private ReporterDetails validateCSIP84() {
    List<StructMapType> structMap = mets.getStructMap();
    if (structMap != null) {
      for (StructMapType struct : structMap) {
        DivType div = struct.getDiv();
        if (div == null) {
          return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
            Message.createErrorMessage("Must have a single division mets/structMap[@LABEL='CSIP']/div in %1$s",
              metsName, isRootMets()),
            false, false);
        }
      }
    }
    return new ReporterDetails();
  }

  /*
   * mets/structMap[@LABEL='CSIP']/div/@ID Mandatory, xml:id identifier must be
   * unique within the package.
   */
  private ReporterDetails validateCSIP85() {
    List<StructMapType> structMap = mets.getStructMap();
    if (structMap != null) {
      for (StructMapType struct : structMap) {
        DivType div = struct.getDiv();
        if (div != null) {
          String id = div.getID();
          if (id == null) {
            return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, Message.createErrorMessage(
              "mets/structMap[@LABEL='CSIP']/div/@ID %1$s can't be null", metsName, isRootMets()), false, false);
          } else {
            if (checkId(id)) {
              StringBuilder message = new StringBuilder();
              message.append("Value ").append(id)
                .append(" %1$s for mets/structMap[@LABEL='CSIP']/div/@ID isn't unique in the package");
              return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
                Message.createErrorMessage(message.toString(), metsName, isRootMets()), false, false);
            } else {
              addId(id);
            }
          }
        }
      }
    }
    return new ReporterDetails();
  }

  /*
   * mets/structMap[@LABEL='CSIP']/div/@LABEL The package’s top-level structural
   * division <div> element’s @LABEL attribute value must be identical to the
   * package identifier, i.e. the same value as the mets/@OBJID attribute.
   */
  private ReporterDetails validateCSIP86() {
    List<StructMapType> structMap = mets.getStructMap();
    String objid = mets.getOBJID();
    if (structMap != null) {
      for (StructMapType struct : structMap) {
        DivType div = struct.getDiv();
        if (div != null) {
          String label = div.getLABEL();
          if (label == null) {
            return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, Message.createErrorMessage(
              "mets/structMap[@LABEL='CSIP']/div/@LABEL in %1$s can't be null", metsName, isRootMets()), false, false);
          } else {
            if (!label.equals(objid)) {
              return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
                Message.createErrorMessage(
                  "mets/structMap[@LABEL='CSIP']/div/@LABEL value in %1$s must be equal to the package identifier",
                  metsName, isRootMets()),
                false, false);
            }
          }
        }
      }
    }
    return new ReporterDetails();
  }

  /*
   * mets/structMap[@LABEL='CSIP']/div/div[@LABEL='Metadata'] The metadata
   * referenced in the administrative and/or descriptive metadata section is
   * described in the structural map with one sub division. When the transfer
   * consists of only administrative and/or descriptive metadata this is the only
   * sub division that occurs.
   */
  private ReporterDetails validateCSIP88() {
    List<StructMapType> structMap = mets.getStructMap();
    List<MdSecType> dmdSec = mets.getDmdSec();
    List<AmdSecType> amdSec = mets.getAmdSec();

    if (dmdSec == null && amdSec == null) {
      for (StructMapType struct : structMap) {
        DivType div = struct.getDiv();
        if (div != null) {
          List<DivType> divs = div.getDiv();
          for (DivType d : divs) {
            if (d.getLABEL().equals("Metadata")) {
              return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
                Message.createErrorMessage(
                  "The section of administrative or descriptive metadata remains to be added in %1$s", metsName,
                  isRootMets()),
                false, false);
            }
          }
        }
      }
    } else {
      boolean found = false;
      for (StructMapType struct : structMap) {
        DivType div = struct.getDiv();
        if (div != null) {
          List<DivType> divs = div.getDiv();
          for (DivType d : divs) {
            if (d.getLABEL().equals("Metadata")) {
              found = true;
              break;
            }
          }
        }
      }
      if (!found) {
        return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
          Message.createErrorMessage("mets/structMap[@LABEL='CSIP']/div/div[@LABEL='Metadata'] in %1$s not found",
            metsName, isRootMets()),
          false, false);
      }
    }
    return new ReporterDetails();
  }

  /*
   * Metadata division identifier
   * mets/structMap[@LABEL='CSIP']/div/div[@LABEL='Metadata']/@ID Mandatory,
   * xml:id identifier must be unique within the package.
   */
  private ReporterDetails validateCSIP89() {
    List<StructMapType> structMap = mets.getStructMap();
    if (structMap != null) {
      for (StructMapType struct : structMap) {
        DivType div = struct.getDiv();
        if (div != null) {
          List<DivType> divs = div.getDiv();
          for (DivType d : divs) {
            if (d.getLABEL().equals("Metadata")) {
              String id = d.getID();
              if (id == null) {
                return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
                  Message.createErrorMessage(
                    "mets/structMap[@LABEL='CSIP']/div/div[@LABEL='Metadata']/@ID in %1$s can't be null", metsName,
                    isRootMets()),
                  false, false);
              } else {
                if (checkId(id)) {
                  StringBuilder message = new StringBuilder();
                  message.append("Value ").append(id).append(
                    " in %1$s for mets/structMap[@LABEL='CSIP']/div/div[@LABEL='Metadata']/@ID isn't unique in the package");
                  return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
                    Message.createErrorMessage(message.toString(), metsName, isRootMets()), false, false);
                } else {
                  addId(id);
                }
              }
            }
          }
        }
      }
    }
    return new ReporterDetails();
  }

  /*
   * mets/structMap[@LABEL='CSIP']/div/div[@LABEL='Metadata'] The metadata
   * division <div> element’s @LABEL attribute value must be “Metadata”. See also:
   * File group names
   */
  private ReporterDetails validateCSIP90() {
    List<StructMapType> structMap = mets.getStructMap();
    boolean found = false;
    if (structMap != null) {
      for (StructMapType struct : structMap) {
        DivType div = struct.getDiv();
        if (div != null) {
          List<DivType> divs = div.getDiv();
          for (DivType d : divs) {
            if (d.getLABEL().equals("Metadata")) {
              found = true;
            }
          }
          if (!found) {
            return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
              Message.createErrorMessage("mets/structMap[@LABEL='CSIP']/div/div[@LABEL='Metadata'] in %1$s not found",
                metsName, isRootMets()),
              false, false);
          }
        }
      }
    }
    return new ReporterDetails();
  }

  /*
   * mets/structMap[@LABEL='CSIP']/div/div[@LABEL='Metadata']/@ADMID When there is
   * administrative metadata and the amdSec is present, all administrative
   * metadata MUST be referenced via the administrative sections different
   * identifiers. All of the <amdSec> identifiers are listed in a single @ADMID
   * using spaces as delimiters.
   */
  private ReporterDetails validateCSIP91() {
    List<StructMapType> structMap = mets.getStructMap();
    List<AmdSecType> amdSec = mets.getAmdSec();
    boolean found = false;
    List<String> amdSecIDs = new ArrayList<>();
    if (amdSec.size() != 0) {
      for (AmdSecType amdSecType : amdSec) {
        List<MdSecType> digiProv = amdSecType.getDigiprovMD();
        for (MdSecType mdSecType : digiProv) {
          amdSecIDs.add(mdSecType.getID());
        }
      }
    }
    if (structMap != null) {
      for (StructMapType struct : structMap) {
        DivType div = struct.getDiv();
        if (div != null) {
          List<DivType> divs = div.getDiv();
          for (DivType d : divs) {
            if (d.getLABEL().matches("Metadata.*?")) {
              List<Object> admids = d.getADMID();
              if (admids != null && admids.size() != 0) {
                for (Object o : admids) {
                  String admid = ((MdSecType) o).getID();
                  if (!amdSecIDs.contains(admid)) {
                    StringBuilder message = new StringBuilder();
                    message.append("mets/structMap[@LABEL='CSIP']/div/div[@LABEL='Metadata']/@ADMID (").append(admid)
                      .append(") doesn't match with any mets/amdSec/digiprovMD/@ID in %1$s");
                    return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
                      Message.createErrorMessage(message.toString(), metsName, isRootMets()), false, false);
                  }
                }
              }
            }
          }
        }
      }
    }
    return new ReporterDetails();
  }

  /*
   * Metadata division descriptive metadata referencing
   * mets/structMap[@LABEL='CSIP']/div/div[@LABEL='Metadata']/@DMDID When there
   * are descriptive metadata and one or more dmdSec is present, all descriptive
   * metadata MUST be referenced via the descriptive section identifiers. Every
   * <dmdSec> identifier is listed in a single @DMDID attribute using spaces as
   * delimiters.
   */
  private ReporterDetails validateCSIP92() {
    List<StructMapType> structMap = mets.getStructMap();
    List<MdSecType> dmdSec = mets.getDmdSec();
    List<String> dmdSecIDs = new ArrayList<>();
    if (dmdSec.size() != 0) {
      for (MdSecType md : dmdSec) {
        dmdSecIDs.add(md.getID());
      }
    }
    boolean found = false;
    if (structMap != null) {
      for (StructMapType struct : structMap) {
        DivType div = struct.getDiv();
        if (div != null) {
          List<DivType> divs = div.getDiv();
          for (DivType d : divs) {
            if (d.getLABEL().matches("Metadata.*?")) {
              List<Object> dmdids = d.getDMDID();
              if (dmdids.size() > 0) {
                for (Object o : dmdids) {
                  String dmid = ((MdSecType) o).getID();
                  if (!dmdSecIDs.contains(dmid)) {
                    StringBuilder message = new StringBuilder();
                    message.append("mets/structMap[@LABEL='CSIP']/div/div[@LABEL='Metadata']/@DMDID (").append(dmid)
                      .append(") not match with any mets/dmdSec/mdRef/@ID in %1$s");
                    return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
                      Message.createErrorMessage(message.toString(), metsName, isRootMets()), false, false);
                  }
                }
              }
            }
          }
        }
      }
    }
    return new ReporterDetails();
  }

  /*
   * mets/structMap[@LABEL='CSIP']/div/div[@LABEL='Documentation'] The
   * documentation referenced in the file section file groups is described in the
   * structural map with one sub division.
   */
  private ReporterDetails validateCSIP93() {
    boolean found = false;
    List<StructMapType> structMap = mets.getStructMap();
    MetsType.FileSec fileSec = mets.getFileSec();
    if (fileSec != null) {
      List<MetsType.FileSec.FileGrp> fileGrps = fileSec.getFileGrp();
      boolean existDocumentation = false;
      for (MetsType.FileSec.FileGrp fileGrp : fileGrps) {
        if (fileGrp.getUSE().equals("Documentation")) {
          existDocumentation = true;
          break;
        }
      }
      for (StructMapType struct : structMap) {
        DivType div = struct.getDiv();
        if (div != null) {
          List<DivType> divs = div.getDiv();
          for (DivType d : divs) {
            if (d.getLABEL().equals("Documentation")) {
              found = true;
              break;
            }
          }
          if (existDocumentation && !found) {
            return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
              Message.createErrorMessage(
                "mets/structMap[@LABEL='CSIP']/div/div[@LABEL='Documentation'] must be added in %1$s", metsName,
                isRootMets()),
              false, false);
          } else {
            if (!existDocumentation && found) {
              return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
                Message.createErrorMessage("mets/fileSec/fileGrp[@USE='Documentation'] must be added in %1$s", metsName,
                  isRootMets()),
                false, false);
            }
          }
        }

      }
    }
    return new ReporterDetails();
  }

  /*
   * Metadata division identifier
   * mets/structMap[@LABEL='CSIP']/div/div[@LABEL='Documentation']/@ID Mandatory,
   * xml:id identifier must be unique within the package.
   */
  private ReporterDetails validateCSIP94() {
    List<StructMapType> structMap = mets.getStructMap();
    if (structMap != null) {
      for (StructMapType struct : structMap) {
        DivType div = struct.getDiv();
        if (div != null) {
          List<DivType> divs = div.getDiv();
          for (DivType d : divs) {
            if (d.getLABEL().equals("Documentation")) {
              String id = d.getID();
              if (id == null) {
                return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
                  Message.createErrorMessage(
                    "mets/structMap[@LABEL='CSIP']/div/div[@LABEL='Documentation']/@ID in %1$s can't be null", metsName,
                    isRootMets()),
                  false, false);
              } else {
                if (checkId(id)) {
                  StringBuilder message = new StringBuilder();
                  message.append("Value ").append(id).append(
                    " in %1$s for mets/structMap[@LABEL='CSIP']/div/div[@LABEL='Documentation']/@ID isn't unique in the package");
                  return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
                    Message.createErrorMessage(message.toString(), metsName, isRootMets()), false, false);
                } else {
                  addId(id);
                }
              }
            }
          }
        }
      }
    }
    return new ReporterDetails();
  }

  /*
   * mets/structMap[@LABEL='CSIP']/div/div[@LABEL='Documentation'] The
   * documentation division <div> element in the package uses the value
   * “Documentation” from the vocabulary as the value for the @LABEL attribute.
   * See also: File group names
   */
  private ReporterDetails validateCSIP95() {
    boolean found = false;
    List<StructMapType> structMap = mets.getStructMap();
    if (structMap != null) {
      for (StructMapType struct : structMap) {
        DivType div = struct.getDiv();
        if (div != null) {
          List<DivType> divs = div.getDiv();
          for (DivType d : divs) {
            if (d.getLABEL().equals("Documentation")) {
              found = true;
              break;
            }
          }
          if (found) {
            break;
          }
        }
      }
    }
    if (!found) {
      return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
        Message.createErrorMessage("mets/structMap[@LABEL='CSIP']/div/div[@LABEL='Documentation'] in %1$s not found",
          metsName, isRootMets()),
        false, false);
    }
    return new ReporterDetails();
  }

  /*
   * mets/structMap[@LABEL='CSIP']/div/div[@LABEL='Documentation']/fptr All file
   * groups containing documentation described in the package are referenced via
   * the relevant file group identifiers. There MUST be one file group reference
   * per <fptr> element.
   */
  private ReporterDetails validateCSIP96() {
    List<StructMapType> structMap = mets.getStructMap();
    List<MetsType.FileSec.FileGrp> fileGrps = mets.getFileSec().getFileGrp();
    int fileGrpDocumentation = 0;
    int structDocumentation = 0;
    if (structMap != null) {
      if (fileGrps != null && fileGrps.size() != 0) {
        for (MetsType.FileSec.FileGrp fileGrp : fileGrps) {
          if (fileGrp.getUSE().equals("Documentation")) {
            if (!fileGrp.getFile().isEmpty()) {
              fileGrpDocumentation++;
            }
          }
        }
      }
      for (StructMapType struct : structMap) {
        DivType div = struct.getDiv();
        if (div != null) {
          List<DivType> divs = div.getDiv();
          for (DivType d : divs) {
            if (d.getLABEL().equals("Documentation")) {
              List<DivType.Fptr> ftprs = d.getFptr();
              structDocumentation = ftprs.size();
            }
          }
        }
      }

      if (fileGrpDocumentation != structDocumentation) {
        return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, Message.createErrorMessage(
          "In %1$s must be one file group reference per mets/structMap[@LABEL='CSIP']/div/div[@LABEL='Documentation']/fptr ",
          metsName, isRootMets()), false, false);
      }
    }
    return new ReporterDetails();
  }

  /*
   * mets/structMap[@LABEL='CSIP']/div/div[@LABEL='Documentation']/fptr/@FILEID A
   * reference, by ID, to the “Documentation” file group. Related to the
   * requirements CSIP60 which describes the “Documentation” file group and CSIP65
   * which describes the file group identifier.
   */
  private ReporterDetails validateCSIP116() {
    List<StructMapType> structMap = mets.getStructMap();
    List<MetsType.FileSec.FileGrp> fileGrps = mets.getFileSec().getFileGrp();
    boolean found = false;
    if (structMap != null) {
      for (StructMapType struct : structMap) {
        DivType div = struct.getDiv();
        if (div != null) {
          List<DivType> divs = div.getDiv();
          for (DivType d : divs) {
            if (d.getLABEL().equals("Documentation")) {
              List<DivType.Fptr> ftprs = d.getFptr();
              if (ftprs != null && ftprs.size() > 0) {
                for (DivType.Fptr fptr : ftprs) {
                  String fileid = ((MetsType.FileSec.FileGrp) fptr.getFILEID()).getID();
                  for (MetsType.FileSec.FileGrp fileGrp : fileGrps) {
                    if (fileGrp.getUSE().equals("Documentation")) {
                      String id = fileGrp.getID();
                      if (id.equals(fileid)) {
                        found = true;
                      }
                    }
                  }
                  if (!found) {
                    StringBuilder message = new StringBuilder();
                    message.append("Value ").append(fileid).append(
                      " in %1$s for mets/structMap[@LABEL='CSIP']/div/div[@LABEL='Documentation']/fptr/@FILEID doesn't match with any mets/fileSec/fileGrp/@ID  ");
                    return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
                      Message.createErrorMessage(message.toString(), metsName, isRootMets()), false, false);
                  }
                }
              }
            }
          }
        }
      }
    }
    return new ReporterDetails();
  }

  /*
   * mets/structMap[@LABEL='CSIP']/div/div[@LABEL='Schemas'] The schemas
   * referenced in the file section file groups are described in the structural
   * map within a single sub-division.
   */
  private ReporterDetails validateCSIP97() {
    boolean found = false;
    List<StructMapType> structMap = mets.getStructMap();
    MetsType.FileSec fileSec = mets.getFileSec();
    if (fileSec != null) {
      List<MetsType.FileSec.FileGrp> fileGrps = fileSec.getFileGrp();
      boolean existSchemas = false;
      for (MetsType.FileSec.FileGrp fileGrp : fileGrps) {
        if (fileGrp.getUSE().equals("Schemas")) {
          existSchemas = true;
          break;
        }
      }
      for (StructMapType struct : structMap) {
        DivType div = struct.getDiv();
        if (div != null) {
          List<DivType> divs = div.getDiv();
          for (DivType d : divs) {
            if (d.getLABEL().equals("Schemas")) {
              found = true;
              break;
            }
          }
          if (existSchemas && !found) {
            return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
              Message.createErrorMessage(
                "mets/structMap[@LABEL='CSIP']/div/div[@LABEL='Schemas'] must be added in %1$s", metsName,
                isRootMets()),
              false, false);
          } else {
            if (!existSchemas && found) {
              return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, Message.createErrorMessage(
                "mets/fileSec/fileGrp[@USE='Schemas'] must be added in %1$s", metsName, isRootMets()), false, false);
            }
          }
        }

      }
    }
    return new ReporterDetails();
  }

  /*
   * mets/structMap[@LABEL='CSIP']/div/div[@LABEL='Schemas']/@ID Mandatory, xml:id
   * identifier must be unique within the package.
   */
  private ReporterDetails validateCSIP98() {
    List<StructMapType> structMap = mets.getStructMap();
    if (structMap != null) {
      for (StructMapType struct : structMap) {
        DivType div = struct.getDiv();
        if (div != null) {
          List<DivType> divs = div.getDiv();
          for (DivType d : divs) {
            if (d.getLABEL().equals("Schemas")) {
              String id = d.getID();
              if (id == null) {
                return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
                  Message.createErrorMessage(
                    "mets/structMap[@LABEL='CSIP']/div/div[@LABEL='Schemas']/@ID in %1$s can't be null", metsName,
                    isRootMets()),
                  false, false);
              } else {
                if (checkId(id)) {
                  StringBuilder message = new StringBuilder();
                  message.append("Value ").append(id).append(
                    " in %1$s for mets/structMap[@LABEL='CSIP']/div/div[@LABEL='Schemas']/@ID isn't unique in the package");
                  return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
                    Message.createErrorMessage(message.toString(), metsName, isRootMets()), false, false);
                } else {
                  addId(id);
                }
              }
            }
          }
        }
      }
    }
    return new ReporterDetails();
  }

  /*
   * mets/structMap[@LABEL='CSIP']/div/div[@LABEL='Schemas'] The schema division
   * <div> element’s @LABEL attribute has the value “Schemas” from the vocabulary.
   * See also: File group names
   */
  private ReporterDetails validateCSIP99() {
    boolean found = false;
    List<StructMapType> structMap = mets.getStructMap();
    if (structMap != null) {
      for (StructMapType struct : structMap) {
        DivType div = struct.getDiv();
        if (div != null) {
          List<DivType> divs = div.getDiv();
          for (DivType d : divs) {
            if (d.getLABEL().equals("Schemas")) {
              found = true;
              break;
            }
          }
          if (found) {
            break;
          }
        }
      }
    }
    if (!found) {
      return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
        Message.createErrorMessage("mets/structMap[@LABEL='CSIP']/div/div[@LABEL='Schemas'] not found in %1$s",
          metsName, isRootMets()),
        false, false);
    }
    return new ReporterDetails();
  }

  /*
   * mets/structMap[@LABEL='CSIP']/div/div[@LABEL='Schemas']/fptr All file groups
   * containing schemas described in the package are referenced via the relevant
   * file group identifiers. One file group reference per fptr-element
   */
  private ReporterDetails validateCSIP100() {
    List<StructMapType> structMap = mets.getStructMap();
    List<MetsType.FileSec.FileGrp> fileGrps = mets.getFileSec().getFileGrp();
    int fileGrpSchemas = 0;
    int structSchemas = 0;
    if (structMap != null) {
      if (fileGrps != null && fileGrps.size() != 0) {
        for (MetsType.FileSec.FileGrp fileGrp : fileGrps) {
          if (fileGrp.getUSE().equals("Schemas")) {
            if (!fileGrp.getFile().isEmpty()) {
              fileGrpSchemas++;
            }
          }
        }
      }
      for (StructMapType struct : structMap) {
        DivType div = struct.getDiv();
        if (div != null) {
          List<DivType> divs = div.getDiv();
          for (DivType d : divs) {
            if (d.getLABEL().equals("Schemas")) {
              List<DivType.Fptr> ftprs = d.getFptr();
              structSchemas = ftprs.size();
            }
          }
        }
      }

      if (fileGrpSchemas != structSchemas) {
        return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, Message.createErrorMessage(
          "In %1$s must be one file group reference per mets/structMap[@LABEL='CSIP']/div/div[@LABEL='Schemas']/fptr ",
          metsName, isRootMets()), false, false);
      }
    }
    return new ReporterDetails();
  }

  /*
   * mets/structMap[@LABEL='CSIP']/div/div[@LABEL='Schemas']/fptr/@FILEID The
   * pointer to the identifier for the “Schema” file group. Related to the
   * requirements CSIP113 which describes the “Schema” file group and CSIP65 which
   * describes the file group identifier.
   */
  private ReporterDetails validateCSIP118() {
    List<StructMapType> structMap = mets.getStructMap();
    List<MetsType.FileSec.FileGrp> fileGrps = mets.getFileSec().getFileGrp();
    boolean found = false;
    if (structMap != null) {
      for (StructMapType struct : structMap) {
        DivType div = struct.getDiv();
        if (div != null) {
          List<DivType> divs = div.getDiv();
          for (DivType d : divs) {
            if (d.getLABEL().equals("Schemas")) {
              List<DivType.Fptr> ftprs = d.getFptr();
              if (ftprs != null && ftprs.size() > 0) {
                for (DivType.Fptr fptr : ftprs) {
                  String fileid = ((MetsType.FileSec.FileGrp) fptr.getFILEID()).getID();
                  for (MetsType.FileSec.FileGrp fileGrp : fileGrps) {
                    if (fileGrp.getUSE().equals("Schemas")) {
                      String id = fileGrp.getID();
                      if (id.equals(fileid)) {
                        found = true;
                      }
                    }
                  }
                  if (!found) {
                    StringBuilder message = new StringBuilder();
                    message.append("Value ").append(fileid).append(
                      " In %1$s for mets/structMap[@LABEL='CSIP']/div/div[@LABEL='Schemas']/fptr/@FILEID doesn't match with any mets/fileSec/fileGrp/@ID ");
                    return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
                      Message.createErrorMessage(message.toString(), metsName, isRootMets()), false, false);
                  }
                }
              }
            }
          }
        }
      }
    }
    return new ReporterDetails();
  }

  /*
   * mets/structMap[@LABEL='CSIP']/div/div[@LABEL='Representations'] When no
   * representations are present the content referenced in the file section file
   * group with @USE attribute value “Representations” is described in the
   * structural map as a single sub division.
   */
  private ReporterDetails validateCSIP101() {
    boolean found = false;
    List<StructMapType> structMap = mets.getStructMap();
    MetsType.FileSec fileSec = mets.getFileSec();
    if (fileSec != null) {
      List<MetsType.FileSec.FileGrp> fileGrps = fileSec.getFileGrp();
      boolean existRepresentations = false;
      for (MetsType.FileSec.FileGrp fileGrp : fileGrps) {
        if (fileGrp.getUSE().equals("Representations")) {
          existRepresentations = true;
          break;
        }
      }
      for (StructMapType struct : structMap) {
        DivType div = struct.getDiv();
        if (div != null) {
          List<DivType> divs = div.getDiv();
          for (DivType d : divs) {
            if (d.getLABEL().equals("Representations")) {
              found = true;
              break;
            }
          }
          if (existRepresentations && !found) {
            return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
              Message.createErrorMessage(
                "mets/structMap[@LABEL='CSIP']/div/div[@LABEL='Representations'] must be added in %1$s", metsName,
                isRootMets()),
              false, false);
          } else {
            if (!existRepresentations && found) {
              return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
                Message.createErrorMessage("mets/fileSec/fileGrp[@USE='Representations'] must be added in %1$s",
                  metsName, isRootMets()),
                false, false);
            }
          }
        }
      }
    }
    return new ReporterDetails();
  }

  /*
   * mets/structMap[@LABEL='CSIP']/div/div[@LABEL='Representations']/@ID
   * Mandatory, xml:id identifier must be unique within the package.
   */
  private ReporterDetails validateCSIP102() {
    List<StructMapType> structMap = mets.getStructMap();
    if (structMap != null) {
      for (StructMapType struct : structMap) {
        DivType div = struct.getDiv();
        if (div != null) {
          List<DivType> divs = div.getDiv();
          for (DivType d : divs) {
            if (d.getLABEL().equals("Representations")) {
              String id = d.getID();
              if (id == null) {
                return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
                  Message.createErrorMessage(
                    "mets/structMap[@LABEL='CSIP']/div/div[@LABEL='Representations']/@ID in %1$s can't be null",
                    metsName, isRootMets()),
                  false, false);
              } else {
                if (checkId(id)) {
                  StringBuilder message = new StringBuilder();
                  message.append("Value ").append(id).append(
                    " in %1$s for mets/structMap[@LABEL='CSIP']/div/div[@LABEL='Representations']/@ID isn't unique in the package");
                  return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
                    Message.createErrorMessage(message.toString(), metsName, isRootMets()), false, false);
                } else {
                  addId(id);
                }
              }
            }
          }
        }
      }
    }
    return new ReporterDetails();
  }

  /*
   * mets/structMap[@LABEL='CSIP']/div/div[@LABEL='Representations'] The package’s
   * content division <div> element must have the @LABEL attribute value
   * “Representations”, taken from the vocabulary. See also: File group names
   */
  private ReporterDetails validateCSIP103() throws IOException {
    boolean found = false;
    List<StructMapType> structMap = mets.getStructMap();
    boolean existSubMets;
    if (structMap != null) {
      if (isZipFileFlag()) {
        existSubMets = zipManager.checkIfExistsSubMets(getEARKSIPpath());
      } else {
        existSubMets = folderManager.checkIfExistsSubMets(getEARKSIPpath());
      }
      if (existSubMets) {
        return new ReporterDetails();
      } else {
        for (StructMapType struct : structMap) {
          DivType div = struct.getDiv();
          if (div != null) {
            List<DivType> divs = div.getDiv();
            for (DivType d : divs) {
              if (d.getLABEL().equals("Representations")) {
                found = true;
                break;
              }
            }
            if (!found) {
              return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
                Message.createErrorMessage(
                  "mets/structMap[@LABEL='CSIP']/div/div[@LABEL='Representations'] not found in %1$s", metsName,
                  isRootMets()),
                false, false);
            }
          }
        }
      }
    }
    return new ReporterDetails();
  }

  /*
   * mets/structMap[@LABEL='CSIP']/div/div[@LABEL='Representations']/fptr All file
   * groups containing content described in the package are referenced via the
   * relevant file group identifiers. One file group reference per fptr-element.
   */
  private ReporterDetails validateCSIP104() {
    List<StructMapType> structMap = mets.getStructMap();
    List<MetsType.FileSec.FileGrp> fileGrps = mets.getFileSec().getFileGrp();
    boolean isMptr = false;
    int fileGrpRepresentations = 0;
    int structRepresentations = 0;
    if (structMap != null) {
      if (fileGrps != null && !fileGrps.isEmpty()) {
        for (MetsType.FileSec.FileGrp fileGrp : fileGrps) {
          if (fileGrp.getUSE().equals("Representations")) {
            fileGrpRepresentations++;
          }
        }
      }
      for (StructMapType struct : structMap) {
        DivType div = struct.getDiv();
        if (div != null) {
          List<DivType> divs = div.getDiv();
          for (DivType d : divs) {
            if (d.getLABEL().equals("Representations")) {
              List<DivType.Fptr> ftprs = d.getFptr();
              if (!ftprs.isEmpty()) {
                structRepresentations = ftprs.size();
              } else {
                if (!d.getMptr().isEmpty()) {
                  isMptr = true;
                }
              }

            }
          }
        }
      }

      if (fileGrpRepresentations != structRepresentations && !isMptr) {
        return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, Message.createErrorMessage(
          "In %1$s must be one file group reference per mets/structMap[@LABEL='CSIP']/div/div[@LABEL='Schemas']/fptr ",
          metsName, isRootMets()), false, false);
      }
    }
    return new ReporterDetails();
  }

  /*
   * mets/structMap[@LABEL='CSIP']/div/div[@LABEL='Representations']/fptr/@FILEID
   * The pointer to the identifier for the “Representations” file group. Related
   * to the requirements CSIP114 which describes the “Representations” file group
   * and CSIP65 which describes the file group identifier.
   */
  private ReporterDetails validateCSIP119() {
    List<StructMapType> structMap = mets.getStructMap();
    List<MetsType.FileSec.FileGrp> fileGrps = mets.getFileSec().getFileGrp();
    boolean found = false;
    if (structMap != null) {
      for (StructMapType struct : structMap) {
        DivType div = struct.getDiv();
        if (div != null) {
          List<DivType> divs = div.getDiv();
          for (DivType d : divs) {
            if (d.getLABEL().equals("Representations")) {
              List<DivType.Fptr> ftprs = d.getFptr();
              if (ftprs != null && ftprs.size() > 0) {
                for (DivType.Fptr fptr : ftprs) {
                  String fileid = ((MetsType.FileSec.FileGrp) fptr.getFILEID()).getID();
                  for (MetsType.FileSec.FileGrp fileGrp : fileGrps) {
                    if (fileGrp.getUSE().equals("Representations")) {
                      String id = fileGrp.getID();
                      if (id.equals(fileid)) {
                        found = true;
                      }
                    }
                  }
                  if (!found) {
                    StringBuilder message = new StringBuilder();
                    message.append("mets/structMap[@LABEL='CSIP']/div/div[@LABEL='Representations']/fptr/@FILEID (")
                      .append(fileid).append(") doesn't match with any mets/fileSec/fileGrp/@ID in %1$s");
                    return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
                      Message.createErrorMessage(message.toString(), metsName, isRootMets()), false, false);
                  }
                }
              }
            }
          }
        }
      }
    }
    return new ReporterDetails();
  }

  /*
   * mets/structMap[@LABEL='CSIP']/div/div When a package consists of multiple
   * representations, each described by a representation level METS.xml document,
   * there is a discrete representation div element for each representation. Each
   * representation div references the representation level METS.xml document,
   * documenting the structure of the package and its constituent representations.
   */
  private ReporterDetails validateCSIP105() {
    List<StructMapType> structMap = mets.getStructMap();
    if (!structMap.isEmpty()) {
      if (isRootMets()) {
        for (StructMapType struct : structMap) {
          DivType firstDiv = struct.getDiv();
          if (firstDiv != null) {
            List<DivType> divs = firstDiv.getDiv();
            for (DivType div : divs) {
              if (div.getLABEL().matches("Representations/.*/") && div.getMptr().isEmpty()) {
                return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, Message.createErrorMessage(
                  "When a package consists of multiple representations, each described by a representation level METS.xml document, there is a discrete representation div element for each representation (%1$s)",
                  metsName, isRootMets()), false, false);
              }
            }
          }
        }
      }
    }
    return new ReporterDetails();
  }

  /*
   * mets/structMap[@LABEL='CSIP']/div/div/@ID Mandatory, xml:id identifier must
   * be unique within the package.
   */
  private ReporterDetails validateCSIP106() {
    List<StructMapType> structMap = mets.getStructMap();
    if (structMap != null) {
      for (StructMapType struct : structMap) {
        DivType firstDiv = struct.getDiv();
        if (firstDiv != null) {
          List<DivType> divs = firstDiv.getDiv();
          for (DivType div : divs) {
            String id = div.getID();
            if (id == null) {
              return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
                Message.createErrorMessage("mets/structMap[@LABEL='CSIP']/div/div/@ID in %1$s can't be null", metsName,
                  isRootMets()),
                false, false);
            } else {
              if (checkId(id)) {
                StringBuilder message = new StringBuilder();
                message.append("Value ").append(id)
                  .append(" in %1$s for mets/structMap[@LABEL='CSIP']/div/div/@ID isn't unique in the package");
                return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
                  Message.createErrorMessage(message.toString(), metsName, isRootMets()), false, false);
              } else {
                addId(id);
              }
            }
          }

        }
      }
    }
    return new ReporterDetails();
  }

  /*
   * mets/structMap[@LABEL='CSIP']/div/div/@LABEL The package’s representation
   * division <div> element @LABEL attribute value must be the path to the
   * representation level METS document. This requirement gives the same value to
   * be used as the requirement named “File group identifier” (CSIP64) See also:
   * File group names Preciso teste
   */
  private ReporterDetails validateCSIP107() throws IOException {
    List<StructMapType> structMap = mets.getStructMap();
    StringBuilder message = new StringBuilder();
    if (structMap != null) {
      for (StructMapType struct : structMap) {
        DivType div = struct.getDiv();
        if (div != null) {
          List<DivType> divs = div.getDiv();
          for (DivType d : divs) {
            String label = d.getLABEL();
            if (isZipFileFlag()) {
              StringBuilder path = new StringBuilder();
              if (isRootMets()) {
                if (mets.getOBJID() != null) {
                  path.append(mets.getOBJID()).append("/").append(label.toLowerCase());
                } else {
                  return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
                    Message.createErrorMessage("mets/OBJECTID in %1$s can't be null", metsName, isRootMets()), false,
                    false);
                }
              } else {
                path.append(metsPath).append(label.toLowerCase());
              }
              if (!zipManager.checkDirectory(getEARKSIPpath(), path.toString())) {
                message.append("mets/structMap[@LABEL='CSIP']/div/div/@LABEL in %1$s ( ").append(label).append(" )")
                  .append("does not lead to a directory ( ").append(path).append(" )");
                return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
                  Message.createErrorMessage(message.toString(), metsName, isRootMets()), false, false);
              }
            } else {
              if (!folderManager.checkDirectory(Paths.get(metsPath).resolve(label.toLowerCase()))) {
                message.append("mets/structMap[@LABEL='CSIP']/div/div/@LABEL in %1$s ( ").append(label).append(" )")
                  .append("does not lead to a directory ( ").append(Paths.get(metsPath).resolve(label.toLowerCase()))
                  .append(" )");
                return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
                  Message.createErrorMessage(message.toString(), metsName, isRootMets()), false, false);
              }
            }
          }
        }
      }
    }
    return new ReporterDetails();
  }

  /*
   * mets/structMap[@LABEL='CSIP']/div/div/mptr/@xlink:title The file group
   * containing the files described in the package are referenced via the relevant
   * file group identifier. Related to the requirements CSIP114 which describes
   * the “Representations” file group and CSIP65 which describes the file group
   * identifier.
   */
  private ReporterDetails validateCSIP108() {
    List<StructMapType> structMap = mets.getStructMap();
    MetsType.FileSec fileSec = mets.getFileSec();
    if (structMap != null) {
      for (StructMapType struct : structMap) {
        DivType div = struct.getDiv();
        if (div != null) {
          List<DivType> divs = div.getDiv();
          for (DivType d : divs) {
            if (d.getLABEL().matches("Representations/.*")) {
              List<DivType.Mptr> mptrs = d.getMptr();
              if (mptrs != null && !mptrs.isEmpty()) {
                for (DivType.Mptr mptr : mptrs) {
                  String title = mptr.getTitle();
                  if (title != null) {
                    List<MetsType.FileSec.FileGrp> fileGrps = fileSec.getFileGrp();
                    boolean found = false;
                    for (MetsType.FileSec.FileGrp fileGrp : fileGrps) {
                      if (title.equals(fileGrp.getID())) {
                        found = true;
                      }
                    }
                    if (!found) {
                      StringBuilder message = new StringBuilder();
                      message.append("mets/structMap[@LABEL='CSIP']/div/div/mptr/@xlink:title in %1$s (").append(title)
                        .append(") does not correspond a file group ID");
                      return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
                        Message.createErrorMessage(message.toString(), metsName, isRootMets()), false, false);
                    }
                  } else {
                    return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
                      Message.createErrorMessage(
                        "mets/structMap[@LABEL='CSIP']/div/div/mptr/@xlink:title in %1$s can't be null", metsName,
                        isRootMets()),
                      false, false);
                  }
                }
              }
            }
          }
        }
      }
    }
    return new ReporterDetails();
  }

  /*
   * mets/structMap[@LABEL='CSIP']/div/div/mptr The division <div> of the specific
   * representation includes one occurrence of the METS pointer <mptr> element,
   * pointing to the appropriate representation METS file.
   */
  private ReporterDetails validateCSIP109() {
    List<StructMapType> structMap = mets.getStructMap();
    if (structMap != null) {
      for (StructMapType struct : structMap) {
        DivType div = struct.getDiv();
        if (div != null) {
          List<DivType> divs = div.getDiv();
          for (DivType d : divs) {
            if (d.getLABEL().matches("Representations/.*")) {
              List<DivType.Mptr> mptrs = d.getMptr();
              if (d.getFptr().isEmpty()) {
                if (mptrs == null || mptrs.size() != 1) {
                  return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
                    Message.createErrorMessage(
                      "mets/structMap[@LABEL='CSIP']/div/div/mptr in %1$s can't be null or more than one", metsName,
                      isRootMets()),
                    false, false);
                }
              }
            }
          }
        }
      }
    }
    return new ReporterDetails();
  }

  /*
   * mets/structMap/div/div/mptr/@xlink:href The actual location of the resource.
   * We recommend recording a URL type filepath within this attribute.
   */
  private ReporterDetails validateCSIP110() throws IOException {
    List<StructMapType> structMap = mets.getStructMap();
    StringBuilder message = new StringBuilder();
    if (structMap != null) {
      for (StructMapType struct : structMap) {
        DivType div = struct.getDiv();
        if (div != null) {
          List<DivType> divs = div.getDiv();
          for (DivType d : divs) {
            if (d.getLABEL().matches("Representations/.*")) {
              List<DivType.Mptr> mptrs = d.getMptr();
              if (!mptrs.isEmpty()) {
                for (DivType.Mptr mptr : mptrs) {
                  String href = URLDecoder.decode(mptr.getHref(), "UTF-8");
                  if (isZipFileFlag()) {
                    StringBuilder filePath = new StringBuilder();
                    if (isRootMets()) {
                      if (mets.getOBJID() != null) {
                        filePath.append(mets.getOBJID()).append("/").append(href);
                      } else {
                        return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
                          Message.createErrorMessage("mets/@OBJECTID in %1$s can't be null", metsName, isRootMets()),
                          false, false);
                      }
                    } else {
                      filePath.append(metsPath).append(href);
                    }
                    if (!zipManager.checkPathExists(getEARKSIPpath(), filePath.toString())) {
                      message.append("mets/structMap/div/div/mptr/@xlink:href  ").append(filePath)
                        .append(" doesn't exists (in %1$s)");
                      return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
                        Message.createErrorMessage(message.toString(), metsName, isRootMets()), false, false);
                    }
                  } else {
                    if (!folderManager.checkPathExists(Paths.get(metsPath).resolve(href))) {
                      message.append("mets/structMap/div/div/mptr/@xlink:href ")
                        .append(Paths.get(metsPath).resolve(href)).append(" doesn't exists (in %1$s)");
                      return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
                        Message.createErrorMessage(message.toString(), metsName, isRootMets()), false, false);
                    }
                  }
                }
              }
            }
          }
        }
      }
    }
    return new ReporterDetails();
  }

  /*
   * mets/structMap/div/div/mptr[@xlink:type='simple'] Attribute used with the
   * value “simple”. Value list is maintained by the xlink standard.
   */
  private ReporterDetails validateCSIP111() throws IOException {
    List<StructMapType> structMap = mets.getStructMap();
    HashMap<String, String> structMapTypes = new HashMap<>();
    MetsHandler fileSecHandler = new MetsHandler("div", "mptr", structMapTypes);
    MetsParser metsParser = new MetsParser();
    InputStream metsStream = null;
    if (!structMap.isEmpty()) {
      if (isZipFileFlag()) {
        if (isRootMets()) {
          metsStream = zipManager.getMetsRootInputStream(getEARKSIPpath());
        } else {
          metsStream = zipManager.getZipInputStream(getEARKSIPpath(), metsPath + "METS.xml");
        }
      } else {
        if (isRootMets()) {
          metsStream = folderManager.getMetsRootInputStream(getEARKSIPpath());
        } else {
          metsStream = folderManager.getInputStream(Paths.get(metsPath).resolve("METS.xml"));
        }
      }
    }
    if (metsStream != null) {
      metsParser.parse(fileSecHandler, metsStream);
    }
    if (!structMap.isEmpty()) {
      for (StructMapType struct : structMap) {
        DivType div = struct.getDiv();
        if (div != null) {
          List<DivType> divs = div.getDiv();
          for (DivType d : divs) {
            if (d.getLABEL().matches("Representations/")) {
              List<DivType.Mptr> mptrs = d.getMptr();
              if (!mptrs.isEmpty()) {
                for (DivType.Mptr mptr : mptrs) {
                  if (structMapTypes.get(mptr.getHref()) != null) {
                    if (!structMapTypes.get(mptr.getHref()).equals("simple")) {
                      return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
                        Message.createErrorMessage(
                          "mets/structMap/div/div/mptr[@xlink:type='simple'] value in %1$s must be 'simple'", metsName,
                          isRootMets()),
                        false, false);
                    }
                  } else {
                    return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
                      Message.createErrorMessage(
                        "mets/structMap/div/div/mptr[@xlink:type='simple'] in %1$s can't be null", metsName,
                        isRootMets()),
                      false, false);
                  }
                }
              }
            }
          }
        }
      }
    }
    return new ReporterDetails();
  }

  /*
   * mets/structMap/div/div/mptr[@LOCTYPE='URL'] The locator type is always used
   * with the value “URL” from the vocabulary in the attribute.
   */
  private ReporterDetails validateCSIP112() {
    List<StructMapType> structMap = mets.getStructMap();
    if (structMap != null) {
      for (StructMapType struct : structMap) {
        DivType div = struct.getDiv();
        if (div != null) {
          List<DivType> divs = div.getDiv();
          for (DivType d : divs) {
            if (d.getLABEL().matches("Representations/")) {
              List<DivType.Mptr> mptrs = d.getMptr();
              for (DivType.Mptr mptr : mptrs) {
                String locType = mptr.getLOCTYPE();
                if (locType != null) {
                  if (!locType.equals("URL")) {
                    return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
                      Message.createErrorMessage(
                        "mets/structMap/div/div/mptr[@LOCTYPE='URL'] value in %1$s must be 'URL'", metsName,
                        isRootMets()),
                      false, false);
                  }
                } else {
                  return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
                    Message.createErrorMessage("mets/structMap/div/div/mptr[@LOCTYPE='URL'] in %1$s can't be null",
                      metsName, isRootMets()),
                    false, false);
                }
              }
            }
          }
        }
      }
    }
    return new ReporterDetails();
  }
}
