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