package org.roda_project.commons_ip.migration;

import java.nio.file.Path;

public interface Migrator {
  Path convert(Path source) throws MigrationException;
}
