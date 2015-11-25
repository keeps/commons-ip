/**
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE file at the root of the source
 * tree and available online at
 *
 * https://github.com/keeps/commons-ip
 */
package org.roda_project.commons_ip.validation;

import java.nio.file.Path;

import org.roda_project.commons_ip.validation.model.ValidationReport;

public interface Validator {
  ValidationReport isSIPValid(Path sipPath);
}
