package org.roda_project.commons_ip2.validator.state;

import java.nio.file.Path;

import org.roda_project.commons_ip2.validator.common.FolderManager;
import org.roda_project.commons_ip2.validator.common.ZipManager;

/**
 * @author João Gomes <jgomes@keep.pt>
 */
public class StructureValidatorState {
  private ZipManager zipManager;
  private FolderManager folderManager;
  private boolean isZipFileFlag;
  private Path ipPath;

  /**
   * Initialize all objects of structure Component
   * 
   * @param ipPath
   *          {@link Path}
   */
  public StructureValidatorState(Path ipPath) {
    this.zipManager = new ZipManager();
    this.folderManager = new FolderManager();
    this.isZipFileFlag = false;
    this.ipPath = ipPath;
  }

  /**
   * @return the {@link ZipManager}
   */
  public ZipManager getZipManager() {
    return zipManager;
  }

  /**
   * @return the {@link FolderManager}
   */
  public FolderManager getFolderManager() {
    return folderManager;
  }

  /**
   * @return if the IP is in zip format or is a folder
   */
  public boolean isZipFileFlag() {
    return isZipFileFlag;
  }

  /**
   * Set if the IP is in zip format or is a folder
   * 
   * @param zipFileFlag
   */
  public void setZipFileFlag(boolean zipFileFlag) {
    isZipFileFlag = zipFileFlag;
  }

  /**
   * @return {@link Path}
   *
   */
  public Path getIpPath() {
    return ipPath;
  }

}
