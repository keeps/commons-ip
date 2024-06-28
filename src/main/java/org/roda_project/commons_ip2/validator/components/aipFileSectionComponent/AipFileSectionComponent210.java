package org.roda_project.commons_ip2.validator.components.aipFileSectionComponent;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.roda_project.commons_ip2.mets_v1_12.beans.FileType;
import org.roda_project.commons_ip2.mets_v1_12.beans.MetsType;
import org.roda_project.commons_ip2.validator.components.MetsValidatorImpl;
import org.roda_project.commons_ip2.validator.constants.Constants;
import org.roda_project.commons_ip2.validator.constants.ConstantsAIPspec;
import org.roda_project.commons_ip2.validator.reporter.ReporterDetails;
import org.roda_project.commons_ip2.validator.state.MetsValidatorState;
import org.roda_project.commons_ip2.validator.state.StructureValidatorState;
import org.roda_project.commons_ip2.validator.utils.Message;
import org.roda_project.commons_ip2.validator.utils.ResultsUtils;

/** {@author João Gomes <jgomes@keep.pt>}. */
public class AipFileSectionComponent210 extends MetsValidatorImpl {
  /**
   * The module of specification.
   */
  private final String moduleName;
  /**
   * The flag if is to validate this component.
   */
  private boolean isToValidate = true;

  /**
   * the empty constructor.
   */
  public AipFileSectionComponent210() {
    moduleName = Constants.AIP_MODULE_NAME_2;
  }

  /**
   * Set the flag isToValidate.
   *
   * @param isToValidate
   *          the flag.
   */
  public void setIsToValidate(final boolean isToValidate) {
    this.isToValidate = isToValidate;
  }

  @Override
  public Map<String, ReporterDetails> validate(final StructureValidatorState structureValidatorState,
    final MetsValidatorState metsValidatorState) {
    final Map<String, ReporterDetails> results = new HashMap<>();

    if (isToValidate) {
      /* AIP9 */
      notifyObserversValidationStarted(moduleName, ConstantsAIPspec.VALIDATION_REPORT_SPECIFICATION_AIP9_ID);
      ResultsUtils.addResult(results, ConstantsAIPspec.VALIDATION_REPORT_SPECIFICATION_AIP9_ID,
        validateAIP9(metsValidatorState).setSpecification(Constants.VALIDATION_REPORT_HEADER_AIP_VERSION));

      /* AIP10 */
      ResultsUtils.addResult(results, ConstantsAIPspec.VALIDATION_REPORT_SPECIFICATION_AIP10_ID,
        new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_AIP_VERSION,
          Message.createErrorMessage("SKIPPED because it has been validated in CSIP74",
            metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
          true, true));

      ResultsUtils.addResult(results, ConstantsAIPspec.VALIDATION_REPORT_SPECIFICATION_AIP11_ID,
        new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_AIP_VERSION,
          Message.createErrorMessage("SKIPPED because it has been validated in CSIP72 ",
            metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
          true, true));

      ResultsUtils.addResult(results, ConstantsAIPspec.VALIDATION_REPORT_SPECIFICATION_AIP12_ID,
        new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_AIP_VERSION,
          Message.createErrorMessage("SKIPPED because it has been validated in CSIP71 ",
            metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
          true, true));

      ResultsUtils.addResult(results, ConstantsAIPspec.VALIDATION_REPORT_SPECIFICATION_AIP13_ID,
        new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_AIP_VERSION,
          Message.createErrorMessage("SKIPPED because it has been validated in CSIP70 ",
            metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
          true, true));

      ResultsUtils.addResult(results, ConstantsAIPspec.VALIDATION_REPORT_SPECIFICATION_AIP14_ID,
        new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
          Message.createErrorMessage("SKIPPED because it has been validated in CSIP69 ",
            metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
          true, true));

      ResultsUtils.addResult(results, ConstantsAIPspec.VALIDATION_REPORT_SPECIFICATION_AIP15_ID,
        new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_AIP_VERSION,
          Message.createErrorMessage("SKIPPED because it has been validated in CSIP68 ",
            metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
          true, true));

    } else {
      final String message = Message.createErrorMessage("SKIPPED in %1$s because mets/fileSec doesn't exist",
        metsValidatorState.getMetsName(), metsValidatorState.isRootMets());

      ResultsUtils.addResults(results,
        new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_AIP_VERSION, message, true, true),
        ConstantsAIPspec.VALIDATION_REPORT_SPECIFICATION_AIP9_ID,
        ConstantsAIPspec.VALIDATION_REPORT_SPECIFICATION_AIP10_ID,
        ConstantsAIPspec.VALIDATION_REPORT_SPECIFICATION_AIP11_ID,
        ConstantsAIPspec.VALIDATION_REPORT_SPECIFICATION_AIP12_ID,
        ConstantsAIPspec.VALIDATION_REPORT_SPECIFICATION_AIP13_ID,
        ConstantsAIPspec.VALIDATION_REPORT_SPECIFICATION_AIP14_ID,
        ConstantsAIPspec.VALIDATION_REPORT_SPECIFICATION_AIP15_ID);
    }

    return results;
  }

  private ReporterDetails validateAIP9(final MetsValidatorState metsValidatorState) {
    if (metsValidatorState.getMets().getFileSec() != null) {
      final List<MetsType.FileSec.FileGrp> fileGrps = metsValidatorState.getMets().getFileSec().getFileGrp();
      for (MetsType.FileSec.FileGrp fileGrp : fileGrps) {
        final List<FileType> files = fileGrp.getFile();
        for (FileType file : files) {
          final String id = file.getID();
          if (!id.startsWith("ID")) {
              String message = "The mets/fileSec/fileGrp/file/@ID must start with ID in " + id +
                      "does not start with ID";
            return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_AIP_VERSION, Message.createErrorMessage(
                    message, metsValidatorState.getMetsName(), metsValidatorState.isRootMets()), false, false);
          }
        }
      }
    }
    return new ReporterDetails();
  }
}
