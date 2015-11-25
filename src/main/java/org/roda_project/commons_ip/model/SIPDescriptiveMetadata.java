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

  public SIPDescriptiveMetadata(Path metadata, Path schema, MetadataType metadataType) {
    super(metadata, schema);
    this.metadataType = metadataType;
  }

  private MetadataType metadataType;

  public MetadataType getMetadataType() {
    return metadataType;
  }

  public void setMetadataType(MetadataType metadataType) {
    this.metadataType = metadataType;
  }
}
