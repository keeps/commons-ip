/**
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE file at the root of the source
 * tree and available online at
 *
 * https://github.com/keeps/commons-ip
 */
package org.roda_project.commons_ip.model;

import java.nio.file.Path;

public class SIPMetadata {
  private Path metadata;
  private Path schema;

  public SIPMetadata(Path metadata, Path schema) {
    this.metadata = metadata;
    this.schema = schema;
  }

  public Path getMetadata() {
    return metadata;
  }

  public void setMetadata(Path metadata) {
    this.metadata = metadata;
  }

  public Path getSchema() {
    return schema;
  }

  public void setSchema(Path schema) {
    this.schema = schema;
  }

}
