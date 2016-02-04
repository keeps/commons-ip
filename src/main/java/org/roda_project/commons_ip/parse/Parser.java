/**
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE file at the root of the source
 * tree and available online at
 *
 * https://github.com/keeps/commons-ip
 */
package org.roda_project.commons_ip.parse;

import java.nio.file.Path;

import org.roda_project.commons_ip.model.ParseException;
import org.roda_project.commons_ip.model.SIP;

public interface Parser {
  SIP parse(Path source) throws ParseException;
}
