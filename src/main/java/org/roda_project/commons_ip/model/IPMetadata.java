/**
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE file at the root of the source
 * tree and available online at
 *
 * https://github.com/keeps/commons-ip
 */
package org.roda_project.commons_ip.model;

import java.io.Serializable;

public class IPMetadata implements Serializable {
  private static final long serialVersionUID = 3697420963728707487L;

  private IPFile metadata;

  public IPMetadata() {
    // empty constructor for serialization purposes
  }

  public IPMetadata(IPFile metadata) {
    this.metadata = metadata;
  }

  public IPFile getMetadata() {
    return metadata;
  }

  public void setMetadata(IPFile metadata) {
    this.metadata = metadata;
  }

  @Override
  public String toString() {
    return "IPMetadata [metadata=" + metadata + "]";
  }

}
