package org.roda_project.commons_ip2.validator.components.sipFileSectionComponent;

import org.roda_project.commons_ip2.mets_v1_12.beans.FileType;
import org.roda_project.commons_ip2.mets_v1_12.beans.MetsType;
import org.roda_project.commons_ip2.validator.constants.Constants;
import org.roda_project.commons_ip2.validator.reporter.ReporterDetails;
import org.roda_project.commons_ip2.validator.state.MetsValidatorState;
import org.roda_project.commons_ip2.validator.utils.Message;

import java.util.List;

/**
 * @author Carlos Afonso <cafonso@keep.pt>
 */
public abstract class SipFileValidator {

  protected abstract String getSIPVersion();

  /*
   * fileSec/fileGrp/file/@sip:FILEFORMATNAME An optional attribute may be used if
   * the MIMETYPE is not suicient for the purposes of processing the information
   * package. Example: “Extensible Markup Language” Example: “PDF/A” Example:
   * “ISO/IEC 26300:2006”
   */

  protected ReporterDetails validateSIP32(final MetsValidatorState metsValidatorState) {
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
        return new ReporterDetails(getSIPVersion(),
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
  protected ReporterDetails validateSIP33(final MetsValidatorState metsValidatorState) {
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
        return new ReporterDetails(getSIPVersion(),
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
  protected ReporterDetails validateSIP34(final MetsValidatorState metsValidatorState) {
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
        return new ReporterDetails(getSIPVersion(),
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
  protected ReporterDetails validateSIP35(final MetsValidatorState metsValidatorState) {
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
        return new ReporterDetails(getSIPVersion(),
          Message.createErrorMessage("You can add @sip:FILEFORMATKEY attribute in the file element for the %1$s",
            metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
          false, false);
      }
    }
    return new ReporterDetails();
  }
  
}
