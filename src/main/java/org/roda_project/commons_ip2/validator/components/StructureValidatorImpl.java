package org.roda_project.commons_ip2.validator.components;

import java.util.ArrayList;
import java.util.List;

import org.roda_project.commons_ip2.validator.observer.ValidationObserver;

/** {@author João Gomes <jgomes@keep.pt>}. */
public abstract class StructureValidatorImpl implements StructureValidator {
  /**
   * {@link List} of {@link ValidationObserver}.
   */
  private final List<ValidationObserver> observers = new ArrayList<>();

  @Override
  public void addObserver(final ValidationObserver observer) {
    this.observers.add(observer);
  }

  @Override
  public void removeObserver(final ValidationObserver observer) {
    this.observers.remove(observer);
  }

  /**
   * Notify Observers that validation is started.
   */
  public void notifyObserversIPValidationStarted() {
    observers.forEach(ValidationObserver::notifyValidationStart);
  }

  /**
   * Notify observers that validation is finished.
   */
  public void notifyObserversIPValidationFinished() {
    observers.forEach(ValidationObserver::notifyFinishValidation);
  }

  /**
   * Notify observers with all final results of validation.
   *
   * @param errors
   *          number of requirements with errors.
   * @param success
   *          number of requirements with success
   * @param warnings
   *          number of requirements with warnings
   * @param notes
   *          number of requirements with notes
   * @param skipped
   *          number of requirements skipped
   */
  public void notifyIndicators(final int errors, final int success, final int warnings, final int notes,
    final int skipped) {
    for (ValidationObserver observer : observers) {
      observer.notifyIndicators(errors, success, warnings, notes, skipped);
    }
  }

  protected void notifyObserversValidationStarted(final String moduleName, final String id) {
    for (ValidationObserver observer : observers) {
      observer.notifyStartValidationModule(moduleName, id);
      observer.notifyStartStep(id);
    }
  }

  protected void notifyObserversFinishModule(final String moduleName) {
    for (ValidationObserver observer : observers) {
      observer.notifyFinishModule(moduleName);
    }
  }
}
