package org.roda_project.commons_ip2.cli.model.exception.handlers;

import org.roda_project.commons_ip2.validator.constants.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import picocli.CommandLine;

/**
 * @author Miguel Guimarães <mguimaraes@keep.pt>
 */

public class PrintExceptionMessageHandler implements CommandLine.IExecutionExceptionHandler {

  private static final Logger LOGGER = LoggerFactory.getLogger(PrintExceptionMessageHandler.class);

  public int handleExecutionException(Exception ex, CommandLine cmd, CommandLine.ParseResult parseResult) {

    // bold red error message
    cmd.getErr().println(cmd.getColorScheme().errorText(ex.getMessage()));

    // LOGGER.error(Arrays.toString(ex.getStackTrace()));

    for (StackTraceElement e : ex.getStackTrace()) {
      LOGGER.error(String.valueOf(e));
    }

    CommandLine.Model.CommandSpec spec = cmd.getCommandSpec();

    cmd.getOut().printf("Try '%s --help' for more information.%n", spec.qualifiedName());

    cmd.getOut().printf(
      "If you think you've found a bug, please report the log file located in %s/%s to %s",
      System.getProperty(Constants.PROPERTY_KEY_HOME), Constants.LOGGER_FILE, Constants.ISSUES_LINK);

    return cmd.getExitCodeExceptionMapper() != null ? cmd.getExitCodeExceptionMapper().getExitCode(ex)
      : cmd.getCommandSpec().exitCodeOnExecutionException();
  }
}
