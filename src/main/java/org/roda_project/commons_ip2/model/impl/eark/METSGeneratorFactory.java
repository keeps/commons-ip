package org.roda_project.commons_ip2.model.impl.eark;

/**
 * @author Carlos Afonso <cafonso@keep.pt>
 */
public class METSGeneratorFactory {

  public METSGeneratorFactory() {}

  public EARKMETSGenerator getMetsGenerator(String version) {

    switch (version) {
      case "2.0.4":
        return new EARKMETSUtils204();
      default:
        return new EARKMETSUtils210();
    }

  }
}
