package org.roda_project.commons_ip2.validator.CLI;

import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

/**
 * {@author João Gomes <jgomes@keep.pt>}.
 */
public class CreateCLI {

  private final Options parameters;

  private final CommandLineParser parser;

  public CreateCLI() {
    this.parameters = new Options();
    this.parser = new DefaultParser();

    Option metadataFile = new Option("mf", "metadata-file", true, "Metadata file");
    metadataFile.setArgs(1);
    metadataFile.setRequired(false);
    parameters.addOption(metadataFile);

    Option metadataType = new Option("mt", "metadata-type", true, "Metadata Type");
    metadataType.setArgs(1);
    metadataType.setRequired(false);
    parameters.addOption(metadataType);

    Option metadataVersion = new Option("mv", "metadata-version", true, "Metadata Version");
    metadataVersion.setArgs(1);
    metadataVersion.setRequired(false);
    parameters.addOption(metadataVersion);

    Option representationData = new Option("rd", "representation-data", true, "Representation Data");
    representationData.setArgs(Option.UNLIMITED_VALUES);
    representationData.setRequired(false);
    parameters.addOption(representationData);

    Option representationID = new Option("rid", "representation-id", true, "Representation ID");
    representationID.setArgs(1);
    representationID.setRequired(false);
    parameters.addOption(representationID);

    Option representationType = new Option("rt", "representation-type", true, "Representation Type");
    representationType.setArgs(1);
    representationType.setRequired(false);
    parameters.addOption(representationType);

    Option ancestors = new Option("a", "ancenstors", true, "Ancestors");
    ancestors.setArgs(Option.UNLIMITED_VALUES);
    ancestors.setRequired(false);
    parameters.addOption(ancestors);

    Option sipID = new Option("sid", "sip-id", true, "SIP Identifier");
    sipID.setArgs(1);
    sipID.setRequired(false);
    parameters.addOption(sipID);

    Option documentation = new Option("doc", "documentation", true, "Documentation");
    documentation.setArgs(Option.UNLIMITED_VALUES);
    documentation.setRequired(false);
    parameters.addOption(documentation);

  }

  public int start(String[] args) {
    try {
      CommandLine commandLine = parser.parse(parameters, args);
      if (validateOptions(commandLine)) {
        String metadataFile = commandLine.getOptionValue(CLIConstants.CLI_CREATE_OPTION_METADATA_FILE);
        String metadataType = commandLine.getOptionValue(CLIConstants.CLI_CREATE_OPTION_METADATA_TYPE);
        String metadataVersion = commandLine.getOptionValue(CLIConstants.CLI_CREATE_OPTION_METADATA_VERSION);
        String[] representationData = commandLine.getOptionValues(CLIConstants.CLI_CREATE_OPTION_REPRESENTATION_DATA);
        String representationType = commandLine.getOptionValue(CLIConstants.CLI_CREATE_OPTION_REPRESENTATION_TYPE);
        String representationID = commandLine.getOptionValue(CLIConstants.CLI_CREATE_OPTION_REPRESENTATION_ID);
        String sipID = commandLine.getOptionValue(CLIConstants.CLI_CREATE_OPTION_SIP_ID);
        String[] ancestors = commandLine.getOptionValues(CLIConstants.CLI_CREATE_OPTION_ANCESTORS);
        String[] documentation = commandLine.getOptionValues(CLIConstants.CLI_CREATE_OPTION_DOCUMENTATION);

        if (!validateMetadataPath(metadataFile)){
          CLIUtils.printErrors(System.out, "The metadata file given does not exist");
        }

        if(!validateRepresentationPaths(representationData)){
          CLIUtils.printErrors(System.out, "Make sure if all the representation data paths exists");
        }

        if(! validateDocumentationPaths(documentation)){
          CLIUtils.printErrors(System.out, "Make sure if all the documentation paths exists");
        }

      } else {
        CLIUtils.printErrors(System.out, "Missing metadata file or representation data.");
      }
    } catch (ParseException e) {
      printUsageCreate(System.out);
    }
    return 0;
  }

  /**
   * Print All available options if some required option is missing.
   *
   * @param printStream
   *          {@link PrintStream}
   */
  private static void printUsageCreate(PrintStream printStream) {
    StringBuilder out = new StringBuilder();
    out.append("Usage: Commons-ip create COMMAND [OPTIONS]\n");
    out.append("\n");
    out.append("Commands:");
    out.append("\n\n");
    out.append("\t").append(CLIConstants.CLI_CREATE_OPTION_METADATA_FILE).append(", --metadata-file").append("\t\t")
      .append("(optional) Path to the metadata file").append("\n");
    out.append("\t").append(CLIConstants.CLI_CREATE_OPTION_METADATA_TYPE).append(", --metadata-type").append("\t\t")
      .append("(optional) The type of the metadata").append("\n");
    out.append("\t").append(CLIConstants.CLI_CREATE_OPTION_METADATA_VERSION).append(", --metadata-version")
      .append("\t\t").append("(optional) The version of the metadata").append("\n");
    out.append("\t").append(CLIConstants.CLI_CREATE_OPTION_REPRESENTATION_DATA).append(", --representation-data")
      .append("\t\t").append("(optional) Paths to the representation data").append("\n");
    out.append("\t").append(CLIConstants.CLI_CREATE_OPTION_REPRESENTATION_ID).append(", --representation-id")
      .append("\t\t").append("(optional) The ID of the representation").append("\n");
    out.append("\t").append(CLIConstants.CLI_CREATE_OPTION_REPRESENTATION_TYPE).append(", --representation-type")
      .append("\t\t").append("(optional) The type of the representation").append("\n");
    out.append("\t").append(CLIConstants.CLI_CREATE_OPTION_ANCESTORS).append(", --ancestors").append("\t\t")
      .append("(optional) Ancestors of this SIP").append("\n");
    out.append("\t").append(CLIConstants.CLI_CREATE_OPTION_SIP_ID).append(", --sip-id").append("\t\t")
      .append("(optional) The ID of the SIP").append("\n");
    out.append("\t").append(CLIConstants.CLI_CREATE_OPTION_DOCUMENTATION).append(", --documentation").append("\t\t")
      .append("(optional) Paths to the documentation").append("\n");
    printStream.append(out).flush();
  }

  private boolean validateOptions(CommandLine commandLine) {

    final String metadataFile = commandLine.getOptionValue(CLIConstants.CLI_CREATE_OPTION_METADATA_FILE);
    final String metadataType = commandLine.getOptionValue(CLIConstants.CLI_CREATE_OPTION_METADATA_TYPE);
    final String metadataVersion = commandLine.getOptionValue(CLIConstants.CLI_CREATE_OPTION_METADATA_VERSION);

    final String[] representationData = commandLine.getOptionValues(CLIConstants.CLI_CREATE_OPTION_REPRESENTATION_DATA);
    final String representationType = commandLine.getOptionValue(CLIConstants.CLI_CREATE_OPTION_REPRESENTATION_TYPE);
    final String representationID = commandLine.getOptionValue(CLIConstants.CLI_CREATE_OPTION_REPRESENTATION_ID);

    boolean metadataValid = validateMetadataOptions(metadataFile, metadataType, metadataVersion);
    boolean representationValid = validateRepresentationOptions(representationData, representationType,
      representationID);

    return metadataValid && representationValid;
  }

  private boolean validateMetadataOptions(String metadataFile, String metadataType, String metadataVersion) {
    return metadataFile != null || (metadataType == null && metadataVersion == null);
  }

  private boolean validateRepresentationOptions(String[] representationData, String representationType,
    String representationID) {
    return representationData != null || (representationType == null && representationID == null);
  }

  private boolean validateMetadataPath(String metadataFile) {
    if (metadataFile != null) {
      return Files.exists(Paths.get(metadataFile));
    }
    return false;
  }

  private boolean validateRepresentationPaths(String[] representationData) {
    for (String data : representationData) {
      if (!Files.exists(Paths.get(data))) {
        return false;
      }
    }
    return true;
  }

  private boolean validateDocumentationPaths(String[] documentationPaths) {
    for (String doc : documentationPaths) {
      if (!Files.exists(Paths.get(doc))) {
        return false;
      }
    }
    return true;
  }
}
