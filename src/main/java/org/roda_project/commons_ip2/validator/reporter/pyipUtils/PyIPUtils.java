package org.roda_project.commons_ip2.validator.reporter.pyipUtils;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.roda_project.commons_ip2.validator.constants.Constants;
import org.roda_project.commons_ip2.validator.pyipModel.InformationPackage;
import org.roda_project.commons_ip2.validator.pyipModel.MetadataChecks;
import org.roda_project.commons_ip2.validator.pyipModel.MetadataResults;
import org.roda_project.commons_ip2.validator.pyipModel.MetadataStatus;
import org.roda_project.commons_ip2.validator.pyipModel.StructResults;
import org.roda_project.commons_ip2.validator.pyipModel.TestResult;
import org.roda_project.commons_ip2.validator.pyipModel.ValidationReport;
import org.roda_project.commons_ip2.validator.reporter.ReporterDetails;

/**
 * @author João Gomes <jgomes@keep.pt>
 */
public class PyIPUtils {

  public static ValidationReport createValidationReport(String name, String ipType, String checkumAlg, String ipPath,
    Map<String, ReporterDetails> results) throws IOException, NoSuchAlgorithmException {
    ValidationReport validationReport = new ValidationReport();
    validationReport.setPackage(createInformationPackage(name, checkumAlg, ipPath, ipType));
    validationReport.setUid(UUID.randomUUID());
    validationReport.setStructure(createStructureResults(results));
    validationReport.setMetadata(createMetadataResults(results));
    return validationReport;
  }

  public static InformationPackage createInformationPackage(String name, String checksumAlg, String ipPath,
    String ipType) throws IOException, NoSuchAlgorithmException {
    InformationPackage informationPackage = new InformationPackage();
    informationPackage.setDetails(InformationPackageUtils.createPackageDetails(name, checksumAlg, ipPath));
    informationPackage.setProfile(
      InformationPackageUtils.createProfileDetails(name, ipType, Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION));
    informationPackage.setRepresentations(InformationPackageUtils.createRepresentation(name));
    return informationPackage;
  }

  public static StructResults createStructureResults(Map<String, ReporterDetails> results) {
    StructResults structResults = new StructResults();
    structResults.setStatus(StructureResultsUtils.calculateStatus(results));
    structResults.setMessages(StructureResultsUtils.createStructureResults(results));
    return structResults;
  }

  public static MetadataResults createMetadataResults(Map<String, ReporterDetails> results) {
    MetadataResults metadataResults = new MetadataResults();
    metadataResults.setSchemaResults(MetadataResultsUtils.createSchemaResult(results));
    if (metadataResults.getSchemaResults().getStatus().equals(MetadataStatus.NOTVALID)) {
      MetadataChecks metadataChecks = new MetadataChecks();
      metadataChecks.setStatus(MetadataStatus.NOTVALID);
      List<TestResult> testResults = new ArrayList<>();
      metadataChecks.setMessages(testResults);
      metadataResults.setSchematronResults(metadataChecks);
    } else {
      metadataResults.setSchematronResults(MetadataResultsUtils.createSchematronResult(results));
    }
    return metadataResults;
  }
}
