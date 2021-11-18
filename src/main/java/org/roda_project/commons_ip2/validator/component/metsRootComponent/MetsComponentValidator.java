package org.roda_project.commons_ip2.validator.component.metsRootComponent;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.xml.parsers.ParserConfigurationException;
import org.apache.commons.lang3.StringUtils;
import org.roda_project.commons_ip2.validator.common.ControlledVocabularyParser;
import org.roda_project.commons_ip2.validator.component.MetsValidatorImpl;
import org.roda_project.commons_ip2.validator.constants.Constants;
import org.roda_project.commons_ip2.validator.constants.ConstantsCSIPspec;
import org.roda_project.commons_ip2.validator.reporter.ReporterDetails;
import org.roda_project.commons_ip2.validator.state.MetsValidatorState;
import org.roda_project.commons_ip2.validator.state.StructureValidatorState;
import org.roda_project.commons_ip2.validator.utils.Message;
import org.roda_project.commons_ip2.validator.utils.ResultsUtils;
import org.xml.sax.SAXException;

/** {@author João Gomes <jgomes@keep.pt>}. */
public class MetsComponentValidator extends MetsValidatorImpl {

  private final String moduleName;

  private final List<String> contentCategory;
  private final List<String> contentInformationTypesList;

  /**
   * Initialize all objects needed to validation of this component.
   *
   * @throws IOException if some I/O errors occurs
   * @throws ParserConfigurationException if some error occurs
   * @throws SAXException if some error occurs
   */
  public MetsComponentValidator() throws IOException, ParserConfigurationException, SAXException {
    this.moduleName = Constants.CSIP_MODULE_NAME_2;
    this.contentCategory =
        ControlledVocabularyParser.parse(Constants.PATH_RESOURCES_CSIP_VOCABULARY_CONTENT_CATEGORY);
    this.contentInformationTypesList =
        ControlledVocabularyParser.parse(
            Constants.PATH_RESOURCES_CSIP_VOCABULARY_CONTENT_INFORMATION_TYPE);
  }

  /**
   * Validates CSIP1 to CSIP6 requirements.
   *
   * @param structureValidatorState the contextual {@link StructureValidatorState}
   * @param metsValidatorState the contextual {@link MetsValidatorState}
   * @return {@link Map} map with results of validation.
   * @throws IOException if some I/O errors occurs
   */
  @Override
  public Map<String, ReporterDetails> validate(
      final StructureValidatorState structureValidatorState,
      final MetsValidatorState metsValidatorState)
      throws IOException {
    Map<String, ReporterDetails> results = new HashMap<>();
    /* CSIP1 */

    notifyObserversValidationStarted(
        moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP1_ID);
    ResultsUtils.addResult(
        results,
        ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP1_ID,
        validateCSIP1(structureValidatorState, metsValidatorState)
            .setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION));

    /* CSIP2 */
    notifyObserversValidationStarted(
        moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP2_ID);
    ResultsUtils.addResult(
        results,
        ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP2_ID,
        validateCSIP2(metsValidatorState)
            .setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION));

    /* CSIP3 */
    notifyObserversValidationStarted(
        moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP3_ID);
    ResultsUtils.addResult(
        results,
        ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP3_ID,
        validateCSIP3(metsValidatorState)
            .setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION));

    /* CSIP4 */
    notifyObserversValidationStarted(
        moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP4_ID);
    ResultsUtils.addResult(
        results,
        ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP4_ID,
        validateCSIP4(metsValidatorState)
            .setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION));

    /* CSIP5 */
    notifyObserversValidationStarted(
        moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP5_ID);
    ResultsUtils.addResult(
        results,
        ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP5_ID,
        validateCSIP5(metsValidatorState)
            .setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION));

    /* CSIP6 */
    notifyObserversValidationStarted(
        moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP6_ID);
    ResultsUtils.addResult(
        results,
        ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP6_ID,
        validateCSIP6(metsValidatorState)
            .setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION));

    notifyObserversFinishModule(moduleName);
    return results;
  }

  /**
   * mets/@OBJID The mets/@OBJID attribute is mandatory, its value is a string identifier for the
   * METS document. For the package METS document, this should be the name/ID of the package, i.e.
   * the name of the package root folder. For a representation level METS document this value
   * records the name/ID of the representation, i.e. the name of the top-level representation
   * folder.
   *
   * @param structureValidatorState the contextual state {@link StructureValidatorState}
   * @param metsValidatorState the contextual state {@link MetsValidatorState}
   * @return {@link ReporterDetails}
   * @throws IOException if some I/O error occurs
   */
  private ReporterDetails validateCSIP1(
      final StructureValidatorState structureValidatorState,
      final MetsValidatorState metsValidatorState)
      throws IOException {
    ReporterDetails details = new ReporterDetails();
    String objid = metsValidatorState.getMets().getOBJID();
    if (objid == null) {
      details.addIssue(
          Message.createErrorMessage(
              "mets/@OBJID can't be null in %1$s the value is null.",
              metsValidatorState.getMetsName(), metsValidatorState.isRootMets()));
      details.setValid(false);
    } else {
      boolean exist;
      if (structureValidatorState.isZipFileFlag()) {
        if (metsValidatorState.isRootMets()) {
          exist =
              structureValidatorState
                  .getZipManager()
                  .checkRootFolderName(structureValidatorState.getIpPath(), objid);
        } else {
          exist =
              structureValidatorState
                  .getZipManager()
                  .checkSubMetsFolder(structureValidatorState.getIpPath(), objid);
        }
      } else {
        exist =
            structureValidatorState
                .getFolderManager()
                .checkRootFolderName(Paths.get(metsValidatorState.getMetsName()), objid);
      }
      if (!exist) {
        details.addIssue(
            Message.createErrorMessage(
                "The folder containing the METS.xml file must have the same name "
                    + "mets/@OBJID, See %1$s",
                metsValidatorState.getMetsName(), metsValidatorState.isRootMets()));
        details.setValid(false);
      }
    }
    return details;
  }

  /**
   * mets/@TYPE The mets/@TYPE attribute MUST be used to declare the category of the content held in
   * the package, e.g. book, journal, stereograph, video, etc.. Legal values are defined in a fixed
   * vocabulary. When the content category used falls outside of the defined vocabulary the
   * mets/@TYPE value must be set to “OTHER” and the specific value declared in mets/@csip:OTHERTYPE
   * . The vocabulary will develop under the curation of the DILCIS Board as additional content
   * information type specifications are produced.See also: Content Category
   *
   * @param metsValidatorState the contextual state {@link MetsValidatorState}
   * @return {@link ReporterDetails}
   */
  private ReporterDetails validateCSIP2(final MetsValidatorState metsValidatorState) {
    String type = metsValidatorState.getMets().getTYPE();
    if (StringUtils.isBlank(type)) {
      return new ReporterDetails(
          Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
          Message.createErrorMessage(
              "mets/@TYPE can't be null, in %1$s the value is null",
              metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
          false,
          false);
    } else {
      if (!contentCategory.contains(type)) {
        StringBuilder message = new StringBuilder();
        message.append("Value ").append(type).append(" is not valid in %1$s.");
        return new ReporterDetails(
            Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
            Message.createErrorMessage(
                message.toString(),
                metsValidatorState.getMetsName(),
                metsValidatorState.isRootMets()),
            false,
            false);
      }
    }
    return new ReporterDetails();
  }

  /**
   * mets[@TYPE=’OTHER’]/@csip:OTHERTYPE When the mets/@TYPE attribute has the value “OTHER” the
   * mets/@csip:OTHERTYPE attribute MUST be used to declare the content category of the
   * package/representation.See also: Content Category
   *
   * @param metsValidatorState the contextual state {@link MetsValidatorState}
   * @return {@link ReporterDetails}
   */
  private ReporterDetails validateCSIP3(final MetsValidatorState metsValidatorState) {
    ReporterDetails details = new ReporterDetails();
    String type = metsValidatorState.getMets().getTYPE();
    String otherType = metsValidatorState.getMets().getOTHERTYPE();
    if (type != null && type.equals("OTHER") && (otherType == null || otherType.equals(""))) {
      details.setValid(false);
      details.addIssue(
          Message.createErrorMessage(
              "When mets/@type have the value OTHER mets/@csip:OTHERTYPE can't be "
                  + "null or empty (%1$s)",
              metsValidatorState.getMetsName(), metsValidatorState.isRootMets()));
    }
    return details;
  }

  /**
   * mets/@csip:CONTENTINFORMATIONTYPE Used to declare the Content Information Type Specification
   * used when creating the package. Legal values are defined in a fixed vocabulary. The attribute
   * is mandatory for representation level METS documents. The vocabulary will evolve under the care
   * of the DILCIS Board as additional Content Information Type Specifications are developed.See
   * also: Content information type specification
   *
   * @param metsValidatorState the contextual state {@link MetsValidatorState}
   * @return {@link ReporterDetails}
   */
  private ReporterDetails validateCSIP4(final MetsValidatorState metsValidatorState) {
    String contentInformationType = metsValidatorState.getMets().getCONTENTINFORMATIONTYPE();
    if (contentInformationType != null) {
      if (!contentInformationTypesList.contains(contentInformationType)) {
        StringBuilder message = new StringBuilder();
        message
            .append("Value ")
            .append(contentInformationType)
            .append(" in %1$s for mets/@csip:CONTENTINFORMATIONTYPE is not valid.");
        return new ReporterDetails(
            Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
            Message.createErrorMessage(
                message.toString(),
                metsValidatorState.getMetsName(),
                metsValidatorState.isRootMets()),
            false,
            false);
      }
    } else {
      return new ReporterDetails(
          Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
          Message.createErrorMessage(
              "mets/@csip:CONTENTINFORMATIONTYPE is null in %1$s",
              metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
          false,
          false);
    }
    return new ReporterDetails();
  }

  /**
   * mets[@csip:CONTENTINFORMATIONTYPE=’OTHER’]/@csip:OTHERCONTENTINFORMATIONTYPE When the
   * mets/@csip:CONTENTINFORMATIONTYPE has the value “OTHER” the
   * mets/@csip:OTHERCONTENTINFORMATIONTYPE must state the content information type.
   *
   * @param metsValidatorState the contextual state {@link MetsValidatorState}
   * @return {@link ReporterDetails}
   */
  private ReporterDetails validateCSIP5(final MetsValidatorState metsValidatorState) {
    String contentInformationType = metsValidatorState.getMets().getCONTENTINFORMATIONTYPE();
    String otherContentInformationType =
        metsValidatorState.getMets().getOTHERCONTENTINFORMATIONTYPE();
    if (contentInformationType == null) {
      return new ReporterDetails();
    }
    if (contentInformationType.equals("OTHER")
        && (otherContentInformationType == null || otherContentInformationType.equals(""))) {
      return new ReporterDetails(
          Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
          Message.createErrorMessage(
              "When mets/@csip:CONTENTINFORMATIONTYPE have the value OTHER  "
                  + "mets/@csip:OTHERCONTENTINFORMATIONTYPE can't be null or empty (%1$s)",
              metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
          false,
          false);
    }
    return new ReporterDetails();
  }

  /**
   * mets/@PROFILE The URL of the METS profile that the information package conforms with.
   *
   * @param metsValidatorState the contextual state {@link MetsValidatorState}
   * @return {@link ReporterDetails}
   */
  private ReporterDetails validateCSIP6(final MetsValidatorState metsValidatorState) {
    ReporterDetails details = new ReporterDetails();
    String profile = metsValidatorState.getMets().getPROFILE();
    if (profile == null || profile.equals("")) {
      details.setValid(false);
      details.addIssue(
          Message.createErrorMessage(
              "mets/@PROFILE can't be null or empty, in %1$s the value is null or empty",
              metsValidatorState.getMetsName(), metsValidatorState.isRootMets()));
    }
    return details;
  }
}
