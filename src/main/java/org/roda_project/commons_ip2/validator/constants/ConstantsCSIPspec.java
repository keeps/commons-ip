package org.roda_project.commons_ip2.validator.constants;

/**
 * @author João Gomes <jgomes@keep.pt>
 */
public class ConstantsCSIPspec {
    private ConstantsCSIPspec(){
    }

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
        return "Not Defined";
    }
}
