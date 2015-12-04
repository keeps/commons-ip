package org.roda_project.commons_ip.parse;

import java.nio.file.Path;

import org.roda_project.commons_ip.model.MigrationException;
import org.roda_project.commons_ip.model.SIP;

public interface Parser {
  SIP parse(Path source) throws MigrationException;
}
