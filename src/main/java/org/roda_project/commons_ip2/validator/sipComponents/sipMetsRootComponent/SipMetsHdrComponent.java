package org.roda_project.commons_ip2.validator.sipComponents.sipMetsRootComponent;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;

import org.roda_project.commons_ip2.mets_v1_12.beans.MetsType;
import org.roda_project.commons_ip2.validator.common.ControlledVocabularyParser;
import org.roda_project.commons_ip2.validator.component.MetsValidatorImpl;
import org.roda_project.commons_ip2.validator.constants.Constants;
import org.roda_project.commons_ip2.validator.constants.ConstantsSIPspec;
import org.roda_project.commons_ip2.validator.reporter.ReporterDetails;
import org.roda_project.commons_ip2.validator.state.MetsValidatorState;
import org.roda_project.commons_ip2.validator.state.StructureValidatorState;
import org.roda_project.commons_ip2.validator.utils.Message;
import org.roda_project.commons_ip2.validator.utils.ResultsUtils;
import org.xml.sax.SAXException;

/**
 * @author João Gomes <jgomes@keep.pt>
 */
public class SipMetsHdrComponent extends MetsValidatorImpl {
  private final String moduleName;
  private boolean isToValidateAgents = true;
  private boolean isToValidateMetsHdr = true;
  private MetsType.MetsHdr metsHdr;
  private List<MetsType.MetsHdr.Agent> agents;
  private List<String> recordsStatus;

  public SipMetsHdrComponent() throws IOException, ParserConfigurationException, SAXException {
    this.moduleName = Constants.SIP_MODULE_NAME_2;
    this.recordsStatus = ControlledVocabularyParser.parse(Constants.PATH_RESOURCES_CSIP_VOCABULARY_RECORD_STATUS);
  }

  public void setIsToValidateMetsHdr(boolean isToValidateMetsHdr) {
    this.isToValidateMetsHdr = isToValidateMetsHdr;
  }

  public void setIsToValidateAgents(boolean isToValidateAgents) {
    this.isToValidateAgents = isToValidateAgents;
  }

  @Override
  public Map<String, ReporterDetails> validate(StructureValidatorState structureValidatorState,
    MetsValidatorState metsValidatorState) {
    Map<String, ReporterDetails> results = new HashMap<>();
    metsHdr = metsValidatorState.getMets().getMetsHdr();
    agents = metsValidatorState.getMets().getMetsHdr().getAgent();
    if (isToValidateMetsHdr) {
      /* SIP3 */
      notifyObserversValidationStarted(moduleName, ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP3_ID);
      ResultsUtils.addResult(results, ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP3_ID,
        validateSIP3(metsValidatorState).setSpecification(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION));

      /* SIP4 */
      notifyObserversValidationStarted(moduleName, ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP4_ID);
      ResultsUtils.addResult(results, ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP4_ID,
        validateSIP4(metsValidatorState).setSpecification(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION));

      /* SIP5 */
      notifyObserversValidationStarted(moduleName, ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP5_ID);
      ResultsUtils.addResult(results, ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP5_ID,
        validateSIP5(metsValidatorState).setSpecification(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION));

      /* SIP6 */
      notifyObserversValidationStarted(moduleName, ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP6_ID);
      ResultsUtils.addResult(results, ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP6_ID,
        validateSIP6(metsValidatorState).setSpecification(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION));

      /* SIP7 */
      notifyObserversValidationStarted(moduleName, ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP7_ID);
      ResultsUtils.addResult(results, ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP7_ID,
        validateSIP7(metsValidatorState).setSpecification(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION));

      /* SIP8 */
      notifyObserversValidationStarted(moduleName, ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP8_ID);
      ResultsUtils.addResult(results, ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP8_ID,
        validateSIP8(metsValidatorState).setSpecification(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION));

      if (isToValidateAgents && metsValidatorState.isRootMets()) {
        /* SIP9 */
        notifyObserversValidationStarted(moduleName, ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP9_ID);
        ResultsUtils.addResult(results, ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP9_ID,
          validateSIP9(metsValidatorState).setSpecification(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION));

        if (ResultsUtils.isResultValid(results, ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP9_ID)) {
          /* SIP10 */
          notifyObserversValidationStarted(moduleName, ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP10_ID);
          ResultsUtils.addResult(results, ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP10_ID,
            validateSIP10(metsValidatorState).setSpecification(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION));

          /* SIP11 */
          notifyObserversValidationStarted(moduleName, ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP11_ID);
          ResultsUtils.addResult(results, ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP11_ID,
            validateSIP11(metsValidatorState).setSpecification(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION));

          /* SIP12 */
          notifyObserversValidationStarted(moduleName, ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP12_ID);
          ResultsUtils.addResult(results, ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP12_ID,
            validateSIP12(metsValidatorState).setSpecification(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION));

          /* SIP13 */
          notifyObserversValidationStarted(moduleName, ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP13_ID);
          ResultsUtils.addResult(results, ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP13_ID,
            validateSIP13(metsValidatorState).setSpecification(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION));

          /* SIP14 */
          notifyObserversValidationStarted(moduleName, ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP14_ID);
          ResultsUtils.addResult(results, ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP14_ID,
            validateSIP14(metsValidatorState).setSpecification(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION));
        } else {
          String message = Message.createErrorMessage(
            "SKIPPED in %1$s because mets/metsHdr/agent with the role ARCHIVIST doesn't exist",
            metsValidatorState.getMetsName(), metsValidatorState.isRootMets());

          ResultsUtils.addResults(results,
            new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION, message, true, true),
            ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP10_ID,
            ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP11_ID,
            ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP12_ID,
            ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP13_ID,
            ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP14_ID);
        }

        /* SIP15 */
        notifyObserversValidationStarted(moduleName, ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP15_ID);
        ResultsUtils.addResult(results, ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP15_ID,
          validateSIP15(metsValidatorState).setSpecification(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION));

        if (ResultsUtils.isResultValid(results, ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP15_ID)) {
          /* SIP16 */
          notifyObserversValidationStarted(moduleName, ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP16_ID);
          ResultsUtils.addResult(results, ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP16_ID,
            validateSIP16(metsValidatorState).setSpecification(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION));

          /* SIP17 */
          notifyObserversValidationStarted(moduleName, ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP17_ID);
          ResultsUtils.addResult(results, ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP17_ID,
            validateSIP17(metsValidatorState).setSpecification(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION));

          /* SIP18 */
          notifyObserversValidationStarted(moduleName, ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP18_ID);
          ResultsUtils.addResult(results, ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP18_ID,
            validateSIP18(metsValidatorState).setSpecification(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION));

          /* SIP19 */
          notifyObserversValidationStarted(moduleName, ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP19_ID);
          ResultsUtils.addResult(results, ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP19_ID,
            validateSIP19(metsValidatorState).setSpecification(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION));

          /* SIP20 */
          notifyObserversValidationStarted(moduleName, ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP20_ID);
          ResultsUtils.addResult(results, ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP20_ID,
            validateSIP20(metsValidatorState).setSpecification(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION));

        } else {
          String message = Message.createErrorMessage(
            "SKIPPED because mets/metsHdr/agent with the role CREATOR doesn't exist", metsValidatorState.getMetsName(),
            metsValidatorState.isRootMets());

          ResultsUtils.addResults(results,
            new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION, message, true, true),
            ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP16_ID,
            ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP17_ID,
            ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP18_ID,
            ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP19_ID,
            ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP20_ID);
        }

        /* SIP21 */
        notifyObserversValidationStarted(moduleName, ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP21_ID);
        ResultsUtils.addResult(results, ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP21_ID,
          validateSIP21(metsValidatorState).setSpecification(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION));

        if (ResultsUtils.isResultValid(results, ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP21_ID)) {
          /* SIP22 */
          notifyObserversValidationStarted(moduleName, ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP22_ID);
          ResultsUtils.addResult(results, ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP22_ID,
            validateSIP22(metsValidatorState).setSpecification(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION));

          /* SIP23 */
          notifyObserversValidationStarted(moduleName, ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP20_ID);
          ResultsUtils.addResult(results, ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP23_ID,
            validateSIP23(metsValidatorState).setSpecification(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION));

          /* SIP24 */
          notifyObserversValidationStarted(moduleName, ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP24_ID);
          ResultsUtils.addResult(results, ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP24_ID,
            validateSIP24(metsValidatorState).setSpecification(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION));

          /* SIP25 */
          notifyObserversValidationStarted(moduleName, ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP25_ID);
          ResultsUtils.addResult(results, ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP25_ID,
            validateSIP25(metsValidatorState).setSpecification(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION));

        } else {
          String message = Message.createErrorMessage(
            "SKIPPED in %1$s because mets/metsHdr/agent with the role OTHER doesn't exist",
            metsValidatorState.getMetsName(), metsValidatorState.isRootMets());

          ResultsUtils.addResults(results,
            new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION, message, true, true),
            ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP22_ID,
            ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP23_ID,
            ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP24_ID,
            ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP25_ID);
        }

        /* SIP26 */
        notifyObserversValidationStarted(moduleName, ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP26_ID);
        ResultsUtils.addResult(results, ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP26_ID,
          validateSIP26(metsValidatorState).setSpecification(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION));

        if (ResultsUtils.isResultValid(results, ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP26_ID)) {
          /* SIP27 */
          notifyObserversValidationStarted(moduleName, ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP27_ID);
          ResultsUtils.addResult(results, ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP27_ID,
            validateSIP27(metsValidatorState).setSpecification(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION));

          /* SIP28 */
          notifyObserversValidationStarted(moduleName, ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP28_ID);
          ResultsUtils.addResult(results, ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP28_ID,
            validateSIP28(metsValidatorState).setSpecification(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION));

          /* SIP29 */
          notifyObserversValidationStarted(moduleName, ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP29_ID);
          ResultsUtils.addResult(results, ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP29_ID,
            validateSIP29(metsValidatorState).setSpecification(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION));

          /* SIP30 */
          notifyObserversValidationStarted(moduleName, ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP30_ID);
          ResultsUtils.addResult(results, ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP30_ID,
            validateSIP30(metsValidatorState).setSpecification(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION));

          /* SIP31 */
          notifyObserversValidationStarted(moduleName, ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP31_ID);
          ResultsUtils.addResult(results, ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP31_ID,
            validateSIP31(metsValidatorState).setSpecification(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION));

        } else {
          String message = Message.createErrorMessage(
            "SKIPPED in %1$s because mets/metsHdr/agent with the role PRESERVATION doesn't exist",
            metsValidatorState.getMetsName(), metsValidatorState.isRootMets());

          ResultsUtils.addResults(results,
            new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION, message, true, true),
            ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP27_ID,
            ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP28_ID,
            ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP29_ID,
            ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP30_ID,
            ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP31_ID);
        }
      }
    } else {
      String message = Message.createErrorMessage("SKIPPED because mets/metsHdr doesn't exist ",
        metsValidatorState.getMetsName(), metsValidatorState.isRootMets());

      ResultsUtils.addResults(results,
        new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION, message, true, true),
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
   * metsHdr/@RECORDSTATUS A way of indicating the status of the package and to
   * instruct the OAIS on how to properly handle the package. If not set, the
   * expected behaviour is equal to NEW.See also: Package status
   */

  private ReporterDetails validateSIP3(MetsValidatorState metsValidatorState) {
    String recordStatus = metsHdr.getRECORDSTATUS();
    if (recordStatus != null && !recordsStatus.contains(recordStatus)) {
      StringBuilder message = new StringBuilder();
      message.append("Value ").append(recordStatus).append("for metsHdr/@RECORDSTATUS value isn't valid in %1$s");
      return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION, Message.createErrorMessage(
        message.toString(), metsValidatorState.getMetsName(), metsValidatorState.isRootMets()), false, false);
    }
    return new ReporterDetails();
  }

  /*
   * metsHdr/@csip:OAISPACKAGETYPE
   *
   * @csip:OAISPACKAGETYPE is used with the value “SIP”.See also: OAIS Package
   * type
   */

  private ReporterDetails validateSIP4(MetsValidatorState metsValidatorState) {
    String oaisPackageType = metsHdr.getOAISPACKAGETYPE();
    if (oaisPackageType == null) {
      return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION,
        Message.createErrorMessage("metsHdr/@csip:OAISPACKAGETYPE can't be null, in %1$s",
          metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
        false, false);
    }

    if (!oaisPackageType.equals("SIP")) {
      return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION,
        Message.createErrorMessage("metsHdr/@csip:OAISPACKAGETYPE must be used with the value SIP, in %1$s",
          metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
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

  private ReporterDetails validateSIP5(MetsValidatorState metsValidatorState) {
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
            metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
          false, false);
      }
      if (count > 1) {
        return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION,
          Message.createErrorMessage(
            "Can't have more than one metsHdr/altRecordID of the TYPE SUBMISSIONAGREEMENT, in %1$s exist more than one",
            metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
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
  private ReporterDetails validateSIP6(MetsValidatorState metsValidatorState) {
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
            "metsHdr/altRecordID with the @TYPE “PREVIOUSSUBMISSIONAGREEMENT” not found in %1$s",
            metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
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
  private ReporterDetails validateSIP7(MetsValidatorState metsValidatorState) {
    List<MetsType.MetsHdr.AltRecordID> altRecordIDS = metsHdr.getAltRecordID();
    boolean found = false;
    int count = 0;
    if (altRecordIDS != null && !altRecordIDS.isEmpty()) {
      for (MetsType.MetsHdr.AltRecordID altRecordID : altRecordIDS) {
        String type = altRecordID.getTYPE();
        if (type.equals("REFERENCECODE")) {
          found = true;
          count++;
        }
      }
      if (!found) {
        return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION,
          Message.createErrorMessage("metsHdr/altRecordID with the @TYPE “REFERENCECODE” not found in %1$s",
            metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
          false, false);
      }
      if (count > 1) {
        return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION,
          Message.createErrorMessage("Can't have more than one metsHdr/altRecordID of the type REFERENCECODE",
            metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
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
  private ReporterDetails validateSIP8(MetsValidatorState metsValidatorState) {
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
          Message.createErrorMessage("metsHdr/altRecordID with the TYPE PREVIOUSREFERENCECODE not found in %1$s",
            metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
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
  private ReporterDetails validateSIP9(MetsValidatorState metsValidatorState) {
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
          return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION,
            Message.createErrorMessage("metsHdr/agent/@ROLE can't be null, in %1$s the @ROLE is null.",
              metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
            false, false);
        }
      }
      if (!found) {
        return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION,
          Message.createErrorMessage("metsHdr/agent you can add agent with the ROLE ARCHIVIST in %1$s",
            metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
          false, false);
      } else {
        if (count != 1) {
          return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION,
            Message.createErrorMessage("Can't have more than one metsHdr/agent with the ROLE ARCHIVIST in %1$s",
              metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
            false, false);
        }
      }
    } else {
      return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION,
        Message.createErrorMessage("Doesn't have agent with the ROLE ARCHIVIST in %1$s",
          metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
        false, false);
    }
    return new ReporterDetails();
  }

  /*
   * metsHdr/agent/@ROLE The role of the person(s) or institution(s) responsible
   * for the document/collection.
   */
  private ReporterDetails validateSIP10(MetsValidatorState metsValidatorState) {
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
          return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION,
            Message.createErrorMessage("metsHdr/agent/@ROLE can't be null, in %1$s @ROLE is null ",
              metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
            false, false);
        }
      }
      if (!found) {
        return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION,
          Message.createErrorMessage("metsHdr/agent you can add agent with the @ROLE ARCHIVIST in %1$s",
            metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
          false, false);
      } else {
        if (count != 1) {
          return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION,
            Message.createErrorMessage("Can't have more than one metsHdr/agent with the @ROLE ARCHIVIST in %1$s",
              metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
            false, false);
        }
      }
    } else {
      return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION,
        Message.createErrorMessage("Skipped in %1$s because metsHdr/agent does not exists",
          metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
        true, true);
    }
    return new ReporterDetails();
  }

  /*
   * metsHdr/agent/@TYPE The type of the archival creator agent is “ORGANIZATION”
   * or “INDIVIDUAL”
   */
  private ReporterDetails validateSIP11(MetsValidatorState metsValidatorState) {
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
              return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION, Message.createErrorMessage(
                message.toString(), metsValidatorState.getMetsName(), metsValidatorState.isRootMets()), false, false);
            }
          }
        } else {
          return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION,
            Message.createErrorMessage("metsHdr/agent/@ROLE can't be null, in %1$s the value is null",
              metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
            false, false);
        }
      }
    } else {
      return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION,
        Message.createErrorMessage("Skipped in %1$s because metsHdr/agent does not exists",
          metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
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
  private ReporterDetails validateSIP12(MetsValidatorState metsValidatorState) {
    if (agents != null && !agents.isEmpty()) {
      for (MetsType.MetsHdr.Agent agent : agents) {
        String role = agent.getROLE();
        if (role != null) {
          if (role.equals("ARCHIVIST")) {
            String name = agent.getName();
            if (name == null) {
              return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION,
                Message.createErrorMessage("metsHdr/agent/name can't be null, in %1$s the value is null",
                  metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
                false, false);
            }
          }
        } else {
          return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION,
            Message.createErrorMessage("metsHdr/agent/@ROLE can't be null, in %1$s the value is null",
              metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
            false, false);
        }
      }
    } else {
      return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION,
        Message.createErrorMessage("Skipped in %1$s because metsHdr/agent does not exists ",
          metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
        true, true);
    }
    return new ReporterDetails();
  }

  /*
   * metsHdr/agent/note The archival creator agent has a note providing a unique
   * identification code for the archival creator.
   */
  private ReporterDetails validateSIP13(MetsValidatorState metsValidatorState) {
    if (agents != null && !agents.isEmpty()) {
      for (MetsType.MetsHdr.Agent agent : agents) {
        String role = agent.getROLE();
        if (role != null) {
          if (role.equals("ARCHIVIST")) {
            List<MetsType.MetsHdr.Agent.Note> notes = agent.getNote();
            if (notes != null && !notes.isEmpty()) {
              if (notes.size() != 1) {
                return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION,
                  Message.createErrorMessage("metsHdr/agent/note in %1$s can't appear more than once ",
                    metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
                  false, false);
              }
            } else {
              return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION,
                Message.createErrorMessage("You can add one metsHdr/agent/note in %1$s ",
                  metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
                false, false);
            }
          }
        } else {
          return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION,
            Message.createErrorMessage("metsHdr/agent/@ROLE can't be null, in %1$s the @ROLE is null ",
              metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
            false, false);
        }
      }
    } else {
      return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION,
        Message.createErrorMessage("Skipped in %1$s because metsHdr/agent does not exists",
          metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
        true, true);
    }
    return new ReporterDetails();
  }

  /*
   * metsHdr/agent/note/@csip:NOTETYPE The archival creator agent note is typed
   * with the value of “IDENTIFICATIONCODE”.See also: Note type
   */
  private ReporterDetails validateSIP14(MetsValidatorState metsValidatorState) {
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
                    Message.createErrorMessage("metsHdr/agent/@csip:NOTETYPE in %1$s can't be null or empty",
                      metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
                    false, false);
                }
              }
            }
          }
        } else {
          return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION,
            Message.createErrorMessage("metsHdr/agent/@ROLE in %1$s can't be null ", metsValidatorState.getMetsName(),
              metsValidatorState.isRootMets()),
            false, false);
        }
      }
    } else {
      return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION,
        Message.createErrorMessage("Skipped in %1$s because metsHdr/@agent does not exists",
          metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
        true, true);
    }
    return new ReporterDetails();
  }

  /*
   * metsHdr/agent The name of the organisation or person submitting the package
   * to the archive.
   */
  private ReporterDetails validateSIP15(MetsValidatorState metsValidatorState) {
    boolean found = false;
    int count = 0;
    if (agents != null && !agents.isEmpty()) {
      for (MetsType.MetsHdr.Agent agent : agents) {
        String role = agent.getROLE();
        if (role != null) {
          if (role.equals("OTHER") && agent.getOTHERROLE().equals("SUBMITTER")) {
            found = true;
            count++;
          }
        } else {
          return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION,
            Message.createErrorMessage("metsHdr/agent/@ROLE in %1$s can't be null ", metsValidatorState.getMetsName(),
              metsValidatorState.isRootMets()),
            false, false);
        }
      }
      if (!found) {
        return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION,
          Message.createErrorMessage("Doesn't have metsHdr/agent with the ROLE OTHER in %1$s",
            metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
          false, false);
      } else {
        if (count != 1) {
          return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION,
            Message.createErrorMessage("Can't have more than one metsHdr/agent in %1$s with the ROLE OTHER",
              metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
            false, false);
        }
      }
    } else {
      return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION,
        Message.createErrorMessage("Doesn't have metsHdr/@agent in %1$s", metsValidatorState.getMetsName(),
          metsValidatorState.isRootMets()),
        false, false);
    }
    return new ReporterDetails();
  }

  /*
   * metsHdr/agent/@ROLE The role of the person(s) or institution(s) responsible
   * for creating and/or submitting the package.
   */
  private ReporterDetails validateSIP16(MetsValidatorState metsValidatorState) {
    boolean found = false;
    int count = 0;
    if (agents != null && !agents.isEmpty()) {
      for (MetsType.MetsHdr.Agent agent : agents) {
        String role = agent.getROLE();
        if (role != null) {
          if (role.equals("OTHER") && agent.getOTHERROLE().equals("SUBMITTER")) {
            found = true;
            count++;
          }
        } else {
          return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION,
            Message.createErrorMessage("metsHdr/agent/@ROLE in %1$s can't be null ", metsValidatorState.getMetsName(),
              metsValidatorState.isRootMets()),
            false, false);
        }
      }
      if (!found) {
        return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION,
          Message.createErrorMessage("metsHdr/agent with the @ROLE OTHER in %1$s not found ",
            metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
          false, false);
      } else {
        if (count != 1) {
          return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION,
            Message.createErrorMessage("Can't have more than one metsHdr/agent in %1$s with the @ROLE OTHER",
              metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
            false, false);
        }
      }
    } else {
      return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION,
        Message.createErrorMessage("Skipped in %1$s because metsHdr/@agent does not exists",
          metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
        true, true);
    }
    return new ReporterDetails();
  }

  /*
   * metsHdr/agent/@TYPE The type of the submitting agent is “ORGANIZATION” or
   * “INDIVIDUAL”
   */
  private ReporterDetails validateSIP17(MetsValidatorState metsValidatorState) {
    if (agents != null && !agents.isEmpty()) {
      for (MetsType.MetsHdr.Agent agent : agents) {
        String role = agent.getROLE();
        if (role != null) {
          if (role.equals("OTHER") && agent.getOTHERROLE().equals("SUBMITTER")) {
            String type = agent.getTYPE();
            if (!type.equals("ORGANIZATION") && !type.equals("INDIVIDUAL")) {
              StringBuilder message = new StringBuilder();
              message.append("Value ").append(type).append(
                " in %1$s for metsHdr/agent/@TYPE when the metsHdr/agent/@ROLE is OTHER isn't valid, must be ORGANIZATION or INDIVIDUAL");
              return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION, Message.createErrorMessage(
                message.toString(), metsValidatorState.getMetsName(), metsValidatorState.isRootMets()), false, false);
            }
          }
        } else {
          return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION,
            Message.createErrorMessage("metsHdr/agent/@ROLE can't be null ", metsValidatorState.getMetsName(),
              metsValidatorState.isRootMets()),
            false, false);
        }
      }
    } else {
      return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION,
        Message.createErrorMessage("Skipped because metsHdr/@agent does not exists", metsValidatorState.getMetsName(),
          metsValidatorState.isRootMets()),
        true, true);
    }
    return new ReporterDetails();
  }

  /*
   * metsHdr/agent/name Name of the organisation submitting the package to the
   * archive
   */
  private ReporterDetails validateSIP18(MetsValidatorState metsValidatorState) {
    if (agents != null && !agents.isEmpty()) {
      for (MetsType.MetsHdr.Agent agent : agents) {
        String role = agent.getROLE();
        if (role != null) {
          if (role.equals("OTHER") && agent.getOTHERROLE().equals("SUBMITTER")) {
            String name = agent.getName();
            if (name == null) {
              return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION,
                Message.createErrorMessage("metsHdr/agent/name in %1$s can't be null", metsValidatorState.getMetsName(),
                  metsValidatorState.isRootMets()),
                false, false);
            }
          }
        } else {
          return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION,
            Message.createErrorMessage("metsHdr/agent/@ROLE in %1$s can't be null ", metsValidatorState.getMetsName(),
              metsValidatorState.isRootMets()),
            false, false);
        }
      }
    } else {
      return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION,
        Message.createErrorMessage("Skipped in %1$s because metsHdr/agent does not exists",
          metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
        true, true);
    }
    return new ReporterDetails();
  }

  /*
   * metsHdr/agent/note The submitting agent has a note providing a unique
   * identification code for the archival creator.
   */
  private ReporterDetails validateSIP19(MetsValidatorState metsValidatorState) {
    if (agents != null && !agents.isEmpty()) {
      for (MetsType.MetsHdr.Agent agent : agents) {
        String role = agent.getROLE();
        if (role != null) {
          if (role.equals("OTHER") && agent.getOTHERROLE().equals("SUBMITTER")) {
            List<MetsType.MetsHdr.Agent.Note> notes = agent.getNote();
            if (notes != null && !notes.isEmpty()) {
              if (notes.size() != 1) {
                return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION,
                  Message.createErrorMessage("metsHdr/agent/note in %1$s can't appear more than once ",
                    metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
                  false, false);
              }
            } else {
              return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION,
                Message.createErrorMessage("You can add one metsHdr/agent/note in %1$s",
                  metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
                false, false);
            }
          }
        } else {
          return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION,
            Message.createErrorMessage("metsHdr/agent/@ROLE in %1$s can't be null ", metsValidatorState.getMetsName(),
              metsValidatorState.isRootMets()),
            false, false);
        }
      }
    } else {
      return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION,
        Message.createErrorMessage("Skipped in %1$s because metsHdr/agent does not exists",
          metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
        true, true);
    }
    return new ReporterDetails();
  }

  /*
   * metsHdr/agent/note/@csip:NOTETYPE The submitting agent note is typed with the
   * value of “IDENTIFICATIONCODE”.See also: Note type
   */
  private ReporterDetails validateSIP20(MetsValidatorState metsValidatorState) {
    if (agents != null && !agents.isEmpty()) {
      for (MetsType.MetsHdr.Agent agent : agents) {
        String role = agent.getROLE();
        if (role != null) {
          if (role.equals("OTHER") && agent.getOTHERROLE().equals("SUBMITTER")) {
            List<MetsType.MetsHdr.Agent.Note> notes = agent.getNote();
            if (notes != null && !notes.isEmpty()) {
              for (MetsType.MetsHdr.Agent.Note note : notes) {
                String noteType = note.getNOTETYPE();
                if (noteType == null || noteType.equals("")) {
                  return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION,
                    Message.createErrorMessage("metsHdr/agent/@csip:NOTETYPE in %1$s can't be null or empty",
                      metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
                    false, false);
                } else {
                  if (!noteType.equals("IDENTIFICATIONCODE")) {
                    return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION,
                      Message.createErrorMessage("metsHdr/agent/@csip:NOTETYPE in %1$s has to be IDENTIFICATIONCODE",
                        metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
                      false, false);
                  }
                }
              }
            }
          }
        } else {
          return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION,
            Message.createErrorMessage("metsHdr/agent/@ROLE in %1$s can't be null ", metsValidatorState.getMetsName(),
              metsValidatorState.isRootMets()),
            false, false);
        }
      }
    } else {
      return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION,
        Message.createErrorMessage("Skipped in %1$s because metsHdr/agent does not exists",
          metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
        true, true);
    }
    return new ReporterDetails();
  }

  /*
   * metsHdr/agent Contact person for the submission.
   */
  private ReporterDetails validateSIP21(MetsValidatorState metsValidatorState) {
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
            Message.createErrorMessage("metsHdr/agent/@ROLE in %1$s can't be null ", metsValidatorState.getMetsName(),
              metsValidatorState.isRootMets()),
            false, false);
        }
      }
      if (!found) {
        return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION,
          Message.createErrorMessage("metsHdr/agent you can add agent with the @ROLE CREATOR in %1$s ",
            metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
          false, false);
      }
    } else {
      return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION,
        Message.createErrorMessage("Skipped in %1$s because metsHdr/@agent does not exists",
          metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
        true, true);
    }
    return new ReporterDetails();
  }

  /*
   * metsHdr/agent/@ROLE The role of the contact person is “CREATOR”.
   */
  private ReporterDetails validateSIP22(MetsValidatorState metsValidatorState) {
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
            Message.createErrorMessage("metsHdr/agent/@ROLE in %1$s can't be null ", metsValidatorState.getMetsName(),
              metsValidatorState.isRootMets()),
            false, false);
        }
      }
      if (!found) {
        return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION,
          Message.createErrorMessage("metsHdr/agent you can add agent with the @ROLE CREATOR in %1$s ",
            metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
          false, false);
      }
    } else {
      return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION,
        Message.createErrorMessage("Skipped in %1$s because metsHdr/@gent does not exists",
          metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
        true, true);
    }
    return new ReporterDetails();
  }

  /*
   * metsHdr/agent/@TYPE The type of the contact person agent is “INDIVIDUAL”
   */
  private ReporterDetails validateSIP23(MetsValidatorState metsValidatorState) {
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
              return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION, Message.createErrorMessage(
                message.toString(), metsValidatorState.getMetsName(), metsValidatorState.isRootMets()), false, false);
            }
          }
        } else {
          return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION,
            Message.createErrorMessage("metsHdr/agent/@ROLE in %1$s can't be null ", metsValidatorState.getMetsName(),
              metsValidatorState.isRootMets()),
            false, false);
        }
      }
    } else {
      return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION,
        Message.createErrorMessage("Skipped in %1$s because metsHdr/@agent does not exists",
          metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
        true, true);
    }
    return new ReporterDetails();
  }

  /*
   * metsHdr/agent/name Name of the contact person.
   */
  private ReporterDetails validateSIP24(MetsValidatorState metsValidatorState) {
    if (agents != null && !agents.isEmpty()) {
      for (MetsType.MetsHdr.Agent agent : agents) {
        String role = agent.getROLE();
        if (role != null) {
          if (role.equals("CREATOR")) {
            String name = agent.getName();
            if (name == null) {
              return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION,
                Message.createErrorMessage("metsHdr/agent/name in %1$s can't be null", metsValidatorState.getMetsName(),
                  metsValidatorState.isRootMets()),
                false, false);
            }
          }
        } else {
          return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION,
            Message.createErrorMessage("metsHdr/agent/@ROLE in %1$s can't be null ", metsValidatorState.getMetsName(),
              metsValidatorState.isRootMets()),
            false, false);
        }
      }
    } else {
      return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION,
        Message.createErrorMessage("Skipped in %1$s because metsHdr/@agent does not exists",
          metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
        true, true);
    }
    return new ReporterDetails();
  }

  /*
   * metsHdr/agent/note The submitting agent has one or more notes giving the
   * contact information
   */
  private ReporterDetails validateSIP25(MetsValidatorState metsValidatorState) {
    if (agents != null && !agents.isEmpty()) {
      for (MetsType.MetsHdr.Agent agent : agents) {
        String role = agent.getROLE();
        if (role != null) {
          if (role.equals("CREATOR")) {
            List<MetsType.MetsHdr.Agent.Note> notes = agent.getNote();
            if (notes == null || notes.isEmpty()) {
              return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION,
                Message.createErrorMessage("can add contact information in one or more metsHdr/agent/note in %1$s ",
                  metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
                false, false);
            }
          }
        } else {
          return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION,
            Message.createErrorMessage("metsHdr/agent/@ROLE in %1$s can't be null ", metsValidatorState.getMetsName(),
              metsValidatorState.isRootMets()),
            false, false);
        }
      }
    }
    return new ReporterDetails();

  }

  /*
   * metsHdr/agent The organisation or person that preserves the package.
   */
  private ReporterDetails validateSIP26(MetsValidatorState metsValidatorState) {
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
            Message.createErrorMessage("metsHdr/agent/@ROLE in %1$s can't be null ", metsValidatorState.getMetsName(),
              metsValidatorState.isRootMets()),
            false, false);
        }
      }
      if (!found) {
        return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION,
          Message.createErrorMessage("Does not have metsHdr/agent with the ROLE PRESERVATION in %1$s ",
            metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
          false, false);
      } else {
        if (count != 1) {
          return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION,
            Message.createErrorMessage("Can't have more than one metsHdr/agent with the ROLE PRESERVATION in %1$s",
              metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
            false, false);
        }
      }
    } else {
      return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION,
        Message.createErrorMessage("Skipped in %1$s because metsHdr/@agent does not exists",
          metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
        true, true);
    }
    return new ReporterDetails();
  }

  /*
   * metsHdr/agent/@ROLE The role of the preservation agent is “PRESERVATION”.
   */
  private ReporterDetails validateSIP27(MetsValidatorState metsValidatorState) {
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
            Message.createErrorMessage("metsHdr/agent/@ROLE in %1$s can't be null ", metsValidatorState.getMetsName(),
              metsValidatorState.isRootMets()),
            false, false);
        }
      }
      if (!found) {
        return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION,
          Message.createErrorMessage("metsHdr/agent you can add agent with the ROLE PRESERVATION in %1$s ",
            metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
          false, false);
      } else {
        if (count != 1) {
          return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION,
            Message.createErrorMessage("Can't have more than one metsHdr/agent with the ROLE PRESERVATION in %1$s",
              metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
            false, false);
        }
      }
    } else {
      return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION,
        Message.createErrorMessage("Skipped in %1$s because metsHdr/agent does not exists",
          metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
        true, true);
    }
    return new ReporterDetails();
  }

  /*
   * metsHdr/agent/@TYPE The type of the submitting agent is “ORGANIZATION”.
   */
  private ReporterDetails validateSIP28(MetsValidatorState metsValidatorState) {
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
              return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION, Message.createErrorMessage(
                message.toString(), metsValidatorState.getMetsName(), metsValidatorState.isRootMets()), false, false);
            }
          }
        } else {
          return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION,
            Message.createErrorMessage("metsHdr/agent/@ROLE in %1$s can't be null ", metsValidatorState.getMetsName(),
              metsValidatorState.isRootMets()),
            false, false);
        }
      }
    } else {
      return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION,
        Message.createErrorMessage("Skipped in %1$s because metsHdr/agent does not exists",
          metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
        true, true);
    }
    return new ReporterDetails();
  }

  /*
   * metsHdr/agent/name Name of the organisation preserving the package.
   */
  private ReporterDetails validateSIP29(MetsValidatorState metsValidatorState) {
    if (agents != null && !agents.isEmpty()) {
      for (MetsType.MetsHdr.Agent agent : agents) {
        String role = agent.getROLE();
        if (role != null) {
          if (role.equals("PRESERVATION")) {
            String name = agent.getName();
            if (name == null) {
              return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION,
                Message.createErrorMessage("metsHdr/agent/name in %1$s can't be null", metsValidatorState.getMetsName(),
                  metsValidatorState.isRootMets()),
                false, false);
            }
          }
        } else {
          return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION,
            Message.createErrorMessage("metsHdr/agent/@ROLE in %1$s can't be null ", metsValidatorState.getMetsName(),
              metsValidatorState.isRootMets()),
            false, false);
        }
      }
    } else {
      return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION,
        Message.createErrorMessage("Skipped in %1$s because metsHdr/@agent does not exists",
          metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
        true, true);
    }
    return new ReporterDetails();
  }

  /*
   * metsHdr/agent/note The preservation agent has a note providing a unique
   * identification code for the archival creator.
   */
  private ReporterDetails validateSIP30(MetsValidatorState metsValidatorState) {
    if (agents != null && !agents.isEmpty()) {
      for (MetsType.MetsHdr.Agent agent : agents) {
        String role = agent.getROLE();
        if (role != null) {
          if (role.equals("PRESERVATION")) {
            List<MetsType.MetsHdr.Agent.Note> notes = agent.getNote();
            if (notes != null && !notes.isEmpty()) {
              if (notes.size() != 1) {
                return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION,
                  Message.createErrorMessage("metsHdr/agent/note in %1$s can't be more than one ",
                    metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
                  false, false);
              }
            } else {
              return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION,
                Message.createErrorMessage("You can add one metsHdr/agent/note in %1$s",
                  metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
                false, false);
            }
          }
        } else {
          return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION,
            Message.createErrorMessage("metsHdr/agent/@ROLE in %1$s can't be null ", metsValidatorState.getMetsName(),
              metsValidatorState.isRootMets()),
            false, false);
        }
      }
    } else {
      return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION,
        Message.createErrorMessage("Skipped in %1$s because metsHdr/@agent does not exists",
          metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
        true, true);
    }
    return new ReporterDetails();
  }

  /*
   * metsHdr/agent/note/@csip:NOTETYPE The preservation agent note is typed with
   * the value of “IDENTIFICATIONCODE”.See also: Note type
   */
  private ReporterDetails validateSIP31(MetsValidatorState metsValidatorState) {
    if (agents != null && !agents.isEmpty()) {
      for (MetsType.MetsHdr.Agent agent : agents) {
        String role = agent.getROLE();
        if (role != null) {
          if (role.equals("PRESERVATION")) {
            List<MetsType.MetsHdr.Agent.Note> notes = agent.getNote();
            if (notes != null && !notes.isEmpty()) {
              for (MetsType.MetsHdr.Agent.Note note : notes) {
                String noteType = note.getNOTETYPE();
                if (noteType == null || noteType.equals("")) {
                  return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION,
                    Message.createErrorMessage("metsHdr/agent/@csip:NOTETYPE in %1$s can't be null or empty",
                      metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
                    false, false);
                }
              }
            }
          }
        } else {
          return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION,
            Message.createErrorMessage("metsHdr/agent/@ROLE in %1$s can't be null ", metsValidatorState.getMetsName(),
              metsValidatorState.isRootMets()),
            false, false);
        }
      }
    } else {
      return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION,
        Message.createErrorMessage("Skipped in %1$s because metsHdr/@agent does not exists",
          metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
        true, true);
    }
    return new ReporterDetails();
  }
}
