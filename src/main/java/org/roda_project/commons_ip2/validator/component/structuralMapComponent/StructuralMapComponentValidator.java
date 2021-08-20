package org.roda_project.commons_ip2.validator.component.structuralMapComponent;

import org.roda_project.commons_ip2.mets_v1_12.beans.DivType;
import org.roda_project.commons_ip2.mets_v1_12.beans.FileType;
import org.roda_project.commons_ip2.mets_v1_12.beans.MetsType;
import org.roda_project.commons_ip2.mets_v1_12.beans.StructMapType;
import org.roda_project.commons_ip2.validator.component.ValidatorComponentImpl;
import org.roda_project.commons_ip2.validator.constants.Constants;
import org.roda_project.commons_ip2.validator.constants.ConstantsCSIPspec;
import org.roda_project.commons_ip2.validator.reporter.ReporterDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

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
    public void validate() throws IOException {
        ReporterDetails csip;

        /* CSIP80 */
        validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP80_ID);
        csip = validateCSIP80();
        csip.setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION);
        addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP80_ID,csip);

        if(csip.isValid()){

            /* CSIP81 */
            validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP81_ID);
            csip = validateCSIP81();
            csip.setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION);
            addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP81_ID,csip);

            /* CSIP82 */
            validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP82_ID);
            csip = validateCSIP82();
            csip.setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION);
            addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP82_ID,csip);

            /* CSIP83 */
            validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP83_ID);
            csip = validateCSIP83();
            csip.setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION);
            addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP83_ID,csip);

            /* CSIP84 */
            validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP84_ID);
            csip = validateCSIP84();
            csip.setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION);
            addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP84_ID,csip);

            /* CSIP85 */
            validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP85_ID);
            csip = validateCSIP85();
            csip.setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION);
            addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP85_ID,csip);

            /* CSIP86 */
            validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP86_ID);
            csip = validateCSIP86();
            csip.setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION);
            addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP86_ID,csip);

            /* CSIP88 */
            validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP88_ID);
            csip = validateCSIP88();
            csip.setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION);
            addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP88_ID,csip);

            /* CSIP89 */
            validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP89_ID);
            csip = validateCSIP89();
            csip.setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION);
            addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP89_ID,csip);

            /* CSIP90 */
            validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP90_ID);
            // csip = validateCSIP90();
            csip.setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION);
            addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP90_ID,csip);

            /* CSIP91 */
            validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP91_ID);
            // csip = validateCSIP91();
            csip.setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION);
            addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP91_ID,csip);

            /* CSIP92 */
            validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP92_ID);
            // csip = validateCSIP92();
            csip.setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION);
            addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP92_ID,csip);

            /* CSIP93 */
            validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP93_ID);
            //csip = validateCSIP93();
            csip.setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION);
            addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP93_ID,csip);

            /* CSIP94 */
            validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP94_ID);
            csip = validateCSIP94();
            csip.setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION);
            addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP94_ID,csip);

            /* CSIP95 */
            validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP95_ID);
            //csip = validateCSIP95();
            csip.setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION);
            addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP95_ID,csip);

            /* CSIP96 */
            validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP96_ID);
            //csip = validateCSIP96();
            csip.setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION);
            addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP96_ID,csip);

            /* CSIP97 */
            validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP116_ID);
            //csip = validateCSIP116();
            csip.setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION);
            addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP116_ID,csip);

            /* CSIP97 */
            validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP97_ID);
            //csip = validateCSIP97();
            csip.setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION);
            addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP97_ID,csip);

            /* CSIP98 */
            validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP98_ID);
            csip = validateCSIP98();
            csip.setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION);
            addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP98_ID,csip);

            /* CSIP99 */
            validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP99_ID);
            //csip = validateCSIP99();
            csip.setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION);
            addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP99_ID,csip);

            /* CSIP100 */
            validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP100_ID);
            //csip = validateCSIP100();
            csip.setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION);
            addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP100_ID,csip);

            /* CSIP118 */
            validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP118_ID);
            //csip = validateCSIP118();
            csip.setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION);
            addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP118_ID,csip);

            /* CSIP101 */
            validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP101_ID);
            //csip = validateCSIP101();
            csip.setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION);
            addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP101_ID,csip);

            /* CSIP102 */
            validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP102_ID);
            csip = validateCSIP102();
            csip.setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION);
            addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP102_ID,csip);

            /* CSIP103 */
            validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP103_ID);
            //csip = validateCSIP103();
            csip.setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION);
            addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP103_ID,csip);

            /* CSIP104 */
            validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP104_ID);
            //csip = validateCSIP104();
            csip.setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION);
            addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP104_ID,csip);

            /* CSIP119 */
            validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP119_ID);
            //csip = validateCSIP119();
            csip.setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION);
            addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP119_ID,csip);

            /* CSIP105 */
            validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP105_ID);
            //csip = validateCSIP105();
            csip.setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION);
            addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP105_ID,csip);

            /* CSIP106 */
            validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP106_ID);
            csip = validateCSIP106();
            csip.setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION);
            addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP106_ID,csip);

            /* CSIP107 */
            validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP107_ID);
            //csip = validateCSIP107();
            csip.setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION);
            addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP107_ID,csip);

            /* CSIP108 */
            validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP108_ID);
            //csip = validateCSIP108();
            csip.setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION);
            addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP108_ID,csip);

            /* CSIP109 */
            validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP109_ID);
            //csip = validateCSIP109();
            csip.setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION);
            addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP109_ID,csip);

            /* CSIP110 */
            validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP110_ID);
            //csip = validateCSIP110();
            csip.setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION);
            addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP110_ID,csip);

            /* CSIP111 */
            validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP111_ID);
            //csip = validateCSIP111();
            csip.setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION);
            addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP111_ID,csip);

            /* CSIP112 */
            validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP112_ID);
            //csip = validateCSIP112();
            csip.setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION);
            addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP112_ID,csip);
        }
        else{
            String message = "SKIPPED because mets/structMap doesn't exist! (" + metsName + ")";

            /* CSIP81 */
            csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,message,true, true);
            addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP81_ID,csip);

            /* CSIP82 */
            csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,message,true, true);
            addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP82_ID,csip);

            /* CSIP83 */
            csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,message,true, true);
            addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP83_ID,csip);

            /* CSIP84 */
            csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,message,true, true);
            addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP84_ID,csip);

            /* CSIP85 */
            csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,message,true, true);
            addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP85_ID,csip);

            /* CSIP86 */
            csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,message,true, true);
            addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP86_ID,csip);

            /* CSIP88 */
            csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,message,true, true);
            addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP88_ID,csip);

            /* CSIP89 */
            csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,message,true, true);
            addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP89_ID,csip);

            /* CSIP90 */
            csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,message,true, true);
            addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP90_ID,csip);

            /* CSIP91 */
            csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,message,true, true);
            addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP91_ID,csip);

            /* CSIP92 */
            csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,message,true, true);
            addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP92_ID,csip);

            /* CSIP93 */
            csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,message,true, true);
            addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP93_ID,csip);

            /* CSIP94 */
            csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,message,true, true);
            addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP94_ID,csip);

            /* CSIP95 */
            csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,message,true, true);
            addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP95_ID,csip);

            /* CSIP96 */
            csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,message,true, true);
            addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP96_ID,csip);

            /* CSIP116 */
            csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,message,true, true);
            addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP81_ID,csip);

            /* CSIP97 */
            csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,message,true, true);
            addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP97_ID,csip);

            /* CSIP98 */
            csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,message,true, true);
            addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP98_ID,csip);

            /* CSIP99 */
            csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,message,true, true);
            addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP99_ID,csip);

            /* CSIP100 */
            csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,message,true, true);
            addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP100_ID,csip);

            /* CSIP118 */
            csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,message,true, true);
            addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP118_ID,csip);

            /* CSIP101 */
            csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,message,true, true);
            addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP101_ID,csip);

            /* CSIP102 */
            csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,message,true, true);
            addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP102_ID,csip);

            /* CSIP103 */
            csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,message,true, true);
            addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP103_ID,csip);

            /* CSIP104 */
            csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,message,true, true);
            addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP104_ID,csip);

            /* CSIP119 */
            csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,message,true, true);
            addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP119_ID,csip);

            /* CSIP105 */
            csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,message,true, true);
            addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP105_ID,csip);

            /* CSIP106 */
            csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,message,true, true);
            addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP106_ID,csip);

            /* CSIP107 */
            csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,message,true, true);
            addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP107_ID,csip);

            /* CSIP108 */
            csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,message,true, true);
            addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP108_ID,csip);

            /* CSIP109 */
            csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,message,true, true);
            addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP109_ID,csip);

            /* CSIP110 */
            csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,message,true, true);
            addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP110_ID,csip);

            /* CSIP111 */
            csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,message,true, true);
            addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP111_ID,csip);

            /* CSIP112 */
            csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,message,true, true);
            addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP112_ID,csip);
        }
//        validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP80_ID);
//        csip = validateCSIP80();
//        if(csip.isValid()){
//            validationOutcomePassed(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP80_ID,csip.getMessage());
//        }
//        else{
//            validationOutcomeFailed(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP80_ID,csip.getMessage());
//            valid = false;
//        }
//
//        /* CSIP81 */
//        validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP81_ID);
//        csip = validateCSIP81();
//        if(csip.isValid()){
//            validationOutcomePassed(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP81_ID,csip.getMessage());
//        }
//        else{
//            validationOutcomeFailed(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP81_ID,csip.getMessage());
//            valid = false;
//        }
//
//
//        /* CSIP82 */
//        validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP82_ID);
//        csip = validateCSIP82();
//        if(csip.isValid()){
//            validationOutcomePassed(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP82_ID,csip.getMessage());
//        }
//        else{
//            validationOutcomeFailed(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP82_ID,csip.getMessage());
//            valid = false;
//        }
//
//        /* CSIP83 */
//        validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP83_ID);
//        csip = validateCSIP83();
//        if(csip.isValid()){
//            validationOutcomePassed(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP83_ID,csip.getMessage());
//        }
//        else{
//            validationOutcomeFailed(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP83_ID,csip.getMessage());
//            valid = false;
//        }
//
//        /* CSIP84 */
//        validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP84_ID);
//        csip = validateCSIP84();
//        if(csip.isValid()){
//            validationOutcomePassed(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP84_ID,csip.getMessage());
//        }
//        else{
//            validationOutcomeFailed(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP84_ID,csip.getMessage());
//            valid = false;
//        }
//
//        /* CSIP85 */
//        validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP85_ID);
//        csip = validateCSIP85();
//        if(csip.isValid()){
//            validationOutcomePassed(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP85_ID,csip.getMessage());
//        }
//        else{
//            validationOutcomeFailed(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP85_ID,csip.getMessage());
//            valid = false;
//        }
//
//        /* CSIP86 */
//        validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP86_ID);
//        csip = validateCSIP86();
//        if(csip.isValid()){
//            validationOutcomePassed(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP86_ID,csip.getMessage());
//        }
//        else{
//            validationOutcomeFailed(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP86_ID,csip.getMessage());
//            valid = false;
//        }
//
//        /* CSIP88 */
//        validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP88_ID);
//        csip = validateCSIP88();
//        if(csip.isValid()){
//            validationOutcomePassed(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP88_ID,csip.getMessage());
//        }
//        else{
//            validationOutcomeFailed(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP88_ID,csip.getMessage());
//            valid = false;
//        }
//
//        /* CSIP89 */
//        validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP89_ID);
//        csip = validateCSIP89();
//        if(csip.isValid()){
//            validationOutcomePassed(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP89_ID,csip.getMessage());
//        }
//        else{
//            validationOutcomeFailed(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP89_ID,csip.getMessage());
//            valid = false;
//        }
//
//        /* CSIP90 */
//        validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP90_ID);
//        if(validateCSIP90()){
//            validationOutcomeSkipped(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP90_ID,"");
//        }
//
//        /* CSIP91 */
//        validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP91_ID);
//        if(validateCSIP91()){
//            validationOutcomeSkipped(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP91_ID,"");
//        }
//
//        /* CSIP92 */
//        validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP92_ID);
//        if(validateCSIP92()){
//            validationOutcomeSkipped(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP92_ID,"");
//        }
//
//        /* CSIP93 */
//        validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP93_ID);
//        if(validateCSIP93()){
//            validationOutcomeSkipped(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP93_ID,"");
//        }
//
//        /* CSIP94 */
//        validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP94_ID);
//        csip = validateCSIP94();
//        if(csip.isValid()){
//            validationOutcomePassed(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP94_ID,csip.getMessage());
//        }
//        else{
//            validationOutcomeFailed(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP94_ID,csip.getMessage());
//            valid = false;
//        }
//
//        /* CSIP95 */
//        validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP95_ID);
//        if(validateCSIP95()){
//            validationOutcomeSkipped(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP95_ID,"");
//        }
//
//        /* CSIP96 */
//        validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP96_ID);
//        if(validateCSIP96()){
//            validationOutcomeSkipped(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP96_ID,"");
//        }
//
//        /* CSIP116 */
//        validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP116_ID);
//        if(validateCSIP116()){
//            validationOutcomeSkipped(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP116_ID,"");
//        }
//
//        /* CSIP97 */
//        validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP97_ID);
//        if(validateCSIP97()){
//            validationOutcomeSkipped(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP97_ID,"");
//        }
//
//        /* CSIP98 */
//        validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP98_ID);
//        csip = validateCSIP98();
//        if(csip.isValid()){
//            validationOutcomePassed(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP98_ID,csip.getMessage());
//        }
//        else{
//            validationOutcomeFailed(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP98_ID,csip.getMessage());
//            valid = false;
//        }
//
//        /* CSIP99 */
//        validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP99_ID);
//        if(validateCSIP99()){
//            validationOutcomeSkipped(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP99_ID,"");
//        }
//
//        /* CSIP100 */
//        validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP100_ID);
//        if(validateCSIP100()){
//            validationOutcomeSkipped(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP100_ID,"");
//        }
//
//        /* CSIP101 */
//        validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP101_ID);
//        if(validateCSIP101()){
//            validationOutcomeSkipped(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP101_ID,"");
//        }
//
//        /* CSIP102 */
//        validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP102_ID);
//        csip = validateCSIP102();
//        if(csip.isValid()){
//            validationOutcomePassed(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP102_ID,csip.getMessage());
//        }
//        else{
//            validationOutcomeFailed(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP102_ID,csip.getMessage());
//            valid = false;
//        }
//
//        /* CSIP100 */
//        validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP103_ID);
//        if(validateCSIP103()){
//            validationOutcomeSkipped(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP103_ID,"");
//        }
//
//        /* CSIP104 */
//        validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP104_ID);
//        if(validateCSIP104()){
//            validationOutcomeSkipped(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP104_ID,"");
//        }
//
//        /* CSIP119 */
//        validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP119_ID);
//        if(validateCSIP119()){
//            validationOutcomeSkipped(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP119_ID,"");
//        }
//
//        /* CSIP105 */
//        validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP105_ID);
//        if(validateCSIP105()){
//            validationOutcomeSkipped(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP105_ID,"");
//        }
//
//        /* CSIP106 */
//        validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP106_ID);
//        csip = validateCSIP106();
//        if(csip.isValid()){
//            validationOutcomePassed(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP106_ID,csip.getMessage());
//        }
//        else{
//            validationOutcomeFailed(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP106_ID,csip.getMessage());
//            valid = false;
//        }
//
//        /* CSIP107 */
//        validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP107_ID);
//        if(validateCSIP107()){
//            validationOutcomeSkipped(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP107_ID,"");
//        }
//
//        /* CSIP108 */
//        validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP108_ID);
//        if(validateCSIP108()){
//            validationOutcomeSkipped(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP108_ID,"");
//        }
//
//        /* CSIP109 */
//        validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP109_ID);
//        if(validateCSIP109()){
//            validationOutcomeSkipped(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP109_ID,"");
//        }
//
//        /* CSIP110 */
//        validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP110_ID);
//        if(validateCSIP110()){
//            validationOutcomeSkipped(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP110_ID,"");
//        }
//
//        /* CSIP111 */
//        validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP111_ID);
//        if(validateCSIP111()){
//            validationOutcomeSkipped(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP111_ID,"");
//        }
//
//        /* CSIP112 */
//        validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP112_ID);
//        if(validateCSIP112()){
//            validationOutcomeSkipped(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP112_ID,"");
//        }
    }

    /*
    * mets/structMap
    * The structural map <structMap> element is the only mandatory element in
    * the METS. The <structMap> in the CSIP describes the highest logical
    * structure of the IP. Each METS file must include ONE structural map
    * <structMap> element used exactly as described here. Institutions can add
    * their own additional custom structural maps as separate <structMap>
    * sections.
    */
    private ReporterDetails validateCSIP80() {
        List<StructMapType> structMap = mets.getStructMap();
        if(structMap == null){
            return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, "mets/structMap can't be null! (" + metsName + ")",false,false);
        }
        else{
            if(structMap.size() == 0){
                return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, "You have to describe mets/structMap (" + metsName + ")" ,false, false);
            }
        }
        return new ReporterDetails();
    }

    /*
     * mets/structMap[@TYPE='PHYSICAL']
     * The mets/structMap/@TYPE attribute must take the value “PHYSICAL” from the vocabulary.
     * See also: Structural map typing
    */
    private ReporterDetails validateCSIP81() {
        List<StructMapType> structMap = mets.getStructMap();
        if(structMap != null){
            for(StructMapType struct : structMap){
                String type = struct.getTYPE();
                if(type == null){
                    return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,"mets/structMap[@TYPE='PHYSICAL'] can't be null! (" + metsName + ")",false,false);
                }
                else {
                    if (!type.equals("PHYSICAL")) {
                        return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,"",false,false);
                    }
                }
            }
        }
        return new ReporterDetails();
    }

    /*
    * mets/structMap[@LABEL='CSIP']
    * The mets/structMap/@LABEL attribute value is set to “CSIP” from the vocabulary.
    * See also: Structural map label
    */
    private ReporterDetails validateCSIP82() {
        List<StructMapType> structMap = mets.getStructMap();
        if(structMap != null){
            for(StructMapType struct : structMap){
                String label = struct.getLABEL();
                if(label == null){
                   return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,"",false,false);
                }
                else{
                    if(!label.equals("CSIP")){
                        return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,"",false,false);
                    }
                }
            }
        }
        return new ReporterDetails();
    }

    /*
    * mets/structMap[@LABEL='CSIP']/@ID
    * An xml:id identifier for the structural description (structMap) used for internal package references. It must be unique within the package.
    */
    private ReporterDetails validateCSIP83() {
        List<StructMapType> structMap = mets.getStructMap();
        if(structMap != null){
            for(StructMapType struct : structMap){
                String id = struct.getID();
                if(id == null){
                   return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,"mets/structMap[@LABEL='CSIP']/@ID can't be null!",false,false);
                }
                else{
                    if(checkId(id)){
                        return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, "mets/structMap[@LABEL='CSIP']/@ID must be unique in the package",false,false);
                    }
                    else{
                        addId(id);
                    }
                }
            }
        }
        return new ReporterDetails();
    }

    /*
    * mets/structMap[@LABEL='CSIP']/div
    * The structural map comprises a single division.
    */
    private ReporterDetails validateCSIP84() {
        List<StructMapType> structMap = mets.getStructMap();
        if(structMap != null){
            for(StructMapType struct : structMap){
                DivType div = struct.getDiv();
                if(div == null){
                    return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,"",false,false);
                }
            }
        }
        return new ReporterDetails();
    }

    /*
    * mets/structMap[@LABEL='CSIP']/div/@ID
    * Mandatory, xml:id identifier must be unique within the package.
    */
    private ReporterDetails validateCSIP85() {
        List<StructMapType> structMap = mets.getStructMap();
        if(structMap != null){
            for(StructMapType struct : structMap){
                DivType div = struct.getDiv();
                if(div != null){
                    String id = div.getID();
                    if(id == null){
                        return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,"mets/structMap[@LABEL='CSIP']/div/@ID can't be null!",false,false);
                    }
                    else{
                        if(checkId(id)){
                            return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,"mets/structMap[@LABEL='CSIP']/div/@ID must be unique in the package!",false,false);
                        }
                        else{
                            addId(id);
                        }
                    }
                }
            }
        }
        return new ReporterDetails();
    }

    /*
    * mets/structMap[@LABEL='CSIP']/div/@LABEL
    * The package’s top-level structural division <div> element’s @LABEL attribute value must be identical to the package identifier, i.e. the same value as the mets/@OBJID attribute.
    */
    private ReporterDetails validateCSIP86() {
        List<StructMapType> structMap = mets.getStructMap();
        String objid = mets.getOBJID();
        if(structMap != null){
            for(StructMapType struct : structMap){
                DivType div = struct.getDiv();
                if(div != null){
                    String label = div.getLABEL();
                    if(!label.equals(objid)){
                        return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,"",false,false);
                    }
                }
            }
        }
        return new ReporterDetails();
    }

    /*
    * mets/structMap[@LABEL='CSIP']/div/div[@LABEL='Metadata']
    * The metadata referenced in the administrative and/or descriptive metadata section is described in the structural map with one sub division.
    * When the transfer consists of only administrative and/or descriptive metadata this is the only sub division that occurs.
    */
    private ReporterDetails validateCSIP88() {
        boolean valid = true;
        boolean found = false;
        List<StructMapType> structMap = mets.getStructMap();
        if(structMap != null){
            for(StructMapType struct : structMap){
                DivType div = struct.getDiv();
                if(div != null){
                    List<DivType> divs = div.getDiv();
                    for(DivType d : divs){
                        if(d.getLABEL().equals("Metadata")){
                            found = true;
                            break;
                        }
                    }
                    if(found){
                        break;
                    }
                }
            }
        }
        if(!found){
            return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,"",false,false);
        }
        return new ReporterDetails();
    }

    /*
    * Metadata division identifier
    * mets/structMap[@LABEL='CSIP']/div/div[@LABEL='Metadata']/@ID
    * Mandatory, xml:id identifier must be unique within the package.
    */
    private ReporterDetails validateCSIP89() {
        List<StructMapType> structMap = mets.getStructMap();
        if(structMap != null){
            for(StructMapType struct : structMap){
                DivType div = struct.getDiv();
                if(div != null){
                    List<DivType> divs = div.getDiv();
                    for(DivType d : divs){
                        if(d.getLABEL().equals("Metadata")){
                            String id = d.getID();
                            if(id == null){
                               return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,"mets/structMap[@LABEL='CSIP']/div/div[@LABEL='Metadata']/@ID can't be null!",false,false);
                            }
                            else{
                                if(checkId(id)){
                                    return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, "mets/structMap[@LABEL='CSIP']/div/div[@LABEL='Metadata']/@ID must be unique!",false,false);
                                }
                                else{
                                    addId(id);
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
    * mets/structMap[@LABEL='CSIP']/div/div[@LABEL='Metadata']
    * The metadata division <div> element’s @LABEL attribute value must be “Metadata”.
    * See also: File group names
    */
    private boolean validateCSIP90() {
        return true;
    }

    /*
    * mets/structMap[@LABEL='CSIP']/div/div[@LABEL='Metadata']/@ADMID
    * When there is administrative metadata and the amdSec is present, all administrative metadata MUST be referenced via the administrative sections different identifiers.
    * All of the <amdSec> identifiers are listed in a single @ADMID using spaces as delimiters.
    */
    private boolean validateCSIP91() {
        return true;
    }

    /*
    * mets/structMap[@LABEL='CSIP']/div/div[@LABEL='Metadata']/@ADMID
    * When there is administrative metadata and the amdSec is present, all administrative metadata MUST be referenced via the administrative sections different identifiers.
    * All of the <amdSec> identifiers are listed in a single @ADMID using spaces as delimiters.
    */
    private boolean validateCSIP92() {
        return true;
    }

    /*
    * mets/structMap[@LABEL='CSIP']/div/div[@LABEL='Documentation']
    * The documentation referenced in the file section file groups is described in the structural map with one sub division.
    */
    private boolean validateCSIP93() {
        return true;
    }

    /*
    * mets/structMap[@LABEL='CSIP']/div/div[@LABEL='Documentation']/@ID
    * Mandatory, xml:id identifier must be unique within the package.
    */
    private ReporterDetails validateCSIP94() {
        List<StructMapType> structMap = mets.getStructMap();
        if(structMap != null){
            for(StructMapType struct : structMap){
                DivType div = struct.getDiv();
                if(div != null){
                    List<DivType> divs = div.getDiv();
                    for(DivType d : divs){
                        if(d.getLABEL().equals("Documentation")){
                            String id = d.getID();
                            if(id == null){
                                return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,"mets/structMap[@LABEL='CSIP']/div/div[@LABEL='Documentation']/@ID can't be null",false,false);
                            }
                            else{
                                if(checkId(id)){
                                    return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,"mets/structMap[@LABEL='CSIP']/div/div[@LABEL='Documentation']/@ID must be unique in the package",false,false);
                                }
                                else{
                                    addId(id);
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
    * mets/structMap[@LABEL='CSIP']/div/div[@LABEL='Documentation']
    * The documentation division <div> element in the package uses the value “Documentation” from the vocabulary as the value for the @LABEL attribute.
    * See also: File group names
    */
    private boolean validateCSIP95() {
        return true;
    }

    /*
    * mets/structMap[@LABEL='CSIP']/div/div[@LABEL='Documentation']/fptr
    * All file groups containing documentation described in the package are referenced via the relevant file group identifiers. There MUST be one file group reference per <fptr> element.
    */
    private boolean validateCSIP96() {
        return true;
    }

    /*
    * mets/structMap[@LABEL='CSIP']/div/div[@LABEL='Documentation']/fptr/@FILEID
    * A reference, by ID, to the “Documentation” file group.
    * Related to the requirements CSIP60 which describes the “Documentation” file group and CSIP65 which describes the file group identifier.
    */
    private boolean validateCSIP116() {
        return true;
    }

    /*
    * mets/structMap[@LABEL='CSIP']/div/div[@LABEL='Schemas']
    * The schemas referenced in the file section file groups are described in the structural map within a single sub-division.
    */
    private boolean validateCSIP97() {
        return true;
    }

    /*
    * mets/structMap[@LABEL='CSIP']/div/div[@LABEL='Schemas']/@ID
    * Mandatory, xml:id identifier must be unique within the package.
    */
    private ReporterDetails validateCSIP98() {
        List<StructMapType> structMap = mets.getStructMap();
        if(structMap != null){
            for(StructMapType struct : structMap){
                DivType div = struct.getDiv();
                if(div != null){
                    List<DivType> divs = div.getDiv();
                    for(DivType d : divs){
                        if(d.getLABEL().equals("Schemas")){
                            String id = d.getID();
                            if(id == null){
                              return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, "mets/structMap[@LABEL='CSIP']/div/div[@LABEL='Schemas']/@ID can't be null!",false,false);
                            }
                            else{
                                if(checkId(id)){
                                    return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,"mets/structMap[@LABEL='CSIP']/div/div[@LABEL='Schemas']/@ID must be unique in the package!",false,false);
                                }
                                else{
                                    addId(id);
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
    * mets/structMap[@LABEL='CSIP']/div/div[@LABEL='Schemas']
    * The schema division <div> element’s @LABEL attribute has the value “Schemas” from the vocabulary.
    * See also: File group names
    */
    private boolean validateCSIP99() {
        return true;
    }

    /*
    * mets/structMap[@LABEL='CSIP']/div/div[@LABEL='Schemas']/fptr
    * All file groups containing schemas described in the package are referenced via the relevant file group identifiers. One file group reference per fptr-element
    */
    private boolean validateCSIP100() {
        return true;
    }

    /*
    * mets/structMap[@LABEL='CSIP']/div/div[@LABEL='Schemas']/fptr/@FILEID
    * The pointer to the identifier for the “Schema” file group.
    * Related to the requirements CSIP113 which describes the “Schema” file group and CSIP65 which describes the file group identifier.
    */
    private boolean validateCSIP101() {
        return true;
    }

    /*
    * mets/structMap[@LABEL='CSIP']/div/div[@LABEL='Representations']/@ID
    * Mandatory, xml:id identifier must be unique within the package.
    */
    private ReporterDetails validateCSIP102() {
        List<StructMapType> structMap = mets.getStructMap();
        if(structMap != null){
            for(StructMapType struct : structMap){
                DivType div = struct.getDiv();
                if(div != null){
                    List<DivType> divs = div.getDiv();
                    for(DivType d : divs){
                        if(d.getLABEL().equals("Representations")){
                            String id = d.getID();
                            if(id == null){
                               return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,"mets/structMap[@LABEL='CSIP']/div/div[@LABEL='Representations']/@ID can't be null",false,false);
                            }
                            else{
                                if(checkId(id)){
                                    return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,"mets/structMap[@LABEL='CSIP']/div/div[@LABEL='Representations']/@ID must be unique in the package",false,false);
                                }
                                else{
                                    addId(id);
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
    * mets/structMap[@LABEL='CSIP']/div/div[@LABEL='Representations']
    * The package’s content division <div> element must have the @LABEL attribute value “Representations”, taken from the vocabulary.
    * See also: File group names
    */
    private boolean validateCSIP103() {
        return true;
    }


    /*
    * mets/structMap[@LABEL='CSIP']/div/div[@LABEL='Representations']/fptr
    * All file groups containing content described in the package are referenced via the relevant file group identifiers. One file group reference per fptr-element.
    */
    private boolean validateCSIP104() {
        return true;
    }

    /*
    * mets/structMap[@LABEL='CSIP']/div/div[@LABEL='Representations']/fptr/@FILEID
    * The pointer to the identifier for the “Representations” file group.
    * Related to the requirements CSIP114 which describes the “Representations” file group and CSIP65 which describes the file group identifier.
    */
    private boolean validateCSIP119() {
        return true;
    }

    /*
    * mets/structMap[@LABEL='CSIP']/div/div
    * When a package consists of multiple representations, each described by a representation level METS.xml document, there is a discrete representation div element for each representation.
    * Each representation div references the representation level METS.xml document, documenting the structure of the package and its constituent representations.
    */
    private boolean validateCSIP105() {
        return true;
    }

    /*
    * mets/structMap[@LABEL='CSIP']/div/div/@ID
    * Mandatory, xml:id identifier must be unique within the package.
    */
    private ReporterDetails validateCSIP106() {
        List<StructMapType> structMap = mets.getStructMap();
        List<String> validation = new ArrayList<>();
        validation.add("Metadata");
        validation.add("Documentation");
        validation.add("Schemas");
        validation.add("Representations");
        if(structMap != null){
            for(StructMapType struct : structMap){
                DivType div = struct.getDiv();
                if(div != null){
                    List<DivType> divs = div.getDiv();
                    for(DivType d : divs){
                        if(validation.contains(d.getLABEL()) || d.getLABEL().matches("Representations/")){
                            String id = d.getID();
                            if(id == null){
                                return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,"mets/structMap[@LABEL='CSIP']/div/div/@ID can't be null!",false,false);
                            }
                            else{
                                if(checkId(id)){
                                    return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,"mets/structMap[@LABEL='CSIP']/div/div/@ID must be unique in the package",false,false);
                                }
                                else{
                                    addId(id);
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
    * mets/structMap[@LABEL='CSIP']/div/div/@LABEL
    * The package’s representation division <div> element @LABEL attribute value must be the path to the representation level METS document.
    * This requirement gives the same value to be used as the requirement named “File group identifier” (CSIP64)
    * See also: File group names
    */
    private ReporterDetails validateCSIP107() {
        List<StructMapType> structMap = mets.getStructMap();
        List<String> validation = new ArrayList<>();
        validation.add("Metadata");
        validation.add("Documentation");
        validation.add("Schemas");
        validation.add("Representations");
        if(structMap != null){
            for(StructMapType struct : structMap){
                DivType div = struct.getDiv();
                if(div != null){
                    List<DivType> divs = div.getDiv();
                    for(DivType d : divs){
                        if(!validation.contains(d.getLABEL()) || !d.getLABEL().matches("Representations/")){
                            return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,"mets/structMap[@LABEL='CSIP']/div/div/@LABEL value isn't valid",false,false);
                        }
                    }
                }
            }
        }
        return new ReporterDetails();
    }

    /*
    * mets/structMap[@LABEL='CSIP']/div/div/mptr/@xlink:title
    * The file group containing the files described in the package are referenced via the relevant file group identifier.
    * Related to the requirements CSIP114 which describes the “Representations” file group and CSIP65 which describes the file group identifier.
    */
    private ReporterDetails validateCSIP108() {
        List<StructMapType> structMap = mets.getStructMap();
        MetsType.FileSec fileSec = mets.getFileSec();
        if(structMap != null){
            for(StructMapType struct : structMap){
                DivType div = struct.getDiv();
                if(div != null){
                    List<DivType> divs = div.getDiv();
                    for(DivType d : divs){
                        if(d.getLABEL().matches("Representations/")) {
                            List<DivType.Mptr> mptrs = d.getMptr();
                            if (mptrs != null && mptrs.size() != 0) {
                                for (DivType.Mptr mptr : mptrs) {
                                    String title = mptr.getTitle();
                                    if (title != null) {
                                        List<MetsType.FileSec.FileGrp> fileGrps = fileSec.getFileGrp();
                                        boolean found = false;
                                        for (MetsType.FileSec.FileGrp fileGrp : fileGrps) {
                                            if (title.equals(fileGrp.getID())) {
                                                found = true;
                                            }
                                        }
                                        if (!found) {
                                            return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, "mets/structMap[@LABEL='CSIP']/div/div/mptr/@xlink:title can't find match id in fileSection", false, false);
                                        }
                                    }
                                    return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, "mets/structMap[@LABEL='CSIP']/div/div/mptr/@xlink:title can't be null", false, false);
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
    * mets/structMap[@LABEL='CSIP']/div/div/mptr
    * The division <div> of the specific representation includes one occurrence of the METS pointer <mptr> element, pointing to the appropriate representation METS file.
    */
    private ReporterDetails validateCSIP109() {
        List<StructMapType> structMap = mets.getStructMap();
        if(structMap != null){
            for(StructMapType struct : structMap){
                DivType div = struct.getDiv();
                if(div != null){
                    List<DivType> divs = div.getDiv();
                    for(DivType d : divs){
                        if(d.getLABEL().matches("Representations/")) {
                            List<DivType.Mptr> mptrs = d.getMptr();
                            if (mptrs == null && mptrs.size() != 1) {
                                return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, "mets/structMap[@LABEL='CSIP']/div/div/mptr can't be null or more than one", false, false);
                            }
                        }
                    }
                }
            }
        }
        return new ReporterDetails();
    }

    /*
    * mets/structMap/div/div/mptr/@xlink:href
    * The actual location of the resource. We recommend recording a URL type filepath within this attribute.
    */
    private ReporterDetails validateCSIP110() throws IOException {
        List<StructMapType> structMap = mets.getStructMap();
        if(structMap != null){
            for(StructMapType struct : structMap){
                DivType div = struct.getDiv();
                if(div != null){
                    List<DivType> divs = div.getDiv();
                    for(DivType d : divs){
                        if(d.getLABEL().matches("Representations/")) {
                            List<DivType.Mptr> mptrs = d.getMptr();
                            for(DivType.Mptr mptr: mptrs){
                                String filePath = URLDecoder.decode(mptr.getHref(),"UTF-8");
                                if(isZipFileFlag()){
                                    if(!zipManager.checkPathExists(getEARKSIPpath(),filePath)){
                                        return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,"mets/structMap/div/div/mptr/@xlink:href " + filePath + " doesn't exists (" + metsName + ")",false,false);
                                    }
                                }
                                else{
                                    if(!folderManager.checkPathExists(getEARKSIPpath(), Paths.get(filePath))){
                                        return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,"mets/structMap/div/div/mptr/@xlink:href " + filePath + " doesn't exists (" + metsName +")" ,false,false);
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
    * mets/structMap/div/div/mptr[@xlink:type='simple']
    * Attribute used with the value “simple”. Value list is maintained by the xlink standard.
    */
    private ReporterDetails validateCSIP111() {
        List<StructMapType> structMap = mets.getStructMap();
        if(structMap != null){
            for(StructMapType struct : structMap){
                DivType div = struct.getDiv();
                if(div != null){
                    List<DivType> divs = div.getDiv();
                    for(DivType d : divs){
                        if(d.getLABEL().matches("Representations/")) {
                            List<DivType.Mptr> mptrs = d.getMptr();
                            for(DivType.Mptr mptr: mptrs){
                                String type = mptr.getType();
                                if(type != null){
                                    if(!type.equals("simple")){
                                        return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, "mets/structMap/div/div/mptr[@xlink:type='simple'] value must be 'simple'", false, false);
                                    }
                                }
                                else{
                                    return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, "mets/structMap/div/div/mptr[@xlink:type='simple'] can't be null", false, false);
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
    * mets/structMap/div/div/mptr[@LOCTYPE='URL']
    * The locator type is always used with the value “URL” from the vocabulary in the attribute.
    */
    private ReporterDetails validateCSIP112() {
        List<StructMapType> structMap = mets.getStructMap();
        if(structMap != null){
            for(StructMapType struct : structMap){
                DivType div = struct.getDiv();
                if(div != null){
                    List<DivType> divs = div.getDiv();
                    for(DivType d : divs){
                        if(d.getLABEL().matches("Representations/")) {
                            List<DivType.Mptr> mptrs = d.getMptr();
                            for(DivType.Mptr mptr: mptrs){
                                String locType = mptr.getLOCTYPE();
                                if(locType != null){
                                    if(!locType.equals("URL")){
                                        return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, "mets/structMap/div/div/mptr[@LOCTYPE='URL']value must be 'URL'", false, false);
                                    }
                                }
                                else{
                                    return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, "mets/structMap/div/div/mptr[@LOCTYPE='URL'] can't be null", false, false);
                                }
                            }
                        }
                    }
                }
            }
        }
        return new ReporterDetails();
    }
}
