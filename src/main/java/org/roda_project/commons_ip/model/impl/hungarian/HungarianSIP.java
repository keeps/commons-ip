/**
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE file at the root of the source
 * tree and available online at
 *
 * https://github.com/keeps/commons-ip
 */
package org.roda_project.commons_ip.model.impl.hungarian;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.channels.ClosedByInterruptException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.NoSuchAlgorithmException;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.roda_project.commons_ip.model.IPConstants;
import org.roda_project.commons_ip.model.IPContentType;
import org.roda_project.commons_ip.model.MetsWrapper;
import org.roda_project.commons_ip.model.SIP;
import org.roda_project.commons_ip.model.impl.ModelUtils;
import org.roda_project.commons_ip.utils.IPException;
import org.roda_project.commons_ip.utils.METSZipEntryInfo;
import org.roda_project.commons_ip.utils.ZIPUtils;
import org.roda_project.commons_ip.utils.ZipEntryInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HungarianSIP extends SIP {
  private static final Logger LOGGER = LoggerFactory.getLogger(HungarianSIP.class);

  private static final String SIP_TEMP_DIR = "HungarianSIP";
  private static final String SIP_FILE_EXTENSION = ".zip";
  private static final String TXT_FILE_EXTENSION = ".txt";

  private String folderTemplate = null;

  public HungarianSIP() {
    super();
  }

  /**
   * @param sipId
   *          will be used as OBJID in METS (/mets[@OBJID])
   */
  public HungarianSIP(String sipId, IPContentType contentType) {
    super(sipId, contentType);
  }

  public String getFolderTemplate() {
    return folderTemplate;
  }

  public void setFolderTemplate(String folderTemplate) {
    this.folderTemplate = folderTemplate;
  }

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
    return build(destinationDirectory, fileNameWithoutExtension, false);
  }

  /**
   *
   * build and all build related methods
   * _________________________________________________________________________
   */
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
  public Path build(final Path destinationDirectory, final String fileNameWithoutExtension, final boolean onlyManifest)
    throws IPException, InterruptedException {
    IPConstants.METS_ENCODE_AND_DECODE_HREF = false;
    Path buildDir = ModelUtils.createBuildDir(SIP_TEMP_DIR);
    Path zipPath = getZipPath(destinationDirectory, fileNameWithoutExtension);
    try {
      Map<String, ZipEntryInfo> zipEntries = getZipEntries();

      MetsWrapper mainMETSWrapper = HungarianMETSUtils.generateMETS(StringUtils.join(this.getIds(), " "),
        this.getDescription(), this.getType(), this.getProfile(), null, this.getHeader());

      HungarianUtils.addMetadataToMETS(mainMETSWrapper, getDescriptiveMetadata());
      HungarianUtils.addRepresentationsToZipAndMETS(this, getRepresentations(), zipEntries, mainMETSWrapper,
        folderTemplate);
      HungarianUtils.addDocumentationToZipAndMETS(zipEntries, mainMETSWrapper, getDocumentation());
      HungarianUtils.addMainMETSToZip(zipEntries, mainMETSWrapper, buildDir);

      createZipFile(zipEntries, zipPath);
      createTxtFile(destinationDirectory, fileNameWithoutExtension, zipEntries, zipPath);

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
      zipPath = destinationDirectory.resolve(getId() + SIP_FILE_EXTENSION);
    }

    try {
      if (Files.exists(zipPath)) {
        Files.delete(zipPath);
      }
    } catch (IOException e) {
      throw new IPException("Error deleting already existing ZIP", e);
    }

    return zipPath;
  }

  private void createZipFile(Map<String, ZipEntryInfo> zipEntries, Path zipPath)
    throws IPException, InterruptedException {
    try {
      notifySipBuildPackagingStarted(zipEntries.size());
      ZIPUtils.zip(zipEntries, Files.newOutputStream(zipPath), this, false, false);
    } catch (ClosedByInterruptException e) {
      throw new InterruptedException();
    } catch (IOException e) {
      throw new IPException("Error generating Hungarian SIP ZIP file. Reason: " + e.getMessage(), e);
    } finally {
      notifySipBuildPackagingEnded();
    }
  }

  private void createTxtFile(Path destinationDirectory, String fileNameWithoutExtension,
    Map<String, ZipEntryInfo> zipEntries, Path zipPath) {
    Path txtPath;
    if (fileNameWithoutExtension != null) {
      txtPath = destinationDirectory.resolve(fileNameWithoutExtension + TXT_FILE_EXTENSION);
    } else {
      txtPath = destinationDirectory.resolve(getId() + TXT_FILE_EXTENSION);
    }

    try (PrintWriter writer = new PrintWriter(txtPath.toFile())) {
      ZipEntryInfo zipEntryInfo = zipEntries.get(IPConstants.HUNGARIAN_METADATA_FILE);
      if (zipEntryInfo != null && zipEntryInfo instanceof METSZipEntryInfo metsEntry) {
          Map<String, String> checksums = metsEntry.getChecksums();
        long size = metsEntry.getSize();
        final String separator = ": ";

        writer.println(IPConstants.METADATA_FILE + separator + size);
        writer
          .println(IPConstants.CHECKSUM_MD5_ALGORITHM + separator + checksums.get(IPConstants.CHECKSUM_MD5_ALGORITHM));
        writer.println(
          IPConstants.CHECKSUM_SHA_1_ALGORITHM + separator + checksums.get(IPConstants.CHECKSUM_SHA_1_ALGORITHM));

        try (FileInputStream zipStream = new FileInputStream(zipPath.toFile())) {
          Set<String> zipChecksums = new HashSet<>();
          zipChecksums.add(IPConstants.CHECKSUM_MD5_ALGORITHM);
          zipChecksums.add(IPConstants.CHECKSUM_SHA_1_ALGORITHM);
          Map<String, String> calculateChecksums = ZIPUtils.calculateChecksums(Optional.empty(), zipStream,
            zipChecksums);

          writer.println(zipPath.getFileName().toString() + separator + zipPath.toFile().length());
          writer.println(IPConstants.CHECKSUM_MD5_ALGORITHM + separator
            + calculateChecksums.get(IPConstants.CHECKSUM_MD5_ALGORITHM));
          writer.println(IPConstants.CHECKSUM_SHA_1_ALGORITHM + separator
            + calculateChecksums.get(IPConstants.CHECKSUM_SHA_1_ALGORITHM));
        } catch (NoSuchAlgorithmException e) {
          LOGGER.error("Wrong checksum name used", e);
        } catch (IOException e) {
          LOGGER.error("Could not open zip file", e);
        }

      }
    } catch (FileNotFoundException e) {
      LOGGER.error("Could not write SIP information to txt file", e);
    }
  }

  @Override
  public Set<String> getExtraChecksumAlgorithms() {
    Set<String> algorithms = new HashSet<>();
    algorithms.add(IPConstants.CHECKSUM_MD5_ALGORITHM);
    algorithms.add(IPConstants.CHECKSUM_SHA_1_ALGORITHM);
    return algorithms;
  }

}
