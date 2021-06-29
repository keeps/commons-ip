package org.roda_project.commons_ip2.validator.component.fileComponent;

import org.roda_project.commons_ip2.validator.common.ZipManager;
import org.roda_project.commons_ip2.validator.component.ValidatorComponentImpl;
import org.roda_project.commons_ip2.validator.component.metsrootComponent.MetsComponentValidator;
import org.roda_project.commons_ip2.validator.constants.Constants;
import org.roda_project.commons_ip2.validator.constants.ConstantsCSIPspec;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * @author João Gomes <jgomes@keep.pt>
 */
public class FileComponentValidator extends ValidatorComponentImpl {
    private static final Logger LOGGER = LoggerFactory.getLogger(FileComponentValidator.class);

    private final String MODULE_NAME;

    public FileComponentValidator(String moduleName){
        this.MODULE_NAME = moduleName;
    }

    @Override
    public boolean validate() throws IOException {
        Path path = getEARKSIPpath();
        boolean valid = true;
        validationInit(MODULE_NAME,"");
        if(validatePathExists(path)){
            if(!validateMetsRootFileLocation(path)){
                validationPathOutcomeFailed(ConstantsCSIPspec.VALIDATION_REPORT_METS_NOT_FOUND_ID,ConstantsCSIPspec.VALIDATION_REPORT_METS_NOT_FOUND_DETAIL);
                valid = false;
            }
        }
        else{
            validationPathOutcomeFailed(ConstantsCSIPspec.VALIDATION_REPORT_PATH_DOES_NOT_EXIST_ID,ConstantsCSIPspec.VALIDATION_REPORT_PATH_DOES_NOT_EXIST_DETAIL);
            valid = false;
        }
        return valid;
    }

    private boolean validatePathExists(Path path) throws IOException {
        boolean valid = true;
        if(Files.exists(path)){
            String contentType = Files.probeContentType(path);
            if(contentType.equals("application/zip")){
                setZipFileFlag(true);
            }
            else{
                if(!Files.isDirectory(path)){
                    valid = false;
                }
            }
        }
        else{
            valid = false;
        }
        return valid;
    }

    private boolean validateMetsRootFileLocation(Path path) throws IOException {
        boolean valid = true;
        boolean found = false;
        if(isZipFileFlag()){
            if(!zipManager.checkIfExistsRootMetsFile(path)){
                valid = false;
            }
        }
        else{
            File dir = path.toFile();
            for(File f: dir.listFiles()){
                if(f.getName().equals("METS.xml")){
                    found = true;
                }
            }
        }
        if(!found){
            valid = false;
        }
        return valid;
    }
}
