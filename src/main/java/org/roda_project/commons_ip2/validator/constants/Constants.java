package org.roda_project.commons_ip2.validator.constants;

/** {@author João Gomes <jgomes@keep.pt>}. */
public final class Constants {

  /**
   * Constant separator.
   */
  public static final String SEPARATOR = "/";

  /**
   * Constant end of line.
   */
  public static final String END_OF_LINE = "\n";

  /**
   * Constant open square bracket.
   */
  public static final String OPEN_SQUARE_BRACKET = "[";

  /**
   * Constant close square bracket.
   */
  public static final String CLOSE_SQUARE_BRACKET = "]";

  /**
   * Constant METS file.
   */
  public static final String METS_FILE = "METS.xml";

  /**
   * Constant ID type CSIP.
   */
  public static final String ID_TYPE_CSIP = "CSIP";

  /**
   * Constant ID type SIP.
   */
  public static final String ID_TYPE_SIP = "SIP";

  /**
   * Constant ID type AIP.
   */
  public static final String ID_TYPE_AIP = "AIP";

  /**
   * Constant requirement level "SHOULD".
   */
  public static final String REQUIREMENT_LEVEL_SHOULD = "SHOULD";

  /**
   * Constant requirement level "MUST".
   */
  public static final String REQUIREMENT_LEVEL_MUST = "MUST";

  /**
   * Constant requirement level "MAY".
   */
  public static final String REQUIREMENT_LEVEL_MAY = "MAY";
  /**
   * Constant empty space.
   */
  public static final String EMPTY_SPACE = " ";
  /* File Paths */

  /**
   * Constant path to the controlled vocabularies.
   */
  public static final String PATH_RESOURCES_CONTROLLED_VOCABULARIES = "/controlledVocabularies";

  /**
   * Constant path to the CSIPVocabularyContentCategory file.
   */
  public static final String PATH_RESOURCES_CSIP_VOCABULARY_CONTENT_CATEGORY = PATH_RESOURCES_CONTROLLED_VOCABULARIES
    + "/CSIPVocabularyContentCategory.xml";

  /**
   * Constant path to the CSIPVocabularyContentInformationType file.
   */
  public static final String PATH_RESOURCES_CSIP_VOCABULARY_CONTENT_INFORMATION_TYPE = PATH_RESOURCES_CONTROLLED_VOCABULARIES
    + "/CSIPVocabularyContentInformationType.xml";

  /**
   * Constant path to the OAIS Package Types file.
   */
  public static final String PATH_RESOURCES_CSIP_VOCABULARY_OAIS_PACKAGE_TYPE = PATH_RESOURCES_CONTROLLED_VOCABULARIES
    + "/CSIPVocabularyOAISPackageType.xml";

  /**
   * Constant path to the Vocabulary Status file.
   */
  public static final String PATH_RESOURCES_CSIP_VOCABULARY_DMD_SEC_STATUS = PATH_RESOURCES_CONTROLLED_VOCABULARIES
    + "/CSIPVocabularyStatus.xml";

  /**
   * Constant path to the SIP Record Status file.
   */
  public static final String PATH_RESOURCES_CSIP_VOCABULARY_RECORD_STATUS = PATH_RESOURCES_CONTROLLED_VOCABULARIES
    + "/SIPVocabularyRecordStatus.xml";

  /**
   * Constant path to the IANA media types file.
   */
  public static final String PATH_RESOURCES_CSIP_VOCABULARY_IANA_MEDIA_TYPES = PATH_RESOURCES_CONTROLLED_VOCABULARIES
    + "/IANA_MEDIA_TYPES.txt";

  /**
   * Constant validation report prefix.
   */
  public static final String VALIDATION_REPORT_PREFIX = "commons-ip-validation-reporter";

  /**
   * Constant with the limit of missing files.
   */
  public static final int LIMIT_MISSING_FILES = 3;
  /* Report Header */

  /**
   * Constant validation report key "title".
   */
  public static final String VALIDATION_REPORT_HEADER_KEY_TITLE = "title";

  /**
   * Constant validation report value to the key "title".
   */
  public static final String VALIDATION_REPORT_HEADER_TITLE = "Validation Report";

  /**
   * Constant validation report key "header".
   */

  public static final String VALIDATION_REPORT_HEADER_KEY_HEADER = "header";

  /**
   * Constant validation report key "specifications".
   */
  public static final String VALIDATION_REPORT_HEADER_KEY_SPECIFICATIONS = "specifications";

  /**
   * Constant validation report key "id".
   */
  public static final String VALIDATION_REPORT_KEY_ID = "id";

  /**
   * Constant validation report value to the key "header".
   */
  public static final String VALIDATION_REPORT_HEADER_CSIP_VERSION = "CSIP-2.0.4";

  /**
   * Constant validation report value to the key "header".
   */
  public static final String VALIDATION_REPORT_HEADER_SIP_VERSION = "SIP-2.0.4";

  /**
   * Constant validation report value to the key "header".
   */
  public static final String VALIDATION_REPORT_HEADER_AIP_VERSION = "AIP-2.0.4";

  /**
   * Constant validation report key "url".
   */
  public static final String VALIDATION_REPORT_HEADER_SPECIFICATIONS_KEY_URL = "url";

  /**
   * Constant validation report key "path".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_KEY_PATH = "path";

  /**
   * Constant validation report value to the key "url".
   */
  public static final String VALIDATION_REPORT_HEADER_URL_DILCIS = "https://github.com/DILCISBoard/";

  /**
   * Constant validation report value to the key "url".
   */
  public static final String VALIDATION_REPORT_HEADER_SPECIFICATIONS_URL_CSIP = VALIDATION_REPORT_HEADER_URL_DILCIS
    + "E-ARK-CSIP/releases/tag/v2.0.4";
  /**
   * Constant validation report value to the key "url".
   */
  public static final String VALIDATION_REPORT_HEADER_SPECIFICATIONS_URL_SIP = VALIDATION_REPORT_HEADER_URL_DILCIS
    + "E-ARK-SIP/releases/tag/v2.0.4";
  /**
   * Constant validation report value to the key "url".
   */
  public static final String VALIDATION_REPORT_HEADER_SPECIFICATIONS_URL_AIP = VALIDATION_REPORT_HEADER_URL_DILCIS
    + "E-ARK-AIP/releases/tag/v2.0.4";

  /* Report Validation */
  /**
   * Constant validation report key "specification".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_KEY_SPECIFICATION = "specification";
  /**
   * Constant validation report key "version".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_KEY_VERSION_COMMONS_IP = "version_commons_ip";
  /**
   * Constant validation report key "date".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_KEY_DATE = "date";
  /**
   * Constant validation report key "validation".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_KEY_VALIDATION = "validation";
  /**
   * Constant validation report key "result".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_KEY_RESULT = "result";
  /**
   * Constant validation report key "summary".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_KEY_SUMMARY = "summary";
  /**
   * Constant validation report key "success".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_KEY_SUCCESS = "success";

  /**
   * Constant validation report key "warnings".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_KEY_WARNINGS = "warnings";
  /**
   * Constant validation report key "errors".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_KEY_ERRORS = "errors";

  /**
   * Constant validation report key "skipped".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_KEY_SKIPPED = "skipped";

  /**
   * Constant validation report key "notes".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_KEY_NOTES = "notes";

  /**
   * Constant validation report key "name".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_KEY_NAME = "name";

  /**
   * Constant validation report key "location".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_KEY_LOCATION = "location";

  /**
   * Constant validation report key "description".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_KEY_DESCRIPTION = "description";

  /**
   * Constant validation report key "cardinality".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_KEY_CARDINALITY = "cardinality";

  /**
   * Constant validation report key "level".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_KEY_LEVEL = "level";

  /**
   * Constant validation report key "testing".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_KEY_TESTING = "testing";

  /**
   * Constant validation report key "outcome".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_KEY_TESTING_OUTCOME = "outcome";

  /**
   * Constant validation report key "detail".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_KEY_TESTING_DETAIL = "detail";

  /**
   * Constant validation report key "issues".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_KEY_TESTING_ISSUES = "issues";

  /**
   * Constant validation report key "warnings".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_KEY_TESTING_WARNINGS = "warnings";

  /**
   * Constant validation report key "notes".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_KEY_TESTING_NOTES = "notes";

  /* Report Status */
  /**
   * Constant validation report key "passed".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_TESTING_OUTCOME_PASSED = "PASSED";

  /**
   * Constant validation report key "failed".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_TESTING_OUTCOME_FAILED = "FAILED";

  /**
   * Constant validation report key "skipped".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_TESTING_OUTCOME_SKIPPED = "SKIPPED";

  /**
   * Constant validation report key "valid".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_RESULT_VALID = "VALID";

  /**
   * Constant validation report key "invalid".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_RESULT_INVALID = "INVALID";

  /**
   * Constant validation report key "error".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_RESULT_ERROR = "ERROR";

  /**
   * Constant validation report key "fileErrors".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_KEY_ERROR = "fileErrors";
  /* Modules Names */

  /**
   * Constant Module Name 0.
   */
  public static final String CSIP_MODULE_NAME_0 = "Validation of the received sip";

  /**
   * Constant Module Name "Use of the METS root element (element mets)".
   */
  public static final String CSIP_MODULE_NAME_1 = "Use of the METS root element (element mets)";

  /**
   * Constant Module Name "Use of the METS header (element metsHdr)".
   */
  public static final String CSIP_MODULE_NAME_2 = "Use of the METS header (element metsHdr)";

  /**
   * Constant Module Name "Use of the METS descriptive metadata section (element
   * dmdSec)".
   */
  public static final String CSIP_MODULE_NAME_3 = "Use of the METS descriptive metadata section (element dmdSec)";

  /**
   * Constant Module Name "Use of the METS administrative metadata section
   * (element amdSec)".
   */
  public static final String CSIP_MODULE_NAME_4 = "Use of the METS administrative metadata section (element amdSec)";

  /**
   * Constant Module Name "Use of the METS file section (element fileSec)".
   */
  public static final String CSIP_MODULE_NAME_5 = "Use of the METS file section (element fileSec)";

  /**
   * Constant Module Name "Use of the METS structural map (<structMap>)".
   */
  public static final String CSIP_MODULE_NAME_6 = "Use of the METS structural map (<structMap>)";

  /**
   * Constant Module Name "Extended use of the METS root element (element mets)".
   */
  public static final String SIP_MODULE_NAME_1 = "Extended use of the METS root element (element mets)";

  /**
   * Constant Module Name "Extended use of the METS header (element metsHdr)".
   */
  public static final String SIP_MODULE_NAME_2 = "Extended use of the METS header (element metsHdr)";

  /**
   * Constant Module Name "Extended use of the METS file section (element
   * fileSec)".
   */
  public static final String SIP_MODULE_NAME_3 = "Extended use of the METS file section (element fileSec)";

  /**
   * Constant Module Name "Structural metadata - Digital objects".
   */
  public static final String AIP_MODULE_NAME_2 = " Structural metadata - Digital objects";

  public static final String PROPERTY_KEY_HOME = "commonsIp.home";

  private Constants() {
  }
}
