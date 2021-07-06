package org.roda_project.commons_ip2.validator.component.fileSectionComponent;

import org.roda_project.commons_ip2.validator.component.ValidatorComponentImpl;
import org.roda_project.commons_ip2.validator.constants.Constants;
import org.roda_project.commons_ip2.validator.constants.ConstantsCSIPspec;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * @author João Gomes <jgomes@keep.pt>
 */
public class FileSectionComponentValidator extends ValidatorComponentImpl {
    private static final Logger LOGGER = LoggerFactory.getLogger(FileSectionComponentValidator.class);

    private final String MODULE_NAME;

    public FileSectionComponentValidator(String module_name) {
        MODULE_NAME = module_name;
    }

    @Override
    public boolean validate() throws IOException {
        boolean valid = true;

        /* CSIP58 */
        validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP58_ID);
        if(validateCSIP58()){
            validationOutcomeSkipped(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP58_ID,"");
        }

        /* CSIP59 */
        validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP59_ID);
        if(validateCSIP59()){
            validationOutcomeSkipped(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP59_ID,"");
        }

        /* CSIP60 */
        validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP60_ID);
        if(validateCSIP60()){
            validationOutcomeSkipped(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP60_ID,"");
        }

        /* CSIP113 */
        validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP113_ID);
        if(validateCSIP113()){
            validationOutcomeSkipped(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP113_ID,"");
        }

        /* CSIP114 */
        validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP114_ID);
        if(validateCSIP114()){
            validationOutcomeSkipped(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP114_ID,"");
        }

        /* CSIP61 */
        validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP61_ID);
        if(validateCSIP61()){
            validationOutcomeSkipped(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP61_ID,"");
        }

        /* CSIP62 */
        validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP62_ID);
        if(validateCSIP62()){
            validationOutcomeSkipped(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP62_ID,"");
        }

        /* CSIP63 */
        validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP63_ID);
        if(validateCSIP63()){
            validationOutcomeSkipped(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP63_ID,"");
        }

        /* CSIP64 */
        validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP64_ID);
        if(validateCSIP64()){
            validationOutcomeSkipped(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP64_ID,"");
        }

        /* CSIP65 */
        validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP65_ID);
        if(validateCSIP65()){
            validationOutcomeSkipped(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP65_ID,"");
        }

        /* CSIP66 */
        validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP66_ID);
        if(validateCSIP66()){
            validationOutcomeSkipped(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP66_ID,"");
        }

        /* CSIP67 */
        validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP67_ID);
        if(validateCSIP67()){
            validationOutcomeSkipped(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP67_ID,"");
        }

        /* CSIP68 */
        validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP68_ID);
        if(validateCSIP68()){
            validationOutcomeSkipped(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP68_ID,"");
        }

        /* CSIP69 */
        validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP69_ID);
        if(validateCSIP69()){
            validationOutcomeSkipped(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP69_ID,"");
        }

        /* CSIP70 */
        validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP70_ID);
        if(validateCSIP70()){
            validationOutcomeSkipped(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP70_ID,"");
        }

        /* CSIP71 */
        validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP71_ID);
        if(validateCSIP71()){
            validationOutcomeSkipped(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP71_ID,"");
        }

        /* CSIP72 */
        validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP72_ID);
        if(validateCSIP72()){
            validationOutcomeSkipped(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP72_ID,"");
        }

        /* CSIP73 */
        validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP73_ID);
        if(validateCSIP73()){
            validationOutcomeSkipped(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP73_ID,"");
        }

        /* CSIP74 */
        validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP74_ID);
        if(validateCSIP74()){
            validationOutcomeSkipped(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP74_ID,"");
        }

        /* CSIP75 */
        validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP75_ID);
        if(validateCSIP75()){
            validationOutcomeSkipped(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP75_ID,"");
        }

        /* CSIP76 */
        validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP76_ID);
        if(validateCSIP76()){
            validationOutcomeSkipped(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP76_ID,"");
        }

        /* CSIP77 */
        validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP77_ID);
        if(validateCSIP77()){
            validationOutcomeSkipped(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP77_ID,"");
        }

        /* CSIP78 */
        validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP78_ID);
        if(validateCSIP78()){
            validationOutcomeSkipped(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP78_ID,"");
        }

        /* CSIP79 */
        validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP79_ID);
        if(validateCSIP79()){
            validationOutcomeSkipped(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP79_ID,"");
        }

        return false;
    }

    private boolean validateCSIP58() {
        return false;
    }

    private boolean validateCSIP59() {
        return false;
    }

    private boolean validateCSIP60() {
        return false;
    }

    private boolean validateCSIP113() {
        return false;
    }

    private boolean validateCSIP114() {
        return false;
    }

    private boolean validateCSIP61() {
        return false;
    }

    private boolean validateCSIP62() {
        return false;
    }

    private boolean validateCSIP63() {
        return false;
    }

    private boolean validateCSIP64() {
        return false;
    }

    private boolean validateCSIP65() {
        return false;
    }

    private boolean validateCSIP66() {
        return false;
    }

    private boolean validateCSIP67() {
        return false;
    }

    private boolean validateCSIP68() {
        return false;
    }

    private boolean validateCSIP69() {
        return false;
    }

    private boolean validateCSIP70() {
        return false;
    }

    private boolean validateCSIP71() {
        return false;
    }

    private boolean validateCSIP72() {
        return false;
    }

    private boolean validateCSIP73() {
        return false;
    }

    private boolean validateCSIP74() {
        return false;
    }

    private boolean validateCSIP75() {
        return false;
    }

    private boolean validateCSIP76() {
        return false;
    }

    private boolean validateCSIP77() {
        return false;
    }

    private boolean validateCSIP78() {
        return false;
    }

    private boolean validateCSIP79() {
        return false;
    }


}
