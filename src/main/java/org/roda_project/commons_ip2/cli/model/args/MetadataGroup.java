package org.roda_project.commons_ip2.cli.model.args;

import picocli.CommandLine;

/**
 * @author Miguel Guimarães <mguimaraes@keep.pt>
 */
public class MetadataGroup {
  @CommandLine.ArgGroup(exclusive = false)
  Metadata metadata;

  public Metadata getMetadata() {
    return metadata;
  }
}
