package org.roda_project.commons_ip2.cli;

import org.roda_project.commons_ip2.cli.providers.VersionProvider;
import picocli.CommandLine;

import java.util.concurrent.Callable;

/**
 * @author Miguel Guimarães <mguimaraes@keep.pt>
 */
@CommandLine.Command(name = "", subcommands = {Create.class,
  Validate.class}, mixinStandardHelpOptions = true, versionProvider = VersionProvider.class)
public class Main implements Callable<Integer> {

  @Override
  public Integer call() {
    CommandLine.usage(new Main(), System.out);
    return 0;
  }

  public static void main(String... args) {
    CommandLine commandLine = new CommandLine(new Main());
    commandLine.parseArgs(args);
    if (commandLine.isVersionHelpRequested()) {
      commandLine.printVersionHelp(System.out);
      System.exit(0);
    } else {
      int exitCode = commandLine.execute(args);
      System.exit(exitCode);
    }
  }
}
