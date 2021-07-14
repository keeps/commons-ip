package org.roda_project.commons_ip2.validator.component.descriptiveMetadataComponent;

import org.roda_project.commons_ip2.mets_v1_12.beans.MdSecType;
import org.roda_project.commons_ip2.validator.common.ControlledVocabularyParser;
import org.roda_project.commons_ip2.validator.common.ZipManager;
import org.roda_project.commons_ip2.validator.component.ValidatorComponentImpl;
import org.roda_project.commons_ip2.validator.constants.Constants;
import org.roda_project.commons_ip2.validator.constants.ConstantsCSIPspec;
import org.roda_project.commons_ip2.validator.utils.CHECKSUMTYPE;
import org.roda_project.commons_ip2.validator.utils.MetadataType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author João Gomes <jgomes@keep.pt>
 */
public class DescriptiveMetadataComponentValidator extends ValidatorComponentImpl {
    private static final Logger LOGGER = LoggerFactory.getLogger(DescriptiveMetadataComponentValidator.class);

    private final String MODULE_NAME;
    private List<MdSecType> dmdSec;
    private List<String> dmdSecStatus;

    public void setDmdSecStatus(List<String> dmdSecStatus) {
        this.dmdSecStatus = dmdSecStatus;
    }

    public DescriptiveMetadataComponentValidator(String module_name) {
        MODULE_NAME = module_name;
        this.dmdSecStatus = new ArrayList<>();
        ControlledVocabularyParser controlledVocabularyParser = new ControlledVocabularyParser(Constants.PATH_RESOURCES_CSIP_VOCABULARY_DMD_SEC_STATUS,dmdSecStatus);
        controlledVocabularyParser.parse();
        setDmdSecStatus(controlledVocabularyParser.getData());
    }

    @Override
    public boolean validate() throws IOException {
        boolean valid = true;
        dmdSec = mets.getDmdSec();

        /* CSIP17 */
        validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP17_ID);
        if(validateCSIP17()){
            validationOutcomePassed(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP17_ID,"");

            /* CSIP18 */
            validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP18_ID);
            if(validateCSIP18()){
                validationOutcomePassed(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP18_ID,"");
            }
            else{
                validationOutcomeFailed(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP18_ID, "");
                valid = false;
            }

            /* CSIP19 */
            validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP19_ID);
            if(validateCSIP19()){
                validationOutcomePassed(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP18_ID,"");
            }
            else {
                validationOutcomeFailed(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP19_ID, "");
                valid = false;
            }

            /* CSIP20 */
            validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP20_ID);
            if(validateCSIP20()){
                validationOutcomePassed(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP20_ID,"");
            }
            else{
                validationOutcomeFailed(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP20_ID, "");
                valid = false;
            }

            /* CSIP21 */
            validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP21_ID);
            if(validateCSIP21()){
                validationOutcomeSkipped(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP21_ID,"");

                /* CSIP22 */
                validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP22_ID);
                if(validateCSIP22()){
                    validationOutcomePassed(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP22_ID,"");
                }
                else{
                    validationOutcomeFailed(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP22_ID, "");
                    valid = false;
                }

                /* CSIP23 */
                validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP23_ID);
                if(validateCSIP23()){
                    validationOutcomePassed(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP23_ID,"");
                }
                else{
                    validationOutcomeFailed(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP23_ID, "");
                    valid = false;
                }

                /* CSIP24 */
                validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP24_ID);
                if(validateCSIP24()){
                    validationOutcomePassed(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP24_ID,"");
                }
                else{
                    validationOutcomeFailed(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP24_ID, "");
                    valid = false;
                }

                /* CSIP25 */
                validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP25_ID);
                if(validateCSIP25()){
                    validationOutcomePassed(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP25_ID,"");
                }
                else{
                    validationOutcomeFailed(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP25_ID, "");
                    valid = false;
                }

                /* CSIP26 */
                validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP26_ID);
                if(validateCSIP26()){
                    validationOutcomeSkipped(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP26_ID,"");
                }

                /* CSIP27 */
                validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP27_ID);
                if(validateCSIP27()){
                    validationOutcomePassed(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP27_ID,"");
                }
                else{
                    validationOutcomeFailed(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP27_ID, "");
                    valid = false;
                }

                /* CSIP28 */
                if(validateCSIP28()){
                    validationOutcomePassed(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP28_ID,"");
                }
                else{
                    validationOutcomeFailed(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP28_ID, "");
                    valid = false;
                }

                /* CSIP29 */
                validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP29_ID);
                boolean csip29 = false;
                try {
                    csip29 = validateCSIP29();
                } catch (NoSuchAlgorithmException e) {
                    validationOutcomeFailed(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP29_ID, "");
                    valid = false;
                }
                if(csip29){
                    validationOutcomePassed(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP29_ID,"");
                }
                else{
                    validationOutcomeFailed(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP29_ID, "");
                    valid = false;
                }

                /* CSIP30 */
                validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP30_ID);
                if(validateCSIP30()){
                    validationOutcomePassed(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP30_ID,"");
                }
                else{
                    validationOutcomeFailed(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP30_ID, "");
                    valid = false;
                }
            }
            else{
                validationOutcomeFailed(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP21_ID, "");
                valid = false;
            }
        }
        else{
            validationOutcomeFailed(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP17_ID, "");
            valid = false;
        }

        return valid;
    }

    /*
    * mets/dmdSec
    * Must be used if descriptive metadata for the package content is available.
    * Each descriptive metadata section ( <dmdSec> ) contains a single description
    * and must be repeated for multiple descriptions, when available. It is
    * possible to transfer metadata in a package using just the descriptive
    * metadata section and/or administrative metadata section.
    */

    private boolean validateCSIP17() throws IOException {
        boolean valid = true;
        if(isZipFileFlag()){
            String objectID = mets.getOBJID();
            if(objectID == null){
                valid = false;
            }
            else{
                if(mets.getDmdSec() == null) {
                    if (zipManager.verifyMetadataDescriptiveFolder(getEARKSIPpath(),objectID)) {
                        valid = false;
                    }
                }
                else{
                    if(mets.getDmdSec().size() != zipManager.countMetadataDescriptiveFiles(getEARKSIPpath(),objectID)){
                        valid = false;
                    }
                }
            }
        }
        else{
            if(mets.getDmdSec() == null) {
                if (folderManager.verifyMetadataDescriptiveFolder(getEARKSIPpath())) {
                    valid = false;
                }
            }
            else{
                if(mets.getDmdSec().size() != folderManager.countMetadataDescriptiveFiles(getEARKSIPpath())){
                    valid = false;
                }
            }
        }

        return valid;
    }

    /*
    * mets/dmdSec/@ID
    * An xml:id identifier for the descriptive metadata section ( <dmdSec> ) used
    * for internal package references. It must be unique within the package.
    */
    private boolean validateCSIP18() {
        for(MdSecType mdSec: dmdSec){
            if(!checkId(mdSec.getID())){
                addId(mdSec.getID());
            }
            else{
                return false;
            }
        }
        return true;
    }

    /*
    * mets/dmdSec/@CREATED
    * Creation date of the descriptive metadata in this section.
    */
    private boolean validateCSIP19() {
        for(MdSecType mdSec: dmdSec){
            if (mdSec.getCREATED() == null) {
                return false;
            }
        }
        return true;
    }

    /*
    * mets/dmdSec/@STATUS
    * Indicates the status of the package using a fixed vocabulary.See also:
    * dmdSec status
    */
    private boolean validateCSIP20() {
        for(MdSecType mdSec: dmdSec){
            String status = mdSec.getSTATUS();
            if(status == null){
                return false;
            }
            else{
                if(!dmdSecStatus.contains(status)){
                    return false;
                }
            }
        }
        return true;
    }

    /*
    * mets/dmdSec/mdRef
    * Reference to the descriptive metadata file located in the “metadata” section
    * of the IP.
    */
    private boolean validateCSIP21() {
        return true;
    }

    /*
    * mets/dmdSec/mdRef[@LOCTYPE=’URL’]
    * The locator type is always used with the value “URL” from the vocabulary in
    * the attribute.
    */

    private boolean validateCSIP22() {
        for(MdSecType mdSec: dmdSec){
            MdSecType.MdRef mdRef = mdSec.getMdRef();
            String loctype = mdRef.getLOCTYPE();
            if(loctype == null) {
                return false;
            }
            else{
                if(!loctype.equals("URL")){
                    return false;
                }
            }
        }
        return true;
    }

    /*
    * mets/dmdSec/mdRef[@xlink:type=’simple’]
    * Attribute used with the value “simple”. Value list is maintained by the xlink
    * standard.
    */
    private boolean validateCSIP23() {
        for(MdSecType mdSec: dmdSec){
            MdSecType.MdRef mdRef = mdSec.getMdRef();
            String xlinkType = mdRef.getType();
            if(xlinkType == null) {
                return false;
            }
            else{
                if(!xlinkType.equals("simple")){
                    return false;
                }
            }
        }
        return true;
    }

    /*
    * mets/dmdSec/mdRef/@xlink:href
    * The actual location of the resource. This specification recommends
    * recording a URL type filepath in this attribute.
    */
    private boolean validateCSIP24() throws IOException {
        for(MdSecType mdSec: dmdSec){
            MdSecType.MdRef mdRef = mdSec.getMdRef();
            String href = URLDecoder.decode(mdRef.getHref(),"UTF-8");
            if(isZipFileFlag()){
                if(!zipManager.checkPathExists(getEARKSIPpath(),href)){
                    return false;
                }
            }
            else{
                if(!folderManager.checkPathExists(getEARKSIPpath(),Paths.get(href))){
                    return false;
                }
            }
        }
        return true;
    }

    /*
    * mets/dmdSec/mdRef/@MDTYPE
    * Specifies the type of metadata in the referenced file. Values are taken from
    * the list provided by the METS.

    * NOTA: Falta saber que valores são válidos
    * */
    private boolean validateCSIP25() {
        List<String> tmp = new ArrayList<>();
        for(MetadataType md: MetadataType.values()){
            tmp.add(md.toString());
        }
        for(MdSecType mdSec: dmdSec){
            MdSecType.MdRef mdRef = mdSec.getMdRef();
            if(mdRef != null){
                String mdType = mdRef.getMDTYPE();
                if(mdType != null){
                    if(!tmp.contains(mdType)){
                        return false;
                    }
                }
                else{
                  return false;
                }
            }
        }
        return true;
    }

    /*
    * mets/dmdSec/mdRef/@MIMETYPE
    * The IANA mime type of the referenced file.See also: IANA media types

    * Fica para a próxima iteração(Deixar para o fim)
    */
    private boolean validateCSIP26() {
        return true;
    }

    /*
    * mets/dmdSec/mdRef/@SIZE
    * Size of the referenced file in bytes.
    */
    private boolean validateCSIP27() throws IOException {
        for(MdSecType mdSec: dmdSec){
            MdSecType.MdRef mdRef = mdSec.getMdRef();
            String href = URLDecoder.decode(mdRef.getHref(),"UTF-8");
            if(href != null){
                Long size = mdRef.getSIZE();
                if(size != null){
                    if(isZipFileFlag()){
                        if(!zipManager.verifySize(getEARKSIPpath(),href,size)){
                            return false;
                        }
                    }
                    else{
                        if(!folderManager.verifySize(getEARKSIPpath(),href,size)){
                            return false;
                        }
                    }
                }
                else{
                    return false;
                }
            }
            else{
                return false;
            }
        }
        return true;
    }

    /*
    * mets/dmdSec/mdRef/@CREATED
    * The creation date of the referenced file..
    */
    private boolean validateCSIP28() {
        for(MdSecType mdSec: dmdSec){
            MdSecType.MdRef mdRef = mdSec.getMdRef();
            if(mdRef.getCREATED() == null){
                return false;
            }
        }
        return true;
    }

    /*
    * mets/dmdSec/mdRef/@CHECKSUM
    * The checksum of the referenced file.
    */
    private boolean validateCSIP29() throws IOException, NoSuchAlgorithmException {
        List<String> tmp = new ArrayList<>();
        for(CHECKSUMTYPE check: CHECKSUMTYPE.values()){
            tmp.add(check.toString());
        }
        for(MdSecType mdSec: dmdSec) {
            MdSecType.MdRef mdRef = mdSec.getMdRef();
            String checksumType = mdRef.getCHECKSUMTYPE();
            if (checksumType != null) {
                if (tmp.contains(checksumType)) {
                    String checksum = mdRef.getCHECKSUM();
                    if (checksum != null) {
                        String file = URLDecoder.decode(mdRef.getHref(), "UTF-8");
                        if (file != null) {
                            if (isZipFileFlag()) {
                                if (!zipManager.verifyChecksum(getEARKSIPpath(), file, checksumType, checksum)) {
                                    return false;
                                }
                            } else {
                                if (!folderManager.verifyChecksum(getEARKSIPpath(), file, checksumType, checksum)) {
                                    return false;
                                }
                            }
                        } else {
                            return false;
                        }
                    } else {
                        return false;
                    }
                } else {
                    return false;
                }
            } else {
                return false;
            }
        }
        return true;
    }

    /*
    * mets/dmdSec/mdRef/@CHECKSUMTYPE
    * The type of checksum following the value list present in the METS-standard
    * which has been used for calculating the checksum for the referenced file.
    */
    private boolean validateCSIP30() {
        List<String> tmp = new ArrayList<>();
        for(CHECKSUMTYPE check: CHECKSUMTYPE.values()){
            tmp.add(check.toString());
        }
        for(MdSecType mdSec: dmdSec){
            MdSecType.MdRef mdRef = mdSec.getMdRef();
            String checksumType = mdRef.getCHECKSUMTYPE();
            if(checksumType != null){
                if(!tmp.contains(checksumType)){
                   return false;
                }
            }
            else{
              return false;
            }
        }
        return true;
    }

}
