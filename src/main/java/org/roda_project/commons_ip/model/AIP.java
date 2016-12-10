/**
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE file at the root of the source
 * tree and available online at
 *
 * https://github.com/keeps/commons-ip
 */
package org.roda_project.commons_ip.model;

import java.util.ArrayList;
import java.util.List;

import org.roda_project.commons_ip.utils.IPEnums.AIPState;
import org.roda_project.commons_ip.utils.IPEnums.IPType;

/**
 * This is an Archival Information Package (AIP).
 *
 * @author Hélder Silva (hsilva@keep.pt)
 * @author Rui Castro (rui.castro@gmail.com)
 */
public abstract class AIP extends IP implements AIPInterface {

  /**
   * The state.
   */
  private AIPState state;

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
   * @param id
   *          the ID.
   */
  public AIP(final String id) {
    this();
    setId(id);
  }

  /**
   * Constructor.
   * 
   * @param id
   *          the ID.
   * @param contentType
   *          the {@link IPContentType}.
   */
  public AIP(final String id, final IPContentType contentType) {
    this(id);
    setContentType(contentType);
  }

  @Override
  public String toString() {
    return "AIP [super=" + super.toString() + ", submissions=" + submissions + "]";
  }

  @Override
  public void setState(final AIPState state) {
    this.state = state;
  }

  @Override
  public AIPState getState() {
    return state;
  }

  @Override
  public List<IPFile> getSubmissions() {
    return submissions;
  }

  @Override
  public AIPInterface addSubmission(final IPFile submission) {
    this.submissions.add(submission);
    return this;
  }

}
