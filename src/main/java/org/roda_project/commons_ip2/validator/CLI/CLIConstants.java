package org.roda_project.commons_ip2.validator.CLI;

/** {@author João Gomes <jgomes@keep.pt>}. */
public final class CLIConstants {

  /**
   * JSON file extension constant.
   */
  public static final String JSON_FILE_EXTENSION = ".json";

  /* Options */

  /**
   * CLI option to validate.
   */
  public static final String CLI_OPTION_VALIDATE = "validate";

  /**
   * CLI option to create.
   */
  public static final String CLI_OPTION_CREATE = "create";

  /**
   * CLI option sip paths to validate.
   */
  public static final String CLI_OPTION_SIP_PATHS = "-i";

  /**
   * CLI option directory to save the report.
   */
  public static final String CLI_OPTION_REPORT_DIRECTORY = "-o";

  /**
   * CLI option to put the type of the report.
   */
  public static final String CLI_OPTION_REPORT_TYPE = "-r";

  /**
   * CLI option to use verbose.
   */
  public static final String CLI_OPTION_VERBOSE = "-v";

  /**
   * CLI option to give the metadata file.
   */
  public static final String CLI_CREATE_OPTION_METADATA_FILE = "-mf";

  /**
   * CLI option to give the metadata type.
   */
  public static final String CLI_CREATE_OPTION_METADATA_TYPE = "-mt";

  /**
   * CLI option to give the metadata version.
   */
  public static final String CLI_CREATE_OPTION_METADATA_VERSION = "-mv";

  /**
   * CLI option to add data to the representation.
   */
  public static final String CLI_CREATE_OPTION_REPRESENTATION_DATA = "-rd";

  /**
   * CLI option to choose the representation ID.
   */
  public static final String CLI_CREATE_OPTION_REPRESENTATION_ID = "-rid";

  /**
   * CLI option to choose the representation type.
   */
  public static final String CLI_CREATE_OPTION_REPRESENTATION_TYPE = "-rt";

  /**
   * CLI option to add ancestors to the SIP.
   */
  public static final String CLI_CREATE_OPTION_ANCESTORS = "-a";

  /**
   * CLI option to choose SIP ID.
   */
  public static final String CLI_CREATE_OPTION_SIP_ID = "-sid";

  /**
   * CLI option to add documentation to the SIP.
   */
  public static final String CLI_CREATE_OPTION_DOCUMENTATION = "-doc";

  /**
   * CLI option to give the path to save the SIP.
   */
  public static final String CLI_CREATE_OPTION_PATH = "-p";

  /**
   * CLI option to give the submitter agent name.
   */
  public static final String CLI_CREATE_OPTION_SUBMITTER_AGENT_NAME = "-san";

  /**
   * CLI option to give the submitter agent id.
   */

  public static final String CLI_CREATE_OPTION_SUBMITTER_AGENT_ID = "-aid";

  /**
   * CLI option to only add contents of target representation folder.
   */
  public static final String CLI_CREATE_OPTION_REPRESENTATION_DATA_ONLY_TARGET = "-to" ;

  /**
   * CLI option to give the checksum algorithm.
   */
  public static final String CLI_CREATE_OPTION_CHECKSUM_ALG = "-ca";

  /* OPTIONS WITHOUT "-" char */

  /**
   * Short option input without ident.
   */
  public static final String CLI_CREATE_SHORT_OPTION_INPUT_WITHOUT_IDENT = "i";

  /**
   * Short option output file without ident.
   */
  public static final String CLI_CREATE_SHORT_OPTION_OUTPUT_FILE_WITHOUT_IDENT = "o";

  /**
   * Short option type of report without ident.
   */
  public static final String CLI_VALIDATE_SHORT_OPTION_TYPE_OF_REPORT_WITHOUT_IDENT = "r";

  /**
   * Short option verbose without ident.
   */
  public static final String CLI_CREATE_SHORT_OPTION_VERBOSE_WITHOUT_IDENT = "v";

  /**
   * Long option metadata file without ident.
   */
  public static final String CLI_CREATE_LONG_OPTION_METADATA_FILE_WITHOUT_IDENT = "metadata-file";

  /**
   * Short option metadata file without ident.
   */
  public static final String CLI_CREATE_SHORT_OPTION_METADATA_FILE_WITHOUT_IDENT = "mf";

  /**
   * Long option metadata type without ident.
   */
  public static final String CLI_CREATE_LONG_OPTION_METADATA_TYPE_WITHOUT_IDENT = "metadata-type";

  /**
   * Short option metadata type without ident.
   */
  public static final String CLI_CREATE_SHORT_OPTION_METADATA_TYPE_WITHOUT_IDENT = "mt";

  /**
   * Long option metadata version without ident.
   */
  public static final String CLI_CREATE_LONG_OPTION_METADATA_VERSION_WITHOUT_IDENT = "metadata-version";

  /**
   * Short option metadata version without ident.
   */
  public static final String CLI_CREATE_SHORT_OPTION_METADATA_VERSION_WITHOUT_IDENT = "mv";

  /**
   * Long option representation data without ident.
   */
  public static final String CLI_CREATE_LONG_OPTION_REPRESENTATION_DATA_WITHOUT_IDENT = "representation-data";

  /**
   * Short option representation data without ident.
   */
  public static final String CLI_CREATE_SHORT_OPTION_REPRESENTATION_DATA_WITHOUT_IDENT = "rd";

  /**
   * Long option representation data only target files.
   */
  public static final String CLI_CREATE_LONG_OPTION_REPRESENTATION_DATA_ONLY_TARGET ="target-only" ;
  /**
   * Short option representation data only target files.
   */
  public static final String CLI_CREATE_SHORT_OPTION_REPRESENTATION_DATA_ONLY_TARGET = "to";

  /**
   * Long option representation type without ident.
   */
  public static final String CLI_CREATE_LONG_OPTION_REPRESENTATION_TYPE_WITHOUT_IDENT = "representation-type";

  /**
   * Short option representation type without ident.
   */
  public static final String CLI_CREATE_SHORT_OPTION_REPRESENTATION_TYPE_WITHOUT_IDENT = "rt";

  /**
   * Long option representation id without ident.
   */
  public static final String CLI_CREATE_LONG_OPTION_REPRESENTATION_ID_WITHOUT_IDENT = "representation-id";

  /**
   * Long option checksum algorithm.
   */
  public static final String CLI_CREATE_LONG_OPTION_CHECKSUM_ALG = "checksum-alg";

  /**
   * Short option checksum algorithm.
   */
  public static final String CLI_CREATE_SHORT_OPTION_CHECKSUM_ALG = "ca";

  /**
   * Short option representation id without ident.
   */
  public static final String CLI_CREATE_SHORT_OPTION_REPRESENTATION_ID_WITHOUT_IDENT = "rid";

  /**
   * Long option SIP id without ident.
   */
  public static final String CLI_CREATE_LONG_OPTION_SIP_ID_WITHOUT_IDENT = "sip-id";

  /**
   * Short option SIP id without ident.
   */
  public static final String CLI_CREATE_SHORT_OPTION_SIP_ID_WITHOUT_IDENT = "sid";

  /**
   * Long option Ancestors without ident.
   */
  public static final String CLI_CREATE_LONG_OPTION_ANCESTORS_WITHOUT_IDENT = "ancestors";

  /**
   * Short option Ancestors without ident.
   */
  public static final String CLI_CREATE_SHORT_OPTION_ANCESTORS_WITHOUT_IDENT = "a";

  /**
   * Long option Documentation without ident.
   */
  public static final String CLI_CREATE_LONG_OPTION_DOCUMENTATION_WITHOUT_IDENT = "documentation";

  /**
   * Short option Documentation without ident.
   */
  public static final String CLI_CREATE_SHORT_DOCUMENTATION_WITHOUT_IDENT = "doc";

  /**
   * Long option path without ident.
   */
  public static final String CLI_CREATE_LONG_OPTION_PATH_WITHOUT_IDENT = "path";

  /**
   * Short option path without ident.
   */
  public static final String CLI_CREATE_SHORT_OPTION_PATH_WITHOUT_IDENT = "p";

  /**
   * Long option submitter agent name without ident.
   */
  public static final String CLI_CREATE_LONG_OPTION_SUBMITTER_AGENT_NAME_WITHOUT_IDENT = "submitter-agent-name";

  /**
   * Short option Ancestors without ident.
   */
  public static final String CLI_CREATE_SHORT_OPTION_SUBMITTER_AGENT_NAME_WITHOUT_IDENT = "san";

  /**
   * Long option submitter agent id without ident.
   */
  public static final String CLI_CREATE_LONG_OPTION_SUBMITTER_AGENT_ID_WITHOUT_IDENT = "submitter-agent-id";

  /**
   * Short option Ancestors without ident.
   */
  public static final String CLI_CREATE_SHORT_OPTION_SUBMITTER_AGENT_ID_WITHOUT_IDENT = "aid";

  /*
   * PRINT CHARS UTILS
   * 
   */
  /**
   * Constant to the end of line.
   */
  public static final String END_OF_LINE = "\n";

  /**
   * Double end of line constant.
   */
  public static final String DOUBLE_END_OF_LINE = "\n\n";

  /**
   * Tab constant.
   */
  public static final String TAB = "\t";
  /**
   * Double tab constant.
   */
  public static final String DOUBLE_TAB = "\t\t";
  /**
   * Constant to the "Commands:" text.
   */
  public static final String COMMANDS_KEY = "Commands:";

  private CLIConstants() {
    // do nothing
  }
}
