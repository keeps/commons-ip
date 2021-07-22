package org.roda_project.commons_ip2.validator.component.descriptiveMetadataComponent;

import org.roda_project.commons_ip2.mets_v1_12.beans.MdSecType;
import org.roda_project.commons_ip2.validator.common.ControlledVocabularyParser;
import org.roda_project.commons_ip2.validator.common.ZipManager;
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
        ReporterDetails csip;
        /* CSIP17 */
        validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP17_ID);
        csip = validateCSIP17();
        if(csip.isValid()){
            validationOutcomePassed(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP17_ID,csip.getMessage());

            /* CSIP18 */
            validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP18_ID);
            csip = validateCSIP18();
            if(csip.isValid()){
                validationOutcomePassed(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP18_ID,csip.getMessage());
            }
            else{
                validationOutcomeFailed(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP18_ID, csip.getMessage());
                valid = false;
            }

            /* CSIP19 */
            validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP19_ID);
            csip = validateCSIP19();
            if(csip.isValid()){
                validationOutcomePassed(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP18_ID,csip.getMessage());
            }
            else {
                validationOutcomeFailed(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP19_ID, csip.getMessage());
                valid = false;
            }

            /* CSIP20 */
            validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP20_ID);
            csip = validateCSIP20();
            if(csip.isValid()){
                validationOutcomePassed(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP20_ID,csip.getMessage());
            }
            else{
                validationOutcomeFailed(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP20_ID,csip.getMessage());
                valid = false;
            }

            /* CSIP21 */
            validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP21_ID);
            if(validateCSIP21()){
                validationOutcomeSkipped(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP21_ID,"");

                /* CSIP22 */
                validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP22_ID);
                csip = validateCSIP22();
                if(csip.isValid()){
                    validationOutcomePassed(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP22_ID,csip.getMessage());
                }
                else{
                    validationOutcomeFailed(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP22_ID, csip.getMessage());
                    valid = false;
                }

                /* CSIP23 */
                validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP23_ID);
                csip = validateCSIP23();
                if(csip.isValid()){
                    validationOutcomePassed(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP23_ID,csip.getMessage());
                }
                else{
                    validationOutcomeFailed(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP23_ID, csip.getMessage());
                    valid = false;
                }

                /* CSIP24 */
                validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP24_ID);
                csip = validateCSIP24();
                if(csip.isValid()){
                    validationOutcomePassed(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP24_ID,csip.getMessage());
                }
                else{
                    validationOutcomeFailed(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP24_ID,csip.getMessage());
                    valid = false;
                }

                /* CSIP25 */
                validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP25_ID);
                csip = validateCSIP25();
                if(csip.isValid()){
                    validationOutcomePassed(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP25_ID,csip.getMessage());
                }
                else{
                    validationOutcomeFailed(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP25_ID, csip.getMessage());
                    valid = false;
                }

                /* CSIP26 */
                validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP26_ID);
                if(validateCSIP26()){
                    validationOutcomeSkipped(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP26_ID,"");
                }

                /* CSIP27 */
                validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP27_ID);
                csip = validateCSIP27();
                if(csip.isValid()){
                    validationOutcomePassed(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP27_ID,csip.getMessage());
                }
                else{
                    validationOutcomeFailed(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP27_ID,csip.getMessage());
                    valid = false;
                }

                /* CSIP28 */
                csip = validateCSIP28();
                if(csip.isValid()){
                    validationOutcomePassed(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP28_ID,csip.getMessage());
                }
                else{
                    validationOutcomeFailed(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP28_ID,csip.getMessage());
                    valid = false;
                }

                /* CSIP29 */
                validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP29_ID);
                try {
                    csip = validateCSIP29();
                } catch (NoSuchAlgorithmException e) {
                    validationOutcomeFailed(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP29_ID, csip.getMessage());
                    valid = false;
                }
                if(csip.isValid()){
                    validationOutcomePassed(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP29_ID,csip.getMessage());
                }
                else{
                    validationOutcomeFailed(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP29_ID, csip.getMessage());
                    valid = false;
                }

                /* CSIP30 */
                validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP30_ID);
                csip = validateCSIP30();
                if(csip.isValid()){
                    validationOutcomePassed(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP30_ID, csip.getMessage());
                }
                else{
                    validationOutcomeFailed(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP30_ID,  csip.getMessage());
                    valid = false;
                }
            }
            else{
                validationOutcomeFailed(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP21_ID,  csip.getMessage());
                valid = false;
            }
        }
        else{
            validationOutcomeFailed(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP17_ID,  csip.getMessage());
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

    private ReporterDetails validateCSIP17() throws IOException {
        ReporterDetails details = new ReporterDetails();
        if(isZipFileFlag()){
            String objectID = mets.getOBJID();
            if(objectID == null){
                details.setValid(false);
                details.setMessage("mets/OBJID can't be null");
            }
            else{
                if(mets.getDmdSec() == null) {
                    if (zipManager.verifyMetadataDescriptiveFolder(getEARKSIPpath(),objectID)) {
                        details.setValid(false);
                        details.setMessage("You have files in the metadata/descriptive folder, you must have mets/dmdSec");
                    }
                }
                else{
                    if(mets.getDmdSec().size() != zipManager.countMetadataDescriptiveFiles(getEARKSIPpath(),objectID)){
                        details.setValid(false);
                        details.setMessage("The number of files described is not equal to the number of files in the metadata/descriptive folder");
                    }
                }
            }
        }
        else{
            if(mets.getDmdSec() == null) {
                if (folderManager.verifyMetadataDescriptiveFolder(getEARKSIPpath())) {
                    details.setValid(false);
                    details.setMessage("You have files in the metadata/descriptive folder, you must have mets/dmdSec");
                }
            }
            else{
                if(mets.getDmdSec().size() != folderManager.countMetadataDescriptiveFiles(getEARKSIPpath())){
                    details.setValid(false);
                    details.setMessage("The number of files described is not equal to the number of files in the metadata/descriptive folder");
                }
            }
        }
        return details;
    }

    /*
    * mets/dmdSec/@ID
    * An xml:id identifier for the descriptive metadata section ( <dmdSec> ) used
    * for internal package references. It must be unique within the package.
    */
    private ReporterDetails validateCSIP18() {
        ReporterDetails details = new ReporterDetails();
        for(MdSecType mdSec: dmdSec){
            if(!checkId(mdSec.getID())){
                addId(mdSec.getID());
            }
            else{
                details.setValid(false);
                details.setMessage("mets/dmdSec/@ID isn't unique in the package!");
                break;
            }
        }
        return details;
    }

    /*
    * mets/dmdSec/@CREATED
    * Creation date of the descriptive metadata in this section.
    */
    private ReporterDetails validateCSIP19() {
        ReporterDetails details = new ReporterDetails();
        for(MdSecType mdSec: dmdSec){
            if (mdSec.getCREATED() == null) {
                details.setValid(false);
                details.setMessage("mets/dmdSec/@CREATED can't be null!");
            }
        }
        return details;
    }

    /*
    * mets/dmdSec/@STATUS
    * Indicates the status of the package using a fixed vocabulary.See also:
    * dmdSec status
    */
    private ReporterDetails validateCSIP20() {
        ReporterDetails details = new ReporterDetails();
        for(MdSecType mdSec: dmdSec){
            String status = mdSec.getSTATUS();
            if(status == null){
                details.setValid(false);
                details.setMessage("mets/dmdSec/@STATUS can't be null!");
            }
            else{
                if(!dmdSecStatus.contains(status)){
                    details.setValid(false);
                    details.setMessage("Check the valid values to mets/dmdSec/@STATUS at dmdSec Status  !");
                }
            }
        }
        return details;
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

    private ReporterDetails validateCSIP22() {
        ReporterDetails details = new ReporterDetails();
        for(MdSecType mdSec: dmdSec){
            MdSecType.MdRef mdRef = mdSec.getMdRef();
            String loctype = mdRef.getLOCTYPE();
            if(loctype == null) {
                details.setValid(false);
                details.setMessage("mets/dmdSec/mdRef[@LOCTYPE=’URL’] can't be null!");
            }
            else{
                if(!loctype.equals("URL")){
                    details.setValid(false);
                    details.setMessage("mets/dmdSec/mdRef[@LOCTYPE=’URL’] value must be URL!");
                }
            }
        }
        return details;
    }

    /*
    * mets/dmdSec/mdRef[@xlink:type=’simple’]
    * Attribute used with the value “simple”. Value list is maintained by the xlink
    * standard.
    */
    private ReporterDetails validateCSIP23() {
        ReporterDetails details = new ReporterDetails();
        for(MdSecType mdSec: dmdSec){
            MdSecType.MdRef mdRef = mdSec.getMdRef();
            String xlinkType = mdRef.getType();
            if(xlinkType == null) {
                details.setValid(false);
                details.setMessage("mets/dmdSec/mdRef[@xlink:type=’simple’] can't be null");
            }
            else{
                if(!xlinkType.equals("simple")){
                    details.setValid(false);
                    details.setMessage("mets/dmdSec/mdRef[@xlink:type=’simple’] value must be 'simple'");
                }
            }
        }
        return details;
    }

    /*
    * mets/dmdSec/mdRef/@xlink:href
    * The actual location of the resource. This specification recommends
    * recording a URL type filepath in this attribute.
    */
    private ReporterDetails validateCSIP24() throws IOException {
        ReporterDetails details = new ReporterDetails();
        for(MdSecType mdSec: dmdSec){
            MdSecType.MdRef mdRef = mdSec.getMdRef();
            String href = mdRef.getHref();
            if(href != null){
                String hrefDecoded = URLDecoder.decode(href,"UTF-8");
                if(isZipFileFlag()){
                    if(!zipManager.checkPathExists(getEARKSIPpath(),hrefDecoded)){
                        details.setValid(false);
                        details.setMessage("mets/dmdSec/mdRef/@xlink:href path doesn't exists");
                    }
                }
                else{
                    if(!folderManager.checkPathExists(getEARKSIPpath(),Paths.get(hrefDecoded))){
                        details.setValid(false);
                        details.setMessage("mets/dmdSec/mdRef/@xlink:href path doesn't exists");
                    }
                }
            }
            else{
                details.setValid(false);
                details.setMessage("mets/dmdSec/mdRef/@xlink:href can't be null");
            }
        }
        return details;
    }

    /*
    * mets/dmdSec/mdRef/@MDTYPE
    * Specifies the type of metadata in the referenced file. Values are taken from
    * the list provided by the METS.
    * */
    private ReporterDetails validateCSIP25() {
        ReporterDetails details = new ReporterDetails();
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
                        details.setValid(false);
                        details.setMessage("mets/dmdSec/mdRef/@MDTYPE isn't a valid value. See also METS SCHEMA");
                    }
                }
                else{
                    details.setValid(false);
                    details.setMessage("mets/dmdSec/mdRef/@MDTYPE can't be null");
                }
            }
        }
        return details;
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
    private ReporterDetails validateCSIP27() throws IOException {
        ReporterDetails details = new ReporterDetails();
        for(MdSecType mdSec: dmdSec){
            MdSecType.MdRef mdRef = mdSec.getMdRef();
            String href = mdRef.getHref();
            if(href != null){
                String hrefDecoded = URLDecoder.decode(mdRef.getHref(),"UTF-8");
                Long size = mdRef.getSIZE();
                if(size != null){
                    if(isZipFileFlag()){
                        if(!zipManager.verifySize(getEARKSIPpath(),hrefDecoded,size)){
                            details.setValid(false);
                            details.setMessage("mets/dmdSec/mdRef/@SIZE and size of file isn't equal");
                        }
                    }
                    else{
                        if(!folderManager.verifySize(getEARKSIPpath(),hrefDecoded,size)){
                            details.setValid(false);
                            details.setMessage("mets/dmdSec/mdRef/@SIZE and size of file isn't equal");
                        }
                    }
                }
                else{
                    details.setValid(false);
                    details.setMessage("mets/dmdSec/mdRef/@SIZE can't be null");
                }
            }
            else{
                details.setValid(false);
                details.setMessage("mets/dmdSec/mdRef/@href can't be null");
            }
        }
        return details;
    }

    /*
    * mets/dmdSec/mdRef/@CREATED
    * The creation date of the referenced file..
    */
    private ReporterDetails validateCSIP28() {
        ReporterDetails details = new ReporterDetails();
        for(MdSecType mdSec: dmdSec){
            MdSecType.MdRef mdRef = mdSec.getMdRef();
            if(mdRef.getCREATED() == null){
                details.setValid(false);
                details.setMessage("mets/dmdSec/mdRef/@CREATED can't be null");
            }
        }
        return details;
    }

    /*
    * mets/dmdSec/mdRef/@CHECKSUM
    * The checksum of the referenced file.
    */
    private ReporterDetails validateCSIP29() throws IOException, NoSuchAlgorithmException {
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
                        String href = mdRef.getHref();
                        if (href != null) {
                            String file = URLDecoder.decode(href, "UTF-8");
                            if (isZipFileFlag()) {
                                if (!zipManager.verifyChecksum(getEARKSIPpath(), file, checksumType, checksum)) {
                                    return new ReporterDetails("mets/dmdSec/mdRef/@CHECKSUM and checksum of file isn't equal",false);
                                }
                            } else {
                                if (!folderManager.verifyChecksum(getEARKSIPpath(), file, checksumType, checksum)) {
                                    return new ReporterDetails("mets/dmdSec/mdRef/@CHECKSUM and checksum of file isn't equal",false);
                                }
                            }
                        } else {
                            return new ReporterDetails("mets/dmdSec/mdRef/@href can't be null!",false);
                        }
                    } else {
                        return new ReporterDetails("mets/dmdSec/mdRef/@CHECKSUM can't be null",false);
                    }
                } else {
                    return new ReporterDetails("mets/dmdSec/mdRef/@CHECKSUMTYPE isn't valid. See valid values at METS SCHEMA",false);
                }
            } else {
                return new ReporterDetails("mets/dmdSec/mdRef/@CHECKSUMTYPE can't be null!",false);
            }
        }
        return new ReporterDetails();
    }

    /*
    * mets/dmdSec/mdRef/@CHECKSUMTYPE
    * The type of checksum following the value list present in the METS-standard
    * which has been used for calculating the checksum for the referenced file.
    */
    private ReporterDetails validateCSIP30() {
        List<String> tmp = new ArrayList<>();
        for(CHECKSUMTYPE check: CHECKSUMTYPE.values()){
            tmp.add(check.toString());
        }
        for(MdSecType mdSec: dmdSec){
            MdSecType.MdRef mdRef = mdSec.getMdRef();
            String checksumType = mdRef.getCHECKSUMTYPE();
            if(checksumType != null){
                if(!tmp.contains(checksumType)){
                    return new ReporterDetails("mets/dmdSec/mdRef/@CHECKSUMTYPE isn't valid. See valid values at METS SCHEMA",false);
                }
            }
            else{
                return new ReporterDetails("mets/dmdSec/mdRef/@CHECKSUMTYPE can't be null!",false);
            }
        }
        return new ReporterDetails();
    }

}
