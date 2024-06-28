package org.roda_project.commons_ip2.validator.components;

import java.util.ArrayList;
import java.util.List;

import org.roda_project.commons_ip2.validator.observer.ValidationObserver;

/** {@author João Gomes <jgomes@keep.pt>}. */
public abstract class MetsValidatorImpl implements MetsValidator {

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

  public void notifyObserversValidationStarted(final String moduleName, final String id) {
    for (ValidationObserver observer : observers) {
      observer.notifyStartValidationModule(moduleName, id);
      observer.notifyStartStep(id);
    }
  }

  public void notifyObserversFinishModule(final String moduleName) {
    for (ValidationObserver observer : observers) {
      observer.notifyFinishModule(moduleName);
    }
  }
}
