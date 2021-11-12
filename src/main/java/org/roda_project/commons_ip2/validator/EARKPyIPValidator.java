package org.roda_project.commons_ip2.validator;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;

import org.roda_project.commons_ip2.validator.aipComponents.aipFileSectionComponent.AipFileSectionComponent;
import org.roda_project.commons_ip2.validator.common.InstatiateMets;
import org.roda_project.commons_ip2.validator.component.MetsValidator;
import org.roda_project.commons_ip2.validator.component.administritiveMetadataComponent.AdministritiveMetadataComponentValidator;
import org.roda_project.commons_ip2.validator.component.descriptiveMetadataComponent.DescriptiveMetadataComponentValidator;
import org.roda_project.commons_ip2.validator.component.fileComponent.StructureComponentValidator;
import org.roda_project.commons_ip2.validator.component.fileSectionComponent.FileSectionComponentValidator;
import org.roda_project.commons_ip2.validator.component.metsRootComponent.MetsComponentValidator;
import org.roda_project.commons_ip2.validator.component.metsRootComponent.MetsHeaderComponentValidator;
import org.roda_project.commons_ip2.validator.component.structuralMapComponent.StructuralMapComponentValidator;
import org.roda_project.commons_ip2.validator.constants.Constants;
import org.roda_project.commons_ip2.validator.constants.ConstantsCSIPspec;
import org.roda_project.commons_ip2.validator.observer.ValidationObserver;
import org.roda_project.commons_ip2.validator.pyipModel.IpType;
import org.roda_project.commons_ip2.validator.reporter.ReporterDetails;
import org.roda_project.commons_ip2.validator.reporter.ValidationReportOutputJSONPyIP;
import org.roda_project.commons_ip2.validator.sipComponents.sipFileSectionComponent.SipFileSectionComponent;
import org.roda_project.commons_ip2.validator.sipComponents.sipMetsRootComponent.SipMetsComponent;
import org.roda_project.commons_ip2.validator.sipComponents.sipMetsRootComponent.SipMetsHdrComponent;
import org.roda_project.commons_ip2.validator.state.MetsValidatorState;
import org.roda_project.commons_ip2.validator.state.StructureValidatorState;
import org.roda_project.commons_ip2.validator.utils.ResultsUtils;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

/**
 * @author João Gomes <jgomes@keep.pt>
 */
public class EARKPyIPValidator {

  /**
   * IP path.
   */
  private final Path earksipPath;

  /**
   * {@link ValidationReportOutputJSONPyIP}.
   */
  private final ValidationReportOutputJSONPyIP validationReportOutputJSONPyIP;
  /**
   * {@link StructureComponentValidator}.
   */
  private final StructureComponentValidator structureComponent;
  /**
   * the contextual structural state {@link StructureValidatorState}.
   */
  private final StructureValidatorState structureValidatorState;
  /**
   * List of CSIP components to validate.
   */
  private final List<MetsValidator> csipComponents = new ArrayList<>();
  /**
   * List of SIP components to validate.
   */
  private final List<MetsValidator> sipComponents = new ArrayList<>();
  /**
   * List of AIP components to validate.
   */
  private final List<MetsValidator> aipComponents = new ArrayList<>();
  /**
   * The contextual mets state {@link MetsValidatorState}.
   */
  private final MetsValidatorState metsValidatorState;

  /**
   * @param validationReportOutputJSONPyIP
   *          the {@link ValidationReportOutputJSONPyIP}
   * @throws IOException
   *           if some I/O error occurs
   * @throws ParserConfigurationException
   *           if some error occurs
   * @throws SAXException
   *           if some error occurs
   */
  public EARKPyIPValidator(final ValidationReportOutputJSONPyIP validationReportOutputJSONPyIP)
    throws IOException, ParserConfigurationException, SAXException {

    this.earksipPath = validationReportOutputJSONPyIP.getSipPath().toAbsolutePath().normalize();

    this.validationReportOutputJSONPyIP = validationReportOutputJSONPyIP;

    this.structureValidatorState = new StructureValidatorState(
      validationReportOutputJSONPyIP.getSipPath().toAbsolutePath().normalize());
    this.structureComponent = new StructureComponentValidator();
    this.metsValidatorState = new MetsValidatorState();
    setupComponents();
  }

  /**
   * Setup Validation Components.
   *
   * @throws IOException
   *           if some I/O error occurs.
   * @throws ParserConfigurationException
   *           if some error occur.
   * @throws SAXException
   *           if some error occur.
   */
  private void setupComponents() throws IOException, ParserConfigurationException, SAXException {
    this.csipComponents.add(new MetsComponentValidator());
    this.csipComponents.add(new MetsHeaderComponentValidator());
    this.csipComponents.add(new DescriptiveMetadataComponentValidator());
    this.csipComponents.add(new AdministritiveMetadataComponentValidator());
    this.csipComponents.add(new FileSectionComponentValidator());
    this.csipComponents.add(new StructuralMapComponentValidator());

    this.sipComponents.add(new SipMetsComponent());
    this.sipComponents.add(new SipMetsHdrComponent());
    this.sipComponents.add(new SipFileSectionComponent());

    this.aipComponents.add(new AipFileSectionComponent());
  }

  /**
   * Add {@link ValidationObserver} to the lists of observers.
   *
   * @param observer
   *          the {@link ValidationObserver}
   */
  public void addObserver(final ValidationObserver observer) {
    structureComponent.addObserver(observer);
    csipComponents.forEach(c -> c.addObserver(observer));
    sipComponents.forEach(c -> c.addObserver(observer));
  }

  /**
   * Remove {@link ValidationObserver} from the lists of observers.
   *
   * @param observer
   *          the {@link ValidationObserver}
   */
  public void removeObserver(final ValidationObserver observer) {
    structureComponent.removeObserver(observer);
    csipComponents.forEach(c -> c.removeObserver(observer));
    sipComponents.forEach(c -> c.removeObserver(observer));
  }

  /**
   * Validates the Information Package.
   * 
   * @return if the Information package is valid or not
   * @throws IOException
   *           if some I/O error occurs.
   * @throws NoSuchAlgorithmException
   *           if some error occurs in Checksum Calculation.
   */
  public boolean validate() throws IOException, NoSuchAlgorithmException {
    structureComponent.notifyObserversIPValidationStarted();
    try {
      final Map<String, ReporterDetails> structureValidationResults = structureComponent
        .validate(structureValidatorState);
      validationReportOutputJSONPyIP.getResults().putAll(structureValidationResults);

      if (validationReportOutputJSONPyIP.validFileComponent()) {
        final Map<String, InputStream> subMets;
        if (structureValidatorState.isZipFileFlag()) {
          metsValidatorState.setMetsFiles(structureValidatorState.getZipManager().getFiles(earksipPath));
          subMets = structureValidatorState.getZipManager().getSubMets(earksipPath);
        } else {
          metsValidatorState.setMetsFiles(structureValidatorState.getFolderManager().getFiles(earksipPath));
          subMets = structureValidatorState.getFolderManager().getSubMets(earksipPath);
        }

        if (subMets.size() > 0) {
          validateSubMets(subMets, structureValidatorState.isZipFileFlag());
        }
        validateRootMets();

        final ReporterDetails csipStr0 = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, "", true,
          false);
        csipStr0.setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION);
        validationReportOutputJSONPyIP.getResults().put(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP0_ID,
          csipStr0);
      }
    } catch (IOException | JAXBException | SAXException e) {
      final StringBuilder message = new StringBuilder();

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
          final SAXParseException e1 = (SAXParseException) cause;
          message.append(" (line: ").append(e1.getLineNumber()).append(", column: ").append(e1.getColumnNumber())
            .append(") - ");
        }
      }

      final ReporterDetails csipStr0 = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
        message.toString(), false, false);
      csipStr0.setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION);
      validationReportOutputJSONPyIP.getResults().put(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP0_ID,
        csipStr0);
    }

    validationReportOutputJSONPyIP
      .setIpType(metsValidatorState.getIpType() != null ? metsValidatorState.getIpType() : IpType.CSIP.toString());
    validationReportOutputJSONPyIP.writeReport();
    return validationReportOutputJSONPyIP.isValid();
  }

  /**
   * Iterate over all Components and merge all results.
   * 
   * @throws IOException
   *           if some I/O error occurs.
   */
  private void validateComponents() throws IOException {
    for (MetsValidator component : csipComponents) {
      final Map<String, ReporterDetails> componentResults = component.validate(structureValidatorState,
        metsValidatorState);
      ResultsUtils.mergeResults(validationReportOutputJSONPyIP.getResults(), componentResults);
    }
    validateIpTypeExtendedComponents();
  }

  /**
   * Validate METS files inside representations.
   * 
   * @param subMets
   *          the {@link java.util.HashMap<String,InputStream>} with path to sub
   *          METS and InputStream of file.
   * @param isZip
   *          flag if the Information Package is in compact format or if it is a
   *          folder.
   * @throws IOException
   *           If some I/O error occurs
   * @throws JAXBException
   *           If some error occurs
   * @throws SAXException
   *           If some error occurs
   */
  private void validateSubMets(final Map<String, InputStream> subMets, final boolean isZip)
    throws IOException, JAXBException, SAXException {
    for (Map.Entry<String, InputStream> entry : subMets.entrySet()) {
      final InstatiateMets instatiateMets = new InstatiateMets(entry.getValue());
      metsValidatorState.setMets(instatiateMets.instatiateMetsFile());
      metsValidatorState.setIpType(metsValidatorState.getMets().getMetsHdr().getOAISPACKAGETYPE());
      setupMetsValidatorState(entry.getKey(), isZip, false);
      validateComponents();
    }
  }

  /**
   * Validates the METS Root.
   * 
   * @throws IOException
   *           If some I/O error occurs
   * @throws JAXBException
   *           If some error occurs
   * @throws SAXException
   *           If some error occurs
   */
  private void validateRootMets() throws IOException, JAXBException, SAXException {
    final InputStream metsRootStream;
    final String ipPath;
    if (structureValidatorState.isZipFileFlag()) {
      metsRootStream = structureValidatorState.getZipManager().getMetsRootInputStream(earksipPath);
      ipPath = earksipPath.toString();
    } else {
      metsRootStream = structureValidatorState.getFolderManager().getMetsRootInputStream(earksipPath);
      ipPath = earksipPath.resolve("METS.xml").toString();
    }

    final InstatiateMets metsRoot = new InstatiateMets(metsRootStream);
    metsValidatorState.setMetsPath(earksipPath.toString());
    metsValidatorState.setMetsName(ipPath);
    metsValidatorState.setIsRootMets(true);
    metsValidatorState.setMets(metsRoot.instatiateMetsFile());
    validateComponents();
  }

  /**
   * Setup State of METS.
   *
   * @param key
   *          the METS file path
   * @param isZip
   *          Flag if Information package is compacted or not
   * @param isRootMets
   *          Flag if METS file is root or representation METS
   */
  private void setupMetsValidatorState(final String key, final boolean isZip, final boolean isRootMets) {
    this.metsValidatorState.setMetsName(key);
    this.metsValidatorState.setIsRootMets(isRootMets);
    if (isZip) {
      final StringBuilder metsPath = new StringBuilder();
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

  /**
   * Validate SIP specifications or AIP Specifications if the type is SIP or AIP.
   *
   * @throws IOException
   *           if some I/O error occurs.
   *
   */
  private void validateIpTypeExtendedComponents() throws IOException {
    if (metsValidatorState.getIpType() != null && metsValidatorState.getIpType().equals("SIP")) {
      validateSIPComponents();
    } else if (metsValidatorState.getIpType() != null && metsValidatorState.getIpType().equals("AIP")) {
      validateAIPComponets();
    }
  }

  /**
   * Iterate over SIP components and merge the results with CSIP validations.
   *
   * @throws IOException
   *           if some I/O error occurs.
   */

  private void validateSIPComponents() throws IOException {
    aipComponents.clear();
    for (MetsValidator component : sipComponents) {
      if (component instanceof SipFileSectionComponent) {
        ((SipFileSectionComponent) component).setIsToValidate(ResultsUtils.isResultValid(
          validationReportOutputJSONPyIP.getResults(), ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP58_ID));
      }
      if (component instanceof SipMetsHdrComponent) {
        ((SipMetsHdrComponent) component).setIsToValidateMetsHdr(ResultsUtils.isResultValid(
          validationReportOutputJSONPyIP.getResults(), ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP117_ID));
        if (validationReportOutputJSONPyIP.getResults()
          .get(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP10_ID) != null) {
          ((SipMetsHdrComponent) component).setIsToValidateAgents(ResultsUtils.isResultValid(
            validationReportOutputJSONPyIP.getResults(), ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP10_ID));
        }
      }
      final Map<String, ReporterDetails> sipComponentResults = component.validate(structureValidatorState,
        metsValidatorState);
      ResultsUtils.mergeResults(validationReportOutputJSONPyIP.getResults(), sipComponentResults);
    }
  }

  /**
   * Iterate over AIP components and merges the results with CSIP validations
   * results.
   *
   * @throws IOException
   *           if some I/O error occurs.
   */
  private void validateAIPComponets() throws IOException {
    sipComponents.clear();
    for (MetsValidator component : aipComponents) {
      if (component instanceof AipFileSectionComponent) {
        ((AipFileSectionComponent) component).setIsToValidate(ResultsUtils.isResultValid(
          validationReportOutputJSONPyIP.getResults(), ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP58_ID));
      }
      final Map<String, ReporterDetails> aipComponentResults = component.validate(structureValidatorState,
        metsValidatorState);
      ResultsUtils.mergeResults(validationReportOutputJSONPyIP.getResults(), aipComponentResults);
    }
  }
}
