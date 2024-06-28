package org.roda_project.commons_ip2.validator.components.metsRootComponent.metsValidator;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.roda_project.commons_ip2.validator.reporter.ReporterDetails;
import org.roda_project.commons_ip2.validator.state.MetsValidatorState;
import org.roda_project.commons_ip2.validator.state.StructureValidatorState;
import org.roda_project.commons_ip2.validator.utils.Message;

/**
 * @author Carlos Afonso <cafonso@keep.pt>
 */
public abstract class MetsValidator {

  protected abstract String getCSIPVersion();

  /**
   * mets/@OBJID The mets/@OBJID attribute is mandatory, its value is a string
   * identifier for the METS document. For the package METS document, this should
   * be the name/ID of the package, i.e. the name of the package root folder. For
   * a representation level METS document this value records the name/ID of the
   * representation, i.e. the name of the top-level representation folder.
   *
   * @param structureValidatorState
   *          the contextual state {@link StructureValidatorState}
   * @param metsValidatorState
   *          the contextual state {@link MetsValidatorState}
   * @return {@link ReporterDetails}
   * @throws IOException
   *           if some I/O error occurs
   */
  protected ReporterDetails validateCSIP1(final StructureValidatorState structureValidatorState,
    final MetsValidatorState metsValidatorState) throws IOException {
    final ReporterDetails details = new ReporterDetails();
    final String objid = metsValidatorState.getMets().getOBJID();
    if (objid == null) {
      details.addIssue(Message.createErrorMessage("mets/@OBJID can't be null in %1$s the value is null.",
        metsValidatorState.getMetsName(), metsValidatorState.isRootMets()));
      details.setValid(false);
    } else {
      final boolean exist;
      if (structureValidatorState.isZipFileFlag()) {
        if (metsValidatorState.isRootMets()) {
          exist = structureValidatorState.getZipManager().checkRootFolderName(structureValidatorState.getIpPath(),
            objid);
        } else {
          exist = structureValidatorState.getZipManager().checkSubMetsFolder(structureValidatorState.getIpPath(),
            objid);
        }
      } else {
        exist = structureValidatorState.getFolderManager()
          .checkRootFolderName(Paths.get(metsValidatorState.getMetsName()), objid);
      }
      if (!exist) {
        details.addIssue(Message.createErrorMessage(
          "The folder containing the METS.xml file must have the same name " + "mets/@OBJID, See %1$s",
          metsValidatorState.getMetsName(), metsValidatorState.isRootMets()));
        details.setValid(false);
      }
    }
    return details;
  }

  /**
   * mets/@TYPE The mets/@TYPE attribute MUST be used to declare the category of
   * the content held in the package, e.g. book, journal, stereograph, video,
   * etc.. Legal values are defined in a fixed vocabulary. When the content
   * category used falls outside of the defined vocabulary the mets/@TYPE value
   * must be set to “OTHER” and the specific value declared in
   * mets/@csip:OTHERTYPE . The vocabulary will develop under the curation of the
   * DILCIS Board as additional content information type specifications are
   * produced.See also: Content Category
   *
   * @param metsValidatorState
   *          the contextual state {@link MetsValidatorState}
   * @return {@link ReporterDetails}
   */
  protected ReporterDetails validateCSIP2(final MetsValidatorState metsValidatorState,
    final List<String> contentCategory) {
    final String type = metsValidatorState.getMets().getTYPE();
    if (StringUtils.isBlank(type)) {
      return new ReporterDetails(getCSIPVersion(),
        Message.createErrorMessage("mets/@TYPE can't be null, in %1$s the value is null",
          metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
        false, false);
    } else {
      if (!contentCategory.contains(type)) {
          String message = "Value " + type + " is not valid in %1$s.";
        return new ReporterDetails(getCSIPVersion(), Message.createErrorMessage(message,
          metsValidatorState.getMetsName(), metsValidatorState.isRootMets()), false, false);
      }
    }
    return new ReporterDetails();
  }

  /**
   * mets[@TYPE=’OTHER’]/@csip:OTHERTYPE When the mets/@TYPE attribute has the
   * value “OTHER” the mets/@csip:OTHERTYPE attribute MUST be used to declare the
   * content category of the package/representation.See also: Content Category.
   *
   * @param metsValidatorState
   *          the contextual state {@link MetsValidatorState}
   * @return {@link ReporterDetails}
   */
  protected ReporterDetails validateCSIP3(final MetsValidatorState metsValidatorState) {
    final ReporterDetails details = new ReporterDetails();
    final String type = metsValidatorState.getMets().getTYPE();
    final String otherType = metsValidatorState.getMets().getOTHERTYPE();
    if (type != null && type.equals("OTHER") && (otherType == null || otherType.equals(""))) {
      details.setValid(false);
      details.addIssue(Message.createErrorMessage(
        "When mets/@type have the value OTHER mets/@csip:OTHERTYPE can't be " + "null or empty (%1$s)",
        metsValidatorState.getMetsName(), metsValidatorState.isRootMets()));
    }
    return details;
  }

  /**
   * mets/@csip:CONTENTINFORMATIONTYPE Used to declare the Content Information
   * Type Specification used when creating the package. Legal values are defined
   * in a fixed vocabulary. The attribute is mandatory for representation level
   * METS documents. The vocabulary will evolve under the care of the DILCIS Board
   * as additional Content Information Type Specifications are developed.See also:
   * Content information type specification
   *
   * @param metsValidatorState
   *          the contextual state {@link MetsValidatorState}
   * @return {@link ReporterDetails}
   */
  protected ReporterDetails validateCSIP4(final MetsValidatorState metsValidatorState,
    final List<String> contentInformationTypesList) {
    final String contentInformationType = metsValidatorState.getMets().getCONTENTINFORMATIONTYPE();
    if (contentInformationType != null) {
      if (!contentInformationTypesList.contains(contentInformationType)) {
          String message = "Value " + contentInformationType +
                  " in %1$s for mets/@csip:CONTENTINFORMATIONTYPE is not valid.";
        return new ReporterDetails(getCSIPVersion(), Message.createErrorMessage(message,
          metsValidatorState.getMetsName(), metsValidatorState.isRootMets()), false, false);
      }
    } else {
      return new ReporterDetails(getCSIPVersion(),
        Message.createErrorMessage("mets/@csip:CONTENTINFORMATIONTYPE is null in %1$s",
          metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
        false, false);
    }
    return new ReporterDetails();
  }

  /**
   * mets[@csip:CONTENTINFORMATIONTYPE=’OTHER’]/@csip:OTHERCONTENTINFORMATIONTYPE
   * When the mets/@csip:CONTENTINFORMATIONTYPE has the value “OTHER” the
   * mets/@csip:OTHERCONTENTINFORMATIONTYPE must state the content information
   * type.
   *
   * @param metsValidatorState
   *          the contextual state {@link MetsValidatorState}
   * @return {@link ReporterDetails}
   */
  protected ReporterDetails validateCSIP5(final MetsValidatorState metsValidatorState) {
    final String contentInformationType = metsValidatorState.getMets().getCONTENTINFORMATIONTYPE();
    final String otherContentInformationType = metsValidatorState.getMets().getOTHERCONTENTINFORMATIONTYPE();
    if (contentInformationType == null) {
      return new ReporterDetails();
    }
    if (contentInformationType.equals("OTHER")
      && (otherContentInformationType == null || otherContentInformationType.equals(""))) {
      return new ReporterDetails(getCSIPVersion(),
        Message.createErrorMessage(
          "When mets/@csip:CONTENTINFORMATIONTYPE have the value OTHER  "
            + "mets/@csip:OTHERCONTENTINFORMATIONTYPE can't be null or empty (%1$s)",
          metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
        false, false);
    }
    return new ReporterDetails();
  }

  /**
   * mets/@PROFILE The URL of the METS profile that the information package
   * conforms with.
   *
   * @param metsValidatorState
   *          the contextual state {@link MetsValidatorState}
   * @return {@link ReporterDetails}
   */
  protected ReporterDetails validateCSIP6(final MetsValidatorState metsValidatorState) {
    final ReporterDetails details = new ReporterDetails();
    final String profile = metsValidatorState.getMets().getPROFILE();
    if (profile == null || profile.equals("")) {
      details.setValid(false);
      details
        .addIssue(Message.createErrorMessage("mets/@PROFILE can't be null or empty, in %1$s the value is null or empty",
          metsValidatorState.getMetsName(), metsValidatorState.isRootMets()));
    }
    return details;
  }
}
