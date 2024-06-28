package org.roda_project.commons_ip2.validator.common;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import jakarta.xml.bind.DatatypeConverter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** {@author João Gomes <jgomes@keep.pt>}. */
public class ZipManager {
  private static final Logger LOGGER = LoggerFactory.getLogger(ZipManager.class);
  private ZipFile zipFile = null;

  /**
   * Gets {@link InputStream} to the IP in zip format.
   *
   * @param path
   *          {@link Path} to the IP
   * @param entry
   *          {@link String} entry
   * @return {@link InputStream} to the zip file
   * @throws IOException
   *           if some I/O error occurs
   */
  public InputStream getZipInputStream(Path path, String entry) throws IOException {
    if (zipFile == null) {
      zipFile = new ZipFile(path.toFile());
    }

    ZipEntry zipArchiveEntry = zipFile.getEntry(entry);
    if (zipArchiveEntry == null) {
      return null;
    }
    return zipFile.getInputStream(zipArchiveEntry);
  }

  /**
   * Get METS file in Root of the IP {@link InputStream}.
   *
   * @param path
   *          {@link Path} to the IP
   * @return {@link InputStream} to the file.
   * @throws IOException
   *           if some I/O error occurs
   */
  public InputStream getMetsRootInputStream(Path path) throws IOException {
    if (zipFile == null) {
      zipFile = new ZipFile(path.toFile());
    }

    Enumeration entries = zipFile.entries();
    String entry = null;
    while (entries.hasMoreElements()) {
      ZipEntry entr = (ZipEntry) entries.nextElement();
      if (entr.getName().endsWith("/METS.xml")) {
        if (entr.getName().split("/").length == 2) {
          entry = entr.getName();
        }
      }
    }
    if (entry == null) {
      LOGGER.debug("METS.xml not Found");
      throw new IOException("METS.xml not Found");
    }
    ZipEntry zipArchiveEntry = zipFile.getEntry(entry);
    return zipFile.getInputStream(zipArchiveEntry);
  }

  public Enumeration getEntries() {
    return zipFile.entries();
  }

  /**
   * Get specific entry of IP.
   *
   * @param path
   *          {@link Path} to the IP
   * @param entry
   *          {@link String} entry
   * @return {@link ZipEntry}
   */
  public ZipEntry getZipEntry(Path path, String entry) {
    try {
      if (zipFile == null) {
        zipFile = new ZipFile(path.toFile());
      }
      return zipFile.getEntry(entry);
    } catch (IOException e) {
      LOGGER.debug("Failed to retrieve the entry: {} from {}", entry, path, e);
      return null;
    }
  }

  /**
   * Check if exists METS file in root of the IP.
   *
   * @param path
   *          {@link Path} to the IP
   * @return if exists or not.
   * @throws IOException
   *           if some I/O error occurs.
   */
  public boolean checkIfExistsRootMetsFile(Path path) throws IOException {
    boolean found = false;
    ZipFile zipFile = new ZipFile(path.toFile());
    Enumeration entries = zipFile.entries();
    while (entries.hasMoreElements()) {
      ZipEntry entry = (ZipEntry) entries.nextElement();
      if (entry.getName().endsWith("/METS.xml")) {
        if (entry.getName().split("/").length == 2) {
          found = true;
          break;
        }
      }
    }
    return found;
  }

  /** Closes Zip file. */
  public void closeZipFile() {
    if (zipFile != null) {
      try {
        zipFile.close();
        zipFile = null;
      } catch (IOException e) {
        LOGGER.debug("Failed to close the ZipFile after an error occurred", e);
      }
    }
  }

  /**
   * Checks if specific path exists on the IP.
   *
   * @param path
   *          {@link Path} to the IP.
   * @param filePath
   *          {@link String} file path.
   * @return if exists the path or not.
   * @throws IOException
   *           if some I/O error occurs.
   */
  public boolean checkPathExists(Path path, String filePath) throws IOException {
    boolean found = false;
    ZipFile zipFile = new ZipFile(path.toFile());
    Enumeration entries = zipFile.entries();
    while (entries.hasMoreElements()) {
      ZipEntry entry = (ZipEntry) entries.nextElement();
      if (entry.getName().equals(filePath)) {
        found = true;
        break;
      }
    }
    return found;
  }

  /**
   * Verify if checksum given is equal against the calculation of file checksum.
   * 
   * @param path
   *          {@link Path} to the IP
   * @param file
   *          {@link String} to the file
   * @param alg
   *          {@link String} with the algorithm to checksum calculation
   * @param checksum
   *          {@link String} value of the given checksum.
   * @return if the calculation of checksum and the given checksum are equal.
   * @throws IOException
   *           if some I/O error occurs.
   * @throws NoSuchAlgorithmException
   */
  public boolean verifyChecksum(Path path, String file, String alg, String checksum)
    throws IOException, NoSuchAlgorithmException {
    boolean valid = true;
    InputStream entry = getZipInputStream(path, file);
    if (entry == null) {
      valid = false;
    } else {
      MessageDigest messageDigest = MessageDigest.getInstance(alg);
      byte[] buffer = new byte[8192];
      int numOfBytesRead;
      while ((numOfBytesRead = entry.read(buffer)) > 0) {
        messageDigest.update(buffer, 0, numOfBytesRead);
      }
      byte[] hash = messageDigest.digest();
      String fileChecksum = DatatypeConverter.printHexBinary(hash);
      if (!checksum.equalsIgnoreCase(fileChecksum)) {
        valid = false;
      }
    }
    return valid;
  }

  public boolean verifySize(Path path, String file, Long metsSize) {
    boolean valid = true;
    ZipEntry entry = getZipEntry(path, file);
    if (entry == null) {
      valid = false;
    } else {
      if (entry.getSize() != metsSize) {
        valid = false;
      }
    }
    return valid;
  }

  public boolean verifyIfExistsFilesInFolder(Path path, String regex) throws IOException {
    ZipFile zipFile = new ZipFile(path.toFile());
    Enumeration entries = zipFile.entries();
    while (entries.hasMoreElements()) {
      ZipEntry entry = (ZipEntry) entries.nextElement();
      if (entry.getName().matches(regex)) {
        if (entry.getName().split("/").length == 3) {
          if (!entry.isDirectory()) {
            return true;
          }
        }
      }
    }
    return false;
  }

  public int countMetadataFiles(Path path, String regex) throws IOException {
    int count = 0;
    ZipFile zipFile = new ZipFile(path.toFile());
    Enumeration entries = zipFile.entries();

    if (regex.contains("descriptive")) {
      while (entries.hasMoreElements()) {
        ZipEntry entry = (ZipEntry) entries.nextElement();
        if (entry.getName().matches(regex)) {
          if (!entry.isDirectory()) {
            count++;
          }
        }
      }
    } else {
      while (entries.hasMoreElements()) {
        ZipEntry entry = (ZipEntry) entries.nextElement();
        if (entry.getName().matches(regex)) {
          if (!entry.isDirectory() && !entry.toString().contains("descriptive")) {
            count++;
          }
        }
      }
    }
    return count;
  }

  public HashMap<String, InputStream> getSubMets(Path path) throws IOException {
    HashMap<String, InputStream> subMets = new HashMap<>();
    ZipFile zipFile = new ZipFile(path.toFile());
    Enumeration entries = zipFile.entries();

    while (entries.hasMoreElements()) {
      ZipEntry entry = (ZipEntry) entries.nextElement();
      if (entry.getName().endsWith("/METS.xml") && entry.getName().split("/").length > 2
        && entry.getName().split("/").length <= 4 && !entry.getName().matches(".+/submission/.+")) {
        InputStream stream = zipFile.getInputStream(entry);
        if (stream != null) {
          subMets.put(entry.getName(), stream);
        }
      }
    }
    return subMets;
  }

  public boolean checkSingleRootFolder(Path path) throws IOException {
    ZipFile zipFile = new ZipFile(path.toFile());
    Enumeration entries = zipFile.entries();

    Set<String> tmp = new HashSet<>();
    while (entries.hasMoreElements()) {
      ZipEntry entry = (ZipEntry) entries.nextElement();
      String name = entry.getName().split("/")[0];
      tmp.add(name);
    }
    return tmp.size() == 1;
  }

  public boolean checkDirectory(Path path, String directoryPath) throws IOException {
    ZipFile zipFile = new ZipFile(path.toFile());
    ZipEntry e = zipFile.getEntry(directoryPath);
    Enumeration entries = zipFile.entries();
    boolean found = false;
    if (e == null) {
      while (entries.hasMoreElements()) {
        ZipEntry entry = (ZipEntry) entries.nextElement();
        if (entry.getName().startsWith(directoryPath) && !entry.isDirectory()) {
          found = true;
        }
      }
    } else {
      found = true;
    }
    return found;
  }

  public boolean checkSubMetsFolder(Path path, String objectId) throws IOException {
    ZipFile zipFile = new ZipFile(path.toFile());
    Enumeration entries = zipFile.entries();
    while (entries.hasMoreElements()) {
      ZipEntry entry = (ZipEntry) entries.nextElement();
      String name = entry.getName();
      if (name.matches(".*/?" + objectId + "/METS.xml")) {
        return true;
      } else {
        if (name.matches(".*/?" + objectId.toLowerCase() + "/METS.xml")) {
          return true;
        }
      }
    }
    return false;
  }

  public boolean checkRootFolderName(Path path, String objectId) throws IOException {
    if (zipFile == null) {
      zipFile = new ZipFile(path.toFile());
    }

    Enumeration entries = zipFile.entries();
    String entry = null;
    while (entries.hasMoreElements()) {
      ZipEntry entr = (ZipEntry) entries.nextElement();
      if (entr.getName().matches(".*/METS.xml")) {
        if (entr.getName().split("/").length == 2) {
          entry = entr.getName();
        }
      }
    }
    if (entry == null) {
      LOGGER.debug("METS.xml not Found");
      throw new IOException("METS.xml not Found");
    }
    return entry.split("/")[0].equals(objectId);
  }

  public HashMap<String, Boolean> getMetadataFiles(Path path, String regex) throws IOException {
    HashMap<String, Boolean> metadataFiles = new HashMap<>();
    ZipFile zipFile = new ZipFile(path.toFile());
    Enumeration entries = zipFile.entries();
    while (entries.hasMoreElements()) {
      ZipEntry entry = (ZipEntry) entries.nextElement();
      if (entry.getName().matches(regex)) {
        if (!entry.isDirectory()) {
          metadataFiles.put(entry.getName(), false);
        }
      }
    }
    return metadataFiles;
  }

  public HashMap<String, Boolean> getFiles(Path path) throws IOException {
    HashMap<String, Boolean> metadataFiles = new HashMap<>();
    ZipFile zipFile = new ZipFile(path.toFile());
    Enumeration entries = zipFile.entries();
    while (entries.hasMoreElements()) {
      ZipEntry entry = (ZipEntry) entries.nextElement();
      if (!entry.getName().matches(".*/METS.xml") && !entry.getName().contains("/metadata") && !entry.isDirectory()
        && !entry.getName().contains("/aip.json")) {
        metadataFiles.put(entry.getName(), false);
      }
    }
    return metadataFiles;
  }

  public boolean checkPathIsDirectory(Path path, String filePath) throws IOException {
    boolean found = false;
    ZipFile zipFile = new ZipFile(path.toFile());
    Enumeration entries = zipFile.entries();
    while (entries.hasMoreElements()) {
      ZipEntry entry = (ZipEntry) entries.nextElement();
      if (entry.getName().matches(".*/?" + filePath + "/")) {
        if (entry.isDirectory()) {
          found = true;
          break;
        }
      }
    }
    return found;
  }

  public boolean checkIfExistsFolderInRoot(Path path, String folder) throws IOException {
    boolean found = false;
    ZipFile zipFile = new ZipFile(path.toFile());
    Enumeration entries = zipFile.entries();
    while (entries.hasMoreElements()) {
      ZipEntry entry = (ZipEntry) entries.nextElement();
      if (entry.getName().matches(".*/" + folder + "/.*")) {
        if (entry.getName().split("/")[1].equals(folder)) {
          found = true;
          break;
        }
      }
    }
    return found;
  }

  public boolean checkIfExistsFolderInside(Path path, String folder) throws IOException {
    boolean found = false;
    ZipFile zipFile = new ZipFile(path.toFile());
    Enumeration entries = zipFile.entries();
    while (entries.hasMoreElements()) {
      ZipEntry entry = (ZipEntry) entries.nextElement();
      if (entry.getName().matches(".*/" + folder + "/.*") && entry.getName().split("/").length >= 3) {
        found = true;
        break;
      }
    }
    return found;
  }

  public boolean checkIfExistsFolderInsideRepresentation(Path path, String folder) throws IOException {
    ZipFile zipFile = new ZipFile(path.toFile());
    Enumeration entries = zipFile.entries();
    while (entries.hasMoreElements()) {
      ZipEntry entry = (ZipEntry) entries.nextElement();
      if (entry.getName().matches(".*/representations/.*/" + folder + "/.*")
        && entry.getName().split("/").length >= 4) {
        return true;
      }
    }
    return false;
  }

  public boolean checkIfExistsSubMets(Path path) throws IOException {
    ZipFile zipFile = new ZipFile(path.toFile());
    Enumeration entries = zipFile.entries();
    int countSubMets = 0;
    int countRepresentations;
    List<String> representationsFoldersNames = new ArrayList<>();
    while (entries.hasMoreElements()) {
      ZipEntry entry = (ZipEntry) entries.nextElement();
      if (entry.getName().endsWith("/METS.xml")) {
        if (entry.getName().split("/").length > 2 && entry.getName().split("/").length <= 4
          && !entry.getName().matches(".+/submission/.+")) {
          countSubMets++;
        }
      } else {
        if (entry.getName().contains("/representations/") && (entry.getName().split("/").length > 3
          && !entry.isDirectory() && !entry.getName().matches(".+/submission/.+"))) {
          String representationName = getRepresentationName(entry.getName());
          if (!representationsFoldersNames.contains(representationName)) {
            representationsFoldersNames.add(representationName);
          }
        }
      }
    }
    zipFile.close();
    countRepresentations = representationsFoldersNames.size();
    return countSubMets == countRepresentations;
  }

  public List<String> getRepresentationsFoldersNames(Path path) throws IOException {
    List<String> representationsFoldersNames = new ArrayList<>();
    ZipFile zipFile = new ZipFile(path.toFile());
    Enumeration entries = zipFile.entries();
    while (entries.hasMoreElements()) {
      ZipEntry entry = (ZipEntry) entries.nextElement();
      if (entry.getName().contains("/representations/") && entry.getName().split("/").length > 3
        && !entry.getName().matches(".+/submission/.+")) {
        String representationName = getRepresentationName(entry.getName());
        if (!representationsFoldersNames.contains(representationName)) {
          representationsFoldersNames.add(representationName);
        }
      }
    }
    zipFile.close();
    return representationsFoldersNames;
  }

  public int countFilesInsideRepresentations(Path path) throws IOException {
    int count = 0;
    ZipFile zipFile = new ZipFile(path.toFile());
    Enumeration entries = zipFile.entries();
    while (entries.hasMoreElements()) {
      ZipEntry entry = (ZipEntry) entries.nextElement();
      if (entry.getName().contains("/representations/") && !entry.getName().matches(".+/submission/.+")
        && entry.getName().split("/").length == 3 && !entry.getName().endsWith("/")) {
        count++;
      }
    }
    return count;
  }

  public List<String> verifyAdditionalFoldersInRoot(Path path) throws IOException {
    List<String> commonFolders = new ArrayList<>();
    commonFolders.add("metadata");
    commonFolders.add("documentation");
    commonFolders.add("schemas");
    commonFolders.add("representations");
    List<String> additionalFolders = new ArrayList<>();
    ZipFile zipFile = new ZipFile(path.toFile());
    Enumeration entries = zipFile.entries();
    while (entries.hasMoreElements()) {
      ZipEntry entry = (ZipEntry) entries.nextElement();
      String[] folder = entry.getName().split("/");
      if (folder.length == 2 && entry.isDirectory()) {
        if (!commonFolders.contains(folder[1])) {
          additionalFolders.add(folder[1]);
        }
      }
    }
    return additionalFolders;
  }

  private String getRepresentationName(String entry) {
    String[] representations = entry.split("/");
      String representationName = representations[0] + "/" + representations[1] + "/" +
              representations[2];
    return representationName;
  }

  public boolean checkIfExistsFolderRepresentation(Path ipPath, String folder, String representation)
    throws IOException {
    ZipFile zipFile = new ZipFile(ipPath.toFile());
    Enumeration entries = zipFile.entries();
    StringBuilder regex = new StringBuilder();
    regex.append(".+/").append(representation).append("/").append(folder);
    while (entries.hasMoreElements()) {
      ZipEntry entry = (ZipEntry) entries.nextElement();
      if (entry.getName().matches(regex.toString())) {
        return true;
      }
    }
    return false;
  }
}
