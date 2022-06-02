package org.roda_project.commons_ip2.validator.CLI;

import java.io.PrintStream;
import java.nio.file.Path;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.roda_project.commons_ip.utils.IPException;
import org.roda_project.commons_ip2.validator.utils.CLIUtils;
import org.roda_project.commons_ip2.validator.utils.ExitCodes;
import org.roda_project.commons_ip2.validator.utils.SipCreatorUtils;

/**
 * {@author João Gomes <jgomes@keep.pt>}.
 */
public class CLICreator {

  private final Options parameters;

  private final CommandLineParser parser;

  public CLICreator() {
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

    Option path = new Option("p", "path", true, "Path to Save the sip");
    path.setArgs(1);
    path.setRequired(false);
    parameters.addOption(path);

    Option submitterAgentName = new Option("sn", "submitter-agent-name", true, "Name of the submitter agent");
    submitterAgentName.setArgs(1);
    submitterAgentName.setRequired(false);
    parameters.addOption(submitterAgentName);

  }

  public int start(String[] args) {
    try {
      CommandLine commandLine = parser.parse(parameters, args);
      if (validateOptions(commandLine)) {
        String metadataFile = commandLine.getOptionValue("metadata-file") == null ? commandLine.getOptionValue("mv")
          : commandLine.getOptionValue("metadata-file");
        String metadataType = commandLine.getOptionValue("metadata-type") == null ? commandLine.getOptionValue("mt")
          : commandLine.getOptionValue("metadata-type");
        String metadataVersion = commandLine.getOptionValue("metadata-version") == null
          ? commandLine.getOptionValue("mv")
          : commandLine.getOptionValue("metadata-version");
        String[] representationData = commandLine.getOptionValues("representation-data") == null
          ? commandLine.getOptionValues("rd")
          : commandLine.getOptionValues("representation-data");
        String representationType = commandLine.getOptionValue("representation-type") == null
          ? commandLine.getOptionValue("rt")
          : commandLine.getOptionValue("representation-type");
        String representationID = commandLine.getOptionValue("representation-id") == null
          ? commandLine.getOptionValue("rid")
          : commandLine.getOptionValue("representation-id");
        String sipID = commandLine.getOptionValue("sip-id") == null ? commandLine.getOptionValue("sid")
          : commandLine.getOptionValue("sip-id");
        String[] ancestors = commandLine.getOptionValues("ancestors") == null ? commandLine.getOptionValues("a")
          : commandLine.getOptionValues("ancestors");
        String[] documentation = commandLine.getOptionValues("documentation") == null
          ? commandLine.getOptionValues("doc")
          : commandLine.getOptionValues("documentation");
        String path = commandLine.getOptionValue("path") == null ? commandLine.getOptionValue("p")
          : commandLine.getOptionValue("path");
        String submitterAgentName = commandLine.getOptionValue("submitter-agent-name") == null
          ? commandLine.getOptionValue("sn")
          : commandLine.getOptionValue("submitter-agent-name");
        String submitterAgentID = commandLine.getOptionValue("submitter-agent-id") == null
          ? commandLine.getOptionValue("aid")
          : commandLine.getOptionValue("submitter-agent-id");

        if (!SipCreatorUtils.validateMetadataPath(metadataFile)) {
          CLIUtils.printErrors(System.out, "The metadata file given does not exist");
          return ExitCodes.EXIT_CODE_CREATE_INVALID_PATHS;
        }

        if (!SipCreatorUtils.validateRepresentationPaths(representationData)) {
          CLIUtils.printErrors(System.out, "Make sure if all the representation data paths exists");
          return ExitCodes.EXIT_CODE_CREATE_INVALID_PATHS;
        }

        if (!SipCreatorUtils.validateDocumentationPaths(documentation)) {
          CLIUtils.printErrors(System.out, "Make sure if all the documentation paths exists");
          return ExitCodes.EXIT_CODE_CREATE_INVALID_PATHS;
        }

        try {
          Path eark2SIP = SipCreatorUtils.createEARK2SIP(metadataFile, metadataType, metadataVersion,
            representationData, representationType, representationID, sipID, ancestors, documentation,
            getClass().getPackage().getImplementationVersion(), path, submitterAgentName, submitterAgentID);
          System.out.println("Created the sip in " + eark2SIP.normalize().toAbsolutePath());
        } catch (IPException | InterruptedException e) {
          CLIUtils.printErrors(System.out, "Can't create the sip");
          return ExitCodes.EXIT_CODE_CREATE_CANNOT_SIP;
        }

      } else {
        CLIUtils.printErrors(System.out, "Missing metadata file and metadata type or representation data.");
        return ExitCodes.EXIT_CODE_CREATE_MISSING_ARGS;
      }
    } catch (ParseException e) {
      printUsageCreate(System.out);
      return ExitCodes.EXIT_PARSE_ARG;
    }
    return ExitCodes.EXIT_CODE_OK;
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
    out.append("\t").append(CLIConstants.CLI_CREATE_OPTION_PATH).append(", --path").append("\t\t")
      .append("(optional) Path to save the SIP").append("\n");
    out.append("\t").append(CLIConstants.CLI_CREATE_OPTION_SUBMITTER_AGENT_NAME).append(", --submitter-agent-name")
      .append("\t\t").append("(optional) The name of the submitter agent").append("\n");
    out.append("\t").append(CLIConstants.CLI_CREATE_OPTION_SUBMITTER_AGENT_ID).append(", --submitter-agent-id")
      .append("\t\t").append("(optional) The identification code of the submitter id").append("\n");
    printStream.append(out).flush();
  }

  private boolean validateOptions(CommandLine commandLine) {

    final String metadataFile = commandLine.getOptionValue("metadata-file") == null ? commandLine.getOptionValue("mv")
      : commandLine.getOptionValue("metadata-file");
    final String metadataType = commandLine.getOptionValue("metadata-type") == null ? commandLine.getOptionValue("mt")
      : commandLine.getOptionValue("metadata-type");
    final String metadataVersion = commandLine.getOptionValue("metadata-version") == null
      ? commandLine.getOptionValue("mv")
      : commandLine.getOptionValue("metadata-version");

    final String[] representationData = commandLine.getOptionValues("representation-data") == null
      ? commandLine.getOptionValues("rd")
      : commandLine.getOptionValues("representation-data");
    final String representationType = commandLine.getOptionValue("representation-type") == null
      ? commandLine.getOptionValue("rt")
      : commandLine.getOptionValue("representation-type");
    final String representationID = commandLine.getOptionValue("representation-id") == null
      ? commandLine.getOptionValue("rid")
      : commandLine.getOptionValue("representation-id");

    boolean metadataValid = SipCreatorUtils.validateMetadataOptions(metadataFile, metadataType, metadataVersion);
    boolean representationValid = SipCreatorUtils.validateRepresentationOptions(representationData, representationType,
      representationID);

    return metadataValid && representationValid;
  }
}
