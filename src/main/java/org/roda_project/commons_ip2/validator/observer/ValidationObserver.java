package org.roda_project.commons_ip2.validator.observer;

/** {@author João Gomes <jgomes@keep.pt>}. */
public interface ValidationObserver {

  /**
   * Notify when the validation start.
   */
  void notifyValidationStart();

  /**
   * notify which module is validating.
   * 
   * @param moduleName
   *          {@link String}
   * @param id
   *          {@link String}
   */
  void notifyStartValidationModule(String moduleName, String id);

  /**
   * Notify which step is validating.
   * 
   * @param id
   *          {@link String}.
   */
  void notifyStartStep(String id);

  /**
   * Notify which step is finishing.
   *
   * @param id
   *          {@link String}.
   */
  void notifyFinishStep(String id);

  /**
   * Notify which module is finishing.
   * 
   * @param moduleName
   *          {@link String}.
   */
  void notifyFinishModule(String moduleName);

  /**
   * Notify finished validation.
   */
  void notifyFinishValidation();

  /**
   * Notify the indicators of validation.
   * 
   * @param errors
   *          number of errors.
   * @param success
   *          number of success.
   * @param warnings
   *          number of warnings
   * @param notes
   *          number of notes.
   * @param skipped
   *          number of skipped.
   */
  void notifyIndicators(int errors, int success, int warnings, int notes, int skipped);

}
