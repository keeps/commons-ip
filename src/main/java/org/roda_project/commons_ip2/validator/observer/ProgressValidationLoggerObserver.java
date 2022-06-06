package org.roda_project.commons_ip2.validator.observer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** {@author João Gomes <jgomes@keep.pt>}. */
public class ProgressValidationLoggerObserver implements ValidationObserver {
  /**
   * The {@link Logger}.
   */
  private static final Logger LOGGER = LoggerFactory.getLogger(ProgressValidationLoggerObserver.class);

  @Override
  public void notifyValidationStart() {
    LOGGER.info("Start validation");
  }

  @Override
  public void notifyStartValidationModule(final String moduleName, final String id) {
    LOGGER.info("Start validation of: {} - {}", id, moduleName);
  }

  @Override
  public void notifyStartStep(final String id) {
    LOGGER.info("Start validation of: {}", id);
  }

  @Override
  public void notifyFinishStep(final String id) {
    LOGGER.info("Finish validation of: {}", id);
  }

  @Override
  public void notifyFinishModule(final String moduleName) {
    LOGGER.info("Finish validation of module: {}", moduleName);
  }

  @Override
  public void notifyFinishValidation() {
    LOGGER.info("Finish validation");
  }

  @Override
  public void notifyIndicators(final int errors, final int success, final int warnings, final int notes,
    final int skipped) {
    LOGGER.info("Number of requirements success [{}]", success);
    LOGGER.info("Number of requirements failed [{}]", errors);
    LOGGER.info("Number of warnings [{}]", warnings);
    LOGGER.info("Number of notes [{}]", notes);
    LOGGER.info("Number of skipped [{}]", skipped);
  }
}
