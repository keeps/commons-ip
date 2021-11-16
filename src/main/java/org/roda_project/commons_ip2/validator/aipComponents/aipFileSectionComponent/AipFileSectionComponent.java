package org.roda_project.commons_ip2.validator.aipComponents.aipFileSectionComponent;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.roda_project.commons_ip2.mets_v1_12.beans.FileType;
import org.roda_project.commons_ip2.mets_v1_12.beans.MetsType;
import org.roda_project.commons_ip2.validator.component.MetsValidatorImpl;
import org.roda_project.commons_ip2.validator.constants.Constants;
import org.roda_project.commons_ip2.validator.constants.ConstantsAIPspec;
import org.roda_project.commons_ip2.validator.reporter.ReporterDetails;
import org.roda_project.commons_ip2.validator.state.MetsValidatorState;
import org.roda_project.commons_ip2.validator.state.StructureValidatorState;
import org.roda_project.commons_ip2.validator.utils.Message;
import org.roda_project.commons_ip2.validator.utils.ResultsUtils;

/**
 * @author João Gomes <jgomes@keep.pt>
 */
public class AipFileSectionComponent extends MetsValidatorImpl {
  private final String moduleName;
  private boolean isToValidate = true;

  public AipFileSectionComponent() {
    moduleName = Constants.AIP_MODULE_NAME_2;
  }

  public void setIsToValidate(boolean isToValidate) {
    this.isToValidate = isToValidate;
  }

  @Override
  public Map<String, ReporterDetails> validate(StructureValidatorState structureValidatorState,
    MetsValidatorState metsValidatorState) {
    Map<String, ReporterDetails> results = new HashMap<>();

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
      String message = Message.createErrorMessage("SKIPPED in %1$s because mets/fileSec doesn't exist",
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

  private ReporterDetails validateAIP9(MetsValidatorState metsValidatorState) {
    if (metsValidatorState.getMets().getFileSec() != null) {
      List<MetsType.FileSec.FileGrp> fileGrps = metsValidatorState.getMets().getFileSec().getFileGrp();
      for (MetsType.FileSec.FileGrp fileGrp : fileGrps) {
        List<FileType> files = fileGrp.getFile();
        for (FileType file : files) {
          String id = file.getID();
          if (!id.startsWith("ID")) {
            StringBuilder message = new StringBuilder();
            message.append("The mets/fileSec/fileGrp/file/@ID must start with ID in ").append(id)
              .append("does not start with ID");
            return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_AIP_VERSION, Message.createErrorMessage(
              message.toString(), metsValidatorState.getMetsName(), metsValidatorState.isRootMets()), false, false);
          }
        }
      }
    }
    return new ReporterDetails();
  }
}
