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
import java.util.stream.Collectors;

import javax.xml.namespace.QName;
import javax.xml.parsers.ParserConfigurationException;

import org.roda_project.commons_ip2.mets_v1_12.beans.AmdSecType;
import org.roda_project.commons_ip2.mets_v1_12.beans.FileGrpType;
import org.roda_project.commons_ip2.mets_v1_12.beans.FileType;
import org.roda_project.commons_ip2.mets_v1_12.beans.MdSecType;
import org.roda_project.commons_ip2.mets_v1_12.beans.MetsType;
import org.roda_project.commons_ip2.utils.IanaMediaTypes;
import org.roda_project.commons_ip2.validator.common.ControlledVocabularyParser;
import org.roda_project.commons_ip2.validator.common.MetsParser;
import org.roda_project.commons_ip2.validator.component.MetsValidatorImpl;
import org.roda_project.commons_ip2.validator.constants.Constants;
import org.roda_project.commons_ip2.validator.constants.ConstantsCSIPspec;
import org.roda_project.commons_ip2.validator.constants.ConstantsSIPspec;
import org.roda_project.commons_ip2.validator.handlers.MetsHandler;
import org.roda_project.commons_ip2.validator.reporter.ReporterDetails;
import org.roda_project.commons_ip2.validator.state.MetsValidatorState;
import org.roda_project.commons_ip2.validator.state.StructureValidatorState;
import org.roda_project.commons_ip2.validator.utils.CHECKSUMTYPE;
import org.roda_project.commons_ip2.validator.utils.Message;
import org.roda_project.commons_ip2.validator.utils.ResultsUtils;
import org.xml.sax.SAXException;

/** {@author João Gomes <jgomes@keep.pt>}. */
public class FileSectionComponentValidator extends MetsValidatorImpl {
  /**
   * Constant "UTF-8".
   */
  public static final String UTF_8 = "UTF-8";
  /**
   * Constant DILCIS URL.
   */
  public static final String HTTPS_DILCIS_EU_XML_METS_CSIPEXTENSION_METS = "https://"
    + "dilcis.eu/XML/METS/CSIPExtensionMETS";

  /**
   * {@link String} module name.
   */
  private final String moduleName;
  /**
   * {@link List} of content information type.
   */
  private List<String> contentInformationType;

  /**
   * Initializes Validation component.
   *
   * @throws IOException
   *           if some I/O error occurs.
   * @throws ParserConfigurationException
   *           if some error occurs.
   * @throws SAXException
   *           if some error occurs.
   */
  public FileSectionComponentValidator() throws IOException, ParserConfigurationException, SAXException {
    moduleName = Constants.CSIP_MODULE_NAME_5;
    this.contentInformationType = ControlledVocabularyParser
      .parse(Constants.PATH_RESOURCES_CSIP_VOCABULARY_CONTENT_INFORMATION_TYPE);
  }

  @Override
  public Map<String, ReporterDetails> validate(final StructureValidatorState structureValidatorState,
    final MetsValidatorState metsValidatorState) throws IOException {
    ReporterDetails csip;
    final Map<String, ReporterDetails> results = new HashMap<>();
    /* CSIP58 */
    notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP58_ID);
    ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP58_ID,
      validateCSIP58(metsValidatorState).setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION));

    if (ResultsUtils.isResultValid(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP58_ID)) {

      /* CSIP59 */
      notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP59_ID);
      ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP59_ID,
        validateCSIP59(metsValidatorState).setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION));

      /* CSIP60 */
      notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP60_ID);
      ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP60_ID,
        validateCSIP60(structureValidatorState, metsValidatorState)
          .setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION));

      /* CSIP113 */
      notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP113_ID);
      ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP113_ID,
        validateCSIP113(structureValidatorState, metsValidatorState)
          .setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION));

      /* CSIP114 */
      notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP114_ID);
      ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP114_ID,
        validateCSIP114(structureValidatorState, metsValidatorState)
          .setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION));

      /* CSIP61 */
      notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP61_ID);
      ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP61_ID,
        validateCSIP61(metsValidatorState).setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION));

      /* CSIP62 */
      notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP62_ID);
      ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP62_ID,
        validateCSIP62(metsValidatorState).setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION));

      /* CSIP63 */
      notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP63_ID);
      ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP63_ID,
        validateCSIP63(metsValidatorState).setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION));

      /* CSIP64 */
      notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP64_ID);
      ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP64_ID,
        validateCSIP64(structureValidatorState, metsValidatorState)
          .setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION));

      /* CSIP65 */
      notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP65_ID);
      ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP65_ID,
        validateCSIP65(metsValidatorState).setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION));

      /* CSIP66 */
      notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP66_ID);
      ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP66_ID,
        validateCSIP66(structureValidatorState, metsValidatorState)
          .setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION));

      if (ResultsUtils.isResultValid(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP66_ID)) {

        /* CSIP67 */
        notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP67_ID);
        ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP67_ID,
          validateCSIP67(metsValidatorState).setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION));

        /* CSIP68 */
        notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP68_ID);
        ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP68_ID,
          validateCSIP68(metsValidatorState).setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION));

        /* CSIP69 */
        notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP69_ID);
        ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP69_ID,
          validateCSIP69(structureValidatorState, metsValidatorState)
            .setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION));

        /* CSIP70 */
        notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP70_ID);
        ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP70_ID,
          validateCSIP70(metsValidatorState).setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION));

        /* CSIP71 */
        notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP71_ID);
        try {
          csip = validateCSIP71(structureValidatorState, metsValidatorState)
            .setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION);
        } catch (final Exception e) {
          csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
            Message.createErrorMessage("Can't calculate checksum of file %1$s", metsValidatorState.getMetsName(),
              metsValidatorState.isRootMets()),
            false, false);
        }
        ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP71_ID, csip);

        /* CSIP72 */
        notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP72_ID);
        ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP72_ID,
          validateCSIP72(metsValidatorState)
            .setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION));

        /* CSIP73 */
        notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP73_ID);
        ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP73_ID,
          validateCSIP73(metsValidatorState)
            .setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION));

        /* CSIP74 */
        notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP74_ID);
        ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP74_ID,
          validateCSIP74(metsValidatorState)
            .setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION));

        /* CSIP75 */
        notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP75_ID);
        ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP75_ID,
          validateCSIP75(metsValidatorState)
            .setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION));

        /* CSIP76 */
        notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP76_ID);
        ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP76_ID,
          validateCSIP76(metsValidatorState)
            .setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION));

        if (ResultsUtils.isResultValid(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP76_ID)) {

          /* CSIP77 */
          notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP77_ID);
          ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP77_ID,
            validateCSIP77(metsValidatorState)
              .setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION));

          /* CSIP78 */
          notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP78_ID);
          ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP78_ID,
            validateCSIP78(structureValidatorState, metsValidatorState)
              .setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION));

          /* CSIP79 */
          notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP79_ID);
          ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP79_ID,
            validateCSIP79(structureValidatorState, metsValidatorState)
              .setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION));

        } else {
          final String message = Message.createErrorMessage(
            "SKIPPED in %1$s because mets/fileSec/fileGrp/file/FLocat doesn't exist", metsValidatorState.getMetsName(),
            metsValidatorState.isRootMets());

          ResultsUtils.addResults(results,
            new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, message, true, true),
            ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP77_ID,
            ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP78_ID,
            ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP79_ID);
        }

      } else {
        final String message = Message.createErrorMessage(
          "SKIPPED in %1$s because mets/fileSec/fileGrp/file/ doesn't exist", metsValidatorState.getMetsName(),
          metsValidatorState.isRootMets());

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
      final String message = Message.createErrorMessage("SKIPPED in %1$s because mets/fileSec doesn't exist",
        metsValidatorState.getMetsName(), metsValidatorState.isRootMets());

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
        ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP79_ID);
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
  private ReporterDetails validateCSIP58(final MetsValidatorState metsValidatorState) {
    final ReporterDetails details = new ReporterDetails();
    final MetsType.FileSec fileSec = metsValidatorState.getMets().getFileSec();
    if (fileSec == null) {
      details.setValid(false);
      details.addIssue(Message.createErrorMessage("mets/fileSec in %1$s can't be null",
        metsValidatorState.getMetsName(), metsValidatorState.isRootMets()));
    }
    return details;
  }

  /*
   * mets/fileSec/@ID An xml:id identifier for the file section used for internal
   * package references. It must be unique within the package.
   */
  private ReporterDetails validateCSIP59(final MetsValidatorState metsValidatorState) {
    final MetsType.FileSec fileSec = metsValidatorState.getMets().getFileSec();
    if (fileSec != null) {
      final String id = fileSec.getID();
      if (id != null) {
        if (!metsValidatorState.checkMetsInternalId(id)) {
          metsValidatorState.addMetsInternalId(id);
        } else {
          final StringBuilder message = new StringBuilder();
          message.append("Value ").append(id).append(" in %1$s for mets/fileSec/@ID isn't unique in the package");
          return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, Message.createErrorMessage(
            message.toString(), metsValidatorState.getMetsName(), metsValidatorState.isRootMets()), false, false);
        }
      } else {
        return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
          Message.createErrorMessage("mets/fileSec/@ID in %1$s can't be null", metsValidatorState.getMetsName(),
            metsValidatorState.isRootMets()),
          false, false);
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
  private ReporterDetails validateCSIP60(final StructureValidatorState structureValidatorState,
    final MetsValidatorState metsValidatorState) throws IOException {
    final List<MetsType.FileSec.FileGrp> fileGrps = metsValidatorState.getMets().getFileSec().getFileGrp();
    final StringBuilder message = new StringBuilder();
    for (MetsType.FileSec.FileGrp fileGrp : fileGrps) {
      if (fileGrp.getUSE() != null && fileGrp.getUSE().equals("Documentation")) {
        final List<FileType> files = fileGrp.getFile();
        if (files != null && !files.isEmpty()) {
          for (FileType file : files) {
            final List<FileType.FLocat> fLocats = file.getFLocat();
            if (structureValidatorState.isZipFileFlag()) {
              for (FileType.FLocat flocat : fLocats) {
                final String href = URLDecoder.decode(flocat.getHref(), UTF_8);
                final StringBuilder filePath = new StringBuilder();
                if (metsValidatorState.isRootMets()) {
                  filePath.append(metsValidatorState.getMets().getOBJID()).append(Constants.SEPARATOR).append(href);
                } else {
                  filePath.append(metsValidatorState.getMetsPath()).append(href);
                }
                if (!structureValidatorState.getZipManager().checkPathExists(structureValidatorState.getIpPath(),
                  filePath.toString())) {
                  message.append("mets/fileSec/fileGrp[@USE=’Documentation’] ").append(filePath)
                    .append(" doesn't exists (%1$s)");
                  return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
                    Message.createErrorMessage(message.toString(), metsValidatorState.getMetsName(),
                      metsValidatorState.isRootMets()),
                    false, false);
                }
              }
            } else {
              for (FileType.FLocat flocat : fLocats) {
                final String filePath = URLDecoder.decode(flocat.getHref(), UTF_8);
                if (!structureValidatorState.getFolderManager()
                  .checkPathExists(Paths.get(metsValidatorState.getMetsPath()).resolve(filePath))) {
                  message.append("mets/fileSec/fileGrp[@USE=’Documentation’] ")
                    .append(Paths.get(metsValidatorState.getMetsPath()).resolve(filePath))
                    .append(" doesn't exists (in %1$s)");
                  return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
                    Message.createErrorMessage(message.toString(), metsValidatorState.getMetsName(),
                      metsValidatorState.isRootMets()),
                    false, false);
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
  private ReporterDetails validateCSIP113(final StructureValidatorState structureValidatorState,
    final MetsValidatorState metsValidatorState) throws IOException {
    final List<MetsType.FileSec.FileGrp> fileGrps = metsValidatorState.getMets().getFileSec().getFileGrp();
    final StringBuilder message = new StringBuilder();
    for (MetsType.FileSec.FileGrp fileGrp : fileGrps) {
      if (fileGrp.getUSE() != null && fileGrp.getUSE().equals("Schemas")) {
        final List<FileType> files = fileGrp.getFile();
        for (FileType file : files) {
          final List<FileType.FLocat> fLocats = file.getFLocat();
          if (structureValidatorState.isZipFileFlag()) {
            for (FileType.FLocat flocat : fLocats) {
              final String href = URLDecoder.decode(flocat.getHref(), UTF_8);
              final StringBuilder filePath = new StringBuilder();
              if (metsValidatorState.isRootMets()) {
                filePath.append(metsValidatorState.getMets().getOBJID()).append(Constants.SEPARATOR).append(href);
              } else {
                filePath.append(metsValidatorState.getMetsPath()).append(href);
              }
              if (!structureValidatorState.getZipManager().checkPathExists(structureValidatorState.getIpPath(),
                filePath.toString())) {
                message.append("mets/fileSec/fileGrp[@USE=’Schemas’] ").append(filePath.toString().replace("%", "%%"))
                  .append(" doesn't exists (%1$s)");
                return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, Message.createErrorMessage(
                  message.toString(), metsValidatorState.getMetsName(), metsValidatorState.isRootMets()), false, false);
              }
            }
          } else {
            for (FileType.FLocat flocat : fLocats) {
              final String filePath = URLDecoder.decode(flocat.getHref(), UTF_8);
              if (!structureValidatorState.getFolderManager()
                .checkPathExists(Paths.get(metsValidatorState.getMetsPath()).resolve(filePath))) {
                message.append("mets/fileSec/fileGrp[@USE=’Schemas’] ")
                  .append(Paths.get(metsValidatorState.getMetsPath()).resolve(filePath))
                  .append(" doesn't exists (%1$s)");
                return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, Message.createErrorMessage(
                  message.toString(), metsValidatorState.getMetsName(), metsValidatorState.isRootMets()), false, false);
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
  private ReporterDetails validateCSIP114(final StructureValidatorState structureValidatorState,
    final MetsValidatorState metsValidatorState) throws IOException {
    final List<MetsType.FileSec.FileGrp> fileGrps = metsValidatorState.getMets().getFileSec().getFileGrp();
    final StringBuilder message = new StringBuilder();
    for (MetsType.FileSec.FileGrp fileGrp : fileGrps) {
      if (fileGrp.getUSE() != null && fileGrp.getUSE().matches("Representations/")) {
        final List<FileType> files = fileGrp.getFile();
        for (FileType file : files) {
          final List<FileType.FLocat> fLocats = file.getFLocat();
          if (structureValidatorState.isZipFileFlag()) {
            for (FileType.FLocat flocat : fLocats) {
              final String href = URLDecoder.decode(flocat.getHref(), UTF_8);
              final StringBuilder filePath = new StringBuilder();
              if (metsValidatorState.isRootMets()) {
                filePath.append(metsValidatorState.getMets().getOBJID()).append(Constants.SEPARATOR).append(href);
              } else {
                filePath.append(metsValidatorState.getMetsPath()).append(href);
              }
              if (!structureValidatorState.getZipManager().checkPathExists(structureValidatorState.getIpPath(),
                filePath.toString())) {
                message.append("mets/fileSec/fileGrp[@USE=’Representations’] ").append(filePath)
                  .append(" doesn't exists (%1$s)");
                return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, Message.createErrorMessage(
                  message.toString(), metsValidatorState.getMetsName(), metsValidatorState.isRootMets()), false, false);
              }
            }
          } else {
            for (FileType.FLocat flocat : fLocats) {
              final String filePath = URLDecoder.decode(flocat.getHref(), UTF_8);
              if (!structureValidatorState.getFolderManager()
                .checkPathExists(Paths.get(metsValidatorState.getMetsPath()).resolve(filePath))) {
                message.append("mets/fileSec/fileGrp[@USE=’Representations’] ")
                  .append(Paths.get(metsValidatorState.getMetsPath()).resolve(filePath))
                  .append(" doesn't exists (%1$s)");
                return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, Message.createErrorMessage(
                  message.toString(), metsValidatorState.getMetsName(), metsValidatorState.isRootMets()), false, false);
              }
            }
          }
        }
      }
    }
    return new ReporterDetails();
  }

  /**
   * mets/fileSec/fileGrp/@ADMID If administrative metadata has been provided at
   * file group mets/fileSec/fileGrp level this attribute refers to its
   * administrative metadata section by ID.
   *
   * <p>
   * Nota: verificar se é id do digiprovMD ou amdSec
   *
   * @return reporter detail results
   */
  private ReporterDetails validateCSIP61(final MetsValidatorState metsValidatorState) {
    final List<MetsType.FileSec.FileGrp> fileGrps = metsValidatorState.getMets().getFileSec().getFileGrp();
    final List<AmdSecType> amdSec = metsValidatorState.getMets().getAmdSec();
    for (MetsType.FileSec.FileGrp fileGrp : fileGrps) {
      final QName keyAdmid = new QName(HTTPS_DILCIS_EU_XML_METS_CSIPEXTENSION_METS, "ADMID", "csip");
      final String admid = fileGrp.getOtherAttributes().get(keyAdmid);
      if (admid != null) {
        boolean found = false;
        for (AmdSecType a : amdSec) {
          final List<MdSecType> digiProv = a.getDigiprovMD();
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
          final StringBuilder message = new StringBuilder();
          message.append("Value ").append(admid).append(
            " in %1$s for mets/fileSec/fileGrp/file/@ADMID " + "doesn't match with any mets/amdSec/digiprovMD/@ID");
          return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, Message.createErrorMessage(
            message.toString(), metsValidatorState.getMetsName(), metsValidatorState.isRootMets()), false, false);
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
  private ReporterDetails validateCSIP62(final MetsValidatorState metsValidatorState) {
    final List<MetsType.FileSec.FileGrp> fileGrps = metsValidatorState.getMets().getFileSec().getFileGrp();
    for (MetsType.FileSec.FileGrp fileGrp : fileGrps) {
      if (fileGrp.getUSE() != null && fileGrp.getUSE().matches("Representations/")) {
        final QName keyContentInformationType = new QName(HTTPS_DILCIS_EU_XML_METS_CSIPEXTENSION_METS,
          "CONTENTINFORMATIONTYPE", "csip");
        final String cType = fileGrp.getOtherAttributes().get(keyContentInformationType);
        if (cType != null) {
          if (!contentInformationType.contains(cType)) {
            final StringBuilder message = new StringBuilder();
            message.append("Value ").append(cType).append(" in %1$s for mets/fileSec/fileGrp[@USE=’Representations’]"
              + "/@csip:CONTENTINFORMATIONTYPE value isn't valid");
            return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, Message.createErrorMessage(
              message.toString(), metsValidatorState.getMetsName(), metsValidatorState.isRootMets()), false, false);
          }
        } else {
          return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
            Message.createErrorMessage(
              "mets/fileSec/fileGrp[@USE=’Representations’]" + "/@csip:CONTENTINFORMATIONTYPE in %1$s can't be null",
              metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
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
  private ReporterDetails validateCSIP63(final MetsValidatorState metsValidatorState) {
    final List<MetsType.FileSec.FileGrp> fileGrps = metsValidatorState.getMets().getFileSec().getFileGrp();
    for (MetsType.FileSec.FileGrp fileGrp : fileGrps) {
      final QName keyContentInformationType = new QName(HTTPS_DILCIS_EU_XML_METS_CSIPEXTENSION_METS,
        "CONTENTINFORMATIONTYPE", "csip");
      final String contentInformationType = fileGrp.getOtherAttributes().get(keyContentInformationType);
      if (contentInformationType != null && contentInformationType.equals("OTHER")) {
        final QName keyOtherContentInformationType = new QName(HTTPS_DILCIS_EU_XML_METS_CSIPEXTENSION_METS,
          "OTHERCONTENTINFORMATIONTYPE", "csip");
        final String otherContentInformationType = fileGrp.getOtherAttributes().get(keyOtherContentInformationType);
        if (otherContentInformationType == null) {
          return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
            Message.createErrorMessage(
              "In %1$s mets/fileSec/fileGrp/@csip:CONTENTINFORMATIONTYPE " + "have the value OTHER mets/fileSec"
                + "/fileGrp[@csip:CONTENTINFORMATIONTYPE='OTHER']" + "/@csip:OTHERCONTENTINFORMATIONTYPE can't be null",
              metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
            false, false);
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
  private ReporterDetails validateCSIP64(final StructureValidatorState structureValidatorState,
    final MetsValidatorState metsValidatorState) throws IOException {
    final List<MetsType.FileSec.FileGrp> fileGrps = metsValidatorState.getMets().getFileSec().getFileGrp();
    final List<String> tmp = new ArrayList<>();
    tmp.add("Schemas");
    tmp.add("Documentation");
    tmp.add("Representations");
    final StringBuilder message = new StringBuilder();
    for (MetsType.FileSec.FileGrp fileGrp : fileGrps) {
      final String use = fileGrp.getUSE();
      if (use != null) {
        if (!tmp.contains(use)) {
          if (fileGrp.getFile().isEmpty()) {
            if (structureValidatorState.isZipFileFlag()) {
              final String expr;
              if (metsValidatorState.isRootMets()) {
                expr = metsValidatorState.getMets().getOBJID() + Constants.SEPARATOR + use.toLowerCase();
              } else {
                expr = metsValidatorState.getMetsPath() + use.toLowerCase();
              }
              if (!structureValidatorState.getZipManager().checkPathIsDirectory(structureValidatorState.getIpPath(),
                expr)) {
                message.append("Value ").append(use)
                  .append(" in %1$s for mets/fileSec/fileGrp/@USE " + "doesn't match with any directory in sip(")
                  .append(expr).append(")");
                return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, Message.createErrorMessage(
                  message.toString(), metsValidatorState.getMetsName(), metsValidatorState.isRootMets()), false, false);
              }
            } else {
              if (!structureValidatorState.getFolderManager()
                .checkDirectory(Paths.get(metsValidatorState.getMetsPath()).resolve(use.toLowerCase()))) {
                message.append("Value ").append(use)
                  .append(" in %1$s for mets/fileSec/fileGrp/@USE " + "doesn't match with any directory in sip(")
                  .append(Paths.get(metsValidatorState.getMetsPath()).resolve(use.toLowerCase())).append(")");
                return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, Message.createErrorMessage(
                  message.toString(), metsValidatorState.getMetsName(), metsValidatorState.isRootMets()), false, false);
              }
            }
          }
        }
      } else {
        return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
          Message.createErrorMessage("mets/fileSec/fileGrp/@USE in %1$s can't be null",
            metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
          false, false);
      }
    }
    return new ReporterDetails();
  }

  /*
   * mets/fileSec/fileGrp/@ID An xml:id identifier for the file group used for
   * internal package references. It must be unique within the package.
   */
  private ReporterDetails validateCSIP65(final MetsValidatorState metsValidatorState) {
    final MetsType.FileSec fileSec = metsValidatorState.getMets().getFileSec();
    final List<MetsType.FileSec.FileGrp> fileGrp = fileSec.getFileGrp();
    for (MetsType.FileSec.FileGrp grp : fileGrp) {
      final String id = grp.getID();
      if (id != null) {
        if (!metsValidatorState.checkMetsInternalId(id)) {
          metsValidatorState.addMetsInternalId(id);
        } else {
          final StringBuilder message = new StringBuilder();
          message.append("Value ").append(id)
            .append(" in %1$s for mets/fileSec/fileGrp/@ID isn't unique in the package");
          return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, Message.createErrorMessage(
            message.toString(), metsValidatorState.getMetsName(), metsValidatorState.isRootMets()), false, false);
        }
      } else {
        return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
          Message.createErrorMessage("mets/fileSec/fileGrp/@ID in %1$s can't be null", metsValidatorState.getMetsName(),
            metsValidatorState.isRootMets()),
          false, false);
      }
    }
    return new ReporterDetails();
  }

  /*
   * mets/fileSec/fileGrp/file The file group ( <fileGrp> ) contains the file
   * elements which describe the file objects.
   */
  private ReporterDetails validateCSIP66(final StructureValidatorState structureValidatorState,
    final MetsValidatorState metsValidatorState) throws IOException {

    final MetsType.FileSec fileSec = metsValidatorState.getMets().getFileSec();
    final List<MetsType.FileSec.FileGrp> fileGrp = fileSec.getFileGrp();
    for (MetsType.FileSec.FileGrp grp : fileGrp) {
      final List<FileType> fileTypes = grp.getFile();
      if (!fileTypes.isEmpty()) {
        for (FileType file : fileTypes) {
          final List<FileType.FLocat> fLocats = file.getFLocat();
          if (!fLocats.isEmpty()) {
            for (FileType.FLocat fLocat : fLocats) {
              final String hrefDecoded = URLDecoder.decode(fLocat.getHref(), UTF_8);
              final StringBuilder filePath = new StringBuilder();
              if (structureValidatorState.isZipFileFlag()) {
                if (metsValidatorState.isRootMets()) {
                  filePath.append(metsValidatorState.getMets().getOBJID()).append(Constants.SEPARATOR).append(hrefDecoded);
                } else {
                  filePath.append(metsValidatorState.getMetsPath()).append(hrefDecoded);
                }
              } else {
                filePath.append(Paths.get(metsValidatorState.getMetsPath()).resolve(hrefDecoded));
              }
              if (metsValidatorState.getMetsFiles().containsKey(filePath.toString())) {
                metsValidatorState.getMetsFiles().replace(filePath.toString(), true);
              }
            }
          }
        }
      }
    }
    if (metsValidatorState.getMetsFiles().containsValue(false) && metsValidatorState.isRootMets()) {
      return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
        Message.createErrorMessage("You have files in SIP does not referenced in %1$s",
          metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
        false, false);
    }
    return new ReporterDetails();
  }

  /*
   * mets/fileSec/fileGrp/file/@ID A unique xml:id identifier for this file across
   * the package.
   */
  private ReporterDetails validateCSIP67(final MetsValidatorState metsValidatorState) {
    final MetsType.FileSec fileSec = metsValidatorState.getMets().getFileSec();
    final List<MetsType.FileSec.FileGrp> fileGrp = fileSec.getFileGrp();
    for (MetsType.FileSec.FileGrp grp : fileGrp) {
      final List<FileType> files = grp.getFile();
      for (FileType file : files) {
        final String id = file.getID();
        if (id != null) {
          if (!metsValidatorState.checkMetsInternalId(id)) {
            metsValidatorState.addMetsInternalId(id);
          } else {
            final StringBuilder message = new StringBuilder();
            message.append("Value ").append(id)
              .append(" in %1$s for mets/fileSec/fileGrp/@ID isn't unique in the package");
            return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, Message.createErrorMessage(
              message.toString(), metsValidatorState.getMetsName(), metsValidatorState.isRootMets()), false, false);
          }
        } else {
          return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
            Message.createErrorMessage("mets/fileSec/fileGrp/file/@ID in %1$s can't be null",
              metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
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
  private ReporterDetails validateCSIP68(final MetsValidatorState metsValidatorState) {
    final MetsType.FileSec fileSec = metsValidatorState.getMets().getFileSec();
    final List<MetsType.FileSec.FileGrp> fileGrp = fileSec.getFileGrp();
    for (MetsType.FileSec.FileGrp grp : fileGrp) {
      final List<FileType> files = grp.getFile();
      for (FileType file : files) {
        final String mimeType = file.getMIMETYPE();
        if (mimeType != null) {
          if (!IanaMediaTypes.getIanaMediaTypesList().contains(mimeType)) {
            final StringBuilder message = new StringBuilder();
            message.append("Value ").append(mimeType)
              .append(" in %1$s for mets/fileSec/fileGrp/file/@MIMETYPE value isn't valid");
            return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, Message.createErrorMessage(
              message.toString(), metsValidatorState.getMetsName(), metsValidatorState.isRootMets()), false, false);
          }
        } else {
          return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
            Message.createErrorMessage("mets/fileSec/fileGrp/file/@MIMETYPE of file in %1$s can't be null",
              metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
            false, false);
        }
      }
    }
    return new ReporterDetails();
  }

  /*
   * mets/fileSec/fileGrp/file/@SIZE Size of the referenced file in bytes.
   */
  private ReporterDetails validateCSIP69(final StructureValidatorState structureValidatorState,
    final MetsValidatorState metsValidatorState) throws IOException {
    final MetsType.FileSec fileSec = metsValidatorState.getMets().getFileSec();
    final StringBuilder message = new StringBuilder();
    final List<MetsType.FileSec.FileGrp> fileGrp = fileSec.getFileGrp();
    for (MetsType.FileSec.FileGrp grp : fileGrp) {
      final List<FileType> files = grp.getFile();
      for (FileType file : files) {
        final List<FileType.FLocat> flocat = file.getFLocat();
        if (flocat != null) {
          if (flocat.size() == 1) {
            final String href = URLDecoder.decode(flocat.get(0).getHref(), UTF_8);
            if (href != null) {
              final Long size = file.getSIZE();
              if (size != null) {
                if (structureValidatorState.isZipFileFlag()) {
                  final StringBuilder filePath = new StringBuilder();
                  if (metsValidatorState.isRootMets()) {
                    filePath.append(metsValidatorState.getMets().getOBJID()).append(Constants.SEPARATOR).append(href);
                  } else {
                    filePath.append(metsValidatorState.getMetsPath()).append(href);
                  }
                  if (!structureValidatorState.getZipManager().verifySize(structureValidatorState.getIpPath(),
                    filePath.toString(), size)) {
                    message.append("mets/dmdSec/mdRef/@SIZE ").append(size).append(" in %1$s and size of file (")
                      .append(filePath).append(") isn't equal");
                    return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
                      Message.createErrorMessage(message.toString(), metsValidatorState.getMetsName(),
                        metsValidatorState.isRootMets()),
                      false, false);
                  }
                } else {
                  if (metsValidatorState.isRootMets()) {
                    if (!structureValidatorState.getFolderManager()
                      .verifySize(structureValidatorState.getIpPath().resolve(href), size)) {
                      message.append("mets/dmdSec/mdRef/@SIZE ").append(size).append(" in %1$s and size of file (")
                        .append(structureValidatorState.getIpPath()
                          .resolve(structureValidatorState.getIpPath().resolve(href)))
                        .append(") isn't equal");
                      return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
                        Message.createErrorMessage(message.toString(), metsValidatorState.getMetsName(),
                          metsValidatorState.isRootMets()),
                        false, false);
                    }
                  } else {
                    if (!structureValidatorState.getFolderManager()
                      .verifySize(Paths.get(metsValidatorState.getMetsPath()).resolve(href), size)) {
                      message.append("mets/dmdSec/mdRef/@SIZE ").append(size).append(" in %1$s and size of file (")
                        .append(structureValidatorState.getIpPath()
                          .resolve(Paths.get(metsValidatorState.getMetsPath()).resolve(href)))
                        .append(") isn't equal");
                      return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
                        Message.createErrorMessage(message.toString(), metsValidatorState.getMetsName(),
                          metsValidatorState.isRootMets()),
                        false, false);
                    }
                  }
                }
              } else {
                return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
                  Message.createErrorMessage("mets/fileSec/fileGrp/file/@SIZE in %1$s can't be null",
                    metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
                  false, false);
              }
            } else {
              return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
                Message.createErrorMessage("mets/fileSec/fileGrp/file/flocat/@href in %1$s can't be null",
                  metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
                false, false);
            }
          } else {
            return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
              Message.createErrorMessage("cannot have more than one mets/fileSec/fileGrp/file/flocat in %1$s",
                metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
              false, false);
          }
        } else {
          return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
            Message.createErrorMessage("mets/fileSec/fileGrp/file/flocat in %1$s can't be null",
              metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
            false, false);
        }
      }
    }
    return new ReporterDetails();
  }

  /*
   * mets/fileSec/fileGrp/file/@CREATED Creation date of the referenced file.
   */
  private ReporterDetails validateCSIP70(final MetsValidatorState metsValidatorState) {
    final MetsType.FileSec fileSec = metsValidatorState.getMets().getFileSec();
    final List<MetsType.FileSec.FileGrp> fileGrp = fileSec.getFileGrp();
    for (MetsType.FileSec.FileGrp grp : fileGrp) {
      final List<FileType> files = grp.getFile();
      for (FileType file : files) {
        if (file.getCREATED() == null) {
          return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
            Message.createErrorMessage("mets/fileSec/fileGrp/file/@CREATED in %1$s can't be null",
              metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
            false, false);
        }
      }
    }
    return new ReporterDetails();
  }

  /*
   * mets/fileSec/fileGrp/file/@CHECKSUM The checksum of the referenced file.
   */
  private ReporterDetails validateCSIP71(final StructureValidatorState structureValidatorState,
    final MetsValidatorState metsValidatorState) throws IOException, NoSuchAlgorithmException {
    final List<String> tmp = new ArrayList<>();
    for (CHECKSUMTYPE check : CHECKSUMTYPE.values()) {
      tmp.add(check.toString());
    }
    final StringBuilder message = new StringBuilder();
    final MetsType.FileSec fileSec = metsValidatorState.getMets().getFileSec();
    final List<MetsType.FileSec.FileGrp> fileGrp = fileSec.getFileGrp();
    for (MetsType.FileSec.FileGrp grp : fileGrp) {
      final List<FileType> files = grp.getFile();
      for (FileType file : files) {
        final String checksumType = file.getCHECKSUMTYPE();
        if (checksumType == null) {
          return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
            Message.createErrorMessage("mets/fileSec/fileGrp/file/@CHECKSUMTYPE in %1$s can't be null",
              metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
            false, false);
        } else {
          if (!tmp.contains(checksumType)) {
            message.append("Value ").append(checksumType)
              .append(" in %1$s for mets/fileSec/fileGrp/file/@CHECKSUMTYPE isn't valid");
            return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, Message.createErrorMessage(
              message.toString(), metsValidatorState.getMetsName(), metsValidatorState.isRootMets()), false, false);
          } else {
            final String checksum = file.getCHECKSUM();
            if (checksum == null) {
              return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
                Message.createErrorMessage("mets/fileSec/fileGrp/file/@CHECKSUM in %1$s can't be null",
                  metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
                false, false);
            } else {
              final String href = file.getFLocat().get(0).getHref();
              if (href == null) {
                return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
                  Message.createErrorMessage("mets/fileSec/fileGrp/file/flocat/href in %1$s can't be null",
                    metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
                  false, false);
              } else {
                final String filePath = URLDecoder.decode(href, UTF_8);
                if (structureValidatorState.isZipFileFlag()) {
                  final StringBuilder finalPath = new StringBuilder();
                  if (!metsValidatorState.isRootMets()) {
                    finalPath.append(metsValidatorState.getMetsPath()).append(filePath);
                  } else {
                    finalPath.append(metsValidatorState.getMets().getOBJID()).append(Constants.SEPARATOR).append(filePath);
                  }
                  if (!structureValidatorState.getZipManager().verifyChecksum(structureValidatorState.getIpPath(),
                    finalPath.toString(), checksumType, checksum)) {
                    message.append("mets/dmdSec/mdRef/@CHECKSUM ").append(checksum)
                      .append(" in %1$s and checksum of file (").append(finalPath).append(") isn't equal");
                    return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
                      Message.createErrorMessage(message.toString(), metsValidatorState.getMetsName(),
                        metsValidatorState.isRootMets()),
                      false, false);
                  }
                } else {
                  if (!structureValidatorState.getFolderManager().verifyChecksum(
                    Paths.get(metsValidatorState.getMetsPath()).resolve(filePath), checksumType, checksum)) {
                    message.append("mets/dmdSec/mdRef/@CHECKSUM ").append(checksum)
                      .append(" in %1$s and checksum of file (")
                      .append(Paths.get(metsValidatorState.getMetsPath()).resolve(filePath)).append(") isn't equal");
                    return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
                      Message.createErrorMessage(message.toString(), metsValidatorState.getMetsName(),
                        metsValidatorState.isRootMets()),
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
   * mets/fileSec/fileGrp/file/@CHECKSUMTYPE The type of checksum following the
   * value list present in the METS-standard which has been used for calculating
   * the checksum for the referenced file.
   */
  private ReporterDetails validateCSIP72(final MetsValidatorState metsValidatorState) {
    final List<String> tmp = new ArrayList<>();
    for (CHECKSUMTYPE check : CHECKSUMTYPE.values()) {
      tmp.add(check.toString());
    }
    final MetsType.FileSec fileSec = metsValidatorState.getMets().getFileSec();
    final List<MetsType.FileSec.FileGrp> fileGrp = fileSec.getFileGrp();
    for (MetsType.FileSec.FileGrp grp : fileGrp) {
      final List<FileType> files = grp.getFile();
      for (FileType file : files) {
        final String checksumType = file.getCHECKSUMTYPE();
        if (checksumType == null) {
          return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
            Message.createErrorMessage("mets/fileSec/fileGrp/file/@CHECKSUMTYPE in %1$s can't be null",
              metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
            false, false);
        } else {
          if (!tmp.contains(checksumType)) {
            final StringBuilder message = new StringBuilder();
            message.append("Value ").append(checksumType)
              .append(" in %1$s for mets/fileSec/fileGrp/file/@CHECKSUMTYPE isn't valid");
            return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, Message.createErrorMessage(
              message.toString(), metsValidatorState.getMetsName(), metsValidatorState.isRootMets()), false, false);
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
  private ReporterDetails validateCSIP73(final MetsValidatorState metsValidatorState) {
    final MetsType.FileSec fileSec = metsValidatorState.getMets().getFileSec();
    final List<MetsType.FileSec.FileGrp> fileGrp = fileSec.getFileGrp();
    for (MetsType.FileSec.FileGrp grp : fileGrp) {
      final List<FileType> files = grp.getFile();
      for (FileType file : files) {
        final String ownerID = file.getOWNERID();
        if (ownerID != null) {
          return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
            Message.createErrorMessage("A owner identifier was defined in %1$s", metsValidatorState.getMetsName(),
              metsValidatorState.isRootMets()),
            false, false);
        }
      }
    }
    return new ReporterDetails();
  }

  /**
   * mets/fileSec/fileGrp/file/@ADMID If administrative metadata has been provided
   * for the file this attribute refers to the file’s administrative metadata by
   * ID.
   *
   * @param metsValidatorState
   *          the contextual METS validator state
   * @return the result of the validation
   */
  private ReporterDetails validateCSIP74(final MetsValidatorState metsValidatorState) {
    final List<MetsType.FileSec.FileGrp> fileGrps = metsValidatorState.getMets().getFileSec().getFileGrp();
    final List<AmdSecType> amdSec = metsValidatorState.getMets().getAmdSec();

    // Get all identifiers for DigiprovMD
    final List<String> amdIds = amdSec.stream().map(AmdSecType::getDigiprovMD).flatMap(List::stream)
      .filter(dp -> dp.getMdRef() != null).map(dp -> dp.getMdRef().getID()).collect(Collectors.toList());

    // Get all file ADMIDs that are NOT in the list of DigiprovMD identifiers
    final List<String> admidsNotInAmd = fileGrps.stream().map(FileGrpType::getFile).flatMap(List::stream)
      .map(FileType::getADMID).flatMap(List::stream).filter(MdSecType.class::isInstance).map(MdSecType.class::cast)
      .filter(md -> md.getMdRef() != null).map(md -> md.getMdRef().getID()).distinct()
      .filter(admid -> !amdIds.contains(admid)).collect(Collectors.toList());

    // Report only valid if all ADMIDs are in DigiprovMD identifiers
    final ReporterDetails r = new ReporterDetails();
    r.setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION);
    r.setSkipped(false);
    r.setValid(admidsNotInAmd.isEmpty());

    // for each ADMID not in the DigiprovMD create an issue
    for (String admid : admidsNotInAmd) {
      final StringBuilder message = new StringBuilder();
      message.append("Value ").append(admid).append(
        " in %1$s for mets/fileSec/fileGrp/file/@ADMID " + "does not match with any mets/amdSec/digiprovMd/@ID");
      r.addIssue(Message.createErrorMessage(message.toString(), metsValidatorState.getMetsName(),
        metsValidatorState.isRootMets()));
    }

    return r;
  }

  /*
   * mets/fileSec/fileGrp/file/@DMDID If descriptive metadata has been provided
   * per file this attribute refers to the file’s descriptive metadata by ID.
   */
  private ReporterDetails validateCSIP75(final MetsValidatorState metsValidatorState) {
    final List<MetsType.FileSec.FileGrp> fileGrps = metsValidatorState.getMets().getFileSec().getFileGrp();
    final List<MdSecType> dmdSec = metsValidatorState.getMets().getDmdSec();
    for (MetsType.FileSec.FileGrp fileGrp : fileGrps) {
      final List<FileType> files = fileGrp.getFile();
      for (FileType file : files) {
        final List<Object> dmdids = file.getDMDID();
        if (dmdids != null && !dmdids.isEmpty()) {
          boolean found = false;
          for (Object o : dmdids) {
            final MdSecType mdSecType = (MdSecType) o;
            final String dmdid = mdSecType.getMdRef().getID();
            for (MdSecType md : dmdSec) {
              final String id = md.getMdRef().getID();
              if (id != null && dmdid.equals(id)) {
                found = true;
                break;
              }
            }
            if (!found) {
              final StringBuilder message = new StringBuilder();
              message.append("Value ").append(dmdid).append(
                " in %1$s for mets/fileSec/fileGrp/file/@DMDID " + "does not match with any mets/dmdSec/mdRef/@ID");
              return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, Message.createErrorMessage(
                message.toString(), metsValidatorState.getMetsName(), metsValidatorState.isRootMets()), false, false);
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
  private ReporterDetails validateCSIP76(final MetsValidatorState metsValidatorState) {
    final MetsType.FileSec fileSec = metsValidatorState.getMets().getFileSec();
    final List<MetsType.FileSec.FileGrp> fileGrp = fileSec.getFileGrp();
    for (MetsType.FileSec.FileGrp grp : fileGrp) {
      final List<FileType> files = grp.getFile();
      for (FileType file : files) {
        final List<FileType.FLocat> flocat = file.getFLocat();
        if (flocat == null) {
          return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
            Message.createErrorMessage("mets/fileSec/fileGrp/file/FLocat in %1$s can't be null",
              metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
            false, false);
        } else {
          if (flocat.size() != 1) {
            return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
              Message.createErrorMessage("Can't have more than one mets/fileSec/fileGrp/file/FLocat in %1$s",
                metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
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
  private ReporterDetails validateCSIP77(final MetsValidatorState metsValidatorState) {
    final MetsType.FileSec fileSec = metsValidatorState.getMets().getFileSec();
    final List<MetsType.FileSec.FileGrp> fileGrp = fileSec.getFileGrp();
    for (MetsType.FileSec.FileGrp grp : fileGrp) {
      final List<FileType> files = grp.getFile();
      for (FileType file : files) {
        final List<FileType.FLocat> flocat = file.getFLocat();
        if (flocat == null) {
          return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
            Message.createErrorMessage("mets/fileSec/fileGrp/file/FLocat in %1$s can't be null",
              metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
            false, false);
        } else {
          for (FileType.FLocat floc : flocat) {
            final String loctype = floc.getLOCTYPE();
            if (loctype == null) {
              return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
                Message.createErrorMessage("mets/fileSec/fileGrp/file/FLocat[@LOCTYPE=’URL’] in %1$s can't be null",
                  metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
                false, false);
            } else {
              if (!loctype.equals("URL")) {
                return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
                  Message.createErrorMessage(
                    "mets/fileSec/fileGrp/file/" + "FLocat[@LOCTYPE=’URL’] value in %1$s has to be URL ",
                    metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
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
  private ReporterDetails validateCSIP78(final StructureValidatorState structureValidatorState,
    final MetsValidatorState metsValidatorState) throws IOException {
    final MetsType.FileSec fileSec = metsValidatorState.getMets().getFileSec();
    final List<MetsType.FileSec.FileGrp> fileGrp = fileSec.getFileGrp();
    final HashMap<String, String> fileSecTypes = new HashMap<>();
    final MetsHandler fileSecHandler = new MetsHandler("file", "FLocat", fileSecTypes);
    final MetsParser metsParser = new MetsParser();
    InputStream metsStream = null;
    if (!fileGrp.isEmpty()) {
      if (structureValidatorState.isZipFileFlag()) {
        if (metsValidatorState.isRootMets()) {
          metsStream = structureValidatorState.getZipManager()
            .getMetsRootInputStream(structureValidatorState.getIpPath());
        } else {
          metsStream = structureValidatorState.getZipManager().getZipInputStream(structureValidatorState.getIpPath(),
            metsValidatorState.getMetsPath() + "METS.xml");
        }
      } else {
        if (metsValidatorState.isRootMets()) {
          metsStream = structureValidatorState.getFolderManager()
            .getMetsRootInputStream(structureValidatorState.getIpPath());
        } else {
          metsStream = structureValidatorState.getFolderManager()
            .getInputStream(Paths.get(metsValidatorState.getMetsPath()).resolve("METS.xml"));
        }
      }
    }
    if (metsStream != null) {
      metsParser.parse(fileSecHandler, metsStream);
    }

    for (MetsType.FileSec.FileGrp grp : fileGrp) {
      final List<FileType> files = grp.getFile();
      for (FileType file : files) {
        final List<FileType.FLocat> flocat = file.getFLocat();
        if (flocat.isEmpty()) {
          return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
            Message.createErrorMessage("mets/fileSec/fileGrp/file/FLocat in %1$s can't be null",
              metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
            false, false);
        } else {
          for (FileType.FLocat floc : flocat) {
            if (fileSecTypes.get(floc.getHref()) == null) {
              return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
                Message.createErrorMessage(
                  "mets/fileSec/fileGrp/file/FLocat[@xlink:type=’simple’] " + "in %1$s can't be null",
                  metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
                false, false);
            } else {
              if (!fileSecTypes.get(floc.getHref()).equals("simple")) {
                return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
                  Message.createErrorMessage(
                    "mets/fileSec/fileGrp/file/FLocat[@xlink:type=’simple’] " + "value in %1$s has to be simple",
                    metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
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
  private ReporterDetails validateCSIP79(final StructureValidatorState structureValidatorState,
    final MetsValidatorState metsValidatorState) throws IOException {
    final MetsType.FileSec fileSec = metsValidatorState.getMets().getFileSec();
    final List<MetsType.FileSec.FileGrp> fileGrp = fileSec.getFileGrp();
    final StringBuilder message = new StringBuilder();
    for (MetsType.FileSec.FileGrp grp : fileGrp) {
      final List<FileType> files = grp.getFile();
      for (FileType file : files) {
        final List<FileType.FLocat> flocat = file.getFLocat();
        if (flocat == null) {
          return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
            Message.createErrorMessage("mets/fileSec/fileGrp/file/FLocat in %1$s can't be null",
              metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
            false, false);
        } else {
          for (FileType.FLocat floc : flocat) {
            final String href = floc.getHref();
            if (href != null) {
              final String hrefDecoded = URLDecoder.decode(href, UTF_8);
              if (structureValidatorState.isZipFileFlag()) {
                final StringBuilder finalPath = new StringBuilder();
                if (!metsValidatorState.isRootMets()) {
                  finalPath.append(metsValidatorState.getMetsPath()).append(hrefDecoded);
                } else {
                  finalPath.append(metsValidatorState.getMets().getOBJID()).append(Constants.SEPARATOR).append(hrefDecoded);
                }
                if (!structureValidatorState.getZipManager().checkPathExists(structureValidatorState.getIpPath(),
                  finalPath.toString())) {
                  message.append("mets/fileSec/fileGrp/file/@xlink:href ").append(finalPath)
                    .append(" does not exist (%1$s)");
                  return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
                    Message.createErrorMessage(message.toString(), metsValidatorState.getMetsName(),
                      metsValidatorState.isRootMets()),
                    false, false);
                }
              } else {
                if (!structureValidatorState.getFolderManager()
                  .checkPathExists(Paths.get(metsValidatorState.getMetsPath()).resolve(hrefDecoded))) {
                  message.append("mets/fileSec/fileGrp/file/@xlink:href ")
                    .append(Paths.get(metsValidatorState.getMetsPath()).resolve(hrefDecoded))
                    .append(" does not exist (%1$s)");
                  return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
                    Message.createErrorMessage(message.toString(), metsValidatorState.getMetsName(),
                      metsValidatorState.isRootMets()),
                    false, false);
                }
              }
            } else {
              return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
                Message.createErrorMessage("mets/fileSec/fileGrp/file/FLocat/@xlink:href in %1$s can't be null",
                  metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
                false, false);
            }
          }
        }
      }
    }
    return new ReporterDetails();
  }
}
