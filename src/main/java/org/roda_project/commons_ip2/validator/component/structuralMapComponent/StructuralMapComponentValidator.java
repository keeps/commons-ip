package org.roda_project.commons_ip2.validator.component.structuralMapComponent;

import org.roda_project.commons_ip2.validator.component.ValidatorComponentImpl;
import org.roda_project.commons_ip2.validator.constants.Constants;
import org.roda_project.commons_ip2.validator.constants.ConstantsCSIPspec;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * @author João Gomes <jgomes@keep.pt>
 */
public class StructuralMapComponentValidator extends ValidatorComponentImpl {
    private static final Logger LOGGER = LoggerFactory.getLogger(StructuralMapComponentValidator.class);

    private final String MODULE_NAME;

    public StructuralMapComponentValidator(String module_name) {
        MODULE_NAME = module_name;
    }

    @Override
    public boolean validate() throws IOException {

        /* CSIP80 */
        validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP80_ID);
        if(validateCSIP80()){
            validationOutcomeSkipped(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP80_ID,"");
        }

        /* CSIP81 */
        validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP81_ID);
        if(validateCSIP81()){
            validationOutcomeSkipped(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP81_ID,"");
        }

        /* CSIP82 */
        validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP82_ID);
        if(validateCSIP82()){
            validationOutcomeSkipped(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP82_ID,"");
        }

        /* CSIP83 */
        validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP83_ID);
        if(validateCSIP83()){
            validationOutcomeSkipped(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP83_ID,"");
        }

        /* CSIP84 */
        validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP84_ID);
        if(validateCSIP84()){
            validationOutcomeSkipped(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP84_ID,"");
        }

        /* CSIP85 */
        validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP85_ID);
        if(validateCSIP85()){
            validationOutcomeSkipped(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP85_ID,"");
        }

        /* CSIP86 */
        validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP86_ID);
        if(validateCSIP86()){
            validationOutcomeSkipped(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP86_ID,"");
        }

        /* CSIP88 */
        validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP88_ID);
        if(validateCSIP88()){
            validationOutcomeSkipped(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP88_ID,"");
        }

        /* CSIP89 */
        validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP89_ID);
        if(validateCSIP89()){
            validationOutcomeSkipped(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP89_ID,"");
        }

        /* CSIP90 */
        validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP90_ID);
        if(validateCSIP90()){
            validationOutcomeSkipped(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP90_ID,"");
        }

        /* CSIP91 */
        validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP91_ID);
        if(validateCSIP91()){
            validationOutcomeSkipped(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP91_ID,"");
        }

        /* CSIP92 */
        validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP92_ID);
        if(validateCSIP92()){
            validationOutcomeSkipped(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP92_ID,"");
        }

        /* CSIP93 */
        validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP93_ID);
        if(validateCSIP93()){
            validationOutcomeSkipped(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP93_ID,"");
        }

        /* CSIP94 */
        validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP94_ID);
        if(validateCSIP94()){
            validationOutcomeSkipped(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP94_ID,"");
        }

        /* CSIP95 */
        validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP95_ID);
        if(validateCSIP95()){
            validationOutcomeSkipped(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP95_ID,"");
        }

        /* CSIP96 */
        validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP96_ID);
        if(validateCSIP96()){
            validationOutcomeSkipped(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP96_ID,"");
        }

        /* CSIP116 */
        validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP116_ID);
        if(validateCSIP116()){
            validationOutcomeSkipped(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP116_ID,"");
        }

        /* CSIP97 */
        validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP97_ID);
        if(validateCSIP97()){
            validationOutcomeSkipped(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP97_ID,"");
        }

        /* CSIP98 */
        validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP98_ID);
        if(validateCSIP98()){
            validationOutcomeSkipped(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP98_ID,"");
        }

        /* CSIP99 */
        validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP99_ID);
        if(validateCSIP99()){
            validationOutcomeSkipped(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP99_ID,"");
        }

        /* CSIP100 */
        validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP100_ID);
        if(validateCSIP100()){
            validationOutcomeSkipped(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP100_ID,"");
        }

        /* CSIP101 */
        validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP101_ID);
        if(validateCSIP101()){
            validationOutcomeSkipped(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP101_ID,"");
        }

        /* CSIP102 */
        validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP102_ID);
        if(validateCSIP102()){
            validationOutcomeSkipped(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP102_ID,"");
        }

        /* CSIP100 */
        validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP103_ID);
        if(validateCSIP103()){
            validationOutcomeSkipped(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP103_ID,"");
        }

        /* CSIP104 */
        validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP104_ID);
        if(validateCSIP104()){
            validationOutcomeSkipped(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP104_ID,"");
        }

        /* CSIP119 */
        validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP119_ID);
        if(validateCSIP119()){
            validationOutcomeSkipped(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP119_ID,"");
        }

        /* CSIP105 */
        validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP105_ID);
        if(validateCSIP105()){
            validationOutcomeSkipped(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP105_ID,"");
        }

        /* CSIP106 */
        validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP106_ID);
        if(validateCSIP106()){
            validationOutcomeSkipped(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP106_ID,"");
        }

        /* CSIP107 */
        validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP107_ID);
        if(validateCSIP107()){
            validationOutcomeSkipped(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP107_ID,"");
        }

        /* CSIP108 */
        validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP108_ID);
        if(validateCSIP108()){
            validationOutcomeSkipped(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP108_ID,"");
        }

        /* CSIP109 */
        validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP109_ID);
        if(validateCSIP109()){
            validationOutcomeSkipped(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP109_ID,"");
        }

        /* CSIP110 */
        validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP110_ID);
        if(validateCSIP110()){
            validationOutcomeSkipped(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP110_ID,"");
        }

        /* CSIP111 */
        validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP111_ID);
        if(validateCSIP111()){
            validationOutcomeSkipped(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP111_ID,"");
        }

        /* CSIP112 */
        validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP112_ID);
        if(validateCSIP112()){
            validationOutcomeSkipped(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP112_ID,"");
        }

        return false;
    }

    private boolean validateCSIP80() {
        return false;
    }

    private boolean validateCSIP81() {
        return false;
    }

    private boolean validateCSIP82() {
        return false;
    }

    private boolean validateCSIP83() {
        return false;
    }

    private boolean validateCSIP84() {
        return false;
    }

    private boolean validateCSIP85() {
        return false;
    }

    private boolean validateCSIP86() {
        return false;
    }

    private boolean validateCSIP88() {
        return false;
    }

    private boolean validateCSIP89() {
        return false;
    }

    private boolean validateCSIP90() {
        return false;
    }

    private boolean validateCSIP91() {
        return false;
    }

    private boolean validateCSIP92() {
        return false;
    }

    private boolean validateCSIP93() {
        return false;
    }

    private boolean validateCSIP94() {
        return false;
    }

    private boolean validateCSIP95() {
        return false;
    }

    private boolean validateCSIP96() {
        return false;
    }

    private boolean validateCSIP116() {
        return false;
    }

    private boolean validateCSIP97() {
        return false;
    }

    private boolean validateCSIP98() {
        return false;
    }

    private boolean validateCSIP99() {
        return false;
    }

    private boolean validateCSIP100() {
        return false;
    }

    private boolean validateCSIP101() {
        return false;
    }

    private boolean validateCSIP102() {
        return false;
    }

    private boolean validateCSIP103() {
        return false;
    }

    private boolean validateCSIP104() {
        return false;
    }

    private boolean validateCSIP119() {
        return false;
    }

    private boolean validateCSIP105() {
        return false;
    }

    private boolean validateCSIP106() {
        return false;
    }

    private boolean validateCSIP107() {
        return false;
    }

    private boolean validateCSIP108() {
        return false;
    }

    private boolean validateCSIP109() {
        return false;
    }

    private boolean validateCSIP110() {
        return false;
    }

    private boolean validateCSIP111() {
        return false;
    }

    private boolean validateCSIP112() {
        return false;
    }
}
