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

  public Mets getMets() {
    return mets;
  }

  public void setMets(Mets mets) {
    this.mets = mets;
  }

  public String getMetsName() {
    return this.metsName;
  }

  public void setMetsName(String metsName){
    this.metsName = metsName;
  }

  public String getMetsPath() {
    return this.metsPath;
  }

  public void setMetsPath(String metsPath) {
    this.metsPath = metsPath;
  }

  public boolean isRootMets() {
    return isRootMets;
  }

  public void setIsRootMets(boolean isRootMets) {
    this.isRootMets = isRootMets;
  }

  public void addMetsInternalId(String id) {
    metsInternalIds.add(id);
  }

  public boolean checkMetsInternalId(String id) {
    return metsInternalIds.contains(id);
  }

  public Map<String, Boolean> getMetsFiles() {
    return this.metsFiles;
  }

  public void setMetsFiles(Map<String,Boolean> metsFiles){
    this.metsFiles = metsFiles;
  }
}
