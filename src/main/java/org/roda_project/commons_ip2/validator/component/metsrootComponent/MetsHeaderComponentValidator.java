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

        /* CSIP8 */
        validationInit(MODULE_NAME,ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP8_ID);
        validationOutcomeSkipped(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP8_ID,"");

        /* CSIP9 */
        validationInit(MODULE_NAME,ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP9_ID);
        if(!validateCSIP9()){
            validationOutcomeFailed(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP9_ID, "");
            valid = false;
        }
        else validationOutcomePassed(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP9_ID,"");

        /* CSIP10 */
        validationInit(MODULE_NAME,ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP10_ID);
        if(!validateCSIP10()){
            validationOutcomeFailed(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP10_ID, "");
            valid = false;
        }
        else validationOutcomePassed(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP10_ID,"");

        /* CSIP11 */
        validationInit(MODULE_NAME,ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP11_ID);
        if(!validateCSIP11()){
            validationOutcomeFailed(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP11_ID, "");
            valid = false;
        }
        else validationOutcomePassed(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP11_ID,"");

        /* CSIP12 */
        validationInit(MODULE_NAME,ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP12_ID);
        if(!validateCSIP12()){
            validationOutcomeFailed(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP12_ID, "");
            valid = false;
        }
        else validationOutcomePassed(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP12_ID,"");

        /* CSIP13 */
        validationInit(MODULE_NAME,ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP13_ID);
        if(!validateCSIP13()){
            validationOutcomeFailed(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP13_ID, "");
            valid = false;
        }
        else validationOutcomePassed(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP13_ID,"");

        /* CSIP14 */
        validationInit(MODULE_NAME,ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP14_ID);
        if(!validateCSIP14()){
            validationOutcomeFailed(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP14_ID, "");
            valid = false;
        }
        else validationOutcomePassed(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP14_ID,"");

        /* CSIP15 */
        validationInit(MODULE_NAME,ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP15_ID);
        if(!validateCSIP15()){
            validationOutcomeFailed(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP15_ID, "");
            valid = false;

        }
        else validationOutcomePassed(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP15_ID,"");

        /* CSIP16 */
        validationInit(MODULE_NAME,ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP16_ID);
        if(!validateCSIP16()){
            validationOutcomeFailed(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP16_ID, "");
            valid = false;
        }
        else validationOutcomePassed(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP16_ID,"");

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

    /*
     * mets/metsHdr/agent
     * A mandatory agent element records the software used to create the package.
     * Other uses of agents may be described in any local implementations that
     * extend the profile.
     */
    private boolean validateCSIP10(){
        boolean valid = true;
        if(agents == null){
            valid = false;
        }
        else{
            if(agents.size() == 0){
                valid = false;
            }
        }
        return valid;
    }

    /*
    * mets/metsHdr/agent[@ROLE=’CREATOR’]
    * The mandatory agent element MUST have a @ROLE attribute with the value “CREATOR”.
    */
    private boolean validateCSIP11(){
        boolean valid = true;
        boolean found = false;
        for(MetsType.MetsHdr.Agent a : agents){
            if (a.getROLE().equals("CREATOR") && a.getTYPE().equals("OTHER") && a.getOTHERTYPE().equals("SOFTWARE")) {
                found = true;
                break;
            }
        }
        if(!found) {
            valid = false;
        }
        return valid;
    }

    /*
    * mets/metsHdr/agent[@TYPE=’OTHER’]
    * The mandatory agent element MUST have a @TYPE attribute with the value “OTHER”.
    */

    private boolean validateCSIP12() {
        boolean valid = true;
        boolean found = false;
        for(MetsType.MetsHdr.Agent a : agents){
            if (a.getROLE().equals("CREATOR") && a.getTYPE().equals("OTHER") && a.getOTHERTYPE().equals("SOFTWARE")) {
                found = true;
                break;
            }
        }
        if(!found) {
            valid = false;
        }
        return valid;
    }

    /*
    * mets/metsHdr/agent[@OTHERTYPE=’SOFTWARE’]
    * The mandatory agent element MUST have a @OTHERTYPE attribute with the
    * value “SOFTWARE”.See also: Other agent type
    */

    private boolean validateCSIP13() {
        boolean valid = true;
        boolean found = false;
        for(MetsType.MetsHdr.Agent a : agents){
            if (a.getROLE().equals("CREATOR") && a.getTYPE().equals("OTHER") && a.getOTHERTYPE().equals("SOFTWARE")) {
                found = true;
                break;
            }
        }
        if(!found) {
            valid = false;
        }
        return valid;
    }

    /*
    * mets/metsHdr/agent/name
    * The mandatory agent’s name element records the name of the software tool
    * used to create the IP.
     */

    private boolean validateCSIP14() {
        boolean valid = true;
        for(MetsType.MetsHdr.Agent a : agents){
            if (a.getROLE().equals("CREATOR") && a.getTYPE().equals("OTHER") && a.getOTHERTYPE().equals("SOFTWARE")) {
                if(a.getName() == null) {
                    valid = false;
                    break;
                }
                else{
                    if(a.getName().equals("")){
                        valid = false;
                        break;
                    }
                }
            }
        }
        return valid;
    }

    /*
    * mets/metsHdr/agent/note
    * The mandatory agent’s note element records the version of the tool used to
    * create the IP.
    */

    private boolean validateCSIP15() {
        boolean valid = true;
        for(MetsType.MetsHdr.Agent a : agents){
            if (a.getROLE().equals("CREATOR") && a.getTYPE().equals("OTHER") && a.getOTHERTYPE().equals("SOFTWARE")) {
                List<MetsType.MetsHdr.Agent.Note> notes = a.getNote();
                if(notes == null || notes.size() == 0){
                    valid = false;
                    break;
                }
            }
        }
        return valid;
    }

    /*
    * mets/metsHdr/agent/note[@csip:NOTETYPE=’SOFTWARE VERSION’]
    * The mandatory agent element’s note child has a @csip:NOTETYPE attribute
    * with a fixed value of “SOFTWARE VERSION”.See also: Note type
    */
    private boolean validateCSIP16() {
        boolean valid = true;
        for(MetsType.MetsHdr.Agent a : agents){
            if (a.getROLE().equals("CREATOR") && a.getTYPE().equals("OTHER") && a.getOTHERTYPE().equals("SOFTWARE")) {
                List<MetsType.MetsHdr.Agent.Note> notes = a.getNote();
                if(notes == null || notes.size() == 0){
                    valid = false;
                }
                else{
                    for(MetsType.MetsHdr.Agent.Note note : notes){
                        if(note.getNOTETYPE() == null){
                            valid = false;
                        }
                        else{
                            if(!note.getNOTETYPE().equals("SOFTWARE VERSION")) {
                                valid = false;
                            }
                        }
                    }
                }
            }
        }
        return valid;
    }

}
