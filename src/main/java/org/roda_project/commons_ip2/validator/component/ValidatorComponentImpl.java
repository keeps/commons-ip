package org.roda_project.commons_ip2.validator.component;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.roda_project.commons_ip2.mets_v1_12.beans.Mets;
import org.roda_project.commons_ip2.validator.common.FolderManager;
import org.roda_project.commons_ip2.validator.common.ZipManager;
import org.roda_project.commons_ip2.validator.constants.Constants;
import org.roda_project.commons_ip2.validator.constants.ConstantsCSIPspec;
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
  protected ZipManager zipManager = null;
  protected FolderManager folderManager = null;
  protected Mets mets = null;
  protected List<String> ids = null;
  private Map<String, ReporterDetails> results = new HashMap<>();
  protected String metsName;
  protected String metsPath;

  private String name = null;
  private boolean zipFileFlag = false;
  private boolean isRootMets = false;

  protected HashMap<String, Boolean> files = null;
  protected List<String> ianaMediaTypes = null;
  private List<ValidationObserver> observers = new ArrayList<>();

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
  public void addObserver(ValidationObserver observer) {
    this.observers.add(observer);
  }

  @Override
  public void removeObserver(ValidationObserver observer) {
    this.observers.remove(observer);
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
  public void setFiles(HashMap<String, Boolean> files) {
    this.files = files;
  }

  protected void notifyObserversValidationStarted(String moduleName, String ID) {
    for (ValidationObserver observer : observers) {
      observer.notifyStartValidationModule(moduleName, ID);
      observer.notifyStartStep(ID);
    }
  }

  protected void notifyObserversFinishModule(String moduleName) {
    for (ValidationObserver observer : observers) {
      observer.notifyFinishModule(moduleName);
    }
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

  protected void addResults(ReporterDetails details, String... specifications) {
    for (String specification : specifications) {
      addResult(specification, details);
    }
  }

  protected Map<String, ReporterDetails> getResults() {
    return this.results;
  }

  protected boolean isResultValid(String specification) {
    return results.get(specification).isValid() && !results.get(specification).isSkipped();
  }

  protected void addResult(String specification, ReporterDetails details) {
    if (results.containsKey(specification)) {
      ReporterDetails currentResult = results.get(specification);
      if(specification.equals(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP46_ID)) {
        LOGGER.debug("Current result: {}",  currentResult.getIssues());
        LOGGER.debug("Details: {}", details.getIssues());
      }


      // Merge current result with new test case validation outcome
      currentResult.addIssues(details.getIssues());
      currentResult.setSkipped(currentResult.isSkipped() && details.isSkipped());
      currentResult.setValid(currentResult.isValid() && details.isValid());
    } else {
      results.put(specification, details);
    }

  }

  @Override
  public void setIANAMediaTypes(List<String> ianaMediaTypes) {
    this.ianaMediaTypes = ianaMediaTypes;
  }
}
