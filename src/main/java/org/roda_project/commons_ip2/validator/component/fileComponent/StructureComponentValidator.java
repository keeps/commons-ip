package org.roda_project.commons_ip2.validator.component.fileComponent;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.roda_project.commons_ip2.validator.component.ValidatorComponentImpl;
import org.roda_project.commons_ip2.validator.constants.Constants;
import org.roda_project.commons_ip2.validator.constants.ConstantsCSIPspec;
import org.roda_project.commons_ip2.validator.reporter.ReporterDetails;
import org.roda_project.commons_ip2.validator.utils.Message;
import org.roda_project.commons_ip2.validator.utils.ResultsUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author João Gomes <jgomes@keep.pt>
 */
public class StructureComponentValidator extends ValidatorComponentImpl {
  private static final Logger LOGGER = LoggerFactory.getLogger(StructureComponentValidator.class);

  private final String moduleName;
  private final Path path;

  public StructureComponentValidator(Path path) {
    this.moduleName = Constants.CSIP_MODULE_NAME_0;
    this.path = path;
  }

  private static final byte[] ZIP_MAGIC_NUMBER = {'P', 'K', 0x3, 0x4};

  @Override
  public Map<String, ReporterDetails> validate() throws IOException {
    Map<String, ReporterDetails> results = new HashMap<>();

    /* CSIPSTR1 */
    notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIPSTR1_ID);
    ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIPSTR1_ID,
      validateCSIPSTR1().setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION));
    if (ResultsUtils.isResultValid(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIPSTR1_ID)) {

      /* CSIPSTR2 */
      ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIPSTR2_ID,
        new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
          "Requirement check was skipped as it will be checked under CSIP1", true, true));

      /* CSIPSTR3 */
      notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIPSTR3_ID);
      ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIPSTR3_ID,
        validateCSIPSTR3().setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION));

      /* CSIPSTR4 */
      notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIPSTR4_ID);
      ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIPSTR4_ID,
        validateCSIPSTR4().setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION));

      /* CSIPSTR5 */
      notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIPSTR5_ID);
      ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIPSTR5_ID,
        validateCSIPSTR5().setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION));

      /* CSIPSTR6 */
      notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIPSTR6_ID);
      ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIPSTR6_ID,
        validateCSIPSTR6().setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION));

      /* CSIPSTR7 */
      notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIPSTR7_ID);
      ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIPSTR7_ID,
        validateCSIPSTR7().setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION));

      /* CSIPSTR8 */
      notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIPSTR8_ID);
      ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIPSTR8_ID,
        validateCSIPSTR8().setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION));

      /* CSIPSTR9 */
      notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIPSTR9_ID);
      ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIPSTR9_ID,
        validateCSIPSTR9().setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION));

      /* CSIPSTR10 */
      notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIPSTR10_ID);
      ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIPSTR10_ID,
        validateCSIPSTR10().setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION));

      /* CSIPSTR11 */
      notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIPSTR11_ID);
      ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIPSTR11_ID,
        validateCSIPSTR11().setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION));

      /* CSIPSTR12 */
      notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIPSTR12_ID);
      ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIPSTR12_ID,
        validateCSIPSTR12().setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION));

      /* CSIPSTR13 */
      notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIPSTR13_ID);
      ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIPSTR13_ID,
        validateCSIPSTR13().setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION));

      /* CSIPSTR14 */
      notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIPSTR14_ID);
      ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIPSTR14_ID,
        validateCSIPSTR14().setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION));

      /* CSIPSTR15 */
      notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIPSTR15_ID);
      ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIPSTR15_ID,
        validateCSIPSTR15().setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION));

      /* CSIPSTR16 */
      notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIPSTR16_ID);
      ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIPSTR16_ID,
        validateCSIPSTR16().setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION));

    } else {
      String message;
      if (isZipFileFlag()) {
        message = "SKIPPED because must unpack to a single root folder";
      } else {
        message = "Root must be a single directory";
      }

      ResultsUtils.addResults(results,
        new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, message, true, true),
        ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIPSTR2_ID,
        ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIPSTR3_ID,
        ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIPSTR4_ID,
        ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIPSTR5_ID,
        ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIPSTR6_ID,
        ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIPSTR7_ID,
        ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIPSTR8_ID,
        ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIPSTR9_ID,
        ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIPSTR10_ID,
        ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIPSTR11_ID,
        ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIPSTR12_ID,
        ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIPSTR13_ID,
        ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIPSTR14_ID,
        ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIPSTR15_ID,
        ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIPSTR16_ID);
    }
    notifyObserversFinishModule(moduleName);

    return results;
  }

  /*
   * Any Information Package MUST be included within a single physical root folder
   * (known as the “Information Package root folder”). For packages presented in
   * an archive format, see CSIPSTR3, the archive MUST unpack to a single root
   * folder.
   */
  private ReporterDetails validateCSIPSTR1() throws IOException {
    ReporterDetails details = new ReporterDetails();
    if (Files.exists(path)) {
      if (isZipFile()) {
        setZipFileFlag(true);
        if (!zipManager.checkSingleRootFolder(path)) {
          details.setValid(false);
          details.addIssue("MUST unpack to a single folder");
        }
      } else {
        if (!Files.isDirectory(path)) {
          details.setValid(false);
          details.addIssue(Message.createErrorMessage("The SIP path (%1$s) does not exist or is not a directory or is not an archived file format",path.toString(),false));
        }
      }
    } else {
      details.setValid(false);
      details.addIssue(Message.createErrorMessage("The SIP path (%1$s) does not exist or is not a directory or is not an archived file format",path.toString(),false));
    }
    return details;
  }

  /*
   * The Information Package root folder SHOULD be named with the ID or name of
   * the Information Package, that is the value of the package METS.xml’s root
   * <mets> element’s @OBJID attribute.
   */
  private ReporterDetails validateCSIPSTR2() {
    return new ReporterDetails();
  }

  /*
   * The Information Package root folder MAY be compressed (for example by using
   * TAR or ZIP). Which specific compression format to use needs to be stated in
   * the Submission Agreement.
   */
  private ReporterDetails validateCSIPSTR3() throws IOException {
    if (isZipFileFlag()) {
      if (getEARKSIPpath().toString().contains("zip")) {
        return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
          "Information package is compressed in ZIP format", true, false);
      } else {
        return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
          "Information package is compressed in TAR format", true, false);
      }
    } else {
      return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
        "The Information Package root folder MAY be compressed.", false, false);
    }
  }

  /*
   * The Information Package root folder MUST include a file named METS.xml. This
   * file MUST contain metadata that identifies the package, provides a high-level
   * package description, and describes its structure, including pointers to
   * constituent representations.
   */
  private ReporterDetails validateCSIPSTR4() throws IOException {
    ReporterDetails details = new ReporterDetails();
    if (isZipFileFlag()) {
      if (!zipManager.checkIfExistsRootMetsFile(path)) {
        details.setValid(false);
        details.addIssue("METS.xml in root folder not found; Please verify name of File or is existence.");
      }
    } else {
      if (!folderManager.checkIfExistsRootMetsFile(path)) {
        details.setValid(false);
        details.addIssue("METS.xml in root folder not found; Please verify name of File or is existence.");
      }
    }
    return details;
  }

  /*
   * The Information Package root folder SHOULD include a folder named metadata,
   * which SHOULD include metadata relevant to the whole package.
   */
  private ReporterDetails validateCSIPSTR5() throws IOException {
    if (isZipFileFlag()) {
      if (!zipManager.checkIfExistsFolderInRoot(path, "metadata")) {
        return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
          "Include Metadata relevant to the whole package in folder metadata", false, false);
      }
    } else {
      if (!folderManager.checkIfExistsFolderInRoot(path, "metadata")) {
        return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
          "Include Metadata relevant to the whole package in folder metadata", false, false);
      }
    }
    return new ReporterDetails();
  }

  /*
   * If preservation metadata are available, they SHOULD be included in sub-folder
   * preservation.
   */
  private ReporterDetails validateCSIPSTR6() throws IOException {
    if (isZipFileFlag()) {
      if (!zipManager.checkIfExistsFolderInside(path, "metadata/preservation")) {
        if (zipManager.verifyIfExistsFilesInFolder(path, ".*/metadata/")) {
          return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
            "If preservation metadata are available should include inside metadata/preservation", false, false);
        }
      }
    } else {
      if (!folderManager.checkIfExistsFolderInside(path, "metadata", "preservation")) {
        if (folderManager.verifyIfExistsFilesInFolder(path, "metadata")) {
          return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
            "If preservation metadata are available should include inside metadata/preservation", false, false);
        }
      }
    }
    return new ReporterDetails();
  }

  /*
   * If descriptive metadata are available, they SHOULD be included in sub-folder
   * descriptive.
   */
  private ReporterDetails validateCSIPSTR7() throws IOException {
    if (isZipFileFlag()) {
      if (!zipManager.checkIfExistsFolderInside(path, "metadata/descriptive")) {
        if (zipManager.verifyIfExistsFilesInFolder(path, ".*/metadata/")) {
          return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
            "If descriptive metadata are available should include inside metadata/descriptive", false, false);
        }
      }
    } else {
      if (!folderManager.checkIfExistsFolderInside(path, "metadata", "descriptive")) {
        if (folderManager.verifyIfExistsFilesInFolder(path, "metadata")) {
          return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
            "If descriptive metadata are available should include inside metadata/descriptive", false, false);
        }
      }
    }
    return new ReporterDetails();
  }

  /*
   * If any other metadata are available, they MAY be included in separate
   * sub-folders, for example an additional folder named other.
   */
  private ReporterDetails validateCSIPSTR8() throws IOException {
    if (isZipFileFlag()) {
      if (zipManager.verifyIfExistsFilesInFolder(path, "metadata/descriptive")
        || zipManager.verifyIfExistsFilesInFolder(path, "metadata/preservation")) {
        if (zipManager.verifyIfExistsFilesInFolder(path, ".*/metadata/")) {
          return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
            "If any other metadata are available, they MAY be included in separate sub-folders, for example an additional folder named other.",
            false, false);
        }
      }
    } else {
      if (folderManager.checkIfExistsFolderInside(path, "metadata", "descriptive")
        || folderManager.checkIfExistsFolderInside(path, "metadata", "preservation")) {
        if (folderManager.verifyIfExistsFilesInFolder(path, "metadata")) {
          return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
            "If any other metadata are available, they MAY be included in separate sub-folders, for example an additional folder named other.",
            false, false);
        }
      }
    }
    return new ReporterDetails();
  }

  /*
   * The Information Package folder SHOULD include a folder named representations.
   */
  private ReporterDetails validateCSIPSTR9() throws IOException {
    if (isZipFileFlag()) {
      if (!zipManager.checkIfExistsFolderInRoot(path, "representations")) {
        return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
          "The Information Package folder SHOULD include a folder named representations.", false, false);
      }
    } else {
      if (!folderManager.checkIfExistsFolderInRoot(path, "representations")) {
        return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
          "The Information Package folder SHOULD include a folder named representations.", false, false);
      }
    }
    return new ReporterDetails();
  }

  /*
   * The representations folder SHOULD include a sub-folder for each individual
   * representation (i.e. the “representation folder”). Each representation folder
   * should have a string name that is unique within the package scope. For
   * example the name of the representation and/or its creation date might be good
   * candidates as a representation sub-folder name.
   */
  private ReporterDetails validateCSIPSTR10() throws IOException {
    List<String> representationsFoldersNames;
    List<String> tmp;
    if (isZipFileFlag()) {
      representationsFoldersNames = zipManager.getRepresentationsFoldersNames(getEARKSIPpath());
      if (!representationsFoldersNames.isEmpty()) {
        if (zipManager.countFilesInsideRepresentations(getEARKSIPpath()) != 0) {
          return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
            "The representations folder SHOULD include a sub-folder for each individual representation.", false, false);
        } else {
          tmp = representationsFoldersNames.stream().distinct().collect(Collectors.toList());
          if (representationsFoldersNames.size() != tmp.size()) {
            return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
              "Names of representatios folders must be unique", false, false);
          }
        }
      } else {
        return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
          "The representations folder SHOULD include a sub-folder for each individual representation.", false, false);
      }
    } else {
      representationsFoldersNames = folderManager.getRepresentationsFoldersNames(getEARKSIPpath());
      if (!representationsFoldersNames.isEmpty()) {
        if (folderManager.countFilesInsideRepresentations(getEARKSIPpath()) != 0) {
          return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
            "The representations folder SHOULD include a sub-folder for each individual representation.", false, false);
        } else {
          tmp = representationsFoldersNames.stream().distinct().collect(Collectors.toList());
          if (representationsFoldersNames.size() != tmp.size()) {
            return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
              "Names of representatios folders must be unique", false, false);
          }
        }
      } else {
        return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
          "The representations folder SHOULD include a sub-folder for each individual representation.", false, false);
      }
    }
    return new ReporterDetails();
  }

  /*
   * The representation folder SHOULD include a sub-folder named data which MAY
   * include all data constituting the representation.
   */
  private ReporterDetails validateCSIPSTR11() throws IOException {
    if (isZipFileFlag()) {
      if (!zipManager.checkIfExistsFolderInsideRepresentation(path, "data")) {
        if (zipManager.checkIfExistsFilesInsideRepresentationFolder(path)) {
          return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
            "The representation folder SHOULD include a sub-folder named data which MAY include all data constituting the representation",
            false, false);
        }
      }
    } else {
      if (!folderManager.checkIfExistsFolderInsideRepresentation(path, "data")) {
        if (folderManager.checkIfExistsFilesInsideRepresentationFolder(path)) {
          return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
            "The representation folder SHOULD include a sub-folder named data which MAY include all data constituting the representation",
            false, false);
        }
      }
    }
    return new ReporterDetails();
  }

  /*
   * The representation folder SHOULD include a metadata file named METS.xml which
   * includes information about the identity and structure of the representation
   * and its components. The recommended best practice is to always have a
   * METS.xml in the representation folder.
   */
  private ReporterDetails validateCSIPSTR12() throws IOException {
    if (isZipFileFlag()) {
      if (!zipManager.checkIfExistsSubMets(path)) {
        return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
          "The recommended best practice is to always have a METS.xml in the representation folder.", false, false);
      }
    } else {
      if (!folderManager.checkIfExistsSubMets(path)) {
        return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
          "The recommended best practice is to always have a METS.xml in the representation folder.", false, false);
      }
    }
    return new ReporterDetails();
  }

  /*
   * The representation folder SHOULD include a sub-folder named metadata which
   * MAY include all metadata about the specific representation.
   */
  private ReporterDetails validateCSIPSTR13() throws IOException {
    if (isZipFileFlag()) {
      if (!zipManager.checkIfExistsFolderInsideRepresentation(path, "metadata")) {
        if (zipManager.checkIfExistsFilesInsideRepresentationFolder(path)) {
          return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
            "The representation folder SHOULD include a sub-folder named metadata which MAY include all metadata about the specific representation.",
            false, false);
        }
      }
    } else {
      if (!folderManager.checkIfExistsFolderInsideRepresentation(path, "metadata")) {
        if (folderManager.checkIfExistsFilesInsideRepresentationFolder(path)) {
          return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
            "The representation folder SHOULD include a sub-folder named metadata which MAY include all metadata about the specific representation.",
            false, false);
        }
      }
    }
    return new ReporterDetails();
  }

  /*
   * The Information Package MAY be extended with additional sub-folders.
   */
  private ReporterDetails validateCSIPSTR14() throws IOException {
    List<String> additionalFolders;
    if (isZipFileFlag()) {
      additionalFolders = zipManager.verifyAdditionalFoldersInRoot(getEARKSIPpath());
    } else {
      additionalFolders = folderManager.verifyAdditionalFoldersInRoot(getEARKSIPpath());
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
      return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, folders.toString(), false, false);
    }
    return new ReporterDetails();
  }

  /*
   * We recommend including all XML schema documents for any structured metadata
   * within package. These schema documents SHOULD be placed in a sub-folder
   * called schemas within the Information Package root folder and/or the
   * representation folder.
   */
  private ReporterDetails validateCSIPSTR15() throws IOException {
    if (isZipFileFlag()) {
      if (!zipManager.checkIfExistsFolderInRoot(path, "schemas")) {
        if (!zipManager.checkIfExistsFolderInsideRepresentation(path, "schemas")) {
          if (zipManager.checkIfExistsFilesInsideRepresentationFolder(path)) {
            return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
              "We recommend including all XML schema documents for any structured metadata within package. These schema documents SHOULD be placed in a sub-folder called schemas within the Information Package root folder and/or the representation folder.",
              false, false);
          }
        }
      }
    } else {
      if (!folderManager.checkIfExistsFolderInRoot(path, "schemas")) {
        if (!folderManager.checkIfExistsFolderInsideRepresentation(path, "schemas")) {
          if (folderManager.checkIfExistsFilesInsideRepresentationFolder(path)) {
            return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
              "We recommend including all XML schema documents for any structured metadata within package. These schema documents SHOULD be placed in a sub-folder called schemas within the Information Package root folder and/or the representation folder.",
              false, false);
          }
        }
      }
    }
    return new ReporterDetails();
  }

  /*
   * We recommend including any supplementary documentation for the package or a
   * specific representation within the package. Supplementary documentation
   * SHOULD be placed in a sub-folder called documentation within the Information
   * Package root folder and/or the representation folder.
   */
  private ReporterDetails validateCSIPSTR16() throws IOException {
    if (isZipFileFlag()) {
      if (!zipManager.checkIfExistsFolderInRoot(path, "documentation")) {
        if (!zipManager.checkIfExistsFolderInsideRepresentation(path, "documentation")) {
          if (zipManager.checkIfExistsFilesInsideRepresentationFolder(path)) {
            return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
              "We recommend including any supplementary documentation for the package or a specific representation within the package. Supplementary documentation SHOULD be placed in a sub-folder called documentation within the Information Package root folder and/or the representation folder.",
              false, false);
          }
        }
      }
    } else {
      if (!folderManager.checkIfExistsFolderInRoot(path, "documentation")) {
        if (!folderManager.checkIfExistsFolderInsideRepresentation(path, "documentation")) {
          if (folderManager.checkIfExistsFilesInsideRepresentationFolder(path)) {
            return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
              "We recommend including any supplementary documentation for the package or a specific representation within the package. Supplementary documentation SHOULD be placed in a sub-folder called documentation within the Information Package root folder and/or the representation folder.",
              false, false);
          }
        }
      }
    }
    return new ReporterDetails();
  }

  private boolean isZipFile() {
    boolean isZip = true;

    byte[] buffer = new byte[ZIP_MAGIC_NUMBER.length];
    try (RandomAccessFile raf = new RandomAccessFile(path.toFile(), "r")) {
      raf.readFully(buffer);
      for (int i = 0; i < ZIP_MAGIC_NUMBER.length; i++) {
        if (buffer[i] != ZIP_MAGIC_NUMBER[i]) {
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
}
