package org.roda_project.commons_ip2.cli;

import picocli.CommandLine;


import java.util.concurrent.Callable;

/**
 * @author Miguel Guimarães <mguimaraes@keep.pt>
 */
@CommandLine.Command(name= "create", usageHelpAutoWidth = true, mixinStandardHelpOptions = true, description = "")
public class Create implements Callable<Integer> {
  @CommandLine.Option(names = {"-to", "--target-only"}, required = false)
  private boolean targetOnly;

  @Override
  public Integer call() throws Exception {

    return 0;
  }
}
