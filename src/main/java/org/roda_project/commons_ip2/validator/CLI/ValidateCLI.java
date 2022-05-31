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
import org.roda_project.commons_ip2.validator.utils.ExitCodes;
import org.xml.sax.SAXException;

/** {@author João Gomes <jgomes@keep.pt>}. */
public class ValidateCLI {
  private final Options parameters;
  private final CommandLineParser parser;

  /** Initialize available CLI options. */
  public ValidateCLI() {
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
    Option typeValidatorReportOption = new Option("r", "Type of Validation Report");
    typeValidatorReportOption.setRequired(false);
    typeValidatorReportOption.setOptionalArg(true);
    typeValidatorReportOption.setArgs(1);
    parameters.addOption(typeValidatorReportOption);
    Option verbose = new Option("v", "Verbose Option");
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
      .append("\n\n");
    out.append("\t").append(CLIConstants.CLI_OPTION_REPORT_TYPE).append("\t\t")
      .append("(optional) By default generate json report, with option eark generate E-ARK JSON").append("\n\n");
    out.append("\t").append(CLIConstants.CLI_OPTION_VERBOSE).append("\t\t")
      .append("(optional) Verbose command line output with all validation steps").append("/n");
    out.append("\n");
    printStream.append(out).flush();
  }

  /**
   * Start the CLI.
   *
   * @param args
   *          {@link String} array with arguments of command.
   * @return one {@link ExitCodes}
   */
  public int start(String[] args) {
    try {
      CommandLine commandLine = parser.parse(parameters, args);

      String[] sipPaths = commandLine.getOptionValues("i");
      String reportDirectoryPath = commandLine.getOptionValue("o");
      String typeReportOption = commandLine.getOptionValue("r");

      if (commandLine.hasOption("o") && reportDirectoryPath == null) {
        printUsageValidator(System.out);
        return ExitCodes.EXIT_PARSE_ARG;
      }

      if (sipPaths == null || sipPaths.length == 0) {
        printMissingSipPath(System.out);
      }

      if (reportDirectoryPath != null && !Files.isDirectory(Paths.get(reportDirectoryPath))) {
        int code = createDirectory(reportDirectoryPath);
        if (code == ExitCodes.EXIT_CODE_CREATE_DIRECTORY_FAILS) {
          CLIUtils.printErrors(System.out, "Cannot create the directory for the report.");
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

        validate(typeReportOption, reportPath, sipPath, commandLine.hasOption("v"));
        System.out.println(reportPath.normalize().toAbsolutePath());
      }

    } catch (ParseException e) {
      printUsageValidator(System.out);
      return ExitCodes.EXIT_PARSE_ARG;
    } catch (DateTimeException d) {
      CLIUtils.printErrors(System.out, "Invalid date format error");
      return ExitCodes.EXIT_CODE_INVALID_DATE_FORMAT;
    } catch (ParserConfigurationException | SAXException e) {
      CLIUtils.printErrors(System.out, "Error on object initialize");
      return ExitCodes.EXIT_CANNOT_CREATE_EARKVALIDATOR_OBJECT;
    } catch (NoSuchAlgorithmException e) {
      CLIUtils.printErrors(System.out, "Error on object initialize EARKPYIP");
      return ExitCodes.EXIT_CANNOT_CREATE_EARKVALIDATOR_OBJECT;
    } catch (IOException e) {
      CLIUtils.printErrors(System.out, "Error on Report Initialize");
      return ExitCodes.EXIT_CANNOT_CREATE_REPORT;
    }

    return ExitCodes.EXIT_CODE_OK;
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

  private int createDirectory(String path) {
    try {
      Files.createDirectories(Paths.get(path));
    } catch (IOException e) {
      return ExitCodes.EXIT_CODE_CREATE_DIRECTORY_FAILS;
    }
    return ExitCodes.EXIT_CODE_OK;
  }

  private int validate(String typeReportOption, Path reportPath, Path sipPath, boolean verbose)
    throws IOException, ParserConfigurationException, SAXException, NoSuchAlgorithmException {
    if (typeReportOption == null || typeReportOption.equals("default")) {
      OutputStream outputStream = createReportOutputStream(reportPath);
      if (outputStream != null) {
        ValidationReportOutputJson jsonReporter = new ValidationReportOutputJson(sipPath, outputStream);
        EARKSIPValidator earksipValidator = new EARKSIPValidator(jsonReporter);
        if (verbose) {
          earksipValidator.addObserver(new ProgressValidationLoggerObserver());
        }
        earksipValidator.validate();
      } else {
        CLIUtils.printErrors(System.out, "Error on creation of reportPath");
        return ExitCodes.EXIT_CANNOT_CREATE_REPORT;
      }
    } else if (typeReportOption.equals("eark")) {
      ValidationReportOutputJSONPyIP jsonReporter = new ValidationReportOutputJSONPyIP(reportPath, sipPath);
      EARKPyIPValidator earkPyIPValidator = new EARKPyIPValidator(jsonReporter);
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

  private OutputStream createReportOutputStream(Path reportPath) throws IOException {
    Path outputFile = createReportFile(reportPath);
    OutputStream outputStream = null;
    if (outputFile != null) {
      outputStream = new BufferedOutputStream(new FileOutputStream(outputFile.toFile()));
    }
    return outputStream;
  }

  private Path createReportFile(Path reportPath) throws IOException {
    Path outputFile = reportPath;
    if (!outputFile.toFile().exists()) {
      try {
        Files.createFile(outputFile);
      } catch (IOException e) {
        outputFile = Files.createTempFile(Constants.VALIDATION_REPORT_PREFIX, ".json");
      }
    } else {
      Files.deleteIfExists(outputFile);
      try {
        Files.createFile(outputFile);
      } catch (IOException e) {
        outputFile = Files.createTempFile(Constants.VALIDATION_REPORT_PREFIX, ".json");
      }
    }
    return outputFile;
  }
}
