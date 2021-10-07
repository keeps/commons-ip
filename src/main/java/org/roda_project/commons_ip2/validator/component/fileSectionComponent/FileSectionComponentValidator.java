package org.roda_project.commons_ip2.validator.component.fileSectionComponent;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLDecoder;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.namespace.QName;
import javax.xml.parsers.ParserConfigurationException;

import org.roda_project.commons_ip2.mets_v1_12.beans.AmdSecType;
import org.roda_project.commons_ip2.mets_v1_12.beans.FileType;
import org.roda_project.commons_ip2.mets_v1_12.beans.MdSecType;
import org.roda_project.commons_ip2.mets_v1_12.beans.MetsType;
import org.roda_project.commons_ip2.utils.IANAMediaTypes;
import org.roda_project.commons_ip2.validator.common.ControlledVocabularyParser;
import org.roda_project.commons_ip2.validator.common.MetsParser;
import org.roda_project.commons_ip2.validator.component.ValidatorComponentImpl;
import org.roda_project.commons_ip2.validator.constants.Constants;
import org.roda_project.commons_ip2.validator.constants.ConstantsCSIPspec;
import org.roda_project.commons_ip2.validator.constants.ConstantsSIPspec;
import org.roda_project.commons_ip2.validator.handlers.MetsHandler;
import org.roda_project.commons_ip2.validator.reporter.ReporterDetails;
import org.roda_project.commons_ip2.validator.utils.CHECKSUMTYPE;
import org.roda_project.commons_ip2.validator.utils.Message;
import org.roda_project.commons_ip2.validator.utils.ResultsUtils;
import org.xml.sax.SAXException;

/**
 * @author João Gomes <jgomes@keep.pt>
 */
public class FileSectionComponentValidator extends ValidatorComponentImpl {
  public static final String UTF_8 = "UTF-8";
  public static final String HTTPS_DILCIS_EU_XML_METS_CSIPEXTENSION_METS = "https://dilcis.eu/XML/METS/CSIPExtensionMETS";

  private final String moduleName;
  private List<String> contentInformationType;

  public FileSectionComponentValidator() throws IOException, ParserConfigurationException, SAXException {
    moduleName = Constants.CSIP_MODULE_NAME_5;
    this.contentInformationType = ControlledVocabularyParser
      .parse(Constants.PATH_RESOURCES_CSIP_VOCABULARY_CONTENT_INFORMATION_TYPE);
  }

  @Override
  public Map<String, ReporterDetails> validate() throws IOException {
    ReporterDetails csip;
    Map<String, ReporterDetails> results = new HashMap<>();
    /* CSIP58 */
    notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP58_ID);
    ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP58_ID,
      validateCSIP58().setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION));

    if (ResultsUtils.isResultValid(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP58_ID)) {

      /* CSIP59 */
      notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP59_ID);
      ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP59_ID,
        validateCSIP59().setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION));

      /* CSIP60 */
      notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP60_ID);
      ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP60_ID,
        validateCSIP60().setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION));

      /* CSIP113 */
      notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP113_ID);
      ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP113_ID,
        validateCSIP113().setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION));

      /* CSIP114 */
      notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP114_ID);
      ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP114_ID,
        validateCSIP114().setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION));

      /* CSIP61 */
      notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP61_ID);
      ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP61_ID,
        validateCSIP61().setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION));

      /* CSIP62 */
      notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP62_ID);
      ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP62_ID,
        validateCSIP62().setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION));

      /* CSIP63 */
      notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP63_ID);
      ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP63_ID,
        validateCSIP63().setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION));

      /* CSIP64 */
      notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP64_ID);
      ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP64_ID,
        validateCSIP64().setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION));

      /* CSIP65 */
      notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP65_ID);
      ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP65_ID,
        validateCSIP65().setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION));

      /* CSIP66 */
      notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP66_ID);
      ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP66_ID,
        validateCSIP66().setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION));

      if (ResultsUtils.isResultValid(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP66_ID)) {

        /* CSIP67 */
        notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP67_ID);
        ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP67_ID,
          validateCSIP67().setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION));

        /* CSIP68 */
        notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP68_ID);
        ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP68_ID,
          validateCSIP68().setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION));

        /* CSIP69 */
        notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP69_ID);
        ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP69_ID,
          validateCSIP69().setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION));

        /* CSIP70 */
        notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP70_ID);
        ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP70_ID,
          validateCSIP70().setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION));

        /* CSIP71 */
        notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP71_ID);
        try {
          csip = validateCSIP71().setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION);
        } catch (Exception e) {
          csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
            Message.createErrorMessage("Can't calculate checksum of file %1$s", metsName, isRootMets()), false, false);
        }
        ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP71_ID, csip);

        /* CSIP72 */
        notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP72_ID);
        ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP72_ID,
          validateCSIP72().setSpecification(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP72_ID));

        /* CSIP73 */
        notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP73_ID);
        ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP73_ID,
          validateCSIP73().setSpecification(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP72_ID));

        /* CSIP74 */
        notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP74_ID);
        ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP74_ID,
          validateCSIP74().setSpecification(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP72_ID));

        /* CSIP75 */
        notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP75_ID);
        ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP75_ID,
          validateCSIP75().setSpecification(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP72_ID));

        /* CSIP76 */
        notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP76_ID);
        ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP76_ID,
          validateCSIP76().setSpecification(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP72_ID));

        if (ResultsUtils.isResultValid(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP76_ID)) {

          /* CSIP77 */
          notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP77_ID);
          ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP77_ID,
            validateCSIP77().setSpecification(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP72_ID));

          /* CSIP78 */
          notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP78_ID);
          ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP78_ID,
            validateCSIP78().setSpecification(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP72_ID));

          /* CSIP79 */
          notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP79_ID);
          ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP79_ID,
            validateCSIP79().setSpecification(ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP72_ID));

        } else {
          String message = Message.createErrorMessage(
            "SKIPPED in %1$s because mets/fileSec/fileGrp/file/FLocat doesn't exist", metsName, isRootMets());

          ResultsUtils.addResults(results,
            new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, message, true, true),
            ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP77_ID,
            ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP78_ID,
            ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP79_ID);
        }

        /* SIP32 */
        notifyObserversValidationStarted(moduleName, ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP32_ID);
        ResultsUtils.addResult(results, ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP32_ID,
          validateSIP32().setSpecification(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION));

        /* SIP33 */
        notifyObserversValidationStarted(moduleName, ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP33_ID);
        ResultsUtils.addResult(results, ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP33_ID,
          validateSIP33().setSpecification(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION));

        /* SIP34 */
        notifyObserversValidationStarted(moduleName, ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP34_ID);
        ResultsUtils.addResult(results, ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP34_ID,
          validateSIP34().setSpecification(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION));

        /* SIP35 */
        notifyObserversValidationStarted(moduleName, ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP35_ID);
        ResultsUtils.addResult(results, ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP35_ID,
          validateSIP35().setSpecification(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION));

      } else {
        String message = Message.createErrorMessage("SKIPPED in %1$s because mets/fileSec/fileGrp/file/ doesn't exist",
          metsName, isRootMets());

        ResultsUtils.addResults(results,
          new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, message, true, true),
          ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP67_ID,
          ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP68_ID,
          ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP69_ID,
          ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP70_ID,
          ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP71_ID,
          ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP72_ID,
          ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP73_ID,
          ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP74_ID,
          ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP75_ID,
          ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP76_ID,
          ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP77_ID,
          ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP78_ID,
          ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP79_ID,
          ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP32_ID,
          ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP33_ID,
          ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP34_ID,
          ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP35_ID);
      }
    } else {
      String message = Message.createErrorMessage("SKIPPED in %1$s because mets/fileSec doesn't exist", metsName,
        isRootMets());

      ResultsUtils.addResults(results,
        new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, message, true, true),
        ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP59_ID,
        ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP60_ID,
        ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP113_ID,
        ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP114_ID,
        ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP61_ID,
        ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP62_ID,
        ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP63_ID,
        ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP64_ID,
        ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP65_ID,
        ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP66_ID,
        ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP67_ID,
        ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP68_ID,
        ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP69_ID,
        ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP70_ID,
        ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP71_ID,
        ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP72_ID,
        ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP73_ID,
        ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP74_ID,
        ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP75_ID,
        ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP76_ID,
        ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP77_ID,
        ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP78_ID,
        ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP79_ID,
        ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP32_ID,
        ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP33_ID,
        ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP34_ID,
        ConstantsSIPspec.VALIDATION_REPORT_SPECIFICATION_SIP35_ID);
    }
    notifyObserversFinishModule(moduleName);
    return results;
  }

  /*
   * mets/fileSec The transferred content is placed in the file section in
   * different file group elements, described in other requirements. Only a single
   * file section ( <fileSec> ) element should be present. It is possible to
   * transfer just descriptive metadata and/or administrative metadata without
   * files placed in this section.
   */
  private ReporterDetails validateCSIP58() {
    ReporterDetails details = new ReporterDetails();
    MetsType.FileSec fileSec = mets.getFileSec();
    if (fileSec == null) {
      details.setValid(false);
      details.addIssue(Message.createErrorMessage("mets/fileSec in %1$s can't be null", metsName, isRootMets()));
    }
    return details;
  }

  /*
   * mets/fileSec/@ID An xml:id identifier for the file section used for internal
   * package references. It must be unique within the package.
   */
  private ReporterDetails validateCSIP59() {
    MetsType.FileSec fileSec = mets.getFileSec();
    if (fileSec != null) {
      String id = fileSec.getID();
      if (id != null) {
        if (!checkId(id)) {
          addId(id);
        } else {
          StringBuilder message = new StringBuilder();
          message.append("Value ").append(id).append(" in %1$s for mets/fileSec/@ID isn't unique in the package");
          return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
            Message.createErrorMessage(message.toString(), metsName, isRootMets()), false, false);
        }
      } else {
        return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
          Message.createErrorMessage("mets/fileSec/@ID in %1$s can't be null", metsName, isRootMets()), false, false);
      }
    }
    return new ReporterDetails();

  }

  /*
   * mets/fileSec/fileGrp[@USE=’Documentation’] All documentation pertaining to
   * the transferred content is placed in one or more file group elements with
   * mets/fileSec/fileGrp/@USE attribute value “Documentation”.See also: File
   * group names
   */
  private ReporterDetails validateCSIP60() throws IOException {
    List<MetsType.FileSec.FileGrp> fileGrps = mets.getFileSec().getFileGrp();
    StringBuilder message = new StringBuilder();
    for (MetsType.FileSec.FileGrp fileGrp : fileGrps) {
      if (fileGrp.getUSE().equals("Documentation")) {
        List<FileType> files = fileGrp.getFile();
        if (files != null && !files.isEmpty()) {
          for (FileType file : files) {
            List<FileType.FLocat> fLocats = file.getFLocat();
            if (isZipFileFlag()) {
              for (FileType.FLocat flocat : fLocats) {
                String href = URLDecoder.decode(flocat.getHref(), UTF_8);
                StringBuilder filePath = new StringBuilder();
                if (isRootMets()) {
                  filePath.append(mets.getOBJID()).append("/").append(href);
                } else {
                  filePath.append(metsPath).append(href);
                }
                if (!zipManager.checkPathExists(getEARKSIPpath(), filePath.toString())) {
                  message.append("mets/fileSec/fileGrp[@USE=’Documentation’] ").append(filePath)
                    .append(" doesn't exists (%1$s)");
                  return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
                    Message.createErrorMessage(message.toString(), metsName, isRootMets()), false, false);
                }
              }
            } else {
              for (FileType.FLocat flocat : fLocats) {
                String filePath = URLDecoder.decode(flocat.getHref(), UTF_8);
                if (!folderManager.checkPathExists(Paths.get(metsPath).resolve(filePath))) {
                  message.append("mets/fileSec/fileGrp[@USE=’Documentation’] ")
                    .append(Paths.get(metsPath).resolve(filePath)).append(" doesn't exists (in %1$s)");
                  return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
                    Message.createErrorMessage(message.toString(), metsName, isRootMets()), false, false);
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
   * mets/fileSec/fileGrp[@USE=’Schemas’] All XML schemas used in the information
   * package should be referenced from one or more file groups with
   * mets/fileSec/fileGrp/@USE attribute value “Schemas”.See also: File group
   * names
   */
  private ReporterDetails validateCSIP113() throws IOException {
    List<MetsType.FileSec.FileGrp> fileGrps = mets.getFileSec().getFileGrp();
    StringBuilder message = new StringBuilder();
    for (MetsType.FileSec.FileGrp fileGrp : fileGrps) {
      if (fileGrp.getUSE().equals("Schemas")) {
        List<FileType> files = fileGrp.getFile();
        for (FileType file : files) {
          List<FileType.FLocat> fLocats = file.getFLocat();
          if (isZipFileFlag()) {
            for (FileType.FLocat flocat : fLocats) {
              String href = URLDecoder.decode(flocat.getHref(), UTF_8);
              StringBuilder filePath = new StringBuilder();
              if (isRootMets()) {
                filePath.append(mets.getOBJID()).append("/").append(href);
              } else {
                filePath.append(metsPath).append(href);
              }
              if (!zipManager.checkPathExists(getEARKSIPpath(), filePath.toString())) {
                message.append("mets/fileSec/fileGrp[@USE=’Schemas’] ").append(filePath)
                  .append(" doesn't exists (%1$s)");
                return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
                  Message.createErrorMessage(message.toString(), metsName, isRootMets()), false, false);
              }
            }
          } else {
            for (FileType.FLocat flocat : fLocats) {
              String filePath = URLDecoder.decode(flocat.getHref(), UTF_8);
              if (!folderManager.checkPathExists(Paths.get(metsPath).resolve(filePath))) {
                message.append("mets/fileSec/fileGrp[@USE=’Schemas’] ").append(Paths.get(metsPath).resolve(filePath))
                  .append(" doesn't exists (%1$s)");
                return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
                  Message.createErrorMessage(message.toString(), metsName, isRootMets()), false, false);
              }
            }
          }
        }
      }
    }
    return new ReporterDetails();
  }

  /*
   * mets/fileSec/fileGrp[@USE=’Representations’] A pointer to the METS document
   * describing the representation or pointers to the content being transferred
   * must be present in one or more file groups with mets/fileSec/fileGrp/@USE
   * attribute value “Representations”.See also: File group names
   */
  private ReporterDetails validateCSIP114() throws IOException {
    List<MetsType.FileSec.FileGrp> fileGrps = mets.getFileSec().getFileGrp();
    StringBuilder message = new StringBuilder();
    for (MetsType.FileSec.FileGrp fileGrp : fileGrps) {
      if (fileGrp.getUSE().matches("Representations/")) {
        List<FileType> files = fileGrp.getFile();
        for (FileType file : files) {
          List<FileType.FLocat> fLocats = file.getFLocat();
          if (isZipFileFlag()) {
            for (FileType.FLocat flocat : fLocats) {
              String href = URLDecoder.decode(flocat.getHref(), UTF_8);
              StringBuilder filePath = new StringBuilder();
              if (isRootMets()) {
                filePath.append(mets.getOBJID()).append("/").append(href);
              } else {
                filePath.append(metsPath).append(href);
              }
              if (!zipManager.checkPathExists(getEARKSIPpath(), filePath.toString())) {
                message.append("mets/fileSec/fileGrp[@USE=’Representations’] ").append(filePath)
                  .append(" doesn't exists (%1$s)");
                return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
                  Message.createErrorMessage(message.toString(), metsName, isRootMets()), false, false);
              }
            }
          } else {
            for (FileType.FLocat flocat : fLocats) {
              String filePath = URLDecoder.decode(flocat.getHref(), UTF_8);
              if (!folderManager.checkPathExists(Paths.get(metsPath).resolve(filePath))) {
                message.append("mets/fileSec/fileGrp[@USE=’Representations’] ")
                  .append(Paths.get(metsPath).resolve(filePath)).append(" doesn't exists (%1$s)");
                return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
                  Message.createErrorMessage(message.toString(), metsName, isRootMets()), false, false);
              }
            }
          }
        }
      }
    }
    return new ReporterDetails();
  }

  /*
   * mets/fileSec/fileGrp/@ADMID If administrative metadata has been provided at
   * file group mets/fileSec/fileGrp level this attribute refers to its
   * administrative metadata section by ID.
   *
   * Nota: verificar se é id do digiprovMD ou amdSec
   */
  private ReporterDetails validateCSIP61() {
    List<MetsType.FileSec.FileGrp> fileGrps = mets.getFileSec().getFileGrp();
    List<AmdSecType> amdSec = mets.getAmdSec();
    for (MetsType.FileSec.FileGrp fileGrp : fileGrps) {
      QName keyAdmid = new QName(HTTPS_DILCIS_EU_XML_METS_CSIPEXTENSION_METS, "ADMID", "csip");
      String admid = fileGrp.getOtherAttributes().get(keyAdmid);
      if (admid != null) {
        boolean found = false;
        for (AmdSecType a : amdSec) {
          List<MdSecType> digiProv = a.getDigiprovMD();
          for (MdSecType mdSecType : digiProv) {
            if (admid.equals(mdSecType.getID())) {
              found = true;
              break;
            }
          }
          if (found) {
            break;
          }
        }
        if (!found) {
          StringBuilder message = new StringBuilder();
          message.append("Value ").append(admid)
            .append(" in %1$s for mets/fileSec/fileGrp/file/@ADMID doesn't match with any mets/amdSec/digiprovMD/@ID");
          return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
            Message.createErrorMessage(message.toString(), metsName, isRootMets()), false, false);
        }
      }
    }
    return new ReporterDetails();
  }

  /*
   * mets/fileSec/fileGrp[@USE=’Representations’]/@csip:CONTENTINFORMATIONTYPE An
   * added attribute which states the name of the content information type
   * specification used to create the package. The vocabulary will evolve under
   * the curation of the DILCIS Board as additional content information type
   * specifications are developed. This attribute is mandatory when the
   * mets/fileSec/fileGrp/@USE attribute value is “Representations”. When the
   * “Package type” value is “Mixed” and/or the file group describes a
   * “Representation”, then this element states the content information type
   * specification used for the file group.See also: Content information type
   * specification
   */
  private ReporterDetails validateCSIP62() {
    List<MetsType.FileSec.FileGrp> fileGrps = mets.getFileSec().getFileGrp();
    for (MetsType.FileSec.FileGrp fileGrp : fileGrps) {
      if (fileGrp.getUSE().matches("Representations/")) {
        QName keyContentInformationType = new QName(HTTPS_DILCIS_EU_XML_METS_CSIPEXTENSION_METS,
          "CONTENTINFORMATIONTYPE", "csip");
        String cType = fileGrp.getOtherAttributes().get(keyContentInformationType);
        if (cType != null) {
          if (!contentInformationType.contains(cType)) {
            StringBuilder message = new StringBuilder();
            message.append("Value ").append(cType).append(
              " in %1$s for mets/fileSec/fileGrp[@USE=’Representations’]/@csip:CONTENTINFORMATIONTYPE value isn't valid");
            return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
              Message.createErrorMessage(message.toString(), metsName, isRootMets()), false, false);
          }
        } else {
          return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
            Message.createErrorMessage(
              "mets/fileSec/fileGrp[@USE=’Representations’]/@csip:CONTENTINFORMATIONTYPE in %1$s can't be null",
              metsName, isRootMets()),
            false, false);
        }
      }
    }
    return new ReporterDetails();
  }

  /*
   * mets/fileSec/fileGrp[@csip:CONTENTINFORMATIONTYPE=’OTHER’]/@csip:
   * OTHERCONTENTINFORMATIONTYPE When the
   * mets/fileSec/fileGrp/@csip:CONTENTINFORMATIONTYPE attribute has the value
   * “OTHER” the mets/fileSec/fileGrp/@csip:OTHERCONTENTINFORMATIONTYPE must state
   * a value for the Content Information Type Specification used.
   */
  private ReporterDetails validateCSIP63() {
    List<MetsType.FileSec.FileGrp> fileGrps = mets.getFileSec().getFileGrp();
    for (MetsType.FileSec.FileGrp fileGrp : fileGrps) {
      QName keyContentInformationType = new QName(HTTPS_DILCIS_EU_XML_METS_CSIPEXTENSION_METS, "CONTENTINFORMATIONTYPE",
        "csip");
      String contentInformationType = fileGrp.getOtherAttributes().get(keyContentInformationType);
      if (contentInformationType != null && contentInformationType.equals("OTHER")) {
        QName keyOtherContentInformationType = new QName(HTTPS_DILCIS_EU_XML_METS_CSIPEXTENSION_METS,
          "OTHERCONTENTINFORMATIONTYPE", "csip");
        String otherContentInformationType = fileGrp.getOtherAttributes().get(keyOtherContentInformationType);
        if (otherContentInformationType == null) {
          return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, Message.createErrorMessage(
            "In %1$s mets/fileSec/fileGrp/@csip:CONTENTINFORMATIONTYPE have the value OTHER mets/fileSec/fileGrp[@csip:CONTENTINFORMATIONTYPE='OTHER']/@csip:OTHERCONTENTINFORMATIONTYPE can't be null",
            metsName, isRootMets()), false, false);
        }
      }
    }
    return new ReporterDetails();
  }

  /*
   * mets/fileSec/fileGrp/@USE The value in the mets/fileSec/fileGrp/@USE is the
   * name of the whole folder structure to the data, e.g “Documentation”,
   * “Schemas”, “Representations/preingest” or “Representations/submission/data”.
   * Falta perguntar o use: Data
   */
  private ReporterDetails validateCSIP64() throws IOException {
    List<MetsType.FileSec.FileGrp> fileGrps = mets.getFileSec().getFileGrp();
    List<String> tmp = new ArrayList<>();
    tmp.add("Schemas");
    tmp.add("Documentation");
    tmp.add("Representations");
    StringBuilder message = new StringBuilder();
    for (MetsType.FileSec.FileGrp fileGrp : fileGrps) {
      String use = fileGrp.getUSE();
      if (use != null) {
        if (!tmp.contains(use)) {
          if (fileGrp.getFile().isEmpty()) {
            if (isZipFileFlag()) {
              String expr;
              if (isRootMets()) {
                expr = mets.getOBJID() + "/" + use.toLowerCase();
              } else {
                expr = metsPath + use.toLowerCase();
              }
              if (!zipManager.checkPathIsDirectory(getEARKSIPpath(), expr)) {
                message.append("Value ").append(use)
                  .append(" in %1$s for mets/fileSec/fileGrp/@USE doesn't match with any directory in sip(")
                  .append(expr).append(")");
                return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
                  Message.createErrorMessage(message.toString(), metsName, isRootMets()), false, false);
              }
            } else {
              if (!folderManager.checkDirectory(Paths.get(metsPath).resolve(use.toLowerCase()))) {
                message.append("Value ").append(use)
                  .append(" in %1$s for mets/fileSec/fileGrp/@USE doesn't match with any directory in sip(")
                  .append(Paths.get(metsPath).resolve(use.toLowerCase())).append(")");
                return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
                  Message.createErrorMessage(message.toString(), metsName, isRootMets()), false, false);
              }
            }
          }
        }
      } else {
        return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
          Message.createErrorMessage("mets/fileSec/fileGrp/@USE in %1$s can't be null", metsName, isRootMets()), false,
          false);
      }
    }
    return new ReporterDetails();
  }

  /*
   * mets/fileSec/fileGrp/@ID An xml:id identifier for the file group used for
   * internal package references. It must be unique within the package.
   */
  private ReporterDetails validateCSIP65() {
    MetsType.FileSec fileSec = mets.getFileSec();
    List<MetsType.FileSec.FileGrp> fileGrp = fileSec.getFileGrp();
    for (MetsType.FileSec.FileGrp grp : fileGrp) {
      String id = grp.getID();
      if (id != null) {
        if (!checkId(id)) {
          addId(id);
        } else {
          StringBuilder message = new StringBuilder();
          message.append("Value ").append(id)
            .append(" in %1$s for mets/fileSec/fileGrp/@ID isn't unique in the package");
          return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
            Message.createErrorMessage(message.toString(), metsName, isRootMets()), false, false);
        }
      } else {
        return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
          Message.createErrorMessage("mets/fileSec/fileGrp/@ID in %1$s can't be null", metsName, isRootMets()), false,
          false);
      }
    }
    return new ReporterDetails();
  }

  /*
   * mets/fileSec/fileGrp/file The file group ( <fileGrp> ) contains the file
   * elements which describe the file objects.
   */
  private ReporterDetails validateCSIP66() throws IOException {
    if (isZipFileFlag()) {
      MetsType.FileSec fileSec = mets.getFileSec();
      List<MetsType.FileSec.FileGrp> fileGrp = fileSec.getFileGrp();
      for (MetsType.FileSec.FileGrp grp : fileGrp) {
        List<FileType> fileTypes = grp.getFile();
        if (!fileTypes.isEmpty()) {
          for (FileType file : fileTypes) {
            List<FileType.FLocat> fLocats = file.getFLocat();
            if (!fLocats.isEmpty()) {
              for (FileType.FLocat fLocat : fLocats) {
                String hrefDecoded = URLDecoder.decode(fLocat.getHref(), UTF_8);
                StringBuilder filePath = new StringBuilder();
                if (isRootMets()) {
                  filePath.append(mets.getOBJID()).append("/").append(hrefDecoded);
                } else {
                  filePath.append(metsPath).append(hrefDecoded);
                }
                if (files.containsKey(filePath.toString())) {
                  files.replace(filePath.toString(), true);
                }
              }
            }
          }
        }
      }
      if (files.containsValue(false) && isRootMets()) {
        return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
          Message.createErrorMessage("You have files in SIP does not referenced in %1$s", metsName, isRootMets()),
          false, false);
      }
    }
    return new ReporterDetails();
  }

  /*
   * mets/fileSec/fileGrp/file/@ID A unique xml:id identifier for this file across
   * the package.
   */
  private ReporterDetails validateCSIP67() {
    MetsType.FileSec fileSec = mets.getFileSec();
    List<MetsType.FileSec.FileGrp> fileGrp = fileSec.getFileGrp();
    for (MetsType.FileSec.FileGrp grp : fileGrp) {
      List<FileType> files = grp.getFile();
      for (FileType file : files) {
        String id = file.getID();
        if (id != null) {
          if (!checkId(id)) {
            addId(id);
          } else {
            StringBuilder message = new StringBuilder();
            message.append("Value ").append(id)
              .append(" in %1$s for mets/fileSec/fileGrp/@ID isn't unique in the package");
            return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
              Message.createErrorMessage(message.toString(), metsName, isRootMets()), false, false);
          }
        } else {
          return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
            Message.createErrorMessage("mets/fileSec/fileGrp/file/@ID in %1$s can't be null", metsName, isRootMets()),
            false, false);
        }
      }
    }
    return new ReporterDetails();
  }

  /*
   * mets/fileSec/fileGrp/file/@MIMETYPE The IANA mime type for the referenced
   * file.See also: IANA media types
   */
  private ReporterDetails validateCSIP68() {
    MetsType.FileSec fileSec = mets.getFileSec();
    List<MetsType.FileSec.FileGrp> fileGrp = fileSec.getFileGrp();
    for (MetsType.FileSec.FileGrp grp : fileGrp) {
      List<FileType> files = grp.getFile();
      for (FileType file : files) {
        String mimeType = file.getMIMETYPE();
        if (mimeType != null) {
          if (!IANAMediaTypes.getIANAMediaTypes().contains(mimeType)) {
            StringBuilder message = new StringBuilder();
            message.append("Value ").append(mimeType)
              .append(" in %1$s for mets/fileSec/fileGrp/file/@MIMETYPE value isn't valid");
            return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
              Message.createErrorMessage(message.toString(), metsName, isRootMets()), false, false);
          }
        } else {
          return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, Message.createErrorMessage(
            "mets/fileSec/fileGrp/file/@MIMETYPE of file in %1$s can't be null", metsName, isRootMets()), false, false);
        }
      }
    }
    return new ReporterDetails();
  }

  /*
   * mets/fileSec/fileGrp/file/@SIZE Size of the referenced file in bytes.
   */
  private ReporterDetails validateCSIP69() throws IOException {
    MetsType.FileSec fileSec = mets.getFileSec();
    StringBuilder message = new StringBuilder();
    List<MetsType.FileSec.FileGrp> fileGrp = fileSec.getFileGrp();
    for (MetsType.FileSec.FileGrp grp : fileGrp) {
      List<FileType> files = grp.getFile();
      for (FileType file : files) {
        List<FileType.FLocat> flocat = file.getFLocat();
        if (flocat != null) {
          if (flocat.size() == 1) {
            String href = URLDecoder.decode(flocat.get(0).getHref(), UTF_8);
            if (href != null) {
              Long size = file.getSIZE();
              if (size != null) {
                if (isZipFileFlag()) {
                  StringBuilder filePath = new StringBuilder();
                  if (isRootMets()) {
                    filePath.append(mets.getOBJID()).append("/").append(href);
                  } else {
                    filePath.append(metsPath).append(href);
                  }
                  if (!zipManager.verifySize(getEARKSIPpath(), filePath.toString(), size)) {
                    message.append("mets/dmdSec/mdRef/@SIZE ").append(size).append(" in %1$s and size of file (")
                      .append(filePath).append(") isn't equal");
                    return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
                      Message.createErrorMessage(message.toString(), metsName, isRootMets()), false, false);
                  }
                } else {
                  if (isRootMets()) {
                    if (!folderManager.verifySize(getEARKSIPpath().resolve(href), size)) {
                      message.append("mets/dmdSec/mdRef/@SIZE ").append(size).append(" in %1$s and size of file (")
                        .append(getEARKSIPpath().resolve(getEARKSIPpath().resolve(href))).append(") isn't equal");
                      return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
                        Message.createErrorMessage(message.toString(), metsName, isRootMets()), false, false);
                    }
                  } else {
                    if (!folderManager.verifySize(Paths.get(metsPath).resolve(href), size)) {
                      message.append("mets/dmdSec/mdRef/@SIZE ").append(size).append(" in %1$s and size of file (")
                        .append(getEARKSIPpath().resolve(Paths.get(metsPath).resolve(href))).append(") isn't equal");
                      return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
                        Message.createErrorMessage(message.toString(), metsName, isRootMets()), false, false);
                    }
                  }
                }
              } else {
                return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, Message.createErrorMessage(
                  "mets/fileSec/fileGrp/file/@SIZE in %1$s can't be null", metsName, isRootMets()), false, false);
              }
            } else {
              return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, Message.createErrorMessage(
                "mets/fileSec/fileGrp/file/flocat/@href in %1$s can't be null", metsName, isRootMets()), false, false);
            }
          } else {
            return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
              Message.createErrorMessage("cannot have more than one mets/fileSec/fileGrp/file/flocat in %1$s", metsName,
                isRootMets()),
              false, false);
          }
        } else {
          return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, Message.createErrorMessage(
            "mets/fileSec/fileGrp/file/flocat in %1$s can't be null", metsName, isRootMets()), false, false);
        }
      }
    }
    return new ReporterDetails();
  }

  /*
   * mets/fileSec/fileGrp/file/@CREATED Creation date of the referenced file.
   */
  private ReporterDetails validateCSIP70() {
    MetsType.FileSec fileSec = mets.getFileSec();
    List<MetsType.FileSec.FileGrp> fileGrp = fileSec.getFileGrp();
    for (MetsType.FileSec.FileGrp grp : fileGrp) {
      List<FileType> files = grp.getFile();
      for (FileType file : files) {
        if (file.getCREATED() == null) {
          return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, Message.createErrorMessage(
            "mets/fileSec/fileGrp/file/@CREATED in %1$s can't be null", metsName, isRootMets()), false, false);
        }
      }
    }
    return new ReporterDetails();
  }

  /*
   * mets/fileSec/fileGrp/file/@CHECKSUM The checksum of the referenced file.
   */
  private ReporterDetails validateCSIP71() throws IOException, NoSuchAlgorithmException {
    List<String> tmp = new ArrayList<>();
    for (CHECKSUMTYPE check : CHECKSUMTYPE.values()) {
      tmp.add(check.toString());
    }
    StringBuilder message = new StringBuilder();
    MetsType.FileSec fileSec = mets.getFileSec();
    List<MetsType.FileSec.FileGrp> fileGrp = fileSec.getFileGrp();
    for (MetsType.FileSec.FileGrp grp : fileGrp) {
      List<FileType> files = grp.getFile();
      for (FileType file : files) {
        String checksumType = file.getCHECKSUMTYPE();
        if (checksumType == null) {
          return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, Message.createErrorMessage(
            "mets/fileSec/fileGrp/file/@CHECKSUMTYPE in %1$s can't be null", metsName, isRootMets()), false, false);
        } else {
          if (!tmp.contains(checksumType)) {
            message.append("Value ").append(checksumType)
              .append(" in %1$s for mets/fileSec/fileGrp/file/@CHECKSUMTYPE isn't valid");
            return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
              Message.createErrorMessage(message.toString(), metsName, isRootMets()), false, false);
          } else {
            String checksum = file.getCHECKSUM();
            if (checksum == null) {
              return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, Message.createErrorMessage(
                "mets/fileSec/fileGrp/file/@CHECKSUM in %1$s can't be null", metsName, isRootMets()), false, false);
            } else {
              String href = file.getFLocat().get(0).getHref();
              if (href == null) {
                return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, Message.createErrorMessage(
                  "mets/fileSec/fileGrp/file/flocat/href in %1$s can't be null", metsName, isRootMets()), false, false);
              } else {
                String filePath = URLDecoder.decode(href, UTF_8);
                if (isZipFileFlag()) {
                  StringBuilder finalPath = new StringBuilder();
                  if (!isRootMets()) {
                    finalPath.append(metsPath).append(filePath);
                  } else {
                    finalPath.append(mets.getOBJID()).append("/").append(filePath);
                  }
                  if (!zipManager.verifyChecksum(getEARKSIPpath(), finalPath.toString(), checksumType, checksum)) {
                    message.append("mets/dmdSec/mdRef/@CHECKSUM ").append(checksum)
                      .append(" in %1$s and checksum of file (").append(finalPath).append(") isn't equal");
                    return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
                      Message.createErrorMessage(message.toString(), metsName, isRootMets()), false, false);
                  }
                } else {
                  if (!folderManager.verifyChecksum(Paths.get(metsPath).resolve(filePath), checksumType, checksum)) {
                    message.append("mets/dmdSec/mdRef/@CHECKSUM ").append(checksum)
                      .append(" in %1$s and checksum of file (").append(Paths.get(metsPath).resolve(filePath))
                      .append(") isn't equal");
                    return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
                      Message.createErrorMessage(message.toString(), metsName, isRootMets()), false, false);
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
   * mets/fileSec/fileGrp/file/@CHECKSUMTYPE The type of checksum following the
   * value list present in the METS-standard which has been used for calculating
   * the checksum for the referenced file.
   */
  private ReporterDetails validateCSIP72() {
    List<String> tmp = new ArrayList<>();
    for (CHECKSUMTYPE check : CHECKSUMTYPE.values()) {
      tmp.add(check.toString());
    }
    MetsType.FileSec fileSec = mets.getFileSec();
    List<MetsType.FileSec.FileGrp> fileGrp = fileSec.getFileGrp();
    for (MetsType.FileSec.FileGrp grp : fileGrp) {
      List<FileType> files = grp.getFile();
      for (FileType file : files) {
        String checksumType = file.getCHECKSUMTYPE();
        if (checksumType == null) {
          return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, Message.createErrorMessage(
            "mets/fileSec/fileGrp/file/@CHECKSUMTYPE in %1$s can't be null", metsName, isRootMets()), false, false);
        } else {
          if (!tmp.contains(checksumType)) {
            StringBuilder message = new StringBuilder();
            message.append("Value ").append(checksumType)
              .append(" in %1$s for mets/fileSec/fileGrp/file/@CHECKSUMTYPE isn't valid");
            return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
              Message.createErrorMessage(message.toString(), metsName, isRootMets()), false, false);
          }
        }
      }
    }
    return new ReporterDetails();
  }

  /*
   * mets/fileSec/fileGrp/file/@OWNERID If an identifier for the file was supplied
   * by the owner it can be recorded in this attribute.
   */
  private ReporterDetails validateCSIP73() {
    MetsType.FileSec fileSec = mets.getFileSec();
    List<MetsType.FileSec.FileGrp> fileGrp = fileSec.getFileGrp();
    for (MetsType.FileSec.FileGrp grp : fileGrp) {
      List<FileType> files = grp.getFile();
      for (FileType file : files) {
        String ownerID = file.getOWNERID();
        if (ownerID != null) {
          return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
            Message.createErrorMessage("A owner identifier was defined in %1$s", metsName, isRootMets()), false, false);
        }
      }
    }
    return new ReporterDetails();
  }

  /*
   * mets/fileSec/fileGrp/file/@ADMID If administrative metadata has been provided
   * for the file this attribute refers to the file’s administrative metadata by
   * ID.
   */
  private ReporterDetails validateCSIP74() {
    List<MetsType.FileSec.FileGrp> fileGrps = mets.getFileSec().getFileGrp();
    List<AmdSecType> amdSec = mets.getAmdSec();
    for (MetsType.FileSec.FileGrp fileGrp : fileGrps) {
      List<FileType> files = fileGrp.getFile();
      for (FileType file : files) {
        List<Object> admids = file.getADMID();
        if (admids != null && !admids.isEmpty()) {
          boolean found = false;
          for (Object o : admids) {
            MdSecType.MdRef mdRef = (MdSecType.MdRef) o;
            String admid = mdRef.getID();
            for (AmdSecType amdSecType : amdSec) {
              List<MdSecType> digiProv = amdSecType.getDigiprovMD();
              for (MdSecType mdSecType : digiProv) {
                String id = mdSecType.getMdRef().getID();
                if (admid.equals(id)) {
                  found = true;
                  break;
                }
              }
            }
            if (!found) {
              StringBuilder message = new StringBuilder();
              message.append("Value ").append(admid).append(
                " in %1$s for mets/fileSec/fileGrp/file/@ADMID does not match with any mets/amdSec/digiprovMd/@ID");
              return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
                Message.createErrorMessage(message.toString(), metsName, isRootMets()), false, false);
            }
          }
        }
      }
    }
    return new ReporterDetails();
  }

  /*
   * mets/fileSec/fileGrp/file/@DMDID If descriptive metadata has been provided
   * per file this attribute refers to the file’s descriptive metadata by ID.
   */
  private ReporterDetails validateCSIP75() {
    List<MetsType.FileSec.FileGrp> fileGrps = mets.getFileSec().getFileGrp();
    List<MdSecType> dmdSec = mets.getDmdSec();
    for (MetsType.FileSec.FileGrp fileGrp : fileGrps) {
      List<FileType> files = fileGrp.getFile();
      for (FileType file : files) {
        List<Object> dmdids = file.getDMDID();
        if (dmdids != null && !dmdids.isEmpty()) {
          boolean found = false;
          for (Object o : dmdids) {
            MdSecType.MdRef mdRef = (MdSecType.MdRef) o;
            String dmdid = mdRef.getID();
            for (MdSecType md : dmdSec) {
              String id = md.getMdRef().getID();
              if (dmdid.equals(id)) {
                found = true;
                break;
              }
            }
            if (!found) {
              StringBuilder message = new StringBuilder();
              message.append("Value ").append(dmdid)
                .append(" in %1$s for mets/fileSec/fileGrp/file/@DMDID does not match with any mets/dmdSec/mdRef/@ID");
              return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
                Message.createErrorMessage(message.toString(), metsName, isRootMets()), false, false);
            }
          }

        }
      }
    }
    return new ReporterDetails();
  }

  /*
   * mets/fileSec/fileGrp/file/FLocat The location of each external file must be
   * defined by the file location <FLocat> element using the same rules as for
   * referencing metadata files. All references to files should be made using the
   * XLink href attribute and the file protocol using the relative location of the
   * file.
   */
  private ReporterDetails validateCSIP76() {
    MetsType.FileSec fileSec = mets.getFileSec();
    List<MetsType.FileSec.FileGrp> fileGrp = fileSec.getFileGrp();
    for (MetsType.FileSec.FileGrp grp : fileGrp) {
      List<FileType> files = grp.getFile();
      for (FileType file : files) {
        List<FileType.FLocat> flocat = file.getFLocat();
        if (flocat == null) {
          return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, Message.createErrorMessage(
            "mets/fileSec/fileGrp/file/FLocat in %1$s can't be null", metsName, isRootMets()), false, false);
        } else {
          if (flocat.size() != 1) {
            return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
              Message.createErrorMessage("Can't have more than one mets/fileSec/fileGrp/file/FLocat in %1$s", metsName,
                isRootMets()),
              false, false);
          }
        }
      }
    }
    return new ReporterDetails();
  }

  /*
   * mets/fileSec/fileGrp/file/FLocat[@LOCTYPE=’URL’] The locator type is always
   * used with the value “URL” from the vocabulary in the attribute.
   */
  private ReporterDetails validateCSIP77() {
    MetsType.FileSec fileSec = mets.getFileSec();
    List<MetsType.FileSec.FileGrp> fileGrp = fileSec.getFileGrp();
    for (MetsType.FileSec.FileGrp grp : fileGrp) {
      List<FileType> files = grp.getFile();
      for (FileType file : files) {
        List<FileType.FLocat> flocat = file.getFLocat();
        if (flocat == null) {
          return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, Message.createErrorMessage(
            "mets/fileSec/fileGrp/file/FLocat in %1$s can't be null", metsName, isRootMets()), false, false);
        } else {
          for (FileType.FLocat floc : flocat) {
            String loctype = floc.getLOCTYPE();
            if (loctype == null) {
              return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
                Message.createErrorMessage("mets/fileSec/fileGrp/file/FLocat[@LOCTYPE=’URL’] in %1$s can't be null",
                  metsName, isRootMets()),
                false, false);
            } else {
              if (!loctype.equals("URL")) {
                return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
                  Message.createErrorMessage(
                    "mets/fileSec/fileGrp/file/FLocat[@LOCTYPE=’URL’] value in %1$s has to be URL ", metsName,
                    isRootMets()),
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
   * mets/fileSec/fileGrp/file/FLocat[@xlink:type=’simple’] Attribute used with
   * the value “simple”. Value list is maintained by the xlink standard.
   */
  private ReporterDetails validateCSIP78() throws IOException {
    MetsType.FileSec fileSec = mets.getFileSec();
    List<MetsType.FileSec.FileGrp> fileGrp = fileSec.getFileGrp();
    HashMap<String, String> fileSecTypes = new HashMap<>();
    MetsHandler fileSecHandler = new MetsHandler("file", "FLocat", fileSecTypes);
    MetsParser metsParser = new MetsParser();
    InputStream metsStream = null;
    if (!fileGrp.isEmpty()) {
      if (isZipFileFlag()) {
        if (isRootMets()) {
          metsStream = zipManager.getMetsRootInputStream(getEARKSIPpath());
        } else {
          metsStream = zipManager.getZipInputStream(getEARKSIPpath(), metsPath + "METS.xml");
        }
      } else {
        if (isRootMets()) {
          metsStream = folderManager.getMetsRootInputStream(getEARKSIPpath());
        } else {
          metsStream = folderManager.getInputStream(Paths.get(metsPath).resolve("METS.xml"));
        }
      }
    }
    if (metsStream != null) {
      metsParser.parse(fileSecHandler, metsStream);
    }

    for (MetsType.FileSec.FileGrp grp : fileGrp) {
      List<FileType> files = grp.getFile();
      for (FileType file : files) {
        List<FileType.FLocat> flocat = file.getFLocat();
        if (flocat.isEmpty()) {
          return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, Message.createErrorMessage(
            "mets/fileSec/fileGrp/file/FLocat in %1$s can't be null", metsName, isRootMets()), false, false);
        } else {
          for (FileType.FLocat floc : flocat) {
            if (fileSecTypes.get(floc.getHref()) == null) {
              return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
                Message.createErrorMessage(
                  "mets/fileSec/fileGrp/file/FLocat[@xlink:type=’simple’] in %1$s can't be null", metsName,
                  isRootMets()),
                false, false);
            } else {
              if (!fileSecTypes.get(floc.getHref()).equals("simple")) {
                return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
                  Message.createErrorMessage(
                    "mets/fileSec/fileGrp/file/FLocat[@xlink:type=’simple’] value in %1$s has to be simple", metsName,
                    isRootMets()),
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
   * mets/fileSec/fileGrp/file/FLocat/@xlink:href The actual location of the
   * resource. We recommend recording a URL type filepath within this attribute.
   */
  private ReporterDetails validateCSIP79() throws IOException {
    MetsType.FileSec fileSec = mets.getFileSec();
    List<MetsType.FileSec.FileGrp> fileGrp = fileSec.getFileGrp();
    StringBuilder message = new StringBuilder();
    for (MetsType.FileSec.FileGrp grp : fileGrp) {
      List<FileType> files = grp.getFile();
      for (FileType file : files) {
        List<FileType.FLocat> flocat = file.getFLocat();
        if (flocat == null) {
          return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, Message.createErrorMessage(
            "mets/fileSec/fileGrp/file/FLocat in %1$s can't be null", metsName, isRootMets()), false, false);
        } else {
          for (FileType.FLocat floc : flocat) {
            String href = floc.getHref();
            if (href != null) {
              String hrefDecoded = URLDecoder.decode(href, UTF_8);
              if (isZipFileFlag()) {
                StringBuilder finalPath = new StringBuilder();
                if (!isRootMets()) {
                  finalPath.append(metsPath).append(hrefDecoded);
                } else {
                  finalPath.append(mets.getOBJID()).append("/").append(hrefDecoded);
                }
                if (!zipManager.checkPathExists(getEARKSIPpath(), finalPath.toString())) {
                  message.append("mets/fileSec/fileGrp/file/@xlink:href ").append(finalPath)
                    .append(" does not exist (%1$s)");
                  return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
                    Message.createErrorMessage(message.toString(), metsName, isRootMets()), false, false);
                }
              } else {
                if (!folderManager.checkPathExists(Paths.get(metsPath).resolve(hrefDecoded))) {
                  message.append("mets/fileSec/fileGrp/file/@xlink:href ")
                    .append(Paths.get(metsPath).resolve(hrefDecoded)).append(" does not exist (%1$s)");
                  return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
                    Message.createErrorMessage(message.toString(), metsName, isRootMets()), false, false);
                }
              }
            } else {
              return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
                Message.createErrorMessage("mets/fileSec/fileGrp/file/FLocat/@xlink:href in %1$s can't be null",
                  metsName, isRootMets()),
                false, false);
            }
          }
        }
      }
    }
    return new ReporterDetails();
  }

  /* SIP Specification Validations */

  /*
   * fileSec/fileGrp/file/@sip:FILEFORMATNAME An optional attribute may be used if
   * the MIMETYPE is not suicient for the purposes of processing the information
   * package. Example: “Extensible Markup Language” Example: “PDF/A” Example:
   * “ISO/IEC 26300:2006”
   */

  private ReporterDetails validateSIP32() {
    List<MetsType.FileSec.FileGrp> fileGrps = mets.getFileSec().getFileGrp();
    for (MetsType.FileSec.FileGrp fileGrp : fileGrps) {
      int count = 0;
      List<FileType> files = fileGrp.getFile();
      for (FileType file : files) {
        String fileFormatName = file.getFILEFORMATNAME();
        if (fileFormatName != null && !fileFormatName.equals("")) {
          count++;
        }
      }
      if (count != files.size()) {
        return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION, Message.createErrorMessage(
          "can add fileSec/fileGrp/file/@sip:FILEFORMATNAME in %1$s  if the MIMETYPE is not sufficient for the purposes of processing the information package.",
          metsName, isRootMets()), false, false);
      }
    }
    return new ReporterDetails();
  }

  /*
   * fileSec/fileGrp/file/@sip:FILEFORMATVERSION The version of the file format
   * when the use of PREMIS has not been agreed upon in the submission agreement.
   * Example: “1.0”
   */
  private ReporterDetails validateSIP33() {
    List<MetsType.FileSec.FileGrp> fileGrps = mets.getFileSec().getFileGrp();
    for (MetsType.FileSec.FileGrp fileGrp : fileGrps) {
      int count = 0;
      List<FileType> files = fileGrp.getFile();
      for (FileType file : files) {
        String fileFormatVersion = file.getFILEFORMATVERSION();
        if (fileFormatVersion != null && !fileFormatVersion.equals("")) {
          count++;
        }
      }
      if (count != files.size()) {
        return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION, Message.createErrorMessage(
          "can add fileSec/fileGrp/file/@sip:FILEFORMATVERSION in %1$s. The version of the file format when the use of PREMIS has not been agreed upon in the submission agreement.",
          metsName, isRootMets()), false, false);
      }
    }
    return new ReporterDetails();
  }

  /*
   * fileSec/fileGrp/file/@sip:FILEFORMATREGISTRY The name of the format registry
   * used to identify the file format when the use of PREMIS has not been agreed
   * upon in the submission agreement. Example: “PRONOM”
   */
  private ReporterDetails validateSIP34() {
    List<MetsType.FileSec.FileGrp> fileGrps = mets.getFileSec().getFileGrp();
    for (MetsType.FileSec.FileGrp fileGrp : fileGrps) {
      int count = 0;
      List<FileType> files = fileGrp.getFile();
      for (FileType file : files) {
        String fileFormatRegistry = file.getFORMATREGISTRY();
        if (fileFormatRegistry != null && !fileFormatRegistry.equals("")) {
          count++;
        }
      }
      if (count != files.size()) {
        return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION, Message.createErrorMessage(
          "can add fileSec/fileGrp/file/@sip:FILEFORMATREGISTRY in %1$s. The name of the format registry used to identify the file format when the use of PREMIS has not been agreed upon in the submission agreement.",
          metsName, isRootMets()), false, false);
      }
    }
    return new ReporterDetails();
  }

  /*
   * fileSec/fileGrp/file/@sip:FILEFORMATKEY Key of the file format in the
   * registry when use of PREMIS has not been agreed upon in the submission
   * agreement. Example: “fmt/101”
   */
  private ReporterDetails validateSIP35() {
    List<MetsType.FileSec.FileGrp> fileGrps = mets.getFileSec().getFileGrp();
    for (MetsType.FileSec.FileGrp fileGrp : fileGrps) {
      int count = 0;
      List<FileType> files = fileGrp.getFile();
      for (FileType file : files) {
        String fileFormatKey = file.getFORMATREGISTRYKEY();
        if (fileFormatKey != null && !fileFormatKey.equals("")) {
          count++;
        }
      }
      if (count != files.size()) {
        return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION, Message.createErrorMessage(
          "can add fileSec/fileGrp/file/@sip:FILEFORMATKEY in %1$s. Key of the file format in the registry when use of PREMIS has not been agreed upon in the submission agreement.",
          metsName, isRootMets()), false, false);
      }
    }
    return new ReporterDetails();
  }

}
