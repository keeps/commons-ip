package org.roda_project.commons_ip2.validator.utils;

import org.roda_project.commons_ip2.validator.component.fileComponent.StructureComponentValidator;
import org.roda_project.commons_ip2.validator.constants.ConstantsCSIPspec;
import org.roda_project.commons_ip2.validator.reporter.ReporterDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * @author João Gomes <jgomes@keep.pt>
 */
public class ResultsUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(ResultsUtils.class);

    public static void addResult(Map<String, ReporterDetails> results, String specification, ReporterDetails details) {
        if (results.containsKey(specification)) {
            ReporterDetails currentResult = results.get(specification);
            if(specification.equals(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP46_ID)) {
                LOGGER.debug("Current result: {}",  currentResult.getIssues());
                LOGGER.debug("Details: {}", details.getIssues());
            }


            // Merge current result with new test case validation outcome
            currentResult.addIssues(details.getIssues());
            currentResult.setSkipped(currentResult.isSkipped() && details.isSkipped());
            currentResult.setValid(currentResult.isValid() && details.isValid());
        } else {
            results.put(specification, details);
        }

    }

    public static void addResults(Map<String, ReporterDetails> results, Map<String,ReporterDetails> componentResults){
        componentResults.forEach((s,d) -> addResult(results, s, d));
    }
}
