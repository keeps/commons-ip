/**
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE file at the root of the source
 * tree and available online at
 *
 * https://github.com/keeps/commons-ip
 */
package org.roda_project.commons_ip2.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.xml.bind.JAXBException;

import org.roda_project.commons_ip.utils.FileZipEntryInfo;
import org.roda_project.commons_ip.utils.IPException;
import org.roda_project.commons_ip2.mets_v1_12.beans.FileType;
import org.roda_project.commons_ip2.mets_v1_12.beans.Mets;
import org.roda_project.commons_ip2.model.IPConstants;
import org.roda_project.commons_ip2.validator.constants.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class METSZipEntryInfo extends FileZipEntryInfo {
  private static final Logger LOGGER = LoggerFactory.getLogger(METSZipEntryInfo.class);

  private Mets mets;
  private boolean rootMETS;
  private Map<String, String> checksums;
  private long size;
  private FileType fileType;

  public METSZipEntryInfo(String name, Path filePath, Mets mets, boolean rootMETS, FileType fileType) {
    super(name, filePath);
    this.mets = mets;
    this.rootMETS = rootMETS;
    checksums = new HashMap<>();
    size = 0;
    this.fileType = fileType;
  }

  public Map<String, String> getChecksums() {
    return checksums;
  }

  public void setChecksums(Map<String, String> checksums) {
    this.checksums = checksums;
  }

  public long getSize() {
    return size;
  }

  public void setSize(long size) {
    this.size = size;
  }

  @Override
  public void prepareEntryforZipping() throws IPException {
    try {
      METSUtils.marshallMETS(mets, getFilePath(), rootMETS);
      if (!rootMETS && fileType != null) {
        Set<String> ianaMediaTypes = new BufferedReader(new InputStreamReader(
                getClass().getClassLoader().getResourceAsStream(Constants.PATH_RESOURCES_CSIP_VOCABULARY_IANA_MEDIA_TYPES),
                StandardCharsets.UTF_8)).lines().collect(Collectors.toSet());
        METSUtils.setFileBasicInformation(LOGGER, getFilePath(), fileType,ianaMediaTypes);

        String checksumType = IPConstants.CHECKSUM_ALGORITHM;
        Set<String> checksumAlgorithms = new HashSet<>();
        checksumAlgorithms.add(checksumType);
        try (InputStream inputStream = Files.newInputStream(getFilePath())) {
          Map<String, String> checksums = ZIPUtils.calculateChecksums(Optional.empty(), inputStream,
            checksumAlgorithms);
          String checksum = checksums.get(checksumType);
          fileType.setCHECKSUM(checksum);
          fileType.setCHECKSUMTYPE(checksumType);
        } catch (NoSuchAlgorithmException e) {
          // do nothing
        }

      }
    } catch (JAXBException | IOException e) {
      throw new IPException("Error marshalling METS", e);
    } catch (InterruptedException e) {
      // do nothing
    }
  }

}
