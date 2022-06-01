package org.roda_project.commons_ip2.validator.utils;

import org.roda_project.commons_ip2.validator.CLI.CLIConstants;

import java.io.PrintStream;

/**
 * {@author João Gomes <jgomes@keep.pt>}.
 */
public class CLIUtils {

  private CLIUtils() {
    // do nothing
  }

  /**
   * If first option validate is missing, print the first option to the user know.
   *
   * @param printStream
   *          {@link PrintStream}
   */
  public static void printUsage(PrintStream printStream) {
    final StringBuilder out = new StringBuilder();

    out.append("Usage: Commons-ip validator COMMAND [OPTIONS]\n");

    out.append("\n");
    out.append("Commands:");
    out.append("\n\n");
    out.append("\t").append(CLIConstants.CLI_OPTION_VALIDATE).append("\t\t").append("Validate a SIP file").append("\n");

    out.append("\n");

    out.append("Usage: Commons-ip create COMMAND [OPTIONS]\n");

    out.append("\n");
    out.append("Commands:");
    out.append("\n\n");
    out.append("\t").append(CLIConstants.CLI_OPTION_CREATE).append("\t\t").append("Create a SIP Shallow file")
      .append("\n");

    out.append("\n");
    printStream.append(out).flush();
  }

  public static void printErrors(PrintStream printStream, String message) {
    StringBuilder out = new StringBuilder();

    out.append("ERROR\n");

    out.append("\n");
    out.append(message);
    out.append("\n\n");
    out.append("\n");
    printStream.append(out).flush();
  }

}
