package org.roda_project.commons_ip2.cli.model.exception.handlers;

import java.io.PrintWriter;

import org.roda_project.commons_ip2.validator.constants.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import picocli.CommandLine;

/**
 * @author Miguel Guimarães <mguimaraes@keep.pt>
 */
public class ShortErrorMessageHandler implements CommandLine.IParameterExceptionHandler {
  private static final Logger LOGGER = LoggerFactory.getLogger(PrintExceptionMessageHandler.class);

  public int handleParseException(CommandLine.ParameterException ex, String[] args) {
    CommandLine cmd = ex.getCommandLine();
    PrintWriter err = cmd.getErr();

    // if tracing at DEBUG level, show the location of the issue
    if ("DEBUG".equalsIgnoreCase(System.getProperty("picocli.trace"))) {
      err.println(cmd.getColorScheme().stackTraceText(ex));
    }

    for (StackTraceElement e : ex.getStackTrace()) {
      LOGGER.error(String.valueOf(e));
    }

    err.println(cmd.getColorScheme().errorText(ex.getMessage())); // bold red
    CommandLine.UnmatchedArgumentException.printSuggestions(ex, err);
    err.print(cmd.getHelp().fullSynopsis());

    CommandLine.Model.CommandSpec spec = cmd.getCommandSpec();
    err.printf("Try '%s --help' for more information.%n", spec.qualifiedName());

    cmd.getOut().printf("If you think you've found a bug, please report the log file located in %s/%s to %s",
      System.getProperty(Constants.PROPERTY_KEY_HOME), Constants.LOGGER_FILE, Constants.ISSUES_LINK);

    return cmd.getExitCodeExceptionMapper() != null ? cmd.getExitCodeExceptionMapper().getExitCode(ex)
      : spec.exitCodeOnInvalidInput();
  }
}
