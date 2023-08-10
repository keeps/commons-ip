package org.roda_project.commons_ip2.cli.model.args;

import picocli.CommandLine;

/**
 * @author Miguel Guimarães <mguimaraes@keep.pt>
 */
public class RepresentationGroup {
  @CommandLine.ArgGroup(exclusive = false)
  Representation representation;

  public Representation getRepresentation() {
    return representation;
  }
}
