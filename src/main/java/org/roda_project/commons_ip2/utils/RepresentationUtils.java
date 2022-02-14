package org.roda_project.commons_ip2.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.roda_project.commons_ip2.model.IPFile;
import org.roda_project.commons_ip2.model.IPRepresentation;

/**
 * {@author João Gomes <jgomes@keep.pt>}.
 */
public final class RepresentationUtils {

  private RepresentationUtils() {
    // do nothing
  }

  public static void addToRepresentation(final List<Path> filesOrFolders, final IPRepresentation representation) {

    for (Path file : filesOrFolders) {
      try {
        addToRepresentation(file, representation, Collections.EMPTY_LIST);
      } catch (final IOException e) {
        // do nothing
      }
    }
  }

  public static void includeInRepresentation(final Path directory, final IPRepresentation representation) throws IOException {
    if (Files.isDirectory(directory)) {
      Files.list(directory).forEach(f -> {
        try {
          addToRepresentation(f, representation, Collections.EMPTY_LIST);
        } catch (final IOException e) {
          // do nothing
        }
      });
    }
  }

  private static void addToRepresentation(final Path path, final IPRepresentation representation,
    final List<String> relativeFolders) throws IOException {
    if (Files.isDirectory(path)) {
      final List<String> newRelative = new ArrayList<>(relativeFolders);
      newRelative.add(path.getFileName().toString());
      Files.list(path).forEach(f -> {
        try {
          addToRepresentation(f, representation, newRelative);
        } catch (final IOException e) {
          // do nothing
        }
      });
    } else {
      representation.addFile(new IPFile(path, relativeFolders));
    }
  }
}
