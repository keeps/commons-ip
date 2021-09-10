package org.roda_project.commons_ip2.validator;

import org.roda_project.commons_ip2.mets_v1_12.beans.Mets;
import org.roda_project.commons_ip2.validator.common.FolderManager;
import org.roda_project.commons_ip2.validator.common.InstatiateMets;
import org.roda_project.commons_ip2.validator.common.ZipManager;
import org.roda_project.commons_ip2.validator.component.administritiveMetadataComponent.AdministritiveMetadataComponentValidator;
import org.roda_project.commons_ip2.validator.component.descriptiveMetadataComponent.DescriptiveMetadataComponentValidator;
import org.roda_project.commons_ip2.validator.component.fileComponent.StructureComponentValidator;
import org.roda_project.commons_ip2.validator.component.fileSectionComponent.FileSectionComponentValidator;
import org.roda_project.commons_ip2.validator.component.metsrootComponent.MetsComponentValidator;
import org.roda_project.commons_ip2.validator.component.ValidatorComponent;
import org.roda_project.commons_ip2.validator.component.metsrootComponent.MetsHeaderComponentValidator;
import org.roda_project.commons_ip2.validator.component.structuralMapComponent.StructuralMapComponentValidator;
import org.roda_project.commons_ip2.validator.constants.Constants;
import org.roda_project.commons_ip2.validator.observer.ProgressValidationLoggerObserver;
import org.roda_project.commons_ip2.validator.observer.ValidationObserver;
import org.roda_project.commons_ip2.validator.reporter.ReporterDetails;
import org.roda_project.commons_ip2.validator.reporter.ValidationReporter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author João Gomes <jgomes@keep.pt>
 */

public class EARKSIPValidator {
  private static final Logger LOGGER = LoggerFactory.getLogger(EARKSIPValidator.class);
  private Path earksipPath;

  private ValidationReporter reporter;
  private ZipManager zipManager;
  private ValidationObserver observer;
  private FolderManager folderManager;
  private Mets mets;
  private List<String> ids;
  private TreeMap<String, ReporterDetails> results;

  private List<ValidatorComponent> components;
  private HashMap<String,Boolean> files;


  public EARKSIPValidator(Path earksipPath, Path reportPath){
    this.earksipPath = earksipPath.toAbsolutePath().normalize();
    reporter = new ValidationReporter(reportPath.toAbsolutePath().normalize(),this.earksipPath);
    zipManager = new ZipManager();
    observer = new ProgressValidationLoggerObserver();
    folderManager = new FolderManager();
    ids = new ArrayList<>();

    results = new TreeMap<>(new Comparator<String>() {
      public int compare(String o1, String o2) {
        int c1;
        int c2;
        if(o1.contains("R") && o2.contains("R")){
          c1 = Integer.parseInt(o1.split("R")[1]);
          c2 = Integer.parseInt(o2.split("R")[1]);
          return compareInt(c1,c2);
        }
        else{
          if(o1.contains("R") && !o2.contains("R")){
            return -1;
          }
          else{
            if(!o1.contains("R") && o2.contains("R")){
              return 1;
            }
            else{
              if(o1.contains("C") && o2.contains("C")){
                c1 = Integer.parseInt(o1.split("P")[1]);
                c2 = Integer.parseInt(o2.split("P")[1]);
                return compareInt(c1,c2);
              }
              else{
                if(o1.contains("C") && !o2.contains("C")){
                  return -1;
                }
                else{
                  if(!o1.contains("C") && o2.contains("C")){
                    return 1;
                  }
                  else{
                    c1 = Integer.parseInt(o1.split("P")[1]);
                    c2 = Integer.parseInt(o2.split("P")[1]);
                    return compareInt(c1,c2);
                  }
                }
              }


            }
          }
        }
      }
    });
    setupComponents();
  }

  private  void setupComponents() {
    components = new ArrayList<>();
    ValidatorComponent metsComponent = new MetsComponentValidator(Constants.CSIP_MODULE_NAME_1);
    components.add(metsComponent);
    ValidatorComponent metsHeaderComponent = new MetsHeaderComponentValidator(Constants.CSIP_MODULE_NAME_2);
    components.add(metsHeaderComponent);
    ValidatorComponent descriptiveMetadataComponent = new DescriptiveMetadataComponentValidator(Constants.CSIP_MODULE_NAME_3);
    components.add(descriptiveMetadataComponent);
    ValidatorComponent administritiveMetadataComponent = new AdministritiveMetadataComponentValidator(Constants.CSIP_MODULE_NAME_4);
    components.add(administritiveMetadataComponent);
    ValidatorComponent fileSectionComponent = new FileSectionComponentValidator(Constants.CSIP_MODULE_NAME_5);
    components.add(fileSectionComponent);
    ValidatorComponent structuralMapComponent = new StructuralMapComponentValidator(Constants.CSIP_MODULE_NAME_6);
    components.add(structuralMapComponent);
  }

  public boolean validate() {
    observer.notifyValidationStart();
    ValidatorComponent structureComponent = new StructureComponentValidator(Constants.CSIP_MODULE_NAME_0,earksipPath);
    structureComponent.setObserver(observer);
    structureComponent.setReporter(reporter);
    structureComponent.setZipManager(zipManager);
    structureComponent.setFolderManager(folderManager);
    structureComponent.setEARKSIPpath(earksipPath);
    structureComponent.setResults(results);
    try {
      structureComponent.validate();
      if(validFileComponent()){
        HashMap<String,InputStream> subMets;
        if(structureComponent.isZipFileFlag()){
          files = zipManager.getFiles(earksipPath);
          subMets = zipManager.getSubMets(earksipPath);
          if(subMets.size() > 0){
            validateSubMets(subMets,structureComponent.isZipFileFlag());
            InputStream metsRootStream = zipManager.getMetsRootInputStream(earksipPath);
            InstatiateMets metsRoot = new InstatiateMets(metsRootStream);
            mets = metsRoot.instatiateMetsFile();
            validateComponents(structureComponent.isZipFileFlag(),earksipPath.toString(),true);

          }
          else{
            InputStream metsRootStream = zipManager.getMetsRootInputStream(earksipPath);
            InstatiateMets metsRoot = new InstatiateMets(metsRootStream);
            mets = metsRoot.instatiateMetsFile();
            validateComponents(structureComponent.isZipFileFlag(),earksipPath.toString(),true);
          }
        }
        else{
          subMets = folderManager.getSubMets(earksipPath);
          if(subMets.size() > 0){
            validateSubMets(subMets,structureComponent.isZipFileFlag());
            InputStream metsRootStream = folderManager.getMetsRootInputStream(earksipPath);
            InstatiateMets metsRoot = new InstatiateMets(metsRootStream);
            mets = metsRoot.instatiateMetsFile();
            validateComponents(structureComponent.isZipFileFlag(),earksipPath.toString(),true);

          }
          else{
            InputStream metsRootStream = folderManager.getMetsRootInputStream(earksipPath);
            InstatiateMets metsRoot = new InstatiateMets(metsRootStream);
            mets = metsRoot.instatiateMetsFile();
            validateComponents(structureComponent.isZipFileFlag(),earksipPath.toString(),true);
          }
        }
      }
      reporter.validationResults(results);
      if(reporter.getErrors() > 0){
        reporter.componentValidationFinish(Constants.VALIDATION_REPORT_SPECIFICATION_RESULT_INVALID);
      }
      else{
        reporter.componentValidationFinish(Constants.VALIDATION_REPORT_SPECIFICATION_RESULT_VALID);
      }
      observer.notifyIndicators( reporter.getErrors(), reporter.getSuccess(), reporter.getWarnings());
      reporter.close();
      observer.notifyFinishValidation();

    } catch (IOException | JAXBException | SAXException e) {
      LOGGER.error("Could not parse file.", e);
    }
    return true;
  }

  public void validateComponents(boolean isZip,String key, boolean isRootMets) throws IOException {
    for(ValidatorComponent component : components){
          component.setObserver(observer);
          component.setReporter(reporter);
          component.setZipManager(zipManager);
          component.setFolderManager(folderManager);
          component.setEARKSIPpath(earksipPath);
          component.setMets(mets);
          component.setIds(ids);
          component.setFiles(files);
          component.setZipFileFlag(isZip);
          component.setMetsName(key);
          component.setIsRootMets(isRootMets);
          String metsPath = "";
          for(String s: key.split("/")){ 
            if(!s.equals("METS.xml")){
              metsPath += s + "/";
            }
          }
          component.setMetsPath(metsPath);
          component.setResults(results);
          component.validate();
          component.clean();
        }
  }

  public void validateSubMets(HashMap<String,InputStream> subMets, boolean isZip) throws IOException, JAXBException, SAXException {
    for(Map.Entry<String, InputStream> entry : subMets.entrySet()){
      InstatiateMets instatiateMets = new InstatiateMets(entry.getValue());
      mets = instatiateMets.instatiateMetsFile();
      validateComponents(isZip,entry.getKey(),false);
    }
  }

  public boolean validFileComponent(){
    for(Map.Entry<String,ReporterDetails> result: results.entrySet()){
      String strCsip = result.getKey();
      if(strCsip.equals("CSIPSTR1") || strCsip.equals("CSIPSTR4")){
        if(!result.getValue().isValid()){
          return false;
        }
      }
    }
    return true;
  }

  public int compareInt(int c1, int c2){
    if(c1 < c2){
      return -1;
    }
    else{
      if(c1 > c2){
        return 1;
      }
      return 0;
    }
  }
}
