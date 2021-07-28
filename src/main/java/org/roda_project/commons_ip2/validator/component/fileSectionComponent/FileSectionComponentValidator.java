package org.roda_project.commons_ip2.validator.component.fileSectionComponent;

import org.roda_project.commons_ip2.mets_v1_12.beans.FileType;
import org.roda_project.commons_ip2.mets_v1_12.beans.MetsType;
import org.roda_project.commons_ip2.validator.component.ValidatorComponentImpl;
import org.roda_project.commons_ip2.validator.constants.Constants;
import org.roda_project.commons_ip2.validator.constants.ConstantsCSIPspec;
import org.roda_project.commons_ip2.validator.reporter.ReporterDetails;
import org.roda_project.commons_ip2.validator.utils.CHECKSUMTYPE;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URLDecoder;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

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
    public void validate() throws IOException {
        ReporterDetails csip;

        /* CSIP58 */
        validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP58_ID);
        csip = validateCSIP58();
        csip.setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION);
        addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP58_ID,csip);

        if(csip.isValid()){

            /* CSIP59 */
            validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP59_ID);
            csip = validateCSIP59();
            csip.setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION);
            addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP59_ID,csip);

            /* CSIP60 */
            validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP60_ID);
            // csip = validateCSIP60();
            csip.setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION);
            addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP60_ID,csip);

            /* CSIP60 */
            validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP60_ID);
            // csip = validateCSIP60();
            csip.setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION);
            addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP60_ID,csip);

            /* CSIP113 */
            validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP113_ID);
            // csip = validateCSIP113();
            csip.setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION);
            addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP113_ID,csip);

            /* CSIP114 */
            validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP114_ID);
            // csip = validateCSIP114();
            csip.setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION);
            addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP114_ID,csip);

            /* CSIP61 */
            validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP61_ID);
            // csip = validateCSIP61();
            csip.setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION);
            addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP61_ID,csip);

            /* CSIP62 */
            validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP62_ID);
            // csip = validateCSIP62();
            csip.setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION);
            addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP62_ID,csip);

            /* CSIP63 */
            validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP63_ID);
            // csip = validateCSIP63();
            csip.setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION);
            addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP63_ID,csip);

            /* CSIP64 */
            validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP64_ID);
            // csip = validateCSIP64();
            csip.setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION);
            addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP64_ID,csip);

            /* CSIP65 */
            validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP65_ID);
            csip = validateCSIP65();
            csip.setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION);
            addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP65_ID,csip);

            /* CSIP66 */
            validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP66_ID);
            csip = validateCSIP66();
            csip.setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION);
            addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP66_ID,csip);

            if(csip.isValid()){

                /* CSIP67 */
                validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP67_ID);
                csip = validateCSIP67();
                csip.setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION);
                addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP67_ID,csip);

                /* CSIP68 */
                validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP68_ID);
                // csip = validateCSIP68();
                csip.setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION);
                addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP68_ID,csip);

                /* CSIP69 */
                validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP69_ID);
                csip = validateCSIP69();
                csip.setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION);
                addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP69_ID,csip);

                /* CSIP70 */
                validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP70_ID);
                csip = validateCSIP70();
                csip.setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION);
                addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP70_ID,csip);

                /* CSIP71 */
                validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP71_ID);
                //csip = validateCSIP71();
                csip.setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION);
                addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP71_ID,csip);

                /* CSIP72 */
                validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP72_ID);
                csip = validateCSIP72();
                csip.setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION);
                addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP72_ID,csip);

                /* CSIP73 */
                validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP73_ID);
                // csip = validateCSIP73();
                csip.setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION);
                addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP73_ID,csip);

                /* CSIP74 */
                validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP74_ID);
                //csip = validateCSIP74();
                csip.setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION);
                addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP74_ID,csip);

                /* CSIP75 */
                validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP75_ID);
                //csip = validateCSIP75();
                csip.setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION);
                addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP75_ID,csip);

                /* CSIP76 */
                validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP76_ID);
                csip = validateCSIP76();
                csip.setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION);
                addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP76_ID,csip);

                if(csip.isValid()){

                    /* CSIP77 */
                    validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP77_ID);
                    csip = validateCSIP77();
                    csip.setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION);
                    addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP77_ID,csip);

                    /* CSIP78 */
                    validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP78_ID);
                    csip = validateCSIP78();
                    csip.setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION);
                    addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP78_ID,csip);

                    /* CSIP79 */
                    validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP79_ID);
                    csip = validateCSIP79();
                    csip.setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION);
                    addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP79_ID,csip);
                }
                else{
                    String message = "SKIPPED because mets/fileSec/fileGrp/file/FLocat doesn't exist! (" + metsName + ")";

                    /* CSIP77 */
                    csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,message,true, true);
                    addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP77_ID,csip);

                    /* CSIP78 */
                    csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,message,true, true);
                    addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP78_ID,csip);

                    /* CSIP79 */
                    csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,message,true, true);
                    addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP79_ID,csip);
                }
            }
            else{
                String message = "SKIPPED because mets/fileSec/fileGrp/file/ doesn't exist! (" + metsName + ")";
                /* CSIP67 */
                csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,message,true, true);
                addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP67_ID,csip);

                /* CSIP68 */
                csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,message,true, true);
                addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP68_ID,csip);

                /* CSIP69 */
                csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,message,true, true);
                addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP69_ID,csip);

                /* CSIP70 */
                csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,message,true, true);
                addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP70_ID,csip);

                /* CSIP71 */
                csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,message,true, true);
                addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP71_ID,csip);

                /* CSIP72 */
                csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,message,true, true);
                addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP72_ID,csip);

                /* CSIP73 */
                csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,message,true, true);
                addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP73_ID,csip);

                /* CSIP74 */
                csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,message,true, true);
                addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP74_ID,csip);

                /* CSIP75 */
                csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,message,true, true);
                addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP75_ID,csip);

                /* CSIP76 */
                csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,message,true, true);
                addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP76_ID,csip);

                /* CSIP77 */
                csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,message,true, true);
                addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP77_ID,csip);

                /* CSIP78 */
                csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,message,true, true);
                addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP78_ID,csip);

                /* CSIP79 */
                csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,message,true, true);
                addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP79_ID,csip);
            }
        }
        else{
            String message = "SKIPPED because mets/fileSec doesn't exist! (" + metsName + ")";

            /* CSIP59 */
            csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,message,true, true);
            addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP59_ID,csip);

            /* CSIP60 */
            csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,message,true, true);
            addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP60_ID,csip);

            /* CSIP113 */
            csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,message,true, true);
            addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP113_ID,csip);

            /* CSIP114 */
            csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,message,true, true);
            addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP114_ID,csip);

            /* CSIP61 */
            csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,message,true, true);
            addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP61_ID,csip);

            /* CSIP62 */
            csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,message,true, true);
            addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP62_ID,csip);

            /* CSIP63 */
            csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,message,true, true);
            addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP63_ID,csip);

            /* CSIP64 */
            csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,message,true, true);
            addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP64_ID,csip);

            /* CSIP65 */
            csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,message,true, true);
            addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP65_ID,csip);

            /* CSIP66 */
            csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,message,true, true);
            addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP66_ID,csip);

            /* CSIP67 */
            csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,message,true, true);
            addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP67_ID,csip);

            /* CSIP68 */
            csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,message,true, true);
            addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP68_ID,csip);

            /* CSIP69 */
            csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,message,true, true);
            addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP69_ID,csip);

            /* CSIP70 */
            csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,message,true, true);
            addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP70_ID,csip);

            /* CSIP71 */
            csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,message,true, true);
            addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP71_ID,csip);

            /* CSIP72 */
            csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,message,true, true);
            addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP72_ID,csip);

            /* CSIP73 */
            csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,message,true, true);
            addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP73_ID,csip);

            /* CSIP74 */
            csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,message,true, true);
            addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP74_ID,csip);

            /* CSIP75 */
            csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,message,true, true);
            addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP75_ID,csip);

            /* CSIP76 */
            csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,message,true, true);
            addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP76_ID,csip);

            /* CSIP77 */
            csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,message,true, true);
            addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP77_ID,csip);

            /* CSIP78 */
            csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,message,true, true);
            addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP78_ID,csip);

            /* CSIP79 */
            csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,message,true, true);
            addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP79_ID,csip);

        }

//        /* CSIP58 */
//        validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP58_ID);
//        if(validateCSIP58()){
//            validationOutcomeSkipped(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP58_ID,"");
//
//            /* CSIP59 */
//            validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP59_ID);
//            csip = validateCSIP59();
//            if(csip.isValid()){
//                validationOutcomePassed(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP59_ID,csip.getMessage());
//            }
//            else{
//                validationOutcomeFailed(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP59_ID, csip.getMessage());
//                valid = false;
//            }
//
//
//            /* CSIP60 */
//            validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP60_ID);
//            if(validateCSIP60()){
//                validationOutcomeSkipped(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP60_ID,"");
//            }
//
//            /* CSIP113 */
//            validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP113_ID);
//            if(validateCSIP113()){
//                validationOutcomeSkipped(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP113_ID,"");
//            }
//
//            /* CSIP114 */
//            validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP114_ID);
//            if(validateCSIP114()){
//                validationOutcomeSkipped(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP114_ID,"");
//            }
//
//            /* CSIP61 */
//            validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP61_ID);
//            if(validateCSIP61()){
//                validationOutcomeSkipped(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP61_ID,"");
//            }
//
//            /* CSIP62 */
//            validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP62_ID);
//            if(validateCSIP62()){
//                validationOutcomeSkipped(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP62_ID,"");
//            }
//
//            /* CSIP63 */
//            validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP63_ID);
//            if(validateCSIP63()){
//                validationOutcomeSkipped(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP63_ID,"");
//            }
//
//            /* CSIP64 */
//            validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP64_ID);
//            if(validateCSIP64()){
//                validationOutcomeSkipped(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP64_ID,"");
//            }
//
//            /* CSIP65 */
//            validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP65_ID);
//            csip = validateCSIP65();
//            if(csip.isValid()){
//                validationOutcomeSkipped(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP65_ID,csip.getMessage());
//            }
//
//            /* CSIP66 */
//            validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP66_ID);
//            if(validateCSIP66()){
//                validationOutcomeSkipped(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP66_ID,"");
//
//                /* CSIP67 */
//                validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP67_ID);
//                csip = validateCSIP67();
//                if(csip.isValid()){
//                    validationOutcomeSkipped(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP67_ID,csip.getMessage());
//                }
//
//                /* CSIP68 */
//                validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP68_ID);
//                if(validateCSIP68()){
//                    validationOutcomeSkipped(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP68_ID,"");
//                }
//
//                /* CSIP69 */
//                validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP69_ID);
//                csip = validateCSIP69();
//                if(csip.isValid()){
//                    validationOutcomeSkipped(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP69_ID,"");
//                }
//
//                /* CSIP70 */
//                validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP70_ID);
//                csip = validateCSIP70();
//                if(csip.isValid()){
//                    validationOutcomeSkipped(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP70_ID,csip.getMessage());
//                }
//
//                /* CSIP71 */
//                validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP71_ID);
//                try {
//                    csip = validateCSIP71();
//                } catch (NoSuchAlgorithmException e) {
//                    validationOutcomeFailed(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP71_ID,csip.getMessage());
//                    valid = false;
//                }
//                if(csip.isValid()){
//                    validationOutcomePassed(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP71_ID,csip.getMessage());
//                }
//                else{
//                    validationOutcomeFailed(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP71_ID,csip.getMessage());
//                    valid = false;
//                }
//
//                /* CSIP72 */
//                validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP72_ID);
//                csip = validateCSIP72();
//                if(csip.isValid()){
//                    validationOutcomePassed(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP72_ID,csip.getMessage());
//                }
//                else{
//                    validationOutcomeFailed(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP72_ID,csip.getMessage());
//                    valid = false;
//                }
//
//                /* CSIP73 */
//                validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP73_ID);
//                if(validateCSIP73()){
//                    validationOutcomeSkipped(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP73_ID,"");
//                }
//
//                /* CSIP74 */
//                validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP74_ID);
//                if(validateCSIP74()){
//                    validationOutcomeSkipped(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP74_ID,"");
//                }
//
//                /* CSIP75 */
//                validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP75_ID);
//                if(validateCSIP75()){
//                    validationOutcomeSkipped(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP75_ID,"");
//                }
//
//                /* CSIP76 */
//                validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP76_ID);
//                csip = validateCSIP76();
//                if(csip.isValid()){
//                    validationOutcomeSkipped(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP76_ID,csip.getMessage());
//                }
//
//                /* CSIP77 */
//                validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP77_ID);
//                csip = validateCSIP77();
//                if(csip.isValid()){
//                    validationOutcomeSkipped(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP77_ID,csip.getMessage());
//                }
//
//                /* CSIP78 */
//                validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP78_ID);
//                csip = validateCSIP78();
//                if(csip.isValid()){
//                    validationOutcomeSkipped(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP78_ID,csip.getMessage());
//                }
//
//                /* CSIP79 */
//                validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP79_ID);
//                csip = validateCSIP79();
//                if(csip.isValid()){
//                    validationOutcomeSkipped(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP79_ID,csip.getMessage());
//                }
//
//            }
//            else{
//                validationOutcomeFailed(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP66_ID, "");
//                valid = false;
//            }
//
//        }
//        else{
//            validationOutcomeFailed(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP58_ID, "");
//            valid = false;
//        }
    }

    /*
    * mets/fileSec
    * The transferred content is placed in the file section in different file group
    * elements, described in other requirements. Only a single file section
    * ( <fileSec> ) element should be present. It is possible to transfer just
    * descriptive metadata and/or administrative metadata without files placed in
    * this section.
    */
    private ReporterDetails validateCSIP58() {

        return new ReporterDetails();
    }

    /*
    * mets/fileSec/@ID
    * An xml:id identifier for the file section used for internal package references.
    * It must be unique within the package.
    */
    private ReporterDetails validateCSIP59() {
        MetsType.FileSec fileSec = mets.getFileSec();
        if(fileSec != null){
            String id = fileSec.getID();
            if(id != null){
                if(!checkId(id)){
                    addId(id);
                }
                else{
                    return new ReporterDetails("mets/fileSec/@ID must be unique in the package!",false);
                }
            }
            else{
                return new ReporterDetails("mets/fileSec/@ID can't be null!",false);
            }
        }
        return new ReporterDetails();

    }

    /*
    * mets/fileSec/fileGrp[@USE=’Documentation’]
    * All documentation pertaining to the transferred content is placed in one or
    * more file group elements with mets/fileSec/fileGrp/@USE attribute value
    * “Documentation”.See also: File group names
    */
    private boolean validateCSIP60() {
        return true;
    }

    /*
    * mets/fileSec/fileGrp[@USE=’Schemas’]
    * All XML schemas used in the information package should be referenced from
    * one or more file groups with mets/fileSec/fileGrp/@USE attribute value
    * “Schemas”.See also: File group names
    */
    private boolean validateCSIP113() {
        return true;
    }

    /*
    * mets/fileSec/fileGrp[@USE=’Representations’]
    * A pointer to the METS document describing the representation or pointers to
    * the content being transferred must be present in one or more file groups
    * with mets/fileSec/fileGrp/@USE attribute value “Representations”.See
    * also: File group names
    */
    private boolean validateCSIP114() {
        return true;
    }

    /*
    * mets/fileSec/fileGrp/@ADMID
    * If administrative metadata has been provided at file group
    * mets/fileSec/fileGrp level this attribute refers to its administrative
    * metadata section by ID.
    */
    private boolean validateCSIP61() {
        return true;
    }

    /*
    * mets/fileSec/fileGrp[@USE=’Representations’]/@csip:CONTENTINFORMATIONTYPE
    * An added attribute which states the name of the content information type
    * specification used to create the package. The vocabulary will evolve under
    * the curation of the DILCIS Board as additional content information type
    * specifications are developed. This attribute is mandatory when the
    * mets/fileSec/fileGrp/@USE attribute value is “Representations”. When
    * the “Package type” value is “Mixed” and/or the file group describes a
    * “Representation”, then this element states the content information type
    * specification used for the file group.See also: Content information type
    * specification
    */
    private boolean validateCSIP62() {
        return true;
    }

    /*
    * mets/fileSec/fileGrp[@csip:CONTENTINFORMATIONTYPE=’OTHER’]/@csip:OTHERCONTENTINFORMATIONTYPE
    * When the mets/fileSec/fileGrp/@csip:CONTENTINFORMATIONTYPE
    * attribute has the value “OTHER” the
    * mets/fileSec/fileGrp/@csip:OTHERCONTENTINFORMATIONTYPE must
    * state a value for the Content Information Type Specification used.
    */
    private boolean validateCSIP63() {
        return true;
    }

    /*
    * mets/fileSec/fileGrp/@USE
    * The value in the mets/fileSec/fileGrp/@USE is the name of the whole
    * folder structure to the data, e.g “Documentation”, “Schemas”,
    * “Representations/preingest” or “Representations/submission/data”.
    */
    private boolean validateCSIP64() {
        return true;
    }

    /*
    * mets/fileSec/fileGrp/@ID
    * An xml:id identifier for the file group used for internal package references.
    * It must be unique within the package.
    */
    private ReporterDetails validateCSIP65() {
        MetsType.FileSec fileSec = mets.getFileSec();
        List<MetsType.FileSec.FileGrp> fileGrp = fileSec.getFileGrp();
        for(MetsType.FileSec.FileGrp grp : fileGrp){
            String id = grp.getID();
            if(id != null){
                if(!checkId(id)){
                    addId(id);
                }
                else{
                    return new ReporterDetails("mets/fileSec/fileGrp/@ID must be unique in the package!",false);
                }
            }
            else{
                return new ReporterDetails(" mets/fileSec/fileGrp/@ID can't be null!",false);
            }
        }
        return new ReporterDetails();
    }

    /*
    * mets/fileSec/fileGrp/file
    * The file group ( <fileGrp> ) contains the file elements which describe the file
    * objects.
    */
    private ReporterDetails validateCSIP66() {
        boolean valid = true;
//        MetsType.FileSec fileSec = mets.getFileSec();
//        List<MetsType.FileSec.FileGrp> fileGrp = fileSec.getFileGrp();
//        for(MetsType.FileSec.FileGrp grp : fileGrp){
//            List<FileType> files = grp.getFile();
//            System.out.println(files.size());
//            if(files == null || files.size() == 0){
//                valid = false;
//                break;
//            }
//        }
        return new ReporterDetails();
    }

    /*
    * mets/fileSec/fileGrp/file/@ID
    * A unique xml:id identifier for this file across the package.
    */
    private ReporterDetails validateCSIP67() {
        boolean valid = true;
        MetsType.FileSec fileSec = mets.getFileSec();
        List<MetsType.FileSec.FileGrp> fileGrp = fileSec.getFileGrp();
        for(MetsType.FileSec.FileGrp grp : fileGrp){
            List<FileType> files = grp.getFile();
            for(FileType file : files){
                String id = file.getID();
                if(id != null){
                    if(!checkId(id)){
                        addId(id);
                    }
                    else{
                        return new ReporterDetails("mets/fileSec/fileGrp/file/@ID must be unique in the package!",false);
                    }
                }
                else{
                    return new ReporterDetails("mets/fileSec/fileGrp/file/@ID can't be null!",false);
                }
            }
        }
        return new ReporterDetails();
    }

    /*
    * mets/fileSec/fileGrp/file/@MIMETYPE
    * The IANA mime type for the referenced file.See also: IANA media types
    */
    private boolean validateCSIP68() {
        return true;
    }

    /*
    * mets/fileSec/fileGrp/file/@SIZE
    * Size of the referenced file in bytes.
    */
    private ReporterDetails validateCSIP69() throws IOException {
        MetsType.FileSec fileSec = mets.getFileSec();
        List<MetsType.FileSec.FileGrp> fileGrp = fileSec.getFileGrp();
        for(MetsType.FileSec.FileGrp grp : fileGrp){
            List<FileType> files = grp.getFile();
            for(FileType file : files){
                List<FileType.FLocat> flocat = file.getFLocat();
                if(flocat != null){
                    if(flocat.size() == 1){
                        String href = URLDecoder.decode(flocat.get(0).getHref(),"UTF-8");
                        if(href != null){
                            Long size = file.getSIZE();
                            if(size != null){
                                if(isZipFileFlag()){
                                    if(!zipManager.verifySize(getEARKSIPpath(),href,size)){
                                        return new ReporterDetails("mets/fileSec/fileGrp/file/@SIZE and size of file isn't equal!",false);
                                    }
                                }
                                else{
                                    if(!folderManager.verifySize(getEARKSIPpath(),href,size)){
                                        return new ReporterDetails("mets/fileSec/fileGrp/file/@SIZE and size of file isn't equal!",false);
                                    }
                                }
                            }
                            else{
                                return new ReporterDetails("mets/fileSec/fileGrp/file/@SIZE can't be null!",false);
                            }
                        }
                        else{
                            return new ReporterDetails("mets/fileSec/fileGrp/file/flocat/@href can't be null!",false);
                        }
                    }
                    else{
                        return new ReporterDetails("cannot have more than one mets/fileSec/fileGrp/file/flocat!",false);
                    }
                }
                else{
                    return new ReporterDetails("mets/fileSec/fileGrp/file/flocat can't be null!",false);
                }
            }
        }
        return new ReporterDetails();
    }

    /*
    * mets/fileSec/fileGrp/file/@CREATED
    * Creation date of the referenced file.
    */
    private ReporterDetails validateCSIP70() {
        MetsType.FileSec fileSec = mets.getFileSec();
        List<MetsType.FileSec.FileGrp> fileGrp = fileSec.getFileGrp();
        for(MetsType.FileSec.FileGrp grp : fileGrp){
            List<FileType> files = grp.getFile();
            for(FileType file : files){
                if(file.getCREATED() == null){
                    return new ReporterDetails("mets/fileSec/fileGrp/file/@CREATED can't be null",false);
                }
            }
        }
        return new ReporterDetails();
    }

    /*
    * mets/fileSec/fileGrp/file/@CHECKSUM
    * The checksum of the referenced file.
    */
    private ReporterDetails validateCSIP71() throws IOException, NoSuchAlgorithmException {
        List<String> tmp = new ArrayList<>();
        for(CHECKSUMTYPE check: CHECKSUMTYPE.values()){
            tmp.add(check.toString());
        }
        MetsType.FileSec fileSec = mets.getFileSec();
        List<MetsType.FileSec.FileGrp> fileGrp = fileSec.getFileGrp();
        for(MetsType.FileSec.FileGrp grp : fileGrp){
            List<FileType> files = grp.getFile();
            for(FileType file : files){
                String checksumType = file.getCHECKSUMTYPE();
                if(checksumType == null){
                    return new ReporterDetails("mets/fileSec/fileGrp/file/@CHECKSUMTYPE can't be null ",false);
                }
                else{
                    if(!tmp.contains(checksumType)){
                        return new ReporterDetails("mets/fileSec/fileGrp/file/@CHECKSUMTYPE see valid values at METS schema",false);
                    }
                    else{
                        String checksum = file.getCHECKSUM();
                        if(checksum == null){
                            return new ReporterDetails("mets/fileSec/fileGrp/file/@CHECKSUM can't be null",false);
                        }
                        else{
                            String href = file.getFLocat().get(0).getHref();

                            if(href == null){
                                return new ReporterDetails("mets/fileSec/fileGrp/file/flocat/href can't be null",false);
                            }
                            else{
                                String filePath = URLDecoder.decode(href,"UTF-8");
                                if(isZipFileFlag()){
                                    if(!zipManager.verifyChecksum(getEARKSIPpath(),filePath,checksumType,checksum)){
                                        return new ReporterDetails("mets/fileSec/fileGrp/file/@CHECKSUM and file checksum isn't equal",false);
                                    }
                                }
                                else{
                                    if(!folderManager.verifyChecksum(getEARKSIPpath(),filePath,checksumType,checksum)){
                                        return new ReporterDetails("mets/fileSec/fileGrp/file/@CHECKSUM and file checksum isn't equal",false);
                                    }
                                }

                            }
                        }
                    }
                }
            }
        }
        return new ReporterDetails();
    }

    /*
    * mets/fileSec/fileGrp/file/@CHECKSUMTYPE
    * The type of checksum following the value list present in the METS-standard
    * which has been used for calculating the checksum for the referenced file.
    */
    private ReporterDetails validateCSIP72() {
        boolean valid = true;
        List<String> tmp = new ArrayList<>();
        for(CHECKSUMTYPE check: CHECKSUMTYPE.values()){
            tmp.add(check.toString());
        }
        MetsType.FileSec fileSec = mets.getFileSec();
        List<MetsType.FileSec.FileGrp> fileGrp = fileSec.getFileGrp();
        for(MetsType.FileSec.FileGrp grp : fileGrp) {
            List<FileType> files = grp.getFile();
            for (FileType file : files) {
                String checksumType = file.getCHECKSUMTYPE();
                if(checksumType == null){
                    return new ReporterDetails("mets/fileSec/fileGrp/file/@CHECKSUMTYPE can't be null!",false);
                }
                else{
                    if(!tmp.contains(checksumType)){
                        return new ReporterDetails("mets/fileSec/fileGrp/file/@CHECKSUMTYPE see valid values at METS schema",false);
                    }
                }
            }
        }
        return new ReporterDetails();
    }

    /*
    * mets/fileSec/fileGrp/file/@OWNERID
    * If an identifier for the file was supplied by the owner it can be recorded in
    * this attribute.
    */
    private boolean validateCSIP73() {
        return true;
    }

    /*
    * mets/fileSec/fileGrp/file/@ADMID
    * If administrative metadata has been provided for the file this attribute refers
    * to the file’s administrative metadata by ID.
    */
    private boolean validateCSIP74() {
        return true;
    }

    /*
    * mets/fileSec/fileGrp/file/@DMDID
    * If descriptive metadata has been provided per file this attribute refers to the
    * file’s descriptive metadata by ID.
    */
    private boolean validateCSIP75() {
        return true;
    }

    /*
    * mets/fileSec/fileGrp/file/FLocat
    * The location of each external file must be defined by the file location
    * <FLocat> element using the same rules as for referencing metadata files.
    * All references to files should be made using the XLink href attribute and the
    * file protocol using the relative location of the file.
    */
    private ReporterDetails validateCSIP76() {
        MetsType.FileSec fileSec = mets.getFileSec();
        List<MetsType.FileSec.FileGrp> fileGrp = fileSec.getFileGrp();
        for(MetsType.FileSec.FileGrp grp : fileGrp) {
            List<FileType> files = grp.getFile();
            for (FileType file : files) {
                List<FileType.FLocat> flocat = file.getFLocat();
                if(flocat == null){
                    return new ReporterDetails("mets/fileSec/fileGrp/file/FLocat can't be null!",false);
                }
                else{
                    if(flocat.size() != 1){
                        return new ReporterDetails("mets/fileSec/fileGrp/file/FLocat cannot have more than one",false);
                    }
                }
            }
        }
        return new ReporterDetails();
    }

    /*
    * mets/fileSec/fileGrp/file/FLocat[@LOCTYPE=’URL’]
    * The locator type is always used with the value “URL” from the vocabulary in
    * the attribute.
    */
    private ReporterDetails validateCSIP77() {
        MetsType.FileSec fileSec = mets.getFileSec();
        List<MetsType.FileSec.FileGrp> fileGrp = fileSec.getFileGrp();
        for(MetsType.FileSec.FileGrp grp : fileGrp) {
            List<FileType> files = grp.getFile();
            for (FileType file : files) {
                List<FileType.FLocat> flocat = file.getFLocat();
                if(flocat == null){
                    return new ReporterDetails("mets/fileSec/fileGrp/file/FLocat can't be null!",false);
                }
                else{
                    for(FileType.FLocat floc : flocat){
                        String loctype = floc.getLOCTYPE();
                        if(loctype == null){
                            return new ReporterDetails("mets/fileSec/fileGrp/file/FLocat[@LOCTYPE=’URL’] can't be null!",false);
                        }
                        else{
                            if(!loctype.equals("URL")){
                                return new ReporterDetails("mets/fileSec/fileGrp/file/FLocat[@LOCTYPE=’URL’] value has to be URL",false);
                            }
                        }
                    }
                }
            }
        }
        return new ReporterDetails();
    }

    /*
    * mets/fileSec/fileGrp/file/FLocat[@xlink:type=’simple’]
    * Attribute used with the value “simple”. Value list is maintained by the xlink
    * standard.
    */
    private ReporterDetails validateCSIP78() {
        MetsType.FileSec fileSec = mets.getFileSec();
        List<MetsType.FileSec.FileGrp> fileGrp = fileSec.getFileGrp();
        for(MetsType.FileSec.FileGrp grp : fileGrp) {
            List<FileType> files = grp.getFile();
            for (FileType file : files) {
                List<FileType.FLocat> flocat = file.getFLocat();
                if(flocat == null){
                    return new ReporterDetails("mets/fileSec/fileGrp/file/FLocat can't be null!",false);
                }
                else{
                    for(FileType.FLocat floc : flocat){
                        String type = floc.getType();
                        if(type == null){
                            return new ReporterDetails("mets/fileSec/fileGrp/file/FLocat[@xlink:type=’simple’] can't be null!",false);
                        }
                        else{
                            if(!type.equals("simple")){
                                return new ReporterDetails("mets/fileSec/fileGrp/file/FLocat[@xlink:type=’simple’] value has to be simple",false);
                            }
                        }
                    }
                }
            }
        }
        return new ReporterDetails();
    }

    /*
    * mets/fileSec/fileGrp/file/FLocat/@xlink:href
    * The actual location of the resource. We recommend recording a URL type
    * filepath within this attribute.
    */
    private ReporterDetails validateCSIP79() throws IOException {
        MetsType.FileSec fileSec = mets.getFileSec();
        List<MetsType.FileSec.FileGrp> fileGrp = fileSec.getFileGrp();
        for(MetsType.FileSec.FileGrp grp : fileGrp) {
            List<FileType> files = grp.getFile();
            for (FileType file : files) {
                List<FileType.FLocat> flocat = file.getFLocat();
                if(flocat == null){
                    return new ReporterDetails("mets/fileSec/fileGrp/file/FLocat can't be null!",false);
                }
                else{
                    for(FileType.FLocat floc : flocat){
                        String href = floc.getHref();
                        if(href != null){
                            String filepath = URLDecoder.decode(href,"UTF-8");
                            if(isZipFileFlag()){
                                if(!zipManager.checkPathExists(getEARKSIPpath(),filepath)){
                                    return new ReporterDetails("mets/fileSec/fileGrp/file/@xlink:href file does not exist or invalid path!",false);
                                }
                            }
                            else{
                                if(!folderManager.checkPathExists(getEARKSIPpath(), Paths.get(filepath))){
                                    return new ReporterDetails("mets/fileSec/fileGrp/file/@xlink:href file does not exist or invalid path",false);
                                }
                            }
                        }
                        else{
                            return new ReporterDetails("mets/fileSec/fileGrp/file/FLocat/@xlink:href can't be null!",false);
                        }
                    }
                }
            }
        }
        return new ReporterDetails();
    }


}
