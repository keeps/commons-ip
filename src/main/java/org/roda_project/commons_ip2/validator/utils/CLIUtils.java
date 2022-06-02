package org.roda_project.commons_ip2.validator.utils;

import java.io.PrintStream;

import org.roda_project.commons_ip2.validator.CLI.CLIConstants;

/**
 * {@author João Gomes <jgomes@keep.pt>}.
 */
public final class CLIUtils {

  private CLIUtils() {
    // do nothing
  }

  /**
   * If first option validate is missing, print the first option to the user know.
   *
   * @param printStream
   *          {@link PrintStream}
   */
  public static void printUsage(final PrintStream printStream) {
    final StringBuilder out = new StringBuilder();

    out.append("Usage: Commons-ip validator COMMAND [OPTIONS]\n");

    out.append(CLIConstants.END_OF_LINE);
    out.append(CLIConstants.COMMANDS_KEY);
    out.append(CLIConstants.DOUBLE_END_OF_LINE);
    out.append(CLIConstants.TAB).append(CLIConstants.CLI_OPTION_VALIDATE).append(CLIConstants.DOUBLE_TAB)
      .append("Validate a SIP file").append(CLIConstants.END_OF_LINE);

    out.append(CLIConstants.END_OF_LINE);

    out.append("Usage: Commons-ip create COMMAND [OPTIONS]\n");

    out.append(CLIConstants.END_OF_LINE);
    out.append(CLIConstants.COMMANDS_KEY);
    out.append(CLIConstants.DOUBLE_END_OF_LINE);
    out.append(CLIConstants.TAB).append(CLIConstants.CLI_OPTION_CREATE).append(CLIConstants.DOUBLE_TAB)
      .append("Create a SIP Shallow file").append(CLIConstants.END_OF_LINE);

    out.append(CLIConstants.END_OF_LINE);
    printStream.append(out).flush();
  }

  /**
   * Print errors in CLI.
   * 
   * @param printStream
   *          the {@link PrintStream}
   * @param message
   *          the message to print.
   */
  public static void printErrors(final PrintStream printStream, final String message) {
    final StringBuilder out = new StringBuilder();

    out.append("ERROR\n");

    out.append(CLIConstants.END_OF_LINE);
    out.append(message);
    out.append(CLIConstants.DOUBLE_END_OF_LINE);
    printStream.append(out).flush();
  }

}
