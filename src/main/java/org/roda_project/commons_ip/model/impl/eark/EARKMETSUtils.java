/**
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE file at the root of the source
 * tree and available online at
 *
 * https://github.com/keeps/commons-ip
 */
package org.roda_project.commons_ip.model.impl.eark;

import java.io.IOException;
import java.nio.channels.ClosedByInterruptException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.PropertyException;
import javax.xml.bind.Unmarshaller;
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
import org.roda_project.commons_ip.utils.METSEnums.CreatorType;
import org.roda_project.commons_ip.utils.METSEnums.LocType;
import org.roda_project.commons_ip.utils.SIPException;
import org.roda_project.commons_ip.utils.Utils;
import org.roda_project.commons_ip.utils.ZIPUtils;
import org.roda_project.commons_ip.utils.ZipEntryInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class EARKMETSUtils {
  private static final Logger LOGGER = LoggerFactory.getLogger(EARKMETSUtils.class);

  private EARKMETSUtils() {

  }

  /**
   * @param mainMets
   *          boolean stating if the generated mets belongs to root/sip or
   *          representation
   */
  public static MetsWrapper generateMETS(String objectId, String label, String type, String profile,
    List<IPAgent> ipAgents, boolean mainMets, String parentId) throws SIPException {
    Mets mets = new Mets();
    MetsWrapper metsWrapper = new MetsWrapper(mets);

    // basic attributes
    mets.setOBJID(objectId);
    mets.setPROFILE(profile);
    mets.setTYPE(type);
    mets.setLABEL(label);

    // header
    MetsHdr header = new MetsHdr();
    try {
      XMLGregorianCalendar currentDate = Utils.getCurrentCalendar();
      header.setCREATEDATE(currentDate);
      header.setLASTMODDATE(currentDate);
    } catch (DatatypeConfigurationException e) {
      throw new SIPException("Error getting current calendar", e);
    }
    // header/agent
    for (IPAgent sipAgent : ipAgents) {
      header.getAgent().add(createMETSAgent(sipAgent));
    }
    mets.setMetsHdr(header);

    // administrative section
    AmdSecType amdSec = new AmdSecType();
    amdSec.setID(Utils.generateRandomId());
    mets.getAmdSec().add(amdSec);

    // file section
    FileSec fileSec = new FileSec();
    fileSec.setID(Utils.generateRandomId());
    FileGrp mainFileGroup = mainMets ? createFileGroup(IPConstants.E_ARK_FILES_ROOT)
      : createFileGroup(IPConstants.E_ARK_FILES_REPRESENTATION + objectId);

    FileGrpType representationsFileGroup = createFileGroupType(IPConstants.REPRESENTATIONS);
    mainFileGroup.getFileGrp().add(representationsFileGroup);
    metsWrapper.setRepresentationsFileGroup(representationsFileGroup);
    FileGrpType dataFileGroup = createFileGroupType(IPConstants.DATA);
    mainFileGroup.getFileGrp().add(dataFileGroup);
    metsWrapper.setDataFileGroup(dataFileGroup);
    FileGrpType schemasFileGroup = createFileGroupType(IPConstants.SCHEMAS);
    mainFileGroup.getFileGrp().add(schemasFileGroup);
    metsWrapper.setSchemasFileGroup(schemasFileGroup);
    FileGrpType documentationFileGroup = createFileGroupType(IPConstants.DOCUMENTATION);
    mainFileGroup.getFileGrp().add(documentationFileGroup);
    metsWrapper.setDocumentationFileGroup(documentationFileGroup);

    fileSec.getFileGrp().add(mainFileGroup);
    mets.setFileSec(fileSec);

    // E-ARK struct map
    StructMapType structMap = new StructMapType();
    structMap.setID(Utils.generateRandomId());
    structMap.setTYPE(IPConstants.METS_TYPE_PHYSICAL);
    structMap.setLABEL(IPConstants.E_ARK_STRUCTURAL_MAP);

    DivType mainDiv = createDivForStructMap(objectId);
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

    structMap.setDiv(mainDiv);
    mets.getStructMap().add(structMap);

    // RODA struct map
    if (parentId != null) {
      StructMapType structMapParent = generateParentIDStructMap(parentId);
      mets.getStructMap().add(structMapParent);
    }
    return metsWrapper;
  }

  private static FileGrpType createFileGroupType(String use) {
    FileGrpType fileGroup = new FileGrpType();
    fileGroup.setID(Utils.generateRandomId());
    fileGroup.setUSE(use);
    return fileGroup;
  }

  private static FileGrp createFileGroup(String use) {
    FileGrp fileGroup = new FileGrp();
    fileGroup.setID(Utils.generateRandomId());
    fileGroup.setUSE(use);
    return fileGroup;
  }

  private static DivType createDivForStructMap(String label) {
    DivType div = new DivType();
    div.setID(Utils.generateRandomId());
    div.setLABEL(label);
    return div;
  }

  public static void addMainMETSToZip(List<ZipEntryInfo> zipEntries, MetsWrapper metsWrapper, String metsPath,
    Path buildDir) throws SIPException {
    try {
      addMETSToZip(zipEntries, metsWrapper, metsPath, buildDir, true);
    } catch (JAXBException | IOException e) {
      throw new SIPException(e.getMessage(), e);
    }
  }

  public static void addRepresentationMETSToZipAndToMainMETS(List<ZipEntryInfo> zipEntries, MetsWrapper mainMETSWrapper,
    String representationId, MetsWrapper representationMETSWrapper, String representationMetsPath, Path buildDir)
      throws SIPException, InterruptedException {
    try {
      if (Thread.interrupted()) {
        throw new InterruptedException();
      }
      addMETSToZip(zipEntries, representationMETSWrapper, representationMetsPath, buildDir, false);
      Mptr mptr = new Mptr();
      mptr.setLOCTYPE(LocType.URL.toString());
      mptr.setType(IPConstants.METS_TYPE_SIMPLE);
      mptr.setHref(IPConstants.METS_FILE_URI_PREFIX + representationMetsPath);
      DivType representationDiv = createDivForStructMap(representationId);
      representationDiv.getMptr().add(mptr);
      mainMETSWrapper.getRepresentationsDiv().getDiv().add(representationDiv);
    } catch (JAXBException | IOException e) {
      throw new SIPException("Error saving representation METS", e);
    }
  }

  private static void addMETSToZip(List<ZipEntryInfo> zipEntries, MetsWrapper metsWrapper, String metsPath,
    Path buildDir, boolean mainMets) throws JAXBException, IOException, PropertyException, SIPException {
    Path temp = Files.createTempFile(buildDir, IPConstants.METS_FILE_NAME, IPConstants.METS_FILE_EXTENSION);
    ZIPUtils.addMETSFileToZip(zipEntries, temp, metsPath, metsWrapper.getMets(), mainMets);
  }

  public static Mets instantiateMETSFromFile(Path metsFile) throws JAXBException {
    JAXBContext jaxbContext = JAXBContext.newInstance(Mets.class);
    Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
    return (Mets) jaxbUnmarshaller.unmarshal(metsFile.toFile());
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
    String descriptiveMetadataPath, boolean calculateChecksum) throws SIPException, InterruptedException {
    return addMetadataToMETS(metsWrapper, descriptiveMetadata, descriptiveMetadataPath,
      descriptiveMetadata.getMetadataType().getType(), descriptiveMetadata.getMetadataType().getOtherType(),
      descriptiveMetadata.getMetadataVersion(), true, calculateChecksum);
  }

  public static MdRef addOtherMetadataToMETS(MetsWrapper metsWrapper, IPMetadata otherMetadata,
    String otherMetadataPath, boolean calculateChecksum) throws SIPException, InterruptedException {
    return addMetadataToMETS(metsWrapper, otherMetadata, otherMetadataPath, null, null, null, false, calculateChecksum);
  }

  private static MdRef addMetadataToMETS(MetsWrapper metsWrapper, IPMetadata metadata, String metadataPath,
    String mdType, String mdOtherType, String mdTypeVersion, boolean isDescriptive, boolean calculateChecksum)
      throws SIPException, InterruptedException {
    MdSecType dmdSec = new MdSecType();
    dmdSec.setID(Utils.generateRandomId());

    MdRef mdRef = createMdRef(metadataPath);
    mdRef.setMDTYPE(mdType);
    if (StringUtils.isNotBlank(mdOtherType)) {
      mdRef.setOTHERMDTYPE(mdOtherType);
    }
    mdRef.setMDTYPEVERSION(mdTypeVersion);

    // set checksum, mimetype, etc.
    setFileBasicInformation(metadata.getMetadata().getPath(), mdRef, calculateChecksum);

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
    String preservationMetadataPath, boolean calculateChecksum) throws SIPException, InterruptedException {
    MdSecType digiprovMD = new MdSecType();
    digiprovMD.setID(Utils.generateRandomId());

    MdRef mdRef = createMdRef(preservationMetadataPath);

    // set checksum, mimetype, etc.
    setFileBasicInformation(preservationMetadata.getMetadata().getPath(), mdRef, calculateChecksum);

    // structural map info.
    Fptr fptr = new Fptr();
    fptr.setFILEID(mdRef);
    metsWrapper.getPreservationMetadataDiv().getFptr().add(fptr);

    digiprovMD.setMdRef(mdRef);
    metsWrapper.getMets().getAmdSec().get(0).getDigiprovMD().add(digiprovMD);
    return mdRef;
  }

  private static MdRef createMdRef(String metadataPath) {
    MdRef mdRef = new MdRef();
    mdRef.setID(Utils.generateRandomId());
    mdRef.setType(IPConstants.METS_TYPE_SIMPLE);
    mdRef.setLOCTYPE(LocType.URL.toString());
    mdRef.setHref(IPConstants.METS_FILE_URI_PREFIX + metadataPath);
    return mdRef;
  }

  public static FileType addDataFileToMETS(MetsWrapper representationMETS, String dataFilePath, Path dataFile,
    boolean calculateChecksum) throws SIPException, InterruptedException {
    FileType file = new FileType();
    file.setID(Utils.generateRandomId());

    // set checksum, mimetype, etc.
    setFileBasicInformation(dataFile, file, calculateChecksum);

    // add to file section
    FLocat fileLocation = createFileLocation(dataFilePath);
    file.getFLocat().add(fileLocation);
    representationMETS.getDataFileGroup().getFile().add(file);

    // add to struct map
    Fptr fptr = new Fptr();
    fptr.setFILEID(file);
    representationMETS.getDataDiv().getFptr().add(fptr);
    return file;
  }

  private static MdRef setFileBasicInformation(Path file, MdRef mdRef, boolean calculateChecksum)
    throws SIPException, InterruptedException {
    if (calculateChecksum) {
      // checksum info.
      try {
        LOGGER.debug("Calculating checksum {}", file);
        mdRef.setCHECKSUM(Utils.calculateChecksum(Files.newInputStream(file), IPConstants.CHECKSUM_ALGORITHM));
        LOGGER.debug("Done calculating checksum");
      } catch (ClosedByInterruptException e) {
        throw new InterruptedException();
      } catch (IOException e) {
        throw new SIPException("Error calculating checksum for file " + file, e);
      } catch (NoSuchAlgorithmException e) {
        throw new SIPException("Error calculating checksum for file " + file + " (no such algorithm)", e);
      }
      mdRef.setCHECKSUMTYPE(IPConstants.CHECKSUM_ALGORITHM);
    }

    // mimetype info.
    try {
      mdRef.setMIMETYPE(Files.probeContentType(file));
    } catch (IOException e) {
      throw new SIPException("Error probing file content (" + file + ")", e);
    }

    // date creation info.
    try {
      mdRef.setCREATED(Utils.getCurrentCalendar());
    } catch (DatatypeConfigurationException e) {
      throw new SIPException("Error getting current calendar", e);
    }

    // size info.
    try {
      mdRef.setSIZE(Files.size(file));
    } catch (IOException e) {
      throw new SIPException("Error getting file size (" + file + ")", e);
    }

    return mdRef;
  }

  private static void setFileBasicInformation(Path file, FileType fileType, boolean calculateChecksum)
    throws SIPException, InterruptedException {
    // checksum info.
    if (calculateChecksum) {
      try {
        LOGGER.debug("Calculating checksum {}", file);
        fileType.setCHECKSUM(Utils.calculateChecksum(Files.newInputStream(file), IPConstants.CHECKSUM_ALGORITHM));
        LOGGER.debug("Done calculating checksum");
      } catch (ClosedByInterruptException e) {
        throw new InterruptedException();
      } catch (IOException e) {
        throw new SIPException("Error calculating checksum for file " + file, e);
      } catch (NoSuchAlgorithmException e) {
        throw new SIPException("Error calculating checksum for file " + file + " (no such algorithm)", e);
      }
      fileType.setCHECKSUMTYPE(IPConstants.CHECKSUM_ALGORITHM);
    }

    // mimetype info.
    try {
      LOGGER.debug("Setting mimetype {}", file);
      fileType.setMIMETYPE(Files.probeContentType(file));
      LOGGER.debug("Done setting mimetype");
    } catch (IOException e) {
      throw new SIPException("Error probing content-type (" + file.toString() + ")", e);
    }

    // date creation info.
    try {
      fileType.setCREATED(Utils.getCurrentCalendar());
    } catch (DatatypeConfigurationException e) {
      throw new SIPException("Error getting curent calendar (" + file.toString() + ")", e);
    }

    // size info.
    try {
      LOGGER.debug("Setting file size {}", file);
      fileType.setSIZE(Files.size(file));
      LOGGER.debug("Done setting file size");
    } catch (IOException e) {
      throw new SIPException("Error getting file size (" + file.toString() + ")", e);
    }
  }

  public static FileType addSchemaFileToMETS(MetsWrapper metsWrapper, String schemaFilePath, Path schemaFile,
    boolean calculateChecksum) throws SIPException, InterruptedException {
    FileType file = new FileType();
    file.setID(Utils.generateRandomId());

    // set checksum, mimetype, etc.
    setFileBasicInformation(schemaFile, file, calculateChecksum);

    // add to file section
    FLocat fileLocation = createFileLocation(schemaFilePath);
    file.getFLocat().add(fileLocation);
    metsWrapper.getSchemasFileGroup().getFile().add(file);

    // add to struct map
    Fptr fptr = new Fptr();
    fptr.setFILEID(file);
    metsWrapper.getSchemasDiv().getFptr().add(fptr);
    return file;
  }

  public static FileType addDocumentationFileToMETS(MetsWrapper metsWrapper, String documentationFilePath,
    Path documentationFile, boolean calculateChecksum) throws SIPException, InterruptedException {
    FileType file = new FileType();
    file.setID(Utils.generateRandomId());

    // set checksum, mimetype, etc.
    setFileBasicInformation(documentationFile, file, calculateChecksum);

    // add to file section
    FLocat fileLocation = createFileLocation(documentationFilePath);
    file.getFLocat().add(fileLocation);
    metsWrapper.getDocumentationFileGroup().getFile().add(file);

    // add to struct map
    Fptr fptr = new Fptr();
    fptr.setFILEID(file);
    metsWrapper.getDocumentationDiv().getFptr().add(fptr);

    return file;
  }

  private static FLocat createFileLocation(String filePath) {
    FLocat fileLocation = new FLocat();
    fileLocation.setType(IPConstants.METS_TYPE_SIMPLE);
    fileLocation.setLOCTYPE(LocType.URL.toString());
    fileLocation.setHref(IPConstants.METS_FILE_URI_PREFIX + filePath);

    return fileLocation;
  }

  private static StructMapType generateParentIDStructMap(String parentId) {
    StructMapType structMap = new StructMapType();
    structMap.setID(Utils.generateRandomId());
    structMap.setLABEL("RODA structural map");

    DivType mainDiv = createDivForStructMap("RODA");
    DivType parentIdDiv = createDivForStructMap("Parent ID");

    Mptr mptrParent = new Mptr();
    mptrParent.setType(IPConstants.METS_TYPE_SIMPLE);
    mptrParent.setHref(parentId);
    mptrParent.setLOCTYPE(LocType.HANDLE.toString());
    parentIdDiv.getMptr().add(mptrParent);

    mainDiv.getDiv().add(parentIdDiv);
    structMap.setDiv(mainDiv);
    return structMap;
  }

  public static String extractParentIDFromStructMap(Mets mets) {
    String parentID = null;
    boolean found = false;

    for (StructMapType structMap : mets.getStructMap()) {
      if (structMap.getLABEL() != null && "RODA structural map".equalsIgnoreCase(structMap.getLABEL())
        && structMap.getDiv() != null) {
        DivType mainDiv = structMap.getDiv();

        if ("RODA".equalsIgnoreCase(mainDiv.getLABEL()) && mainDiv.getDiv() != null) {
          for (DivType div : mainDiv.getDiv()) {
            if ("Parent ID".equalsIgnoreCase(div.getLABEL()) && div.getMptr() != null) {
              for (Mptr m : div.getMptr()) {
                if (m.getHref() != null) {
                  parentID = m.getHref();
                  found = true;
                  break;
                }
              }
            }
            if (found) {
              break;
            }
          }
        }
      }
      if (found) {
        break;
      }
    }

    return parentID;
  }
}
