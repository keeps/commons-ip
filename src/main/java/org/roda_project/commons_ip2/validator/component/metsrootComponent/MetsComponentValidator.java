package org.roda_project.commons_ip2.validator.component.metsrootComponent;

import org.roda_project.commons_ip2.validator.common.ControlledVocabularyParser;
import org.roda_project.commons_ip2.validator.component.ValidatorComponentImpl;
import org.roda_project.commons_ip2.validator.constants.Constants;
import org.roda_project.commons_ip2.validator.constants.ConstantsCSIPspec;
import org.roda_project.commons_ip2.validator.handlers.MetsComponentHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author João Gomes <jgomes@keep.pt>
 */
public class MetsComponentValidator extends ValidatorComponentImpl {
  private static final Logger LOGGER = LoggerFactory.getLogger(MetsComponentValidator.class);

  private HashMap<String,String> data;
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
    this.data = new HashMap<>();
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
  public boolean validate() throws SAXException, ParserConfigurationException, IOException {
    boolean valid = true;

    /* Parse mets element in METS.xml  */
    MetsComponentHandler handler = new MetsComponentHandler("mets", data);
    InputStream stream = zipManager.getMetsRootInputStream(path);
    getSAXParser().parse(stream, handler);

    /* CSIP1 Validation */
    observer.notifyStartValidationModule(MODULE_NAME,ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP1_ID);
    observer.notifyStartStep(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP1_ID);
    if(!validateCSIP1()){
      getReporter().componentValidationResult(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP1_ID,
              false,"mets/@OBJID attribute is mandatory!");
      getReporter().countErrors();
      valid = false;
    }
    getReporter().componentValidationResult(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP1_ID,
            true,"");
    getReporter().countSuccess();
    observer.notifyFinishStep(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP1_ID);

    /* CSIP2 Validation */
    observer.notifyStartValidationModule(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP2_ID);
    observer.notifyStartStep(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP2_ID);
    if(!validateCSIP2()){
      getReporter().componentValidationResult(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP2_ID,
              false,"The mets/@TYPE attribute MUST be used to declare the category of the content held in the package.");
      getReporter().countErrors();
      valid = false;
    }
    getReporter().componentValidationResult(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP2_ID,
            true,"");
    getReporter().countSuccess();
    observer.notifyFinishStep(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP2_ID);

    /* CSIP3 Validation */
    observer.notifyStartValidationModule(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP3_ID);
    observer.notifyStartStep(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP3_ID);
    if(!validateCSIP3()){
      getReporter().componentValidationResult(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP3_ID,
                      false, "When the mets/@TYPE attribute has the value “OTHER” the " +
                      "mets/@csip:OTHERTYPE attribute MUST be used to declare the content " +
                      "category of the package/representation");
      getReporter().countWarnings();
      valid = false;
    }
    getReporter().componentValidationResult(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP3_ID,true,"");
    getReporter().countSuccess();
    observer.notifyFinishStep(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP3_ID);

    /* CSIP4 Validation */
    observer.notifyStartValidationModule(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP4_ID);
    observer.notifyStartStep(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP4_ID);
    if(!validateCSIP4()){
      getReporter().componentValidationResult(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP4_ID,
              false, "Should include @csip:CONTENTINFORMATIONTYPE!");
      getReporter().countWarnings();
      valid = false;
    }
    getReporter().componentValidationResult(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP4_ID,true,"");
    getReporter().countSuccess();
    observer.notifyFinishStep(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP4_ID);

    /* CSIP5 Validation */
    observer.notifyStartValidationModule(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP5_ID);
    observer.notifyStartStep(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP5_ID);
    if(!validateCSIP5()){
      getReporter().componentValidationResult(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP5_ID,
              false, "Should include @csip:OTHERCONTENTINFORMATIONTYPE!");
      getReporter().countWarnings();
      valid = false;
    }
    getReporter().componentValidationResult(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP5_ID,true,"");
    getReporter().countSuccess();
    observer.notifyFinishStep(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP5_ID);

    /* CSIP6 Validation */
    observer.notifyStartValidationModule(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP6_ID);
    observer.notifyStartStep(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP6_ID);
    if(!validateCSIP6()){
      getReporter().componentValidationResult(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP6_ID,
              false, "Must include The URL of the METS profile that the information package conforms with.");
      getReporter().countErrors();
      valid = false;
    }
    getReporter().componentValidationResult(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP6_ID,true,"");
    getReporter().countSuccess();
    observer.notifyFinishStep(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP5_ID);
    observer.notifyFinishModule(MODULE_NAME);
    return valid;
  }

  @Override
  public void clean() {
    zipManager.closeZipFile();
  }

  private boolean validateCSIP1() throws IOException {
    boolean valid = true;
    String OBJID = data.get("OBJID");
    if(OBJID == null){
      valid = false;
    }
    else {
      String metsxmlPath = zipManager.getMETSxmlPath(path);

      if(metsxmlPath.contains("/")){
        String comparator = metsxmlPath.split("/")[metsxmlPath.split("/").length - 2];
        if(!OBJID.equals(comparator)){
          valid = false;
        }
      }
      else{
        if(!OBJID.equals(getZipName().split("\\.")[0])){
          valid = false;
        }
      }
    }
    return valid;
  }

  private boolean validateCSIP2() {
    boolean valid = true;
    String TYPE = data.get("TYPE");
    if(!contentCategory.contains(TYPE)){
      valid = false;
    }
    return valid;
  }

  private boolean validateCSIP3() {
    boolean valid = true;
    String TYPE = data.get("TYPE");
    String otherType = data.get("csip:OTHERTYPE");

    if(TYPE.equalsIgnoreCase("Other") && otherType == null){
      valid = false;
    }
    return valid;
  }

  private boolean validateCSIP4() {
    boolean valid = true;
    String ContentInformationType = data.get("@csip:CONTENTINFORMATIONTYPE");
    if(ContentInformationType != null){
      if(!contentInformationType.contains(ContentInformationType)){
        valid = false;
      }
    }
    return valid;
  }

  private boolean validateCSIP5(){
    boolean valid = true;
    String ContentInformationType = data.get("@csip:CONTENTINFORMATIONTYPE");
    String OtherContentInformationType = data.get("@csip:OTHERCONTENTINFORMATIONTYPE");
    if(ContentInformationType != null){
      if(ContentInformationType.equalsIgnoreCase("Other") && OtherContentInformationType == null){
        valid = false;
      }
    }
    return valid;
  }

  private boolean validateCSIP6(){
    boolean valid = true;
    String profile = data.get("PROFILE");
    if(profile == null){
      valid = false;
    }
    return valid;
  }
}
