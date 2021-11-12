package org.roda_project.commons_ip2.validator.state;

import java.util.ArrayList;
import java.util.Map;

import org.roda_project.commons_ip2.mets_v1_12.beans.Mets;

/**
 * @author João Gomes <jgomes@keep.pt>
 */
public class MetsValidatorState {
  private Mets mets = null;
  private ArrayList<String> metsInternalIds = new ArrayList<>();
  private String metsName = null;
  private String metsPath = null;
  private boolean isRootMets = false;
  private Map<String, Boolean> metsFiles = null;
  private String ipType = null;

  /**
   * @return {@link Mets}
   */
  public Mets getMets() {
    return mets;
  }

  /**
   * Set {@link Mets} object
   * 
   * @param mets
   *          the {@link Mets}
   */
  public void setMets(Mets mets) {
    this.mets = mets;
  }

  /**
   * @return METS file name
   */
  public String getMetsName() {
    return this.metsName;
  }

  /**
   * Set METS file name
   * 
   * @param metsName
   *          the {@link String }
   */
  public void setMetsName(String metsName) {
    this.metsName = metsName;
  }

  /**
   * @return METS file path
   */
  public String getMetsPath() {
    return this.metsPath;
  }

  /**
   * Set METS file path
   * 
   * @param metsPath
   *          the {@link String }
   */
  public void setMetsPath(String metsPath) {
    this.metsPath = metsPath;
  }

  /**
   * @return if the validation METS file is root METS or a sub METS
   */
  public boolean isRootMets() {
    return isRootMets;
  }

  /**
   * Set if is METS root file or not
   * 
   * @param isRootMets
   *          flag if is METS file on root or not
   */
  public void setIsRootMets(boolean isRootMets) {
    this.isRootMets = isRootMets;
  }

  /**
   * Add id to list of all METS files id's in the IP
   *
   * @param id
   *          the {@link String }
   */
  public void addMetsInternalId(String id) {
    metsInternalIds.add(id);
  }

  /**
   * Check if exists this {@link String} in the list of id's
   * 
   * @param id
   *          the {@link String}
   * @return if already exists this {@link String}
   */
  public boolean checkMetsInternalId(String id) {
    return metsInternalIds.contains(id);
  }

  /**
   * Get {@link Map<String,Boolean>} all files in IP
   * 
   * @return {@link Map<String,Boolean>}
   */
  public Map<String, Boolean> getMetsFiles() {
    return this.metsFiles;
  }

  /**
   * Set {@link Map<String,Boolean>} METS files
   * 
   * @param metsFiles
   *          the {@link Map<String,Boolean>}
   */
  public void setMetsFiles(Map<String, Boolean> metsFiles) {
    this.metsFiles = metsFiles;
  }

  /**
   * @return the type of IP (SIP,AIP)
   */
  public String getIpType() {
    return ipType;
  }

  /**
   * Set the type of IP (SIP, AIP)
   * 
   * @param ipType
   *          the {@link String}
   */
  public void setIpType(String ipType) {
    this.ipType = ipType;
  }
}
