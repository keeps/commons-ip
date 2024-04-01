package org.roda_project.commons_ip2.validator;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;

import org.roda_project.commons_ip2.cli.model.exception.UnmarshallerException;
import org.roda_project.commons_ip2.validator.common.InstatiateMets;
import org.roda_project.commons_ip2.validator.components.MetsValidator;
import org.roda_project.commons_ip2.validator.components.StructureValidatorImpl;
import org.roda_project.commons_ip2.validator.components.administritiveMetadataComponent.AdministritiveMetadataComponentValidator204;
import org.roda_project.commons_ip2.validator.components.administritiveMetadataComponent.AdministritiveMetadataComponentValidator210;
import org.roda_project.commons_ip2.validator.components.aipFileSectionComponent.AipFileSectionComponent204;
import org.roda_project.commons_ip2.validator.components.aipFileSectionComponent.AipFileSectionComponent210;
import org.roda_project.commons_ip2.validator.components.descriptiveMetadataComponent.DescriptiveMetadataComponentValidator204;
import org.roda_project.commons_ip2.validator.components.descriptiveMetadataComponent.DescriptiveMetadataComponentValidator210;
import org.roda_project.commons_ip2.validator.components.fileComponent.StructureComponentValidator204;
import org.roda_project.commons_ip2.validator.components.fileSectionComponent.FileSectionComponentValidator204;
import org.roda_project.commons_ip2.validator.components.fileSectionComponent.FileSectionComponentValidator210;
import org.roda_project.commons_ip2.validator.components.metsRootComponent.metsHeaderValidator.MetsHeaderComponentValidator204;
import org.roda_project.commons_ip2.validator.components.metsRootComponent.metsHeaderValidator.MetsHeaderComponentValidator210;
import org.roda_project.commons_ip2.validator.components.metsRootComponent.metsValidator.MetsComponentValidator204;
import org.roda_project.commons_ip2.validator.components.metsRootComponent.metsValidator.MetsComponentValidator210;
import org.roda_project.commons_ip2.validator.components.sipFileSectionComponent.SipFileSectionComponent204;
import org.roda_project.commons_ip2.validator.components.sipFileSectionComponent.SipFileSectionComponent210;
import org.roda_project.commons_ip2.validator.components.sipMetsRootComponent.sipMetsComponent.SipMetsComponent204;
import org.roda_project.commons_ip2.validator.components.sipMetsRootComponent.sipMetsComponent.SipMetsComponent210;
import org.roda_project.commons_ip2.validator.components.sipMetsRootComponent.sipMetsHdrComponent.SipMetsHdrComponent204;
import org.roda_project.commons_ip2.validator.components.sipMetsRootComponent.sipMetsHdrComponent.SipMetsHdrComponent210;
import org.roda_project.commons_ip2.validator.components.structuralMapComponent.StructuralMapComponentValidator204;
import org.roda_project.commons_ip2.validator.components.structuralMapComponent.StructuralMapComponentValidator210;
import org.roda_project.commons_ip2.validator.constants.Constants;
import org.roda_project.commons_ip2.validator.constants.ConstantsCSIPspec;
import org.roda_project.commons_ip2.validator.model.pyip.IpType;
import org.roda_project.commons_ip2.validator.observer.ValidationObserver;
import org.roda_project.commons_ip2.validator.reporter.ReporterDetails;
import org.roda_project.commons_ip2.validator.reporter.ValidationReportOutputJSONPyIP;
import org.roda_project.commons_ip2.validator.state.MetsValidatorState;
import org.roda_project.commons_ip2.validator.state.StructureValidatorState;
import org.roda_project.commons_ip2.validator.utils.ResultsUtils;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import jakarta.xml.bind.JAXBException;

/** {@author João Gomes <jgomes@keep.pt>}. */
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
   * {@link StructureValidatorImpl}.
   */
  private final StructureValidatorImpl structureComponent;
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
   * Initializes Validation Objects.
   *
   * @param validationReportOutputJSONPyIP
   *          the {@link ValidationReportOutputJSONPyIP}
   * @throws IOException
   *           if some I/O error occurs
   * @throws ParserConfigurationException
   *           if some error occurs
   * @throws SAXException
   *           if some error occurs
   */
  public EARKPyIPValidator(final ValidationReportOutputJSONPyIP validationReportOutputJSONPyIP, String version)
    throws IOException, ParserConfigurationException, SAXException {

    this.earksipPath = validationReportOutputJSONPyIP.getSipPath().toAbsolutePath().normalize();

    this.validationReportOutputJSONPyIP = validationReportOutputJSONPyIP;

    this.structureValidatorState = new StructureValidatorState(
      validationReportOutputJSONPyIP.getSipPath().toAbsolutePath().normalize());
    this.structureComponent = new StructureComponentValidator204();
    this.metsValidatorState = new MetsValidatorState();
    setupComponents(version);
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
  private void setupComponents(String version) throws IOException, ParserConfigurationException, SAXException {

    // method aux para povoar o array consoante a versão

    this.csipComponents.addAll(getComponentsForVersion(version, "csipComponents"));
    this.sipComponents.addAll(getComponentsForVersion(version, "sipComponents"));
    this.aipComponents.addAll(getComponentsForVersion(version, "aipComponents"));
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

        if (!subMets.isEmpty()) {
          validateSubMets(subMets, structureValidatorState.isZipFileFlag());
        }
        validateRootMets();

        final ReporterDetails csipStr0 = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, "", true,
          false);
        csipStr0.setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION);
        validationReportOutputJSONPyIP.getResults().put(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP0_ID,
          csipStr0);
      }
    } catch (IOException | UnmarshallerException e) {
      final StringBuilder message = new StringBuilder();

      Throwable cause = e;
      if (e.getMessage() != null) {
        message.append(Constants.OPEN_SQUARE_BRACKET).append(e.getClass().getSimpleName())
          .append(Constants.CLOSE_SQUARE_BRACKET).append(" ").append(e.getMessage());
      }
      while (cause.getCause() != null) {
        cause = cause.getCause();
        if (!message.isEmpty()) {
          message.append(" caused by ");
        }

        message.append(Constants.OPEN_SQUARE_BRACKET).append(cause.getClass().getSimpleName())
          .append(Constants.CLOSE_SQUARE_BRACKET).append(cause.getMessage());

        if (cause instanceof SAXParseException e1) {
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
   *          the {@link Map} with path to sub METS and InputStream of file.
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
    throws IOException, UnmarshallerException {
    for (Map.Entry<String, InputStream> entry : subMets.entrySet()) {
      final InstatiateMets instatiateMets = new InstatiateMets(entry.getValue());
      metsValidatorState.setMets(instatiateMets.instatiateMetsFile(entry.getKey()));
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
   * @throws UnmarshallerException
   *           If some error occurs
   */
  private void validateRootMets() throws IOException, UnmarshallerException {
    final InputStream metsRootStream;
    final String ipPath;
    if (structureValidatorState.isZipFileFlag()) {
      metsRootStream = structureValidatorState.getZipManager().getMetsRootInputStream(earksipPath);
      ipPath = earksipPath.toString();
    } else {
      metsRootStream = structureValidatorState.getFolderManager().getMetsRootInputStream(earksipPath);
      ipPath = earksipPath.resolve(Constants.METS_FILE).toString();
    }

    final InstatiateMets metsRoot = new InstatiateMets(metsRootStream);
    metsValidatorState.setMetsPath(earksipPath.toString());
    metsValidatorState.setMetsName(ipPath);
    metsValidatorState.setIsRootMets(true);
    metsValidatorState.setMets(metsRoot.instatiateMetsFile(Constants.METS_FILE));
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
      for (String path : key.split(Constants.SEPARATOR)) {
        if (!path.equals(Constants.METS_FILE)) {
          metsPath.append(path).append(Constants.SEPARATOR);
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
      if (component instanceof SipFileSectionComponent204) {
        ((SipFileSectionComponent204) component).setIsToValidate(ResultsUtils.isResultValid(
          validationReportOutputJSONPyIP.getResults(), ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP58_ID));
      }
      if (component instanceof SipFileSectionComponent210) {
        ((SipFileSectionComponent210) component).setIsToValidate(ResultsUtils.isResultValid(
          validationReportOutputJSONPyIP.getResults(), ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP58_ID));
      }
      if (component instanceof SipMetsHdrComponent204) {
        ((SipMetsHdrComponent204) component).setIsToValidateMetsHdr(ResultsUtils.isResultValid(
          validationReportOutputJSONPyIP.getResults(), ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP117_ID));
        if (validationReportOutputJSONPyIP.getResults()
          .get(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP10_ID) != null) {
          ((SipMetsHdrComponent204) component).setIsToValidateAgents(ResultsUtils.isResultValid(
            validationReportOutputJSONPyIP.getResults(), ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP10_ID));
        }
      }
      if (component instanceof SipMetsHdrComponent210) {
        ((SipMetsHdrComponent210) component).setIsToValidateMetsHdr(ResultsUtils.isResultValid(
          validationReportOutputJSONPyIP.getResults(), ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP117_ID));
        if (validationReportOutputJSONPyIP.getResults()
          .get(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP10_ID) != null) {
          ((SipMetsHdrComponent210) component).setIsToValidateAgents(ResultsUtils.isResultValid(
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
      if (component instanceof AipFileSectionComponent204) {
        ((AipFileSectionComponent204) component).setIsToValidate(ResultsUtils.isResultValid(
          validationReportOutputJSONPyIP.getResults(), ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP58_ID));
      }
      if (component instanceof AipFileSectionComponent210) {
        ((AipFileSectionComponent210) component).setIsToValidate(ResultsUtils.isResultValid(
          validationReportOutputJSONPyIP.getResults(), ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP58_ID));
      }
      final Map<String, ReporterDetails> aipComponentResults = component.validate(structureValidatorState,
        metsValidatorState);
      ResultsUtils.mergeResults(validationReportOutputJSONPyIP.getResults(), aipComponentResults);
    }
  }

  private List<MetsValidator> getComponentsForVersion(String version, String type)
    throws IOException, ParserConfigurationException, SAXException {
    List<MetsValidator> values = new ArrayList<>();
    if (version.equals("2.0.4")) {
      if (type.equals("csipComponents")) {
        values.add(new MetsComponentValidator204());
        values.add(new MetsHeaderComponentValidator204());
        values.add(new DescriptiveMetadataComponentValidator204());
        values.add(new AdministritiveMetadataComponentValidator204());
        values.add(new FileSectionComponentValidator204());
        values.add(new StructuralMapComponentValidator204());
      } else if (type.equals("sipComponents")) {
        values.add(new SipMetsComponent204());
        values.add(new SipMetsHdrComponent204());
        values.add(new SipFileSectionComponent204());
      } else {
        values.add(new AipFileSectionComponent204());
      }
    } else {
      if (type.equals("csipComponents")) {
        values.add(new MetsComponentValidator210());
        values.add(new MetsHeaderComponentValidator210());
        values.add(new DescriptiveMetadataComponentValidator210());
        values.add(new AdministritiveMetadataComponentValidator210());
        values.add(new FileSectionComponentValidator210());
        values.add(new StructuralMapComponentValidator210());
      } else if (type.equals("sipComponents")) {
        values.add(new SipMetsComponent210());
        values.add(new SipMetsHdrComponent210());
        values.add(new SipFileSectionComponent210());
      } else {
        values.add(new AipFileSectionComponent210());
      }
    }
    return values;
  }
}
