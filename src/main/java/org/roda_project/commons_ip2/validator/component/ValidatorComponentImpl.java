package org.roda_project.commons_ip2.validator.component;

import org.roda_project.commons_ip2.mets_v1_12.beans.Mets;
import org.roda_project.commons_ip2.model.IPConstants;
import org.roda_project.commons_ip2.utils.METSUtils;
import org.roda_project.commons_ip2.utils.ResourceResolver;
import org.roda_project.commons_ip2.validator.common.FolderManager;
import org.roda_project.commons_ip2.validator.common.ZipManager;
import org.roda_project.commons_ip2.validator.constants.Constants;
import org.roda_project.commons_ip2.validator.constants.ConstantsCSIPspec;
import org.roda_project.commons_ip2.validator.observer.ValidationObserver;
import org.roda_project.commons_ip2.validator.reporter.ReporterDetails;
import org.roda_project.commons_ip2.validator.reporter.ValidationReporter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

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

  protected Path getEARKSIPpath() {
    return  path;
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

  protected ValidationReporter getReporter() {
    return reporter;
  }

  @Override
  public void setMets(Mets mets){
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
  public void setFolderManager(FolderManager folderManager){
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
  public void setIds(List<String> ids){
    this.ids = ids;
  }

  @Override
  public void setResults(TreeMap<String,ReporterDetails> results){this.results = results;}

//  protected void validationOutcomeFailed(String specification,String ID, String detail){
//    reporter.componentValidationResult(specification, ID, Constants.VALIDATION_REPORT_SPECIFICATION_TESTING_OUTCOME_FAILED, detail);
//    if(ConstantsCSIPspec.getSpecificationLevel(ID).equals("MUST")){
//      reporter.countErrors();
//    }
//    else{
//      reporter.countWarnings();
//    }
//    observer.notifyFinishStep(ID);
//  }
//
//  protected void validationOutcomePassed(String specification,String ID, String detail){
//    reporter.componentValidationResult(specification, ID, Constants.VALIDATION_REPORT_SPECIFICATION_TESTING_OUTCOME_PASSED, detail);
//    reporter.countSuccess();
//    observer.notifyFinishStep(ID);
//  }
//
//  protected void validationOutcomeSkipped(String specification, String ID,String detail){
//    reporter.componentValidationResult(specification,ID, Constants.VALIDATION_REPORT_SPECIFICATION_TESTING_OUTCOME_SKIPPED,detail);
//    reporter.countSkipped();
//    observer.notifyFinishStep(ID);
//  }

  protected void validationInit(String moduleName, String ID){
    observer.notifyStartValidationModule(moduleName,ID);
    observer.notifyStartStep(ID);
  }

  protected void validationPathOutcomeFailed(String id, String detail){
    reporter.componentPathValidationResult(id,Constants.VALIDATION_REPORT_SPECIFICATION_TESTING_OUTCOME_FAILED, detail);
    reporter.countErrors();
  }

  protected void addId(String id){
    ids.add(id);
  }

  protected boolean checkId(String id){
    return ids.contains(id);
  }

  @Override
  public void setMetsName(String metsName) {
    this.metsName = metsName;
  }

  @Override
  public void setMetsPath(String metsPath) { this.metsPath = metsPath;}

  protected void addResult(String specification, ReporterDetails details){
    if(results.containsKey(specification)){
      ReporterDetails tmp = results.get(specification);
      if(tmp.isSkipped()){
        if(!details.isSkipped()){
          if(!details.isValid()){
            for(String issue: details.getIssues()){
              tmp.addIssue(issue);
              tmp.countErrors();
            }
          }
          tmp.setSkipped(false);
        }
        else{
          for(String issue: details.getIssues()){
            tmp.addIssue(issue);
            tmp.countErrors();
          }
        }
      }
      else{
        if(tmp.isValid()){
          if(!details.isValid()){
            tmp.setValid(details.isValid());
            for(String issue: details.getIssues()){
              tmp.addIssue(issue);
              tmp.countErrors();
            }
          }
        }
        else{
          if(!details.isValid()){
            for(String issue: details.getIssues()){
              tmp.addIssue(issue);
              tmp.countErrors();
            }
          }
        }
      }
      results.replace(specification,tmp);
    }
    else{
      if(!details.isValid()) details.countErrors();
      results.put(specification,details);
    }

  }
}
