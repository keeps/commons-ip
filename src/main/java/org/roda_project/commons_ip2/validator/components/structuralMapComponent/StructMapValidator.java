package org.roda_project.commons_ip2.validator.components.structuralMapComponent;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.roda_project.commons_ip2.mets_v1_12.beans.AmdSecType;
import org.roda_project.commons_ip2.mets_v1_12.beans.DivType;
import org.roda_project.commons_ip2.mets_v1_12.beans.MdSecType;
import org.roda_project.commons_ip2.mets_v1_12.beans.MetsType;
import org.roda_project.commons_ip2.mets_v1_12.beans.StructMapType;
import org.roda_project.commons_ip2.validator.common.MetsParser;
import org.roda_project.commons_ip2.validator.constants.Constants;
import org.roda_project.commons_ip2.validator.handlers.MetsHandler;
import org.roda_project.commons_ip2.validator.reporter.ReporterDetails;
import org.roda_project.commons_ip2.validator.state.MetsValidatorState;
import org.roda_project.commons_ip2.validator.state.StructureValidatorState;
import org.roda_project.commons_ip2.validator.utils.Message;

/**
 * @author Carlos Afonso <cafonso@keep.pt>
 */
public abstract class StructMapValidator {

  protected abstract String getCSIPVersion();

  /*
   * mets/structMap The structural map <structMap> element is the only mandatory
   * element in the METS. The <structMap> in the CSIP describes the highest
   * logical structure of the IP. Each METS file must include ONE structural map
   * <structMap> element used exactly as described here. Institutions can add
   * their own additional custom structural maps as separate <structMap> sections.
   */
  protected ReporterDetails validateCSIP80(final MetsValidatorState metsValidatorState) {
    final List<StructMapType> structMap = metsValidatorState.getMets().getStructMap();
    if (structMap == null) {
      return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
        Message.createErrorMessage("mets/structMap in %1$s can't be null", metsValidatorState.getMetsName(),
          metsValidatorState.isRootMets()),
        false, false);
    } else {
      int numberOfCSIPstructMaps = 0;
      for (StructMapType struct : structMap) {
        if (struct.getLABEL() != null && struct.getLABEL().equals("CSIP")) {
          numberOfCSIPstructMaps++;
        }
      }
      if (numberOfCSIPstructMaps != 1) {
        final String message = numberOfCSIPstructMaps == 0
          ? "Must have one structMap with the mets/structMap[@LABEL='CSIP'] in "
            + "%1$s doens't appear mets/structMap[@LABEL='CSIP']."
          : "Only one structMap with the mets/structMap/@LABEL value CSIP is allowed. " + "See %1$s";
        return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
          Message.createErrorMessage(message, metsValidatorState.getMetsName(), metsValidatorState.isRootMets()), false,
          false);
      }
    }
    return new ReporterDetails();
  }

  /*
   * mets/structMap[@TYPE='PHYSICAL'] The mets/structMap/@TYPE attribute must take
   * the value “PHYSICAL” from the vocabulary. See also: Structural map typing
   */
  protected ReporterDetails validateCSIP81(final MetsValidatorState metsValidatorState) {
    final List<StructMapType> structMap = metsValidatorState.getMets().getStructMap();
    if (structMap != null) {
      for (StructMapType struct : structMap) {
        final String type = struct.getTYPE();
        final String label = struct.getLABEL();
        if (label.equals("CSIP")) {
          if (type == null) {
            return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
              Message.createErrorMessage("mets/structMap[@TYPE='PHYSICAL'] in %1$s can't be null",
                metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
              false, false);
          } else {
            if (!type.equals("PHYSICAL")) {
              return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
                Message.createErrorMessage("mets/structMap[@TYPE='PHYSICAL'] value in %1$s must be PHYSICAL",
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
   * mets/structMap[@LABEL='CSIP'] The mets/structMap/@LABEL attribute value is
   * set to “CSIP” from the vocabulary. See also: Structural map label
   */
  protected ReporterDetails validateCSIP82(final MetsValidatorState metsValidatorState) {
    final List<StructMapType> structMap = metsValidatorState.getMets().getStructMap();
    if (structMap != null) {
      int numberOfCsipLabels = 0;
      for (StructMapType struct : structMap) {
        final String label = struct.getLABEL();
        if (label == null) {
          return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
            Message.createErrorMessage("mets/structMap[@LABEL='CSIP'] in %1$s can't be null",
              metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
            false, false);
        } else {
          if (label.equals("CSIP")) {
            numberOfCsipLabels++;
          }
        }
      }
      if (numberOfCsipLabels != 1) {
        return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
          Message.createErrorMessage("mets/structMap[@LABEL='CSIP'] value in %1$s must be CSIP",
            metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
          false, false);
      }
    }
    return new ReporterDetails();
  }

  /*
   * mets/structMap[@LABEL='CSIP']/@ID An xml:id identifier for the structural
   * description (structMap) used for internal package references. It must be
   * unique within the package.
   */
  protected ReporterDetails validateCSIP83(final MetsValidatorState metsValidatorState) {
    final List<StructMapType> structMap = metsValidatorState.getMets().getStructMap();
    if (structMap != null) {
      for (StructMapType struct : structMap) {
        final String id = struct.getID();
        if (id == null) {
          return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
            Message.createErrorMessage("mets/structMap[@LABEL='CSIP']/@ID in %1$s can't be null",
              metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
            false, false);
        } else {
          if (metsValidatorState.checkMetsInternalId(id)) {
              String message = "Value " + id +
                      " in %1$s for mets/structMap[@LABEL='CSIP']/@ID isn't unique in the package";
            return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, Message.createErrorMessage(
                    message, metsValidatorState.getMetsName(), metsValidatorState.isRootMets()), false, false);
          } else {
            metsValidatorState.addMetsInternalId(id);
          }
        }
      }
    }
    return new ReporterDetails();
  }

  /*
   * mets/structMap[@LABEL='CSIP']/div The structural map comprises a single
   * division.
   */
  protected ReporterDetails validateCSIP84(final MetsValidatorState metsValidatorState) {
    final List<StructMapType> structMap = metsValidatorState.getMets().getStructMap();
    if (structMap != null) {
      for (StructMapType struct : structMap) {
        if (struct.getLABEL().equals("CSIP")) {
          final DivType div = struct.getDiv();
          if (div == null) {
            return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
              Message.createErrorMessage("Must have a single division mets/structMap[@LABEL='CSIP']/div in %1$s",
                metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
              false, false);
          }
        }
      }
    }
    return new ReporterDetails();
  }

  /*
   * mets/structMap[@LABEL='CSIP']/div/@ID Mandatory, xml:id identifier must be
   * unique within the package.
   */
  protected ReporterDetails validateCSIP85(final MetsValidatorState metsValidatorState) {
    final List<StructMapType> structMap = metsValidatorState.getMets().getStructMap();
    if (structMap != null) {
      for (StructMapType struct : structMap) {
        final DivType div = struct.getDiv();
        if (div != null) {
          final String id = div.getID();
          if (id == null) {
            return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
              Message.createErrorMessage("mets/structMap[@LABEL='CSIP']/div/@ID %1$s can't be null",
                metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
              false, false);
          } else {
            if (metsValidatorState.checkMetsInternalId(id)) {
                String message = "Value " + id +
                        " %1$s for mets/structMap[@LABEL='CSIP']/div/@ID " + "isn't unique in the package";
              return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, Message.createErrorMessage(
                      message, metsValidatorState.getMetsName(), metsValidatorState.isRootMets()), false, false);
            } else {
              metsValidatorState.addMetsInternalId(id);
            }
          }
        }
      }
    }
    return new ReporterDetails();
  }

  /*
   * mets/structMap[@LABEL='CSIP']/div/@LABEL The package’s top-level structural
   * division <div> element’s @LABEL attribute value must be identical to the
   * package identifier, i.e. the same value as the mets/@OBJID attribute.
   */
  protected ReporterDetails validateCSIP86(final MetsValidatorState metsValidatorState) {
    final List<StructMapType> structMap = metsValidatorState.getMets().getStructMap();
    final String objid = metsValidatorState.getMets().getOBJID();
    if (structMap != null) {
      for (StructMapType struct : structMap) {
        final DivType div = struct.getDiv();
        if (div != null && struct.getLABEL().equals("CSIP")) {
          final String label = div.getLABEL();
          if (label == null) {
            return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
              Message.createErrorMessage("mets/structMap[@LABEL='CSIP']/div/@LABEL in %1$s can't be null",
                metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
              false, false);
          } else {
            if (!label.equals(objid)) {
              return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
                Message.createErrorMessage(
                  "mets/structMap[@LABEL='CSIP']/div/@LABEL " + "value in %1$s must be equal to the package identifier",
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
   * mets/structMap[@LABEL='CSIP']/div/div[@LABEL='Metadata'] The metadata
   * referenced in the administrative and/or descriptive metadata section is
   * described in the structural map with one sub division. When the transfer
   * consists of only administrative and/or descriptive metadata this is the only
   * sub division that occurs.
   */
  protected ReporterDetails validateCSIP88(final MetsValidatorState metsValidatorState) {
    final List<StructMapType> structMap = metsValidatorState.getMets().getStructMap();
    final List<MdSecType> dmdSec = metsValidatorState.getMets().getDmdSec();
    final List<AmdSecType> amdSec = metsValidatorState.getMets().getAmdSec();
    boolean isMetadata = false;
    if ((dmdSec != null && !dmdSec.isEmpty())) {
      for (MdSecType mdSecType : dmdSec) {
          if (mdSecType.getMdRef() != null) {
              isMetadata = true;
              break;
          }
      }
      if (!isMetadata && (amdSec != null && !amdSec.isEmpty())) {
        for (AmdSecType amdSecType : amdSec) {
          if (amdSecType.getDigiprovMD() != null && !amdSecType.getDigiprovMD().isEmpty()) {
            isMetadata = true;
          }
        }
      }
    } else {
      if (amdSec != null && !amdSec.isEmpty()) {
        for (AmdSecType amdSecType : amdSec) {
          if (amdSecType.getDigiprovMD() != null && !amdSecType.getDigiprovMD().isEmpty()) {
            isMetadata = true;
          }
        }
      }
    }
    if (isMetadata) {
      for (StructMapType struct : structMap) {
        if (struct.getLABEL() != null && struct.getLABEL().equals("CSIP")) {
          final List<DivType> divs = struct.getDiv().getDiv();
          int counter = 0;
          for (DivType d : divs) {
            if (d.getLABEL().equals("Metadata")) {
              counter++;
            }
          }
          if (counter == 0) {
            return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
              Message.createErrorMessage(
                "You have metadata files, must add mets/structMap[@LABEL='CSIP']"
                  + "/div/div[@LABEL='Metadata'] in %1$s",
                metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
              false, false);
          }
          if (counter > 1) {
            return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
              Message.createErrorMessage(
                "You have more than one mets/structMap[@LABEL='CSIP']" + "/div/div[@LABEL='Metadata'] in %1$s",
                metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
              false, false);
          }
        }
      }
    }
    return new ReporterDetails();
  }

  /*
   * Metadata division identifier
   * mets/structMap[@LABEL='CSIP']/div/div[@LABEL='Metadata']/@ID Mandatory,
   * xml:id identifier must be unique within the package.
   */
  protected ReporterDetails validateCSIP89(final MetsValidatorState metsValidatorState) {
    final List<StructMapType> structMap = metsValidatorState.getMets().getStructMap();
    if (structMap != null) {
      for (StructMapType struct : structMap) {
        final DivType div = struct.getDiv();
        if (div != null) {
          final List<DivType> divs = div.getDiv();
          for (DivType d : divs) {
            if (d.getLABEL() != null && d.getLABEL().equals("Metadata")) {
              final String id = d.getID();
              if (id == null) {
                return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
                  Message.createErrorMessage(
                    "mets/structMap[@LABEL='CSIP']/div" + "/div[@LABEL='Metadata']/@ID in %1$s can't be null",
                    metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
                  false, false);
              } else {
                if (metsValidatorState.checkMetsInternalId(id)) {
                    String message = "Value " + id + " in %1$s for mets/structMap[@LABEL='CSIP']"
                            + "/div/div[@LABEL='Metadata']/@ID isn't unique in the package";
                  return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
                    Message.createErrorMessage(message, metsValidatorState.getMetsName(),
                      metsValidatorState.isRootMets()),
                    false, false);
                } else {
                  metsValidatorState.addMetsInternalId(id);
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
   * mets/structMap[@LABEL='CSIP']/div/div[@LABEL='Metadata'] The metadata
   * division <div> element’s @LABEL attribute value must be “Metadata”. See also:
   * File group names
   */
  protected ReporterDetails validateCSIP90(final MetsValidatorState metsValidatorState) {
    final List<StructMapType> structMap = metsValidatorState.getMets().getStructMap();
    boolean found = false;
    if (structMap != null) {
      for (StructMapType struct : structMap) {
        final DivType div = struct.getDiv();
        if (div != null) {
          final List<DivType> divs = div.getDiv();
          for (DivType d : divs) {
              if (d.getLABEL() != null && d.getLABEL().equals("Metadata")) {
                  found = true;
                  break;
              }
          }
          if (!found) {
            return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
              Message.createErrorMessage("mets/structMap[@LABEL='CSIP']/div/div[@LABEL='Metadata'] in %1$s not found",
                metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
              false, false);
          }
        }
      }
    }
    return new ReporterDetails();
  }

  /*
   * mets/structMap[@LABEL='CSIP']/div/div[@LABEL='Metadata']/@ADMID When there is
   * administrative metadata and the amdSec is present, all administrative
   * metadata MUST be referenced via the administrative sections different
   * identifiers. All of the <amdSec> identifiers are listed in a single @ADMID
   * using spaces as delimiters.
   */
  protected ReporterDetails validateCSIP91(final MetsValidatorState metsValidatorState) {
    final List<StructMapType> structMap = metsValidatorState.getMets().getStructMap();
    final List<AmdSecType> amdSec = metsValidatorState.getMets().getAmdSec();
    final List<String> amdSecIDs = new ArrayList<>();
    if (amdSec != null && !amdSec.isEmpty()) {
      for (AmdSecType amdSecType : amdSec) {
        final List<MdSecType> allMDS = new ArrayList<>();
        allMDS.addAll(amdSecType.getDigiprovMD());
        allMDS.addAll(amdSecType.getRightsMD());
        allMDS.addAll(amdSecType.getTechMD());
        allMDS.addAll(amdSecType.getSourceMD());
        for (MdSecType mdSecType : allMDS) {
          amdSecIDs.add(mdSecType.getID());
        }
      }
    }
    if (structMap != null) {
      for (StructMapType struct : structMap) {
        final DivType div = struct.getDiv();
        if (div != null) {
          final List<DivType> divs = div.getDiv();
          for (DivType d : divs) {
            if (d.getLABEL() != null && d.getLABEL().matches("Metadata.*?")) {
              final List<Object> admids = d.getADMID();
              if (admids != null && !admids.isEmpty()) {
                for (Object o : admids) {
                  String admid;
                  if (o.getClass().toString().contains("AmdSecType")) {
                    admid = ((AmdSecType) o).getID();
                  } else {
                    admid = ((MdSecType) o).getID();
                  }
                  if (!amdSecIDs.contains(admid)) {
                      String message = "mets/structMap[@LABEL='CSIP']/div/div[@LABEL='Metadata']/@ADMID (" + admid +
                              ") doesn't match with any mets/amdSec/digiprovMD/@ID in %1$s";
                    return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
                      Message.createErrorMessage(message, metsValidatorState.getMetsName(),
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
   * Metadata division descriptive metadata referencing
   * mets/structMap[@LABEL='CSIP']/div/div[@LABEL='Metadata']/@DMDID When there
   * are descriptive metadata and one or more dmdSec is present, all descriptive
   * metadata MUST be referenced via the descriptive section identifiers. Every
   * <dmdSec> identifier is listed in a single @DMDID attribute using spaces as
   * delimiters.
   */
  protected ReporterDetails validateCSIP92(final MetsValidatorState metsValidatorState) {
    final List<StructMapType> structMap = metsValidatorState.getMets().getStructMap();
    final List<MdSecType> dmdSec = metsValidatorState.getMets().getDmdSec();
    final List<String> dmdSecIDs = new ArrayList<>();
    if (!dmdSec.isEmpty()) {
      for (MdSecType md : dmdSec) {
        dmdSecIDs.add(md.getID());
      }
    }
    if (structMap != null) {
      for (StructMapType struct : structMap) {
        final DivType div = struct.getDiv();
        if (div != null) {
          final List<DivType> divs = div.getDiv();
          for (DivType d : divs) {
            if (d.getLABEL() != null && d.getLABEL().matches("Metadata.*?")) {
              final List<Object> dmdids = d.getDMDID();
              if (!dmdids.isEmpty()) {
                for (Object o : dmdids) {
                  final String dmid = ((MdSecType) o).getID();
                  if (!dmdSecIDs.contains(dmid)) {
                      String message = "mets/structMap[@LABEL='CSIP']/div/div[@LABEL='Metadata']/@DMDID (" + dmid +
                              ") not match with any mets/dmdSec/mdRef/@ID in %1$s";
                    return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
                      Message.createErrorMessage(message, metsValidatorState.getMetsName(),
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
   * mets/structMap[@LABEL='CSIP']/div/div[@LABEL='Documentation'] The
   * documentation referenced in the file section file groups is described in the
   * structural map with one sub division.
   */
  protected ReporterDetails validateCSIP93(final MetsValidatorState metsValidatorState) {
    boolean found = false;
    final List<StructMapType> structMap = metsValidatorState.getMets().getStructMap();
    final MetsType.FileSec fileSec = metsValidatorState.getMets().getFileSec();
    if (fileSec != null) {
      final List<MetsType.FileSec.FileGrp> fileGrps = fileSec.getFileGrp();
      boolean existDocumentation = false;
      for (MetsType.FileSec.FileGrp fileGrp : fileGrps) {
        if (fileGrp.getUSE() != null && fileGrp.getUSE().equals("Documentation")) {
          existDocumentation = true;
          break;
        }
      }
      for (StructMapType struct : structMap) {
        final DivType div = struct.getDiv();
        if (div != null) {
          final List<DivType> divs = div.getDiv();
          for (DivType d : divs) {
            if (d.getLABEL() != null && d.getLABEL().equals("Documentation")) {
              found = true;
              break;
            }
          }
          if (existDocumentation && !found) {
            return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
              Message.createErrorMessage(
                "mets/structMap[@LABEL='CSIP']" + "/div/div[@LABEL='Documentation'] must be added in %1$s",
                metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
              false, false);
          } else {
            if (!existDocumentation && found) {
              return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
                Message.createErrorMessage("mets/fileSec/fileGrp[@USE='Documentation'] must be added in %1$s",
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
   * Metadata division identifier
   * mets/structMap[@LABEL='CSIP']/div/div[@LABEL='Documentation']/@ID Mandatory,
   * xml:id identifier must be unique within the package.
   */
  protected ReporterDetails validateCSIP94(final MetsValidatorState metsValidatorState) {
    final List<StructMapType> structMap = metsValidatorState.getMets().getStructMap();
    if (structMap != null) {
      for (StructMapType struct : structMap) {
        final DivType div = struct.getDiv();
        if (div != null) {
          final List<DivType> divs = div.getDiv();
          for (DivType d : divs) {
            if (d.getLABEL() != null && d.getLABEL().equals("Documentation")) {
              final String id = d.getID();
              if (id == null) {
                return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
                  Message.createErrorMessage(
                    "mets/structMap[@LABEL='CSIP']/div" + "/div[@LABEL='Documentation']/@ID in %1$s can't be null",
                    metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
                  false, false);
              } else {
                if (metsValidatorState.checkMetsInternalId(id)) {
                    String message = "Value " + id + " in %1$s for mets/structMap[@LABEL='CSIP']/div"
                            + "/div[@LABEL='Documentation']/@ID isn't unique in the package";
                  return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
                    Message.createErrorMessage(message, metsValidatorState.getMetsName(),
                      metsValidatorState.isRootMets()),
                    false, false);
                } else {
                  metsValidatorState.addMetsInternalId(id);
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
   * mets/structMap[@LABEL='CSIP']/div/div[@LABEL='Documentation'] The
   * documentation division <div> element in the package uses the value
   * “Documentation” from the vocabulary as the value for the @LABEL attribute.
   * See also: File group names
   */
  protected ReporterDetails validateCSIP95(final MetsValidatorState metsValidatorState) {
    boolean found = false;
    final List<StructMapType> structMap = metsValidatorState.getMets().getStructMap();
    if (structMap != null) {
      for (StructMapType struct : structMap) {
        final DivType div = struct.getDiv();
        if (div != null) {
          final List<DivType> divs = div.getDiv();
          for (DivType d : divs) {
            if (d.getLABEL() != null && d.getLABEL().equals("Documentation")) {
              found = true;
              break;
            }
          }
          if (found) {
            break;
          }
        }
      }
    }
    if (!found) {
      return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
        Message.createErrorMessage("mets/structMap[@LABEL='CSIP']/div/div[@LABEL='Documentation'] in %1$s not found",
          metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
        false, false);
    }
    return new ReporterDetails();
  }

  /*
   * mets/structMap[@LABEL='CSIP']/div/div[@LABEL='Documentation']/fptr All file
   * groups containing documentation described in the package are referenced via
   * the relevant file group identifiers. There MUST be one file group reference
   * per <fptr> element.
   */
  protected ReporterDetails validateCSIP96(final MetsValidatorState metsValidatorState) {
    final List<StructMapType> structMap = metsValidatorState.getMets().getStructMap();
    final MetsType.FileSec fileSec = metsValidatorState.getMets().getFileSec();
    final List<MetsType.FileSec.FileGrp> fileGrps;
    if (fileSec != null) {
      fileGrps = metsValidatorState.getMets().getFileSec().getFileGrp();
    } else {
      fileGrps = new ArrayList<>();
    }
    int fileGrpDocumentation = 0;
    int structDocumentation = 0;
    if (structMap != null) {
      if (fileGrps != null && !fileGrps.isEmpty()) {
        for (MetsType.FileSec.FileGrp fileGrp : fileGrps) {
          if (fileGrp.getUSE() != null && fileGrp.getUSE().equals("Documentation")) {
            if (!fileGrp.getFile().isEmpty()) {
              fileGrpDocumentation++;
            }
          }
        }
      }
      for (StructMapType struct : structMap) {
        final DivType div = struct.getDiv();
        if (div != null) {
          final List<DivType> divs = div.getDiv();
          for (DivType d : divs) {
            if (d.getLABEL() != null && d.getLABEL().equals("Documentation")) {
              final List<DivType.Fptr> ftprs = d.getFptr();
              structDocumentation = ftprs.size();
            }
          }
        }
      }

      if (fileGrpDocumentation != structDocumentation) {
        return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
          Message.createErrorMessage(
            "In %1$s must be one file group reference per "
              + "mets/structMap[@LABEL='CSIP']/div/div[@LABEL='Documentation']/fptr ",
            metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
          false, false);
      }
    }
    return new ReporterDetails();
  }

  /*
   * mets/structMap[@LABEL='CSIP']/div/div[@LABEL='Documentation']/fptr/@FILEID A
   * reference, by ID, to the “Documentation” file group. Related to the
   * requirements CSIP60 which describes the “Documentation” file group and CSIP65
   * which describes the file group identifier.
   */
  protected ReporterDetails validateCSIP116(final MetsValidatorState metsValidatorState) {
    final List<StructMapType> structMap = metsValidatorState.getMets().getStructMap();
    final MetsType.FileSec fileSec = metsValidatorState.getMets().getFileSec();
    final List<MetsType.FileSec.FileGrp> fileGrps;
    if (fileSec != null) {
      fileGrps = metsValidatorState.getMets().getFileSec().getFileGrp();
    } else {
      fileGrps = new ArrayList<>();
    }
    boolean found = false;
    if (structMap != null) {
      for (StructMapType struct : structMap) {
        final DivType div = struct.getDiv();
        if (div != null) {
          final List<DivType> divs = div.getDiv();
          for (DivType d : divs) {
            if (d.getLABEL() != null && d.getLABEL().equals("Documentation")) {
              final List<DivType.Fptr> ftprs = d.getFptr();
              if (ftprs != null && !ftprs.isEmpty()) {
                for (DivType.Fptr fptr : ftprs) {
                  final String fileid = ((MetsType.FileSec.FileGrp) fptr.getFILEID()).getID();
                  for (MetsType.FileSec.FileGrp fileGrp : fileGrps) {
                    if (fileGrp.getUSE() != null && fileGrp.getUSE().equals("Documentation")) {
                      final String id = fileGrp.getID();
                      if (id.equals(fileid)) {
                        found = true;
                      }
                    }
                  }
                  if (!found) {
                      String message = "Value " + fileid +
                              " in %1$s for mets/structMap[@LABEL='CSIP']/div" + "/div[@LABEL='Documentation']"
                              + "/fptr/@FILEID doesn't match with " + "any mets/fileSec/fileGrp/@ID  ";
                    return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
                      Message.createErrorMessage(message, metsValidatorState.getMetsName(),
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
   * mets/structMap[@LABEL='CSIP']/div/div[@LABEL='Schemas'] The schemas
   * referenced in the file section file groups are described in the structural
   * map within a single sub-division.
   */
  protected ReporterDetails validateCSIP97(final MetsValidatorState metsValidatorState) {
    boolean found = false;
    final List<StructMapType> structMap = metsValidatorState.getMets().getStructMap();
    final MetsType.FileSec fileSec = metsValidatorState.getMets().getFileSec();
    if (fileSec != null) {
      final List<MetsType.FileSec.FileGrp> fileGrps = fileSec.getFileGrp();
      boolean existSchemas = false;
      for (MetsType.FileSec.FileGrp fileGrp : fileGrps) {
        if (fileGrp.getUSE() != null && fileGrp.getUSE().equals("Schemas")) {
          existSchemas = true;
          break;
        }
      }
      for (StructMapType struct : structMap) {
        final DivType div = struct.getDiv();
        if (div != null) {
          final List<DivType> divs = div.getDiv();
          for (DivType d : divs) {
            if (d.getLABEL() != null && d.getLABEL().equals("Schemas")) {
              found = true;
              break;
            }
          }
          if (existSchemas && !found) {
            return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
              Message.createErrorMessage(
                "mets/structMap[@LABEL='CSIP']/div/div[@LABEL='Schemas'] must be added in %1$s",
                metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
              false, false);
          } else {
            if (!existSchemas && found) {
              return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
                Message.createErrorMessage("mets/fileSec/fileGrp[@USE='Schemas'] must be added in %1$s",
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
   * mets/structMap[@LABEL='CSIP']/div/div[@LABEL='Schemas']/@ID Mandatory, xml:id
   * identifier must be unique within the package.
   */
  protected ReporterDetails validateCSIP98(final MetsValidatorState metsValidatorState) {
    final List<StructMapType> structMap = metsValidatorState.getMets().getStructMap();
    if (structMap != null) {
      for (StructMapType struct : structMap) {
        final DivType div = struct.getDiv();
        if (div != null) {
          final List<DivType> divs = div.getDiv();
          for (DivType d : divs) {
            if (d.getLABEL() != null && d.getLABEL().equals("Schemas")) {
              final String id = d.getID();
              if (id == null) {
                return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
                  Message.createErrorMessage(
                    "mets/structMap[@LABEL='CSIP']/div" + "/div[@LABEL='Schemas']/@ID in %1$s can't be null",
                    metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
                  false, false);
              } else {
                if (metsValidatorState.checkMetsInternalId(id)) {
                    String message = "Value " + id + " in %1$s for mets/structMap[@LABEL='CSIP']/div"
                            + "/div[@LABEL='Schemas']/@ID isn't unique in the package";
                  return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
                    Message.createErrorMessage(message, metsValidatorState.getMetsName(),
                      metsValidatorState.isRootMets()),
                    false, false);
                } else {
                  metsValidatorState.addMetsInternalId(id);
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
   * mets/structMap[@LABEL='CSIP']/div/div[@LABEL='Schemas'] The schema division
   * <div> element’s @LABEL attribute has the value “Schemas” from the vocabulary.
   * See also: File group names
   */
  protected ReporterDetails validateCSIP99(final MetsValidatorState metsValidatorState) {
    boolean found = false;
    final List<StructMapType> structMap = metsValidatorState.getMets().getStructMap();
    if (structMap != null) {
      for (StructMapType struct : structMap) {
        final DivType div = struct.getDiv();
        if (div != null) {
          final List<DivType> divs = div.getDiv();
          for (DivType d : divs) {
            if (d.getLABEL() != null && d.getLABEL().equals("Schemas")) {
              found = true;
              break;
            }
          }
          if (found) {
            break;
          }
        }
      }
    }
    if (!found) {
      return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
        Message.createErrorMessage("mets/structMap[@LABEL='CSIP']/div/div[@LABEL='Schemas'] not found in %1$s",
          metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
        false, false);
    }
    return new ReporterDetails();
  }

  /*
   * mets/structMap[@LABEL='CSIP']/div/div[@LABEL='Schemas']/fptr All file groups
   * containing schemas described in the package are referenced via the relevant
   * file group identifiers. One file group reference per fptr-element
   */
  protected ReporterDetails validateCSIP100(final MetsValidatorState metsValidatorState) {
    final List<StructMapType> structMap = metsValidatorState.getMets().getStructMap();
    final MetsType.FileSec fileSec = metsValidatorState.getMets().getFileSec();
    final List<MetsType.FileSec.FileGrp> fileGrps;
    if (fileSec != null) {
      fileGrps = metsValidatorState.getMets().getFileSec().getFileGrp();
    } else {
      fileGrps = new ArrayList<>();
    }
    int fileGrpSchemas = 0;
    int structSchemas = 0;
    if (structMap != null) {
      if (fileGrps != null && !fileGrps.isEmpty()) {
        for (MetsType.FileSec.FileGrp fileGrp : fileGrps) {
          if (fileGrp.getUSE() != null && fileGrp.getUSE().equals("Schemas")) {
            if (!fileGrp.getFile().isEmpty()) {
              fileGrpSchemas++;
            }
          }
        }
      }
      for (StructMapType struct : structMap) {
        final DivType div = struct.getDiv();
        if (div != null) {
          final List<DivType> divs = div.getDiv();
          for (DivType d : divs) {
            if (d.getLABEL() != null && d.getLABEL().equals("Schemas")) {
              final List<DivType.Fptr> ftprs = d.getFptr();
              structSchemas = ftprs.size();
            }
          }
        }
      }

      if (fileGrpSchemas != structSchemas) {
        return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
          Message.createErrorMessage(
            "In %1$s must be one file group reference per "
              + "mets/structMap[@LABEL='CSIP']/div/div[@LABEL='Schemas']/fptr ",
            metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
          false, false);
      }
    }
    return new ReporterDetails();
  }

  /*
   * mets/structMap[@LABEL='CSIP']/div/div[@LABEL='Schemas']/fptr/@FILEID The
   * pointer to the identifier for the “Schema” file group. Related to the
   * requirements CSIP113 which describes the “Schema” file group and CSIP65 which
   * describes the file group identifier.
   */
  protected ReporterDetails validateCSIP118(final MetsValidatorState metsValidatorState) {
    final List<StructMapType> structMap = metsValidatorState.getMets().getStructMap();
    final MetsType.FileSec fileSec = metsValidatorState.getMets().getFileSec();
    final List<MetsType.FileSec.FileGrp> fileGrps;
    if (fileSec != null) {
      fileGrps = metsValidatorState.getMets().getFileSec().getFileGrp();
    } else {
      fileGrps = new ArrayList<>();
    }
    boolean found = false;
    if (structMap != null) {
      for (StructMapType struct : structMap) {
        final DivType div = struct.getDiv();
        if (div != null) {
          final List<DivType> divs = div.getDiv();
          for (DivType d : divs) {
            if (d.getLABEL() != null && d.getLABEL().equals("Schemas")) {
              final List<DivType.Fptr> ftprs = d.getFptr();
              if (ftprs != null && !ftprs.isEmpty()) {
                for (DivType.Fptr fptr : ftprs) {
                  final String fileid = ((MetsType.FileSec.FileGrp) fptr.getFILEID()).getID();
                  for (MetsType.FileSec.FileGrp fileGrp : fileGrps) {
                    if (fileGrp.getUSE() != null && fileGrp.getUSE().equals("Schemas")) {
                      final String id = fileGrp.getID();
                      if (id.equals(fileid)) {
                        found = true;
                      }
                    }
                  }
                  if (!found) {
                      String message = "Value " + fileid +
                              " In %1$s for mets/structMap[@LABEL='CSIP']/div/div[@LABEL='Schemas']"
                              + "/fptr/@FILEID doesn't match with any mets/fileSec/fileGrp/@ID ";
                    return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
                      Message.createErrorMessage(message, metsValidatorState.getMetsName(),
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
   * mets/structMap[@LABEL='CSIP']/div/div[@LABEL='Representations'] When no
   * representations are present the content referenced in the file section file
   * group with @USE attribute value “Representations” is described in the
   * structural map as a single sub division.
   */
  protected ReporterDetails validateCSIP101(final MetsValidatorState metsValidatorState) {
    boolean found = false;
    final List<StructMapType> structMap = metsValidatorState.getMets().getStructMap();
    final MetsType.FileSec fileSec = metsValidatorState.getMets().getFileSec();
    if (fileSec != null) {
      final List<MetsType.FileSec.FileGrp> fileGrps = fileSec.getFileGrp();
      boolean existRepresentations = false;
      for (MetsType.FileSec.FileGrp fileGrp : fileGrps) {
        if (fileGrp.getUSE() != null && fileGrp.getUSE().equals("Representations")) {
          existRepresentations = true;
          break;
        }
      }
      for (StructMapType struct : structMap) {
        final DivType div = struct.getDiv();
        if (div != null) {
          final List<DivType> divs = div.getDiv();
          for (DivType d : divs) {
            if (d.getLABEL() != null && d.getLABEL().equals("Representations")) {
              found = true;
              break;
            }
          }
          if (existRepresentations && !found) {
            return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
              Message.createErrorMessage(
                "mets/structMap[@LABEL='CSIP']/div" + "/div[@LABEL='Representations'] must be added in %1$s",
                metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
              false, false);
          } else {
            if (!existRepresentations && found) {
              return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
                Message.createErrorMessage("mets/fileSec/fileGrp[@USE='Representations'] must be added in %1$s",
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
   * mets/structMap[@LABEL='CSIP']/div/div[@LABEL='Representations']/@ID
   * Mandatory, xml:id identifier must be unique within the package.
   */
  protected ReporterDetails validateCSIP102(final MetsValidatorState metsValidatorState) {
    final List<StructMapType> structMap = metsValidatorState.getMets().getStructMap();
    if (structMap != null) {
      for (StructMapType struct : structMap) {
        final DivType div = struct.getDiv();
        if (div != null) {
          final List<DivType> divs = div.getDiv();
          for (DivType d : divs) {
            if (d.getLABEL() != null && d.getLABEL().equals("Representations")) {
              final String id = d.getID();
              if (id == null) {
                return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
                  Message.createErrorMessage(
                    "mets/structMap[@LABEL='CSIP']/div" + "/div[@LABEL='Representations']/@ID in %1$s can't be null",
                    metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
                  false, false);
              } else {
                if (metsValidatorState.checkMetsInternalId(id)) {
                    String message = "Value " + id + " in %1$s for mets/structMap[@LABEL='CSIP']/div"
                            + "/div[@LABEL='Representations']/@ID isn't unique in the package";
                  return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
                    Message.createErrorMessage(message, metsValidatorState.getMetsName(),
                      metsValidatorState.isRootMets()),
                    false, false);
                } else {
                  metsValidatorState.addMetsInternalId(id);
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
   * mets/structMap[@LABEL='CSIP']/div/div[@LABEL='Representations'] The package’s
   * content division <div> element must have the @LABEL attribute value
   * “Representations”, taken from the vocabulary. See also: File group names
   */
  protected ReporterDetails validateCSIP103(final StructureValidatorState structureValidatorState,
    final MetsValidatorState metsValidatorState) throws IOException {
    boolean found = false;
    final List<StructMapType> structMap = metsValidatorState.getMets().getStructMap();
    final boolean existSubMets;
    if (structMap != null) {
      if (structureValidatorState.isZipFileFlag()) {
        existSubMets = structureValidatorState.getZipManager()
          .checkIfExistsSubMets(structureValidatorState.getIpPath());
      } else {
        existSubMets = structureValidatorState.getFolderManager()
          .checkIfExistsSubMets(structureValidatorState.getIpPath());
      }
      if (existSubMets) {
        return new ReporterDetails();
      } else {
        for (StructMapType struct : structMap) {
          final DivType div = struct.getDiv();
          if (div != null) {
            final List<DivType> divs = div.getDiv();
            for (DivType d : divs) {
              if (d.getLABEL() != null && d.getLABEL().equals("Representations")) {
                found = true;
                break;
              }
            }
            if (!found) {
              return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
                Message.createErrorMessage(
                  "mets/structMap[@LABEL='CSIP']/div" + "/div[@LABEL='Representations'] not found in %1$s",
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
   * mets/structMap[@LABEL='CSIP']/div/div[@LABEL='Representations']/fptr All file
   * groups containing content described in the package are referenced via the
   * relevant file group identifiers. One file group reference per fptr-element.
   */
  protected ReporterDetails validateCSIP104(final MetsValidatorState metsValidatorState) {
    final List<StructMapType> structMap = metsValidatorState.getMets().getStructMap();
    final MetsType.FileSec fileSec = metsValidatorState.getMets().getFileSec();
    final List<MetsType.FileSec.FileGrp> fileGrps;
    if (fileSec != null) {
      fileGrps = metsValidatorState.getMets().getFileSec().getFileGrp();
    } else {
      fileGrps = new ArrayList<>();
    }
    boolean isMptr = false;
    int fileGrpRepresentations = 0;
    int structRepresentations = 0;
    if (structMap != null) {
      if (fileGrps != null && !fileGrps.isEmpty()) {
        for (MetsType.FileSec.FileGrp fileGrp : fileGrps) {
          if (fileGrp.getUSE().equals("Representations")) {
            fileGrpRepresentations++;
          }
        }
      }
      for (StructMapType struct : structMap) {
        final DivType div = struct.getDiv();
        if (div != null) {
          final List<DivType> divs = div.getDiv();
          for (DivType d : divs) {
            if (d.getLABEL() != null && d.getLABEL().equals("Representations")) {
              final List<DivType.Fptr> ftprs = d.getFptr();
              if (!ftprs.isEmpty()) {
                structRepresentations = ftprs.size();
              } else {
                if (!d.getMptr().isEmpty()) {
                  isMptr = true;
                }
              }
            }
          }
        }
      }

      if (fileGrpRepresentations != structRepresentations && !isMptr) {
        return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
          Message.createErrorMessage(
            "In %1$s must be one file group reference per "
              + "mets/structMap[@LABEL='CSIP']/div/div[@LABEL='Schemas']/fptr ",
            metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
          false, false);
      }
    }
    return new ReporterDetails();
  }

  /*
   * mets/structMap[@LABEL='CSIP']/div/div[@LABEL='Representations']/fptr/@FILEID
   * The pointer to the identifier for the “Representations” file group. Related
   * to the requirements CSIP114 which describes the “Representations” file group
   * and CSIP65 which describes the file group identifier.
   */
  protected ReporterDetails validateCSIP119(final MetsValidatorState metsValidatorState) {
    final List<StructMapType> structMap = metsValidatorState.getMets().getStructMap();
    final MetsType.FileSec fileSec = metsValidatorState.getMets().getFileSec();
    final List<MetsType.FileSec.FileGrp> fileGrps;
    if (fileSec != null) {
      fileGrps = metsValidatorState.getMets().getFileSec().getFileGrp();
    } else {
      fileGrps = new ArrayList<>();
    }
    boolean found = false;
    if (structMap != null) {
      for (StructMapType struct : structMap) {
        final DivType div = struct.getDiv();
        if (div != null) {
          final List<DivType> divs = div.getDiv();
          for (DivType d : divs) {
            if (d.getLABEL() != null && d.getLABEL().equals("Representations")) {
              final List<DivType.Fptr> ftprs = d.getFptr();
              if (ftprs != null && !ftprs.isEmpty()) {
                for (DivType.Fptr fptr : ftprs) {
                  final String fileid = ((MetsType.FileSec.FileGrp) fptr.getFILEID()).getID();
                  for (MetsType.FileSec.FileGrp fileGrp : fileGrps) {
                    if (fileGrp.getUSE().equals("Representations")) {
                      final String id = fileGrp.getID();
                      if (id.equals(fileid)) {
                        found = true;
                      }
                    }
                  }
                  if (!found) {
                      String message = "mets/structMap[@LABEL='CSIP']/div/div" + "[@LABEL='Representations']/fptr/@FILEID (" +
                              fileid + ") doesn't match with any mets/fileSec/fileGrp/@ID in %1$s";
                    return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
                      Message.createErrorMessage(message, metsValidatorState.getMetsName(),
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
   * mets/structMap[@LABEL='CSIP']/div/div When a package consists of multiple
   * representations, each described by a representation level METS.xml document,
   * there is a discrete representation div element for each representation. Each
   * representation div references the representation level METS.xml document,
   * documenting the structure of the package and its constituent representations.
   */
  protected ReporterDetails validateCSIP105(final MetsValidatorState metsValidatorState) {
    final List<StructMapType> structMap = metsValidatorState.getMets().getStructMap();
    if (!structMap.isEmpty()) {
      if (metsValidatorState.isRootMets()) {
        for (StructMapType struct : structMap) {
          final DivType firstDiv = struct.getDiv();
          if (firstDiv != null && struct.getLABEL().equals("CSIP")) {
            final List<DivType> divs = firstDiv.getDiv();
            for (DivType div : divs) {
              if (div.getLABEL().matches("Representations/.*/") && div.getMptr().isEmpty()) {
                return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
                  Message.createErrorMessage(
                    "When a package consists of multiple representations, "
                      + "each described by a representation level METS.xml "
                      + "document, there is a discrete representation div " + "element for each representation (%1$s)",
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
   * mets/structMap[@LABEL='CSIP']/div/div/@ID Mandatory, xml:id identifier must
   * be unique within the package.
   */
  protected ReporterDetails validateCSIP106(final MetsValidatorState metsValidatorState) {
    final List<StructMapType> structMap = metsValidatorState.getMets().getStructMap();
    if (structMap != null) {
      for (StructMapType struct : structMap) {
        if (struct.getLABEL() != null && struct.getLABEL().equals("CSIP")) {
          final List<DivType> divs = struct.getDiv().getDiv();
          for (DivType div : divs) {
            final String id = div.getID();
            if (id == null) {
              return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
                Message.createErrorMessage("mets/structMap[@LABEL='CSIP']/div/div/@ID in %1$s can't be null",
                  metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
                false, false);
            } else {
              if (metsValidatorState.checkMetsInternalId(id)) {
                  String message = "Value " + id +
                          " in %1$s for mets/structMap[@LABEL='CSIP']" + "/div/div/@ID isn't unique in the package";
                return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION, Message.createErrorMessage(
                        message, metsValidatorState.getMetsName(), metsValidatorState.isRootMets()), false, false);
              } else {
                metsValidatorState.addMetsInternalId(id);
              }
            }
          }
        }
      }
    }
    return new ReporterDetails();
  }

  /*
   * mets/structMap[@LABEL='CSIP']/div/div/@LABEL The package’s representation
   * division <div> element @LABEL attribute value must be the path to the
   * representation level METS document. This requirement gives the same value to
   * be used as the requirement named “File group identifier” (CSIP64) See also:
   * File group names Preciso teste
   */
  protected ReporterDetails validateCSIP107(final StructureValidatorState structureValidatorState,
    final MetsValidatorState metsValidatorState) throws IOException {
    final List<StructMapType> structMap = metsValidatorState.getMets().getStructMap();
    final StringBuilder message = new StringBuilder();
    if (structMap != null) {
      for (StructMapType struct : structMap) {
        final DivType div = struct.getDiv();
        if (div != null && struct.getLABEL().equals("CSIP")) {
          final List<DivType> divs = div.getDiv();
          for (DivType d : divs) {
            final String label = d.getLABEL();
            if (d.getLABEL() != null) {
              if (structureValidatorState.isZipFileFlag()) {
                final StringBuilder path = new StringBuilder();
                if (metsValidatorState.isRootMets()) {
                  if (metsValidatorState.getMets().getOBJID() != null) {
                    path.append(metsValidatorState.getMets().getOBJID()).append("/").append(label.toLowerCase());
                  } else {
                    return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
                      Message.createErrorMessage("mets/OBJECTID in %1$s can't be null",
                        metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
                      false, false);
                  }
                } else {
                  path.append(metsValidatorState.getMetsPath()).append(label.toLowerCase());
                }
                if (!structureValidatorState.getZipManager().checkDirectory(structureValidatorState.getIpPath(),
                  path.toString())) {
                  message.append("mets/structMap[@LABEL='CSIP']/div/div/@LABEL in %1$s ( ").append(label).append(" )")
                    .append("does not lead to a directory ( ").append(path).append(" )");
                  return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
                    Message.createErrorMessage(message.toString(), metsValidatorState.getMetsName(),
                      metsValidatorState.isRootMets()),
                    false, false);
                }
              } else {
                if (!structureValidatorState.getFolderManager()
                  .checkDirectory(Paths.get(metsValidatorState.getMetsPath()).resolve(label.toLowerCase()))) {
                  message.append("mets/structMap[@LABEL='CSIP']/div/div/@LABEL in %1$s ( ").append(label).append(" )")
                    .append("does not lead to a directory ( ")
                    .append(Paths.get(metsValidatorState.getMetsPath()).resolve(label.toLowerCase())).append(" )");
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
   * mets/structMap[@LABEL='CSIP']/div/div/mptr/@xlink:title The file group
   * containing the files described in the package are referenced via the relevant
   * file group identifier. Related to the requirements CSIP114 which describes
   * the “Representations” file group and CSIP65 which describes the file group
   * identifier.
   */
  protected ReporterDetails validateCSIP108(final MetsValidatorState metsValidatorState) {
    final List<StructMapType> structMap = metsValidatorState.getMets().getStructMap();
    final MetsType.FileSec fileSec = metsValidatorState.getMets().getFileSec();
    if (structMap != null) {
      for (StructMapType struct : structMap) {
        final DivType div = struct.getDiv();
        if (div != null && struct.getLABEL().equals("CSIP")) {
          final List<DivType> divs = div.getDiv();
          for (DivType d : divs) {
            if (d.getLABEL() != null && d.getLABEL().matches("Representations/.*")) {
              final List<DivType.Mptr> mptrs = d.getMptr();
              if (mptrs != null && !mptrs.isEmpty()) {
                for (DivType.Mptr mptr : mptrs) {
                  final String title = mptr.getTitle();
                  if (title != null) {
                    final List<MetsType.FileSec.FileGrp> fileGrps = fileSec.getFileGrp();
                    boolean found = false;
                    for (MetsType.FileSec.FileGrp fileGrp : fileGrps) {
                        if (title.equals(fileGrp.getID())) {
                            found = true;
                            break;
                        }
                    }
                    if (!found) {
                        String message = "mets/structMap[@LABEL='CSIP']/div/div/mptr/@xlink:title in %1$s (" + title +
                                ") does not correspond a file group ID";
                      return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
                        Message.createErrorMessage(message, metsValidatorState.getMetsName(),
                          metsValidatorState.isRootMets()),
                        false, false);
                    }
                  } else {
                    return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
                      Message.createErrorMessage(
                        "mets/structMap[@LABEL='CSIP']" + "/div/div/mptr/@xlink:title in %1$s can't be null",
                        metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
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
   * mets/structMap[@LABEL='CSIP']/div/div/mptr The division <div> of the specific
   * representation includes one occurrence of the METS pointer <mptr> element,
   * pointing to the appropriate representation METS file.
   */
  protected ReporterDetails validateCSIP109(final MetsValidatorState metsValidatorState) {
    final List<StructMapType> structMap = metsValidatorState.getMets().getStructMap();
    if (structMap != null) {
      for (StructMapType struct : structMap) {
        final DivType div = struct.getDiv();
        if (div != null && struct.getLABEL().equals("CSIP")) {
          final List<DivType> divs = div.getDiv();
          for (DivType d : divs) {
            if (d.getLABEL() != null && d.getLABEL().matches("Representations/.*")) {
              final List<DivType.Mptr> mptrs = d.getMptr();
              if (d.getFptr().isEmpty()) {
                if (mptrs == null || mptrs.size() != 1) {
                  return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
                    Message.createErrorMessage(
                      "mets/structMap[@LABEL='CSIP']/div" + "/div/mptr in %1$s can't be null or more than one",
                      metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
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
   * mets/structMap/div/div/mptr/@xlink:href The actual location of the resource.
   * We recommend recording a URL type filepath within this attribute.
   */
  protected ReporterDetails validateCSIP110(final StructureValidatorState structureValidatorState,
    final MetsValidatorState metsValidatorState) throws IOException {
    final List<StructMapType> structMap = metsValidatorState.getMets().getStructMap();
    final StringBuilder message = new StringBuilder();
    if (structMap != null) {
      for (StructMapType struct : structMap) {
        final DivType div = struct.getDiv();
        if (div != null && struct.getLABEL().equals("CSIP")) {
          final List<DivType> divs = div.getDiv();
          for (DivType d : divs) {
            if (d.getLABEL().matches("Representations/.*")) {
              final List<DivType.Mptr> mptrs = d.getMptr();
              if (!mptrs.isEmpty()) {
                for (DivType.Mptr mptr : mptrs) {
                  final String href = URLDecoder.decode(mptr.getHref(), StandardCharsets.UTF_8);
                  if (structureValidatorState.isZipFileFlag()) {
                    final StringBuilder filePath = new StringBuilder();
                    if (metsValidatorState.isRootMets()) {
                      if (metsValidatorState.getMets().getOBJID() != null) {
                        filePath.append(metsValidatorState.getMets().getOBJID()).append("/").append(href);
                      } else {
                        return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
                          Message.createErrorMessage("mets/@OBJECTID in %1$s can't be null",
                            metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
                          false, false);
                      }
                    } else {
                      filePath.append(metsValidatorState.getMetsPath()).append(href);
                    }
                    if (!structureValidatorState.getZipManager().checkPathExists(structureValidatorState.getIpPath(),
                      filePath.toString())) {
                      message.append("mets/structMap/div/div/mptr/@xlink:href  ").append(filePath)
                        .append(" doesn't exists (in %1$s)");
                      return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
                        Message.createErrorMessage(message.toString(), metsValidatorState.getMetsName(),
                          metsValidatorState.isRootMets()),
                        false, false);
                    }
                  } else {
                    if (!structureValidatorState.getFolderManager()
                      .checkPathExists(Paths.get(metsValidatorState.getMetsPath()).resolve(href))) {
                      message.append("mets/structMap/div/div/mptr/@xlink:href ")
                        .append(Paths.get(metsValidatorState.getMetsPath()).resolve(href))
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
      }
    }
    return new ReporterDetails();
  }

  /*
   * mets/structMap/div/div/mptr[@xlink:type='simple'] Attribute used with the
   * value “simple”. Value list is maintained by the xlink standard.
   */
  protected ReporterDetails validateCSIP111(final StructureValidatorState structureValidatorState,
    final MetsValidatorState metsValidatorState) throws IOException {
    final List<StructMapType> structMap = metsValidatorState.getMets().getStructMap();
    final HashMap<String, String> structMapTypes = new HashMap<>();
    final MetsHandler fileSecHandler = new MetsHandler("div", "mptr", structMapTypes);
    final MetsParser metsParser = new MetsParser();
    InputStream metsStream = null;
    if (!structMap.isEmpty()) {
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
    if (!structMap.isEmpty()) {
      for (StructMapType struct : structMap) {
        final DivType div = struct.getDiv();
        if (div != null && struct.getLABEL().equals("CSIP")) {
          final List<DivType> divs = div.getDiv();
          for (DivType d : divs) {
            if (d.getLABEL() != null && d.getLABEL().matches("Representations/")) {
              final List<DivType.Mptr> mptrs = d.getMptr();
              if (!mptrs.isEmpty()) {
                for (DivType.Mptr mptr : mptrs) {
                  if (structMapTypes.get(mptr.getHref()) != null) {
                    if (!structMapTypes.get(mptr.getHref()).equals("simple")) {
                      return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
                        Message.createErrorMessage(
                          "mets/structMap/div/div/" + "mptr[@xlink:type='simple'] value in %1$s must be 'simple'",
                          metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
                        false, false);
                    }
                  } else {
                    return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
                      Message.createErrorMessage(
                        "mets/structMap/div/div/" + "mptr[@xlink:type='simple'] in %1$s can't be null",
                        metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
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
   * mets/structMap/div/div/mptr[@LOCTYPE='URL'] The locator type is always used
   * with the value “URL” from the vocabulary in the attribute.
   */
  protected ReporterDetails validateCSIP112(final MetsValidatorState metsValidatorState) {
    final List<StructMapType> structMap = metsValidatorState.getMets().getStructMap();
    if (structMap != null) {
      for (StructMapType struct : structMap) {
        final DivType div = struct.getDiv();
        if (div != null && struct.getLABEL().equals("CSIP")) {
          final List<DivType> divs = div.getDiv();
          for (DivType d : divs) {
            if (d.getLABEL() != null && d.getLABEL().matches("Representations/")) {
              final List<DivType.Mptr> mptrs = d.getMptr();
              for (DivType.Mptr mptr : mptrs) {
                final String locType = mptr.getLOCTYPE();
                if (locType != null) {
                  if (!locType.equals("URL")) {
                    return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
                      Message.createErrorMessage(
                        "mets/structMap/div/div/" + "mptr[@LOCTYPE='URL'] value in %1$s must be 'URL'",
                        metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
                      false, false);
                  }
                } else {
                  return new ReporterDetails(Constants.VALIDATION_REPORT_HEADER_CSIP_VERSION,
                    Message.createErrorMessage("mets/structMap/div/div/" + "mptr[@LOCTYPE='URL'] in %1$s can't be null",
                      metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
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
}
