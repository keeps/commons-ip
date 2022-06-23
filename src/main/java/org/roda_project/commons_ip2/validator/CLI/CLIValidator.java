package org.roda_project.commons_ip2.validator.CLI;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;
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
import org.roda_project.commons_ip2.validator.EARKPyIPValidator;
import org.roda_project.commons_ip2.validator.EARKSIPValidator;
import org.roda_project.commons_ip2.validator.constants.Constants;
import org.roda_project.commons_ip2.validator.observer.ProgressValidationLoggerObserver;
import org.roda_project.commons_ip2.validator.reporter.ValidationReportOutputJSONPyIP;
import org.roda_project.commons_ip2.validator.reporter.ValidationReportOutputJson;
import org.roda_project.commons_ip2.validator.utils.CLIUtils;
import org.roda_project.commons_ip2.validator.utils.ExitCodes;
import org.xml.sax.SAXException;

/** {@author João Gomes <jgomes@keep.pt>}. */
public class CLIValidator {
  /**
   * {@link Options}.
   */
  private final Options parameters;
  /**
   * {@link CommandLineParser}.
   */
  private final CommandLineParser parser;

  /** Initialize available CLI options. */
  public CLIValidator() {
    this.parameters = new Options();
    this.parser = new DefaultParser();

    final Option op = new Option(CLIConstants.CLI_CREATE_SHORT_OPTION_INPUT_WITHOUT_IDENT, "input", true,
      "List of files to be used as inputs");
    op.setArgs(Option.UNLIMITED_VALUES);
    op.setRequired(true);
    parameters.addOption(op);
    final Option reportOption = new Option(CLIConstants.CLI_CREATE_SHORT_OPTION_OUTPUT_FILE_WITHOUT_IDENT, true,
      "Output to file");
    reportOption.setRequired(false);
    reportOption.setOptionalArg(true);
    parameters.addOption(reportOption);
    final Option typeValidatorReportOption = new Option(CLIConstants.CLI_VALIDATE_SHORT_OPTION_TYPE_OF_REPORT_WITHOUT_IDENT,
      "Type of Validation Report");
    typeValidatorReportOption.setRequired(false);
    typeValidatorReportOption.setOptionalArg(true);
    typeValidatorReportOption.setArgs(1);
    parameters.addOption(typeValidatorReportOption);
    final Option verbose = new Option(CLIConstants.CLI_CREATE_SHORT_OPTION_VERBOSE_WITHOUT_IDENT, "Verbose Option");
    verbose.setRequired(false);
    verbose.setOptionalArg(false);
    verbose.setArgs(0);
    parameters.addOption(verbose);
  }

  /**
   * Print All available options if some required option is missing.
   *
   * @param printStream
   *          {@link PrintStream}
   */
  public static void printUsageValidator(final PrintStream printStream) {
    final StringBuilder out = new StringBuilder();

    out.append("Usage: Commons-ip validator COMMAND [OPTIONS]\n");

    out.append(CLIConstants.END_OF_LINE);
    out.append("Commands:");
    out.append(CLIConstants.DOUBLE_END_OF_LINE);
    out.append(CLIConstants.TAB).append(CLIConstants.CLI_OPTION_SIP_PATHS).append(CLIConstants.DOUBLE_TAB)
      .append("(required) Paths to the SIPs archive file or files").append(CLIConstants.END_OF_LINE);
    out.append(CLIConstants.TAB).append(CLIConstants.CLI_OPTION_REPORT_DIRECTORY).append(CLIConstants.DOUBLE_TAB)
      .append(
        "(optional) Path to save the validation report. If not set a report will be " + "generated in the sip folder.")
      .append(CLIConstants.DOUBLE_END_OF_LINE);
    out.append(CLIConstants.TAB).append(CLIConstants.CLI_OPTION_REPORT_TYPE).append(CLIConstants.DOUBLE_TAB)
      .append("(optional) By default generate json report, with option eark generate E-ARK JSON")
      .append(CLIConstants.DOUBLE_END_OF_LINE);
    out.append(CLIConstants.TAB).append(CLIConstants.CLI_OPTION_VERBOSE).append(CLIConstants.DOUBLE_TAB)
      .append("(optional) Verbose command line output with all validation steps").append("/n");
    out.append(CLIConstants.END_OF_LINE);
    printStream.append(out).flush();
  }

  /**
   * Start the CLI.
   *
   * @param args
   *          {@link String} array with arguments of command.
   * @return one {@link ExitCodes}
   */
  public int start(final String[] args) {
    try {
      final CommandLine commandLine = parser.parse(parameters, args);

      final String[] sipPaths = commandLine.getOptionValues(CLIConstants.CLI_CREATE_SHORT_OPTION_INPUT_WITHOUT_IDENT);
      final String reportDirectoryPath = commandLine
        .getOptionValue(CLIConstants.CLI_CREATE_SHORT_OPTION_OUTPUT_FILE_WITHOUT_IDENT);
      final String typeReportOption = commandLine.getOptionValue(CLIConstants.CLI_VALIDATE_SHORT_OPTION_TYPE_OF_REPORT_WITHOUT_IDENT);

      if (commandLine.hasOption(CLIConstants.CLI_CREATE_SHORT_OPTION_OUTPUT_FILE_WITHOUT_IDENT)
        && reportDirectoryPath == null) {
        printUsageValidator(System.out);
        return ExitCodes.EXIT_PARSE_ARG;
      }

      if (sipPaths == null || sipPaths.length == 0) {
        printMissingSipPath(System.out);
        return ExitCodes.EXIT_CODE_CREATE_MISSING_ARGS;
      }

      if (reportDirectoryPath != null && !Files.isDirectory(Paths.get(reportDirectoryPath))) {
        final int code = createDirectory(reportDirectoryPath);
        if (code == ExitCodes.EXIT_CODE_CREATE_DIRECTORY_FAILS) {
          CLIUtils.printErrors(System.out, "Cannot create the directory for the report.");
          return code;
        }
      }

      final LocalDateTime localDateTime = LocalDateTime.now();

      final String date = localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
      for (String sip : sipPaths) {
        final Path sipPath = Paths.get(sip);
        Path reportPath;
        int count = 1;
        do {
          final String reportName = sipPath.getFileName() + "_validation-report_" + date + "_" + count++
            + CLIConstants.JSON_FILE_EXTENSION;
          if (reportDirectoryPath != null) {
            reportPath = Paths.get(reportDirectoryPath).resolve(reportName);
          } else {
            reportPath = sipPath.normalize().toAbsolutePath().getParent().resolve(reportName);
          }

        } while (Files.exists(reportPath));

        validate(typeReportOption, reportPath, sipPath,
          commandLine.hasOption(CLIConstants.CLI_CREATE_SHORT_OPTION_VERBOSE_WITHOUT_IDENT));
        System.out.println(reportPath.normalize().toAbsolutePath());
      }

    } catch (final ParseException e) {
      printUsageValidator(System.out);
      return ExitCodes.EXIT_PARSE_ARG;
    } catch (final DateTimeException d) {
      CLIUtils.printErrors(System.out, "Invalid date format error");
      return ExitCodes.EXIT_CODE_INVALID_DATE_FORMAT;
    } catch (final ParserConfigurationException | SAXException e) {
      CLIUtils.printErrors(System.out, "Error on object initialize");
      return ExitCodes.EXIT_CANNOT_CREATE_EARKVALIDATOR_OBJECT;
    } catch (final NoSuchAlgorithmException e) {
      CLIUtils.printErrors(System.out, "Error on object initialize EARKPYIP");
      return ExitCodes.EXIT_CANNOT_CREATE_EARKVALIDATOR_OBJECT;
    } catch (final IOException e) {
      CLIUtils.printErrors(System.out, "Error on Report Initialize");
      return ExitCodes.EXIT_CANNOT_CREATE_REPORT;
    }

    return ExitCodes.EXIT_CODE_OK;
  }

  private void printMissingSipPath(final PrintStream printStream) {
    final StringBuilder out = new StringBuilder();

    out.append("ERROR\n");

    out.append(CLIConstants.END_OF_LINE);
    out.append("Missing SIP Path");
    out.append(CLIConstants.DOUBLE_END_OF_LINE);
    out.append(CLIConstants.END_OF_LINE);
    printStream.append(out).flush();
  }

  private int createDirectory(final String path) {
    try {
      Files.createDirectories(Paths.get(path));
    } catch (final IOException e) {
      return ExitCodes.EXIT_CODE_CREATE_DIRECTORY_FAILS;
    }
    return ExitCodes.EXIT_CODE_OK;
  }

  private int validate(final String typeReportOption, final Path reportPath, final Path sipPath, final boolean verbose)
    throws IOException, ParserConfigurationException, SAXException, NoSuchAlgorithmException {
    if (typeReportOption == null || typeReportOption.equals("default")) {
      final OutputStream outputStream = createReportOutputStream(reportPath);
      if (outputStream != null) {
        final ValidationReportOutputJson jsonReporter = new ValidationReportOutputJson(sipPath, outputStream);
        final EARKSIPValidator earksipValidator = new EARKSIPValidator(jsonReporter);
        if (verbose) {
          earksipValidator.addObserver(new ProgressValidationLoggerObserver());
        }
        earksipValidator.validate();
      } else {
        CLIUtils.printErrors(System.out, "Error on creation of reportPath");
        return ExitCodes.EXIT_CANNOT_CREATE_REPORT;
      }
    } else if (typeReportOption.equals("eark")) {
      final ValidationReportOutputJSONPyIP jsonReporter = new ValidationReportOutputJSONPyIP(reportPath, sipPath);
      final EARKPyIPValidator earkPyIPValidator = new EARKPyIPValidator(jsonReporter);
      if (verbose) {
        earkPyIPValidator.addObserver(new ProgressValidationLoggerObserver());
      }
      earkPyIPValidator.validate();
    } else {
      CLIUtils.printErrors(System.out, "Invalid Option of ReportType");
      return ExitCodes.EXIT_REPORT_TYPE_INVALID;
    }
    return ExitCodes.EXIT_CODE_OK;
  }

  private OutputStream createReportOutputStream(final Path reportPath) throws IOException {
    final Path outputFile = createReportFile(reportPath);
    OutputStream outputStream = null;
    if (outputFile != null) {
      outputStream = new BufferedOutputStream(new FileOutputStream(outputFile.toFile()));
    }
    return outputStream;
  }

  private Path createReportFile(final Path reportPath) throws IOException {
    Path outputFile = reportPath;
    if (!outputFile.toFile().exists()) {
      try {
        Files.createFile(outputFile);
      } catch (final IOException e) {
        outputFile = Files.createTempFile(Constants.VALIDATION_REPORT_PREFIX, CLIConstants.JSON_FILE_EXTENSION);
      }
    } else {
      Files.deleteIfExists(outputFile);
      try {
        Files.createFile(outputFile);
      } catch (final IOException e) {
        outputFile = Files.createTempFile(Constants.VALIDATION_REPORT_PREFIX, CLIConstants.JSON_FILE_EXTENSION);
      }
    }
    return outputFile;
  }
}
