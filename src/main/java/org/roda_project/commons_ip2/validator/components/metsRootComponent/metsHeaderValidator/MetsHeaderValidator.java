package org.roda_project.commons_ip2.validator.components.metsRootComponent.metsHeaderValidator;

import java.util.List;

import javax.xml.datatype.XMLGregorianCalendar;

import org.roda_project.commons_ip2.mets_v1_12.beans.MetsType;
import org.roda_project.commons_ip2.validator.reporter.ReporterDetails;
import org.roda_project.commons_ip2.validator.state.MetsValidatorState;
import org.roda_project.commons_ip2.validator.utils.Message;

/**
 * @author Carlos Afonso <cafonso@keep.pt>
 */
public abstract class MetsHeaderValidator {

  protected abstract String getCSIPVersion();

  /*
   * mets/metsHdr General element for describing the package.
   */
  protected ReporterDetails validateCSIP117(final MetsValidatorState metsValidatorState, MetsType.MetsHdr metsHdr) {
    final ReporterDetails details = new ReporterDetails();
    if (metsHdr == null) {
      details.setValid(false);
      details.addIssue(Message.createErrorMessage("mets/metsHdr can't be null, in %1$s the mets/metsHdr does not exist",
        metsValidatorState.getMetsName(), metsValidatorState.isRootMets()));
    }
    return details;
  }

  /*
   * mets/metsHdr/@CREATEDATE mets/metsHdr/@CREATEDATE records the date the
   * package was created.
   */
  protected ReporterDetails validateCSIP7(final MetsValidatorState metsValidatorState, MetsType.MetsHdr metsHdr) {
    final ReporterDetails details = new ReporterDetails();
    final XMLGregorianCalendar createDate = metsHdr.getCREATEDATE();
    if (createDate == null) {
      details.setValid(false);
      details.addIssue(Message.createErrorMessage("mets/metsHdr/@CREATEDATE can't be null, in %1$s the value is null",
        metsValidatorState.getMetsName(), metsValidatorState.isRootMets()));
    }
    return details;
  }

  /*
   * mets/metsHdr/@csip:OAISPACKAGETYPE mets/metsHdr/@csip:OAISPACKAGETYPE is an
   * additional CSIP attribute that declares the type of the IP.See also:
   * OAISPackage type
   */
  protected ReporterDetails validateCSIP9(final MetsValidatorState metsValidatorState, MetsType.MetsHdr metsHdr,
    final List<String> oaisPackageTypes) {
    final ReporterDetails details = new ReporterDetails();
    final String oaisPackageType = metsHdr.getOAISPACKAGETYPE();
    if (oaisPackageType == null || oaisPackageType.equals("")) {
      details.setValid(false);
      details.addIssue(Message.createErrorMessage(
        "mets/metsHdr/@csip:OAISPACKAGETYPE can't be null or empty, " + "in %1$s the value is null or empty",
        metsValidatorState.getMetsName(), metsValidatorState.isRootMets()));
    } else {
      if (!oaisPackageTypes.contains(oaisPackageType)) {
          String message = "Value " + oaisPackageType +
                  "in %1$s for mets/metsHdr/@csip:OAISPACKAGETYPE isn't valid";
        details.setValid(false);
        details.addIssue(Message.createErrorMessage(message, metsValidatorState.getMetsName(),
          metsValidatorState.isRootMets()));
      }
    }
    return details;
  }

  /*
   * mets/metsHdr/agent A mandatory agent element records the software used to
   * create the package. Other uses of agents may be described in any local
   * implementations that extend the profile.
   */
  protected ReporterDetails validateCSIP10(final MetsValidatorState metsValidatorState,
    List<MetsType.MetsHdr.Agent> agents) {
    final ReporterDetails details = new ReporterDetails();
    if (agents == null && metsValidatorState.isRootMets()) {
      details.setValid(false);
      details.addIssue(Message.createErrorMessage("Must have at least one mets/metsHdr/agent (%1$s)",
        metsValidatorState.getMetsName(), metsValidatorState.isRootMets()));
    } else {
      if (agents == null && !metsValidatorState.isRootMets()) {
        return new ReporterDetails(getCSIPVersion(), "", true, true);
      } else {
        if (agents.isEmpty() && metsValidatorState.isRootMets()) {
          details.setValid(false);
          details.addIssue(Message.createErrorMessage("Must have at least one mets/metsHdr/agent (%1$s)",
            metsValidatorState.getMetsName(), metsValidatorState.isRootMets()));
        } else {
          if (agents.isEmpty() && !metsValidatorState.isRootMets()) {
            return new ReporterDetails(getCSIPVersion(), "", true, true);
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
  protected ReporterDetails validateCSIP11(final MetsValidatorState metsValidatorState,
    List<MetsType.MetsHdr.Agent> agents) {
    final ReporterDetails details = new ReporterDetails();
    boolean found = false;
    if (agents != null && !agents.isEmpty()) {
      for (MetsType.MetsHdr.Agent a : agents) {
        final String role = a.getROLE();
        final String type = a.getTYPE();
        final String otherType = a.getOTHERTYPE();
        if ((role != null && role.equals("CREATOR")) && (type != null && type.equals("OTHER"))
          && (otherType != null && otherType.equals("SOFTWARE"))) {
          found = true;
          break;
        }
      }
      if (!found) {
        return new ReporterDetails(getCSIPVersion(),
          Message.createErrorMessage(
            "Must have a mets/metsHdr/agent[@ROLE='CREATOR'] " + "with the value CREATOR, in %1$s does not exists",
            metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
          false, false);
      }
    } else {
      return new ReporterDetails(getCSIPVersion(), "", true, true);
    }
    return details;
  }

  /*
   * mets/metsHdr/agent[@TYPE=’OTHER’] The mandatory agent element MUST have
   * a @TYPE attribute with the value “OTHER”.
   */

  protected ReporterDetails validateCSIP12(final MetsValidatorState metsValidatorState,
    List<MetsType.MetsHdr.Agent> agents) {
    if (agents != null && !agents.isEmpty()) {
      for (MetsType.MetsHdr.Agent a : agents) {
        final String role = a.getROLE();
        final String type = a.getTYPE();
        final String otherType = a.getOTHERTYPE();
        if ((role != null && role.equals("CREATOR")) && (type != null && type.equals("OTHER"))
          && (otherType != null && otherType.equals("SOFTWARE"))) {
          return new ReporterDetails();
        }
      }
    } else {
      return new ReporterDetails(getCSIPVersion(), "", true, true);
    }
    return new ReporterDetails(getCSIPVersion(),
      Message.createErrorMessage(
        "Must have a mets/metsHdr/agent[@TYPE='OTHER'] " + "with the value OTHER, in %1$s does not exist",
        metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
      false, false);
  }

  /*
   * mets/metsHdr/agent[@OTHERTYPE=’SOFTWARE’] The mandatory agent element MUST
   * have a @OTHERTYPE attribute with the value “SOFTWARE”.See also: Other agent
   * type.
   */

  protected ReporterDetails validateCSIP13(final MetsValidatorState metsValidatorState,
    List<MetsType.MetsHdr.Agent> agents) {
    if (agents != null && !agents.isEmpty()) {
      for (MetsType.MetsHdr.Agent a : agents) {
        final String role = a.getROLE();
        final String type = a.getTYPE();
        final String otherType = a.getOTHERTYPE();
        if ((role != null && role.equals("CREATOR")) && (type != null && type.equals("OTHER"))
          && (otherType != null && otherType.equals("SOFTWARE"))) {
          return new ReporterDetails();
        }
      }
    } else {
      new ReporterDetails(getCSIPVersion(),
        Message.createErrorMessage("", metsValidatorState.getMetsName(), metsValidatorState.isRootMets()), true, true);
    }
    return new ReporterDetails(getCSIPVersion(),
      Message.createErrorMessage(
        "Must have a mets/metsHdr/agent[@OTHERTYPE=’SOFTWARE’] "
          + "with the value SOFTWARE, in the %1$s the value isn't SOFTWARE",
        metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
      false, false);
  }

  /*
   * mets/metsHdr/agent/name The mandatory agent’s name element records the name
   * of the software tool used to create the IP.
   */

  protected ReporterDetails validateCSIP14(final MetsValidatorState metsValidatorState,
    List<MetsType.MetsHdr.Agent> agents) {
    if (agents != null && !agents.isEmpty()) {
      for (MetsType.MetsHdr.Agent a : agents) {
        final String role = a.getROLE();
        final String type = a.getTYPE();
        final String otherType = a.getOTHERTYPE();
        if ((role != null && role.equals("CREATOR")) && (type != null && type.equals("OTHER"))
          && (otherType != null && otherType.equals("SOFTWARE"))) {
          if (a.getName() == null) {
            return new ReporterDetails(getCSIPVersion(),
              Message.createErrorMessage("mets/metsHdr/agent/name can't be null, in %1$s the value is null",
                metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
              false, false);
          } else {
            if (a.getName().equals("")) {
              return new ReporterDetails(getCSIPVersion(),
                Message.createErrorMessage("mets/metsHdr/agent/name can't be empty, in %1$s the value is empty",
                  metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
                false, false);
            }
          }
        }
      }
    } else {
      return new ReporterDetails(getCSIPVersion(), "", true, true);
    }
    return new ReporterDetails();
  }

  /*
   * mets/metsHdr/agent/note The mandatory agent’s note element records the
   * version of the tool used to create the IP.
   */

  protected ReporterDetails validateCSIP15(final MetsValidatorState metsValidatorState,
    List<MetsType.MetsHdr.Agent> agents) {
    if (agents != null && !agents.isEmpty()) {
      for (MetsType.MetsHdr.Agent a : agents) {
        final String role = a.getROLE();
        final String type = a.getTYPE();
        final String otherType = a.getOTHERTYPE();
        if ((role != null && role.equals("CREATOR")) && (type != null && type.equals("OTHER"))
          && (otherType != null && otherType.equals("SOFTWARE"))) {
          final List<MetsType.MetsHdr.Agent.Note> notes = a.getNote();
          if (notes == null || notes.isEmpty()) {
            return new ReporterDetails(getCSIPVersion(),
              Message.createErrorMessage("mets/metsHdr/agent/note can't be null, in %1$s the value is null",
                metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
              false, false);
          } else {
            if (notes.size() > 1) {
              return new ReporterDetails(getCSIPVersion(),
                Message.createErrorMessage(
                  "mets/metsHdr/agent/note exists more than once, " + "in %1$s exists more than once note",
                  metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
                false, false);
            } else {
              for (MetsType.MetsHdr.Agent.Note note : notes) {
                if (note.getValue().isEmpty()) {
                  return new ReporterDetails(getCSIPVersion(),
                    Message.createErrorMessage("mets/metsHdr/agent/note can't be empty, in %1$s the note is empty",
                      metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
                    false, false);
                }
              }
            }
          }
        }
      }
    } else {
      return new ReporterDetails(getCSIPVersion(), "", true, true);
    }
    return new ReporterDetails();
  }

  /*
   * mets/metsHdr/agent/note[@csip:NOTETYPE=’SOFTWARE VERSION’] The mandatory
   * agent element’s note child has a @csip:NOTETYPE attribute with a fixed value
   * of “SOFTWARE VERSION”.See also: Note type
   */
  protected ReporterDetails validateCSIP16(final MetsValidatorState metsValidatorState,
    List<MetsType.MetsHdr.Agent> agents) {
    final ReporterDetails details = new ReporterDetails();
    if (agents == null || agents.isEmpty()) {
      return new ReporterDetails(getCSIPVersion(), "", true, true);
    }
    for (MetsType.MetsHdr.Agent a : agents) {
      final String role = a.getROLE();
      final String type = a.getTYPE();
      final String otherType = a.getOTHERTYPE();
      if ((role != null && role.equals("CREATOR")) && (type != null && type.equals("OTHER"))
        && (otherType != null && otherType.equals("SOFTWARE"))) {
        final List<MetsType.MetsHdr.Agent.Note> notes = a.getNote();
        if (notes == null || notes.isEmpty()) {
          return new ReporterDetails(getCSIPVersion(),
            Message.createErrorMessage("mets/metsHdr/agent/note can't be null, in the %1$s the value is null",
              metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
            false, false);
        } else {
          for (MetsType.MetsHdr.Agent.Note note : notes) {
            if (note.getNOTETYPE() == null) {
              return new ReporterDetails(getCSIPVersion(),
                Message.createErrorMessage(
                  "mets/metsHdr/agent/note[@csip:NOTETYPE=’SOFTWARE VERSION’] "
                    + "can't be null, in %1$s the value is null",
                  metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
                false, false);
            } else {
              if (!note.getNOTETYPE().equals("SOFTWARE VERSION")) {
                  String message = "Value " + note.getNOTETYPE() +
                          " in %1$s for mets/metsHdr/agent/note[@csip:NOTETYPE=’SOFTWARE VERSION’] "
                          + "isn't valid, the value must be SOFTWARE VERSION";
                return new ReporterDetails(getCSIPVersion(), Message.createErrorMessage(message,
                  metsValidatorState.getMetsName(), metsValidatorState.isRootMets()), false, false);
              }
            }
          }
        }
      }
    }
    return details;
  }
}
