package org.roda_project.commons_ip.model;

/**
 * Interface for {@link AIPInterface} readers.
 */
public interface AIPReader {

  /**
   * Read an AIP.
   *
   * @return the {@link AIPInterface}.
   * @throws ParseException
   *           if some error occurred.
   */
  AIPInterface read() throws ParseException;

}
