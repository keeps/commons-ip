package org.roda_project.commons_ip2.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import org.roda_project.commons_ip2.validator.constants.Constants;

/**
 * @author João Gomes <jgomes@keep.pt>
 */
public class IANAMediaTypes {
  private static Set<String> ianaMediaTypes = null;

  public static Set<String> getIANAMediaTypes() {
    if (ianaMediaTypes == null) {
      ianaMediaTypes = new BufferedReader(new InputStreamReader(Objects.requireNonNull(IANAMediaTypes.class.getClassLoader()
              .getResourceAsStream(Constants.PATH_RESOURCES_CSIP_VOCABULARY_IANA_MEDIA_TYPES)), StandardCharsets.UTF_8))
          .lines().collect(Collectors.toSet());
    }
    return ianaMediaTypes;
  }
}
