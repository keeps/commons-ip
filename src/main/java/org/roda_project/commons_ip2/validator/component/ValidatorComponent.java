package org.roda_project.commons_ip2.validator.component;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

import org.roda_project.commons_ip2.mets_v1_12.beans.Mets;
import org.roda_project.commons_ip2.validator.common.FolderManager;
import org.roda_project.commons_ip2.validator.common.ZipManager;
import org.roda_project.commons_ip2.validator.observer.ValidationObserver;
import org.roda_project.commons_ip2.validator.reporter.ReporterDetails;
import org.roda_project.commons_ip2.validator.reporter.ValidationReporter;

/**
 * @author João Gomes <jgomes@keep.pt>
 */

public interface ValidatorComponent {

  void setEARKSIPpath(Path path);

  void setReporter(ValidationReporter reporter);

  void setMets(Mets mets);

  void setZipManager(ZipManager zipManager);

  void setFolderManager(FolderManager folderManager);

  void setObserver(ValidationObserver observer);

  void validate() throws IOException;

  boolean isZipFileFlag();

  void setZipFileFlag(boolean zipFileFlag);

  boolean isRootMets();

  void setIsRootMets(boolean isRootMets);

  void setIds(List<String> ids);

  void setMetsName(String name);

  void setMetsPath(String metsPath);

  void setResults(TreeMap<String, ReporterDetails> results);

  void setFiles(HashMap<String, Boolean> files);

  void setIANAMediaTypes(List<String> ianaMediaTypes);

  void clean();
}
