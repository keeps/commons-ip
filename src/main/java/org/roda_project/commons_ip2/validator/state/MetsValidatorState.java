package org.roda_project.commons_ip2.validator.state;

import java.util.ArrayList;
import java.util.Map;

import org.roda_project.commons_ip2.mets_v1_12.beans.Mets;

/** {@author João Gomes <jgomes@keep.pt>}. */
public class MetsValidatorState {
  /**
   * {@link Mets}.
   */
  private Mets mets = null;
  /**
   * {@link ArrayList} with the internal ids.
   */
  private final ArrayList<String> metsInternalIds = new ArrayList<>();
  /**
   * Mets name.
   */
  private String metsName = null;
  /**
   * Mets path.
   */
  private String metsPath = null;
  /**
   * Flag if is root mets.
   */
  private boolean isRootMets = false;
  /**
   * {@link Map} with the files of the mets.
   */
  private Map<String, Boolean> metsFiles = null;
  /**
   * Type of the ip.
   */
  private String ipType = null;

  /**
   * Get the {@link Mets} object.
   *
   * @return {@link Mets}
   */
  public Mets getMets() {
    return mets;
  }

  /**
   * Set {@link Mets} object.
   *
   * @param mets
   *          the {@link Mets}
   */
  public void setMets(final Mets mets) {
    this.mets = mets;
  }

  /**
   * Get the METS file name.
   *
   * @return METS file name
   */
  public String getMetsName() {
    return this.metsName;
  }

  /**
   * Set METS file name.
   *
   * @param metsName
   *          the {@link String }
   */
  public void setMetsName(final String metsName) {
    this.metsName = metsName;
  }

  /**
   * Get the METS file path.
   *
   * @return METS file path
   */
  public String getMetsPath() {
    return this.metsPath;
  }

  /**
   * Set METS file path.
   *
   * @param metsPath
   *          the {@link String }
   */
  public void setMetsPath(final String metsPath) {
    this.metsPath = metsPath;
  }

  /**
   * Get the flag isRootMets.
   *
   * @return if the validation METS file is root METS or a sub METS
   */
  public boolean isRootMets() {
    return isRootMets;
  }

  /**
   * Set if is METS root file or not.
   *
   * @param isRootMets
   *          flag if is METS file on root or not
   */
  public void setIsRootMets(final boolean isRootMets) {
    this.isRootMets = isRootMets;
  }

  /**
   * Add id to list of all METS files id's in the IP.
   *
   * @param id
   *          the {@link String }
   */
  public void addMetsInternalId(final String id) {
    metsInternalIds.add(id);
  }

  /**
   * Check if exists this {@link String} in the list of id's.
   *
   * @param id
   *          the {@link String}
   * @return if already exists this {@link String}
   */
  public boolean checkMetsInternalId(final String id) {
    return metsInternalIds.contains(id);
  }

  /**
   * Delete all entries from metsInternalIds list
   */
  public void flushEntries() {
    metsInternalIds.clear();
  }

  /**
   * Get {@link Map} all files in IP.
   *
   * @return {@link Map}
   */
  public Map<String, Boolean> getMetsFiles() {
    return this.metsFiles;
  }

  /**
   * Set {@link Map} METS files.
   *
   * @param metsFiles
   *          the {@link Map}
   */
  public void setMetsFiles(final Map<String, Boolean> metsFiles) {
    this.metsFiles = metsFiles;
  }

  /**
   * Get the type of the IP (SIP,AIP,DIP).
   *
   * @return the type of IP (SIP,AIP,DIP)
   */
  public String getIpType() {
    return ipType;
  }

  /**
   * Set the type of IP (SIP, AIP,DIP).
   *
   * @param ipType
   *          the {@link String}
   */
  public void setIpType(final String ipType) {
    this.ipType = ipType;
  }
}
