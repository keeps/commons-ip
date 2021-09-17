package org.roda_project.commons_ip2.validator.CLI;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author João Gomes <jgomes@keep.pt>
 */
public class Main {

  public static void main(String[] args) {
    if (args.length == 0) {
      CLI.printUsage(System.out);
    } else {
      if (args[0].equals(CLIConstants.CLI_OPTION_VALIDATE)) {
        if (args.length == 1) {
          CLI.printUsageValidator(System.out);
        } else {
          if (args.length <= 3) {
            CLI.printUsageValidator(System.out);
          } else {
            if (args[1].equals("-i") && args[2].equals("-o")) {
              CLI.printUsageValidator(System.out);
            } else {
              List<String> filteredArgs = new ArrayList<>(Arrays.asList(args));
              filteredArgs.remove(0);
              CLI cli = new CLI();
              cli.start(filteredArgs.toArray(new String[] {}));
            }
          }
        }
      } else {
        CLI.printUsage(System.out);
      }
    }

  }
}
