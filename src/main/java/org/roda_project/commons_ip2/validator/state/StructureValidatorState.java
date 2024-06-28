package org.roda_project.commons_ip2.validator.state;

import java.nio.file.Path;

import org.roda_project.commons_ip2.validator.common.FolderManager;
import org.roda_project.commons_ip2.validator.common.ZipManager;

/** {@author João Gomes <jgomes@keep.pt>}. */
public class StructureValidatorState {
  /**
   * {@link ZipManager}.
   */
  private final ZipManager zipManager;
  /**
   * {@link FolderManager}.
   */
  private final FolderManager folderManager;
  /**
   * Flag if is zip file or is a directory.
   */
  private boolean isZipFileFlag;
  /**
   * The IP path.
   */
  private final Path ipPath;

  /**
   * Initialize all objects of structure Component.
   *
   * @param ipPath
   *          {@link Path}
   */
  public StructureValidatorState(final Path ipPath) {
    this.zipManager = new ZipManager();
    this.folderManager = new FolderManager();
    this.isZipFileFlag = false;
    this.ipPath = ipPath;
  }

  /**
   * Get the {@link ZipManager}.
   *
   * @return the {@link ZipManager}
   */
  public ZipManager getZipManager() {
    return zipManager;
  }

  /**
   * Get the {@link FolderManager}.
   *
   * @return the {@link FolderManager}
   */
  public FolderManager getFolderManager() {
    return folderManager;
  }

  /**
   * Get the value of isZipFileFlag.
   *
   * @return if the IP is in zip format or is a folder.
   */
  public boolean isZipFileFlag() {
    return isZipFileFlag;
  }

  /**
   * Set if the IP is in zip format or is a folder.
   *
   * @param zipFileFlag
   *          flag if IP is in zip format or not.
   */
  public void setZipFileFlag(final boolean zipFileFlag) {
    isZipFileFlag = zipFileFlag;
  }

  /**
   * Get the IP {@link Path}.
   *
   * @return {@link Path}.
   */
  public Path getIpPath() {
    return ipPath;
  }
}
