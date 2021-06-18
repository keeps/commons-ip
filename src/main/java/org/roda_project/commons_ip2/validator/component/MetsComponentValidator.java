package org.roda_project.commons_ip2.validator.component;

import org.roda_project.commons_ip2.validator.common.ControlledVocabularyParser;
import org.roda_project.commons_ip2.validator.constants.Constants;
import org.roda_project.commons_ip2.validator.reporter.ValidationReporter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author João Gomes <jgomes@keep.pt>
 */
public class MetsComponentValidator {
  private static final Logger LOGGER = LoggerFactory.getLogger(MetsComponentValidator.class);
  private HashMap<String,String> data;
  private final String MODULE_NAME;
  private ValidationReporter reporter;
  private List<String> contentCategory;
  private List<String> contentInformationType;

  public void setContentCategory(List<String> contentCategory) {
    this.contentCategory = new ArrayList<String>(contentCategory);
  }

  public void setContentInformationType(List<String> contentInformationType) {
    this.contentInformationType = new ArrayList<String>(contentInformationType);
  }

  public MetsComponentValidator(String moduleName ,HashMap<String, String> data, ValidationReporter reporter) {
    this.MODULE_NAME = moduleName;
    this.data = data;
    this.reporter = reporter;
    this.contentCategory = new ArrayList<>();
    ControlledVocabularyParser controlledVocabularyParser = new ControlledVocabularyParser(Constants.PATH_RESOURCES_CSIP_VOCABULARY_CONTENT_CATEGORY,contentCategory);
    controlledVocabularyParser.parse();
    setContentCategory(controlledVocabularyParser.getData());

    this.contentInformationType = new ArrayList<>();
    controlledVocabularyParser = new ControlledVocabularyParser(Constants.PATH_RESOURCES_CSIP_VOCABULARY_CONTENT_INFORMATION_TYPE,contentInformationType);
    controlledVocabularyParser.parse();
    setContentInformationType(controlledVocabularyParser.getData());
  }

  public boolean validateMets() throws SAXException {
    String OBJID = data.get("OBJID");
    String CONTENTINFORMATIONTYPE = data.get("CONTENTINFORMATIONTYPE");
    String PROFILE = data.get("PROFILE");
    if(OBJID == null){
      reporter.componentValidationResult(Constants.VALIDATION_REPORT_HEADER_ID_CSIP,"CSIP1",false,"mets/@OBJID attribute is mandatory!");
      reporter.countErrors();
      return false;
    }
    reporter.componentValidationResult(Constants.VALIDATION_REPORT_HEADER_ID_CSIP,"CSIP1",true,"mets/@OBJID attribute is mandatory!");
    reporter.countSuccess();
    if(PROFILE == null){
      reporter.componentValidationResult(Constants.VALIDATION_REPORT_HEADER_ID_CSIP,"CSIP2",false,"mets/@PROFILE The URL of the METS profile that the information package conforms with.");
      reporter.countErrors();
    }
    else{
      reporter.componentValidationResult(Constants.VALIDATION_REPORT_HEADER_ID_CSIP,"CSIP2",true,"mets/@PROFILE The URL of the METS profile that the information package conforms with.");
      reporter.countSuccess();
    }

    for(String s: contentCategory){
      System.out.println(s);
    }
    System.out.println("##################");
    for(String s: contentInformationType){
      System.out.println(s);
    }
    return true;
  }
}
