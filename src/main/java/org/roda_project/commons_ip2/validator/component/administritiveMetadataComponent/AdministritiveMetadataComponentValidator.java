package org.roda_project.commons_ip2.validator.component.administritiveMetadataComponent;

import org.roda_project.commons_ip2.mets_v1_12.beans.AmdSecType;
import org.roda_project.commons_ip2.mets_v1_12.beans.MdSecType;
import org.roda_project.commons_ip2.mets_v1_12.beans.Mets;
import org.roda_project.commons_ip2.mets_v1_12.beans.MetsType;
import org.roda_project.commons_ip2.validator.common.ControlledVocabularyParser;
import org.roda_project.commons_ip2.validator.component.ValidatorComponentImpl;
import org.roda_project.commons_ip2.validator.constants.Constants;
import org.roda_project.commons_ip2.validator.constants.ConstantsCSIPspec;
import org.roda_project.commons_ip2.validator.reporter.ReporterDetails;
import org.roda_project.commons_ip2.validator.utils.CHECKSUMTYPE;
import org.roda_project.commons_ip2.validator.utils.MetadataType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author João Gomes <jgomes@keep.pt>
 */
public class AdministritiveMetadataComponentValidator extends ValidatorComponentImpl {
    private static final Logger LOGGER = LoggerFactory.getLogger(AdministritiveMetadataComponentValidator.class);

    private final String MODULE_NAME;
    private List<AmdSecType> amdSec;
    private List<String> dmdSecStatus;

    public void setDmdSecStatus(List<String> dmdSecStatus) {
        this.dmdSecStatus = dmdSecStatus;
    }

    public AdministritiveMetadataComponentValidator(String module_name) {
        MODULE_NAME = module_name;
        this.dmdSecStatus = new ArrayList<>();
        ControlledVocabularyParser controlledVocabularyParser = new ControlledVocabularyParser(Constants.PATH_RESOURCES_CSIP_VOCABULARY_DMD_SEC_STATUS,dmdSecStatus);
        controlledVocabularyParser.parse();
        setDmdSecStatus(controlledVocabularyParser.getData());
    }

    @Override
    public void validate() throws IOException {
        amdSec = mets.getAmdSec();
        ReporterDetails csip;

        /* CSIP31 */
        validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP31_ID);
        csip = validateCSIP31();
        csip.setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION);
        addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP31_ID,csip);

        if(csip.isValid()){

            /* CSIP32 */
            validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP32_ID);
            csip = validateCSIP32();
            csip.setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION);
            addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP32_ID,csip);

            if(csip.isValid()){

                /* CSIP33 */
                validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP33_ID);
                csip = validateCSIP33();
                csip.setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION);
                addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP33_ID,csip);

                /* CSIP34 */
                validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP34_ID);
                csip = validateCSIP34();
                csip.setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION);
                addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP34_ID,csip);

                /* CSIP35 */
                validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP35_ID);
                csip = validateCSIP35();
                csip.setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION);
                addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP35_ID,csip);

                if(csip.isValid()){

                    /* CSIP36 */
                    validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP36_ID);
                    // csip = validateCSIP36();
                    csip.setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION);
                    addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP36_ID,csip);

                    /* CSIP37 */
                    validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP37_ID);
                    // csip = validateCSIP37();
                    csip.setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION);
                    addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP37_ID,csip);

                    /* CSIP38 */
                    validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP38_ID);
                    // csip = validateCSIP38();
                    csip.setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION);
                    addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP38_ID,csip);

                    /* CSIP39 */
                    validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP39_ID);
                    // csip = validateCSIP39();
                    csip.setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION);
                    addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP39_ID,csip);

                    /* CSIP40 */
                    validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP40_ID);
                    // csip = validateCSIP40();
                    csip.setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION);
                    addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP40_ID,csip);

                    /* CSIP41 */
                    validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP41_ID);
                    // csip = validateCSIP41();
                    csip.setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION);
                    addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP41_ID,csip);

                    /* CSIP42 */
                    validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP42_ID);
                    // csip = validateCSIP42();
                    csip.setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION);
                    addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP42_ID,csip);

                    /* CSIP43 */
                    validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP43_ID);
                    //csip = validateCSIP43();
                    csip.setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION);
                    addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP43_ID,csip);

                    /* CSIP44 */
                    validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP44_ID);
                    //csip = validateCSIP44();
                    csip.setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION);
                    addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP44_ID,csip);
                }
                else{
                    String message = "SKIPPED because mets/amdSec/digiprovMD/mdRef doesn't exist! (" + metsName + ")";
                    /* CSIP36 */
                    csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,message,true, true);
                    addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP36_ID,csip);

                    /* CSIP37 */
                    csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,message,true, true);
                    addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP37_ID,csip);

                    /* CSIP38 */
                    csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,message,true, true);
                    addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP38_ID,csip);

                    /* CSIP39 */
                    csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,message,true, true);
                    addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP39_ID,csip);

                    /* CSIP40 */
                    csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,message,true, true);
                    addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP40_ID,csip);

                    /* CSIP41 */
                    csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,message,true, true);
                    addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP41_ID,csip);

                    /* CSIP42 */
                    csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,message,true, true);
                    addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP42_ID,csip);

                    /* CSIP43 */
                    csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,message,true, true);
                    addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP43_ID,csip);

                    /* CSIP44 */
                    csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,message,true, true);
                    addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP44_ID,csip);
                }


            }
            else{
                String message = "SKIPPED because mets/amdSec/digiprovMD doesn't exist! (" + metsName + ")";

                /* CSIP33 */
                csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,message,true, true);
                addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP33_ID,csip);

                /* CSIP34 */
                csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,message,true, true);
                addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP34_ID,csip);

                /* CSIP35 */
                csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,message,true, true);
                addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP35_ID,csip);

                /* CSIP36 */
                csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,message,true, true);
                addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP36_ID,csip);

                /* CSIP37 */
                csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,message,true, true);
                addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP37_ID,csip);

                /* CSIP38 */
                csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,message,true, true);
                addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP38_ID,csip);

                /* CSIP39 */
                csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,message,true, true);
                addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP39_ID,csip);

                /* CSIP40 */
                csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,message,true, true);
                addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP40_ID,csip);

                /* CSIP41 */
                csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,message,true, true);
                addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP41_ID,csip);

                /* CSIP42 */
                csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,message,true, true);
                addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP42_ID,csip);

                /* CSIP43 */
                csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,message,true, true);
                addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP43_ID,csip);

                /* CSIP44 */
                csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,message,true, true);
                addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP44_ID,csip);

            }

            /* CSIP45 */
            validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP45_ID);
            // csip = validateCSIP45();
            csip.setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION);
            addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP45_ID,csip);

            if(csip.isValid()){

                /* CSIP46 */
                validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP46_ID);
                //csip = validateCSIP46();
                csip.setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION);
                addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP46_ID,csip);

                /* CSIP47 */
                validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP47_ID);
                //csip = validateCSIP47();
                csip.setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION);
                addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP47_ID,csip);

                /* CSIP48 */
                validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP48_ID);
                csip = validateCSIP48();
                csip.setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION);
                addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP48_ID,csip);

                if(csip.isValid()){

                    /* CSIP49 */
                    validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP49_ID);
                    //csip = validateCSIP49();
                    csip.setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION);
                    addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP49_ID,csip);

                    /* CSIP50 */
                    validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP50_ID);
                    //csip = validateCSIP50();
                    csip.setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION);
                    addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP50_ID,csip);

                    /* CSIP51 */
                    validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP51_ID);
                    //csip = validateCSIP51();
                    csip.setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION);
                    addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP51_ID,csip);

                    /* CSIP52 */
                    validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP52_ID);
                    //csip = validateCSIP52();
                    csip.setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION);
                    addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP52_ID,csip);

                    /* CSIP53 */
                    validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP53_ID);
                    //csip = validateCSIP53();
                    csip.setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION);
                    addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP53_ID,csip);

                    /* CSIP54 */
                    validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP54_ID);
                    //csip = validateCSIP54();
                    csip.setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION);
                    addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP54_ID,csip);

                    /* CSIP55 */
                    validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP55_ID);
                    //csip = validateCSIP55();
                    csip.setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION);
                    addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP55_ID,csip);

                    /* CSIP56 */
                    validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP56_ID);
                    //csip = validateCSIP56();
                    csip.setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION);
                    addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP56_ID,csip);

                    /* CSIP57 */
                    validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP57_ID);
                    //csip = validateCSIP57();
                    csip.setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION);
                    addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP57_ID,csip);
                }
                else{
                    String message = "SKIPPED because mets/amdSec/rightsMD/mdRef doesn't exist! (" + metsName + ")";

                    /* CSIP49 */
                    csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,message,true, true);
                    addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP49_ID,csip);

                    /* CSIP50 */
                    csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,message,true, true);
                    addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP50_ID,csip);

                    /* CSIP51 */
                    csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,message,true, true);
                    addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP51_ID,csip);

                    /* CSIP52 */
                    csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,message,true, true);
                    addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP52_ID,csip);

                    /* CSIP53 */
                    csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,message,true, true);
                    addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP53_ID,csip);

                    /* CSIP54 */
                    csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,message,true, true);
                    addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP54_ID,csip);

                    /* CSIP55 */
                    csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,message,true, true);
                    addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP55_ID,csip);

                    /* CSIP56 */
                    csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,message,true, true);
                    addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP56_ID,csip);

                    /* CSIP57 */
                    csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,message,true, true);
                    addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP57_ID,csip);
                }
            }
            else{
                String message = "SKIPPED because mets/amdSec/rightsMD doesn't exist! (" + metsName + ")";

                /* CSIP46 */
                csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,message,true, true);
                addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP46_ID,csip);

                /* CSIP47 */
                csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,message,true, true);
                addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP47_ID,csip);

                /* CSIP48 */
                csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,message,true, true);
                addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP48_ID,csip);

                /* CSIP49 */
                csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,message,true, true);
                addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP49_ID,csip);

                /* CSIP50 */
                csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,message,true, true);
                addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP50_ID,csip);

                /* CSIP51 */
                csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,message,true, true);
                addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP51_ID,csip);

                /* CSIP52 */
                csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,message,true, true);
                addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP52_ID,csip);

                /* CSIP53 */
                csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,message,true, true);
                addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP53_ID,csip);

                /* CSIP54 */
                csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,message,true, true);
                addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP54_ID,csip);

                /* CSIP55 */
                csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,message,true, true);
                addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP55_ID,csip);

                /* CSIP56 */
                csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,message,true, true);
                addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP56_ID,csip);

                /* CSIP57 */
                csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,message,true, true);
                addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP57_ID,csip);
            }

        }
        else{
            String message = "SKIPPED because mets/amdSec doesn't exist! (" + metsName + ")";

            /* CSIP32 */
            csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,message,true, true);
            addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP32_ID,csip);

            /* CSIP33 */
            csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,message,true, true);
            addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP33_ID,csip);

            /* CSIP34 */
            csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,message,true, true);
            addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP34_ID,csip);

            /* CSIP35 */
            csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,message,true, true);
            addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP35_ID,csip);

            /* CSIP36 */
            csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,message,true, true);
            addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP36_ID,csip);

            /* CSIP37 */
            csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,message,true, true);
            addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP37_ID,csip);

            /* CSIP38 */
            csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,message,true, true);
            addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP38_ID,csip);

            /* CSIP39 */
            csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,message,true, true);
            addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP39_ID,csip);

            /* CSIP40 */
            csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,message,true, true);
            addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP40_ID,csip);

            /* CSIP41 */
            csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,message,true, true);
            addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP41_ID,csip);

            /* CSIP42 */
            csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,message,true, true);
            addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP42_ID,csip);

            /* CSIP43 */
            csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,message,true, true);
            addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP43_ID,csip);

            /* CSIP44 */
            csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,message,true, true);
            addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP44_ID,csip);

            /* CSIP45 */
            csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,message,true, true);
            addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP45_ID,csip);

            /* CSIP46 */
            csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,message,true, true);
            addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP46_ID,csip);

            /* CSIP47 */
            csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,message,true, true);
            addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP47_ID,csip);

            /* CSIP48 */
            csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,message,true, true);
            addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP48_ID,csip);

            /* CSIP49 */
            csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,message,true, true);
            addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP49_ID,csip);

            /* CSIP50 */
            csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,message,true, true);
            addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP50_ID,csip);

            /* CSIP51 */
            csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,message,true, true);
            addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP51_ID,csip);

            /* CSIP52 */
            csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,message,true, true);
            addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP52_ID,csip);

            /* CSIP53 */
            csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,message,true, true);
            addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP53_ID,csip);

            /* CSIP54 */
            csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,message,true, true);
            addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP54_ID,csip);

            /* CSIP55 */
            csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,message,true, true);
            addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP55_ID,csip);

            /* CSIP56 */
            csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,message,true, true);
            addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP56_ID,csip);

            /* CSIP57 */
            csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,message,true, true);
            addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP57_ID,csip);
        }
    }

    /*
    * mets/amdSec
    * If administrative / preservation metadata is available, it must be described
    * using the administrative metadata section ( <amdSec> ) element. All
    * administrative metadata is present in a single <amdSec> element. It is
    * possible to transfer metadata in a package using just the descriptive
    * metadata section and/or administrative metadata section.
    */
    private ReporterDetails validateCSIP31() throws IOException {
        ReporterDetails details = new ReporterDetails();
        if(isZipFileFlag()){
            String objectID = mets.getOBJID();
            if(objectID == null){
                details.setValid(false);
                details.addIssue("mets/OBJID can't be null");
            }
            else{
                String regex = metsPath + "metadata/preservation/.*";
                if(mets.getAmdSec() == null) {
                    if (zipManager.verifyMetadataFilesFolder(getEARKSIPpath(),regex)) {
                        details.setValid(false);
                        details.addIssue("You have files in the metadata/preservation folder, you must have mets/amdSec");
                    }
                }
                else{
                    if(mets.getAmdSec().size() != zipManager.countMetadataFiles(getEARKSIPpath(),regex)){
                        details.setValid(false);
                        details.addIssue("The number of files described is not equal to the number of files in the metadata/preservation folder");
                    }
                }
            }
        }
        else{
            if(mets.getAmdSec() == null) {
                if (folderManager.verifyMetadataFilesFolder(Paths.get(metsPath),"preservation")) {
                    details.setValid(false);
                    details.addIssue("You have files in the metadata/preservation folder, you must have mets/amdSec");
                }
            }
            else{
                if(mets.getAmdSec().size() != folderManager.countMetadataFiles(Paths.get(metsPath),"preservation")){
                    details.setValid(false);
                    details.addIssue("The number of files described is not equal to the number of files in the metadata/preservation folder");
                }
            }
        }
        return details;
    }

    /*
    * mets/amdSec/digiprovMD
    * For recording information about preservation the standard PREMIS is used.
    * It is mandatory to include one <digiprovMD> element for each piece of
    * PREMIS metadata. The use if PREMIS in METS is following the
    * recommendations in the 2017 version of PREMIS in METS Guidelines.
    */
    private ReporterDetails validateCSIP32() {
        // Falta contar e comparar quantos premis metadata tem o sip
        for(AmdSecType a: amdSec){
            List<MdSecType> digiprov = a.getDigiprovMD();
            if(digiprov == null){
                return new ReporterDetails("",false);
            }
        }
        return new ReporterDetails();
    }

    /*
    * mets/amdSec/digiprovMD/@ID
    * An xml:id identifier for the digital provenance metadata section
    * mets/amdSec/digiprovMD used for internal package references. It must
    * be unique within the package.
    */
    private ReporterDetails validateCSIP33() {
        for(AmdSecType a: amdSec) {
            List<MdSecType> digiprov = a.getDigiprovMD();
            for(MdSecType md: digiprov){
               if(!checkId(md.getID())){
                   addId(md.getID());
               }
               else{
                   return new ReporterDetails("mets/amdSec/digiprovMD/@ID isn't unique in the package",false);
               }
            }
        }
        return new ReporterDetails();
    }

    /*
    * mets/amdSec/digiprovMD/@STATUS
    * Indicates the status of the package using a fixed vocabulary.See also:
    * dmdSec status
    */
    private ReporterDetails validateCSIP34() {
        for(AmdSecType a: amdSec){
            List<MdSecType> digiprov = a.getDigiprovMD();
            for(MdSecType md: digiprov){
                String status = md.getSTATUS();
                if(!dmdSecStatus.contains(status)){
                    return new ReporterDetails("mets/amdSec/digiprovMD/@STATUS see valid values dmdSec status",false);
                }
            }
        }
        return new ReporterDetails();

    }

    /*
    * mets/amdSec/digiprovMD/mdRef
    * Reference to the digital provenance metadata file stored in the “metadata”
    * section of the IP.
    * Está mal
    */
    private ReporterDetails validateCSIP35(){
        for(AmdSecType a: amdSec){
            List<MdSecType> digiprov = a.getDigiprovMD();
            for(MdSecType md: digiprov){
                MdSecType.MdRef mdRef = md.getMdRef();
                if(mdRef == null){
                    return new ReporterDetails("",false);
                }
            }
        }
        return new ReporterDetails();
    }

    /*
    * mets/amdSec/digiprovMD/mdRef[@LOCTYPE=’URL’]
    * The locator type is always used with the value “URL” from the vocabulary in
    * the attribute.
    * Ver este tb
    */
    private boolean validateCSIP36() {
        boolean valid = true;
        for(AmdSecType a: amdSec){
            List<MdSecType> digiprov = a.getDigiprovMD();
            for(MdSecType md: digiprov){
                MdSecType.MdRef mdRef = md.getMdRef();
                if(mdRef == null){
                    return false;
                }
                String loctype = mdRef.getLOCTYPE();
                if(loctype == null) {
                    valid = false;
                }
                else{
                    if(!loctype.equals("URL")){
                        valid = false;
                    }
                }
            }
        }
        return valid;
    }

    /*
    * mets/amdSec/digiprovMD/mdRef[@xlink:type=’simple’]
    * Attribute used with the value “simple”. Value list is maintained by the xlink
    * standard.
    */
    private ReporterDetails validateCSIP37() {
        ReporterDetails details = new ReporterDetails();
        for(AmdSecType a: amdSec){
            List<MdSecType> digiprov = a.getDigiprovMD();
            for(MdSecType md: digiprov){
                MdSecType.MdRef mdRef = md.getMdRef();
                String xLinktype = mdRef.getType();
                if(xLinktype == null) {
                    details.setValid(false);
                    details.addIssue("mets/amdSec/digiprovMD/mdRef[@xlink:type=’simple’] can't be null  (" + metsName + ")");
                }
                else{
                    if(!xLinktype.equals("simple")){
                        details.setValid(false);
                        details.addIssue("mets/amdSec/digiprovMD/mdRef[@xlink:type=’simple’] value must be 'simple'  (" + metsName + ")");
                    }
                }
            }
        }
        return details;
    }

    /*
    * mets/amdSec/digiprovMD/mdRef/@xlink:href
    * The actual location of the resource. This specification recommends
    * recording a URL type filepath within this attribute.
    */
    private ReporterDetails validateCSIP38() throws IOException {
        for(AmdSecType a: amdSec){
            List<MdSecType> digiprov = a.getDigiprovMD();
            for(MdSecType md: digiprov){
                MdSecType.MdRef mdRef = md.getMdRef();
                if(mdRef != null){
                    String href = URLDecoder.decode(mdRef.getHref(),"UTF-8");
                    if(isZipFileFlag()){
                        if(!zipManager.checkPathExists(getEARKSIPpath(),href)){
                          return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,"mets/amdSec/digiprovMD/mdRef/@xlink:href path doesn't exists",false,false);
                        }
                    }
                    else{
                        if(!folderManager.checkPathExists(getEARKSIPpath(), Paths.get(href))){
                            return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,"mets/amdSec/digiprovMD/mdRef/@xlink:href path doesn't exists",false,false);
                        }
                    }
                }
            }
        }
        return new ReporterDetails();
    }

    /*
    * mets/amdSec/digiprovMD/mdRef/@MDTYPE
    * Specifies the type of metadata in the referenced file. Values are taken from
    * the list provided by the METS.
    */
    private ReporterDetails validateCSIP39() {
        List<String> tmp = new ArrayList<>();
        for(MetadataType md: MetadataType.values()){
            tmp.add(md.toString());
        }
        for(AmdSecType a: amdSec){
            List<MdSecType> digiprov = a.getDigiprovMD();
            for(MdSecType md: digiprov){
                MdSecType.MdRef mdRef = md.getMdRef();
                if(mdRef != null){
                    String mdType = mdRef.getMDTYPE();
                    if(mdType == null){
                        return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,"mets/amdSec/digiprovMD/mdRef/@MDTYPE can't be null (" + metsName + ")",false,false);
                    }
                    else {
                        if(!tmp.contains(mdType)){
                            return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,"mets/amdSec/digiprovMD/mdRef/@MDTYPE value isn't valid (" + metsName + ")",false,false);
                        }
                    }
                }
            }
        }
        return new ReporterDetails();
    }

    /*
    * mets/amdSec/digiprovMD/mdRef/@MIMETYPE
    * The IANA mime type for the referenced file.See also: IANA media types
    */
    private boolean validateCSIP40() {
        return true;
    }

    /*
    * mets/amdSec/digiprovMD/mdRef/@SIZE
    * Size of the referenced file in bytes.
    */
    private ReporterDetails validateCSIP41() throws IOException {
        for(AmdSecType a: amdSec){
            List<MdSecType> digiprov = a.getDigiprovMD();
            for(MdSecType md: digiprov){
                MdSecType.MdRef mdRef = md.getMdRef();
                if(mdRef != null){
                    String href = URLDecoder.decode(mdRef.getHref(),"UTF-8");
                    Long size = mdRef.getSIZE();
                    if(size == null){
                        return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,"mets/amdSec/digiprovMD/mdRef/@SIZE of file can't be null (" + metsName + ")",false,false);
                    }
                    else{
                        if(isZipFileFlag()){
                            if(!zipManager.verifySize(getEARKSIPpath(),href,size)){
                                return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,"mets/amdSec/digiprovMD/mdRef/@SIZE doesn't match with the size of file (" + metsName + ")",false,false);
                            }
                        }
                        else{
                            if(!folderManager.verifySize(getEARKSIPpath(),href,size)){
                                return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,"mets/amdSec/digiprovMD/mdRef/@SIZE doesn't match with the size of file (" + metsName + ")",false,false);
                            }
                        }
                    }
                }
            }
        }
        return new ReporterDetails();
    }

    /*
    * mets/amdSec/digiprovMD/mdRef/@CREATED
    * Creation date of the referenced file.
    */
    private ReporterDetails validateCSIP42() {
        for(AmdSecType a: amdSec){
            List<MdSecType> digiprov = a.getDigiprovMD();
            for(MdSecType md: digiprov){
                MdSecType.MdRef mdRef = md.getMdRef();
                if(mdRef.getCREATED() == null){
                    return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,"mets/amdSec/digiprovMD/mdRef/@CREATED can't be null (" + metsName + ")",false,false);
                }
            }
        }
        return new ReporterDetails();
    }

    /*
    * mets/amdSec/digiprovMD/mdRef/@CHECKSUM
    * The checksum of the referenced file.
    */
    private ReporterDetails validateCSIP43() throws IOException, NoSuchAlgorithmException {
        List<String> tmp = new ArrayList<>();
        for(CHECKSUMTYPE check: CHECKSUMTYPE.values()){
            tmp.add(check.toString());
        }
        for(AmdSecType a: amdSec){
            List<MdSecType> digiprov = a.getDigiprovMD();
            for(MdSecType md: digiprov){
                MdSecType.MdRef mdRef = md.getMdRef();
                String checksumType = mdRef.getCHECKSUMTYPE();
                if(checksumType == null){
                    return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,"mets/amdSec/digiprovMD/mdRef/@CHECKSUMTYPE can't be null (" + metsName + ")",false,false);
                }
                else {
                    if (!tmp.contains(checksumType)) {
                        return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,"mets/amdSec/digiprovMD/mdRef/@CHECKSUMTYPE value isn't valid (" + metsName + ")",false,false);
                    } else {
                        String checksum = mdRef.getCHECKSUM();
                        if (checksum == null) {
                            return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,"mets/amdSec/digiprovMD/mdRef/@CHECKSUM can't be null (" + metsName + ")",false,false);
                        } else {
                            String file = URLDecoder.decode(mdRef.getHref(), "UTF-8");
                            if (isZipFileFlag()) {
                                if (!zipManager.verifyChecksum(getEARKSIPpath(), file, checksumType, checksum)) {
                                    return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,"mets/amdSec/digiprovMD/mdRef/@CHECKSUM doesn't match with file checksum (" + metsName + ")",false,false);
                                }
                            } else {
                                if (!folderManager.verifyChecksum(getEARKSIPpath(), file, checksumType, checksum)) {
                                    return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,"mets/amdSec/digiprovMD/mdRef/@CHECKSUM doesn't match with file checksum (" + metsName + ")",false,false);
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
    * mets/amdSec/digiprovMD/mdRef/@CHECKSUMTYPE
    * The type of checksum following the value list present in the METS-standard
    * which has been used for calculating the checksum for the referenced file.
    */
    private ReporterDetails validateCSIP44() {
        List<String> tmp = new ArrayList<>();
        for(CHECKSUMTYPE check: CHECKSUMTYPE.values()){
            tmp.add(check.toString());
        }
        for(AmdSecType a: amdSec){
            List<MdSecType> digiprov = a.getDigiprovMD();
            for(MdSecType md: digiprov){
                MdSecType.MdRef mdRef = md.getMdRef();
                String checksumType = mdRef.getCHECKSUMTYPE();
                if(checksumType == null){
                    return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,"mets/amdSec/digiprovMD/mdRef/@CHECKSUMTYPE can't be null (" + metsName + ")",false,false);
                }
                else{
                    if(!tmp.contains(checksumType)){
                        return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,"mets/amdSec/digiprovMD/mdRef/@CHECKSUMTYPE value isn't valid (" + metsName + ")",false,false);
                    }
                }
            }
        }
        return new ReporterDetails();
    }

    /*
    * mets/amdSec/rightsMD
    * A simple rights statement may be used to describe general permissions for
    * the package. Individual representations should state their specific rights in
    * their representation METS file. Available standards include
    * RightsStatements.org , Europeana rights statements info , METS Rights
    * Schema created and maintained by the METS Board, the rights part of
    * PREMIS as well as own local rights statements in use.
    */
    private boolean validateCSIP45() {
        boolean valid = true;
        int count = 0;
        for(AmdSecType a: amdSec){
            if(a.getRightsMD() == null){
                count++;
            }
        }
        if(count == amdSec.size()){
            valid = false;
        }
        return valid;
    }

    /*
    * mets/amdSec/rightsMD/@ID
    * An xml:id identifier for the rights metadata section ( <rightsMD> ) used for
    * internal package references. It must be unique within the package.
    */
    private ReporterDetails validateCSIP46() {
        for(AmdSecType a: amdSec){
            List<MdSecType> rigthsMD = a.getRightsMD();
            if(rigthsMD != null) {
                for (MdSecType rmd : rigthsMD) {
                    if(checkId(rmd.getID())){
                        return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,"mets/amdSec/rightsMD/@ID must be unique in the package and not null (" + metsName + ")",false,false);
                    }
                    else{
                        addId(rmd.getID());
                    }
                }
            }
        }
        return new ReporterDetails();
    }

    /*
    * mets/amdSec/rightsMD/@STATUS
    * Indicates the status of the package using a fixed vocabulary.See also:
    * dmdSec status
    */
    private ReporterDetails validateCSIP47() {
        for(AmdSecType a: amdSec){
            List<MdSecType> rigthsMD = a.getRightsMD();
            if(rigthsMD != null) {
                for (MdSecType rmd : rigthsMD) {
                    String status = rmd.getSTATUS();
                    if(status == null){
                        return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,"mets/amdSec/rightsMD/@STATUS can't be null (" + metsName + ")",false,false);
                    }
                    else{
                        if(!dmdSecStatus.contains(status)){
                            return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,"mets/amdSec/rightsMD/@STATUS value isn't valid (" + metsName + ")",false,false);
                        }
                    }
                }
            }
        }
        return new ReporterDetails();
    }

    /*
    * mets/amdSec/rightsMD/mdRef
    * Reference to the rights metadata file stored in the “metadata” section of the
    * IP.
    *
    * Rever este
    */
    private ReporterDetails validateCSIP48() {
        boolean valid = true;
        for(AmdSecType a: amdSec){
            List<MdSecType> rigthsMD = a.getRightsMD();
            if(rigthsMD != null) {
                for(MdSecType rmd: rigthsMD){
                    if(rmd.getMdRef() == null){
                        valid = false;
                        break;
                    }
                }
                if(!valid){
                    break;
                }
            }
        }
        return new ReporterDetails();
    }

    /*
    * mets/amdSec/rightsMD/mdRef[@LOCTYPE=’URL’]
    * The locator type is always used with the value “URL” from the vocabulary in
    * the attribute.
    */
    private ReporterDetails validateCSIP49() {
        for(AmdSecType a: amdSec){
            List<MdSecType> rigthsMD = a.getRightsMD();
            if(rigthsMD != null) {
                for(MdSecType rmd: rigthsMD){
                    MdSecType.MdRef mdRef = rmd.getMdRef();
                    if(mdRef != null){
                        String loctype = mdRef.getLOCTYPE();
                        if(loctype == null){
                            return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,"mets/amdSec/rightsMD/mdRef[@LOCTYPE=’URL’] can't be null (" + metsName + ")",false,false);
                        }
                        else{
                            if(!loctype.equals("URL")){
                                return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,"mets/amdSec/rightsMD/mdRef[@LOCTYPE=’URL’] value must be 'URL' (" + metsName + ")",false,false);
                            }
                        }
                    }
                }
            }
        }
        return new ReporterDetails();
    }

    /*
    * mets/amdSec/rightsMD/mdRef[@xlink:type=’simple’]
    * Attribute used with the value “simple”. Value list is maintained by the xlink
    * standard.
    */
    private ReporterDetails validateCSIP50() {
        for(AmdSecType a: amdSec){
            List<MdSecType> rigthsMD = a.getRightsMD();
            if(rigthsMD != null) {
                for(MdSecType rmd: rigthsMD){
                    MdSecType.MdRef mdRef = rmd.getMdRef();
                    if(mdRef != null){
                        String type = mdRef.getType();
                        if(type == null){
                            return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,"mets/amdSec/rightsMD/mdRef[@xlink:type=’simple’] can't be null (" + metsName + ")",false,false);
                        }
                        else{
                            if(!type.equals("simple")){
                                return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,"mets/amdSec/rightsMD/mdRef[@xlink:type=’simple’] value isn't 'simple' (" + metsName + ")",false,false);
                            }
                        }
                    }
                }
            }
        }
        return new ReporterDetails();
    }

    /*
    * mets/amdSec/rightsMD/mdRef/@xlink:href
    * The actual location of the resource. We recommend recording a URL type
    * filepath within this attribute.
    */
    private ReporterDetails validateCSIP51() throws IOException {
        for(AmdSecType a: amdSec){
            List<MdSecType> rigthsMD = a.getRightsMD();
            if(rigthsMD != null) {
                for(MdSecType rmd: rigthsMD){
                    MdSecType.MdRef mdRef = rmd.getMdRef();
                    if(mdRef != null){
                        String href = URLDecoder.decode(mdRef.getHref(),"UTF-8");
                        if(isZipFileFlag()){
                            if(!zipManager.checkPathExists(getEARKSIPpath(),href)){
                                return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,"mets/amdSec/rightsMD/mdRef/@xlink:href path doesn't exists (" + metsName + ")",false,false);
                            }
                        }
                        else{
                            if(!folderManager.checkPathExists(getEARKSIPpath(),Paths.get(href))){
                                return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,"mets/amdSec/rightsMD/mdRef/@xlink:href path doesn't exists (" + metsName + ")",false,false);
                            }
                        }
                    }
                }
            }
        }
        return new ReporterDetails();
    }

    /*
    * mets/amdSec/rightsMD/mdRef/@MDTYPE
    * Specifies the type of metadata in the referenced file. Value is taken from the
    * list provided by the METS.
    */
    private ReporterDetails validateCSIP52() {
        List<String> tmp = new ArrayList<>();
        for(MetadataType md: MetadataType.values()){
            tmp.add(md.toString());
        }
        for(AmdSecType a: amdSec){
            List<MdSecType> rigthsMD = a.getRightsMD();
            if(rigthsMD != null) {
                for(MdSecType rmd: rigthsMD){
                    MdSecType.MdRef mdRef = rmd.getMdRef();
                    if(mdRef != null){
                        String mdType = mdRef.getMDTYPE();
                        if(mdType == null){
                            return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,"mets/amdSec/rightsMD/mdRef/@MDTYPE can't be null (" + metsName + ")",false,false);
                        }
                        else{
                            if(!tmp.contains(mdType)){
                                return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,"mets/amdSec/rightsMD/mdRef/@MDTYPE value isn't valid (" + metsName + ")",false,false);
                            }
                        }
                    }
                }
            }
        }
        return new ReporterDetails();
    }

    /*
    * mets/amdSec/rightsMD/mdRef/@MIMETYPE
    * The IANA mime type for the referenced file.See also: IANA media types
    */
    private boolean validateCSIP53() {
        return false;
    }

    /*
    * mets/amdSec/rightsMD/mdRef/@SIZE
    * Size of the referenced file in bytes.
    */
    private ReporterDetails validateCSIP54() throws IOException {
        for(AmdSecType a: amdSec){
            List<MdSecType> rigthsMD = a.getRightsMD();
            if(rigthsMD != null) {
                for(MdSecType rmd: rigthsMD){
                    MdSecType.MdRef mdRef = rmd.getMdRef();
                    if(mdRef != null){
                        String href = URLDecoder.decode(mdRef.getHref(),"UTF-8");
                        Long size = mdRef.getSIZE();
                        if(size == null){
                            return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,"mets/amdSec/rightsMD/mdRef/@SIZE can't be null (" + metsName + ")",false,false);
                        }
                        else{
                            if(isZipFileFlag()){
                                if(!zipManager.verifySize(getEARKSIPpath(),href,size)){
                                    return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,"mets/amdSec/rightsMD/mdRef/@SIZE doesn't match with the size of file (" + metsName + ")",false,false);
                                }
                            }
                            else{
                                if(!folderManager.verifySize(getEARKSIPpath(),href,size)){
                                    return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,"mets/amdSec/rightsMD/mdRef/@SIZE doesn't match with the size of file (" + metsName + ")",false,false);
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
    * mets/amdSec/rightsMD/mdRef/@CREATED
    * Creation date of the referenced file.
    */
    private ReporterDetails validateCSIP55() {
        boolean valid = true;
        for(AmdSecType a: amdSec){
            List<MdSecType> rigthsMD = a.getRightsMD();
            if(rigthsMD != null) {
                for(MdSecType rmd: rigthsMD){
                    MdSecType.MdRef mdRef = rmd.getMdRef();
                    if(mdRef.getCREATED() == null){
                        return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,"mets/amdSec/rightsMD/mdRef/@CREATED can't be null (" + metsName + ")",false,false);
                    }
                }
            }
        }
        return new ReporterDetails();
    }

    /*
    * mets/amdSec/rightsMD/mdRef/@CHECKSUM
    * The checksum of the referenced file.
    */
    private ReporterDetails validateCSIP56() throws IOException, NoSuchAlgorithmException {
        List<String> tmp = new ArrayList<>();
        for(CHECKSUMTYPE check: CHECKSUMTYPE.values()){
            tmp.add(check.toString());
        }
        for(AmdSecType a: amdSec){
            List<MdSecType> rigthsMD = a.getRightsMD();
            if(rigthsMD != null) {
                for(MdSecType rmd: rigthsMD){
                    MdSecType.MdRef mdRef = rmd.getMdRef();
                    String checksumType = mdRef.getCHECKSUMTYPE();
                    if(checksumType == null){
                        return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,"mets/amdSec/rightsMD/mdRef/@CHECKSUMTYPE can't be null (" + metsName + ")",false,false);
                    }
                    else {
                        if (!tmp.contains(checksumType)) {
                            return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,"mets/amdSec/rightsMD/mdRef/@CHECKSUMTYPE value isn't valid (" + metsName + ")",false,false);
                        } else {
                            String checksum = mdRef.getCHECKSUM();
                            if (checksum == null) {
                                return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,"mets/amdSec/rightsMD/mdRef/@CHECKSUM can't be null (" + metsName + ")",false,false);
                            } else {
                                String file = URLDecoder.decode(mdRef.getHref(), "UTF-8");
                                if (isZipFileFlag()) {
                                    if (!zipManager.verifyChecksum(getEARKSIPpath(), file, checksumType, checksum)) {
                                        return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,"mets/amdSec/rightsMD/mdRef/@CHECKSUM value doesn't match with the checksum of file (" + metsName + ")",false,false);
                                    }
                                } else {
                                    if (!folderManager.verifyChecksum(getEARKSIPpath(), file, checksumType, checksum)) {
                                        return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,"mets/amdSec/rightsMD/mdRef/@CHECKSUM value doesn't match with the checksum of file (" + metsName + ")",false,false);
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
    * mets/amdSec/rightsMD/mdRef/@CHECKSUMTYPE
    * The type of checksum following the value list present in the METS-standard
    * which has been used for calculating the checksum for the referenced file.
    */
    private ReporterDetails validateCSIP57() {
        List<String> tmp = new ArrayList<>();
        for(CHECKSUMTYPE check: CHECKSUMTYPE.values()){
            tmp.add(check.toString());
        }
        for(AmdSecType a: amdSec){
            List<MdSecType> rigthsMD = a.getRightsMD();
            if(rigthsMD != null) {
                for(MdSecType rmd: rigthsMD){
                    MdSecType.MdRef mdRef = rmd.getMdRef();
                    String checksumType = mdRef.getCHECKSUMTYPE();
                    if(checksumType == null){
                        return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,"mets/amdSec/rightsMD/mdRef/@CHECKSUMTYPE can't be null (" + metsName + ")",false,false);
                    }
                    else{
                        if(!tmp.contains(checksumType)){
                            return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,"mets/amdSec/rightsMD/mdRef/@CHECKSUMTYPE value isn't valid (" + metsName + ")",false,false);
                        }
                    }
                }
            }
        }
        return new ReporterDetails();
    }


}
