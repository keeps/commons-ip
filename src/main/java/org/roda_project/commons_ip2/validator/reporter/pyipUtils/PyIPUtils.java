package org.roda_project.commons_ip2.validator.reporter.pyipUtils;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.roda_project.commons_ip2.validator.constants.Constants;
import org.roda_project.commons_ip2.validator.model.pyip.InformationPackage;
import org.roda_project.commons_ip2.validator.model.pyip.MetadataChecks;
import org.roda_project.commons_ip2.validator.model.pyip.MetadataResults;
import org.roda_project.commons_ip2.validator.model.pyip.MetadataStatus;
import org.roda_project.commons_ip2.validator.model.pyip.StructResults;
import org.roda_project.commons_ip2.validator.model.pyip.StructStatus;
import org.roda_project.commons_ip2.validator.model.pyip.TestResult;
import org.roda_project.commons_ip2.validator.model.pyip.ValidationReport;
import org.roda_project.commons_ip2.validator.reporter.ReporterDetails;

/** {@author João Gomes <jgomes@keep.pt>}. */
public final class PyIPUtils {

  private PyIPUtils() {
    // do nothing
  }

  /**
   * Initializes the PyIP report {@link ValidationReport}.
   *
   * @param name
   *          the {@link String} name of the IP
   * @param ipType
   *          the {@link String} type of the IP
   * @param checkumAlg
   *          the {@link String} algorithm to calculate the checksum of the IP
   * @param ipPath
   *          the {@link String} IP path
   * @param results
   *          the {@link Map} with all validation results
   * @return {@link ValidationReport}.
   * @throws IOException
   *           if some I/O error occurs.
   * @throws NoSuchAlgorithmException
   *           if the Cryptographic algorithm is not available
   */
  public static ValidationReport createValidationReport(final String name, final String ipType, final String checkumAlg,
    final String ipPath, final Map<String, ReporterDetails> results) throws IOException, NoSuchAlgorithmException {
    final ValidationReport validationReport = new ValidationReport();
    validationReport.setPackage(createInformationPackage(name, checkumAlg, ipPath, ipType));
    validationReport.setUid(UUID.randomUUID());
    validationReport.setStructure(createStructureResults(results));
    if (!validationReport.getStructure().getStatus().equals(StructStatus.NOTWELLFORMED)) {
      validationReport.setMetadata(createMetadataResults(results));
    } else {
      validationReport.setMetadata(createInvalidMetadataResults());
    }
    return validationReport;
  }

  /**
   * Creates an {@link InformationPackage}.
   *
   * @param name
   *          the {@link String} name of the IP
   * @param checksumAlg
   *          the {@link String} algorithm to calculate the checksum of the IP
   * @param ipPath
   *          the {@link String} IP path
   * @param ipType
   *          the {@link String} type of the IP
   * @return an {@link InformationPackage}
   * @throws IOException
   *           if some I/O error occurs.
   * @throws NoSuchAlgorithmException
   *           if the Cryptographic algorithm is not available
   */
  public static InformationPackage createInformationPackage(final String name, final String checksumAlg,
    final String ipPath, final String ipType) throws IOException, NoSuchAlgorithmException {
    final InformationPackage informationPackage = new InformationPackage();
    informationPackage.setDetails(InformationPackageUtils.createPackageDetails(name, checksumAlg, ipPath));
    informationPackage.setProfile(
      InformationPackageUtils.createProfileDetails(name, ipType, Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION));
    informationPackage.setRepresentations(InformationPackageUtils.createRepresentation(name));
    return informationPackage;
  }

  /**
   * Creates the {@link StructResults}.
   *
   * @param results
   *          {@link Map} with all validation results.
   * @return {@link StructResults}
   */
  public static StructResults createStructureResults(final Map<String, ReporterDetails> results) {
    final StructResults structResults = new StructResults();
    structResults.setStatus(StructureResultsUtils.calculateStatus(results));
    structResults.setMessages(StructureResultsUtils.createStructureResults(results));
    return structResults;
  }

  /**
   * Creates the {@link MetadataResults}.
   *
   * @param results
   *          {@link Map} with all validation results.
   * @return {@link MetadataResults}.
   */
  public static MetadataResults createMetadataResults(final Map<String, ReporterDetails> results) {
    final MetadataResults metadataResults = new MetadataResults();
    metadataResults.setSchemaResults(MetadataResultsUtils.createSchemaResult(results));
    if (metadataResults.getSchemaResults().getStatus().equals(MetadataStatus.NOTVALID)) {
      final MetadataChecks metadataChecks = new MetadataChecks();
      metadataChecks.setStatus(MetadataStatus.NOTVALID);
      final List<TestResult> testResults = new ArrayList<>();
      metadataChecks.setMessages(testResults);
      metadataResults.setSchematronResults(metadataChecks);
    } else {
      metadataResults.setSchematronResults(MetadataResultsUtils.createSchematronResult(results));
    }
    return metadataResults;
  }

  /**
   * Creates invalid {@link MetadataResults} if IP doesn't pass in structure
   * validation.
   *
   * @return {@link MetadataResults}.
   */
  public static MetadataResults createInvalidMetadataResults() {
    final MetadataResults metadataResults = new MetadataResults();
    final MetadataChecks metadataChecks = new MetadataChecks();
    metadataChecks.setStatus(MetadataStatus.NOTVALID);
    metadataChecks.setMessages(new ArrayList<>());
    metadataResults.schemaResults(metadataChecks);
    metadataResults.schematronResults(metadataChecks);
    return metadataResults;
  }
}
