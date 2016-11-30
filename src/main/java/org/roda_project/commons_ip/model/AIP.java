/**
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE file at the root of the source
 * tree and available online at
 *
 * https://github.com/keeps/commons-ip
 */
package org.roda_project.commons_ip.model;

import java.nio.file.Path;

import org.roda_project.commons_ip.utils.IPEnums.IPType;

public abstract class AIP extends IP {

  public AIP() {
    super();
  }

  public AIP(String aipId) {
    super();
    setId(aipId);
    setType(IPType.AIP);
  }

  public AIP(String aipId, IPContentType contentType) {
    super();
    setId(aipId);
    setType(IPType.AIP);
    setContentType(contentType);
  }

  public static AIP parse(Path source) throws ParseException {
    throw new ParseException("One must implement static method parse in a concrete class");
  }

  public static AIP parse(Path source, Path destinationDirectory) throws ParseException {
    throw new ParseException("One must implement static method parse in a concrete class");
  }

}
