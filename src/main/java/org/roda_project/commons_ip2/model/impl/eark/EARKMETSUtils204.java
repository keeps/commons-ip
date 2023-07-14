/**
 * The contents of this file are subject to the license and copyright detailed in the LICENSE file
 * at the root of the source tree and available online at
 *
 * <p>https://github.com/keeps/commons-ip
 */
package org.roda_project.commons_ip2.model.impl.eark;

import java.util.HashMap;
import java.util.Map;
import org.roda_project.commons_ip2.mets_v1_12.beans.MetsType.FileSec.FileGrp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EARKMETSUtils204 extends EARKMETSGenerator {
  /**
   * {@link Logger}.
   */
  private static final Logger LOGGER = LoggerFactory.getLogger(EARKMETSUtils204.class);

  /**
   * {@link HashMap} with all data fileGrps.
   */
  private static final Map<String, FileGrp> dataFileGrp = new HashMap<>();

  public EARKMETSUtils204() {
    // do nothing
  }



}
