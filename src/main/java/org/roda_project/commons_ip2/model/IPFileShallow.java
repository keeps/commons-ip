package org.roda_project.commons_ip2.model;

import java.net.URI;
import java.nio.file.Path;
import java.util.List;

import org.apache.commons.lang3.NotImplementedException;
import org.roda_project.commons_ip2.mets_v1_12.beans.FileType;

/**
 * @author Miguel Guimarães <mguimaraes@keep.pt>
 */
public class IPFileShallow implements IPFileInterface {


  public static IPFileShallow createEmptyFolder(final List<String> emptyFolderPath) {
    return new IPFileShallow(emptyFolderPath);
  }

  private URI fileLocation;
  private FileType fileType;
  private List<String> relativeFolders;

  /**
   * Constructor only with relative folders.
   * 
   * @param relativeFolders
   *          {@link List}
   */
  public IPFileShallow(final List<String> relativeFolders) {
    this.relativeFolders = relativeFolders;
  }

  /**
   * Constructor with {@link URI} and {@link FileType}.
   * 
   * @param fileLocation
   *          {@link URI}
   * @param fileType
   *          {@link FileType}
   */
  public IPFileShallow(URI fileLocation, FileType fileType) {
    super();
    this.fileLocation = fileLocation;
    this.fileType = fileType;
  }

  /**
   * Constructor with {@link URI}, {@link FileType} and {@link List}.
   * 
   * @param fileLocation
   *          {@link URI}
   * @param fileType
   *          {@link FileType}
   * @param relativeFolders
   *          {@link List}
   */
  public IPFileShallow(final URI fileLocation, final FileType fileType, final List<String> relativeFolders) {
    super();
    this.fileLocation = fileLocation;
    this.fileType = fileType;
    this.relativeFolders = relativeFolders;
  }

  public URI getFileLocation() {
    return fileLocation;
  }

  public void setFileLocation(URI fileLocation) {
    this.fileLocation = fileLocation;
  }

  public FileType getFileType() {
    return fileType;
  }

  public void setFileType(FileType fileType) {
    this.fileType = fileType;
  }

  @Override
  public List<String> getRelativeFolders() {
    return relativeFolders;
  }

  /**
   * Set of relative folders.
   * 
   * @param relativeFolders
   *          {@link List}
   * @return {@link IPFileInterface}
   */
  public IPFileInterface setRelativeFolders(List<String> relativeFolders) {
    this.relativeFolders = relativeFolders;
    return this;
  }

  @Override
  public String getFileName() {
    return null;
  }

  @Override
  public Path getPath() {
    throw new UnsupportedOperationException("IPFileShallow does not support this method");
  }

  @Override
  public String getChecksum() {
    throw new NotImplementedException("IPFileShallow does not support this method");
  }

  @Override
  public String getChecksumAlgorithm() {
    throw new NotImplementedException("IPFileShallow does not support this method");
  }
}
