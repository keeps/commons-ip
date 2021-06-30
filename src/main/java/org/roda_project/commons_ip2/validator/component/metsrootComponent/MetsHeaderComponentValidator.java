package org.roda_project.commons_ip2.validator.component.metsrootComponent;

import org.roda_project.commons_ip2.mets_v1_12.beans.Mets;
import org.roda_project.commons_ip2.mets_v1_12.beans.MetsType;
import org.roda_project.commons_ip2.validator.common.ControlledVocabularyParser;
import org.roda_project.commons_ip2.validator.component.ValidatorComponentImpl;
import org.roda_project.commons_ip2.validator.constants.Constants;
import org.roda_project.commons_ip2.validator.constants.ConstantsCSIPspec;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.datatype.XMLGregorianCalendar;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author João Gomes <jgomes@keep.pt>
 */
public class MetsHeaderComponentValidator extends ValidatorComponentImpl {
    private static final Logger LOGGER = LoggerFactory.getLogger(MetsHeaderComponentValidator.class);

    private final String MODULE_NAME;
    private List<String> oaisPackageTypes;
    private MetsType.MetsHdr metsHdr;
    private List<MetsType.MetsHdr.Agent> agents;

    public void setOaisPackageTypes(List<String> oaisPackageTypes) {
        this.oaisPackageTypes = new ArrayList<>(oaisPackageTypes);
    }
    public MetsHeaderComponentValidator(String module_name) {
        MODULE_NAME = module_name;
        oaisPackageTypes = new ArrayList<>();
        ControlledVocabularyParser controlledVocabularyParser = new ControlledVocabularyParser(Constants.PATH_RESOURCES_CSIP_VOCABULARY_OAIS_PACKAGE_TYPE,oaisPackageTypes);
        controlledVocabularyParser.parse();
        setOaisPackageTypes(controlledVocabularyParser.getData());
    }

    @Override
    public boolean validate() throws IOException {
        boolean valid = true;
        /* CSIP117 */
        metsHdr = mets.getMetsHdr();
        agents = metsHdr.getAgent();
        validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP117_ID);
        if(!validateCSIP117()){
            validationOutcomeFailed(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP117_ID, "");
            valid = false;
        }
        else validationOutcomePassed(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP117_ID,"");

        /* CSIP7 */
        validationInit(MODULE_NAME,ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP7_ID);
        if(!validateCSIP7()){
            validationOutcomeFailed(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP7_ID, "");
            valid = false;
        }
        else validationOutcomePassed(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP7_ID,"");

        /* CSIP9 */
        validationInit(MODULE_NAME,ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP9_ID);
        if(!validateCSIP9()){
            validationOutcomeFailed(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP9_ID, "");
            valid = false;
        }
        else validationOutcomePassed(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP9_ID,"");

        observer.notifyFinishModule(MODULE_NAME);
        return valid;
    }

    /*
     * mets/metsHdr
     * General element for describing the package.
     */
    private boolean validateCSIP117(){
        boolean valid = true;
        if(metsHdr == null){
            valid = false;
        }
        return valid;
    }

    /*
     * mets/metsHdr/@CREATEDATE
     * mets/metsHdr/@CREATEDATE records the date the package was created.
     */
    private boolean validateCSIP7(){
        boolean valid = true;
        XMLGregorianCalendar createDate = metsHdr.getCREATEDATE();
        if(createDate == null){
            valid = false;
        }
        return valid;
    }

    /*
     * mets/metsHdr/@csip:OAISPACKAGETYPE
     * mets/metsHdr/@csip:OAISPACKAGETYPE is an additional CSIP attribute
     * that declares the type of the IP.See also: OAIS Package type
     */
    private boolean validateCSIP9(){
        boolean valid = true;
        String oaisPackageType = metsHdr.getOAISPACKAGETYPE();
        if(oaisPackageType == null || oaisPackageType.equals("")){
            valid = false;
        }
        else{
            if(!oaisPackageTypes.contains(oaisPackageType)){
                valid = false;
            }
        }
        return valid;
    }

}
