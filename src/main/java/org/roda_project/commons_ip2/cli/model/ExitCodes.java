package org.roda_project.commons_ip2.cli.model;

/** {@author João Gomes <jgomes@keep.pt>}. */
public final class ExitCodes {
  /**
   * Exit code success.
   */
  public static final int EXIT_CODE_OK = 0;
  /**
   * Exit code when can't create the report.
   */
  public static final int EXIT_CANNOT_CREATE_REPORT = 3;
  /**
   * Exit code when can't create the directory.
   */
  public static final int EXIT_CODE_CREATE_DIRECTORY_FAILS = 4;
  /**
   * Exit code when the date format is invalid.
   */
  public static final int EXIT_CODE_INVALID_DATE_FORMAT = 5;
  /**
   * Exit code when can't create EARK REPORT object.
   */
  public static final int EXIT_CANNOT_CREATE_EARKVALIDATOR_OBJECT = 7;

  /**
   * Exit code when missing args to execute the CLI.
   */
  public static final int EXIT_CODE_CREATE_MISSING_ARGS = 2;

  /**
   * Exit code when fails to create the SIP.
   */
  public static final int EXIT_CODE_CREATE_CANNOT_SIP = 3;

  /**
   * Exit code when the given paths are invalid.
   */
  public static final int EXIT_CODE_CREATE_INVALID_PATHS = 4;

  private ExitCodes() {
    // do nothing.
  }
}
