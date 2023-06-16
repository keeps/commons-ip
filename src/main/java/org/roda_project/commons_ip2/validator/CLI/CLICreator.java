package org.roda_project.commons_ip2.validator.CLI;

import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

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

  /**
   * {@link Options}.
   */
  private final Options parameters;

  /**
   * {@link CommandLineParser}.
   */
  private final CommandLineParser parser;

  /**
   * Constructor that initializes create cli options.
   */
  public CLICreator() {
    this.parameters = new Options();
    this.parser = new DefaultParser();

    final Option metadata = new Option("m", "metadata", true,
      "Metadata");
    metadata.setArgs(Option.UNLIMITED_VALUES);
    metadata.setRequired(false);
    parameters.addOption(metadata);

    final Option representation = new Option("r",
      "representation", true, "Representation");
    representation.setArgs(Option.UNLIMITED_VALUES);
    representation.setRequired(false);
    parameters.addOption(representation);

    final Option metadataFile = new Option("mf", CLIConstants.CLI_CREATE_LONG_OPTION_METADATA_FILE_WITHOUT_IDENT, true,
      "Metadata file");
    metadataFile.setArgs(1);
    metadataFile.setRequired(false);
    parameters.addOption(metadataFile);

    final Option metadataType = new Option(CLIConstants.CLI_CREATE_SHORT_OPTION_METADATA_TYPE_WITHOUT_IDENT,
      CLIConstants.CLI_CREATE_LONG_OPTION_METADATA_TYPE_WITHOUT_IDENT, true, "Metadata Type");
    metadataType.setArgs(1);
    metadataType.setRequired(false);
    parameters.addOption(metadataType);

    final Option metadataVersion = new Option(CLIConstants.CLI_CREATE_SHORT_OPTION_METADATA_VERSION_WITHOUT_IDENT,
      CLIConstants.CLI_CREATE_LONG_OPTION_METADATA_VERSION_WITHOUT_IDENT, true, "Metadata Version");
    metadataVersion.setArgs(1);
    metadataVersion.setRequired(false);
    parameters.addOption(metadataVersion);

    final Option representationData = new Option(CLIConstants.CLI_CREATE_SHORT_OPTION_REPRESENTATION_DATA_WITHOUT_IDENT,
      CLIConstants.CLI_CREATE_LONG_OPTION_REPRESENTATION_DATA_WITHOUT_IDENT, true, "Representation Data");
    representationData.setArgs(Option.UNLIMITED_VALUES);
    representationData.setRequired(false);
    parameters.addOption(representationData);

    final Option targetOnly = new Option(CLIConstants.CLI_CREATE_SHORT_OPTION_REPRESENTATION_DATA_ONLY_TARGET,
      CLIConstants.CLI_CREATE_LONG_OPTION_REPRESENTATION_DATA_ONLY_TARGET, false, "Target Only");
    targetOnly.setArgs(0);
    targetOnly.setRequired(false);
    parameters.addOption(targetOnly);

    final Option representationID = new Option(CLIConstants.CLI_CREATE_SHORT_OPTION_REPRESENTATION_ID_WITHOUT_IDENT,
      CLIConstants.CLI_CREATE_LONG_OPTION_REPRESENTATION_ID_WITHOUT_IDENT, true, "Representation ID");
    representationID.setArgs(1);
    representationID.setRequired(false);
    parameters.addOption(representationID);

    final Option checksumAlg = new Option(CLIConstants.CLI_CREATE_SHORT_OPTION_CHECKSUM_ALG,
      CLIConstants.CLI_CREATE_LONG_OPTION_CHECKSUM_ALG, true, "Checksum Algorithms");
    checksumAlg.setArgs(1);
    checksumAlg.setRequired(false);
    parameters.addOption(checksumAlg);

    final Option representationType = new Option(CLIConstants.CLI_CREATE_SHORT_OPTION_REPRESENTATION_TYPE_WITHOUT_IDENT,
      CLIConstants.CLI_CREATE_LONG_OPTION_REPRESENTATION_TYPE_WITHOUT_IDENT, true, "Representation Type");
    representationType.setArgs(1);
    representationType.setRequired(false);
    parameters.addOption(representationType);

    final Option ancestors = new Option(CLIConstants.CLI_CREATE_SHORT_OPTION_ANCESTORS_WITHOUT_IDENT, "ancenstors",
      true, CLIConstants.CLI_CREATE_LONG_OPTION_ANCESTORS_WITHOUT_IDENT);
    ancestors.setArgs(Option.UNLIMITED_VALUES);
    ancestors.setRequired(false);
    parameters.addOption(ancestors);

    final Option sipID = new Option(CLIConstants.CLI_CREATE_SHORT_OPTION_SIP_ID_WITHOUT_IDENT,
      CLIConstants.CLI_CREATE_LONG_OPTION_SIP_ID_WITHOUT_IDENT, true, "SIP Identifier");
    sipID.setArgs(1);
    sipID.setRequired(false);
    parameters.addOption(sipID);

    final Option documentation = new Option(CLIConstants.CLI_CREATE_SHORT_DOCUMENTATION_WITHOUT_IDENT,
      CLIConstants.CLI_CREATE_LONG_OPTION_DOCUMENTATION_WITHOUT_IDENT, true,
      CLIConstants.CLI_CREATE_LONG_OPTION_DOCUMENTATION_WITHOUT_IDENT);
    documentation.setArgs(Option.UNLIMITED_VALUES);
    documentation.setRequired(false);
    parameters.addOption(documentation);

    final Option path = new Option("p", CLIConstants.CLI_CREATE_LONG_OPTION_PATH_WITHOUT_IDENT, true,
      "Path to Save the sip");
    path.setArgs(1);
    path.setRequired(false);
    parameters.addOption(path);

    final Option submitterAgentName = new Option(
      CLIConstants.CLI_CREATE_SHORT_OPTION_SUBMITTER_AGENT_NAME_WITHOUT_IDENT,
      CLIConstants.CLI_CREATE_LONG_OPTION_SUBMITTER_AGENT_NAME_WITHOUT_IDENT, true, "Name of the submitter agent");
    submitterAgentName.setArgs(1);
    submitterAgentName.setRequired(false);
    parameters.addOption(submitterAgentName);

  }

  /**
   * Start the creation CLI.
   *
   * @param args
   *          the args given to the CLI.
   * @return a exit code.
   */
  public int start(final String[] args) {
    try {
      final CommandLine commandLine = parser.parse(parameters, args);
      if (validateOptions(commandLine)) {
        final String[] metadata = commandLine
          .getOptionValues(CLIConstants.CLI_CREATE_LONG_OPTION_METADATA_WITHOUT_IDENT) == null
          ? commandLine.getOptionValues(CLIConstants.CLI_CREATE_SHORT_OPTION_METADATA_WITHOUT_IDENT)
          : commandLine.getOptionValues(CLIConstants.CLI_CREATE_LONG_OPTION_METADATA_WITHOUT_IDENT);
        final String[] representation = commandLine
          .getOptionValues(CLIConstants.CLI_CREATE_LONG_OPTION_REPRESENTATION_WITHOUT_IDENT) == null
          ? commandLine.getOptionValues(CLIConstants.CLI_CREATE_SHORT_OPTION_REPRESENTATION_WITHOUT_IDENT)
          : commandLine.getOptionValues(CLIConstants.CLI_CREATE_LONG_OPTION_REPRESENTATION_WITHOUT_IDENT);
        final boolean targetOnly = (commandLine
          .hasOption(CLIConstants.CLI_CREATE_SHORT_OPTION_REPRESENTATION_DATA_ONLY_TARGET) || (commandLine
          .hasOption(CLIConstants.CLI_CREATE_LONG_OPTION_REPRESENTATION_DATA_ONLY_TARGET)));  
        final String sipID = commandLine
          .getOptionValue(CLIConstants.CLI_CREATE_LONG_OPTION_SIP_ID_WITHOUT_IDENT) == null
            ? commandLine.getOptionValue(CLIConstants.CLI_CREATE_SHORT_OPTION_SIP_ID_WITHOUT_IDENT)
            : commandLine.getOptionValue(CLIConstants.CLI_CREATE_LONG_OPTION_SIP_ID_WITHOUT_IDENT);
        final String[] ancestors = commandLine
          .getOptionValues(CLIConstants.CLI_CREATE_LONG_OPTION_ANCESTORS_WITHOUT_IDENT) == null
            ? commandLine.getOptionValues(CLIConstants.CLI_CREATE_SHORT_OPTION_ANCESTORS_WITHOUT_IDENT)
            : commandLine.getOptionValues(CLIConstants.CLI_CREATE_LONG_OPTION_ANCESTORS_WITHOUT_IDENT);
        final String[] documentation = commandLine
          .getOptionValues(CLIConstants.CLI_CREATE_LONG_OPTION_DOCUMENTATION_WITHOUT_IDENT) == null
            ? commandLine.getOptionValues(CLIConstants.CLI_CREATE_SHORT_DOCUMENTATION_WITHOUT_IDENT)
            : commandLine.getOptionValues(CLIConstants.CLI_CREATE_LONG_OPTION_DOCUMENTATION_WITHOUT_IDENT);
        final String path = commandLine.getOptionValue(CLIConstants.CLI_CREATE_LONG_OPTION_PATH_WITHOUT_IDENT) == null
          ? commandLine.getOptionValue(CLIConstants.CLI_CREATE_SHORT_OPTION_PATH_WITHOUT_IDENT)
          : commandLine.getOptionValue(CLIConstants.CLI_CREATE_LONG_OPTION_PATH_WITHOUT_IDENT);
        final String submitterAgentName = commandLine
          .getOptionValue(CLIConstants.CLI_CREATE_LONG_OPTION_SUBMITTER_AGENT_NAME_WITHOUT_IDENT) == null
            ? commandLine.getOptionValue(CLIConstants.CLI_CREATE_SHORT_OPTION_SUBMITTER_AGENT_NAME_WITHOUT_IDENT)
            : commandLine.getOptionValue(CLIConstants.CLI_CREATE_LONG_OPTION_SUBMITTER_AGENT_NAME_WITHOUT_IDENT);
        final String submitterAgentID = commandLine
          .getOptionValue(CLIConstants.CLI_CREATE_LONG_OPTION_SUBMITTER_AGENT_ID_WITHOUT_IDENT) == null
            ? commandLine.getOptionValue(CLIConstants.CLI_CREATE_SHORT_OPTION_SUBMITTER_AGENT_ID_WITHOUT_IDENT)
            : commandLine.getOptionValue(CLIConstants.CLI_CREATE_LONG_OPTION_SUBMITTER_AGENT_ID_WITHOUT_IDENT);
        final String checkSum = commandLine
          .getOptionValue(CLIConstants.CLI_CREATE_LONG_OPTION_CHECKSUM_ALG) == null
            ? commandLine.getOptionValue(CLIConstants.CLI_CREATE_SHORT_OPTION_CHECKSUM_ALG)
            : commandLine.getOptionValue(CLIConstants.CLI_CREATE_LONG_OPTION_CHECKSUM_ALG);
        if (!SipCreatorUtils.validateAllOptions(metadata, documentation, representation)) {
          CLIUtils.printErrors(System.out,
            "You have to add at least one metadata file or documentation file or representation data file");
          printUsageCreate(System.out);
          return ExitCodes.EXIT_CODE_CREATE_INVALID_PATHS;
        }

        if (!SipCreatorUtils.validateMetadataPath(metadata)) {
          CLIUtils.printErrors(System.out, "The metadata file(s) given does not exist");
          return ExitCodes.EXIT_CODE_CREATE_INVALID_PATHS;
        }

        if (!SipCreatorUtils.validateRepresentationPaths(representation)) {
          CLIUtils.printErrors(System.out, "Make sure if all the representation data paths exists");
          return ExitCodes.EXIT_CODE_CREATE_INVALID_PATHS;
        }

        if (!SipCreatorUtils.validateDocumentationPaths(documentation)) {
          CLIUtils.printErrors(System.out, "Make sure if all the documentation paths exists");
          return ExitCodes.EXIT_CODE_CREATE_INVALID_PATHS;
        }

        if (!SipCreatorUtils.validateChecksumAlg(checkSum)) {
          CLIUtils.printErrors(System.out, "Make sure that the checksum algorithm is valid. You can check the "
            + "existing algorithms here: https://github.com/keeps/commons-ip/blob/master/CHECKSUM_ALGORITHMS.md");
          return ExitCodes.EXIT_CODE_CREATE_INVALID_CHECKSUM;
        }

        try {
          final Path eark2SIP = SipCreatorUtils.createEARK2SIP(metadata,
            representation, targetOnly, sipID, ancestors, documentation,
            getClass().getPackage().getImplementationVersion(), path, submitterAgentName, submitterAgentID, checkSum);
          System.out.println("Created the sip in " + eark2SIP.normalize().toAbsolutePath());
        } catch (IPException | InterruptedException e) {
          CLIUtils.printErrors(System.out, "Can't create the sip");
          return ExitCodes.EXIT_CODE_CREATE_CANNOT_SIP;
        }

      } else {
        CLIUtils.printErrors(System.out, "Missing metadata file and metadata type or representation data.");
        return ExitCodes.EXIT_CODE_CREATE_MISSING_ARGS;
      }
    } catch (final ParseException e) {
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
  private static void printUsageCreate(final PrintStream printStream) {
    final StringBuilder out = new StringBuilder();
    out.append("Usage: Commons-ip create COMMAND [OPTIONS]\n");
    out.append(CLIConstants.END_OF_LINE);
    out.append(CLIConstants.COMMANDS_KEY);
    out.append(CLIConstants.DOUBLE_END_OF_LINE);
    out.append(CLIConstants.TAB).append(CLIConstants.CLI_CREATE_OPTION_METADATA_FILE).append(", --metadata-file")
      .append(CLIConstants.DOUBLE_TAB).append("(optional) Path to the metadata file").append(CLIConstants.END_OF_LINE);
    out.append(CLIConstants.TAB).append(CLIConstants.CLI_CREATE_OPTION_METADATA_TYPE).append(", --metadata-type")
      .append(CLIConstants.DOUBLE_TAB).append("(optional) The type of the metadata").append(CLIConstants.END_OF_LINE);
    out.append(CLIConstants.TAB).append(CLIConstants.CLI_CREATE_OPTION_METADATA_VERSION).append(", --metadata-version")
      .append(CLIConstants.DOUBLE_TAB).append("(optional) The version of the metadata")
      .append(CLIConstants.END_OF_LINE);
    out.append(CLIConstants.TAB).append(CLIConstants.CLI_CREATE_OPTION_REPRESENTATION_DATA)
      .append(", --representation-data").append(CLIConstants.DOUBLE_TAB)
      .append("(optional) Paths to the representation data").append(CLIConstants.END_OF_LINE);
    out.append(CLIConstants.TAB).append(CLIConstants.CLI_CREATE_OPTION_REPRESENTATION_ID)
      .append(", --representation-id").append(CLIConstants.DOUBLE_TAB).append("(optional) The ID of the representation")
      .append(CLIConstants.END_OF_LINE);
    out.append(CLIConstants.TAB).append(CLIConstants.CLI_CREATE_OPTION_REPRESENTATION_TYPE)
      .append(", --representation-type").append(CLIConstants.DOUBLE_TAB)
      .append("(optional) The type of the representation").append(CLIConstants.END_OF_LINE);
    out.append(CLIConstants.TAB).append(CLIConstants.CLI_CREATE_OPTION_ANCESTORS).append(", --ancestors")
      .append(CLIConstants.DOUBLE_TAB).append("(optional) Ancestors of this SIP").append(CLIConstants.END_OF_LINE);
    out.append(CLIConstants.TAB).append(CLIConstants.CLI_CREATE_OPTION_SIP_ID).append(", --sip-id")
      .append(CLIConstants.DOUBLE_TAB).append("(optional) The ID of the SIP").append(CLIConstants.END_OF_LINE);
    out.append(CLIConstants.TAB).append(CLIConstants.CLI_CREATE_OPTION_DOCUMENTATION).append(", --documentation")
      .append(CLIConstants.DOUBLE_TAB).append("(optional) Paths to the documentation").append(CLIConstants.END_OF_LINE);
    out.append(CLIConstants.TAB).append(CLIConstants.CLI_CREATE_OPTION_PATH).append(", --path")
      .append(CLIConstants.DOUBLE_TAB).append("(optional) Path to save the SIP").append(CLIConstants.END_OF_LINE);
    out.append(CLIConstants.TAB).append(CLIConstants.CLI_CREATE_OPTION_SUBMITTER_AGENT_NAME)
      .append(", --submitter-agent-name").append(CLIConstants.DOUBLE_TAB)
      .append("(optional) The name of the submitter agent").append(CLIConstants.END_OF_LINE);
    out.append(CLIConstants.TAB).append(CLIConstants.CLI_CREATE_OPTION_SUBMITTER_AGENT_ID)
      .append(", --submitter-agent-id").append(CLIConstants.DOUBLE_TAB)
      .append("(optional) The identification code of the submitter id").append(CLIConstants.END_OF_LINE);
    out.append(CLIConstants.TAB).append(CLIConstants.CLI_CREATE_OPTION_REPRESENTATION_DATA_ONLY_TARGET)
      .append(", --target-only").append(CLIConstants.DOUBLE_TAB)
      .append("(optional) Only add contents of target representation folder").append(CLIConstants.END_OF_LINE);
    out.append(CLIConstants.TAB).append(CLIConstants.CLI_CREATE_OPTION_CHECKSUM_ALG)
      .append(", --checksum-alg").append(CLIConstants.DOUBLE_TAB)
      .append("(optional) Checksum Algorithm").append(CLIConstants.END_OF_LINE);
    printStream.append(out).flush();
  }

  private boolean validateOptions(final CommandLine commandLine) {

    final String[] metadata = commandLine
      .getOptionValues(CLIConstants.CLI_CREATE_LONG_OPTION_METADATA_WITHOUT_IDENT) == null
      ? commandLine.getOptionValues(CLIConstants.CLI_CREATE_SHORT_OPTION_METADATA_WITHOUT_IDENT)
      : commandLine.getOptionValues(CLIConstants.CLI_CREATE_LONG_OPTION_METADATA_WITHOUT_IDENT);

    final String[] representation = commandLine
      .getOptionValues(CLIConstants.CLI_CREATE_LONG_OPTION_REPRESENTATION_WITHOUT_IDENT) == null
      ? commandLine.getOptionValues(CLIConstants.CLI_CREATE_SHORT_OPTION_REPRESENTATION_WITHOUT_IDENT)
      : commandLine.getOptionValues(CLIConstants.CLI_CREATE_LONG_OPTION_REPRESENTATION_WITHOUT_IDENT);

    for(String metadataInfo : metadata){
      String[] metadataInfoArray = metadataInfo.split(";");
      if(!SipCreatorUtils.validateMetadataOptions(metadataInfoArray[0], metadataInfoArray[1], metadataInfoArray[2])){
        return false;
      }
    }

    for(String representationInfo: representation){
      String[] representationInfoArray = representationInfo.split(";");
      String[] representationFiles = representationInfoArray[0].split(",");
      if(!SipCreatorUtils.validateRepresentationOptions(representationFiles,
        representationInfoArray[1],  representationInfoArray[2])){
        return false;
      }
    }
    return true;
  }
}
