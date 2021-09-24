package org.roda_project.commons_ip2.validator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;

import org.roda_project.commons_ip2.mets_v1_12.beans.Mets;
import org.roda_project.commons_ip2.validator.common.FolderManager;
import org.roda_project.commons_ip2.validator.common.InstatiateMets;
import org.roda_project.commons_ip2.validator.common.ZipManager;
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
import org.roda_project.commons_ip2.validator.utils.ResultsUtils;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

/**
 * @author João Gomes <jgomes@keep.pt>
 */

public class EARKSIPValidator {
  private final Path earksipPath;
  private final Path reportPath;

  private final ValidationReportOutputJson validationReportOutputJson;
  private final ZipManager zipManager;
  private final List<ValidationObserver> observers;
  private final FolderManager folderManager;
  private Mets mets;
  private final List<String> metsInternalIds;
  private final TreeMap<String, ReporterDetails> results;

  private ValidatorComponent structureComponent;
  private List<ValidatorComponent> metsComponents = new ArrayList<>();
  private HashMap<String, Boolean> files;
  private List<String> ianaMediaTypes;

  public EARKSIPValidator(Path earksipPath, Path reportPath)
    throws IOException, ParserConfigurationException, SAXException {

    this.earksipPath = earksipPath.toAbsolutePath().normalize();
    this.reportPath = reportPath.toAbsolutePath().normalize();

    this.validationReportOutputJson = new ValidationReportOutputJson(this.reportPath, this.earksipPath);
    this.zipManager = new ZipManager();
    this.observers = new ArrayList<>();

    this.folderManager = new FolderManager();
    this.metsInternalIds = new ArrayList<>();
    this.ianaMediaTypes = new BufferedReader(new InputStreamReader(
      getClass().getClassLoader().getResourceAsStream(Constants.PATH_RESOURCES_CSIP_VOCABULARY_IANA_MEDIA_TYPES),
      StandardCharsets.UTF_8)).lines().collect(Collectors.toList());
    this.results = new TreeMap<>(new RequirementsComparator());
    setupStructureComponent();
    setupComponents();
  }

  private void setupStructureComponent() {
    this.structureComponent = new StructureComponentValidator(earksipPath);
    this.structureComponent.setReporter(validationReportOutputJson);
    this.structureComponent.setZipManager(zipManager);
    this.structureComponent.setFolderManager(folderManager);
    this.structureComponent.setEARKSIPpath(earksipPath);
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
    observers.add(observer);
    structureComponent.addObserver(observer);
    metsComponents.forEach(c -> c.addObserver(observer));
  }

  public void removeObserver(ValidationObserver observer) {
    observers.remove(observer);
    structureComponent.removeObserver(observer);
    metsComponents.forEach(c -> c.removeObserver(observer));
  }

  public boolean validate() {
    observers.forEach(ValidationObserver::notifyValidationStart);
    try {
      Map<String, ReporterDetails> structureValidationResults = structureComponent.validate();
      results.putAll(structureValidationResults);

      if (validFileComponent()) {
        if (!ianaMediaTypes.contains("text/plain")) {
          ianaMediaTypes.add("text/plain");
        }
        Map<String, InputStream> subMets;
        if (structureComponent.isZipFileFlag()) {
          files = zipManager.getFiles(earksipPath);
          subMets = zipManager.getSubMets(earksipPath);
          if (subMets.size() > 0) {
            validateSubMets(subMets, structureComponent.isZipFileFlag());
            InputStream metsRootStream = zipManager.getMetsRootInputStream(earksipPath);
            InstatiateMets metsRoot = new InstatiateMets(metsRootStream);
            mets = metsRoot.instatiateMetsFile();
            validateComponents(structureComponent.isZipFileFlag(), earksipPath.toString(), true);

          } else {
            InputStream metsRootStream = zipManager.getMetsRootInputStream(earksipPath);
            InstatiateMets metsRoot = new InstatiateMets(metsRootStream);
            mets = metsRoot.instatiateMetsFile();
            validateComponents(structureComponent.isZipFileFlag(), earksipPath.toString(), true);
          }
        } else {
          subMets = folderManager.getSubMets(earksipPath);
          if (subMets.size() > 0) {
            validateSubMets(subMets, structureComponent.isZipFileFlag());
            InputStream metsRootStream = folderManager.getMetsRootInputStream(earksipPath);
            InstatiateMets metsRoot = new InstatiateMets(metsRootStream);
            mets = metsRoot.instatiateMetsFile();
            validateComponents(structureComponent.isZipFileFlag(), earksipPath.resolve("METS.xml").toString(), true);
          } else {
            InputStream metsRootStream = folderManager.getMetsRootInputStream(earksipPath);
            InstatiateMets metsRoot = new InstatiateMets(metsRootStream);
            mets = metsRoot.instatiateMetsFile();
            validateComponents(structureComponent.isZipFileFlag(), earksipPath.resolve("METS.xml").toString(), true);
          }
        }
        ReporterDetails csipStr0 = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, "", true,
          false);
        csipStr0.setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION);
        results.put(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP0_ID, csipStr0);
      }

      validationReportOutputJson.validationResults(results);
      if (validationReportOutputJson.getErrors() > 0) {
        validationReportOutputJson.componentValidationFinish(Constants.VALIDATION_REPORT_SPECIFICATION_RESULT_INVALID);
      } else {
        validationReportOutputJson.componentValidationFinish(Constants.VALIDATION_REPORT_SPECIFICATION_RESULT_VALID);
      }
      notifyIndicatorsObservers();
      validationReportOutputJson.close();
      observers.forEach(ValidationObserver::notifyFinishValidation);

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
      results.put(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP0_ID, csipStr0);

      validationReportOutputJson.validationResults(results);
      validationReportOutputJson.componentValidationFinish(Constants.VALIDATION_REPORT_SPECIFICATION_RESULT_INVALID);
      observers.forEach(ValidationObserver::notifyValidationStart);
      notifyIndicatorsObservers();
      validationReportOutputJson.close();
      observers.forEach(ValidationObserver::notifyFinishValidation);
    }
    return validationReportOutputJson.getErrors() == 0;
  }

  public void validateComponents(boolean isZip, String key, boolean isRootMets) throws IOException {
    for (ValidatorComponent component : metsComponents) {
      component.setReporter(validationReportOutputJson);
      component.setZipManager(zipManager);
      component.setFolderManager(folderManager);
      component.setEARKSIPpath(earksipPath);
      component.setMets(mets);
      component.setIds(metsInternalIds);
      component.setFiles(files);
      component.setZipFileFlag(isZip);
      component.setMetsName(key);
      component.setIsRootMets(isRootMets);
      component.setIANAMediaTypes(ianaMediaTypes);
      if (isZip) {
        StringBuilder metsPath = new StringBuilder();
        for (String path : key.split("/")) {
          if (!path.equals("METS.xml")) {
            metsPath.append(path).append("/");
          }
        }
        component.setMetsPath(metsPath.toString());
      } else {
        component.setMetsPath(Paths.get(key).getParent().toString());
      }
      Map<String, ReporterDetails> componentResults = component.validate();
      ResultsUtils.mergeResults(results, componentResults);
      component.clean();
    }
  }

  public void validateSubMets(Map<String, InputStream> subMets, boolean isZip)
    throws IOException, JAXBException, SAXException {
    for (Map.Entry<String, InputStream> entry : subMets.entrySet()) {
      InstatiateMets instatiateMets = new InstatiateMets(entry.getValue());
      mets = instatiateMets.instatiateMetsFile();
      validateComponents(isZip, entry.getKey(), false);
    }
  }

  public boolean validFileComponent() {
    for (Map.Entry<String, ReporterDetails> result : results.entrySet()) {
      String strCsip = result.getKey();
      if ((strCsip.equals("CSIPSTR1") || strCsip.equals("CSIPSTR4")) && !result.getValue().isValid()) {
        return false;
      }
    }
    return true;
  }

  public void notifyIndicatorsObservers() {
    for (ValidationObserver observer : observers) {
      observer.notifyIndicators(this.validationReportOutputJson.getErrors(),
        this.validationReportOutputJson.getSuccess(), this.validationReportOutputJson.getWarnings(),
        this.validationReportOutputJson.getNotes(), this.validationReportOutputJson.getSkipped());
    }
  }

  public int compareInt(int c1, int c2) {
    if (c1 < c2) {
      return -1;
    } else {
      if (c1 > c2) {
        return 1;
      }
      return 0;
    }
  }

  private class RequirementsComparator implements Comparator<String> {

    private int calculateWeight(String o) {
      int c;

      if (o.startsWith("CSIPSTR")) {
        c = 1000;
        c += Integer.parseInt(o.substring("CSIPSTR".length()));
      } else if (o.startsWith("CSIP")) {
        c = 2000;
        c += Integer.parseInt(o.substring("CSIP".length()));
      } else if (o.startsWith("SIP")) {
        c = 4000;
        c += Integer.parseInt(o.substring("SIP".length()));
      } else {
        c = 9000;
      }
      return c;
    }

    @Override
    public int compare(String o1, String o2) {
      return compareInt(calculateWeight(o1), calculateWeight(o2));
    }
  }
}
