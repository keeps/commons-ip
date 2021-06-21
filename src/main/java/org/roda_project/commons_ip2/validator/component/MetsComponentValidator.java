package org.roda_project.commons_ip2.validator.component;

import org.roda_project.commons_ip2.validator.common.ControlledVocabularyParser;
import org.roda_project.commons_ip2.validator.common.ZipManager;
import org.roda_project.commons_ip2.validator.constants.Constants;
import org.roda_project.commons_ip2.validator.constants.ConstantsCSIPspec;
import org.roda_project.commons_ip2.validator.handlers.MetsComponentHandler;
import org.roda_project.commons_ip2.validator.observer.ValidationObserver;
import org.roda_project.commons_ip2.validator.reporter.ValidationReporter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
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
  public boolean validateComponent() throws SAXException, ParserConfigurationException, IOException {
    observer.notifyStartValidationModule(MODULE_NAME,ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP1_ID);
    SAXParserFactory factory = SAXParserFactory.newInstance();
    SAXParser saxParser = factory.newSAXParser();
    MetsComponentHandler handler = new MetsComponentHandler("mets", data);
    InputStream stream = zipManager.getZipInputStream(path,"METS.xml");
    saxParser.parse(stream, handler);


    String OBJID = data.get("OBJID");
    String CONTENTINFORMATIONTYPE = data.get("CONTENTINFORMATIONTYPE");
    String PROFILE = data.get("PROFILE");
    observer.notifyStartStep(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP1_ID);
    if(OBJID == null){
      getReporter().componentValidationResult(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP1_ID,false,"mets/@OBJID attribute is mandatory!");
      getReporter().countErrors();
      return false;
    }
    getReporter().componentValidationResult(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP1_ID,true,"mets/@OBJID attribute is mandatory!");
    getReporter().countSuccess();
    observer.notifyFinishStep(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP1_ID);
    observer.notifyStartValidationModule(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP2_ID);
    observer.notifyStartStep(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP2_ID);
    if(PROFILE == null){
      getReporter().componentValidationResult(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP2_ID,false,"mets/@PROFILE The URL of the METS profile that the information package conforms with.");
      getReporter().countErrors();
    }
    else{
      getReporter().componentValidationResult(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP2_ID,true,"mets/@PROFILE The URL of the METS profile that the information package conforms with.");
      getReporter().countSuccess();
    }
    observer.notifyFinishStep(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP2_ID);
    observer.notifyFinishModule(MODULE_NAME);
    return true;
  }

  @Override
  public void clean() {
    zipManager.closeZipFile();
  }
}
