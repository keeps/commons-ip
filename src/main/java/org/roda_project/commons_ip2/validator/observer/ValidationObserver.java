package org.roda_project.commons_ip2.validator.observer;

/**
 * @author João Gomes <jgomes@keep.pt>
 */

public interface ValidationObserver {

  void notifyValidationStart();

  void notifyStartValidationModule(String moduleName, String ID);

  void notifyStartStep(String id);

  void notifyFinishStep(String id);

  void notifyFinishModule(String moduleName);

  void notifyFinishValidation();

  void notifyIndicators(int errors, int success, int warnings);

}
