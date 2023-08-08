package org.roda_project.commons_ip2.cli.providers;

import java.util.Collections;

import picocli.CommandLine;

/**
 * @author Miguel Guimarães <mguimaraes@keep.pt>
 */
public class VersionProvider implements CommandLine.IVersionProvider {
  @Override
  public String[] getVersion() {
    String implementationVersion = this.getClass().getPackage().getImplementationVersion();
    String version = String.format("commons-ip version %s", implementationVersion);
    return Collections.singletonList(version).toArray(String[]::new);
  }
}
