package org.roda_project.commons_ip2.validator.component.metsrootComponent;

import org.roda_project.commons_ip2.mets_v1_12.beans.Mets;
import org.roda_project.commons_ip2.validator.common.ControlledVocabularyParser;
import org.roda_project.commons_ip2.validator.component.ValidatorComponentImpl;
import org.roda_project.commons_ip2.validator.constants.Constants;
import org.roda_project.commons_ip2.validator.constants.ConstantsCSIPspec;
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

    /* CSIP1 Validation */
    validationInit(MODULE_NAME,ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP1_ID);
    if(!validateCSIP1()){
      validationOutcomeFailed(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP1_ID,"mets/@OBJID attribute is mandatory!");
      valid = false;
    }
    else validationOutcomePassed(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP1_ID,"");

    /* CSIP2 Validation */
    validationInit(MODULE_NAME,ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP2_ID);
    if(!validateCSIP2()){
      validationOutcomeFailed(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP2_ID,"The mets/@TYPE attribute MUST be used to declare the category of the content held in the package.");
      valid = false;
    }
    else validationOutcomePassed(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP2_ID,"");

    /* CSIP3 Validation */
    validationInit(MODULE_NAME,ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP3_ID);
    if(!validateCSIP3()){
      validationOutcomeFailed(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP3_ID, "When the mets/@TYPE attribute has the value “OTHER” the " +
                      "mets/@csip:OTHERTYPE attribute MUST be used to declare the content " +
                      "category of the package/representation");
      valid = false;
    }
    else validationOutcomePassed(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP3_ID,"");

    /* CSIP4 Validation */
    validationInit(MODULE_NAME,ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP4_ID);
    if(!validateCSIP4()){
      validationOutcomeFailed(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP4_ID, "Should include @csip:CONTENTINFORMATIONTYPE!");
      valid = false;
    }
    else validationOutcomePassed(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP4_ID,"");

    /* CSIP5 Validation */
    validationInit(MODULE_NAME,ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP5_ID);
    if(!validateCSIP5()){
      validationOutcomeFailed(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP5_ID, "Should include @csip:OTHERCONTENTINFORMATIONTYPE!");
      valid = false;
    }
    else validationOutcomePassed(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP5_ID,"");

    /* CSIP6 Validation */
    validationInit(MODULE_NAME,ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP6_ID);
    if(!validateCSIP6()){
      validationOutcomeFailed(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP6_ID, "Must include The URL of the METS profile that the information package conforms with.");
      valid = false;
    }
    else validationOutcomePassed(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP6_ID,"");
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
  private boolean validateCSIP1() throws IOException {
    boolean valid = true;
    String OBJID = mets.getOBJID();
    if(OBJID == null){
      valid = false;
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
          valid = false;
        }
    }
    return valid;
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
  private boolean validateCSIP2() {
    boolean valid = true;
    String TYPE = mets.getTYPE();
    if(TYPE == null || TYPE.equals("")){
      valid = false;
    }
    else {
      if(!contentCategory.contains(TYPE)){
        valid = false;
      }
    }
    return valid;
  }

  /*
  * mets[@TYPE=’OTHER’]/@csip:OTHERTYPE
  * When the mets/@TYPE attribute has the value “OTHER” the
  * mets/@csip:OTHERTYPE attribute MUST be used to declare the content
  * category of the package/representation.See also: Content Category
  */
  private boolean validateCSIP3() {
    boolean valid = true;
    String TYPE = mets.getTYPE();
    String otherType = mets.getOTHERTYPE();
    if(TYPE != null){
      if(TYPE.equalsIgnoreCase("Other") && otherType == null){
        valid = false;
      }
    }
    return valid;
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
  private boolean validateCSIP4() {
    boolean valid = true;
    String ContentInformationType = mets.getCONTENTINFORMATIONTYPE();
    if(ContentInformationType != null){
      if(!contentInformationType.contains(ContentInformationType)){
        valid = false;
      }
    }
    return valid;
  }

  /*
  * mets[@csip:CONTENTINFORMATIONTYPE=’OTHER’]/@csip:OTHERCONTENTINFORMATIONTYPE
  * When the mets/@csip:CONTENTINFORMATIONTYPE has the value
  * “OTHER” the mets/@csip:OTHERCONTENTINFORMATIONTYPE must state
  * the content information type.
  */
  private boolean validateCSIP5(){
    boolean valid = true;
    String ContentInformationType = mets.getCONTENTINFORMATIONTYPE();
    String OtherContentInformationType = mets.getOTHERCONTENTINFORMATIONTYPE();
    if(ContentInformationType != null){
      if(ContentInformationType.equalsIgnoreCase("Other") && OtherContentInformationType == null){
        valid = false;
      }
    }
    return valid;
  }

  /*
  * mets/@PROFILE
  * The URL of the METS profile that the information package conforms with.
  */
  private boolean validateCSIP6(){
    boolean valid = true;
    String profile = mets.getPROFILE();
    if(profile == null || profile.equals("")){
      valid = false;
    }
    return valid;
  }

  /*
  * Method for cleaning all the objects used in the validation
  */

  private void cleanValidationObjects(){
    this.contentCategory.clear();
    this.contentInformationType.clear();
  }
}
