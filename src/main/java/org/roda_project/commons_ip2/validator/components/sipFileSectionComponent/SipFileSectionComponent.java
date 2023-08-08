package org.roda_project.commons_ip2.validator.components.sipFileSectionComponent;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.roda_project.commons_ip2.mets_v1_12.beans.FileType;
import org.roda_project.commons_ip2.mets_v1_12.beans.MetsType;
import org.roda_project.commons_ip2.validator.components.MetsValidatorImpl;
import org.roda_project.commons_ip2.validator.constants.Constants;
import org.roda_project.commons_ip2.validator.constants.ConstantsSIPspec;
import org.roda_project.commons_ip2.validator.reporter.ReporterDetails;
import org.roda_project.commons_ip2.validator.state.MetsValidatorState;
import org.roda_project.commons_ip2.validator.state.StructureValidatorState;
import org.roda_project.commons_ip2.validator.utils.Message;
import org.roda_project.commons_ip2.validator.utils.ResultsUtils;

/** {@author João Gomes <jgomes@keep.pt>}. */
public class SipFileSectionComponent extends MetsValidatorImpl {
  /**
   * Module name of the specification.
   */
  private final String moduleName;
  /**
   * Flag if is to validate or not.
   */
  private boolean isToValidate = true;

  /**
   * Empty constructor.
   */
  public SipFileSectionComponent() {
    this.moduleName = Constants.SIP_MODULE_NAME_3;
  }

  /**
   * Set the flag isToValidate.
   * 
   * @param isToValidate
   *          flag if is to validate or not
   */
  public void setIsToValidate(final boolean isToValidate) {
    this.isToValidate = isToValidate;
  }

  @Override
  public Map<String, ReporterDetails> validate(final StructureValidatorState structureValidatorState,
    final MetsValidatorState metsValidatorState) {
    final Map<String, ReporterDetails> results = new HashMap<>();

    if (isToValidate) {
      /* SIP32 */
      notifyObserversValidationStarted(moduleName, ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP32_ID);
      ResultsUtils.addResult(results, ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP32_ID,
        validateSIP32(metsValidatorState).setSpecification(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION));

      /* SIP33 */
      notifyObserversValidationStarted(moduleName, ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP33_ID);
      ResultsUtils.addResult(results, ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP33_ID,
        validateSIP33(metsValidatorState).setSpecification(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION));

      /* SIP34 */
      notifyObserversValidationStarted(moduleName, ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP34_ID);
      ResultsUtils.addResult(results, ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP34_ID,
        validateSIP34(metsValidatorState).setSpecification(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION));

      /* SIP35 */
      notifyObserversValidationStarted(moduleName, ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP35_ID);
      ResultsUtils.addResult(results, ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP35_ID,
        validateSIP35(metsValidatorState).setSpecification(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION));
    } else {
      final String message = Message.createErrorMessage("SKIPPED in %1$s because mets/fileSec doesn't exist",
        metsValidatorState.getMetsName(), metsValidatorState.isRootMets());

      ResultsUtils.addResults(results,
        new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_AIP_VERSION, message, true, true),
        ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP32_ID,
        ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP33_ID,
        ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP34_ID,
        ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP35_ID);
    }
    notifyObserversFinishModule(moduleName);
    return results;
  }

  /*
   * fileSec/fileGrp/file/@sip:FILEFORMATNAME An optional attribute may be used if
   * the MIMETYPE is not suicient for the purposes of processing the information
   * package. Example: “Extensible Markup Language” Example: “PDF/A” Example:
   * “ISO/IEC 26300:2006”
   */

  private ReporterDetails validateSIP32(final MetsValidatorState metsValidatorState) {
    final List<MetsType.FileSec.FileGrp> fileGrps = metsValidatorState.getMets().getFileSec().getFileGrp();
    for (MetsType.FileSec.FileGrp fileGrp : fileGrps) {
      int count = 0;
      final List<FileType> files = fileGrp.getFile();
      for (FileType file : files) {
        final String fileFormatName = file.getFILEFORMATNAME();
        if (fileFormatName != null && !fileFormatName.equals("")) {
          count++;
        }
      }
      if (count != files.size()) {
        return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION,
          Message.createErrorMessage(
            "If the MIMETYPE is not sufficient for the purposes of processing the "
              + "information package, you can add @sip:FILEFORMATNAME " + "attribute in the file element for the %1$s.",
            metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
          false, false);
      }
    }
    return new ReporterDetails();
  }

  /*
   * fileSec/fileGrp/file/@sip:FILEFORMATVERSION The version of the file format
   * when the use of PREMIS has not been agreed upon in the submission agreement.
   * Example: “1.0”
   */
  private ReporterDetails validateSIP33(final MetsValidatorState metsValidatorState) {
    final List<MetsType.FileSec.FileGrp> fileGrps = metsValidatorState.getMets().getFileSec().getFileGrp();
    for (MetsType.FileSec.FileGrp fileGrp : fileGrps) {
      int count = 0;
      final List<FileType> files = fileGrp.getFile();
      for (FileType file : files) {
        final String fileFormatVersion = file.getFILEFORMATVERSION();
        if (fileFormatVersion != null && !fileFormatVersion.equals("")) {
          count++;
        }
      }
      if (count != files.size()) {
        return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION,
          Message.createErrorMessage("You can add @sip:FILEFORMATVERSION attribute in the file element for the %1$s",
            metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
          false, false);
      }
    }
    return new ReporterDetails();
  }

  /*
   * fileSec/fileGrp/file/@sip:FILEFORMATREGISTRY The name of the format registry
   * used to identify the file format when the use of PREMIS has not been agreed
   * upon in the submission agreement. Example: “PRONOM”
   */
  private ReporterDetails validateSIP34(final MetsValidatorState metsValidatorState) {
    final List<MetsType.FileSec.FileGrp> fileGrps = metsValidatorState.getMets().getFileSec().getFileGrp();
    for (MetsType.FileSec.FileGrp fileGrp : fileGrps) {
      int count = 0;
      final List<FileType> files = fileGrp.getFile();
      for (FileType file : files) {
        final String fileFormatRegistry = file.getFORMATREGISTRY();
        if (fileFormatRegistry != null && !fileFormatRegistry.equals("")) {
          count++;
        }
      }
      if (count != files.size()) {
        return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION,
          Message.createErrorMessage("You can add @sip:FILEFORMATREGISTRY attribute in the file element for the %1$s",
            metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
          false, false);
      }
    }
    return new ReporterDetails();
  }

  /*
   * fileSec/fileGrp/file/@sip:FILEFORMATKEY Key of the file format in the
   * registry when use of PREMIS has not been agreed upon in the submission
   * agreement. Example: “fmt/101”
   */
  private ReporterDetails validateSIP35(final MetsValidatorState metsValidatorState) {
    final List<MetsType.FileSec.FileGrp> fileGrps = metsValidatorState.getMets().getFileSec().getFileGrp();
    for (MetsType.FileSec.FileGrp fileGrp : fileGrps) {
      int count = 0;
      final List<FileType> files = fileGrp.getFile();
      for (FileType file : files) {
        final String fileFormatKey = file.getFORMATREGISTRYKEY();
        if (fileFormatKey != null && !fileFormatKey.equals("")) {
          count++;
        }
      }
      if (count != files.size()) {
        return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION,
          Message.createErrorMessage("You can add @sip:FILEFORMATKEY attribute in the file element for the %1$s",
            metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
          false, false);
      }
    }
    return new ReporterDetails();
  }
}
