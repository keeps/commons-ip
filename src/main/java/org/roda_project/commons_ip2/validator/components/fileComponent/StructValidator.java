package org.roda_project.commons_ip2.validator.components.fileComponent;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import org.roda_project.commons_ip2.validator.reporter.ReporterDetails;
import org.roda_project.commons_ip2.validator.state.StructureValidatorState;
import org.roda_project.commons_ip2.validator.utils.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Carlos Afonso <cafonso@keep.pt>
 */
public abstract class StructValidator {

  protected abstract String getCSIPVersion();

  private static final Logger LOGGER = LoggerFactory.getLogger(StructValidator.class);

  /**
   * Any Information Package MUST be included within a single physical root folder
   * (known as the “Information Package root folder”). For packages presented in
   * an archive format, see CSIPSTR3, the archive MUST unpack to a single root
   * folder.
   *
   * @param structureValidatorState
   *          the contextual {@link StructureValidatorState}
   * @param zipMagicNumber
   * @param moduleName
   * @return {@link ReporterDetails}
   * @throws IOException
   *           if some I/O error occurs
   */
  protected ReporterDetails validateCSIPSTR1(final StructureValidatorState structureValidatorState,
    byte[] zipMagicNumber, String moduleName) throws IOException {
    ReporterDetails details = new ReporterDetails();
    if (Files.exists(structureValidatorState.getIpPath())) {
      if (isZipFile(structureValidatorState.getIpPath(), zipMagicNumber, moduleName)) {
        structureValidatorState.setZipFileFlag(true);
        if (!structureValidatorState.getZipManager().checkSingleRootFolder(structureValidatorState.getIpPath())) {
          details.setValid(false);
          details.addIssue("MUST unpack to a single folder");
        }
      } else {
        if (!Files.isDirectory(structureValidatorState.getIpPath())) {
          details.setValid(false);
          details.addIssue(Message.createErrorMessage(
            "The SIP path (%1$s) does not exist or " + "is not a directory or is not an archived file format",
            structureValidatorState.getIpPath().toString(), false));
        }
      }
    } else {
      details.setValid(false);
      details.addIssue(Message.createErrorMessage(
        "The SIP path (%1$s) does not exist or is " + "not a directory or is not an archived file format",
        structureValidatorState.getIpPath().toString(), false));
    }
    return details;
  }

  /**
   * The Information Package root folder MAY be compressed (for example by using
   * TAR or ZIP). Which specific compression format to use needs to be stated in
   * the Submission Agreement.
   *
   * @param structureValidatorState
   *          the contextual {@link StructureValidatorState}
   * @return the {@link ReporterDetails}
   */
  protected ReporterDetails validateCSIPSTR3(final StructureValidatorState structureValidatorState) {
    if (structureValidatorState.isZipFileFlag()) {
      if (structureValidatorState.getIpPath().toString().contains("zip")) {
        return new ReporterDetails(getCSIPVersion(), "Information package is compressed in ZIP format", true, false);
      } else {
        return new ReporterDetails(getCSIPVersion(), "Information package is compressed in TAR format", true, false);
      }
    } else {
      return new ReporterDetails(getCSIPVersion(), "The Information Package root folder MAY be compressed.", false,
        false);
    }
  }

  /**
   * The Information Package root folder MUST include a file named METS.xml. This
   * file MUST contain metadata that identifies the package, provides a high-level
   * package description, and describes its structure, including pointers to
   * constituent representations.
   *
   * @param structureValidatorState
   *          the contextual {@link StructureValidatorState}
   * @return {@link ReporterDetails}
   * @throws IOException
   *           if some I/O error occurs
   */
  protected ReporterDetails validateCSIPSTR4(final StructureValidatorState structureValidatorState) throws IOException {
    ReporterDetails details = new ReporterDetails();
    if (structureValidatorState.isZipFileFlag()) {
      if (!structureValidatorState.getZipManager().checkIfExistsRootMetsFile(structureValidatorState.getIpPath())) {
        details.setValid(false);
        details.addIssue("METS.xml in root folder not found; Please verify name of File or is existence.");
      }
    } else {
      if (!structureValidatorState.getFolderManager().checkIfExistsRootMetsFile(structureValidatorState.getIpPath())) {
        details.setValid(false);
        details.addIssue("METS.xml in root folder not found; Please verify name of File or is existence.");
      }
    }
    return details;
  }

  /**
   * The Information Package root folder SHOULD include a folder named metadata,
   * which SHOULD include metadata relevant to the whole package.
   *
   * @param structureValidatorState
   *          the contextual {@link StructureValidatorState}
   * @return {@link ReporterDetails}
   * @throws IOException
   *           if some I/O error occurs
   */
  protected ReporterDetails validateCSIPSTR5(final StructureValidatorState structureValidatorState) throws IOException {
    if (structureValidatorState.isZipFileFlag()) {
      if (!structureValidatorState.getZipManager().checkIfExistsFolderInRoot(structureValidatorState.getIpPath(),
        "metadata")) {
        return new ReporterDetails(getCSIPVersion(),
          "Include Metadata relevant to the whole package in folder metadata", false, false);
      }
    } else {
      if (!structureValidatorState.getFolderManager().checkIfExistsFolderInRoot(structureValidatorState.getIpPath(),
        "metadata")) {
        return new ReporterDetails(getCSIPVersion(),
          "Include Metadata relevant to the whole package in folder metadata", false, false);
      }
    }
    return new ReporterDetails();
  }

  /**
   * If preservation metadata are available, they SHOULD be included in sub-folder
   * preservation.
   *
   * @param structureValidatorState
   *          the contextual {@link StructureValidatorState}
   * @return {@link ReporterDetails}
   * @throws IOException
   *           if some I/O error occurs
   */
  protected ReporterDetails validateCSIPSTR6(final StructureValidatorState structureValidatorState) throws IOException {
    if (structureValidatorState.isZipFileFlag()) {
      if (structureValidatorState.getZipManager().checkIfExistsFolderInside(structureValidatorState.getIpPath(),
        "*/preservation")) {
        if (!structureValidatorState.getZipManager().checkIfExistsFolderInside(structureValidatorState.getIpPath(),
          "metadata/preservation")) {
          return new ReporterDetails(getCSIPVersion(),
            "If preservation metadata are available should include inside metadata/preservation", false, false);
        }
      }

    } else {
      if (!structureValidatorState.getFolderManager().checkIfExistsFolderInside(structureValidatorState.getIpPath(),
        "metadata", "preservation")) {
        if (structureValidatorState.getFolderManager().checkIfExistsFolderInRoot(structureValidatorState.getIpPath(),
          "preservation")) {
          return new ReporterDetails(getCSIPVersion(),
            "If preservation metadata are available should include inside metadata/preservation", false, false);
        }
      }
    }
    return new ReporterDetails();
  }

  /**
   * If descriptive metadata are available, they SHOULD be included in sub-folder
   * descriptive.
   *
   * @param structureValidatorState
   *          the contextual {@link StructureValidatorState}
   * @return {@link ReporterDetails}
   * @throws IOException
   *           if some I/O error occurs
   */
  protected ReporterDetails validateCSIPSTR7(final StructureValidatorState structureValidatorState) throws IOException {
    if (structureValidatorState.isZipFileFlag()) {
      if (!structureValidatorState.getZipManager().checkIfExistsFolderInside(structureValidatorState.getIpPath(),
        "metadata/descriptive")) {
        return new ReporterDetails(getCSIPVersion(),
          "If descriptive metadata are available should include inside metadata/descriptive", false, false);
      }
    } else {
      if (!structureValidatorState.getFolderManager().checkIfExistsFolderInside(structureValidatorState.getIpPath(),
        "metadata", "descriptive")) {
        return new ReporterDetails(getCSIPVersion(),
          "If descriptive metadata are available should include inside metadata/descriptive", false, false);
      }
    }
    return new ReporterDetails();
  }

  /**
   * If any other metadata are available, they MAY be included in separate
   * sub-folders, for example an additional folder named other.
   *
   * @param structureValidatorState
   *          the contextual {@link StructureValidatorState}
   * @return {@link ReporterDetails}
   * @throws IOException
   *           if some I/O error occurs
   */
  protected ReporterDetails validateCSIPSTR8(final StructureValidatorState structureValidatorState) throws IOException {
    if (structureValidatorState.isZipFileFlag()) {
      if (!structureValidatorState.getZipManager().checkIfExistsFolderInside(structureValidatorState.getIpPath(),
        "metadata/other")) {
        return new ReporterDetails(getCSIPVersion(), "If any other metadata are available, they MAY be included in "
          + "separate sub-folders, for example an additional folder named other.", false, false);
      }
    } else {
      if (!structureValidatorState.getFolderManager().checkIfExistsFolderInside(structureValidatorState.getIpPath(),
        "metadata", "other")) {
        return new ReporterDetails(getCSIPVersion(),
          "If any other metadata are available, they MAY be included in separate sub-folders, "
            + "for example an additional folder named other.",
          false, false);
      }
    }
    return new ReporterDetails();
  }

  /**
   * The Information Package folder SHOULD include a folder named representations.
   *
   * @param structureValidatorState
   *          the contextual {@link StructureValidatorState}
   * @return {@link ReporterDetails}
   * @throws IOException
   *           if some I/O error occurs
   */
  protected ReporterDetails validateCSIPSTR9(final StructureValidatorState structureValidatorState) throws IOException {
    if (structureValidatorState.isZipFileFlag()) {
      if (!structureValidatorState.getZipManager().checkIfExistsFolderInRoot(structureValidatorState.getIpPath(),
        "representations")) {
        return new ReporterDetails(getCSIPVersion(),
          "The Information Package folder SHOULD include a folder named representations.", false, false);
      }
    } else {
      if (!structureValidatorState.getFolderManager().checkIfExistsFolderInRoot(structureValidatorState.getIpPath(),
        "representations")) {
        return new ReporterDetails(getCSIPVersion(),
          "The Information Package folder SHOULD include a folder named representations.", false, false);
      }
    }
    return new ReporterDetails();
  }

  /**
   * The representations folder SHOULD include a sub-folder for each individual
   * representation (i.e. the “representation folder”). Each representation folder
   * should have a string name that is unique within the package scope. For
   * example the name of the representation and/or its creation date might be good
   * candidates as a representation sub-folder name.
   *
   * @param structureValidatorState
   *          the contextual {@link StructureValidatorState}
   * @return {@link ReporterDetails}
   * @throws IOException
   *           if some I/O error occurs
   */
  protected ReporterDetails validateCSIPSTR10(final StructureValidatorState structureValidatorState)
    throws IOException {
    List<String> representationsFoldersNames;
    if (structureValidatorState.isZipFileFlag()) {
      representationsFoldersNames = structureValidatorState.getZipManager()
        .getRepresentationsFoldersNames(structureValidatorState.getIpPath());
      if (!representationsFoldersNames.isEmpty()) {
        if (structureValidatorState.getZipManager()
          .countFilesInsideRepresentations(structureValidatorState.getIpPath()) != 0) {
          return new ReporterDetails(getCSIPVersion(),
            "The representations folder SHOULD include a " + "sub-folder for each individual representation.", false,
            false);
        }
      } else {
        return new ReporterDetails(getCSIPVersion(),
          "The representations folder SHOULD include a " + "sub-folder for each individual representation.", false,
          true);
      }
    } else {
      representationsFoldersNames = structureValidatorState.getFolderManager()
        .getRepresentationsFoldersNames(structureValidatorState.getIpPath());
      if (!representationsFoldersNames.isEmpty()) {
        if (structureValidatorState.getFolderManager()
          .countFilesInsideRepresentations(structureValidatorState.getIpPath()) != 0) {
          return new ReporterDetails(getCSIPVersion(),
            "The representations folder SHOULD " + "include a sub-folder for each individual representation.", false,
            false);
        }
      } else {
        return new ReporterDetails(getCSIPVersion(),
          "The representations folder SHOULD include a " + "sub-folder for each individual representation.", false,
          true);
      }
    }
    return new ReporterDetails();
  }

  /**
   * The representation folder SHOULD include a sub-folder named data which MAY
   * include all data constituting the representation.
   *
   * @param structureValidatorState
   *          the contextual {@link StructureValidatorState}
   * @return the {@link ReporterDetails}
   * @throws IOException
   *           if some I/O error occurs
   */
  protected ReporterDetails validateCSIPSTR11(final StructureValidatorState structureValidatorState)
    throws IOException {
    if (structureValidatorState.isZipFileFlag()) {
      if (!structureValidatorState.getZipManager().checkIfExistsFolderInRoot(structureValidatorState.getIpPath(),
        "representations")) {
        return new ReporterDetails(getCSIPVersion(), "The representation folder SHOULD include a sub-folder named "
          + "data which MAY include all data constituting the representation", false, true);
      }
      else if (!structureValidatorState.getZipManager()
        .checkIfExistsFolderInsideRepresentation(structureValidatorState.getIpPath(), "data")) {
        return new ReporterDetails(getCSIPVersion(), "The representation folder SHOULD include a sub-folder named "
          + "data which MAY include all data constituting the representation", false, false);
      }

    } else {
      if (!structureValidatorState.getFolderManager()
        .checkIfExistsFolderInRoot(structureValidatorState.getIpPath(), "representations")) {
        return new ReporterDetails(getCSIPVersion(), "The representation folder SHOULD include a sub-folder named data "
          + "which MAY include all data constituting the representation", false, true);
      }
      else if (!structureValidatorState.getFolderManager()
        .checkIfExistsFolderInsideRepresentation(structureValidatorState.getIpPath(), "data")) {
        return new ReporterDetails(getCSIPVersion(), "The representation folder SHOULD include a sub-folder named data "
          + "which MAY include all data constituting the representation", false, false);
      }
    }
    return new ReporterDetails();
  }

  /**
   * The representation folder SHOULD include a metadata file named METS.xml which
   * includes information about the identity and structure of the representation
   * and its components. The recommended best practice is to always have a
   * METS.xml in the representation folder.
   *
   * @param structureValidatorState
   *          the contextual {@link StructureValidatorState}
   * @return {@link ReporterDetails}
   * @throws IOException
   *           if some I/O error occurs
   */
  protected ReporterDetails validateCSIPSTR12(final StructureValidatorState structureValidatorState)
    throws IOException {
    if (structureValidatorState.isZipFileFlag()) {
      if (!structureValidatorState.getZipManager().checkIfExistsSubMets(structureValidatorState.getIpPath())) {
        return new ReporterDetails(getCSIPVersion(),
          "The recommended best practice is to always " + "have a METS.xml in the representation folder.", false,
          false);
      }
    } else {
      if (!structureValidatorState.getFolderManager().checkIfExistsSubMets(structureValidatorState.getIpPath())) {
        return new ReporterDetails(getCSIPVersion(),
          "The recommended best practice is to always " + "have a METS.xml in the representation folder.", false,
          false);
      }
    }
    return new ReporterDetails();
  }

  /**
   * The representation folder SHOULD include a sub-folder named metadata which
   * MAY include all metadata about the specific representation.
   *
   * @param structureValidatorState
   *          the contextual {@link StructureValidatorState}
   * @return the {@link ReporterDetails}
   * @throws IOException
   *           if some I/O error occurs
   */
  protected ReporterDetails validateCSIPSTR13(final StructureValidatorState structureValidatorState)
    throws IOException {
    if (structureValidatorState.isZipFileFlag()) {
      if (!structureValidatorState.getZipManager().checkIfExistsFolderInRoot(structureValidatorState.getIpPath(),
        "representations")) {
        return new ReporterDetails(getCSIPVersion(), "The representation folder SHOULD include a sub-folder named "
          + "metadata which MAY include all metadata about the specific representation.", false, true);
      }
      else if (!structureValidatorState.getZipManager()
        .checkIfExistsFolderInsideRepresentation(structureValidatorState.getIpPath(), "metadata")) {
        return new ReporterDetails(getCSIPVersion(), "The representation folder SHOULD include a sub-folder named "
          + "metadata which MAY include all metadata about the specific representation.", false, false);
      }
    } else {
      if (!structureValidatorState.getFolderManager()
        .checkIfExistsFolderInRoot(structureValidatorState.getIpPath(), "representations")) {
        return new ReporterDetails(getCSIPVersion(), "The representation folder SHOULD include a sub-folder named "
          + "metadata which MAY include all metadata about the specific representation.", false, true);
      }
      else if (!structureValidatorState.getFolderManager()
        .checkIfExistsFolderInsideRepresentation(structureValidatorState.getIpPath(), "metadata")) {
        return new ReporterDetails(getCSIPVersion(), "The representation folder SHOULD include a sub-folder named "
          + "metadata which MAY include all metadata about the specific representation.", false, false);
      }
    }
    return new ReporterDetails();
  }

  /**
   * The Information Package MAY be extended with additional sub-folders.
   *
   * @param structureValidatorState
   *          the contextual {@link StructureValidatorState}
   * @return the {@link ReporterDetails}
   * @throws IOException
   *           if some I/O error occurs
   */
  protected ReporterDetails validateCSIPSTR14(final StructureValidatorState structureValidatorState)
    throws IOException {
    List<String> additionalFolders;
    if (structureValidatorState.isZipFileFlag()) {
      additionalFolders = structureValidatorState.getZipManager()
        .verifyAdditionalFoldersInRoot(structureValidatorState.getIpPath());
    } else {
      additionalFolders = structureValidatorState.getFolderManager()
        .verifyAdditionalFoldersInRoot(structureValidatorState.getIpPath());
    }
    if (!additionalFolders.isEmpty()) {
      StringBuilder folders = new StringBuilder();
      folders.append("An additional sub-folder was found:");
      int i = 0;
      for (String folder : additionalFolders) {
        if (i == additionalFolders.size() - 1) {
          folders.append(" ").append(folder).append(".");
        } else {
          folders.append(" ").append(folder).append(" ");
        }
      }
      return new ReporterDetails(getCSIPVersion(), folders.toString(), false, false);
    }
    return new ReporterDetails();
  }

  /**
   * We recommend including all XML schema documents for any structured metadata
   * within package. These schema documents SHOULD be placed in a sub-folder
   * called schemas within the Information Package root folder and/or the
   * representation folder.
   *
   * @param structureValidatorState
   *          the contextual {@link StructureValidatorState}
   * @return the {@link ReporterDetails}
   * @throws IOException
   *           if some I/O error occurs
   */
  protected ReporterDetails validateCSIPSTR15(final StructureValidatorState structureValidatorState)
    throws IOException {
    if (structureValidatorState.isZipFileFlag()) {
      if (!structureValidatorState.getZipManager().checkIfExistsFolderInRoot(structureValidatorState.getIpPath(),
        "schemas")) {
        String message = checkIfExistsZipFolderInRepresentations(structureValidatorState, "schemas");
        if (message.length() > 0) {
          return new ReporterDetails(getCSIPVersion(), message, false, false);
        }
      } else {
        return new ReporterDetails();
      }
    } else {
      if (!structureValidatorState.getFolderManager().checkIfExistsFolderInRoot(structureValidatorState.getIpPath(),
        "schemas")) {
        String message = checkIfExistsFolderInRepresentations(structureValidatorState, "schemas");
        if (message.length() > 0) {
          return new ReporterDetails(getCSIPVersion(), message, false, false);
        }
      } else {
        return new ReporterDetails();
      }
    }
    return new ReporterDetails();
  }

  /**
   * We recommend including any supplementary documentation for the package or a
   * specific representation within the package. Supplementary documentation
   * SHOULD be placed in a sub-folder called documentation within the Information
   * Package root folder and/or the representation folder.
   *
   * @param structureValidatorState
   *          the contextual {@link StructureValidatorState}
   * @return the {@link ReporterDetails}
   * @throws IOException
   *           if some I/O error occurs
   */
  protected ReporterDetails validateCSIPSTR16(final StructureValidatorState structureValidatorState)
    throws IOException {
    if (structureValidatorState.isZipFileFlag()) {
      if (!structureValidatorState.getZipManager().checkIfExistsFolderInRoot(structureValidatorState.getIpPath(),
        "documentation")) {
        String message = checkIfExistsZipFolderInRepresentations(structureValidatorState, "documentation");
        if (message.length() > 0) {
          return new ReporterDetails(getCSIPVersion(), message, false, false);
        }
      } else {
        return new ReporterDetails();
      }
    } else {
      if (!structureValidatorState.getFolderManager().checkIfExistsFolderInRoot(structureValidatorState.getIpPath(),
        "documentation")) {
        String message = checkIfExistsFolderInRepresentations(structureValidatorState, "documentation");
        if (message.length() > 0) {
          return new ReporterDetails(getCSIPVersion(), message, false, false);
        }
      } else {
        return new ReporterDetails();
      }
    }
    return new ReporterDetails();
  }

  /**
   * Check if the IP is a ZIP file or not.
   *
   * @param ipPath
   *          the {@link Path} to the IP
   * @param zipMagicNumber
   * @param moduleName
   * @return if the IP is a ZIP file
   */
  protected boolean isZipFile(Path ipPath, byte[] zipMagicNumber, String moduleName) {
    boolean isZip = true;

    byte[] buffer = new byte[zipMagicNumber.length];
    try (RandomAccessFile raf = new RandomAccessFile(ipPath.toFile(), "r")) {
      raf.readFully(buffer);
      for (int i = 0; i < zipMagicNumber.length; i++) {
        if (buffer[i] != zipMagicNumber[i]) {
          isZip = false;
          break;
        }
      }
    } catch (IOException e) {
      LOGGER.debug("Failed to validate {} Failed to validate due to an exception on CSIP1 " + moduleName,
        "Please check the file submitted", e);
      isZip = false;
    }

    return isZip;
  }

  /**
   * Check if exists some folder inside Representations in ZIP file Packages.
   *
   * @param structureValidatorState
   *          the contextual {@link StructureValidatorState}
   * @param folder
   *          the folder to find
   * @return a message
   * @throws IOException
   *           if some I/O error occurs
   */
  protected String checkIfExistsZipFolderInRepresentations(final StructureValidatorState structureValidatorState,
    String folder) throws IOException {
    List<String> representationFoldersNames = structureValidatorState.getZipManager()
      .getRepresentationsFoldersNames(structureValidatorState.getIpPath());
    StringBuilder message = new StringBuilder();
    int i = 0;
    for (String representation : representationFoldersNames) {
      if (!structureValidatorState.getZipManager()
        .checkIfExistsFolderRepresentation(structureValidatorState.getIpPath(), folder, representation)) {
        message.append("There is no ").append(folder).append(" folder in the representation folder ")
          .append(representation);
        if (i != representationFoldersNames.size() - 1) {
          message.append(", ");
        } else {
          message.append(".");
        }
        i++;
      }
    }
    return message.toString();
  }

  /**
   * Check if exists some folder inside Representations in folders.
   *
   * @param structureValidatorState
   *          the contextual {@link StructureValidatorState}
   * @param folder
   *          the folder to find
   * @return a message
   */
  protected String checkIfExistsFolderInRepresentations(final StructureValidatorState structureValidatorState,
    String folder) {
    List<String> representationFoldersNames = structureValidatorState.getFolderManager()
      .getRepresentationsFoldersNames(structureValidatorState.getIpPath());
    StringBuilder message = new StringBuilder();
    int i = 0;
    for (String representation : representationFoldersNames) {
      if (!structureValidatorState.getFolderManager()
        .checkIfExistsFolderRepresentation(structureValidatorState.getIpPath(), "schemas", representation)) {
        message.append("There is no ").append(folder).append(" folder in the representation folder ")
          .append(structureValidatorState.getIpPath().resolve("representations").resolve(representation).toString()
            .substring(structureValidatorState.getIpPath().getParent().toString().length()));
        if (i != representationFoldersNames.size() - 1) {
          message.append(", ");
        } else {
          message.append(".");
        }
        i++;
      }
    }
    return message.toString();
  }

}
