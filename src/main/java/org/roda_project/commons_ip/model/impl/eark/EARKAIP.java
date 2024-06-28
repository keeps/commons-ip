/**
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE file at the root of the source
 * tree and available online at
 *
 * https://github.com/keeps/commons-ip
 */
package org.roda_project.commons_ip.model.impl.eark;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.Optional;

import jakarta.xml.bind.DatatypeConverter;

import org.apache.commons.io.IOUtils;
import org.apache.commons.io.output.NullOutputStream;
import org.apache.commons.lang3.StringUtils;
import org.roda_project.commons_ip.mets_v1_11.beans.StructMapType;
import org.roda_project.commons_ip.model.AIP;
import org.roda_project.commons_ip.model.IPConstants;
import org.roda_project.commons_ip.model.MetsWrapper;
import org.roda_project.commons_ip.model.ParseException;
import org.roda_project.commons_ip.model.impl.AIPWrap;
import org.roda_project.commons_ip.model.impl.BasicAIP;
import org.roda_project.commons_ip.model.impl.ModelUtils;
import org.roda_project.commons_ip.utils.IPException;
import org.roda_project.commons_ip.utils.METSFileTypeZipEntryInfo;
import org.roda_project.commons_ip.utils.METSMdRefZipEntryInfo;
import org.roda_project.commons_ip.utils.METSUtils;
import org.roda_project.commons_ip.utils.METSZipEntryInfo;
import org.roda_project.commons_ip.utils.ZIPUtils;
import org.roda_project.commons_ip.utils.ZipEntryInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * EARK AIP. This implementation of {@link AIP} can read/write AIPs from/to a
 * folder.
 * 
 * @author Rui Castro (rui.castro@gmail.com)
 */
public class EARKAIP extends AIPWrap {
  private static final Logger LOGGER = LoggerFactory.getLogger(EARKAIP.class);
  private static final String TEMP_DIR = "EARKAIP";

  /**
   * Constructor.
   *
   * @param aip
   *          the {@link AIP} to warp.
   */
  public EARKAIP(final AIP aip) {
    super(aip);
  }

  public static AIP parse(final Path source) throws ParseException {
    try {
      if (Files.isDirectory(source)) {
        return parseEARKAIPFromPath(source);
      } else {
        return parse(source, Files.createTempDirectory("unzipped"));
      }
    } catch (final IOException e) {
      throw new ParseException("Error creating temporary directory for E-ARK AIP parse", e);
    }
  }

  public static AIP parse(Path source, Path destinationDirectory) throws ParseException {
    return parseEARKAIP(source, destinationDirectory);
  }

  private static AIP parseEARKAIP(final Path source, final Path destinationDirectory) throws ParseException {
    Path aipPath = ZIPUtils.extractIPIfInZipFormat(source, destinationDirectory);
    return parseEARKAIPFromPath(aipPath);
  }

  private static AIP parseEARKAIPFromPath(final Path aipPath) throws ParseException {
    try {
      final AIP aip = new EARKAIP(new BasicAIP());
      aip.setBasePath(aipPath);
      final MetsWrapper metsWrapper = EARKUtils.processMainMets(aip, aipPath);

      if (aip.isValid()) {

        final StructMapType structMap = EARKUtils.getEARKStructMap(metsWrapper, aip, true);

        if (structMap != null) {
          EARKUtils.preProcessStructMap(metsWrapper, structMap);

          EARKUtils.processDescriptiveMetadata(metsWrapper, aip, LOGGER, null, aip.getBasePath());

          EARKUtils.processOtherMetadata(metsWrapper, aip, LOGGER, null, aip.getBasePath());

          EARKUtils.processPreservationMetadata(metsWrapper, aip, LOGGER, null, aip.getBasePath());

          EARKUtils.processRepresentations(metsWrapper, aip, LOGGER);

          EARKUtils.processSchemasMetadata(metsWrapper, aip, aip.getBasePath());

          EARKUtils.processDocumentationMetadata(metsWrapper, aip, aip.getBasePath());

          EARKUtils.processSubmissionMetadata(metsWrapper, aip, aip.getBasePath());

          EARKUtils.processAncestors(metsWrapper, aip);
        }
      }

      return aip;
    } catch (final IPException e) {
      throw new ParseException("Error parsing E-ARK AIP", e);
    }
  }

  @Override
  public Path build(final Path destinationDirectory) throws IPException, InterruptedException {
    return build(destinationDirectory, null);
  }

  @Override
  public Path build(final Path destinationDirectory, final boolean onlyManifest)
    throws IPException, InterruptedException {
    return build(destinationDirectory, null, onlyManifest);
  }

  @Override
  public Path build(final Path destinationDirectory, final String fileNameWithoutExtension)
    throws IPException, InterruptedException {
    return build(destinationDirectory, fileNameWithoutExtension, false);
  }

  @Override
  public Path build(final Path destinationDirectory, final String fileNameWithoutExtension, final boolean onlyManifest)
    throws IPException, InterruptedException {
    final Path buildDir = ModelUtils.createBuildDir(TEMP_DIR);
    Path zipPath = null;
    try {
      final Map<String, ZipEntryInfo> zipEntries = getZipEntries();
      zipPath = getDirPath(destinationDirectory, fileNameWithoutExtension, false);

      // 20160407 hsilva: as METS does not have an attribute 'otherType', the
      // other type must be put in the 'type' attribute allowing this way other
      // values besides the ones in the Enum
      final String contentType = this.getContentType().asString();

      final MetsWrapper mainMETSWrapper = EARKMETSUtils.generateMETS(StringUtils.join(this.getIds(), " "),
        this.getDescription(), this.getType() + ":" + contentType, this.getProfile(), true,
        Optional.ofNullable(this.getAncestors()), null, this.getHeader());

      EARKUtils.addDescriptiveMetadataToZipAndMETS(zipEntries, mainMETSWrapper, getDescriptiveMetadata(), null);

      EARKUtils.addPreservationMetadataToZipAndMETS(zipEntries, mainMETSWrapper, getPreservationMetadata(), null);

      EARKUtils.addOtherMetadataToZipAndMETS(zipEntries, mainMETSWrapper, getOtherMetadata(), null);

      EARKUtils.addRepresentationsToZipAndMETS(this, getRepresentations(), zipEntries, mainMETSWrapper, buildDir);

      EARKUtils.addDefaultSchemas(LOGGER, getSchemas(), buildDir);

      EARKUtils.addSchemasToZipAndMETS(zipEntries, mainMETSWrapper, getSchemas(), null);

      EARKUtils.addDocumentationToZipAndMETS(zipEntries, mainMETSWrapper, getDocumentation(), null);

      EARKUtils.addSubmissionsToZipAndMETS(zipEntries, mainMETSWrapper, getSubmissions());

      METSUtils.addMainMETSToZip(zipEntries, mainMETSWrapper, buildDir);

      writeToPath(zipEntries, zipPath, onlyManifest);

      return zipPath;
    } catch (final InterruptedException e) {
      ModelUtils.cleanUpUponInterrupt(LOGGER, zipPath);
      throw e;
    } finally {
      ModelUtils.deleteBuildDir(buildDir);
    }
  }

  private Path getDirPath(final Path targetPath, final String name, final boolean deleteExisting) throws IPException {
    final Path path;
    if (name != null) {
      path = targetPath.resolve(name);
    } else {
      path = targetPath.resolve(getId());
    }

    try {
      if (deleteExisting && Files.exists(path)) {
        Files.delete(path);
      }
    } catch (final IOException e) {
      throw new IPException("Error deleting existing path - " + e.getMessage(), e);
    }
    return path;
  }

  private void writeToPath(final Map<String, ZipEntryInfo> zipEntryInfos, final Path path, final boolean onlyMets)
    throws IPException, InterruptedException {
    try {
      Files.createDirectories(path);
      for (ZipEntryInfo zipEntryInfo : zipEntryInfos.values()) {
        if (Thread.interrupted()) {
          throw new InterruptedException();
        }

        zipEntryInfo.prepareEntryforZipping();
        LOGGER.debug("Writing file {}", zipEntryInfo.getFilePath());
        final Path outputPath = Paths.get(path.toString(), zipEntryInfo.getName());
        writeFileToPath(zipEntryInfo, outputPath, onlyMets);
      }
    } catch (final IOException | NoSuchAlgorithmException e) {
      LOGGER.debug("Error in write method", e);
      throw new IPException(e.getMessage(), e);
    }
  }

  private void writeFileToPath(final ZipEntryInfo zipEntryInfo, final Path outputPath, final boolean onlyMets)
    throws IOException, NoSuchAlgorithmException {
    InputStream is = null;
    OutputStream os = null;
    try {

      is = Files.newInputStream(zipEntryInfo.getFilePath());

      if (!onlyMets || zipEntryInfo instanceof METSZipEntryInfo) {
        Files.createDirectories(outputPath.getParent());
        os = Files.newOutputStream(outputPath);
      } else {
        os = new NullOutputStream();
      }

      final byte[] buffer = new byte[4096];
      final MessageDigest complete = MessageDigest.getInstance(IPConstants.CHECKSUM_ALGORITHM);
      int numRead;
      do {
        numRead = is.read(buffer);
        if (numRead > 0) {
          complete.update(buffer, 0, numRead);
          if (!onlyMets || zipEntryInfo instanceof METSZipEntryInfo) {
            os.write(buffer, 0, numRead);
          }
        }
      } while (numRead != -1);

      setChecksum(zipEntryInfo, DatatypeConverter.printHexBinary(complete.digest()), IPConstants.CHECKSUM_ALGORITHM);
    } finally {
      IOUtils.closeQuietly(is);
      IOUtils.closeQuietly(os);
    }
  }

  private void setChecksum(final ZipEntryInfo zipEntryInfo, final String checksum, final String checksumType)
    throws IOException, NoSuchAlgorithmException {
    zipEntryInfo.setChecksum(checksum);
    zipEntryInfo.setChecksumAlgorithm(checksumType);
    if (zipEntryInfo instanceof METSFileTypeZipEntryInfo f) {
        f.getMetsFileType().setCHECKSUM(checksum);
      f.getMetsFileType().setCHECKSUMTYPE(checksumType);
    } else if (zipEntryInfo instanceof METSMdRefZipEntryInfo f) {
        f.getMetsMdRef().setCHECKSUM(checksum);
      f.getMetsMdRef().setCHECKSUMTYPE(checksumType);
    }
  }

}
