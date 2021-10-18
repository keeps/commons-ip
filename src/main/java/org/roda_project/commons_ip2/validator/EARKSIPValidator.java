package org.roda_project.commons_ip2.validator;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;

import org.roda_project.commons_ip2.mets_v1_12.beans.Mets;
import org.roda_project.commons_ip2.validator.common.FolderManager;
import org.roda_project.commons_ip2.validator.common.InstatiateMets;
import org.roda_project.commons_ip2.validator.common.ZipManager;
import org.roda_project.commons_ip2.validator.component.MetsValidator;
import org.roda_project.commons_ip2.validator.component.ValidatorComponent;
import org.roda_project.commons_ip2.validator.component.administritiveMetadataComponent.AdministritiveMetadataComponentValidator;
import org.roda_project.commons_ip2.validator.component.descriptiveMetadataComponent.DescriptiveMetadataComponentValidator;
import org.roda_project.commons_ip2.validator.component.fileComponent.StructureComponentValidator;
import org.roda_project.commons_ip2.validator.component.fileSectionComponent.FileSectionComponentValidator;
import org.roda_project.commons_ip2.validator.component.metsrootComponent.MetsComponentValidator;
import org.roda_project.commons_ip2.validator.component.metsrootComponent.MetsHeaderComponentValidator;
import org.roda_project.commons_ip2.validator.component.structuralMapComponent.StructuralMapComponentValidator;
import org.roda_project.commons_ip2.validator.constants.Constants;
import org.roda_project.commons_ip2.validator.constants.ConstantsCSIPspec;
import org.roda_project.commons_ip2.validator.observer.ValidationObserver;
import org.roda_project.commons_ip2.validator.reporter.ReporterDetails;
import org.roda_project.commons_ip2.validator.reporter.ValidationReportOutputJson;
import org.roda_project.commons_ip2.validator.state.MetsValidatorState;
import org.roda_project.commons_ip2.validator.state.StructureValidatorState;
import org.roda_project.commons_ip2.validator.utils.ResultsUtils;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

/**
 * @author João Gomes <jgomes@keep.pt>
 */

public class EARKSIPValidator {
  private final Path earksipPath;

  private final ValidationReportOutputJson validationReportOutputJson;
  private final StructureComponentValidator structureComponent;
  private final StructureValidatorState structureValidatorState;
  private final List<MetsValidator> metsComponents = new ArrayList<>();
  private final MetsValidatorState metsValidatorState;

  public EARKSIPValidator(ValidationReportOutputJson reportOutputJson)
    throws IOException, ParserConfigurationException, SAXException {

    this.earksipPath = reportOutputJson.getSipPath().toAbsolutePath().normalize();

    this.validationReportOutputJson = reportOutputJson;

    this.structureValidatorState = new StructureValidatorState(reportOutputJson.getSipPath().toAbsolutePath().normalize());
    this.structureComponent = new StructureComponentValidator();
    this.metsValidatorState = new MetsValidatorState();
    setupComponents();
  }

  private void setupComponents() throws IOException, ParserConfigurationException, SAXException {
    this.metsComponents.add(new MetsComponentValidator());
    this.metsComponents.add(new MetsHeaderComponentValidator());
    this.metsComponents.add(new DescriptiveMetadataComponentValidator());
    this.metsComponents.add(new AdministritiveMetadataComponentValidator());
    this.metsComponents.add(new FileSectionComponentValidator());
    this.metsComponents.add(new StructuralMapComponentValidator());
  }

  public void addObserver(ValidationObserver observer) {
    structureComponent.addObserver(observer);
    metsComponents.forEach(c -> c.addObserver(observer));
  }

  public void removeObserver(ValidationObserver observer) {
    structureComponent.removeObserver(observer);
    metsComponents.forEach(c -> c.removeObserver(observer));
  }

  public boolean validate() {
    structureComponent.notifyObserversIPValidationStarted();
    try {
      Map<String, ReporterDetails> structureValidationResults = structureComponent.validate(structureValidatorState);
      validationReportOutputJson.getResults().putAll(structureValidationResults);

      if (validationReportOutputJson.validFileComponent()) {
        Map<String, InputStream> subMets;
        if (structureValidatorState.isZipFileFlag()) {
          metsValidatorState.setMetsFiles(structureValidatorState.getZipManager().getFiles(earksipPath));
          subMets = structureValidatorState.getZipManager().getSubMets(earksipPath);
        } else {
          subMets = structureValidatorState.getFolderManager().getSubMets(earksipPath);
        }

        if (subMets.size() > 0) {
          validateSubMets(subMets, structureValidatorState.isZipFileFlag());
        }
        validateRootMets();

        ReporterDetails csipStr0 = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, "", true,
          false);
        csipStr0.setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION);
        validationReportOutputJson.getResults().put(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP0_ID, csipStr0);
      }

      validationReportOutputJson.validationResults();
      validationReportOutputJson.writeFinalResult();
      notifyIndicatorsObservers();
      validationReportOutputJson.close();
      structureComponent.notifyObserversIPValidationFinished();

    } catch (IOException | JAXBException | SAXException e) {
      StringBuilder message = new StringBuilder();

      Throwable cause = e;
      if (e.getMessage() != null) {
        message.append("[").append(e.getClass().getSimpleName()).append("] ").append(e.getMessage());
      }
      while (cause.getCause() != null) {
        cause = cause.getCause();
        if (message.length() > 0) {
          message.append(" caused by ");
        }

        message.append("[").append(cause.getClass().getSimpleName()).append("] ").append(cause.getMessage());

        if (cause instanceof SAXParseException) {
          SAXParseException e1 = (SAXParseException) cause;
          message.append(" (line: ").append(e1.getLineNumber()).append(", column: ").append(e1.getColumnNumber())
            .append(") - ");
        }
      }

      ReporterDetails csipStr0 = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
        message.toString(), false, false);
      csipStr0.setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION);
      validationReportOutputJson.getResults().put(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP0_ID, csipStr0);

      validationReportOutputJson.validationResults();
      validationReportOutputJson.writeFinalResult();
      notifyIndicatorsObservers();
      validationReportOutputJson.close();
      structureComponent.notifyObserversIPValidationFinished();
    }
    return validationReportOutputJson.getErrors() == 0;
  }

  private void validateComponents() throws IOException {
    for (MetsValidator component : metsComponents) {
      Map<String, ReporterDetails> componentResults = component.validate(structureValidatorState, metsValidatorState);
      ResultsUtils.mergeResults(validationReportOutputJson.getResults(),componentResults);
    }
  }

  private void validateSubMets(Map<String, InputStream> subMets, boolean isZip)
    throws IOException, JAXBException, SAXException {
    for (Map.Entry<String, InputStream> entry : subMets.entrySet()) {
      InstatiateMets instatiateMets = new InstatiateMets(entry.getValue());
      metsValidatorState.setMets(instatiateMets.instatiateMetsFile());
      setupMetsValidatorState(entry.getKey(), isZip, false);
      validateComponents();
    }
  }

  private void validateRootMets() throws IOException, JAXBException, SAXException {
    InputStream metsRootStream;
    String ipPath;
    if (structureValidatorState.isZipFileFlag()) {
      metsRootStream = structureValidatorState.getZipManager().getMetsRootInputStream(earksipPath);
      ipPath = earksipPath.toString();
    } else {
      metsRootStream = structureValidatorState.getFolderManager().getMetsRootInputStream(earksipPath);
      ipPath = earksipPath.resolve("METS.xml").toString();
    }

    InstatiateMets metsRoot = new InstatiateMets(metsRootStream);
    metsValidatorState.setMetsPath(ipPath);
    metsValidatorState.setMetsName(ipPath);
    metsValidatorState.setIsRootMets(true);
    metsValidatorState.setMets(metsRoot.instatiateMetsFile());
    validateComponents();
  }

  private void setupMetsValidatorState(String key, boolean isZip, boolean isRootMets) {
    this.metsValidatorState.setMetsName(key);
    this.metsValidatorState.setIsRootMets(isRootMets);
    if (isZip) {
      StringBuilder metsPath = new StringBuilder();
      for (String path : key.split("/")) {
        if (!path.equals("METS.xml")) {
          metsPath.append(path).append("/");
        }
      }
      this.metsValidatorState.setMetsPath(metsPath.toString());
    } else {
      this.metsValidatorState.setMetsPath(Paths.get(key).getParent().toString());
    }
  }

  public void notifyIndicatorsObservers() {
    structureComponent.notifyIndicators(this.validationReportOutputJson.getErrors(),
        this.validationReportOutputJson.getSuccess(), this.validationReportOutputJson.getWarnings(),
        this.validationReportOutputJson.getNotes(), this.validationReportOutputJson.getSkipped());
  }
}
