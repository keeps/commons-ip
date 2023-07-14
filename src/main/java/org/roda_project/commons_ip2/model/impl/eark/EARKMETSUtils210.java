package org.roda_project.commons_ip2.model.impl.eark;

import org.roda_project.commons_ip2.mets_v1_12.beans.MetsType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Carlos Afonso <cafonso@keep.pt>
 */
public class EARKMETSUtils210 extends EARKMETSGenerator {
  /**
   * {@link Logger}.
   */
  private static final Logger LOGGER = LoggerFactory.getLogger(EARKMETSUtils210.class);

  /**
   * {@link HashMap} with all data fileGrps.
   */
  private static final Map<String, MetsType.FileSec.FileGrp> dataFileGrp = new HashMap<>();

  public EARKMETSUtils210() {
    // do nothing
  }

}
