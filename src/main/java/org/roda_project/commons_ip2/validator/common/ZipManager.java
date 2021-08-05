package org.roda_project.commons_ip2.validator.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.DatatypeConverter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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

/**
 * @author João Gomes <jgomes@keep.pt>
 */

public class ZipManager {
  private static final Logger LOGGER = LoggerFactory.getLogger(ZipManager.class);
  private ZipFile zipFile = null;

  public InputStream getZipInputStream(Path path, String entry) throws IOException {
    if (zipFile == null) {
      zipFile = new ZipFile(path.toFile());
    }

    ZipEntry zipArchiveEntry = zipFile.getEntry(entry);
    if(zipArchiveEntry == null){
      return null;
    }
    return zipFile.getInputStream(zipArchiveEntry);
  }

  public InputStream getMetsRootInputStream(Path path) throws IOException {
    if (zipFile == null) {
      zipFile = new ZipFile(path.toFile());
    }

    Enumeration entries = zipFile.entries();
    String entry = null;
    while (entries.hasMoreElements()){
      ZipEntry entr = (ZipEntry) entries.nextElement();
      if(entr.getName().matches(".*/METS.xml")){
        if(entr.getName().split("/").length == 2){
          entry = entr.getName();
        }
      }
    }
    if(entry == null){
      LOGGER.debug("METS.xml not Found");
      throw new IOException("METS.xml not Found");
    }
    ZipEntry zipArchiveEntry = zipFile.getEntry(entry);
    return zipFile.getInputStream(zipArchiveEntry);
  }

  public Enumeration getEntries(){
    return zipFile.entries();
  }

  public ZipEntry getZipEntry(Path path, String entry){
    try {
      if (zipFile == null) {
        zipFile = new ZipFile(path.toFile());
      }

      return zipFile.getEntry(entry);
    } catch (IOException e) {
      LOGGER.debug("Failed to retrieve the entry: {} from {}", entry, path.toString(), e);
      return null;
    }
  }

  public String getSipRootFolderName(Path path) throws IOException {
    if (zipFile == null) {
      zipFile = new ZipFile(path.toFile());
    }

    Enumeration entries = zipFile.entries();
    String entry = null;
    while (entries.hasMoreElements()){
      ZipEntry entr = (ZipEntry) entries.nextElement();
      if(entr.getName().matches(".*/METS.xml")){
        if(entr.getName().split("/").length == 2){
          entry = entr.getName();
        }
      }
    }
    if(entry == null){
      LOGGER.debug("METS.xml not Found");
      throw new IOException("METS.xml not Found");
    }
    return entry.split("/")[0];
  }

  public boolean checkIfExistsRootMetsFile(Path path) throws IOException {
    boolean found = false;
    ZipFile zipFile = new ZipFile(path.toFile());
    Enumeration entries = zipFile.entries();
    while (entries.hasMoreElements()){
      ZipEntry entry = (ZipEntry) entries.nextElement();
      if(entry.getName().matches(".*/METS.xml")){
        if(entry.getName().split("/").length == 2){
          found = true;
        }
      }
    }
    return found;
  }

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

  public boolean checkPathExists(Path path, String filePath) throws IOException {
    boolean found = false;
    ZipFile zipFile = new ZipFile(path.toFile());
    Enumeration entries = zipFile.entries();
    while (entries.hasMoreElements()){
      ZipEntry entry = (ZipEntry) entries.nextElement();
      if(entry.getName().equals(filePath)){
        found = true;
        break;
      }
    }
    return found;
  }

  public boolean verifyChecksum(Path path,String file,String alg, String checksum) throws IOException, NoSuchAlgorithmException {
    boolean valid = true;
    InputStream entry = getZipInputStream(path,file);
    if(entry == null){
      valid = false;
    }
    else{
      MessageDigest messageDigest = MessageDigest.getInstance(alg);
      byte[] buffer = new byte[8192];
      int numOfBytesRead;
      while ((numOfBytesRead = entry.read(buffer)) > 0) {
        messageDigest.update(buffer, 0, numOfBytesRead);
      }
      byte[] hash = messageDigest.digest();
      String fileChecksum = DatatypeConverter.printHexBinary(hash);
      if(!checksum.equals(fileChecksum)){
        valid = false;
      }
    }
    return valid;
  }

  public boolean verifySize(Path path, String file, Long metsSize){
    boolean valid = true;
    ZipEntry entry = getZipEntry(path,file);
    if(entry == null){
      valid = false;
    }
    else{
      if(entry.getSize() != metsSize){
       valid = false;
      }
    }
    return  valid;
  }

  public boolean verifyMetadataDescriptiveFolder(Path path,String objectID) throws IOException {
    boolean found = false;
    ZipFile zipFile = new ZipFile(path.toFile());
    Enumeration entries = zipFile.entries();
    String regex = objectID + "/metadata/descriptive/.*";
    while (entries.hasMoreElements()){
      ZipEntry entry = (ZipEntry) entries.nextElement();
      if(entry.getName().matches(regex)){
        found = true;
        break;
      }
    }
    return found;
  }

  public int countMetadataDescriptiveFiles(Path path, String objectID) throws IOException {
    int count = 0;
    ZipFile zipFile = new ZipFile(path.toFile());
    Enumeration entries = zipFile.entries();
    String regex = objectID + "/metadata/descriptive/.*";
    while (entries.hasMoreElements()){
      ZipEntry entry = (ZipEntry) entries.nextElement();
      if(entry.getName().matches(regex)){
        if(!entry.isDirectory()){
          count++;
        }
      }
    }
    return count;
  }

  public HashMap<String,InputStream> getSubMets(Path path) throws IOException {
    HashMap<String,InputStream> subMets = new HashMap<>();
    ZipFile zipFile = new ZipFile(path.toFile());
    Enumeration entries = zipFile.entries();

    while (entries.hasMoreElements()){
      ZipEntry entry = (ZipEntry) entries.nextElement();
      if(entry.getName().matches(".*/METS.xml")){
        if(entry.getName().split("/").length > 2){
          InputStream stream = zipFile.getInputStream(entry);
          if(stream != null){
            subMets.put(entry.getName(),stream);
          }
        }
      }
    }
    return subMets;
  }

  public boolean checkSingleRootFolder(Path path) throws IOException{
    ZipFile zipFile = new ZipFile(path.toFile());
    Enumeration entries = zipFile.entries();

    long countSingleFolder = 0;
    Set<String> tmp = new HashSet<>();
    while (entries.hasMoreElements()){
      ZipEntry entry = (ZipEntry) entries.nextElement();
      String name = entry.getName().split("/")[0];
      tmp.add(name);
    }
    if(tmp.size() == 1){
      return true;
    }
    return false;
  }
}
