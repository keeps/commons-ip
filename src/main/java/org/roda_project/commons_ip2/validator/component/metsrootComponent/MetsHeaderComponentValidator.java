package org.roda_project.commons_ip2.validator.component.metsrootComponent;

import org.roda_project.commons_ip2.mets_v1_12.beans.Mets;
import org.roda_project.commons_ip2.mets_v1_12.beans.MetsType;
import org.roda_project.commons_ip2.validator.common.ControlledVocabularyParser;
import org.roda_project.commons_ip2.validator.component.ValidatorComponentImpl;
import org.roda_project.commons_ip2.validator.constants.Constants;
import org.roda_project.commons_ip2.validator.constants.ConstantsCSIPspec;
import org.roda_project.commons_ip2.validator.reporter.ReporterDetails;
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
        metsHdr = mets.getMetsHdr();
        agents = metsHdr.getAgent();
        ReporterDetails csip;

        /* CSIP117 */
        validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP117_ID);
        csip = validateCSIP117();
        if(csip.isValid()){
            validationOutcomePassed(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP117_ID, csip.getMessage());

            /* CSIP7 */
            validationInit(MODULE_NAME,ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP7_ID);
            csip = validateCSIP7();
            if(csip.isValid()) {
                validationOutcomePassed(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP7_ID, csip.getMessage());
            }
            else{
                validationOutcomeFailed(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP7_ID, csip.getMessage());
                valid = false;
            }

            /* CSIP8 */
            validationInit(MODULE_NAME,ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP8_ID);
            validationOutcomeSkipped(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP8_ID,"");

            /* CSIP9 */
            validationInit(MODULE_NAME,ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP9_ID);
            csip = validateCSIP9();
            if(csip.isValid()){
                validationOutcomePassed(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP9_ID,csip.getMessage());
            }
            else{
                validationOutcomeFailed(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP9_ID, csip.getMessage());
                valid = false;
            }

            /* CSIP10 */
            validationInit(MODULE_NAME,ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP10_ID);
            csip = validateCSIP10();
            if(csip.isValid()){
                validationOutcomePassed(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP10_ID,csip.getMessage());

                /* CSIP11 */
                validationInit(MODULE_NAME,ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP11_ID);
                csip = validateCSIP11();
                if(csip.isValid()){
                    validationOutcomePassed(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP11_ID,csip.getMessage());
                }
                else{
                    validationOutcomeFailed(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP11_ID, csip.getMessage());
                    valid = false;
                }

                /* CSIP12 */
                validationInit(MODULE_NAME,ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP12_ID);
                csip = validateCSIP12();
                if(csip.isValid()){
                    validationOutcomePassed(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP12_ID,csip.getMessage());
                }
                else{
                    validationOutcomeFailed(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP12_ID, csip.getMessage());
                    valid = false;
                }

                /* CSIP13 */
                validationInit(MODULE_NAME,ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP13_ID);
                csip = validateCSIP13();
                if(csip.isValid()){
                    validationOutcomePassed(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP13_ID, csip.getMessage());
                }
                else{
                    validationOutcomeFailed(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP13_ID, csip.getMessage());
                    valid = false;
                }

                /* CSIP14 */
                validationInit(MODULE_NAME,ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP14_ID);
                csip = validateCSIP14();
                if(csip.isValid()){
                    validationOutcomePassed(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP14_ID,csip.getMessage());
                }
                else{
                    validationOutcomeFailed(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP14_ID,csip.getMessage());
                    valid = false;
                }

                /* CSIP15 */
                validationInit(MODULE_NAME,ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP15_ID);
                csip = validateCSIP15();
                if(csip.isValid()){
                    validationOutcomePassed(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP15_ID,csip.getMessage());
                }
                else{
                    validationOutcomeFailed(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP15_ID, csip.getMessage());
                    valid = false;
                }

                /* CSIP16 */
                validationInit(MODULE_NAME,ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP16_ID);
                csip = validateCSIP16();
                if(csip.isValid()){
                    validationOutcomePassed(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP16_ID,csip.getMessage());
                }
                else{
                    validationOutcomeFailed(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP16_ID, csip.getMessage());
                    valid = false;
                }
            }
            else {
                 validationOutcomeFailed(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP10_ID, csip.getMessage());
                valid = false;
            }

        }
        else{
            validationOutcomeFailed(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP117_ID, csip.getMessage());
            valid = false;
        }

        observer.notifyFinishModule(MODULE_NAME);
        return valid;
    }

    /*
     * mets/metsHdr
     * General element for describing the package.
     */
    private ReporterDetails validateCSIP117(){
        ReporterDetails details = new ReporterDetails();
        if(metsHdr == null){
            details.setValid(false);
            details.setMessage("mets/metsHdr can't be null!");
        }
        return details;
    }

    /*
     * mets/metsHdr/@CREATEDATE
     * mets/metsHdr/@CREATEDATE records the date the package was created.
     */
    private ReporterDetails validateCSIP7(){
        ReporterDetails details = new ReporterDetails();
        XMLGregorianCalendar createDate = metsHdr.getCREATEDATE();
        if(createDate == null){
            details.setValid(false);
            details.setMessage("mets/metsHdr/@CREATEDATE can't be null!");
        }
        return details;
    }

    /*
     * mets/metsHdr/@csip:OAISPACKAGETYPE
     * mets/metsHdr/@csip:OAISPACKAGETYPE is an additional CSIP attribute
     * that declares the type of the IP.See also: OAIS Package type
     */
    private ReporterDetails validateCSIP9(){
        ReporterDetails details = new ReporterDetails();
        String oaisPackageType = metsHdr.getOAISPACKAGETYPE();
        if(oaisPackageType == null || oaisPackageType.equals("")){
            details.setValid(false);
            details.setMessage("mets/metsHdr/@csip:OAISPACKAGETYPE can't be null or empty!");
        }
        else{
            if(!oaisPackageTypes.contains(oaisPackageType)){
                details.setValid(false);
                details.setMessage("mets/metsHdr/@csip:OAISPACKAGETYPE must have a value from OAIS Package type! ");
            }
        }
        return details;
    }

    /*
     * mets/metsHdr/agent
     * A mandatory agent element records the software used to create the package.
     * Other uses of agents may be described in any local implementations that
     * extend the profile.
     */
    private ReporterDetails validateCSIP10(){
        ReporterDetails details = new ReporterDetails();
        if(agents == null){
            details.setValid(false);
            details.setMessage("Must have at least one mets/metsHdr/agent!");
        }
        else{
            if(agents.size() == 0){
                details.setValid(false);
                details.setMessage("Must have at least one mets/metsHdr/agent!");
            }
        }
        return details;
    }

    /*
    * mets/metsHdr/agent[@ROLE=’CREATOR’]
    * The mandatory agent element MUST have a @ROLE attribute with the value “CREATOR”.
    */
    private ReporterDetails validateCSIP11(){
        ReporterDetails details = new ReporterDetails();
        boolean found = false;
        for(MetsType.MetsHdr.Agent a : agents){
            String role = a.getROLE();
            String type = a.getTYPE();
            String otherType = a.getOTHERTYPE();
            if(role == null || type == null || otherType == null){
                break;
            }
            else{
                if (role.equals("CREATOR") && type.equals("OTHER") && otherType.equals("SOFTWARE")) {
                    found = true;
                    break;
                }
            }

        }
        if(!found) {
            details.setValid(false);
            details.setMessage("Must have a mets/metsHdr/agent[@ROLE='CREATOR'] with the value CREATOR");
        }
        return details;
    }

    /*
    * mets/metsHdr/agent[@TYPE=’OTHER’]
    * The mandatory agent element MUST have a @TYPE attribute with the value “OTHER”.
    */

    private ReporterDetails validateCSIP12() {
        ReporterDetails details = new ReporterDetails();
        boolean found = false;
        for(MetsType.MetsHdr.Agent a : agents){
            String role = a.getROLE();
            String type = a.getTYPE();
            String otherType = a.getOTHERTYPE();
            if(role == null || type == null || otherType == null){
                break;
            }
            else {
                if (role.equals("CREATOR") && type.equals("OTHER") && otherType.equals("SOFTWARE")) {
                    found = true;
                    break;
                }
            }
        }
        if(!found) {
            details.setValid(false);
            details.setMessage("Must have a mets/metsHdr/agent[@TYPE='OTHER'] with the value OTHER");
        }
        return details;
    }

    /*
    * mets/metsHdr/agent[@OTHERTYPE=’SOFTWARE’]
    * The mandatory agent element MUST have a @OTHERTYPE attribute with the
    * value “SOFTWARE”.See also: Other agent type
    */

    private ReporterDetails validateCSIP13() {
        ReporterDetails details = new ReporterDetails();
        boolean found = false;
        for(MetsType.MetsHdr.Agent a : agents){
            String role = a.getROLE();
            String type = a.getTYPE();
            String otherType = a.getOTHERTYPE();
            if(role == null || type == null || otherType == null){
                break;
            }
            else {
                if (role.equals("CREATOR") && type.equals("OTHER") && otherType.equals("SOFTWARE")) {
                    found = true;
                    break;
                }
            }
        }
        if(!found) {
            details.setValid(false);
            details.setMessage("Must have a mets/metsHdr/agent[@OTHERTYPE=’SOFTWARE’] with the value Software\"");
        }
        return details;
    }

    /*
    * mets/metsHdr/agent/name
    * The mandatory agent’s name element records the name of the software tool
    * used to create the IP.
     */

    private ReporterDetails validateCSIP14() {
        ReporterDetails details = new ReporterDetails();
        for(MetsType.MetsHdr.Agent a : agents){
            String role = a.getROLE();
            String type = a.getTYPE();
            String otherType = a.getOTHERTYPE();
            if(role == null || type == null || otherType == null){
                break;
            }
            else {
                if (role.equals("CREATOR") && type.equals("OTHER") && otherType.equals("SOFTWARE")) {
                    if (a.getName() == null) {
                        details.setValid(false);
                        details.setMessage("mets/metsHdr/agent/name can't be null!");
                    } else {
                        if (a.getName().equals("")) {
                            details.setValid(false);
                            details.setMessage("mets/metsHdr/agent/name can't be empty!");
                        }
                    }
                }
            }
        }
        return details;
    }

    /*
    * mets/metsHdr/agent/note
    * The mandatory agent’s note element records the version of the tool used to
    * create the IP.
    */

    private ReporterDetails validateCSIP15() {
        ReporterDetails details = new ReporterDetails();
        for(MetsType.MetsHdr.Agent a : agents){
            String role = a.getROLE();
            String type = a.getTYPE();
            String otherType = a.getOTHERTYPE();
            if(role == null || type == null || otherType == null){
                break;
            }
            else {
                if (role.equals("CREATOR") && type.equals("OTHER") && otherType.equals("SOFTWARE")) {
                    List<MetsType.MetsHdr.Agent.Note> notes = a.getNote();
                    if (notes == null || notes.size() == 0) {
                        details.setValid(false);
                        details.setMessage("mets/metsHdr/agent/note can't be null");
                    }
                }
            }
        }
        return details;
    }

    /*
    * mets/metsHdr/agent/note[@csip:NOTETYPE=’SOFTWARE VERSION’]
    * The mandatory agent element’s note child has a @csip:NOTETYPE attribute
    * with a fixed value of “SOFTWARE VERSION”.See also: Note type
    */
    private ReporterDetails validateCSIP16() {
        ReporterDetails details = new ReporterDetails();
        for(MetsType.MetsHdr.Agent a : agents){
            String role = a.getROLE();
            String type = a.getTYPE();
            String otherType = a.getOTHERTYPE();
            if(role == null || type == null || otherType == null){
                break;
            }
            else {
                if (role.equals("CREATOR") && type.equals("OTHER") && otherType.equals("SOFTWARE")) {
                    List<MetsType.MetsHdr.Agent.Note> notes = a.getNote();
                    if (notes == null || notes.size() == 0) {
                        details.setValid(false);
                        details.setMessage("mets/metsHdr/agent/note can't be null");
                    } else {
                        for (MetsType.MetsHdr.Agent.Note note : notes) {
                            if (note.getNOTETYPE() == null) {
                                details.setValid(false);
                                details.setMessage("mets/metsHdr/agent/note[@csip:NOTETYPE=’SOFTWARE VERSION’] can't be null");
                            } else {
                                if (!note.getNOTETYPE().equals("SOFTWARE VERSION")) {
                                    details.setValid(false);
                                    details.setMessage("mets/metsHdr/agent/note[@csip:NOTETYPE=’SOFTWARE VERSION’] value must be SOFTWARE VERSION");
                                }
                            }
                        }
                    }
                }
            }
        }
        return details;
    }

}
