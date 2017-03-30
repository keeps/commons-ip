/**
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE file at the root of the source
 * tree and available online at
 *
 * https://github.com/keeps/commons-ip
 */
package org.roda_project.commons_ip.model.impl.eark;

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
import org.roda_project.commons_ip.mets_v1_11.beans.AmdSecType;
import org.roda_project.commons_ip.mets_v1_11.beans.DivType;
import org.roda_project.commons_ip.mets_v1_11.beans.DivType.Fptr;
import org.roda_project.commons_ip.mets_v1_11.beans.DivType.Mptr;
import org.roda_project.commons_ip.mets_v1_11.beans.FileGrpType;
import org.roda_project.commons_ip.mets_v1_11.beans.FileType;
import org.roda_project.commons_ip.mets_v1_11.beans.FileType.FLocat;
import org.roda_project.commons_ip.mets_v1_11.beans.MdSecType;
import org.roda_project.commons_ip.mets_v1_11.beans.MdSecType.MdRef;
import org.roda_project.commons_ip.mets_v1_11.beans.Mets;
import org.roda_project.commons_ip.mets_v1_11.beans.MetsType.FileSec;
import org.roda_project.commons_ip.mets_v1_11.beans.MetsType.FileSec.FileGrp;
import org.roda_project.commons_ip.mets_v1_11.beans.MetsType.MetsHdr;
import org.roda_project.commons_ip.mets_v1_11.beans.MetsType.MetsHdr.Agent;
import org.roda_project.commons_ip.mets_v1_11.beans.StructMapType;
import org.roda_project.commons_ip.model.IPAgent;
import org.roda_project.commons_ip.model.IPConstants;
import org.roda_project.commons_ip.model.IPDescriptiveMetadata;
import org.roda_project.commons_ip.model.IPMetadata;
import org.roda_project.commons_ip.model.MetsWrapper;
import org.roda_project.commons_ip.utils.IPEnums;
import org.roda_project.commons_ip.utils.IPException;
import org.roda_project.commons_ip.utils.METSEnums.CreatorType;
import org.roda_project.commons_ip.utils.METSEnums.LocType;
import org.roda_project.commons_ip.utils.METSUtils;
import org.roda_project.commons_ip.utils.Utils;
import org.roda_project.commons_ip.utils.ZIPUtils;
import org.roda_project.commons_ip.utils.ZipEntryInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class EARKMETSUtils {
  private static final Logger LOGGER = LoggerFactory.getLogger(EARKMETSUtils.class);

  private EARKMETSUtils() {
    // do nothing
  }

  public static MetsWrapper generateMETS(String id, String label, String type, String profile, List<IPAgent> ipAgents,
    boolean mainMets, Optional<List<String>> ancestors, Path metsPath, IPEnums.IPStatus status) throws IPException {
    Mets mets = new Mets();
    MetsWrapper metsWrapper = new MetsWrapper(mets, metsPath);

    // basic attributes
    mets.setOBJID(id);
    mets.setPROFILE(profile);
    mets.setTYPE(type);
    mets.setLABEL(label);

    // header
    MetsHdr header = new MetsHdr();
    try {
      XMLGregorianCalendar currentDate = Utils.getCurrentCalendar();
      header.setCREATEDATE(currentDate);
      header.setLASTMODDATE(currentDate);
      header.setRECORDSTATUS(status.toString());
    } catch (DatatypeConfigurationException e) {
      throw new IPException("Error getting current calendar", e);
    }

    // header/agent
    for (IPAgent sipAgent : ipAgents) {
      header.getAgent().add(createMETSAgent(sipAgent));
    }

    mets.setMetsHdr(header);

    // administrative section
    AmdSecType amdSec = new AmdSecType();
    amdSec.setID(Utils.generateRandomAndPrefixedUUID());
    mets.getAmdSec().add(amdSec);

    // file section
    FileSec fileSec = new FileSec();
    fileSec.setID(Utils.generateRandomAndPrefixedUUID());
    FileGrp mainFileGroup = mainMets ? createFileGroup(IPConstants.E_ARK_FILES_ROOT)
      : createFileGroup(IPConstants.E_ARK_FILES_REPRESENTATION + id);

    FileGrpType representationsFileGroup = createFileGroupType(IPConstants.REPRESENTATIONS);
    mainFileGroup.getFileGrp().add(representationsFileGroup);
    metsWrapper.setRepresentationsFileGroup(representationsFileGroup);
    FileGrpType dataFileGroup = createFileGroupType(IPConstants.DATA);
    mainFileGroup.getFileGrp().add(dataFileGroup);
    metsWrapper.setDataFileGroup(dataFileGroup);
    FileGrpType schemasFileGroup = createFileGroupType(IPConstants.SCHEMAS);
    mainFileGroup.getFileGrp().add(schemasFileGroup);
    metsWrapper.setSchemasFileGroup(schemasFileGroup);
    FileGrpType submissionFileGroup = createFileGroupType(IPConstants.SUBMISSION);
    mainFileGroup.getFileGrp().add(submissionFileGroup);
    metsWrapper.setSubmissionFileGroup(submissionFileGroup);
    FileGrpType documentationFileGroup = createFileGroupType(IPConstants.DOCUMENTATION);
    mainFileGroup.getFileGrp().add(documentationFileGroup);
    metsWrapper.setDocumentationFileGroup(documentationFileGroup);

    fileSec.getFileGrp().add(mainFileGroup);
    mets.setFileSec(fileSec);

    // E-ARK struct map
    StructMapType structMap = new StructMapType();
    structMap.setID(Utils.generateRandomAndPrefixedUUID());
    structMap.setTYPE(IPConstants.METS_TYPE_PHYSICAL);
    structMap.setLABEL(IPConstants.E_ARK_STRUCTURAL_MAP);

    DivType mainDiv = createDivForStructMap(id);
    metsWrapper.setMainDiv(mainDiv);
    // metadata
    DivType metadataDiv = createDivForStructMap(IPConstants.METADATA);
    // metadata/descriptive
    DivType descriptiveMetadataDiv = createDivForStructMap(IPConstants.DESCRIPTIVE);
    metadataDiv.getDiv().add(descriptiveMetadataDiv);
    metsWrapper.setDescriptiveMetadataDiv(descriptiveMetadataDiv);
    // metadata/preservation
    DivType preservationMetadataDiv = createDivForStructMap(IPConstants.PRESERVATION);
    metadataDiv.getDiv().add(preservationMetadataDiv);
    metsWrapper.setPreservationMetadataDiv(preservationMetadataDiv);
    // metadata/other
    DivType otherMetadataDiv = createDivForStructMap(IPConstants.OTHER);
    metadataDiv.getDiv().add(otherMetadataDiv);
    metsWrapper.setOtherMetadataDiv(otherMetadataDiv);
    mainDiv.getDiv().add(metadataDiv);

    // representations
    DivType representationsDiv = createDivForStructMap(IPConstants.REPRESENTATIONS);
    mainDiv.getDiv().add(representationsDiv);
    metsWrapper.setRepresentationsDiv(representationsDiv);
    // data
    DivType dataDiv = createDivForStructMap(IPConstants.DATA);
    metsWrapper.setDataDiv(dataDiv);
    mainDiv.getDiv().add(dataDiv);
    // schemas
    DivType schemasDiv = createDivForStructMap(IPConstants.SCHEMAS);
    metsWrapper.setSchemasDiv(schemasDiv);
    mainDiv.getDiv().add(schemasDiv);
    // documentation
    DivType documentationDiv = createDivForStructMap(IPConstants.DOCUMENTATION);
    metsWrapper.setDocumentationDiv(documentationDiv);
    mainDiv.getDiv().add(documentationDiv);
    // submission
    DivType submissionDiv = createDivForStructMap(IPConstants.SUBMISSION);
    metsWrapper.setSubmissionsDiv(submissionDiv);
    mainDiv.getDiv().add(submissionDiv);

    structMap.setDiv(mainDiv);
    mets.getStructMap().add(structMap);

    // RODA struct map
    if (ancestors.isPresent() && !ancestors.get().isEmpty()) {
      StructMapType structMapParent = generateAncestorStructMap(ancestors.get());
      mets.getStructMap().add(structMapParent);
    }

    return metsWrapper;
  }

  private static FileGrpType createFileGroupType(String use) {
    FileGrpType fileGroup = new FileGrpType();
    fileGroup.setID(Utils.generateRandomAndPrefixedUUID());
    fileGroup.setUSE(use);
    return fileGroup;
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

  public static void addRepresentationMETSToZipAndToMainMETS(Map<String, ZipEntryInfo> zipEntries,
    MetsWrapper mainMETSWrapper, String representationId, MetsWrapper representationMETSWrapper,
    String representationMetsPath, Path buildDir) throws IPException, InterruptedException {
    try {
      if (Thread.interrupted()) {
        throw new InterruptedException();
      }
      addMETSToZip(zipEntries, representationMETSWrapper, representationMetsPath, buildDir, false);
      Mptr mptr = new Mptr();
      mptr.setLOCTYPE(LocType.URL.toString());
      mptr.setType(IPConstants.METS_TYPE_SIMPLE);
      mptr.setHref(Utils.encode(representationMetsPath));
      DivType representationDiv = createDivForStructMap(representationId);
      representationDiv.getMptr().add(mptr);
      mainMETSWrapper.getRepresentationsDiv().getDiv().add(representationDiv);
    } catch (JAXBException | IOException e) {
      throw new IPException("Error saving representation METS", e);
    }
  }

  private static void addMETSToZip(Map<String, ZipEntryInfo> zipEntries, MetsWrapper metsWrapper, String metsPath,
    Path buildDir, boolean mainMets) throws JAXBException, IOException, IPException {
    Path temp = Files.createTempFile(buildDir, IPConstants.METS_FILE_NAME, IPConstants.METS_FILE_EXTENSION);
    ZIPUtils.addMETSFileToZip(zipEntries, temp, metsPath, metsWrapper.getMets(), mainMets);
  }

  private static Agent createMETSAgent(IPAgent ipAgent) {
    Agent agent = new Agent();
    agent.setName(ipAgent.getName());
    agent.setROLE(ipAgent.getRole());
    agent.setOTHERROLE(ipAgent.getOtherRole());
    agent.setTYPE(ipAgent.getType().toString());
    agent.setOTHERTYPE(ipAgent.getOtherType());

    return agent;
  }

  public static IPAgent createIPAgent(Agent agent) {
    IPAgent ipAgent = new IPAgent();
    CreatorType agentType;
    try {
      agentType = CreatorType.valueOf(agent.getTYPE());
    } catch (IllegalArgumentException e) {
      agentType = CreatorType.OTHER;
      LOGGER.debug("Setting agent type to {}", agentType);
    }
    ipAgent.setName(agent.getName()).setRole(agent.getROLE()).setOtherRole(agent.getOTHERROLE()).setType(agentType)
      .setOtherType(agent.getOTHERTYPE());

    return ipAgent;
  }

  public static MdRef addDescriptiveMetadataToMETS(MetsWrapper metsWrapper, IPDescriptiveMetadata descriptiveMetadata,
    String descriptiveMetadataPath) throws IPException, InterruptedException {
    return addMetadataToMETS(metsWrapper, descriptiveMetadata, descriptiveMetadataPath,
      descriptiveMetadata.getMetadataType().getType().getType(), descriptiveMetadata.getMetadataType().getOtherType(),
      descriptiveMetadata.getMetadataVersion(), true);
  }

  public static MdRef addOtherMetadataToMETS(MetsWrapper metsWrapper, IPMetadata otherMetadata,
    String otherMetadataPath) throws IPException, InterruptedException {
    return addMetadataToMETS(metsWrapper, otherMetadata, otherMetadataPath, "OTHER", null, null, false);
  }

  private static MdRef addMetadataToMETS(MetsWrapper metsWrapper, IPMetadata metadata, String metadataPath,
    String mdType, String mdOtherType, String mdTypeVersion, boolean isDescriptive)
    throws IPException, InterruptedException {
    MdSecType dmdSec = new MdSecType();
    dmdSec.setID(Utils.generateRandomAndPrefixedUUID());

    MdRef mdRef = createMdRef(metadata.getId(), metadataPath);
    mdRef.setMDTYPE(mdType);
    if (StringUtils.isNotBlank(mdOtherType)) {
      mdRef.setOTHERMDTYPE(mdOtherType);
    }
    mdRef.setMDTYPEVERSION(mdTypeVersion);

    // set mimetype, date creation, etc.
    METSUtils.setFileBasicInformation(metadata.getMetadata().getPath(), mdRef);

    // structural map info.
    Fptr fptr = new Fptr();
    fptr.setFILEID(mdRef);
    if (isDescriptive) {
      metsWrapper.getDescriptiveMetadataDiv().getFptr().add(fptr);
    } else {
      metsWrapper.getOtherMetadataDiv().getFptr().add(fptr);
    }

    dmdSec.setMdRef(mdRef);
    metsWrapper.getMets().getDmdSec().add(dmdSec);
    return mdRef;
  }

  public static MdRef addPreservationMetadataToMETS(MetsWrapper metsWrapper, IPMetadata preservationMetadata,
    String preservationMetadataPath) throws IPException, InterruptedException {
    MdSecType digiprovMD = new MdSecType();
    digiprovMD.setID(Utils.generateRandomAndPrefixedUUID());

    MdRef mdRef = createMdRef(preservationMetadata.getId(), preservationMetadataPath);
    mdRef.setMDTYPE("OTHER");

    // set mimetype, date creation, etc.
    METSUtils.setFileBasicInformation(preservationMetadata.getMetadata().getPath(), mdRef);

    // structural map info.
    Fptr fptr = new Fptr();
    fptr.setFILEID(mdRef);
    metsWrapper.getPreservationMetadataDiv().getFptr().add(fptr);

    digiprovMD.setMdRef(mdRef);
    metsWrapper.getMets().getAmdSec().get(0).getDigiprovMD().add(digiprovMD);
    return mdRef;
  }

  private static MdRef createMdRef(String id, String metadataPath) {
    MdRef mdRef = new MdRef();
    mdRef.setID(id);
    mdRef.setType(IPConstants.METS_TYPE_SIMPLE);
    mdRef.setLOCTYPE(LocType.URL.toString());
    mdRef.setHref(Utils.encode(metadataPath));
    return mdRef;
  }

  public static FileType addDataFileToMETS(MetsWrapper representationMETS, String dataFilePath, Path dataFile)
    throws IPException, InterruptedException {
    FileType file = new FileType();
    file.setID(Utils.generateRandomAndPrefixedUUID());

    // set mimetype, date creation, etc.
    METSUtils.setFileBasicInformation(LOGGER, dataFile, file);

    // add to file section
    FLocat fileLocation = METSUtils.createFileLocation(dataFilePath);
    file.getFLocat().add(fileLocation);
    representationMETS.getDataFileGroup().getFile().add(file);

    // add to struct map
    Fptr fptr = new Fptr();
    fptr.setFILEID(file);
    representationMETS.getDataDiv().getFptr().add(fptr);
    return file;
  }

  public static FileType addSchemaFileToMETS(MetsWrapper metsWrapper, String schemaFilePath, Path schemaFile)
    throws IPException, InterruptedException {
    FileType file = new FileType();
    file.setID(Utils.generateRandomAndPrefixedUUID());

    // set mimetype, date creation, etc.
    METSUtils.setFileBasicInformation(LOGGER, schemaFile, file);

    // add to file section
    FLocat fileLocation = METSUtils.createFileLocation(schemaFilePath);
    file.getFLocat().add(fileLocation);
    metsWrapper.getSchemasFileGroup().getFile().add(file);

    // add to struct map
    Fptr fptr = new Fptr();
    fptr.setFILEID(file);
    metsWrapper.getSchemasDiv().getFptr().add(fptr);
    return file;
  }

  public static FileType addSubmissionFileToMETS(MetsWrapper metsWrapper, String submissionFilePath,
    Path submissionFile) throws IPException, InterruptedException {
    FileType file = new FileType();
    file.setID(Utils.generateRandomAndPrefixedUUID());

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

  public static FileType addDocumentationFileToMETS(MetsWrapper metsWrapper, String documentationFilePath,
    Path documentationFile) throws IPException, InterruptedException {
    FileType file = new FileType();
    file.setID(Utils.generateRandomAndPrefixedUUID());

    // set mimetype, date creation, etc.
    METSUtils.setFileBasicInformation(LOGGER, documentationFile, file);

    // add to file section
    FLocat fileLocation = METSUtils.createFileLocation(documentationFilePath);
    file.getFLocat().add(fileLocation);
    metsWrapper.getDocumentationFileGroup().getFile().add(file);

    // add to struct map
    Fptr fptr = new Fptr();
    fptr.setFILEID(file);
    metsWrapper.getDocumentationDiv().getFptr().add(fptr);

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
      mptr.setHref(Utils.encode(anc));
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
      if (structMap.getLABEL() != null && IPConstants.RODA_STRUCTURAL_MAP.equalsIgnoreCase(structMap.getLABEL())
        && structMap.getDiv() != null) {
        DivType mainDiv = structMap.getDiv();

        if (IPConstants.RODA_DIV_LABEL.equalsIgnoreCase(mainDiv.getLABEL()) && mainDiv.getDiv() != null) {
          for (DivType div : mainDiv.getDiv()) {
            if (IPConstants.RODA_ANCESTORS_DIV_LABEL.equalsIgnoreCase(div.getLABEL()) && div.getMptr() != null) {
              for (Mptr m : div.getMptr()) {
                String href = Utils.decode(m.getHref());
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
