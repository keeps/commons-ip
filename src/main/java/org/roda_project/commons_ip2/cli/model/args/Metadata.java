package org.roda_project.commons_ip2.cli.model.args;

import picocli.CommandLine;

/**
 * @author Miguel Guimarães <mguimaraes@keep.pt>
 */
public class Metadata {
  @CommandLine.Option(names = "--metadata-file", required = true, paramLabel = "<path>", description = "Path to descriptive metadata file")
  String metadataFile;
  @CommandLine.Option(names = "--metadata-type", paramLabel = "<type>", description = "Descriptive metadata type")
  String metadataType;
  @CommandLine.Option(names = "--metadata-version", paramLabel = "<version>", description = "Descriptive metadata version")
  String metadataVersion;
  @CommandLine.Option(names = {
    "--metadata-schema"}, description = "Path to descriptive metadata schema file", paramLabel = "<path>")
  String metadataSchema;

  public String getMetadataFile() {
    return metadataFile;
  }

  public String getMetadataType() {
    return metadataType;
  }

  public String getMetadataVersion() {
    return metadataVersion;
  }

  public String getMetadataSchema() {
    return metadataSchema;
  }
}
