package org.roda_project.commons_ip2.validator.CLI;

import org.roda_project.commons_ip2.validator.utils.CLIUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/** {@author João Gomes <jgomes@keep.pt>}. */
public class Main {

  /**
   * Main of the CLI.
   *
   * @param args
   *          {@link String} array with the args of the command.
   */
  public static void main(String[] args) {
    if (args.length == 0) {
      CLIUtils.printUsage(System.out);
    } else {
      if (args[0].equals(CLIConstants.CLI_OPTION_VALIDATE)) {
        List<String> filteredArgs = new ArrayList<>(Arrays.asList(args));
        filteredArgs.remove(0);
        CLIValidator cliValidator = new CLIValidator();
        cliValidator.start(filteredArgs.toArray(new String[] {}));
      }
      if (args[0].equals(CLIConstants.CLI_OPTION_CREATE)) {
        List<String> filteredArgs = new ArrayList<>(Arrays.asList(args));
        filteredArgs.remove(0);
        CLICreator cliCreator = new CLICreator();
        cliCreator.start(filteredArgs.toArray(new String[] {}));
      } else {
        CLIUtils.printUsage(System.out);
      }
    }
  }
}
