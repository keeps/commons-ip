package org.roda_project.commons_ip2.validator.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import jakarta.xml.bind.DatatypeConverter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** {@author João Gomes <jgomes@keep.pt>}. */
public class FolderManager {
  /**
   * {@link Logger}.
   */
  private static final Logger LOGGER = LoggerFactory.getLogger(FolderManager.class);

  /**
   * {@link File}.
   */
  private File folder = null;

  public boolean checkIfExistsRootMetsFile(final Path path) {
    boolean found = false;
    folder = path.toFile();
    for (File f : folder.listFiles()) {
        if (f.getName().equals("METS.xml")) {
            found = true;
            break;
        }
    }
    return found;
  }

  public InputStream getMetsRootInputStream(final Path path) throws FileNotFoundException {
    folder = path.toFile();
    String metsPath = null;
    for (File f : folder.listFiles()) {
      if (f.getName().equals("METS.xml")) {
        metsPath = f.getPath();
      }
    }
    if (metsPath == null) {
      LOGGER.debug("METS.xml not Found");
      throw new FileNotFoundException("METS.xml not Found");
    }
    return new FileInputStream(metsPath);
  }

  public String getSipRootFolderName(final Path path) {
    final String[] tmp = path.toString().split("/");
    return tmp[tmp.length - 1];
  }

  public boolean checkPathExists(final Path path) {
    return Files.exists(path);
  }

  public boolean verifyChecksum(final Path path, final String alg, final String checksum)
    throws IOException, NoSuchAlgorithmException {
    boolean valid = true;

    if (!Files.exists(path)) {
      valid = false;
    } else {
      final InputStream stream = new FileInputStream(path.toFile());
      final MessageDigest messageDigest = MessageDigest.getInstance(alg);
      final byte[] buffer = new byte[8192];
      int numOfBytesRead;
      while ((numOfBytesRead = stream.read(buffer)) > 0) {
        messageDigest.update(buffer, 0, numOfBytesRead);
      }
      final byte[] hash = messageDigest.digest();
      final String fileChecksum = DatatypeConverter.printHexBinary(hash);
      if (!checksum.equalsIgnoreCase(fileChecksum)) {
        valid = false;
      }
      stream.close();
    }
    return valid;
  }

  public boolean verifySize(final Path path, final Long metsSize) throws IOException {
    boolean valid = true;
    if (path == null) {
      valid = false;
    } else {
      if (Files.size(path) != metsSize) {
        valid = false;
      }
    }
    return valid;
  }

  public int countMetadataFiles(final Path path) {
    int count = 0;
    final File[] folder = path.toFile().listFiles();
    if (folder != null) {
      if (path.toString().contains("descriptive")) {
        for (File ignored : folder) {
          count++;
        }
      } else {
        for (File f : folder) {
          if (f.getName().equals("metadata")) {
            if (f.isDirectory()) {
              final File[] metadataFiles = f.listFiles();
              if (metadataFiles != null) {
                for (File metadata : metadataFiles) {
                  if (metadata.isDirectory() && !metadata.toString().contains("descriptive")) {
                    final File[] descriptiveFiles = metadata.listFiles();
                    if (descriptiveFiles != null) {
                      count += descriptiveFiles.length;
                    }
                  } else {
                    if (!metadata.toString().contains("descriptive")) {
                      count++;
                    }
                  }
                }
              }
            }
          }
        }
      }
    }
    return count;
  }

  public Map<String, InputStream> getSubMets(final Path path) throws FileNotFoundException {
    final HashMap<String, InputStream> subMets = new HashMap<>();
    final File[] representationsFolder = path.resolve("representations").toFile().listFiles();
    if (representationsFolder != null) {
      for (File representation : representationsFolder) {
        if (representation.isDirectory()) {
          final File[] representationFiles = representation.listFiles();
          if (representationFiles != null) {
            for (File file : representationFiles) {
              if (file.getName().equals("METS.xml")) {
                final InputStream subStream = new FileInputStream(file.getPath());
                subMets.put(file.getPath(), subStream);
              }
            }
          }
        }
      }
    }
    return subMets;
  }

  public boolean checkDirectory(final Path path) throws IOException {
    return Files.exists(path);
  }

  public Boolean checkRootFolderName(final Path path, final String objectId) {
    return path.getParent().endsWith(objectId);
  }

  public boolean checkIfExistsFolderInRoot(final Path path, final String folder) {
    final File[] root = path.toFile().listFiles();
    for (File file : root) {
      if (file.getName().equals(folder)) {
        if (file.isDirectory()) {
          return true;
        }
      }
    }
    return false;
  }

  public boolean checkIfExistsFolderInside(final Path path, final String rootFolder, final String insideFolder) {
    final File[] root = path.toFile().listFiles();
    for (File file : root) {
      if (file.getName().equals(rootFolder)) {
        if (file.isDirectory()) {
          final File[] insideFiles = file.listFiles();
          for (File f : insideFiles) {
            if (f.getName().equals(insideFolder)) {
              if (f.isDirectory()) {
                return true;
              }
            }
          }
        }
      }
    }
    return false;
  }

  public boolean checkIfExistsFolderInsideRepresentation(final Path path, final String folder) {
    final File[] representationFiles = path.resolve("representations").toFile().listFiles();
    if (representationFiles != null) {
      for (File representation : representationFiles) {
        if (representation.isDirectory()) {
          final File[] filesInsideRepresentation = representation.listFiles();
          for (File fileInside : filesInsideRepresentation) {
            if (fileInside.getName().equals(folder) && fileInside.isDirectory()) {
              return true;
            }
          }
        }
      }
    }
    return false;
  }

  public boolean checkIfExistsSubMets(final Path path) {
    final File[] root = path.toFile().listFiles();
    int countSubMets = 0;
    int countRepresentationsFolder = 0;
    for (File file : root) {
      if (file.getName().equals("representations")) {
        if (file.isDirectory()) {
          final File[] insideFiles = file.listFiles();
          for (File f : insideFiles) {
            if (f.isDirectory()) {
              countRepresentationsFolder++;
              final File[] representationFiles = f.listFiles();
              for (File representationFile : representationFiles) {
                if (representationFile.getName().equals("METS.xml")) {
                  countSubMets++;
                }
              }
            }
          }
        }
      }
    }
    return countSubMets == countRepresentationsFolder;
  }

  public List<String> getRepresentationsFoldersNames(final Path path) {
    final List<String> representationsFoldersNames = new ArrayList<>();
    final File[] rootFiles = path.toFile().listFiles();
    for (File rootFile : rootFiles) {
      if (rootFile.getName().equals("representations")) {
        if (rootFile.isDirectory()) {
          final File[] representationsFiles = rootFile.listFiles();
          for (File representation : representationsFiles) {
            if (representation.isDirectory()) {
              representationsFoldersNames.add(representation.getName());
            }
          }
        }
      }
    }
    return representationsFoldersNames;
  }

  public int countFilesInsideRepresentations(final Path path) {
    int count = 0;
    final File[] rootFiles = path.toFile().listFiles();
    for (File rootFile : rootFiles) {
      if (rootFile.getName().equals("representations")) {
        if (rootFile.isDirectory()) {
          final File[] representationsFiles = rootFile.listFiles();
          for (File representation : representationsFiles) {
            if (!representation.isDirectory()) {
              count++;
            }
          }
        }
      }
    }
    return count;
  }

  public InputStream getInputStream(final Path path) throws FileNotFoundException {
    if (path == null) {
      LOGGER.debug("File not Found");
      throw new FileNotFoundException("File not Found");
    }
    return new FileInputStream(path.toFile());
  }

  public HashMap<String, Boolean> getMetadataFiles(final Path path) throws IOException {
    if (path == null) {
      LOGGER.debug("File not Found");
      throw new FileNotFoundException("File not Found");
    }
    final HashMap<String, Boolean> data = new HashMap<>();
    try (Stream<Path> paths = Files.walk(path.resolve("metadata"))) {
      paths.forEach(filePath -> {
        if (!Files.isDirectory(filePath)) {
          data.put(filePath.toString(), false);
        }
      });
    }
    return data;
  }

  public List<String> verifyAdditionalFoldersInRoot(final Path path) {
    final List<String> additionalFolders = new ArrayList<>();
    final List<String> commonFolders = new ArrayList<>();
    commonFolders.add("metadata");
    commonFolders.add("documentation");
    commonFolders.add("schemas");
    commonFolders.add("representations");
    final File[] rootFiles = path.toFile().listFiles();
    if (rootFiles != null) {
      for (File rootFolder : rootFiles) {
        if (rootFolder.isDirectory() && !commonFolders.contains(rootFolder.getName())) {
          additionalFolders.add(rootFolder.getName());
        }
      }
    }
    return additionalFolders;
  }

  public boolean checkIfExistsFolderRepresentation(final Path ipPath, final String folder,
    final String representation) {
    final File[] representationFiles = ipPath.resolve("representations").resolve(representation).toFile().listFiles();
    if (representationFiles != null) {
      for (File representationFile : representationFiles) {
        if (representationFile.isDirectory() && representationFile.getName().equals(folder)) {
          return true;
        }
      }
    }
    return false;
  }

  public HashMap<String, Boolean> getFiles(final Path path) {
    final HashMap<String, Boolean> files = new HashMap<>();
    files.putAll(getFilesDirectory(path));
    return files;
  }

  public HashMap<String, Boolean> getFilesDirectory(Path path) {
    HashMap<String, Boolean> files = new HashMap<>();
    File folder = path.toFile();
    for (final File fileEntry : folder.listFiles()) {
      if (fileEntry.isDirectory() && !fileEntry.getName().equals("metadata")) {
        files.putAll(getFilesDirectory(Paths.get(fileEntry.getPath())));
      } else {
        if (!fileEntry.isDirectory() && !fileEntry.getName().equals("METS.xml")
          && !fileEntry.getName().equals("aip.json")) {
          files.put(Paths.get(fileEntry.getPath()).toString(), false);
        }
      }
    }
    return files;
  }
}
