/**
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE file at the root of the source
 * tree and available online at
 *
 * https://github.com/keeps/commons-ip
 */
package org.roda_project.commons_ip2.model.impl.eark;

import java.io.IOException;
import java.nio.channels.ClosedByInterruptException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.roda_project.commons_ip.model.ParseException;
import org.roda_project.commons_ip.utils.IPEnums;
import org.roda_project.commons_ip.utils.IPException;
import org.roda_project.commons_ip.utils.ZipEntryInfo;
import org.roda_project.commons_ip2.mets_v1_12.beans.StructMapType;
import org.roda_project.commons_ip2.model.IPConstants;
import org.roda_project.commons_ip2.model.IPContentInformationType;
import org.roda_project.commons_ip2.model.IPContentType;
import org.roda_project.commons_ip2.model.MetsWrapper;
import org.roda_project.commons_ip2.model.SIP;
import org.roda_project.commons_ip2.model.impl.ModelUtils;
import org.roda_project.commons_ip2.model.impl.eark.out.writers.strategy.WriteStrategy;
import org.roda_project.commons_ip2.utils.METSUtils;
import org.roda_project.commons_ip2.utils.ZIPUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.roda_project.commons_ip2.model.IPConstants.METS_ENCODE_AND_DECODE_HREF;

public class EARKSIP extends SIP {
  private static final Logger LOGGER = LoggerFactory.getLogger(EARKSIP.class);

  private static final String SIP_TEMP_DIR = "EARKSIP";

  private static final String DEFAULT_SIP_VERSION = "2.1.0";

  private EARKMETSCreator metsCreator;

  public EARKSIP() {
    super();
    setProfile(IPConstants.SIP_SPEC_PROFILE);
    METSGeneratorFactory factory = new METSGeneratorFactory();
    metsCreator = factory.getGenerator(DEFAULT_SIP_VERSION);
  }

  /**
   * @param sipId
   *          will be used as OBJID in METS (/mets[@OBJID])
   */
  public EARKSIP(String sipId) {
    super(sipId);
    setProfile(IPConstants.SIP_SPEC_PROFILE);

    METSGeneratorFactory factory = new METSGeneratorFactory();
    metsCreator = factory.getGenerator(DEFAULT_SIP_VERSION);
  }

  /**
   * @param sipId
   *          will be used as OBJID in METS (/mets[@OBJID])
   */
  public EARKSIP(String sipId, IPContentType contentType, IPContentInformationType contentInformationType) {
    this(sipId, contentType, contentInformationType, DEFAULT_SIP_VERSION);
  }

  public EARKSIP(String sipId, IPContentType contentType, IPContentInformationType contentInformationType, String version) {
    super(sipId, contentType,contentInformationType);
    if (version.equals("2.2.0") || version.equals("2.1.0")) {
      setProfile(IPConstants.SIP_SPEC_PROFILE.replace(".xml", "-v" + version.replace(".", "-") + ".xml"));
    }
    else {
      setProfile(IPConstants.SIP_SPEC_PROFILE);
    }
    METSGeneratorFactory factory = new METSGeneratorFactory();
    metsCreator = factory.getGenerator(version);
  }

  /**
   *
   * parse and all parse related methods; during parse, validation is also
   * conducted and stored inside the SIP
   * _________________________________________________________________________
   */

  public SIP parse(Path source, Path destinationDirectory) throws ParseException {
    return parseEARKSIP(source, destinationDirectory);
  }

  public SIP parse(Path source) throws ParseException {
    try {
      return parse(source, Files.createTempDirectory("unzipped"));
    } catch (IOException e) {
      throw new ParseException("Error creating temporary directory for E-ARK SIP parse", e);
    }
  }

  private SIP parseEARKSIP(final Path source, final Path destinationDirectory) throws ParseException {
    try {
      METS_ENCODE_AND_DECODE_HREF = true;
      SIP sip = new EARKSIP();

      EARKUtils earkUtils = new EARKUtils(metsCreator);

      Path sipPath = ZIPUtils.extractIPIfInZipFormat(source, destinationDirectory);
      sip.setBasePath(sipPath);

      MetsWrapper metsWrapper = earkUtils.processMainMets(sip, sipPath);

      if (sip.isValid()) {

        StructMapType structMap = earkUtils.getEARKStructMap(metsWrapper, sip, true);

        if (structMap != null) {
          earkUtils.preProcessStructMap(metsWrapper, structMap);
          earkUtils.processDescriptiveMetadata(metsWrapper, sip, LOGGER, null, sip.getBasePath());
          earkUtils.processOtherMetadata(metsWrapper, sip, LOGGER, null, sip.getBasePath());
          earkUtils.processPreservationMetadata(metsWrapper, sip, LOGGER, null, sip.getBasePath());
          earkUtils.processRepresentations(metsWrapper, sip, LOGGER);
          earkUtils.processSchemasMetadata(metsWrapper, sip, sip.getBasePath());
          earkUtils.processDocumentationMetadata(metsWrapper, sip, sip.getBasePath());
          earkUtils.processAncestors(metsWrapper, sip);
        }
      }

      return sip;
    } catch (final IPException e) {
      throw new ParseException("Error parsing E-ARK SIP", e);
    }
  }

  /**
   *
   * build and all build related methods
   * _________________________________________________________________________
   */
  @Override
  public Path build(WriteStrategy writeStrategy) throws IPException, InterruptedException {
    return build(writeStrategy, false);
  }

  @Override
  public Path build(WriteStrategy writeStrategy, final boolean onlyManifest)
    throws IPException, InterruptedException {
    return build(writeStrategy, null);
  }

  @Override
  public Path build(WriteStrategy writeStrategy, String fileNameWithoutExtension)
    throws IPException, InterruptedException {
    return build(writeStrategy, fileNameWithoutExtension, false, IPEnums.SipType.EARK2);
  }

  @Override
  public Path build(WriteStrategy writeStrategy, String fileNameWithoutExtension, IPEnums.SipType sipType)
    throws IPException, InterruptedException {
    return build(writeStrategy, fileNameWithoutExtension, false, sipType);
  }

  @Override
  public Path build(WriteStrategy writeStrategy, String fileNameWithoutExtension, boolean onlyManifest)
    throws IPException, InterruptedException {
    return build(writeStrategy, fileNameWithoutExtension, false, IPEnums.SipType.EARK2);
  }

  @Override
  public Path build(WriteStrategy writeStrategy, final String fileNameWithoutExtension, final boolean onlyManifest,
    IPEnums.SipType sipType) throws IPException, InterruptedException {
    IPConstants.METS_ENCODE_AND_DECODE_HREF = true;
    Path buildDir = ModelUtils.createBuildDir(SIP_TEMP_DIR);

    EARKUtils earkUtils = new EARKUtils(metsCreator);

    try {
      Map<String, ZipEntryInfo> zipEntries = getZipEntries();
      //default metadata need to be added before creating the mets in order to add them in the mets file
      earkUtils.addDefaultSchemas(LOGGER, getSchemas(), buildDir, getOverride());

      boolean isMetadataOther = (this.getOtherMetadata() != null && !this.getOtherMetadata().isEmpty());
      boolean isMetadata = ((this.getDescriptiveMetadata() != null && !this.getDescriptiveMetadata().isEmpty())
        || (this.getPreservationMetadata() != null && !this.getPreservationMetadata().isEmpty()));
      boolean isDocumentation = (this.getDocumentation() != null && !this.getDocumentation().isEmpty());
      boolean isSchemas = (this.getSchemas() != null && !this.getSchemas().isEmpty());
      boolean isRepresentations = (this.getRepresentations() != null && !this.getRepresentations().isEmpty());

      MetsWrapper mainMETSWrapper = metsCreator.generateMETS(StringUtils.join(this.getIds(), " "),
        this.getDescription(), this.getProfile(), true, Optional.ofNullable(this.getAncestors()), null,
        this.getHeader(), this.getType(), this.getContentType(), this.getContentInformationType(), isMetadata,
        isMetadataOther, isSchemas, isDocumentation, false, isRepresentations, false);

      earkUtils.addDescriptiveMetadataToZipAndMETS(zipEntries, mainMETSWrapper, getDescriptiveMetadata(), null);
      earkUtils.addPreservationMetadataToZipAndMETS(zipEntries, mainMETSWrapper, getPreservationMetadata(), null);
      earkUtils.addOtherMetadataToZipAndMETS(zipEntries, mainMETSWrapper, getOtherMetadata(), null);
      earkUtils.addRepresentationsToZipAndMETS(this, getRepresentations(), zipEntries, mainMETSWrapper, buildDir,
        sipType);
      earkUtils.addSchemasToZipAndMETS(zipEntries, mainMETSWrapper, getSchemas(), null);
      earkUtils.addDocumentationToZipAndMETS(zipEntries, mainMETSWrapper, getDocumentation(), null);
      METSUtils.addMainMETSToZip(zipEntries, mainMETSWrapper, buildDir);

      notifySipBuildPackagingStarted(zipEntries.size());
      return writeStrategy.write(zipEntries, this, fileNameWithoutExtension, getId(), true);
    } catch (InterruptedException e) {
      ModelUtils.cleanUpUponInterrupt(LOGGER, writeStrategy.getDestinationPath());
      throw e;
    } finally {
      ModelUtils.deleteBuildDir(buildDir);
      notifySipBuildPackagingEnded();
    }
  }

  @Override
  public Set<String> getExtraChecksumAlgorithms() {
    return Collections.emptySet();
  }

}
