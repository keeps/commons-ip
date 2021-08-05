package org.roda_project.commons_ip2.validator.component.fileComponent;

import org.roda_project.commons_ip2.validator.component.ValidatorComponentImpl;
import org.roda_project.commons_ip2.validator.constants.Constants;
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
    private Path path;

    public StructureComponentValidator(String moduleName, Path path){
        this.MODULE_NAME = moduleName;
        this.path = path;
    }

    @Override
    public void validate() throws IOException {
        ReporterDetails strCsip;

        /* CSIPSTR1 */
        validationInit(MODULE_NAME,ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIPSTR1_ID);
        strCsip = validateCSIPSTR1();
        strCsip.setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION);
        addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIPSTR1_ID,strCsip);
        if(strCsip.isValid()){
            String message = "SKIPPED because it will be validated afterwards";

            /* CSIPSTR2 */
            strCsip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,message,true, true);
            addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIPSTR2_ID,strCsip);

            /* CSIPSTR3 */
            strCsip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,message,true, true);
            addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIPSTR3_ID,strCsip);

            /* CSIPSTR4 */
            validationInit(MODULE_NAME,ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIPSTR1_ID);
            strCsip = validateCSIPSTR4();
            strCsip.setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION);
            addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIPSTR4_ID,strCsip);

            /* CSIPSTR5 */
            strCsip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,message,true, true);
            addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIPSTR5_ID,strCsip);

            /* CSIPSTR6 */
            strCsip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,message,true, true);
            addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIPSTR6_ID,strCsip);

            /* CSIPSTR7 */
            strCsip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,message,true, true);
            addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIPSTR7_ID,strCsip);

            /* CSIPSTR8 */
            strCsip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,message,true, true);
            addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIPSTR8_ID,strCsip);

            /* CSIPSTR9 */
            strCsip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,message,true, true);
            addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIPSTR9_ID,strCsip);

            /* CSIPSTR10 */
            strCsip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,message,true, true);
            addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIPSTR10_ID,strCsip);

            /* CSIPSTR11 */
            strCsip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,message,true, true);
            addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIPSTR11_ID,strCsip);

            /* CSIPSTR12 */
            strCsip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,message,true, true);
            addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIPSTR12_ID,strCsip);

            /* CSIPSTR13 */
            strCsip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,message,true, true);
            addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIPSTR13_ID,strCsip);

            /* CSIPSTR14 */
            strCsip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,message,true, true);
            addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIPSTR14_ID,strCsip);

            /* CSIPSTR15 */
            strCsip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,message,true, true);
            addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIPSTR15_ID,strCsip);

            /* CSIPSTR16 */
            strCsip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,message,true, true);
            addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIPSTR16_ID,strCsip);
        }
        else{
            String message;
            if(isZipFileFlag()){
                message = "SKIPPED because must unpack to a single root folder";
            }
            else{
                message = "Root must be a single directory";
            }

            /* CSIPSTR2 */
            strCsip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,message,true, true);
            addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIPSTR2_ID,strCsip);

            /* CSIPSTR3 */
            strCsip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,message,true, true);
            addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIPSTR3_ID,strCsip);

            /* CSIPSTR4 */
            strCsip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,message,true, true);
            addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIPSTR4_ID,strCsip);

            /* CSIPSTR5 */
            strCsip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,message,true, true);
            addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIPSTR5_ID,strCsip);

            /* CSIPSTR6 */
            strCsip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,message,true, true);
            addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIPSTR6_ID,strCsip);

            /* CSIPSTR7 */
            strCsip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,message,true, true);
            addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIPSTR7_ID,strCsip);

            /* CSIPSTR8 */
            strCsip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,message,true, true);
            addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIPSTR8_ID,strCsip);

            /* CSIPSTR9 */
            strCsip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,message,true, true);
            addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIPSTR9_ID,strCsip);

            /* CSIPSTR10 */
            strCsip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,message,true, true);
            addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIPSTR10_ID,strCsip);

            /* CSIPSTR11 */
            strCsip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,message,true, true);
            addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIPSTR11_ID,strCsip);

            /* CSIPSTR12 */
            strCsip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,message,true, true);
            addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIPSTR12_ID,strCsip);

            /* CSIPSTR13 */
            strCsip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,message,true, true);
            addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIPSTR13_ID,strCsip);

            /* CSIPSTR14 */
            strCsip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,message,true, true);
            addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIPSTR14_ID,strCsip);

            /* CSIPSTR15 */
            strCsip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,message,true, true);
            addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIPSTR15_ID,strCsip);

            /* CSIPSTR16 */
            strCsip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,message,true, true);
            addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIPSTR16_ID,strCsip);
        }
    }

    private ReporterDetails validateCSIPSTR1() throws IOException {
        ReporterDetails details = new ReporterDetails();
        if(Files.exists(path)){
            String contentType = Files.probeContentType(path);
            if(contentType.equals("application/zip")){
                setZipFileFlag(true);
                if(!zipManager.checkSingleRootFolder(path)){
                    details.setValid(false);
                    details.addIssue("MUST unpack to a single folder");
                }
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

    private ReporterDetails validateCSIPSTR4() throws IOException {
        ReporterDetails details = new ReporterDetails();
        if(isZipFileFlag()){
            if(!zipManager.checkIfExistsRootMetsFile(path)){
                details.setValid(false);
                details.addIssue("METS.xml in root folder not found; Please verify name of File or is existence.");
            }
        }
        else{
            if(!folderManager.checkIfExistsRootMetsFile(path)){
                details.setValid(false);
                details.addIssue("METS.xml in root folder not found; Please verify name of File or is existence.");
            }
        }
        return details;
    }
}
