package org.roda_project.commons_ip2.validator.constants;

/** {@author João Gomes <jgomes@keep.pt>}. */
public final class Constants {

  /**
   * Constant separator.
   */
  public static final String SEPARATOR = "/";

  public static final String END_OF_LINE = "\n";

  public static final String OPEN_SQUARE_BRACKET = "[";

  public static final String CLOSE_SQUARE_BRACKET = "]";

  public static final String METS_FILE = "METS.xml";

  public static final String EMPTY_SPACE = " ";
  /* File Paths */

  public static final String PATH_RESOURCES_CONTROLLED_VOCABULARIES = "/controlledVocabularies";
  public static final String PATH_RESOURCES_CSIP_VOCABULARY_CONTENT_CATEGORY = PATH_RESOURCES_CONTROLLED_VOCABULARIES
    + "/CSIPVocabularyContentCategory.xml";
  public static final String PATH_RESOURCES_CSIP_VOCABULARY_CONTENT_INFORMATION_TYPE = PATH_RESOURCES_CONTROLLED_VOCABULARIES
    + "/CSIPVocabularyContentInformationType.xml";
  public static final String PATH_RESOURCES_CSIP_VOCABULARY_OAIS_PACKAGE_TYPE = PATH_RESOURCES_CONTROLLED_VOCABULARIES
    + "/CSIPVocabularyOAISPackageType.xml";
  public static final String PATH_RESOURCES_CSIP_VOCABULARY_DMD_SEC_STATUS = PATH_RESOURCES_CONTROLLED_VOCABULARIES
    + "/CSIPVocabularyStatus.xml";
  public static final String PATH_RESOURCES_CSIP_VOCABULARY_RECORD_STATUS = PATH_RESOURCES_CONTROLLED_VOCABULARIES
    + "/SIPVocabularyRecordStatus.xml";
  public static final String PATH_RESOURCES_CSIP_VOCABULARY_IANA_MEDIA_TYPES = PATH_RESOURCES_CONTROLLED_VOCABULARIES
    + "/IANA_MEDIA_TYPES.txt";
  public static final String VALIDATION_REPORT_PREFIX = "commons-ip-validation-reporter";
  public static final int LIMIT_MISSING_FILES = 3;
  /* Report Header */
  public static final String VALIDATION_REPORT_HEADER_KEY_TITLE = "title";
  public static final String VALIDATION_REPORT_HEADER_TITLE = "Validation Report";
  public static final String VALIDATION_REPORT_HEADER_KEY_HEADER = "header";
  public static final String VALIDATION_REPORT_HEADER_KEY_SPECIFICATIONS = "specifications";
  public static final String VALIDATION_REPORT_KEY_ID = "id";
  public static final String VALIDATION_REPORT_HEADER_CSIP_VERSION = "CSIP-2.0.4";
  public static final String VALIDATION_REPORT_HEADER_SIP_VERSION = "SIP-2.0.4";
  public static final String VALIDATION_REPORT_HEADER_AIP_VERSION = "AIP-2.0.4";
  public static final String VALIDATION_REPORT_HEADER_SPECIFICATIONS_KEY_URL = "url";
  public static final String VALIDATION_REPORT_SPECIFICATION_KEY_PATH = "path";

  public static final String VALIDATION_REPORT_HEADER_URL_DILCIS = "https://github.com/DILCISBoard/";
  public static final String VALIDATION_REPORT_HEADER_SPECIFICATIONS_URL_CSIP = VALIDATION_REPORT_HEADER_URL_DILCIS
    + "E-ARK-CSIP/releases/tag/v2.0.4";
  public static final String VALIDATION_REPORT_HEADER_SPECIFICATIONS_URL_SIP = VALIDATION_REPORT_HEADER_URL_DILCIS
    + "E-ARK-SIP/releases/tag/v2.0.4";
  public static final String VALIDATION_REPORT_HEADER_SPECIFICATIONS_URL_AIP = VALIDATION_REPORT_HEADER_URL_DILCIS
    + "E-ARK-AIP/releases/tag/v2.0.4";
  /* Depois vai ser removida */
  public static final String VALIDATION_REPORT_SPECIFICATION_COMMONS_IP_VERSION = "2.0.0";
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
  public static final String VALIDATION_REPORT_SPECIFICATION_KEY_SKIPPED = "skipped";
  public static final String VALIDATION_REPORT_SPECIFICATION_KEY_NOTES = "notes";
  public static final String VALIDATION_REPORT_SPECIFICATION_KEY_NAME = "name";
  public static final String VALIDATION_REPORT_SPECIFICATION_KEY_LOCATION = "location";
  public static final String VALIDATION_REPORT_SPECIFICATION_KEY_DESCRIPTION = "description";
  public static final String VALIDATION_REPORT_SPECIFICATION_KEY_CARDINALITY = "cardinality";
  public static final String VALIDATION_REPORT_SPECIFICATION_KEY_LEVEL = "level";
  public static final String VALIDATION_REPORT_SPECIFICATION_KEY_TESTING = "testing";
  public static final String VALIDATION_REPORT_SPECIFICATION_KEY_TESTING_OUTCOME = "outcome";
  public static final String VALIDATION_REPORT_SPECIFICATION_KEY_TESTING_DETAIL = "detail";
  public static final String VALIDATION_REPORT_SPECIFICATION_KEY_TESTING_ISSUES = "issues";
  public static final String VALIDATION_REPORT_SPECIFICATION_KEY_TESTING_WARNINGS = "warnings";
  public static final String VALIDATION_REPORT_SPECIFICATION_KEY_TESTING_NOTES = "notes";
  /* Report Status */
  public static final String VALIDATION_REPORT_SPECIFICATION_TESTING_OUTCOME_PASSED = "PASSED";
  public static final String VALIDATION_REPORT_SPECIFICATION_TESTING_OUTCOME_FAILED = "FAILED";
  public static final String VALIDATION_REPORT_SPECIFICATION_TESTING_OUTCOME_SKIPPED = "SKIPPED";
  public static final String VALIDATION_REPORT_SPECIFICATION_RESULT_VALID = "VALID";
  public static final String VALIDATION_REPORT_SPECIFICATION_RESULT_INVALID = "INVALID";
  public static final String VALIDATION_REPORT_SPECIFICATION_RESULT_ERROR = "ERROR";
  public static final String VALIDATION_REPORT_SPECIFICATION_KEY_ERROR = "fileErrors";
  /* Modules Names */
  public static final String CSIP_MODULE_NAME_0 = "Validation of the received sip";
  public static final String CSIP_MODULE_NAME_1 = "Use of the METS root element (element mets)";
  public static final String CSIP_MODULE_NAME_2 = "Use of the METS header (element metsHdr)";
  public static final String CSIP_MODULE_NAME_3 = "Use of the METS descriptive metadata section (element dmdSec)";
  public static final String CSIP_MODULE_NAME_4 = "Use of the METS administrative metadata section (element amdSec)";
  public static final String CSIP_MODULE_NAME_5 = "Use of the METS file section (element fileSec)";
  public static final String CSIP_MODULE_NAME_6 = "Use of the METS structural map (<structMap>)";
  public static final String SIP_MODULE_NAME_1 = "Extended use of the METS root element (element mets)";
  public static final String SIP_MODULE_NAME_2 = "Extended use of the METS header (element metsHdr)";
  public static final String SIP_MODULE_NAME_3 = "Extended use of the METS file section (element fileSec)";
  public static final String AIP_MODULE_NAME_2 = " Structural metadata - Digital objects";

  private Constants() {
  }
}
