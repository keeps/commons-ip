package org.roda_project.commons_ip2.validator;

import org.roda_project.commons_ip2.mets_v1_12.beans.Mets;
import org.roda_project.commons_ip2.validator.common.FolderManager;
import org.roda_project.commons_ip2.validator.common.InstatiateMets;
import org.roda_project.commons_ip2.validator.common.ZipManager;
import org.roda_project.commons_ip2.validator.component.administritiveMetadataComponent.AdministritiveMetadataComponentValidator;
import org.roda_project.commons_ip2.validator.component.descriptiveMetadataComponent.DescriptiveMetadataComponentValidator;
import org.roda_project.commons_ip2.validator.component.fileComponent.FileComponentValidator;
import org.roda_project.commons_ip2.validator.component.fileSectionComponent.FileSectionComponentValidator;
import org.roda_project.commons_ip2.validator.component.metsrootComponent.MetsComponentValidator;
import org.roda_project.commons_ip2.validator.component.ValidatorComponent;
import org.roda_project.commons_ip2.validator.component.metsrootComponent.MetsHeaderComponentValidator;
import org.roda_project.commons_ip2.validator.component.structuralMapComponent.StructuralMapComponentValidator;
import org.roda_project.commons_ip2.validator.constants.Constants;
import org.roda_project.commons_ip2.validator.observer.ProgressValidationLoggerObserver;
import org.roda_project.commons_ip2.validator.observer.ValidationObserver;
import org.roda_project.commons_ip2.validator.reporter.ValidationReporter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;

import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

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

  private List<ValidatorComponent> components;


  public EARKSIPValidator(Path earksipPath, Path reportPath){
    this.earksipPath = earksipPath.toAbsolutePath().normalize();
    reporter = new ValidationReporter(reportPath.toAbsolutePath().normalize());
    zipManager = new ZipManager();
    observer = new ProgressValidationLoggerObserver();
    folderManager = new FolderManager();
    ids = new ArrayList<>();
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
    ValidatorComponent fileComponent = new FileComponentValidator(Constants.CSIP_MODULE_NAME_0);
    fileComponent.setObserver(observer);
    fileComponent.setReporter(reporter);
    fileComponent.setZipManager(zipManager);
    fileComponent.setFolderManager(folderManager);
    fileComponent.setEARKSIPpath(earksipPath);
    try {
      boolean validFileComponent = fileComponent.validate();
      if(validFileComponent){
        InstatiateMets instatiateMets = new InstatiateMets(zipManager,folderManager);
        mets = instatiateMets.instatiateMetsFile(earksipPath,fileComponent.isZipFileFlag());
        for(ValidatorComponent component : components){
          component.setObserver(observer);
          component.setReporter(reporter);
          component.setZipManager(zipManager);
          component.setFolderManager(folderManager);
          component.setEARKSIPpath(earksipPath);
          component.setMets(mets);
          component.setIds(ids);
          component.setZipFileFlag(fileComponent.isZipFileFlag());
          boolean valid = component.validate();
          component.clean();
        }
      }
      if(reporter.getErrors() > 0){
        reporter.componentValidationFinish(Constants.VALIDATION_REPORT_SPECIFICATION_RESULT_INVALID);
      }
      else{
        reporter.componentValidationFinish(Constants.VALIDATION_REPORT_SPECIFICATION_RESULT_VALID);
      }
      observer.notifyIndicators( reporter.getErrors(), reporter.getSuccess(), reporter.getWarnings());
      reporter.close();
      observer.notifyFinishValidation();

    } catch (IOException | JAXBException | SAXException e){
      LOGGER.error("Could not parse file.", e);
    }
    return true;
  }
}
