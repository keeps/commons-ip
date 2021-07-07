package org.roda_project.commons_ip2.validator.component.fileSectionComponent;

import org.roda_project.commons_ip2.mets_v1_12.beans.FileType;
import org.roda_project.commons_ip2.mets_v1_12.beans.MetsType;
import org.roda_project.commons_ip2.validator.component.ValidatorComponentImpl;
import org.roda_project.commons_ip2.validator.constants.Constants;
import org.roda_project.commons_ip2.validator.constants.ConstantsCSIPspec;
import org.roda_project.commons_ip2.validator.utils.CHECKSUMTYPE;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author João Gomes <jgomes@keep.pt>
 */
public class FileSectionComponentValidator extends ValidatorComponentImpl {
    private static final Logger LOGGER = LoggerFactory.getLogger(FileSectionComponentValidator.class);

    private final String MODULE_NAME;

    public FileSectionComponentValidator(String module_name) {
        MODULE_NAME = module_name;
    }

    @Override
    public boolean validate() throws IOException {
        boolean valid = true;

        /* CSIP58 */
        validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP58_ID);
        if(validateCSIP58()){
            validationOutcomeSkipped(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP58_ID,"");
        }

        /* CSIP59 */
        validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP59_ID);
        if(validateCSIP59()){
            validationOutcomeSkipped(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP59_ID,"");
        }

        /* CSIP60 */
        validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP60_ID);
        if(validateCSIP60()){
            validationOutcomeSkipped(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP60_ID,"");
        }

        /* CSIP113 */
        validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP113_ID);
        if(validateCSIP113()){
            validationOutcomeSkipped(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP113_ID,"");
        }

        /* CSIP114 */
        validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP114_ID);
        if(validateCSIP114()){
            validationOutcomeSkipped(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP114_ID,"");
        }

        /* CSIP61 */
        validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP61_ID);
        if(validateCSIP61()){
            validationOutcomeSkipped(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP61_ID,"");
        }

        /* CSIP62 */
        validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP62_ID);
        if(validateCSIP62()){
            validationOutcomeSkipped(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP62_ID,"");
        }

        /* CSIP63 */
        validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP63_ID);
        if(validateCSIP63()){
            validationOutcomeSkipped(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP63_ID,"");
        }

        /* CSIP64 */
        validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP64_ID);
        if(validateCSIP64()){
            validationOutcomeSkipped(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP64_ID,"");
        }

        /* CSIP65 */
        validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP65_ID);
        if(validateCSIP65()){
            validationOutcomeSkipped(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP65_ID,"");
        }

        /* CSIP66 */
        validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP66_ID);
        if(validateCSIP66()){
            validationOutcomeSkipped(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP66_ID,"");
        }

        /* CSIP67 */
        validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP67_ID);
        if(validateCSIP67()){
            validationOutcomeSkipped(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP67_ID,"");
        }

        /* CSIP68 */
        validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP68_ID);
        if(validateCSIP68()){
            validationOutcomeSkipped(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP68_ID,"");
        }

        /* CSIP69 */
        validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP69_ID);
        if(validateCSIP69()){
            validationOutcomeSkipped(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP69_ID,"");
        }

        /* CSIP70 */
        validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP70_ID);
        if(validateCSIP70()){
            validationOutcomeSkipped(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP70_ID,"");
        }

        /* CSIP71 */
        validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP71_ID);
        if(validateCSIP71()){
            validationOutcomeSkipped(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP71_ID,"");
        }

        /* CSIP72 */
        validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP72_ID);
        if(validateCSIP72()){
            validationOutcomeSkipped(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP72_ID,"");
        }

        /* CSIP73 */
        validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP73_ID);
        if(validateCSIP73()){
            validationOutcomeSkipped(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP73_ID,"");
        }

        /* CSIP74 */
        validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP74_ID);
        if(validateCSIP74()){
            validationOutcomeSkipped(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP74_ID,"");
        }

        /* CSIP75 */
        validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP75_ID);
        if(validateCSIP75()){
            validationOutcomeSkipped(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP75_ID,"");
        }

        /* CSIP76 */
        validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP76_ID);
        if(validateCSIP76()){
            validationOutcomeSkipped(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP76_ID,"");
        }

        /* CSIP77 */
        validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP77_ID);
        if(validateCSIP77()){
            validationOutcomeSkipped(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP77_ID,"");
        }

        /* CSIP78 */
        validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP78_ID);
        if(validateCSIP78()){
            validationOutcomeSkipped(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP78_ID,"");
        }

        /* CSIP79 */
        validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP79_ID);
        if(validateCSIP79()){
            validationOutcomeSkipped(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP79_ID,"");
        }

        return false;
    }

    /*
    * mets/fileSec
    * The transferred content is placed in the file section in different file group
    * elements, described in other requirements. Only a single file section
    * ( <fileSec> ) element should be present. It is possible to transfer just
    * descriptive metadata and/or administrative metadata without files placed in
    * this section.
    */
    private boolean validateCSIP58() {

        return false;
    }

    /*
    * mets/fileSec/@ID
    * An xml:id identifier for the file section used for internal package references.
    * It must be unique within the package.
    */
    private boolean validateCSIP59() {
        boolean valid = true;
        MetsType.FileSec fileSec = mets.getFileSec();
        if(fileSec != null){
            String id = fileSec.getID();
            if(id == null){
                valid = false;
            }
            else{
                if(checkId(id)){
                    valid = false;
                }
                else{
                    addId(id);
                }
            }
        }
        return valid;
    }

    /*
    * mets/fileSec/fileGrp[@USE=’Documentation’]
    * All documentation pertaining to the transferred content is placed in one or
    * more file group elements with mets/fileSec/fileGrp/@USE attribute value
    * “Documentation”.See also: File group names
    */
    private boolean validateCSIP60() {
        return false;
    }

    /*
    * mets/fileSec/fileGrp[@USE=’Schemas’]
    * All XML schemas used in the information package should be referenced from
    * one or more file groups with mets/fileSec/fileGrp/@USE attribute value
    * “Schemas”.See also: File group names
    */
    private boolean validateCSIP113() {
        return false;
    }

    /*
    * mets/fileSec/fileGrp[@USE=’Representations’]
    * A pointer to the METS document describing the representation or pointers to
    * the content being transferred must be present in one or more file groups
    * with mets/fileSec/fileGrp/@USE attribute value “Representations”.See
    * also: File group names
    */
    private boolean validateCSIP114() {
        return false;
    }

    /*
    * mets/fileSec/fileGrp/@ADMID
    * If administrative metadata has been provided at file group
    * mets/fileSec/fileGrp level this attribute refers to its administrative
    * metadata section by ID.
    */
    private boolean validateCSIP61() {
        return false;
    }

    /*
    * mets/fileSec/fileGrp[@USE=’Representations’]/@csip:CONTENTINFORMATIONTYPE
    * An added attribute which states the name of the content information type
    * specification used to create the package. The vocabulary will evolve under
    * the curation of the DILCIS Board as additional content information type
    * specifications are developed. This attribute is mandatory when the
    * mets/fileSec/fileGrp/@USE attribute value is “Representations”. When
    * the “Package type” value is “Mixed” and/or the file group describes a
    * “Representation”, then this element states the content information type
    * specification used for the file group.See also: Content information type
    * specification
    */
    private boolean validateCSIP62() {
        return false;
    }

    /*
    * mets/fileSec/fileGrp[@csip:CONTENTINFORMATIONTYPE=’OTHER’]/@csip:OTHERCONTENTINFORMATIONTYPE
    * When the mets/fileSec/fileGrp/@csip:CONTENTINFORMATIONTYPE
    * attribute has the value “OTHER” the
    * mets/fileSec/fileGrp/@csip:OTHERCONTENTINFORMATIONTYPE must
    * state a value for the Content Information Type Specification used.
    */
    private boolean validateCSIP63() {
        return false;
    }

    /*
    * mets/fileSec/fileGrp/@USE
    * The value in the mets/fileSec/fileGrp/@USE is the name of the whole
    * folder structure to the data, e.g “Documentation”, “Schemas”,
    * “Representations/preingest” or “Representations/submission/data”.
    */
    private boolean validateCSIP64() {
        return false;
    }

    /*
    * mets/fileSec/fileGrp/@ID
    * An xml:id identifier for the file group used for internal package references.
    * It must be unique within the package.
    */
    private boolean validateCSIP65() {
        boolean valid = true;
        MetsType.FileSec fileSec = mets.getFileSec();
        List<MetsType.FileSec.FileGrp> fileGrp = fileSec.getFileGrp();
        for(MetsType.FileSec.FileGrp grp : fileGrp){
            String id = grp.getID();
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
        return valid;
    }

    /*
    * mets/fileSec/fileGrp/file
    * The file group ( <fileGrp> ) contains the file elements which describe the file
    * objects.
    */
    private boolean validateCSIP66() {
        boolean valid = true;
        MetsType.FileSec fileSec = mets.getFileSec();
        List<MetsType.FileSec.FileGrp> fileGrp = fileSec.getFileGrp();
        for(MetsType.FileSec.FileGrp grp : fileGrp){
            List<FileType> files = grp.getFile();
            if(files == null || files.size() == 0){
                valid = false;
                break;
            }
        }
        return valid;
    }

    /*
    * mets/fileSec/fileGrp/file/@ID
    * A unique xml:id identifier for this file across the package.
    */
    private boolean validateCSIP67() {
        boolean valid = true;
        MetsType.FileSec fileSec = mets.getFileSec();
        List<MetsType.FileSec.FileGrp> fileGrp = fileSec.getFileGrp();
        for(MetsType.FileSec.FileGrp grp : fileGrp){
            List<FileType> files = grp.getFile();
            for(FileType file : files){
                String id = file.getID();
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
            if(!valid){
                break;
            }
        }
        return valid;
    }

    /*
    * mets/fileSec/fileGrp/file/@MIMETYPE
    * The IANA mime type for the referenced file.See also: IANA media types
    */
    private boolean validateCSIP68() {
        return false;
    }

    /*
    * mets/fileSec/fileGrp/file/@SIZE
    * Size of the referenced file in bytes.
    */
    private boolean validateCSIP69() {
        return false;
    }

    /*
    * mets/fileSec/fileGrp/file/@CREATED
    * Creation date of the referenced file.
    */
    private boolean validateCSIP70() {
        boolean valid = true;
        MetsType.FileSec fileSec = mets.getFileSec();
        List<MetsType.FileSec.FileGrp> fileGrp = fileSec.getFileGrp();
        for(MetsType.FileSec.FileGrp grp : fileGrp){
            List<FileType> files = grp.getFile();
            for(FileType file : files){
                if(file.getCREATED() == null){
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
    * mets/fileSec/fileGrp/file/@CHECKSUM
    * The checksum of the referenced file.
    */
    private boolean validateCSIP71() {
        return false;
    }

    /*
    * mets/fileSec/fileGrp/file/@CHECKSUMTYPE
    * The type of checksum following the value list present in the METS-standard
    * which has been used for calculating the checksum for the referenced file.
    */
    private boolean validateCSIP72() {
        boolean valid = true;
        List<String> tmp = new ArrayList<>();
        for(CHECKSUMTYPE check: CHECKSUMTYPE.values()){
            tmp.add(check.toString());
        }
        MetsType.FileSec fileSec = mets.getFileSec();
        List<MetsType.FileSec.FileGrp> fileGrp = fileSec.getFileGrp();
        for(MetsType.FileSec.FileGrp grp : fileGrp) {
            List<FileType> files = grp.getFile();
            for (FileType file : files) {
                String checksumType = file.getCHECKSUMTYPE();
                if(checksumType == null){
                    valid = false;
                    break;
                }
                else{
                    if(!tmp.contains(checksumType)){
                        valid = false;
                        break;
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
    * mets/fileSec/fileGrp/file/@OWNERID
    * If an identifier for the file was supplied by the owner it can be recorded in
    * this attribute.
    */
    private boolean validateCSIP73() {
        return false;
    }

    /*
    * mets/fileSec/fileGrp/file/@ADMID
    * If administrative metadata has been provided for the file this attribute refers
    * to the file’s administrative metadata by ID.
    */
    private boolean validateCSIP74() {
        return false;
    }

    /*
    * mets/fileSec/fileGrp/file/@DMDID
    * If descriptive metadata has been provided per file this attribute refers to the
    * file’s descriptive metadata by ID.
    */
    private boolean validateCSIP75() {
        return false;
    }

    /*
    * mets/fileSec/fileGrp/file/FLocat
    * The location of each external file must be defined by the file location
    * <FLocat> element using the same rules as for referencing metadata files.
    * All references to files should be made using the XLink href attribute and the
    * file protocol using the relative location of the file.
    */
    private boolean validateCSIP76() {
        return false;
    }

    /*
    * mets/fileSec/fileGrp/file/FLocat[@LOCTYPE=’URL’]
    * The locator type is always used with the value “URL” from the vocabulary in
    * the attribute.
    */
    private boolean validateCSIP77() {
        boolean valid = true;
        MetsType.FileSec fileSec = mets.getFileSec();
        List<MetsType.FileSec.FileGrp> fileGrp = fileSec.getFileGrp();
        for(MetsType.FileSec.FileGrp grp : fileGrp) {
            List<FileType> files = grp.getFile();
            for (FileType file : files) {
                List<FileType.FLocat> flocat = file.getFLocat();
                if(flocat == null){
                    valid = false;
                }
                else{
                    for(FileType.FLocat floc : flocat){
                        String loctype = floc.getLOCTYPE();
                        if(loctype == null){
                            valid = false;
                            break;
                        }
                        else{
                            if(!loctype.equals("URL")){
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
            if(!valid){
                break;
            }
        }
        return valid;
    }

    /*
    * mets/fileSec/fileGrp/file/FLocat[@xlink:type=’simple’]
    * Attribute used with the value “simple”. Value list is maintained by the xlink
    * standard.
    */
    private boolean validateCSIP78() {
        boolean valid = true;
        MetsType.FileSec fileSec = mets.getFileSec();
        List<MetsType.FileSec.FileGrp> fileGrp = fileSec.getFileGrp();
        for(MetsType.FileSec.FileGrp grp : fileGrp) {
            List<FileType> files = grp.getFile();
            for (FileType file : files) {
                List<FileType.FLocat> flocat = file.getFLocat();
                if(flocat == null){
                    valid = false;
                }
                else{
                    for(FileType.FLocat floc : flocat){
                        String type = floc.getType();
                        if(type == null){
                            valid = false;
                            break;
                        }
                        else{
                            if(!type.equals("simple")){
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
            if(!valid){
                break;
            }
        }
        return valid;
    }

    /*
    * mets/fileSec/fileGrp/file/FLocat/@xlink:href
    * The actual location of the resource. We recommend recording a URL type
    * filepath within this attribute.
    */
    private boolean validateCSIP79() {
        return false;
    }


}
