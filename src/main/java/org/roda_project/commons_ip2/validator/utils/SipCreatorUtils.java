package org.roda_project.commons_ip2.validator.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.security.MessageDigest;

import org.roda_project.commons_ip.utils.IPException;
import org.roda_project.commons_ip2.model.IPContentInformationType;
import org.roda_project.commons_ip2.model.IPContentType;
import org.roda_project.commons_ip2.model.IPDescriptiveMetadata;
import org.roda_project.commons_ip2.model.IPFile;
import org.roda_project.commons_ip2.model.IPRepresentation;
import org.roda_project.commons_ip2.model.MetadataType;
import org.roda_project.commons_ip2.model.SIP;
import org.roda_project.commons_ip2.model.impl.eark.EARKSIP;
import org.roda_project.commons_ip2.utils.Utils;

/**
 * {@author João Gomes <jgomes@keep.pt>}.
 */
public final class SipCreatorUtils {

  /**
   * Constant with "_" char.
   */
  private static final String UNDERSCORE = "_";
  /**
   * Constant with regex "." char.
   */
  private static final String DOT_REGEX = "\\.";

  private SipCreatorUtils() {
    // do nothing
  }

  /**
   * Validates the metadata options.
   * 
   * @param metadataFile
   *          the metadata file arg.
   * @param metadataType
   *          the metadata type arg.
   * @param metadataVersion
   *          the metadata version arg.
   * @return a flag if is valid or not.
   */
  public static boolean validateMetadataOptions(final String[] metadataInfoArray) {
    String metadataFile = (metadataInfoArray.length >= 1) ? metadataInfoArray[0] : null;
    String metadataType = (metadataInfoArray.length >= 2) ? metadataInfoArray[1] : null;
    String metadataVersion = (metadataInfoArray.length == 3) ? metadataInfoArray[2] : null;
    if (metadataFile == null && metadataType == null && metadataVersion == null) {
      return true;
    } else {
      return metadataFile != null && metadataType != null;
    }
  }

  /**
   * Validate representation options.
   * 
   * @param representationData
   *          the paths to the representation data.
   * @param representationType
   *          the representation type.
   * @param representationID
   *          the representation id.
   * @return flag if is valid or not.
   */
  public static boolean validateRepresentationOptions(final String[] representationData,
    final String[] representationInfo) {
    String representationType = (representationInfo.length >= 2) ? representationInfo[1] : null;
    String representationID = (representationInfo.length == 3) ? representationInfo[2] : null;

    return representationData != null || (representationType == null && representationID == null);
  }

  /**
   * Check if the path to the metadata files exists.
   * 
   * @param metadata
   *          the path to the metadata file.
   * @return if exists or not.
   */
  public static boolean validateMetadataPath(final String[] metadata) {
    if (metadata != null) {
      for(String metadataInfo : metadata){
        String metadataFile = metadataInfo.split(";")[0];
        if(!Files.exists(Paths.get(metadataFile))){
          return false;
        }
      }
    }
    return true;
  }

  /**
   * Check if the paths representation data exists.
   *
   * @param representation
   *          the paths to the representation data files or folders.
   * @return if exists all the paths or not.
   */
  public static boolean validateRepresentationPaths(final String[] representation) {
    if (representation != null) {
      for(String representationInfo : representation){
        String representationFilesList = representationInfo.split(";")[0];
        for(String representationFile : representationFilesList.split(",")){
          if (!Files.exists(Paths.get(representationFile))) {
            return false;
          }
        }
      }
    }
    return true;
  }

  /**
   * Check if the paths to documentation exists.
   *
   * @param documentationPaths
   *          the paths to the documentation files or folders.
   * @return if exists all the paths or not.
   */
  public static boolean validateDocumentationPaths(final String[] documentationPaths) {
    if (documentationPaths != null) {
      for (String doc : documentationPaths) {
        if (!Files.exists(Paths.get(doc))) {
          return false;
        }
      }
    }
    return true;
  }

  /**
   * Check if the checksum algorithms exist.
   *
   * @param checksumAlg
   *          the checksum algorithm(s).
   * @return if exists all the algorithms or not.
   */
  public static boolean validateChecksumAlg(final String checksumAlg) {
    if (checksumAlg != null) {
      try {
        MessageDigest.getInstance(checksumAlg);
      } catch (NoSuchAlgorithmException e) {
        return false;
      }
    }
    return true;
  }


  /**
   * Create the EARK2 SIP with the args passed to the CLI.
   *
   * @param metadata           the path to the metadata file, its type and version.
   * @param representation     the paths to the representation data files or folders, the type of content in representation and the representation id.
   * @param sipID              the SIP id.
   * @param ancestors          the ancestors of the SIP.
   * @param documentation      the paths to the documentation files or folders.
   * @param softwareVersion    the software version.
   * @param path               the path to save the SIP.
   * @param submitterAgentName the name of the submitter agent.
   * @param submitterAgentID   the id of the submitter agent
   * @return {@link SIP}.
   * @throws IPException          if some error occur.
   * @throws InterruptedException if some error occur.
   */
  public static Path createEARK2SIP(final String[] metadata,
                                    final String[] representation, final boolean targetOnly,
    final String sipID, final String[] ancestors, final String[] documentation, final String softwareVersion,
    final String path, final String submitterAgentName, final String submitterAgentID, final String checksum)
    throws IPException, InterruptedException {
    String id = sipID;
    if (id == null) {
      id = Utils.generateRandomAndPrefixedUUID();
    }
    else {
      id = sipID.replaceAll("[^a-zA-Z0-9-_\\.]", "_");
    }

    final SIP sip = new EARKSIP(id, IPContentType.getMIXED(), IPContentInformationType.getMIXED());
    String softVersion="DEVELOPMENT-VERSION";

    if (softwareVersion!=null) {
      softVersion=softwareVersion;
    }

    sip.addCreatorSoftwareAgent("RODA Commons IP", softVersion);
    sip.addSubmitterAgent(submitterAgentName, submitterAgentID);
    sip.setDescription("SIP created by commons-ip cli tool");

    if (checksum != null) {
      sip.setChecksum(checksum);
    }

    if (metadata != null) {
      try {
        for(String metadataInfo : metadata){
          String[] metadataInfoArray = metadataInfo.split(";");
          addMetadataToSIP(sip, metadataInfoArray);
        }
      } catch (final IPException e) {
        CLIUtils.printErrors(System.out, "Cannot add metadata to the SIP.");
      }
    }

    if (representation != null) {
      try {
        for(String representationInfo : representation){
          String[] representationInfoArray = representationInfo.split(";");
          String[] representationFiles = representationInfoArray[0].split(",");
          addRepresentationDataToSIP(sip, representationFiles, targetOnly, representationInfoArray);
        }

      } catch (final IPException e) {
        CLIUtils.printErrors(System.out, "Cannot add representation to the SIP.");
      }
    }

    if (documentation != null) {
      try {
        addDocumentationToSIP(sip, documentation);
      } catch (final IOException e) {
        CLIUtils.printErrors(System.out, "Cannot add documentation to the SIP.");
      }
    }

    if (ancestors != null) {
      sip.setAncestors(Arrays.asList(ancestors));
    }

    final Path buildPath;
    if (path != null && Files.exists(Paths.get(path))) {
      buildPath = Paths.get(path);
    } else {
      buildPath = Paths.get(System.getProperty("user.dir"));
    }

    return sip.build(buildPath);
  }

  private static void addMetadataToSIP(final SIP sip, final String[] metadataInfoArray) throws IPException {
    String metadataFile = (metadataInfoArray.length >= 1) ? metadataInfoArray[0] : null;
    String metadataType = (metadataInfoArray.length >= 2) ? metadataInfoArray[1] : null;
    String metadataVersion = (metadataInfoArray.length == 3) ? metadataInfoArray[2] : null;
    MetadataType metadataTypeEnum = null;
    String version = metadataVersion;
    if (metadataType == null && metadataVersion == null) {
      metadataTypeEnum = getMetadataTypeFromMetadataFile(metadataFile);
      version = getMetadataVersionFromMetadataFile(metadataFile);
    } else if (metadataVersion != null && metadataType == null) {
      metadataTypeEnum = getMetadataTypeFromMetadataFile(metadataFile);
    } else if (metadataVersion == null) {
      metadataTypeEnum = new MetadataType(metadataType);
      version = getMetadataVersionFromMetadataFile(metadataFile);
    } else {
      metadataTypeEnum = new MetadataType(metadataType);
    }

    final IPDescriptiveMetadata descriptiveMetadata = new IPDescriptiveMetadata(new IPFile(Paths.get(metadataFile)),
      metadataTypeEnum, version);
    sip.addDescriptiveMetadata(descriptiveMetadata);
  }

  private static MetadataType getMetadataTypeFromMetadataFile(final String metadataFile) {
    final Path metadataPath = Paths.get(metadataFile);
    String filename = metadataPath.getFileName().toString();
    final String metadataTypeValue = filename = filename.split(DOT_REGEX)[0];

    MetadataType metadataType = new MetadataType(metadataTypeValue);

    if (MetadataType.OTHER().equals(metadataType)) {
      final String[] splitedFileName = filename.split(UNDERSCORE);
      if (splitedFileName.length == 2) {
        metadataType = new MetadataType(splitedFileName[0]);
      }
    }
    return metadataType;
  }

  private static String getMetadataVersionFromMetadataFile(final String metadataFile) {
    final Path metadataPath = Paths.get(metadataFile);
    String filename = metadataPath.getFileName().toString();
    filename = filename.split(DOT_REGEX)[0];
    final String[] filenameSplitedWithoutUnderscore = filename.split(UNDERSCORE);
    String metadataVersion = null;
    if (filenameSplitedWithoutUnderscore.length == 2) {
      metadataVersion = filenameSplitedWithoutUnderscore[1];
    }

    return metadataVersion;
  }

  private static void addRepresentationDataToSIP(final SIP sip, final String[] representationData,
    final boolean targetOnly, final String[] representationInfo) throws IPException {
    String representationType = (representationInfo.length == 2) ? representationInfo[1] : null;
    String id = (representationInfo.length == 3) ? representationInfo[2] : null;

    if (id == null) {
      id = "rep1";
    }

    final IPRepresentation representation = new IPRepresentation(id);
    sip.addRepresentation(representation);
    if (representationType != null) {
      final IPContentType ipContentType = getIPContentType(representationType);
      representation.setContentType(ipContentType);
    }

    for (String data : representationData) {
      final Path dataPath = Paths.get(data);
      addFileToRepresentation(representation, targetOnly, dataPath, new ArrayList<>());
    }

  }

  private static void addFileToRepresentation(final IPRepresentation representation, final boolean targetOnly, final Path dataPath,
    final List<String> relativePath) {
    if (Files.isDirectory(dataPath)) {
      final List<String> newRelativePath = new ArrayList<>(relativePath);
      if (!targetOnly) newRelativePath.add(dataPath.getFileName().toString());
      // recursive call to all the node's children
      final File[] files = dataPath.toFile().listFiles();
      if (files != null) {
        for (File file : files) {
          addFileToRepresentation(representation, false, file.toPath(), newRelativePath);
        }
      }
    } else {
      if (!relativePath.isEmpty()) {
        representation.addFile(dataPath, relativePath);
      } else {
        representation.addFile(new IPFile(dataPath));
      }
    }
  }

  private static IPContentType getIPContentType(final String representationType) {
    final List<IPContentType.IPContentTypeEnum> ipContentTypeEnums = Arrays
      .asList(IPContentType.IPContentTypeEnum.values());
    for (IPContentType.IPContentTypeEnum ipContentTypeEnum : ipContentTypeEnums) {
      if (representationType.equalsIgnoreCase(ipContentTypeEnum.getType())) {
        return new IPContentType(ipContentTypeEnum);
      }
    }
    return new IPContentType(IPContentType.IPContentTypeEnum.MIXED);
  }

  private static void addDocumentationToSIP(final SIP sip, final String[] documentation) throws IOException {
    for (String doc : documentation) {
      final Path docPath = Paths.get(doc);
      if (Files.isDirectory(docPath)) {
        final List<Path> filesInDirectory = getFilesInDirectory(docPath);
        for (Path docFile : filesInDirectory) {
          sip.addDocumentation(new IPFile(docFile));
        }
      } else {
        sip.addDocumentation(new IPFile(docPath));
      }
    }
  }

  private static List<Path> getFilesInDirectory(final Path docPath) throws IOException {
    final List<Path> filesInDirectory;
    try (Stream<Path> walk = Files.walk(docPath)) {
      filesInDirectory = walk.filter(Files::isRegularFile).collect(Collectors.toList());
    }
    return filesInDirectory;
  }

  /**
   * Validates if at least something is given as parameter.
   * 
   * @param metadata
   *          {@link String}
   * @param documentation
   *          {@link String[]}
   * @param representation
   *          {@link String[]}
   * @return true if at least one file has given as parameter.
   */
  public static boolean validateAllOptions(final String[] metadata, final String[] documentation,
                                           final String[] representation) {
    return metadata != null || documentation != null || representation != null;
  }

}
