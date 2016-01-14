/**
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE file at the root of the source
 * tree and available online at
 *
 * https://github.com/keeps/commons-ip
 */
package org.roda_project.commons_ip.model;

import java.nio.file.Path;

import org.roda_project.commons_ip.utils.METSEnums.MetadataType;

public class SIPDescriptiveMetadata extends SIPMetadata {

  public SIPDescriptiveMetadata(Path metadata, Path schema, MetadataType metadataType, String metadataVersion) {
    super(metadata, schema);
    this.metadataType = metadataType;
    this.metadataVersion = metadataVersion;
  }

  private MetadataType metadataType;
  private String metadataVersion;

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
}
