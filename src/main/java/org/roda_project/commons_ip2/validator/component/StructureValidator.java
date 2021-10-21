package org.roda_project.commons_ip2.validator.component;

import org.roda_project.commons_ip2.validator.observer.ValidationObserver;
import org.roda_project.commons_ip2.validator.reporter.ReporterDetails;
import org.roda_project.commons_ip2.validator.state.StructureValidatorState;

import java.io.IOException;
import java.util.Map;

/**
 * @author João Gomes <jgomes@keep.pt>
 */
public interface StructureValidator {

    void addObserver(ValidationObserver observer);

    void removeObserver(ValidationObserver observer);

    Map<String, ReporterDetails> validate(StructureValidatorState structureValidatorState) throws IOException;
}
