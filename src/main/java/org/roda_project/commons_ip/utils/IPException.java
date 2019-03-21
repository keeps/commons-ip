/**
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE file at the root of the source
 * tree and available online at
 *
 * https://github.com/keeps/commons-ip
 */
package org.roda_project.commons_ip.utils;

public class IPException extends Exception {
  private static final long serialVersionUID = 6925675286696076482L;

  public IPException(String message) {
    super(message);
  }

  public IPException(String message, Throwable t) {
    super(message, t);
  }
}
