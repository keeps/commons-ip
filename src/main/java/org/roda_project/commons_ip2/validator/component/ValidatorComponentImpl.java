package org.roda_project.commons_ip2.validator.component;

import org.roda_project.commons_ip2.validator.common.ZipManager;
import org.roda_project.commons_ip2.validator.observer.ValidationObserver;
import org.roda_project.commons_ip2.validator.reporter.ValidationReporter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

  protected Path getEARKSIPpath() {
    return  path;
  }

  @Override
  public void setEARKSIPpath(Path path) {
    this.path = path;
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

  protected void validationError(String specification,String MODULE_NAME,String ID, boolean status, String detail){
    reporter.componentValidationResult(specification, ID,status, detail);
  }
  
}
