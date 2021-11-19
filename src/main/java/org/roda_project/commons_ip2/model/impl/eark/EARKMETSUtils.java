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
import org.roda_project.commons_ip2.model.IPFileShallow;
import org.roda_project.commons_ip2.model.IPHeader;
import org.roda_project.commons_ip2.model.IPInterface;
import org.roda_project.commons_ip2.model.IPMetadata;
import org.roda_project.commons_ip2.model.MetsWrapper;
import org.roda_project.commons_ip2.model.ValidationEntry.LEVEL;
import org.roda_project.commons_ip2.utils.METSUtils;
import org.roda_project.commons_ip2.utils.Utils;
import org.roda_project.commons_ip2.utils.ValidationUtils;
import org.roda_project.commons_ip2.utils.ZIPUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class EARKMETSUtils {
  private static final Logger LOGGER = LoggerFactory.getLogger(EARKMETSUtils.class);

  private EARKMETSUtils() {
    // do nothing
  }

  public static MetsWrapper generateMETS(
      String id,
      String label,
      String profile,
      boolean mainMets,
      Optional<List<String>> ancestors,
      Path metsPath,
      IPHeader ipHeader,
      String type,
      IPContentType contentType,
      IPContentInformationType contentInformationType,
      boolean isMetadata,
      boolean isMetadataOther,
      boolean isSchemas,
      boolean isDocumentation,
      boolean isSubmission)
      throws IPException {
    Mets mets = new Mets();
    MetsWrapper metsWrapper = new MetsWrapper(mets, metsPath);

    // basic attributes
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

    // header
    MetsHdr header = new MetsHdr();
    try {
      XMLGregorianCalendar currentDate = Utils.getCurrentCalendar();
      header.setCREATEDATE(currentDate);
      header.setLASTMODDATE(currentDate);
      header.setRECORDSTATUS(ipHeader.getStatus().toString());
      header.setOAISPACKAGETYPE(type);
    } catch (DatatypeConfigurationException e) {
      throw new IPException("Error getting current calendar", e);
    }

    // header/agent
    for (IPAgent sipAgent : ipHeader.getAgents()) {
      header.getAgent().add(createMETSAgent(sipAgent));
    }

    // records
    for (IPAltRecordID iprecord : ipHeader.getAltRecordIDs()) {
      AltRecordID recordId = new AltRecordID();
      recordId.setTYPE(iprecord.getType());
      recordId.setValue(iprecord.getValue());
      header.getAltRecordID().add(recordId);
    }

    mets.setMetsHdr(header);

    // administrative section
    AmdSecType amdSec = new AmdSecType();
    amdSec.setID(Utils.generateRandomAndPrefixedUUID());
    mets.getAmdSec().add(amdSec);

    // file section

    FileSec fileSec = new FileSec();
    fileSec.setID(Utils.generateRandomAndPrefixedUUID());
    if (!mainMets) {
      FileGrp dataFileGroup = createFileGroup(IPConstants.DATA_WITH_FIRST_LETTER_CAPITAL);
      fileSec.getFileGrp().add(dataFileGroup);
      metsWrapper.setDataFileGroup(dataFileGroup);
    }
    if (isSchemas) {
      FileGrp schemasFileGroup = createFileGroup(IPConstants.SCHEMAS_WITH_FIRST_LETTER_CAPITAL);
      fileSec.getFileGrp().add(schemasFileGroup);
      metsWrapper.setSchemasFileGroup(schemasFileGroup);
      if (IPType.AIP.toString().equals(type) && isSubmission) {
        FileGrp submissionFileGroup = createFileGroup(IPConstants.SUBMISSION);
        fileSec.getFileGrp().add(submissionFileGroup);
        metsWrapper.setSubmissionFileGroup(submissionFileGroup);
      }
    }
    if (isDocumentation) {
      FileGrp documentationFileGroup =
          createFileGroup(IPConstants.DOCUMENTATION_WITH_FIRST_LETTER_CAPITAL);
      fileSec.getFileGrp().add(documentationFileGroup);
      metsWrapper.setDocumentationFileGroup(documentationFileGroup);
    }
    if (!fileSec.getFileGrp().isEmpty()) {
      mets.setFileSec(fileSec);
    }

    // E-ARK struct map
    StructMapType structMap = new StructMapType();
    structMap.setID(Utils.generateRandomAndPrefixedUUID());
    structMap.setTYPE(IPConstants.METS_TYPE_PHYSICAL);
    structMap.setLABEL(IPConstants.COMMON_SPEC_STRUCTURAL_MAP);

    DivType mainDiv = createDivForStructMap(id);
    metsWrapper.setMainDiv(mainDiv);
    // metadata
    if (isMetadata) {
      DivType metadataDiv = createDivForStructMap(IPConstants.METADATA_WITH_FIRST_LETTER_CAPITAL);
      metsWrapper.setMetadataDiv(metadataDiv);
      mainDiv.getDiv().add(metadataDiv);
    }
    // INFO metadata/descriptive & metadata/preservation will be added to
    // metadata div appropriate attributes

    // metadata/other
    if (isMetadataOther) {
      DivType otherMetadataDiv =
          createDivForStructMap(
              IPConstants.METADATA_WITH_FIRST_LETTER_CAPITAL
                  + IPConstants.ZIP_PATH_SEPARATOR
                  + IPConstants.OTHER_WITH_FIRST_LETTER_CAPITAL);
      metsWrapper.setOtherMetadataDiv(otherMetadataDiv);
      mainDiv.getDiv().add(otherMetadataDiv);
    }
    // data
    if (!mainMets) {
      DivType dataDiv = createDivForStructMap(IPConstants.DATA_WITH_FIRST_LETTER_CAPITAL);
      metsWrapper.setDataDiv(dataDiv);
      mainDiv.getDiv().add(dataDiv);
    }
    // schemas
    if (isSchemas) {
      DivType schemasDiv = createDivForStructMap(IPConstants.SCHEMAS_WITH_FIRST_LETTER_CAPITAL);
      metsWrapper.setSchemasDiv(schemasDiv);
      mainDiv.getDiv().add(schemasDiv);
    }
    // documentation
    if (isDocumentation) {
      DivType documentationDiv =
          createDivForStructMap(IPConstants.DOCUMENTATION_WITH_FIRST_LETTER_CAPITAL);
      metsWrapper.setDocumentationDiv(documentationDiv);
      mainDiv.getDiv().add(documentationDiv);
    }
    // submission
    if (IPType.AIP.toString().equals(type) && isSubmission) {
      DivType submissionDiv = createDivForStructMap(IPConstants.SUBMISSION);
      metsWrapper.setSubmissionsDiv(submissionDiv);
      mainDiv.getDiv().add(submissionDiv);
    }
    structMap.setDiv(mainDiv);
    mets.getStructMap().add(structMap);

    // RODA struct map
    if (ancestors.isPresent() && !ancestors.get().isEmpty()) {
      StructMapType structMapParent = generateAncestorStructMap(ancestors.get());
      mets.getStructMap().add(structMapParent);
    }

    return metsWrapper;
  }

  private static FileGrp createFileGroup(String use) {
    FileGrp fileGroup = new FileGrp();
    fileGroup.setID(Utils.generateRandomAndPrefixedUUID());
    fileGroup.setUSE(use);
    return fileGroup;
  }

  private static DivType createDivForStructMap(String label) {
    DivType div = new DivType();
    div.setID(Utils.generateRandomAndPrefixedUUID());
    div.setLABEL(label);
    return div;
  }

  private static DivType createRepresentationDivForStructMap(String representationId, Mptr mptr) {
    DivType div = new DivType();
    div.setID(Utils.generateRandomAndPrefixedUUID());
    div.setLABEL(IPConstants.REPRESENTATIONS_WITH_FIRST_LETTER_CAPITAL + "/" + representationId);
    div.getMptr().add(mptr);
    return div;
  }

  public static void addRepresentationMETSToZipAndToMainMETS(
      Map<String, ZipEntryInfo> zipEntries,
      MetsWrapper mainMETSWrapper,
      String representationId,
      MetsWrapper representationMETSWrapper,
      String representationMetsPath,
      Path buildDir)
      throws IPException, InterruptedException {
    try {
      if (Thread.interrupted()) {
        throw new InterruptedException();
      }

      // create mets pointer
      Mptr mptr = new Mptr();
      mptr.setLOCTYPE(LocType.URL.toString());
      mptr.setType(IPConstants.METS_TYPE_SIMPLE);
      mptr.setHref(METSUtils.encodeHref(representationMetsPath));

      // create file
      FileType fileType = new FileType();
      fileType.setID(Utils.generateRandomAndPrefixedFileID());

      addMETSToZip(
          zipEntries, representationMETSWrapper, representationMetsPath, buildDir, false, fileType);

      // add to file group and then to file section
      FileGrp fileGrp =
          createFileGroup(
              IPConstants.REPRESENTATIONS_WITH_FIRST_LETTER_CAPITAL + "/" + representationId);
      FLocat fileLocation = METSUtils.createFileLocation(representationMetsPath);
      fileType.getFLocat().add(fileLocation);
      fileGrp.getFile().add(fileType);
      mainMETSWrapper.getMets().getFileSec().getFileGrp().add(fileGrp);

      // set mets pointer
      DivType representationDiv = createRepresentationDivForStructMap(representationId, mptr);
      mptr.setTitle(fileGrp.getID());
      mainMETSWrapper.getMainDiv().getDiv().add(representationDiv);
    } catch (JAXBException | IOException e) {
      throw new IPException("Error saving representation METS", e);
    }
  }

  private static void addMETSToZip(
      Map<String, ZipEntryInfo> zipEntries,
      MetsWrapper metsWrapper,
      String metsPath,
      Path buildDir,
      boolean mainMets,
      FileType fileType)
      throws JAXBException, IOException, IPException {
    Path temp =
        Files.createTempFile(buildDir, IPConstants.METS_FILE_NAME, IPConstants.METS_FILE_EXTENSION);
    ZIPUtils.addMETSFileToZip(
        zipEntries, temp, metsPath, metsWrapper.getMets(), mainMets, fileType);
  }

  private static Agent createMETSAgent(IPAgent ipAgent) {
    Agent agent = new Agent();
    agent.setName(ipAgent.getName());
    agent.setROLE(ipAgent.getRole());
    agent.setOTHERROLE(ipAgent.getOtherRole());
    agent.setTYPE(ipAgent.getType().toString());
    agent.setOTHERTYPE(ipAgent.getOtherType());
    Note note = new Note();
    note.setValue(ipAgent.getNote());
    note.setNOTETYPE(ipAgent.getNoteType().asString());
    agent.getNote().add(note);
    return agent;
  }

  public static IPAgent createIPAgent(IPInterface ip, Agent agent) {
    IPAgent ipAgent = new IPAgent();
    CreatorType agentType;
    try {
      agentType = CreatorType.valueOf(agent.getTYPE());
    } catch (IllegalArgumentException e) {
      agentType = CreatorType.OTHER;
      LOGGER.debug("Setting agent type to {}", agentType);
    }

    int notes = agent.getNote().size();
    if (notes >= 1) {
      Note note = agent.getNote().get(0);
      ipAgent.setNote(note.getValue());
      ipAgent.setNoteType(IPAgentNoteTypeEnum.parse(note.getNOTETYPE()));
    }
    if (notes > 1) {
      ValidationUtils.addIssue(
          ip.getValidationReport(),
          ValidationConstants.METS_AGENT_HAS_SEVERAL_NOTE_ENTRIES,
          LEVEL.WARN,
          agent.getID());
    }

    ipAgent
        .setName(agent.getName())
        .setRole(agent.getROLE())
        .setOtherRole(agent.getOTHERROLE())
        .setType(agentType)
        .setOtherType(agent.getOTHERTYPE());

    return ipAgent;
  }

  public static MdRef addDescriptiveMetadataToMETS(
      MetsWrapper metsWrapper,
      IPDescriptiveMetadata descriptiveMetadata,
      String descriptiveMetadataPath)
      throws IPException, InterruptedException {
    return addMetadataToMETS(
        metsWrapper,
        descriptiveMetadata,
        descriptiveMetadataPath,
        descriptiveMetadata.getMetadataType().getType().getType(),
        descriptiveMetadata.getMetadataType().getOtherType(),
        descriptiveMetadata.getMetadataVersion(),
        true);
  }

  public static MdRef addOtherMetadataToMETS(
      MetsWrapper metsWrapper, IPMetadata otherMetadata, String otherMetadataPath)
      throws IPException, InterruptedException {
    return addMetadataToMETS(
        metsWrapper, otherMetadata, otherMetadataPath, "OTHER", null, null, false);
  }

  private static MdRef addMetadataToMETS(
      MetsWrapper metsWrapper,
      IPMetadata metadata,
      String metadataPath,
      String mdType,
      String mdOtherType,
      String mdTypeVersion,
      boolean isDescriptive)
      throws IPException, InterruptedException {
    MdSecType dmdSec = new MdSecType();
    dmdSec.setSTATUS(metadata.getMetadataStatus().toString());
    dmdSec.setID(Utils.generateRandomAndPrefixedUUID());

    MdRef mdRef = createMdRef(metadata.getId(), metadataPath);
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

  public static MdRef addPreservationMetadataToMETS(
      MetsWrapper metsWrapper, IPMetadata preservationMetadata, String preservationMetadataPath)
      throws IPException, InterruptedException {
    MdSecType digiprovMD = new MdSecType();
    digiprovMD.setSTATUS(preservationMetadata.getMetadataStatus().toString());
    digiprovMD.setID(Utils.generateRandomAndPrefixedUUID());
    MdRef mdRef = createMdRef(preservationMetadata.getId(), preservationMetadataPath);
    mdRef.setMDTYPE(preservationMetadata.getMetadataType().asString());

    // set mimetype, date creation, etc.
    METSUtils.setFileBasicInformation(preservationMetadata.getMetadata().getPath(), mdRef);

    // structural map info.
    metsWrapper.getMetadataDiv().getADMID().add(digiprovMD);

    digiprovMD.setMdRef(mdRef);
    metsWrapper.getMets().getAmdSec().get(0).getDigiprovMD().add(digiprovMD);
    return mdRef;
  }

  private static String escapeNCName(String id) {
    return id.replaceAll("[:@$%&/+,;\\s]", "_");
  }

  private static MdRef createMdRef(String id, String metadataPath) {
    MdRef mdRef = new MdRef();
    mdRef.setID(escapeNCName(id));
    mdRef.setType(IPConstants.METS_TYPE_SIMPLE);
    mdRef.setLOCTYPE(LocType.URL.toString());
    mdRef.setHref(METSUtils.encodeHref(metadataPath));
    return mdRef;
  }

  public static void addDataFileToMETS(MetsWrapper representationMETS, IPFileShallow shallow) {
    FileType file = shallow.getFileType();
    file.setID(Utils.generateRandomAndPrefixedFileID());

    // add to file section
    FLocat fileLocation = METSUtils.createShallowFileLocation(shallow.getFileLocation().toString());
    file.getFLocat().add(fileLocation);
    representationMETS.getDataFileGroup().getFile().add(file);

    // add to struct map
    if (representationMETS.getDataDiv().getFptr().isEmpty()) {
      Fptr fptr = new Fptr();
      fptr.setFILEID(representationMETS.getDataFileGroup());
      representationMETS.getDataDiv().getFptr().add(fptr);
    }
  }

  public static FileType addDataFileToMETS(
      MetsWrapper representationMETS, String dataFilePath, Path dataFile)
      throws IPException, InterruptedException {
    FileType file = new FileType();
    file.setID(Utils.generateRandomAndPrefixedFileID());

    // set mimetype, date creation, etc.
    METSUtils.setFileBasicInformation(LOGGER, dataFile, file);

    // add to file section
    FLocat fileLocation = METSUtils.createFileLocation(dataFilePath);
    file.getFLocat().add(fileLocation);
    representationMETS.getDataFileGroup().getFile().add(file);

    // add to struct map
    if (representationMETS.getDataDiv().getFptr().isEmpty()) {
      Fptr fptr = new Fptr();
      fptr.setFILEID(representationMETS.getDataFileGroup());
      representationMETS.getDataDiv().getFptr().add(fptr);
    }
    return file;
  }

  public static FileType addSchemaFileToMETS(
      MetsWrapper metsWrapper, String schemaFilePath, Path schemaFile)
      throws IPException, InterruptedException {
    FileType file = new FileType();
    file.setID(Utils.generateRandomAndPrefixedFileID());

    // set mimetype, date creation, etc.
    METSUtils.setFileBasicInformation(LOGGER, schemaFile, file);

    // add to file section
    FLocat fileLocation = METSUtils.createFileLocation(schemaFilePath);
    file.getFLocat().add(fileLocation);
    if (metsWrapper.getSchemasFileGroup() != null) {
      metsWrapper.getSchemasFileGroup().getFile().add(file);
    }

    // add to struct map
    if (metsWrapper.getSchemasDiv() != null && metsWrapper.getSchemasDiv().getFptr().isEmpty()) {
      Fptr fptr = new Fptr();
      fptr.setFILEID(metsWrapper.getSchemasFileGroup());
      metsWrapper.getSchemasDiv().getFptr().add(fptr);
    }
    return file;
  }

  public static FileType addSubmissionFileToMETS(
      MetsWrapper metsWrapper, String submissionFilePath, Path submissionFile)
      throws IPException, InterruptedException {
    FileType file = new FileType();
    file.setID(Utils.generateRandomAndPrefixedFileID());

    // set mimetype, date creation, etc.
    METSUtils.setFileBasicInformation(LOGGER, submissionFile, file);

    // add to file section
    FLocat fileLocation = METSUtils.createFileLocation(submissionFilePath);
    file.getFLocat().add(fileLocation);
    metsWrapper.getSubmissionFileGroup().getFile().add(file);

    // add to struct map
    Fptr fptr = new Fptr();
    fptr.setFILEID(file);
    metsWrapper.getSubmissionsDiv().getFptr().add(fptr);
    return file;
  }

  public static FileType addDocumentationFileToMETS(
      MetsWrapper metsWrapper, String documentationFilePath, Path documentationFile)
      throws IPException, InterruptedException {
    FileType file = new FileType();
    file.setID(Utils.generateRandomAndPrefixedFileID());

    // set mimetype, date creation, etc.
    METSUtils.setFileBasicInformation(LOGGER, documentationFile, file);

    // add to file section
    FLocat fileLocation = METSUtils.createFileLocation(documentationFilePath);
    file.getFLocat().add(fileLocation);
    metsWrapper.getDocumentationFileGroup().getFile().add(file);

    // add to struct map
    if (metsWrapper.getDocumentationDiv().getFptr().isEmpty()) {
      Fptr fptr = new Fptr();
      fptr.setFILEID(metsWrapper.getDocumentationFileGroup());
      metsWrapper.getDocumentationDiv().getFptr().add(fptr);
    }

    return file;
  }

  private static StructMapType generateAncestorStructMap(List<String> ancestors) {
    StructMapType structMap = new StructMapType();
    structMap.setID(Utils.generateRandomAndPrefixedUUID());
    structMap.setLABEL(IPConstants.RODA_STRUCTURAL_MAP);

    DivType mainDiv = createDivForStructMap(IPConstants.RODA_DIV_LABEL);
    DivType ancestorsDiv = createDivForStructMap(IPConstants.RODA_ANCESTORS_DIV_LABEL);

    for (String anc : ancestors) {
      Mptr mptr = new Mptr();
      mptr.setType(IPConstants.METS_TYPE_SIMPLE);
      mptr.setHref(METSUtils.encodeHref(anc));
      mptr.setLOCTYPE(LocType.HANDLE.toString());
      ancestorsDiv.getMptr().add(mptr);
    }
    mainDiv.getDiv().add(ancestorsDiv);
    structMap.setDiv(mainDiv);
    return structMap;
  }

  public static List<String> extractAncestorsFromStructMap(Mets mets) {
    List<String> ancestors = new ArrayList<>();

    for (StructMapType structMap : mets.getStructMap()) {
      if (structMap.getLABEL() != null
          && IPConstants.RODA_STRUCTURAL_MAP.equalsIgnoreCase(structMap.getLABEL())
          && structMap.getDiv() != null) {
        DivType mainDiv = structMap.getDiv();

        if (IPConstants.RODA_DIV_LABEL.equalsIgnoreCase(mainDiv.getLABEL())
            && mainDiv.getDiv() != null) {
          for (DivType div : mainDiv.getDiv()) {
            if (IPConstants.RODA_ANCESTORS_DIV_LABEL.equalsIgnoreCase(div.getLABEL())
                && div.getMptr() != null) {
              for (Mptr m : div.getMptr()) {
                String href = METSUtils.decodeHref(m.getHref());
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
}
