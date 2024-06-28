package org.roda_project.commons_ip2.model.impl.eark;

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
import org.roda_project.commons_ip.model.ParseException;
import org.roda_project.commons_ip.utils.IPEnums;
import org.roda_project.commons_ip.utils.IPException;
import org.roda_project.commons_ip.utils.ZipEntryInfo;
import org.roda_project.commons_ip2.mets_v1_12.beans.StructMapType;
import org.roda_project.commons_ip2.model.AIP;
import org.roda_project.commons_ip2.model.IPConstants;
import org.roda_project.commons_ip2.model.MetsWrapper;
import org.roda_project.commons_ip2.model.impl.AIPWrap;
import org.roda_project.commons_ip2.model.impl.BasicAIP;
import org.roda_project.commons_ip2.model.impl.ModelUtils;
import org.roda_project.commons_ip2.utils.METSFileTypeZipEntryInfo;
import org.roda_project.commons_ip2.utils.METSMdRefZipEntryInfo;
import org.roda_project.commons_ip2.utils.METSUtils;
import org.roda_project.commons_ip2.utils.METSZipEntryInfo;
import org.roda_project.commons_ip2.utils.ZIPUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author João Gomes <jgomes@keep.pt>
 */
public class EARKAIP extends AIPWrap {
  private static final Logger LOGGER = LoggerFactory.getLogger(EARKAIP.class);
  private static final String TEMP_DIR = "EARKAIP";

  private final EARKMETSCreator metsCreator;

  /**
   * Constructor.
   *
   * @param aip
   *          the {@link AIP} to warp.
   */
  public EARKAIP(final AIP aip, String version) {
    super(aip);
    METSGeneratorFactory factory = new METSGeneratorFactory();
    metsCreator = factory.getGenerator(version);
  }

  public AIP parse(final Path source, String version) throws ParseException {
    try {
      if (Files.isDirectory(source)) {
        return parseEARKAIPFromPath(source, version);
      } else {
        return parse(source, Files.createTempDirectory("unzipped"), version);
      }
    } catch (final IOException e) {
      throw new ParseException("Error creating temporary directory for E-ARK AIP parse", e);
    }
  }

  public AIP parse(Path source, Path destinationDirectory, String version) throws ParseException {
    return parseEARKAIP(source, destinationDirectory, version);
  }

  private AIP parseEARKAIP(final Path source, final Path destinationDirectory, String version) throws ParseException {
    Path aipPath = ZIPUtils.extractIPIfInZipFormat(source, destinationDirectory);
    return parseEARKAIPFromPath(aipPath, version);
  }

  private AIP parseEARKAIPFromPath(final Path aipPath, String version) throws ParseException {
    try {
      final AIP aip = new EARKAIP(new BasicAIP(), version);
      aip.setBasePath(aipPath);

      EARKUtils metsUtils = new EARKUtils(this.metsCreator);

      final MetsWrapper metsWrapper = metsUtils.processMainMets(aip, aipPath);

      if (aip.isValid()) {

        final StructMapType structMap = metsUtils.getEARKStructMap(metsWrapper, aip, true);

        if (structMap != null) {
          metsUtils.preProcessStructMap(metsWrapper, structMap);

          metsUtils.processDescriptiveMetadata(metsWrapper, aip, LOGGER, null, aip.getBasePath());

          metsUtils.processOtherMetadata(metsWrapper, aip, LOGGER, null, aip.getBasePath());

          metsUtils.processPreservationMetadata(metsWrapper, aip, LOGGER, null, aip.getBasePath());

          metsUtils.processRepresentations(metsWrapper, aip, LOGGER);

          metsUtils.processSchemasMetadata(metsWrapper, aip, aip.getBasePath());

          metsUtils.processDocumentationMetadata(metsWrapper, aip, aip.getBasePath());

          metsUtils.processSubmissionMetadata(metsWrapper, aip, aip.getBasePath());

          metsUtils.processAncestors(metsWrapper, aip);
        }
      }

      return aip;
    } catch (final IPException e) {
      LOGGER.debug("Error parsing E-ARK AIP", e);
      throw new ParseException("Error parsing E-ARK AIP", e);
    }
  }

  @Override
  public Path build(final Path destinationDirectory) throws IPException, InterruptedException {
    return this.build(destinationDirectory, null);
  }

  @Override
  public Path build(final Path destinationDirectory, final boolean onlyManifest)
    throws IPException, InterruptedException {
    return this.build(destinationDirectory, null, onlyManifest);
  }

  @Override
  public Path build(final Path destinationDirectory, final String fileNameWithoutExtension)
    throws IPException, InterruptedException {
    return this.build(destinationDirectory, fileNameWithoutExtension, false);
  }

  @Override
  public Path build(final Path destinationDirectory, final String fileNameWithoutExtension, final boolean onlyManifest)
    throws IPException, InterruptedException {
    final Path buildDir = ModelUtils.createBuildDir(TEMP_DIR);
    Path zipPath = null;

    EARKUtils utils = new EARKUtils(metsCreator);

    try {
      final Map<String, ZipEntryInfo> zipEntries = getZipEntries();
      zipPath = getDirPath(destinationDirectory, fileNameWithoutExtension, false);

      boolean isMetadataOther = (this.getOtherMetadata() != null && !this.getOtherMetadata().isEmpty());
      boolean isMetadata = ((this.getDescriptiveMetadata() != null && !this.getDescriptiveMetadata().isEmpty())
        || (this.getPreservationMetadata() != null && !this.getPreservationMetadata().isEmpty()));
      boolean isDocumentation = (this.getDocumentation() != null && !this.getDocumentation().isEmpty());
      boolean isSchemas = (this.getSchemas() != null && !this.getSchemas().isEmpty());
      boolean isSubmission = (this.getSubmissions() != null && !this.getSubmissions().isEmpty());
      boolean isRepresentations = (this.getRepresentations() != null && !this.getRepresentations().isEmpty());

      final MetsWrapper mainMETSWrapper = metsCreator.generateMETS(StringUtils.join(this.getIds(), " "),
        this.getDescription(), this.getProfile(), true, Optional.ofNullable(this.getAncestors()), null,
        this.getHeader(), this.getType(), this.getContentType(), this.getContentInformationType(), isMetadata,
        isMetadataOther, isSchemas, isDocumentation, isSubmission, isRepresentations, false);

      utils.addDescriptiveMetadataToZipAndMETS(zipEntries, mainMETSWrapper, getDescriptiveMetadata(), null);
      utils.addPreservationMetadataToZipAndMETS(zipEntries, mainMETSWrapper, getPreservationMetadata(), null);
      utils.addOtherMetadataToZipAndMETS(zipEntries, mainMETSWrapper, getOtherMetadata(), null);
      utils.addRepresentationsToZipAndMETS(this, getRepresentations(), zipEntries, mainMETSWrapper, buildDir,
        IPEnums.SipType.EARK2);
      utils.addSchemasToZipAndMETS(zipEntries, mainMETSWrapper, getSchemas(), null);
      utils.addDocumentationToZipAndMETS(zipEntries, mainMETSWrapper, getDocumentation(), null);
      utils.addSubmissionsToZipAndMETS(zipEntries, mainMETSWrapper, getSubmissions());

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
        zipEntryInfo.setChecksum(IPConstants.CHECKSUM_ALGORITHM);
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
        os = NullOutputStream.INSTANCE;
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

  private void setChecksum(final ZipEntryInfo zipEntryInfo, final String checksum, final String checksumType) {
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
