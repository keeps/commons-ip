package org.roda_project.commons_ip2.validator.reporter;

import org.roda_project.commons_ip2.validator.constants.Constants;
import org.roda_project.commons_ip2.validator.constants.ConstantsCSIPspec;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import org.joda.time.DateTime;
/**
 * @author João Gomes <jgomes@keep.pt>
 */

public class ValidationReporter {
  private static final Logger LOGGER = LoggerFactory.getLogger(ValidationReporter.class);
  private Path outputFile;
  private OutputStream outputStream;
  private JsonGenerator jsonGenerator;
  private int success;
  private int errors;
  private int warnings;

  public ValidationReporter(Path path) {
    init(path);
  }

  public void countSuccess(){
    success++;
  }

  public void countWarnings(){
    warnings++;
  }

  public void countErrors(){
    errors++;
  }

  private void init(Path path) {
    this.outputFile = path;
    this.success = 0;
    this.errors = 0;
    this.warnings = 0;
    if (outputFile.toFile().exists()) {
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
    }

    if (outputFile != null) {
      try {
        outputStream = new BufferedOutputStream(new FileOutputStream(outputFile.toFile()));
        JsonFactory jsonFactory = new JsonFactory();
        jsonGenerator = jsonFactory.createGenerator(outputStream, JsonEncoding.UTF8);
        jsonGenerator.writeStartObject();
        // header object
        jsonGenerator.writeFieldName(Constants.VALIDATION_REPORT_HEADER_KEY_HEADER);
        jsonGenerator.writeStartObject();
        // header -> title
        jsonGenerator.writeStringField(Constants.VALIDATION_REPORT_HEADER_KEY_TITLE, Constants.VALIDATION_REPORT_HEADER_TITLE);
        // header -> specifications
        jsonGenerator.writeFieldName(Constants.VALIDATION_REPORT_HEADER_KEY_SPECIFICATIONS);
        jsonGenerator.writeStartArray();
        // header -> specifications -> CSIP
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField(Constants.VALIDATION_REPORT_KEY_ID, Constants.VALIDATION_REPORT_HEADER_ID_CSIP);
        jsonGenerator.writeStringField(Constants.VALIDATION_REPORT_HEADER_SPECIFICATIONS_KEY_URL, Constants.VALIDATION_REPORT_HEADER_SPECIFICATIONS_URL_CSIP);
        jsonGenerator.writeEndObject();
        // header -> specifications -> SIP
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField(Constants.VALIDATION_REPORT_KEY_ID, Constants.VALIDATION_REPORT_HEADER_ID_SIP);
        jsonGenerator.writeStringField(Constants.VALIDATION_REPORT_HEADER_SPECIFICATIONS_KEY_URL, Constants.VALIDATION_REPORT_HEADER_SPECIFICATIONS_URL_SIP);
        jsonGenerator.writeEndObject();
        jsonGenerator.writeEndArray();
        // header -> version_commons_ip
        jsonGenerator.writeStringField(Constants.VALIDATION_REPORT_SPECIFICATION_KEY_VERSION_COMMONS_IP, Constants.VALIDATION_REPORT_SPECIFICATION_COMMONS_IP_VERSION);
        // header -> date (date of sip validation)
        jsonGenerator.writeStringField(Constants.VALIDATION_REPORT_SPECIFICATION_KEY_DATE, new org.joda.time.DateTime().toString());
        jsonGenerator.writeEndObject();
        // initialize validation array
        jsonGenerator.writeFieldName(Constants.VALIDATION_REPORT_SPECIFICATION_KEY_VALIDATION);
        jsonGenerator.writeStartArray();
      } catch (IOException e) {
        LOGGER.error("Could not create an output stream for file '" + outputFile.normalize().toAbsolutePath().toString() + "'", e);
      }
    }
  }

  public void componentValidationResult(String specification,String id,Boolean status, String detail) {
    try {
      jsonGenerator.writeStartObject();
      jsonGenerator.writeStringField(Constants.VALIDATION_REPORT_SPECIFICATION_KEY_SPECIFICATION, specification);
      jsonGenerator.writeStringField(Constants.VALIDATION_REPORT_KEY_ID, id);
      jsonGenerator.writeStringField(Constants.VALIDATION_REPORT_SPECIFICATION_KEY_NAME, ConstantsCSIPspec.getSpecificationName(id));
      jsonGenerator.writeStringField(Constants.VALIDATION_REPORT_SPECIFICATION_KEY_LOCATION, ConstantsCSIPspec.getSpecificationLocation(id));
      jsonGenerator.writeStringField(Constants.VALIDATION_REPORT_SPECIFICATION_KEY_DESCRIPTION, ConstantsCSIPspec.getSpecificationDescription(id));
      jsonGenerator.writeStringField(Constants.VALIDATION_REPORT_SPECIFICATION_KEY_CARDINALITY, ConstantsCSIPspec.getSpecificationCardinality(id));
      jsonGenerator.writeStringField(Constants.VALIDATION_REPORT_SPECIFICATION_KEY_LEVEL, ConstantsCSIPspec.getSpecificationLevel(id));
      jsonGenerator.writeFieldName(Constants.VALIDATION_REPORT_SPECIFICATION_KEY_OUTCOME);
      jsonGenerator.writeStartObject();
      jsonGenerator.writeObjectField(Constants.VALIDATION_REPORT_SPECIFICATION_KEY_OUTCOME_VALID, status);
      jsonGenerator.writeStringField(Constants.VALIDATION_REPORT_SPECIFICATION_KEY_OUTCOME_DETAIL, detail);
      jsonGenerator.writeEndObject();
      jsonGenerator.writeEndObject();
    } catch (IOException e) {
      LOGGER.error("Could not write specification " + specification + "result in file '", e);
    }
  }

  public void componentValidationFinish(String status){
    try {
      jsonGenerator.writeEndArray();
      jsonGenerator.writeFieldName(Constants.VALIDATION_REPORT_SPECIFICATION_KEY_SUMMARY);
      jsonGenerator.writeStartObject();
      jsonGenerator.writeNumberField(Constants.VALIDATION_REPORT_SPECIFICATION_KEY_SUCCESS, success);
      jsonGenerator.writeNumberField(Constants.VALIDATION_REPORT_SPECIFICATION_KEY_WARNINGS, warnings);
      jsonGenerator.writeNumberField(Constants.VALIDATION_REPORT_SPECIFICATION_KEY_ERRORS, errors);
      jsonGenerator.writeStringField(Constants.VALIDATION_REPORT_SPECIFICATION_KEY_RESULT,status);
      jsonGenerator.writeEndObject();
      jsonGenerator.writeEndObject();
    } catch (IOException e) {
      LOGGER.error("Could not finish report!", e);
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
        LOGGER.info("A report with a listing of information  about the individual validations could not be generated, please submit a bug report to help us fix this.");
      }
    }
  }
}
