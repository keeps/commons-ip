package org.roda_project.commons_ip2.cli.utils;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.roda_project.commons_ip.utils.IPException;
import org.roda_project.commons_ip2.cli.model.args.MetadataGroup;
import org.roda_project.commons_ip2.cli.model.args.RepresentationGroup;
import org.roda_project.commons_ip2.model.IPContentType;
import org.roda_project.commons_ip2.model.IPDescriptiveMetadata;
import org.roda_project.commons_ip2.model.IPFile;
import org.roda_project.commons_ip2.model.IPRepresentation;
import org.roda_project.commons_ip2.model.MetadataType;
import org.roda_project.commons_ip2.model.SIP;
import org.roda_project.commons_ip2.utils.Utils;

/**
 * @author Miguel Guimarães <mguimaraes@keep.pt>
 */
public class SIPBuilderUtils {

  private static final String UNDERSCORE = "_";
  private static final String DOT_REGEX = "\\.";

  public static String getOrGenerateID(String sipId) {
    String id = sipId;
    if (id == null) {
      id = Utils.generateRandomAndPrefixedUUID();
    } else {
      id = URLEncoder.encode(sipId, StandardCharsets.UTF_8).replace("*", "%2A");
    }

    return id;
  }

  public static void addDocumentationToSIP(final SIP sip, final List<String> documentation) throws IOException {
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

  public static void addRepresentationGroupsToSIP(SIP sip, final List<RepresentationGroup> representationGroups,
    boolean targetOnly) throws IPException {
    if (representationGroups != null) {
      for (RepresentationGroup representationGroup : representationGroups) {
        addRepresentationToSIP(sip, representationGroup, targetOnly);
      }
    }
  }

  private static void addRepresentationToSIP(SIP sip, final RepresentationGroup representationGroup, boolean targetOnly)
    throws IPException {
    String id = representationGroup.getRepresentation().getRepresentationId();

    if (representationGroup.getRepresentation().getRepresentationId() == null) {
      id = "rep1";
    }

    final IPRepresentation representation = new IPRepresentation(id);
    sip.addRepresentation(representation);

    if (representationGroup.getRepresentation().getRepresentationType() != null) {
      final IPContentType ipContentType = getIPContentType(
        representationGroup.getRepresentation().getRepresentationType());
      representation.setContentType(ipContentType);
    }

    for (String representationData : representationGroup.getRepresentation().getRepresentationData()) {
      final Path dataPath = Paths.get(representationData);
      addFileToRepresentation(representation, targetOnly, dataPath, new ArrayList<>());
    }
  }

  private static void addFileToRepresentation(final IPRepresentation representation, final boolean targetOnly,
    final Path dataPath, final List<String> relativePath) {
    if (Files.isDirectory(dataPath)) {
      final List<String> newRelativePath = new ArrayList<>(relativePath);
      if (!targetOnly)
        newRelativePath.add(dataPath.getFileName().toString());
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
    final IPContentType.IPContentTypeEnum[] ipContentTypeEnums = IPContentType.IPContentTypeEnum.values();
    for (IPContentType.IPContentTypeEnum ipContentTypeEnum : ipContentTypeEnums) {
      if (representationType.equalsIgnoreCase(ipContentTypeEnum.getType())) {
        return new IPContentType(ipContentTypeEnum);
      }
    }
    return new IPContentType(IPContentType.IPContentTypeEnum.MIXED);
  }

  public static void addMetadataGroupsToSIP(SIP sip, final List<MetadataGroup> metadataGroups) throws IPException {
    if (metadataGroups != null) {
      for (MetadataGroup metadataGroup : metadataGroups) {
        addMetadataToSIP(sip, metadataGroup);
      }
    }
  }

  private static void addMetadataToSIP(SIP sip, final MetadataGroup metadataGroup) throws IPException {
    String metadataFile = metadataGroup.getMetadata().getMetadataFile();
    String metadataType = metadataGroup.getMetadata().getMetadataType();
    String metadataVersion = metadataGroup.getMetadata().getMetadataVersion();

    MetadataType metadataTypeEnum;
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
    String metadataSchema = metadataGroup.getMetadata().getMetadataSchema();
    if(metadataSchema != null){
      sip.addSchema(new IPFile(Paths.get(metadataSchema)));
    }
  }

  private static MetadataType getMetadataTypeFromMetadataFile(final String metadataFile) {
    final Path metadataPath = Paths.get(metadataFile);
    String filename = metadataPath.getFileName().toString();
    final String metadataTypeValue = filename = filename.split(DOT_REGEX)[0];

    MetadataType metadataType = new MetadataType(metadataTypeValue);

    if (MetadataType.OTHER().equals(metadataType)) {
      final String[] splitFileName = filename.split(UNDERSCORE);
      if (splitFileName.length == 2) {
        metadataType = new MetadataType(splitFileName[0]);
      }
    }
    return metadataType;
  }

  private static String getMetadataVersionFromMetadataFile(final String metadataFile) {
    final Path metadataPath = Paths.get(metadataFile);
    String filename = metadataPath.getFileName().toString();
    filename = filename.split(DOT_REGEX)[0];
    final String[] filenameSplitWithoutUnderscore = filename.split(UNDERSCORE);
    String metadataVersion = null;
    if (filenameSplitWithoutUnderscore.length == 2) {
      metadataVersion = filenameSplitWithoutUnderscore[1];
    }

    return metadataVersion;
  }

  private static List<Path> getFilesInDirectory(final Path docPath) throws IOException {
    final List<Path> filesInDirectory;
    try (Stream<Path> walk = Files.walk(docPath)) {
      filesInDirectory = walk.filter(Files::isRegularFile).toList();
    }
    return filesInDirectory;
  }

  private SIPBuilderUtils() {
  }
}
