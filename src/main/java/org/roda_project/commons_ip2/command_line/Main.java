/**
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE file at the root of the source
 * tree and available online at
 *
 * https://github.com/keeps/commons-ip
 */
package org.roda_project.commons_ip2.command_line;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.concurrent.Callable;

import org.roda_project.commons_ip.model.ParseException;
import org.roda_project.commons_ip2.model.SIP;
import org.roda_project.commons_ip2.model.impl.IPConfig;
import org.roda_project.commons_ip2.model.impl.eark.EARKSIP;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Help.Visibility;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

@Command(name = "commons-ip2", version = "x.y.z", description = "E-ARK Information Package validation tool. This is a command-line\n"
  + "tool to analyse and validate the structure and metadata against the E-ARK\n"
  + "Information Package specifications. It is designed for simple integration into\n" + "automated workflows.")
public final class Main implements Callable<Integer> {
  private enum REPORT_TYPE {
    HTML, JSON, NONE
  }

  @Option(names = "-r", description = "Types available: ${COMPLETION-CANDIDATES}", defaultValue = "NONE", showDefaultValue = Visibility.ALWAYS)
  REPORT_TYPE reportType = null;

  @Option(names = {
    "-rDisableInfo"}, defaultValue = "false", description = "Disables reporting entries of the type INFO", showDefaultValue = Visibility.ALWAYS)
  private boolean disableReportInfo;

  @Option(names = {
    "-rDisableWarn"}, defaultValue = "false", description = "Disables reporting entries of the type WARN", showDefaultValue = Visibility.ALWAYS)
  private boolean disableReportWarn;

  @Option(names = "--strict", defaultValue = "true", description = "Enables strict mode", hidden = true, hideParamSyntax = true)
  private boolean strictMode;

  @Option(names = {"-s",
    "--schematron"}, defaultValue = "false", description = "Enables schematron validation", showDefaultValue = Visibility.ALWAYS)
  private boolean enableSchematronValidation;

  @Parameters(paramLabel = "PACKAGE", description = "Path to the package (either zip or folder)")
  private File packagePath;

  @Option(names = {"-h", "--help"}, usageHelp = true, description = "Display a help message")
  private boolean helpRequested = false;

  public Main() {
    // do nothing
  }

  @Override
  public Integer call() throws Exception {
    IPConfig ipConfig = new IPConfig();
    if (strictMode) {
      ipConfig.enableStrictMode();
    }
    if (enableSchematronValidation) {
      ipConfig.enableSchematronValidation();
    }
    if (disableReportInfo) {
      ipConfig.disableReportInfo();
    }
    if (disableReportWarn) {
      ipConfig.disableReportWarn();
    }

    SIP sip = EARKSIP.parse(Paths.get(packagePath.getAbsolutePath()), ipConfig);
    switch (reportType) {
      case HTML:
        System.out.println(sip.getValidationReport().toHtml());
        break;
      case JSON:
        System.out.println(sip.getValidationReport().toJson(ipConfig));
        break;
      default:
        break;
    }

    return sip.getValidationReport().isValid() ? 0 : 1;
  }

  public static void main(String[] args) throws ParseException, IOException {
    System.exit(new CommandLine(new Main()).execute(args));
  }

}
