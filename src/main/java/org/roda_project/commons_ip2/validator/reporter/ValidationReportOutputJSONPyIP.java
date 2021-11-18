package org.roda_project.commons_ip2.validator.reporter;

import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.TreeMap;
import org.roda_project.commons_ip2.validator.pyipModel.ChecksumAlg;
import org.roda_project.commons_ip2.validator.pyipModel.MetadataStatus;
import org.roda_project.commons_ip2.validator.pyipModel.StructStatus;
import org.roda_project.commons_ip2.validator.pyipModel.ValidationReport;
import org.roda_project.commons_ip2.validator.reporter.pyipUtils.PyIPUtils;

/** {@author João Gomes <jgomes@keep.pt>}. */
public class ValidationReportOutputJSONPyIP {

  private final Path sipPath;
  private final Path reportPath;

  private final Map<String, ReporterDetails> results = new TreeMap<>(new RequirementsComparator());
  private String ipType = "";
  private ValidationReport validationReport = null;

  public ValidationReportOutputJSONPyIP(Path path, Path sipPath) {
    this.sipPath = sipPath;
    this.reportPath = path;
  }

  public void setIpType(String ipType) {
    this.ipType = ipType;
  }

  public Path getSipPath() {
    return sipPath;
  }

  public Map<String, ReporterDetails> getResults() {
    return results;
  }

  /**
   * Write the PyIP report.
   *
   * @throws IOException if some I/O error occurs.
   * @throws NoSuchAlgorithmException if the Cryptographic algorithm is not available
   */
  public void writeReport() throws IOException, NoSuchAlgorithmException {
    validationReport =
        PyIPUtils.createValidationReport(
            sipPath.getFileName().toString(),
            ipType,
            ChecksumAlg.SHA1.toString(),
            sipPath.toString(),
            results);

    ObjectMapper objectMapper = new ObjectMapper();

    ObjectWriter objectWriter = objectMapper.writer(new DefaultPrettyPrinter());
    objectWriter.writeValue(reportPath.toFile(), validationReport);
  }

  /**
   * Check if the structure of the IP is valid.
   *
   * @return if the IP passes on the structure validations
   */
  public boolean validFileComponent() {
    for (Map.Entry<String, ReporterDetails> result : results.entrySet()) {
      String strCsip = result.getKey();
      if ((strCsip.equals("CSIPSTR1") || strCsip.equals("CSIPSTR4"))
          && !result.getValue().isValid()) {
        return false;
      }
    }
    return true;
  }

  /**
   * Check if the IP is valid .
   *
   * @return if the IP is valid or not
   */
  public boolean isValid() {
    StructStatus structureStatus = validationReport.getStructure().getStatus();
    MetadataStatus schemaStatus = validationReport.getMetadata().getSchemaResults().getStatus();
    MetadataStatus schematronStatus =
        validationReport.getMetadata().getSchematronResults().getStatus();
    return !structureStatus.equals(StructStatus.NOTWELLFORMED)
        && !schemaStatus.equals(MetadataStatus.NOTVALID)
        && !schematronStatus.equals(MetadataStatus.NOTVALID);
  }
}
