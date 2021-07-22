package org.roda_project.commons_ip2.validator.component.metsrootComponent;

import org.roda_project.commons_ip2.validator.common.ControlledVocabularyParser;
import org.roda_project.commons_ip2.validator.component.ValidatorComponentImpl;
import org.roda_project.commons_ip2.validator.constants.Constants;
import org.roda_project.commons_ip2.validator.constants.ConstantsCSIPspec;
import org.roda_project.commons_ip2.validator.reporter.ReporterDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author João Gomes <jgomes@keep.pt>
 */
public class MetsComponentValidator extends ValidatorComponentImpl {
  private static final Logger LOGGER = LoggerFactory.getLogger(MetsComponentValidator.class);

  private final String MODULE_NAME;

  private List<String> contentCategory;
  private List<String> contentInformationType;

  public void setContentCategory(List<String> contentCategory) {
    this.contentCategory = new ArrayList<String>(contentCategory);
  }

  public void setContentInformationType(List<String> contentInformationType) {
    this.contentInformationType = new ArrayList<String>(contentInformationType);
  }

  public MetsComponentValidator(String moduleName) {
    this.MODULE_NAME = moduleName;
    this.contentCategory = new ArrayList<>();
    ControlledVocabularyParser controlledVocabularyParser = new ControlledVocabularyParser(Constants.PATH_RESOURCES_CSIP_VOCABULARY_CONTENT_CATEGORY,contentCategory);
    controlledVocabularyParser.parse();
    setContentCategory(controlledVocabularyParser.getData());

    this.contentInformationType = new ArrayList<>();
    controlledVocabularyParser = new ControlledVocabularyParser(Constants.PATH_RESOURCES_CSIP_VOCABULARY_CONTENT_INFORMATION_TYPE,contentInformationType);
    controlledVocabularyParser.parse();
    setContentInformationType(controlledVocabularyParser.getData());
  }

  @Override
  public boolean validate() throws IOException {
    boolean valid = true;
    ReporterDetails csip;
    /* CSIP1 Validation */
    validationInit(MODULE_NAME,ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP1_ID);
    csip = validateCSIP1();
    if(csip.isValid()){
      validationOutcomePassed(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP1_ID,csip.getMessage());

    }
    else{
      validationOutcomeFailed(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP1_ID,csip.getMessage());
      valid = false;
    }

    /* CSIP2 Validation */
    validationInit(MODULE_NAME,ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP2_ID);
    csip = validateCSIP2();
    if(csip.isValid()){
      validationOutcomePassed(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP2_ID,csip.getMessage());

    }
    else{
      validationOutcomeFailed(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP2_ID,csip.getMessage());
      valid = false;
    }

    /* CSIP3 Validation */
    validationInit(MODULE_NAME,ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP3_ID);
    csip = validateCSIP3();
    if(csip.isValid()){
      validationOutcomePassed(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP3_ID,csip.getMessage());
    }
    else{
      validationOutcomeFailed(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP3_ID, csip.getMessage());
      valid = false;
    }

    /* CSIP4 Validation */
    validationInit(MODULE_NAME,ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP4_ID);
    csip = validateCSIP4();
    if(csip.isValid()){
      validationOutcomePassed(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP4_ID,csip.getMessage());
    }
    else{
      validationOutcomeFailed(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP4_ID, csip.getMessage());
      valid = false;
    }

    /* CSIP5 Validation */
    validationInit(MODULE_NAME,ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP5_ID);
    csip = validateCSIP5();
    if(csip.isValid()){
      validationOutcomePassed(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP5_ID,csip.getMessage());
    }
    else{
      validationOutcomeFailed(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP5_ID, csip.getMessage());
      valid = false;
    }

    /* CSIP6 Validation */
    validationInit(MODULE_NAME,ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP6_ID);
    csip = validateCSIP6();
    if(csip.isValid()){
      validationOutcomePassed(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP6_ID,csip.getMessage());
    }
    else{
      validationOutcomeFailed(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP6_ID, csip.getMessage());
      valid = false;
    }
    observer.notifyFinishModule(MODULE_NAME);
    cleanValidationObjects();
    return valid;
  }

  /*
  * mets/@OBJID
  * The mets/@OBJID attribute is mandatory, its value is a string identifier for
  * the METS document. For the package METS document, this should be the
  * name/ID of the package, i.e. the name of the package root folder. For a
  * representation level METS document this value records the name/ID of the
  * representation, i.e. the name of the top-level representation folder.
  */
  private ReporterDetails validateCSIP1() throws IOException {
    ReporterDetails details = new ReporterDetails();
    String OBJID = mets.getOBJID();
    if(OBJID == null){
      details.setMessage("mets/@OBJID attribute is mandatory, can't be null!");
      details.setValid(false);
    }
    else {
        String rootFolderName;
        if(isZipFileFlag()){
          rootFolderName = zipManager.getSipRootFolderName(path);
        }
        else {
          rootFolderName = folderManager.getSipRootFolderName(path);
        }
        if(!rootFolderName.equals(OBJID)){
          details.setMessage("The folder containing the METS.xml file must have the same name mets/@OBJID!");
          details.setValid(false);
        }
    }
    return details;
  }

  /*
  * mets/@TYPE
  * The mets/@TYPE attribute MUST be used to declare the category of the
  * content held in the package, e.g. book, journal, stereograph, video, etc..
  * Legal values are defined in a fixed vocabulary. When the content category
  * used falls outside of the defined vocabulary the mets/@TYPE value must be
  * set to “OTHER” and the specific value declared in mets/@csip:OTHERTYPE .
  * The vocabulary will develop under the curation of the DILCIS Board as
  * additional content information type specifications are produced.See also:
  * Content Category
  */
  private ReporterDetails validateCSIP2() {
    ReporterDetails details = new ReporterDetails();
    String TYPE = mets.getTYPE();
    if(TYPE == null || TYPE.equals("")){
      details.setValid(false);
      details.setMessage("mets/@TYPE attribute is mandatory, can't be null!");
    }
    else {
      if(!contentCategory.contains(TYPE)){
        details.setValid(false);
        details.setMessage("Value of mets/@TYPE is not valid; see Content category for valid mets/@TYPE");
      }
    }
    return details;
  }

  /*
  * mets[@TYPE=’OTHER’]/@csip:OTHERTYPE
  * When the mets/@TYPE attribute has the value “OTHER” the
  * mets/@csip:OTHERTYPE attribute MUST be used to declare the content
  * category of the package/representation.See also: Content Category
  */
  private ReporterDetails validateCSIP3() {
    ReporterDetails details = new ReporterDetails();
    String TYPE = mets.getTYPE();
    String otherType = mets.getOTHERTYPE();
    if(TYPE != null){
      if(TYPE.equalsIgnoreCase("Other") && (otherType == null || otherType.equals(""))){
        details.setValid(false);
        details.setMessage("When mets/@type have the value OTHER  mets/@csip:OTHERTYPE can't be null or empty");
      }

    }
    return details;
  }

  /*
  * mets/@csip:CONTENTINFORMATIONTYPE
  * Used to declare the Content Information Type Specification used when
  * creating the package. Legal values are defined in a fixed vocabulary. The
  * attribute is mandatory for representation level METS documents. The
  * vocabulary will evolve under the care of the DILCIS Board as additional
  * Content Information Type Specifications are developed.See also: Content
  * information type specification
  */
  private ReporterDetails validateCSIP4() {
    ReporterDetails details = new ReporterDetails();
    String ContentInformationType = mets.getCONTENTINFORMATIONTYPE();
    if(ContentInformationType != null){
      if(!contentInformationType.contains(ContentInformationType)){
        details.setValid(false);
        details.setMessage("Value of mets/@csip:CONTENTINFORMATIONTYPE is not valid; see Content information type specification for valid mets/@@csip:CONTENTINFORMATIONTYPE");
      }
    }
    return details;
  }

  /*
  * mets[@csip:CONTENTINFORMATIONTYPE=’OTHER’]/@csip:OTHERCONTENTINFORMATIONTYPE
  * When the mets/@csip:CONTENTINFORMATIONTYPE has the value
  * “OTHER” the mets/@csip:OTHERCONTENTINFORMATIONTYPE must state
  * the content information type.
  */
  private ReporterDetails validateCSIP5(){
    ReporterDetails details = new ReporterDetails();
    String ContentInformationType = mets.getCONTENTINFORMATIONTYPE();
    String OtherContentInformationType = mets.getOTHERCONTENTINFORMATIONTYPE();
    if(ContentInformationType != null){
      if(ContentInformationType.equalsIgnoreCase("Other") && (OtherContentInformationType == null || OtherContentInformationType.equals(""))){
        details.setValid(false);
        details.setMessage("When mets/@csip:CONTENTINFORMATIONTYPE have the value OTHER  mets/@csip:OTHERCONTENTINFORMATIONTYPE can't be null or empty");
      }
    }
    return details;
  }

  /*
  * mets/@PROFILE
  * The URL of the METS profile that the information package conforms with.
  */
  private ReporterDetails validateCSIP6(){
    ReporterDetails details = new ReporterDetails();
    String profile = mets.getPROFILE();
    if(profile == null || profile.equals("")){
      details.setValid(false);
      details.setMessage("mets/@PROFILE attribute is mandatory, can't be null or empty!");
    }
    return details;
  }

  /*
  * Method for cleaning all the objects used in the validation
  */

  private void cleanValidationObjects(){
    this.contentCategory.clear();
    this.contentInformationType.clear();
  }
}
