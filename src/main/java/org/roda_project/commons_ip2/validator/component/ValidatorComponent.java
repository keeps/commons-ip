package org.roda_project.commons_ip2.validator.component;

import org.roda_project.commons_ip2.validator.common.ZipManager;
import org.roda_project.commons_ip2.validator.observer.ValidationObserver;
import org.roda_project.commons_ip2.validator.reporter.ValidationReporter;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.nio.file.Path;

/**
 * @author João Gomes <jgomes@keep.pt>
 */

public interface ValidatorComponent {

  void setEARKSIPpath(Path path);

  void setReporter(ValidationReporter reporter);

  void setZipManager(ZipManager zipManager);

  void setObserver(ValidationObserver observer);

  boolean validate() throws SAXException, ParserConfigurationException, IOException;

  void clean();
}
