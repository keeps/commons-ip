package org.roda_project.commons_ip2.validator.component.administritiveMetadataComponent;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLDecoder;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.roda_project.commons_ip2.mets_v1_12.beans.AmdSecType;
import org.roda_project.commons_ip2.mets_v1_12.beans.MdSecType;
import org.roda_project.commons_ip2.validator.common.ControlledVocabularyParser;
import org.roda_project.commons_ip2.validator.common.MetsParser;
import org.roda_project.commons_ip2.validator.component.ValidatorComponentImpl;
import org.roda_project.commons_ip2.validator.constants.Constants;
import org.roda_project.commons_ip2.validator.constants.ConstantsCSIPspec;
import org.roda_project.commons_ip2.validator.handlers.MetsHandler;
import org.roda_project.commons_ip2.validator.reporter.ReporterDetails;
import org.roda_project.commons_ip2.validator.utils.CHECKSUMTYPE;
import org.roda_project.commons_ip2.validator.utils.MetadataType;

/**
 * @author João Gomes <jgomes@keep.pt>
 */
public class AdministritiveMetadataComponentValidator extends ValidatorComponentImpl {
  public static final String METS_AMD_SEC_DIGIPROV_MD_MD_REF_SIZE_DOESN_T_MATCH_WITH_THE_SIZE_OF_FILE = "mets/amdSec/digiprovMD/mdRef/@SIZE doesn't match with the size of file (";
  public static final String METS_OBJECTID_CAN_T_BE_NULL = "mets/@OBJECTID can't be null";
  public static final String UTF_8 = "UTF-8";
  public static final String METS_AMD_SEC_RIGHTS_MD_MD_REF_SIZE_DOESN_T_MATCH_WITH_THE_SIZE_OF_FILE = "mets/amdSec/rightsMD/mdRef/@SIZE doesn't match with the size of file (";

  private final String moduleName;
  private List<AmdSecType> amdSec;
  private List<String> dmdSecStatus;

  public void setDmdSecStatus(List<String> dmdSecStatus) {
    this.dmdSecStatus = dmdSecStatus;
  }

  public AdministritiveMetadataComponentValidator(String moduleName) {
    this.moduleName = moduleName;
    this.dmdSecStatus = new ArrayList<>();
    ControlledVocabularyParser controlledVocabularyParser = new ControlledVocabularyParser(
      Constants.PATH_RESOURCES_CSIP_VOCABULARY_DMD_SEC_STATUS, dmdSecStatus);
    controlledVocabularyParser.parse();
    setDmdSecStatus(controlledVocabularyParser.getData());
  }

  @Override
  public void validate() throws IOException {
    amdSec = mets.getAmdSec();
    ReporterDetails csip;

    /* CSIP31 */
    validationInit(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP31_ID);
    csip = validateCSIP31();
    csip.setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION);
    addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP31_ID, csip);

    /* CSIP32 */
    validationInit(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP32_ID);
    csip = validateCSIP32();
    csip.setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION);
    addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP32_ID, csip);

    if (csip.isValid() && !csip.isSkipped()) {

      /* CSIP33 */
      validationInit(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP33_ID);
      csip = validateCSIP33();
      csip.setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION);
      addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP33_ID, csip);

      /* CSIP34 */
      validationInit(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP34_ID);
      csip = validateCSIP34();
      csip.setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION);
      addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP34_ID, csip);

      /* CSIP35 */
      validationInit(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP35_ID);
      csip = validateCSIP35();
      csip.setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION);
      addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP35_ID, csip);

      if (csip.isValid()) {

        /* CSIP36 */
        validationInit(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP36_ID);
        csip = validateCSIP36();
        csip.setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION);
        addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP36_ID, csip);

        /* CSIP37 */
        validationInit(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP37_ID);
        csip = validateCSIP37();
        csip.setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION);
        addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP37_ID, csip);

        /* CSIP38 */
        validationInit(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP38_ID);
        csip = validateCSIP38();
        csip.setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION);
        addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP38_ID, csip);

        /* CSIP39 */
        validationInit(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP39_ID);
        csip = validateCSIP39();
        csip.setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION);
        addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP39_ID, csip);

        /* CSIP40 */
        validationInit(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP40_ID);
        csip = validateCSIP40();
        csip.setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION);
        addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP40_ID, csip);

        /* CSIP41 */
        validationInit(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP41_ID);
        csip = validateCSIP41();
        csip.setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION);
        addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP41_ID, csip);

        /* CSIP42 */
        validationInit(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP42_ID);
        csip = validateCSIP42();
        csip.setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION);
        addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP42_ID, csip);

        /* CSIP43 */
        validationInit(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP43_ID);
        try {
          csip = validateCSIP43();
        } catch (Exception e) {
          csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
            "Can't calculate checksum of file", false, false);
        }
        csip.setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION);
        addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP43_ID, csip);

        /* CSIP44 */
        validationInit(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP44_ID);
        csip = validateCSIP44();
        csip.setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION);
        addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP44_ID, csip);
      } else {
        String message = "SKIPPED because mets/amdSec/digiprovMD/mdRef doesn't exist! (" + metsName + ")";
        /* CSIP36 */
        csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, message, true, true);
        addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP36_ID, csip);

        /* CSIP37 */
        csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, message, true, true);
        addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP37_ID, csip);

        /* CSIP38 */
        csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, message, true, true);
        addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP38_ID, csip);

        /* CSIP39 */
        csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, message, true, true);
        addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP39_ID, csip);

        /* CSIP40 */
        csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, message, true, true);
        addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP40_ID, csip);

        /* CSIP41 */
        csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, message, true, true);
        addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP41_ID, csip);

        /* CSIP42 */
        csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, message, true, true);
        addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP42_ID, csip);

        /* CSIP43 */
        csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, message, true, true);
        addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP43_ID, csip);

        /* CSIP44 */
        csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, message, true, true);
        addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP44_ID, csip);
      }
    } else {
      String message = "SKIPPED because mets/amdSec/digiprovMD doesn't exist! (" + metsName + ")";

      /* CSIP33 */
      csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, message, true, true);
      addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP33_ID, csip);

      /* CSIP34 */
      csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, message, true, true);
      addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP34_ID, csip);

      /* CSIP35 */
      csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, message, true, true);
      addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP35_ID, csip);

      /* CSIP36 */
      csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, message, true, true);
      addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP36_ID, csip);

      /* CSIP37 */
      csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, message, true, true);
      addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP37_ID, csip);

      /* CSIP38 */
      csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, message, true, true);
      addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP38_ID, csip);

      /* CSIP39 */
      csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, message, true, true);
      addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP39_ID, csip);

      /* CSIP40 */
      csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, message, true, true);
      addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP40_ID, csip);

      /* CSIP41 */
      csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, message, true, true);
      addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP41_ID, csip);

      /* CSIP42 */
      csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, message, true, true);
      addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP42_ID, csip);

      /* CSIP43 */
      csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, message, true, true);
      addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP43_ID, csip);

      /* CSIP44 */
      csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, message, true, true);
      addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP44_ID, csip);
    }

    /* CSIP45 */
    validationInit(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP45_ID);
    csip = validateCSIP45();
    csip.setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION);
    addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP45_ID, csip);

    if (csip.isValid()) {
      /* CSIP46 */
      validationInit(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP46_ID);
      csip = validateCSIP46();
      csip.setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION);
      addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP46_ID, csip);

      /* CSIP47 */
      validationInit(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP47_ID);
      csip = validateCSIP47();
      csip.setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION);
      addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP47_ID, csip);

      /* CSIP48 */
      validationInit(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP48_ID);
      csip = validateCSIP48();
      csip.setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION);
      addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP48_ID, csip);

      if (csip.isValid()) {
        /* CSIP49 */
        validationInit(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP49_ID);
        csip = validateCSIP49();
        csip.setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION);
        addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP49_ID, csip);

        /* CSIP50 */
        validationInit(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP50_ID);
        csip = validateCSIP50();
        csip.setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION);
        addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP50_ID, csip);

        /* CSIP51 */
        validationInit(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP51_ID);
        csip = validateCSIP51();
        csip.setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION);
        addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP51_ID, csip);

        /* CSIP52 */
        validationInit(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP52_ID);
        csip = validateCSIP52();
        csip.setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION);
        addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP52_ID, csip);

        /* CSIP53 */
        validationInit(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP53_ID);
        csip = validateCSIP53();
        csip.setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION);
        addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP53_ID, csip);

        /* CSIP54 */
        validationInit(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP54_ID);
        csip = validateCSIP54();
        csip.setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION);
        addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP54_ID, csip);

        /* CSIP55 */
        validationInit(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP55_ID);
        csip = validateCSIP55();
        csip.setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION);
        addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP55_ID, csip);

        /* CSIP56 */
        validationInit(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP56_ID);
        try {
          csip = validateCSIP56();
        } catch (Exception e) {
          csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
            "Can't calculate checksum of file", false, false);
        }
        csip.setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION);
        addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP56_ID, csip);

        /* CSIP57 */
        validationInit(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP57_ID);
        csip = validateCSIP57();
        csip.setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION);
        addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP57_ID, csip);
      } else {
        String message = "SKIPPED because mets/amdSec/rightsMD/mdRef doesn't exist! (" + metsName + ")";

        /* CSIP49 */
        csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, message, true, true);
        addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP49_ID, csip);

        /* CSIP50 */
        csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, message, true, true);
        addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP50_ID, csip);

        /* CSIP51 */
        csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, message, true, true);
        addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP51_ID, csip);

        /* CSIP52 */
        csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, message, true, true);
        addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP52_ID, csip);

        /* CSIP53 */
        csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, message, true, true);
        addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP53_ID, csip);

        /* CSIP54 */
        csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, message, true, true);
        addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP54_ID, csip);

        /* CSIP55 */
        csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, message, true, true);
        addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP55_ID, csip);

        /* CSIP56 */
        csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, message, true, true);
        addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP56_ID, csip);

        /* CSIP57 */
        csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, message, true, true);
        addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP57_ID, csip);
      }
    } else {
      String message = "SKIPPED because mets/amdSec/rightsMD doesn't exist! (" + metsName + ")";

      /* CSIP46 */
      csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, message, true, true);
      addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP46_ID, csip);

      /* CSIP47 */
      csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, message, true, true);
      addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP47_ID, csip);

      /* CSIP48 */
      csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, message, true, true);
      addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP48_ID, csip);

      /* CSIP49 */
      csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, message, true, true);
      addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP49_ID, csip);

      /* CSIP50 */
      csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, message, true, true);
      addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP50_ID, csip);

      /* CSIP51 */
      csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, message, true, true);
      addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP51_ID, csip);

      /* CSIP52 */
      csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, message, true, true);
      addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP52_ID, csip);

      /* CSIP53 */
      csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, message, true, true);
      addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP53_ID, csip);

      /* CSIP54 */
      csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, message, true, true);
      addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP54_ID, csip);

      /* CSIP55 */
      csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, message, true, true);
      addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP55_ID, csip);

      /* CSIP56 */
      csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, message, true, true);
      addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP56_ID, csip);

      /* CSIP57 */
      csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, message, true, true);
      addResult(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP57_ID, csip);
    }
  }

  /*
   * mets/amdSec If administrative / preservation metadata is available, it must
   * be described using the administrative metadata section ( <amdSec> ) element.
   * All administrative metadata is present in a single <amdSec> element. It is
   * possible to transfer metadata in a package using just the descriptive
   * metadata section and/or administrative metadata section.
   */
  private ReporterDetails validateCSIP31() throws IOException {
    ReporterDetails details = new ReporterDetails();
    if (isZipFileFlag()) {
      String regex;
      if (isRootMets()) {
        String OBJECTID = mets.getOBJID();
        if (OBJECTID != null) {
          regex = OBJECTID + "/metadata/.*";
        } else {
          return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, METS_OBJECTID_CAN_T_BE_NULL,
            false, false);
        }
      } else {
        regex = metsPath + "metadata/.*";
      }
      if (amdSec == null || amdSec.isEmpty()) {
        if (zipManager.countMetadataFiles(getEARKSIPpath(), regex) != 0 && (mets.getDmdSec() == null || mets.getDmdSec().isEmpty())) {
            details.setValid(false);
            details.addIssue(
              "You have files in the metadata/folder, you must have mets/dmdSec or mets/amdSec (" + metsName + ")");
        }
      } else {
        if (zipManager.countMetadataFiles(getEARKSIPpath(), regex) == 0) {
          for (AmdSecType amd : amdSec) {
            if (amd.getDigiprovMD() != null && !amd.getDigiprovMD().isEmpty()) {
              return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
                "Doesn't have files in metadata folder but have in amdSec; Put the files under metadata folder", false,
                false);
            }
          }
        } else {
          HashMap<String, Boolean> metadataFiles = zipManager.getMetadataFiles(getEARKSIPpath(), regex);
          for (AmdSecType amd : mets.getAmdSec()) {
            for (MdSecType md : amd.getDigiprovMD()) {
              MdSecType.MdRef mdRef = md.getMdRef();
              if (mdRef != null) {
                String hrefDecoded = URLDecoder.decode(mdRef.getHref(), UTF_8);
                if (isRootMets()) {
                  if (metadataFiles.containsKey(mets.getOBJID() + "/" + hrefDecoded)) {
                    metadataFiles.replace(mets.getOBJID() + "/" + hrefDecoded, true);
                  }
                } else {
                  if (metadataFiles.containsKey(metsPath + hrefDecoded)) {
                    metadataFiles.replace(metsPath + hrefDecoded, true);
                  }
                }
              }
            }
          }
          if (metadataFiles.containsValue(false)) {
            for (MdSecType md : mets.getDmdSec()) {
              MdSecType.MdRef mdRef = md.getMdRef();
              if (mdRef != null) {
                String hrefDecoded = URLDecoder.decode(mdRef.getHref(), UTF_8);
                if (isRootMets()) {
                  if (metadataFiles.containsKey(mets.getOBJID() + "/" + hrefDecoded)) {
                    metadataFiles.replace(mets.getOBJID() + "/" + hrefDecoded, true);
                  }
                } else {
                  if (metadataFiles.containsKey(metsPath + hrefDecoded)) {
                    metadataFiles.replace(metsPath + hrefDecoded, true);
                  }
                }
              }
            }
            if (metadataFiles.containsValue(false)) {
              return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
                "Have metadata files not referenced in mets file", false, false);
            }
          }
        }
      }
    } else {
      if (mets.getAmdSec() == null) {
        if (folderManager.verifyMetadataFilesFolder(Paths.get(metsPath), "preservation")
          || folderManager.verifyMetadataFilesFolder(Paths.get(metsPath), "administritive")) {
          details.setValid(false);
          details.addIssue("You have files in the metadata/preservation folder, you must have mets/amdSec");
        }
      } else {
        int count = folderManager.countMetadataFiles(Paths.get(metsPath), "preservation")
          + folderManager.countMetadataFiles(Paths.get(metsPath), "administrative");
        if (mets.getAmdSec().size() != count) {
          details.setValid(false);
          details.addIssue(
            "The number of files described is not equal to the number of files in the metadata/preservation folder");
        }
      }
    }
    return details;
  }

  /*
   * mets/amdSec/digiprovMD For recording information about preservation the
   * standard PREMIS is used. It is mandatory to include one <digiprovMD> element
   * for each piece of PREMIS metadata. The use if PREMIS in METS is following the
   * recommendations in the 2017 version of PREMIS in METS Guidelines.
   */
  private ReporterDetails validateCSIP32() throws IOException {
    int countPremis;
    if (isZipFileFlag()) {
      String regex;
      if (isRootMets()) {
        String OBJECTID = mets.getOBJID();
        if (OBJECTID != null) {
          regex = OBJECTID + "/";
        } else {
          return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, METS_OBJECTID_CAN_T_BE_NULL,
            false, false);
        }
      } else {
        regex = metsPath;
      }
      countPremis = zipManager.countMetadataFiles(getEARKSIPpath(), regex + "metadata/preservation/.*")
        + zipManager.countMetadataFiles(getEARKSIPpath(), regex + "metadata/administrative/.*");
    } else {
      countPremis = folderManager.countMetadataFiles(Paths.get(metsPath), "preservation")
        + folderManager.countMetadataFiles(Paths.get(metsPath), "administrative");
    }
    for (AmdSecType a : amdSec) {
      List<MdSecType> digiprov = a.getDigiprovMD();
      if (countPremis != digiprov.size()) {
        return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
          "Number of mets/amdSec/digiprovMD/ isn't equal with number of premis metadata", false, false);
      } else {
        if (digiprov.isEmpty()) {
          return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
            "Skipped (" + metsPath + ") doesn't have premis metadata", true, true);
        }
      }
    }
    return new ReporterDetails();
  }

  /*
   * mets/amdSec/digiprovMD/@ID An xml:id identifier for the digital provenance
   * metadata section mets/amdSec/digiprovMD used for internal package references.
   * It must be unique within the package.
   */
  private ReporterDetails validateCSIP33() {
    for (AmdSecType a : amdSec) {
      List<MdSecType> digiprov = a.getDigiprovMD();
      for (MdSecType md : digiprov) {
        if (!checkId(md.getID())) {
          addId(md.getID());
        } else {
          return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
            "mets/amdSec/digiprovMD/@ID isn't unique in the package (" + metsName + ")", false, false);
        }
      }
    }
    return new ReporterDetails();
  }

  /*
   * mets/amdSec/digiprovMD/@STATUS Indicates the status of the package using a
   * fixed vocabulary.See also: dmdSec status
   */
  private ReporterDetails validateCSIP34() {
    for (AmdSecType a : amdSec) {
      List<MdSecType> digiprov = a.getDigiprovMD();
      for (MdSecType md : digiprov) {
        String status = md.getSTATUS();
        if (!dmdSecStatus.contains(status)) {
          return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
            "mets/amdSec/digiprovMD/@STATUS see valid values dmdSec status", false, false);
        }
      }
    }
    return new ReporterDetails();

  }

  /*
   * mets/amdSec/digiprovMD/mdRef Reference to the digital provenance metadata
   * file stored in the “metadata” section of the IP.
   */
  private ReporterDetails validateCSIP35() {
    for (AmdSecType a : amdSec) {
      List<MdSecType> digiprov = a.getDigiprovMD();
      for (MdSecType md : digiprov) {
        MdSecType.MdRef mdRef = md.getMdRef();
        if (mdRef == null) {
          return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
            "mets/amdSec/digiprovMD/mdRef is the reference to the digital provenance metadata file", false, false);
        }
      }
    }
    return new ReporterDetails();
  }

  /*
   * mets/amdSec/digiprovMD/mdRef[@LOCTYPE=’URL’] The locator type is always used
   * with the value “URL” from the vocabulary in the attribute. Ver este tb
   */
  private ReporterDetails validateCSIP36() {
    for (AmdSecType a : amdSec) {
      List<MdSecType> digiprov = a.getDigiprovMD();
      for (MdSecType md : digiprov) {
        MdSecType.MdRef mdRef = md.getMdRef();
        if (mdRef != null) {
          String loctype = mdRef.getLOCTYPE();
          if (loctype != null) {
            if (!loctype.equals("URL")) {
              return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
                "mets/amdSec/digiprovMD/mdRef[@LOCTYPE='URL'] value must be URL", false, false);
            }
          } else {
            return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
              "mets/amdSec/digiprovMD/mdRef[@LOCTYPE='URL'] can't be null", false, false);
          }
        }
      }
    }
    return new ReporterDetails();
  }

  /*
   * mets/amdSec/digiprovMD/mdRef[@xlink:type=’simple’] Attribute used with the
   * value “simple”. Value list is maintained by the xlink standard.
   */
  private ReporterDetails validateCSIP37() throws IOException {
    HashMap<String,String> amdSecTypes = new HashMap<>();
    MetsHandler amdSecHandler = new MetsHandler("digiprovMD","mdRef",amdSecTypes);
    MetsParser metsParser = new MetsParser();
    InputStream metsStream = null;
    if(!amdSec.isEmpty()) {
      if (isZipFileFlag()) {
        if (isRootMets()) {
          metsStream = zipManager.getMetsRootInputStream(getEARKSIPpath());
        } else {
          metsStream = zipManager.getZipInputStream(getEARKSIPpath(), metsPath + "METS.xml");
        }
      }
      else{
        if(isRootMets()){
          metsStream = folderManager.getMetsRootInputStream(getEARKSIPpath());
        }
        else{
          metsStream = folderManager.getInputStream(Paths.get(metsPath));
        }
      }
    }
    if(metsStream != null){
      metsParser.parse(amdSecHandler,metsStream);
    }
    ReporterDetails details = new ReporterDetails();
    for (AmdSecType a : amdSec) {
      List<MdSecType> digiprov = a.getDigiprovMD();
      for (MdSecType md : digiprov) {
        MdSecType.MdRef mdRef = md.getMdRef();

        if (amdSecTypes.get(mdRef.getID()) == null) {
          details.setValid(false);
          details.addIssue("mets/amdSec/digiprovMD/mdRef[@xlink:type=’simple’] can't be null  (" + metsName + ")");
        } else {
          if (!amdSecTypes.get(mdRef.getID()).equals("simple")) {
            details.setValid(false);
            details.addIssue(
              "mets/amdSec/digiprovMD/mdRef[@xlink:type=’simple’] value must be 'simple'  (" + metsName + ")");
          }
        }
      }
    }
    return details;
  }

  /*
   * mets/amdSec/digiprovMD/mdRef/@xlink:href The actual location of the resource.
   * This specification recommends recording a URL type filepath within this
   * attribute.
   */
  private ReporterDetails validateCSIP38() throws IOException {
    for (AmdSecType a : amdSec) {
      List<MdSecType> digiprov = a.getDigiprovMD();
      for (MdSecType md : digiprov) {
        MdSecType.MdRef mdRef = md.getMdRef();
        if (mdRef != null) {
          String href = URLDecoder.decode(mdRef.getHref(), UTF_8);
          if (isZipFileFlag()) {
            if (!zipManager.checkPathExists(getEARKSIPpath(), href)) {
              return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
                "mets/amdSec/digiprovMD/mdRef/@xlink:href path doesn't exists", false, false);
            }
          } else {
            if (!folderManager.checkPathExists(getEARKSIPpath(), Paths.get(href))) {
              return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
                "mets/amdSec/digiprovMD/mdRef/@xlink:href path doesn't exists", false, false);
            }
          }
        }
      }
    }
    return new ReporterDetails();
  }

  /*
   * mets/amdSec/digiprovMD/mdRef/@MDTYPE Specifies the type of metadata in the
   * referenced file. Values are taken from the list provided by the METS.
   */
  private ReporterDetails validateCSIP39() {
    List<String> tmp = new ArrayList<>();
    for (MetadataType md : MetadataType.values()) {
      tmp.add(md.toString());
    }
    for (AmdSecType a : amdSec) {
      List<MdSecType> digiprov = a.getDigiprovMD();
      for (MdSecType md : digiprov) {
        MdSecType.MdRef mdRef = md.getMdRef();
        if (mdRef != null) {
          String mdType = mdRef.getMDTYPE();
          if (mdType == null) {
            return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
              "mets/amdSec/digiprovMD/mdRef/@MDTYPE can't be null (" + metsName + ")", false, false);
          } else {
            if (!tmp.contains(mdType)) {
              return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
                "mets/amdSec/digiprovMD/mdRef/@MDTYPE value isn't valid (" + metsName + ")", false, false);
            }
          }
        }
      }
    }
    return new ReporterDetails();
  }

  /*
   * mets/amdSec/digiprovMD/mdRef/@MIMETYPE The IANA mime type for the referenced
   * file.See also: IANA media types
   */
  private ReporterDetails validateCSIP40() {
    for (AmdSecType amdSecType : amdSec) {
      List<MdSecType> digiprovMD = amdSecType.getDigiprovMD();
      for (MdSecType digiprov : digiprovMD) {
        MdSecType.MdRef mdRef = digiprov.getMdRef();
        String mimeType = mdRef.getMIMETYPE();
        if (mimeType != null) {
          if (!ianaMediaTypes.contains(mimeType)) {
            return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
              "mets/amdSec/digiprovMD/mdRef/@MIMETYPE value isn't valid. See IANA Media Types (" + metsName + ")",
              false, false);
          }
        } else {
          return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
            "mets/amdSec/digiprovMD/mdRef/@MIMETYPE of file can't be null (" + metsName + ")", false, false);
        }
      }
    }
    return new ReporterDetails();
  }

  /*
   * mets/amdSec/digiprovMD/mdRef/@SIZE Size of the referenced file in bytes.
   */
  private ReporterDetails validateCSIP41() throws IOException {
    for (AmdSecType amdSecType : amdSec) {
      List<MdSecType> digiprov = amdSecType.getDigiprovMD();
      for (MdSecType md : digiprov) {
        MdSecType.MdRef mdRef = md.getMdRef();
        if (mdRef != null) {
          String href = URLDecoder.decode(mdRef.getHref(), UTF_8);
          Long size = mdRef.getSIZE();
          if (size == null) {
            return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
              "mets/amdSec/digiprovMD/mdRef/@SIZE of file can't be null (" + metsName + ")", false, false);
          } else {
            if (isZipFileFlag()) {
              String file;
              if (isRootMets()) {
                String OBJECTID = mets.getOBJID();
                if (OBJECTID != null) {
                  file = OBJECTID + "/" + href;
                } else {
                  return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
                    METS_OBJECTID_CAN_T_BE_NULL, false, false);
                }
              } else {
                file = metsPath + href;
              }
              if (!zipManager.verifySize(getEARKSIPpath(), file, size)) {
                return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
                  METS_AMD_SEC_DIGIPROV_MD_MD_REF_SIZE_DOESN_T_MATCH_WITH_THE_SIZE_OF_FILE + metsName + ")", false,
                  false);
              }
            } else {
              if (isRootMets()) {
                if (!folderManager.verifySize(getEARKSIPpath().resolve(href), size)) {
                  return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
                    METS_AMD_SEC_DIGIPROV_MD_MD_REF_SIZE_DOESN_T_MATCH_WITH_THE_SIZE_OF_FILE + metsName + ")", false,
                    false);
                }
              } else {
                if (!folderManager.verifySize(Paths.get(metsPath).resolve(href), size)) {
                  return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
                    METS_AMD_SEC_DIGIPROV_MD_MD_REF_SIZE_DOESN_T_MATCH_WITH_THE_SIZE_OF_FILE + metsName + ")", false,
                    false);
                }
              }
            }
          }
        }
      }
    }
    return new ReporterDetails();
  }

  /*
   * mets/amdSec/digiprovMD/mdRef/@CREATED Creation date of the referenced file.
   */
  private ReporterDetails validateCSIP42() {
    for (AmdSecType a : amdSec) {
      List<MdSecType> digiprov = a.getDigiprovMD();
      for (MdSecType md : digiprov) {
        MdSecType.MdRef mdRef = md.getMdRef();
        if (mdRef.getCREATED() == null) {
          return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
            "mets/amdSec/digiprovMD/mdRef/@CREATED can't be null (" + metsName + ")", false, false);
        }
      }
    }
    return new ReporterDetails();
  }

  /*
   * mets/amdSec/digiprovMD/mdRef/@CHECKSUM The checksum of the referenced file.
   */
  private ReporterDetails validateCSIP43() throws IOException, NoSuchAlgorithmException {
    List<String> tmp = new ArrayList<>();
    for (CHECKSUMTYPE check : CHECKSUMTYPE.values()) {
      tmp.add(check.toString());
    }
    for (AmdSecType a : amdSec) {
      List<MdSecType> digiprov = a.getDigiprovMD();
      for (MdSecType md : digiprov) {
        MdSecType.MdRef mdRef = md.getMdRef();
        String checksumType = mdRef.getCHECKSUMTYPE();
        if (checksumType == null) {
          return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
            "mets/amdSec/digiprovMD/mdRef/@CHECKSUMTYPE can't be null (" + metsName + ")", false, false);
        } else {
          if (!tmp.contains(checksumType)) {
            return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
              "mets/amdSec/digiprovMD/mdRef/@CHECKSUMTYPE value isn't valid (" + metsName + ")", false, false);
          } else {
            String checksum = mdRef.getCHECKSUM();
            if (checksum == null) {
              return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
                "mets/amdSec/digiprovMD/mdRef/@CHECKSUM can't be null (" + metsName + ")", false, false);
            } else {
              String href = URLDecoder.decode(mdRef.getHref(), UTF_8);
              if (isZipFileFlag()) {
                String file;
                if (isRootMets()) {
                  String OBJECTID = mets.getOBJID();
                  if (OBJECTID != null) {
                    file = OBJECTID + "/" + href;
                  } else {
                    return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
                      METS_OBJECTID_CAN_T_BE_NULL, false, false);
                  }
                } else {
                  file = metsPath + href;
                }
                if (!zipManager.verifyChecksum(getEARKSIPpath(), file, checksumType, checksum)) {
                  return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
                    "mets/amdSec/digiprovMD/mdRef/@CHECKSUM doesn't match with file checksum (" + metsName + ")", false,
                    false);
                }
              } else {
                if (!folderManager.verifyChecksum(getEARKSIPpath(), href, checksumType, checksum)) {
                  return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
                    "mets/amdSec/digiprovMD/mdRef/@CHECKSUM doesn't match with file checksum (" + metsName + ")", false,
                    false);
                }
              }
            }
          }
        }
      }
    }
    return new ReporterDetails();
  }

  /*
   * mets/amdSec/digiprovMD/mdRef/@CHECKSUMTYPE The type of checksum following the
   * value list present in the METS-standard which has been used for calculating
   * the checksum for the referenced file.
   */
  private ReporterDetails validateCSIP44() {
    List<String> tmp = new ArrayList<>();
    for (CHECKSUMTYPE check : CHECKSUMTYPE.values()) {
      tmp.add(check.toString());
    }
    for (AmdSecType a : amdSec) {
      List<MdSecType> digiprov = a.getDigiprovMD();
      for (MdSecType md : digiprov) {
        MdSecType.MdRef mdRef = md.getMdRef();
        String checksumType = mdRef.getCHECKSUMTYPE();
        if (checksumType == null) {
          return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
            "mets/amdSec/digiprovMD/mdRef/@CHECKSUMTYPE can't be null (" + metsName + ")", false, false);
        } else {
          if (!tmp.contains(checksumType)) {
            return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
              "mets/amdSec/digiprovMD/mdRef/@CHECKSUMTYPE value isn't valid (" + metsName + ")", false, false);
          }
        }
      }
    }
    return new ReporterDetails();
  }

  /*
   * mets/amdSec/rightsMD A simple rights statement may be used to describe
   * general permissions for the package. Individual representations should state
   * their specific rights in their representation METS file. Available standards
   * include RightsStatements.org , Europeana rights statements info , METS Rights
   * Schema created and maintained by the METS Board, the rights part of PREMIS as
   * well as own local rights statements in use.
   */
  private ReporterDetails validateCSIP45() {
    boolean found = false;
    for (AmdSecType a : amdSec) {
      if (a.getRightsMD() != null) {
        found = true;
      }
    }
    if (found) {
      return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
        "You have specified mets/amdSec/rightsMD.", true, true);
    }
    return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
      "Individual representations should state their specific rights in their representation METS file.", true, true);

  }

  /*
   * mets/amdSec/rightsMD/@ID An xml:id identifier for the rights metadata section
   * ( <rightsMD> ) used for internal package references. It must be unique within
   * the package.
   */
  private ReporterDetails validateCSIP46() {
    for (AmdSecType a : amdSec) {
      List<MdSecType> rigthsMD = a.getRightsMD();
      if (rigthsMD != null) {
        for (MdSecType rmd : rigthsMD) {
          if (checkId(rmd.getID())) {
            return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
              "mets/amdSec/rightsMD/@ID must be unique in the package and not null (" + metsName + ")", false, false);
          } else {
            addId(rmd.getID());
          }
        }
      }
    }
    return new ReporterDetails();
  }

  /*
   * mets/amdSec/rightsMD/@STATUS Indicates the status of the package using a
   * fixed vocabulary.See also: dmdSec status
   */
  private ReporterDetails validateCSIP47() {
    for (AmdSecType a : amdSec) {
      List<MdSecType> rigthsMD = a.getRightsMD();
      if (rigthsMD != null) {
        for (MdSecType rmd : rigthsMD) {
          String status = rmd.getSTATUS();
          if (status == null) {
            return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
              "mets/amdSec/rightsMD/@STATUS can't be null (" + metsName + ")", false, false);
          } else {
            if (!dmdSecStatus.contains(status)) {
              return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
                "mets/amdSec/rightsMD/@STATUS value isn't valid (" + metsName + ")", false, false);
            }
          }
        }
      }
    }
    return new ReporterDetails();
  }

  /*
   * mets/amdSec/rightsMD/mdRef Reference to the rights metadata file stored in
   * the “metadata” section of the IP.
   *
   * Rever este
   */
  private ReporterDetails validateCSIP48() {
    boolean valid = true;
    for (AmdSecType a : amdSec) {
      List<MdSecType> rigthsMD = a.getRightsMD();
      if (rigthsMD != null) {
        for (MdSecType rmd : rigthsMD) {
          if (rmd.getMdRef() == null) {
            valid = false;
            break;
          }
        }
        if (!valid) {
          break;
        }
      }
    }
    return new ReporterDetails();
  }

  /*
   * mets/amdSec/rightsMD/mdRef[@LOCTYPE=’URL’] The locator type is always used
   * with the value “URL” from the vocabulary in the attribute.
   */
  private ReporterDetails validateCSIP49() {
    for (AmdSecType a : amdSec) {
      List<MdSecType> rigthsMD = a.getRightsMD();
      if (rigthsMD != null) {
        for (MdSecType rmd : rigthsMD) {
          MdSecType.MdRef mdRef = rmd.getMdRef();
          if (mdRef != null) {
            String loctype = mdRef.getLOCTYPE();
            if (loctype == null) {
              return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
                "mets/amdSec/rightsMD/mdRef[@LOCTYPE=’URL’] can't be null (" + metsName + ")", false, false);
            } else {
              if (!loctype.equals("URL")) {
                return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
                  "mets/amdSec/rightsMD/mdRef[@LOCTYPE=’URL’] value must be 'URL' (" + metsName + ")", false, false);
              }
            }
          }
        }
      }
    }
    return new ReporterDetails();
  }

  /*
   * mets/amdSec/rightsMD/mdRef[@xlink:type=’simple’] Attribute used with the
   * value “simple”. Value list is maintained by the xlink standard.
   */
  private ReporterDetails validateCSIP50() throws IOException {
    HashMap<String,String> amdSecTypes = new HashMap<>();
    MetsHandler amdSecHandler = new MetsHandler("rightsMD","mdRef",amdSecTypes);
    MetsParser metsParser = new MetsParser();
    InputStream metsStream = null;
    if(!amdSec.isEmpty()) {
      if (isZipFileFlag()) {
        if (isRootMets()) {
          metsStream = zipManager.getMetsRootInputStream(getEARKSIPpath());
        } else {
          metsStream = zipManager.getZipInputStream(getEARKSIPpath(), metsPath + "METS.xml");
        }
      }
      else{
        if(isRootMets()){
          metsStream = folderManager.getMetsRootInputStream(getEARKSIPpath());
        }
        else{
          metsStream = folderManager.getInputStream(Paths.get(metsPath));
        }
      }
    }
    if(metsStream != null){
      metsParser.parse(amdSecHandler,metsStream);
    }
    for (AmdSecType a : amdSec) {
      List<MdSecType> rigthsMD = a.getRightsMD();
      if (rigthsMD != null) {
        for (MdSecType rmd : rigthsMD) {
          MdSecType.MdRef mdRef = rmd.getMdRef();
          if (mdRef != null) {
            if (amdSecTypes.get(mdRef.getID()) == null) {
              return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
                "mets/amdSec/rightsMD/mdRef[@xlink:type=’simple’] can't be null (" + metsName + ")", false, false);
            } else {
              if (!amdSecTypes.get(mdRef.getID()).equals("simple")) {
                return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
                  "mets/amdSec/rightsMD/mdRef[@xlink:type=’simple’] value isn't 'simple' (" + metsName + ")", false,
                  false);
              }
            }
          }
        }
      }
    }
    return new ReporterDetails();
  }

  /*
   * mets/amdSec/rightsMD/mdRef/@xlink:href The actual location of the resource.
   * We recommend recording a URL type filepath within this attribute.
   */
  private ReporterDetails validateCSIP51() throws IOException {
    for (AmdSecType a : amdSec) {
      List<MdSecType> rigthsMD = a.getRightsMD();
      if (rigthsMD != null) {
        for (MdSecType rmd : rigthsMD) {
          MdSecType.MdRef mdRef = rmd.getMdRef();
          if (mdRef != null) {
            String href = URLDecoder.decode(mdRef.getHref(), UTF_8);
            if (isZipFileFlag()) {
              if (!zipManager.checkPathExists(getEARKSIPpath(), href)) {
                return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
                  "mets/amdSec/rightsMD/mdRef/@xlink:href path doesn't exists (" + metsName + ")", false, false);
              }
            } else {
              if (!folderManager.checkPathExists(getEARKSIPpath(), Paths.get(href))) {
                return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
                  "mets/amdSec/rightsMD/mdRef/@xlink:href path doesn't exists (" + metsName + ")", false, false);
              }
            }
          }
        }
      }
    }
    return new ReporterDetails();
  }

  /*
   * mets/amdSec/rightsMD/mdRef/@MDTYPE Specifies the type of metadata in the
   * referenced file. Value is taken from the list provided by the METS.
   */
  private ReporterDetails validateCSIP52() {
    List<String> tmp = new ArrayList<>();
    for (MetadataType md : MetadataType.values()) {
      tmp.add(md.toString());
    }
    for (AmdSecType a : amdSec) {
      List<MdSecType> rigthsMD = a.getRightsMD();
      if (rigthsMD != null) {
        for (MdSecType rmd : rigthsMD) {
          MdSecType.MdRef mdRef = rmd.getMdRef();
          if (mdRef != null) {
            String mdType = mdRef.getMDTYPE();
            if (mdType == null) {
              return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
                "mets/amdSec/rightsMD/mdRef/@MDTYPE can't be null (" + metsName + ")", false, false);
            } else {
              if (!tmp.contains(mdType)) {
                return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
                  "mets/amdSec/rightsMD/mdRef/@MDTYPE value isn't valid (" + metsName + ")", false, false);
              }
            }
          }
        }
      }
    }
    return new ReporterDetails();
  }

  /*
   * mets/amdSec/rightsMD/mdRef/@MIMETYPE The IANA mime type for the referenced
   * file.See also: IANA media types
   */
  private ReporterDetails validateCSIP53() {
    for (AmdSecType a : amdSec) {
      List<MdSecType> rigthsMD = a.getRightsMD();
      if (rigthsMD != null) {
        for (MdSecType rmd : rigthsMD) {
          MdSecType.MdRef mdRef = rmd.getMdRef();
          if (mdRef != null) {
            String mimeType = mdRef.getMIMETYPE();
            if (mimeType == null) {
              return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
                "mets/amdSec/rightsMD/mdRef/@MIMETYPE can't be null (" + metsName + ")", false, false);
            } else {
              if (!ianaMediaTypes.contains(mimeType)) {
                return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
                  "mets/amdSec/rightsMD/mdRef/@MIMETYPE value isn't valid. See IANA Media Types (" + metsName + ")",
                  false, false);
              }
            }
          }
        }
      }
    }
    return new ReporterDetails();
  }

  /*
   * mets/amdSec/rightsMD/mdRef/@SIZE Size of the referenced file in bytes.
   */
  private ReporterDetails validateCSIP54() throws IOException {
    for (AmdSecType a : amdSec) {
      List<MdSecType> rigthsMD = a.getRightsMD();
      if (rigthsMD != null) {
        for (MdSecType rmd : rigthsMD) {
          MdSecType.MdRef mdRef = rmd.getMdRef();
          if (mdRef != null) {
            String href = URLDecoder.decode(mdRef.getHref(), UTF_8);
            Long size = mdRef.getSIZE();
            if (size == null) {
              return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
                "mets/amdSec/rightsMD/mdRef/@SIZE can't be null (" + metsName + ")", false, false);
            } else {
              if (isZipFileFlag()) {
                if (!zipManager.verifySize(getEARKSIPpath(), href, size)) {
                  return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
                    METS_AMD_SEC_RIGHTS_MD_MD_REF_SIZE_DOESN_T_MATCH_WITH_THE_SIZE_OF_FILE + metsName + ")", false,
                    false);
                }
              } else {
                if (isRootMets()) {
                  if (!folderManager.verifySize(getEARKSIPpath().resolve(href), size)) {
                    return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
                      METS_AMD_SEC_RIGHTS_MD_MD_REF_SIZE_DOESN_T_MATCH_WITH_THE_SIZE_OF_FILE + metsName + ")", false,
                      false);
                  }
                } else {
                  if (!folderManager.verifySize(Paths.get(metsPath).resolve(href), size)) {
                    return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
                      METS_AMD_SEC_RIGHTS_MD_MD_REF_SIZE_DOESN_T_MATCH_WITH_THE_SIZE_OF_FILE + metsName + ")", false,
                      false);
                  }
                }
              }
            }
          }
        }
      }
    }
    return new ReporterDetails();
  }

  /*
   * mets/amdSec/rightsMD/mdRef/@CREATED Creation date of the referenced file.
   */
  private ReporterDetails validateCSIP55() {
    for (AmdSecType a : amdSec) {
      List<MdSecType> rigthsMD = a.getRightsMD();
      if (rigthsMD != null) {
        for (MdSecType rmd : rigthsMD) {
          MdSecType.MdRef mdRef = rmd.getMdRef();
          if (mdRef.getCREATED() == null) {
            return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
              "mets/amdSec/rightsMD/mdRef/@CREATED can't be null (" + metsName + ")", false, false);
          }
        }
      }
    }
    return new ReporterDetails();
  }

  /*
   * mets/amdSec/rightsMD/mdRef/@CHECKSUM The checksum of the referenced file.
   */
  private ReporterDetails validateCSIP56() throws IOException, NoSuchAlgorithmException {
    List<String> tmp = new ArrayList<>();
    for (CHECKSUMTYPE check : CHECKSUMTYPE.values()) {
      tmp.add(check.toString());
    }
    for (AmdSecType a : amdSec) {
      List<MdSecType> rigthsMD = a.getRightsMD();
      if (rigthsMD != null) {
        for (MdSecType rmd : rigthsMD) {
          MdSecType.MdRef mdRef = rmd.getMdRef();
          String checksumType = mdRef.getCHECKSUMTYPE();
          if (checksumType == null) {
            return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
              "mets/amdSec/rightsMD/mdRef/@CHECKSUMTYPE can't be null (" + metsName + ")", false, false);
          } else {
            if (!tmp.contains(checksumType)) {
              return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
                "mets/amdSec/rightsMD/mdRef/@CHECKSUMTYPE value isn't valid (" + metsName + ")", false, false);
            } else {
              String checksum = mdRef.getCHECKSUM();
              if (checksum == null) {
                return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
                  "mets/amdSec/rightsMD/mdRef/@CHECKSUM can't be null (" + metsName + ")", false, false);
              } else {
                String file = URLDecoder.decode(mdRef.getHref(), UTF_8);
                if (isZipFileFlag()) {
                  if (!zipManager.verifyChecksum(getEARKSIPpath(), file, checksumType, checksum)) {
                    return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
                      "mets/amdSec/rightsMD/mdRef/@CHECKSUM value doesn't match with the checksum of file (" + metsName
                        + ")",
                      false, false);
                  }
                } else {
                  if (!folderManager.verifyChecksum(getEARKSIPpath(), file, checksumType, checksum)) {
                    return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
                      "mets/amdSec/rightsMD/mdRef/@CHECKSUM value doesn't match with the checksum of file (" + metsName
                        + ")",
                      false, false);
                  }
                }
              }
            }
          }
        }
      }
    }
    return new ReporterDetails();
  }

  /*
   * mets/amdSec/rightsMD/mdRef/@CHECKSUMTYPE The type of checksum following the
   * value list present in the METS-standard which has been used for calculating
   * the checksum for the referenced file.
   */
  private ReporterDetails validateCSIP57() {
    List<String> tmp = new ArrayList<>();
    for (CHECKSUMTYPE check : CHECKSUMTYPE.values()) {
      tmp.add(check.toString());
    }
    for (AmdSecType a : amdSec) {
      List<MdSecType> rigthsMD = a.getRightsMD();
      if (rigthsMD != null) {
        for (MdSecType rmd : rigthsMD) {
          MdSecType.MdRef mdRef = rmd.getMdRef();
          String checksumType = mdRef.getCHECKSUMTYPE();
          if (checksumType == null) {
            return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
              "mets/amdSec/rightsMD/mdRef/@CHECKSUMTYPE can't be null (" + metsName + ")", false, false);
          } else {
            if (!tmp.contains(checksumType)) {
              return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
                "mets/amdSec/rightsMD/mdRef/@CHECKSUMTYPE value isn't valid (" + metsName + ")", false, false);
            }
          }
        }
      }
    }
    return new ReporterDetails();
  }
}
