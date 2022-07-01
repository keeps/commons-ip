package org.roda_project.commons_ip2.validator.reporter;

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

import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

/** {@author João Gomes <jgomes@keep.pt>}. */
public class ValidationReportOutputJSONPyIP {

  /**
   * The IP {@link Path}.
   */
  private final Path sipPath;
  /**
   * The Report {@link Path}.
   */
  private final Path reportPath;

  /**
   * The {@link Map} of results.
   */
  private final Map<String, ReporterDetails> results = new TreeMap<>(new RequirementsComparator());
  /**
   * The IP type.
   */
  private String ipType = "";
  /**
   * {@link ValidationReport}.
   */
  private ValidationReport validationReport = null;

  /**
   * Constructor of {@link ValidationReportOutputJSONPyIP}.
   * 
   * @param path
   *          {@link Path}.
   * @param sipPath
   *          {@link Path}.
   */
  public ValidationReportOutputJSONPyIP(final Path path, final Path sipPath) {
    this.sipPath = sipPath;
    this.reportPath = path;
  }

  public void setIpType(final String ipType) {
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
   * @throws IOException
   *           if some I/O error occurs.
   * @throws NoSuchAlgorithmException
   *           if the Cryptographic algorithm is not available
   */
  public void writeReport() throws IOException, NoSuchAlgorithmException {
    validationReport = PyIPUtils.createValidationReport(sipPath.getFileName().toString(), ipType,
      ChecksumAlg.SHA1.toString(), sipPath.toString(), results);

    final ObjectMapper objectMapper = new ObjectMapper();

    final ObjectWriter objectWriter = objectMapper.writer(new DefaultPrettyPrinter());
    objectWriter.writeValue(reportPath.toFile(), validationReport);
  }

  /**
   * Check if the structure of the IP is valid.
   *
   * @return if the IP passes on the structure validations
   */
  public boolean validFileComponent() {
    for (Map.Entry<String, ReporterDetails> result : results.entrySet()) {
      final String strCsip = result.getKey();
      if ((strCsip.equals("CSIPSTR1") || strCsip.equals("CSIPSTR4")) && !result.getValue().isValid()) {
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
    final StructStatus structureStatus = validationReport.getStructure().getStatus();
    final MetadataStatus schemaStatus = validationReport.getMetadata().getSchemaResults().getStatus();
    final MetadataStatus schematronStatus = validationReport.getMetadata().getSchematronResults().getStatus();
    return !structureStatus.equals(StructStatus.NOTWELLFORMED) && !schemaStatus.equals(MetadataStatus.NOTVALID)
      && !schematronStatus.equals(MetadataStatus.NOTVALID);
  }
}
