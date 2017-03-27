/**
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE file at the root of the source
 * tree and available online at
 *
 * https://github.com/keeps/commons-ip
 */
package org.roda_project.commons_ip.model.impl.hungarian;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

import org.roda_project.commons_ip.mets_v1_11.beans.FileType;
import org.roda_project.commons_ip.model.IPConstants;
import org.roda_project.commons_ip.model.IPDescriptiveMetadata;
import org.roda_project.commons_ip.model.IPFile;
import org.roda_project.commons_ip.model.IPInterface;
import org.roda_project.commons_ip.model.IPRepresentation;
import org.roda_project.commons_ip.model.MetsWrapper;
import org.roda_project.commons_ip.model.SIP;
import org.roda_project.commons_ip.utils.IPException;
import org.roda_project.commons_ip.utils.Utils;
import org.roda_project.commons_ip.utils.ZIPUtils;
import org.roda_project.commons_ip.utils.ZipEntryInfo;
import org.slf4j.Logger;

public final class HungarianUtils {

  private HungarianUtils() {
    // do nothing
  }

  protected static void addMetadataToMETS(MetsWrapper metsWrapper, List<IPDescriptiveMetadata> descriptiveMetadata)
    throws IPException, InterruptedException {
    if (descriptiveMetadata != null && !descriptiveMetadata.isEmpty()) {
      for (IPDescriptiveMetadata dm : descriptiveMetadata) {
        if (Thread.interrupted()) {
          throw new InterruptedException();
        }

        HungarianMETSUtils.addDescriptiveMetadataToMETS(metsWrapper, dm);
      }
    }
  }

  protected static void addRepresentationsToZipAndMETS(IPInterface ip, List<IPRepresentation> representations,
    Map<String, ZipEntryInfo> zipEntries, MetsWrapper mainMETSWrapper) throws IPException, InterruptedException {
    // representations
    if (representations != null && !representations.isEmpty()) {
      if (ip instanceof SIP) {
        ((SIP) ip).notifySipBuildRepresentationsProcessingStarted(representations.size());
      }

      for (IPRepresentation representation : representations) {
        if (Thread.interrupted()) {
          throw new InterruptedException();
        }

        // representation data
        addRepresentationDataFilesToZipAndMETS(ip, zipEntries, mainMETSWrapper, representation);
      }

      if (ip instanceof SIP) {
        ((SIP) ip).notifySipBuildRepresentationsProcessingEnded();
      }
    }
  }

  protected static void addRepresentationDataFilesToZipAndMETS(IPInterface ip, Map<String, ZipEntryInfo> zipEntries,
    MetsWrapper mainMETSWrapper, IPRepresentation representation) throws IPException, InterruptedException {
    if (representation.getData() != null && !representation.getData().isEmpty()) {
      if (ip instanceof SIP) {
        ((SIP) ip).notifySipBuildRepresentationProcessingStarted(representation.getData().size());
      }

      int i = 0;
      for (IPFile file : representation.getData()) {
        if (Thread.interrupted()) {
          throw new InterruptedException();
        }

        String dataFilePath = IPConstants.DATA_FOLDER + getFoldersFromList(file.getRelativeFolders())
          + file.getFileName();
        FileType fileType = HungarianMETSUtils.addDataFileToMETS(mainMETSWrapper, dataFilePath, file.getPath());

        dataFilePath = IPConstants.CONTENT_FOLDER + IPConstants.ZIP_PATH_SEPARATOR + dataFilePath;
        ZIPUtils.addFileTypeFileToZip(zipEntries, file.getPath(), dataFilePath, fileType);

        i++;
        if (ip instanceof SIP) {
          ((SIP) ip).notifySipBuildRepresentationProcessingCurrentStatus(i);
        }
      }

      if (ip instanceof SIP) {
        ((SIP) ip).notifySipBuildRepresentationProcessingEnded();
      }
    }
  }

  protected static void addDocumentationToZipAndMETS(Map<String, ZipEntryInfo> zipEntries, MetsWrapper metsWrapper,
    List<IPFile> documentation) throws IPException, InterruptedException {
    if (documentation != null && !documentation.isEmpty()) {
      for (IPFile doc : documentation) {
        if (Thread.interrupted()) {
          throw new InterruptedException();
        }

        String documentationFilePath = IPConstants.CONTENT_FOLDER + IPConstants.ZIP_PATH_SEPARATOR
          + IPConstants.DOCUMENTATION_FOLDER + getFoldersFromList(doc.getRelativeFolders()) + doc.getFileName();
        FileType fileType = HungarianMETSUtils.addDocumentationFileToMETS(metsWrapper, documentationFilePath,
          doc.getPath());

        ZIPUtils.addFileTypeFileToZip(zipEntries, doc.getPath(), documentationFilePath, fileType);
      }
    }
  }

  protected static void addMainMETSToZip(Map<String, ZipEntryInfo> zipEntries, MetsWrapper mainMETSWrapper,
    Path buildDir) throws IPException {
    HungarianMETSUtils.addMainMETSToZip(zipEntries, mainMETSWrapper, IPConstants.HUNGARIAN_METADATA_FILE, buildDir);
  }

  /*--------------------------------*/
  protected static void deleteBuildDir(Path buildDir) throws IPException {
    try {
      Utils.deletePath(buildDir);
    } catch (IOException e) {
      throw new IPException("Error while deleting temporary directory that was created to hold IP files", e);
    }
  }

  protected static Path createBuildDir(String prefix) throws IPException {
    try {
      return Files.createTempDirectory(prefix);
    } catch (IOException e) {
      throw new IPException("Unable to create temporary directory to hold IP files with prefix '" + prefix + "'", e);
    }
  }

  protected static String getFoldersFromList(List<String> folders) {
    StringBuilder sb = new StringBuilder();
    for (String folder : folders) {
      sb.append(folder);
      if (sb.length() > 0) {
        sb.append(IPConstants.ZIP_PATH_SEPARATOR);
      }
    }
    return sb.toString();
  }

  protected static void cleanUpUponInterrupt(Logger logger, Path path) {
    if (path != null && Files.exists(path)) {
      try {
        Utils.deletePath(path);
      } catch (IOException e) {
        logger.error("Error while cleaning up unneeded files", e);
      }
    }
  }

}
