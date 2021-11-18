package org.roda_project.commons_ip2.validator.observer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** {@author João Gomes <jgomes@keep.pt>}. */
public class ProgressValidationLoggerObserver implements ValidationObserver {
  private static final Logger LOGGER =
      LoggerFactory.getLogger(ProgressValidationLoggerObserver.class);

  @Override
  public void notifyValidationStart() {
    LOGGER.info("Start validation");
  }

  @Override
  public void notifyStartValidationModule(String moduleName, String ID) {
    LOGGER.info("Start validation of: {} - {}", ID, moduleName);
  }

  @Override
  public void notifyStartStep(String ID) {
    LOGGER.info("Start validation of: {}", ID);
  }

  @Override
  public void notifyFinishStep(String ID) {
    LOGGER.info("Finish validation of: {}", ID);
  }

  @Override
  public void notifyFinishModule(String moduleName) {
    LOGGER.info("Finish validation of module: {}", moduleName);
  }

  @Override
  public void notifyFinishValidation() {
    LOGGER.info("Finish validation");
  }

  @Override
  public void notifyIndicators(int errors, int success, int warnings, int notes, int skipped) {
    LOGGER.info("Number of requirements success [{}]", success);
    LOGGER.info("Number of requirements failed [{}]", errors);
    LOGGER.info("Number of warnings [{}]", warnings);
    LOGGER.info("Number of notes [{}]", notes);
    LOGGER.info("Number of skipped [{}]", skipped);
  }
}
