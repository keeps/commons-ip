package org.roda_project.commons_ip2.validator.component.metsrootComponent;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.parsers.ParserConfigurationException;

import org.roda_project.commons_ip2.mets_v1_12.beans.MetsType;
import org.roda_project.commons_ip2.validator.common.ControlledVocabularyParser;
import org.roda_project.commons_ip2.validator.component.ValidatorComponentImpl;
import org.roda_project.commons_ip2.validator.constants.Constants;
import org.roda_project.commons_ip2.validator.constants.ConstantsCSIPspec;
import org.roda_project.commons_ip2.validator.constants.ConstantsSIPspec;
import org.roda_project.commons_ip2.validator.reporter.ReporterDetails;
import org.roda_project.commons_ip2.validator.utils.Message;
import org.roda_project.commons_ip2.validator.utils.ResultsUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;

/**
 * @author João Gomes <jgomes@keep.pt>
 */
public class MetsHeaderComponentValidator extends ValidatorComponentImpl {
  private static final Logger LOGGER = LoggerFactory.getLogger(MetsHeaderComponentValidator.class);

  private final String moduleName;
  private List<String> oaisPackageTypes;
  private MetsType.MetsHdr metsHdr;
  private List<MetsType.MetsHdr.Agent> agents;
  private List<String> recordsStatus;

  public MetsHeaderComponentValidator() throws IOException, ParserConfigurationException, SAXException {
    this.moduleName = Constants.CSIP_MODULE_NAME_2;
    this.oaisPackageTypes = ControlledVocabularyParser
      .parse(Constants.PATH_RESOURCES_CSIP_VOCABULARY_OAIS_PACKAGE_TYPE);
    this.recordsStatus = ControlledVocabularyParser.parse(Constants.PATH_RESOURCES_CSIP_VOCABULARY_RECORD_STATUS);
  }

  @Override
  public Map<String, ReporterDetails> validate() throws IOException {
    metsHdr = mets.getMetsHdr();
    agents = metsHdr.getAgent();
    Map<String, ReporterDetails> results = new HashMap<>();

    ReporterDetails skippedCSIP;
    /* CSIP117 */
    notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP117_ID);
    ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP117_ID,
      validateCSIP117().setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION));

    if (ResultsUtils.isResultValid(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP117_ID)) {
      /* CSIP7 */
      notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP7_ID);
      ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP7_ID,
        validateCSIP7().setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION));

      /* CSIP8 */
      skippedCSIP = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
        Message.createErrorMessage("SKIPPED in  %1$s  can't validate", metsName, isRootMets()), true, true);
      ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP8_ID, skippedCSIP);

      /* CSIP9 */
      notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP9_ID);
      ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP9_ID,
        validateCSIP9().setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION));

      if (isRootMets()) {
        /* CSIP10 */
        notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP10_ID);
        ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP10_ID,
          validateCSIP10().setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION));

        if (ResultsUtils.isResultValid(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP10_ID)) {
          /* CSIP11 */
          notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP11_ID);
          ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP11_ID,
            validateCSIP11().setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION));

          /* CSIP12 */
          notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP12_ID);
          ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP12_ID,
            validateCSIP12().setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION));

          /* CSIP13 */
          notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP13_ID);
          ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP13_ID,
            validateCSIP13().setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION));

          /* CSIP14 */
          notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP14_ID);
          ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP14_ID,
            validateCSIP14().setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION));

          /* CSIP15 */
          notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP15_ID);
          ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP15_ID,
            validateCSIP15().setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION));

          /* CSIP16 */
          notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP16_ID);
          ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP16_ID,
            validateCSIP16().setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION));

          /* SIP9 */
          notifyObserversValidationStarted(moduleName, ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP9_ID);
          ResultsUtils.addResult(results, ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP9_ID,
            validateSIP9().setSpecification(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION));

          if (ResultsUtils.isResultValid(results, ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP9_ID)) {
            /* SIP10 */
            notifyObserversValidationStarted(moduleName, ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP10_ID);
            ResultsUtils.addResult(results, ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP10_ID,
              validateSIP10().setSpecification(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION));

            /* SIP11 */
            notifyObserversValidationStarted(moduleName, ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP11_ID);
            ResultsUtils.addResult(results, ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP11_ID,
              validateSIP11().setSpecification(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION));

            /* SIP12 */
            notifyObserversValidationStarted(moduleName, ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP12_ID);
            ResultsUtils.addResult(results, ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP12_ID,
              validateSIP12().setSpecification(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION));

            /* SIP13 */
            notifyObserversValidationStarted(moduleName, ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP13_ID);
            ResultsUtils.addResult(results, ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP13_ID,
              validateSIP13().setSpecification(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION));

            /* SIP14 */
            notifyObserversValidationStarted(moduleName, ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP14_ID);
            ResultsUtils.addResult(results, ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP14_ID,
              validateSIP14().setSpecification(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION));
          } else {
            String message = Message.createErrorMessage(
              "SKIPPED in %1$s because mets/metsHdr/agent with the role ARCHIVIST doesn't exist", metsName,
              isRootMets());

            ResultsUtils.addResults(results,
              new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, message, true, true),
              ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP10_ID,
              ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP11_ID,
              ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP12_ID,
              ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP13_ID,
              ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP14_ID);
          }

          /* SIP15 */
          notifyObserversValidationStarted(moduleName, ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP15_ID);
          ResultsUtils.addResult(results, ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP15_ID,
            validateSIP15().setSpecification(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION));

          if (ResultsUtils.isResultValid(results, ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP15_ID)) {
            /* SIP16 */
            notifyObserversValidationStarted(moduleName, ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP16_ID);
            ResultsUtils.addResult(results, ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP16_ID,
              validateSIP16().setSpecification(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION));

            /* SIP17 */
            notifyObserversValidationStarted(moduleName, ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP17_ID);
            ResultsUtils.addResult(results, ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP17_ID,
              validateSIP17().setSpecification(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION));

            /* SIP18 */
            notifyObserversValidationStarted(moduleName, ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP18_ID);
            ResultsUtils.addResult(results, ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP18_ID,
              validateSIP18().setSpecification(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION));

            /* SIP19 */
            notifyObserversValidationStarted(moduleName, ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP19_ID);
            ResultsUtils.addResult(results, ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP19_ID,
              validateSIP19().setSpecification(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION));

            /* SIP20 */
            notifyObserversValidationStarted(moduleName, ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP20_ID);
            ResultsUtils.addResult(results, ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP20_ID,
              validateSIP20().setSpecification(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION));

          } else {
            String message = Message.createErrorMessage(
              "SKIPPED because mets/metsHdr/agent with the role CREATOR doesn't exist", metsName, isRootMets());

            ResultsUtils.addResults(results,
              new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, message, true, true),
              ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP16_ID,
              ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP17_ID,
              ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP18_ID,
              ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP19_ID,
              ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP20_ID);
          }

          /* SIP21 */
          notifyObserversValidationStarted(moduleName, ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP21_ID);
          ResultsUtils.addResult(results, ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP21_ID,
            validateSIP21().setSpecification(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION));

          if (ResultsUtils.isResultValid(results, ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP21_ID)) {
            /* SIP22 */
            notifyObserversValidationStarted(moduleName, ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP22_ID);
            ResultsUtils.addResult(results, ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP22_ID,
              validateSIP22().setSpecification(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION));

            /* SIP23 */
            notifyObserversValidationStarted(moduleName, ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP20_ID);
            ResultsUtils.addResult(results, ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP23_ID,
              validateSIP23().setSpecification(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION));

            /* SIP24 */
            notifyObserversValidationStarted(moduleName, ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP24_ID);
            ResultsUtils.addResult(results, ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP24_ID,
              validateSIP24().setSpecification(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION));

            /* SIP25 */
            notifyObserversValidationStarted(moduleName, ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP25_ID);
            ResultsUtils.addResult(results, ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP25_ID,
              validateSIP25().setSpecification(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION));

          } else {
            String message = Message.createErrorMessage(
              "SKIPPED in %1$s because mets/metsHdr/agent with the role OTHER doesn't exist", metsName, isRootMets());

            ResultsUtils.addResults(results,
              new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, message, true, true),
              ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP22_ID,
              ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP23_ID,
              ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP24_ID,
              ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP25_ID);
          }

          /* SIP26 */
          notifyObserversValidationStarted(moduleName, ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP26_ID);
          ResultsUtils.addResult(results, ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP26_ID,
            validateSIP26().setSpecification(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION));

          if (ResultsUtils.isResultValid(results, ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP26_ID)) {
            /* SIP27 */
            notifyObserversValidationStarted(moduleName, ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP27_ID);
            ResultsUtils.addResult(results, ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP27_ID,
              validateSIP27().setSpecification(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION));

            /* SIP28 */
            notifyObserversValidationStarted(moduleName, ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP28_ID);
            ResultsUtils.addResult(results, ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP28_ID,
              validateSIP28().setSpecification(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION));

            /* SIP29 */
            notifyObserversValidationStarted(moduleName, ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP29_ID);
            ResultsUtils.addResult(results, ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP29_ID,
              validateSIP29().setSpecification(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION));

            /* SIP30 */
            notifyObserversValidationStarted(moduleName, ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP30_ID);
            ResultsUtils.addResult(results, ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP30_ID,
              validateSIP30().setSpecification(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION));

            /* SIP31 */
            notifyObserversValidationStarted(moduleName, ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP31_ID);
            ResultsUtils.addResult(results, ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP31_ID,
              validateSIP31().setSpecification(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION));

          } else {
            String message = Message.createErrorMessage(
              "SKIPPED in %1$s because mets/metsHdr/agent with the role PRESERVATION doesn't exist", metsName,
              isRootMets());

            ResultsUtils.addResults(results,
              new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, message, true, true),
              ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP27_ID,
              ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP28_ID,
              ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP29_ID,
              ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP30_ID,
              ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP31_ID);
          }
        } else {
          String message = Message.createErrorMessage(
            "SKIPPED in %1$s because it does not contain a mets/metsHdr/agent element", metsName, isRootMets());

          ResultsUtils.addResults(results,
            new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, message, true, true),
            ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP11_ID,
            ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP12_ID,
            ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP13_ID,
            ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP14_ID,
            ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP15_ID,
            ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP16_ID,
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
      }

      /* SIP3 */
      notifyObserversValidationStarted(moduleName, ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP3_ID);
      ResultsUtils.addResult(results, ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP3_ID,
        validateSIP3().setSpecification(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION));

      /* SIP4 */
      notifyObserversValidationStarted(moduleName, ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP4_ID);
      ResultsUtils.addResult(results, ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP4_ID,
        validateSIP4().setSpecification(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION));

      /* SIP5 */
      notifyObserversValidationStarted(moduleName, ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP5_ID);
      ResultsUtils.addResult(results, ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP5_ID,
        validateSIP5().setSpecification(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION));

      /* SIP6 */
      notifyObserversValidationStarted(moduleName, ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP6_ID);
      ResultsUtils.addResult(results, ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP6_ID,
        validateSIP6().setSpecification(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION));

      /* SIP7 */
      notifyObserversValidationStarted(moduleName, ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP7_ID);
      ResultsUtils.addResult(results, ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP7_ID,
        validateSIP7().setSpecification(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION));

      /* SIP8 */
      notifyObserversValidationStarted(moduleName, ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP8_ID);
      ResultsUtils.addResult(results, ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP8_ID,
        validateSIP8().setSpecification(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION));

    } else {
      String message = Message.createErrorMessage("SKIPPED because mets/metsHdr doesn't exist ", metsName,
        isRootMets());

      ResultsUtils.addResults(results,
        new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, message, true, true),
        ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP7_ID,
        ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP8_ID,
        ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP9_ID,
        ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP10_ID,
        ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP11_ID,
        ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP12_ID,
        ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP13_ID,
        ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP14_ID,
        ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP15_ID,
        ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP16_ID,
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

  /*
   * mets/metsHdr General element for describing the package.
   */
  private ReporterDetails validateCSIP117() {
    ReporterDetails details = new ReporterDetails();
    if (metsHdr == null) {
      details.setValid(false);
      details.addIssue(Message.createErrorMessage("mets/metsHdr can't be null, in %1$s the mets/metsHdr does not exist",
        metsName, isRootMets()));
    }
    return details;
  }

  /*
   * mets/metsHdr/@CREATEDATE mets/metsHdr/@CREATEDATE records the date the
   * package was created.
   */
  private ReporterDetails validateCSIP7() {
    ReporterDetails details = new ReporterDetails();
    XMLGregorianCalendar createDate = metsHdr.getCREATEDATE();
    if (createDate == null) {
      details.setValid(false);
      details.addIssue(Message.createErrorMessage("mets/metsHdr/@CREATEDATE can't be null, in %1$s the value is null",
        metsName, isRootMets()));
    }
    return details;
  }

  /*
   * mets/metsHdr/@csip:OAISPACKAGETYPE mets/metsHdr/@csip:OAISPACKAGETYPE is an
   * additional CSIP attribute that declares the type of the IP.See also:
   * OAISPackage type
   */
  private ReporterDetails validateCSIP9() {
    ReporterDetails details = new ReporterDetails();
    String oaisPackageType = metsHdr.getOAISPACKAGETYPE();
    if (oaisPackageType == null || oaisPackageType.equals("")) {
      details.setValid(false);
      details.addIssue(Message.createErrorMessage(
        "mets/metsHdr/@csip:OAISPACKAGETYPE can't be null or empty, in %1$s the value is null or empty", metsName,
        isRootMets()));
    } else {
      if (!oaisPackageTypes.contains(oaisPackageType)) {
        StringBuilder message = new StringBuilder();
        message.append("Value ").append(oaisPackageType)
          .append("in %1$s for mets/metsHdr/@csip:OAISPACKAGETYPE isn't valid");
        details.setValid(false);
        details.addIssue(Message.createErrorMessage(message.toString(), metsName, isRootMets()));
      }
    }
    return details;
  }

  /*
   * mets/metsHdr/agent A mandatory agent element records the software used to
   * create the package. Other uses of agents may be described in any local
   * implementations that extend the profile.
   */
  private ReporterDetails validateCSIP10() {
    ReporterDetails details = new ReporterDetails();
    if (agents == null && isRootMets()) {
      details.setValid(false);
      details.addIssue(
        Message.createErrorMessage("Must have at least one mets/metsHdr/agent (%1$s)", metsName, isRootMets()));
    } else {
      if (agents == null && !isRootMets()) {
        return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, "", true, true);
      } else {
        if (agents.isEmpty() && isRootMets()) {
          details.setValid(false);
          details.addIssue(
            Message.createErrorMessage("Must have at least one mets/metsHdr/agent (%1$s)", metsName, isRootMets()));
        } else {
          if (agents.isEmpty() && !isRootMets()) {
            return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, "", true, true);
          }
        }
      }
    }
    return details;
  }

  /*
   * mets/metsHdr/agent[@ROLE=’CREATOR’] The mandatory agent element MUST have
   * a @ROLE attribute with the value “CREATOR”.
   */
  private ReporterDetails validateCSIP11() {
    ReporterDetails details = new ReporterDetails();
    boolean found = false;
    if (agents != null && !agents.isEmpty()) {
      for (MetsType.MetsHdr.Agent a : agents) {
        String role = a.getROLE();
        String type = a.getTYPE();
        String otherType = a.getOTHERTYPE();
        if (role.equals("CREATOR") && type.equals("OTHER") && otherType.equals("SOFTWARE")) {
          found = true;
          break;
        }
      }
      if (!found) {
        return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
          Message.createErrorMessage(
            "Must have a mets/metsHdr/agent[@ROLE='CREATOR'] with the value CREATOR, in %1$s does not exists", metsName,
            isRootMets()),
          false, false);
      }
    } else {
      return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, "", true, true);
    }
    return details;
  }

  /*
   * mets/metsHdr/agent[@TYPE=’OTHER’] The mandatory agent element MUST have
   * a @TYPE attribute with the value “OTHER”.
   */

  private ReporterDetails validateCSIP12() {
    if (agents != null && !agents.isEmpty()) {
      for (MetsType.MetsHdr.Agent a : agents) {
        String role = a.getROLE();
        String type = a.getTYPE();
        String otherType = a.getOTHERTYPE();
        if (role.equals("CREATOR") && type.equals("OTHER") && otherType.equals("SOFTWARE")) {
          return new ReporterDetails();
        }
      }
    } else {
      return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, "", true, true);
    }
    return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
      Message.createErrorMessage(
        "Must have a mets/metsHdr/agent[@TYPE='OTHER'] with the value OTHER, in %1$s does not exist", metsName,
        isRootMets()),
      false, false);
  }

  /*
   * mets/metsHdr/agent[@OTHERTYPE=’SOFTWARE’] The mandatory agent element MUST
   * have a @OTHERTYPE attribute with the value “SOFTWARE”.See also: Other agent
   * type
   */

  private ReporterDetails validateCSIP13() {
    if (agents != null && !agents.isEmpty()) {
      for (MetsType.MetsHdr.Agent a : agents) {
        String role = a.getROLE();
        String type = a.getTYPE();
        String otherType = a.getOTHERTYPE();
        if (role.equals("CREATOR") && type.equals("OTHER") && otherType.equals("SOFTWARE")) {
          return new ReporterDetails();
        }
      }
    } else {
      new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
        Message.createErrorMessage("", metsName, isRootMets()), true, true);
    }
    return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, Message.createErrorMessage(
      "Must have a mets/metsHdr/agent[@OTHERTYPE=’SOFTWARE’] with the value SOFTWARE, in the %1$s the value isn't SOFTWARE",
      metsName, isRootMets()), false, false);

  }

  /*
   * mets/metsHdr/agent/name The mandatory agent’s name element records the name
   * of the software tool used to create the IP.
   */

  private ReporterDetails validateCSIP14() {
    if (agents != null && !agents.isEmpty()) {
      for (MetsType.MetsHdr.Agent a : agents) {
        String role = a.getROLE();
        String type = a.getTYPE();
        String otherType = a.getOTHERTYPE();
        if (role.equals("CREATOR") && type.equals("OTHER") && otherType.equals("SOFTWARE")) {
          if (a.getName() == null) {
            return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
              Message.createErrorMessage("mets/metsHdr/agent/name can't be null, in %1$s the value is null", metsName,
                isRootMets()),
              false, false);
          } else {
            if (a.getName().equals("")) {
              return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
                Message.createErrorMessage("mets/metsHdr/agent/name can't be empty, in %1$s the value is empty",
                  metsName, isRootMets()),
                false, false);
            }
          }
        }
      }
    } else {
      return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, "", true, true);
    }
    return new ReporterDetails();
  }

  /*
   * mets/metsHdr/agent/note The mandatory agent’s note element records the
   * version of the tool used to create the IP.
   */

  private ReporterDetails validateCSIP15() {
    if (agents != null && !agents.isEmpty()) {
      for (MetsType.MetsHdr.Agent a : agents) {
        String role = a.getROLE();
        String type = a.getTYPE();
        String otherType = a.getOTHERTYPE();
        if (role.equals("CREATOR") && type.equals("OTHER") && otherType.equals("SOFTWARE")) {
          List<MetsType.MetsHdr.Agent.Note> notes = a.getNote();
          if (notes == null || notes.isEmpty()) {
            return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
              Message.createErrorMessage("mets/metsHdr/agent/note can't be null, in %1$s the value is null", metsName,
                isRootMets()),
              false, false);
          } else {
            if (notes.size() > 1) {
              return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
                Message.createErrorMessage(
                  "mets/metsHdr/agent/note exists more than once, in %1$s exists more than once note", metsName,
                  isRootMets()),
                false, false);
            } else {
              for (MetsType.MetsHdr.Agent.Note note : notes) {
                if (note.getValue().isEmpty()) {
                  return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
                    Message.createErrorMessage("mets/metsHdr/agent/note can't be empty, in %1$s the note is empty",
                      metsName, isRootMets()),
                    false, false);
                }
              }
            }
          }
        }
      }
    } else {
      return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, "", true, true);
    }
    return new ReporterDetails();

  }

  /*
   * mets/metsHdr/agent/note[@csip:NOTETYPE=’SOFTWARE VERSION’] The mandatory
   * agent element’s note child has a @csip:NOTETYPE attribute with a fixed value
   * of “SOFTWARE VERSION”.See also: Note type
   */
  private ReporterDetails validateCSIP16() {
    ReporterDetails details = new ReporterDetails();
    if (agents == null || agents.isEmpty()) {
      return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, "", true, true);
    }
    for (MetsType.MetsHdr.Agent a : agents) {
      String role = a.getROLE();
      String type = a.getTYPE();
      String otherType = a.getOTHERTYPE();
      if (role.equals("CREATOR") && type.equals("OTHER") && otherType.equals("SOFTWARE")) {
        List<MetsType.MetsHdr.Agent.Note> notes = a.getNote();
        if (notes == null || notes.isEmpty()) {
          return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
            Message.createErrorMessage("mets/metsHdr/agent/note can't be null, in the %1$s the value is null", metsName,
              isRootMets()),
            false, false);
        } else {
          for (MetsType.MetsHdr.Agent.Note note : notes) {
            if (note.getNOTETYPE() == null) {
              return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
                Message.createErrorMessage(
                  "mets/metsHdr/agent/note[@csip:NOTETYPE=’SOFTWARE VERSION’] can't be null, in %1$s the value is null",
                  metsName, isRootMets()),
                false, false);
            } else {
              if (!note.getNOTETYPE().equals("SOFTWARE VERSION")) {
                StringBuilder message = new StringBuilder();
                message.append("Value ").append(note.getNOTETYPE()).append(
                  " in %1$s for mets/metsHdr/agent/note[@csip:NOTETYPE=’SOFTWARE VERSION’] isn't valid, the value must be SOFTWARE VERSION");
                return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
                  Message.createErrorMessage(message.toString(), metsName, isRootMets()), false, false);
              }
            }
          }
        }
      }
    }
    return details;
  }

  /* METS header SIP validation */

  /*
   * metsHdr/@RECORDSTATUS A way of indicating the status of the package and to
   * instruct the OAIS on how to properly handle the package. If not set, the
   * expected behaviour is equal to NEW.See also: Package status
   */

  private ReporterDetails validateSIP3() {
    String recordStatus = metsHdr.getRECORDSTATUS();
    if (recordStatus != null && !recordsStatus.contains(recordStatus)) {
      StringBuilder message = new StringBuilder();
      message.append("Value ").append(recordStatus).append("for metsHdr/@RECORDSTATUS value isn't valid in %1$s");
      return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION,
        Message.createErrorMessage(message.toString(), metsName, isRootMets()), false, false);
    }
    return new ReporterDetails();
  }

  /*
   * metsHdr/@csip:OAISPACKAGETYPE
   * 
   * @csip:OAISPACKAGETYPE is used with the value “SIP”.See also: OAIS Package
   * type
   */

  private ReporterDetails validateSIP4() {
    String oaisPackageType = metsHdr.getOAISPACKAGETYPE();
    if (oaisPackageType == null) {
      return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION,
        Message.createErrorMessage("metsHdr/@csip:OAISPACKAGETYPE can't be null, in %1$s", metsName, isRootMets()),
        false, false);
    }

    if (!oaisPackageType.equals("SIP")) {
      return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION,
        Message.createErrorMessage("metsHdr/@csip:OAISPACKAGETYPE must be used with the value SIP, in %1$s", metsName,
          isRootMets()),
        false, false);
    }
    return new ReporterDetails();
  }

  /*
   * metsHdr/altRecordID A reference to the Submission Agreement associated with
   * the package.
   * 
   * @TYPE is used with the value “SUBMISSIONAGREEMENT”. Example: RA 13-2011/5329;
   * 2012-04-12 Example:
   * http://submissionagreement.kb.se/dnr331-1144-2011/20120711/ Note: It is
   * recommended to use a machine-readable format for a better description of a
   * submission agreement. For example, the submission agreement developed by
   * Docuteam GmbH http://www.loc.gov/standards/mets/profiles/00000041.xmlSee
   * also: Alternative record ID’s
   */

  private ReporterDetails validateSIP5() {
    List<MetsType.MetsHdr.AltRecordID> altRecordIDS = metsHdr.getAltRecordID();
    boolean found = false;
    int count = 0;
    if (altRecordIDS != null && !altRecordIDS.isEmpty()) {
      for (MetsType.MetsHdr.AltRecordID altRecordID : altRecordIDS) {
        String type = altRecordID.getTYPE();
        if (type.equals("SUBMISSIONAGREEMENT")) {
          found = true;
          count++;
        }
      }
      if (!found) {
        return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION,
          Message.createErrorMessage("metsHdr/altRecordID with the @TYPE “SUBMISSIONAGREEMENT” not found in %1$s",
            metsName, isRootMets()),
          false, false);
      }
      if (count > 1) {
        return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION,
          Message.createErrorMessage(
            "Can't have more than one metsHdr/altRecordID of the TYPE SUBMISSIONAGREEMENT, in %1$s exist more than one",
            metsName, isRootMets()),
          false, false);
      }
    }
    return new ReporterDetails();
  }

  /*
   * metsHdr/altRecordID An optional reference to a previous submission
   * agreement(s) which the information may have belonged to. @TYPE is used with
   * the value “PREVIOUSSUBMISSIONAGREEMENT”. Example: FM 12-2387/12726,
   * 2007-09-19 Example:
   * http://submissionagreement.kb.se/dnr331-1144-2011/20120711/ Note: It is
   * recommended to use a machine-readable format for a better description of a
   * submission agreement. For example, the submission agreement developed by
   * Docuteam GmbH http://www.loc.gov/standards/mets/profiles/00000041.xmlSee
   * also: Alternative record ID’s
   */
  private ReporterDetails validateSIP6() {
    List<MetsType.MetsHdr.AltRecordID> altRecordIDS = metsHdr.getAltRecordID();
    boolean found = false;
    if (altRecordIDS != null && !altRecordIDS.isEmpty()) {
      for (MetsType.MetsHdr.AltRecordID altRecordID : altRecordIDS) {
        String type = altRecordID.getTYPE();
        if (type.equals("PREVIOUSSUBMISSIONAGREEMENT")) {
          found = true;
        }
      }
      if (!found) {
        return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION,
          Message.createErrorMessage(
            "metsHdr/altRecordID with the @TYPE “PREVIOUSSUBMISSIONAGREEMENT” not found in %1$s", metsName,
            isRootMets()),
          false, false);
      }
    }
    return new ReporterDetails();
  }

  /*
   * metsHdr/altRecordID An optional reference code indicating where in the
   * archival hierarchy the package shall be placed in the OAIS. @TYPE is used
   * with the value “REFERENCECODE”. Example: FM 12-2387/12726, 2007-09-19See
   * also: Alternative record ID’s
   */
  private ReporterDetails validateSIP7() {
    List<MetsType.MetsHdr.AltRecordID> altRecordIDS = metsHdr.getAltRecordID();
    boolean found = false;
    int count = 0;
    if (altRecordIDS != null && altRecordIDS.size() != 0) {
      for (MetsType.MetsHdr.AltRecordID altRecordID : altRecordIDS) {
        String type = altRecordID.getTYPE();
        if (type.equals("REFERENCECODE")) {
          found = true;
          count++;
        }
      }
      if (!found) {
        return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION,
          Message.createErrorMessage("metsHdr/altRecordID with the @TYPE “REFERENCECODE” not found in %1$s", metsName,
            isRootMets()),
          false, false);
      }
      if (count > 1) {
        return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION,
          Message.createErrorMessage("Can't have more than one metsHdr/altRecordID of the type REFERENCECODE", metsName,
            isRootMets()),
          false, false);
      }
    }
    return new ReporterDetails();
  }

  /*
   * metsHdr/altRecordID In cases where the SIP originates from other institutions
   * maintaining a reference code structure, this element can be used to record
   * these reference codes and therefore support the provenance of the package
   * when a whole archival description is not submitted with the submission. @TYPE
   * is used with the value “PREVIOUSREFERENCECODE”. Example:
   * SE/FM/123/123.1/123.1.3See also: Alternative record ID’s
   */
  private ReporterDetails validateSIP8() {
    List<MetsType.MetsHdr.AltRecordID> altRecordIDS = metsHdr.getAltRecordID();
    boolean found = false;
    if (altRecordIDS != null && altRecordIDS.size() != 0) {
      for (MetsType.MetsHdr.AltRecordID altRecordID : altRecordIDS) {
        String type = altRecordID.getTYPE();
        if (type.equals("PREVIOUSSUBMISSIONAGREEMENT")) {
          found = true;
        }
      }
      if (!found) {
        return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION,
          Message.createErrorMessage("metsHdr/altRecordID with the TYPE PREVIOUSREFERENCECODE not found in %1$s",
            metsName, isRootMets()),
          false, false);
      }
    }
    return new ReporterDetails();
  }

  /*
   * metsHdr/agent A wrapper element that enables to encode the name of the
   * organisation or person that originally created the data being transferred.
   * Please note that this might be dierent from the organisation which has been
   * charged with preparing and sending the SIP to the archives.
   */
  private ReporterDetails validateSIP9() {
    boolean found = false;
    int count = 0;
    if (agents != null && !agents.isEmpty()) {
      for (MetsType.MetsHdr.Agent agent : agents) {
        String role = agent.getROLE();
        if (role != null) {
          if (role.equals("ARCHIVIST")) {
            found = true;
            count++;
          }
        } else {
          return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION, Message.createErrorMessage(
            "metsHdr/agent/@ROLE can't be null, in %1$s the @ROLE is null.", metsName, isRootMets()), false, false);
        }
      }
      if (!found) {
        return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION, Message.createErrorMessage(
          "metsHdr/agent you can add agent with the ROLE ARCHIVIST in %1$s", metsName, isRootMets()), false, false);
      } else {
        if (count != 1) {
          return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION,
            Message.createErrorMessage("Can't have more than one metsHdr/agent with the ROLE ARCHIVIST in %1$s",
              metsName, isRootMets()),
            false, false);
        }
      }
    } else {
      return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION,
        Message.createErrorMessage("Doesn't have agent with the ROLE ARCHIVIST in %1$s", metsName, isRootMets()), false,
        false);
    }
    return new ReporterDetails();
  }

  /*
   * metsHdr/agent/@ROLE The role of the person(s) or institution(s) responsible
   * for the document/collection.
   */
  private ReporterDetails validateSIP10() {
    boolean found = false;
    int count = 0;
    if (agents != null && !agents.isEmpty()) {
      for (MetsType.MetsHdr.Agent agent : agents) {
        String role = agent.getROLE();
        if (role != null) {
          if (role.equals("ARCHIVIST")) {
            found = true;
            count++;
          }
        } else {
          return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION, Message.createErrorMessage(
            "metsHdr/agent/@ROLE can't be null, in %1$s @ROLE is null ", metsName, isRootMets()), false, false);
        }
      }
      if (!found) {
        return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION, Message.createErrorMessage(
          "metsHdr/agent you can add agent with the @ROLE ARCHIVIST in %1$s", metsName, isRootMets()), false, false);
      } else {
        if (count != 1) {
          return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION,
            Message.createErrorMessage("Can't have more than one metsHdr/agent with the @ROLE ARCHIVIST in %1$s",
              metsName, isRootMets()),
            false, false);
        }
      }
    } else {
      return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION,
        Message.createErrorMessage("Skipped in %1$s because metsHdr/agent does not exists", metsName, isRootMets()),
        true, true);
    }
    return new ReporterDetails();
  }

  /*
   * metsHdr/agent/@TYPE The type of the archival creator agent is “ORGANIZATION”
   * or “INDIVIDUAL”
   */
  private ReporterDetails validateSIP11() {
    if (agents != null && !agents.isEmpty()) {
      for (MetsType.MetsHdr.Agent agent : agents) {
        String role = agent.getROLE();
        if (role != null) {
          if (role.equals("ARCHIVIST")) {
            String type = agent.getTYPE();
            if (!type.equals("ORGANIZATION") && !type.equals("INDIVIDUAL")) {
              StringBuilder message = new StringBuilder();
              message.append("Value ").append(type).append(
                " in %1$s for metsHdr/agent/@TYPE when metsHdr/agent/@ROLE is ARCHIVIST isn't valid, must be ORGANIZATION or INDIVIDUAL ");
              return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION,
                Message.createErrorMessage(message.toString(), metsName, isRootMets()), false, false);
            }
          }
        } else {
          return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION, Message.createErrorMessage(
            "metsHdr/agent/@ROLE can't be null, in %1$s the value is null", metsName, isRootMets()), false, false);
        }
      }
    } else {
      return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION,
        Message.createErrorMessage("Skipped in %1$s because metsHdr/agent does not exists", metsName, isRootMets()),
        true, true);
    }
    return new ReporterDetails();
  }

  /*
   * metsHdr/agent/name The name of the organisation(s) that originally created
   * the data being transferred. Please note that this might be dierent from the
   * organisation which has been charged with preparing and sending the SIP to the
   * archives.
   */
  private ReporterDetails validateSIP12() {
    if (agents != null && !agents.isEmpty()) {
      for (MetsType.MetsHdr.Agent agent : agents) {
        String role = agent.getROLE();
        if (role != null) {
          if (role.equals("ARCHIVIST")) {
            String name = agent.getName();
            if (name == null) {
              return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION, Message.createErrorMessage(
                "metsHdr/agent/name can't be null, in %1$s the value is null", metsName, isRootMets()), false, false);
            }
          }
        } else {
          return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION, Message.createErrorMessage(
            "metsHdr/agent/@ROLE can't be null, in %1$s the value is null", metsName, isRootMets()), false, false);
        }
      }
    } else {
      return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION,
        Message.createErrorMessage("Skipped in %1$s because metsHdr/agent does not exists ", metsName, isRootMets()),
        true, true);
    }
    return new ReporterDetails();
  }

  /*
   * metsHdr/agent/note The archival creator agent has a note providing a unique
   * identification code for the archival creator.
   */
  private ReporterDetails validateSIP13() {
    if (agents != null && !agents.isEmpty()) {
      for (MetsType.MetsHdr.Agent agent : agents) {
        String role = agent.getROLE();
        if (role != null) {
          if (role.equals("ARCHIVIST")) {
            List<MetsType.MetsHdr.Agent.Note> notes = agent.getNote();
            if (notes != null && notes.size() != 0) {
              if (notes.size() != 1) {
                return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION, Message.createErrorMessage(
                  "metsHdr/agent/note in %1$s can't appear more than once ", metsName, isRootMets()), false, false);
              }
            } else {
              return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION,
                Message.createErrorMessage("You can add one metsHdr/agent/note in %1$s ", metsName, isRootMets()),
                false, false);
            }
          }
        } else {
          return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION, Message.createErrorMessage(
            "metsHdr/agent/@ROLE can't be null, in %1$s the @ROLE is null ", metsName, isRootMets()), false, false);
        }
      }
    } else {
      return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION,
        Message.createErrorMessage("Skipped in %1$s because metsHdr/agent does not exists", metsName, isRootMets()),
        true, true);
    }
    return new ReporterDetails();
  }

  /*
   * metsHdr/agent/note/@csip:NOTETYPE The archival creator agent note is typed
   * with the value of “IDENTIFICATIONCODE”.See also: Note type
   */
  private ReporterDetails validateSIP14() {
    if (agents != null && !agents.isEmpty()) {
      for (MetsType.MetsHdr.Agent agent : agents) {
        String role = agent.getROLE();
        if (role != null) {
          if (role.equals("ARCHIVIST")) {
            List<MetsType.MetsHdr.Agent.Note> notes = agent.getNote();
            if (notes != null && !notes.isEmpty()) {
              for (MetsType.MetsHdr.Agent.Note note : notes) {
                String noteType = note.getNOTETYPE();
                if (noteType == null || noteType.equals("")) {
                  return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION,
                    Message.createErrorMessage("metsHdr/agent/@csip:NOTETYPE in %1$s can't be null or empty", metsName,
                      isRootMets()),
                    false, false);
                }
              }
            }
          }
        } else {
          return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION,
            Message.createErrorMessage("metsHdr/agent/@ROLE in %1$s can't be null ", metsName, isRootMets()), false,
            false);
        }
      }
    } else {
      return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION,
        Message.createErrorMessage("Skipped in %1$s because metsHdr/@agent does not exists", metsName, isRootMets()),
        true, true);
    }
    return new ReporterDetails();
  }

  /*
   * metsHdr/agent The name of the organisation or person submitting the package
   * to the archive.
   */
  private ReporterDetails validateSIP15() {
    boolean found = false;
    int count = 0;
    if (agents != null && !agents.isEmpty()) {
      for (MetsType.MetsHdr.Agent agent : agents) {
        String role = agent.getROLE();
        if (role != null) {
          if (role.equals("OTHER")) {
            found = true;
            count++;
          }
        } else {
          return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION,
            Message.createErrorMessage("metsHdr/agent/@ROLE in %1$s can't be null ", metsName, isRootMets()), false,
            false);
        }
      }
      if (!found) {
        return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION,
          Message.createErrorMessage("Doesn't have metsHdr/agent with the ROLE OTHER in %1$s", metsName, isRootMets()),
          false, false);
      } else {
        if (count != 1) {
          return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION,
            Message.createErrorMessage("Can't have more than one metsHdr/agent in %1$s with the ROLE OTHER", metsName,
              isRootMets()),
            false, false);
        }
      }
    } else {
      return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION,
        Message.createErrorMessage("Doesn't have metsHdr/@agent in %1$s", metsName, isRootMets()), false, false);
    }
    return new ReporterDetails();
  }

  /*
   * metsHdr/agent/@ROLE The role of the person(s) or institution(s) responsible
   * for creating and/or submitting the package.
   */
  private ReporterDetails validateSIP16() {
    boolean found = false;
    int count = 0;
    if (agents != null && !agents.isEmpty()) {
      for (MetsType.MetsHdr.Agent agent : agents) {
        String role = agent.getROLE();
        if (role != null) {
          if (role.equals("OTHER")) {
            found = true;
            count++;
          }
        } else {
          return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION,
            Message.createErrorMessage("metsHdr/agent/@ROLE in %1$s can't be null ", metsName, isRootMets()), false,
            false);
        }
      }
      if (!found) {
        return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION,
          Message.createErrorMessage("metsHdr/agent with the @ROLE OTHER in %1$s not found ", metsName, isRootMets()),
          false, false);
      } else {
        if (count != 1) {
          return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION,
            Message.createErrorMessage("Can't have more than one metsHdr/agent in %1$s with the @ROLE OTHER", metsName,
              isRootMets()),
            false, false);
        }
      }
    } else {
      return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION,
        Message.createErrorMessage("Skipped in %1$s because metsHdr/@agent does not exists", metsName, isRootMets()),
        true, true);
    }
    return new ReporterDetails();
  }

  /*
   * metsHdr/agent/@TYPE The type of the submitting agent is “ORGANIZATION” or
   * “INDIVIDUAL”
   */
  private ReporterDetails validateSIP17() {
    if (agents != null && !agents.isEmpty()) {
      for (MetsType.MetsHdr.Agent agent : agents) {
        String role = agent.getROLE();
        if (role != null) {
          if (role.equals("OTHER")) {
            String type = agent.getTYPE();
            if (!type.equals("ORGANIZATION") && !type.equals("INDIVIDUAL")) {
              StringBuilder message = new StringBuilder();
              message.append("Value ").append(type).append(
                " in %1$s for metsHdr/agent/@TYPE when the metsHdr/agent/@ROLE is OTHER isn't valid, must be ORGANIZATION or INDIVIDUAL");
              return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION,
                Message.createErrorMessage(message.toString(), metsName, isRootMets()), false, false);
            }
          }
        } else {
          return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION,
            Message.createErrorMessage("metsHdr/agent/@ROLE can't be null ", metsName, isRootMets()), false, false);
        }
      }
    } else {
      return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION,
        Message.createErrorMessage("Skipped because metsHdr/@agent does not exists", metsName, isRootMets()), true,
        true);
    }
    return new ReporterDetails();
  }

  /*
   * metsHdr/agent/name Name of the organisation submitting the package to the
   * archive
   */
  private ReporterDetails validateSIP18() {
    if (agents != null && !agents.isEmpty()) {
      for (MetsType.MetsHdr.Agent agent : agents) {
        String role = agent.getROLE();
        if (role != null) {
          if (role.equals("OTHER")) {
            String name = agent.getName();
            if (name == null) {
              return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION,
                Message.createErrorMessage("metsHdr/agent/name in %1$s can't be null", metsName, isRootMets()), false,
                false);
            }
          }
        } else {
          return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION,
            Message.createErrorMessage("metsHdr/agent/@ROLE in %1$s can't be null ", metsName, isRootMets()), false,
            false);
        }
      }
    } else {
      return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION,
        Message.createErrorMessage("Skipped in %1$s because metsHdr/agent does not exists", metsName, isRootMets()),
        true, true);
    }
    return new ReporterDetails();
  }

  /*
   * metsHdr/agent/note The submitting agent has a note providing a unique
   * identification code for the archival creator.
   */
  private ReporterDetails validateSIP19() {
    if (agents != null && !agents.isEmpty()) {
      for (MetsType.MetsHdr.Agent agent : agents) {
        String role = agent.getROLE();
        if (role != null) {
          if (role.equals("OTHER")) {
            List<MetsType.MetsHdr.Agent.Note> notes = agent.getNote();
            if (notes != null && !notes.isEmpty()) {
              if (notes.size() != 1) {
                return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION, Message.createErrorMessage(
                  "metsHdr/agent/note in %1$s can't appear more than once ", metsName, isRootMets()), false, false);
              }
            } else {
              return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION,
                Message.createErrorMessage("You can add one metsHdr/agent/note in %1$s", metsName, isRootMets()), false,
                false);
            }
          }
        } else {
          return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION,
            Message.createErrorMessage("metsHdr/agent/@ROLE in %1$s can't be null ", metsName, isRootMets()), false,
            false);
        }
      }
    } else {
      return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION,
        Message.createErrorMessage("Skipped in %1$s because metsHdr/agent does not exists", metsName, isRootMets()),
        true, true);
    }
    return new ReporterDetails();
  }

  /*
   * metsHdr/agent/note/@csip:NOTETYPE The submitting agent note is typed with the
   * value of “IDENTIFICATIONCODE”.See also: Note type
   */
  private ReporterDetails validateSIP20() {
    if (agents != null && !agents.isEmpty()) {
      for (MetsType.MetsHdr.Agent agent : agents) {
        String role = agent.getROLE();
        if (role != null) {
          if (role.equals("OTHER")) {
            List<MetsType.MetsHdr.Agent.Note> notes = agent.getNote();
            if (notes != null && !notes.isEmpty()) {
              for (MetsType.MetsHdr.Agent.Note note : notes) {
                String noteType = note.getNOTETYPE();
                if (noteType == null || noteType.equals("")) {
                  return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION,
                    Message.createErrorMessage("metsHdr/agent/@csip:NOTETYPE in %1$s can't be null or empty", metsName,
                      isRootMets()),
                    false, false);
                }
              }
            }
          }
        } else {
          return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION,
            Message.createErrorMessage("metsHdr/agent/@ROLE in %1$s can't be null ", metsName, isRootMets()), false,
            false);
        }
      }
    } else {
      return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION,
        Message.createErrorMessage("Skipped in %1$s because metsHdr/agent does not exists", metsName, isRootMets()),
        true, true);
    }
    return new ReporterDetails();
  }

  /*
   * metsHdr/agent Contact person for the submission.
   */
  private ReporterDetails validateSIP21() {
    boolean found = false;
    if (agents != null && !agents.isEmpty()) {
      for (MetsType.MetsHdr.Agent agent : agents) {
        String role = agent.getROLE();
        if (role != null) {
          if (role.equals("CREATOR")) {
            found = true;
          }
        } else {
          return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION,
            Message.createErrorMessage("metsHdr/agent/@ROLE in %1$s can't be null ", metsName, isRootMets()), false,
            false);
        }
      }
      if (!found) {
        return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION, Message.createErrorMessage(
          "metsHdr/agent you can add agent with the @ROLE CREATOR in %1$s ", metsName, isRootMets()), false, false);
      }
    } else {
      return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION,
        Message.createErrorMessage("Skipped in %1$s because metsHdr/@agent does not exists", metsName, isRootMets()),
        true, true);
    }
    return new ReporterDetails();
  }

  /*
   * metsHdr/agent/@ROLE The role of the contact person is “CREATOR”.
   */
  private ReporterDetails validateSIP22() {
    boolean found = false;
    if (agents != null && !agents.isEmpty()) {
      for (MetsType.MetsHdr.Agent agent : agents) {
        String role = agent.getROLE();
        if (role != null) {
          if (role.equals("CREATOR")) {
            found = true;
          }
        } else {
          return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION,
            Message.createErrorMessage("metsHdr/agent/@ROLE in %1$s can't be null ", metsName, isRootMets()), false,
            false);
        }
      }
      if (!found) {
        return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION, Message.createErrorMessage(
          "metsHdr/agent you can add agent with the @ROLE CREATOR in %1$s ", metsName, isRootMets()), false, false);
      }
    } else {
      return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION,
        Message.createErrorMessage("Skipped in %1$s because metsHdr/@gent does not exists", metsName, isRootMets()),
        true, true);
    }
    return new ReporterDetails();
  }

  /*
   * metsHdr/agent/@TYPE The type of the contact person agent is “INDIVIDUAL”
   */
  private ReporterDetails validateSIP23() {
    if (agents != null && !agents.isEmpty()) {
      for (MetsType.MetsHdr.Agent agent : agents) {
        String role = agent.getROLE();
        if (role != null) {
          if (role.equals("CREATOR") && (agent.getOTHERTYPE() != null && !agent.getOTHERTYPE().equals("SOFTWARE"))) {
            String type = agent.getTYPE();
            if (!type.equals("INDIVIDUAL")) {
              StringBuilder message = new StringBuilder();
              message.append("Value ").append(type).append(
                " in %1$s for metsHdr/agent/@TYPE isn't valid, when the metsHdr/agent/@ROLE is CREATOR and  metsHdr/agent/@OTHERTYPE isn't SOFTWARE metsHdr/agent/@TYPE must be INDIVIDUAL");
              return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION,
                Message.createErrorMessage(message.toString(), metsName, isRootMets()), false, false);
            }
          }
        } else {
          return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION,
            Message.createErrorMessage("metsHdr/agent/@ROLE in %1$s can't be null ", metsName, isRootMets()), false,
            false);
        }
      }
    } else {
      return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION,
        Message.createErrorMessage("Skipped in %1$s because metsHdr/@agent does not exists", metsName, isRootMets()),
        true, true);
    }
    return new ReporterDetails();
  }

  /*
   * metsHdr/agent/name Name of the contact person.
   */
  private ReporterDetails validateSIP24() {
    if (agents != null && !agents.isEmpty()) {
      for (MetsType.MetsHdr.Agent agent : agents) {
        String role = agent.getROLE();
        if (role != null) {
          if (role.equals("CREATOR")) {
            String name = agent.getName();
            if (name == null) {
              return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION,
                Message.createErrorMessage("metsHdr/agent/name in %1$s can't be null", metsName, isRootMets()), false,
                false);
            }
          }
        } else {
          return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION,
            Message.createErrorMessage("metsHdr/agent/@ROLE in %1$s can't be null ", metsName, isRootMets()), false,
            false);
        }
      }
    } else {
      return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION,
        Message.createErrorMessage("Skipped in %1$s because metsHdr/@agent does not exists", metsName, isRootMets()),
        true, true);
    }
    return new ReporterDetails();
  }

  /*
   * metsHdr/agent/note The submitting agent has one or more notes giving the
   * contact information
   */
  private ReporterDetails validateSIP25() {
    if (agents != null && !agents.isEmpty()) {
      for (MetsType.MetsHdr.Agent agent : agents) {
        String role = agent.getROLE();
        if (role != null) {
          if (role.equals("CREATOR")) {
            List<MetsType.MetsHdr.Agent.Note> notes = agent.getNote();
            if (notes == null || notes.isEmpty()) {
              return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION,
                Message.createErrorMessage("can add contact information in one or more metsHdr/agent/note in %1$s ",
                  metsName, isRootMets()),
                false, false);
            }
          }
        } else {
          return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION,
            Message.createErrorMessage("metsHdr/agent/@ROLE in %1$s can't be null ", metsName, isRootMets()), false,
            false);
        }
      }
    }
    return new ReporterDetails();

  }

  /*
   * metsHdr/agent The organisation or person that preserves the package.
   */
  private ReporterDetails validateSIP26() {
    boolean found = false;
    int count = 0;
    if (agents != null && !agents.isEmpty()) {
      for (MetsType.MetsHdr.Agent agent : agents) {
        String role = agent.getROLE();
        if (role != null) {
          if (role.equals("PRESERVATION")) {
            found = true;
            count++;
          }
        } else {
          return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION,
            Message.createErrorMessage("metsHdr/agent/@ROLE in %1$s can't be null ", metsName, isRootMets()), false,
            false);
        }
      }
      if (!found) {
        return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION, Message.createErrorMessage(
          "Does not have metsHdr/agent with the ROLE PRESERVATION in %1$s ", metsName, isRootMets()), false, false);
      } else {
        if (count != 1) {
          return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION,
            Message.createErrorMessage("Can't have more than one metsHdr/agent with the ROLE PRESERVATION in %1$s",
              metsName, isRootMets()),
            false, false);
        }
      }
    } else {
      return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION,
        Message.createErrorMessage("Skipped in %1$s because metsHdr/@agent does not exists", metsName, isRootMets()),
        true, true);
    }
    return new ReporterDetails();
  }

  /*
   * metsHdr/agent/@ROLE The role of the preservation agent is “PRESERVATION”.
   */
  private ReporterDetails validateSIP27() {
    boolean found = false;
    int count = 0;
    if (agents != null && !agents.isEmpty()) {
      for (MetsType.MetsHdr.Agent agent : agents) {
        String role = agent.getROLE();
        if (role != null) {
          if (role.equals("PRESERVATION")) {
            found = true;
            count++;
          }
        } else {
          return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION,
            Message.createErrorMessage("metsHdr/agent/@ROLE in %1$s can't be null ", metsName, isRootMets()), false,
            false);
        }
      }
      if (!found) {
        return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION, Message.createErrorMessage(
          "metsHdr/agent you can add agent with the ROLE PRESERVATION in %1$s ", metsName, isRootMets()), false, false);
      } else {
        if (count != 1) {
          return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION,
            Message.createErrorMessage("Can't have more than one metsHdr/agent with the ROLE PRESERVATION in %1$s",
              metsName, isRootMets()),
            false, false);
        }
      }
    } else {
      return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION,
        Message.createErrorMessage("Skipped in %1$s because metsHdr/agent does not exists", metsName, isRootMets()),
        true, true);
    }
    return new ReporterDetails();
  }

  /*
   * metsHdr/agent/@TYPE The type of the submitting agent is “ORGANIZATION”.
   */
  private ReporterDetails validateSIP28() {
    if (agents != null && !agents.isEmpty()) {
      for (MetsType.MetsHdr.Agent agent : agents) {
        String role = agent.getROLE();
        if (role != null) {
          if (role.equals("PRESERVATION")) {
            String type = agent.getTYPE();
            if (!type.equals("ORGANIZATION")) {
              StringBuilder message = new StringBuilder();
              message.append("Value ").append(type).append(
                " in %1$s for metsHdr/agent/@TYPE isn't valid, When the metsHdr/agent/@ROLE is PRESERVATION  metsHdr/agent/@TYPE must be ORGANIZATION");
              return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION,
                Message.createErrorMessage(message.toString(), metsName, isRootMets()), false, false);
            }
          }
        } else {
          return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION,
            Message.createErrorMessage("metsHdr/agent/@ROLE in %1$s can't be null ", metsName, isRootMets()), false,
            false);
        }
      }
    } else {
      return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION,
        Message.createErrorMessage("Skipped in %1$s because metsHdr/agent does not exists", metsName, isRootMets()),
        true, true);
    }
    return new ReporterDetails();
  }

  /*
   * metsHdr/agent/name Name of the organisation preserving the package.
   */
  private ReporterDetails validateSIP29() {
    if (agents != null && !agents.isEmpty()) {
      for (MetsType.MetsHdr.Agent agent : agents) {
        String role = agent.getROLE();
        if (role != null) {
          if (role.equals("PRESERVATION")) {
            String name = agent.getName();
            if (name == null) {
              return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION,
                Message.createErrorMessage("metsHdr/agent/name in %1$s can't be null", metsName, isRootMets()), false,
                false);
            }
          }
        } else {
          return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION,
            Message.createErrorMessage("metsHdr/agent/@ROLE in %1$s can't be null ", metsName, isRootMets()), false,
            false);
        }
      }
    } else {
      return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION,
        Message.createErrorMessage("Skipped in %1$s because metsHdr/@agent does not exists", metsName, isRootMets()),
        true, true);
    }
    return new ReporterDetails();
  }

  /*
   * metsHdr/agent/note The preservation agent has a note providing a unique
   * identification code for the archival creator.
   */
  private ReporterDetails validateSIP30() {
    if (agents != null && !agents.isEmpty()) {
      for (MetsType.MetsHdr.Agent agent : agents) {
        String role = agent.getROLE();
        if (role != null) {
          if (role.equals("PRESERVATION")) {
            List<MetsType.MetsHdr.Agent.Note> notes = agent.getNote();
            if (notes != null && !notes.isEmpty()) {
              if (notes.size() != 1) {
                return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION, Message.createErrorMessage(
                  "metsHdr/agent/note in %1$s can't be more than one ", metsName, isRootMets()), false, false);
              }
            } else {
              return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION,
                Message.createErrorMessage("You can add one metsHdr/agent/note in %1$s", metsName, isRootMets()), false,
                false);
            }
          }
        } else {
          return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION,
            Message.createErrorMessage("metsHdr/agent/@ROLE in %1$s can't be null ", metsName, isRootMets()), false,
            false);
        }
      }
    } else {
      return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION,
        Message.createErrorMessage("Skipped in %1$s because metsHdr/@agent does not exists", metsName, isRootMets()),
        true, true);
    }
    return new ReporterDetails();
  }

  /*
   * metsHdr/agent/note/@csip:NOTETYPE The preservation agent note is typed with
   * the value of “IDENTIFICATIONCODE”.See also: Note type
   */
  private ReporterDetails validateSIP31() {
    if (agents != null && !agents.isEmpty()) {
      for (MetsType.MetsHdr.Agent agent : agents) {
        String role = agent.getROLE();
        if (role != null) {
          if (role.equals("PRESERVATION")) {
            List<MetsType.MetsHdr.Agent.Note> notes = agent.getNote();
            if (notes != null && notes.size() != 0) {
              for (MetsType.MetsHdr.Agent.Note note : notes) {
                String noteType = note.getNOTETYPE();
                if (noteType == null || noteType.equals("")) {
                  return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION,
                    Message.createErrorMessage("metsHdr/agent/@csip:NOTETYPE in %1$s can't be null or empty", metsName,
                      isRootMets()),
                    false, false);
                }
              }
            }
          }
        } else {
          return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION,
            Message.createErrorMessage("metsHdr/agent/@ROLE in %1$s can't be null ", metsName, isRootMets()), false,
            false);
        }
      }
    } else {
      return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION,
        Message.createErrorMessage("Skipped in %1$s because metsHdr/@agent does not exists", metsName, isRootMets()),
        true, true);
    }
    return new ReporterDetails();
  }
}
