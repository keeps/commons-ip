package org.roda_project.commons_ip2.validator.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
public class SipCreatorUtils {

  private SipCreatorUtils() {
    // do nothing
  }

  public static boolean validateMetadataOptions(String metadataFile, String metadataType, String metadataVersion) {
    if (metadataFile == null && metadataType == null && metadataVersion == null) {
      return true;
    } else
      return metadataFile != null && metadataType != null;
  }

  public static boolean validateRepresentationOptions(String[] representationData, String representationType,
    String representationID) {
    return representationData != null || (representationType == null && representationID == null);
  }

  public static boolean validateMetadataPath(String metadataFile) {
    if (metadataFile != null) {
      return Files.exists(Paths.get(metadataFile));
    }
    return false;
  }

  public static boolean validateRepresentationPaths(String[] representationData) {
    for (String data : representationData) {
      if (!Files.exists(Paths.get(data))) {
        return false;
      }
    }
    return true;
  }

  public static boolean validateDocumentationPaths(String[] documentationPaths) {
    if (documentationPaths != null) {
      for (String doc : documentationPaths) {
        if (!Files.exists(Paths.get(doc))) {
          return false;
        }
      }
    }
    return true;
  }

  public static Path createEARK2SIP(String metadataFile, String metadataType, String metadataVersion,
    String[] representationData, String representationType, String representationID, String sipID, String[] ancestors,
    String[] documentation, String softwareVersion, String path, String submitterAgentName, String submitterAgentID)
    throws IPException, InterruptedException {

    if (sipID == null) {
      sipID = Utils.generateRandomAndPrefixedUUID();
    }

    final SIP sip = new EARKSIP(sipID, IPContentType.getMIXED(), IPContentInformationType.getMIXED());
    sip.addCreatorSoftwareAgent("RODA Commons IP", softwareVersion);
    sip.addSubmitterAgent(submitterAgentName, submitterAgentID);

    sip.setDescription("SIP created by commons-ip cli tool");

    if (metadataFile != null) {
      try {
        addMetadataToSIP(sip, metadataFile, metadataType, metadataVersion);
      } catch (IPException e) {
        CLIUtils.printErrors(System.out, "Cannot add metadata to the SIP.");
      }
    }

    if (representationData != null) {
      try {
        addRepresentationDataToSIP(sip, representationData, representationType, representationID);
      } catch (IPException e) {
        CLIUtils.printErrors(System.out, "Cannot add representation to the SIP.");
      }
    }

    if (documentation != null) {
      try {
        addDocumentationToSIP(sip, documentation);
      } catch (IOException e) {
        CLIUtils.printErrors(System.out, "Cannot add metadata to the SIP.");
      }
    }

    if (ancestors != null) {
      sip.setAncestors(Arrays.asList(ancestors));
    }

    Path buildPath;
    if (path != null && Files.exists(Paths.get(path))) {
      buildPath = Paths.get(path);
    } else {
      buildPath = Paths.get(System.getProperty("user.dir"));
    }

    return sip.build(buildPath);
  }

  private static void addMetadataToSIP(SIP sip, String metadataFile, String metadataType, String metadataVersion)
    throws IPException {
    MetadataType.MetadataTypeEnum metadataTypeValue = null;
    if (metadataType == null && metadataVersion == null) {
      metadataTypeValue = getMetadataTypeFromMetadataFile(metadataFile);
      metadataVersion = getMetadataVersionFromMetadataFile(metadataFile);
    } else if (metadataVersion != null && metadataType == null) {
      metadataTypeValue = getMetadataTypeFromMetadataFile(metadataFile);
    } else if (metadataVersion == null) {
      metadataTypeValue = getType(metadataType);
      metadataVersion = getMetadataVersionFromMetadataFile(metadataFile);
    } else {
      metadataTypeValue = getType(metadataType);
    }

    IPDescriptiveMetadata descriptiveMetadata = new IPDescriptiveMetadata(new IPFile(Paths.get(metadataFile)),
      new MetadataType(metadataTypeValue), metadataVersion);
    sip.addDescriptiveMetadata(descriptiveMetadata);
  }

  private static MetadataType.MetadataTypeEnum getMetadataTypeFromMetadataFile(String metadataFile) {
    Path metadataPath = Paths.get(metadataFile);
    String filename = metadataPath.getFileName().toString();
    filename = filename.split("\\.")[0];

    MetadataType.MetadataTypeEnum metadataType = getType(filename);

    if (metadataType == null) {
      String[] splitedFileName = filename.split("_");
      if (splitedFileName.length == 2) {
        metadataType = getType(splitedFileName[0]);
      }
    }
    return metadataType;
  }

  private static MetadataType.MetadataTypeEnum getType(String type) {
    List<MetadataType.MetadataTypeEnum> metadataEnumList = Arrays.asList(MetadataType.MetadataTypeEnum.values());
    for (MetadataType.MetadataTypeEnum metadataTypeEnum : metadataEnumList) {
      if (type.equalsIgnoreCase(metadataTypeEnum.getType())) {
        return metadataTypeEnum;
      }
    }

    return null;
  }

  private static String getMetadataVersionFromMetadataFile(String metadataFile) {
    Path metadataPath = Paths.get(metadataFile);
    String filename = metadataPath.getFileName().toString();
    filename = filename.split("\\.")[0];
    String[] filenameSplitedWithoutUnderscore = filename.split("_");
    String metadataVersion = null;
    if (filenameSplitedWithoutUnderscore.length == 2) {
      metadataVersion = filenameSplitedWithoutUnderscore[1];
    } else {
      metadataVersion = filenameSplitedWithoutUnderscore[0].replaceAll("[A-Z-a-z]", "");
    }

    return metadataVersion;
  }

  private static void addRepresentationDataToSIP(SIP sip, String[] representationData, String representationType,
    String representationID) throws IPException {
    if (representationID == null) {
      representationID = "rep1";
    }

    IPRepresentation representation = new IPRepresentation(representationID);
    sip.addRepresentation(representation);
    if (representationType != null) {
      IPContentType ipContentType = getIPContentType(representationType);
      representation.setContentType(ipContentType);
    }

    for (String data : representationData) {
      Path dataPath = Paths.get(data);
      addFileToRepresentation(representation, dataPath, new ArrayList<>());
    }

  }

  private static void addFileToRepresentation(IPRepresentation representation, Path dataPath,
    List<String> relativePath) {
    if (Files.isDirectory(dataPath)) {
      final List<String> newRelativePath = new ArrayList<>(relativePath);
      newRelativePath.add(dataPath.getFileName().toString());
      // recursive call to all the node's children
      File[] files = dataPath.toFile().listFiles();
      if (files != null) {
        for (File file : files) {
          addFileToRepresentation(representation, file.toPath(), newRelativePath);
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

  private static IPContentType getIPContentType(String representationType) {
    List<IPContentType.IPContentTypeEnum> ipContentTypeEnums = Arrays.asList(IPContentType.IPContentTypeEnum.values());
    for (IPContentType.IPContentTypeEnum ipContentTypeEnum : ipContentTypeEnums) {
      if (representationType.equalsIgnoreCase(ipContentTypeEnum.getType())) {
        return new IPContentType(ipContentTypeEnum);
      }
    }
    return new IPContentType(IPContentType.IPContentTypeEnum.MIXED);
  }

  private static void addDocumentationToSIP(SIP sip, String[] documentation) throws IOException {
    for (String doc : documentation) {
      Path docPath = Paths.get(doc);
      if (Files.isDirectory(docPath)) {
        List<Path> filesInDirectory = getFilesInDirectory(docPath);
        for (Path docFile : filesInDirectory) {
          sip.addDocumentation(new IPFile(docFile));
        }
      } else {
        sip.addDocumentation(new IPFile(docPath));
      }
    }
  }

  private static List<Path> getFilesInDirectory(Path docPath) throws IOException {
    List<Path> filesInDirectory;
    try (Stream<Path> walk = Files.walk(docPath)) {
      filesInDirectory = walk.filter(Files::isRegularFile).collect(Collectors.toList());
    }
    return filesInDirectory;
  }

}
