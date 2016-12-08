package org.roda_project.commons_ip.model.impl.eark;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.channels.ClosedByInterruptException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.xml.bind.DatatypeConverter;
import javax.xml.bind.JAXBException;

import org.apache.commons.lang3.StringUtils;
import org.roda_project.commons_ip.mets_v1_11.beans.DivType;
import org.roda_project.commons_ip.mets_v1_11.beans.DivType.Fptr;
import org.roda_project.commons_ip.mets_v1_11.beans.DivType.Mptr;
import org.roda_project.commons_ip.mets_v1_11.beans.FileType;
import org.roda_project.commons_ip.mets_v1_11.beans.FileType.FLocat;
import org.roda_project.commons_ip.mets_v1_11.beans.MdSecType.MdRef;
import org.roda_project.commons_ip.mets_v1_11.beans.Mets;
import org.roda_project.commons_ip.mets_v1_11.beans.MetsType;
import org.roda_project.commons_ip.mets_v1_11.beans.StructMapType;
import org.roda_project.commons_ip.model.AIP;
import org.roda_project.commons_ip.model.IPConstants;
import org.roda_project.commons_ip.model.IPContentType;
import org.roda_project.commons_ip.model.IPDescriptiveMetadata;
import org.roda_project.commons_ip.model.IPFile;
import org.roda_project.commons_ip.model.IPMetadata;
import org.roda_project.commons_ip.model.IPRepresentation;
import org.roda_project.commons_ip.model.MetadataType;
import org.roda_project.commons_ip.model.MetsWrapper;
import org.roda_project.commons_ip.model.ParseException;
import org.roda_project.commons_ip.model.RepresentationContentType;
import org.roda_project.commons_ip.model.RepresentationStatus;
import org.roda_project.commons_ip.model.ValidationEntry;
import org.roda_project.commons_ip.utils.IPEnums;
import org.roda_project.commons_ip.utils.IPEnums.IPStatus;
import org.roda_project.commons_ip.utils.IPException;
import org.roda_project.commons_ip.utils.METSFileTypeZipEntryInfo;
import org.roda_project.commons_ip.utils.METSMdRefZipEntryInfo;
import org.roda_project.commons_ip.utils.METSUtils;
import org.roda_project.commons_ip.utils.SIPException;
import org.roda_project.commons_ip.utils.Utils;
import org.roda_project.commons_ip.utils.ValidationConstants;
import org.roda_project.commons_ip.utils.ValidationUtils;
import org.roda_project.commons_ip.utils.ZIPUtils;
import org.roda_project.commons_ip.utils.ZipEntryInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;

/**
 * EARK AIP.
 * 
 * @author Rui Castro (rui.castro@gmail.com)
 */
public class EARKAIP extends AIP {
  private static final Logger LOGGER = LoggerFactory.getLogger(EARKSIP.class);

  private static final String TEMP_DIR = "EARKAIP";
  private static final String FILE_EXTENSION = ".zip";

  private static boolean VALIDATION_FAIL_IF_REPRESENTATION_METS_DOES_NOT_HAVE_TWO_PARTS = false;

  public EARKAIP() {
    super();
  }

  /**
   * @param aipId
   *          will be used as OBJID in METS (/mets[@OBJID])
   */
  public EARKAIP(String aipId) {
    super(aipId);
  }

  /**
   * @param aipId
   *          will be used as OBJID in METS (/mets[@OBJID])
   */
  public EARKAIP(String aipId, IPContentType contentType) {
    super(aipId, contentType);
  }

  @Override
  public Path build(final Path destinationDirectory) throws IPException, InterruptedException {
    return build(destinationDirectory, null);
  }

  @Override
  public Path build(final Path destinationDirectory, final String fileNameWithoutExtension)
    throws IPException, InterruptedException {
    final Path buildDir = createBuildDir();
    Path zipPath = null;
    try {
      final List<ZipEntryInfo> zipEntries = getZipEntries();
      zipPath = getDirPath(destinationDirectory, fileNameWithoutExtension);

      // 20160407 hsilva: as METS does not have an attribute 'otherType', the
      // other type must be put in the 'type' attribute allowing this way other
      // values besides the ones in the Enum
      final String contentType = this.getContentType().asString();

      final MetsWrapper mainMETSWrapper = EARKMETSUtils.generateMETS(StringUtils.join(this.getIds(), " "),
        this.getDescription(), this.getType() + ":" + contentType, this.getProfile(), this.getAgents(), true,
        Optional.ofNullable(this.getAncestors()), null, this.getStatus());

      addDescriptiveMetadataToZipAndMETS(zipEntries, mainMETSWrapper, getDescriptiveMetadata(), null);

      addPreservationMetadataToZipAndMETS(zipEntries, mainMETSWrapper, getPreservationMetadata(), null);

      addOtherMetadataToZipAndMETS(zipEntries, mainMETSWrapper, getOtherMetadata(), null);

      addRepresentationsToZipAndMETS(zipEntries, mainMETSWrapper, buildDir);

      addDefaultSchemas(buildDir);

      addSchemasToZipAndMETS(zipEntries, mainMETSWrapper, getSchemas(), null);

      addDocumentationToZipAndMETS(zipEntries, mainMETSWrapper, getDocumentation(), null);

      addSubmissionsToZipAndMETS(zipEntries, mainMETSWrapper, getSubmissions());

      addMainMETSToZip(zipEntries, mainMETSWrapper, buildDir);

      // createZipFile(zipEntries, zipPath);
      writeToPath(zipEntries, zipPath);

      return zipPath;
    } catch (final InterruptedException e) {
      cleanUpUponInterrupt(zipPath);
      throw e;
    } catch (final IPException e) {
      throw new SIPException(e.getMessage(), e);
    } finally {
      deleteBuildDir(buildDir);
    }
  }

  public static AIP parse(final Path source) throws ParseException {
    try {
      if (source.toFile().isDirectory()) {
        return parseEARKAIPFromPath(source);
      } else {
        return parse(source, Files.createTempDirectory("unzipped"));
      }
    } catch (final IOException e) {
      throw new ParseException("Error creating temporary directory for E-ARK SIP parse", e);
    }
  }

  public static AIP parse(Path source, Path destinationDirectory) throws ParseException {
    return parseEARKAIP(source, destinationDirectory);
  }

  private Path createBuildDir() throws SIPException {
    try {
      return Files.createTempDirectory(TEMP_DIR);
    } catch (IOException e) {
      throw new SIPException("Unable to create temporary directory to hold SIP files", e);
    }
  }

  private void cleanUpUponInterrupt(Path zipPath) {
    if (zipPath != null && Files.exists(zipPath)) {
      try {
        Utils.deletePath(zipPath);
      } catch (IOException e) {
        LOGGER.error("Error while cleaning up unneeded files", e);
      }
    }
  }

  private void deleteBuildDir(Path buildDir) throws SIPException {
    try {
      Utils.deletePath(buildDir);
    } catch (IOException e) {
      throw new SIPException("Error while deleting temporary directory that was created to hold SIP files", e);
    }
  }

  private Path getDirPath(final Path targetPath, final String name) throws IPException {
    final Path path;
    if (name != null) {
      path = targetPath.resolve(name);
    } else {
      path = targetPath.resolve(getId());
    }

    try {
      if (Files.exists(path)) {
        Files.delete(path);
      }
    } catch (final IOException e) {
      throw new IPException("Error deleting existing path - " + e.getMessage(), e);
    }
    return path;
  }

  public void addDescriptiveMetadataToZipAndMETS(List<ZipEntryInfo> zipEntries, MetsWrapper metsWrapper,
    List<IPDescriptiveMetadata> descriptiveMetadata, String representationId)
    throws SIPException, InterruptedException {
    if (descriptiveMetadata != null && !descriptiveMetadata.isEmpty()) {
      for (IPDescriptiveMetadata dm : descriptiveMetadata) {
        if (Thread.interrupted()) {
          throw new InterruptedException();
        }
        IPFile file = dm.getMetadata();

        String descriptiveFilePath = IPConstants.DESCRIPTIVE_FOLDER + getFoldersFromList(file.getRelativeFolders())
          + file.getFileName();
        MdRef mdRef = EARKMETSUtils.addDescriptiveMetadataToMETS(metsWrapper, dm, descriptiveFilePath);

        if (representationId != null) {
          descriptiveFilePath = IPConstants.REPRESENTATIONS_FOLDER + representationId + IPConstants.ZIP_PATH_SEPARATOR
            + descriptiveFilePath;
        }
        ZIPUtils.addMdRefFileToZip(zipEntries, file.getPath(), descriptiveFilePath, mdRef);
      }
    }
  }

  public void addPreservationMetadataToZipAndMETS(List<ZipEntryInfo> zipEntries, MetsWrapper metsWrapper,
    List<IPMetadata> preservationMetadata, String representationId) throws SIPException, InterruptedException {
    if (preservationMetadata != null && !preservationMetadata.isEmpty()) {
      for (IPMetadata pm : preservationMetadata) {
        if (Thread.interrupted()) {
          throw new InterruptedException();
        }
        IPFile file = pm.getMetadata();

        String preservationMetadataPath = IPConstants.PRESERVATION_FOLDER
          + getFoldersFromList(file.getRelativeFolders()) + file.getFileName();
        MdRef mdRef = EARKMETSUtils.addPreservationMetadataToMETS(metsWrapper, pm, preservationMetadataPath);

        if (representationId != null) {
          preservationMetadataPath = IPConstants.REPRESENTATIONS_FOLDER + representationId
            + IPConstants.ZIP_PATH_SEPARATOR + preservationMetadataPath;
        }
        ZIPUtils.addMdRefFileToZip(zipEntries, file.getPath(), preservationMetadataPath, mdRef);
      }
    }
  }

  public void addOtherMetadataToZipAndMETS(List<ZipEntryInfo> zipEntries, MetsWrapper metsWrapper,
    List<IPMetadata> otherMetadata, String representationId) throws SIPException, InterruptedException {
    if (otherMetadata != null && !otherMetadata.isEmpty()) {
      for (IPMetadata om : otherMetadata) {
        if (Thread.interrupted()) {
          throw new InterruptedException();
        }
        IPFile file = om.getMetadata();

        String otherMetadataPath = IPConstants.OTHER_FOLDER + getFoldersFromList(file.getRelativeFolders())
          + file.getFileName();
        MdRef mdRef = EARKMETSUtils.addOtherMetadataToMETS(metsWrapper, om, otherMetadataPath);

        if (representationId != null) {
          otherMetadataPath = IPConstants.REPRESENTATIONS_FOLDER + representationId + IPConstants.ZIP_PATH_SEPARATOR
            + otherMetadataPath;
        }
        ZIPUtils.addMdRefFileToZip(zipEntries, file.getPath(), otherMetadataPath, mdRef);
      }
    }
  }

  public void addRepresentationsToZipAndMETS(List<ZipEntryInfo> zipEntries, MetsWrapper mainMETSWrapper, Path buildDir)
    throws SIPException, InterruptedException {
    // representations
    if (getRepresentations() != null && !getRepresentations().isEmpty()) {
      // this.notifySipBuildRepresentationsProcessingStarted(getRepresentations().size());
      for (IPRepresentation representation : getRepresentations()) {
        if (Thread.interrupted()) {
          throw new InterruptedException();
        }
        String representationId = representation.getObjectID();
        // 20160407 hsilva: not being used by Common Specification v0.13
        String representationProfile = "";
        String representationContentType = representation.getContentType().asString();

        MetsWrapper representationMETSWrapper = EARKMETSUtils.generateMETS(representationId,
          representation.getDescription(),
          IPConstants.METS_REPRESENTATION_TYPE_PART_1 + ":" + representationContentType, representationProfile,
          representation.getAgents(), false, Optional.empty(), null, IPEnums.IPStatus.NEW);
        representationMETSWrapper.getMainDiv().setTYPE(representation.getStatus().asString());

        // representation data
        addRepresentationDataFilesToZipAndMETS(zipEntries, representationMETSWrapper, representation, representationId);

        // representation descriptive metadata
        addDescriptiveMetadataToZipAndMETS(zipEntries, representationMETSWrapper,
          representation.getDescriptiveMetadata(), representationId);

        // representation preservation metadata
        addPreservationMetadataToZipAndMETS(zipEntries, representationMETSWrapper,
          representation.getPreservationMetadata(), representationId);

        // representation other metadata
        addOtherMetadataToZipAndMETS(zipEntries, representationMETSWrapper, representation.getOtherMetadata(),
          representationId);

        // representation schemas
        addSchemasToZipAndMETS(zipEntries, representationMETSWrapper, representation.getSchemas(), representationId);

        // representation documentation
        addDocumentationToZipAndMETS(zipEntries, representationMETSWrapper, representation.getDocumentation(),
          representationId);

        // add representation METS to Zip file and to main METS file
        EARKMETSUtils.addRepresentationMETSToZipAndToMainMETS(zipEntries, mainMETSWrapper, representationId,
          representationMETSWrapper, IPConstants.REPRESENTATIONS_FOLDER + representationId
            + IPConstants.ZIP_PATH_SEPARATOR + IPConstants.METS_FILE,
          buildDir);
      }
      // this.notifySipBuildRepresentationsProcessingEnded();
    }
  }

  private void addRepresentationDataFilesToZipAndMETS(List<ZipEntryInfo> zipEntries,
    MetsWrapper representationMETSWrapper, IPRepresentation representation, String representationId)
    throws SIPException, InterruptedException {
    if (representation.getData() != null && !representation.getData().isEmpty()) {
      // this.notifySipBuildRepresentationProcessingStarted(representation.getData().size());
      int i = 0;
      for (IPFile file : representation.getData()) {
        if (Thread.interrupted()) {
          throw new InterruptedException();
        }

        String dataFilePath = IPConstants.DATA_FOLDER + getFoldersFromList(file.getRelativeFolders())
          + file.getFileName();
        FileType fileType = EARKMETSUtils.addDataFileToMETS(representationMETSWrapper, dataFilePath, file.getPath());

        dataFilePath = IPConstants.REPRESENTATIONS_FOLDER + representationId + IPConstants.ZIP_PATH_SEPARATOR
          + dataFilePath;
        ZIPUtils.addFileTypeFileToZip(zipEntries, file.getPath(), dataFilePath, fileType);

        i++;
        // this.notifySipBuildRepresentationProcessingCurrentStatus(i);
      }
      // this.notifySipBuildRepresentationProcessingEnded();
    }
  }

  private void addDefaultSchemas(Path buildDir) throws InterruptedException {
    try {
      if (Thread.interrupted()) {
        throw new InterruptedException();
      }
      Path metsSchema = Utils.copyResourceFromClasspathToDir(EARKAIP.class, buildDir, "mets.xsd",
        "/schemas/mets1_11.xsd");
      getSchemas().add(new IPFile(metsSchema, "mets.xsd"));
      Path xlinkSchema = Utils.copyResourceFromClasspathToDir(EARKAIP.class, buildDir, "xlink.xsd",
        "/schemas/xlink.xsd");
      getSchemas().add(new IPFile(xlinkSchema, "xlink.xsd"));
    } catch (IOException e) {
      LOGGER.error("Error while trying to add default schemas", e);
    }
  }

  public void addSchemasToZipAndMETS(List<ZipEntryInfo> zipEntries, MetsWrapper metsWrapper, List<IPFile> schemas,
    String representationId) throws SIPException, InterruptedException {
    if (schemas != null && !schemas.isEmpty()) {
      for (IPFile schema : schemas) {
        if (Thread.interrupted()) {
          throw new InterruptedException();
        }

        String schemaFilePath = IPConstants.SCHEMAS_FOLDER + getFoldersFromList(schema.getRelativeFolders())
          + schema.getFileName();
        FileType fileType = EARKMETSUtils.addSchemaFileToMETS(metsWrapper, schemaFilePath, schema.getPath());

        if (representationId != null) {
          schemaFilePath = IPConstants.REPRESENTATIONS_FOLDER + representationId + IPConstants.ZIP_PATH_SEPARATOR
            + schemaFilePath;
        }
        ZIPUtils.addFileTypeFileToZip(zipEntries, schema.getPath(), schemaFilePath, fileType);
      }
    }
  }

  public void addSubmissionsToZipAndMETS(List<ZipEntryInfo> zipEntries, MetsWrapper metsWrapper,
    List<IPFile> submissions) throws SIPException, InterruptedException {
    if (submissions != null && !submissions.isEmpty()) {
      for (IPFile submission : submissions) {
        if (Thread.interrupted()) {
          throw new InterruptedException();
        }
        String submissionFilePath = IPConstants.SCHEMAS_FOLDER + getFoldersFromList(submission.getRelativeFolders())
          + submission.getFileName();
        FileType fileType = EARKMETSUtils.addSubmissionFileToMETS(metsWrapper, submissionFilePath,
          submission.getPath());
        ZIPUtils.addFileTypeFileToZip(zipEntries, submission.getPath(), submissionFilePath, fileType);
      }
    }
  }

  public void addDocumentationToZipAndMETS(List<ZipEntryInfo> zipEntries, MetsWrapper metsWrapper,
    List<IPFile> documentation, String representationId) throws SIPException, InterruptedException {
    if (documentation != null && !documentation.isEmpty()) {
      for (IPFile doc : documentation) {
        if (Thread.interrupted()) {
          throw new InterruptedException();
        }

        String documentationFilePath = IPConstants.DOCUMENTATION_FOLDER + getFoldersFromList(doc.getRelativeFolders())
          + doc.getFileName();
        FileType fileType = EARKMETSUtils.addDocumentationFileToMETS(metsWrapper, documentationFilePath, doc.getPath());

        if (representationId != null) {
          documentationFilePath = IPConstants.REPRESENTATIONS_FOLDER + representationId + IPConstants.ZIP_PATH_SEPARATOR
            + documentationFilePath;
        }
        ZIPUtils.addFileTypeFileToZip(zipEntries, doc.getPath(), documentationFilePath, fileType);
      }
    }
  }

  private void addMainMETSToZip(List<ZipEntryInfo> zipEntries, MetsWrapper mainMETSWrapper, Path buildDir)
    throws SIPException {
    EARKMETSUtils.addMainMETSToZip(zipEntries, mainMETSWrapper, IPConstants.METS_FILE, buildDir);
  }

  private void createZipFile(List<ZipEntryInfo> zipEntries, Path zipPath) throws IPException, InterruptedException {
    try {
      // notifySipBuildPackagingStarted(zipEntries.size());
      ZIPUtils.zip(zipEntries, Files.newOutputStream(zipPath), this);
    } catch (ClosedByInterruptException e) {
      throw new InterruptedException();
    } catch (IOException e) {
      throw new SIPException("Error generating E-ARK SIP ZIP file. Reason: " + e.getMessage(), e);
    } finally {
      // notifySipBuildPackagingEnded();
    }
  }

  public void writeToPath(List<ZipEntryInfo> zipEntryInfos, Path path) throws IPException, InterruptedException {
    try {
      Files.createDirectory(path);
      for (ZipEntryInfo zipEntryInfo : zipEntryInfos) {
        if (Thread.interrupted()) {
          throw new InterruptedException();
        }
        zipEntryInfo.prepareEntryforZipping();
        LOGGER.debug("Zipping file {}", zipEntryInfo.getFilePath());

        final Path outputPath = Paths.get(path.toString(), zipEntryInfo.getName());
        Files.createDirectories(outputPath.getParent());

        try (InputStream is = Files.newInputStream(zipEntryInfo.getFilePath());
          OutputStream os = Files.newOutputStream(outputPath)) {
          byte[] buffer = new byte[4096];
          MessageDigest complete;
          try {
            complete = MessageDigest.getInstance(IPConstants.CHECKSUM_ALGORITHM);
            int numRead;
            do {
              numRead = is.read(buffer);
              if (numRead > 0) {
                complete.update(buffer, 0, numRead);
                os.write(buffer, 0, numRead);
              }
            } while (numRead != -1);
            LOGGER.debug("Done zipping file");
            String checksum = DatatypeConverter.printHexBinary(complete.digest());
            String checksumType = IPConstants.CHECKSUM_ALGORITHM;
            zipEntryInfo.setChecksum(checksum);
            zipEntryInfo.setChecksumAlgorithm(checksumType);
            if (zipEntryInfo instanceof METSFileTypeZipEntryInfo) {
              METSFileTypeZipEntryInfo f = (METSFileTypeZipEntryInfo) zipEntryInfo;
              f.getMetsFileType().setCHECKSUM(checksum);
              f.getMetsFileType().setCHECKSUMTYPE(checksumType);
            } else if (zipEntryInfo instanceof METSMdRefZipEntryInfo) {
              METSMdRefZipEntryInfo f = (METSMdRefZipEntryInfo) zipEntryInfo;
              f.getMetsMdRef().setCHECKSUM(checksum);
              f.getMetsMdRef().setCHECKSUMTYPE(checksumType);
            }
          } catch (NoSuchAlgorithmException e) {
            LOGGER.error("Error while zipping files", e);
          }
        }
      }
    } catch (final IOException e) {
      LOGGER.debug("Error in write method - " + e.getMessage(), e);
      throw new IPException(e.getMessage(), e);
    }
  }

  private String getFoldersFromList(List<String> folders) {
    StringBuilder sb = new StringBuilder();
    for (String folder : folders) {
      sb.append(folder);
      if (sb.length() > 0) {
        sb.append(IPConstants.ZIP_PATH_SEPARATOR);
      }
    }
    return sb.toString();
  }

  private static AIP parseEARKAIP(final Path source, final Path destinationDirectory) throws ParseException {
    Path aipPath = ZIPUtils.extractSIPIfInZipFormat(source, destinationDirectory, FILE_EXTENSION);
    return parseEARKAIPFromPath(aipPath);
  }

  private static AIP parseEARKAIPFromPath(final Path aipPath) throws ParseException {
    try {
      AIP aip = new EARKAIP();
      aip.setBasePath(aipPath);
      MetsWrapper metsWrapper = processMainMets(aip, aipPath);

      if (aip.isValid()) {

        StructMapType structMap = getEARKStructMap(metsWrapper, aip, true);

        if (structMap != null) {
          preProcessStructMap(metsWrapper, structMap);

          processDescriptiveMetadata(metsWrapper, aip, null, aip.getBasePath());

          processOtherMetadata(metsWrapper, aip, null, aip.getBasePath());

          processPreservationMetadata(metsWrapper, aip, null, aip.getBasePath());

          processRepresentations(metsWrapper, aip);

          processSchemasMetadata(metsWrapper, aip, aip.getBasePath());

          processDocumentationMetadata(metsWrapper, aip, aip.getBasePath());

          processAncestors(metsWrapper, aip);
        }
      }

      return aip;
    } catch (SIPException e) {
      throw new ParseException("Error parsing E-ARK SIP", e);
    }
  }

  private static MetsWrapper processMainMets(AIP sip, Path sipPath) {
    Path mainMETSFile = sipPath.resolve(IPConstants.METS_FILE);
    Mets mainMets = null;
    if (Files.exists(mainMETSFile)) {
      ValidationUtils.addInfo(sip.getValidationReport(), ValidationConstants.MAIN_METS_FILE_FOUND, sipPath,
        mainMETSFile);
      try {
        mainMets = METSUtils.instantiateMETSFromFile(mainMETSFile);
        sip.setIds(Arrays.asList(mainMets.getOBJID().split(" ")));
        sip.setCreateDate(mainMets.getMetsHdr().getCREATEDATE());
        sip.setModificationDate(mainMets.getMetsHdr().getLASTMODDATE());
        sip.setStatus(IPStatus.parse(mainMets.getMetsHdr().getRECORDSTATUS()));
        setAIPContentType(mainMets, sip);
        addAgentsToMETS(mainMets, sip, null);

        ValidationUtils.addInfo(sip.getValidationReport(), ValidationConstants.MAIN_METS_IS_VALID, sipPath,
          mainMETSFile);
      } catch (JAXBException | ParseException | SAXException e) {
        mainMets = null;
        ValidationUtils.addIssue(sip.getValidationReport(), ValidationConstants.MAIN_METS_NOT_VALID,
          ValidationEntry.LEVEL.ERROR, e, sip.getBasePath(), mainMETSFile);
      }
    } else {
      ValidationUtils.addIssue(sip.getValidationReport(), ValidationConstants.MAIN_METS_FILE_NOT_FOUND,
        ValidationEntry.LEVEL.ERROR, sip.getBasePath(), mainMETSFile);
    }
    return new MetsWrapper(mainMets, mainMETSFile);
  }

  private static MetsWrapper processRepresentationMets(AIP sip, Path representationMetsFile,
    IPRepresentation representation) {
    Mets representationMets = null;
    if (Files.exists(representationMetsFile)) {
      ValidationUtils.addInfo(sip.getValidationReport(), ValidationConstants.REPRESENTATION_METS_FILE_FOUND,
        sip.getBasePath(), representationMetsFile);
      try {
        representationMets = METSUtils.instantiateMETSFromFile(representationMetsFile);
        setRepresentationContentType(representationMets, representation);
        ValidationUtils.addInfo(sip.getValidationReport(), ValidationConstants.REPRESENTATION_METS_IS_VALID,
          sip.getBasePath(), representationMetsFile);
      } catch (JAXBException | ParseException | SAXException e) {
        representationMets = null;
        ValidationUtils.addIssue(sip.getValidationReport(), ValidationConstants.REPRESENTATION_METS_NOT_VALID,
          ValidationEntry.LEVEL.ERROR, e, sip.getBasePath(), representationMetsFile);
      }
    } else {
      ValidationUtils.addIssue(sip.getValidationReport(), ValidationConstants.REPRESENTATION_METS_FILE_NOT_FOUND,
        ValidationEntry.LEVEL.ERROR, sip.getBasePath(), representationMetsFile);
    }
    return new MetsWrapper(representationMets, representationMetsFile);
  }

  private static void setAIPContentType(Mets mets, AIP sip) throws ParseException {
    String metsType = mets.getTYPE();

    if (StringUtils.isBlank(metsType)) {
      throw new ParseException("METS 'TYPE' attribute does not contain any value");
    }

    String[] contentTypeParts = metsType.split(":");
    if (contentTypeParts.length != 2 || StringUtils.isBlank(contentTypeParts[0])
      || StringUtils.isBlank(contentTypeParts[1])) {
      throw new ParseException("METS 'TYPE' attribute does not contain a valid value");
    }

    IPEnums.IPType packageType;
    try {
      packageType = IPEnums.IPType.valueOf(contentTypeParts[0]);
      if (IPEnums.IPType.AIP != packageType) {
        throw new ParseException("METS 'TYPE' attribute should start with 'AIP:'");
      }
    } catch (IllegalArgumentException e) {
      throw new ParseException("METS 'TYPE' attribute does not contain a valid package type");
    }

    sip.setContentType(new IPContentType(contentTypeParts[1]));
  }

  private static void setRepresentationContentType(Mets mets, IPRepresentation representation) throws ParseException {
    String metsType = mets.getTYPE();

    if (StringUtils.isBlank(metsType)) {
      throw new ParseException("METS 'TYPE' attribute does not contain any value");
    }

    if ("representation".equals(metsType)) {
      if (VALIDATION_FAIL_IF_REPRESENTATION_METS_DOES_NOT_HAVE_TWO_PARTS) {
        throw new ParseException(
          "METS 'TYPE' attribute is not valid as it should be 'representation:REPRESENTATION_TYPE'");
      } else {
        return;
      }
    }

    String[] contentTypeParts = metsType.split(":");
    if (contentTypeParts.length != 2 || StringUtils.isBlank(contentTypeParts[0])
      || !"representation".equals(contentTypeParts[0]) || StringUtils.isBlank(contentTypeParts[1])) {
      throw new ParseException("METS 'TYPE' attribute does not contain a valid value");
    }

    representation.setContentType(new RepresentationContentType(contentTypeParts[1]));
  }

  private static Mets addAgentsToMETS(Mets mets, AIP sip, IPRepresentation representation) {
    if (mets.getMetsHdr() != null && mets.getMetsHdr().getAgent() != null) {
      for (MetsType.MetsHdr.Agent agent : mets.getMetsHdr().getAgent()) {
        if (representation == null) {
          sip.addAgent(EARKMETSUtils.createIPAgent(agent));
        } else {
          representation.addAgent(EARKMETSUtils.createIPAgent(agent));
        }
      }
    }

    return mets;
  }

  private static StructMapType getEARKStructMap(MetsWrapper metsWrapper, AIP sip, boolean mainMets) {
    Mets mets = metsWrapper.getMets();
    StructMapType res = null;
    for (StructMapType structMap : mets.getStructMap()) {
      if (StringUtils.equals(structMap.getLABEL(), IPConstants.E_ARK_STRUCTURAL_MAP)) {
        res = structMap;
        break;
      }
    }
    if (res == null) {
      ValidationUtils.addIssue(sip.getValidationReport(),
        mainMets ? ValidationConstants.MAIN_METS_HAS_NO_E_ARK_STRUCT_MAP
          : ValidationConstants.REPRESENTATION_METS_HAS_NO_E_ARK_STRUCT_MAP,
        ValidationEntry.LEVEL.ERROR, res, sip.getBasePath(), metsWrapper.getMetsPath());
    } else {
      ValidationUtils.addInfo(sip.getValidationReport(),
        mainMets ? ValidationConstants.MAIN_METS_HAS_E_ARK_STRUCT_MAP
          : ValidationConstants.REPRESENTATION_METS_HAS_E_ARK_STRUCT_MAP,
        res, sip.getBasePath(), metsWrapper.getMetsPath());
    }
    return res;
  }

  private static void preProcessStructMap(MetsWrapper metsWrapper, StructMapType structMap) {

    DivType sipDiv = structMap.getDiv();
    if (sipDiv.getDiv() != null) {
      metsWrapper.setMainDiv(sipDiv);
      for (DivType firstLevel : sipDiv.getDiv()) {
        if (IPConstants.METADATA.equalsIgnoreCase(firstLevel.getLABEL()) && firstLevel.getDiv() != null) {
          for (DivType secondLevel : firstLevel.getDiv()) {
            if (IPConstants.DESCRIPTIVE.equalsIgnoreCase(secondLevel.getLABEL())) {
              metsWrapper.setDescriptiveMetadataDiv(secondLevel);
            } else if (IPConstants.PRESERVATION.equalsIgnoreCase(secondLevel.getLABEL())) {
              metsWrapper.setPreservationMetadataDiv(secondLevel);
            } else if (IPConstants.OTHER.equalsIgnoreCase(secondLevel.getLABEL())) {
              metsWrapper.setOtherMetadataDiv(secondLevel);
            }
          }
        } else if (IPConstants.REPRESENTATIONS.equalsIgnoreCase(firstLevel.getLABEL())) {
          metsWrapper.setRepresentationsDiv(firstLevel);
        } else if (IPConstants.DATA.equalsIgnoreCase(firstLevel.getLABEL())) {
          metsWrapper.setDataDiv(firstLevel);
        } else if (IPConstants.SCHEMAS.equalsIgnoreCase(firstLevel.getLABEL())) {
          metsWrapper.setSchemasDiv(firstLevel);
        } else if (IPConstants.DOCUMENTATION.equalsIgnoreCase(firstLevel.getLABEL())) {
          metsWrapper.setDocumentationDiv(firstLevel);
        }
      }
    }
  }

  private static AIP processDescriptiveMetadata(MetsWrapper metsWrapper, AIP sip, IPRepresentation representation,
    Path basePath) throws SIPException {

    return processMetadata(sip, metsWrapper, representation, metsWrapper.getDescriptiveMetadataDiv(),
      IPConstants.DESCRIPTIVE, basePath);
  }

  private static AIP processOtherMetadata(MetsWrapper metsWrapper, AIP sip, IPRepresentation representation,
    Path basePath) throws SIPException {

    return processMetadata(sip, metsWrapper, representation, metsWrapper.getOtherMetadataDiv(), IPConstants.OTHER,
      basePath);
  }

  private static AIP processPreservationMetadata(MetsWrapper metsWrapper, AIP sip, IPRepresentation representation,
    Path basePath) throws SIPException {

    return processMetadata(sip, metsWrapper, representation, metsWrapper.getPreservationMetadataDiv(),
      IPConstants.PRESERVATION, basePath);
  }

  private static AIP processMetadata(AIP sip, MetsWrapper representationMetsWrapper, IPRepresentation representation,
    DivType div, String metadataType, Path basePath) throws SIPException {
    if (div != null && div.getFptr() != null) {
      for (Fptr fptr : div.getFptr()) {
        MdRef mdRef = (MdRef) fptr.getFILEID();
        String href = Utils.extractedRelativePathFromHref(mdRef);
        Path filePath = basePath.resolve(href);
        if (Files.exists(filePath)) {
          List<String> fileRelativeFolders = Utils
            .getFileRelativeFolders(basePath.resolve(IPConstants.METADATA).resolve(metadataType), filePath);

          processMetadataFile(sip, representation, metadataType, mdRef, filePath, fileRelativeFolders);
        } else {
          ValidationUtils.addIssue(sip.getValidationReport(),
            ValidationConstants.getMetadataFileNotFoundString(metadataType), ValidationEntry.LEVEL.ERROR,
            sip.getBasePath(), filePath);
        }
      }
    } else {
      ValidationUtils.addIssue(sip.getValidationReport(),
        ValidationConstants.getMetadataFileFptrNotFoundString(metadataType), ValidationEntry.LEVEL.ERROR,
        sip.getBasePath(), representationMetsWrapper.getMetsPath());
    }

    return sip;
  }

  private static void processMetadataFile(AIP sip, IPRepresentation representation, String metadataType, MdRef mdRef,
    Path filePath, List<String> fileRelativeFolders) throws SIPException {
    Optional<IPFile> metadataFile = validateMetadataFile(sip, filePath, mdRef, fileRelativeFolders);
    if (metadataFile.isPresent()) {
      ValidationUtils.addInfo(sip.getValidationReport(),
        ValidationConstants.getMetadataFileFoundWithMatchingChecksumString(metadataType), sip.getBasePath(), filePath);

      if (IPConstants.DESCRIPTIVE.equalsIgnoreCase(metadataType)) {
        MetadataType dmdType = new MetadataType(mdRef.getMDTYPE().toUpperCase());
        String dmdVersion = null;
        try {
          dmdVersion = mdRef.getMDTYPEVERSION();
          if (StringUtils.isNotBlank(mdRef.getOTHERMDTYPE())) {
            dmdType.setOtherType(mdRef.getOTHERMDTYPE());
          }
          LOGGER.debug("Metadata type valid: {}", dmdType);
        } catch (NullPointerException | IllegalArgumentException e) {
          // do nothing and use already defined values for metadataType &
          // metadataVersion
          LOGGER.debug("Setting metadata type to {}", dmdType);
          ValidationUtils.addEntry(sip.getValidationReport(), ValidationConstants.UNKNOWN_DESCRIPTIVE_METADATA_TYPE,
            ValidationEntry.LEVEL.WARN, "Setting metadata type to " + dmdType, sip.getBasePath(), filePath);
        }

        IPDescriptiveMetadata descriptiveMetadata = new IPDescriptiveMetadata(mdRef.getID(), metadataFile.get(),
          dmdType, dmdVersion);
        descriptiveMetadata.setCreateDate(mdRef.getCREATED());
        if (representation == null) {
          sip.addDescriptiveMetadata(descriptiveMetadata);
        } else {
          representation.addDescriptiveMetadata(descriptiveMetadata);
        }
      } else if (IPConstants.PRESERVATION.equalsIgnoreCase(metadataType)) {
        IPMetadata preservationMetadata = new IPMetadata(metadataFile.get());
        preservationMetadata.setCreateDate(mdRef.getCREATED());
        if (representation == null) {
          sip.addPreservationMetadata(preservationMetadata);
        } else {
          representation.addPreservationMetadata(preservationMetadata);
        }
      } else if (IPConstants.OTHER.equalsIgnoreCase(metadataType)) {
        IPMetadata otherMetadata = new IPMetadata(metadataFile.get());
        otherMetadata.setCreateDate(mdRef.getCREATED());
        if (representation == null) {
          sip.addOtherMetadata(otherMetadata);
        } else {
          representation.addOtherMetadata(otherMetadata);
        }
      }
    }
  }

  private static Optional<IPFile> validateFile(AIP sip, Path filePath, FileType fileType,
    List<String> fileRelativeFolders) {
    return Utils.validateFile(sip, filePath, fileRelativeFolders, fileType.getCHECKSUM(), fileType.getCHECKSUMTYPE(),
      fileType.getID());
  }

  private static Optional<IPFile> validateMetadataFile(AIP sip, Path filePath, MdRef mdRef,
    List<String> fileRelativeFolders) {
    return Utils.validateFile(sip, filePath, fileRelativeFolders, mdRef.getCHECKSUM(), mdRef.getCHECKSUMTYPE(),
      mdRef.getID());
  }

  private static AIP processFile(AIP sip, DivType div, String folder, Path basePath) throws SIPException {
    if (div != null && div.getFptr() != null) {
      for (Fptr fptr : div.getFptr()) {
        FileType fileType = (FileType) fptr.getFILEID();

        if (fileType.getFLocat() != null) {
          FLocat fLocat = fileType.getFLocat().get(0);
          String href = Utils.extractedRelativePathFromHref(fLocat.getHref());
          Path filePath = basePath.resolve(href);

          if (Files.exists(filePath)) {
            List<String> fileRelativeFolders = Utils.getFileRelativeFolders(basePath.resolve(folder), filePath);
            Optional<IPFile> file = validateFile(sip, filePath, fileType, fileRelativeFolders);

            if (file.isPresent()) {
              if (IPConstants.SCHEMAS.equalsIgnoreCase(folder)) {
                ValidationUtils.addInfo(sip.getValidationReport(),
                  ValidationConstants.SCHEMA_FILE_FOUND_WITH_MATCHING_CHECKSUMS, sip.getBasePath(), filePath);
                sip.addSchema(file.get());
              } else if (IPConstants.DOCUMENTATION.equalsIgnoreCase(folder)) {
                ValidationUtils.addInfo(sip.getValidationReport(),
                  ValidationConstants.DOCUMENTATION_FILE_FOUND_WITH_MATCHING_CHECKSUMS, sip.getBasePath(), filePath);
                sip.addDocumentation(file.get());
              }
            }
          } else {
            ValidationUtils.addIssue(sip.getValidationReport(),
              IPConstants.SCHEMAS.equalsIgnoreCase(folder) ? ValidationConstants.SCHEMA_FILE_NOT_FOUND
                : ValidationConstants.DOCUMENTATION_FILE_NOT_FOUND,
              ValidationEntry.LEVEL.ERROR, div, sip.getBasePath(), filePath);
          }
        }
      }
    }

    return sip;
  }

  private static AIP processRepresentations(MetsWrapper metsWrapper, AIP sip) throws SIPException {

    if (metsWrapper.getRepresentationsDiv() != null && metsWrapper.getRepresentationsDiv().getDiv() != null) {
      for (DivType representationDiv : metsWrapper.getRepresentationsDiv().getDiv()) {
        if (representationDiv.getMptr() != null && !representationDiv.getMptr().isEmpty()) {
          // we can assume one and only one mets for each representation div
          Mptr mptr = representationDiv.getMptr().get(0);
          String href = Utils.extractedRelativePathFromHref(mptr.getHref());
          Path metsFilePath = sip.getBasePath().resolve(href);
          IPRepresentation representation = new IPRepresentation(representationDiv.getLABEL());
          MetsWrapper representationMetsWrapper = processRepresentationMets(sip, metsFilePath, representation);

          if (representationMetsWrapper.getMets() != null) {
            Path representationBasePath = metsFilePath.getParent();

            StructMapType representationStructMap = getEARKStructMap(representationMetsWrapper, sip, false);
            if (representationStructMap != null) {

              preProcessStructMap(representationMetsWrapper, representationStructMap);
              representation.setStatus(new RepresentationStatus(representationMetsWrapper.getMainDiv().getTYPE()));
              sip.addRepresentation(representation);

              // process representation agents
              processRepresentationAgents(representationMetsWrapper, representation);

              // process files
              processRepresentationFiles(sip, representationMetsWrapper, representation, representationBasePath);

              // process descriptive metadata
              processDescriptiveMetadata(representationMetsWrapper, sip, representation, representationBasePath);

              // process preservation metadata
              processPreservationMetadata(representationMetsWrapper, sip, representation, representationBasePath);

              // process other metadata
              processOtherMetadata(representationMetsWrapper, sip, representation, representationBasePath);

              // process schemas
              processSchemasMetadata(representationMetsWrapper, sip, representationBasePath);

              // process documentation
              processDocumentationMetadata(representationMetsWrapper, sip, representationBasePath);
            }
          }
        }
      }

      // post-process validations
      if (sip.getRepresentations().isEmpty()) {
        ValidationUtils.addIssue(sip.getValidationReport(), ValidationConstants.MAIN_METS_NO_REPRESENTATIONS_FOUND,
          ValidationEntry.LEVEL.WARN, metsWrapper.getRepresentationsDiv(), sip.getBasePath(),
          metsWrapper.getMetsPath());
      }
    }

    return sip;

  }

  private static void processRepresentationAgents(MetsWrapper representationMetsWrapper,
    IPRepresentation representation) {

    addAgentsToMETS(representationMetsWrapper.getMets(), null, representation);
  }

  private static void processRepresentationFiles(AIP sip, MetsWrapper representationMetsWrapper,
    IPRepresentation representation, Path representationBasePath) throws SIPException {

    if (representationMetsWrapper.getDataDiv() != null && representationMetsWrapper.getDataDiv().getFptr() != null) {
      for (Fptr fptr : representationMetsWrapper.getDataDiv().getFptr()) {
        FileType fileType = (FileType) fptr.getFILEID();

        if (fileType != null && fileType.getFLocat() != null) {
          FLocat fLocat = fileType.getFLocat().get(0);
          String href = Utils.extractedRelativePathFromHref(fLocat.getHref());
          Path filePath = representationBasePath.resolve(href);
          if (Files.exists(filePath)) {
            List<String> fileRelativeFolders = Utils
              .getFileRelativeFolders(representationBasePath.resolve(IPConstants.DATA), filePath);
            Optional<IPFile> file = validateFile(sip, filePath, fileType, fileRelativeFolders);

            if (file.isPresent()) {
              representation.addFile(file.get());
              ValidationUtils.addInfo(sip.getValidationReport(),
                ValidationConstants.REPRESENTATION_FILE_FOUND_WITH_MATCHING_CHECKSUMS, sip.getBasePath(), filePath);
            }
          } else {
            ValidationUtils.addIssue(sip.getValidationReport(), ValidationConstants.REPRESENTATION_FILE_NOT_FOUND,
              ValidationEntry.LEVEL.ERROR, sip.getBasePath(), filePath);
          }
        } else {
          ValidationUtils.addIssue(sip.getValidationReport(), ValidationConstants.REPRESENTATION_FILE_HAS_NO_FLOCAT,
            ValidationEntry.LEVEL.ERROR, fileType, sip.getBasePath(), representationMetsWrapper.getMetsPath());
        }
      }

      // post-process validations
      if (representation.getData().isEmpty()) {
        ValidationUtils.addIssue(sip.getValidationReport(), ValidationConstants.REPRESENTATION_HAS_NO_FILES,
          ValidationEntry.LEVEL.WARN, representationMetsWrapper.getDataDiv(), sip.getBasePath(),
          representationMetsWrapper.getMetsPath());
      }
    }

  }

  private static AIP processSchemasMetadata(MetsWrapper metsWrapper, AIP sip, Path basePath) throws SIPException {

    return processFile(sip, metsWrapper.getSchemasDiv(), IPConstants.SCHEMAS, basePath);
  }

  private static AIP processDocumentationMetadata(MetsWrapper metsWrapper, AIP sip, Path basePath) throws SIPException {

    return processFile(sip, metsWrapper.getDocumentationDiv(), IPConstants.DOCUMENTATION, basePath);
  }

  private static AIP processAncestors(MetsWrapper metsWrapper, AIP sip) {
    Mets mets = metsWrapper.getMets();

    if (mets.getStructMap() != null && !mets.getStructMap().isEmpty()) {
      sip.setAncestors(EARKMETSUtils.extractAncestorsFromStructMap(mets));
    }

    return sip;
  }
}
