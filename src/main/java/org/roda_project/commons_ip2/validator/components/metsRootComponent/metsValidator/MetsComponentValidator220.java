package org.roda_project.commons_ip2.validator.components.metsRootComponent.metsValidator;

import org.roda_project.commons_ip2.validator.common.ControlledVocabularyParser;
import org.roda_project.commons_ip2.validator.components.MetsValidatorImpl;
import org.roda_project.commons_ip2.validator.constants220.Constants;
import org.roda_project.commons_ip2.validator.constants220.ConstantsCSIPspec;
import org.roda_project.commons_ip2.validator.reporter.ReporterDetails;
import org.roda_project.commons_ip2.validator.state.MetsValidatorState;
import org.roda_project.commons_ip2.validator.state.StructureValidatorState;
import org.roda_project.commons_ip2.validator.utils.ResultsUtils;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Carlos Afonso <cafonso@keep.pt>
 */
public class MetsComponentValidator220 extends MetsValidatorImpl {

  /**
   * {@link String} moduleName.
   */
  private final String moduleName;

  /**
   * {@link List} of {@link String} with content categories.
   */
  private final List<String> contentCategory;

  /**
   * {@link List} of {@link String} with content information types.
   */
  private final List<String> contentInformationTypesList;

  /**
   * Initialize all objects needed to validation of this component.
   *
   * @throws IOException
   *           if some I/O errors occurs
   * @throws ParserConfigurationException
   *           if some error occurs
   * @throws SAXException
   *           if some error occurs
   */
  public MetsComponentValidator220() throws IOException, ParserConfigurationException, SAXException {
    this.moduleName = Constants.CSIP_MODULE_NAME_2;
    this.contentCategory = ControlledVocabularyParser.parse(Constants.PATH_RESOURCES_CSIP_VOCABULARY_CONTENT_CATEGORY);
    this.contentInformationTypesList = ControlledVocabularyParser
      .parse(Constants.PATH_RESOURCES_CSIP_VOCABULARY_CONTENT_INFORMATION_TYPE);
  }

  /**
   * Validates CSIP1 to CSIP6 requirements.
   *
   * @param structureValidatorState
   *          the contextual {@link StructureValidatorState}
   * @param metsValidatorState
   *          the contextual {@link MetsValidatorState}
   * @return {@link Map} map with results of validation.
   * @throws IOException
   *           if some I/O errors occurs
   */
  @Override
  public Map<String, ReporterDetails> validate(final StructureValidatorState structureValidatorState,
                                               final MetsValidatorState metsValidatorState) throws IOException {
    final Map<String, ReporterDetails> results = new HashMap<>();

    MetsValidatorFactory metsValidatorFactory = new MetsValidatorFactory();
    MetsValidator generator = metsValidatorFactory.getGenerator("2.2.0");
    /* CSIP1 */

    notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP1_ID);
    ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP1_ID, generator
      .validateCSIP1(structureValidatorState, metsValidatorState).setSpecification(generator.getCSIPVersion()));

    /* CSIP2 */
    notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP2_ID);
    ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP2_ID,
      generator.validateCSIP2(metsValidatorState, contentCategory).setSpecification(generator.getCSIPVersion()));

    /* CSIP3 */
    notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP3_ID);
    ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP3_ID,
      generator.validateCSIP3(metsValidatorState).setSpecification(generator.getCSIPVersion()));

    /* CSIP4 */
    notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP4_ID);
    ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP4_ID, generator
      .validateCSIP4(metsValidatorState, contentInformationTypesList).setSpecification(generator.getCSIPVersion()));

    /* CSIP5 */
    notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP5_ID);
    ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP5_ID,
      generator.validateCSIP5(metsValidatorState).setSpecification(generator.getCSIPVersion()));

    /* CSIP6 */
    notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP6_ID);
    ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP6_ID,
      generator.validateCSIP6(metsValidatorState).setSpecification(generator.getCSIPVersion()));

    notifyObserversFinishModule(moduleName);
    return results;
  }
}

