package org.roda_project.commons_ip2.cli.utils.CLI;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.roda_project.commons_ip2.cli.model.args.MetadataGroup;
import org.roda_project.commons_ip2.cli.model.args.RepresentationGroup;

/**
 * @author Miguel Guimarães <mguimaraes@keep.pt>
 */
public class CreateCommandUtils {

  public static boolean validateDocumentationPaths(final List<String> documentationPaths) {
    if (documentationPaths != null) {
      return documentationPaths.stream().allMatch(doc -> Files.exists(Paths.get(doc)));
    }

    return true;
  }

  public static boolean validateRepresentationDataPaths(final List<RepresentationGroup> representationGroups) {
    if (representationGroups != null) {
      boolean valid = true;
      for (RepresentationGroup representationGroup : representationGroups) {
        valid &= representationGroup.getRepresentation().getRepresentationData().stream()
          .allMatch(rep -> Files.exists(Paths.get(rep)));
      }
      return valid;
    }

    return true;
  }

  public static boolean validateMetadataPaths(final List<MetadataGroup> metadataGroups) {
    if (metadataGroups != null) {
      return metadataGroups.stream()
        .allMatch(metadata -> Files.exists(Paths.get(metadata.getMetadata().getMetadataFile())));
    }

    return true;
  }

  public static boolean validateMetadataSchemaPaths(final List<MetadataGroup> metadataGroups) {
    if (metadataGroups != null) {
      return metadataGroups.stream()
        .allMatch(metadata -> {
          String schemaPath = metadata.getMetadata().getMetadataSchema();
          return schemaPath == null || Files.exists(Paths.get(schemaPath));
        });
    }

    return true;
  }

  private CreateCommandUtils() {
  }
}
