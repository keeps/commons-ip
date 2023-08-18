package org.roda_project.commons_ip2.validator.components.metsRootComponent;

/**
 * @author Carlos Afonso <cafonso@keep.pt>
 */
public class METSfile {
  private String objID;
  private String type;
  private String profile;
  private String otherType;
  private String contentInformationType;
  private String otherContentInformationType;
  private static METSfile instance = null;

  private METSfile() {
  }

  public static METSfile getInstance() {
    if (instance == null) {
      instance = new METSfile();
    }
    return instance;
  }

  public String getObjID() {
    return objID;
  }

  public void setObjID(String objID) {
    this.objID = objID;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getProfile() {
    return profile;
  }

  public void setProfile(String profile) {
    this.profile = profile;
  }

  public String getOtherType() {
    return this.otherType;
  }

  public void setOtherType(String otherType) {
    this.otherType = otherType;
  }

  public String getContentInformationType() {
    return this.contentInformationType;
  }

  public void setContentInformationType(String contentInformationType) {
    this.contentInformationType = contentInformationType;
  }

  public String getOtherContentInformationType() {
    return this.otherContentInformationType;
  }

  public void setOtherContentInformationType(String otherContentInformationType) {
    this.otherContentInformationType = otherContentInformationType;
  }

  public static void destroy() {
    instance = null;
  }

}
