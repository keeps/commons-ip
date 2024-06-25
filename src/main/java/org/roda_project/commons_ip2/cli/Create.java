package org.roda_project.commons_ip2.cli;

import static org.roda_project.commons_ip2.cli.model.ExitCodes.EXIT_CODE_OK;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import org.roda_project.commons_ip2.cli.model.args.MetadataGroup;
import org.roda_project.commons_ip2.cli.model.args.RepresentationGroup;
import org.roda_project.commons_ip2.cli.model.enums.CSIPVersion;
import org.roda_project.commons_ip2.cli.model.enums.ChecksumAlgorithm;
import org.roda_project.commons_ip2.cli.model.exception.CLIException;
import org.roda_project.commons_ip2.cli.model.exception.InvalidPathException;
import org.roda_project.commons_ip2.cli.model.exception.SIPBuilderException;
import org.roda_project.commons_ip2.cli.utils.SIPBuilder;
import org.roda_project.commons_ip2.cli.utils.CLI.CreateCommandUtils;
import org.roda_project.commons_ip2.utils.LogSystem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import picocli.CommandLine;

/**
 * @author Miguel Guimarães <mguimaraes@keep.pt>
 */
@CommandLine.Command(name = "create", description = "Creates E-ARK SIP packages%n", showDefaultValues = true)
public class Create implements Callable<Integer> {
  private static final Logger LOGGER = LoggerFactory.getLogger(LogSystem.class);
  @CommandLine.Spec
  CommandLine.Model.CommandSpec spec;
  @CommandLine.ArgGroup(exclusive = false, multiplicity = "0..*", heading = "This is the descriptive metadata section:%n")
  List<MetadataGroup> metadataListArgs = new ArrayList<>();

  @CommandLine.ArgGroup(exclusive = false, multiplicity = "0..*", heading = "This is the representation section:%n")
  List<RepresentationGroup> representationListArgs = new ArrayList<>();

  @CommandLine.Option(names = {"-h", "--help"}, usageHelp = true, description = "display this help and exit")
  boolean help;

  @CommandLine.Option(names = {"-T", "--target-only"}, description = "Adds only the files for the representations")
  boolean targetOnly;

  @CommandLine.Option(names = {"--override-schema"}, description = "Overrides default schema")
  boolean overrideSchema;

  @CommandLine.Option(names = {"-v",
    "--version"}, description = "E-ARK SIP specification version (possible values: ${COMPLETION-CANDIDATES})")
  CSIPVersion version = CSIPVersion.V210;

  @CommandLine.Option(names = {"-p", "--path"}, arity = "1", description = "Path where the E-ARK SIP should be saved")
  String path = System.getProperty("user.dir");

  @CommandLine.Option(names = {"--submitter-name"}, description = "Submitter agent name", paramLabel = "<name>")
  String submitterAgentName;

  @CommandLine.Option(names = {"--submitter-id"}, description = "Submitter agent identifier", paramLabel = "<id>")
  String submitterAgentId;

  @CommandLine.Option(names = {"--sip-id"}, description = "E-ARK SIP identifier")
  String sipId;

  @CommandLine.Option(names = {"-a",
    "--ancestors"}, split = ",", description = "E-ARK SIP ancestors", paramLabel = "<ancestor>")
  List<String> ancestors;

  @CommandLine.Option(names = {"-C",
    "--checksum"}, paramLabel = "<algorithm>", description = "Checksum algorithms (possible values: ${COMPLETION-CANDIDATES})")
  ChecksumAlgorithm checksumAlgorithm = ChecksumAlgorithm.SHA256;

  @CommandLine.Option(names = {"-d",
    "--documentation"}, description = "Path(s) to documentation file(s)", split = ",", paramLabel = "<path>", showDefaultValue = CommandLine.Help.Visibility.NEVER)
  List<String> documentation = new ArrayList<>();

  @Override
  public Integer call() throws CLIException, InvalidPathException, SIPBuilderException, InterruptedException {
    if (!CreateCommandUtils.validateRepresentationDataPaths(representationListArgs)) {
      throw new InvalidPathException("Make sure if all the representation paths exists");
    }

    if (!CreateCommandUtils.validateDocumentationPaths(documentation)) {
      throw new InvalidPathException("Make sure if all the documentation paths exists");
    }

    if (!CreateCommandUtils.validateMetadataPaths(metadataListArgs)) {
      throw new InvalidPathException("Make sure if all the descriptive metadata paths exists");
    }

    if (!CreateCommandUtils.validateMetadataSchemaPaths(metadataListArgs)) {
      throw new InvalidPathException("Make sure if all the descriptive metadata schema paths exists");
    }

    if (metadataListArgs.isEmpty() && representationListArgs.isEmpty()) {
      throw new CLIException("At least one section must be present, metadata or representation");
    }

    CommandLine cmd = spec.commandLine();
    String commandLineString = String.join(" ", cmd.getParseResult().originalArgs());
    LogSystem.logOperatingSystemInfo();
    LOGGER.debug("command executed: " + commandLineString);

    final Path sipPath = new SIPBuilder().setMetadataArgs(metadataListArgs).setOverride(overrideSchema)
      .setRepresentationArgs(representationListArgs).setTargetOnly(targetOnly).setSipId(sipId).setAncestors(ancestors)
      .setDocumentation(documentation).setSoftwareVersion(getClass().getPackage().getImplementationVersion())
      .setPath(path).setSubmitterAgentId(submitterAgentId).setSubmitterAgentName(submitterAgentName)
      .setChecksumAlgorithm(checksumAlgorithm).setVersion(version).build();

    new CommandLine(this).getOut().printf("E-ARK SIP created at '%s'%n", sipPath.normalize().toAbsolutePath());

    return EXIT_CODE_OK;
  }
}
