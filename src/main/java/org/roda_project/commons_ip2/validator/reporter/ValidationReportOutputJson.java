package org.roda_project.commons_ip2.validator.reporter;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
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

/**
 * @author João Gomes <jgomes@keep.pt>
 */

public class ValidationReportOutputJson {
  private static final Logger LOGGER = LoggerFactory.getLogger(ValidationReportOutputJson.class);
  private final Path sipPath;
  private final Path reportPath;
  private Path outputFile;
  private OutputStream outputStream;
  private JsonGenerator jsonGenerator;
  private int success;
  private int errors;
  private int warnings;
  private int skipped;
  private int notes;

  private Map<String, ReporterDetails> results = new TreeMap<>(new RequirementsComparator());
  private String ipType;

  public ValidationReportOutputJson(Path path, Path sipPath) {
    this.sipPath = sipPath;
    this.reportPath = path;
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

  public void setIpType(String ipType){
    this.ipType = ipType;
  }

  public void init() {
    this.outputFile = reportPath;
    this.success = 0;
    this.errors = 0;
    this.warnings = 0;
    if (!outputFile.toFile().exists()) {
      try {
        Files.createFile(outputFile);
      } catch (IOException e) {
        LOGGER.warn("Could not create report file in current working directory. Attempting to use a temporary file", e);
        try {
          outputFile = Files.createTempFile(Constants.VALIDATION_REPORT_PREFIX, ".json");
        } catch (IOException e1) {
          LOGGER.error("Could not create report temporary file. Reporting will not function.", e1);
        }
      }
    } else {
      try {
        Files.deleteIfExists(outputFile);
        try {
          Files.createFile(outputFile);
        } catch (IOException e) {
          LOGGER.warn("Could not create report file in current working directory. Attempting to use a temporary file",
            e);
          try {
            outputFile = Files.createTempFile(Constants.VALIDATION_REPORT_PREFIX, ".json");
          } catch (IOException e1) {
            LOGGER.error("Could not create report temporary file. Reporting will not function.", e1);
          }
        }
      } catch (IOException e) {
        LOGGER.warn("Could not eliminate old report file in current working directory.", e);
      }
    }

    if (outputFile != null) {
      try {
        // Depois receber parametro new BufferedOutputStream(System.out)

        outputStream = new BufferedOutputStream(new FileOutputStream(outputFile.toFile()));
        JsonFactory jsonFactory = new JsonFactory();
        jsonGenerator = jsonFactory.createGenerator(outputStream, JsonEncoding.UTF8).useDefaultPrettyPrinter();
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
        jsonGenerator.writeStringField(Constants.VALIDATION_REPORT_KEY_ID,
          Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION);
        jsonGenerator.writeStringField(Constants.VALIDATION_REPORT_HEADER_SPECIFICATIONS_KEY_URL,
          Constants.VALIDATION_REPORT_HEADER_SPECIFICATIONS_URL_CSIP);
        jsonGenerator.writeEndObject();
        if(ipType.equals("SIP")) {
          // header -> specifications -> SIP
          jsonGenerator.writeStartObject();
          jsonGenerator.writeStringField(Constants.VALIDATION_REPORT_KEY_ID,
                  Constants.VALIDATION_REPORT_HEADER_SIP_VERSION);
          jsonGenerator.writeStringField(Constants.VALIDATION_REPORT_HEADER_SPECIFICATIONS_KEY_URL,
                  Constants.VALIDATION_REPORT_HEADER_SPECIFICATIONS_URL_SIP);
          jsonGenerator.writeEndObject();
        }
        else{
          if(ipType.equals("AIP")){
            // header -> specifications -> AIP
            jsonGenerator.writeStartObject();
            jsonGenerator.writeStringField(Constants.VALIDATION_REPORT_KEY_ID,
                    Constants.VALIDATION_REPORT_HEADER_AIP_VERSION);
            jsonGenerator.writeStringField(Constants.VALIDATION_REPORT_HEADER_SPECIFICATIONS_KEY_URL,
                    Constants.VALIDATION_REPORT_HEADER_SPECIFICATIONS_URL_AIP);
            jsonGenerator.writeEndObject();
          }
        }
        jsonGenerator.writeEndArray();
        // header -> version_commons_ip
        jsonGenerator.writeStringField(Constants.VALIDATION_REPORT_SPECIFICATION_KEY_VERSION_COMMONS_IP,
          Constants.VALIDATION_REPORT_SPECIFICATION_COMMONS_IP_VERSION);
        // header -> date (date of sip validation)
        jsonGenerator.writeStringField(Constants.VALIDATION_REPORT_SPECIFICATION_KEY_DATE,
          new org.joda.time.DateTime().toString());
        // header -> path of sip
        jsonGenerator.writeStringField(Constants.VALIDATION_REPORT_SPECIFICATION_KEY_PATH, sipPath.toString());
        jsonGenerator.writeEndObject();
        // initialize validation array
        jsonGenerator.writeFieldName(Constants.VALIDATION_REPORT_SPECIFICATION_KEY_VALIDATION);
        jsonGenerator.writeStartArray();
      } catch (IOException e) {
        LOGGER.error("Could not create an output stream for file '" + outputFile.normalize().toAbsolutePath() + "'", e);
      }
    }
  }

  public void componentValidationResult(String specification, String id, String status, List<String> issues,
    String detail) {
    try {
      String level = null;
      if (id.startsWith("CSIP")) {
        level = ConstantsCSIPspec.getSpecificationLevel(id);
      } else {
        if (id.startsWith("SIP")) {
          level = ConstantsSIPspec.getSpecificationLevel(id);
        } else {
          if (id.startsWith("AIP")) {
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
    } catch (IOException e) {
      StringBuilder message = new StringBuilder();
      message.append("Could not write specification ").append(specification).append("result in file");
      LOGGER.error(message.toString(), e);
    }
  }

  public void componentPathValidationResult(String id, String status, String detail) {
    try {
      jsonGenerator.writeStartObject();
      jsonGenerator.writeStringField(Constants.VALIDATION_REPORT_KEY_ID, id);
      jsonGenerator.writeFieldName(Constants.VALIDATION_REPORT_SPECIFICATION_KEY_TESTING);
      jsonGenerator.writeStartObject();
      jsonGenerator.writeObjectField(Constants.VALIDATION_REPORT_SPECIFICATION_KEY_TESTING_OUTCOME, status);

      jsonGenerator.writeStringField(Constants.VALIDATION_REPORT_SPECIFICATION_KEY_TESTING_DETAIL, detail);
      jsonGenerator.writeEndObject();
      jsonGenerator.writeEndObject();
    } catch (IOException e) {
      StringBuilder message = new StringBuilder();
      message.append("Could not write result of ").append(id).append(" result in file");
      LOGGER.error(message.toString(), e);
    }
  }

  public void componentValidationFinish(String status) {
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
    } catch (IOException e) {
      LOGGER.error("Could not finish report!", e);
    }
  }

  public void validationResults() {
    for (Map.Entry<String, ReporterDetails> entry : results.entrySet()) {
      ReporterDetails details = entry.getValue();
      List<String> issues = details.getIssues();
      String detail = details.getDetail();
      String level = null;
      if (details.getSpecification().startsWith("CSIP")) {
        level = ConstantsCSIPspec.getSpecificationLevel(entry.getKey());
      } else {
        if (details.getSpecification().startsWith("SIP")) {
          level = ConstantsSIPspec.getSpecificationLevel(entry.getKey());
        } else {
          if (details.getSpecification().startsWith("AIP")) {
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
          if (!level.equals("MAY") || issues.isEmpty()) {
            success++;
          } else {
            notes++;
          }
          componentValidationResult(details.getSpecification(), entry.getKey(),
            Constants.VALIDATION_REPORT_SPECIFICATION_TESTING_OUTCOME_PASSED, issues, detail);
        } else {
          if (level.equals("MAY")) {
            componentValidationResult(details.getSpecification(), entry.getKey(),
              Constants.VALIDATION_REPORT_SPECIFICATION_TESTING_OUTCOME_PASSED, issues, detail);
            notes++;
          } else {
            componentValidationResult(details.getSpecification(), entry.getKey(),
              Constants.VALIDATION_REPORT_SPECIFICATION_TESTING_OUTCOME_FAILED, issues, detail);
            if (level.equals("MUST")) {
              errors++;
            } else {
              if (level.equals("SHOULD")) {
                warnings++;
              }
            }
          }
        }
      }
    }
  }

  public void close() {
    try {
      if (outputStream != null) {
        jsonGenerator.close();
        outputStream.close();
      }
    } catch (IOException e) {
      LOGGER.debug("Unable to close validation reporter file", e);
    } finally {
      if (outputStream != null) {
        LOGGER.info("A report was generated with a listing of information about the individual validations.");
        LOGGER.info("The report file is located at {}", outputFile.normalize().toAbsolutePath());
      } else {
        LOGGER.info(
          "A report with a listing of information  about the individual validations could not be generated, please submit a bug report to help us fix this.");
      }
    }
  }

  private void writeSpecificationDetails(String id) throws IOException {
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

  private void writeIssuesByLevel(String level, List<String> issues) throws IOException {
    switch (level) {
      default:
        break;
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
    }
  }

  public void writeFinalResult() {
    if (errors > 0) {
      componentValidationFinish(Constants.VALIDATION_REPORT_SPECIFICATION_RESULT_INVALID);
    } else {
      componentValidationFinish(Constants.VALIDATION_REPORT_SPECIFICATION_RESULT_VALID);
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

  private class RequirementsComparator implements Comparator<String> {
    private int compareInt(int c1, int c2) {
      if (c1 < c2) {
        return -1;
      } else {
        if (c1 > c2) {
          return 1;
        }
        return 0;
      }
    }

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
      } else if (o.startsWith("AIP")) {
        c = 4000;
        c += Integer.parseInt(o.substring("AIP".length()));
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
