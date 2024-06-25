package org.roda_project.commons_ip2.cli.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.roda_project.commons_ip.utils.IPException;
import org.roda_project.commons_ip2.cli.model.args.MetadataGroup;
import org.roda_project.commons_ip2.cli.model.args.RepresentationGroup;
import org.roda_project.commons_ip2.cli.model.enums.CSIPVersion;
import org.roda_project.commons_ip2.cli.model.enums.ChecksumAlgorithm;
import org.roda_project.commons_ip2.cli.model.exception.SIPBuilderException;
import org.roda_project.commons_ip2.model.IPContentInformationType;
import org.roda_project.commons_ip2.model.IPContentType;
import org.roda_project.commons_ip2.model.SIP;
import org.roda_project.commons_ip2.model.impl.eark.EARKSIP;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Miguel Guimarães <mguimaraes@keep.pt>
 */
public class SIPBuilder {
  private static final Logger LOGGER = LoggerFactory.getLogger(SIPBuilder.class);

  private List<MetadataGroup> metadataArgs = new ArrayList<>();
  private List<RepresentationGroup> representationArgs = new ArrayList<>();
  private boolean targetOnly;
  private CSIPVersion version = CSIPVersion.V210;
  private String path;
  private String submitterAgentName;
  private String submitterAgentId;
  private String sipId;
  private List<String> ancestors;
  private ChecksumAlgorithm checksumAlgorithm = ChecksumAlgorithm.SHA256;
  private List<String> documentation = new ArrayList<>();

  private String softwareVersion;
  private Boolean overrideSchema;

  public SIPBuilder() {
    // Empty Constructor
  }

  public SIPBuilder setMetadataArgs(List<MetadataGroup> metadataArgs) {
    this.metadataArgs = metadataArgs;
    return this;
  }

  public SIPBuilder setOverride(Boolean override) {
    this.overrideSchema = override;
    return this;
  }

  public SIPBuilder setRepresentationArgs(List<RepresentationGroup> representationArgs) {
    this.representationArgs = representationArgs;
    return this;
  }

  public SIPBuilder setTargetOnly(boolean targetOnly) {
    this.targetOnly = targetOnly;
    return this;
  }

  public SIPBuilder setVersion(CSIPVersion version) {
    this.version = version;
    return this;
  }

  public SIPBuilder setPath(String path) {
    this.path = path;
    return this;
  }

  public SIPBuilder setSubmitterAgentName(String submitterAgentName) {
    this.submitterAgentName = submitterAgentName;
    return this;
  }

  public SIPBuilder setSubmitterAgentId(String submitterAgentId) {
    this.submitterAgentId = submitterAgentId;
    return this;
  }

  public SIPBuilder setSipId(String sipId) {
    this.sipId = sipId;
    return this;
  }

  public SIPBuilder setAncestors(List<String> ancestors) {
    this.ancestors = ancestors;
    return this;
  }

  public SIPBuilder setChecksumAlgorithm(ChecksumAlgorithm checksumAlgorithm) {
    this.checksumAlgorithm = checksumAlgorithm;
    return this;
  }

  public SIPBuilder setDocumentation(List<String> documentation) {
    this.documentation = documentation;
    return this;
  }

  public SIPBuilder setSoftwareVersion(String softwareVersion) {
    this.softwareVersion = softwareVersion;
    return this;
  }

  public Path build() throws SIPBuilderException, InterruptedException {
    final SIP sip = new EARKSIP(SIPBuilderUtils.getOrGenerateID(this.sipId), IPContentType.getMIXED(),
      IPContentInformationType.getMIXED(), version.toString());

    String softVersion = "DEVELOPMENT-VERSION";

    if (softwareVersion != null) {
      softVersion = softwareVersion;
    }

    sip.addCreatorSoftwareAgent("RODA commons-ip", softVersion);
    sip.addSubmitterAgent(submitterAgentName, submitterAgentId);
    sip.setDescription("SIP created by commons-ip CLI");

    if (checksumAlgorithm != null) {
      sip.setChecksumAlgorithm(checksumAlgorithm.toString());
    }

    if (overrideSchema) {
      sip.setOverride();
    }

    try {
      SIPBuilderUtils.addMetadataGroupsToSIP(sip, metadataArgs);
    } catch (IPException e) {
      LOGGER.debug("Cannot add metadata to the SIP", e);
      throw new SIPBuilderException("Cannot add metadata to the SIP.");
    }

    try {
      SIPBuilderUtils.addRepresentationGroupsToSIP(sip, representationArgs, targetOnly);
    } catch (IPException e) {
      LOGGER.debug("Cannot add representation to the SIP", e);
      throw new SIPBuilderException("Cannot add representation to the SIP");
    }

    if (documentation != null) {
      try {
        SIPBuilderUtils.addDocumentationToSIP(sip, documentation);
      } catch (IOException e) {
        LOGGER.debug("Cannot add documentation to the SIP", e);
        throw new SIPBuilderException("Cannot add documentation to the SIP");
      }
    }

    if (ancestors != null) {
      sip.setAncestors(ancestors);
    }

    final Path buildPath;
    if (path != null && Files.exists(Paths.get(path))) {
      buildPath = Paths.get(path);
    } else {
      buildPath = Paths.get(System.getProperty("user.dir"));
    }

    try {
      return sip.build(buildPath);
    } catch (IPException e) {
      LOGGER.debug("Unable to create the E-ARK SIP", e);
      throw new SIPBuilderException("Unable to create the E-ARK SIP");
    } catch (IOException e) {
      LOGGER.debug("Unable to create the E-ARK SIP", e);
      throw new SIPBuilderException("Unable to create the E-ARK SIP");
    }
  }
}
