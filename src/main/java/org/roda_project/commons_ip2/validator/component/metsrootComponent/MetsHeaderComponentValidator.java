package org.roda_project.commons_ip2.validator.component.metsrootComponent;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.datatype.XMLGregorianCalendar;

import org.roda_project.commons_ip2.mets_v1_12.beans.MetsType;
import org.roda_project.commons_ip2.validator.common.ControlledVocabularyParser;
import org.roda_project.commons_ip2.validator.component.ValidatorComponentImpl;
import org.roda_project.commons_ip2.validator.constants.Constants;
import org.roda_project.commons_ip2.validator.constants.ConstantsCSIPspec;
import org.roda_project.commons_ip2.validator.constants.ConstantsSIPspec;
import org.roda_project.commons_ip2.validator.reporter.ReporterDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author João Gomes <jgomes@keep.pt>
 */
public class MetsHeaderComponentValidator extends ValidatorComponentImpl {
  private static final Logger LOGGER = LoggerFactory.getLogger(MetsHeaderComponentValidator.class);

  private final String MODULE_NAME;
  private List<String> oaisPackageTypes;
  private MetsType.MetsHdr metsHdr;
  private List<MetsType.MetsHdr.Agent> agents;
  private List<String> recordsStatus;

  public void setOaisPackageTypes(List<String> oaisPackageTypes) {
    this.oaisPackageTypes = new ArrayList<>(oaisPackageTypes);
  }

  public void setRecordsStatus(List<String> recordsStatus) {
    this.recordsStatus = new ArrayList<>(recordsStatus);
  }

  public MetsHeaderComponentValidator(String module_name) {
    MODULE_NAME = module_name;
    oaisPackageTypes = new ArrayList<>();
    ControlledVocabularyParser controlledVocabularyParser = new ControlledVocabularyParser(
      Constants.PATH_RESOURCES_CSIP_VOCABULARY_OAIS_PACKAGE_TYPE, oaisPackageTypes);
    controlledVocabularyParser.parse();
    setOaisPackageTypes(controlledVocabularyParser.getData());

    recordsStatus = new ArrayList<>();
    controlledVocabularyParser = new ControlledVocabularyParser(Constants.PATH_RESOURCES_CSIP_VOCABULARY_RECORD_STATUS,
      recordsStatus);
    controlledVocabularyParser.parse();
    setRecordsStatus(controlledVocabularyParser.getData());
  }

  @Override
  public void validate() throws IOException {
    metsHdr = mets.getMetsHdr();
    agents = metsHdr.getAgent();
    ReporterDetails csip;

    /* CSIP117 */
    validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP117_ID);
    csip = validateCSIP117();
    csip.setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION);
    addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP117_ID, csip);

    if (csip.isValid()) {
      /* CSIP7 */
      validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP7_ID);
      csip = validateCSIP7();
      csip.setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION);
      addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP7_ID, csip);

      /* CSIP8 */
      csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, "SKIPPED can't validate", true, true);
      addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP8_ID, csip);

      /* CSIP9 */
      validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP9_ID);
      csip = validateCSIP9();
      csip.setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION);
      addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP9_ID, csip);

      if (isRootMets()) {
        /* CSIP10 */
        validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP10_ID);
        csip = validateCSIP10();
        csip.setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION);
        addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP10_ID, csip);

        if (csip.isValid()) {
          /* CSIP11 */
          validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP11_ID);
          csip = validateCSIP11();
          csip.setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION);
          addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP11_ID, csip);

          /* CSIP12 */
          validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP12_ID);
          csip = validateCSIP12();
          csip.setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION);
          addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP12_ID, csip);

          /* CSIP13 */
          validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP13_ID);
          csip = validateCSIP13();
          csip.setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION);
          addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP13_ID, csip);

          /* CSIP14 */
          validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP14_ID);
          csip = validateCSIP14();
          csip.setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION);
          addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP14_ID, csip);

          /* CSIP15 */
          validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP15_ID);
          csip = validateCSIP15();
          csip.setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION);
          addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP15_ID, csip);

          /* CSIP16 */
          validationInit(MODULE_NAME, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP16_ID);
          csip = validateCSIP16();
          csip.setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION);
          addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP16_ID, csip);

          /* SIP9 */
          validationInit(MODULE_NAME, ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP9_ID);
          csip = validateSIP9();
          csip.setSpecification(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION);
          addResult(ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP9_ID, csip);

          if (csip.isValid()) {
            /* SIP10 */
            validationInit(MODULE_NAME, ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP10_ID);
            csip = validateSIP10();
            csip.setSpecification(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION);
            addResult(ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP10_ID, csip);

            /* SIP11 */
            validationInit(MODULE_NAME, ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP11_ID);
            csip = validateSIP11();
            csip.setSpecification(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION);
            addResult(ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP11_ID, csip);

            /* SIP12 */
            validationInit(MODULE_NAME, ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP12_ID);
            csip = validateSIP12();
            csip.setSpecification(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION);
            addResult(ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP12_ID, csip);

            /* SIP13 */
            validationInit(MODULE_NAME, ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP13_ID);
            csip = validateSIP13();
            csip.setSpecification(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION);
            addResult(ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP13_ID, csip);

            /* SIP14 */
            validationInit(MODULE_NAME, ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP14_ID);
            csip = validateSIP14();
            csip.setSpecification(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION);
            addResult(ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP14_ID, csip);
          } else {
            String message = "SKIPPED because mets/metsHdr/agent with the role ARCHIVIST doesn't exist!";
            /* SIP10 */
            csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION, message, true, true);
            addResult(ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP10_ID, csip);

            /* SIP11 */
            csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION, message, true, true);
            addResult(ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP11_ID, csip);

            /* SIP12 */
            csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION, message, true, true);
            addResult(ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP12_ID, csip);

            /* SIP13 */
            csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION, message, true, true);
            addResult(ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP13_ID, csip);

            /* SIP14 */
            csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION, message, true, true);
            addResult(ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP14_ID, csip);
          }

          /* SIP15 */
          validationInit(MODULE_NAME, ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP15_ID);
          csip = validateSIP15();
          csip.setSpecification(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION);
          addResult(ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP15_ID, csip);

          if (csip.isValid()) {
            /* SIP16 */
            validationInit(MODULE_NAME, ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP16_ID);
            csip = validateSIP16();
            csip.setSpecification(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION);
            addResult(ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP16_ID, csip);

            /* SIP17 */
            validationInit(MODULE_NAME, ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP17_ID);
            csip = validateSIP17();
            csip.setSpecification(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION);
            addResult(ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP17_ID, csip);

            /* SIP18 */
            validationInit(MODULE_NAME, ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP18_ID);
            csip = validateSIP18();
            csip.setSpecification(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION);
            addResult(ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP18_ID, csip);

            /* SIP19 */
            validationInit(MODULE_NAME, ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP19_ID);
            csip = validateSIP19();
            csip.setSpecification(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION);
            addResult(ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP19_ID, csip);

            /* SIP20 */
            validationInit(MODULE_NAME, ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP20_ID);
            csip = validateSIP20();
            csip.setSpecification(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION);
            addResult(ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP20_ID, csip);
          } else {
            String message = "SKIPPED because mets/metsHdr/agent with the role CREATOR doesn't exist!";

            /* SIP16 */
            csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION, message, true, true);
            addResult(ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP16_ID, csip);

            /* SIP17 */
            csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION, message, true, true);
            addResult(ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP17_ID, csip);

            /* SIP18 */
            csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION, message, true, true);
            addResult(ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP18_ID, csip);

            /* SIP19 */
            csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION, message, true, true);
            addResult(ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP19_ID, csip);

            /* SIP20 */
            csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION, message, true, true);
            addResult(ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP20_ID, csip);
          }

          /* SIP21 */
          validationInit(MODULE_NAME, ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP21_ID);
          csip = validateSIP21();
          csip.setSpecification(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION);
          addResult(ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP21_ID, csip);

          if (csip.isValid()) {
            /* SIP22 */
            validationInit(MODULE_NAME, ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP22_ID);
            csip = validateSIP22();
            csip.setSpecification(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION);
            addResult(ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP22_ID, csip);

            /* SIP23 */
            validationInit(MODULE_NAME, ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP20_ID);
            csip = validateSIP23();
            csip.setSpecification(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION);
            addResult(ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP23_ID, csip);

            /* SIP24 */
            validationInit(MODULE_NAME, ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP24_ID);
            csip = validateSIP24();
            csip.setSpecification(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION);
            addResult(ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP24_ID, csip);

            /* SIP25 */
            validationInit(MODULE_NAME, ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP25_ID);
            csip = validateSIP25();
            csip.setSpecification(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION);
            addResult(ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP25_ID, csip);
          } else {
            String message = "SKIPPED because mets/metsHdr/agent with the role OTHER doesn't exist!";

            /* SIP22 */
            csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION, message, true, true);
            addResult(ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP22_ID, csip);

            /* SIP23 */
            csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION, message, true, true);
            addResult(ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP23_ID, csip);

            /* SIP24 */
            csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION, message, true, true);
            addResult(ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP24_ID, csip);

            /* SIP25 */
            csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION, message, true, true);
            addResult(ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP25_ID, csip);
          }

          /* SIP26 */
          validationInit(MODULE_NAME, ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP26_ID);
          csip = validateSIP26();
          csip.setSpecification(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION);
          addResult(ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP26_ID, csip);

          if (csip.isValid()) {
            /* SIP27 */
            validationInit(MODULE_NAME, ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP27_ID);
            csip = validateSIP27();
            csip.setSpecification(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION);
            addResult(ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP27_ID, csip);

            /* SIP28 */
            validationInit(MODULE_NAME, ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP28_ID);
            csip = validateSIP28();
            csip.setSpecification(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION);
            addResult(ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP28_ID, csip);

            /* SIP29 */
            validationInit(MODULE_NAME, ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP29_ID);
            csip = validateSIP29();
            csip.setSpecification(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION);
            addResult(ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP29_ID, csip);

            /* SIP30 */
            validationInit(MODULE_NAME, ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP30_ID);
            csip = validateSIP30();
            csip.setSpecification(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION);
            addResult(ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP30_ID, csip);

            /* SIP31 */
            validationInit(MODULE_NAME, ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP31_ID);
            csip = validateSIP31();
            csip.setSpecification(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION);
            addResult(ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP31_ID, csip);
          } else {
            String message = "SKIPPED because mets/metsHdr/agent with the role PRESERVATION doesn't exist!";

            /* SIP27 */
            csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION, message, true, true);
            addResult(ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP27_ID, csip);

            /* SIP28 */
            csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION, message, true, true);
            addResult(ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP28_ID, csip);

            /* SIP29 */
            csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION, message, true, true);
            addResult(ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP29_ID, csip);

            /* SIP30 */
            csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION, message, true, true);
            addResult(ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP30_ID, csip);

            /* SIP31 */
            csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION, message, true, true);
            addResult(ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP31_ID, csip);
          }
        } else {
          String message = "SKIPPED because mets/metsHdr/agent doesn't exist! (" + metsName + ")";
          /* CSIP11 */
          csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, message, true, true);
          addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP11_ID, csip);

          /* CSIP12 */
          csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, message, true, true);
          addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP12_ID, csip);

          /* CSIP13 */
          csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, message, true, true);
          addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP13_ID, csip);

          /* CSIP14 */
          csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, message, true, true);
          addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP14_ID, csip);

          /* CSIP15 */
          csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, message, true, true);
          addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP15_ID, csip);

          /* CSIP16 */
          csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, message, true, true);
          addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP16_ID, csip);

          /* SIP9 */
          csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION, message, true, true);
          addResult(ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP9_ID, csip);

          /* SIP10 */
          csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION, message, true, true);
          addResult(ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP10_ID, csip);

          /* SIP11 */
          csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION, message, true, true);
          addResult(ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP11_ID, csip);

          /* SIP12 */
          csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION, message, true, true);
          addResult(ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP12_ID, csip);

          /* SIP13 */
          csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION, message, true, true);
          addResult(ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP13_ID, csip);

          /* SIP14 */
          csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION, message, true, true);
          addResult(ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP14_ID, csip);

          /* SIP15 */
          csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION, message, true, true);
          addResult(ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP15_ID, csip);

          /* SIP16 */
          csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION, message, true, true);
          addResult(ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP16_ID, csip);

          /* SIP17 */
          csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION, message, true, true);
          addResult(ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP17_ID, csip);

          /* SIP18 */
          csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION, message, true, true);
          addResult(ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP18_ID, csip);

          /* SIP19 */
          csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION, message, true, true);
          addResult(ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP19_ID, csip);

          /* SIP20 */
          csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION, message, true, true);
          addResult(ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP20_ID, csip);

          /* SIP21 */
          csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION, message, true, true);
          addResult(ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP21_ID, csip);

          /* SIP22 */
          csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION, message, true, true);
          addResult(ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP22_ID, csip);

          /* SIP23 */
          csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION, message, true, true);
          addResult(ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP23_ID, csip);

          /* SIP24 */
          csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION, message, true, true);
          addResult(ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP24_ID, csip);

          /* SIP25 */
          csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION, message, true, true);
          addResult(ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP25_ID, csip);

          /* SIP26 */
          csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION, message, true, true);
          addResult(ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP26_ID, csip);

          /* SIP27 */
          csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION, message, true, true);
          addResult(ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP27_ID, csip);

          /* SIP28 */
          csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION, message, true, true);
          addResult(ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP28_ID, csip);

          /* SIP29 */
          csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION, message, true, true);
          addResult(ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP29_ID, csip);

          /* SIP30 */
          csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION, message, true, true);
          addResult(ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP30_ID, csip);

          /* SIP31 */
          csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION, message, true, true);
          addResult(ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP31_ID, csip);
        }
      }
      /* SIP3 */
      validationInit(MODULE_NAME, ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP3_ID);
      csip = validateSIP3();
      csip.setSpecification(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION);
      addResult(ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP3_ID, csip);

      /* SIP4 */
      validationInit(MODULE_NAME, ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP4_ID);
      csip = validateSIP4();
      csip.setSpecification(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION);
      addResult(ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP4_ID, csip);

      /* SIP5 */
      validationInit(MODULE_NAME, ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP5_ID);
      csip = validateSIP5();
      csip.setSpecification(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION);
      addResult(ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP5_ID, csip);

      /* SIP6 */
      validationInit(MODULE_NAME, ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP6_ID);
      csip = validateSIP6();
      csip.setSpecification(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION);
      addResult(ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP6_ID, csip);

      /* SIP7 */
      validationInit(MODULE_NAME, ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP7_ID);
      csip = validateSIP7();
      csip.setSpecification(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION);
      addResult(ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP7_ID, csip);

      /* SIP8 */
      validationInit(MODULE_NAME, ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP8_ID);
      csip = validateSIP8();
      csip.setSpecification(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION);
      addResult(ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP8_ID, csip);
    } else {
      String message = "SKIPPED because mets/metsHdr doesn't exist! (" + metsName + ")";

      /* CSIP7 */
      csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, message, true, true);
      addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP9_ID, csip);

      /* CSIP8 */
      csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, message, true, true);
      addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP8_ID, csip);

      /* CSIP9 */
      csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, message, true, true);
      addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP9_ID, csip);

      /* CSIP10 */
      csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, message, true, true);
      addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP10_ID, csip);

      /* CSIP11 */
      csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, message, true, true);
      addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP11_ID, csip);

      /* CSIP12 */
      csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, message, true, true);
      addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP12_ID, csip);

      /* CSIP13 */
      csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, message, true, true);
      addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP13_ID, csip);

      /* CSIP14 */
      csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, message, true, true);
      addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP14_ID, csip);

      /* CSIP15 */
      csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, message, true, true);
      addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP15_ID, csip);

      /* CSIP16 */
      csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, message, true, true);
      addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP16_ID, csip);

      /* SIP3 */
      csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION, message, true, true);
      addResult(ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP3_ID, csip);

      /* SIP4 */
      csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION, message, true, true);
      addResult(ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP4_ID, csip);

      /* SIP5 */
      csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION, message, true, true);
      addResult(ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP5_ID, csip);

      /* SIP6 */
      csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION, message, true, true);
      addResult(ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP6_ID, csip);

      /* SIP7 */
      csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION, message, true, true);
      addResult(ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP7_ID, csip);

      /* SIP8 */
      csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION, message, true, true);
      addResult(ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP8_ID, csip);

      /* SIP9 */
      csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION, message, true, true);
      addResult(ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP9_ID, csip);

      /* SIP10 */
      csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION, message, true, true);
      addResult(ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP10_ID, csip);

      /* SIP11 */
      csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION, message, true, true);
      addResult(ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP11_ID, csip);

      /* SIP12 */
      csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION, message, true, true);
      addResult(ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP12_ID, csip);

      /* SIP13 */
      csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION, message, true, true);
      addResult(ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP13_ID, csip);

      /* SIP14 */
      csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION, message, true, true);
      addResult(ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP14_ID, csip);

      /* SIP15 */
      csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION, message, true, true);
      addResult(ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP15_ID, csip);

      /* SIP16 */
      csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION, message, true, true);
      addResult(ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP16_ID, csip);

      /* SIP17 */
      csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION, message, true, true);
      addResult(ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP17_ID, csip);

      /* SIP18 */
      csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION, message, true, true);
      addResult(ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP18_ID, csip);

      /* SIP19 */
      csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION, message, true, true);
      addResult(ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP19_ID, csip);

      /* SIP20 */
      csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION, message, true, true);
      addResult(ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP20_ID, csip);

      /* SIP21 */
      csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION, message, true, true);
      addResult(ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP21_ID, csip);

      /* SIP22 */
      csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION, message, true, true);
      addResult(ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP22_ID, csip);

      /* SIP23 */
      csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION, message, true, true);
      addResult(ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP23_ID, csip);

      /* SIP24 */
      csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION, message, true, true);
      addResult(ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP24_ID, csip);

      /* SIP25 */
      csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION, message, true, true);
      addResult(ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP25_ID, csip);

      /* SIP26 */
      csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION, message, true, true);
      addResult(ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP26_ID, csip);

      /* SIP27 */
      csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION, message, true, true);
      addResult(ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP27_ID, csip);

      /* SIP28 */
      csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION, message, true, true);
      addResult(ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP28_ID, csip);

      /* SIP29 */
      csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION, message, true, true);
      addResult(ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP29_ID, csip);

      /* SIP30 */
      csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION, message, true, true);
      addResult(ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP30_ID, csip);

      /* SIP31 */
      csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION, message, true, true);
      addResult(ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP31_ID, csip);
    }
    observer.notifyFinishModule(MODULE_NAME);
  }

  /*
   * mets/metsHdr General element for describing the package.
   */
  private ReporterDetails validateCSIP117() {
    ReporterDetails details = new ReporterDetails();
    if (metsHdr == null) {
      details.setValid(false);
      details.addIssue("mets/metsHdr can't be null!");
    }
    return details;
  }

  /*
   * mets/metsHdr/@CREATEDATE mets/metsHdr/@CREATEDATE records the date the
   * package was created.
   */
  private ReporterDetails validateCSIP7() {
    ReporterDetails details = new ReporterDetails();
    XMLGregorianCalendar createDate = metsHdr.getCREATEDATE();
    if (createDate == null) {
      details.setValid(false);
      details.addIssue("mets/metsHdr/@CREATEDATE can't be null!");
    }
    return details;
  }

  /*
   * mets/metsHdr/@csip:OAISPACKAGETYPE mets/metsHdr/@csip:OAISPACKAGETYPE is an
   * additional CSIP attribute that declares the type of the IP.See also: OAIS
   * Package type
   */
  private ReporterDetails validateCSIP9() {
    ReporterDetails details = new ReporterDetails();
    String oaisPackageType = metsHdr.getOAISPACKAGETYPE();
    if (oaisPackageType == null || oaisPackageType.equals("")) {
      details.setValid(false);
      details.addIssue("mets/metsHdr/@csip:OAISPACKAGETYPE can't be null or empty!");
    } else {
      if (!oaisPackageTypes.contains(oaisPackageType)) {
        details.setValid(false);
        details.addIssue("mets/metsHdr/@csip:OAISPACKAGETYPE must have a value from OAIS Package type! ");
      }
    }
    return details;
  }

  /*
   * mets/metsHdr/agent A mandatory agent element records the software used to
   * create the package. Other uses of agents may be described in any local
   * implementations that extend the profile.
   */
  private ReporterDetails validateCSIP10() {
    ReporterDetails details = new ReporterDetails();
    if (agents == null && isRootMets()) {
      details.setValid(false);
      details.addIssue("Must have at least one mets/metsHdr/agent!");
    } else {
      if (agents == null && !isRootMets()) {
        return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, "", true, true);
      } else {
        if (agents.size() == 0 && isRootMets()) {
          details.setValid(false);
          details.addIssue("Must have at least one mets/metsHdr/agent!");
        } else {
          if (agents.size() == 0 && !isRootMets()) {
            return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, "", true, true);
          }
        }
      }

    }
    return details;
  }

  /*
   * mets/metsHdr/agent[@ROLE=’CREATOR’] The mandatory agent element MUST have
   * a @ROLE attribute with the value “CREATOR”.
   */
  private ReporterDetails validateCSIP11() {
    ReporterDetails details = new ReporterDetails();
    boolean found = false;
    for (MetsType.MetsHdr.Agent a : agents) {
      String role = a.getROLE();
      String type = a.getTYPE();
      String otherType = a.getOTHERTYPE();
      if (role == null || type == null || otherType == null) {
        break;
      } else {
        if (role.equals("CREATOR") && type.equals("OTHER") && otherType.equals("SOFTWARE")) {
          found = true;
          break;
        }
      }

    }
    if (!found) {
      details.setValid(false);
      details.addIssue("Must have a mets/metsHdr/agent[@ROLE='CREATOR'] with the value CREATOR");
    }
    return details;
  }

  /*
   * mets/metsHdr/agent[@TYPE=’OTHER’] The mandatory agent element MUST have
   * a @TYPE attribute with the value “OTHER”.
   */

  private ReporterDetails validateCSIP12() {
    ReporterDetails details = new ReporterDetails();
    boolean found = false;
    for (MetsType.MetsHdr.Agent a : agents) {
      String role = a.getROLE();
      String type = a.getTYPE();
      String otherType = a.getOTHERTYPE();
      if (role == null || type == null || otherType == null) {
        break;
      } else {
        if (role.equals("CREATOR") && type.equals("OTHER") && otherType.equals("SOFTWARE")) {
          found = true;
          break;
        }
      }
    }
    if (!found) {
      details.setValid(false);
      details.addIssue("Must have a mets/metsHdr/agent[@TYPE='OTHER'] with the value OTHER");
    }
    return details;
  }

  /*
   * mets/metsHdr/agent[@OTHERTYPE=’SOFTWARE’] The mandatory agent element MUST
   * have a @OTHERTYPE attribute with the value “SOFTWARE”.See also: Other agent
   * type
   */

  private ReporterDetails validateCSIP13() {
    ReporterDetails details = new ReporterDetails();
    boolean found = false;
    for (MetsType.MetsHdr.Agent a : agents) {
      String role = a.getROLE();
      String type = a.getTYPE();
      String otherType = a.getOTHERTYPE();
      if (role == null || type == null || otherType == null) {
        break;
      } else {
        if (role.equals("CREATOR") && type.equals("OTHER") && otherType.equals("SOFTWARE")) {
          found = true;
          break;
        }
      }
    }
    if (!found) {
      details.setValid(false);
      details.addIssue("Must have a mets/metsHdr/agent[@OTHERTYPE=’SOFTWARE’] with the value Software\"");
    }
    return details;
  }

  /*
   * mets/metsHdr/agent/name The mandatory agent’s name element records the name
   * of the software tool used to create the IP.
   */

  private ReporterDetails validateCSIP14() {
    ReporterDetails details = new ReporterDetails();
    for (MetsType.MetsHdr.Agent a : agents) {
      String role = a.getROLE();
      String type = a.getTYPE();
      String otherType = a.getOTHERTYPE();
      if (role == null || type == null || otherType == null) {
        break;
      } else {
        if (role.equals("CREATOR") && type.equals("OTHER") && otherType.equals("SOFTWARE")) {
          if (a.getName() == null) {
            details.setValid(false);
            details.addIssue("mets/metsHdr/agent/name can't be null!");
          } else {
            if (a.getName().equals("")) {
              details.setValid(false);
              details.addIssue("mets/metsHdr/agent/name can't be empty!");
            }
          }
        }
      }
    }
    return details;
  }

  /*
   * mets/metsHdr/agent/note The mandatory agent’s note element records the
   * version of the tool used to create the IP.
   */

  private ReporterDetails validateCSIP15() {
    ReporterDetails details = new ReporterDetails();
    for (MetsType.MetsHdr.Agent a : agents) {
      String role = a.getROLE();
      String type = a.getTYPE();
      String otherType = a.getOTHERTYPE();
      if (role == null || type == null || otherType == null) {
        break;
      } else {
        if (role.equals("CREATOR") && type.equals("OTHER") && otherType.equals("SOFTWARE")) {
          List<MetsType.MetsHdr.Agent.Note> notes = a.getNote();
          if (notes == null || notes.size() == 0) {
            details.setValid(false);
            details.addIssue("mets/metsHdr/agent/note can't be null");
          } else {
            if (notes.size() > 1) {
              details.setValid(false);
              details.addIssue("mets/metsHdr/agent/note exists more than once");
            } else {
              for (MetsType.MetsHdr.Agent.Note note : notes) {
                if (note.getValue().isEmpty()) {
                  details.setValid(false);
                  details.addIssue("mets/metsHdr/agent/note can't be empty");
                }
              }
            }
          }
        }
      }
    }
    return details;
  }

  /*
   * mets/metsHdr/agent/note[@csip:NOTETYPE=’SOFTWARE VERSION’] The mandatory
   * agent element’s note child has a @csip:NOTETYPE attribute with a fixed value
   * of “SOFTWARE VERSION”.See also: Note type
   */
  private ReporterDetails validateCSIP16() {
    ReporterDetails details = new ReporterDetails();
    for (MetsType.MetsHdr.Agent a : agents) {
      String role = a.getROLE();
      String type = a.getTYPE();
      String otherType = a.getOTHERTYPE();
      if (role == null || type == null || otherType == null) {
        break;
      } else {
        if (role.equals("CREATOR") && type.equals("OTHER") && otherType.equals("SOFTWARE")) {
          List<MetsType.MetsHdr.Agent.Note> notes = a.getNote();
          if (notes == null || notes.size() == 0) {
            details.setValid(false);
            details.addIssue("mets/metsHdr/agent/note can't be null");
          } else {
            for (MetsType.MetsHdr.Agent.Note note : notes) {
              if (note.getNOTETYPE() == null) {
                details.setValid(false);
                details.addIssue("mets/metsHdr/agent/note[@csip:NOTETYPE=’SOFTWARE VERSION’] can't be null");
              } else {
                if (!note.getNOTETYPE().equals("SOFTWARE VERSION")) {
                  details.setValid(false);
                  details.addIssue(
                    "mets/metsHdr/agent/note[@csip:NOTETYPE=’SOFTWARE VERSION’] value must be SOFTWARE VERSION");
                }
              }
            }
          }
        }
      }
    }
    return details;
  }

  /* METS header SIP validation */

  /*
   * metsHdr/@RECORDSTATUS A way of indicating the status of the package and to
   * instruct the OAIS on how to properly handle the package. If not set, the
   * expected behaviour is equal to NEW.See also: Package status
   */

  private ReporterDetails validateSIP3() {
    String recordStatus = metsHdr.getRECORDSTATUS();
    if (recordStatus != null) {
      if (!recordsStatus.contains(recordStatus)) {
        return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION,
          "metsHdr/@RECORDSTATUS value isn't valid", false, false);
      }
    } else {
      return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION,
        "You can add the status of the package with metsHdr/@RECORDSTATUS", false, false);
    }
    return new ReporterDetails();
  }

  /*
   * metsHdr/@csip:OAISPACKAGETYPE
   * 
   * @csip:OAISPACKAGETYPE is used with the value “SIP”.See also: OAIS Package
   * type
   */

  private ReporterDetails validateSIP4() {
    String oaisPackageType = metsHdr.getOAISPACKAGETYPE();
    if (oaisPackageType != null) {
      if (!oaisPackageType.equals("SIP")) {
        return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION,
          "metsHdr/@csip:OAISPACKAGETYPE must be used with the value SIP", false, false);
      }
    } else {
      return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION,
        "metsHdr/@csip:OAISPACKAGETYPE can't be null", false, false);
    }
    return new ReporterDetails();
  }

  /*
   * metsHdr/altRecordID A reference to the Submission Agreement associated with
   * the package.
   * 
   * @TYPE is used with the value “SUBMISSIONAGREEMENT”. Example: RA 13-2011/5329;
   * 2012-04-12 Example:
   * http://submissionagreement.kb.se/dnr331-1144-2011/20120711/ Note: It is
   * recommended to use a machine-readable format for a better description of a
   * submission agreement. For example, the submission agreement developed by
   * Docuteam GmbH http://www.loc.gov/standards/mets/profiles/00000041.xmlSee
   * also: Alternative record ID’s
   */

  private ReporterDetails validateSIP5() {
    List<MetsType.MetsHdr.AltRecordID> altRecordIDS = metsHdr.getAltRecordID();
    boolean found = false;
    int count = 0;
    if (altRecordIDS != null && altRecordIDS.size() != 0) {
      for (MetsType.MetsHdr.AltRecordID altRecordID : altRecordIDS) {
        String type = altRecordID.getTYPE();
        if (type.equals("SUBMISSIONAGREEMENT")) {
          found = true;
          count++;
        }
      }
      if (!found) {
        return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION, "metsHdr/altRecordID "
          + "A reference to the Submission Agreement associated with the package. @TYPE is used with the value “SUBMISSIONAGREEMENT”",
          false, false);
      }
      if (count > 1) {
        return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION,
          "Can't have more than one metsHdr/altRecordID of the TYPE SUBMISSIONAGREEMENT ", false, false);
      }
    } else {
      return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION,
        "metsHdr/altRecordID can be added to the package if you pretend", false, false);
    }
    return new ReporterDetails();
  }

  /*
   * metsHdr/altRecordID An optional reference to a previous submission
   * agreement(s) which the information may have belonged to. @TYPE is used with
   * the value “PREVIOUSSUBMISSIONAGREEMENT”. Example: FM 12-2387/12726,
   * 2007-09-19 Example:
   * http://submissionagreement.kb.se/dnr331-1144-2011/20120711/ Note: It is
   * recommended to use a machine-readable format for a better description of a
   * submission agreement. For example, the submission agreement developed by
   * Docuteam GmbH http://www.loc.gov/standards/mets/profiles/00000041.xmlSee
   * also: Alternative record ID’s
   */
  private ReporterDetails validateSIP6() {
    List<MetsType.MetsHdr.AltRecordID> altRecordIDS = metsHdr.getAltRecordID();
    boolean found = false;
    if (altRecordIDS != null && altRecordIDS.size() != 0) {
      for (MetsType.MetsHdr.AltRecordID altRecordID : altRecordIDS) {
        String type = altRecordID.getTYPE();
        if (type.equals("PREVIOUSSUBMISSIONAGREEMENT")) {
          found = true;
        }
      }
      if (!found) {
        return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION,
          "metsHdr/altRecordID " + "An optional reference to a previous submission agreement(s) which the "
            + "information may have belonged to. @TYPE is used with the value " + "“PREVIOUSSUBMISSIONAGREEMENT”",
          false, false);
      }
    } else {
      return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION,
        "metsHdr/altRecordID can be added to the package if you pretend", false, false);
    }
    return new ReporterDetails();
  }

  /*
   * metsHdr/altRecordID An optional reference code indicating where in the
   * archival hierarchy the package shall be placed in the OAIS. @TYPE is used
   * with the value “REFERENCECODE”. Example: FM 12-2387/12726, 2007-09-19See
   * also: Alternative record ID’s
   */
  private ReporterDetails validateSIP7() {
    List<MetsType.MetsHdr.AltRecordID> altRecordIDS = metsHdr.getAltRecordID();
    boolean found = false;
    int count = 0;
    if (altRecordIDS != null && altRecordIDS.size() != 0) {
      for (MetsType.MetsHdr.AltRecordID altRecordID : altRecordIDS) {
        String type = altRecordID.getTYPE();
        if (type.equals("REFERENCECODE")) {
          found = true;
          count++;
        }
      }
      if (!found) {
        return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION,
          "metsHdr/altRecordID " + "An optional reference code indicating where in the archival hierarchy the "
            + "package shall be placed in the OAIS. @TYPE is used with the value " + "“REFERENCECODE”",
          false, false);
      }
      if (count > 1) {
        return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION,
          "Can't have more than one metsHdr/altRecordID of the type REFERENCECODE ", false, false);
      }
    } else {
      return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION,
        "metsHdr/altRecordID can be added to the package if you pretend", false, false);
    }
    return new ReporterDetails();
  }

  /*
   * metsHdr/altRecordID In cases where the SIP originates from other institutions
   * maintaining a reference code structure, this element can be used to record
   * these reference codes and therefore support the provenance of the package
   * when a whole archival description is not submitted with the submission. @TYPE
   * is used with the value “PREVIOUSREFERENCECODE”. Example:
   * SE/FM/123/123.1/123.1.3See also: Alternative record ID’s
   */
  private ReporterDetails validateSIP8() {
    List<MetsType.MetsHdr.AltRecordID> altRecordIDS = metsHdr.getAltRecordID();
    boolean found = false;
    if (altRecordIDS != null && altRecordIDS.size() != 0) {
      for (MetsType.MetsHdr.AltRecordID altRecordID : altRecordIDS) {
        String type = altRecordID.getTYPE();
        if (type.equals("PREVIOUSSUBMISSIONAGREEMENT")) {
          found = true;
        }
      }
      if (!found) {
        return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION,
          "metsHdr/altRecordID " + "In cases where the SIP originates from other institutions maintaining a "
            + "reference code structure, this element can be used to record these reference "
            + "codes and therefore support the provenance of the package when a whole "
            + "archival description is not submitted with the submission. @TYPE is used "
            + "with the value “PREVIOUSREFERENCECODE””",
          false, false);
      }
    } else {
      return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION,
        "metsHdr/altRecordID can be added to the package if you pretend", false, false);
    }
    return new ReporterDetails();
  }

  /*
   * metsHdr/agent A wrapper element that enables to encode the name of the
   * organisation or person that originally created the data being transferred.
   * Please note that this might be dierent from the organisation which has been
   * charged with preparing and sending the SIP to the archives.
   */
  private ReporterDetails validateSIP9() {
    boolean found = false;
    int count = 0;
    if (agents != null && agents.size() != 0) {
      for (MetsType.MetsHdr.Agent agent : agents) {
        String role = agent.getROLE();
        if (role != null) {
          if (role.equals("ARCHIVIST")) {
            found = true;
            count++;
          }
        } else {
          return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION,
            "metsHdr/agent/@ROLE can't be null ", false, false);
        }
      }
      if (!found) {
        return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION,
          "metsHdr/agent you can add agent with the ROLE ARCHIVIST ", false, false);
      } else {
        if (count != 1) {
          return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION,
            "Can't have more than one metsHdr/agent with the ROLE ARCHIVIST", false, false);
        }
      }
    } else {
      return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION,
        "metsHdr/agent A wrapper element that enables to encode the name of the organisation or person that originally created the data being transferred. Please note that this might be different from the organisation which has been charged with preparing and sending the SIP to the archives.",
        false, false);
    }
    return new ReporterDetails();
  }

  /*
   * metsHdr/agent/@ROLE The role of the person(s) or institution(s) responsible
   * for the document/collection.
   */
  private ReporterDetails validateSIP10() {
    boolean found = false;
    int count = 0;
    if (agents != null && agents.size() != 0) {
      for (MetsType.MetsHdr.Agent agent : agents) {
        String role = agent.getROLE();
        if (role != null) {
          if (role.equals("ARCHIVIST")) {
            found = true;
            count++;
          }
        } else {
          return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION,
            "metsHdr/agent/@ROLE can't be null ", false, false);
        }
      }
      if (!found) {
        return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION,
          "metsHdr/agent you can add agent with the ROLE ARCHIVIST ", false, false);
      } else {
        if (count != 1) {
          return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION,
            "Can't have more than one metsHdr/agent with the ROLE ARCHIVIST", false, false);
        }
      }
    } else {
      return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION,
        "metsHdr/agent A wrapper element that enables to encode the name of the organisation or person that originally created the data being transferred. Please note that this might be different from the organisation which has been charged with preparing and sending the SIP to the archives.",
        false, false);
    }
    return new ReporterDetails();
  }

  /*
   * metsHdr/agent/@TYPE The type of the archival creator agent is “ORGANIZATION”
   * or “INDIVIDUAL”
   */
  private ReporterDetails validateSIP11() {
    if (agents != null && agents.size() != 0) {
      for (MetsType.MetsHdr.Agent agent : agents) {
        String role = agent.getROLE();
        if (role != null) {
          if (role.equals("ARCHIVIST")) {
            String type = agent.getTYPE();
            if (!type.equals("ORGANIZATION") && !type.equals("INDIVIDUAL")) {
              return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION,
                "When the metsHdr/agent/@ROLE is ARCHIVIST  metsHdr/agent/@TYPE must be ORGANIZATION or INDIVIDUAL",
                false, false);
            }
          }
        } else {
          return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION,
            "metsHdr/agent/@ROLE can't be null ", false, false);
        }
      }
    } else {
      return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION,
        "metsHdr/agent A wrapper element that enables to encode the name of the organisation or person that originally created the data being transferred. Please note that this might be different from the organisation which has been charged with preparing and sending the SIP to the archives.",
        false, false);
    }
    return new ReporterDetails();
  }

  /*
   * metsHdr/agent/name The name of the organisation(s) that originally created
   * the data being transferred. Please note that this might be dierent from the
   * organisation which has been charged with preparing and sending the SIP to the
   * archives.
   */
  private ReporterDetails validateSIP12() {
    if (agents != null && agents.size() != 0) {
      for (MetsType.MetsHdr.Agent agent : agents) {
        String role = agent.getROLE();
        if (role != null) {
          if (role.equals("ARCHIVIST")) {
            String name = agent.getName();
            if (name == null) {
              return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION,
                "metsHdr/agent/name can't be null", false, false);
            }
          }
        } else {
          return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION,
            "metsHdr/agent/@ROLE can't be null ", false, false);
        }
      }
    } else {
      return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION,
        "metsHdr/agent A wrapper element that enables to encode the name of the organisation or person that originally created the data being transferred. Please note that this might be different from the organisation which has been charged with preparing and sending the SIP to the archives.",
        false, false);
    }
    return new ReporterDetails();
  }

  /*
   * metsHdr/agent/note The archival creator agent has a note providing a unique
   * identification code for the archival creator.
   */
  private ReporterDetails validateSIP13() {
    if (agents != null && agents.size() != 0) {
      for (MetsType.MetsHdr.Agent agent : agents) {
        String role = agent.getROLE();
        if (role != null) {
          if (role.equals("ARCHIVIST")) {
            List<MetsType.MetsHdr.Agent.Note> notes = agent.getNote();
            if (notes != null && notes.size() != 0) {
              if (notes.size() != 1) {
                return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION,
                  "metsHdr/agent/note can't be more than one ", false, false);
              }
            } else {
              return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION,
                "You can add one metsHdr/agent/note ", false, false);
            }
          }
        } else {
          return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION,
            "metsHdr/agent/@ROLE can't be null ", false, false);
        }
      }
    } else {
      return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION,
        "metsHdr/agent A wrapper element that enables to encode the name of the organisation or person that originally created the data being transferred. Please note that this might be different from the organisation which has been charged with preparing and sending the SIP to the archives.",
        false, false);
    }
    return new ReporterDetails();
  }

  /*
   * metsHdr/agent/note/@csip:NOTETYPE The archival creator agent note is typed
   * with the value of “IDENTIFICATIONCODE”.See also: Note type
   */
  private ReporterDetails validateSIP14() {
    if (agents != null && agents.size() != 0) {
      for (MetsType.MetsHdr.Agent agent : agents) {
        String role = agent.getROLE();
        if (role != null) {
          if (role.equals("ARCHIVIST")) {
            List<MetsType.MetsHdr.Agent.Note> notes = agent.getNote();
            if (notes != null && notes.size() != 0) {
              for (MetsType.MetsHdr.Agent.Note note : notes) {
                String noteType = note.getNOTETYPE();
                if (noteType == null || noteType.equals("")) {
                  return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION,
                    "metsHdr/agent/@csip:NOTETYPE can't be null or empty", false, false);
                }
              }
            }
          }
        } else {
          return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION,
            "metsHdr/agent/@ROLE can't be null ", false, false);
        }
      }
    } else {
      return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION,
        "metsHdr/agent A wrapper element that enables to encode the name of the organisation or person that originally created the data being transferred. Please note that this might be different from the organisation which has been charged with preparing and sending the SIP to the archives.",
        false, false);
    }
    return new ReporterDetails();
  }

  /*
   * metsHdr/agent The name of the organisation or person submitting the package
   * to the archive.
   */
  private ReporterDetails validateSIP15() {
    boolean found = false;
    int count = 0;
    if (agents != null && agents.size() != 0) {
      for (MetsType.MetsHdr.Agent agent : agents) {
        String role = agent.getROLE();
        if (role != null) {
          if (role.equals("OTHER")) {
            found = true;
            count++;
          }
        } else {
          return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION,
            "metsHdr/agent/@ROLE can't be null ", false, false);
        }
      }
      if (!found) {
        return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION,
          "metsHdr/agent you can add agent with the ROLE OTHER ", false, false);
      } else {
        if (count != 1) {
          return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION,
            "Can't have more than one metsHdr/agent with the ROLE OTHER", false, false);
        }
      }
    } else {
      return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION,
        "metsHdr/agent A wrapper element that enables to encode the name of the organisation or person that originally created the data being transferred. Please note that this might be different from the organisation which has been charged with preparing and sending the SIP to the archives.",
        false, false);
    }
    return new ReporterDetails();
  }

  /*
   * metsHdr/agent/@ROLE The role of the person(s) or institution(s) responsible
   * for creating and/or submitting the package.
   */
  private ReporterDetails validateSIP16() {
    boolean found = false;
    int count = 0;
    if (agents != null && agents.size() != 0) {
      for (MetsType.MetsHdr.Agent agent : agents) {
        String role = agent.getROLE();
        if (role != null) {
          if (role.equals("OTHER")) {
            found = true;
            count++;
          }
        } else {
          return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION,
            "metsHdr/agent/@ROLE can't be null ", false, false);
        }
      }
      if (!found) {
        return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION,
          "metsHdr/agent you can add agent with the ROLE OTHER ", false, false);
      } else {
        if (count != 1) {
          return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION,
            "Can't have more than one metsHdr/agent with the ROLE OTHER", false, false);
        }
      }
    } else {
      return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION,
        "metsHdr/agent A wrapper element that enables to encode the name of the organisation or person that originally created the data being transferred. Please note that this might be different from the organisation which has been charged with preparing and sending the SIP to the archives.",
        false, false);
    }
    return new ReporterDetails();
  }

  /*
   * metsHdr/agent/@TYPE The type of the submitting agent is “ORGANIZATION” or
   * “INDIVIDUAL”
   */
  private ReporterDetails validateSIP17() {
    if (agents != null && agents.size() != 0) {
      for (MetsType.MetsHdr.Agent agent : agents) {
        String role = agent.getROLE();
        if (role != null) {
          if (role.equals("OTHER")) {
            String type = agent.getTYPE();
            if (!type.equals("ORGANIZATION") && !type.equals("INDIVIDUAL")) {
              return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION,
                "When the metsHdr/agent/@ROLE is OTHER  metsHdr/agent/@TYPE must be ORGANIZATION or INDIVIDUAL", false,
                false);
            }
          }
        } else {
          return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION,
            "metsHdr/agent/@ROLE can't be null ", false, false);
        }
      }
    } else {
      return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION,
        "metsHdr/agent A wrapper element that enables to encode the name of the organisation or person that originally created the data being transferred. Please note that this might be different from the organisation which has been charged with preparing and sending the SIP to the archives.",
        false, false);
    }
    return new ReporterDetails();
  }

  /*
   * metsHdr/agent/name Name of the organisation submitting the package to the
   * archive
   */
  private ReporterDetails validateSIP18() {
    if (agents != null && agents.size() != 0) {
      for (MetsType.MetsHdr.Agent agent : agents) {
        String role = agent.getROLE();
        if (role != null) {
          if (role.equals("OTHER")) {
            String name = agent.getName();
            if (name == null) {
              return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION,
                "metsHdr/agent/name can't be null", false, false);
            }
          }
        } else {
          return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION,
            "metsHdr/agent/@ROLE can't be null ", false, false);
        }
      }
    } else {
      return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION,
        "metsHdr/agent A wrapper element that enables to encode the name of the organisation or person that originally created the data being transferred. Please note that this might be different from the organisation which has been charged with preparing and sending the SIP to the archives.",
        false, false);
    }
    return new ReporterDetails();
  }

  /*
   * metsHdr/agent/note The submitting agent has a note providing a unique
   * identification code for the archival creator.
   */
  private ReporterDetails validateSIP19() {
    if (agents != null && agents.size() != 0) {
      for (MetsType.MetsHdr.Agent agent : agents) {
        String role = agent.getROLE();
        if (role != null) {
          if (role.equals("OTHER")) {
            List<MetsType.MetsHdr.Agent.Note> notes = agent.getNote();
            if (notes != null && notes.size() != 0) {
              if (notes.size() != 1) {
                return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION,
                  "metsHdr/agent/note can't be more than one ", false, false);
              }
            } else {
              return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION,
                "You can add one metsHdr/agent/note ", false, false);
            }
          }
        } else {
          return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION,
            "metsHdr/agent/@ROLE can't be null ", false, false);
        }
      }
    } else {
      return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION,
        "metsHdr/agent A wrapper element that enables to encode the name of the organisation or person that originally created the data being transferred. Please note that this might be different from the organisation which has been charged with preparing and sending the SIP to the archives.",
        false, false);
    }
    return new ReporterDetails();
  }

  /*
   * metsHdr/agent/note/@csip:NOTETYPE The submitting agent note is typed with the
   * value of “IDENTIFICATIONCODE”.See also: Note type
   */
  private ReporterDetails validateSIP20() {
    if (agents != null && agents.size() != 0) {
      for (MetsType.MetsHdr.Agent agent : agents) {
        String role = agent.getROLE();
        if (role != null) {
          if (role.equals("OTHER")) {
            List<MetsType.MetsHdr.Agent.Note> notes = agent.getNote();
            if (notes != null && notes.size() != 0) {
              for (MetsType.MetsHdr.Agent.Note note : notes) {
                String noteType = note.getNOTETYPE();
                if (noteType == null || noteType.equals("")) {
                  return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION,
                    "metsHdr/agent/@csip:NOTETYPE can't be null or empty", false, false);
                }
              }
            }
          }
        } else {
          return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION,
            "metsHdr/agent/@ROLE can't be null ", false, false);
        }
      }
    } else {
      return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION,
        "metsHdr/agent A wrapper element that enables to encode the name of the organisation or person that originally created the data being transferred. Please note that this might be different from the organisation which has been charged with preparing and sending the SIP to the archives.",
        false, false);
    }
    return new ReporterDetails();
  }

  /*
   * metsHdr/agent Contact person for the submission.
   */
  private ReporterDetails validateSIP21() {
    boolean found = false;
    if (agents != null && agents.size() != 0) {
      for (MetsType.MetsHdr.Agent agent : agents) {
        String role = agent.getROLE();
        if (role != null) {
          if (role.equals("CREATOR")) {
            found = true;
          }
        } else {
          return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION,
            "metsHdr/agent/@ROLE can't be null ", false, false);
        }
      }
      if (!found) {
        return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION,
          "metsHdr/agent you can add agent with the ROLE CREATOR ", false, false);
      }
    } else {
      return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION,
        "metsHdr/agent A wrapper element that enables to encode the name of the organisation or person that originally created the data being transferred. Please note that this might be different from the organisation which has been charged with preparing and sending the SIP to the archives.",
        false, false);
    }
    return new ReporterDetails();
  }

  /*
   * metsHdr/agent/@ROLE The role of the contact person is “CREATOR”.
   */
  private ReporterDetails validateSIP22() {
    boolean found = false;
    if (agents != null && agents.size() != 0) {
      for (MetsType.MetsHdr.Agent agent : agents) {
        String role = agent.getROLE();
        if (role != null) {
          if (role.equals("CREATOR")) {
            found = true;
          }
        } else {
          return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION,
            "metsHdr/agent/@ROLE can't be null ", false, false);
        }
      }
      if (!found) {
        return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION,
          "metsHdr/agent you can add agent with the ROLE CREATOR ", false, false);
      }
    } else {
      return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION,
        "metsHdr/agent A wrapper element that enables to encode the name of the organisation or person that originally created the data being transferred. Please note that this might be different from the organisation which has been charged with preparing and sending the SIP to the archives.",
        false, false);
    }
    return new ReporterDetails();
  }

  /*
   * metsHdr/agent/@TYPE The type of the contact person agent is “INDIVIDUAL”
   */
  private ReporterDetails validateSIP23() {
    if (agents != null && agents.size() != 0) {
      for (MetsType.MetsHdr.Agent agent : agents) {
        String role = agent.getROLE();
        if (role != null) {
          if (role.equals("CREATOR")) {
            String type = agent.getTYPE();
            if (!type.equals("INDIVIDUAL")) {
              return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION,
                "When the metsHdr/agent/@ROLE is CREATOR  metsHdr/agent/@TYPE must be ORGANIZATION or INDIVIDUAL",
                false, false);
            }
          }
        } else {
          return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION,
            "metsHdr/agent/@ROLE can't be null ", false, false);
        }
      }
    } else {
      return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION,
        "metsHdr/agent A wrapper element that enables to encode the name of the organisation or person that originally created the data being transferred. Please note that this might be different from the organisation which has been charged with preparing and sending the SIP to the archives.",
        false, false);
    }
    return new ReporterDetails();
  }

  /*
   * metsHdr/agent/name Name of the contact person.
   */
  private ReporterDetails validateSIP24() {
    if (agents != null && agents.size() != 0) {
      for (MetsType.MetsHdr.Agent agent : agents) {
        String role = agent.getROLE();
        if (role != null) {
          if (role.equals("CREATOR")) {
            String name = agent.getName();
            if (name == null) {
              return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION,
                "metsHdr/agent/name can't be null", false, false);
            }
          }
        } else {
          return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION,
            "metsHdr/agent/@ROLE can't be null ", false, false);
        }
      }
    } else {
      return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION,
        "metsHdr/agent A wrapper element that enables to encode the name of the organisation or person that originally created the data being transferred. Please note that this might be different from the organisation which has been charged with preparing and sending the SIP to the archives.",
        false, false);
    }
    return new ReporterDetails();
  }

  /*
   * metsHdr/agent/note The submitting agent has one or more notes giving the
   * contact information
   */
  private ReporterDetails validateSIP25() {
    if (agents != null && agents.size() != 0) {
      for (MetsType.MetsHdr.Agent agent : agents) {
        String role = agent.getROLE();
        if (role != null) {
          if (role.equals("CREATOR")) {
            List<MetsType.MetsHdr.Agent.Note> notes = agent.getNote();
            if (notes == null || notes.size() == 0) {
              return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION,
                "can add contact information in one or more metsHdr/agent/note ", false, false);
            }
          }
        } else {
          return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION,
            "metsHdr/agent/@ROLE can't be null ", false, false);
        }
      }
    } else {
      return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION,
        "metsHdr/agent A wrapper element that enables to encode the name of the organisation or person that originally created the data being transferred. Please note that this might be different from the organisation which has been charged with preparing and sending the SIP to the archives.",
        false, false);
    }
    return new ReporterDetails();

  }

  /*
   * metsHdr/agent The organisation or person that preserves the package.
   */
  private ReporterDetails validateSIP26() {
    boolean found = false;
    int count = 0;
    if (agents != null && agents.size() != 0) {
      for (MetsType.MetsHdr.Agent agent : agents) {
        String role = agent.getROLE();
        if (role != null) {
          if (role.equals("PRESERVATION")) {
            found = true;
            count++;
          }
        } else {
          return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION,
            "metsHdr/agent/@ROLE can't be null ", false, false);
        }
      }
      if (!found) {
        return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION,
          "metsHdr/agent you can add agent with the ROLE PRESERVATION ", false, false);
      } else {
        if (count != 1) {
          return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION,
            "Can't have more than one metsHdr/agent with the ROLE PRESERVATION", false, false);
        }
      }
    } else {
      return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION,
        "metsHdr/agent A wrapper element that enables to encode the name of the organisation or person that originally created the data being transferred. Please note that this might be different from the organisation which has been charged with preparing and sending the SIP to the archives.",
        false, false);
    }
    return new ReporterDetails();
  }

  /*
   * metsHdr/agent/@ROLE The role of the preservation agent is “PRESERVATION”.
   */
  private ReporterDetails validateSIP27() {
    boolean found = false;
    int count = 0;
    if (agents != null && agents.size() != 0) {
      for (MetsType.MetsHdr.Agent agent : agents) {
        String role = agent.getROLE();
        if (role != null) {
          if (role.equals("PRESERVATION")) {
            found = true;
            count++;
          }
        } else {
          return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION,
            "metsHdr/agent/@ROLE can't be null ", false, false);
        }
      }
      if (!found) {
        return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION,
          "metsHdr/agent you can add agent with the ROLE PRESERVATION ", false, false);
      } else {
        if (count != 1) {
          return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION,
            "Can't have more than one metsHdr/agent with the ROLE OTHER", false, false);
        }
      }
    } else {
      return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION,
        "metsHdr/agent A wrapper element that enables to encode the name of the organisation or person that originally created the data being transferred. Please note that this might be different from the organisation which has been charged with preparing and sending the SIP to the archives.",
        false, false);
    }
    return new ReporterDetails();
  }

  /*
   * metsHdr/agent/@TYPE The type of the submitting agent is “ORGANIZATION”.
   */
  private ReporterDetails validateSIP28() {
    if (agents != null && agents.size() != 0) {
      for (MetsType.MetsHdr.Agent agent : agents) {
        String role = agent.getROLE();
        if (role != null) {
          if (role.equals("PRESERVATION")) {
            String type = agent.getTYPE();
            if (!type.equals("ORGANIZATION")) {
              return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION,
                "When the metsHdr/agent/@ROLE is PRESERVATION  metsHdr/agent/@TYPE must be ORGANIZATION", false, false);
            }
          }
        } else {
          return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION,
            "metsHdr/agent/@ROLE can't be null ", false, false);
        }
      }
    } else {
      return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION,
        "metsHdr/agent A wrapper element that enables to encode the name of the organisation or person that originally created the data being transferred. Please note that this might be different from the organisation which has been charged with preparing and sending the SIP to the archives.",
        false, false);
    }
    return new ReporterDetails();
  }

  /*
   * metsHdr/agent/name Name of the organisation preserving the package.
   */
  private ReporterDetails validateSIP29() {

    if (agents != null && agents.size() != 0) {
      for (MetsType.MetsHdr.Agent agent : agents) {
        String role = agent.getROLE();
        if (role != null) {
          if (role.equals("PRESERVATION")) {
            String name = agent.getName();
            if (name == null) {
              return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION,
                "metsHdr/agent/name can't be null", false, false);
            }
          }
        } else {
          return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION,
            "metsHdr/agent/@ROLE can't be null ", false, false);
        }
      }
    } else {
      return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION,
        "metsHdr/agent A wrapper element that enables to encode the name of the organisation or person that originally created the data being transferred. Please note that this might be different from the organisation which has been charged with preparing and sending the SIP to the archives.",
        false, false);
    }
    return new ReporterDetails();
  }

  /*
   * metsHdr/agent/note The preservation agent has a note providing a unique
   * identification code for the archival creator.
   */
  private ReporterDetails validateSIP30() {
    if (agents != null && agents.size() != 0) {
      for (MetsType.MetsHdr.Agent agent : agents) {
        String role = agent.getROLE();
        if (role != null) {
          if (role.equals("PRESERVATION")) {
            List<MetsType.MetsHdr.Agent.Note> notes = agent.getNote();
            if (notes != null && notes.size() != 0) {
              if (notes.size() != 1) {
                return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION,
                  "metsHdr/agent/note can't be more than one ", false, false);
              }
            } else {
              return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION,
                "You can add one metsHdr/agent/note", false, false);
            }
          }
        } else {
          return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION,
            "metsHdr/agent/@ROLE can't be null ", false, false);
        }
      }
    } else {
      return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION,
        "metsHdr/agent A wrapper element that enables to encode the name of the organisation or person that originally created the data being transferred. Please note that this might be different from the organisation which has been charged with preparing and sending the SIP to the archives.",
        false, false);
    }
    return new ReporterDetails();
  }

  /*
   * metsHdr/agent/note/@csip:NOTETYPE The preservation agent note is typed with
   * the value of “IDENTIFICATIONCODE”.See also: Note type
   */
  private ReporterDetails validateSIP31() {
    if (agents != null && agents.size() != 0) {
      for (MetsType.MetsHdr.Agent agent : agents) {
        String role = agent.getROLE();
        if (role != null) {
          if (role.equals("PRESERVATION")) {
            List<MetsType.MetsHdr.Agent.Note> notes = agent.getNote();
            if (notes != null && notes.size() != 0) {
              for (MetsType.MetsHdr.Agent.Note note : notes) {
                String noteType = note.getNOTETYPE();
                if (noteType == null || noteType.equals("")) {
                  return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION,
                    "metsHdr/agent/@csip:NOTETYPE can't be null or empty", false, false);
                }
              }
            }
          }
        } else {
          return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION,
            "metsHdr/agent/@ROLE can't be null ", false, false);
        }
      }
    } else {
      return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION,
        "metsHdr/agent A wrapper element that enables to encode the name of the organisation or person that originally created the data being transferred. Please note that this might be different from the organisation which has been charged with preparing and sending the SIP to the archives.",
        false, false);
    }
    return new ReporterDetails();
  }
}
