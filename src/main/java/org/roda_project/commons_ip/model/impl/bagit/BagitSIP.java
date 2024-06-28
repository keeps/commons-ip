/**
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE file at the root of the source
 * tree and available online at
 * 
 * https://github.com/keeps/commons-ip
 */
package org.roda_project.commons_ip.model.impl.bagit;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.channels.ClosedByInterruptException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.NoSuchAlgorithmException;
import java.util.AbstractMap;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.io.IOUtils;
import org.roda_project.commons_ip.model.IPConstants;
import org.roda_project.commons_ip.model.IPContentType;
import org.roda_project.commons_ip.model.IPFile;
import org.roda_project.commons_ip.model.IPRepresentation;
import org.roda_project.commons_ip.model.ParseException;
import org.roda_project.commons_ip.model.SIP;
import org.roda_project.commons_ip.model.impl.ModelUtils;
import org.roda_project.commons_ip.utils.IPException;
import org.roda_project.commons_ip.utils.Utils;
import org.roda_project.commons_ip.utils.ZIPUtils;
import org.roda_project.commons_ip.utils.ZipEntryInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import gov.loc.repository.bagit.creator.BagCreator;
import gov.loc.repository.bagit.domain.Bag;
import gov.loc.repository.bagit.domain.Manifest;
import gov.loc.repository.bagit.domain.Metadata;
import gov.loc.repository.bagit.exceptions.CorruptChecksumException;
import gov.loc.repository.bagit.exceptions.FileNotInPayloadDirectoryException;
import gov.loc.repository.bagit.exceptions.InvalidBagitFileFormatException;
import gov.loc.repository.bagit.exceptions.MaliciousPathException;
import gov.loc.repository.bagit.exceptions.MissingBagitFileException;
import gov.loc.repository.bagit.exceptions.MissingPayloadDirectoryException;
import gov.loc.repository.bagit.exceptions.MissingPayloadManifestException;
import gov.loc.repository.bagit.exceptions.UnparsableVersionException;
import gov.loc.repository.bagit.exceptions.UnsupportedAlgorithmException;
import gov.loc.repository.bagit.exceptions.VerificationException;
import gov.loc.repository.bagit.hash.StandardSupportedAlgorithms;
import gov.loc.repository.bagit.reader.BagReader;
import gov.loc.repository.bagit.verify.BagVerifier;

public class BagitSIP extends SIP {
  private static final Logger LOGGER = LoggerFactory.getLogger(BagitSIP.class);
  private static final String SIP_TEMP_DIR = "BAGIT";
  private static final String SIP_FILE_EXTENSION = ".zip";

  public BagitSIP() {
    super();
  }

  /**
   * @param sipId
   */
  public BagitSIP(String sipId) {
    super(sipId);
  }

  /**
   * @param sipId
   */
  public BagitSIP(String sipId, IPContentType contentType) {
    super(sipId, contentType);
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
    IPConstants.METS_ENCODE_AND_DECODE_HREF = true;
    Path buildDir = ModelUtils.createBuildDir(SIP_TEMP_DIR);
    Path zipPath = getZipPath(destinationDirectory, fileNameWithoutExtension);

    try {
      Map<String, ZipEntryInfo> zipEntries = getZipEntries();

      // Create Metadata
      Path metadataPath = getDescriptiveMetadata().get(0).getMetadata().getPath();
      Map<String, String> metadataList = BagitUtils.getBagitInfo(metadataPath);
      Metadata metadata = new Metadata();
      for (Entry<String, String> entry : metadataList.entrySet()) {
        metadata.add(entry.getKey(), entry.getValue());
      }
      metadata.add(IPConstants.BAGIT_VENDOR, IPConstants.BAGIT_VENDOR_COMMONS_IP);

      // representation data
      BagitUtils.addRepresentationToZipAndBagit(this, getRepresentations(), zipEntries, buildDir);

      // Create Bag
      BagCreator.bagInPlace(buildDir, List.of(StandardSupportedAlgorithms.SHA256), false, metadata);

      // Add bag files to zip
      BagitUtils.addBagFileToZip(zipEntries, buildDir, BagitUtils.BAGIT_FILE_NAME);
      BagitUtils.addBagFileToZip(zipEntries, buildDir, BagitUtils.BAGIT_INFO_FILE_NAME);
      BagitUtils.addBagFileToZip(zipEntries, buildDir,
        BagitUtils.BAGIT_MANIFEST_FILE_NAME + StandardSupportedAlgorithms.SHA256.getBagitName());
      BagitUtils.addBagFileToZip(zipEntries, buildDir,
        BagitUtils.BAGIT_TAG_MANIFEST_FILE_NAME + StandardSupportedAlgorithms.SHA256.getBagitName());

      createZipFile(zipPath, zipEntries);
      return zipPath;
    } catch (IOException | NoSuchAlgorithmException e) {
      throw new IPException("Could not make bag in place", e);
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

  private void createZipFile(Path zipPath, Map<String, ZipEntryInfo> zipEntries)
    throws IPException, InterruptedException {
    try {
      notifySipBuildPackagingStarted(zipEntries.size());
      ZIPUtils.zip(zipEntries, Files.newOutputStream(zipPath), this, true, true);
    } catch (ClosedByInterruptException e) {
      throw new InterruptedException();
    } catch (IOException e) {
      throw new IPException("Error generating Bagit ZIP file. Reason: " + e.getMessage(), e);
    } finally {
      notifySipBuildPackagingEnded();
    }
  }

  /**
   *
   * parse and all parse related methods; during parse, validation is also
   * conducted and stored inside the SIP
   * _________________________________________________________________________
   */

  public static SIP parse(Path source, Path destinationDirectory) throws ParseException {
    return parseBagit(source, destinationDirectory);
  }

  public static SIP parse(Path source) throws ParseException {
    try {
      return parse(source, Files.createTempDirectory("unzipped"));
    } catch (IOException e) {
      throw new ParseException("Error creating temporary directory for bagit SIP parse", e);
    }
  }

  private static SIP parseBagit(final Path source, final Path destinationDirectory) throws ParseException {
    IPConstants.METS_ENCODE_AND_DECODE_HREF = true;
    SIP sip = new BagitSIP();

    Path sipPath = BagitUtils.extractBagitIPIfInZipFormat(source, destinationDirectory);
    sip.setBasePath(sipPath);

    try (BagVerifier verifier = new BagVerifier()) {
      BagReader reader = new BagReader();
      Bag bag = reader.read(sipPath);
      verifier.isValid(bag, false);

      Map<String, String> metadataMap = new HashMap<>();

      for (AbstractMap.SimpleImmutableEntry<String, String> nameValue : bag.getMetadata().getAll()) {
        String key = nameValue.getKey();
        String value = nameValue.getValue();

        if (IPConstants.BAGIT_PARENT.equals(key)) {
          sip.setAncestors(Collections.singletonList(value));
        } else {
          if (IPConstants.BAGIT_ID.equals(key)) {
            sip.setId(value);
          }
          metadataMap.put(key, value);
        }
      }

      String vendor = metadataMap.get(IPConstants.BAGIT_VENDOR);
      Path metadataPath = destinationDirectory.resolve(Utils.generateRandomAndPrefixedUUID());
      sip.addDescriptiveMetadata(BagitUtils.createBagitMetadata(metadataMap, metadataPath));
      Map<String, IPRepresentation> representations = new HashMap<>();

      for (Manifest payLoadManifest : bag.getPayLoadManifests()) {
        Map<Path, String> fileToChecksumMap = payLoadManifest.getFileToChecksumMap();
        for (Path payload : fileToChecksumMap.keySet()) {
          List<String> split = Arrays.asList(sipPath.relativize(payload).toString().split("/"));
          if (split.size() > 1 && IPConstants.BAGIT_DATA_FOLDER.equals(split.get(0))) {
            String representationId = "rep1";
            int beginIndex = 1;
            if (IPConstants.BAGIT_VENDOR_COMMONS_IP.equals(vendor)) {
              representationId = split.get(1);
              beginIndex = 2;
            }

            if (!representations.containsKey(representationId)) {
              representations.put(representationId, new IPRepresentation(representationId));
            }

            IPRepresentation representation = representations.get(representationId);
            List<String> directoryPath = split.subList(beginIndex, split.size() - 1);
            Path destPath = destinationDirectory.resolve(split.get(split.size() - 1));
            try (InputStream bagStream = Files.newInputStream(payload);
              OutputStream destStream = Files.newOutputStream(destPath)) {
              IOUtils.copyLarge(bagStream, destStream);
            }

            IPFile file = new IPFile(destPath, directoryPath);
            representation.addFile(file);
          }
        }
      }

      for (IPRepresentation rep : representations.values()) {
        sip.addRepresentation(rep);
      }

      return sip;
    } catch (final IPException | IOException | UnparsableVersionException e) {
      throw new ParseException("Error parsing bagit SIP", e);
    } catch (MissingPayloadManifestException | MissingBagitFileException | InterruptedException
      | FileNotInPayloadDirectoryException | InvalidBagitFileFormatException | VerificationException
      | UnsupportedAlgorithmException | CorruptChecksumException | MaliciousPathException
      | MissingPayloadDirectoryException e) {
      throw new ParseException("Error validating bagit SIP", e);
    }
  }

  @Override
  public Set<String> getExtraChecksumAlgorithms() {
    return Collections.emptySet();
  }
}
