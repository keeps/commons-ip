package org.roda_project.commons_ip2.validator.component.metsrootComponent;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.parsers.ParserConfigurationException;

import org.roda_project.commons_ip2.mets_v1_12.beans.MetsType;
import org.roda_project.commons_ip2.validator.common.ControlledVocabularyParser;
import org.roda_project.commons_ip2.validator.component.MetsValidatorImpl;
import org.roda_project.commons_ip2.validator.constants.Constants;
import org.roda_project.commons_ip2.validator.constants.ConstantsCSIPspec;
import org.roda_project.commons_ip2.validator.reporter.ReporterDetails;
import org.roda_project.commons_ip2.validator.state.MetsValidatorState;
import org.roda_project.commons_ip2.validator.state.StructureValidatorState;
import org.roda_project.commons_ip2.validator.utils.Message;
import org.roda_project.commons_ip2.validator.utils.ResultsUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;

/**
 * @author João Gomes <jgomes@keep.pt>
 */
public class MetsHeaderComponentValidator extends MetsValidatorImpl {
  private static final Logger LOGGER = LoggerFactory.getLogger(MetsHeaderComponentValidator.class);

  private final String moduleName;
  private List<String> oaisPackageTypes;
  private MetsType.MetsHdr metsHdr;
  private List<MetsType.MetsHdr.Agent> agents;

  public MetsHeaderComponentValidator() throws IOException, ParserConfigurationException, SAXException {
    this.moduleName = Constants.CSIP_MODULE_NAME_2;
    this.oaisPackageTypes = ControlledVocabularyParser
      .parse(Constants.PATH_RESOURCES_CSIP_VOCABULARY_OAIS_PACKAGE_TYPE);
  }

  @Override
  public Map<String, ReporterDetails> validate(StructureValidatorState structureValidatorState,
    MetsValidatorState metsValidatorState) throws IOException {
    metsHdr = metsValidatorState.getMets().getMetsHdr();
    agents = metsHdr.getAgent();
    Map<String, ReporterDetails> results = new HashMap<>();

    ReporterDetails skippedCSIP;
    /* CSIP117 */
    notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP117_ID);
    ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP117_ID,
      validateCSIP117(metsValidatorState).setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION));

    if (ResultsUtils.isResultValid(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP117_ID)) {
      /* CSIP7 */
      notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP7_ID);
      ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP7_ID,
        validateCSIP7(metsValidatorState).setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION));

      /* CSIP8 */
      skippedCSIP = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
        Message.createErrorMessage("SKIPPED in  %1$s  can't validate", metsValidatorState.getMetsName(),
          metsValidatorState.isRootMets()),
        true, true);
      ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP8_ID, skippedCSIP);

      /* CSIP9 */
      notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP9_ID);
      ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP9_ID,
        validateCSIP9(metsValidatorState).setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION));

      if (metsValidatorState.isRootMets()) {
        /* CSIP10 */
        notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP10_ID);
        ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP10_ID,
          validateCSIP10(metsValidatorState).setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION));

        if (ResultsUtils.isResultValid(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP10_ID)) {
          /* CSIP11 */
          notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP11_ID);
          ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP11_ID,
            validateCSIP11(metsValidatorState).setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION));

          /* CSIP12 */
          notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP12_ID);
          ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP12_ID,
            validateCSIP12(metsValidatorState).setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION));

          /* CSIP13 */
          notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP13_ID);
          ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP13_ID,
            validateCSIP13(metsValidatorState).setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION));

          /* CSIP14 */
          notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP14_ID);
          ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP14_ID,
            validateCSIP14(metsValidatorState).setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION));

          /* CSIP15 */
          notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP15_ID);
          ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP15_ID,
            validateCSIP15(metsValidatorState).setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION));

          /* CSIP16 */
          notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP16_ID);
          ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP16_ID,
            validateCSIP16(metsValidatorState).setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION));
        } else {
          String message = Message.createErrorMessage(
            "SKIPPED in %1$s because it does not contain a mets/metsHdr/agent element",
            metsValidatorState.getMetsName(), metsValidatorState.isRootMets());

          ResultsUtils.addResults(results,
            new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, message, true, true),
            ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP11_ID,
            ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP12_ID,
            ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP13_ID,
            ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP14_ID,
            ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP15_ID,
            ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP16_ID);
        }
      }
    } else {
      String message = Message.createErrorMessage("SKIPPED because mets/metsHdr doesn't exist ",
        metsValidatorState.getMetsName(), metsValidatorState.isRootMets());

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
        ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP16_ID);
    }
    notifyObserversFinishModule(moduleName);
    return results;
  }

  /*
   * mets/metsHdr General element for describing the package.
   */
  private ReporterDetails validateCSIP117(MetsValidatorState metsValidatorState) {
    ReporterDetails details = new ReporterDetails();
    if (metsHdr == null) {
      details.setValid(false);
      details.addIssue(Message.createErrorMessage("mets/metsHdr can't be null, in %1$s the mets/metsHdr does not exist",
        metsValidatorState.getMetsName(), metsValidatorState.isRootMets()));
    }
    return details;
  }

  /*
   * mets/metsHdr/@CREATEDATE mets/metsHdr/@CREATEDATE records the date the
   * package was created.
   */
  private ReporterDetails validateCSIP7(MetsValidatorState metsValidatorState) {
    ReporterDetails details = new ReporterDetails();
    XMLGregorianCalendar createDate = metsHdr.getCREATEDATE();
    if (createDate == null) {
      details.setValid(false);
      details.addIssue(Message.createErrorMessage("mets/metsHdr/@CREATEDATE can't be null, in %1$s the value is null",
        metsValidatorState.getMetsName(), metsValidatorState.isRootMets()));
    }
    return details;
  }

  /*
   * mets/metsHdr/@csip:OAISPACKAGETYPE mets/metsHdr/@csip:OAISPACKAGETYPE is an
   * additional CSIP attribute that declares the type of the IP.See also:
   * OAISPackage type
   */
  private ReporterDetails validateCSIP9(MetsValidatorState metsValidatorState) {
    ReporterDetails details = new ReporterDetails();
    String oaisPackageType = metsHdr.getOAISPACKAGETYPE();
    if (oaisPackageType == null || oaisPackageType.equals("")) {
      details.setValid(false);
      details.addIssue(Message.createErrorMessage(
        "mets/metsHdr/@csip:OAISPACKAGETYPE can't be null or empty, in %1$s the value is null or empty",
        metsValidatorState.getMetsName(), metsValidatorState.isRootMets()));
    } else {
      if (!oaisPackageTypes.contains(oaisPackageType)) {
        StringBuilder message = new StringBuilder();
        message.append("Value ").append(oaisPackageType)
          .append("in %1$s for mets/metsHdr/@csip:OAISPACKAGETYPE isn't valid");
        details.setValid(false);
        details.addIssue(Message.createErrorMessage(message.toString(), metsValidatorState.getMetsName(),
          metsValidatorState.isRootMets()));
      }
    }
    return details;
  }

  /*
   * mets/metsHdr/agent A mandatory agent element records the software used to
   * create the package. Other uses of agents may be described in any local
   * implementations that extend the profile.
   */
  private ReporterDetails validateCSIP10(MetsValidatorState metsValidatorState) {
    ReporterDetails details = new ReporterDetails();
    if (agents == null && metsValidatorState.isRootMets()) {
      details.setValid(false);
      details.addIssue(Message.createErrorMessage("Must have at least one mets/metsHdr/agent (%1$s)",
        metsValidatorState.getMetsName(), metsValidatorState.isRootMets()));
    } else {
      if (agents == null && !metsValidatorState.isRootMets()) {
        return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, "", true, true);
      } else {
        if (agents.isEmpty() && metsValidatorState.isRootMets()) {
          details.setValid(false);
          details.addIssue(Message.createErrorMessage("Must have at least one mets/metsHdr/agent (%1$s)",
            metsValidatorState.getMetsName(), metsValidatorState.isRootMets()));
        } else {
          if (agents.isEmpty() && !metsValidatorState.isRootMets()) {
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
  private ReporterDetails validateCSIP11(MetsValidatorState metsValidatorState) {
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
            "Must have a mets/metsHdr/agent[@ROLE='CREATOR'] with the value CREATOR, in %1$s does not exists",
            metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
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

  private ReporterDetails validateCSIP12(MetsValidatorState metsValidatorState) {
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
        "Must have a mets/metsHdr/agent[@TYPE='OTHER'] with the value OTHER, in %1$s does not exist",
        metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
      false, false);
  }

  /*
   * mets/metsHdr/agent[@OTHERTYPE=’SOFTWARE’] The mandatory agent element MUST
   * have a @OTHERTYPE attribute with the value “SOFTWARE”.See also: Other agent
   * type
   */

  private ReporterDetails validateCSIP13(MetsValidatorState metsValidatorState) {
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
        Message.createErrorMessage("", metsValidatorState.getMetsName(), metsValidatorState.isRootMets()), true, true);
    }
    return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, Message.createErrorMessage(
      "Must have a mets/metsHdr/agent[@OTHERTYPE=’SOFTWARE’] with the value SOFTWARE, in the %1$s the value isn't SOFTWARE",
      metsValidatorState.getMetsName(), metsValidatorState.isRootMets()), false, false);

  }

  /*
   * mets/metsHdr/agent/name The mandatory agent’s name element records the name
   * of the software tool used to create the IP.
   */

  private ReporterDetails validateCSIP14(MetsValidatorState metsValidatorState) {
    if (agents != null && !agents.isEmpty()) {
      for (MetsType.MetsHdr.Agent a : agents) {
        String role = a.getROLE();
        String type = a.getTYPE();
        String otherType = a.getOTHERTYPE();
        if (role.equals("CREATOR") && type.equals("OTHER") && otherType.equals("SOFTWARE")) {
          if (a.getName() == null) {
            return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
              Message.createErrorMessage("mets/metsHdr/agent/name can't be null, in %1$s the value is null",
                metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
              false, false);
          } else {
            if (a.getName().equals("")) {
              return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
                Message.createErrorMessage("mets/metsHdr/agent/name can't be empty, in %1$s the value is empty",
                  metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
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

  private ReporterDetails validateCSIP15(MetsValidatorState metsValidatorState) {
    if (agents != null && !agents.isEmpty()) {
      for (MetsType.MetsHdr.Agent a : agents) {
        String role = a.getROLE();
        String type = a.getTYPE();
        String otherType = a.getOTHERTYPE();
        if (role.equals("CREATOR") && type.equals("OTHER") && otherType.equals("SOFTWARE")) {
          List<MetsType.MetsHdr.Agent.Note> notes = a.getNote();
          if (notes == null || notes.isEmpty()) {
            return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
              Message.createErrorMessage("mets/metsHdr/agent/note can't be null, in %1$s the value is null",
                metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
              false, false);
          } else {
            if (notes.size() > 1) {
              return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
                Message.createErrorMessage(
                  "mets/metsHdr/agent/note exists more than once, in %1$s exists more than once note",
                  metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
                false, false);
            } else {
              for (MetsType.MetsHdr.Agent.Note note : notes) {
                if (note.getValue().isEmpty()) {
                  return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
                    Message.createErrorMessage("mets/metsHdr/agent/note can't be empty, in %1$s the note is empty",
                      metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
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
  private ReporterDetails validateCSIP16(MetsValidatorState metsValidatorState) {
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
            Message.createErrorMessage("mets/metsHdr/agent/note can't be null, in the %1$s the value is null",
              metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
            false, false);
        } else {
          for (MetsType.MetsHdr.Agent.Note note : notes) {
            if (note.getNOTETYPE() == null) {
              return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
                Message.createErrorMessage(
                  "mets/metsHdr/agent/note[@csip:NOTETYPE=’SOFTWARE VERSION’] can't be null, in %1$s the value is null",
                  metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
                false, false);
            } else {
              if (!note.getNOTETYPE().equals("SOFTWARE VERSION")) {
                StringBuilder message = new StringBuilder();
                message.append("Value ").append(note.getNOTETYPE()).append(
                  " in %1$s for mets/metsHdr/agent/note[@csip:NOTETYPE=’SOFTWARE VERSION’] isn't valid, the value must be SOFTWARE VERSION");
                return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, Message.createErrorMessage(
                  message.toString(), metsValidatorState.getMetsName(), metsValidatorState.isRootMets()), false, false);
              }
            }
          }
        }
      }
    }
    return details;
  }
}
