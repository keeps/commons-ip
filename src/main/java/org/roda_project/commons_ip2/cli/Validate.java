package org.roda_project.commons_ip2.cli;

import org.apache.commons.lang3.StringUtils;
import org.roda_project.commons_ip2.cli.exception.CLIException;
import org.roda_project.commons_ip2.validator.CLI.CLIConstants;
import org.roda_project.commons_ip2.validator.EARKPyIPValidator;
import org.roda_project.commons_ip2.validator.EARKSIPValidator;
import org.roda_project.commons_ip2.validator.constants.Constants;
import org.roda_project.commons_ip2.validator.observer.ProgressValidationLoggerObserver;
import org.roda_project.commons_ip2.validator.reporter.ValidationReportOutputJSONPyIP;
import org.roda_project.commons_ip2.validator.reporter.ValidationReportOutputJson;
import org.xml.sax.SAXException;
import picocli.CommandLine;

import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.Callable;

/**
 * @author Miguel Guimarães <mguimaraes@keep.pt>
 */
@CommandLine.Command(name = "validate", usageHelpAutoWidth = true, description = "Validates E-ARK IP packages against the specification")
public class Validate implements Callable<Integer> {

  private enum ReportType {COMMONS_IP, PYIP}

  @CommandLine.Option(names = {"-h", "--help"}, usageHelp = true, description = "display this help and exit")
  boolean help;

  @CommandLine.Option(names = {"-i",
    "--inputs"}, arity = "1..*", required = true, description = "Paths to the SIPs archive file or files")
  private String[] sipPaths;

  @CommandLine.Option(names = {"-o",
    "--output-report-dir"}, description = "Path to save the validation report. If not set a report will be generated in the sip folder.")
  private String reportPathDir;

  @CommandLine.Option(names = {"-r",
    "--reporter-type"}, description = "Report type (possible values: ${COMPLETION-CANDIDATES})", showDefaultValue = CommandLine.Help.Visibility.ALWAYS, defaultValue = "COMMONS_IP")
  private ReportType reportType;

  @CommandLine.Option(names = {"-v",
    "--verbose"}, description = "Verbose command line output with all validation steps", type = Boolean.class)
  private boolean verbose;

  @Override
  public Integer call() {
    for (String sip : sipPaths) {
      try {
        handleSipValidation(sip, reportPathDir, reportType, verbose);
      } catch (IOException | SAXException | NoSuchAlgorithmException | ParserConfigurationException | CLIException e) {
        return 1;
      }
    }
    return 0;
  }

  private void handleSipValidation(final String sip, final String reportPathDir, final ReportType reportType,
    final boolean verbose)
    throws IOException, ParserConfigurationException, SAXException, NoSuchAlgorithmException, CLIException {
    final Path sipPath = Paths.get(sip);

    Path reportPath = obtainReportPath(sipPath, reportPathDir);

    if (reportType.equals(ReportType.COMMONS_IP)) {
      final OutputStream outputStream = createReportOutputStream(reportPath);
      if (outputStream != null) {
        final ValidationReportOutputJson jsonReporter = new ValidationReportOutputJson(sipPath, outputStream);
        final EARKSIPValidator earksipValidator = new EARKSIPValidator(jsonReporter);
        if (verbose) {
          earksipValidator.addObserver(new ProgressValidationLoggerObserver());
        }
        earksipValidator.validate();
      } else {
        throw new CLIException("test");
      }
    } else if (reportType.equals(ReportType.PYIP)) {
      final ValidationReportOutputJSONPyIP jsonReporter = new ValidationReportOutputJSONPyIP(reportPath, sipPath);
      final EARKPyIPValidator earkPyIPValidator = new EARKPyIPValidator(jsonReporter);
      if (verbose) {
        earkPyIPValidator.addObserver(new ProgressValidationLoggerObserver());
      }
      earkPyIPValidator.validate();
    } else {
      throw new CLIException("Invalid Option of ReportType");
    }
  }

  private Path obtainReportPath(final Path sipPath, final String reportPathDir) {
    Path reportPath;
    final LocalDateTime localDateTime = LocalDateTime.now();
    final String date = localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

    final String reportName = sipPath.getFileName() + "_validation-report_" + date + CLIConstants.JSON_FILE_EXTENSION;

    if (StringUtils.isNotBlank(reportPathDir) && Files.exists(Paths.get(reportPathDir))) {
      reportPath = Paths.get(reportPathDir).resolve(reportName);
    } else {
      reportPath = sipPath.normalize().toAbsolutePath().getParent().resolve(reportName);
    }

    return reportPath;
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
