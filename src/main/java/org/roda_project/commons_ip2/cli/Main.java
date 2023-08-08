package org.roda_project.commons_ip2.cli;

import org.roda_project.commons_ip2.cli.model.exception.mappers.CommonsIPExceptionMapper;
import org.roda_project.commons_ip2.cli.model.exception.handlers.PrintExceptionMessageHandler;
import org.roda_project.commons_ip2.cli.model.exception.handlers.ShortErrorMessageHandler;
import org.roda_project.commons_ip2.cli.providers.VersionProvider;
import picocli.CommandLine;

/**
 * @author Miguel Guimarães <mguimaraes@keep.pt>
 */
@CommandLine.Command(name = "commons-ip", subcommands = {Create.class,
  Validate.class}, mixinStandardHelpOptions = true, versionProvider = VersionProvider.class)
public class Main implements Runnable {
  public static void main(String... args) {
    System.exit(new CommandLine(new Main()).execute(args));
  }

  @CommandLine.Spec
  CommandLine.Model.CommandSpec spec;

  @Override
  public void run() {
    // if the command was invoked without subcommand, show the usage help
    spec.commandLine().usage(System.err);
  }
}
