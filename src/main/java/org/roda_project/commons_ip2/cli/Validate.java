package org.roda_project.commons_ip2.cli;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.concurrent.Callable;

import javax.xml.parsers.ParserConfigurationException;

import org.roda_project.commons_ip2.cli.model.ExitCodes;
import org.roda_project.commons_ip2.cli.model.enums.ReportType;
import org.roda_project.commons_ip2.cli.model.exception.CLIException;
import org.roda_project.commons_ip2.cli.model.exception.ValidationException;
import org.roda_project.commons_ip2.cli.utils.CLI.ValidateCommandUtils;
import org.roda_project.commons_ip2.validator.EARKPyIPValidator;
import org.roda_project.commons_ip2.validator.EARKSIPValidator;
import org.roda_project.commons_ip2.validator.observer.ProgressValidationLoggerObserver;
import org.roda_project.commons_ip2.validator.reporter.ValidationReportOutputJSONPyIP;
import org.roda_project.commons_ip2.validator.reporter.ValidationReportOutputJson;
import org.xml.sax.SAXException;

import picocli.CommandLine;

/**
 * @author Miguel Guimarães <mguimaraes@keep.pt>
 */
@CommandLine.Command(name = "validate", showDefaultValues = true, description = "Validates E-ARK IP packages against the specification")
public class Validate implements Callable<Integer> {
  @CommandLine.Option(names = {"-h", "--help"}, usageHelp = true, description = "display this help and exit")
  boolean help;

  @CommandLine.Option(names = {"-i",
    "--inputs"}, split = ",", required = true, description = "Paths to the SIPs archive file or files", paramLabel = "<path>")
  List<String> sipPaths;

  @CommandLine.Option(names = {"-o",
    "--output-report-dir"}, paramLabel = "<path>", description = "Path to save the validation report. If not set a report will be generated in the same folder as the IP package.")
  String reportPathDir = System.getProperty("user.dir");

  @CommandLine.Option(names = {"-r",
    "--reporter-type"}, paramLabel = "<type>", description = "Report type (possible values: ${COMPLETION-CANDIDATES})")
  ReportType reportType = ReportType.COMMONS_IP;

  @CommandLine.Option(names = {"-v",
    "--verbose"}, description = "Verbose command line output with all validation steps")
  boolean verbose;

  @CommandLine.Option(names = {"-cv", "--commons-version"}, description = "Commons IP version")
  String version = "2.1.0";

  @Override
  public Integer call() throws ValidationException, CLIException {
    for (String sip : sipPaths) {
      try {
        handleSipValidation(sip, reportPathDir, reportType, verbose);
      } catch (IOException e) {
        throw new ValidationException("Unable to create necessary files to start validation");
      } catch (ParserConfigurationException | SAXException | NoSuchAlgorithmException e) {
        throw new ValidationException("Failed to validate");
      }
    }

    return ExitCodes.EXIT_CODE_OK;
  }

  private void handleSipValidation(final String sip, final String reportPathDir, final ReportType reportType,
    final boolean verbose)
    throws IOException, ParserConfigurationException, SAXException, CLIException, NoSuchAlgorithmException {
    final Path sipPath = Paths.get(sip);

    Path reportPath = ValidateCommandUtils.obtainReportPath(sipPath, reportPathDir);

    switch (reportType) {
      case COMMONS_IP -> {
        final OutputStream outputStream = ValidateCommandUtils.createReportOutputStream(reportPath);
        if (outputStream != null) {
          final ValidationReportOutputJson jsonReporter = new ValidationReportOutputJson(sipPath, outputStream);
          final EARKSIPValidator earksipValidator = new EARKSIPValidator(jsonReporter, version);
          if (verbose) {
            earksipValidator.addObserver(new ProgressValidationLoggerObserver());
          }
          earksipValidator.validate(version);
        }
      }
      case PYIP -> {
        final ValidationReportOutputJSONPyIP jsonReporter = new ValidationReportOutputJSONPyIP(reportPath, sipPath);
        final EARKPyIPValidator earkPyIPValidator = new EARKPyIPValidator(jsonReporter, version);
        if (verbose) {
          earkPyIPValidator.addObserver(new ProgressValidationLoggerObserver());
        }
        earkPyIPValidator.validate();
      }
      default -> throw new CLIException("Unexpected value: " + reportType);
    }
  }
}