package org.roda_project.commons_ip2.validator.CLI;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.roda_project.commons_ip2.validator.utils.CLIUtils;

/** {@author João Gomes <jgomes@keep.pt>}. */
public final class Main {

  private Main() {
    // do nothing
  }

  /**
   * Main of the CLI.
   *
   * @param args
   *          {@link String} array with the args of the command.
   */
  public static void main(final String[] args) {
    if (args.length == 0) {
      CLIUtils.printUsage(System.out);
    } else {
      if (args[0].equals(CLIConstants.CLI_OPTION_VALIDATE)) {
        final List<String> filteredArgs = new ArrayList<>(Arrays.asList(args));
        filteredArgs.remove(0);
        final CLIValidator cliValidator = new CLIValidator();
        cliValidator.start(filteredArgs.toArray(new String[] {}));
      } else if (args[0].equals(CLIConstants.CLI_OPTION_CREATE)) {
        final List<String> filteredArgs = new ArrayList<>(Arrays.asList(args));
        filteredArgs.remove(0);
        final CLICreator cliCreator = new CLICreator();
        cliCreator.start(filteredArgs.toArray(new String[] {}));
      } else {
        CLIUtils.printUsage(System.out);

      }
    }
  }
}
