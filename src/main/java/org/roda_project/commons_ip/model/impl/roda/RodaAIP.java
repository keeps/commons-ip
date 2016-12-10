package org.roda_project.commons_ip.model.impl.roda;

import java.nio.file.Path;

import org.roda_project.commons_ip.model.AIPInterface;
import org.roda_project.commons_ip.model.ParseException;
import org.roda_project.commons_ip.model.impl.AIPWrap;

/**
 * {@link AIPInterface} implementation that can read a RODA AIP from a folder.
 *
 * @author Rui Castro (rui.castro@gmail.com)
 */
public class RodaAIP extends AIPWrap {

  /**
   * Constructor.
   * 
   * @param aip
   *          the {@link AIPInterface} to wrap.
   */
  public RodaAIP(final AIPInterface aip) {
    super(aip);
  }

  /**
   * Reads a {@link RodaAIP} from the given folder.
   * 
   * @param source
   *          the source folder.
   * @return a {@link RodaAIP}.
   * @throws ParseException
   *           if some error occurs.
   */
  public static AIPInterface parse(final Path source) throws ParseException {
    return new RodaAIPReader(source).read();
  }

}
