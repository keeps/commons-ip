package org.roda_project.commons_ip2.validator.constants;

/**
 * @author João Gomes <jgomes@keep.pt>
 */
public class Constants {
  private Constants() {
  }

  public static final String VALIDATION_REPORT_PREFIX = "commons-ip-validation-reporter";
  /* Report Header */
  public static final String VALIDATION_REPORT_HEADER_KEY_TITLE = "title";
  public static final String VALIDATION_REPORT_HEADER_TITLE = "Validation Report CSIP";
  public static final String VALIDATION_REPORT_HEADER_KEY_HEADER = "header";
  public static final String VALIDATION_REPORT_HEADER_KEY_SPECIFICATIONS = "specifications";
  public static final String VALIDATION_REPORT_KEY_ID = "id";
  public static final String VALIDATION_REPORT_HEADER_ID_CSIP = "CSIP-2.0.4";
  public static final String VALIDATION_REPORT_HEADER_ID_SIP = "SIP-2.0.4";
  public static final String VALIDATION_REPORT_HEADER_SPECIFICATIONS_KEY_URL = "url";
  public static final String VALIDATION_REPORT_HEADER_SPECIFICATIONS_URL_CSIP = "https://github.com/DILCISBoard/E-ARK-CSIP/releases/tag/v2.0.4";
  public static final String VALIDATION_REPORT_HEADER_SPECIFICATIONS_URL_SIP = "https://github.com/DILCISBoard/E-ARK-SIP/releases/tag/v2.0.4";

  /* Depois vai ser removida */
  public static final String VALIDATION_REPORT_SPECIFICATION_COMMONS_IP_VERSION = "2.0.0-alpha3-SNAPSHOT";

  /* Report Validation */
  public static final String VALIDATION_REPORT_SPECIFICATION_KEY_SPECIFICATION = "specification";
  public static final String VALIDATION_REPORT_SPECIFICATION_KEY_VERSION_COMMONS_IP = "version_commons_ip";
  public static final String VALIDATION_REPORT_SPECIFICATION_KEY_DATE = "date";
  public static final String VALIDATION_REPORT_SPECIFICATION_KEY_VALIDATION = "validation";
  public static final String VALIDATION_REPORT_SPECIFICATION_KEY_RESULT = "result";
  public static final String VALIDATION_REPORT_SPECIFICATION_KEY_SUMMARY = "summary";
  public static final String VALIDATION_REPORT_SPECIFICATION_KEY_SUCCESS = "success";
  public static final String VALIDATION_REPORT_SPECIFICATION_KEY_WARNINGS = "warnings";
  public static final String VALIDATION_REPORT_SPECIFICATION_KEY_ERRORS = "errors";
  public static final String VALIDATION_REPORT_SPECIFICATION_KEY_NAME = "name";
  public static final String VALIDATION_REPORT_SPECIFICATION_KEY_LOCATION = "location";
  public static final String VALIDATION_REPORT_SPECIFICATION_KEY_DESCRIPTION = "description";
  public static final String VALIDATION_REPORT_SPECIFICATION_KEY_CARDINALITY = "cardinality";
  public static final String VALIDATION_REPORT_SPECIFICATION_KEY_LEVEL = "level";
  public static final String VALIDATION_REPORT_SPECIFICATION_KEY_OUTCOME = "outcome";
  public static final String VALIDATION_REPORT_SPECIFICATION_KEY_OUTCOME_VALID = "valid";
  public static final String VALIDATION_REPORT_SPECIFICATION_KEY_OUTCOME_DETAIL = "detail";

  /* CSIP1 */
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


  /* Get Name, Location, Description, Cardinality and Level for given specification ID */
  public static String getSpecificationName(String id){
    if (id.equals("CSIP1")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP1_NAME;
    }
    if (id.equals("CSIP2")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP2_NAME;
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
    return "Not Defined";
  }

  public static String getSpecificationDescription(String id){
    if (id.equals("CSIP1")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP1_DESCRIPTION;
    }
    if (id.equals("CSIP2")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP2_DESCRIPTION;
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
    return "Not Defined";
  }

  public static String getSpecificationLevel(String id){
    if (id.equals("CSIP1")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP1_LEVEL;
    }
    if (id.equals("CSIP2")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP2_LEVEL;
    }
    return "Not Defined";
  }
}
