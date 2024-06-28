package org.roda_project.commons_ip2.validator.reporter;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.roda_project.commons_ip2.validator.constants.Constants;
import org.roda_project.commons_ip2.validator.constants.ConstantsAIPspec;
import org.roda_project.commons_ip2.validator.constants.ConstantsCSIPspec;
import org.roda_project.commons_ip2.validator.constants.ConstantsSIPspec;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;

/** {@author João Gomes <jgomes@keep.pt>}. */
public class ValidationReportOutputJson {
  /**
   * {@link Logger}.
   */
  private static final Logger LOGGER = LoggerFactory.getLogger(ValidationReportOutputJson.class);
  /**
   * {@link Path} the sipPath.
   */
  private final Path sipPath;
  /**
   * the {@link OutputStream}.
   */
  private final OutputStream outputStream;
  /**
   * the {@link JsonGenerator}.
   */
  private JsonGenerator jsonGenerator;
  /**
   * the number of requirements validated with success.
   */
  private int success;

  /**
   * the number of requirements validated with errors.
   */
  private int errors;
  /**
   * the number of requirements validated with warnings.
   */
  private int warnings;
  /**
   * the number of requirements skipped.
   */
  private int skipped;
  /**
   * the number of notes.
   */
  private int notes;

  /**
   * {@link Map} with the results.
   */
  private final Map<String, ReporterDetails> results = new TreeMap<>(new RequirementsComparator());
  /**
   * {@link String}.
   */
  private String ipType = "";

  /**
   * The public constructor that sets the {@link Path} and the
   * {@link OutputStream}.
   * 
   * @param sipPath
   *          {@link Path}.
   * @param outputStream
   *          {@link OutputStream}.
   */
  public ValidationReportOutputJson(final Path sipPath, final OutputStream outputStream) {
    this.sipPath = sipPath;
    this.outputStream = outputStream;
  }

  public int getSuccess() {
    return success;
  }

  public int getErrors() {
    return errors;
  }

  public int getWarnings() {
    return warnings;
  }

  public int getSkipped() {
    return skipped;
  }

  public int getNotes() {
    return notes;
  }

  public Path getSipPath() {
    return sipPath;
  }

  public Map<String, ReporterDetails> getResults() {
    return results;
  }

  public void setIpType(final String ipType) {
    this.ipType = ipType;
  }

  /**
   * Initializes the json report.
   *
   * @throws IOException
   *           if some I/O error occurs
   */
  public void init(String version) throws IOException {
    this.success = 0;
    this.errors = 0;
    this.warnings = 0;
    // Depois receber parametro new BufferedOutputStream(System.out)
    final JsonFactory jsonFactory = new JsonFactory();
    jsonGenerator = jsonFactory.createGenerator(this.outputStream, JsonEncoding.UTF8).useDefaultPrettyPrinter();
    jsonGenerator.writeStartObject();
    // header object
    jsonGenerator.writeFieldName(Constants.VALIDATION_REPORT_HEADER_KEY_HEADER);
    jsonGenerator.writeStartObject();
    // header -> title
    jsonGenerator.writeStringField(Constants.VALIDATION_REPORT_HEADER_KEY_TITLE,
      Constants.VALIDATION_REPORT_HEADER_TITLE);
    // header -> specifications
    jsonGenerator.writeFieldName(Constants.VALIDATION_REPORT_HEADER_KEY_SPECIFICATIONS);
    jsonGenerator.writeStartArray();
    // header -> specifications -> CSIP
    jsonGenerator.writeStartObject();
    jsonGenerator.writeStringField(Constants.VALIDATION_REPORT_KEY_ID, Constants.VALIDATION_REPORT_CSIP_VERSION + version);
    jsonGenerator.writeStringField(Constants.VALIDATION_REPORT_HEADER_SPECIFICATIONS_KEY_URL,
      Constants.VALIDATION_REPORT_HEADER_SPECIFICATIONS_URL_CSIP + version);
    jsonGenerator.writeEndObject();
    if (ipType != null) {
      if (Constants.ID_TYPE_SIP.equals(ipType)) {
        // header -> specifications -> SIP
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField(Constants.VALIDATION_REPORT_KEY_ID,
          Constants.VALIDATION_REPORT_HEADER_SIP_VERSION + version);
        jsonGenerator.writeStringField(Constants.VALIDATION_REPORT_HEADER_SPECIFICATIONS_KEY_URL,
          Constants.VALIDATION_REPORT_HEADER_SPECIFICATIONS_URL_SIP + version);
        jsonGenerator.writeEndObject();
      } else if (Constants.ID_TYPE_AIP.equals(ipType)) {
        // header -> specifications -> AIP
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField(Constants.VALIDATION_REPORT_KEY_ID,
          Constants.VALIDATION_REPORT_HEADER_AIP_VERSION + version);
        jsonGenerator.writeStringField(Constants.VALIDATION_REPORT_HEADER_SPECIFICATIONS_KEY_URL,
          Constants.VALIDATION_REPORT_HEADER_SPECIFICATIONS_URL_AIP + version);
        jsonGenerator.writeEndObject();
      }
    }
    jsonGenerator.writeEndArray();
    // header -> version_commons_ip
    jsonGenerator.writeStringField(Constants.VALIDATION_REPORT_SPECIFICATION_KEY_VERSION_COMMONS_IP,
      getClass().getPackage().getImplementationVersion());
    // header -> date (date of sip validation)
    jsonGenerator.writeStringField(Constants.VALIDATION_REPORT_SPECIFICATION_KEY_DATE,
      new org.joda.time.DateTime().toString());
    // header -> path of sip
    jsonGenerator.writeStringField(Constants.VALIDATION_REPORT_SPECIFICATION_KEY_PATH, sipPath.toString());
    jsonGenerator.writeEndObject();
    // initialize validation array
    jsonGenerator.writeFieldName(Constants.VALIDATION_REPORT_SPECIFICATION_KEY_VALIDATION);
    jsonGenerator.writeStartArray();
  }

  /**
   * Write the result json object in the json array of the report.
   *
   * @param specification
   *          the {@link String} to the specification.
   * @param id
   *          the {@link String} id of the requirement.
   * @param status
   *          the {@link String} status of the requirement.
   * @param issues
   *          the {@link List} with the issues.
   * @param detail
   *          the {@link String} the detail.
   */
  public void componentValidationResult(final String specification, final String id, final String status,
    final List<String> issues, final String detail) {
    try {
      String level = null;
      if (id.startsWith(Constants.ID_TYPE_CSIP)) {
        level = ConstantsCSIPspec.getSpecificationLevel(id);
      } else {
        if (id.startsWith(Constants.ID_TYPE_SIP)) {
          level = ConstantsSIPspec.getSpecificationLevel(id);
        } else {
          if (id.startsWith(Constants.ID_TYPE_AIP)) {
            level = ConstantsAIPspec.getSpecificationLevel(id);
          }
        }
      }
      jsonGenerator.writeStartObject();
      jsonGenerator.writeStringField(Constants.VALIDATION_REPORT_SPECIFICATION_KEY_SPECIFICATION, specification);
      jsonGenerator.writeStringField(Constants.VALIDATION_REPORT_KEY_ID, id);
      writeSpecificationDetails(id);
      jsonGenerator.writeFieldName(Constants.VALIDATION_REPORT_SPECIFICATION_KEY_TESTING);
      jsonGenerator.writeStartObject();
      jsonGenerator.writeObjectField(Constants.VALIDATION_REPORT_SPECIFICATION_KEY_TESTING_OUTCOME, status);
      if (!detail.equals("")) {
        jsonGenerator.writeObjectField(Constants.VALIDATION_REPORT_SPECIFICATION_KEY_TESTING_DETAIL, detail);
      }
      writeIssuesByLevel(level, issues);
      jsonGenerator.writeEndObject();
      jsonGenerator.writeEndObject();
    } catch (final IOException e) {
        LOGGER.error("Could not write specification " + specification + "result in file", e);
    }
  }

  /**
   * Write the summary section of the Report.
   *
   * @param status
   *          {@link String} with the result of validation
   */
  public void componentValidationFinish(final String status) {
    try {
      jsonGenerator.writeEndArray();
      jsonGenerator.writeFieldName(Constants.VALIDATION_REPORT_SPECIFICATION_KEY_SUMMARY);
      jsonGenerator.writeStartObject();
      jsonGenerator.writeNumberField(Constants.VALIDATION_REPORT_SPECIFICATION_KEY_SUCCESS, success);
      jsonGenerator.writeNumberField(Constants.VALIDATION_REPORT_SPECIFICATION_KEY_WARNINGS, warnings);
      jsonGenerator.writeNumberField(Constants.VALIDATION_REPORT_SPECIFICATION_KEY_ERRORS, errors);
      jsonGenerator.writeNumberField(Constants.VALIDATION_REPORT_SPECIFICATION_KEY_SKIPPED, skipped);
      jsonGenerator.writeNumberField(Constants.VALIDATION_REPORT_SPECIFICATION_KEY_NOTES, notes);
      jsonGenerator.writeStringField(Constants.VALIDATION_REPORT_SPECIFICATION_KEY_RESULT, status);
      jsonGenerator.writeEndObject();
      jsonGenerator.writeEndObject();
    } catch (final IOException e) {
      LOGGER.error("Could not finish report!", e);
    }
  }

  /** Write the results of validation in the report. */
  public void validationResults() {
    for (Map.Entry<String, ReporterDetails> entry : results.entrySet()) {
      final ReporterDetails details = entry.getValue();
      final List<String> issues = details.getIssues();
      final String detail = details.getDetail();
      String level = null;
      if (details.getSpecification().startsWith(Constants.ID_TYPE_CSIP)) {
        level = ConstantsCSIPspec.getSpecificationLevel(entry.getKey());
      } else {
        if (details.getSpecification().startsWith(Constants.ID_TYPE_SIP)) {
          level = ConstantsSIPspec.getSpecificationLevel(entry.getKey());
        } else {
          if (details.getSpecification().startsWith(Constants.ID_TYPE_AIP)) {
            level = ConstantsAIPspec.getSpecificationLevel(entry.getKey());
          }
        }
      }

      if (details.isSkipped()) {
        componentValidationResult(details.getSpecification(), entry.getKey(),
          Constants.VALIDATION_REPORT_SPECIFICATION_TESTING_OUTCOME_SKIPPED, issues, detail);
        skipped++;
      } else {
        if (details.isValid()) {
          if (!Constants.REQUIREMENT_LEVEL_MAY.equals(level) || issues.isEmpty()) {
            success++;
          } else {
            notes++;
          }
          componentValidationResult(details.getSpecification(), entry.getKey(),
            Constants.VALIDATION_REPORT_SPECIFICATION_TESTING_OUTCOME_PASSED, issues, detail);
        } else {
          if (Constants.REQUIREMENT_LEVEL_MAY.equals(level)) {
            componentValidationResult(details.getSpecification(), entry.getKey(),
              Constants.VALIDATION_REPORT_SPECIFICATION_TESTING_OUTCOME_PASSED, issues, detail);
            notes++;
          } else {
            componentValidationResult(details.getSpecification(), entry.getKey(),
              Constants.VALIDATION_REPORT_SPECIFICATION_TESTING_OUTCOME_FAILED, issues, detail);
            if (Constants.REQUIREMENT_LEVEL_MUST.equals(level)) {
              errors++;
            } else {
              if (Constants.REQUIREMENT_LEVEL_SHOULD.equals(level)) {
                warnings++;
              }
            }
          }
        }
      }
    }
  }

  /** Close the {@link JsonGenerator} and {@link OutputStream}. */
  public void close() {
    try {
      if (this.outputStream != null) {
        jsonGenerator.close();
        this.outputStream.close();
      }
    } catch (final IOException e) {
      LOGGER.debug("Unable to close validation reporter file", e);
    }
  }

  private void writeSpecificationDetails(final String id) throws IOException {
    String name = null;
    String location = null;
    String description = null;
    String cardinality = null;
    String level = null;
    if (id.startsWith("CSIP")) {
      name = ConstantsCSIPspec.getSpecificationName(id);
      location = ConstantsCSIPspec.getSpecificationLocation(id);
      description = ConstantsCSIPspec.getSpecificationDescription(id);
      cardinality = ConstantsCSIPspec.getSpecificationCardinality(id);
      level = ConstantsCSIPspec.getSpecificationLevel(id);
    } else {
      if (id.startsWith("SIP")) {
        name = ConstantsSIPspec.getSpecificationName(id);
        location = ConstantsSIPspec.getSpecificationLocation(id);
        description = ConstantsSIPspec.getSpecificationDescription(id);
        cardinality = ConstantsSIPspec.getSpecificationCardinality(id);
        level = ConstantsSIPspec.getSpecificationLevel(id);
      } else {
        if (id.startsWith("AIP")) {
          name = ConstantsAIPspec.getSpecificationName(id);
          location = ConstantsAIPspec.getSpecificationLocation(id);
          description = ConstantsAIPspec.getSpecificationDescription(id);
          cardinality = ConstantsAIPspec.getSpecificationCardinality(id);
          level = ConstantsAIPspec.getSpecificationLevel(id);
        }
      }
    }

    jsonGenerator.writeStringField(Constants.VALIDATION_REPORT_SPECIFICATION_KEY_NAME, name);
    jsonGenerator.writeStringField(Constants.VALIDATION_REPORT_SPECIFICATION_KEY_LOCATION, location);
    jsonGenerator.writeStringField(Constants.VALIDATION_REPORT_SPECIFICATION_KEY_DESCRIPTION, description);
    jsonGenerator.writeStringField(Constants.VALIDATION_REPORT_SPECIFICATION_KEY_CARDINALITY, cardinality);
    jsonGenerator.writeStringField(Constants.VALIDATION_REPORT_SPECIFICATION_KEY_LEVEL, level);
  }

  private void writeIssuesByLevel(final String level, final List<String> issues) throws IOException {
    switch (level) {
      case "MUST":
        jsonGenerator.writeFieldName(Constants.VALIDATION_REPORT_SPECIFICATION_KEY_TESTING_ISSUES);
        jsonGenerator.writeStartArray();
        for (String issue : issues) {
          jsonGenerator.writeString(issue);
        }
        jsonGenerator.writeEndArray();
        jsonGenerator.writeFieldName(Constants.VALIDATION_REPORT_SPECIFICATION_KEY_TESTING_WARNINGS);
        jsonGenerator.writeStartArray();
        jsonGenerator.writeEndArray();
        jsonGenerator.writeFieldName(Constants.VALIDATION_REPORT_SPECIFICATION_KEY_TESTING_NOTES);
        jsonGenerator.writeStartArray();
        jsonGenerator.writeEndArray();
        break;
      case "SHOULD":
        jsonGenerator.writeFieldName(Constants.VALIDATION_REPORT_SPECIFICATION_KEY_TESTING_ISSUES);
        jsonGenerator.writeStartArray();
        jsonGenerator.writeEndArray();
        jsonGenerator.writeFieldName(Constants.VALIDATION_REPORT_SPECIFICATION_KEY_TESTING_WARNINGS);
        jsonGenerator.writeStartArray();
        for (String issue : issues) {
          jsonGenerator.writeString(issue);
        }
        jsonGenerator.writeEndArray();
        jsonGenerator.writeFieldName(Constants.VALIDATION_REPORT_SPECIFICATION_KEY_TESTING_NOTES);
        jsonGenerator.writeStartArray();
        jsonGenerator.writeEndArray();
        break;
      case "MAY":
        jsonGenerator.writeFieldName(Constants.VALIDATION_REPORT_SPECIFICATION_KEY_TESTING_ISSUES);
        jsonGenerator.writeStartArray();
        jsonGenerator.writeEndArray();
        jsonGenerator.writeFieldName(Constants.VALIDATION_REPORT_SPECIFICATION_KEY_TESTING_WARNINGS);
        jsonGenerator.writeStartArray();
        jsonGenerator.writeEndArray();
        jsonGenerator.writeFieldName(Constants.VALIDATION_REPORT_SPECIFICATION_KEY_TESTING_NOTES);
        jsonGenerator.writeStartArray();
        for (String issue : issues) {
          jsonGenerator.writeString(issue);
        }
        jsonGenerator.writeEndArray();
        break;
      default:
        break;
    }
  }

  /** Write the final result on the report. */
  public void writeFinalResult() {
    if (errors > 0) {
      componentValidationFinish(Constants.VALIDATION_REPORT_SPECIFICATION_RESULT_INVALID);
    } else {
      componentValidationFinish(Constants.VALIDATION_REPORT_SPECIFICATION_RESULT_VALID);
    }
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
}
