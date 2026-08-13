package org.roda_project.commons_ip2.validator.utils;

import java.nio.file.InvalidPathException;
import java.nio.file.Paths;

/**
 * @author Carlos Afonso <cafonso@keep.pt>
 */
public class DecoderUtils {

  public static String normalizePath(String href) {

    try {
      return Paths.get(href).normalize() // Removes unnecessary elements (like "." or "..")
        .toString().replace("\\", "/"); // Assure it returns in unix-style format
    } catch (InvalidPathException e) {
      // href contains a character that isn't valid in a filesystem path (e.g. a NUL
      // character); return it unchanged so callers can still report it as an issue
      // instead of crashing.
      return href;
    }

  }

}
