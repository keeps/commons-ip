/**
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE file at the root of the source
 * tree and available online at
 *
 * https://github.com/keeps/commons-ip
 */
package org.roda_project.commons_ip.model;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import org.roda_project.commons_ip.utils.IPEnums.IPType;

/**
 * This is an Archival Information Package (AIP).
 *
 * @author Hélder Silva (hsilva@keep.pt)
 * @author Rui Castro (rui.castro@gmail.com)
 */
public abstract class AIP extends IP {

  /**
   * List of submission files.
   */
  private List<IPFile> submissions;

  /**
   * Constructor.
   */
  public AIP() {
    super();
    this.setType(IPType.AIP);
    this.submissions = new ArrayList<>();
  }

  /**
   * Constructor.
   * 
   * @param aipId
   *          the ID.
   */
  public AIP(final String aipId) {
    this();
    setId(aipId);
  }

  /**
   * Constructor.
   * 
   * @param aipId
   *          the ID.
   * @param contentType
   *          the {@link IPContentType}.
   */
  public AIP(final String aipId, final IPContentType contentType) {
    this(aipId);
    setContentType(contentType);
  }

  @Override
  public String toString() {
    return "AIP [super=" + super.toString() + ", submissions=" + submissions + "]";
  }

  /**
   * Get the {@link List} of submission {@link IPFile}.
   * 
   * @return a {@link List<IPFile>}.
   */
  public List<IPFile> getSubmissions() {
    return submissions;
  }

  /**
   * Parse an {@link AIP} from the given source {@link Path}.
   * 
   * @param source
   *          the source {@link Path}.
   * @return the parsed {@link AIP}.
   * @throws ParseException
   *           if some error occurred.
   */
  public static AIP parse(final Path source) throws ParseException {
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
  public static AIP parse(final Path source, final Path destination) throws ParseException {
    throw new ParseException("One must implement static method parse in a concrete class");
  }

}
