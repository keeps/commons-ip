package org.roda_project.commons_ip2.validator.component;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

import org.roda_project.commons_ip2.mets_v1_12.beans.Mets;
import org.roda_project.commons_ip2.validator.common.FolderManager;
import org.roda_project.commons_ip2.validator.common.ZipManager;
import org.roda_project.commons_ip2.validator.constants.Constants;
import org.roda_project.commons_ip2.validator.observer.ValidationObserver;
import org.roda_project.commons_ip2.validator.reporter.ReporterDetails;
import org.roda_project.commons_ip2.validator.reporter.ValidationReporter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author João Gomes <jgomes@keep.pt>
 */
public abstract class ValidatorComponentImpl implements ValidatorComponent {
  private static final Logger LOGGER = LoggerFactory.getLogger(ValidatorComponentImpl.class);

  protected Path path = null;
  private ValidationReporter reporter = null;
  protected ValidationObserver observer = null;
  protected ZipManager zipManager = null;
  protected FolderManager folderManager = null;
  protected Mets mets = null;
  protected List<String> ids = null;
  private TreeMap<String, ReporterDetails> results;
  protected String metsName;
  protected String metsPath;

  private String name = null;
  private boolean zipFileFlag = false;
  private boolean isRootMets = false;

  protected HashMap<String, Boolean> files = null;

  protected Path getEARKSIPpath() {
    return path;
  }

  @Override
  public void setEARKSIPpath(Path path) {
    this.path = path;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public boolean isZipFileFlag() {
    return zipFileFlag;
  }

  @Override
  public void setZipFileFlag(boolean zipFileFlag) {
    this.zipFileFlag = zipFileFlag;
  }

  @Override
  public boolean isRootMets() {
    return this.isRootMets;
  }

  @Override
  public void setIsRootMets(boolean isRootMets) {
    this.isRootMets = isRootMets;
  }

  protected ValidationReporter getReporter() {
    return reporter;
  }

  @Override
  public void setMets(Mets mets) {
    this.mets = mets;
  }

  @Override
  public void setReporter(ValidationReporter reporter) {
    this.reporter = reporter;
  }

  @Override
  public void setZipManager(ZipManager zipManager) {
    this.zipManager = zipManager;
  }

  @Override
  public void setFolderManager(FolderManager folderManager) {
    this.folderManager = folderManager;
  }

  @Override
  public void setObserver(ValidationObserver observer) {
    this.observer = observer;
  }

  @Override
  public void clean() {
    this.zipManager.closeZipFile();
  }

  @Override
  public void setIds(List<String> ids) {
    this.ids = ids;
  }

  @Override
  public void setResults(TreeMap<String, ReporterDetails> results) {
    this.results = results;
  }

  @Override
  public void setFiles(HashMap<String, Boolean> files) {
    this.files = files;
  }

  protected void validationInit(String moduleName, String ID) {
    observer.notifyStartValidationModule(moduleName, ID);
    observer.notifyStartStep(ID);
  }

  protected void validationPathOutcomeFailed(String id, String detail) {
    reporter.componentPathValidationResult(id, Constants.VALIDATION_REPORT_SPECIFICATION_TESTING_OUTCOME_FAILED,
      detail);
    reporter.countErrors();
  }

  protected void addId(String id) {
    ids.add(id);
  }

  protected boolean checkId(String id) {
    for (String s : ids) {
      if (s.equals(id)) {
        return true;
      }
    }
    return false;
  }

  @Override
  public void setMetsName(String metsName) {
    this.metsName = metsName;
  }

  @Override
  public void setMetsPath(String metsPath) {
    this.metsPath = metsPath;
  }

  protected void addResult(String specification, ReporterDetails details) {
    if (results.containsKey(specification)) {
      ReporterDetails tmp = results.get(specification);
      if (tmp.isSkipped()) {
        if (!details.isSkipped()) {
          if (!details.isValid()) {
            for (String issue : details.getIssues()) {
              tmp.addIssue(issue);
              tmp.countErrors();
            }
          }
          tmp.setSkipped(false);
        } else {
          for (String issue : details.getIssues()) {
            tmp.addIssue(issue);
          }
        }
      } else {
        if (tmp.isValid()) {
          if (!details.isValid()) {
            tmp.setValid(details.isValid());
            for (String issue : details.getIssues()) {
              tmp.addIssue(issue);
              tmp.countErrors();
            }
          }
        } else {
          if (!details.isValid()) {
            for (String issue : details.getIssues()) {
              tmp.addIssue(issue);
              tmp.countErrors();
            }
          }
        }
      }
      results.replace(specification, tmp);
    } else {
      if (!details.isValid())
        details.countErrors();
      results.put(specification, details);
    }

  }
}
