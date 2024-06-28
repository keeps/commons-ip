/**
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE file at the root of the source
 * tree and available online at
 *
 * https://github.com/keeps/commons-ip
 */
package org.roda_project.commons_ip2.model.impl.eark;

import java.io.IOException;
import java.io.InputStream;
import java.nio.channels.ClosedByInterruptException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.io.FileOutputStream;

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
import org.roda_project.commons_ip2.utils.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.roda_project.commons_ip2.utils.ZIPUtils.writeToOutputAndCalculateChecksum;

public class EARKSIP extends SIP {
  private static final Logger LOGGER = LoggerFactory.getLogger(EARKSIP.class);

  private static final String SIP_TEMP_DIR = "EARKSIP";
  private static final String SIP_FILE_EXTENSION = ".zip";

  private static final String DEFAULT_SIP_VERSION = "2.1.0";

  private final EARKMETSCreator metsCreator;

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
    setProfile(IPConstants.SIP_SPEC_PROFILE);

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

  public SIP parseUnzipped(Path source) throws ParseException {
    return parseAndValidateEARKSIP(source);
  }

  private SIP parseEARKSIP(final Path source, final Path destinationDirectory) throws ParseException {
    Path sipPath = ZIPUtils.extractIPIfInZipFormat(source, destinationDirectory);
    return parseAndValidateEARKSIP(sipPath);
  }

  private SIP parseAndValidateEARKSIP(Path sipPath) throws ParseException {
    try {
      IPConstants.METS_ENCODE_AND_DECODE_HREF = true;
      SIP sip = new EARKSIP();

      EARKUtils earkUtils = new EARKUtils(metsCreator);

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
  public Path build(Path destinationDirectory) throws IPException, InterruptedException, IOException {
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
    throws IPException, InterruptedException, IOException {
    return build(destinationDirectory, null);
  }

  @Override
  public Path build(Path destinationDirectory, String fileNameWithoutExtension)
    throws IPException, InterruptedException, IOException {
    return build(destinationDirectory, fileNameWithoutExtension, false, IPEnums.SipType.EARK2);
  }

  @Override
  public Path build(Path destinationDirectory, String fileNameWithoutExtension, IPEnums.SipType sipType)
    throws IPException, InterruptedException, IOException {
    return build(destinationDirectory, fileNameWithoutExtension, false, sipType);
  }

  @Override
  public Path build(Path destinationDirectory, String fileNameWithoutExtension, boolean onlyManifest)
    throws IPException, InterruptedException, IOException {
    return build(destinationDirectory, fileNameWithoutExtension, false, IPEnums.SipType.EARK2);
  }

  private boolean hasOtherMetadata() {
    return this.getOtherMetadata() != null && !this.getOtherMetadata().isEmpty();
  }

  private boolean hasMetadata() {
    return (this.getDescriptiveMetadata() != null && !this.getDescriptiveMetadata().isEmpty())
            || (this.getPreservationMetadata() != null && !this.getPreservationMetadata().isEmpty());
  }

  private boolean hasDocumentation() {
    return this.getDocumentation() != null && !this.getDocumentation().isEmpty();
  }

  private boolean hasSchemas() {
    return this.getSchemas() != null && !this.getSchemas().isEmpty();
  }

  private boolean hasRepresentations() {
    return this.getRepresentations() != null && !this.getRepresentations().isEmpty();
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
                    IPEnums.SipType sipType) throws IPException, InterruptedException, IOException {
    IPConstants.METS_ENCODE_AND_DECODE_HREF = true;
    Path buildDir = ModelUtils.createBuildDir(SIP_TEMP_DIR);

    EARKUtils earkUtils = new EARKUtils(metsCreator);

    Path outputPath = getShouldOutputInZip() ? getZipPath(destinationDirectory, fileNameWithoutExtension) : destinationDirectory;

    try {
      Map<String, ZipEntryInfo> zipEntries = getZipEntries();
      //default metadata need to be added before creating the mets in order to add them in the mets file
      earkUtils.addDefaultSchemas(LOGGER, getSchemas(), buildDir, getOverride());

      MetsWrapper mainMETSWrapper = metsCreator.generateMETS(
              StringUtils.join(getIds(), " "),
              getDescription(),
              getProfile(),
              true,
              Optional.ofNullable(getAncestors()),
              null,
              getHeader(),
              getType(),
              getContentType(),
              getContentInformationType(),
              hasMetadata(),
              hasOtherMetadata(),
              hasSchemas(),
              hasDocumentation(),
              false,
              hasRepresentations(),
              false
      );

      earkUtils.addDescriptiveMetadataToZipAndMETS(zipEntries, mainMETSWrapper, getDescriptiveMetadata(), null);
      earkUtils.addPreservationMetadataToZipAndMETS(zipEntries, mainMETSWrapper, getPreservationMetadata(), null);
      earkUtils.addOtherMetadataToZipAndMETS(zipEntries, mainMETSWrapper, getOtherMetadata(), null);
      earkUtils.addRepresentationsToZipAndMETS(this, getRepresentations(), zipEntries, mainMETSWrapper, buildDir,
        sipType);
      earkUtils.addSchemasToZipAndMETS(zipEntries, mainMETSWrapper, getSchemas(), null);
      earkUtils.addDocumentationToZipAndMETS(zipEntries, mainMETSWrapper, getDocumentation(), null);
      METSUtils.addMainMETSToZip(zipEntries, mainMETSWrapper, buildDir);

      if(getShouldOutputInZip()){
        finalizeAndZip(zipEntries, outputPath);
      }
      else{
        finalize(zipEntries, outputPath, true);
        outputPath = outputPath.resolve(this.getId());
      }

      return outputPath;
    } catch (InterruptedException e) {
      ModelUtils.cleanUpUponInterrupt(LOGGER, outputPath);
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
      zipPath = destinationDirectory.resolve(getId() + SIP_FILE_EXTENSION);
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

  private void finalizeAndZip(Map<String, ZipEntryInfo> zipEntries, Path zipPath)
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

  public void finalize(Map<String, ZipEntryInfo> files, Path outputPath, boolean createSipIdFolder)
          throws IOException, InterruptedException, IPException {
    if (Files.exists(outputPath)) {
      Files.deleteIfExists(outputPath);
    }
    Files.createDirectories(outputPath);

    Set<String> nonMetsChecksumAlgorithms = new TreeSet<>();
    nonMetsChecksumAlgorithms.add(getChecksumAlgorithm());

    Set<String> metsChecksumAlgorithms = new TreeSet<>();
    metsChecksumAlgorithms.addAll(nonMetsChecksumAlgorithms);
    metsChecksumAlgorithms.addAll(getExtraChecksumAlgorithms());

    int i = 0;
    for (ZipEntryInfo file : files.values()) {
      if (Thread.interrupted()) {
        throw new InterruptedException();
      }

      // When HasPregenerated checksum, some files will have algorithm set and some won't, if missing, we do SIP default checksum
      if(!getHasPregeneratedChecksums() || file.getChecksumAlgorithm() == null || file.getChecksumAlgorithm().isEmpty()){
        file.setChecksumAlgorithm(getChecksumAlgorithm());
      }
      file.prepareEntryforZipping();

      LOGGER.debug("Creating file {}", file.getFilePath());
      Path outputFilePath = outputPath.resolve(createSipIdFolder? getId() + "/" + file.getName() : file.getName());
      Files.createDirectories(outputFilePath.getParent());
      try (InputStream inputStream = Files.newInputStream(file.getFilePath())) {
        Map<String, String> checksums = new HashMap<>();

        if (file instanceof METSZipEntryInfo metsEntry) {
          if(getHasPregeneratedChecksums() && file.getChecksum() != null && !file.getChecksum().isEmpty()){
            LOGGER.info(file.getFilePath() + " already has checksum, skipping checksum calculation");
            Files.copy(file.getFilePath(), outputFilePath);
            checksums.put(file.getChecksumAlgorithm(), file.getChecksum());
          }
          else{
            checksums = writeToOutputAndCalculateChecksum(Optional.of(new FileOutputStream(outputFilePath.toFile())), inputStream, metsChecksumAlgorithms);
          }

            metsEntry.setChecksums(checksums);
          metsEntry.setSize(metsEntry.getFilePath().toFile().length());
        } else if(getHasPregeneratedChecksums() && file.getChecksum() != null && !file.getChecksum().isEmpty()) {
          LOGGER.info(file.getFilePath() + " already has checksum, skipping checksum calculation");
          Files.copy(file.getFilePath(), outputFilePath);
          checksums.put(file.getChecksumAlgorithm(), file.getChecksum());
        }
        else{
          checksums = writeToOutputAndCalculateChecksum(Optional.of(new FileOutputStream(outputFilePath.toFile())), inputStream, nonMetsChecksumAlgorithms);
        }

        LOGGER.debug("Done creating file");
        String checksum = checksums.get(getChecksumAlgorithm());
        String checksumType = getChecksumAlgorithm();
        file.setChecksum(checksum);
        file.setChecksumAlgorithm(checksumType);
        if (file instanceof METSFileTypeZipEntryInfo metsFileTypeZipEntryInfo) {
            metsFileTypeZipEntryInfo.getMetsFileType().setCHECKSUM(checksum);
          metsFileTypeZipEntryInfo.getMetsFileType().setCHECKSUMTYPE(checksumType);
        } else if (file instanceof METSMdRefZipEntryInfo metsMdRefZipEntryInfo) {
            metsMdRefZipEntryInfo.getMetsMdRef().setCHECKSUM(checksum);
          metsMdRefZipEntryInfo.getMetsMdRef().setCHECKSUMTYPE(checksumType);
        }
      } catch (NoSuchAlgorithmException e) {
        LOGGER.error("Error while creating files", e);
      }
      i++;
      notifySipBuildPackagingCurrentStatus(i);
    }
  }

  @Override
  public Set<String> getExtraChecksumAlgorithms() {
    return Collections.emptySet();
  }

}
