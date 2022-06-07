package org.roda_project.commons_ip2.validator.component;

import java.io.IOException;
import java.util.Map;

import org.roda_project.commons_ip2.validator.observer.ValidationObserver;
import org.roda_project.commons_ip2.validator.reporter.ReporterDetails;
import org.roda_project.commons_ip2.validator.state.StructureValidatorState;

/** {@author João Gomes <jgomes@keep.pt>}. */
public interface StructureValidator {

  /**
   * Adds the {@link ValidationObserver} to the {@link java.util.List} of
   * observers.
   * 
   * @param observer
   *          {@link ValidationObserver}.
   */
  void addObserver(ValidationObserver observer);

  /**
   * Removes the {@link ValidationObserver} to the {@link java.util.List} of
   * observers.
   * 
   * @param observer
   *          {@link ValidationObserver}.
   */
  void removeObserver(ValidationObserver observer);

  /**
   * Validates the IP.
   * 
   * @param structureValidatorState
   *          {@link StructureValidatorState}.
   * @return {@link Map} with the results.
   * @throws IOException
   *           if some error occurs.
   */
  Map<String, ReporterDetails> validate(StructureValidatorState structureValidatorState) throws IOException;
}
