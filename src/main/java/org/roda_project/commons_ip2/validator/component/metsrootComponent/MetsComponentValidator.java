package org.roda_project.commons_ip2.validator.component.metsrootComponent;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.lang3.StringUtils;
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
public class MetsComponentValidator extends ValidatorComponentImpl {
  private static final Logger LOGGER = LoggerFactory.getLogger(MetsComponentValidator.class);

  private final String moduleName;

  private List<String> contentCategory;
  private List<String> contentInformationTypesList;

  public MetsComponentValidator() throws IOException, ParserConfigurationException, SAXException {
    this.moduleName = Constants.CSIP_MODULE_NAME_2;

    this.contentCategory = new ArrayList<>();
    this.contentCategory = ControlledVocabularyParser.parse(Constants.PATH_RESOURCES_CSIP_VOCABULARY_CONTENT_CATEGORY);
    this.contentInformationTypesList = ControlledVocabularyParser
      .parse(Constants.PATH_RESOURCES_CSIP_VOCABULARY_CONTENT_INFORMATION_TYPE);
  }

  @Override
  public Map<String, ReporterDetails> validate() throws IOException {
    Map<String, ReporterDetails> results = new HashMap<>();
    /* CSIP1 */
    notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP1_ID);
    ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP1_ID,
      validateCSIP1().setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION));

    /* CSIP2 */
    notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP2_ID);
    ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP2_ID,
      validateCSIP2().setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION));

    /* CSIP3 */
    notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP3_ID);
    ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP3_ID,
      validateCSIP3().setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION));

    /* CSIP4 */
    notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP4_ID);
    ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP4_ID,
      validateCSIP4().setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION));

    /* CSIP5 */
    notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP5_ID);
    ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP5_ID,
      validateCSIP5().setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION));

    /* CSIP6 */
    notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP6_ID);
    ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP6_ID,
      validateCSIP6().setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION));

    /* SIP1 */
    notifyObserversValidationStarted(moduleName, ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP1_ID);
    ResultsUtils.addResult(results, ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP1_ID,
      validateSIP1().setSpecification(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION));

    /* SIP2 */
    notifyObserversValidationStarted(moduleName, ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP2_ID);
    ResultsUtils.addResult(results, ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP2_ID,
      validateSIP2().setSpecification(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION));

    notifyObserversFinishModule(moduleName);
    return results;
  }

  /*
   * mets/@OBJID The mets/@OBJID attribute is mandatory, its value is a string
   * identifier for the METS document. For the package METS document, this should
   * be the name/ID of the package, i.e. the name of the package root folder. For
   * a representation level METS document this value records the name/ID of the
   * representation, i.e. the name of the top-level representation folder.
   */
  private ReporterDetails validateCSIP1() throws IOException {
    ReporterDetails details = new ReporterDetails();
    String objid = mets.getOBJID();
    if (objid == null) {
      details.addIssue(
        Message.createErrorMessage("mets/@OBJID can't be null in %1$s the value is null.", metsName, isRootMets()));
      details.setValid(false);
    } else {
      boolean exist;
      if (isZipFileFlag()) {
        if (isRootMets()) {
          exist = zipManager.checkRootFolderName(path, objid);
        } else {
          exist = zipManager.checkSubMetsFolder(path, objid);
        }
      } else {
        exist = folderManager.checkRootFolderName(Paths.get(metsPath), objid);
      }
      if (!exist) {
        details.addIssue(Message.createErrorMessage(
          "The folder containing the METS.xml file must have the same name mets/@OBJID, See %1$s", metsName,
          isRootMets()));
        details.setValid(false);
      }
    }
    return details;
  }

  /*
   * mets/@TYPE The mets/@TYPE attribute MUST be used to declare the category of
   * the content held in the package, e.g. book, journal, stereograph, video,
   * etc.. Legal values are defined in a fixed vocabulary. When the content
   * category used falls outside of the defined vocabulary the mets/@TYPE value
   * must be set to “OTHER” and the specific value declared in
   * mets/@csip:OTHERTYPE . The vocabulary will develop under the curation of the
   * DILCIS Board as additional content information type specifications are
   * produced.See also: Content Category
   */
  private ReporterDetails validateCSIP2() {
    String type = mets.getTYPE();
    if (StringUtils.isBlank(type)) {
      return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
        Message.createErrorMessage("mets/@TYPE can't be null, in %1$s the value is null", metsName, isRootMets()),
        false, false);
    } else {
      if (!contentCategory.contains(type)) {
        StringBuilder message = new StringBuilder();
        message.append("Value ").append(type).append(" is not valid in %1$s.");
        return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
          Message.createErrorMessage(message.toString(), metsName, isRootMets()), false, false);
      }
    }
    return new ReporterDetails();
  }

  /*
   * mets[@TYPE=’OTHER’]/@csip:OTHERTYPE When the mets/@TYPE attribute has the
   * value “OTHER” the mets/@csip:OTHERTYPE attribute MUST be used to declare the
   * content category of the package/representation.See also: Content Category
   */
  private ReporterDetails validateCSIP3() {
    ReporterDetails details = new ReporterDetails();
    String type = mets.getTYPE();
    String otherType = mets.getOTHERTYPE();
    if (type != null) {
      if (type.equals("OTHER") && (otherType == null || otherType.equals(""))) {
        details.setValid(false);
        details.addIssue(Message.createErrorMessage(
          "When mets/@type have the value OTHER mets/@csip:OTHERTYPE can't be null or empty (%1$s)", metsName,
          isRootMets()));
      }
    }
    return details;
  }

  /*
   * mets/@csip:CONTENTINFORMATIONTYPE Used to declare the Content Information
   * Type Specification used when creating the package. Legal values are defined
   * in a fixed vocabulary. The attribute is mandatory for representation level
   * METS documents. The vocabulary will evolve under the care of the DILCIS Board
   * as additional Content Information Type Specifications are developed.See also:
   * Content information type specification
   */
  private ReporterDetails validateCSIP4() {
    String contentInformationType = mets.getCONTENTINFORMATIONTYPE();
    if (contentInformationType != null) {
      if (!contentInformationTypesList.contains(contentInformationType)) {
        StringBuilder message = new StringBuilder();
        message.append("Value ").append(contentInformationType)
          .append(" in %1$s for mets/@csip:CONTENTINFORMATIONTYPE is not valid.");
        return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
          Message.createErrorMessage(message.toString(), metsName, isRootMets()), false, false);
      }
    } else {
      return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
        Message.createErrorMessage("mets/@csip:CONTENTINFORMATIONTYPE is null in %1$s", metsName, isRootMets()), false,
        false);
    }
    return new ReporterDetails();
  }

  /*
   * mets[@csip:CONTENTINFORMATIONTYPE=’OTHER’]/@csip:OTHERCONTENTINFORMATIONTYPE
   * When the mets/@csip:CONTENTINFORMATIONTYPE has the value “OTHER” the
   * mets/@csip:OTHERCONTENTINFORMATIONTYPE must state the content information
   * type.
   */
  private ReporterDetails validateCSIP5() {
    String contentInformationType = mets.getCONTENTINFORMATIONTYPE();
    String otherContentInformationType = mets.getOTHERCONTENTINFORMATIONTYPE();
    if (contentInformationType == null) {
      return new ReporterDetails();
    }
    if (contentInformationType.equals("OTHER")
      && (otherContentInformationType == null || otherContentInformationType.equals(""))) {
      return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, Message.createErrorMessage(
        "When mets/@csip:CONTENTINFORMATIONTYPE have the value OTHER  mets/@csip:OTHERCONTENTINFORMATIONTYPE can't be null or empty (%1$s)",
        metsName, isRootMets()), false, false);
    }
    return new ReporterDetails();

  }

  /*
   * mets/@PROFILE The URL of the METS profile that the information package
   * conforms with.
   */
  private ReporterDetails validateCSIP6() {
    ReporterDetails details = new ReporterDetails();
    String profile = mets.getPROFILE();
    if (profile == null || profile.equals("")) {
      details.setValid(false);
      details.addIssue(Message.createErrorMessage(
        "mets/@PROFILE can't be null or empty, in %1$s the value is null or empty", metsName, isRootMets()));
    }
    return details;
  }

  /*
   * mets/@LABEL An optional short text describing the contents of the package,
   * e.g. “Accounting records of 2017”.
   */
  private ReporterDetails validateSIP1() {
    String label = mets.getLABEL();
    if (label == null) {
      return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION,
        Message.createErrorMessage("Doesn't have an mets/@LABEL in %1$s", metsName, isRootMets()), false, false);
    }
    return new ReporterDetails();
  }

  /*
   * mets/@PROFILE An optional short text describing the contents of the package,
   * e.g. “Accounting records of 2017”.
   */
  private ReporterDetails validateSIP2() {
    String profile = mets.getPROFILE();
    String profileValue = "https://earkcsip.dilcis.eu/profile/E-ARK-CSIP.xml";
    if (profile != null) {
      if (!profile.equals(profileValue)) {
        StringBuilder message = new StringBuilder();
        message.append("mets/@PROFILE value isn't ").append(profileValue).append(" %1$s");
        return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION,
          Message.createErrorMessage(message.toString(), metsName, isRootMets()), false, false);
      }
    } else {
      return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION,
        Message.createErrorMessage("mets/@PROFILE can't be null, in %1$s is null", metsName, isRootMets()), false,
        false);
    }
    return new ReporterDetails();
  }
}
