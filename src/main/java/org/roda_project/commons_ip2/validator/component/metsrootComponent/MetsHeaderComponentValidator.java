package org.roda_project.commons_ip2.validator.component.metsrootComponent;

import org.roda_project.commons_ip2.validator.common.ControlledVocabularyParser;
import org.roda_project.commons_ip2.validator.component.ValidatorComponentImpl;
import org.roda_project.commons_ip2.validator.constants.Constants;
import org.roda_project.commons_ip2.validator.constants.ConstantsCSIPspec;
import org.roda_project.commons_ip2.validator.handlers.MetsComponentHandler;
import org.roda_project.commons_ip2.validator.handlers.MetsHdrAgentComponentHandler;
import org.roda_project.commons_ip2.validator.metsHelpers.Agent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author João Gomes <jgomes@keep.pt>
 */

public class MetsHeaderComponentValidator extends ValidatorComponentImpl {
    private static final Logger LOGGER = LoggerFactory.getLogger(MetsHeaderComponentValidator.class);

    private final String MODULE_NAME;
    private HashMap<String,String> metsHdr;
    private List<String> oaisPackageTypes;

    private List<Agent> metsHdrAgent;

    public void setOaisPackageTypes(List<String> oaisPackageTypes) {
        this.oaisPackageTypes = new ArrayList<>(oaisPackageTypes);
    }

    public MetsHeaderComponentValidator(String module_name) {
        MODULE_NAME = module_name;
        this.metsHdr = new HashMap<>();
        this.metsHdrAgent = new ArrayList<>();

        oaisPackageTypes = new ArrayList<>();
        ControlledVocabularyParser controlledVocabularyParser = new ControlledVocabularyParser(Constants.PATH_RESOURCES_CSIP_VOCABULARY_OAIS_PACKAGE_TYPE,oaisPackageTypes);
        controlledVocabularyParser.parse();
        setOaisPackageTypes(controlledVocabularyParser.getData());
    }

    @Override
    public boolean validate() throws SAXException, ParserConfigurationException, IOException {
        boolean valid = true;
        /* Parse metsHdr element in METS.xml  */
        MetsComponentHandler handlerMetsHdr = new MetsComponentHandler("metsHdr", metsHdr);
        InputStream stream = zipManager.getMetsRootInputStream(path);
        getSAXParser().parse(stream, handlerMetsHdr);

        InputStream streamMetsHdrAgent = zipManager.getMetsRootInputStream(path);
        MetsHdrAgentComponentHandler handlerMetsHdrAgent = new MetsHdrAgentComponentHandler(metsHdrAgent);
        getSAXParser().parse(streamMetsHdrAgent, handlerMetsHdrAgent);

        /* CSIP117 */
        validationInit(MODULE_NAME,ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP117_ID);
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

        /* CSIP8 */
        validationInit(MODULE_NAME,ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP8_ID);
        if(!validateCSIP8()){
            validationOutcomeFailed(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP8_ID, "");
            valid = false;
        }
        else validationOutcomePassed(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP8_ID,"");

        /* CSIP9 */
        validationInit(MODULE_NAME,ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP9_ID);
        if(!validateCSIP9()){
            validationOutcomeFailed(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP9_ID, "");
            valid = false;
        }
        else validationOutcomePassed(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP9_ID,"");

        /* CSIP10 */
        if(!validateCSIP10()){
            valid = false;
        }
        /* CSIP11 */

        /* CSIP12 */

        /* CSIP13 */

        /* CSIP14 */

        /* CSIP15 */

        /* CSIP16 */

        observer.notifyFinishModule(MODULE_NAME);
        return valid;
    }

    /*
    * mets/metsHdr
    * General element for describing the package.
    */
    private boolean validateCSIP117(){
        boolean valid = true;
        if(metsHdr.size() < 0){
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
        String createDate = metsHdr.get("CREATEDATE");
        if(createDate == null || createDate.equals("")){
            valid = false;
        }
        return valid;
    }

    /*
    * mets/metsHdr/@LASTMODDATE
    * mets/metsHdr/@LASTMODDATE is mandatory when the package has been
    * modified.
    */
    private boolean validateCSIP8(){
        boolean valid = true;
        String lastModDate = metsHdr.get("LASTMODDATE");
        if(lastModDate == null || lastModDate.equals("")){
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
        String oaisPackageType = metsHdr.get("csip:OAISPACKAGETYPE");
        if(oaisPackageType == null || oaisPackageType.equals("")){
            valid = false;
        }
        if(!oaisPackageTypes.contains(oaisPackageType)){
            valid = false;
        }
        return valid;
    }

    /*
    * mets/metsHdr/agent
    * A mandatory agent element records the software used to create the package.
    * Other uses of agents may be described in any local implementations that
    * extend the profile.
    */
    private boolean validateCSIP10(){
        boolean valid = true;
        for(Agent a: metsHdrAgent){
            System.out.println(a.toString());
        }
        return valid;
    }
}
