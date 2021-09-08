package org.roda_project.commons_ip2.validator.component.descriptiveMetadataComponent;

import org.roda_project.commons_ip2.mets_v1_12.beans.AmdSecType;
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
import java.util.HashMap;
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
    public void validate() throws IOException {

        dmdSec = mets.getDmdSec();
        ReporterDetails csip;

        /* CSIP17 */
        validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP17_ID);
        csip = validateCSIP17();
        csip.setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION);
        addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP17_ID,csip);

        /* CSIP18 */
        validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP18_ID);
        csip = validateCSIP18();
        csip.setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION);
        addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP18_ID,csip);

        /* CSIP19 */
        validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP19_ID);
        csip = validateCSIP19();
        csip.setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION);
        addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP19_ID,csip);

        /* CSIP20 */
        validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP20_ID);
        csip = validateCSIP20();
        csip.setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION);
        addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP20_ID,csip);

        /* CSIP21 */
        validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP21_ID);
        csip = validateCSIP21();
        csip.setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION);
        addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP21_ID,csip);

        if(csip.isValid()){
            /* CSIP22 */
            validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP22_ID);
            csip = validateCSIP22();
            csip.setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION);
            addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP22_ID,csip);

            /* CSIP23 */
            validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP23_ID);
            csip = validateCSIP23();
            csip.setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION);
            addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP23_ID,csip);

            /* CSIP24 */
            validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP24_ID);
            csip = validateCSIP24();
            csip.setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION);
            addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP24_ID,csip);

            /* CSIP25 */
            validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP25_ID);
            csip = validateCSIP25();
            csip.setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION);
            addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP25_ID,csip);

            /* CSIP26 */
            csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,"",true, true);
            addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP26_ID,csip);

            /* CSIP27 */
            validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP27_ID);
            csip = validateCSIP27();
            csip.setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION);
            addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP27_ID,csip);

            /* CSIP28 */
            validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP28_ID);
            csip = validateCSIP28();
            csip.setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION);
            addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP28_ID,csip);

            /* CSIP29 */
            validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP29_ID);
            try{
                csip = validateCSIP29();
            }
            catch (Exception e){
                csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,"Can't calculate checksum of file",false,false);
            }
            csip.setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION);
            addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP29_ID,csip);

            /* CSIP30 */
            validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP30_ID);
            csip = validateCSIP30();
            csip.setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION);
            addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP30_ID,csip);
        }
        else{
            String message = "SKIPPED because mets/dmdSec/mdRef doesn't exist! (" + metsName + ")";
            /* CSIP22 */
            csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,message,true, true);
            addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP22_ID,csip);

            /* CSIP23 */
            csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,message,true, true);
            addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP23_ID,csip);

            /* CSIP24 */
            csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,message,true, true);
            addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP24_ID,csip);

            /* CSIP25 */
            csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,message,true, true);
            addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP25_ID,csip);

            /* CSIP26 */
            csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,message,true, true);
            addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP26_ID,csip);

            /* CSIP27 */
            csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,message,true, true);
            addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP27_ID,csip);

            /* CSIP28 */
            csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,message,true, true);
            addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP28_ID,csip);

            /* CSIP29 */
            csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,message,true, true);
            addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP29_ID,csip);

            /* CSIP30 */
            csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,message,true, true);
            addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP30_ID,csip);
        }
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
            String regex;
            if(isRootMets()){
                String OBJECTID = mets.getOBJID();
                if(OBJECTID != null){
                    regex = OBJECTID +  "/metadata/.*";
                }
                else{
                    return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,"mets/@OBJECTID can't be null",false,false);
                }
            }
            else{
                regex = metsPath + "metadata/.*";
            }
            if(dmdSec == null || dmdSec.size() == 0) {
                if (zipManager.countMetadataFiles(getEARKSIPpath(),regex) != 0) {
                    if(mets.getAmdSec()== null && mets.getAmdSec().size() == 0){
                        details.setValid(false);
                        details.addIssue("You have files in the metadata/folder, you must have mets/dmdSec or mets/amdSec (" + metsName + ")" );
                    }
                }
            }
            else {
                if (zipManager.countMetadataFiles(getEARKSIPpath(), regex) == 0) {
                    return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, "Doesn't have files in metadata folder but have in dmdSec; Put the files under metadata folder", false, false);
                }
                else {
                    HashMap<String,Boolean> metadataFiles = zipManager.getMetadataFiles(getEARKSIPpath(), regex);
                    for (MdSecType md : dmdSec) {
                        MdSecType.MdRef mdRef = md.getMdRef();
                        if (mdRef != null) {
                            String hrefDecoded = URLDecoder.decode(mdRef.getHref(), "UTF-8");
                            if(isRootMets()){
                                if (metadataFiles.containsKey(mets.getOBJID() + "/" + hrefDecoded)) {
                                    metadataFiles.replace(mets.getOBJID() + "/" + hrefDecoded, true);
                                }
                            }
                             else {
                                if (metadataFiles.containsKey(metsPath + hrefDecoded)) {
                                    metadataFiles.replace(metsPath + hrefDecoded, true);
                                }
                            }
                        }
                    }
                    if (metadataFiles.containsValue(false)) {
                        for (AmdSecType amd : mets.getAmdSec()) {
                            for (MdSecType md : amd.getDigiprovMD()) {
                                MdSecType.MdRef mdRef = md.getMdRef();
                                if (mdRef != null) {
                                    String hrefDecoded = URLDecoder.decode(mdRef.getHref(), "UTF-8");
                                    if(isRootMets()){
                                        if (metadataFiles.containsKey(mets.getOBJID() + "/" + hrefDecoded)) {
                                            metadataFiles.replace(mets.getOBJID() + "/" + hrefDecoded, true);
                                        }
                                    }
                                    else {
                                        if (metadataFiles.containsKey(metsPath + hrefDecoded)) {
                                            metadataFiles.replace(metsPath + hrefDecoded, true);
                                        }
                                    }
                                }
                            }
                        }
                        if (metadataFiles.containsValue(false)) {
                            return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, "Have metadata files not referenced in mets file", false, false);
                        }
                    }
                }
            }
        }
        else{
            if(mets.getDmdSec() == null) {
                if (folderManager.verifyMetadataFilesFolder(Paths.get(metsPath),"descriptive")) {
                    details.setValid(false);
                    details.addIssue("You have files in the metadata/descriptive folder, you must have mets/dmdSec");
                }
            }
            else{
                if(mets.getDmdSec().size() != folderManager.countMetadataFiles(Paths.get(metsPath),"descriptive")){
                    details.setValid(false);
                    details.addIssue("The number of files described is not equal to the number of files in the metadata/descriptive folder");
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
                details.addIssue("mets/dmdSec/@ID isn't unique in the package!");
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
                details.addIssue("mets/dmdSec/@CREATED can't be null!");
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
                details.addIssue("mets/dmdSec/@STATUS can't be null!");
            }
            else{
                if(!dmdSecStatus.contains(status)){
                    details.setValid(false);
                    details.addIssue("Check the valid values to mets/dmdSec/@STATUS at dmdSec Status  !");
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
    private ReporterDetails validateCSIP21() {
        ReporterDetails details = new ReporterDetails();
        for(MdSecType mdSec: dmdSec){
            MdSecType.MdRef mdRef = mdSec.getMdRef();
            if(mdRef == null){
                details.setValid(false);
                details.addIssue("You should reference the metadata file existing in the sip in mets/dmdSec/mdRef");
            }
        }
        return details;
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
                details.addIssue("mets/dmdSec/mdRef[@LOCTYPE=’URL’] can't be null!");
            }
            else{
                if(!loctype.equals("URL")){
                    details.setValid(false);
                    details.addIssue("mets/dmdSec/mdRef[@LOCTYPE=’URL’] value must be URL!");
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
                details.addIssue("mets/dmdSec/mdRef[@xlink:type=’simple’] can't be null");
            }
            else{
                if(!xlinkType.equals("simple")){
                    details.setValid(false);
                    details.addIssue("mets/dmdSec/mdRef[@xlink:type=’simple’] value must be 'simple'");
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
                        details.addIssue("mets/dmdSec/mdRef/@xlink:href path doesn't exists");
                    }
                }
                else{
                    if(!folderManager.checkPathExists(getEARKSIPpath(),Paths.get(hrefDecoded))){
                        details.setValid(false);
                        details.addIssue("mets/dmdSec/mdRef/@xlink:href path doesn't exists");
                    }
                }
            }
            else{
                details.setValid(false);
                details.addIssue("mets/dmdSec/mdRef/@xlink:href can't be null");
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
                        details.addIssue("mets/dmdSec/mdRef/@MDTYPE isn't a valid value. See also METS SCHEMA");
                    }
                }
                else{
                    details.setValid(false);
                    details.addIssue("mets/dmdSec/mdRef/@MDTYPE can't be null");
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
                        String file;
                        if(isRootMets()){
                            String OBJECTID = mets.getOBJID();
                            if(OBJECTID != null){
                                file = OBJECTID +  "/" + hrefDecoded;
                            }
                            else{
                                return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,"mets/@OBJECTID can't be null",false,false);
                            }
                        }
                        else{
                            file = metsPath + hrefDecoded ;
                        }
                        if(!zipManager.verifySize(getEARKSIPpath(),file,size)){
                            details.setValid(false);
                            details.addIssue("mets/dmdSec/mdRef/@SIZE and size of file isn't equal");
                        }
                    }
                    else{
                        if(!folderManager.verifySize(getEARKSIPpath(),hrefDecoded,size)){
                            details.setValid(false);
                            details.addIssue("mets/dmdSec/mdRef/@SIZE and size of file isn't equal");
                        }
                    }
                }
                else{
                    details.setValid(false);
                    details.addIssue("mets/dmdSec/mdRef/@SIZE can't be null");
                }
            }
            else{
                details.setValid(false);
                details.addIssue("mets/dmdSec/mdRef/@href can't be null");
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
                details.addIssue("mets/dmdSec/mdRef/@CREATED can't be null");
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
                            String filePath;
                            if (isZipFileFlag()) {
                                if(isRootMets()){
                                    String OBJECTID = mets.getOBJID();
                                    if(OBJECTID != null){
                                        filePath = OBJECTID +  "/" + file;
                                    }
                                    else{
                                        return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,"mets/@OBJECTID can't be null",false,false);
                                    }
                                }
                                else{
                                    filePath = metsPath + file ;
                                }
                                if (!zipManager.verifyChecksum(getEARKSIPpath(), filePath, checksumType, checksum)) {
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
