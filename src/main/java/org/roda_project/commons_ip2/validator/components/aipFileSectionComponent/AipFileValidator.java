package org.roda_project.commons_ip2.validator.components.aipFileSectionComponent;

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
public abstract class AipFileValidator {

  protected abstract String getAIPVersion();

  protected ReporterDetails validateAIP9(final MetsValidatorState metsValidatorState) {
    if (metsValidatorState.getMets().getFileSec() != null) {
      final List<MetsType.FileSec.FileGrp> fileGrps = metsValidatorState.getMets().getFileSec().getFileGrp();
      for (MetsType.FileSec.FileGrp fileGrp : fileGrps) {
        final List<FileType> files = fileGrp.getFile();
        for (FileType file : files) {
          final String id = file.getID();
          if (!id.startsWith("ID")) {
              String message = "The mets/fileSec/fileGrp/file/@ID must start with ID in " + id +
                      "does not start with ID";
            return new ReporterDetails(getAIPVersion(), Message.createErrorMessage(
                    message, metsValidatorState.getMetsName(), metsValidatorState.isRootMets()), false, false);
          }
        }
      }
    }
    return new ReporterDetails();
  }
}
