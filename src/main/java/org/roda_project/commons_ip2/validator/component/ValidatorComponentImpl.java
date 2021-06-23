package org.roda_project.commons_ip2.validator.component;

import org.roda_project.commons_ip2.validator.common.ZipManager;
import org.roda_project.commons_ip2.validator.constants.Constants;
import org.roda_project.commons_ip2.validator.constants.ConstantsCSIPspec;
import org.roda_project.commons_ip2.validator.observer.ValidationObserver;
import org.roda_project.commons_ip2.validator.reporter.ValidationReporter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.nio.file.Path;

/**
 * @author João Gomes <jgomes@keep.pt>
 */
public abstract class ValidatorComponentImpl implements ValidatorComponent {
  private static final Logger LOGGER = LoggerFactory.getLogger(ValidatorComponentImpl.class);

  protected Path path = null;
  private ValidationReporter reporter = null;
  protected ValidationObserver observer = null;
  protected ZipManager zipManager = null;
  protected SAXParserFactory factory = null;
  private String zipName = null;

  protected Path getEARKSIPpath() {
    return  path;
  }

  @Override
  public void setEARKSIPpath(Path path) {
    this.path = path;
    zipName = path.toString().split("/")[path.toString().split("/").length - 1];
  }

  public String getZipName(){
    return zipName;
  }

  protected ValidationReporter getReporter() {
    return reporter;
  }

  @Override
  public void setReporter(ValidationReporter reporter) {
    this.reporter = reporter;
  }

  @Override
  public void setZipManager(ZipManager zipManager) {
    this.zipManager = zipManager;
  }

  @Override
  public void setObserver(ValidationObserver observer) {
    this.observer = observer;
  }

  @Override
  public void clean() {
    this.zipManager.closeZipFile();
  }

  public SAXParser getSAXParser() throws ParserConfigurationException, SAXException {
    factory = SAXParserFactory.newInstance();
    return factory.newSAXParser();
  }

  protected void validationOutcomeFailed(String specification,String ID, String detail){
    reporter.componentValidationResult(specification, ID, Constants.VALIDATION_REPORT_SPECIFICATION_TESTING_OUTCOME_FAILED, detail);
    if(ConstantsCSIPspec.getSpecificationLevel(ID).equals("MUST")){
      reporter.countErrors();
    }
    else{
      reporter.countWarnings();
    }
    observer.notifyFinishStep(ID);
  }

  protected void validationOutcomePassed(String specification,String ID, String detail){
    reporter.componentValidationResult(specification, ID, Constants.VALIDATION_REPORT_SPECIFICATION_TESTING_OUTCOME_PASSED, detail);
    reporter.countSuccess();
    observer.notifyFinishStep(ID);
  }

  protected void validationInit(String moduleName, String ID){
    observer.notifyStartValidationModule(moduleName,ID);
    observer.notifyStartStep(ID);
  }




}
