/**
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE file at the root of the source
 * tree and available online at
 *
 * https://github.com/keeps/commons-ip
 */
package org.roda_project.commons_ip.model;

public class MigrationException extends Exception {
  private static final long serialVersionUID = -6775193172396834272L;

  public MigrationException(String message, Throwable t) {
    super(message, t);
  }

  public MigrationException() {
    super();
  }
}