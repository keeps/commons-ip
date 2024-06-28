package org.roda_project.commons_ip2.utils;

import java.util.HashMap;

/**
 * {@author João Gomes <jgomes@keep.pt>}.
 */
public class StructMapDiv {

  /**
   * Label of Div.
   */
  private final String label;
  /**
   * {@link String} with the key to FileGrps {@link HashMap}.
   *
   */
  private String fileLocation;

  /**
   * Constructor of Struct Map.
   * 
   * @param label
   *          {@link String}.
   */
  public StructMapDiv(final String label) {
    this.label = label;
  }

  public String getLabel() {
    return this.label;
  }

  public String getFileLocation() {
    return this.fileLocation;
  }

  public void setFileLocation(final String fileLocation) {
    this.fileLocation = fileLocation;
  }

  /**
   * Equals method to this Class.
   * 
   * @param obj
   *          {@link Object}
   * @return if is equals or not.
   */
  public boolean equals(final Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || obj.getClass() != this.getClass()) {
      return false;
    }
    // object must be Test at this point
    final StructMapDiv structMapDiv = (StructMapDiv) obj;
    return label.equals(structMapDiv.label);
  }
}
