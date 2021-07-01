package org.roda_project.commons_ip2.validator.constants;

/**
 * @author João Gomes <jgomes@keep.pt>
 */
public class ConstantsCSIPspec {
    private ConstantsCSIPspec(){
    }

    /* Errors and ID's for constants in validation of sip path */
    public static final String VALIDATION_REPORT_PATH_DOES_NOT_EXIST_ID = "Path";
    public static final String VALIDATION_REPORT_PATH_DOES_NOT_EXIST_DETAIL = "The path does not exist or is not a directory or archive files";
    public static final String VALIDATION_REPORT_METS_NOT_FOUND_ID = "METS";
    public static final String VALIDATION_REPORT_METS_NOT_FOUND_DETAIL = "Root METS.xml not found";

    /* Use of the METS root element (element mets) */
    /* CSIP1 */
    public static final String VALIDATION_REPORT_SPECIFICATION_CSIP1_ID = "CSIP1";
    public static final String VALIDATION_REPORT_SPECIFICATION_CSIP1_NAME = "Package Identifier";
    public static final String VALIDATION_REPORT_SPECIFICATION_CSIP1_LOCATION = "mets/@OBJID";
    public static final String VALIDATION_REPORT_SPECIFICATION_CSIP1_DESCRIPTION = "The mets/@OBJID attribute is mandatory, its value is a string identifier for " +
            "the METS document. For the package METS document, this should be the " +
            "name/ID of the package, i.e. the name of the package root folder. For a " +
            "representation level METS document this value records the name/ID of the " +
            "representation, i.e. the name of the top-level representation folder.";
    public static final String VALIDATION_REPORT_SPECIFICATION_CSIP1_CARDINALITY = "1..1";
    public static final String VALIDATION_REPORT_SPECIFICATION_CSIP1_LEVEL = "MUST";

    /* CSIP2 */
    public static final String VALIDATION_REPORT_SPECIFICATION_CSIP2_ID = "CSIP2";
    public static final String VALIDATION_REPORT_SPECIFICATION_CSIP2_NAME = "Content Category";
    public static final String VALIDATION_REPORT_SPECIFICATION_CSIP2_LOCATION = "mets/@TYPE";
    public static final String VALIDATION_REPORT_SPECIFICATION_CSIP2_DESCRIPTION = "The mets/@TYPE attribute MUST be used to declare the category of the " +
            "content held in the package, e.g. book, journal, stereograph, video, etc.. " +
            "Legal values are defined in a fixed vocabulary. When the content category " +
            "used falls outside of the defined vocabulary the mets/@TYPE value must be " +
            "set to “OTHER” and the specific value declared in mets/@csip:OTHERTYPE. " +
            "The vocabulary will develop under the curation of the DILCIS Board as " +
            " additional content information type specifications are produced.See also: Content Category";
    public static final String VALIDATION_REPORT_SPECIFICATION_CSIP2_CARDINALITY = "0..1";
    public static final String VALIDATION_REPORT_SPECIFICATION_CSIP2_LEVEL = "MUST";

    /* CSIP 3 */
    public static final String VALIDATION_REPORT_SPECIFICATION_CSIP3_ID = "CSIP3";
    public static final String VALIDATION_REPORT_SPECIFICATION_CSIP3_NAME = "Other Content Category";
    public static final String VALIDATION_REPORT_SPECIFICATION_CSIP3_LOCATION = "mets[@TYPE=’OTHER’]/@csip:OTHERTYPE";
    public static final String VALIDATION_REPORT_SPECIFICATION_CSIP3_DESCRIPTION = "When the mets/@TYPE attribute has the value “OTHER” the " +
            "mets/@csip:OTHERTYPE attribute MUST be used to declare the content " +
            "category of the package/representation.See also: Content Category ";
    public static final String VALIDATION_REPORT_SPECIFICATION_CSIP3_CARDINALITY = "0..1";
    public static final String VALIDATION_REPORT_SPECIFICATION_CSIP3_LEVEL = "SHOULD";

    /* CSIP 4 */
    public static final String VALIDATION_REPORT_SPECIFICATION_CSIP4_ID = "CSIP4";
    public static final String VALIDATION_REPORT_SPECIFICATION_CSIP4_NAME = "Content Information Type Specification";
    public static final String VALIDATION_REPORT_SPECIFICATION_CSIP4_LOCATION = "mets/@csip:CONTENTINFORMATIONTYPE";
    public static final String VALIDATION_REPORT_SPECIFICATION_CSIP4_DESCRIPTION = "Used to declare the Content Information Type Specification used when " +
            "creating the package. Legal values are defined in a fixed vocabulary. The " +
            "attribute is mandatory for representation level METS documents. The " +
            "vocabulary will evolve under the care of the DILCIS Board as additional "+
            "Content Information Type Specifications are developed.See also: Content " +
            "information type specification";
    public static final String VALIDATION_REPORT_SPECIFICATION_CSIP4_CARDINALITY = "0..1";
    public static final String VALIDATION_REPORT_SPECIFICATION_CSIP4_LEVEL = "SHOULD";

    /* CSIP 5 */
    public static final String VALIDATION_REPORT_SPECIFICATION_CSIP5_ID = "CSIP5";
    public static final String VALIDATION_REPORT_SPECIFICATION_CSIP5_NAME = "Other Content Information Type Specification";
    public static final String VALIDATION_REPORT_SPECIFICATION_CSIP5_LOCATION = "mets[@csip:CONTENTINFORMATIONTYPE=’OTHER’]/@csip:OTHERCONTENTINFORMATIONTYPE";
    public static final String VALIDATION_REPORT_SPECIFICATION_CSIP5_DESCRIPTION = "When the mets/@csip:CONTENTINFORMATIONTYPE has the value " +
            "'OTHER' the mets/@csip:OTHERCONTENTINFORMATIONTYPE must state " +
            "the content information type. ";
    public static final String VALIDATION_REPORT_SPECIFICATION_CSIP5_CARDINALITY = "0..1";
    public static final String VALIDATION_REPORT_SPECIFICATION_CSIP5_LEVEL = "MAY";

    /* CSIP 6 */
    public static final String VALIDATION_REPORT_SPECIFICATION_CSIP6_ID = "CSIP6";
    public static final String VALIDATION_REPORT_SPECIFICATION_CSIP6_NAME = "METS Profile";
    public static final String VALIDATION_REPORT_SPECIFICATION_CSIP6_LOCATION = "mets/@PROFILE";
    public static final String VALIDATION_REPORT_SPECIFICATION_CSIP6_DESCRIPTION = "The URL of the METS profile that the information package conforms with. ";
    public static final String VALIDATION_REPORT_SPECIFICATION_CSIP6_CARDINALITY = "1..1";
    public static final String VALIDATION_REPORT_SPECIFICATION_CSIP6_LEVEL = "MUST";

    /* Use of the METS header (element metsHdr) */
    /* CSIP117 */
    public static final String VALIDATION_REPORT_SPECIFICATION_CSIP117_ID = "CSIP117";
    public static final String VALIDATION_REPORT_SPECIFICATION_CSIP117_NAME = "Package header";
    public static final String VALIDATION_REPORT_SPECIFICATION_CSIP117_LOCATION = "mets/metsHdr";
    public static final String VALIDATION_REPORT_SPECIFICATION_CSIP117_DESCRIPTION = "General element for describing the package.";
    public static final String VALIDATION_REPORT_SPECIFICATION_CSIP117_CARDINALITY = "1..1";
    public static final String VALIDATION_REPORT_SPECIFICATION_CSIP117_LEVEL = "MUST";

    /* CSIP7 */
    public static final String VALIDATION_REPORT_SPECIFICATION_CSIP7_ID = "CSIP7";
    public static final String VALIDATION_REPORT_SPECIFICATION_CSIP7_NAME = "Package creation date";
    public static final String VALIDATION_REPORT_SPECIFICATION_CSIP7_LOCATION = "mets/metsHdr/@CREATEDATE";
    public static final String VALIDATION_REPORT_SPECIFICATION_CSIP7_DESCRIPTION = "mets/metsHdr/@CREATEDATE records the date the package was created.";
    public static final String VALIDATION_REPORT_SPECIFICATION_CSIP7_CARDINALITY = "1..1";
    public static final String VALIDATION_REPORT_SPECIFICATION_CSIP7_LEVEL = "MUST";

    /* CSIP8 */
    public static final String VALIDATION_REPORT_SPECIFICATION_CSIP8_ID = "CSIP8";
    public static final String VALIDATION_REPORT_SPECIFICATION_CSIP8_NAME = "Package last modification date";
    public static final String VALIDATION_REPORT_SPECIFICATION_CSIP8_LOCATION = "mets/metsHdr/@LASTMODDATE";
    public static final String VALIDATION_REPORT_SPECIFICATION_CSIP8_DESCRIPTION = "mets/metsHdr/@LASTMODDATE is mandatory when the package has been modified.";
    public static final String VALIDATION_REPORT_SPECIFICATION_CSIP8_CARDINALITY = "0..1";
    public static final String VALIDATION_REPORT_SPECIFICATION_CSIP8_LEVEL = "SHOULD";

    /* CSIP9 */
    public static final String VALIDATION_REPORT_SPECIFICATION_CSIP9_ID = "CSIP9";
    public static final String VALIDATION_REPORT_SPECIFICATION_CSIP9_NAME = "OAIS Package type information";
    public static final String VALIDATION_REPORT_SPECIFICATION_CSIP9_LOCATION = "mets/metsHdr/@csip:OAISPACKAGETYPE";
    public static final String VALIDATION_REPORT_SPECIFICATION_CSIP9_DESCRIPTION = "mets/metsHdr/@csip:OAISPACKAGETYPE is an additional CSIP attribute that declares the type of the IP.See also: OAIS Package type";
    public static final String VALIDATION_REPORT_SPECIFICATION_CSIP9_CARDINALITY = "1..1";
    public static final String VALIDATION_REPORT_SPECIFICATION_CSIP9_LEVEL = "MUST";

    /* CSIP10 */
    public static final String VALIDATION_REPORT_SPECIFICATION_CSIP10_ID = "CSIP10";
    public static final String VALIDATION_REPORT_SPECIFICATION_CSIP10_NAME = "Agent";
    public static final String VALIDATION_REPORT_SPECIFICATION_CSIP10_LOCATION = "mets/metsHdr/agent";
    public static final String VALIDATION_REPORT_SPECIFICATION_CSIP10_DESCRIPTION = "A mandatory agent element records the software used to create the package. Other uses of agents may be described in any local implementations that extend the profile.";
    public static final String VALIDATION_REPORT_SPECIFICATION_CSIP10_CARDINALITY = "1..n";
    public static final String VALIDATION_REPORT_SPECIFICATION_CSIP10_LEVEL = "MUST";

    /* CSIP11 */
    public static final String VALIDATION_REPORT_SPECIFICATION_CSIP11_ID = "CSIP11";
    public static final String VALIDATION_REPORT_SPECIFICATION_CSIP11_NAME = "Agent role";
    public static final String VALIDATION_REPORT_SPECIFICATION_CSIP11_LOCATION = "mets/metsHdr/agent[@ROLE=’CREATOR’]";
    public static final String VALIDATION_REPORT_SPECIFICATION_CSIP11_DESCRIPTION = "The mandatory agent element MUST have a @ROLE attribute with the value 'CREATOR'";
    public static final String VALIDATION_REPORT_SPECIFICATION_CSIP11_CARDINALITY = "1..1";
    public static final String VALIDATION_REPORT_SPECIFICATION_CSIP11_LEVEL = "MUST";

    /* CSIP12 */
    public static final String VALIDATION_REPORT_SPECIFICATION_CSIP12_ID = "CSIP12";
    public static final String VALIDATION_REPORT_SPECIFICATION_CSIP12_NAME = "Agent type";
    public static final String VALIDATION_REPORT_SPECIFICATION_CSIP12_LOCATION = "mets/metsHdr/agent[@TYPE=’OTHER’]";
    public static final String VALIDATION_REPORT_SPECIFICATION_CSIP12_DESCRIPTION = "The mandatory agent element MUST have a @TYPE attribute with the value 'OTHER'.";
    public static final String VALIDATION_REPORT_SPECIFICATION_CSIP12_CARDINALITY = "1..1";
    public static final String VALIDATION_REPORT_SPECIFICATION_CSIP12_LEVEL = "MUST";

    /* CSIP13 */
    public static final String VALIDATION_REPORT_SPECIFICATION_CSIP13_ID = "CSIP13";
    public static final String VALIDATION_REPORT_SPECIFICATION_CSIP13_NAME = "Agent other type";
    public static final String VALIDATION_REPORT_SPECIFICATION_CSIP13_LOCATION = "mets/metsHdr/agent[@OTHERTYPE=’SOFTWARE’]";
    public static final String VALIDATION_REPORT_SPECIFICATION_CSIP13_DESCRIPTION = "The mandatory agent element MUST have a @OTHERTYPE attribute with the value “SOFTWARE”.See also: Other agent type";
    public static final String VALIDATION_REPORT_SPECIFICATION_CSIP13_CARDINALITY = "1..1";
    public static final String VALIDATION_REPORT_SPECIFICATION_CSIP13_LEVEL = "MUST";

    /* CSIP14 */
    public static final String VALIDATION_REPORT_SPECIFICATION_CSIP14_ID = "CSIP14";
    public static final String VALIDATION_REPORT_SPECIFICATION_CSIP14_NAME = "Agent name";
    public static final String VALIDATION_REPORT_SPECIFICATION_CSIP14_LOCATION = "mets/metsHdr/agent/name";
    public static final String VALIDATION_REPORT_SPECIFICATION_CSIP14_DESCRIPTION = "The mandatory agent’s name element records the name of the software tool used to create the IP.";
    public static final String VALIDATION_REPORT_SPECIFICATION_CSIP14_CARDINALITY = "1..1";
    public static final String VALIDATION_REPORT_SPECIFICATION_CSIP14_LEVEL = "MUST";

    /* CSIP15 */
    public static final String VALIDATION_REPORT_SPECIFICATION_CSIP15_ID = "CSIP15";
    public static final String VALIDATION_REPORT_SPECIFICATION_CSIP15_NAME = "Agent additional information";
    public static final String VALIDATION_REPORT_SPECIFICATION_CSIP15_LOCATION = "mets/metsHdr/agent/note";
    public static final String VALIDATION_REPORT_SPECIFICATION_CSIP15_DESCRIPTION = "The mandatory agent’s note element records the version of the tool used to create the IP.";
    public static final String VALIDATION_REPORT_SPECIFICATION_CSIP15_CARDINALITY = "1..1";
    public static final String VALIDATION_REPORT_SPECIFICATION_CSIP15_LEVEL = "MUST";

    /* CSIP16 */
    public static final String VALIDATION_REPORT_SPECIFICATION_CSIP16_ID = "CSIP16";
    public static final String VALIDATION_REPORT_SPECIFICATION_CSIP16_NAME = "Classification of the agent additional information";
    public static final String VALIDATION_REPORT_SPECIFICATION_CSIP16_LOCATION = "mets/metsHdr/agent/note[@csip:NOTETYPE=’SOFTWARE VERSION’]";
    public static final String VALIDATION_REPORT_SPECIFICATION_CSIP16_DESCRIPTION = "The mandatory agent element’s note child has a @csip:NOTETYPE attribute with a fixed value of “SOFTWARE VERSION”.See also: Note type";
    public static final String VALIDATION_REPORT_SPECIFICATION_CSIP16_CARDINALITY = "1..1";
    public static final String VALIDATION_REPORT_SPECIFICATION_CSIP16_LEVEL = "MUST";

    /* Use of the METS descriptive metadata section (element dmdSec) */
    /* CSIP17 */
    public static final String VALIDATION_REPORT_SPECIFICATION_CSIP17_ID = "CSIP17";
    public static final String VALIDATION_REPORT_SPECIFICATION_CSIP17_NAME = "Descriptive metadata";
    public static final String VALIDATION_REPORT_SPECIFICATION_CSIP17_LOCATION = "mets/dmdSec";
    public static final String VALIDATION_REPORT_SPECIFICATION_CSIP17_DESCRIPTION = "Must be used if descriptive metadata for the package content is available. " +
            "Each descriptive metadata section ( <dmdSec> ) contains a single description " +
            "and must be repeated for multiple descriptions, when available. It is " +
            "possible to transfer metadata in a package using just the descriptive " +
            "metadata section and/or administrative metadata section.";
    public static final String VALIDATION_REPORT_SPECIFICATION_CSIP17_CARDINALITY = "0..n";
    public static final String VALIDATION_REPORT_SPECIFICATION_CSIP17_LEVEL = "SHOULD";

    /* CSIP18 */
    public static final String VALIDATION_REPORT_SPECIFICATION_CSIP18_ID = "CSIP18";
    public static final String VALIDATION_REPORT_SPECIFICATION_CSIP18_NAME = "Descriptive metadata identifier";
    public static final String VALIDATION_REPORT_SPECIFICATION_CSIP18_LOCATION = "mets/dmdSec/@ID";
    public static final String VALIDATION_REPORT_SPECIFICATION_CSIP18_DESCRIPTION = "An xml:id identifier for the descriptive metadata section ( <dmdSec> ) used for internal package references. It must be unique within the package.";
    public static final String VALIDATION_REPORT_SPECIFICATION_CSIP18_CARDINALITY = "1..1";
    public static final String VALIDATION_REPORT_SPECIFICATION_CSIP18_LEVEL = "MUST";

    /* CSIP19 */
    public static final String VALIDATION_REPORT_SPECIFICATION_CSIP19_ID = "CSIP19";
    public static final String VALIDATION_REPORT_SPECIFICATION_CSIP19_NAME = "Descriptive metadata creation date";
    public static final String VALIDATION_REPORT_SPECIFICATION_CSIP19_LOCATION = "mets/dmdSec/@CREATED";
    public static final String VALIDATION_REPORT_SPECIFICATION_CSIP19_DESCRIPTION = "Creation date of the descriptive metadata in this section.";
    public static final String VALIDATION_REPORT_SPECIFICATION_CSIP19_CARDINALITY = "1..1";
    public static final String VALIDATION_REPORT_SPECIFICATION_CSIP19_LEVEL = "MUST";

    /* CSIP20 */
    public static final String VALIDATION_REPORT_SPECIFICATION_CSIP20_ID = "CSIP20";
    public static final String VALIDATION_REPORT_SPECIFICATION_CSIP20_NAME = "Status of the descriptive metadata";
    public static final String VALIDATION_REPORT_SPECIFICATION_CSIP20_LOCATION = "mets/dmdSec/@STATUS";
    public static final String VALIDATION_REPORT_SPECIFICATION_CSIP20_DESCRIPTION = "Indicates the status of the package using a fixed vocabulary.See also: dmdSec status";
    public static final String VALIDATION_REPORT_SPECIFICATION_CSIP20_CARDINALITY = "0..1";
    public static final String VALIDATION_REPORT_SPECIFICATION_CSIP20_LEVEL = "SHOULD";

    /* CSIP21 */
    public static final String VALIDATION_REPORT_SPECIFICATION_CSIP21_ID = "CSIP21";
    public static final String VALIDATION_REPORT_SPECIFICATION_CSIP21_NAME = "Reference to the document with the descriptive metadata";
    public static final String VALIDATION_REPORT_SPECIFICATION_CSIP21_LOCATION = "mets/dmdSec/mdRef";
    public static final String VALIDATION_REPORT_SPECIFICATION_CSIP21_DESCRIPTION = "Reference to the descriptive metadata file located in the “metadata” section of the IP.";
    public static final String VALIDATION_REPORT_SPECIFICATION_CSIP21_CARDINALITY = "0..1";
    public static final String VALIDATION_REPORT_SPECIFICATION_CSIP21_LEVEL = "SHOULD";

    /* CSIP22 */
    public static final String VALIDATION_REPORT_SPECIFICATION_CSIP22_ID = "CSIP22";
    public static final String VALIDATION_REPORT_SPECIFICATION_CSIP22_NAME = "Type of locator";
    public static final String VALIDATION_REPORT_SPECIFICATION_CSIP22_LOCATION = "mets/dmdSec/mdRef";
    public static final String VALIDATION_REPORT_SPECIFICATION_CSIP22_DESCRIPTION = "The locator type is always used with the value “URL” from the vocabulary in the attribute.";
    public static final String VALIDATION_REPORT_SPECIFICATION_CSIP22_CARDINALITY = "1..1";
    public static final String VALIDATION_REPORT_SPECIFICATION_CSIP22_LEVEL = "MUST";

    /* CSIP23 */
    public static final String VALIDATION_REPORT_SPECIFICATION_CSIP23_ID = "CSIP23";
    public static final String VALIDATION_REPORT_SPECIFICATION_CSIP23_NAME = "Type of link";
    public static final String VALIDATION_REPORT_SPECIFICATION_CSIP23_LOCATION = "mets/dmdSec/mdRef[@xlink:type=’simple’]";
    public static final String VALIDATION_REPORT_SPECIFICATION_CSIP23_DESCRIPTION = "Attribute used with the value “simple”. Value list is maintained by the xlink standard.";
    public static final String VALIDATION_REPORT_SPECIFICATION_CSIP23_CARDINALITY = "1..1";
    public static final String VALIDATION_REPORT_SPECIFICATION_CSIP23_LEVEL = "MUST";

    /* CSIP24 */
    public static final String VALIDATION_REPORT_SPECIFICATION_CSIP24_ID = "CSIP24";
    public static final String VALIDATION_REPORT_SPECIFICATION_CSIP24_NAME = "Resource location";
    public static final String VALIDATION_REPORT_SPECIFICATION_CSIP24_LOCATION = "mets/dmdSec/mdRef/@xlink:href";
    public static final String VALIDATION_REPORT_SPECIFICATION_CSIP24_DESCRIPTION = "The actual location of the resource. This specification recommends recording a URL type filepath in this attribute.";
    public static final String VALIDATION_REPORT_SPECIFICATION_CSIP24_CARDINALITY = "1..1";
    public static final String VALIDATION_REPORT_SPECIFICATION_CSIP24_LEVEL = "MUST";

    /* CSIP25 */
    public static final String VALIDATION_REPORT_SPECIFICATION_CSIP25_ID = "CSIP25";
    public static final String VALIDATION_REPORT_SPECIFICATION_CSIP25_NAME = "Type of metadata";
    public static final String VALIDATION_REPORT_SPECIFICATION_CSIP25_LOCATION = "mets/dmdSec/mdRef/@MDTYPE";
    public static final String VALIDATION_REPORT_SPECIFICATION_CSIP25_DESCRIPTION = "Specifies the type of metadata in the referenced file. Values are taken from the list provided by the METS.";
    public static final String VALIDATION_REPORT_SPECIFICATION_CSIP25_CARDINALITY = "1..1";
    public static final String VALIDATION_REPORT_SPECIFICATION_CSIP25_LEVEL = "MUST";

    /* CSIP26 */
    public static final String VALIDATION_REPORT_SPECIFICATION_CSIP26_ID = "CSIP26";
    public static final String VALIDATION_REPORT_SPECIFICATION_CSIP26_NAME = "File mime type";
    public static final String VALIDATION_REPORT_SPECIFICATION_CSIP26_LOCATION = "mets/dmdSec/mdRef/@MIMETYPE";
    public static final String VALIDATION_REPORT_SPECIFICATION_CSIP26_DESCRIPTION = "The IANA mime type of the referenced file.See also: IANA media types";
    public static final String VALIDATION_REPORT_SPECIFICATION_CSIP26_CARDINALITY = "1..1";
    public static final String VALIDATION_REPORT_SPECIFICATION_CSIP26_LEVEL = "MUST";

    /* CSIP27 */
    public static final String VALIDATION_REPORT_SPECIFICATION_CSIP27_ID = "CSIP27";
    public static final String VALIDATION_REPORT_SPECIFICATION_CSIP27_NAME = "File size";
    public static final String VALIDATION_REPORT_SPECIFICATION_CSIP27_LOCATION = "mets/dmdSec/mdRef/@SIZE";
    public static final String VALIDATION_REPORT_SPECIFICATION_CSIP27_DESCRIPTION = "Size of the referenced file in bytes.";
    public static final String VALIDATION_REPORT_SPECIFICATION_CSIP27_CARDINALITY = "1..1";
    public static final String VALIDATION_REPORT_SPECIFICATION_CSIP27_LEVEL = "MUST";

    /* CSIP28 */
    public static final String VALIDATION_REPORT_SPECIFICATION_CSIP28_ID = "CSIP28";
    public static final String VALIDATION_REPORT_SPECIFICATION_CSIP28_NAME = "File creation date";
    public static final String VALIDATION_REPORT_SPECIFICATION_CSIP28_LOCATION = "mets/dmdSec/mdRef/@CREATED";
    public static final String VALIDATION_REPORT_SPECIFICATION_CSIP28_DESCRIPTION = "The creation date of the referenced file.";
    public static final String VALIDATION_REPORT_SPECIFICATION_CSIP28_CARDINALITY = "1..1";
    public static final String VALIDATION_REPORT_SPECIFICATION_CSIP28_LEVEL = "MUST";

    /* CSIP29 */
    public static final String VALIDATION_REPORT_SPECIFICATION_CSIP29_ID = "CSIP29";
    public static final String VALIDATION_REPORT_SPECIFICATION_CSIP29_NAME = "File checksum";
    public static final String VALIDATION_REPORT_SPECIFICATION_CSIP29_LOCATION = "mets/dmdSec/mdRef/@CHECKSUM";
    public static final String VALIDATION_REPORT_SPECIFICATION_CSIP29_DESCRIPTION = "The checksum of the referenced file.";
    public static final String VALIDATION_REPORT_SPECIFICATION_CSIP29_CARDINALITY = "1..1";
    public static final String VALIDATION_REPORT_SPECIFICATION_CSIP29_LEVEL = "MUST";

    /* CSIP30 */
    public static final String VALIDATION_REPORT_SPECIFICATION_CSIP30_ID = "CSIP30";
    public static final String VALIDATION_REPORT_SPECIFICATION_CSIP30_NAME = "File checksum type";
    public static final String VALIDATION_REPORT_SPECIFICATION_CSIP30_LOCATION = "mets/dmdSec/mdRef/@CHECKSUMTYPE";
    public static final String VALIDATION_REPORT_SPECIFICATION_CSIP30_DESCRIPTION = "The type of checksum following the value list present in the METS-standard which has been used for calculating the checksum for the referenced file.";
    public static final String VALIDATION_REPORT_SPECIFICATION_CSIP30_CARDINALITY = "1..1";
    public static final String VALIDATION_REPORT_SPECIFICATION_CSIP30_LEVEL = "MUST";

    /* Get Name, Location, Description, Cardinality and Level for given specification ID */
    public static String getSpecificationName(String id){
        if (id.equals("CSIP1")) {
            return VALIDATION_REPORT_SPECIFICATION_CSIP1_NAME;
        }
        if (id.equals("CSIP2")) {
            return VALIDATION_REPORT_SPECIFICATION_CSIP2_NAME;
        }
        if (id.equals("CSIP3")) {
            return VALIDATION_REPORT_SPECIFICATION_CSIP3_NAME;
        }
        if (id.equals("CSIP4")) {
            return VALIDATION_REPORT_SPECIFICATION_CSIP4_NAME;
        }
        if (id.equals("CSIP5")) {
            return VALIDATION_REPORT_SPECIFICATION_CSIP5_NAME;
        }
        if (id.equals("CSIP6")) {
            return VALIDATION_REPORT_SPECIFICATION_CSIP6_NAME;
        }
        if(id.equals("CSIP117")){
            return VALIDATION_REPORT_SPECIFICATION_CSIP117_NAME;
        }
        if(id.equals("CSIP7")){
            return VALIDATION_REPORT_SPECIFICATION_CSIP7_NAME;
        }
        if(id.equals("CSIP8")){
            return VALIDATION_REPORT_SPECIFICATION_CSIP8_NAME;
        }
        if(id.equals("CSIP9")){
            return VALIDATION_REPORT_SPECIFICATION_CSIP9_NAME;
        }
        if(id.equals("CSIP10")){
            return VALIDATION_REPORT_SPECIFICATION_CSIP10_NAME;
        }
        if(id.equals("CSIP11")){
            return VALIDATION_REPORT_SPECIFICATION_CSIP11_NAME;
        }
        if(id.equals("CSIP12")){
            return VALIDATION_REPORT_SPECIFICATION_CSIP12_NAME;
        }
        if(id.equals("CSIP13")){
            return VALIDATION_REPORT_SPECIFICATION_CSIP13_NAME;
        }
        if(id.equals("CSIP14")){
            return VALIDATION_REPORT_SPECIFICATION_CSIP14_NAME;
        }
        if(id.equals("CSIP15")){
            return VALIDATION_REPORT_SPECIFICATION_CSIP15_NAME;
        }
        if(id.equals("CSIP16")){
            return VALIDATION_REPORT_SPECIFICATION_CSIP16_NAME;
        }
        if(id.equals("CSIP17")){
            return VALIDATION_REPORT_SPECIFICATION_CSIP17_NAME;
        }
        if(id.equals("CSIP18")){
            return VALIDATION_REPORT_SPECIFICATION_CSIP18_NAME;
        }
        if(id.equals("CSIP19")){
            return VALIDATION_REPORT_SPECIFICATION_CSIP19_NAME;
        }
        if(id.equals("CSIP20")){
            return VALIDATION_REPORT_SPECIFICATION_CSIP20_NAME;
        }
        if(id.equals("CSIP21")){
            return VALIDATION_REPORT_SPECIFICATION_CSIP21_NAME;
        }
        if(id.equals("CSIP22")){
            return VALIDATION_REPORT_SPECIFICATION_CSIP22_NAME;
        }
        if(id.equals("CSIP23")){
            return VALIDATION_REPORT_SPECIFICATION_CSIP23_NAME;
        }
        if(id.equals("CSIP24")){
            return VALIDATION_REPORT_SPECIFICATION_CSIP24_NAME;
        }
        if(id.equals("CSIP25")){
            return VALIDATION_REPORT_SPECIFICATION_CSIP25_NAME;
        }
        if(id.equals("CSIP26")){
            return VALIDATION_REPORT_SPECIFICATION_CSIP26_NAME;
        }
        if(id.equals("CSIP27")){
            return VALIDATION_REPORT_SPECIFICATION_CSIP27_NAME;
        }
        if(id.equals("CSIP28")){
            return VALIDATION_REPORT_SPECIFICATION_CSIP28_NAME;
        }
        if(id.equals("CSIP29")){
            return VALIDATION_REPORT_SPECIFICATION_CSIP29_NAME;
        }
        if(id.equals("CSIP30")){
            return VALIDATION_REPORT_SPECIFICATION_CSIP30_NAME;
        }
        return "Not Defined";
    }

    public static String getSpecificationLocation(String id){
        if (id.equals("CSIP1")) {
            return VALIDATION_REPORT_SPECIFICATION_CSIP1_LOCATION;
        }
        if (id.equals("CSIP2")) {
            return VALIDATION_REPORT_SPECIFICATION_CSIP2_LOCATION;
        }
        if (id.equals("CSIP3")) {
            return VALIDATION_REPORT_SPECIFICATION_CSIP3_LOCATION;
        }
        if (id.equals("CSIP4")) {
            return VALIDATION_REPORT_SPECIFICATION_CSIP4_LOCATION;
        }
        if (id.equals("CSIP5")) {
            return VALIDATION_REPORT_SPECIFICATION_CSIP5_LOCATION;
        }
        if (id.equals("CSIP6")) {
            return VALIDATION_REPORT_SPECIFICATION_CSIP6_LOCATION;
        }
        if(id.equals("CSIP117")){
            return VALIDATION_REPORT_SPECIFICATION_CSIP117_LOCATION;
        }
        if(id.equals("CSIP7")){
            return VALIDATION_REPORT_SPECIFICATION_CSIP7_LOCATION;
        }
        if(id.equals("CSIP8")){
            return VALIDATION_REPORT_SPECIFICATION_CSIP8_LOCATION;
        }
        if(id.equals("CSIP9")){
            return VALIDATION_REPORT_SPECIFICATION_CSIP9_LOCATION;
        }
        if(id.equals("CSIP10")){
            return VALIDATION_REPORT_SPECIFICATION_CSIP10_LOCATION;
        }
        if(id.equals("CSIP11")){
            return VALIDATION_REPORT_SPECIFICATION_CSIP11_LOCATION;
        }
        if(id.equals("CSIP12")){
            return VALIDATION_REPORT_SPECIFICATION_CSIP12_LOCATION;
        }
        if(id.equals("CSIP13")){
            return VALIDATION_REPORT_SPECIFICATION_CSIP13_LOCATION;
        }
        if(id.equals("CSIP14")){
            return VALIDATION_REPORT_SPECIFICATION_CSIP14_LOCATION;
        }
        if(id.equals("CSIP15")){
            return VALIDATION_REPORT_SPECIFICATION_CSIP15_LOCATION;
        }
        if(id.equals("CSIP16")){
            return VALIDATION_REPORT_SPECIFICATION_CSIP16_LOCATION;
        }
        if(id.equals("CSIP17")){
            return VALIDATION_REPORT_SPECIFICATION_CSIP17_LOCATION;
        }
        if(id.equals("CSIP18")){
            return VALIDATION_REPORT_SPECIFICATION_CSIP18_LOCATION;
        }
        if(id.equals("CSIP19")){
            return VALIDATION_REPORT_SPECIFICATION_CSIP19_LOCATION;
        }
        if(id.equals("CSIP20")){
            return VALIDATION_REPORT_SPECIFICATION_CSIP20_LOCATION;
        }
        if(id.equals("CSIP21")){
            return VALIDATION_REPORT_SPECIFICATION_CSIP21_LOCATION;
        }
        if(id.equals("CSIP22")){
            return VALIDATION_REPORT_SPECIFICATION_CSIP22_LOCATION;
        }
        if(id.equals("CSIP23")){
            return VALIDATION_REPORT_SPECIFICATION_CSIP23_LOCATION;
        }
        if(id.equals("CSIP24")){
            return VALIDATION_REPORT_SPECIFICATION_CSIP24_LOCATION;
        }
        if(id.equals("CSIP25")){
            return VALIDATION_REPORT_SPECIFICATION_CSIP25_LOCATION;
        }
        if(id.equals("CSIP26")){
            return VALIDATION_REPORT_SPECIFICATION_CSIP26_LOCATION;
        }
        if(id.equals("CSIP27")){
            return VALIDATION_REPORT_SPECIFICATION_CSIP27_LOCATION;
        }
        if(id.equals("CSIP28")){
            return VALIDATION_REPORT_SPECIFICATION_CSIP28_LOCATION;
        }
        if(id.equals("CSIP29")){
            return VALIDATION_REPORT_SPECIFICATION_CSIP29_LOCATION;
        }
        if(id.equals("CSIP30")){
            return VALIDATION_REPORT_SPECIFICATION_CSIP30_LOCATION;
        }
        return "Not Defined";
    }

    public static String getSpecificationDescription(String id){
        if (id.equals("CSIP1")) {
            return VALIDATION_REPORT_SPECIFICATION_CSIP1_DESCRIPTION;
        }
        if (id.equals("CSIP2")) {
            return VALIDATION_REPORT_SPECIFICATION_CSIP2_DESCRIPTION;
        }
        if (id.equals("CSIP3")) {
            return VALIDATION_REPORT_SPECIFICATION_CSIP3_DESCRIPTION;
        }
        if (id.equals("CSIP4")) {
            return VALIDATION_REPORT_SPECIFICATION_CSIP4_DESCRIPTION;
        }
        if (id.equals("CSIP5")) {
            return VALIDATION_REPORT_SPECIFICATION_CSIP5_DESCRIPTION;
        }
        if (id.equals("CSIP6")) {
            return VALIDATION_REPORT_SPECIFICATION_CSIP6_DESCRIPTION;
        }
        if(id.equals("CSIP117")){
            return VALIDATION_REPORT_SPECIFICATION_CSIP117_DESCRIPTION;
        }
        if(id.equals("CSIP7")){
            return VALIDATION_REPORT_SPECIFICATION_CSIP7_DESCRIPTION;
        }
        if(id.equals("CSIP8")){
            return VALIDATION_REPORT_SPECIFICATION_CSIP8_DESCRIPTION;
        }
        if(id.equals("CSIP9")){
            return VALIDATION_REPORT_SPECIFICATION_CSIP9_DESCRIPTION;
        }
        if(id.equals("CSIP10")){
            return VALIDATION_REPORT_SPECIFICATION_CSIP10_DESCRIPTION;
        }
        if(id.equals("CSIP11")){
            return VALIDATION_REPORT_SPECIFICATION_CSIP11_DESCRIPTION;
        }
        if(id.equals("CSIP12")){
            return VALIDATION_REPORT_SPECIFICATION_CSIP12_DESCRIPTION;
        }
        if(id.equals("CSIP13")){
            return VALIDATION_REPORT_SPECIFICATION_CSIP13_DESCRIPTION;
        }
        if(id.equals("CSIP14")){
            return VALIDATION_REPORT_SPECIFICATION_CSIP14_DESCRIPTION;
        }
        if(id.equals("CSIP15")){
            return VALIDATION_REPORT_SPECIFICATION_CSIP15_DESCRIPTION;
        }
        if(id.equals("CSIP16")){
            return VALIDATION_REPORT_SPECIFICATION_CSIP16_DESCRIPTION;
        }
        if(id.equals("CSIP17")){
            return VALIDATION_REPORT_SPECIFICATION_CSIP17_DESCRIPTION;
        }
        if(id.equals("CSIP18")){
            return VALIDATION_REPORT_SPECIFICATION_CSIP18_DESCRIPTION;
        }
        if(id.equals("CSIP19")){
            return VALIDATION_REPORT_SPECIFICATION_CSIP19_DESCRIPTION;
        }
        if(id.equals("CSIP20")){
            return VALIDATION_REPORT_SPECIFICATION_CSIP20_DESCRIPTION;
        }
        if(id.equals("CSIP21")){
            return VALIDATION_REPORT_SPECIFICATION_CSIP21_DESCRIPTION;
        }
        if(id.equals("CSIP22")){
            return VALIDATION_REPORT_SPECIFICATION_CSIP22_DESCRIPTION;
        }
        if(id.equals("CSIP23")){
            return VALIDATION_REPORT_SPECIFICATION_CSIP23_DESCRIPTION;
        }
        if(id.equals("CSIP24")){
            return VALIDATION_REPORT_SPECIFICATION_CSIP24_DESCRIPTION;
        }
        if(id.equals("CSIP25")){
            return VALIDATION_REPORT_SPECIFICATION_CSIP25_DESCRIPTION;
        }
        if(id.equals("CSIP26")){
            return VALIDATION_REPORT_SPECIFICATION_CSIP26_DESCRIPTION;
        }
        if(id.equals("CSIP27")){
            return VALIDATION_REPORT_SPECIFICATION_CSIP27_DESCRIPTION;
        }
        if(id.equals("CSIP28")){
            return VALIDATION_REPORT_SPECIFICATION_CSIP28_DESCRIPTION;
        }
        if(id.equals("CSIP29")){
            return VALIDATION_REPORT_SPECIFICATION_CSIP29_DESCRIPTION;
        }
        if(id.equals("CSIP30")){
            return VALIDATION_REPORT_SPECIFICATION_CSIP30_DESCRIPTION;
        }
        return "Not Defined";
    }

    public static String getSpecificationCardinality(String id){
        if (id.equals("CSIP1")) {
            return VALIDATION_REPORT_SPECIFICATION_CSIP1_CARDINALITY;
        }
        if (id.equals("CSIP2")) {
            return VALIDATION_REPORT_SPECIFICATION_CSIP2_CARDINALITY;
        }
        if (id.equals("CSIP3")) {
            return VALIDATION_REPORT_SPECIFICATION_CSIP3_CARDINALITY;
        }
        if (id.equals("CSIP4")) {
            return VALIDATION_REPORT_SPECIFICATION_CSIP4_CARDINALITY;
        }
        if (id.equals("CSIP5")) {
            return VALIDATION_REPORT_SPECIFICATION_CSIP5_CARDINALITY;
        }
        if (id.equals("CSIP6")) {
            return VALIDATION_REPORT_SPECIFICATION_CSIP6_CARDINALITY;
        }
        if(id.equals("CSIP117")){
            return VALIDATION_REPORT_SPECIFICATION_CSIP117_CARDINALITY;
        }
        if(id.equals("CSIP7")){
            return VALIDATION_REPORT_SPECIFICATION_CSIP7_CARDINALITY;
        }
        if(id.equals("CSIP8")){
            return VALIDATION_REPORT_SPECIFICATION_CSIP8_CARDINALITY;
        }
        if(id.equals("CSIP9")){
            return VALIDATION_REPORT_SPECIFICATION_CSIP9_CARDINALITY;
        }
        if(id.equals("CSIP10")){
            return VALIDATION_REPORT_SPECIFICATION_CSIP10_CARDINALITY;
        }
        if(id.equals("CSIP11")){
            return VALIDATION_REPORT_SPECIFICATION_CSIP11_CARDINALITY;
        }
        if(id.equals("CSIP12")){
            return VALIDATION_REPORT_SPECIFICATION_CSIP12_CARDINALITY;
        }
        if(id.equals("CSIP13")){
            return VALIDATION_REPORT_SPECIFICATION_CSIP13_CARDINALITY;
        }
        if(id.equals("CSIP14")){
            return VALIDATION_REPORT_SPECIFICATION_CSIP14_CARDINALITY;
        }
        if(id.equals("CSIP15")){
            return VALIDATION_REPORT_SPECIFICATION_CSIP15_CARDINALITY;
        }
        if(id.equals("CSIP16")){
            return VALIDATION_REPORT_SPECIFICATION_CSIP16_CARDINALITY;
        }
        if(id.equals("CSIP17")){
            return VALIDATION_REPORT_SPECIFICATION_CSIP17_CARDINALITY;
        }
        if(id.equals("CSIP18")){
            return VALIDATION_REPORT_SPECIFICATION_CSIP18_CARDINALITY;
        }
        if(id.equals("CSIP19")){
            return VALIDATION_REPORT_SPECIFICATION_CSIP19_CARDINALITY;
        }
        if(id.equals("CSIP20")){
            return VALIDATION_REPORT_SPECIFICATION_CSIP20_CARDINALITY;
        }
        if(id.equals("CSIP21")){
            return VALIDATION_REPORT_SPECIFICATION_CSIP21_CARDINALITY;
        }
        if(id.equals("CSIP22")){
            return VALIDATION_REPORT_SPECIFICATION_CSIP22_CARDINALITY;
        }
        if(id.equals("CSIP23")){
            return VALIDATION_REPORT_SPECIFICATION_CSIP23_CARDINALITY;
        }
        if(id.equals("CSIP24")){
            return VALIDATION_REPORT_SPECIFICATION_CSIP24_CARDINALITY;
        }
        if(id.equals("CSIP25")){
            return VALIDATION_REPORT_SPECIFICATION_CSIP25_CARDINALITY;
        }
        if(id.equals("CSIP26")){
            return VALIDATION_REPORT_SPECIFICATION_CSIP26_CARDINALITY;
        }
        if(id.equals("CSIP27")){
            return VALIDATION_REPORT_SPECIFICATION_CSIP27_CARDINALITY;
        }
        if(id.equals("CSIP28")){
            return VALIDATION_REPORT_SPECIFICATION_CSIP28_CARDINALITY;
        }
        if(id.equals("CSIP29")){
            return VALIDATION_REPORT_SPECIFICATION_CSIP29_CARDINALITY;
        }
        if(id.equals("CSIP30")){
            return VALIDATION_REPORT_SPECIFICATION_CSIP30_CARDINALITY;
        }
        return "Not Defined";
    }

    public static String getSpecificationLevel(String id){
        if (id.equals("CSIP1")) {
            return VALIDATION_REPORT_SPECIFICATION_CSIP1_LEVEL;
        }
        if (id.equals("CSIP2")) {
            return VALIDATION_REPORT_SPECIFICATION_CSIP2_LEVEL;
        }
        if (id.equals("CSIP3")) {
            return VALIDATION_REPORT_SPECIFICATION_CSIP3_LEVEL;
        }
        if (id.equals("CSIP4")) {
            return VALIDATION_REPORT_SPECIFICATION_CSIP4_LEVEL;
        }
        if (id.equals("CSIP5")) {
            return VALIDATION_REPORT_SPECIFICATION_CSIP5_LEVEL;
        }
        if (id.equals("CSIP6")) {
            return VALIDATION_REPORT_SPECIFICATION_CSIP6_LEVEL;
        }
        if(id.equals("CSIP117")){
            return VALIDATION_REPORT_SPECIFICATION_CSIP117_LEVEL;
        }
        if(id.equals("CSIP7")){
            return VALIDATION_REPORT_SPECIFICATION_CSIP7_LEVEL;
        }
        if(id.equals("CSIP8")){
            return VALIDATION_REPORT_SPECIFICATION_CSIP8_LEVEL;
        }
        if(id.equals("CSIP9")){
            return VALIDATION_REPORT_SPECIFICATION_CSIP9_LEVEL;
        }
        if(id.equals("CSIP10")){
            return VALIDATION_REPORT_SPECIFICATION_CSIP10_LEVEL;
        }
        if(id.equals("CSIP11")){
            return VALIDATION_REPORT_SPECIFICATION_CSIP11_LEVEL;
        }
        if(id.equals("CSIP12")){
            return VALIDATION_REPORT_SPECIFICATION_CSIP12_LEVEL;
        }
        if(id.equals("CSIP13")){
            return VALIDATION_REPORT_SPECIFICATION_CSIP13_LEVEL;
        }
        if(id.equals("CSIP14")){
            return VALIDATION_REPORT_SPECIFICATION_CSIP14_LEVEL;
        }
        if(id.equals("CSIP15")){
            return VALIDATION_REPORT_SPECIFICATION_CSIP15_LEVEL;
        }
        if(id.equals("CSIP16")){
            return VALIDATION_REPORT_SPECIFICATION_CSIP16_LEVEL;
        }
        if(id.equals("CSIP17")){
            return VALIDATION_REPORT_SPECIFICATION_CSIP17_LEVEL;
        }
        if(id.equals("CSIP18")){
            return VALIDATION_REPORT_SPECIFICATION_CSIP18_LEVEL;
        }
        if(id.equals("CSIP19")){
            return VALIDATION_REPORT_SPECIFICATION_CSIP19_LEVEL;
        }
        if(id.equals("CSIP20")){
            return VALIDATION_REPORT_SPECIFICATION_CSIP20_LEVEL;
        }
        if(id.equals("CSIP21")){
            return VALIDATION_REPORT_SPECIFICATION_CSIP21_LEVEL;
        }
        if(id.equals("CSIP22")){
            return VALIDATION_REPORT_SPECIFICATION_CSIP22_LEVEL;
        }
        if(id.equals("CSIP23")){
            return VALIDATION_REPORT_SPECIFICATION_CSIP23_LEVEL;
        }
        if(id.equals("CSIP24")){
            return VALIDATION_REPORT_SPECIFICATION_CSIP24_LEVEL;
        }
        if(id.equals("CSIP25")){
            return VALIDATION_REPORT_SPECIFICATION_CSIP25_LEVEL;
        }
        if(id.equals("CSIP26")){
            return VALIDATION_REPORT_SPECIFICATION_CSIP26_LEVEL;
        }
        if(id.equals("CSIP27")){
            return VALIDATION_REPORT_SPECIFICATION_CSIP27_LEVEL;
        }
        if(id.equals("CSIP28")){
            return VALIDATION_REPORT_SPECIFICATION_CSIP28_LEVEL;
        }
        if(id.equals("CSIP29")){
            return VALIDATION_REPORT_SPECIFICATION_CSIP29_LEVEL;
        }
        if(id.equals("CSIP30")){
            return VALIDATION_REPORT_SPECIFICATION_CSIP30_LEVEL;
        }
        return "Not Defined";
    }
}
