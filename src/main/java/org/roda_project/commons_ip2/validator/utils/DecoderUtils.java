package org.roda_project.commons_ip2.validator.utils;

import java.nio.file.Paths;

/**
 * @author Carlos Afonso <cafonso@keep.pt>
 */
public class DecoderUtils {

  public static String normalizePath(String href) {
    return Paths.get(href).normalize().toString();
  }

}
