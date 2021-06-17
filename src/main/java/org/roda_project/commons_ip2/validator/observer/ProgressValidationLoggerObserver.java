package org.roda_project.commons_ip2.validator.observer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author João Gomes <jgomes@keep.pt>
 */
public class ProgressValidationLoggerObserver  implements  ValidationObserver{
  private static final Logger LOGGER = LoggerFactory.getLogger(ProgressValidationLoggerObserver.class);

  @Override
  public void notifyValidationStart() {
    LOGGER.info("Start validation");
  }

  @Override
  public void notifyStartValidationModule(String moduleName, String ID) {
    LOGGER.info("Start validation of: {} - {}", ID, moduleName);
  }
}
