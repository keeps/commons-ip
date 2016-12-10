package org.roda_project.commons_ip.model.impl;

import java.nio.file.Path;

import org.roda_project.commons_ip.model.AIP;
import org.roda_project.commons_ip.utils.IPException;

/**
 * AIP basic implementation. This implementation just holds values in memory. It
 * can't read or write AIPs.
 * 
 * Build methods: {@link #build(Path)}, {@link #build(Path, String)} throw a
 * {@link IPException}.
 *
 * Parse methods: {@link #parse(Path)}, {@link #parse(Path, Path)} throw a
 * {@link org.roda_project.commons_ip.model.ParseException}.
 *
 * @author Rui Castro (rui.castro@gmail.com)
 */
public class BasicAIP extends AIP {

  @Override
  public Path build(final Path destinationDirectory) throws IPException, InterruptedException {
    throw new IPException("Not implemented");
  }

  @Override
  public Path build(final Path destinationDirectory, final String fileNameWithoutExtension)
    throws IPException, InterruptedException {
    throw new IPException("Not implemented");
  }
}
