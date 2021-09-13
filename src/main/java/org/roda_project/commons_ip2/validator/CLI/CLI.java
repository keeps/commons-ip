package org.roda_project.commons_ip2.validator.CLI;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.roda_project.commons_ip2.validator.EARKSIPValidator;
import org.roda_project.commons_ip2.validator.utils.ExitCodes;

/**
 * @author João Gomes <jgomes@keep.pt>
 */
public class CLI {
  private final Options parameters;
  private final CommandLineParser parser;
  private CommandLine commandLine;

  public CLI() {
    this.parameters = new Options();
    this.parser = new DefaultParser();
    Option op = new Option("i", "input", true, "List of files to be used as inputs");
    op.setArgs(Option.UNLIMITED_VALUES);
    op.setRequired(true);
    parameters.addOption(op);
    parameters.addOption("o", true, "Output to file");
  }

  public int start(String[] args) {
    try {
      commandLine = parser.parse(parameters, args);
      String[] parsedArgs = commandLine.getOptionValues("i");
      String path = commandLine.getOptionValue("o");

      if (parsedArgs.length == 0) {
        return ExitCodes.EXIT_MISSING_SIP_PATH;
      }

      if (path.isEmpty()) {
        return ExitCodes.EXIT_MISSING_REPORT_DIRECTORY;
      }

      if (!Paths.get(path).toFile().isDirectory()) {
        try {
          Files.createDirectories(Paths.get(path));
        } catch (IOException e) {
          return ExitCodes.EXIT_CODE_CREATE_DIRECTORY_FAILS;
        }
      }
      LocalDateTime localDateTime = LocalDateTime.now();
      int count = 1;
      String date = localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
      for (String s : parsedArgs) {
        String sipName;
        if (s.contains(".zip")) {
          sipName = s.split("/")[s.split("/").length - 1].split(".zip")[0];
        } else {
          sipName = s.split("/")[s.split("/").length - 1];
        }
        String reportName = date + "-#" + count + "-" + sipName + ".json";

        Path reportPath = Paths.get(path).resolve(reportName);
        Path earksipPath = Paths.get(s);

        EARKSIPValidator earksipValidator = new EARKSIPValidator(earksipPath, reportPath);
        earksipValidator.validate();
        count++;
      }

    } catch (ParseException e) {
      return ExitCodes.EXIT_PARSE_ARG;
    } catch (DateTimeException d) {
      return ExitCodes.EXIT_CODE_INVALID_DATE_FORMAT;
    }
    return ExitCodes.EXIT_CODE_OK;
  }
}
