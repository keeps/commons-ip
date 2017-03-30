/**
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE file at the root of the source
 * tree and available online at
 *
 * https://github.com/keeps/commons-ip
 */
package org.roda_project.commons_ip.model.impl.bagit;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.roda_project.commons_ip.model.IPConstants;
import org.roda_project.commons_ip.model.IPContentType;
import org.roda_project.commons_ip.model.IPFile;
import org.roda_project.commons_ip.model.IPRepresentation;
import org.roda_project.commons_ip.model.ParseException;
import org.roda_project.commons_ip.model.SIP;
import org.roda_project.commons_ip.utils.IPException;
import org.roda_project.commons_ip.utils.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import gov.loc.repository.bagit.Bag;
import gov.loc.repository.bagit.BagFactory;
import gov.loc.repository.bagit.BagFile;
import gov.loc.repository.bagit.PreBag;
import gov.loc.repository.bagit.utilities.SimpleResult;
import gov.loc.repository.bagit.utilities.namevalue.NameValueReader.NameValue;
import gov.loc.repository.bagit.writer.impl.ZipWriter;

public class BagitSIP extends SIP {
  private static final Logger LOGGER = LoggerFactory.getLogger(BagitSIP.class);

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
  public BagitSIP(String sipId, IPContentType contentType, String creator) {
    super(sipId, contentType, creator);
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
    Path namePath;
    if (StringUtils.isNotBlank(fileNameWithoutExtension)) {
      namePath = destinationDirectory.resolve(fileNameWithoutExtension);
    } else {
      namePath = destinationDirectory.resolve(getId());
    }

    Path data = namePath.resolve(IPConstants.BAGIT_DATA_FOLDER);
    new File(data.toString()).mkdirs();
    int fileCounter = 0;

    for (IPRepresentation rep : getRepresentations()) {
      for (IPFile file : rep.getData()) {
        createFiles(file, data);
        fileCounter++;
      }
    }

    BagFactory bf = new BagFactory();
    PreBag pb = bf.createPreBag(new File(namePath.toString()));
    try (Bag b = pb.makeBagInPlace(BagFactory.Version.V0_97, false)) {

      // additional metadata
      Path metadataPath = getDescriptiveMetadata().get(0).getMetadata().getPath();
      Map<String, String> metadataList = BagitUtils.getBagitInfo(metadataPath);
      for (Entry<String, String> entry : metadataList.entrySet()) {
        b.getBagInfoTxt().put(entry.getKey(), entry.getValue());
      }

      b.makeComplete();

      notifySipBuildPackagingStarted(fileCounter);
      ZipWriter zipWriter = new ZipWriter(bf);
      zipWriter.write(b, new File(namePath.toString()));
      zipWriter.endPayload();
      notifySipBuildPackagingEnded();
    } catch (IOException e) {
      LOGGER.error("Could not make bag in place");
    }

    return namePath;
  }

  private void createFiles(IPFile file, Path dest) {
    try {
      recCreateFiles(file.getPath(), dest);
    } catch (IOException e) {
      LOGGER.error("Error creating file {} on bagit data folder", file.getFileName());
    }
  }

  private void recCreateFiles(Path nodePath, Path dest) throws IOException {
    if (Files.isDirectory(nodePath)) {
      Path directory = dest.resolve(nodePath.getFileName().toString());
      new File(directory.toString()).mkdir();
      for (File file : nodePath.toFile().listFiles()) {
        recCreateFiles(Paths.get(file.getPath()), directory);
      }
    } else {
      Path destination = dest.resolve(nodePath.getFileName().toString());
      copyFile(nodePath, destination);
    }
  }

  private void copyFile(Path path, Path dest) {
    final int progressCheckpoint = 1000;
    long bytesCopied = 0;
    long previousLength = 0;
    File destFile = dest.toFile();

    try {
      long totalBytes = Files.size(path);
      try (InputStream in = new FileInputStream(path.toFile()); OutputStream out = new FileOutputStream(destFile)) {
        byte[] buf = new byte[1024];
        int counter = 0;
        int len;

        while ((len = in.read(buf)) > 0) {
          out.write(buf, 0, len);
          counter += len;
          bytesCopied += (destFile.length() - previousLength);
          previousLength = destFile.length();
          if (counter > progressCheckpoint || bytesCopied == totalBytes) {
            counter = 0;
          }
        }
      }
    } catch (IOException e) {
      LOGGER.error("Error writing(copying) file. Source: {}; Destination: {}", path, dest, e);
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
    SIP sip = new BagitSIP();
    BagFactory bagFactory = new BagFactory();

    try (Bag bag = bagFactory.createBag(source.toFile())) {
      SimpleResult result = bag.verifyPayloadManifests();
      if (result.isSuccess()) {
        Map<String, String> metadataMap = new HashMap<>();
        for (NameValue nameValue : bag.getBagInfoTxt().asList()) {
          String key = nameValue.getKey();
          String value = nameValue.getValue();

          if (IPConstants.BAGIT_PARENT.equals(key)) {
            sip.setAncestors(Arrays.asList(value));
          } else {
            if (IPConstants.BAGIT_ID.equals(key)) {
              sip.setId(value);
            }
            metadataMap.put(key, value);
          }
        }

        Path metadataPath = destinationDirectory.resolve(Utils.generateRandomAndPrefixedUUID());
        sip.addDescriptiveMetadata(BagitUtils.createBagitMetadata(metadataMap, metadataPath));

        IPRepresentation representation = new IPRepresentation();
        for (BagFile bagFile : bag.getPayload()) {
          List<String> split = Arrays.asList(bagFile.getFilepath().split("/"));
          if (!split.isEmpty() && IPConstants.BAGIT_DATA_FOLDER.equals(split.get(0))) {
            List<String> directoryPath = split.subList(1, split.size() - 1);
            Path destPath = destinationDirectory.resolve(split.get(split.size() - 1));
            IOUtils.copy(bagFile.newInputStream(), Files.newOutputStream(destPath));
            representation.addFile(new IPFile(destPath, directoryPath));
          }
        }

        sip.addRepresentation(representation);
      } else {
        throw new ParseException(result.getMessages().toString());
      }

      return sip;
    } catch (final IPException | IOException e) {
      throw new ParseException("Error parsing bagit SIP", e);
    }
  }

  @Override
  public Set<String> getExtraChecksumAlgorithms() {
    return new HashSet<>();
  }

}
