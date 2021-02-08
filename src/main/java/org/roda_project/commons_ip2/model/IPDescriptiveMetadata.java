/**
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE file at the root of the source
 * tree and available online at
 *
 * https://github.com/keeps/commons-ip
 */
package org.roda_project.commons_ip2.model;

import java.io.Serializable;

public class IPDescriptiveMetadata extends IPMetadata implements Serializable {
  private static final long serialVersionUID = 8499877557153068472L;

  private String metadataVersion;

  public IPDescriptiveMetadata(String id, IPFileInterface metadata, MetadataType metadataType, String metadataVersion) {
    super(id, metadata, metadataType);
    this.metadataVersion = metadataVersion;
  }

  public IPDescriptiveMetadata(IPFileInterface metadata, MetadataType metadataType, String metadataVersion) {
    super(metadata, metadataType);
    this.metadataVersion = metadataVersion;
  }

  public String getMetadataVersion() {
    return metadataVersion;
  }

  public void setMetadataVersion(String metadataVersion) {
    this.metadataVersion = metadataVersion;
  }

  @Override
  public String toString() {
    return "IPDescriptiveMetadata [metadataVersion=" + metadataVersion + ", getMetadata()=" + getMetadata()
      + ", getMetadataType()=" + getMetadataType() + "]";
  }

}
