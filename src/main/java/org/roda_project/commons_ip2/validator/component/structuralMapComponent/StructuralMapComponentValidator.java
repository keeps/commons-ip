package org.roda_project.commons_ip2.validator.component.structuralMapComponent;

import org.roda_project.commons_ip2.mets_v1_12.beans.DivType;
import org.roda_project.commons_ip2.mets_v1_12.beans.StructMapType;
import org.roda_project.commons_ip2.validator.component.ValidatorComponentImpl;
import org.roda_project.commons_ip2.validator.constants.Constants;
import org.roda_project.commons_ip2.validator.constants.ConstantsCSIPspec;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
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

    /*
    * mets/structMap
    * The structural map <structMap> element is the only mandatory element in
    * the METS. The <structMap> in the CSIP describes the highest logical
    * structure of the IP. Each METS file must include ONE structural map
    * <structMap> element used exactly as described here. Institutions can add
    * their own additional custom structural maps as separate <structMap>
    * sections.
    */
    private boolean validateCSIP80() {
        boolean valid = true;
        List<StructMapType> structMap = mets.getStructMap();
        if(structMap == null){
            valid = false;
        }
        else{
            if(structMap.size() == 0){
                valid = false;
            }
        }
        return valid;
    }

    /*
     * mets/structMap[@TYPE='PHYSICAL']
     * The mets/structMap/@TYPE attribute must take the value “PHYSICAL” from the vocabulary.
     * See also: Structural map typing
    */
    private boolean validateCSIP81() {
        boolean valid = true;
        List<StructMapType> structMap = mets.getStructMap();
        if(structMap != null){
            for(StructMapType struct : structMap){
                String type = struct.getTYPE();
                if(type == null){
                    valid = false;
                    break;
                }
                else {
                    if (!type.equals("PHYSICAL")) {
                        valid = false;
                         break;
                    }
                }
            }
        }
        return valid;
    }

    /*
    * mets/structMap[@LABEL='CSIP']
    * The mets/structMap/@LABEL attribute value is set to “CSIP” from the vocabulary.
    * See also: Structural map label
    */
    private boolean validateCSIP82() {
        boolean valid = true;
        List<StructMapType> structMap = mets.getStructMap();
        if(structMap != null){
            for(StructMapType struct : structMap){
                String label = struct.getLABEL();
                if(label == null){
                    valid = false;
                    break;
                }
                else{
                    if(!label.equals("CSIP")){
                        valid = false;
                        break;
                    }
                }
            }
        }
        return valid;
    }

    /*
    * mets/structMap[@LABEL='CSIP']/@ID
    * An xml:id identifier for the structural description (structMap) used for internal package references. It must be unique within the package.
    */
    private boolean validateCSIP83() {
        boolean valid = true;
        List<StructMapType> structMap = mets.getStructMap();
        if(structMap != null){
            for(StructMapType struct : structMap){
                String id = struct.getID();
                if(id == null){
                    valid = false;
                    break;
                }
                else{
                    if(checkId(id)){
                        valid = false;
                        break;
                    }
                    else{
                        addId(id);
                    }
                }
            }
        }
        return valid;
    }

    /*
    * mets/structMap[@LABEL='CSIP']/div
    * The structural map comprises a single division.
    */
    private boolean validateCSIP84() {
        boolean valid = true;
        List<StructMapType> structMap = mets.getStructMap();
        if(structMap != null){
            for(StructMapType struct : structMap){
                DivType div = struct.getDiv();
                if(div == null){
                    valid = false;
                    break;
                }
            }
        }
        return valid;
    }

    /*
    * mets/structMap[@LABEL='CSIP']/div/@ID
    * Mandatory, xml:id identifier must be unique within the package.
    */
    private boolean validateCSIP85() {
        boolean valid = true;
        List<StructMapType> structMap = mets.getStructMap();
        if(structMap != null){
            for(StructMapType struct : structMap){
                DivType div = struct.getDiv();
                if(div != null){
                    String id = div.getID();
                    if(id == null){
                        valid = false;
                        break;
                    }
                    else{
                        if(checkId(id)){
                            valid = false;
                            break;
                        }
                        else{
                            addId(id);
                        }
                    }
                }
            }
        }
        return valid;
    }

    /*
    * mets/structMap[@LABEL='CSIP']/div/@LABEL
    * The package’s top-level structural division <div> element’s @LABEL attribute value must be identical to the package identifier, i.e. the same value as the mets/@OBJID attribute.
    */
    private boolean validateCSIP86() {
        boolean valid = true;
        List<StructMapType> structMap = mets.getStructMap();
        String objid = mets.getOBJID();
        if(structMap != null){
            for(StructMapType struct : structMap){
                DivType div = struct.getDiv();
                if(div != null){
                    String label = div.getLABEL();
                    if(!label.equals(objid)){
                        valid = false;
                        break;
                    }
                }
            }
        }
        return valid;
    }

    /*
    * mets/structMap[@LABEL='CSIP']/div/div[@LABEL='Metadata']
    * The metadata referenced in the administrative and/or descriptive metadata section is described in the structural map with one sub division.
    * When the transfer consists of only administrative and/or descriptive metadata this is the only sub division that occurs.
    */
    private boolean validateCSIP88() {
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
            valid = false;
        }
        return valid;
    }

    /*
    * Metadata division identifier
    * mets/structMap[@LABEL='CSIP']/div/div[@LABEL='Metadata']/@ID
    * Mandatory, xml:id identifier must be unique within the package.
    */
    private boolean validateCSIP89() {
        boolean valid = true;
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
                                valid = false;
                                break;
                            }
                            else{
                                if(checkId(id)){
                                    valid = false;
                                    break;
                                }
                                else{
                                    addId(id);
                                }
                            }
                        }
                    }
                    if(!valid){
                        break;
                    }
                }
            }
        }
        return valid;
    }

    /*
    * mets/structMap[@LABEL='CSIP']/div/div[@LABEL='Metadata']
    * The metadata division <div> element’s @LABEL attribute value must be “Metadata”.
    * See also: File group names
    */
    private boolean validateCSIP90() {
        return false;
    }

    /*
    * mets/structMap[@LABEL='CSIP']/div/div[@LABEL='Metadata']/@ADMID
    * When there is administrative metadata and the amdSec is present, all administrative metadata MUST be referenced via the administrative sections different identifiers.
    * All of the <amdSec> identifiers are listed in a single @ADMID using spaces as delimiters.
    */
    private boolean validateCSIP91() {
        return false;
    }

    /*
    * mets/structMap[@LABEL='CSIP']/div/div[@LABEL='Metadata']/@ADMID
    * When there is administrative metadata and the amdSec is present, all administrative metadata MUST be referenced via the administrative sections different identifiers.
    * All of the <amdSec> identifiers are listed in a single @ADMID using spaces as delimiters.
    */
    private boolean validateCSIP92() {
        return false;
    }

    /*
    * mets/structMap[@LABEL='CSIP']/div/div[@LABEL='Documentation']
    * The documentation referenced in the file section file groups is described in the structural map with one sub division.
    */
    private boolean validateCSIP93() {
        return false;
    }

    /*
    * mets/structMap[@LABEL='CSIP']/div/div[@LABEL='Documentation']/@ID
    * Mandatory, xml:id identifier must be unique within the package.
    */
    private boolean validateCSIP94() {
        boolean valid = true;
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
                                valid = false;
                                break;
                            }
                            else{
                                if(checkId(id)){
                                    valid = false;
                                    break;
                                }
                                else{
                                    addId(id);
                                }
                            }
                        }
                    }
                    if(!valid){
                        break;
                    }
                }
            }
        }
        return valid;
    }

    /*
    * mets/structMap[@LABEL='CSIP']/div/div[@LABEL='Documentation']
    * The documentation division <div> element in the package uses the value “Documentation” from the vocabulary as the value for the @LABEL attribute.
    * See also: File group names
    */
    private boolean validateCSIP95() {
        return false;
    }

    /*
    * mets/structMap[@LABEL='CSIP']/div/div[@LABEL='Documentation']/fptr
    * All file groups containing documentation described in the package are referenced via the relevant file group identifiers. There MUST be one file group reference per <fptr> element.
    */
    private boolean validateCSIP96() {
        return false;
    }

    /*
    * mets/structMap[@LABEL='CSIP']/div/div[@LABEL='Documentation']/fptr/@FILEID
    * A reference, by ID, to the “Documentation” file group.
    * Related to the requirements CSIP60 which describes the “Documentation” file group and CSIP65 which describes the file group identifier.
    */
    private boolean validateCSIP116() {
        return false;
    }

    /*
    * mets/structMap[@LABEL='CSIP']/div/div[@LABEL='Schemas']
    * The schemas referenced in the file section file groups are described in the structural map within a single sub-division.
    */
    private boolean validateCSIP97() {
        return false;
    }

    /*
    * mets/structMap[@LABEL='CSIP']/div/div[@LABEL='Schemas']/@ID
    * Mandatory, xml:id identifier must be unique within the package.
    */
    private boolean validateCSIP98() {
        boolean valid = true;
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
                                valid = false;
                                break;
                            }
                            else{
                                if(checkId(id)){
                                    valid = false;
                                    break;
                                }
                                else{
                                    addId(id);
                                }
                            }
                        }
                    }
                    if(!valid){
                        break;
                    }
                }
            }
        }
        return valid;
    }

    /*
    * mets/structMap[@LABEL='CSIP']/div/div[@LABEL='Schemas']
    * The schema division <div> element’s @LABEL attribute has the value “Schemas” from the vocabulary.
    * See also: File group names
    */
    private boolean validateCSIP99() {
        return false;
    }

    /*
    * mets/structMap[@LABEL='CSIP']/div/div[@LABEL='Schemas']/fptr
    * All file groups containing schemas described in the package are referenced via the relevant file group identifiers. One file group reference per fptr-element
    */
    private boolean validateCSIP100() {
        return false;
    }

    /*
    * mets/structMap[@LABEL='CSIP']/div/div[@LABEL='Schemas']/fptr/@FILEID
    * The pointer to the identifier for the “Schema” file group.
    * Related to the requirements CSIP113 which describes the “Schema” file group and CSIP65 which describes the file group identifier.
    */
    private boolean validateCSIP101() {
        return false;
    }

    /*
    * mets/structMap[@LABEL='CSIP']/div/div[@LABEL='Representations']/@ID
    * Mandatory, xml:id identifier must be unique within the package.
    */
    private boolean validateCSIP102() {
        boolean valid = true;
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
                                valid = false;
                                break;
                            }
                            else{
                                if(checkId(id)){
                                    valid = false;
                                    break;
                                }
                                else{
                                    addId(id);
                                }
                            }
                        }
                    }
                    if(!valid){
                        break;
                    }
                }
            }
        }
        return valid;
    }

    /*
    * mets/structMap[@LABEL='CSIP']/div/div[@LABEL='Representations']
    * The package’s content division <div> element must have the @LABEL attribute value “Representations”, taken from the vocabulary.
    * See also: File group names
    */
    private boolean validateCSIP103() {
        return false;
    }


    /*
    * mets/structMap[@LABEL='CSIP']/div/div[@LABEL='Representations']/fptr
    * All file groups containing content described in the package are referenced via the relevant file group identifiers. One file group reference per fptr-element.
    */
    private boolean validateCSIP104() {
        return false;
    }

    /*
    * mets/structMap[@LABEL='CSIP']/div/div[@LABEL='Representations']/fptr/@FILEID
    * The pointer to the identifier for the “Representations” file group.
    * Related to the requirements CSIP114 which describes the “Representations” file group and CSIP65 which describes the file group identifier.
    */
    private boolean validateCSIP119() {
        return false;
    }

    /*
    * mets/structMap[@LABEL='CSIP']/div/div
    * When a package consists of multiple representations, each described by a representation level METS.xml document, there is a discrete representation div element for each representation.
    * Each representation div references the representation level METS.xml document, documenting the structure of the package and its constituent representations.
    */
    private boolean validateCSIP105() {
        return false;
    }

    /*
    * mets/structMap[@LABEL='CSIP']/div/div/@ID
    * Mandatory, xml:id identifier must be unique within the package.
    */
    private boolean validateCSIP106() {
        boolean valid = true;
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
                        if(!validation.contains(d.getLABEL())){
                            String id = d.getID();
                            if(id == null){
                                valid = false;
                                break;
                            }
                            else{
                                if(checkId(id)){
                                    valid = false;
                                    break;
                                }
                                else{
                                    addId(id);
                                }
                            }
                        }
                    }
                    if(!valid){
                        break;
                    }
                }
            }
        }
        return valid;
    }

    /*
    * mets/structMap[@LABEL='CSIP']/div/div/@LABEL
    * The package’s representation division <div> element @LABEL attribute value must be the path to the representation level METS document.
    * This requirement gives the same value to be used as the requirement named “File group identifier” (CSIP64)
    * See also: File group names
    */
    private boolean validateCSIP107() {
        return false;
    }

    /*
    * mets/structMap[@LABEL='CSIP']/div/div/mptr/@xlink:title
    * The file group containing the files described in the package are referenced via the relevant file group identifier.
    * Related to the requirements CSIP114 which describes the “Representations” file group and CSIP65 which describes the file group identifier.
    */
    private boolean validateCSIP108() {
        return false;
    }

    /*
    * mets/structMap[@LABEL='CSIP']/div/div/mptr
    * The division <div> of the specific representation includes one occurrence of the METS pointer <mptr> element, pointing to the appropriate representation METS file.
    */
    private boolean validateCSIP109() {
        return false;
    }

    /*
    * mets/structMap/div/div/mptr/@xlink:href
    * The actual location of the resource. We recommend recording a URL type filepath within this attribute.
    */
    private boolean validateCSIP110() {
        return false;
    }

    /*
    * mets/structMap/div/div/mptr[@xlink:type='simple']
    * Attribute used with the value “simple”. Value list is maintained by the xlink standard.
    */
    private boolean validateCSIP111() {
        return false;
    }

    /*
    * mets/structMap/div/div/mptr[@LOCTYPE='URL']
    * The locator type is always used with the value “URL” from the vocabulary in the attribute.
    */
    private boolean validateCSIP112() {
        return false;
    }
}
