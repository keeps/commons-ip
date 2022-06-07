package org.roda_project.commons_ip2.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import org.roda_project.commons_ip2.validator.constants.Constants;

/** {@author João Gomes <jgomes@keep.pt>}. */
public final class IanaMediaTypes {
  /**
   * {@link Set} with the IANA Media Types.
   */
  private static Set<String> ianaMediaTypesList = null;

  private IanaMediaTypes() {
    // do nothing
  }

  /**
   * If {@link Set} with the IANA Media types is null, loads from resource.
   *
   * @return {@link Set}.
   */
  public static Set<String> getIanaMediaTypesList() {
    if (ianaMediaTypesList == null) {
      ianaMediaTypesList = new BufferedReader(new InputStreamReader(
        Objects.requireNonNull(
          IanaMediaTypes.class.getResourceAsStream(Constants.PATH_RESOURCES_CSIP_VOCABULARY_IANA_MEDIA_TYPES)),
        StandardCharsets.UTF_8)).lines().collect(Collectors.toSet());
    }
    return ianaMediaTypesList;
  }
}
