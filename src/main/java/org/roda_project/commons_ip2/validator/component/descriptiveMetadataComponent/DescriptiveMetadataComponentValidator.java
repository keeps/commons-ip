package org.roda_project.commons_ip2.validator.component.descriptiveMetadataComponent;

import org.apache.commons.lang3.StringUtils;
import org.roda_project.commons_ip2.mets_v1_12.beans.AmdSecType;
import org.roda_project.commons_ip2.mets_v1_12.beans.MdSecType;
import org.roda_project.commons_ip2.mets_v1_12.beans.Mets;
import org.roda_project.commons_ip2.utils.IanaMediaTypes;
import org.roda_project.commons_ip2.validator.common.ControlledVocabularyParser;
import org.roda_project.commons_ip2.validator.common.FolderManager;
import org.roda_project.commons_ip2.validator.common.MetsParser;
import org.roda_project.commons_ip2.validator.common.ZipManager;
import org.roda_project.commons_ip2.validator.component.MetsValidatorImpl;
import org.roda_project.commons_ip2.validator.constants.Constants;
import org.roda_project.commons_ip2.validator.constants.ConstantsCSIPspec;
import org.roda_project.commons_ip2.validator.handlers.MetsHandler;
import org.roda_project.commons_ip2.validator.reporter.ReporterDetails;
import org.roda_project.commons_ip2.validator.state.MetsValidatorState;
import org.roda_project.commons_ip2.validator.state.StructureValidatorState;
import org.roda_project.commons_ip2.validator.utils.CHECKSUMTYPE;
import org.roda_project.commons_ip2.validator.utils.Message;
import org.roda_project.commons_ip2.validator.utils.MetadataType;
import org.roda_project.commons_ip2.validator.utils.ResultsUtils;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
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

/** {@author João Gomes <jgomes@keep.pt>}. */
public class DescriptiveMetadataComponentValidator extends MetsValidatorImpl {

  /**
   * Constant encoding.
   */
  private static final String ENCODING_UTF_8 = "UTF-8";
  /**
   * {@link String}.
   */
  private final String moduleName;
  /**
   * {@link List}.
   */
  private List<MdSecType> dmdSec;
  /**
   * {@link List}.
   */
  private List<String> dmdSecStatus;
  /**
   * {@link Map}.
   */
  private HashMap<String, String> dmdSecType;

  /**
   * Initialize all objects needed to validation of this component.
   *
   * @throws IOException
   *           if some I/O error occurs.
   * @throws ParserConfigurationException
   *           if some error occurs.
   * @throws SAXException
   *           if some error occurs.
   */
  public DescriptiveMetadataComponentValidator() throws IOException, ParserConfigurationException, SAXException {
    moduleName = Constants.CSIP_MODULE_NAME_3;
    this.dmdSecStatus = ControlledVocabularyParser.parse(Constants.PATH_RESOURCES_CSIP_VOCABULARY_DMD_SEC_STATUS);
  }

  @Override
  public Map<String, ReporterDetails> validate(final StructureValidatorState structureValidatorState,
    final MetsValidatorState metsValidatorState) throws IOException {
    this.dmdSec = metsValidatorState.getMets().getDmdSec();
    ReporterDetails csip;
    final Map<String, ReporterDetails> results = new HashMap<>();

    /* CSIP17 */
    notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP17_ID);
    ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP17_ID,
      validateCSIP17(structureValidatorState, metsValidatorState)
        .setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION));

    /* CSIP18 */
    notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP18_ID);
    ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP18_ID,
      validateCSIP18(metsValidatorState).setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION));

    /* CSIP19 */
    notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP19_ID);
    ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP19_ID,
      validateCSIP19(metsValidatorState).setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION));

    /* CSIP20 */
    notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP20_ID);
    ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP20_ID,
      validateCSIP20(metsValidatorState).setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION));

    /* CSIP21 */
    notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP21_ID);
    ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP21_ID,
      validateCSIP21(metsValidatorState).setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION));

    if (ResultsUtils.isResultValid(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP21_ID)) {
      /* CSIP22 */
      notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP22_ID);
      ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP22_ID,
        validateCSIP22(metsValidatorState).setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION));

      /* CSIP23 */
      notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP23_ID);
      ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP23_ID,
        validateCSIP23(structureValidatorState, metsValidatorState)
          .setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION));

      /* CSIP24 */
      notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP24_ID);
      ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP24_ID,
        validateCSIP24(structureValidatorState, metsValidatorState)
          .setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION));

      /* CSIP25 */
      notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP25_ID);
      ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP25_ID,
        validateCSIP25(metsValidatorState).setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION));

      /* CSIP26 */
      notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP26_ID);
      ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP26_ID,
        validateCSIP26(metsValidatorState).setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION));

      /* CSIP27 */
      notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP27_ID);
      ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP27_ID,
        validateCSIP27(structureValidatorState, metsValidatorState)
          .setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION));

      /* CSIP28 */
      notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP28_ID);
      ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP28_ID,
        validateCSIP28(metsValidatorState).setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION));

      /* CSIP29 */
      notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP29_ID);
      try {
        csip = validateCSIP29(structureValidatorState, metsValidatorState)
          .setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION);
      } catch (final Exception e) {
        csip = new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
          Message.createErrorMessage("Can't calculate checksum of file %1$s", metsValidatorState.getMetsName(),
            metsValidatorState.isRootMets()),
          false, false);
      }
      ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP29_ID, csip);

      /* CSIP30 */
      notifyObserversValidationStarted(moduleName, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP30_ID);
      ResultsUtils.addResult(results, ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP30_ID,
        validateCSIP30(metsValidatorState).setSpecification(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION));

    } else {
      final String message = Message.createErrorMessage("SKIPPED in %1$s because mets/dmdSec/mdRef doesn't exist",
        metsValidatorState.getMetsName(), metsValidatorState.isRootMets());

      ResultsUtils.addResults(results,
        new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, message, true, true),
        ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP22_ID,
        ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP23_ID,
        ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP24_ID,
        ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP25_ID,
        ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP26_ID,
        ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP27_ID,
        ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP28_ID,
        ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP29_ID,
        ConstantsCSIPspec.VALIDATION_REPORT_SPECIFICATION_CSIP30_ID);
    }

    notifyObserversFinishModule(moduleName);
    return results;
  }

  /*
   * mets/dmdSec Must be used if descriptive metadata for the package content is
   * available. Each descriptive metadata section ( <dmdSec> ) contains a single
   * description and must be repeated for multiple descriptions, when available.
   * It is possible to transfer metadata in a package using just the descriptive
   * metadata section and/or administrative metadata section.
   */

  private ReporterDetails validateCSIP17(final StructureValidatorState structureValidatorState,
    final MetsValidatorState metsValidatorState) throws IOException {
    final Mets mets = metsValidatorState.getMets();
    if (structureValidatorState.isZipFileFlag()) {
      final String regex;
      if (metsValidatorState.isRootMets()) {
        final String objectid = metsValidatorState.getMets().getOBJID();
        if (objectid != null) {
          regex = objectid + "/metadata/descriptive/.*";
        } else {
          return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
            Message.createErrorMessage("mets/@OBJECTID in %1$s can't be null", metsValidatorState.getMetsName(),
              metsValidatorState.isRootMets()),
            false, false);
        }
      } else {
        regex = metsValidatorState.getMetsPath() + "/metadata/descriptive/.*";
      }
      final ZipManager zipManager = structureValidatorState.getZipManager();

      if (dmdSec == null || dmdSec.isEmpty()) {
        if (zipManager.countMetadataFiles(structureValidatorState.getIpPath(), regex) != 0) {
          return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
            Message.createErrorMessage(
              "You have files in the metadata/descriptive/folder, " + "you must have mets/dmdSec in %1$s",
              metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
            false, false);
        }
      } else {
        if (zipManager.countMetadataFiles(structureValidatorState.getIpPath(), regex) == 0) {
          return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
            Message.createErrorMessage(
              "Doesn't have files in metadata/descriptive folder but have "
                + "dmdSec in %1$s; Put the files under metadata folder",
              metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
            false, false);
        } else {
          Map<String, Boolean> metadataFiles = zipManager.getMetadataFiles(structureValidatorState.getIpPath(), regex);
          metadataFiles = metadataFiles.entrySet().stream().filter(entry -> !entry.getKey().contains("/preservation/"))
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
          for (MdSecType md : dmdSec) {
            final MdSecType.MdRef mdRef = md.getMdRef();
            if (mdRef != null && mdRef.getHref() != null) {
              final String hrefDecoded = URLDecoder.decode(mdRef.getHref(), ENCODING_UTF_8);
              if (metsValidatorState.isRootMets()) {
                if (metadataFiles.containsKey(mets.getOBJID() + Constants.SEPARATOR + hrefDecoded)) {
                  metadataFiles.replace(mets.getOBJID() + Constants.SEPARATOR + hrefDecoded, true);
                }
              } else {
                final StringBuilder dmdSecFile = new StringBuilder();
                dmdSecFile.append(metsValidatorState.getMetsPath()).append(hrefDecoded);
                if (metadataFiles.containsKey(dmdSecFile.toString())) {
                  metadataFiles.replace(dmdSecFile.toString(), true);
                }
              }
            }
          }
          if (metadataFiles.containsValue(false)) {
            for (AmdSecType amd : mets.getAmdSec()) {
              final List<MdSecType> allMdSecTypes = new ArrayList<>();
              allMdSecTypes.addAll(amd.getDigiprovMD());
              allMdSecTypes.addAll(amd.getRightsMD());
              allMdSecTypes.addAll(amd.getTechMD());
              allMdSecTypes.addAll(amd.getSourceMD());
              for (MdSecType md : allMdSecTypes) {
                final MdSecType.MdRef mdRef = md.getMdRef();
                if (mdRef != null && mdRef.getHref() != null) {
                  final String hrefDecoded = URLDecoder.decode(mdRef.getHref(), ENCODING_UTF_8);
                  if (metsValidatorState.isRootMets()) {
                    if (metadataFiles.containsKey(mets.getOBJID() + Constants.SEPARATOR + hrefDecoded)) {
                      metadataFiles.replace(mets.getOBJID() + Constants.SEPARATOR + hrefDecoded, true);
                    }
                  } else {
                    final StringBuilder amdSecfile = new StringBuilder();
                    amdSecfile.append(metsValidatorState.getMetsPath()).append(hrefDecoded);
                    if (metadataFiles.containsKey(amdSecfile.toString())) {
                      metadataFiles.replace(amdSecfile.toString(), true);
                    }
                  }
                }
              }
            }
            if (metadataFiles.containsValue(false)) {
              final Map<String, Boolean> missedMetadataFiles = metadataFiles.entrySet().stream()
                .filter(entry -> !entry.getValue()).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
              final String initialMessage = "There are descriptive files not referenced: ";
              final String message;
              if (metsValidatorState.isRootMets()) {
                message = Message.createMissingFilesMessage(missedMetadataFiles, initialMessage,
                  structureValidatorState.isZipFileFlag(), metsValidatorState.getMets().getOBJID());
              } else {
                message = Message.createMissingFilesMessage(missedMetadataFiles, initialMessage,
                  structureValidatorState.isZipFileFlag(), metsValidatorState.getMetsPath());
              }
              return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
                Message.createErrorMessage(message, metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
                false, false);
            }
          }
        }
      }
    } else {
      final FolderManager folderManager = structureValidatorState.getFolderManager();
      if (mets.getDmdSec() == null || mets.getDmdSec().isEmpty()) {
        if (folderManager
          .countMetadataFiles(Paths.get(metsValidatorState.getMetsPath() + "/metadata/descriptive/")) != 0) {
          return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
            Message.createErrorMessage(
              "You have files in the metadata/descriptive/folder, " + "you must have mets/dmdSec in %1$s",
              metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
            false, false);
        }
      } else {
        if (folderManager
          .countMetadataFiles(Paths.get(metsValidatorState.getMetsPath() + "/metadata/descriptive/")) == 0) {
          return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
            Message.createErrorMessage(
              "Doesn't have files in metadata/descriptive folder but have "
                + "dmdSec in %1$s. Put the files under metadata folder",
              metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
            false, false);
        } else {
          Map<String, Boolean> metadataFiles = folderManager
            .getMetadataFiles(Paths.get(metsValidatorState.getMetsPath()));
          metadataFiles = metadataFiles.entrySet().stream().filter(entry -> !entry.getKey().contains("/preservation/"))
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
          for (MdSecType md : dmdSec) {
            final MdSecType.MdRef mdRef = md.getMdRef();
            if (mdRef != null) {
              final String hrefDecoded = URLDecoder.decode(mdRef.getHref(), ENCODING_UTF_8);
              if (hrefDecoded != null) {
                final String path = Paths.get(metsValidatorState.getMetsPath()).resolve(hrefDecoded).toString();
                if (metadataFiles.containsKey(path)) {
                  metadataFiles.replace(path, true);
                }
              }
            }
          }
          if (metadataFiles.containsValue(false)) {
            for (AmdSecType amd : mets.getAmdSec()) {
              for (MdSecType md : amd.getDigiprovMD()) {
                final MdSecType.MdRef mdRef = md.getMdRef();
                if (mdRef != null) {
                  final String hrefDecoded = URLDecoder.decode(mdRef.getHref(), ENCODING_UTF_8);
                  if (hrefDecoded != null) {
                    final String path = Paths.get(metsValidatorState.getMetsPath()).resolve(hrefDecoded).toString();
                    if (metadataFiles.containsKey(path)) {
                      metadataFiles.replace(path, true);
                    }
                  }
                }
              }
            }
          }
          if (metadataFiles.containsValue(false)) {
            final Map<String, Boolean> missedMetadataFiles = metadataFiles.entrySet().stream()
              .filter(entry -> !entry.getValue()).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
            final String initialMessage = "There are descriptive files not referenced: ";
            final String message = Message.createMissingFilesMessage(missedMetadataFiles, initialMessage,
              structureValidatorState.isZipFileFlag(), metsValidatorState.getMetsPath());
            return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
              Message.createErrorMessage(message, metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
              false, false);
          }
        }
      }
    }
    return new ReporterDetails();
  }

  /*
   * mets/dmdSec/@ID An xml:id identifier for the descriptive metadata section (
   * <dmdSec> ) used for internal package references. It must be unique within the
   * package.
   */
  private ReporterDetails validateCSIP18(final MetsValidatorState metsValidatorState) {
    if (dmdSec != null && !dmdSec.isEmpty()) {
      for (MdSecType mdSec : dmdSec) {
        if (!metsValidatorState.checkMetsInternalId(mdSec.getID())) {
          metsValidatorState.addMetsInternalId(mdSec.getID());
        } else {
          final StringBuilder message = new StringBuilder();
          message.append("Value ").append(mdSec.getID())
            .append(" in %1$s for mets/dmdSec/@ID isn't unique in the package");
          return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, Message.createErrorMessage(
            message.toString(), metsValidatorState.getMetsName(), metsValidatorState.isRootMets()), false, false);
        }
      }
    } else {
      return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION,
        Message.createErrorMessage("Skipped in %1$s because metsHdr/@dmdSec does not exists",
          metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
        true, true);
    }
    return new ReporterDetails();
  }

  /*
   * mets/dmdSec/@CREATED Creation date of the descriptive metadata in this
   * section.
   */
  private ReporterDetails validateCSIP19(final MetsValidatorState metsValidatorState) {
    if (dmdSec != null && !dmdSec.isEmpty()) {
      for (MdSecType mdSec : dmdSec) {
        if (mdSec.getCREATED() == null) {
          return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
            Message.createErrorMessage("mets/dmdSec/@CREATED in %1$s can't be null", metsValidatorState.getMetsName(),
              metsValidatorState.isRootMets()),
            false, false);
        }
      }
    } else {
      return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_SIP_VERSION,
        Message.createErrorMessage("Skipped in %1$s because metsHdr/dmdSec does not exists",
          metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
        true, true);
    }
    return new ReporterDetails();
  }

  /*
   * mets/dmdSec/@STATUS Indicates the status of the package using a fixed
   * vocabulary.See also: dmdSec status
   */
  private ReporterDetails validateCSIP20(final MetsValidatorState metsValidatorState) {
    final ReporterDetails details = new ReporterDetails();
    for (MdSecType mdSec : dmdSec) {
      final String status = mdSec.getSTATUS();
      if (status == null){
        return new ReporterDetails(
          Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
          "The mets/dmdSec/@STATUS is missing.", true, false);
      }
      else if (!dmdSecStatus.contains(status)) {
        final StringBuilder message = new StringBuilder();
        message.append("Value ").append(status).append(" in %1$s for mets/dmdSec/@STATUS isn't valid");
        details.setValid(false);
        details.addIssue(Message.createErrorMessage(message.toString(), metsValidatorState.getMetsName(),
          metsValidatorState.isRootMets()));
      }
    }
    return details;
  }

  /*
   * mets/dmdSec/mdRef Reference to the descriptive metadata file located in the
   * “metadata” section of the IP.
   */
  private ReporterDetails validateCSIP21(final MetsValidatorState metsValidatorState) {
    final ReporterDetails details = new ReporterDetails();
    if (dmdSec != null && !dmdSec.isEmpty()) {
      for (MdSecType mdSec : dmdSec) {
        final MdSecType.MdRef mdRef = mdSec.getMdRef();
        if (mdRef == null) {
          details.setValid(false);
          details.addIssue(Message.createErrorMessage(
            "In %1$s You should reference the metadata " + "file existing in the sip in mets/dmdSec/mdRef",
            metsValidatorState.getMetsName(), metsValidatorState.isRootMets()));
        }
      }
    }
    return details;
  }

  /*
   * mets/dmdSec/mdRef[@LOCTYPE=’URL’] The locator type is always used with the
   * value “URL” from the vocabulary in the attribute.
   */

  private ReporterDetails validateCSIP22(final MetsValidatorState metsValidatorState) {
    final ReporterDetails details = new ReporterDetails();
    for (MdSecType mdSec : dmdSec) {
      final MdSecType.MdRef mdRef = mdSec.getMdRef();
      final String loctype = mdRef.getLOCTYPE();
      if (loctype == null) {
        details.setValid(false);
        details.addIssue(Message.createErrorMessage("mets/dmdSec/mdRef[@LOCTYPE=’URL’] in %1$s can't be null",
          metsValidatorState.getMetsName(), metsValidatorState.isRootMets()));
      } else {
        if (!loctype.equals("URL")) {
          details.setValid(false);
          details.addIssue(Message.createErrorMessage("mets/dmdSec/mdRef[@LOCTYPE=’URL’] in %1$s value must be URL",
            metsValidatorState.getMetsName(), metsValidatorState.isRootMets()));
        }
      }
    }
    return details;
  }

  /*
   * mets/dmdSec/mdRef[@xlink:type=’simple’] Attribute used with the value
   * “simple”. Value list is maintained by the xlink standard.
   */
  private ReporterDetails validateCSIP23(final StructureValidatorState structureValidatorState,
    final MetsValidatorState metsValidatorState) throws IOException {
    dmdSecType = new HashMap<>();
    final MetsHandler dmdSecHandler = new MetsHandler("dmdSec", "mdRef", dmdSecType);
    final MetsParser metsParser = new MetsParser();
    InputStream metsStream = null;
    if (!dmdSec.isEmpty()) {
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
      metsParser.parse(dmdSecHandler, metsStream);
    }
    int numberOfMdRefs = 0;
    for (MdSecType mdSec : dmdSec) {
      if (mdSec.getMdRef() != null) {
        numberOfMdRefs++;
      }
    }
    if (dmdSecType.size() < numberOfMdRefs) {
      return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
        Message.createErrorMessage("mets/dmdSec/mdRef[@xlink:type=’simple’] in %1$s can't be null",
          metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
        false, false);
    } else {
      for (Map.Entry<String, String> entry : dmdSecType.entrySet()) {
        if (entry.getValue() == null) {
          return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
            Message.createErrorMessage("mets/dmdSec/mdRef[@xlink:type=’simple’] in %1$s can't be null",
              metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
            false, false);
        }
      }
      final Map<String, String> typesInvalid = dmdSecType.entrySet().stream()
        .filter(type -> !type.getValue().equals("simple"))
        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
      if (!typesInvalid.isEmpty()) {
        final StringBuilder message = new StringBuilder();
        message.append("Values ");
        typesInvalid.entrySet().stream().map(Map.Entry::getValue).collect(Collectors.toList())
          .forEach(type -> message.append(type).append(","));
        message.append(" in %1$s for mets/dmdSec/mdRef[@xlink:type=’simple’] isn't valid, must be 'simple'");
        return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, Message.createErrorMessage(
          message.toString(), metsValidatorState.getMetsName(), metsValidatorState.isRootMets()), false, false);
      }
    }
    return new ReporterDetails();
  }

  /*
   * mets/dmdSec/mdRef/@xlink:href The actual location of the resource. This
   * specification recommends recording a URL type filepath in this attribute.
   */
  private ReporterDetails validateCSIP24(final StructureValidatorState structureValidatorState,
    final MetsValidatorState metsValidatorState) throws IOException {
    final ReporterDetails details = new ReporterDetails();
    final StringBuilder message = new StringBuilder();
    for (MdSecType mdSec : dmdSec) {
      final MdSecType.MdRef mdRef = mdSec.getMdRef();
      final String href = mdRef.getHref();
      if (href != null) {
        final String hrefDecoded = URLDecoder.decode(href, ENCODING_UTF_8);
        if (structureValidatorState.isZipFileFlag()) {
          final StringBuilder path = new StringBuilder();
          if (metsValidatorState.isRootMets()) {
            path.append(metsValidatorState.getMets().getOBJID()).append(Constants.SEPARATOR).append(hrefDecoded);
          } else {
            path.append(metsValidatorState.getMetsPath()).append(hrefDecoded);
          }
          if (StringUtils.isBlank(href)){
            message.append("mets/dmdSec/mdRef/@xlink:href ").append(path).append(" in %1$s is empty");
            details.setValid(false);
            details.addIssue(Message.createErrorMessage(message.toString(), metsValidatorState.getMetsName(),
              metsValidatorState.isRootMets()));
          }
          if (!structureValidatorState.getZipManager().checkPathExists(structureValidatorState.getIpPath(),
            path.toString())) {
            message.append("mets/dmdSec/mdRef/@xlink:href ").append(path.toString().replace("%", "%%")).append(" in %1$s does not exist");
            details.setValid(false);
            details.addIssue(Message.createErrorMessage(message.toString(), metsValidatorState.getMetsName(),
              metsValidatorState.isRootMets()));
          }
        } else {
          if (StringUtils.isBlank(href)){
            message.append("mets/dmdSec/mdRef/@xlink:href ")
              .append(Paths.get(metsValidatorState.getMetsPath()).resolve(hrefDecoded))
              .append(" in %1$s is empty");
            details.setValid(false);
            details.addIssue(Message.createErrorMessage(message.toString(), metsValidatorState.getMetsName(),
              metsValidatorState.isRootMets()));
          }
          if (!structureValidatorState.getFolderManager()
            .checkPathExists(Paths.get(metsValidatorState.getMetsPath()).resolve(hrefDecoded))) {
            message.append("mets/dmdSec/mdRef/@xlink:href ")
              .append(Paths.get(metsValidatorState.getMetsPath()).resolve(hrefDecoded))
              .append(" in %1$s does not exist");
            details.setValid(false);
            details.addIssue(Message.createErrorMessage(message.toString(), metsValidatorState.getMetsName(),
              metsValidatorState.isRootMets()));
          }
        }
      } else {
        details.setValid(false);
        details.addIssue(Message.createErrorMessage("mets/dmdSec/mdRef/@xlink:href in %1$s can't be null",
          metsValidatorState.getMetsName(), metsValidatorState.isRootMets()));
      }
    }
    return details;
  }

  /*
   * mets/dmdSec/mdRef/@MDTYPE Specifies the type of metadata in the referenced
   * file. Values are taken from the list provided by the METS.
   */
  private ReporterDetails validateCSIP25(final MetsValidatorState metsValidatorState) {
    final ReporterDetails details = new ReporterDetails();
    final List<String> tmp = new ArrayList<>();
    for (MetadataType md : MetadataType.values()) {
      tmp.add(md.toString());
    }
    for (MdSecType mdSec : dmdSec) {
      final MdSecType.MdRef mdRef = mdSec.getMdRef();
      if (mdRef != null) {
        final String mdType = mdRef.getMDTYPE();
        if (mdType != null) {
          if (!tmp.contains(mdType)) {
            final StringBuilder message = new StringBuilder();
            message.append("Value ").append(mdType)
              .append(" in %1$s for mets/dmdSec/mdRef/@MDTYPE isn't a valid value");
            details.setValid(false);
            details.addIssue(Message.createErrorMessage(message.toString(), metsValidatorState.getMetsName(),
              metsValidatorState.isRootMets()));
          }
        } else {
          details.setValid(false);
          details.addIssue(Message.createErrorMessage("mets/dmdSec/mdRef/@MDTYPE in %1$s can't be null",
            metsValidatorState.getMetsName(), metsValidatorState.isRootMets()));
        }
      }
    }
    return details;
  }

  /*
   * mets/dmdSec/mdRef/@MIMETYPE The IANA mime type of the referenced file.See
   * also: IANA media types
   */
  private ReporterDetails validateCSIP26(final MetsValidatorState metsValidatorState) {
    for (MdSecType mdSecType : dmdSec) {
      final MdSecType.MdRef mdRef = mdSecType.getMdRef();
      final String mimetype = mdRef.getMIMETYPE();
      if (mimetype != null) {
        if (!IanaMediaTypes.getIanaMediaTypesList().contains(mimetype)) {
          final StringBuilder message = new StringBuilder();
          message.append("Value ").append(mimetype)
            .append(" in %1$s for mets/dmdSec/mdRef/@MIMETYPE value isn't valid");
          return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, Message.createErrorMessage(
            message.toString(), metsValidatorState.getMetsName(), metsValidatorState.isRootMets()), false, false);
        }
      } else {
        return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
          Message.createErrorMessage("mets/dmdSec/mdRef/@MIMETYPE in %1$s can't be null",
            metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
          false, false);
      }
    }
    return new ReporterDetails();
  }

  /*
   * mets/dmdSec/mdRef/@SIZE Size of the referenced file in bytes.
   */
  private ReporterDetails validateCSIP27(final StructureValidatorState structureValidatorState,
    final MetsValidatorState metsValidatorState) throws IOException {
    for (MdSecType mdSec : dmdSec) {
      final MdSecType.MdRef mdRef = mdSec.getMdRef();
      final String href = mdRef.getHref();
      if (href != null) {
        final String hrefDecoded = URLDecoder.decode(mdRef.getHref(), ENCODING_UTF_8);
        final Long size = mdRef.getSIZE();
        if (size != null) {
          final StringBuilder message = new StringBuilder();
          if (structureValidatorState.isZipFileFlag()) {
            final StringBuilder file = new StringBuilder();
            if (metsValidatorState.isRootMets()) {
              final String objectid = metsValidatorState.getMets().getOBJID();
              if (objectid != null) {
                file.append(objectid).append(Constants.SEPARATOR).append(hrefDecoded);
              } else {
                return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
                  Message.createErrorMessage("mets/@OBJECTID in %1$s can't be null", metsValidatorState.getMetsName(),
                    metsValidatorState.isRootMets()),
                  false, false);
              }
            } else {
              file.append(metsValidatorState.getMetsPath()).append(hrefDecoded);
            }
            if (!structureValidatorState.getZipManager().verifySize(structureValidatorState.getIpPath(),
              file.toString(), size)) {
              message.append("mets/dmdSec/mdRef/@SIZE ").append(size).append(" in %1$s and size of file (").append(file.toString().replace("%", "%%"))
                .append(") isn't equal");
              return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
                Message.createErrorMessage(message.toString(), metsValidatorState.getMetsName(),
                    metsValidatorState.isRootMets()),false, false);
            }
          } else {
            if (metsValidatorState.isRootMets()) {
              if (!structureValidatorState.getFolderManager()
                .verifySize(structureValidatorState.getIpPath().resolve(hrefDecoded), size)) {
                message.append("mets/dmdSec/mdRef/@SIZE ").append(size).append(" in %1$s and size of file (")
                  .append(structureValidatorState.getIpPath().resolve(hrefDecoded)).append(") isn't equal");
                return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, Message.createErrorMessage(
                  message.toString(), metsValidatorState.getMetsName(), metsValidatorState.isRootMets()), false, false);
              }
            } else {
              if (!structureValidatorState.getFolderManager()
                .verifySize(Paths.get(metsValidatorState.getMetsPath()).resolve(hrefDecoded), size)) {
                message.append("mets/dmdSec/mdRef/@SIZE ").append(size).append(" in %1$s and size of file (")
                  .append(structureValidatorState.getIpPath().resolve(hrefDecoded).toString()).append(") isn't equal");
                return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, Message.createErrorMessage(
                  message.toString(), metsValidatorState.getMetsName(), metsValidatorState.isRootMets()), false, false);
              }
            }
          }
        } else {
          return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
            Message.createErrorMessage("mets/dmdSec/mdRef/@SIZE in %1$s can't be null",
              metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
            false, false);
        }
      } else {
        return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
          Message.createErrorMessage("mets/dmdSec/mdRef/@href in %1$s can't be null", metsValidatorState.getMetsName(),
            metsValidatorState.isRootMets()),
          false, false);
      }
    }
    return new ReporterDetails();
  }

  /*
   * mets/dmdSec/mdRef/@CREATED The creation date of the referenced file..
   */
  private ReporterDetails validateCSIP28(final MetsValidatorState metsValidatorState) {
    final ReporterDetails details = new ReporterDetails();
    for (MdSecType mdSec : dmdSec) {
      final MdSecType.MdRef mdRef = mdSec.getMdRef();
      if (mdRef.getCREATED() == null) {
        details.setValid(false);
        details.addIssue(Message.createErrorMessage("mets/dmdSec/mdRef/@CREATED in %1$s can't be null",
          metsValidatorState.getMetsName(), metsValidatorState.isRootMets()));
      }
    }
    return details;
  }

  /*
   * mets/dmdSec/mdRef/@CHECKSUM The checksum of the referenced file.
   */
  private ReporterDetails validateCSIP29(final StructureValidatorState structureValidatorState,
    final MetsValidatorState metsValidatorState) throws IOException, NoSuchAlgorithmException {
    final List<String> tmp = new ArrayList<>();
    for (CHECKSUMTYPE check : CHECKSUMTYPE.values()) {
      tmp.add(check.toString());
    }
    for (MdSecType mdSec : dmdSec) {
      final MdSecType.MdRef mdRef = mdSec.getMdRef();
      final String checksumType = mdRef.getCHECKSUMTYPE();
      if (checksumType != null) {
        if (tmp.contains(checksumType)) {
          final String checksum = mdRef.getCHECKSUM();
          if (checksum != null) {
            final String href = mdRef.getHref();
            if (href != null) {
              final String file = URLDecoder.decode(href, ENCODING_UTF_8);
              final StringBuilder filePath = new StringBuilder();
              final StringBuilder message = new StringBuilder();
              if (structureValidatorState.isZipFileFlag()) {
                if (metsValidatorState.isRootMets()) {
                  final String objectid = metsValidatorState.getMets().getOBJID();
                  if (objectid != null) {
                    filePath.append(objectid).append(Constants.SEPARATOR).append(file);
                  } else {
                    return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
                      Message.createErrorMessage("mets/@OBJECTID in %1$s can't be null",
                        metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
                      false, false);
                  }
                } else {
                  filePath.append(metsValidatorState.getMetsPath()).append(file);
                }
                if (!structureValidatorState.getZipManager().verifyChecksum(structureValidatorState.getIpPath(),
                  filePath.toString(), checksumType, checksum)) {
                  message.append("mets/dmdSec/mdRef/@CHECKSUM ").append(checksum)
                    .append(" in %1$s and checksum of file (").append(filePath).append(") isn't equal");
                  return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
                    Message.createErrorMessage(message.toString(), metsValidatorState.getMetsName(),
                      metsValidatorState.isRootMets()),
                    false, false);
                }
              } else {
                if (!structureValidatorState.getFolderManager()
                  .verifyChecksum(Paths.get(metsValidatorState.getMetsPath()).resolve(file), checksumType, checksum)) {
                  message.append("mets/dmdSec/mdRef/@CHECKSUM ").append(checksum)
                    .append(" in %1$s and checksum of file (")
                    .append(Paths.get(metsValidatorState.getMetsPath()).resolve(file)).append(") isn't equal");
                  return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
                    Message.createErrorMessage(message.toString(), metsValidatorState.getMetsName(),
                      metsValidatorState.isRootMets()),
                    false, false);
                }
              }
            } else {
              return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
                Message.createErrorMessage("mets/dmdSec/mdRef/@href in %1$s can't be null!",
                  metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
                false, false);
            }
          } else {
            return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
              Message.createErrorMessage("mets/dmdSec/mdRef/@CHECKSUM in %1$s can't be null",
                metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
              false, false);
          }
        } else {
          final StringBuilder errorMessage = new StringBuilder();
          errorMessage.append("Value ").append(checksumType)
            .append(" in %1$s for mets/dmdSec/mdRef/@CHECKSUMTYPE isn't valid");
          return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, Message.createErrorMessage(
            errorMessage.toString(), metsValidatorState.getMetsName(), metsValidatorState.isRootMets()), false, false);
        }
      } else {
        return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
          Message.createErrorMessage("mets/dmdSec/mdRef/@CHECKSUMTYPE in %1$s can't be null",
            metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
          false, false);
      }
    }
    return new ReporterDetails();
  }

  /*
   * mets/dmdSec/mdRef/@CHECKSUMTYPE The type of checksum following the value list
   * present in the METS-standard which has been used for calculating the checksum
   * for the referenced file.
   */
  private ReporterDetails validateCSIP30(final MetsValidatorState metsValidatorState) {
    final List<String> tmp = new ArrayList<>();
    for (CHECKSUMTYPE check : CHECKSUMTYPE.values()) {
      tmp.add(check.toString());
    }
    for (MdSecType mdSec : dmdSec) {
      final MdSecType.MdRef mdRef = mdSec.getMdRef();
      final String checksumType = mdRef.getCHECKSUMTYPE();
      if (checksumType != null) {
        if (!tmp.contains(checksumType)) {
          final StringBuilder message = new StringBuilder();
          message.append("Value ").append(checksumType)
            .append(" in %1$s for mets/dmdSec/mdRef/@CHECKSUMTYPE isn't valid");
          return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, Message.createErrorMessage(
            message.toString(), metsValidatorState.getMetsName(), metsValidatorState.isRootMets()), false, false);
        }
      } else {
        return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
          Message.createErrorMessage("mets/dmdSec/mdRef/@CHECKSUMTYPE in %1$s can't be null",
            metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
          false, false);
      }
    }
    return new ReporterDetails();
  }
}
