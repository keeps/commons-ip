package org.eark.validation;

import java.nio.file.Path;

import org.eark.validation.model.ValidationReport;

public interface Validator {
	ValidationReport isSIPValid(Path sipPath);
}
