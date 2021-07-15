package org.roda_project.commons_ip2.validator.component.administritiveMetadataComponent;

import org.roda_project.commons_ip2.mets_v1_12.beans.AmdSecType;
import org.roda_project.commons_ip2.mets_v1_12.beans.MdSecType;
import org.roda_project.commons_ip2.mets_v1_12.beans.Mets;
import org.roda_project.commons_ip2.mets_v1_12.beans.MetsType;
import org.roda_project.commons_ip2.validator.common.ControlledVocabularyParser;
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
    public boolean validate() throws IOException {
        boolean valid = true;

        amdSec = mets.getAmdSec();

        /* CSIP31 */
        validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP31_ID);
        if(validateCSIP31()){
            validationOutcomePassed(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP31_ID, "");

            /* CSIP32 */
            validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP32_ID);
            if(validateCSIP32()){
                validationOutcomePassed(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP32_ID,"");

                /* CSIP33 */
                validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP33_ID);
                if(validateCSIP33()){
                    validationOutcomePassed(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP33_ID,"");
                }
                else{
                    validationOutcomeFailed(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP33_ID, "");
                    valid = false;
                }

                /* CSIP34 */
                validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP34_ID);
                if(validateCSIP34()){
                    validationOutcomePassed(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP34_ID,"");
                }
                else{
                    validationOutcomeFailed(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP34_ID, "");
                    valid = false;
                }

                /* CSIP35 */
                validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP35_ID);
                if(validateCSIP35()){
                    validationOutcomePassed(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP35_ID,"");
                }
                else{
                    validationOutcomeFailed(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP35_ID, "");
                    valid = false;
                }

                /* CSIP36 */
                validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP36_ID);
                if(validateCSIP36()){
                    validationOutcomePassed(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP36_ID,"");
                }
                else{
                    validationOutcomeFailed(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP36_ID, "");
                    valid = false;
                }

                /* CSIP37 */
                validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP37_ID);
                if(validateCSIP37()){
                    validationOutcomePassed(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP37_ID,"");
                }
                else{
                    validationOutcomeFailed(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP37_ID,"");
                    valid = false;
                }

                /* CSIP38 */
                validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP38_ID);
                if(validateCSIP38()){
                    validationOutcomePassed(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP38_ID,"");
                }
                else{
                    validationOutcomeFailed(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP38_ID,"");
                    valid = false;
                }

                /* CSIP39 */
                validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP39_ID);
                if(validateCSIP39()){
                    validationOutcomePassed(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP39_ID,"");
                }
                else{
                    validationOutcomeFailed(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP39_ID,"");
                    valid = false;
                }

                /* CSIP40 */
                validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP40_ID);
                if(validateCSIP40()){
                    validationOutcomeSkipped(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP40_ID,"");
                }


                /* CSIP41 */
                validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP41_ID);
                if(validateCSIP41()){
                    validationOutcomePassed(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP41_ID,"");
                }
                else{
                    validationOutcomeFailed(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP41_ID,"");
                    valid = false;
                }

                /* CSIP42 */
                validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP42_ID);
                if(validateCSIP42()){
                    validationOutcomePassed(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP42_ID,"");
                }
                else{
                    validationOutcomeFailed(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP42_ID,"");
                    valid = false;
                }



                /* CSIP43 */
                validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP43_ID);
                boolean csip43 = false;
                try {
                    csip43 = validateCSIP43();
                } catch (NoSuchAlgorithmException e) {
                    validationOutcomeFailed(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP37_ID,"");
                    valid = false;

                }

                if(csip43){
                    validationOutcomePassed(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP43_ID,"");
                }
                else{
                    validationOutcomeFailed(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP43_ID,"");
                    valid = false;
                }

                /* CSIP44 */
                validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP44_ID);
                if(validateCSIP44()){
                    validationOutcomePassed(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP44_ID,"");
                }
                else {
                    validationOutcomeFailed(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP44_ID, "");
                    valid = false;
                }

            }
            else{
                validationOutcomeFailed(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP32_ID, "");
                valid = false;
            }

            /* CSIP45 */
            validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP45_ID);
            if(validateCSIP45()){
                validationOutcomePassed(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP45_ID,"");

                /* CSIP46 */
                validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP46_ID);
                if(validateCSIP46()){
                    validationOutcomePassed(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP46_ID,"");
                }
                else{
                    validationOutcomeFailed(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP46_ID, "");
                    valid = false;
                }

                /* CSIP47 */
                validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP47_ID);
                if(validateCSIP47()){
                    validationOutcomePassed(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP47_ID,"");
                }
                else{
                    validationOutcomeFailed(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP47_ID, "");
                    valid = false;
                }

                /* CSIP48 */
                validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP48_ID);
                if(validateCSIP48()){
                    validationOutcomePassed(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP48_ID,"");
                }
                else{
                    validationOutcomeFailed(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP48_ID, "");
                    valid = false;
                }

                /* CSIP49 */
                validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP49_ID);
                if(validateCSIP49()){
                    validationOutcomePassed(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP49_ID,"");
                }
                else{
                    validationOutcomeFailed(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP49_ID, "");
                    valid = false;
                }

                /* CSIP50 */
                validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP50_ID);
                if(validateCSIP50()){
                    validationOutcomePassed(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP50_ID,"");
                }
                else{
                    validationOutcomeFailed(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP50_ID, "");
                    valid = false;
                }

                /* CSIP51 */
                validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP51_ID);
                if(validateCSIP51()){
                    validationOutcomePassed(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP51_ID,"");
                }
                else{
                    validationOutcomeFailed(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP51_ID, "");
                    valid = false;
                }

                /* CSIP52 */
                validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP52_ID);
                if(validateCSIP52()){
                    validationOutcomePassed(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP52_ID,"");
                }
                else{
                    validationOutcomeFailed(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP52_ID, "");
                    valid = false;
                }


                /* CSIP53 */
                validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP53_ID);
                if(validateCSIP53()){
                    validationOutcomeSkipped(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP53_ID,"");
                }

                /* CSIP54 */
                validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP54_ID);
                if(validateCSIP54()){
                    validationOutcomePassed(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP54_ID,"");
                }
                else{
                    validationOutcomeFailed(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP54_ID, "");
                    valid = false;
                }

                /* CSIP55 */
                validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP55_ID);
                if(validateCSIP55()){
                    validationOutcomeSkipped(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP55_ID,"");
                }
                else{
                    validationOutcomeFailed(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP55_ID, "");
                    valid = false;
                }

                /* CSIP56 */
                validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP56_ID);
                boolean csip56 = false;
                try {
                    csip56 = validateCSIP56();
                } catch (NoSuchAlgorithmException e) {
                    validationOutcomeFailed(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP56_ID,"");
                    valid = false;
                }
                if(csip56){
                    validationOutcomePassed(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP56_ID,"");
                }
                else{
                    validationOutcomeFailed(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP56_ID, "");
                    valid = false;
                }

                /* CSIP57 */
                validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP57_ID);
                if(validateCSIP57()){
                    validationOutcomePassed(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP57_ID,"");
                }
                else{
                    validationOutcomeFailed(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP57_ID, "");
                    valid = false;
                }
            }
            else{
                validationOutcomeFailed(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP45_ID, "");
                valid = false;
            }
        }
        else{
            validationOutcomeFailed(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP31_ID, "");
            valid = false;
        }
        return valid;
    }

    /*
    * mets/amdSec
    * If administrative / preservation metadata is available, it must be described
    * using the administrative metadata section ( <amdSec> ) element. All
    * administrative metadata is present in a single <amdSec> element. It is
    * possible to transfer metadata in a package using just the descriptive
    * metadata section and/or administrative metadata section.
    */
    private boolean validateCSIP31() {
        return true;
    }

    /*
    * mets/amdSec/digiprovMD
    * For recording information about preservation the standard PREMIS is used.
    * It is mandatory to include one <digiprovMD> element for each piece of
    * PREMIS metadata. The use if PREMIS in METS is following the
    * recommendations in the 2017 version of PREMIS in METS Guidelines.
    */
    private boolean validateCSIP32() {
        boolean valid = true;

        // Falta contar e comparar quantos premis metadata tem o sip

        for(AmdSecType a: amdSec){
            List<MdSecType> digiprov = a.getDigiprovMD();
            if(digiprov == null){
                valid = false;
            }
        }
        return valid;
    }

    /*
    * mets/amdSec/digiprovMD/@ID
    * An xml:id identifier for the digital provenance metadata section
    * mets/amdSec/digiprovMD used for internal package references. It must
    * be unique within the package.
    */
    private boolean validateCSIP33() {
        for(AmdSecType a: amdSec) {
            List<MdSecType> digiprov = a.getDigiprovMD();
            for(MdSecType md: digiprov){
               if(!checkId(md.getID())){
                   addId(md.getID());
               }
               else{
                   return false;
               }
            }
        }
        return true;
    }

    /*
    * mets/amdSec/digiprovMD/@STATUS
    * Indicates the status of the package using a fixed vocabulary.See also:
    * dmdSec status
    */
    private boolean validateCSIP34() {
        for(AmdSecType a: amdSec){
            List<MdSecType> digiprov = a.getDigiprovMD();
            for(MdSecType md: digiprov){
                String status = md.getSTATUS();
                if(!dmdSecStatus.contains(status)){
                    return false;
                }
            }
        }
        return true;

    }

    /*
    * mets/amdSec/digiprovMD/mdRef
    * Reference to the digital provenance metadata file stored in the “metadata”
    * section of the IP.
    */
    private boolean validateCSIP35(){
        for(AmdSecType a: amdSec){
            List<MdSecType> digiprov = a.getDigiprovMD();
            for(MdSecType md: digiprov){
                MdSecType.MdRef mdRef = md.getMdRef();
                if(mdRef == null){
                    return false;
                }
            }
        }
        return true;
    }

    /*
    * mets/amdSec/digiprovMD/mdRef[@LOCTYPE=’URL’]
    * The locator type is always used with the value “URL” from the vocabulary in
    * the attribute.
    */
    private boolean validateCSIP36() {
        boolean valid = true;
        for(AmdSecType a: amdSec){
            List<MdSecType> digiprov = a.getDigiprovMD();
            for(MdSecType md: digiprov){
                MdSecType.MdRef mdRef = md.getMdRef();
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
    private boolean validateCSIP37() {
        boolean valid = true;
        for(AmdSecType a: amdSec){
            List<MdSecType> digiprov = a.getDigiprovMD();
            for(MdSecType md: digiprov){
                MdSecType.MdRef mdRef = md.getMdRef();
                String xLinktype = mdRef.getType();
                if(xLinktype == null) {
                    valid = false;
                }
                else{
                    if(!xLinktype.equals("simple")){
                        valid = false;
                    }
                }
            }
        }
        return valid;
    }

    /*
    * mets/amdSec/digiprovMD/mdRef/@xlink:href
    * The actual location of the resource. This specification recommends
    * recording a URL type filepath within this attribute.
    */
    private boolean validateCSIP38() throws IOException {
        boolean valid = true;
        for(AmdSecType a: amdSec){
            List<MdSecType> digiprov = a.getDigiprovMD();
            for(MdSecType md: digiprov){
                MdSecType.MdRef mdRef = md.getMdRef();
                if(mdRef != null){
                    String href = URLDecoder.decode(mdRef.getHref(),"UTF-8");
                    if(isZipFileFlag()){
                        if(!zipManager.checkPathExists(getEARKSIPpath(),href)){
                            valid = false;
                            break;
                        }
                    }
                    else{
                        if(!folderManager.checkPathExists(getEARKSIPpath(), Paths.get(href))){
                            valid = false;
                            break;
                        }
                    }
                }
            }
            if(!valid){
                break;
            }
        }
        return valid;
    }

    /*
    * mets/amdSec/digiprovMD/mdRef/@MDTYPE
    * Specifies the type of metadata in the referenced file. Values are taken from
    * the list provided by the METS.
    */
    private boolean validateCSIP39() {
        boolean valid = true;
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
                        valid = false;
                        break;
                    }
                    else {
                        if(!tmp.contains(mdType)){
                            valid = false;
                            break;
                        }
                    }
                }
            }
            if(!valid){
                break;
            }
        }
        return valid;
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
    private boolean validateCSIP41() throws IOException {
        boolean valid = true;
        for(AmdSecType a: amdSec){
            List<MdSecType> digiprov = a.getDigiprovMD();
            for(MdSecType md: digiprov){
                MdSecType.MdRef mdRef = md.getMdRef();
                if(mdRef != null){
                    String href = URLDecoder.decode(mdRef.getHref(),"UTF-8");
                    if(href == null){
                        valid = false;
                        break;
                    }
                    else{
                        Long size = mdRef.getSIZE();
                        if(size == null){
                            valid = false;
                            break;
                        }
                        else{
                            if(isZipFileFlag()){
                                if(!zipManager.verifySize(getEARKSIPpath(),href,size)){
                                    valid = false;
                                    break;
                                }
                            }
                            else{
                                if(!folderManager.verifySize(getEARKSIPpath(),href,size)){
                                    valid = false;
                                    break;
                                }
                            }
                        }
                    }
                }
            }
            if(!valid){
                break;
            }
        }
        return valid;
    }

    /*
    * mets/amdSec/digiprovMD/mdRef/@CREATED
    * Creation date of the referenced file.
    */
    private boolean validateCSIP42() {
        boolean valid = true;
        for(AmdSecType a: amdSec){
            List<MdSecType> digiprov = a.getDigiprovMD();
            for(MdSecType md: digiprov){
                MdSecType.MdRef mdRef = md.getMdRef();
                if(mdRef.getCREATED() == null){
                    valid = false;
                    break;
                }
            }
            if(!valid){
                break;
            }
        }
        return valid;
    }

    /*
    * mets/amdSec/digiprovMD/mdRef/@CHECKSUM
    * The checksum of the referenced file.
    */
    private boolean validateCSIP43() throws IOException, NoSuchAlgorithmException {
        boolean valid = true;
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
                    valid = false;
                    break;
                }
                else {
                    if (!tmp.contains(checksumType)) {
                        valid = false;
                        break;
                    } else {
                        String checksum = mdRef.getCHECKSUM();
                        if (checksum == null) {
                            valid = false;
                            break;
                        } else {
                            String file = URLDecoder.decode(mdRef.getHref(), "UTF-8");
                            if (file == null) {
                                valid = false;
                                break;
                            } else {
                                if (isZipFileFlag()) {
                                    if (!zipManager.verifyChecksum(getEARKSIPpath(), file, checksumType, checksum)) {
                                        valid = false;
                                        break;
                                    }
                                } else {
                                    if (!folderManager.verifyChecksum(getEARKSIPpath(), file, checksumType, checksum)) {
                                        valid = false;
                                        break;
                                    }
                                }
                            }
                        }
                    }
                }
            }
            if(!valid){
                break;
            }
        }
        return valid;
    }

    /*
    * mets/amdSec/digiprovMD/mdRef/@CHECKSUMTYPE
    * The type of checksum following the value list present in the METS-standard
    * which has been used for calculating the checksum for the referenced file.
    */
    private boolean validateCSIP44() {
        boolean valid = true;
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
                    valid = false;
                }
                else{
                    if(!tmp.contains(checksumType)){
                        valid = false;
                    }
                }
            }
        }
        return valid;
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
    private boolean validateCSIP46() {
        boolean valid = true;
        for(AmdSecType a: amdSec){
            List<MdSecType> rigthsMD = a.getRightsMD();
            if(rigthsMD != null) {
                for (MdSecType rmd : rigthsMD) {
                    if(checkId(rmd.getID())){
                        valid = false;
                        break;
                    }
                    else{
                        addId(rmd.getID());
                    }
                }
                if(!valid){
                    break;
                }
            }
        }
        return valid;
    }

    /*
    * mets/amdSec/rightsMD/@STATUS
    * Indicates the status of the package using a fixed vocabulary.See also:
    * dmdSec status
    */
    private boolean validateCSIP47() {
        boolean valid = true;
        for(AmdSecType a: amdSec){
            List<MdSecType> rigthsMD = a.getRightsMD();
            if(rigthsMD != null) {
                for (MdSecType rmd : rigthsMD) {
                    String status = rmd.getSTATUS();
                    if(status == null){
                        valid = false;
                        break;
                    }
                    else{
                        if(!dmdSecStatus.contains(status)){
                            valid = false;
                            break;
                        }
                    }
                }
                if(!valid){
                    break;
                }
            }
        }
        return valid;
    }

    /*
    * mets/amdSec/rightsMD/mdRef
    * Reference to the rights metadata file stored in the “metadata” section of the
    * IP.
    */
    private boolean validateCSIP48() {
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
        return valid;
    }

    /*
    * mets/amdSec/rightsMD/mdRef[@LOCTYPE=’URL’]
    * The locator type is always used with the value “URL” from the vocabulary in
    * the attribute.
    */
    private boolean validateCSIP49() {
        boolean valid = true;
        for(AmdSecType a: amdSec){
            List<MdSecType> rigthsMD = a.getRightsMD();
            if(rigthsMD != null) {
                for(MdSecType rmd: rigthsMD){
                    MdSecType.MdRef mdRef = rmd.getMdRef();
                    if(mdRef != null){
                        String loctype = mdRef.getLOCTYPE();
                        if(loctype == null){
                            valid = false;
                        }
                        else{
                            if(!loctype.equals("URL")){
                                valid = false;
                            }
                        }
                    }
                }
            }
        }
        return valid;
    }

    /*
    * mets/amdSec/rightsMD/mdRef[@xlink:type=’simple’]
    * Attribute used with the value “simple”. Value list is maintained by the xlink
    * standard.
    */
    private boolean validateCSIP50() {
        boolean valid = true;
        for(AmdSecType a: amdSec){
            List<MdSecType> rigthsMD = a.getRightsMD();
            if(rigthsMD != null) {
                for(MdSecType rmd: rigthsMD){
                    MdSecType.MdRef mdRef = rmd.getMdRef();
                    if(mdRef != null){
                        String type = mdRef.getType();
                        if(type == null){
                            valid = false;
                        }
                        else{
                            if(!type.equals("simple")){
                                valid = false;
                            }
                        }
                    }
                }
            }
        }
        return valid;
    }

    /*
    * mets/amdSec/rightsMD/mdRef/@xlink:href
    * The actual location of the resource. We recommend recording a URL type
    * filepath within this attribute.
    */
    private boolean validateCSIP51() throws IOException {
        boolean valid = true;
        for(AmdSecType a: amdSec){
            List<MdSecType> rigthsMD = a.getRightsMD();
            if(rigthsMD != null) {
                for(MdSecType rmd: rigthsMD){
                    MdSecType.MdRef mdRef = rmd.getMdRef();
                    if(mdRef != null){
                        String href = URLDecoder.decode(mdRef.getHref(),"UTF-8");
                        if(isZipFileFlag()){
                            if(!zipManager.checkPathExists(getEARKSIPpath(),href)){
                                valid = false;
                                break;
                            }
                        }
                        else{
                            if(!folderManager.checkPathExists(getEARKSIPpath(),Paths.get(href))){
                                valid = false;
                                break;
                            }
                        }
                    }
                }
            }
        }
        return valid;
    }

    /*
    * mets/amdSec/rightsMD/mdRef/@MDTYPE
    * Specifies the type of metadata in the referenced file. Value is taken from the
    * list provided by the METS.
    */
    private boolean validateCSIP52() {
        boolean valid = true;
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
                            valid = false;
                            break;
                        }
                        else{
                            if(!tmp.contains(mdType)){
                                valid = false;
                                break;
                            }
                        }
                    }
                }
                if(!valid){
                    break;
                }
            }
        }
        return valid;
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
    private boolean validateCSIP54() throws IOException {
        boolean valid = true;
        for(AmdSecType a: amdSec){
            List<MdSecType> rigthsMD = a.getRightsMD();
            if(rigthsMD != null) {
                for(MdSecType rmd: rigthsMD){
                    MdSecType.MdRef mdRef = rmd.getMdRef();
                    if(mdRef != null){
                        String href = URLDecoder.decode(mdRef.getHref(),"UTF-8");
                        if(href == null){
                            valid = false;
                            break;
                        }
                        else{
                            Long size = mdRef.getSIZE();
                            if(size == null){
                                valid = false;
                                break;
                            }
                            else{
                                if(isZipFileFlag()){
                                    if(!zipManager.verifySize(getEARKSIPpath(),href,size)){
                                        valid = false;
                                        break;
                                    }
                                }
                                else{
                                    if(!folderManager.verifySize(getEARKSIPpath(),href,size)){
                                        valid = false;
                                        break;
                                    }
                                }
                            }
                        }
                    }
                }
                if(!valid){
                    break;
                }
            }
        }
        return valid;
    }

    /*
    * mets/amdSec/rightsMD/mdRef/@CREATED
    * Creation date of the referenced file.
    */
    private boolean validateCSIP55() {
        boolean valid = true;
        for(AmdSecType a: amdSec){
            List<MdSecType> rigthsMD = a.getRightsMD();
            if(rigthsMD != null) {
                for(MdSecType rmd: rigthsMD){
                    MdSecType.MdRef mdRef = rmd.getMdRef();
                    if(mdRef.getCREATED() == null){
                        valid = false;
                    }
                }
            }
        }
        return valid;
    }

    /*
    * mets/amdSec/rightsMD/mdRef/@CHECKSUM
    * The checksum of the referenced file.
    */
    private boolean validateCSIP56() throws IOException, NoSuchAlgorithmException {
        boolean valid = true;
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
                        valid = false;
                        break;
                    }
                    else {
                        if (!tmp.contains(checksumType)) {
                            valid = false;
                            break;
                        } else {
                            String checksum = mdRef.getCHECKSUM();
                            if (checksum == null) {
                                valid = false;
                                break;
                            } else {
                                String file = URLDecoder.decode(mdRef.getHref(), "UTF-8");
                                if (file == null) {
                                    valid = false;
                                    break;
                                } else {
                                    if (isZipFileFlag()) {
                                        if (!zipManager.verifyChecksum(getEARKSIPpath(), file, checksumType, checksum)) {
                                            valid = false;
                                            break;
                                        }
                                    } else {
                                        if (!folderManager.verifyChecksum(getEARKSIPpath(), file, checksumType, checksum)) {
                                            valid = false;
                                            break;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                if(!valid){
                    break;
                }
            }
        }
        return valid;
    }

    /*
    * mets/amdSec/rightsMD/mdRef/@CHECKSUMTYPE
    * The type of checksum following the value list present in the METS-standard
    * which has been used for calculating the checksum for the referenced file.
    */
    private boolean validateCSIP57() {
        boolean valid = true;
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
                        valid = false;
                    }
                    else{
                        if(!tmp.contains(checksumType)){
                            valid = false;
                        }
                    }
                }
                if(!valid){
                    break;
                }
            }
        }
        return valid;
    }


}
