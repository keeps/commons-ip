/**
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE file at the root of the source
 * tree and available online at
 *
 * https://github.com/keeps/commons-ip
 */
package org.roda_project.commons_ip2.model;

import java.nio.file.Path;
import java.util.List;

import org.roda_project.commons_ip.model.ParseException;

/**
 * AIP interface.
 * 
 * @author Rui Castro (rui.castro@gmail.com)
 */
public interface AIP extends IPInterface {

  /**
   * Set the {@link AIPState}.
   * 
   * @param state
   *          the {@link AIPState}.
   */
  void setState(String state);

  /**
   * Get the {@link AIPState}.
   * 
   * @return the {@link AIPState}.
   */
  String getState();

  /**
   * Get the {@link List} of submission {@link IPFileInterface}.
   *
   * @return a {@link List<IPFileInterface>}.
   */
  List<IPFileInterface> getSubmissions();

  /**
   * Add a submission.
   * 
   * @param submission
   *          the submission {@link IPFileInterface}.
   * @return the {@link AIP}.
   */
  AIP addSubmission(IPFileInterface submission);

  /**
   * Parse an {@link AIP} from the given source {@link Path}.
   *
   * @param source
   *          the source {@link Path}.
   * @return the parsed {@link AIP}.
   * @throws ParseException
   *           if some error occurred.
   */
  static AIP parse(final Path source) throws ParseException {
    throw new ParseException("One must implement static method parse in a concrete class");
  }

  /**
   * Parse an {@link AIP} from the given source {@link Path} and copy it to the
   * given destination {@link Path}.
   *
   * @param source
   *          the source {@link Path}.
   * @param destination
   *          the destination {@link Path}.
   * @return the parsed {@link AIP}.
   * @throws ParseException
   *           if some error occurred.
   */
  static AIP parse(final Path source, final Path destination) throws ParseException {
    throw new ParseException("One must implement static method parse in a concrete class");
  }

}
