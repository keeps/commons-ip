/**
 * The contents of this file are subject to the license and copyright detailed in the LICENSE file
 * at the root of the source tree and available online at
 *
 * <p>https://github.com/keeps/commons-ip
 */
package org.roda_project.commons_ip2.model.impl.eark;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.xml.bind.JAXBException;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.commons.lang3.StringUtils;
import org.roda_project.commons_ip.utils.IPEnums.IPType;
import org.roda_project.commons_ip.utils.IPException;
import org.roda_project.commons_ip.utils.METSEnums.CreatorType;
import org.roda_project.commons_ip.utils.METSEnums.LocType;
import org.roda_project.commons_ip.utils.ValidationConstants;
import org.roda_project.commons_ip.utils.ZipEntryInfo;
import org.roda_project.commons_ip2.mets_v1_12.beans.AmdSecType;
import org.roda_project.commons_ip2.mets_v1_12.beans.DivType;
import org.roda_project.commons_ip2.mets_v1_12.beans.DivType.Fptr;
import org.roda_project.commons_ip2.mets_v1_12.beans.DivType.Mptr;
import org.roda_project.commons_ip2.mets_v1_12.beans.FileGrpType;
import org.roda_project.commons_ip2.mets_v1_12.beans.FileType;
import org.roda_project.commons_ip2.mets_v1_12.beans.FileType.FLocat;
import org.roda_project.commons_ip2.mets_v1_12.beans.MdSecType;
import org.roda_project.commons_ip2.mets_v1_12.beans.MdSecType.MdRef;
import org.roda_project.commons_ip2.mets_v1_12.beans.Mets;
import org.roda_project.commons_ip2.mets_v1_12.beans.MetsType.FileSec;
import org.roda_project.commons_ip2.mets_v1_12.beans.MetsType.FileSec.FileGrp;
import org.roda_project.commons_ip2.mets_v1_12.beans.MetsType.MetsHdr;
import org.roda_project.commons_ip2.mets_v1_12.beans.MetsType.MetsHdr.Agent;
import org.roda_project.commons_ip2.mets_v1_12.beans.MetsType.MetsHdr.Agent.Note;
import org.roda_project.commons_ip2.mets_v1_12.beans.MetsType.MetsHdr.AltRecordID;
import org.roda_project.commons_ip2.mets_v1_12.beans.StructMapType;
import org.roda_project.commons_ip2.model.IPAgent;
import org.roda_project.commons_ip2.model.IPAgentNoteTypeEnum;
import org.roda_project.commons_ip2.model.IPAltRecordID;
import org.roda_project.commons_ip2.model.IPConstants;
import org.roda_project.commons_ip2.model.IPContentInformationType;
import org.roda_project.commons_ip2.model.IPContentType;
import org.roda_project.commons_ip2.model.IPDescriptiveMetadata;
import org.roda_project.commons_ip2.model.IPFileInterface;
import org.roda_project.commons_ip2.model.IPFileShallow;
import org.roda_project.commons_ip2.model.IPHeader;
import org.roda_project.commons_ip2.model.IPInterface;
import org.roda_project.commons_ip2.model.IPMetadata;
import org.roda_project.commons_ip2.model.IPRepresentation;
import org.roda_project.commons_ip2.model.MetsWrapper;
import org.roda_project.commons_ip2.model.ValidationEntry.LEVEL;
import org.roda_project.commons_ip2.model.impl.ModelUtils;
import org.roda_project.commons_ip2.utils.METSUtils;
import org.roda_project.commons_ip2.utils.StructMapDiv;
import org.roda_project.commons_ip2.utils.Tree;
import org.roda_project.commons_ip2.utils.Utils;
import org.roda_project.commons_ip2.utils.ValidationUtils;
import org.roda_project.commons_ip2.utils.ZIPUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class EARKMETSUtils {
  /**
   * {@link Logger}.
   */
  private static final Logger LOGGER = LoggerFactory.getLogger(EARKMETSUtils.class);

  /**
   * {@link HashMap} with all data fileGrps.
   */
  private static final Map<String, FileGrp> dataFileGrp = new HashMap<>();

  private EARKMETSUtils() {
    // do nothing
  }

  public static MetsWrapper generateMETS(final String id, final String label, final String profile,
    final boolean mainMets, final Optional<List<String>> ancestors, final Path metsPath, final IPHeader ipHeader,
    final String type, final IPContentType contentType, final IPContentInformationType contentInformationType,
    final boolean isMetadata, final boolean isMetadataOther, final boolean isSchemas, final boolean isDocumentation,
    final boolean isSubmission, final boolean isRepresentations, final boolean isRepresentationsData)
    throws IPException {
    final Mets mets = new Mets();
    final MetsWrapper metsWrapper = new MetsWrapper(mets, metsPath);

    // basic attributes
    addBasicAttributesToMets(mets, id, label, profile, contentType, contentInformationType);
    // header

    addHeaderToMets(mets, ipHeader, type);

    // administrative section
    addAmdSecToMets(mets);

    // file section

    final FileSec fileSec = createFileSec();

    // Add data file grp
    addDataFileGrpToMets(metsWrapper, fileSec, mainMets, isRepresentationsData);

    // Add schemas, documentation, submission to main div
    addCommonFileGrpToMets(metsWrapper, fileSec, isSchemas, isSubmission, isDocumentation, type);

    if ((mainMets && isRepresentations) || !fileSec.getFileGrp().isEmpty()) {
      mets.setFileSec(fileSec);
    }

    // E-ARK struct map
    final StructMapType structMap = createStructMap();

    final DivType mainDiv = addCommonDivsToMainDiv(metsWrapper, id, isMetadata, isMetadataOther, isSchemas,
      isDocumentation, isSubmission, type);

    // data div
    addDataDivToMets(metsWrapper, mainDiv, mainMets, isRepresentationsData);

    structMap.setDiv(mainDiv);
    mets.getStructMap().add(structMap);

    addAncestorsToMets(mets, ancestors);

    return metsWrapper;
  }

  /**
   * Generates Shallow Sip Representation Mets with folders represented.
   * 
   * @param representation
   *          {@link IPRepresentation}
   * @param profile
   *          {@link String}
   * @param mainMets
   *          boolean if is main METS file or not.
   * @param ancestors
   *          {@link Optional} of {@link List}.
   * @param metsPath
   *          {@link Path} to the Mets
   * @param ipHeader
   *          {@link IPHeader}.
   * @param type
   *          {@link String}.
   * @param isMetadata
   *          boolean if have metadata or not.
   * @param isMetadataOther
   *          boolean if have other metadata or not.
   * @param isSchemas
   *          boolean if have schemas or not.
   * @param isDocumentation
   *          boolean if have documentation or not.
   * @param isSubmission
   *          boolean if have submission or not.
   * @param isRepresentations
   *          boolean if have representations or not.
   * @param isRepresentationsData
   *          boolean if have data in representations or not.
   * @return {@link MetsWrapper}.
   * @throws IPException
   *           if some error occurs.
   */
  public static MetsWrapper generateMetsShallow(final IPRepresentation representation, final String profile,
    final boolean mainMets, final Optional<List<String>> ancestors, final Path metsPath, final IPHeader ipHeader,
    final String type, final boolean isMetadata, final boolean isMetadataOther, final boolean isSchemas,
    final boolean isDocumentation, final boolean isSubmission, final boolean isRepresentations,
    final boolean isRepresentationsData) throws IPException {

    final Mets mets = new Mets();
    final MetsWrapper metsWrapper = new MetsWrapper(mets, metsPath);

    // basic attributes
    addBasicAttributesToMets(mets, representation.getObjectID(), representation.getDescription(), profile,
      representation.getContentType(), representation.getContentInformationType());
    // header

    addHeaderToMets(mets, ipHeader, type);

    // administrative section
    addAmdSecToMets(mets);

    // file section

    // file section

    final FileSec fileSec = createFileSec();

    // Create Shallows File Grps.
    createShallowFileGrps(metsWrapper, fileSec, mainMets, isRepresentationsData, representation);

    // Add schemas, documentation, submission to main div
    addCommonFileGrpToMets(metsWrapper, fileSec, isSchemas, isSubmission, isDocumentation, type);

    if ((mainMets && isRepresentations) || !fileSec.getFileGrp().isEmpty()) {
      mets.setFileSec(fileSec);
    }

    // E-ARK struct map
    final StructMapType structMap = createStructMap();

    final DivType mainDiv = addCommonDivsToMainDiv(metsWrapper, representation.getObjectID(), isMetadata,
      isMetadataOther, isSchemas, isDocumentation, isSubmission, type);

    // Create Data divs
    createAndAddShallowDataDiv(metsWrapper, representation, mainDiv, mainMets, isRepresentationsData);

    structMap.setDiv(mainDiv);
    mets.getStructMap().add(structMap);

    addAncestorsToMets(mets, ancestors);

    return metsWrapper;
  }

  private static FileGrp createFileGroup(final String use) {
    final FileGrp fileGroup = new FileGrp();
    fileGroup.setID(Utils.generateRandomAndPrefixedUUID());
    fileGroup.setUSE(use);
    return fileGroup;
  }

  private static DivType createDivForStructMap(final String label) {
    final DivType div = new DivType();
    div.setID(Utils.generateRandomAndPrefixedUUID());
    div.setLABEL(label);
    return div;
  }

  private static DivType createRepresentationDivForStructMap(final String representationId, final Mptr mptr) {
    final DivType div = new DivType();
    div.setID(Utils.generateRandomAndPrefixedUUID());
    div.setLABEL(IPConstants.REPRESENTATIONS_WITH_FIRST_LETTER_CAPITAL + "/" + representationId);
    div.getMptr().add(mptr);
    return div;
  }

  public static void addRepresentationMETSToZipAndToMainMETS(final Map<String, ZipEntryInfo> zipEntries,
    final MetsWrapper mainMETSWrapper, final String representationId, final MetsWrapper representationMETSWrapper,
    final String representationMetsPath, final Path buildDir) throws IPException, InterruptedException {
    try {
      if (Thread.interrupted()) {
        throw new InterruptedException();
      }

      // create mets pointer
      final Mptr mptr = new Mptr();
      mptr.setLOCTYPE(LocType.URL.toString());
      mptr.setType(IPConstants.METS_TYPE_SIMPLE);
      mptr.setHref(METSUtils.encodeHref(representationMetsPath));

      // create file
      final FileType fileType = new FileType();
      fileType.setID(Utils.generateRandomAndPrefixedFileID());

      addMETSToZip(zipEntries, representationMETSWrapper, representationMetsPath, buildDir, false, fileType);

      // add to file group and then to file section
      final FileGrp fileGrp = createFileGroup(
        IPConstants.REPRESENTATIONS_WITH_FIRST_LETTER_CAPITAL + "/" + representationId);
      final FLocat fileLocation = METSUtils.createFileLocation(representationMetsPath);
      fileType.getFLocat().add(fileLocation);
      fileGrp.getFile().add(fileType);
      mainMETSWrapper.getMets().getFileSec().getFileGrp().add(fileGrp);

      // set mets pointer
      final DivType representationDiv = createRepresentationDivForStructMap(representationId, mptr);
      mptr.setTitle(fileGrp.getID());
      mainMETSWrapper.getMainDiv().getDiv().add(representationDiv);
    } catch (JAXBException | IOException e) {
      throw new IPException("Error saving representation METS", e);
    }
  }

  private static void addMETSToZip(final Map<String, ZipEntryInfo> zipEntries, final MetsWrapper metsWrapper,
    final String metsPath, final Path buildDir, final boolean mainMets, final FileType fileType)
    throws JAXBException, IOException, IPException {
    final Path temp = Files.createTempFile(buildDir, IPConstants.METS_FILE_NAME, IPConstants.METS_FILE_EXTENSION);
    ZIPUtils.addMETSFileToZip(zipEntries, temp, metsPath, metsWrapper.getMets(), mainMets, fileType);
  }

  private static Agent createMETSAgent(final IPAgent ipAgent) {
    final Agent agent = new Agent();
    agent.setName(ipAgent.getName());
    agent.setROLE(ipAgent.getRole());
    agent.setOTHERROLE(ipAgent.getOtherRole());
    agent.setTYPE(ipAgent.getType().toString());
    agent.setOTHERTYPE(ipAgent.getOtherType());
    final Note note = new Note();
    note.setValue(ipAgent.getNote());
    note.setNOTETYPE(ipAgent.getNoteType().asString());
    agent.getNote().add(note);
    return agent;
  }

  public static IPAgent createIPAgent(final IPInterface ip, final Agent agent) {
    final IPAgent ipAgent = new IPAgent();
    CreatorType agentType;
    try {
      agentType = CreatorType.valueOf(agent.getTYPE());
    } catch (final IllegalArgumentException e) {
      agentType = CreatorType.OTHER;
      LOGGER.debug("Setting agent type to {}", agentType);
    }

    final int notes = agent.getNote().size();
    if (notes >= 1) {
      final Note note = agent.getNote().get(0);
      ipAgent.setNote(note.getValue());
      ipAgent.setNoteType(IPAgentNoteTypeEnum.parse(note.getNOTETYPE()));
    }
    if (notes > 1) {
      ValidationUtils.addIssue(ip.getValidationReport(), ValidationConstants.METS_AGENT_HAS_SEVERAL_NOTE_ENTRIES,
        LEVEL.WARN, agent.getID());
    }

    ipAgent.setName(agent.getName()).setRole(agent.getROLE()).setOtherRole(agent.getOTHERROLE()).setType(agentType)
      .setOtherType(agent.getOTHERTYPE());

    return ipAgent;
  }

  public static MdRef addDescriptiveMetadataToMETS(final MetsWrapper metsWrapper,
    final IPDescriptiveMetadata descriptiveMetadata, final String descriptiveMetadataPath)
    throws IPException, InterruptedException {
    return addMetadataToMETS(metsWrapper, descriptiveMetadata, descriptiveMetadataPath,
      descriptiveMetadata.getMetadataType().getType().getType(), descriptiveMetadata.getMetadataType().getOtherType(),
      descriptiveMetadata.getMetadataVersion(), true);
  }

  public static MdRef addOtherMetadataToMETS(final MetsWrapper metsWrapper, final IPMetadata otherMetadata,
    final String otherMetadataPath) throws IPException, InterruptedException {
    return addMetadataToMETS(metsWrapper, otherMetadata, otherMetadataPath, "OTHER", null, null, false);
  }

  private static MdRef addMetadataToMETS(final MetsWrapper metsWrapper, final IPMetadata metadata,
    final String metadataPath, final String mdType, final String mdOtherType, final String mdTypeVersion,
    final boolean isDescriptive) throws IPException, InterruptedException {
    final MdSecType dmdSec = new MdSecType();
    dmdSec.setSTATUS(metadata.getMetadataStatus().toString());
    dmdSec.setID(Utils.generateRandomAndPrefixedUUID());

    final MdRef mdRef = createMdRef(metadata.getId(), metadataPath);
    mdRef.setMDTYPE(mdType);
    if (StringUtils.isNotBlank(mdOtherType)) {
      mdRef.setOTHERMDTYPE(mdOtherType);
    }
    mdRef.setMDTYPEVERSION(mdTypeVersion);

    // set mimetype, date creation, etc.
    METSUtils.setFileBasicInformation(metadata.getMetadata().getPath(), mdRef);
    // also set date created in dmdSec elem
    dmdSec.setCREATED(mdRef.getCREATED());

    // structural map info.
    if (isDescriptive) {
      metsWrapper.getMetadataDiv().getDMDID().add(dmdSec);
    } else {
      metsWrapper.getOtherMetadataDiv().getDMDID().add(dmdSec);
    }

    dmdSec.setMdRef(mdRef);
    metsWrapper.getMets().getDmdSec().add(dmdSec);
    return mdRef;
  }

  public static MdRef addPreservationMetadataToMETS(final MetsWrapper metsWrapper,
    final IPMetadata preservationMetadata, final String preservationMetadataPath)
    throws IPException, InterruptedException {
    final MdSecType digiprovMD = new MdSecType();
    digiprovMD.setSTATUS(preservationMetadata.getMetadataStatus().toString());
    digiprovMD.setID(Utils.generateRandomAndPrefixedUUID());
    final MdRef mdRef = createMdRef(preservationMetadata.getId(), preservationMetadataPath);
    mdRef.setMDTYPE(preservationMetadata.getMetadataType().asString());

    // set mimetype, date creation, etc.
    METSUtils.setFileBasicInformation(preservationMetadata.getMetadata().getPath(), mdRef);

    // structural map info.
    metsWrapper.getMetadataDiv().getADMID().add(digiprovMD);

    digiprovMD.setMdRef(mdRef);
    metsWrapper.getMets().getAmdSec().get(0).getDigiprovMD().add(digiprovMD);
    return mdRef;
  }

  private static String escapeNCName(final String id) {
    return id.replaceAll("[:@$%&/+,;\\s]", "_");
  }

  private static MdRef createMdRef(final String id, final String metadataPath) {
    final MdRef mdRef = new MdRef();
    mdRef.setID(escapeNCName(id));
    mdRef.setType(IPConstants.METS_TYPE_SIMPLE);
    mdRef.setLOCTYPE(LocType.URL.toString());
    mdRef.setHref(METSUtils.encodeHref(metadataPath));
    return mdRef;
  }

  public static void addDataFileToMETS(final MetsWrapper representationMETS, final IPFileShallow shallow) {
    final FileType file = shallow.getFileType();
    file.setID(Utils.generateRandomAndPrefixedFileID());

    // add to file section
    final FLocat fileLocation = METSUtils.createShallowFileLocation(shallow.getFileLocation().toString());
    file.getFLocat().add(fileLocation);

    addDataFileFromShallow(representationMETS.getDataFileGroup().getFileGrp(), shallow, file);
  }

  public static FileType addDataFileToMETS(final MetsWrapper representationMETS, final String dataFilePath,
    final Path dataFile) throws IPException, InterruptedException {
    final FileType file = new FileType();
    file.setID(Utils.generateRandomAndPrefixedFileID());

    // set mimetype, date creation, etc.
    METSUtils.setFileBasicInformation(LOGGER, dataFile, file);

    // add to file section
    final FLocat fileLocation = METSUtils.createFileLocation(dataFilePath);
    file.getFLocat().add(fileLocation);
    representationMETS.getDataFileGroup().getFile().add(file);

    // add to struct map
    if (representationMETS.getDataDiv().getFptr().isEmpty()) {
      final Fptr fptr = new Fptr();
      fptr.setFILEID(representationMETS.getDataFileGroup());
      representationMETS.getDataDiv().getFptr().add(fptr);
    }
    return file;
  }

  public static FileType addSchemaFileToMETS(final MetsWrapper metsWrapper, final String schemaFilePath,
    final Path schemaFile) throws IPException, InterruptedException {
    final FileType file = new FileType();
    file.setID(Utils.generateRandomAndPrefixedFileID());

    // set mimetype, date creation, etc.
    METSUtils.setFileBasicInformation(LOGGER, schemaFile, file);

    // add to file section
    final FLocat fileLocation = METSUtils.createFileLocation(schemaFilePath);
    file.getFLocat().add(fileLocation);
    if (metsWrapper.getSchemasFileGroup() != null) {
      metsWrapper.getSchemasFileGroup().getFile().add(file);
    }

    // add to struct map
    if (metsWrapper.getSchemasDiv() != null && metsWrapper.getSchemasDiv().getFptr().isEmpty()) {
      final Fptr fptr = new Fptr();
      fptr.setFILEID(metsWrapper.getSchemasFileGroup());
      metsWrapper.getSchemasDiv().getFptr().add(fptr);
    }
    return file;
  }

  public static FileType addSubmissionFileToMETS(final MetsWrapper metsWrapper, final String submissionFilePath,
    final Path submissionFile) throws IPException, InterruptedException {
    final FileType file = new FileType();
    file.setID(Utils.generateRandomAndPrefixedFileID());

    // set mimetype, date creation, etc.
    METSUtils.setFileBasicInformation(LOGGER, submissionFile, file);

    // add to file section
    final FLocat fileLocation = METSUtils.createFileLocation(submissionFilePath);
    file.getFLocat().add(fileLocation);
    metsWrapper.getSubmissionFileGroup().getFile().add(file);

    // add to struct map
    final Fptr fptr = new Fptr();
    fptr.setFILEID(file);
    metsWrapper.getSubmissionsDiv().getFptr().add(fptr);
    return file;
  }

  public static FileType addDocumentationFileToMETS(final MetsWrapper metsWrapper, final String documentationFilePath,
    final Path documentationFile) throws IPException, InterruptedException {
    final FileType file = new FileType();
    file.setID(Utils.generateRandomAndPrefixedFileID());

    // set mimetype, date creation, etc.
    METSUtils.setFileBasicInformation(LOGGER, documentationFile, file);

    // add to file section
    final FLocat fileLocation = METSUtils.createFileLocation(documentationFilePath);
    file.getFLocat().add(fileLocation);
    metsWrapper.getDocumentationFileGroup().getFile().add(file);

    // add to struct map
    if (metsWrapper.getDocumentationDiv().getFptr().isEmpty()) {
      final Fptr fptr = new Fptr();
      fptr.setFILEID(metsWrapper.getDocumentationFileGroup());
      metsWrapper.getDocumentationDiv().getFptr().add(fptr);
    }

    return file;
  }

  private static StructMapType generateAncestorStructMap(final List<String> ancestors) {
    final StructMapType structMap = new StructMapType();
    structMap.setID(Utils.generateRandomAndPrefixedUUID());
    structMap.setLABEL(IPConstants.RODA_STRUCTURAL_MAP);

    final DivType mainDiv = createDivForStructMap(IPConstants.RODA_DIV_LABEL);
    final DivType ancestorsDiv = createDivForStructMap(IPConstants.RODA_ANCESTORS_DIV_LABEL);

    for (String anc : ancestors) {
      final Mptr mptr = new Mptr();
      mptr.setType(IPConstants.METS_TYPE_SIMPLE);
      mptr.setHref(METSUtils.encodeHref(anc));
      mptr.setLOCTYPE(LocType.HANDLE.toString());
      ancestorsDiv.getMptr().add(mptr);
    }
    mainDiv.getDiv().add(ancestorsDiv);
    structMap.setDiv(mainDiv);
    return structMap;
  }

  public static List<String> extractAncestorsFromStructMap(final Mets mets) {
    final List<String> ancestors = new ArrayList<>();

    for (StructMapType structMap : mets.getStructMap()) {
      if (structMap.getLABEL() != null && IPConstants.RODA_STRUCTURAL_MAP.equalsIgnoreCase(structMap.getLABEL())
        && structMap.getDiv() != null) {
        final DivType mainDiv = structMap.getDiv();

        if (IPConstants.RODA_DIV_LABEL.equalsIgnoreCase(mainDiv.getLABEL()) && mainDiv.getDiv() != null) {
          for (DivType div : mainDiv.getDiv()) {
            if (IPConstants.RODA_ANCESTORS_DIV_LABEL.equalsIgnoreCase(div.getLABEL()) && div.getMptr() != null) {
              for (Mptr m : div.getMptr()) {
                final String href = METSUtils.decodeHref(m.getHref());
                if (StringUtils.isNotBlank(href)) {
                  ancestors.add(href);
                }
              }
            }
          }
        }
      }
    }

    return ancestors;
  }

  // Common methods to GenerateMets

  private static void addBasicAttributesToMets(final Mets mets, final String id, final String label,
    final String profile, final IPContentType contentType, final IPContentInformationType contentInformationType) {

    mets.setOBJID(id);
    mets.setPROFILE(profile);
    mets.setLABEL(label);

    mets.setTYPE(contentType.getType().asString());
    if (contentType.isOtherAndOtherTypeIsDefined()) {
      mets.setOTHERTYPE(contentType.getOtherType());
    }
    if (contentInformationType != null) {
      mets.setCONTENTINFORMATIONTYPE(contentInformationType.getType().toString());
      if (!"".equals(contentInformationType.getOtherType())) {
        mets.setOTHERCONTENTINFORMATIONTYPE(contentInformationType.getOtherType());
      }
    }
  }

  private static void addHeaderToMets(final Mets mets, final IPHeader ipHeader, final String type) throws IPException {
    final MetsHdr header = new MetsHdr();
    try {
      final XMLGregorianCalendar currentDate = Utils.getCurrentCalendar();
      header.setCREATEDATE(currentDate);
      header.setLASTMODDATE(currentDate);
      header.setRECORDSTATUS(ipHeader.getStatus().toString());
      header.setOAISPACKAGETYPE(type);
    } catch (final DatatypeConfigurationException e) {
      throw new IPException("Error getting current calendar", e);
    }

    // header/agent
    for (IPAgent sipAgent : ipHeader.getAgents()) {
      header.getAgent().add(createMETSAgent(sipAgent));
    }

    // records
    for (IPAltRecordID iprecord : ipHeader.getAltRecordIDs()) {
      final AltRecordID recordId = new AltRecordID();
      recordId.setTYPE(iprecord.getType());
      recordId.setValue(iprecord.getValue());
      header.getAltRecordID().add(recordId);
    }

    mets.setMetsHdr(header);
  }

  // Separation of actions in generateMets

  private static void addAmdSecToMets(final Mets mets) {
    final AmdSecType amdSec = new AmdSecType();
    amdSec.setID(Utils.generateRandomAndPrefixedUUID());
    mets.getAmdSec().add(amdSec);
  }

  private static void addCommonFileGrpToMets(final MetsWrapper metsWrapper, final FileSec fileSec,
    final boolean isSchemas, final boolean isSubmission, final boolean isDocumentation, final String type) {
    if (isSchemas) {
      final FileGrp schemasFileGroup = createFileGroup(IPConstants.SCHEMAS_WITH_FIRST_LETTER_CAPITAL);
      fileSec.getFileGrp().add(schemasFileGroup);
      metsWrapper.setSchemasFileGroup(schemasFileGroup);
      if (IPType.AIP.toString().equals(type) && isSubmission) {
        final FileGrp submissionFileGroup = createFileGroup(IPConstants.SUBMISSION);
        fileSec.getFileGrp().add(submissionFileGroup);
        metsWrapper.setSubmissionFileGroup(submissionFileGroup);
      }
    }
    if (isDocumentation) {
      final FileGrp documentationFileGroup = createFileGroup(IPConstants.DOCUMENTATION_WITH_FIRST_LETTER_CAPITAL);
      fileSec.getFileGrp().add(documentationFileGroup);
      metsWrapper.setDocumentationFileGroup(documentationFileGroup);
    }
  }

  private static FileSec createFileSec() {
    final FileSec fileSec = new FileSec();
    fileSec.setID(Utils.generateRandomAndPrefixedUUID());
    return fileSec;
  }

  private static void addDataFileGrpToMets(final MetsWrapper metsWrapper, final FileSec fileSec, final boolean mainMets,
    final boolean isRepresentationsData) {
    if (!mainMets && isRepresentationsData) {
      final FileGrp dataFileGroup = createFileGroup(IPConstants.DATA_WITH_FIRST_LETTER_CAPITAL);
      fileSec.getFileGrp().add(dataFileGroup);
      metsWrapper.setDataFileGroup(dataFileGroup);
    }
  }

  private static StructMapType createStructMap() {
    final StructMapType structMap = new StructMapType();
    structMap.setID(Utils.generateRandomAndPrefixedUUID());
    structMap.setTYPE(IPConstants.METS_TYPE_PHYSICAL);
    structMap.setLABEL(IPConstants.COMMON_SPEC_STRUCTURAL_MAP);
    return structMap;
  }

  private static DivType addCommonDivsToMainDiv(final MetsWrapper metsWrapper, final String id,
    final boolean isMetadata, final boolean isMetadataOther, final boolean isSchemas, final boolean isDocumentation,
    final boolean isSubmission, final String type) {
    final DivType mainDiv = createDivForStructMap(id);
    metsWrapper.setMainDiv(mainDiv);
    // metadata
    if (isMetadata) {
      final DivType metadataDiv = createDivForStructMap(IPConstants.METADATA_WITH_FIRST_LETTER_CAPITAL);
      metsWrapper.setMetadataDiv(metadataDiv);
      mainDiv.getDiv().add(metadataDiv);
    }
    // INFO metadata/descriptive & metadata/preservation will be added to
    // metadata div appropriate attributes
    // metadata/other
    if (isMetadataOther) {
      final DivType otherMetadataDiv = createDivForStructMap(IPConstants.METADATA_WITH_FIRST_LETTER_CAPITAL
        + IPConstants.ZIP_PATH_SEPARATOR + IPConstants.OTHER_WITH_FIRST_LETTER_CAPITAL);
      metsWrapper.setOtherMetadataDiv(otherMetadataDiv);
      mainDiv.getDiv().add(otherMetadataDiv);
    }

    // schemas
    if (isSchemas) {
      final DivType schemasDiv = createDivForStructMap(IPConstants.SCHEMAS_WITH_FIRST_LETTER_CAPITAL);
      metsWrapper.setSchemasDiv(schemasDiv);
      mainDiv.getDiv().add(schemasDiv);
    }
    // documentation
    if (isDocumentation) {
      final DivType documentationDiv = createDivForStructMap(IPConstants.DOCUMENTATION_WITH_FIRST_LETTER_CAPITAL);
      metsWrapper.setDocumentationDiv(documentationDiv);
      mainDiv.getDiv().add(documentationDiv);
    }
    // submission
    if (IPType.AIP.toString().equals(type) && isSubmission) {
      final DivType submissionDiv = createDivForStructMap(IPConstants.SUBMISSION);
      metsWrapper.setSubmissionsDiv(submissionDiv);
      mainDiv.getDiv().add(submissionDiv);
    }
    return mainDiv;
  }

  private static void addDataDivToMets(final MetsWrapper metsWrapper, final DivType mainDiv, final boolean mainMets,
    final boolean isRepresentationsData) {
    if (!mainMets && isRepresentationsData) {
      final DivType dataDiv = createDivForStructMap(IPConstants.DATA_WITH_FIRST_LETTER_CAPITAL);
      metsWrapper.setDataDiv(dataDiv);
      mainDiv.getDiv().add(dataDiv);
    }
  }

  private static void addAncestorsToMets(final Mets mets, final Optional<List<String>> ancestors) {
    // RODA struct map
    if (ancestors.isPresent() && !ancestors.get().isEmpty()) {
      final StructMapType structMapParent = generateAncestorStructMap(ancestors.get());
      mets.getStructMap().add(structMapParent);
    }
  }

  public static Map<String, FileGrp> getDataFileGrp() {
    return dataFileGrp;
  }

  /**
   * Iterates trough files in Representation, create the FileGrps and adds to the
   * {@link HashMap}.
   * 
   * @param representation
   *          {@link IPRepresentation}
   */
  private static void addFileGrps(final IPRepresentation representation) {

    for (IPFileInterface file : representation.getData()) {
      final String dataFilePath;
      if (file.getRelativeFolders() == null || file.getRelativeFolders().isEmpty()) {
        dataFilePath = IPConstants.DATA_WITH_FIRST_LETTER_CAPITAL;
      } else {
        dataFilePath = IPConstants.DATA_FOLDER + ModelUtils.getFoldersFromList(file.getRelativeFolders());
      }

      if (!dataFileGrp.containsKey(dataFilePath) && ((IPFileShallow) file).getFileLocation() != null) {
        final FileGrp dataFileGroup = createFileGroup(dataFilePath);
        dataFileGrp.put(dataFilePath, dataFileGroup);
      }
    }
  }

  /**
   * Creates the Shallow {@link FileGrp} and adds to {@link MetsWrapper} and
   * {@link FileSec}.
   * 
   * @param metsWrapper
   *          {@link MetsWrapper}.
   * @param fileSec
   *          a {@link FileSec}.
   * @param mainMets
   *          boolean if is main METS file or not.
   * @param isRepresentationsData
   *          boolean if have Data in Representation.
   * @param representation
   *          {@link IPRepresentation}.
   */
  private static void createShallowFileGrps(final MetsWrapper metsWrapper, final FileSec fileSec,
    final boolean mainMets, final boolean isRepresentationsData, final IPRepresentation representation) {
    if (!mainMets && isRepresentationsData) {
      addFileGrps(representation);
      for (Map.Entry<String, FileGrp> entry : dataFileGrp.entrySet()) {
        fileSec.getFileGrp().add(entry.getValue());
        if (metsWrapper.getDataFileGroup() == null) {
          metsWrapper.setDataFileGroup(new FileGrpType());
        }
        metsWrapper.getDataFileGroup().getFileGrp().add(entry.getValue());
      }
    }
  }

  /**
   * Creates the Data divs from the {@link Tree}.
   * 
   * @param metsWrapper
   *          {@link MetsWrapper}.
   * @param representation
   *          {@link IPRepresentation}.
   * @param mainDiv
   *          {@link DivType} main Div.
   * @param mainMets
   *          boolean if is main METS file or not.
   * @param isRepresentationsData
   *          boolean if have Data in Representation.
   */
  private static void createAndAddShallowDataDiv(final MetsWrapper metsWrapper, final IPRepresentation representation,
    final DivType mainDiv, final boolean mainMets, final boolean isRepresentationsData) {
    if (!mainMets && isRepresentationsData) {
      final Tree<StructMapDiv> dataDivsTree = createTree(representation);
      DivType dataDiv = createDivForStructMap(dataDivsTree.getRoot().getLabel());
      if (dataDiv.getFptr().isEmpty() && dataFileGrp.get(dataDiv.getLABEL()) != null) {
        final Fptr fptr = new Fptr();
        fptr.setFILEID(dataFileGrp.get(dataDiv.getLABEL()));
        dataDiv.getFptr().add(fptr);
      }
      createDataDiv(dataDivsTree, dataDiv);
      metsWrapper.setDataDiv(dataDiv);
      mainDiv.getDiv().add(dataDiv);
    }
  }

  /**
   * Creates the {@link Tree} of {@link StructMapDiv}.
   * 
   * @param representation
   *          {@link IPRepresentation}.
   * @return {@link Tree} of {@link StructMapDiv}.
   */
  private static Tree<StructMapDiv> createTree(IPRepresentation representation) {
    final Tree<StructMapDiv> divsTree = new Tree<>(new StructMapDiv(IPConstants.DATA_WITH_FIRST_LETTER_CAPITAL));
    for (IPFileInterface file : representation.getData()) {
      IPFileShallow shallow = (IPFileShallow) file;
      final String dataFilePath;
      if (shallow.getRelativeFolders() == null || shallow.getRelativeFolders().isEmpty()) {
        dataFilePath = IPConstants.DATA_WITH_FIRST_LETTER_CAPITAL;
      } else {
        dataFilePath = IPConstants.DATA_FOLDER + ModelUtils.getFoldersFromList(shallow.getRelativeFolders());
      }
      addNodes(divsTree, dataFilePath, file.getRelativeFolders());
    }
    return divsTree;
  }

  /**
   * Recursive method to add Nodes in the {@link Tree}.
   * 
   * @param divTree
   *          {@link Tree} of {@link StructMapDiv}.
   * @param fileLocation
   *          {@link String} key of {@link HashMap} of fileGrps.
   * @param fileRelativeFolders
   *          {@link List} of {@link String} of file Relative folders.
   */
  private static void addNodes(final Tree<StructMapDiv> divTree, final String fileLocation,
    List<String> fileRelativeFolders) {
    if (fileRelativeFolders == null || fileRelativeFolders.isEmpty()) {
      if (fileLocation != null) {
        divTree.getRoot().setFileLocation(fileLocation);
      }
    } else {
      if (fileRelativeFolders.get(0) != null) {
        Tree<StructMapDiv> childNode = divTree.addChild(new StructMapDiv(fileRelativeFolders.get(0)),
          divTree.getRoot());
        List<String> newRelativeFolders = new ArrayList<>(fileRelativeFolders);
        newRelativeFolders.remove(0);
        addNodes(childNode, fileLocation, newRelativeFolders);
      }
    }
  }

  /**
   * Recursive method who creates the Struct Map inner Data Divs from {@link Tree}
   * of {@link StructMapDiv}.
   * 
   * @param dataDivsTree
   *          {@link Tree} of {@link StructMapDiv}.
   * @param dataDiv
   *          {@link DivType}.
   */
  private static void createDataDiv(Tree<StructMapDiv> dataDivsTree, DivType dataDiv) {
    if (!dataDivsTree.getChilds().isEmpty()) {
      for (Tree<StructMapDiv> child : dataDivsTree.getChilds()) {
        DivType div = createDivForStructMap(child.getRoot().getLabel());
        if (div.getFptr().isEmpty() && dataFileGrp.get(child.getRoot().getFileLocation()) != null) {
          final Fptr fptr = new Fptr();
          fptr.setFILEID(dataFileGrp.get(child.getRoot().getFileLocation()));
          div.getFptr().add(fptr);
        }
        dataDiv.getDiv().add(div);
        createDataDiv(child, div);
      }
    }
  }

  /**
   * Adds Data File to the respective {@link FileGrp}.
   *
   * @param fileGrpTypes
   *          {@link List} of {@link FileGrpType}.
   * @param shallow
   *          {@link IPFileShallow}.
   * @param file
   *          {@link FileType}.
   */
  private static void addDataFileFromShallow(List<FileGrpType> fileGrpTypes, IPFileShallow shallow, FileType file) {
    for (FileGrpType fileGrpType : fileGrpTypes) {
      final String dataFilePath;
      if (shallow.getRelativeFolders() == null || shallow.getRelativeFolders().isEmpty()) {
        dataFilePath = IPConstants.DATA_WITH_FIRST_LETTER_CAPITAL;
      } else {
        dataFilePath = IPConstants.DATA_FOLDER + ModelUtils.getFoldersFromList(shallow.getRelativeFolders());
      }
      if (fileGrpType.getUSE().equals(dataFilePath)) {
        fileGrpType.getFile().add(file);
      }
    }
  }

  public static void cleanFileGrpStructure() {
    dataFileGrp.clear();
  }

}
