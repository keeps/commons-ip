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
import org.roda_project.commons_ip2.utils.METSUtils;
import org.roda_project.commons_ip2.utils.ZIPUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EARKSIP extends SIP {
  private static final Logger LOGGER = LoggerFactory.getLogger(EARKSIP.class);

  private static final String SIP_TEMP_DIR = "EARKSIP";
  private static final String SIP_FILE_EXTENSION = ".zip";

  public EARKSIP() {
    super();
    setProfile(IPConstants.SIP_SPEC_PROFILE);
  }

  /**
   * @param sipId
   *          will be used as OBJID in METS (/mets[@OBJID])
   */
  public EARKSIP(String sipId) {
    super(sipId);
    setProfile(IPConstants.SIP_SPEC_PROFILE);
  }

  /**
   * @param sipId
   *          will be used as OBJID in METS (/mets[@OBJID])
   */
  public EARKSIP(String sipId, IPContentType contentType, IPContentInformationType contentInformationType) {
    super(sipId, contentType, contentInformationType);
    setProfile(IPConstants.SIP_SPEC_PROFILE);
  }

  /**
   *
   * parse and all parse related methods; during parse, validation is also
   * conducted and stored inside the SIP
   * _________________________________________________________________________
   */

  public static SIP parse(Path source, Path destinationDirectory) throws ParseException {
    return parseEARKSIP(source, destinationDirectory);
  }

  public static SIP parse(Path source) throws ParseException {
    try {
      return parse(source, Files.createTempDirectory("unzipped"));
    } catch (IOException e) {
      throw new ParseException("Error creating temporary directory for E-ARK SIP parse", e);
    }
  }

  private static SIP parseEARKSIP(final Path source, final Path destinationDirectory) throws ParseException {
    try {
      IPConstants.METS_ENCODE_AND_DECODE_HREF = true;
      SIP sip = new EARKSIP();

      Path sipPath = ZIPUtils.extractIPIfInZipFormat(source, destinationDirectory);
      sip.setBasePath(sipPath);

      MetsWrapper metsWrapper = EARKUtils.processMainMets(sip, sipPath);

      if (sip.isValid()) {

        StructMapType structMap = EARKUtils.getEARKStructMap(metsWrapper, sip, true);

        if (structMap != null) {
          EARKUtils.preProcessStructMap(metsWrapper, structMap);
          EARKUtils.processDescriptiveMetadata(metsWrapper, sip, LOGGER, null, sip.getBasePath());
          EARKUtils.processOtherMetadata(metsWrapper, sip, LOGGER, null, sip.getBasePath());
          EARKUtils.processPreservationMetadata(metsWrapper, sip, LOGGER, null, sip.getBasePath());
          EARKUtils.processRepresentations(metsWrapper, sip, LOGGER);
          EARKUtils.processSchemasMetadata(metsWrapper, sip, sip.getBasePath());
          EARKUtils.processDocumentationMetadata(metsWrapper, sip, sip.getBasePath());
          EARKUtils.processAncestors(metsWrapper, sip);
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

  /**
   *
   * build and all build related methods
   * _________________________________________________________________________
   */
  @Override
  public Path build(Path destinationDirectory) throws IPException, InterruptedException {
    return build(destinationDirectory, null);
  }

  /**
   * Builds a SIP.
   *
   * @param destinationDirectory
   *          the {@link Path} where the SIP should be build.
   * @param onlyManifest
   *          build only the manifest file? (<strong>this parameter is
   *          ignored</strong>).
   * @return the {@link Path}.
   * @throws IPException
   *           if some error occurs.
   * @throws InterruptedException
   *           if some error occurs.
   */
  @Override
  public Path build(final Path destinationDirectory, final boolean onlyManifest)
    throws IPException, InterruptedException {
    return build(destinationDirectory, null);
  }

  @Override
  public Path build(Path destinationDirectory, String fileNameWithoutExtension)
    throws IPException, InterruptedException {
    return build(destinationDirectory, fileNameWithoutExtension, false, IPEnums.SipType.EARK2);
  }

  @Override
  public Path build(Path destinationDirectory, String fileNameWithoutExtension, IPEnums.SipType sipType)
    throws IPException, InterruptedException {
    return build(destinationDirectory, fileNameWithoutExtension, false, sipType);
  }

  @Override
  public Path build(Path destinationDirectory, String fileNameWithoutExtension, boolean onlyManifest)
    throws IPException, InterruptedException {
    return build(destinationDirectory, fileNameWithoutExtension, false, IPEnums.SipType.EARK2);
  }

  /**
   * Builds a SIP.
   *
   * @param destinationDirectory
   *          the {@link Path} where the SIP should be build.
   * @param fileNameWithoutExtension
   *          the name of the output file without extension.
   * @param onlyManifest
   *          build only the manifest file? (<strong>this parameter is
   *          ignored</strong>).
   * @return the {@link Path}.
   * @throws IPException
   *           if some error occurs.
   * @throws InterruptedException
   *           if some error occurs.
   */
  @Override
  public Path build(final Path destinationDirectory, final String fileNameWithoutExtension, final boolean onlyManifest,
    IPEnums.SipType sipType) throws IPException, InterruptedException {
    IPConstants.METS_ENCODE_AND_DECODE_HREF = true;
    Path buildDir = ModelUtils.createBuildDir(SIP_TEMP_DIR);
    Path zipPath = getZipPath(destinationDirectory, fileNameWithoutExtension);
    try {
      Map<String, ZipEntryInfo> zipEntries = getZipEntries();
      //default metadata need to be added before creating the mets in order to add them in the mets file
      EARKUtils.addDefaultSchemas(LOGGER, getSchemas(), buildDir);

      boolean isMetadataOther = (this.getOtherMetadata() != null && !this.getOtherMetadata().isEmpty());
      boolean isMetadata = ((this.getDescriptiveMetadata() != null && !this.getDescriptiveMetadata().isEmpty())
        || (this.getPreservationMetadata() != null && !this.getPreservationMetadata().isEmpty()));
      boolean isDocumentation = (this.getDocumentation() != null && !this.getDocumentation().isEmpty());
      boolean isSchemas = (this.getSchemas() != null && !this.getSchemas().isEmpty());
      boolean isRepresentations = (this.getRepresentations() != null && !this.getRepresentations().isEmpty());
      MetsWrapper mainMETSWrapper = EARKMETSUtils.generateMETS(StringUtils.join(this.getIds(), " "),
        this.getDescription(), this.getProfile(), true, Optional.ofNullable(this.getAncestors()), null,
        this.getHeader(), this.getType(), this.getContentType(), this.getContentInformationType(), isMetadata,
        isMetadataOther, isSchemas, isDocumentation, false, isRepresentations, false);

      EARKUtils.addDescriptiveMetadataToZipAndMETS(zipEntries, mainMETSWrapper, getDescriptiveMetadata(), null);
      EARKUtils.addPreservationMetadataToZipAndMETS(zipEntries, mainMETSWrapper, getPreservationMetadata(), null);
      EARKUtils.addOtherMetadataToZipAndMETS(zipEntries, mainMETSWrapper, getOtherMetadata(), null);
      EARKUtils.addRepresentationsToZipAndMETS(this, getRepresentations(), zipEntries, mainMETSWrapper, buildDir,
        sipType);
      EARKUtils.addSchemasToZipAndMETS(zipEntries, mainMETSWrapper, getSchemas(), null);
      EARKUtils.addDocumentationToZipAndMETS(zipEntries, mainMETSWrapper, getDocumentation(), null);
      METSUtils.addMainMETSToZip(zipEntries, mainMETSWrapper, buildDir);

      createZipFile(zipEntries, zipPath);
      return zipPath;
    } catch (InterruptedException e) {
      ModelUtils.cleanUpUponInterrupt(LOGGER, zipPath);
      throw e;
    } finally {
      ModelUtils.deleteBuildDir(buildDir);
    }
  }

  private Path getZipPath(Path destinationDirectory, String fileNameWithoutExtension) throws IPException {
    Path zipPath;
    if (fileNameWithoutExtension != null) {
      zipPath = destinationDirectory.resolve(fileNameWithoutExtension + SIP_FILE_EXTENSION);
    } else {
      zipPath = destinationDirectory.resolve(getId().replaceAll("[^a-zA-Z0-9-_\\.]", "_") + SIP_FILE_EXTENSION);
    }

    try {
      if (Files.exists(zipPath)) {
        Files.delete(zipPath);
      }
    } catch (IOException e) {
      throw new IPException("Error deleting already existing zip", e);
    }
    return zipPath;
  }

  private void createZipFile(Map<String, ZipEntryInfo> zipEntries, Path zipPath)
    throws IPException, InterruptedException {
    try {
      notifySipBuildPackagingStarted(zipEntries.size());
      ZIPUtils.zip(zipEntries, Files.newOutputStream(zipPath), this, true, true);
    } catch (ClosedByInterruptException e) {
      throw new InterruptedException();
    } catch (IOException e) {
      throw new IPException("Error generating E-ARK SIP ZIP file. Reason: " + e.getMessage(), e);
    } finally {
      notifySipBuildPackagingEnded();
    }
  }

  @Override
  public Set<String> getExtraChecksumAlgorithms() {
    return Collections.emptySet();
  }

}
