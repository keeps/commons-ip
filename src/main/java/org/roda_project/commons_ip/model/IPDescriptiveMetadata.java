/**
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE file at the root of the source
 * tree and available online at
 *
 * https://github.com/keeps/commons-ip
 */
package org.roda_project.commons_ip.model;

import java.io.Serializable;

public class IPDescriptiveMetadata extends IPMetadata implements Serializable {
  private static final long serialVersionUID = 8499877557153068472L;

  private MetadataType metadataType;
  private String metadataVersion;

  public IPDescriptiveMetadata(IPFile metadata, MetadataType metadataType, String metadataVersion) {
    super(metadata);
    this.metadataType = metadataType;
    this.metadataVersion = metadataVersion;
  }

  public String getMetadataVersion() {
    return metadataVersion;
  }

  public void setMetadataVersion(String metadataVersion) {
    this.metadataVersion = metadataVersion;
  }

  public MetadataType getMetadataType() {
    return metadataType;
  }

  public void setMetadataType(MetadataType metadataType) {
    this.metadataType = metadataType;
  }

  @Override
  public String toString() {
    return "IPDescriptiveMetadata [metadataType=" + metadataType + ", metadataVersion=" + metadataVersion
      + ", getMetadata()=" + getMetadata() + "]";
  }

}
