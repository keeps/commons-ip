package org.roda_project.commons_ip2.validator;

import org.roda_project.commons_ip2.validator.common.ZipManager;
import org.roda_project.commons_ip2.validator.component.MetsComponentValidator;
import org.roda_project.commons_ip2.validator.component.ValidatorComponent;
import org.roda_project.commons_ip2.validator.constants.Constants;
import org.roda_project.commons_ip2.validator.observer.ProgressValidationLoggerObserver;
import org.roda_project.commons_ip2.validator.observer.ValidationObserver;
import org.roda_project.commons_ip2.validator.reporter.ValidationReporter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * @author João Gomes <jgomes@keep.pt>
 */

public class EARKSIPValidator {
  private static final Logger LOGGER = LoggerFactory.getLogger(EARKSIPValidator.class);
  private Path earksipPath;

  private ValidationReporter reporter;
  private ZipManager zipManager;
  private ValidationObserver observer;

  private List<ValidatorComponent> components;

  public EARKSIPValidator(Path earksipPath, Path reportPath){
    this.earksipPath = earksipPath.toAbsolutePath().normalize();
    reporter = new ValidationReporter(reportPath.toAbsolutePath().normalize());
    zipManager = new ZipManager();
    observer = new ProgressValidationLoggerObserver();
    setupComponents();
  }

  private  void setupComponents() {
    components = new ArrayList<>();
    ValidatorComponent metsComponent = new MetsComponentValidator(Constants.CSIP_MODULE_NAME_1);
    components.add(metsComponent);
  }

  public boolean validate() {
    observer.notifyValidationStart();
    try {
      for(ValidatorComponent component : components){
        component.setObserver(observer);
        component.setReporter(reporter);
        component.setZipManager(zipManager);
        component.setEARKSIPpath(earksipPath);
        if(component.validateComponent()){
          reporter.componentValidationFinish("VALID");
        }
        else{
          reporter.componentValidationFinish("INVALID");
        }
      }
      reporter.close();
      observer.notifyFinishValidation();
    } catch (ParserConfigurationException | SAXException | IOException e){
      LOGGER.error("Could not parse file.", e);
    }
    return true;
  }
}
