package org.roda_project.commons_ip2.validator.component;

import org.roda_project.commons_ip2.validator.constants.Constants;
import org.roda_project.commons_ip2.validator.reporter.ValidationReporter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;

import java.util.HashMap;

/**
 * @author João Gomes <jgomes@keep.pt>
 */
public class MetsComponentValidator {
  private static final Logger LOGGER = LoggerFactory.getLogger(MetsComponentValidator.class);
  private HashMap<String,String> data;
  private final String MODULE_NAME;
  private ValidationReporter reporter;

  public MetsComponentValidator(String moduleName ,HashMap<String, String> data, ValidationReporter reporter) {
    this.MODULE_NAME = moduleName;
    this.data = data;
    this.reporter = reporter;
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

    return true;
  }
}
