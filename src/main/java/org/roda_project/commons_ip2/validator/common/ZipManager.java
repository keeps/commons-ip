package org.roda_project.commons_ip2.validator.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.Enumeration;
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
}
