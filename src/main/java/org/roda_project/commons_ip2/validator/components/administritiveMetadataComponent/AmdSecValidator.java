package org.roda_project.commons_ip2.validator.components.administritiveMetadataComponent;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.roda_project.commons_ip2.mets_v1_12.beans.AmdSecType;
import org.roda_project.commons_ip2.mets_v1_12.beans.MdSecType;
import org.roda_project.commons_ip2.utils.IanaMediaTypes;
import org.roda_project.commons_ip2.validator.common.MetsParser;
import org.roda_project.commons_ip2.validator.constants.Constants;
import org.roda_project.commons_ip2.validator.handlers.MetsHandler;
import org.roda_project.commons_ip2.validator.reporter.ReporterDetails;
import org.roda_project.commons_ip2.validator.state.MetsValidatorState;
import org.roda_project.commons_ip2.validator.state.StructureValidatorState;
import org.roda_project.commons_ip2.validator.utils.CHECKSUMTYPE;
import org.roda_project.commons_ip2.validator.utils.Message;
import org.roda_project.commons_ip2.validator.utils.MetadataType;

/**
 * @author Carlos Afonso <cafonso@keep.pt>
 */
public abstract class AmdSecValidator {

  protected abstract String getVersion();

  /*
   * mets/amdSec If administrative / preservation metadata is available, it must
   * be described using the administrative metadata section ( <amdSec> ) element.
   * All administrative metadata is present in a single <amdSec> element. It is
   * possible to transfer metadata in a package using just the descriptive
   * metadata section and/or administrative metadata section.
   */
  protected ReporterDetails validateCSIP31(final StructureValidatorState structureValidatorState,
    final MetsValidatorState metsValidatorState, final List<AmdSecType> amdSec) throws IOException {
    if (structureValidatorState.isZipFileFlag()) {
      final String regex;
      if (metsValidatorState.isRootMets()) {
        final String objectId = metsValidatorState.getMets().getOBJID();
        if (objectId != null) {
          regex = objectId + "/metadata/.*";
        } else {
          return new ReporterDetails(getVersion(), Constants.METS_OBJECTID_CAN_T_BE_NULL, false, false);
        }
      } else {
        regex = metsValidatorState.getMetsPath() + "metadata/.*";
      }
      if (amdSec == null || amdSec.isEmpty()) {
        if (structureValidatorState.getZipManager().countMetadataFiles(structureValidatorState.getIpPath(),
          regex) != 0) {
          return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
            Message.createErrorMessage(
              "You have administrative files in the metadata/folder, " + "you must have mets/amdSec in %1$s",
              metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
            false, false);
        }
      } else {
        if (structureValidatorState.getZipManager().countMetadataFiles(structureValidatorState.getIpPath(),
          regex) == 0) {
          for (AmdSecType amd : amdSec) {
            if (amd.getDigiprovMD() != null && !amd.getDigiprovMD().isEmpty()) {
              return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
                Message.createErrorMessage(
                  "Doesn't have files in metadata folder "
                    + "but have amdSec in %1$s. Put the files under metadata folder",
                  metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
                false, false);
            }
          }
        } else {
          Map<String, Boolean> metadataFiles = structureValidatorState.getZipManager()
            .getMetadataFiles(structureValidatorState.getIpPath(), regex);
          metadataFiles = metadataFiles.entrySet().stream().filter(entry -> !entry.getKey().contains("/descriptive/"))
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
          for (AmdSecType amd : metsValidatorState.getMets().getAmdSec()) {
            final List<MdSecType> allMdSecTypes = new ArrayList<>();
            allMdSecTypes.addAll(amd.getDigiprovMD());
            allMdSecTypes.addAll(amd.getRightsMD());
            allMdSecTypes.addAll(amd.getTechMD());
            allMdSecTypes.addAll(amd.getSourceMD());
            for (MdSecType md : allMdSecTypes) {
              final MdSecType.MdRef mdRef = md.getMdRef();
              if (mdRef != null && mdRef.getHref() != null) {
                final String hrefDecoded = URLDecoder.decode(mdRef.getHref(), StandardCharsets.UTF_8);
                if (metsValidatorState.isRootMets()) {
                  if (metadataFiles.containsKey(metsValidatorState.getMets().getOBJID() + "/" + hrefDecoded)) {
                    metadataFiles.replace(metsValidatorState.getMets().getOBJID() + "/" + hrefDecoded, true);
                  }
                } else {
                  if (metadataFiles.containsKey(metsValidatorState.getMetsPath() + hrefDecoded)) {
                    metadataFiles.replace(metsValidatorState.getMetsPath() + hrefDecoded, true);
                  }
                }
              }
            }
          }
          if (metadataFiles.containsValue(false)) {
            for (MdSecType md : metsValidatorState.getMets().getDmdSec()) {
              final MdSecType.MdRef mdRef = md.getMdRef();
              if (mdRef != null && mdRef.getHref() != null) {
                final String hrefDecoded = URLDecoder.decode(mdRef.getHref(), StandardCharsets.UTF_8);
                if (metsValidatorState.isRootMets()) {
                  if (metadataFiles.containsKey(metsValidatorState.getMets().getOBJID() + "/" + hrefDecoded)) {
                    metadataFiles.replace(metsValidatorState.getMets().getOBJID() + "/" + hrefDecoded, true);
                  }
                } else {
                  if (metadataFiles.containsKey(metsValidatorState.getMetsPath() + hrefDecoded)) {
                    metadataFiles.replace(metsValidatorState.getMetsPath() + hrefDecoded, true);
                  }
                }
              }
            }
            if (metadataFiles.containsValue(false)) {
              final Map<String, Boolean> missedMetadataFiles = metadataFiles.entrySet().stream()
                .filter(entry -> !entry.getValue()).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
              final String initialMessage = "There are administrative files not referenced: ";
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
      if (amdSec == null || amdSec.isEmpty()) {
        if (structureValidatorState.getFolderManager()
          .countMetadataFiles(Paths.get(metsValidatorState.getMetsPath())) != 0) {
          return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
            Message.createErrorMessage(
              "You have administrative files in the metadata/folder, " + "you must have mets/amdSec in %1$s",
              metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
            false, false);
        }
      } else {
        if (structureValidatorState.getFolderManager()
          .countMetadataFiles(Paths.get(metsValidatorState.getMetsPath())) == 0) {
          for (AmdSecType amd : amdSec) {
            if (amd.getDigiprovMD() != null && !amd.getDigiprovMD().isEmpty()) {
              return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
                Message.createErrorMessage(
                  "Doesn't have files in metadata folder "
                    + "but have amdSec in %1$s. Put the files under metadata folder",
                  metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
                false, false);
            }
          }
        } else {
          Map<String, Boolean> metadataFiles = structureValidatorState.getFolderManager()
            .getMetadataFiles(Paths.get(metsValidatorState.getMetsPath()));
          metadataFiles = metadataFiles.entrySet().stream().filter(entry -> !entry.getKey().contains("/descriptive/"))
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
          for (AmdSecType amd : metsValidatorState.getMets().getAmdSec()) {
            for (MdSecType md : amd.getDigiprovMD()) {
              final MdSecType.MdRef mdRef = md.getMdRef();
              if (mdRef != null) {
                final String hrefDecoded = URLDecoder.decode(mdRef.getHref(), StandardCharsets.UTF_8);
                if (hrefDecoded != null) {
                  final String path = Paths.get(metsValidatorState.getMetsPath()).resolve(hrefDecoded).toString();
                  if (metadataFiles.containsKey(path)) {
                    metadataFiles.replace(path, true);
                  }
                }
              }
            }
          }
          if (metadataFiles.containsValue(false)) {
            for (MdSecType md : metsValidatorState.getMets().getDmdSec()) {
              final MdSecType.MdRef mdRef = md.getMdRef();
              if (mdRef != null) {
                final String hrefDecoded = URLDecoder.decode(mdRef.getHref(), StandardCharsets.UTF_8);
                if (hrefDecoded != null) {
                  final String path = Paths.get(metsValidatorState.getMetsPath()).resolve(hrefDecoded).toString();
                  if (metadataFiles.containsKey(path)) {
                    metadataFiles.replace(path, true);
                  }
                }
              }
            }
            if (metadataFiles.containsValue(false)) {

              final Map<String, Boolean> missedMetadataFiles = metadataFiles.entrySet().stream()
                .filter(entry -> !entry.getValue()).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

              final String initialMessage = "There are administrative files not referenced: ";
              final String message = Message.createMissingFilesMessage(missedMetadataFiles, initialMessage,
                structureValidatorState.isZipFileFlag(), metsValidatorState.getMetsPath());
              return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
                Message.createErrorMessage(message, metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
                false, false);
            }
          }
        }
      }
    }
    return new ReporterDetails();
  }

  /*
   * mets/amdSec/digiprovMD For recording information about preservation the
   * standard PREMIS is used. It is mandatory to include one <digiprovMD> element
   * for each piece of PREMIS metadata. The use if PREMIS in METS is following the
   * recommendations in the 2017 version of PREMIS in METS Guidelines.
   */
  protected ReporterDetails validateCSIP32(final MetsValidatorState metsValidatorState, final List<AmdSecType> amdSec)
    throws IOException {
    if (amdSec != null && !amdSec.isEmpty()) {
      for (AmdSecType a : amdSec) {
        final List<MdSecType> digiprov = a.getDigiprovMD();
        if (!digiprov.isEmpty()) {
          for (MdSecType md : digiprov) {
            if (md.getMdRef() == null) {
              return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
                Message.createErrorMessage(
                  "It is mandatory to include one <digiprovMD> " + "element in %1$s for each piece of PREMIS metadata",
                  metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
                false, false);
            }
          }
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
  protected ReporterDetails validateCSIP33(final MetsValidatorState metsValidatorState, final List<AmdSecType> amdSec) {
    for (AmdSecType a : amdSec) {
      final List<MdSecType> digiprov = a.getDigiprovMD();
      for (MdSecType md : digiprov) {
        if (!metsValidatorState.checkMetsInternalId(md.getID())) {
          metsValidatorState.addMetsInternalId(md.getID());
        } else {
            String message = "Value " + md.getID() +
                    " in %1$s for mets/amdSec/digiprovMD/@ID isn't unique in the package";
          return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, Message.createErrorMessage(
                  message, metsValidatorState.getMetsName(), metsValidatorState.isRootMets()), false, false);
        }
      }
    }
    return new ReporterDetails();
  }

  /*
   * mets/amdSec/digiprovMD/@STATUS Indicates the status of the package using a
   * fixed vocabulary.See also: dmdSec status
   */
  protected ReporterDetails validateCSIP34(final MetsValidatorState metsValidatorState, final List<AmdSecType> amdSec,
    final List<String> dmdSecStatus) {
    for (AmdSecType a : amdSec) {
      final List<MdSecType> digiprov = a.getDigiprovMD();
      for (MdSecType md : digiprov) {
        final String status = md.getSTATUS();
        if (!dmdSecStatus.contains(status)) {
            return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, Message.createErrorMessage(
                    "Value " + status + " in %1$s for mets/amdSec/digiprovMD/@STATUS isn't valid", metsValidatorState.getMetsName(), metsValidatorState.isRootMets()), false, false);
        }
      }
    }
    return new ReporterDetails();
  }

  /*
   * mets/amdSec/digiprovMD/mdRef Reference to the digital provenance metadata
   * file stored in the “metadata” section of the IP.
   */
  protected ReporterDetails validateCSIP35(final MetsValidatorState metsValidatorState, final List<AmdSecType> amdSec) {
    for (AmdSecType a : amdSec) {
      final List<MdSecType> digiprov = a.getDigiprovMD();
      for (MdSecType md : digiprov) {
        final MdSecType.MdRef mdRef = md.getMdRef();
        if (mdRef == null) {
          return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
            Message.createErrorMessage(
              "mets/amdSec/digiprovMD/mdRef in %1$s " + "is the reference to the digital provenance metadata file",
              metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
            false, false);
        }
      }
    }
    return new ReporterDetails();
  }

  /*
   * mets/amdSec/digiprovMD/mdRef[@LOCTYPE=’URL’] The locator type is always used
   * with the value “URL” from the vocabulary in the attribute. Ver este tb
   */
  protected ReporterDetails validateCSIP36(final MetsValidatorState metsValidatorState, final List<AmdSecType> amdSec) {
    for (AmdSecType a : amdSec) {
      final List<MdSecType> digiprov = a.getDigiprovMD();
      for (MdSecType md : digiprov) {
        final MdSecType.MdRef mdRef = md.getMdRef();
        if (mdRef != null) {
          final String loctype = mdRef.getLOCTYPE();
          if (loctype != null) {
            if (!loctype.equals("URL")) {
              return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
                Message.createErrorMessage("mets/amdSec/digiprovMD/mdRef[@LOCTYPE='URL'] value in %1$s must be URL",
                  metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
                false, false);
            }
          } else {
            return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
              Message.createErrorMessage("mets/amdSec/digiprovMD/mdRef[@LOCTYPE='URL'] in %1$s can't be null",
                metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
              false, false);
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
  protected ReporterDetails validateCSIP37(final StructureValidatorState structureValidatorState,
    final MetsValidatorState metsValidatorState, final List<AmdSecType> amdSec) throws IOException {
    final HashMap<String, String> amdSecTypes = new HashMap<>();
    final MetsHandler amdSecHandler = new MetsHandler("digiprovMD", "mdRef", amdSecTypes);
    final MetsParser metsParser = new MetsParser();
    InputStream metsStream = null;
    if (!amdSec.isEmpty()) {
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
      metsParser.parse(amdSecHandler, metsStream);
    }
    int numberOfMdRef = 0;
    for (AmdSecType a : amdSec) {
      final List<MdSecType> digiprovMds = a.getDigiprovMD();
      for (MdSecType mdSecType : digiprovMds) {
        if (mdSecType.getMdRef() != null) {
          numberOfMdRef++;
        }
      }
    }
    if (amdSecTypes.size() < numberOfMdRef) {
      return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
        Message.createErrorMessage("mets/amdSec/mdRef[@xlink:type=’simple’] in %1$s can't be null",
          metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
        false, false);
    } else {
      for (Map.Entry<String, String> entry : amdSecTypes.entrySet()) {
        if (entry.getValue() == null) {
          return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
            Message.createErrorMessage("mets/amdSec/mdRef[@xlink:type=’simple’] in %1$s can't be null",
              metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
            false, false);
        }
      }
      final Map<String, String> typesInvalid = amdSecTypes.entrySet().stream()
        .filter(type -> !type.getValue().equals("simple"))
        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
      if (!typesInvalid.isEmpty()) {
        final StringBuilder message = new StringBuilder();
        message.append("Values ");
        typesInvalid.entrySet().stream().map(Map.Entry::getValue).collect(Collectors.toList())
          .forEach(type -> message.append(type).append(","));
        message.append(" in %1$s for mets/amdSec/mdRef[@xlink:type=’simple’] isn't valid, must be 'simple'");
        return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, Message.createErrorMessage(
          message.toString(), metsValidatorState.getMetsName(), metsValidatorState.isRootMets()), false, false);
      }
    }
    return new ReporterDetails();
  }

  /*
   * mets/amdSec/digiprovMD/mdRef/@xlink:href The actual location of the resource.
   * This specification recommends recording a URL type filepath within this
   * attribute.
   */
  protected ReporterDetails validateCSIP38(final StructureValidatorState structureValidatorState,
    final MetsValidatorState metsValidatorState, final List<AmdSecType> amdSec) throws IOException {
    for (AmdSecType a : amdSec) {
      final List<MdSecType> digiprov = a.getDigiprovMD();
      for (MdSecType md : digiprov) {
        final MdSecType.MdRef mdRef = md.getMdRef();
        final StringBuilder message = new StringBuilder();
        if (mdRef != null && mdRef.getHref() != null) {
          final String href = URLDecoder.decode(mdRef.getHref(), StandardCharsets.UTF_8);
          if (structureValidatorState.isZipFileFlag()) {
            final StringBuilder path = new StringBuilder();
            if (metsValidatorState.isRootMets()) {
              if (metsValidatorState.getMets().getOBJID() != null) {
                path.append(metsValidatorState.getMets().getOBJID()).append("/").append(href);
              } else {
                return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
                  Message.createErrorMessage("mets/OBJECTID in %1$s can't be null", metsValidatorState.getMetsName(),
                    metsValidatorState.isRootMets()),
                  false, false);
              }
            } else {
              path.append(metsValidatorState.getMetsPath()).append(href);
            }
            if (!structureValidatorState.getZipManager().checkPathExists(structureValidatorState.getIpPath(),
              path.toString())) {
              message.append("mets/amdSec/digiprovMD/mdRef/@xlink:href (").append(path)
                .append(") doesn't exists (%1$s)");
              return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, Message.createErrorMessage(
                message.toString(), metsValidatorState.getMetsName(), metsValidatorState.isRootMets()), false, false);
            }
          } else {
            if (!structureValidatorState.getFolderManager()
              .checkPathExists(Paths.get(metsValidatorState.getMetsPath()).resolve(href))) {
              message.append("mets/amdSec/digiprovMD/mdRef/@xlink:href (")
                .append(Paths.get(metsValidatorState.getMetsPath()).resolve(href)).append(") doesn't exists (%1$s)");
              return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, message.toString(), false,
                false);
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
  protected ReporterDetails validateCSIP39(final MetsValidatorState metsValidatorState, final List<AmdSecType> amdSec) {
    final List<String> tmp = new ArrayList<>();
    for (MetadataType md : MetadataType.values()) {
      tmp.add(md.toString());
    }
    for (AmdSecType a : amdSec) {
      final List<MdSecType> digiprov = a.getDigiprovMD();
      for (MdSecType md : digiprov) {
        final MdSecType.MdRef mdRef = md.getMdRef();
        if (mdRef != null) {
          final String mdType = mdRef.getMDTYPE();
          if (mdType == null) {
            return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
              Message.createErrorMessage("mets/amdSec/digiprovMD/mdRef/@MDTYPE in %1$s can't be null",
                metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
              false, false);
          } else {
            if (!tmp.contains(mdType)) {
                String message = "Value " + mdType +
                        " for mets/amdSec/digiprovMD/mdRef/@MDTYPE value in %1$s isn't valid";
              return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, Message.createErrorMessage(
                      message, metsValidatorState.getMetsName(), metsValidatorState.isRootMets()), false, false);
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
  protected ReporterDetails validateCSIP40(final MetsValidatorState metsValidatorState, final List<AmdSecType> amdSec) {
    for (AmdSecType amdSecType : amdSec) {
      final List<MdSecType> digiprovMD = amdSecType.getDigiprovMD();
      for (MdSecType digiprov : digiprovMD) {
        final MdSecType.MdRef mdRef = digiprov.getMdRef();
        final String mimeType = mdRef.getMIMETYPE();
        if (mimeType != null) {
          if (!IanaMediaTypes.getIanaMediaTypesList().contains(mimeType)) {
              String message = "Value " + mimeType +
                      " in %1$s for mets/amdSec/digiprovMD/mdRef/@MIMETYPE isn't valid";
            return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, Message.createErrorMessage(
                    message, metsValidatorState.getMetsName(), metsValidatorState.isRootMets()), false, false);
          }
        } else {
          return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
            Message.createErrorMessage("mets/amdSec/digiprovMD/mdRef/@MIMETYPE of file in %1$s can't be null",
              metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
            false, false);
        }
      }
    }
    return new ReporterDetails();
  }

  /*
   * mets/amdSec/digiprovMD/mdRef/@SIZE Size of the referenced file in bytes.
   */
  protected ReporterDetails validateCSIP41(final StructureValidatorState structureValidatorState,
    final MetsValidatorState metsValidatorState, final List<AmdSecType> amdSec) throws IOException {
    for (AmdSecType amdSecType : amdSec) {
      final List<MdSecType> digiprov = amdSecType.getDigiprovMD();
      for (MdSecType md : digiprov) {
        final MdSecType.MdRef mdRef = md.getMdRef();
        if (mdRef != null && mdRef.getHref() != null) {
          final String href = URLDecoder.decode(mdRef.getHref(), StandardCharsets.UTF_8);
          final Long size = mdRef.getSIZE();
          if (size == null) {
            return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
              Message.createErrorMessage("mets/amdSec/digiprovMD/mdRef/@SIZE of file in %1$s can't be null",
                metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
              false, false);
          } else {
            final StringBuilder message = new StringBuilder();
            if (structureValidatorState.isZipFileFlag()) {
              final String file;
              if (metsValidatorState.isRootMets()) {
                final String objectId = metsValidatorState.getMets().getOBJID();
                if (objectId != null) {
                  file = objectId + "/" + href;
                } else {
                  return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
                    Message.createErrorMessage(Constants.METS_OBJECTID_CAN_T_BE_NULL, metsValidatorState.getMetsName(),
                      metsValidatorState.isRootMets()),
                    false, false);
                }
              } else {
                file = metsValidatorState.getMetsPath() + href;
              }
              if (!structureValidatorState.getZipManager().verifySize(structureValidatorState.getIpPath(), file,
                size)) {
                message.append("mets/dmdSec/mdRef/@SIZE ").append(size).append(" in %1$s and size of file (")
                  .append(file).append(") isn't equal");
                return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, Message.createErrorMessage(
                  message.toString(), metsValidatorState.getMetsName(), metsValidatorState.isRootMets()), false, false);
              }
            } else {
              if (metsValidatorState.isRootMets()) {
                if (!structureValidatorState.getFolderManager()
                  .verifySize(structureValidatorState.getIpPath().resolve(href), size)) {
                  message.append("mets/dmdSec/mdRef/@SIZE ").append(size).append(" in %1$s and size of file (")
                    .append(structureValidatorState.getIpPath().resolve(href)).append(") isn't equal");
                  return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
                    Message.createErrorMessage(message.toString(), metsValidatorState.getMetsName(),
                      metsValidatorState.isRootMets()),
                    false, false);
                }
              } else {
                if (!structureValidatorState.getFolderManager()
                  .verifySize(Paths.get(metsValidatorState.getMetsPath()).resolve(href), size)) {
                  message.append("mets/dmdSec/mdRef/@SIZE ").append(size).append(" in %1$s and size of file (")
                    .append(Paths.get(metsValidatorState.getMetsPath()).resolve(href)).append(") isn't equal");
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
   * mets/amdSec/digiprovMD/mdRef/@CREATED Creation date of the referenced file.
   */
  protected ReporterDetails validateCSIP42(final MetsValidatorState metsValidatorState, final List<AmdSecType> amdSec) {
    for (AmdSecType a : amdSec) {
      final List<MdSecType> digiprov = a.getDigiprovMD();
      for (MdSecType md : digiprov) {
        final MdSecType.MdRef mdRef = md.getMdRef();
        if (mdRef.getCREATED() == null) {
          return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
            Message.createErrorMessage("mets/amdSec/digiprovMD/mdRef/@CREATED in %1$s can't be null",
              metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
            false, false);
        }
      }
    }
    return new ReporterDetails();
  }

  /*
   * mets/amdSec/digiprovMD/mdRef/@CHECKSUM The checksum of the referenced file.
   */
  protected ReporterDetails validateCSIP43(final StructureValidatorState structureValidatorState,
    final MetsValidatorState metsValidatorState, final List<AmdSecType> amdSec)
    throws IOException, NoSuchAlgorithmException {
    final List<String> tmp = new ArrayList<>();
    for (CHECKSUMTYPE check : CHECKSUMTYPE.values()) {
      tmp.add(check.toString());
    }
    for (AmdSecType a : amdSec) {
      final List<MdSecType> digiprov = a.getDigiprovMD();
      for (MdSecType md : digiprov) {
        final MdSecType.MdRef mdRef = md.getMdRef();
        final String checksumType = mdRef.getCHECKSUMTYPE();
        if (checksumType == null) {
          return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
            Message.createErrorMessage("mets/amdSec/digiprovMD/mdRef/@CHECKSUMTYPE in %1$s can't be null",
              metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
            false, false);
        } else {
          if (!tmp.contains(checksumType)) {
            return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
              Message.createErrorMessage("mets/amdSec/digiprovMD/mdRef/@CHECKSUMTYPE value in %1$s isn't valid",
                metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
              false, false);
          } else {
            final String checksum = mdRef.getCHECKSUM();
            if (checksum == null) {
              return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
                Message.createErrorMessage("mets/amdSec/digiprovMD/mdRef/@CHECKSUM in %1$s can't be null",
                  metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
                false, false);
            } else {
              final String href = URLDecoder.decode(mdRef.getHref(), StandardCharsets.UTF_8);
              final StringBuilder message = new StringBuilder();
              if (structureValidatorState.isZipFileFlag()) {
                final StringBuilder file = new StringBuilder();
                if (metsValidatorState.isRootMets()) {
                  final String objectId = metsValidatorState.getMets().getOBJID();
                  if (objectId != null) {
                    file.append(objectId).append("/").append(href);
                  } else {
                    return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
                      Message.createErrorMessage(Constants.METS_OBJECTID_CAN_T_BE_NULL,
                        metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
                      false, false);
                  }
                } else {
                  file.append(metsValidatorState.getMetsPath()).append(href);
                }
                if (!structureValidatorState.getZipManager().verifyChecksum(structureValidatorState.getIpPath(),
                  file.toString(), checksumType, checksum)) {
                  message.append("mets/dmdSec/mdRef/@CHECKSUM ").append(checksum).append(" in %1$s and size of file (")
                    .append(file).append(") isn't equal");
                  return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
                    Message.createErrorMessage(message.toString(), metsValidatorState.getMetsName(),
                      metsValidatorState.isRootMets()),
                    false, false);
                }
              } else {
                if (!structureValidatorState.getFolderManager()
                  .verifyChecksum(Paths.get(metsValidatorState.getMetsPath()).resolve(href), checksumType, checksum)) {
                  message.append("mets/dmdSec/mdRef/@CHECKSUM ").append(checksum).append(" in %1$s and size of file (")
                    .append(Paths.get(metsValidatorState.getMetsPath()).resolve(href)).append(") isn't equal");
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
   * mets/amdSec/digiprovMD/mdRef/@CHECKSUMTYPE The type of checksum following the
   * value list present in the METS-standard which has been used for calculating
   * the checksum for the referenced file.
   */
  protected ReporterDetails validateCSIP44(final MetsValidatorState metsValidatorState, final List<AmdSecType> amdSec) {
    final List<String> tmp = new ArrayList<>();
    for (CHECKSUMTYPE check : CHECKSUMTYPE.values()) {
      tmp.add(check.toString());
    }
    for (AmdSecType a : amdSec) {
      final List<MdSecType> digiprov = a.getDigiprovMD();
      for (MdSecType md : digiprov) {
        final MdSecType.MdRef mdRef = md.getMdRef();
        final String checksumType = mdRef.getCHECKSUMTYPE();
        if (checksumType == null) {

          return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
            Message.createErrorMessage("mets/amdSec/digiprovMD/mdRef/@CHECKSUMTYPE in %1$s can't be null",
              metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
            false, false);
        } else {
          if (!tmp.contains(checksumType)) {
              String message = "Value " + checksumType +
                      " for mets/amdSec/digiprovMD/mdRef/@CHECKSUMTYPE value in %1$s isn't valid";
            return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, Message.createErrorMessage(
                    message, metsValidatorState.getMetsName(), metsValidatorState.isRootMets()), false, false);
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
  protected ReporterDetails validateCSIP45(final MetsValidatorState metsValidatorState, final List<AmdSecType> amdSec) {
    boolean found = false;
    for (AmdSecType a : amdSec) {
      if (a.getRightsMD() != null && !a.getRightsMD().isEmpty()) {
        found = true;
      }
    }
    if (found) {
      return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
        Message.createErrorMessage("You have specified mets/amdSec/rightsMD in %1$s", metsValidatorState.getMetsName(),
          metsValidatorState.isRootMets()),
        true, false);
    }
    return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
      Message.createErrorMessage(
        "Individual representations should state their " + "specific rights in their representation METS file (%1$s)",
        metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
      false, false);
  }

  /*
   * mets/amdSec/rightsMD/@ID An xml:id identifier for the rights metadata section
   * ( <rightsMD> ) used for internal package references. It must be unique within
   * the package.
   */
  protected ReporterDetails validateCSIP46(final MetsValidatorState metsValidatorState, final List<AmdSecType> amdSec) {
    for (AmdSecType a : amdSec) {
      final List<MdSecType> rigthsMD = a.getRightsMD();
      if (rigthsMD != null && !rigthsMD.isEmpty()) {
        for (MdSecType rmd : rigthsMD) {
          if (metsValidatorState.checkMetsInternalId(rmd.getID())) {
              String message = "Value " + rmd.getID() +
                      " in %1$s for mets/amdSec/rightsMD/@ID isn't unique in the package";
            return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, Message.createErrorMessage(
                    message, metsValidatorState.getMetsName(), metsValidatorState.isRootMets()), false, false);
          } else {
            metsValidatorState.addMetsInternalId(rmd.getID());
          }
        }
      } else {
        return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
          Message.createErrorMessage("Skipped in %1$s doesn't have mets/amdSec/rightsMD",
            metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
          true, true);
      }
    }
    return new ReporterDetails();
  }

  /*
   * mets/amdSec/rightsMD/@STATUS Indicates the status of the package using a
   * fixed vocabulary.See also: dmdSec status
   */
  protected ReporterDetails validateCSIP47(final MetsValidatorState metsValidatorState, final List<AmdSecType> amdSec,
    final List<String> dmdSecStatus) {
    for (AmdSecType a : amdSec) {
      final List<MdSecType> rigthsMD = a.getRightsMD();
      if (rigthsMD != null && !rigthsMD.isEmpty()) {
        for (MdSecType rmd : rigthsMD) {
          final String status = rmd.getSTATUS();
          if (status == null) {
            return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
              Message.createErrorMessage("mets/amdSec/rightsMD/@STATUS in %1$s can't be null",
                metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
              false, false);
          } else {
            if (!dmdSecStatus.contains(status)) {
                String message = "Value " + status +
                        " in %1$s for mets/amdSec/rightsMD/@STATUS value isn't valid";
              return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, message, false,
                false);
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
   */
  protected ReporterDetails validateCSIP48(final MetsValidatorState metsValidatorState, final List<AmdSecType> amdSec) {
    for (AmdSecType a : amdSec) {
      final List<MdSecType> rigthsMD = a.getRightsMD();
      if (rigthsMD != null && !rigthsMD.isEmpty()) {
        for (MdSecType rmd : rigthsMD) {
          if (rmd.getMdRef() == null) {
            return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
              Message.createErrorMessage("mets/amdSec/rightsMD/mdRef in %1$s is null", metsValidatorState.getMetsName(),
                metsValidatorState.isRootMets()),
              false, false);
          }
        }
      }
    }
    return new ReporterDetails();
  }

  /*
   * mets/amdSec/rightsMD/mdRef[@LOCTYPE=’URL’] The locator type is always used
   * with the value “URL” from the vocabulary in the attribute.
   */
  protected ReporterDetails validateCSIP49(final MetsValidatorState metsValidatorState, final List<AmdSecType> amdSec) {
    for (AmdSecType a : amdSec) {
      final List<MdSecType> rigthsMD = a.getRightsMD();
      if (rigthsMD != null && !rigthsMD.isEmpty()) {
        for (MdSecType rmd : rigthsMD) {
          final MdSecType.MdRef mdRef = rmd.getMdRef();
          if (mdRef != null) {
            final String loctype = mdRef.getLOCTYPE();
            if (loctype == null) {
              return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
                Message.createErrorMessage("mets/amdSec/rightsMD/mdRef[@LOCTYPE=’URL’] in %1$s can't be null",
                  metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
                false, false);
            } else {
              if (!loctype.equals("URL")) {
                return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
                  Message.createErrorMessage("mets/amdSec/rightsMD/mdRef[@LOCTYPE=’URL’] value in %1$s must be 'URL'",
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
   * mets/amdSec/rightsMD/mdRef[@xlink:type=’simple’] Attribute used with the
   * value “simple”. Value list is maintained by the xlink standard.
   */
  protected ReporterDetails validateCSIP50(final StructureValidatorState structureValidatorState,
    final MetsValidatorState metsValidatorState, final List<AmdSecType> amdSec) throws IOException {
    final HashMap<String, String> amdSecTypes = new HashMap<>();
    final MetsHandler amdSecHandler = new MetsHandler("rightsMD", "mdRef", amdSecTypes);
    final MetsParser metsParser = new MetsParser();
    InputStream metsStream = null;
    if (!amdSec.isEmpty()) {
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
      metsParser.parse(amdSecHandler, metsStream);
    }
    int numberOfMdRef = 0;
    for (AmdSecType a : amdSec) {
      final List<MdSecType> rigthsMD = a.getRightsMD();
      if (rigthsMD != null && !rigthsMD.isEmpty()) {
        for (MdSecType rmd : rigthsMD) {
          final MdSecType.MdRef mdRef = rmd.getMdRef();
          if (mdRef != null) {
            numberOfMdRef++;
          }
        }
      }
    }
    if (amdSecTypes.size() < numberOfMdRef) {
      return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
        Message.createErrorMessage("mets/amdSec/rightsMD/mdRef[@xlink:type='simple'] in %1$s can't be null",
          metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
        false, false);
    } else {
      for (Map.Entry<String, String> entry : amdSecTypes.entrySet()) {
        if (entry.getValue() == null) {
          return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
            Message.createErrorMessage("mets/amdSec/rightsMD/mdRef[@xlink:type='simple'] in %1$s can't be null",
              metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
            false, false);
        }
      }
      final Map<String, String> typesInvalid = amdSecTypes.entrySet().stream()
        .filter(type -> !type.getValue().equals("simple"))
        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
      if (!typesInvalid.isEmpty()) {
        final StringBuilder message = new StringBuilder();
        message.append("Values ");
        typesInvalid.entrySet().stream().map(Map.Entry::getValue).collect(Collectors.toList())
          .forEach(type -> message.append(type).append(","));
        message
          .append(" in %1$s for mets/amdSec/rightsMD/mdRef[@xlink:type='simple'] " + "isn't valid, must be 'simple'");
        return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, Message.createErrorMessage(
          message.toString(), metsValidatorState.getMetsName(), metsValidatorState.isRootMets()), false, false);
      }
    }
    return new ReporterDetails();
  }

  /*
   * mets/amdSec/rightsMD/mdRef/@xlink:href The actual location of the resource.
   * We recommend recording a URL type filepath within this attribute.
   */
  protected ReporterDetails validateCSIP51(final StructureValidatorState structureValidatorState,
    final MetsValidatorState metsValidatorState, final List<AmdSecType> amdSec) throws IOException {
    for (AmdSecType a : amdSec) {
      final List<MdSecType> rigthsMD = a.getRightsMD();
      if (rigthsMD != null && !rigthsMD.isEmpty()) {
        for (MdSecType rmd : rigthsMD) {
          final MdSecType.MdRef mdRef = rmd.getMdRef();
          if (mdRef != null && mdRef.getHref() != null) {
            final String href = URLDecoder.decode(mdRef.getHref(), StandardCharsets.UTF_8);
            final StringBuilder message = new StringBuilder();
            if (structureValidatorState.isZipFileFlag()) {
              final StringBuilder filePath = new StringBuilder();
              if (metsValidatorState.isRootMets()) {
                filePath.append(metsValidatorState.getMets().getOBJID()).append("/").append(href);
              } else {
                filePath.append(metsValidatorState.getMetsPath()).append(href);
              }
              if (!structureValidatorState.getZipManager().checkPathExists(structureValidatorState.getIpPath(),
                filePath.toString())) {
                message.append("mets/amdSec/rightsMD/mdRef/@xlink:href ").append(filePath)
                  .append(" doesn't exists (in %1$s)");
                return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, Message.createErrorMessage(
                  message.toString(), metsValidatorState.getMetsName(), metsValidatorState.isRootMets()), false, false);
              }
            } else {
              if (!structureValidatorState.getFolderManager()
                .checkPathExists(Paths.get(metsValidatorState.getMetsPath()).resolve(href))) {
                message.append("mets/amdSec/rightsMD/mdRef/@xlink:href ")
                  .append(Paths.get(metsValidatorState.getMetsPath()).resolve(href))
                  .append(" doesn't exists (in %1$s)");
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
   * mets/amdSec/rightsMD/mdRef/@MDTYPE Specifies the type of metadata in the
   * referenced file. Value is taken from the list provided by the METS.
   */
  protected ReporterDetails validateCSIP52(final MetsValidatorState metsValidatorState, final List<AmdSecType> amdSec) {
    final List<String> tmp = new ArrayList<>();
    for (MetadataType md : MetadataType.values()) {
      tmp.add(md.toString());
    }
    for (AmdSecType a : amdSec) {
      final List<MdSecType> rigthsMD = a.getRightsMD();
      if (rigthsMD != null && !rigthsMD.isEmpty()) {
        for (MdSecType rmd : rigthsMD) {
          final MdSecType.MdRef mdRef = rmd.getMdRef();
          if (mdRef != null) {
            final String mdType = mdRef.getMDTYPE();
            if (mdType == null) {
              return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
                Message.createErrorMessage("mets/amdSec/rightsMD/mdRef/@MDTYPE in %1$s can't be null",
                  metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
                false, false);
            } else {
              if (!tmp.contains(mdType)) {
                  String message = "Value " + mdType +
                          " in %1$s for mets/amdSec/rightsMD/mdRef/@MDTYPE value isn't valid";
                return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, Message.createErrorMessage(
                        message, metsValidatorState.getMetsName(), metsValidatorState.isRootMets()), false, false);
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
  protected ReporterDetails validateCSIP53(final MetsValidatorState metsValidatorState, final List<AmdSecType> amdSec) {
    for (AmdSecType a : amdSec) {
      final List<MdSecType> rigthsMD = a.getRightsMD();
      if (rigthsMD != null && !rigthsMD.isEmpty()) {
        for (MdSecType rmd : rigthsMD) {
          final MdSecType.MdRef mdRef = rmd.getMdRef();
          if (mdRef != null) {
            final String mimeType = mdRef.getMIMETYPE();
            if (mimeType == null) {
              return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
                Message.createErrorMessage("mets/amdSec/rightsMD/mdRef/@MIMETYPE in %1$s can't be null",
                  metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
                false, false);
            } else {
              if (!IanaMediaTypes.getIanaMediaTypesList().contains(mimeType)) {
                  String message = "Value " + mimeType +
                          " in %1$s for mets/amdSec/rightsMD/mdRef/@MIMETYPE value isn't valid";
                return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, Message.createErrorMessage(
                        message, metsValidatorState.getMetsName(), metsValidatorState.isRootMets()), false, false);
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
  protected ReporterDetails validateCSIP54(final StructureValidatorState structureValidatorState,
    final MetsValidatorState metsValidatorState, final List<AmdSecType> amdSec) throws IOException {
    for (AmdSecType a : amdSec) {
      final List<MdSecType> rigthsMD = a.getRightsMD();
      if (rigthsMD != null && !rigthsMD.isEmpty()) {
        for (MdSecType rmd : rigthsMD) {
          final MdSecType.MdRef mdRef = rmd.getMdRef();
          if (mdRef != null && mdRef.getHref() != null) {
            final String href = URLDecoder.decode(mdRef.getHref(), StandardCharsets.UTF_8);
            final Long size = mdRef.getSIZE();
            if (size == null) {
              return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
                Message.createErrorMessage("mets/amdSec/rightsMD/mdRef/@SIZE in %1$s can't be null",
                  metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
                false, false);
            } else {
              final StringBuilder message = new StringBuilder();
              if (structureValidatorState.isZipFileFlag()) {
                final StringBuilder filePath = new StringBuilder();
                if (metsValidatorState.isRootMets()) {
                  filePath.append(metsValidatorState.getMets().getOBJID()).append("/").append(href);
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
                      .append(structureValidatorState.getIpPath().resolve(href)).append(") isn't equal");
                    return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
                      Message.createErrorMessage(message.toString(), metsValidatorState.getMetsName(),
                        metsValidatorState.isRootMets()),
                      false, false);
                  }
                } else {
                  if (!structureValidatorState.getFolderManager()
                    .verifySize(Paths.get(metsValidatorState.getMetsPath()).resolve(href), size)) {
                    message.append("mets/dmdSec/mdRef/@SIZE ").append(size).append(" in %1$s and size of file (")
                      .append(Paths.get(metsValidatorState.getMetsPath()).resolve(href)).append(") isn't equal");
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
   * mets/amdSec/rightsMD/mdRef/@CREATED Creation date of the referenced file.
   */
  protected ReporterDetails validateCSIP55(final MetsValidatorState metsValidatorState, final List<AmdSecType> amdSec) {
    for (AmdSecType a : amdSec) {
      final List<MdSecType> rigthsMD = a.getRightsMD();
      if (rigthsMD != null && !rigthsMD.isEmpty()) {
        for (MdSecType rmd : rigthsMD) {
          final MdSecType.MdRef mdRef = rmd.getMdRef();
          if (mdRef.getCREATED() == null) {
            return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
              Message.createErrorMessage("mets/amdSec/rightsMD/mdRef/@CREATED in %1$s can't be null",
                metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
              false, false);
          }
        }
      }
    }
    return new ReporterDetails();
  }

  /*
   * mets/amdSec/rightsMD/mdRef/@CHECKSUM The checksum of the referenced file.
   */
  protected ReporterDetails validateCSIP56(final StructureValidatorState structureValidatorState,
    final MetsValidatorState metsValidatorState, final List<AmdSecType> amdSec)
    throws IOException, NoSuchAlgorithmException {
    final List<String> tmp = new ArrayList<>();
    for (CHECKSUMTYPE check : CHECKSUMTYPE.values()) {
      tmp.add(check.toString());
    }
    for (AmdSecType a : amdSec) {
      final List<MdSecType> rigthsMD = a.getRightsMD();
      if (rigthsMD != null && !rigthsMD.isEmpty()) {
        for (MdSecType rmd : rigthsMD) {
          final MdSecType.MdRef mdRef = rmd.getMdRef();
          final String checksumType = mdRef.getCHECKSUMTYPE();
          if (checksumType == null) {
            return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
              Message.createErrorMessage("mets/amdSec/rightsMD/mdRef/@CHECKSUMTYPE in %1$s can't be null",
                metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
              false, false);
          } else {
            if (!tmp.contains(checksumType)) {
                String message = "Value " + checksumType +
                        " for mets/amdSec/rightsMD/mdRef/@CHECKSUMTYPE value in %1$s isn't valid";
              return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, Message.createErrorMessage(
                      message, metsValidatorState.getMetsName(), metsValidatorState.isRootMets()), false, false);
            } else {
              final String checksum = mdRef.getCHECKSUM();
              if (checksum == null) {
                return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
                  Message.createErrorMessage("mets/amdSec/rightsMD/mdRef/@CHECKSUM in %1$s can't be null",
                    metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
                  false, false);
              } else {
                final String href = URLDecoder.decode(mdRef.getHref(), StandardCharsets.UTF_8);
                final StringBuilder message = new StringBuilder();
                if (structureValidatorState.isZipFileFlag()) {
                  final StringBuilder filePath = new StringBuilder();
                  if (metsValidatorState.isRootMets()) {
                    filePath.append(metsValidatorState.getMets().getOBJID()).append("/").append(href);
                  } else {
                    filePath.append(metsValidatorState.getMetsPath()).append(href);
                  }
                  if (!structureValidatorState.getZipManager().verifyChecksum(structureValidatorState.getIpPath(),
                    filePath.toString(), checksumType, checksum)) {
                    message.append("mets/dmdSec/mdRef/@CHECKSUM ").append(checksum).append(" and checksum of file (")
                      .append(Paths.get(metsValidatorState.getMetsPath()).resolve(filePath.toString()))
                      .append(") isn't equal (%1$s)");
                    return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
                      Message.createErrorMessage(message.toString(), metsValidatorState.getMetsName(),
                        metsValidatorState.isRootMets()),
                      false, false);
                  }
                } else {
                  if (!structureValidatorState.getFolderManager().verifyChecksum(
                    Paths.get(metsValidatorState.getMetsPath()).resolve(href), checksumType, checksum)) {
                    message.append("mets/dmdSec/mdRef/@CHECKSUM ").append(checksum).append(" and checksum of file (")
                      .append(Paths.get(metsValidatorState.getMetsPath())
                        .resolve(Paths.get(metsValidatorState.getMetsPath()).resolve(href)))
                      .append(") isn't equal (in %1$s)");
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
   * mets/amdSec/rightsMD/mdRef/@CHECKSUMTYPE The type of checksum following the
   * value list present in the METS-standard which has been used for calculating
   * the checksum for the referenced file.
   */
  protected ReporterDetails validateCSIP57(final MetsValidatorState metsValidatorState, final List<AmdSecType> amdSec) {
    final List<String> tmp = new ArrayList<>();
    for (CHECKSUMTYPE check : CHECKSUMTYPE.values()) {
      tmp.add(check.toString());
    }
    for (AmdSecType a : amdSec) {
      final List<MdSecType> rigthsMD = a.getRightsMD();
      if (rigthsMD != null && !rigthsMD.isEmpty()) {
        for (MdSecType rmd : rigthsMD) {
          final MdSecType.MdRef mdRef = rmd.getMdRef();
          final String checksumType = mdRef.getCHECKSUMTYPE();
          if (checksumType == null) {
            return new ReporterDetails(getVersion(),
              Message.createErrorMessage("mets/amdSec/rightsMD/mdRef/@CHECKSUMTYPE in %1$s can't be null",
                metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
              false, false);
          } else {
            if (!tmp.contains(checksumType)) {
                String message = "Value " + checksumType +
                        " in %1$s for mets/amdSec/rightsMD/mdRef/@CHECKSUMTYPE isn't valid";
              return new ReporterDetails(getVersion(), Message.createErrorMessage(message,
                metsValidatorState.getMetsName(), metsValidatorState.isRootMets()), false, false);
            }
          }
        }
      }
    }
    return new ReporterDetails();
  }
}
