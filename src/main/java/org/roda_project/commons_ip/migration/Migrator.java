package org.roda_project.commons_ip.migration;

import java.nio.file.Path;

import org.roda_project.commons_ip.model.MigrationException;

public interface Migrator {
  Path convert(Path source) throws MigrationException;
}
