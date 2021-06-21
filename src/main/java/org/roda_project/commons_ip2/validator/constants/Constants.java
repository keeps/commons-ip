package org.roda_project.commons_ip2.validator.constants;

/**
 * @author João Gomes <jgomes@keep.pt>
 */
public class Constants {
  private Constants() {
  }

  /* File Paths */
  public static final String PATH_RESOURCES_CSIP_VOCABULARY_CONTENT_CATEGORY = "./src/main/resources/controlledVocabularies/CSIPVocabularyContentCategory.xml";
  public static final String PATH_RESOURCES_CSIP_VOCABULARY_CONTENT_INFORMATION_TYPE = "./src/main/resources/controlledVocabularies/CSIPVocabularyContentInformationType.xml";
  public static final String VALIDATION_REPORT_PREFIX = "commons-ip-validation-reporter";

  /* Report Header */
  public static final String VALIDATION_REPORT_HEADER_KEY_TITLE = "title";
  public static final String VALIDATION_REPORT_HEADER_TITLE = "Validation Report CSIP";
  public static final String VALIDATION_REPORT_HEADER_KEY_HEADER = "header";
  public static final String VALIDATION_REPORT_HEADER_KEY_SPECIFICATIONS = "specifications";
  public static final String VALIDATION_REPORT_KEY_ID = "id";
  public static final String VALIDATION_REPORT_HEADER_CSIP_VERSION = "CSIP-2.0.4";
  public static final String VALIDATION_REPORT_HEADER_SIP_VERSION = "SIP-2.0.4";
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

  /* Modules Names */
  public static final String CSIP_MODULE_NAME_1 = "Use of the METS root element (element mets)";
}
