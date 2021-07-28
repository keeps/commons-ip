package org.roda_project.commons_ip2.validator.component.fileComponent;

import org.roda_project.commons_ip2.validator.component.ValidatorComponentImpl;
import org.roda_project.commons_ip2.validator.constants.ConstantsCSIPspec;
import org.roda_project.commons_ip2.validator.reporter.ReporterDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * @author João Gomes <jgomes@keep.pt>
 */
public class StructureComponentValidator extends ValidatorComponentImpl {
    private static final Logger LOGGER = LoggerFactory.getLogger(StructureComponentValidator.class);

    private final String MODULE_NAME;

    public StructureComponentValidator(String moduleName){
        this.MODULE_NAME = moduleName;
    }

    @Override
    public void validate() throws IOException {
        Path path = getEARKSIPpath();
        ReporterDetails strCsip = validatePathExists(path);
        validationInit(MODULE_NAME,"");
        if(strCsip.isValid()){
            if(!validateMetsRootFileLocation(path)){
                validationPathOutcomeFailed(ConstantsCSIPspec.VALIDATION_REPORT_METS_NOT_FOUND_ID,ConstantsCSIPspec.VALIDATION_REPORT_METS_NOT_FOUND_DETAIL);
            }
        }
        else{
            validationPathOutcomeFailed(ConstantsCSIPspec.VALIDATION_REPORT_PATH_DOES_NOT_EXIST_ID,ConstantsCSIPspec.VALIDATION_REPORT_PATH_DOES_NOT_EXIST_DETAIL);
        }
    }

    private ReporterDetails validatePathExists(Path path) throws IOException {
        ReporterDetails details = new ReporterDetails();
        if(Files.exists(path)){
            String contentType = Files.probeContentType(path);
            if(contentType.equals("application/zip")){
                setZipFileFlag(true);
            }
            else{
                if(!Files.isDirectory(path)){
                    details.setValid(false);
                    details.addIssue(ConstantsCSIPspec.VALIDATION_REPORT_PATH_DOES_NOT_EXIST_DETAIL);
                }
            }
        }
        else{
            details.setValid(false);
            details.addIssue(ConstantsCSIPspec.VALIDATION_REPORT_PATH_DOES_NOT_EXIST_DETAIL);
        }
        return details;
    }

    private boolean validateMetsRootFileLocation(Path path) throws IOException {
        boolean valid = true;
        if(isZipFileFlag()){
            if(!zipManager.checkIfExistsRootMetsFile(path)){
                valid = false;
            }
        }
        else{
            if(!folderManager.checkIfExistsRootMetsFile(path)){
                valid = false;
            }
        }
        return valid;
    }
}
