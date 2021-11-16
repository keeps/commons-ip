package org.roda_project.commons_ip2.validator.sipComponents.sipFileSectionComponent;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.roda_project.commons_ip2.mets_v1_12.beans.FileType;
import org.roda_project.commons_ip2.mets_v1_12.beans.MetsType;
import org.roda_project.commons_ip2.validator.component.MetsValidatorImpl;
import org.roda_project.commons_ip2.validator.constants.Constants;
import org.roda_project.commons_ip2.validator.constants.ConstantsSIPspec;
import org.roda_project.commons_ip2.validator.reporter.ReporterDetails;
import org.roda_project.commons_ip2.validator.state.MetsValidatorState;
import org.roda_project.commons_ip2.validator.state.StructureValidatorState;
import org.roda_project.commons_ip2.validator.utils.Message;
import org.roda_project.commons_ip2.validator.utils.ResultsUtils;

/**
 * @author João Gomes <jgomes@keep.pt>
 */
public class SipFileSectionComponent extends MetsValidatorImpl {
  private final String moduleName;
  private boolean isToValidate = true;

  public SipFileSectionComponent() {
    this.moduleName = Constants.SIP_MODULE_NAME_3;
  }

  public void setIsToValidate(boolean isToValidate) {
    this.isToValidate = isToValidate;
  }

  @Override
  public Map<String, ReporterDetails> validate(StructureValidatorState structureValidatorState,
    MetsValidatorState metsValidatorState) {
    Map<String, ReporterDetails> results = new HashMap<>();

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
      String message = Message.createErrorMessage("SKIPPED in %1$s because mets/fileSec doesn't exist",
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

  private ReporterDetails validateSIP32(MetsValidatorState metsValidatorState) {
    List<MetsType.FileSec.FileGrp> fileGrps = metsValidatorState.getMets().getFileSec().getFileGrp();
    for (MetsType.FileSec.FileGrp fileGrp : fileGrps) {
      int count = 0;
      List<FileType> files = fileGrp.getFile();
      for (FileType file : files) {
        String fileFormatName = file.getFILEFORMATNAME();
        if (fileFormatName != null && !fileFormatName.equals("")) {
          count++;
        }
      }
      if (count != files.size()) {
        return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION, Message.createErrorMessage(
          "If the MIMETYPE is not sufficient for the purposes of processing the information package, you can add @sip:FILEFORMATNAME attribute in the file element for the %1$s.",
          metsValidatorState.getMetsName(), metsValidatorState.isRootMets()), false, false);
      }
    }
    return new ReporterDetails();
  }

  /*
   * fileSec/fileGrp/file/@sip:FILEFORMATVERSION The version of the file format
   * when the use of PREMIS has not been agreed upon in the submission agreement.
   * Example: “1.0”
   */
  private ReporterDetails validateSIP33(MetsValidatorState metsValidatorState) {
    List<MetsType.FileSec.FileGrp> fileGrps = metsValidatorState.getMets().getFileSec().getFileGrp();
    for (MetsType.FileSec.FileGrp fileGrp : fileGrps) {
      int count = 0;
      List<FileType> files = fileGrp.getFile();
      for (FileType file : files) {
        String fileFormatVersion = file.getFILEFORMATVERSION();
        if (fileFormatVersion != null && !fileFormatVersion.equals("")) {
          count++;
        }
      }
      if (count != files.size()) {
        return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION, Message.createErrorMessage(
          "You can add @sip:FILEFORMATVERSION attribute in the file element for the %1$s",
          metsValidatorState.getMetsName(), metsValidatorState.isRootMets()), false, false);
      }
    }
    return new ReporterDetails();
  }

  /*
   * fileSec/fileGrp/file/@sip:FILEFORMATREGISTRY The name of the format registry
   * used to identify the file format when the use of PREMIS has not been agreed
   * upon in the submission agreement. Example: “PRONOM”
   */
  private ReporterDetails validateSIP34(MetsValidatorState metsValidatorState) {
    List<MetsType.FileSec.FileGrp> fileGrps = metsValidatorState.getMets().getFileSec().getFileGrp();
    for (MetsType.FileSec.FileGrp fileGrp : fileGrps) {
      int count = 0;
      List<FileType> files = fileGrp.getFile();
      for (FileType file : files) {
        String fileFormatRegistry = file.getFORMATREGISTRY();
        if (fileFormatRegistry != null && !fileFormatRegistry.equals("")) {
          count++;
        }
      }
      if (count != files.size()) {
        return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION, Message.createErrorMessage(
          "You can add @sip:FILEFORMATREGISTRY attribute in the file element for the %1$s",
          metsValidatorState.getMetsName(), metsValidatorState.isRootMets()), false, false);
      }
    }
    return new ReporterDetails();
  }

  /*
   * fileSec/fileGrp/file/@sip:FILEFORMATKEY Key of the file format in the
   * registry when use of PREMIS has not been agreed upon in the submission
   * agreement. Example: “fmt/101”
   */
  private ReporterDetails validateSIP35(MetsValidatorState metsValidatorState) {
    List<MetsType.FileSec.FileGrp> fileGrps = metsValidatorState.getMets().getFileSec().getFileGrp();
    for (MetsType.FileSec.FileGrp fileGrp : fileGrps) {
      int count = 0;
      List<FileType> files = fileGrp.getFile();
      for (FileType file : files) {
        String fileFormatKey = file.getFORMATREGISTRYKEY();
        if (fileFormatKey != null && !fileFormatKey.equals("")) {
          count++;
        }
      }
      if (count != files.size()) {
        return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION, Message.createErrorMessage(
          "You can add @sip:FILEFORMATKEY attribute in the file element for the %1$s",
          metsValidatorState.getMetsName(), metsValidatorState.isRootMets()), false, false);
      }
    }
    return new ReporterDetails();
  }
}
