package org.roda_project.commons_ip2.validator.component;

import org.roda_project.commons_ip2.validator.observer.ValidationObserver;

import java.util.ArrayList;
import java.util.List;

/**
 * @author João Gomes <jgomes@keep.pt>
 */
public abstract class StructureValidatorImpl implements StructureValidator {
  private List<ValidationObserver> observers = new ArrayList<>();

  @Override
  public void addObserver(ValidationObserver observer) {
    this.observers.add(observer);
  }

  @Override
  public void removeObserver(ValidationObserver observer) {
    this.observers.remove(observer);
  }

  public void notifyObserversIPValidationStarted(){
    observers.forEach(ValidationObserver::notifyValidationStart);
  }

  protected void notifyObserversValidationStarted(String moduleName, String ID) {
    for (ValidationObserver observer : observers) {
      observer.notifyStartValidationModule(moduleName, ID);
      observer.notifyStartStep(ID);
    }
  }

  protected void notifyObserversFinishModule(String moduleName) {
    for (ValidationObserver observer : observers) {
      observer.notifyFinishModule(moduleName);
    }
  }
}
