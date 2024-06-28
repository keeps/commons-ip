package org.roda_project.commons_ip2.validator.components.sipMetsRootComponent.sipMetsHdrComponent;

import java.util.List;

import org.roda_project.commons_ip2.mets_v1_12.beans.MetsType;
import org.roda_project.commons_ip2.validator.constants.Constants;
import org.roda_project.commons_ip2.validator.reporter.ReporterDetails;
import org.roda_project.commons_ip2.validator.state.MetsValidatorState;
import org.roda_project.commons_ip2.validator.utils.Message;

/**
 * @author Carlos Afonso <cafonso@keep.pt>
 */
public abstract class SipMetsHdrValidator {
  protected abstract String getSIPVersion();

  /*
   * metsHdr/@RECORDSTATUS A way of indicating the status of the package and to
   * instruct the OAIS on how to properly handle the package. If not set, the
   * expected behaviour is equal to NEW.See also: Package status
   */

  protected ReporterDetails validateSIP3(final MetsValidatorState metsValidatorState, final MetsType.MetsHdr metsHdr,
    final List<String> recordsStatus) {
    final String recordStatus = metsHdr.getRECORDSTATUS();
    if (recordStatus != null && !recordsStatus.contains(recordStatus)) {
        String message = "Value " + recordStatus + "for metsHdr/@RECORDSTATUS value isn't valid in %1$s";
      return new ReporterDetails(getSIPVersion(), Message.createErrorMessage(
              message, metsValidatorState.getMetsName(), metsValidatorState.isRootMets()), false, false);
    }
    return new ReporterDetails();
  }

  /*
   * metsHdr/@csip:OAISPACKAGETYPE
   *
   * @csip:OAISPACKAGETYPE is used with the value “SIP”.See also: OAIS Package
   * type
   */

  protected ReporterDetails validateSIP4(final MetsValidatorState metsValidatorState, final MetsType.MetsHdr metsHdr) {
    final String oaisPackageType = metsHdr.getOAISPACKAGETYPE();
    if (oaisPackageType == null) {
      return new ReporterDetails(getSIPVersion(),
        Message.createErrorMessage("metsHdr/@csip:OAISPACKAGETYPE can't be null, in %1$s",
          metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
        false, false);
    }

    if (!oaisPackageType.equals("SIP")) {
      return new ReporterDetails(getSIPVersion(),
        Message.createErrorMessage("metsHdr/@csip:OAISPACKAGETYPE must be used with the value SIP, in %1$s",
          metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
        false, false);
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

  protected ReporterDetails validateSIP5(final MetsValidatorState metsValidatorState, final MetsType.MetsHdr metsHdr) {
    final List<MetsType.MetsHdr.AltRecordID> altRecordIDS = metsHdr.getAltRecordID();
    boolean found = false;
    int count = 0;
    if (altRecordIDS != null && !altRecordIDS.isEmpty()) {
      for (MetsType.MetsHdr.AltRecordID altRecordID : altRecordIDS) {
        final String type = altRecordID.getTYPE();
        if (type.equals("SUBMISSIONAGREEMENT")) {
          found = true;
          count++;
        }
      }
      if (!found) {
        return new ReporterDetails(getSIPVersion(),
          Message.createErrorMessage("metsHdr/altRecordID with the @TYPE “SUBMISSIONAGREEMENT” not found in %1$s",
            metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
          false, false);
      }
      if (count > 1) {
        return new ReporterDetails(getSIPVersion(),
          Message.createErrorMessage("Can't have more than one metsHdr/altRecordID of the TYPE SUBMISSIONAGREEMENT, "
            + "in %1$s exist more than one", metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
          false, false);
      }
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
  protected ReporterDetails validateSIP6(final MetsValidatorState metsValidatorState, final MetsType.MetsHdr metsHdr) {
    final List<MetsType.MetsHdr.AltRecordID> altRecordIDS = metsHdr.getAltRecordID();
    boolean found = false;
    if (altRecordIDS != null && !altRecordIDS.isEmpty()) {
      for (MetsType.MetsHdr.AltRecordID altRecordID : altRecordIDS) {
        final String type = altRecordID.getTYPE();
          if ("PREVIOUSSUBMISSIONAGREEMENT".equals(type)) {
              found = true;
              break;
          }
      }
      if (!found) {
        return new ReporterDetails(getSIPVersion(),
          Message.createErrorMessage(
            "metsHdr/altRecordID with the @TYPE " + "“PREVIOUSSUBMISSIONAGREEMENT” not found in %1$s",
            metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
          false, false);
      }
    }
    return new ReporterDetails();
  }

  /*
   * metsHdr/altRecordID An optional reference code indicating where in the
   * archival hierarchy the package shall be placed in the OAIS. @TYPE is used
   * with the value “REFERENCECODE”. Example: FM 12-2387/12726, 2007-09-19See
   * also: Alternative record ID’s
   */
  protected ReporterDetails validateSIP7(final MetsValidatorState metsValidatorState, final MetsType.MetsHdr metsHdr) {
    final List<MetsType.MetsHdr.AltRecordID> altRecordIDS = metsHdr.getAltRecordID();
    boolean found = false;
    int count = 0;
    if (altRecordIDS != null && !altRecordIDS.isEmpty()) {
      for (MetsType.MetsHdr.AltRecordID altRecordID : altRecordIDS) {
        final String type = altRecordID.getTYPE();
        if ("REFERENCECODE".equals(type)) {
          found = true;
          count++;
        }
      }
      if (!found) {
        return new ReporterDetails(getSIPVersion(),
          Message.createErrorMessage("metsHdr/altRecordID with the @TYPE “REFERENCECODE” not found in %1$s",
            metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
          false, false);
      }
      if (count > 1) {
        return new ReporterDetails(getSIPVersion(),
          Message.createErrorMessage("Can't have more than one metsHdr/altRecordID of the type REFERENCECODE",
            metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
          false, false);
      }
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
  protected ReporterDetails validateSIP8(final MetsValidatorState metsValidatorState, final MetsType.MetsHdr metsHdr) {
    final List<MetsType.MetsHdr.AltRecordID> altRecordIDS = metsHdr.getAltRecordID();
    boolean found = false;
    if (altRecordIDS != null && !altRecordIDS.isEmpty()) {
      for (MetsType.MetsHdr.AltRecordID altRecordID : altRecordIDS) {
        final String type = altRecordID.getTYPE();
          if ("PREVIOUSSUBMISSIONAGREEMENT".equals(type)) {
              found = true;
              break;
          }
      }
      if (!found) {
        return new ReporterDetails(getSIPVersion(),
          Message.createErrorMessage("metsHdr/altRecordID with the TYPE PREVIOUSREFERENCECODE not found in %1$s",
            metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
          false, false);
      }
    }
    return new ReporterDetails();
  }

  /*
   * metsHdr/agent A wrapper element that enables to encode the name of the
   * organisation or person that originally created the data being transferred.
   * Please note that this might be dierent from the organisation which has been
   * charged with preparing and sending the SIP to the archives.
   */
  protected ReporterDetails validateSIP9(final MetsValidatorState metsValidatorState,
    final List<MetsType.MetsHdr.Agent> agents) {
    boolean found = false;
    int count = 0;
    if (agents != null && !agents.isEmpty()) {
      for (MetsType.MetsHdr.Agent agent : agents) {
        final String role = agent.getROLE();
        if (role != null) {
          if ("ARCHIVIST".equals(role)) {
            found = true;
            count++;
          }
        } else {
          return new ReporterDetails(getSIPVersion(),
            Message.createErrorMessage("metsHdr/agent/@ROLE can't be null, in %1$s the @ROLE is null.",
              metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
            false, false);
        }
      }
      if (!found) {
        return new ReporterDetails(getSIPVersion(),
          Message.createErrorMessage("metsHdr/agent you can add agent with the ROLE ARCHIVIST in %1$s",
            metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
          false, false);
      } else {
        if (count != 1) {
          return new ReporterDetails(getSIPVersion(),
            Message.createErrorMessage("Can't have more than one metsHdr/agent with the ROLE ARCHIVIST in %1$s",
              metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
            false, false);
        }
      }
    } else {
      return new ReporterDetails(getSIPVersion(),
        Message.createErrorMessage("Doesn't have agent with the ROLE ARCHIVIST in %1$s",
          metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
        false, false);
    }
    return new ReporterDetails();
  }

  /*
   * metsHdr/agent/@ROLE The role of the person(s) or institution(s) responsible
   * for the document/collection.
   */
  protected ReporterDetails validateSIP10(final MetsValidatorState metsValidatorState,
    List<MetsType.MetsHdr.Agent> agents) {
    boolean found = false;
    int count = 0;
    if (agents != null && !agents.isEmpty()) {
      for (MetsType.MetsHdr.Agent agent : agents) {
        final String role = agent.getROLE();
        if (role != null) {
          if ("ARCHIVIST".equals(role)) {
            found = true;
            count++;
          }
        } else {
          return new ReporterDetails(getSIPVersion(),
            Message.createErrorMessage("metsHdr/agent/@ROLE can't be null, in %1$s @ROLE is null ",
              metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
            false, false);
        }
      }
      if (!found) {
        return new ReporterDetails(getSIPVersion(),
          Message.createErrorMessage("metsHdr/agent you can add agent with the @ROLE ARCHIVIST in %1$s",
            metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
          false, false);
      } else {
        if (count != 1) {
          return new ReporterDetails(getSIPVersion(),
            Message.createErrorMessage("Can't have more than one metsHdr/agent with the @ROLE ARCHIVIST in %1$s",
              metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
            false, false);
        }
      }
    } else {
      return new ReporterDetails(getSIPVersion(),
        Message.createErrorMessage("Skipped in %1$s because metsHdr/agent does not exists",
          metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
        true, true);
    }
    return new ReporterDetails();
  }

  /*
   * metsHdr/agent/@TYPE The type of the archival creator agent is “ORGANIZATION”
   * or “INDIVIDUAL”
   */
  protected ReporterDetails validateSIP11(final MetsValidatorState metsValidatorState,
    List<MetsType.MetsHdr.Agent> agents) {
    if (agents != null && !agents.isEmpty()) {
      for (MetsType.MetsHdr.Agent agent : agents) {
        final String role = agent.getROLE();
        if (role != null) {
          if ("ARCHIVIST".equals(role)) {
            final String type = agent.getTYPE();
            if (!"ORGANIZATION".equals(type) && !type.equals("INDIVIDUAL")) {
                String message = "Value " + type +
                        " in %1$s for metsHdr/agent/@TYPE when metsHdr/agent/@ROLE is "
                        + "ARCHIVIST isn't valid, must be ORGANIZATION or INDIVIDUAL ";
              return new ReporterDetails(getSIPVersion(), Message.createErrorMessage(
                      message, metsValidatorState.getMetsName(), metsValidatorState.isRootMets()), false, false);
            }
          }
        } else {
          return new ReporterDetails(getSIPVersion(),
            Message.createErrorMessage("metsHdr/agent/@ROLE can't be null, in %1$s the value is null",
              metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
            false, false);
        }
      }
    } else {
      return new ReporterDetails(getSIPVersion(),
        Message.createErrorMessage("Skipped in %1$s because metsHdr/agent does not exists",
          metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
        true, true);
    }
    return new ReporterDetails();
  }

  /*
   * metsHdr/agent/name The name of the organisation(s) that originally created
   * the data being transferred. Please note that this might be dierent from the
   * organisation which has been charged with preparing and sending the SIP to the
   * archives.
   */
  protected ReporterDetails validateSIP12(final MetsValidatorState metsValidatorState,
    List<MetsType.MetsHdr.Agent> agents) {
    if (agents != null && !agents.isEmpty()) {
      for (MetsType.MetsHdr.Agent agent : agents) {
        final String role = agent.getROLE();
        if (role != null) {
          if ("ARCHIVIST".equals(role)) {
            final String name = agent.getName();
            if (name == null) {
              return new ReporterDetails(getSIPVersion(),
                Message.createErrorMessage("metsHdr/agent/name can't be null, in %1$s the value is null",
                  metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
                false, false);
            }
          }
        } else {
          return new ReporterDetails(getSIPVersion(),
            Message.createErrorMessage("metsHdr/agent/@ROLE can't be null, in %1$s the value is null",
              metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
            false, false);
        }
      }
    } else {
      return new ReporterDetails(getSIPVersion(),
        Message.createErrorMessage("Skipped in %1$s because metsHdr/agent does not exists ",
          metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
        true, true);
    }
    return new ReporterDetails();
  }

  /*
   * metsHdr/agent/note The archival creator agent has a note providing a unique
   * identification code for the archival creator.
   */
  protected ReporterDetails validateSIP13(final MetsValidatorState metsValidatorState,
    List<MetsType.MetsHdr.Agent> agents) {
    if (agents != null && !agents.isEmpty()) {
      for (MetsType.MetsHdr.Agent agent : agents) {
        final String role = agent.getROLE();
        if (role != null) {
          if ("ARCHIVIST".equals(role)) {
            final List<MetsType.MetsHdr.Agent.Note> notes = agent.getNote();
            if (notes != null && !notes.isEmpty()) {
              if (notes.size() != 1) {
                return new ReporterDetails(getSIPVersion(),
                  Message.createErrorMessage("metsHdr/agent/note in %1$s can't appear more than once ",
                    metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
                  false, false);
              }
            } else {
              return new ReporterDetails(getSIPVersion(),
                Message.createErrorMessage("You can add one metsHdr/agent/note in %1$s ",
                  metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
                false, false);
            }
          }
        } else {
          return new ReporterDetails(getSIPVersion(),
            Message.createErrorMessage("metsHdr/agent/@ROLE can't be null, in %1$s the @ROLE is null ",
              metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
            false, false);
        }
      }
    } else {
      return new ReporterDetails(getSIPVersion(),
        Message.createErrorMessage("Skipped in %1$s because metsHdr/agent does not exists",
          metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
        true, true);
    }
    return new ReporterDetails();
  }

  /*
   * metsHdr/agent/note/@csip:NOTETYPE The archival creator agent note is typed
   * with the value of “IDENTIFICATIONCODE”.See also: Note type
   */
  protected ReporterDetails validateSIP14(final MetsValidatorState metsValidatorState,
    List<MetsType.MetsHdr.Agent> agents) {
    if (agents != null && !agents.isEmpty()) {
      for (MetsType.MetsHdr.Agent agent : agents) {
        final String role = agent.getROLE();
        if (role != null) {
          if (role.equals("ARCHIVIST")) {
            final List<MetsType.MetsHdr.Agent.Note> notes = agent.getNote();
            if (notes != null && !notes.isEmpty()) {
              for (MetsType.MetsHdr.Agent.Note note : notes) {
                final String noteType = note.getNOTETYPE();
                if (noteType == null || noteType.equals("")) {
                  return new ReporterDetails(getSIPVersion(),
                    Message.createErrorMessage("metsHdr/agent/@csip:NOTETYPE in %1$s can't be null or empty",
                      metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
                    false, false);
                }
              }
            }
          }
        } else {
          return new ReporterDetails(getSIPVersion(),
            Message.createErrorMessage("metsHdr/agent/@ROLE in %1$s can't be null ", metsValidatorState.getMetsName(),
              metsValidatorState.isRootMets()),
            false, false);
        }
      }
    } else {
      return new ReporterDetails(getSIPVersion(),
        Message.createErrorMessage("Skipped in %1$s because metsHdr/@agent does not exists",
          metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
        true, true);
    }
    return new ReporterDetails();
  }

  /*
   * metsHdr/agent The name of the organisation or person submitting the package
   * to the archive.
   */
  protected ReporterDetails validateSIP15(final MetsValidatorState metsValidatorState,
    List<MetsType.MetsHdr.Agent> agents) {
    boolean found = false;
    int count = 0;
    if (agents != null && !agents.isEmpty()) {
      for (MetsType.MetsHdr.Agent agent : agents) {
        final String role = agent.getROLE();
        if (role != null) {
          if ("CREATOR".equals(role) && (agent.getTYPE() != null && !"OTHER".equals(agent.getTYPE()))) {
            found = true;
            count++;
          }
        } else {
          return new ReporterDetails(getSIPVersion(),
            Message.createErrorMessage("metsHdr/agent/@ROLE in %1$s can't be null ", metsValidatorState.getMetsName(),
              metsValidatorState.isRootMets()),
            false, false);
        }
      }
      if (!found) {
        return new ReporterDetails(getSIPVersion(),
          Message.createErrorMessage("Doesn't have metsHdr/agent that submit the package",
            metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
          false, false);
      } else {
        if (count != 1) {
          return new ReporterDetails(getSIPVersion(),
            Message.createErrorMessage("Can't have more than one metsHdr/agent that submit the package",
              metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
            false, false);
        }
      }
    } else {
      return new ReporterDetails(getSIPVersion(),
        Message.createErrorMessage("Doesn't have metsHdr/@agent in %1$s", metsValidatorState.getMetsName(),
          metsValidatorState.isRootMets()),
        false, false);
    }
    return new ReporterDetails();
  }

  /*
   * metsHdr/agent/@ROLE The role of the person(s) or institution(s) responsible
   * for creating and/or submitting the package.
   */
  protected ReporterDetails validateSIP16(final MetsValidatorState metsValidatorState,
    List<MetsType.MetsHdr.Agent> agents) {
    boolean found = false;
    int count = 0;
    if (agents != null && !agents.isEmpty()) {
      for (MetsType.MetsHdr.Agent agent : agents) {
        final String role = agent.getROLE();
        if (role != null) {
          if ("CREATOR".equals(role) && (agent.getTYPE() != null && !"OTHER".equals(agent.getTYPE()))) {
            found = true;
            count++;
          }
        } else {
          return new ReporterDetails(getSIPVersion(),
            Message.createErrorMessage("metsHdr/agent/@ROLE in %1$s can't be null ", metsValidatorState.getMetsName(),
              metsValidatorState.isRootMets()),
            false, false);
        }
      }
      if (!found) {
        return new ReporterDetails(getSIPVersion(),
          Message.createErrorMessage("metsHdr/agent with the @ROLE OTHER in %1$s not found ",
            metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
          false, false);
      } else {
        if (count != 1) {
          return new ReporterDetails(getSIPVersion(),
            Message.createErrorMessage("Can't have more than one metsHdr/agent in %1$s with the @ROLE OTHER",
              metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
            false, false);
        }
      }
    } else {
      return new ReporterDetails(getSIPVersion(),
        Message.createErrorMessage("Skipped in %1$s because metsHdr/@agent does not exists",
          metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
        true, true);
    }
    return new ReporterDetails();
  }

  /*
   * metsHdr/agent/@TYPE The type of the submitting agent is “ORGANIZATION” or
   * “INDIVIDUAL”
   */
  protected ReporterDetails validateSIP17(final MetsValidatorState metsValidatorState,
    List<MetsType.MetsHdr.Agent> agents) {
    if (agents != null && !agents.isEmpty()) {
      for (MetsType.MetsHdr.Agent agent : agents) {
        final String role = agent.getROLE();
        if (role != null) {
          if ("CREATOR".equals(role) && (agent.getTYPE() != null && !"OTHER".equals(agent.getTYPE()))) {
            final String type = agent.getTYPE();
            if (!"ORGANIZATION".equals(type) && !"INDIVIDUAL".equals(type)) {
                String message = "Value " + type +
                        " in %1$s for metsHdr/agent/@TYPE when the metsHdr/agent/@ROLE "
                        + "is OTHER isn't valid, must be ORGANIZATION or INDIVIDUAL";
              return new ReporterDetails(getSIPVersion(), Message.createErrorMessage(
                      message, metsValidatorState.getMetsName(), metsValidatorState.isRootMets()), false, false);
            }
          }
        } else {
          return new ReporterDetails(getSIPVersion(),
            Message.createErrorMessage("metsHdr/agent/@ROLE can't be null ", metsValidatorState.getMetsName(),
              metsValidatorState.isRootMets()),
            false, false);
        }
      }
    } else {
      return new ReporterDetails(getSIPVersion(),
        Message.createErrorMessage("Skipped because metsHdr/@agent does not exists", metsValidatorState.getMetsName(),
          metsValidatorState.isRootMets()),
        true, true);
    }
    return new ReporterDetails();
  }

  /*
   * metsHdr/agent/name Name of the organisation submitting the package to the
   * archive
   */
  protected ReporterDetails validateSIP18(final MetsValidatorState metsValidatorState,
    List<MetsType.MetsHdr.Agent> agents) {
    if (agents != null && !agents.isEmpty()) {
      for (MetsType.MetsHdr.Agent agent : agents) {
        final String role = agent.getROLE();
        if (role != null) {
          if ("CREATOR".equals(role) && (agent.getTYPE() != null && !"OTHER".equals(agent.getTYPE()))) {
            final String name = agent.getName();
            if (name == null) {
              return new ReporterDetails(getSIPVersion(),
                Message.createErrorMessage("metsHdr/agent/name in %1$s can't be null", metsValidatorState.getMetsName(),
                  metsValidatorState.isRootMets()),
                false, false);
            }
          }
        } else {
          return new ReporterDetails(getSIPVersion(),
            Message.createErrorMessage("metsHdr/agent/@ROLE in %1$s can't be null ", metsValidatorState.getMetsName(),
              metsValidatorState.isRootMets()),
            false, false);
        }
      }
    } else {
      return new ReporterDetails(getSIPVersion(),
        Message.createErrorMessage("Skipped in %1$s because metsHdr/agent does not exists",
          metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
        true, true);
    }
    return new ReporterDetails();
  }

  /*
   * metsHdr/agent/note The submitting agent has a note providing a unique
   * identification code for the archival creator.
   */
  protected ReporterDetails validateSIP19(final MetsValidatorState metsValidatorState,
    List<MetsType.MetsHdr.Agent> agents) {
    if (agents != null && !agents.isEmpty()) {
      for (MetsType.MetsHdr.Agent agent : agents) {
        final String role = agent.getROLE();
        if (role != null) {
          if (role.equals("CREATOR") && (agent.getTYPE() != null && !agent.getTYPE().equals("OTHER"))) {
            final List<MetsType.MetsHdr.Agent.Note> notes = agent.getNote();
            if (notes != null && !notes.isEmpty()) {
              if (notes.size() != 1) {
                return new ReporterDetails(getSIPVersion(),
                  Message.createErrorMessage("metsHdr/agent/note in %1$s can't appear more than once ",
                    metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
                  false, false);
              }
            } else {
              return new ReporterDetails(getSIPVersion(),
                Message.createErrorMessage("You can add one metsHdr/agent/note in %1$s",
                  metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
                false, false);
            }
          }
        } else {
          return new ReporterDetails(getSIPVersion(),
            Message.createErrorMessage("metsHdr/agent/@ROLE in %1$s can't be null ", metsValidatorState.getMetsName(),
              metsValidatorState.isRootMets()),
            false, false);
        }
      }
    } else {
      return new ReporterDetails(getSIPVersion(),
        Message.createErrorMessage("Skipped in %1$s because metsHdr/agent does not exists",
          metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
        true, true);
    }
    return new ReporterDetails();
  }

  /*
   * metsHdr/agent/note/@csip:NOTETYPE The submitting agent note is typed with the
   * value of “IDENTIFICATIONCODE”.See also: Note type
   */
  protected ReporterDetails validateSIP20(final MetsValidatorState metsValidatorState,
    List<MetsType.MetsHdr.Agent> agents) {
    if (agents != null && !agents.isEmpty()) {
      for (MetsType.MetsHdr.Agent agent : agents) {
        final String role = agent.getROLE();
        if (role != null) {
          if (role.equals("CREATOR") && (agent.getTYPE() != null && !agent.getTYPE().equals("OTHER"))) {
            final List<MetsType.MetsHdr.Agent.Note> notes = agent.getNote();
            if (notes != null && !notes.isEmpty()) {
              for (MetsType.MetsHdr.Agent.Note note : notes) {
                final String noteType = note.getNOTETYPE();
                if (noteType == null || noteType.equals("")) {
                  return new ReporterDetails(getSIPVersion(),
                    Message.createErrorMessage("metsHdr/agent/@csip:NOTETYPE in %1$s can't be null or empty",
                      metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
                    false, false);
                } else {
                  if (!noteType.equals("IDENTIFICATIONCODE")) {
                    return new ReporterDetails(getSIPVersion(),
                      Message.createErrorMessage("metsHdr/agent/@csip:NOTETYPE in %1$s has to be IDENTIFICATIONCODE",
                        metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
                      false, false);
                  }
                }
              }
            }
          }
        } else {
          return new ReporterDetails(getSIPVersion(),
            Message.createErrorMessage("metsHdr/agent/@ROLE in %1$s can't be null ", metsValidatorState.getMetsName(),
              metsValidatorState.isRootMets()),
            false, false);
        }
      }
    } else {
      return new ReporterDetails(getSIPVersion(),
        Message.createErrorMessage("Skipped in %1$s because metsHdr/agent does not exists",
          metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
        true, true);
    }
    return new ReporterDetails();
  }

  /*
   * metsHdr/agent Contact person for the submission.
   */
  protected ReporterDetails validateSIP21(final MetsValidatorState metsValidatorState,
    List<MetsType.MetsHdr.Agent> agents) {
    boolean found = false;
    if (agents != null && !agents.isEmpty()) {
      for (MetsType.MetsHdr.Agent agent : agents) {
        final String role = agent.getROLE();
        if (role != null) {
          if (role.equals("CREATOR")) {
            found = true;
          }
        } else {
          return new ReporterDetails(getSIPVersion(),
            Message.createErrorMessage("metsHdr/agent/@ROLE in %1$s can't be null ", metsValidatorState.getMetsName(),
              metsValidatorState.isRootMets()),
            false, false);
        }
      }
      if (!found) {
        return new ReporterDetails(getSIPVersion(),
          Message.createErrorMessage("metsHdr/agent you can add agent with the @ROLE CREATOR in %1$s ",
            metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
          false, false);
      }
    } else {
      return new ReporterDetails(getSIPVersion(),
        Message.createErrorMessage("Skipped in %1$s because metsHdr/@agent does not exists",
          metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
        true, true);
    }
    return new ReporterDetails();
  }

  /*
   * metsHdr/agent/@ROLE The role of the contact person is “CREATOR”.
   */
  protected ReporterDetails validateSIP22(final MetsValidatorState metsValidatorState,
    List<MetsType.MetsHdr.Agent> agents) {
    boolean found = false;
    if (agents != null && !agents.isEmpty()) {
      for (MetsType.MetsHdr.Agent agent : agents) {
        final String role = agent.getROLE();
        if (role != null) {
          if ("CREATOR".equals(role)) {
            found = true;
          }
        } else {
          return new ReporterDetails(getSIPVersion(),
            Message.createErrorMessage("metsHdr/agent/@ROLE in %1$s can't be null ", metsValidatorState.getMetsName(),
              metsValidatorState.isRootMets()),
            false, false);
        }
      }
      if (!found) {
        return new ReporterDetails(getSIPVersion(),
          Message.createErrorMessage("metsHdr/agent you can add agent with the @ROLE CREATOR in %1$s ",
            metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
          false, false);
      }
    } else {
      return new ReporterDetails(getSIPVersion(),
        Message.createErrorMessage("Skipped in %1$s because metsHdr/@gent does not exists",
          metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
        true, true);
    }
    return new ReporterDetails();
  }

  /*
   * metsHdr/agent/@TYPE The type of the contact person agent is “INDIVIDUAL”
   */
  protected ReporterDetails validateSIP23(final MetsValidatorState metsValidatorState,
    List<MetsType.MetsHdr.Agent> agents) {
    if (agents != null && !agents.isEmpty()) {
      for (MetsType.MetsHdr.Agent agent : agents) {
        final String role = agent.getROLE();
        if (role != null) {
          if (role.equals("CREATOR") && (agent.getOTHERTYPE() != null && !agent.getOTHERTYPE().equals("SOFTWARE"))) {
            final String type = agent.getTYPE();
            if (!type.equals("INDIVIDUAL")) {
                String message = "Value " + type +
                        " in %1$s for metsHdr/agent/@TYPE isn't valid, when the metsHdr/agent/@ROLE "
                        + "is CREATOR and  metsHdr/agent/@OTHERTYPE isn't "
                        + "SOFTWARE metsHdr/agent/@TYPE must be INDIVIDUAL";
              return new ReporterDetails(getSIPVersion(), Message.createErrorMessage(
                      message, metsValidatorState.getMetsName(), metsValidatorState.isRootMets()), false, false);
            }
          }
        } else {
          return new ReporterDetails(getSIPVersion(),
            Message.createErrorMessage("metsHdr/agent/@ROLE in %1$s can't be null ", metsValidatorState.getMetsName(),
              metsValidatorState.isRootMets()),
            false, false);
        }
      }
    } else {
      return new ReporterDetails(getSIPVersion(),
        Message.createErrorMessage("Skipped in %1$s because metsHdr/@agent does not exists",
          metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
        true, true);
    }
    return new ReporterDetails();
  }

  /*
   * metsHdr/agent/name Name of the contact person.
   */
  protected ReporterDetails validateSIP24(final MetsValidatorState metsValidatorState,
    List<MetsType.MetsHdr.Agent> agents) {
    if (agents != null && !agents.isEmpty()) {
      for (MetsType.MetsHdr.Agent agent : agents) {
        final String role = agent.getROLE();
        if (role != null) {
          if (role.equals("CREATOR")) {
            final String name = agent.getName();
            if (name == null) {
              return new ReporterDetails(getSIPVersion(),
                Message.createErrorMessage("metsHdr/agent/name in %1$s can't be null", metsValidatorState.getMetsName(),
                  metsValidatorState.isRootMets()),
                false, false);
            }
          }
        } else {
          return new ReporterDetails(getSIPVersion(),
            Message.createErrorMessage("metsHdr/agent/@ROLE in %1$s can't be null ", metsValidatorState.getMetsName(),
              metsValidatorState.isRootMets()),
            false, false);
        }
      }
    } else {
      return new ReporterDetails(getSIPVersion(),
        Message.createErrorMessage("Skipped in %1$s because metsHdr/@agent does not exists",
          metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
        true, true);
    }
    return new ReporterDetails();
  }

  /*
   * metsHdr/agent/note The submitting agent has one or more notes giving the
   * contact information
   */
  protected ReporterDetails validateSIP25(final MetsValidatorState metsValidatorState,
    List<MetsType.MetsHdr.Agent> agents) {
    if (agents != null && !agents.isEmpty()) {
      for (MetsType.MetsHdr.Agent agent : agents) {
        final String role = agent.getROLE();
        if (role != null) {
          if (role.equals("CREATOR")) {
            final List<MetsType.MetsHdr.Agent.Note> notes = agent.getNote();
            if (notes == null || notes.isEmpty()) {
              return new ReporterDetails(getSIPVersion(),
                Message.createErrorMessage("can add contact information in one or more metsHdr/agent/note in %1$s ",
                  metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
                false, false);
            }
          }
        } else {
          return new ReporterDetails(getSIPVersion(),
            Message.createErrorMessage("metsHdr/agent/@ROLE in %1$s can't be null ", metsValidatorState.getMetsName(),
              metsValidatorState.isRootMets()),
            false, false);
        }
      }
    }
    return new ReporterDetails();
  }

  /*
   * metsHdr/agent The organisation or person that preserves the package.
   */
  protected ReporterDetails validateSIP26(final MetsValidatorState metsValidatorState,
    List<MetsType.MetsHdr.Agent> agents) {
    boolean found = false;
    int count = 0;
    if (agents != null && !agents.isEmpty()) {
      for (MetsType.MetsHdr.Agent agent : agents) {
        final String role = agent.getROLE();
        if (role != null) {
          if ("PRESERVATION".equals(role)) {
            found = true;
            count++;
          }
        } else {
          return new ReporterDetails(getSIPVersion(),
            Message.createErrorMessage("metsHdr/agent/@ROLE in %1$s can't be null", metsValidatorState.getMetsName(),
              metsValidatorState.isRootMets()),
            false, false);
        }
      }
      if (!found) {
        return new ReporterDetails(getSIPVersion(),
          Message.createErrorMessage(
            "You can add information about the " + "organisation or person that preserves the package by adding a "
              + "agent in the root METS header with @ROLE='PRESERVATION' in %1$s",
            metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
          false, false);
      } else {
        if (count != 1) {
          return new ReporterDetails(getSIPVersion(),
            Message.createErrorMessage("Can't have more than one metsHdr/agent with the ROLE='PRESERVATION' in %1$s",
              metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
            false, false);
        }
      }
    } else {
      return new ReporterDetails(getSIPVersion(),
        Message.createErrorMessage("Skipped in %1$s because metsHdr/@agent does not exists",
          metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
        true, true);
    }
    return new ReporterDetails();
  }

  /*
   * metsHdr/agent/@ROLE The role of the preservation agent is “PRESERVATION”.
   */
  protected ReporterDetails validateSIP27(final MetsValidatorState metsValidatorState,
    List<MetsType.MetsHdr.Agent> agents) {
    boolean found = false;
    int count = 0;
    if (agents != null && !agents.isEmpty()) {
      for (MetsType.MetsHdr.Agent agent : agents) {
        final String role = agent.getROLE();
        if (role != null) {
          if (role.equals("PRESERVATION")) {
            found = true;
            count++;
          }
        } else {
          return new ReporterDetails(getSIPVersion(),
            Message.createErrorMessage("metsHdr/agent/@ROLE in %1$s can't be null ", metsValidatorState.getMetsName(),
              metsValidatorState.isRootMets()),
            false, false);
        }
      }
      if (!found) {
        return new ReporterDetails(getSIPVersion(),
          Message.createErrorMessage("You can add information about the organisation or person "
            + "that preserves the package by adding a agent in the root METS header "
            + "with @ROLE='PRESERVATION' in %1$s", metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
          false, false);
      } else {
        if (count != 1) {
          return new ReporterDetails(getSIPVersion(),
            Message.createErrorMessage("Can't have more than one metsHdr/agent with the ROLE PRESERVATION in %1$s",
              metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
            false, false);
        }
      }
    } else {
      return new ReporterDetails(getSIPVersion(),
        Message.createErrorMessage("Skipped in %1$s because metsHdr/agent does not exists",
          metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
        true, true);
    }
    return new ReporterDetails();
  }

  /*
   * metsHdr/agent/@TYPE The type of the submitting agent is “ORGANIZATION”.
   */
  protected ReporterDetails validateSIP28(final MetsValidatorState metsValidatorState,
    List<MetsType.MetsHdr.Agent> agents) {
    if (agents != null && !agents.isEmpty()) {
      for (MetsType.MetsHdr.Agent agent : agents) {
        final String role = agent.getROLE();
        if (role != null) {
          if (role.equals("PRESERVATION")) {
            final String type = agent.getTYPE();
            if (!type.equals("ORGANIZATION")) {
                String message = "Value " + type + " in %1$s for metsHdr/agent/@TYPE isn't valid, When the "
                        + "metsHdr/agent/@ROLE is PRESERVATION  " + "metsHdr/agent/@TYPE must be ORGANIZATION";
              return new ReporterDetails(getSIPVersion(), Message.createErrorMessage(
                      message, metsValidatorState.getMetsName(), metsValidatorState.isRootMets()), false, false);
            }
          }
        } else {
          return new ReporterDetails(getSIPVersion(),
            Message.createErrorMessage("metsHdr/agent/@ROLE in %1$s can't be null ", metsValidatorState.getMetsName(),
              metsValidatorState.isRootMets()),
            false, false);
        }
      }
    } else {
      return new ReporterDetails(getSIPVersion(),
        Message.createErrorMessage("Skipped in %1$s because metsHdr/agent does not exists",
          metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
        true, true);
    }
    return new ReporterDetails();
  }

  /*
   * metsHdr/agent/name Name of the organisation preserving the package.
   */
  protected ReporterDetails validateSIP29(final MetsValidatorState metsValidatorState,
    List<MetsType.MetsHdr.Agent> agents) {
    if (agents != null && !agents.isEmpty()) {
      for (MetsType.MetsHdr.Agent agent : agents) {
        final String role = agent.getROLE();
        if (role != null) {
          if (role.equals("PRESERVATION")) {
            final String name = agent.getName();
            if (name == null) {
              return new ReporterDetails(getSIPVersion(),
                Message.createErrorMessage("metsHdr/agent/name in %1$s can't be null", metsValidatorState.getMetsName(),
                  metsValidatorState.isRootMets()),
                false, false);
            }
          }
        } else {
          return new ReporterDetails(getSIPVersion(),
            Message.createErrorMessage("metsHdr/agent/@ROLE in %1$s can't be null ", metsValidatorState.getMetsName(),
              metsValidatorState.isRootMets()),
            false, false);
        }
      }
    } else {
      return new ReporterDetails(getSIPVersion(),
        Message.createErrorMessage("Skipped in %1$s because metsHdr/@agent does not exists",
          metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
        true, true);
    }
    return new ReporterDetails();
  }

  /*
   * metsHdr/agent/note The preservation agent has a note providing a unique
   * identification code for the archival creator.
   */
  protected ReporterDetails validateSIP30(final MetsValidatorState metsValidatorState,
    List<MetsType.MetsHdr.Agent> agents) {
    if (agents != null && !agents.isEmpty()) {
      for (MetsType.MetsHdr.Agent agent : agents) {
        final String role = agent.getROLE();
        if (role != null) {
          if ("PRESERVATION".equals(role)) {
            final List<MetsType.MetsHdr.Agent.Note> notes = agent.getNote();
            if (notes != null && !notes.isEmpty()) {
              if (notes.size() != 1) {
                return new ReporterDetails(getSIPVersion(),
                  Message.createErrorMessage("metsHdr/agent/note in %1$s can't be more than one ",
                    metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
                  false, false);
              }
            } else {
              return new ReporterDetails(getSIPVersion(),
                Message.createErrorMessage("You can add one metsHdr/agent/note in %1$s",
                  metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
                false, false);
            }
          }
        } else {
          return new ReporterDetails(getSIPVersion(),
            Message.createErrorMessage("metsHdr/agent/@ROLE in %1$s can't be null ", metsValidatorState.getMetsName(),
              metsValidatorState.isRootMets()),
            false, false);
        }
      }
    } else {
      return new ReporterDetails(getSIPVersion(),
        Message.createErrorMessage("Skipped in %1$s because metsHdr/@agent does not exists",
          metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
        true, true);
    }
    return new ReporterDetails();
  }

  /*
   * metsHdr/agent/note/@csip:NOTETYPE The preservation agent note is typed with
   * the value of “IDENTIFICATIONCODE”.See also: Note type
   */
  protected ReporterDetails validateSIP31(final MetsValidatorState metsValidatorState,
    List<MetsType.MetsHdr.Agent> agents) {
    if (agents != null && !agents.isEmpty()) {
      for (MetsType.MetsHdr.Agent agent : agents) {
        final String role = agent.getROLE();
        if (role != null) {
          if ("PRESERVATION".equals(role)) {
            final List<MetsType.MetsHdr.Agent.Note> notes = agent.getNote();
            if (notes != null && !notes.isEmpty()) {
              for (MetsType.MetsHdr.Agent.Note note : notes) {
                final String noteType = note.getNOTETYPE();
                if (noteType == null || noteType.equals("")) {
                  return new ReporterDetails(getSIPVersion(),
                    Message.createErrorMessage("metsHdr/agent/@csip:NOTETYPE in %1$s can't be null or empty",
                      metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
                    false, false);
                }
              }
            }
          }
        } else {
          return new ReporterDetails(getSIPVersion(),
            Message.createErrorMessage("metsHdr/agent/@ROLE in %1$s can't be null ", metsValidatorState.getMetsName(),
              metsValidatorState.isRootMets()),
            false, false);
        }
      }
    } else {
      return new ReporterDetails(getSIPVersion(),
        Message.createErrorMessage("Skipped in %1$s because metsHdr/@agent does not exists",
          metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
        true, true);
    }
    return new ReporterDetails();
  }
}
