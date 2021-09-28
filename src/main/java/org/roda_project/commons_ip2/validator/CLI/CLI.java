package org.roda_project.commons_ip2.validator.CLI;

import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.roda_project.commons_ip2.validator.EARKSIPValidator;
import org.roda_project.commons_ip2.validator.observer.ProgressValidationLoggerObserver;
import org.roda_project.commons_ip2.validator.utils.ExitCodes;
import org.xml.sax.SAXException;

/**
 * @author João Gomes <jgomes@keep.pt>
 */
public class CLI {
  private final Options parameters;
  private final CommandLineParser parser;

  public CLI() {
    this.parameters = new Options();
    this.parser = new DefaultParser();

    Option op = new Option("i", "input", true, "List of files to be used as inputs");
    op.setArgs(Option.UNLIMITED_VALUES);
    op.setRequired(true);
    parameters.addOption(op);
    Option reportOption = new Option("o", true, "Output to file");
    reportOption.setRequired(false);
    reportOption.setOptionalArg(true);
    parameters.addOption(reportOption);
  }

  public int start(String[] args) {
    try {
      CommandLine commandLine = parser.parse(parameters, args);
      String[] sipPaths = commandLine.getOptionValues("i");
      String reportDirectoryPath = commandLine.getOptionValue("o");
      if (sipPaths == null || sipPaths.length == 0) {
        printMissingSipPath(System.out);
      }

      if (reportDirectoryPath != null && !Files.isDirectory(Paths.get(reportDirectoryPath))) {
        int code = createDirectory(reportDirectoryPath);
        if (code == ExitCodes.EXIT_CODE_CREATE_DIRECTORY_FAILS) {
          printErrors(System.out,"Cannot create the directory for the report.");
          return code;
        }
      }

      LocalDateTime localDateTime = LocalDateTime.now();

      String date = localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
      for (String sip : sipPaths) {
        Path sipPath = Paths.get(sip);
        Path reportPath;
        int count = 1;
        do {
          String reportName = sipPath.getFileName() + "_validation-report_" + date + "_" + count++ + ".json";
          if (reportDirectoryPath != null) {
            reportPath = Paths.get(reportDirectoryPath).resolve(reportName);
          } else {
            reportPath = sipPath.normalize().toAbsolutePath().getParent().resolve(reportName);
          }

        } while (Files.exists(reportPath));

        EARKSIPValidator earksipValidator = new EARKSIPValidator(sipPath, reportPath);
        earksipValidator.addObserver(new ProgressValidationLoggerObserver());
        earksipValidator.validate();
      }

    } catch (ParseException e) {
      printErrors(System.out,"Can't parse resource files for validation");
      return ExitCodes.EXIT_PARSE_ARG;
    } catch (DateTimeException d) {
      printErrors(System.out,"Invalid date format error");
      return ExitCodes.EXIT_CODE_INVALID_DATE_FORMAT;
    } catch (IOException | ParserConfigurationException | SAXException e) {
      printErrors(System.out,"Error on object initialize");
      return ExitCodes.EXIT_CANNOT_CREATE_EARKVALIDATOR_OBJECT;
    }
    return ExitCodes.EXIT_CODE_OK;
  }

  public static void printUsageValidator(PrintStream printStream) {
    StringBuilder out = new StringBuilder();

    out.append("Usage: Commons-ip validator COMMAND [OPTIONS]\n");

    out.append("\n");
    out.append("Commands:");
    out.append("\n\n");
    out.append("\t").append(CLIConstants.CLI_OPTION_SIP_PATHS).append("\t\t")
      .append("(required) Paths to the SIPs archive file or files").append("\n");
    out.append("\t").append(CLIConstants.CLI_OPTION_REPORT_DIRECTORY).append("\t\t")
      .append(
        "(optional) Path to save the validation report. If not set a report will be " + "generated in the sip folder.")
      .append("\n");

    out.append("\n");
    printStream.append(out).flush();
  }

  public static void printUsage(PrintStream printStream) {
    StringBuilder out = new StringBuilder();

    out.append("Usage: Commons-ip validator COMMAND [OPTIONS]\n");

    out.append("\n");
    out.append("Commands:");
    out.append("\n\n");
    out.append("\t").append(CLIConstants.CLI_OPTION_VALIDATE).append("\t\t").append("Validate a SIP file").append("\n");

    out.append("\n");
    printStream.append(out).flush();
  }

  private void printMissingSipPath(PrintStream printStream) {
    StringBuilder out = new StringBuilder();

    out.append("ERROR\n");

    out.append("\n");
    out.append("Missing SIP Path");
    out.append("\n\n");
    out.append("\n");
    printStream.append(out).flush();
  }

  private void printErrors(PrintStream printStream,String message) {
    StringBuilder out = new StringBuilder();

    out.append("ERROR\n");

    out.append("\n");
    out.append(message);
    out.append("\n\n");
    out.append("\n");
    printStream.append(out).flush();
  }

  private int createDirectory(String path) {
    try {
      Files.createDirectories(Paths.get(path));
    } catch (IOException e) {
      return ExitCodes.EXIT_CODE_CREATE_DIRECTORY_FAILS;
    }
    return ExitCodes.EXIT_CODE_OK;
  }
}