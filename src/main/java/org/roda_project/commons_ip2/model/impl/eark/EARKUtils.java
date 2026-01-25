/**
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE file at the root of the source
 * tree and available online at
 * <p>
 * https://github.com/keeps/commons-ip
 */

package org.roda_project.commons_ip2.model.impl.eark;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.xml.namespace.QName;

import org.apache.commons.lang3.StringUtils;
import org.roda_project.commons_ip.model.ParseException;
import org.roda_project.commons_ip.utils.IPEnums;
import org.roda_project.commons_ip.utils.IPEnums.IPStatus;
import org.roda_project.commons_ip.utils.IPException;
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
import org.roda_project.commons_ip2.mets_v1_12.beans.MetsType.MetsHdr.Agent;
import org.roda_project.commons_ip2.mets_v1_12.beans.StructMapType;
import org.roda_project.commons_ip2.model.AIP;
import org.roda_project.commons_ip2.model.IPConstants;
import org.roda_project.commons_ip2.model.IPContentInformationType;
import org.roda_project.commons_ip2.model.IPContentType;
import org.roda_project.commons_ip2.model.IPDescriptiveMetadata;
import org.roda_project.commons_ip2.model.IPFile;
import org.roda_project.commons_ip2.model.IPFileInterface;
import org.roda_project.commons_ip2.model.IPFileShallow;
import org.roda_project.commons_ip2.model.IPHeader;
import org.roda_project.commons_ip2.model.IPInterface;
import org.roda_project.commons_ip2.model.IPMetadata;
import org.roda_project.commons_ip2.model.IPRepresentation;
import org.roda_project.commons_ip2.model.MetadataType;
import org.roda_project.commons_ip2.model.MetsWrapper;
import org.roda_project.commons_ip2.model.RepresentationStatus;
import org.roda_project.commons_ip2.model.SIP;
import org.roda_project.commons_ip2.model.ValidationEntry;
import org.roda_project.commons_ip2.model.impl.ModelUtils;
import org.roda_project.commons_ip2.utils.METSUtils;
import org.roda_project.commons_ip2.utils.Utils;
import org.roda_project.commons_ip2.utils.ValidationUtils;
import org.roda_project.commons_ip2.utils.ZIPUtils;
import org.slf4j.Logger;
import org.xml.sax.SAXException;

import jakarta.xml.bind.JAXBException;

/**
 * E-ARK common utility methods.
 */
public class EARKUtils {

  /**
   * Marker for OTHER values in METS attributes.
   */
  private static final String OTHER = "OTHER";
  /**
   * Slash used in METS labels.
   */
  private static final String SLASH = "/";
  /**
   * Log message for validated metadata type.
   */
  private static final String METADATA_TYPE_VALID_MSG = "Metadata type valid: {}";
  /**
   * Log message for fallback metadata type.
   */
  private static final String SETTING_METADATA_TYPE_MSG = "Setting metadata type to {}";
  /**
   * Prefix for fallback metadata type in validation entry.
   */
  private static final String SETTING_METADATA_TYPE_PREFIX = "Setting metadata type to ";
  /**
   * Prefix for unknown metadata type error.
   */
  private static final String UNKNOWN_METADATA_TYPE_PREFIX = "Unknown metadata type: ";
  /**
   * Suffix for OAIS package type error messages.
   */
  private static final String OAIS_PACKAGE_TYPE_SUFFIX = "'.";
  /**
   * METS generator used by utilities.
   */
  private final EARKMETSCreator metsGenerator;

  /**
   * Creates a new utility helper bound to a METS generator.
   *
   * @param metsGenerator generator used to build METS content
   */
  public EARKUtils(final EARKMETSCreator metsGenerator) {
    this.metsGenerator = metsGenerator;
  }

  protected void addDescriptiveMetadataToZipAndMETS(final Map<String, ZipEntryInfo> zipEntries,
                                                    final MetsWrapper metsWrapper,
                                                    final List<IPDescriptiveMetadata> descriptiveMetadata,
                                                    final String representationId)
      throws IPException, InterruptedException {
    if (descriptiveMetadata != null && !descriptiveMetadata.isEmpty()) {
      for (final IPDescriptiveMetadata dm : descriptiveMetadata) {
        if (Thread.interrupted()) {
          throw new InterruptedException();
        }
        final IPFileInterface file = dm.getMetadata();

        String descriptiveFilePath = IPConstants.DESCRIPTIVE_FOLDER
            + ModelUtils.getFoldersFromList(file.getRelativeFolders()) + file.getFileName();
        final MdRef mdRef = metsGenerator.addDescriptiveMetadataToMETS(metsWrapper, dm, descriptiveFilePath);

        if (representationId != null) {
          descriptiveFilePath = IPConstants.REPRESENTATIONS_FOLDER + representationId + IPConstants.ZIP_PATH_SEPARATOR
              + descriptiveFilePath;
        }
        ZIPUtils.addMdRefFileToZip(zipEntries, file.getPath(), descriptiveFilePath, mdRef);
      }
    }
  }

  protected void addPreservationMetadataToZipAndMETS(final Map<String, ZipEntryInfo> zipEntries,
                                                     final MetsWrapper metsWrapper,
                                                     final List<IPMetadata> preservationMetadata,
                                                     final String representationId)
      throws IPException, InterruptedException {
    if (preservationMetadata != null && !preservationMetadata.isEmpty()) {
      for (final IPMetadata pm : preservationMetadata) {
        if (Thread.interrupted()) {
          throw new InterruptedException();
        }
        final IPFileInterface file = pm.getMetadata();

        String preservationMetadataPath = IPConstants.PRESERVATION_FOLDER
            + ModelUtils.getFoldersFromList(file.getRelativeFolders()) + file.getFileName();
        final MdRef mdRef = metsGenerator.addPreservationMetadataToMETS(metsWrapper, pm, preservationMetadataPath);

        if (representationId != null) {
          preservationMetadataPath = IPConstants.REPRESENTATIONS_FOLDER + representationId
              + IPConstants.ZIP_PATH_SEPARATOR + preservationMetadataPath;
        }
        ZIPUtils.addMdRefFileToZip(zipEntries, file.getPath(), preservationMetadataPath, mdRef);
      }
    }
  }

  protected void addOtherMetadataToZipAndMETS(final Map<String, ZipEntryInfo> zipEntries,
                                              final MetsWrapper metsWrapper, final List<IPMetadata> otherMetadata,
                                              final String representationId)
      throws IPException, InterruptedException {
    if (otherMetadata != null && !otherMetadata.isEmpty()) {
      for (final IPMetadata om : otherMetadata) {
        if (Thread.interrupted()) {
          throw new InterruptedException();
        }
        final IPFileInterface file = om.getMetadata();

        String otherMetadataPath = IPConstants.OTHER_FOLDER
            + ModelUtils.getFoldersFromList(file.getRelativeFolders()) + file.getFileName();
        final MdRef mdRef = metsGenerator.addOtherMetadataToMETS(metsWrapper, om, otherMetadataPath);

        if (representationId != null) {
          otherMetadataPath = IPConstants.REPRESENTATIONS_FOLDER + representationId
              + IPConstants.ZIP_PATH_SEPARATOR + otherMetadataPath;
        }
        ZIPUtils.addMdRefFileToZip(zipEntries, file.getPath(), otherMetadataPath, mdRef);
      }
    }
  }

  protected void addTechnicalMetadataToZipAndMETS(final Map<String, ZipEntryInfo> zipEntries,
                                                  final MetsWrapper metsWrapper,
                                                  final List<IPMetadata> technicalMetadata,
                                                  final String representationId)
      throws IPException, InterruptedException {
    if (technicalMetadata != null && !technicalMetadata.isEmpty()) {
      for (final IPMetadata tm : technicalMetadata) {
        if (Thread.interrupted()) {
          throw new InterruptedException();
        }
        final IPFileInterface file = tm.getMetadata();

        String technicalMetadataPath = IPConstants.TECHNICAL_FOLDER
            + ModelUtils.getFoldersFromList(file.getRelativeFolders()) + file.getFileName();
        final MdRef mdRef = metsGenerator.addTechnicalMetadataToMETS(metsWrapper, tm, technicalMetadataPath);

        if (representationId != null) {
          technicalMetadataPath = IPConstants.REPRESENTATIONS_FOLDER + representationId + IPConstants.ZIP_PATH_SEPARATOR
              + technicalMetadataPath;
        }
        ZIPUtils.addMdRefFileToZip(zipEntries, file.getPath(), technicalMetadataPath, mdRef);
      }
    }
  }

  protected void addSourceMetadataToZipAndMETS(final Map<String, ZipEntryInfo> zipEntries,
                                               final MetsWrapper metsWrapper, final List<IPMetadata> sourceMetadata,
                                               final String representationId)
      throws IPException, InterruptedException {
    if (sourceMetadata != null && !sourceMetadata.isEmpty()) {
      for (final IPMetadata sm : sourceMetadata) {
        if (Thread.interrupted()) {
          throw new InterruptedException();
        }
        final IPFileInterface file = sm.getMetadata();

        String sourceMetadataPath = IPConstants.SOURCE_FOLDER
            + ModelUtils.getFoldersFromList(file.getRelativeFolders()) + file.getFileName();
        final MdRef mdRef = metsGenerator.addSourceMetadataToMETS(metsWrapper, sm, sourceMetadataPath);

        if (representationId != null) {
          sourceMetadataPath = IPConstants.REPRESENTATIONS_FOLDER + representationId
              + IPConstants.ZIP_PATH_SEPARATOR + sourceMetadataPath;
        }
        ZIPUtils.addMdRefFileToZip(zipEntries, file.getPath(), sourceMetadataPath, mdRef);
      }
    }
  }

  protected void addRightsMetadataToZipAndMETS(final Map<String, ZipEntryInfo> zipEntries,
                                               final MetsWrapper metsWrapper, final List<IPMetadata> rightsMetadata,
                                               final String representationId)
      throws IPException, InterruptedException {
    if (rightsMetadata != null && !rightsMetadata.isEmpty()) {
      for (final IPMetadata rm : rightsMetadata) {
        if (Thread.interrupted()) {
          throw new InterruptedException();
        }
        final IPFileInterface file = rm.getMetadata();

        String rightsMetadataPath = IPConstants.RIGHTS_FOLDER
            + ModelUtils.getFoldersFromList(file.getRelativeFolders()) + file.getFileName();
        final MdRef mdRef = metsGenerator.addRightsMetadataToMETS(metsWrapper, rm, rightsMetadataPath);

        if (representationId != null) {
          rightsMetadataPath = IPConstants.REPRESENTATIONS_FOLDER + representationId
              + IPConstants.ZIP_PATH_SEPARATOR + rightsMetadataPath;
        }
        ZIPUtils.addMdRefFileToZip(zipEntries, file.getPath(), rightsMetadataPath, mdRef);
      }
    }
  }

  protected void addRepresentationsToZipAndMETS(final IPInterface ip,
                                                final List<IPRepresentation> representations,
                                                final Map<String, ZipEntryInfo> zipEntries,
                                                final MetsWrapper mainMETSWrapper, final Path buildDir,
                                                final IPEnums.SipType sipType)
      throws IPException, InterruptedException {
    // representations
    if (representations != null && !representations.isEmpty()) {
      if (ip instanceof SIP) {
        ((SIP) ip).notifySipBuildRepresentationsProcessingStarted(representations.size());
      }
      for (final IPRepresentation representation : representations) {
        if (Thread.interrupted()) {
          throw new InterruptedException();
        }
        final String representationId = representation.getObjectID();
        // 20160407 hsilva: not being used by Common Specification v0.13
        final boolean isRepresentationMetadataOther = representation.getOtherMetadata() != null
            && !representation.getOtherMetadata().isEmpty();
        final boolean isRepresentationMetadata = representation.getDescriptiveMetadata() != null
            && !representation.getDescriptiveMetadata().isEmpty()
            || representation.getPreservationMetadata() != null
            && !representation.getPreservationMetadata().isEmpty();
        final boolean isRepresentationDocumentation = representation.getDocumentation() != null
            && !representation.getDocumentation().isEmpty();
        final boolean isRepresentationSchemas = representation.getSchemas() != null
            && !representation.getSchemas().isEmpty();
        final boolean isRepresentationsData = representation.getData() != null
            && !representation.getData().isEmpty();
        final IPHeader header = new IPHeader(IPEnums.IPStatus.NEW).setAgents(representation.getAgents());
        final MetsWrapper representationMETSWrapper;

        final String siardProfile = "https://citssiard.dilcis.eu/profile/E-ARK-SIARD-REPRESENTATION.xml";
        if (IPEnums.SipType.ERMS.equals(sipType)) {
          representationMETSWrapper = metsGenerator.generateMETS(representationId, representation.getDescription(),
              ip.getProfile(), false, Optional.empty(), null, header,
              mainMETSWrapper.getMets().getMetsHdr().getOAISPACKAGETYPE(), representation.getContentType(),
              representation.getContentInformationType(), isRepresentationMetadata, isRepresentationMetadataOther,
              isRepresentationSchemas, isRepresentationDocumentation, false, false, isRepresentationsData);
        } else if (IPEnums.SipType.SIARD.equals(sipType)) {
          representation.setContentInformationType(representation.getContentInformationType());
          representationMETSWrapper = metsGenerator.generateMetsSiard(representationId, representation.getDescription(),
              siardProfile, false, Optional.empty(), null, header,
              mainMETSWrapper.getMets().getMetsHdr().getOAISPACKAGETYPE(), representation.getContentType(),
              representation.getContentInformationType(), isRepresentationMetadata, isRepresentationMetadataOther,
              isRepresentationSchemas, isRepresentationDocumentation, false, false, isRepresentationsData);
        } else if (!IPEnums.SipType.EARK2S.equals(sipType)) {
          representationMETSWrapper = metsGenerator.generateMETS(representationId, representation.getDescription(),
              ip.getProfile(), false, Optional.empty(), null, header,
              mainMETSWrapper.getMets().getMetsHdr().getOAISPACKAGETYPE(), representation.getContentType(),
              representation.getContentInformationType(), isRepresentationMetadata, isRepresentationMetadataOther,
              isRepresentationSchemas, isRepresentationDocumentation, false, false, isRepresentationsData);
        } else {
          representationMETSWrapper = metsGenerator.generateMetsShallow(representation, ip.getProfile(), false,
              Optional.empty(), null, header, mainMETSWrapper.getMets().getMetsHdr().getOAISPACKAGETYPE(),
              isRepresentationMetadata, isRepresentationMetadataOther, isRepresentationSchemas,
              isRepresentationDocumentation, false, false, isRepresentationsData);
        }

        representationMETSWrapper.getMainDiv().setTYPE(representation.getStatus().asString());

        // representation data
        if (IPEnums.SipType.ERMS.equals(sipType)) {
          addRepresentationDataFilesToZipErmsAndMETS(ip, zipEntries, representationMETSWrapper, representation,
              representationId);
        } else if (IPEnums.SipType.SIARD.equals(sipType)) {
          addRepresentationDataFilesToZipSiardAndMETS(ip, zipEntries, representationMETSWrapper, representation,
              representationId);
        } else {
          addRepresentationDataFilesToZipAndMETS(ip, zipEntries, representationMETSWrapper, representation,
              representationId);
        }

        // representation descriptive metadata
        addDescriptiveMetadataToZipAndMETS(zipEntries, representationMETSWrapper,
            representation.getDescriptiveMetadata(), representationId);

        // representation preservation metadata
        addPreservationMetadataToZipAndMETS(zipEntries, representationMETSWrapper,
            representation.getPreservationMetadata(), representationId);

        // representation technical metadata
        addTechnicalMetadataToZipAndMETS(zipEntries, representationMETSWrapper, representation.getTechnicalMetadata(),
            representationId);

        // representation source metadata
        addSourceMetadataToZipAndMETS(zipEntries, representationMETSWrapper,
            representation.getSourceMetadata(), representationId);

        // representation rights metadata
        addRightsMetadataToZipAndMETS(zipEntries, representationMETSWrapper,
            representation.getRightsMetadata(), representationId);

        // representation other metadata
        addOtherMetadataToZipAndMETS(zipEntries, representationMETSWrapper, representation.getOtherMetadata(),
            representationId);

        // representation schemas
        addSchemasToZipAndMETS(zipEntries, representationMETSWrapper, representation.getSchemas(), representationId);

        // representation documentation
        addDocumentationToZipAndMETS(zipEntries, representationMETSWrapper, representation.getDocumentation(),
            representationId);

        // add representation METS to Zip file and to main METS file
        if (IPEnums.SipType.ERMS.equals(sipType)) {
          metsGenerator.addRepresentationMETSToZipAndToMainMETS(zipEntries, mainMETSWrapper, representationId,
              representationMETSWrapper,
              IPConstants.REPRESENTATIONS_FOLDER + representationId + IPConstants.ZIP_PATH_SEPARATOR + IPConstants.DATA
                  + IPConstants.ZIP_PATH_SEPARATOR + IPConstants.METS_FILE,
              buildDir);
        } else if (IPEnums.SipType.SIARD.equals(sipType)) {
          metsGenerator.addRepresentationSiardMETSToZipAndToMainMETS(zipEntries, mainMETSWrapper, representationId,
              representationMETSWrapper,
              IPConstants.REPRESENTATIONS_FOLDER + representationId + IPConstants.ZIP_PATH_SEPARATOR + IPConstants.DATA
                  + IPConstants.ZIP_PATH_SEPARATOR + IPConstants.METS_FILE,
              buildDir);

        } else {
          metsGenerator.addRepresentationMETSToZipAndToMainMETS(zipEntries, mainMETSWrapper, representationId,
              representationMETSWrapper, IPConstants.REPRESENTATIONS_FOLDER + representationId
                  + IPConstants.ZIP_PATH_SEPARATOR + IPConstants.METS_FILE,
              buildDir);
        }

        metsGenerator.cleanFileGrpStructure();
      }
      if (ip instanceof SIP) {
        ((SIP) ip).notifySipBuildRepresentationsProcessingEnded();
      }
    }

  }

  private void addRepresentationDataFilesToZipErmsAndMETS(final IPInterface ip,
                                                          final Map<String, ZipEntryInfo> zipEntries,
                                                          final MetsWrapper representationMETSWrapper,
                                                          final IPRepresentation representation,
                                                          final String representationId)
      throws InterruptedException, IPException {
    if (representation.getData() != null && !representation.getData().isEmpty()) {
      if (ip instanceof SIP sip) {
        sip.notifySipBuildRepresentationProcessingStarted(representation.getData().size());
      }
      int i = 0;
      for (final IPFileInterface file : representation.getData()) {
        if (Thread.interrupted()) {
          throw new InterruptedException();
        }

        if (file instanceof IPFile) {
          String dataFilePath = ModelUtils.getFoldersFromList(file.getRelativeFolders()) + file.getFileName();
          final FileType fileType = metsGenerator.addDataFileToMETS(representationMETSWrapper, dataFilePath,
              file.getPath());

          dataFilePath = IPConstants.DATA_FOLDER + dataFilePath;
          dataFilePath = IPConstants.REPRESENTATIONS_FOLDER + representationId + IPConstants.ZIP_PATH_SEPARATOR
              + dataFilePath;
          ZIPUtils.addFileTypeFileToZip(zipEntries, file.getPath(), dataFilePath, fileType);
        } else if (file instanceof IPFileShallow shallow && shallow.getFileLocation() != null) {
          metsGenerator.addDataFileToMETS(representationMETSWrapper, shallow);
        }

        i++;
        if (ip instanceof SIP sip) {
          sip.notifySipBuildRepresentationProcessingCurrentStatus(i);
        }
      }
      if (ip instanceof SIP sip) {
        sip.notifySipBuildRepresentationProcessingEnded();
      }
    }

  }

  private void addRepresentationDataFilesToZipSiardAndMETS(final IPInterface ip,
                                                           final Map<String, ZipEntryInfo> zipEntries,
                                                           final MetsWrapper representationMETSWrapper,
                                                           final IPRepresentation representation,
                                                           final String representationId)
      throws InterruptedException, IPException {
    if (representation.getData() != null && !representation.getData().isEmpty()) {
      if (ip instanceof SIP sip) {
        sip.notifySipBuildRepresentationProcessingStarted(representation.getData().size());
      }
      int i = 0;
      for (final IPFileInterface file : representation.getData()) {
        if (Thread.interrupted()) {
          throw new InterruptedException();
        }

        if (file instanceof IPFile) {
          String dataFilePath = ModelUtils.getFoldersFromList(file.getRelativeFolders()) + file.getFileName();
          final FileType fileType = metsGenerator.addDataFileToMETS(representationMETSWrapper, dataFilePath,
              file.getPath());
          if (representation.getContentInformationType().getOtherType() != null) {
            fileType.getOtherAttributes().put(QName.valueOf("csip:OTHERCONTENTINFORMATIONTYPE"),
                representation.getContentInformationType().getOtherType());
          }
          dataFilePath = IPConstants.DATA_FOLDER + dataFilePath;
          dataFilePath = IPConstants.REPRESENTATIONS_FOLDER + representationId + IPConstants.ZIP_PATH_SEPARATOR
              + dataFilePath;
          ZIPUtils.addFileTypeFileToZip(zipEntries, file.getPath(), dataFilePath, fileType);
        } else if (file instanceof IPFileShallow shallow && shallow.getFileLocation() != null) {
          metsGenerator.addDataFileToMETS(representationMETSWrapper, shallow);
        }

        i++;
        if (ip instanceof SIP sip) {
          sip.notifySipBuildRepresentationProcessingCurrentStatus(i);
        }
      }
      if (ip instanceof SIP sip) {
        sip.notifySipBuildRepresentationProcessingEnded();
      }
    }
  }

  protected void addRepresentationDataFilesToZipAndMETS(final IPInterface ip,
                                                        final Map<String, ZipEntryInfo> zipEntries,
                                                        final MetsWrapper representationMETSWrapper,
                                                        final IPRepresentation representation,
                                                        final String representationId)
      throws IPException, InterruptedException {
    if (representation.getData() != null && !representation.getData().isEmpty()) {
      if (ip instanceof SIP sip) {
        sip.notifySipBuildRepresentationProcessingStarted(representation.getData().size());
      }
      int i = 0;
      for (final IPFileInterface file : representation.getData()) {
        if (Thread.interrupted()) {
          throw new InterruptedException();
        }

        if (file instanceof IPFile) {
          String dataFilePath = IPConstants.DATA_FOLDER + ModelUtils.getFoldersFromList(file.getRelativeFolders())
              + file.getFileName();
          final FileType fileType = metsGenerator.addDataFileToMETS(representationMETSWrapper, dataFilePath,
              file.getPath());

          dataFilePath = IPConstants.REPRESENTATIONS_FOLDER + representationId + IPConstants.ZIP_PATH_SEPARATOR
              + dataFilePath;
          ZIPUtils.addFileTypeFileToZip(zipEntries, file.getPath(), dataFilePath, fileType);
        } else if (file instanceof IPFileShallow shallow && shallow.getFileLocation() != null) {
          metsGenerator.addDataFileToMETS(representationMETSWrapper, shallow);
        }

        i++;
        if (ip instanceof SIP sip) {
          sip.notifySipBuildRepresentationProcessingCurrentStatus(i);
        }
      }
      if (ip instanceof SIP sip) {
        sip.notifySipBuildRepresentationProcessingEnded();
      }
    }
  }

  protected void addSchemasToZipAndMETS(final Map<String, ZipEntryInfo> zipEntries, final MetsWrapper metsWrapper,
                                        final List<IPFileInterface> schemas, final String representationId)
      throws IPException, InterruptedException {
    if (schemas != null && !schemas.isEmpty()) {
      for (final IPFileInterface schema : schemas) {
        if (Thread.interrupted()) {
          throw new InterruptedException();
        }

        String schemaFilePath = IPConstants.SCHEMAS_FOLDER + ModelUtils.getFoldersFromList(schema.getRelativeFolders())
            + schema.getFileName();
        final FileType fileType = metsGenerator.addSchemaFileToMETS(metsWrapper, schemaFilePath, schema.getPath());

        if (representationId != null) {
          schemaFilePath = IPConstants.REPRESENTATIONS_FOLDER + representationId + IPConstants.ZIP_PATH_SEPARATOR
              + schemaFilePath;
        }
        ZIPUtils.addFileTypeFileToZip(zipEntries, schema.getPath(), schemaFilePath, fileType);
      }
    }
  }

  protected void addDocumentationToZipAndMETS(final Map<String, ZipEntryInfo> zipEntries,
                                              final MetsWrapper metsWrapper, final List<IPFileInterface> documentation,
                                              final String representationId)
      throws IPException, InterruptedException {
    if (documentation != null && !documentation.isEmpty()) {
      for (final IPFileInterface doc : documentation) {
        if (Thread.interrupted()) {
          throw new InterruptedException();
        }

        String documentationFilePath = IPConstants.DOCUMENTATION_FOLDER
            + ModelUtils.getFoldersFromList(doc.getRelativeFolders()) + doc.getFileName();
        final FileType fileType = metsGenerator.addDocumentationFileToMETS(metsWrapper, documentationFilePath,
            doc.getPath());

        if (representationId != null) {
          documentationFilePath = IPConstants.REPRESENTATIONS_FOLDER + representationId + IPConstants.ZIP_PATH_SEPARATOR
              + documentationFilePath;
        }
        ZIPUtils.addFileTypeFileToZip(zipEntries, doc.getPath(), documentationFilePath, fileType);
      }
    }
  }

  protected void addDefaultSchemas(final Logger logger, final List<IPFileInterface> schemas, final Path buildDir,
                                   final Boolean override)
      throws InterruptedException {
    try {
      if (Thread.interrupted()) {
        throw new InterruptedException();
      }

      String tempSchema = "";
      if (schemas.size() != 0) {

        tempSchema = schemas.get(0).getFileName();

        if (!override) {
          if (tempSchema.equals(IPConstants.SCHEMA_EARK_CSIP_FILENAME)
              || tempSchema.equals(IPConstants.SCHEMA_EARK_SIP_FILENAME)
              || tempSchema.equals(IPConstants.SCHEMA_METS_FILENAME_WITH_VERSION)
              || tempSchema.equals(IPConstants.SCHEMA_XLINK_FILENAME)) {
            schemas.remove(0);
            tempSchema = "";
          }
        }
      }

      final Path earkCsipSchema = Utils.copyResourceFromClasspathToDir(EARKSIP.class, buildDir,
          IPConstants.SCHEMA_EARK_CSIP_FILENAME, IPConstants.SCHEMA_EARK_CSIP_RELATIVE_PATH_FROM_RESOURCES);
      if (!tempSchema.equals(IPConstants.SCHEMA_EARK_CSIP_FILENAME)) {
        schemas.add(new IPFile(earkCsipSchema, IPConstants.SCHEMA_EARK_CSIP_FILENAME));
      }
      final Path earkSipSchema = Utils.copyResourceFromClasspathToDir(EARKSIP.class, buildDir,
          IPConstants.SCHEMA_EARK_SIP_FILENAME, IPConstants.SCHEMA_EARK_SIP_RELATIVE_PATH_FROM_RESOURCES);
      if (!tempSchema.equals(IPConstants.SCHEMA_EARK_SIP_FILENAME)) {
        schemas.add(new IPFile(earkSipSchema, IPConstants.SCHEMA_EARK_SIP_FILENAME));
      }
      final Path metsSchema = Utils.copyResourceFromClasspathToDir(EARKSIP.class, buildDir,
          IPConstants.SCHEMA_METS_FILENAME_WITH_VERSION, IPConstants.SCHEMA_METS_RELATIVE_PATH_FROM_RESOURCES);
      if (!tempSchema.equals(IPConstants.SCHEMA_METS_FILENAME_WITH_VERSION)) {
        schemas.add(new IPFile(metsSchema, IPConstants.SCHEMA_METS_FILENAME_WITH_VERSION));
      }
      final Path xlinkSchema = Utils.copyResourceFromClasspathToDir(EARKSIP.class, buildDir,
          IPConstants.SCHEMA_XLINK_FILENAME, IPConstants.SCHEMA_XLINK_RELATIVE_PATH_FROM_RESOURCES);
      if (!tempSchema.equals(IPConstants.SCHEMA_XLINK_FILENAME)) {
        schemas.add(new IPFile(xlinkSchema, IPConstants.SCHEMA_XLINK_FILENAME));
      }

    } catch (final IOException e) {
      logger.error("Error while trying to add default schemas", e);
    }
  }

  protected void addSubmissionsToZipAndMETS(final Map<String, ZipEntryInfo> zipEntries, final MetsWrapper metsWrapper,
                                            final List<IPFileInterface> submissions)
      throws IPException, InterruptedException {
    if (submissions != null && !submissions.isEmpty()) {
      for (final IPFileInterface submission : submissions) {
        if (Thread.interrupted()) {
          throw new InterruptedException();
        }
        final String submissionFilePath = IPConstants.SUBMISSION_FOLDER
            + ModelUtils.getFoldersFromList(submission.getRelativeFolders()) + submission.getFileName();
        final FileType fileType = metsGenerator.addSubmissionFileToMETS(metsWrapper, submissionFilePath,
            submission.getPath());
        ZIPUtils.addFileTypeFileToZip(zipEntries, submission.getPath(), submissionFilePath, fileType);
      }
    }
  }

  protected MetsWrapper processMainMets(final IPInterface ip, final Path ipPath) {
    final Path mainMETSFile = ipPath.resolve(IPConstants.METS_FILE);
    Mets mainMets = null;
    if (Files.exists(mainMETSFile)) {
      ValidationUtils.addInfo(ip.getValidationReport(), ValidationConstants.MAIN_METS_FILE_FOUND, ipPath, mainMETSFile);
      try {
        mainMets = METSUtils.instantiateMETSFromFile(mainMETSFile);
        ip.setIds(Arrays.asList(mainMets.getOBJID().split(" ")));
        ip.setCreateDate(mainMets.getMetsHdr().getCREATEDATE());
        ip.setModificationDate(mainMets.getMetsHdr().getLASTMODDATE());
        ip.setStatus(IPStatus.parse(mainMets.getMetsHdr().getRECORDSTATUS()));
        setIPContentType(mainMets, ip);
        setIPContentInformationType(mainMets, ip);
        addAgentsToMETS(mainMets, ip, null);

        ValidationUtils.addInfo(ip.getValidationReport(), ValidationConstants.MAIN_METS_IS_VALID, ipPath, mainMETSFile);
      } catch (final JAXBException | ParseException | SAXException | IOException e) {
        mainMets = null;
        ValidationUtils.addIssue(ip.getValidationReport(), ValidationConstants.MAIN_METS_NOT_VALID,
            ValidationEntry.LEVEL.ERROR, e, ip.getBasePath(), mainMETSFile);
      }
    } else {
      ValidationUtils.addIssue(ip.getValidationReport(), ValidationConstants.MAIN_METS_FILE_NOT_FOUND,
          ValidationEntry.LEVEL.ERROR, ip.getBasePath(), mainMETSFile);
    }
    return new MetsWrapper(mainMets, mainMETSFile);
  }

  protected void setIPContentType(final Mets mets, final IPInterface ip) throws ParseException {
    final String oaisPackageType = mets.getMetsHdr().getOAISPACKAGETYPE();

    if (StringUtils.isBlank(oaisPackageType)) {
      throw new ParseException("METS 'OAISPACKAGETYPE' attribute does not contain any value");
    }

    try {
      final IPEnums.IPType packageType = IPEnums.IPType.valueOf(oaisPackageType);

      if (ip instanceof SIP && IPEnums.IPType.SIP != packageType) {
        throw new ParseException("METS 'OAISPACKAGETYPE' should have the value 'SIP' but it doesn't. Instead, it has '"
            + packageType + OAIS_PACKAGE_TYPE_SUFFIX);
      } else if (ip instanceof AIP && IPEnums.IPType.AIP != packageType) {
        throw new ParseException("METS 'OAISPACKAGETYPE' should have the value 'AIP' but it doesn't. Instead, it has '"
            + packageType + OAIS_PACKAGE_TYPE_SUFFIX);
      }
    } catch (final IllegalArgumentException e) {
      throw new ParseException("METS 'OAISPACKAGETYPE' attribute does not contain a valid package type");
    }

    final IPContentType ipContentType = getContentType(mets);
    ip.setContentType(ipContentType);
  }

  private IPContentType getContentType(final Mets mets) throws ParseException {
    String contentType = mets.getTYPE();
    if (StringUtils.isBlank(contentType)) {
      throw new ParseException("METS 'TYPE' attribute does not contain any value");
    }
    if (OTHER.equalsIgnoreCase(contentType)) {
      if (StringUtils.isBlank(mets.getOTHERTYPE())) {
        throw new ParseException("METS 'OTHERTYPE' attribute does not contain any value");
      }
      contentType = mets.getOTHERTYPE();
    }
    return new IPContentType(contentType);
  }

  protected void setIPContentInformationType(final Mets mets, final IPInterface ip) throws ParseException {
    final IPContentInformationType ipContentInformationType = getIpContentInformationType(mets);
    if (ipContentInformationType == null) {
      return;
    }
    ip.setContentInformationType(ipContentInformationType);
  }

  private IPContentInformationType getIpContentInformationType(final Mets mets) throws ParseException {
    String contentInformationType = mets.getCONTENTINFORMATIONTYPE();
    if (StringUtils.isBlank(contentInformationType)) {
      return null;
    }
    if (OTHER.equalsIgnoreCase(contentInformationType)) {
      if (StringUtils.isBlank(mets.getOTHERCONTENTINFORMATIONTYPE())) {
        throw new ParseException("METS 'OTHERCONTENTINFORMATIONTYPE' attribute does not contain any value");
      }
      contentInformationType = mets.getOTHERCONTENTINFORMATIONTYPE();
    }
    return new IPContentInformationType(contentInformationType);
  }

  protected void addAgentsToMETS(final Mets mets, final IPInterface ip, final IPRepresentation representation) {
    if (mets.getMetsHdr() != null && mets.getMetsHdr().getAgent() != null) {
      for (final Agent agent : mets.getMetsHdr().getAgent()) {
        if (representation == null) {
          ip.addAgent(metsGenerator.createIPAgent(ip, agent));
        } else {
          representation.addAgent(metsGenerator.createIPAgent(ip, agent));
        }
      }
    }
  }

  protected MetsWrapper processRepresentationMets(final IPInterface ip, final Path representationMetsFile,
                                                  final IPRepresentation representation) {
    Mets representationMets = null;
    if (Files.exists(representationMetsFile)) {
      ValidationUtils.addInfo(ip.getValidationReport(), ValidationConstants.REPRESENTATION_METS_FILE_FOUND,
          ip.getBasePath(), representationMetsFile);
      try {
        representationMets = METSUtils.instantiateMETSFromFile(representationMetsFile);
        setRepresentationContentType(representationMets, representation);
        setRepresentationContentInformationType(representationMets, representation);
        ValidationUtils.addInfo(ip.getValidationReport(), ValidationConstants.REPRESENTATION_METS_IS_VALID,
            ip.getBasePath(), representationMetsFile);
      } catch (final JAXBException | ParseException | SAXException | IOException e) {
        representationMets = null;
        ValidationUtils.addIssue(ip.getValidationReport(), ValidationConstants.REPRESENTATION_METS_NOT_VALID,
            ValidationEntry.LEVEL.ERROR, e, ip.getBasePath(), representationMetsFile);
      }
    } else {
      ValidationUtils.addIssue(ip.getValidationReport(), ValidationConstants.REPRESENTATION_METS_FILE_NOT_FOUND,
          ValidationEntry.LEVEL.ERROR, ip.getBasePath(), representationMetsFile);
    }
    return new MetsWrapper(representationMets, representationMetsFile);
  }


  protected void setRepresentationContentType(final Mets mets, final IPRepresentation representation)
      throws ParseException {
    final IPContentType ipContentType = getContentType(mets);
    representation.setContentType(ipContentType);
  }

  protected void setRepresentationContentInformationType(final Mets mets, final IPRepresentation representation)
      throws ParseException {
    final IPContentInformationType ipContentInformationType = getIpContentInformationType(mets);
    if (ipContentInformationType == null) {
      return;
    }
    representation.setContentInformationType(ipContentInformationType);
  }

  protected IPInterface processRepresentations(final MetsWrapper metsWrapper, final IPInterface ip,
                                               final Logger logger)
      throws IPException {

    if (metsWrapper.getMainDiv() != null && metsWrapper.getMainDiv().getDiv() != null) {
      for (final DivType div : metsWrapper.getMainDiv().getDiv()) {
        if (div.getLABEL().startsWith(IPConstants.REPRESENTATIONS_WITH_FIRST_LETTER_CAPITAL)
            && div.getMptr() != null && !div.getMptr().isEmpty()) {
          // we can assume one and only one mets for each representation div
          final Mptr mptr = div.getMptr().get(0);
          final String href = Utils.extractedRelativePathFromHref(mptr.getHref());
          final Path metsFilePath = ip.getBasePath().resolve(href);
          final IPRepresentation representation = new IPRepresentation(
              div.getLABEL()
                  .replaceFirst(IPConstants.REPRESENTATIONS_WITH_FIRST_LETTER_CAPITAL + SLASH, ""));
          final MetsWrapper representationMetsWrapper = processRepresentationMets(ip, metsFilePath, representation);

          if (representationMetsWrapper.getMets() != null) {
            final Path representationBasePath = metsFilePath.getParent();

            final StructMapType representationStructMap = getEARKStructMap(representationMetsWrapper, ip, false);
            if (representationStructMap != null) {

              preProcessStructMap(representationMetsWrapper, representationStructMap);
              representation.setStatus(new RepresentationStatus(representationMetsWrapper.getMainDiv().getTYPE()));
              ip.addRepresentation(representation);

              // process representation agents
              processRepresentationAgents(representationMetsWrapper, representation);

              // process files
              processRepresentationFiles(ip, representationMetsWrapper, representation, representationBasePath);

              // process descriptive metadata
              processDescriptiveMetadata(representationMetsWrapper, ip, logger, representation, representationBasePath);

              // process preservation metadata
              processPreservationMetadata(representationMetsWrapper, ip, representation, representationBasePath);

              // process technical metadata
              processTechnicalMetadata(representationMetsWrapper, ip, representation, representationBasePath);

              // process source metadata
              processSourceMetadata(representationMetsWrapper, ip, representation, representationBasePath);

              // process rights metadata
              processRightsMetadata(representationMetsWrapper, ip, representation, representationBasePath);

              // process other metadata
              processOtherMetadata(representationMetsWrapper, ip, logger, representation, representationBasePath);

              // process schemas
              processSchemasMetadata(representationMetsWrapper, ip, representationBasePath);

              // process documentation
              processDocumentationMetadata(representationMetsWrapper, ip, representationBasePath);
            }
          }

        }
      }

      // post-process validations
      if (ip.getRepresentations().isEmpty()) {
        ValidationUtils.addIssue(ip.getValidationReport(), ValidationConstants.MAIN_METS_NO_REPRESENTATIONS_FOUND,
            ValidationEntry.LEVEL.WARN, metsWrapper.getMainDiv(), ip.getBasePath(), metsWrapper.getMetsPath());
      }
    }

    return ip;

  }

  protected StructMapType getEARKStructMap(final MetsWrapper metsWrapper, final IPInterface ip,
                                           final boolean mainMets) {
    final Mets mets = metsWrapper.getMets();
    StructMapType res = null;
    for (final StructMapType structMap : mets.getStructMap()) {
      if (StringUtils.equals(structMap.getLABEL(), IPConstants.COMMON_SPEC_STRUCTURAL_MAP)) {
        res = structMap;
        break;
      }
    }
    if (res == null) {
      ValidationUtils.addIssue(ip.getValidationReport(),
          mainMets ? ValidationConstants.MAIN_METS_HAS_NO_E_ARK_STRUCT_MAP
              : ValidationConstants.REPRESENTATION_METS_HAS_NO_E_ARK_STRUCT_MAP,
          ValidationEntry.LEVEL.ERROR, res, ip.getBasePath(), metsWrapper.getMetsPath());
    } else {
      ValidationUtils.addInfo(ip.getValidationReport(),
          mainMets ? ValidationConstants.MAIN_METS_HAS_E_ARK_STRUCT_MAP
              : ValidationConstants.REPRESENTATION_METS_HAS_E_ARK_STRUCT_MAP,
          res, ip.getBasePath(), metsWrapper.getMetsPath());
    }
    return res;
  }

  protected void preProcessStructMap(final MetsWrapper metsWrapper, final StructMapType structMap) {

    final DivType ipDiv = structMap.getDiv();
    if (ipDiv.getDiv() != null) {
      metsWrapper.setMainDiv(ipDiv);
      for (final DivType firstLevel : ipDiv.getDiv()) {
        if (IPConstants.METADATA.equalsIgnoreCase(firstLevel.getLABEL()) && firstLevel.getDiv() != null) {
          metsWrapper.setMetadataDiv(firstLevel);
        } else if ((IPConstants.METADATA + SLASH + IPConstants.OTHER).equalsIgnoreCase(firstLevel.getLABEL())) {
          metsWrapper.setOtherMetadataDiv(firstLevel);
        } else if (IPConstants.DATA.equalsIgnoreCase(firstLevel.getLABEL())) {
          metsWrapper.setDataDiv(firstLevel);
        } else if (IPConstants.SCHEMAS.equalsIgnoreCase(firstLevel.getLABEL())) {
          metsWrapper.setSchemasDiv(firstLevel);
        } else if (IPConstants.DOCUMENTATION.equalsIgnoreCase(firstLevel.getLABEL())) {
          metsWrapper.setDocumentationDiv(firstLevel);
        } else if (IPConstants.SUBMISSION.equalsIgnoreCase(firstLevel.getLABEL())) {
          metsWrapper.setSubmissionsDiv(firstLevel);
        }
      }
    }
  }

  protected void processDescriptiveMetadata(final MetsWrapper metsWrapper, final IPInterface ip, final Logger logger,
                                            final IPRepresentation representation, final Path basePath)
      throws IPException {
    final String metadataType = IPConstants.DESCRIPTIVE;
    final List<MdSecType> dmdSec = metsWrapper.getMets().getDmdSec();
    for (final MdSecType mdSecType : dmdSec) {
      final MdRef mdRef = mdSecType.getMdRef();
      if (mdRef != null) {
        final String href = Utils.extractedRelativePathFromHref(mdRef);
        final Path filePath = basePath.resolve(href);
        if (Files.exists(filePath)) {
          final List<String> fileRelativeFolders = Utils
              .getFileRelativeFolders(basePath.resolve(IPConstants.METADATA).resolve(metadataType), filePath);

          final Optional<IPFileInterface> metadataFile = validateMetadataFile(ip, filePath, mdRef,
              fileRelativeFolders);
          if (metadataFile.isPresent()) {
            ValidationUtils.addInfo(ip.getValidationReport(),
                ValidationConstants.getMetadataFileFoundWithMatchingChecksumString(metadataType), ip.getBasePath(),
                filePath);

            final MetadataType dmdType = new MetadataType(mdRef.getMDTYPE().toUpperCase());
            String dmdVersion = null;
            try {
              dmdVersion = mdRef.getMDTYPEVERSION();
              if (StringUtils.isNotBlank(mdRef.getOTHERMDTYPE())) {
                dmdType.setOtherType(mdRef.getOTHERMDTYPE());
              }
              logger.debug(METADATA_TYPE_VALID_MSG, dmdType);
            } catch (final NullPointerException | IllegalArgumentException e) {
              // do nothing and use already defined values for metadataType &
              // metadataVersion
              logger.debug(SETTING_METADATA_TYPE_MSG, dmdType);
              ValidationUtils.addEntry(ip.getValidationReport(), ValidationConstants.UNKNOWN_DESCRIPTIVE_METADATA_TYPE,
                  ValidationEntry.LEVEL.WARN, SETTING_METADATA_TYPE_PREFIX + dmdType, ip.getBasePath(), filePath);
            }

            final IPDescriptiveMetadata descriptiveMetadata = new IPDescriptiveMetadata(mdRef.getID(),
                metadataFile.get(), dmdType, dmdVersion);
            descriptiveMetadata.setCreateDate(mdRef.getCREATED());
            if (representation == null) {
              ip.addDescriptiveMetadata(descriptiveMetadata);
            } else {
              representation.addDescriptiveMetadata(descriptiveMetadata);
            }
          }
        } else {
          ValidationUtils.addIssue(ip.getValidationReport(),
              ValidationConstants.getMetadataFileNotFoundString(metadataType), ValidationEntry.LEVEL.ERROR,
              ip.getBasePath(), filePath);
        }
      }
    }
  }

  protected void processOtherMetadata(final MetsWrapper metsWrapper, final IPInterface ip, final Logger logger,
                                      final IPRepresentation representation, final Path basePath) throws IPException {

    processMetadata(ip, logger, representation, metsWrapper.getOtherMetadataDiv(), IPConstants.OTHER, basePath);
  }

  protected void processPreservationMetadata(final MetsWrapper metsWrapper, final IPInterface ip,
                                             final IPRepresentation representation, final Path basePath)
      throws IPException {
    final String metadataType = IPConstants.PRESERVATION;
    for (final AmdSecType amdSecType : metsWrapper.getMets().getAmdSec()) {
      for (final MdSecType mdSecType : amdSecType.getDigiprovMD()) {
        final MdRef mdRef = mdSecType.getMdRef();

        processMdRef(mdRef, metadataType, ip, representation, basePath);
      }
    }
  }

  protected void processTechnicalMetadata(final MetsWrapper metsWrapper, final IPInterface ip,
                                          final IPRepresentation representation, final Path basePath)
      throws IPException {
    final String metadataType = IPConstants.TECHNICAL;
    for (final AmdSecType amdSecType : metsWrapper.getMets().getAmdSec()) {
      for (final MdSecType mdSecType : amdSecType.getTechMD()) {
        final MdRef mdRef = mdSecType.getMdRef();

        processMdRef(mdRef, metadataType, ip, representation, basePath);
      }
    }
  }

  protected void processSourceMetadata(final MetsWrapper metsWrapper, final IPInterface ip,
                                       final IPRepresentation representation, final Path basePath) throws IPException {

    final String metadataType = IPConstants.SOURCE;
    for (final AmdSecType amdSecType : metsWrapper.getMets().getAmdSec()) {
      for (final MdSecType mdSecType : amdSecType.getSourceMD()) {
        final MdRef mdRef = mdSecType.getMdRef();

        processMdRef(mdRef, metadataType, ip, representation, basePath);
      }
    }
  }

  protected void processRightsMetadata(final MetsWrapper metsWrapper, final IPInterface ip,
                                       final IPRepresentation representation, final Path basePath) throws IPException {
    final String metadataType = IPConstants.RIGHTS;
    for (final AmdSecType amdSecType : metsWrapper.getMets().getAmdSec()) {
      for (final MdSecType mdSecType : amdSecType.getRightsMD()) {
        final MdRef mdRef = mdSecType.getMdRef();

        processMdRef(mdRef, metadataType, ip, representation, basePath);
      }
    }
  }

  private void processMdRef(final MdRef mdRef, final String metadataType, final IPInterface ip,
                            final IPRepresentation representation, final Path basePath) throws IPException {
    if (mdRef != null) {
      final String href = Utils.extractedRelativePathFromHref(mdRef);
      final Path filePath = basePath.resolve(href);
      if (Files.exists(filePath)) {
        final List<String> fileRelativeFolders = Utils
            .getFileRelativeFolders(basePath.resolve(IPConstants.METADATA).resolve(metadataType), filePath);

        final Optional<IPFileInterface> metadataFile = validateMetadataFile(ip, filePath, mdRef, fileRelativeFolders);
        if (metadataFile.isPresent()) {
          ValidationUtils.addInfo(ip.getValidationReport(),
              ValidationConstants.getMetadataFileFoundWithMatchingChecksumString(metadataType), ip.getBasePath(),
              filePath);
          final IPMetadata ipMetadata = new IPMetadata(metadataFile.get());
          ipMetadata.setCreateDate(mdRef.getCREATED());
          ipMetadata.setMetadataType(MetadataType.MetadataTypeEnum.valueOf(mdRef.getMDTYPE()));
          ipMetadata.setId(mdRef.getID());
          addMetadata(ip, representation, ipMetadata, metadataType);
        }
      } else {
        ValidationUtils.addIssue(ip.getValidationReport(),
            ValidationConstants.getMetadataFileNotFoundString(metadataType), ValidationEntry.LEVEL.ERROR,
            ip.getBasePath(), filePath);
      }
    }
  }

  private void addMetadata(final IPInterface ip, final IPRepresentation representation, final IPMetadata metadata,
                           final String metadataType) throws IPException {
    if (representation == null) {
      switch (metadataType) {
        case IPConstants.PRESERVATION:
          ip.addPreservationMetadata(metadata);
          break;
        case IPConstants.RIGHTS:
          ip.addRightsMetadata(metadata);
          break;
        case IPConstants.TECHNICAL:
          ip.addTechnicalMetadata(metadata);
          break;
        case IPConstants.SOURCE:
          ip.addSourceMetadata(metadata);
          break;
        case IPConstants.OTHER:
          ip.addOtherMetadata(metadata);
          break;
        default:
          throw new IPException(UNKNOWN_METADATA_TYPE_PREFIX + metadataType);
      }
    } else {
      switch (metadataType) {
        case IPConstants.PRESERVATION:
          representation.addPreservationMetadata(metadata);
          break;
        case IPConstants.RIGHTS:
          representation.addRightsMetadata(metadata);
          break;
        case IPConstants.TECHNICAL:
          representation.addTechnicalMetadata(metadata);
          break;
        case IPConstants.SOURCE:
          representation.addSourceMetadata(metadata);
          break;
        case IPConstants.OTHER:
          representation.addOtherMetadata(metadata);
          break;
        default:
          throw new IPException(UNKNOWN_METADATA_TYPE_PREFIX + metadataType);
      }
    }
  }

  protected void processMetadata(final IPInterface ip, final Logger logger, final IPRepresentation representation,
                                 final DivType div, final String metadataType, final Path basePath) throws IPException {
    if (div != null) {
      final List<Object> objects;
      if (IPConstants.DESCRIPTIVE.equals(metadataType) || IPConstants.OTHER.equals(metadataType)) {
        objects = div.getDMDID();
      } else if (IPConstants.PRESERVATION.equals(metadataType) || IPConstants.RIGHTS.equals(metadataType)
          || IPConstants.TECHNICAL.equals(metadataType) || IPConstants.SOURCE.equals(metadataType)) {
        objects = div.getADMID();
      } else {
        objects = null;
      }

      if (objects != null) {
        for (final Object obj : objects) {
          if (obj instanceof MdSecType mdSecType) {
            final MdRef mdRef = mdSecType.getMdRef();
            if (mdRef != null) {
              final String href = Utils.extractedRelativePathFromHref(mdRef);
              final Path filePath = basePath.resolve(href);
              if (Files.exists(filePath)) {
                final List<String> fileRelativeFolders = Utils
                    .getFileRelativeFolders(basePath.resolve(IPConstants.METADATA).resolve(metadataType), filePath);

                processMetadataFile(ip, logger, representation, metadataType, mdRef, filePath, fileRelativeFolders);
              } else {
                ValidationUtils.addIssue(ip.getValidationReport(),
                    ValidationConstants.getMetadataFileNotFoundString(metadataType), ValidationEntry.LEVEL.ERROR,
                    ip.getBasePath(), filePath);
              }
            }
          }
        }
      }
    }
  }

  protected void processMetadataFile(final IPInterface ip, final Logger logger, final IPRepresentation representation,
                                     final String metadataType, final MdRef mdRef, final Path filePath,
                                     final List<String> fileRelativeFolders) throws IPException {
    final Optional<IPFileInterface> metadataFile = validateMetadataFile(ip, filePath, mdRef, fileRelativeFolders);
    if (metadataFile.isPresent()) {
      ValidationUtils.addInfo(ip.getValidationReport(),
          ValidationConstants.getMetadataFileFoundWithMatchingChecksumString(metadataType), ip.getBasePath(), filePath);

      if (IPConstants.DESCRIPTIVE.equalsIgnoreCase(metadataType)) {
        final MetadataType dmdType = new MetadataType(mdRef.getMDTYPE().toUpperCase());
        String dmdVersion = null;
        try {
          dmdVersion = mdRef.getMDTYPEVERSION();
          if (StringUtils.isNotBlank(mdRef.getOTHERMDTYPE())) {
            dmdType.setOtherType(mdRef.getOTHERMDTYPE());
          }
          logger.debug(METADATA_TYPE_VALID_MSG, dmdType);
        } catch (final NullPointerException | IllegalArgumentException e) {
          // do nothing and use already defined values for metadataType &
          // metadataVersion
          logger.debug(SETTING_METADATA_TYPE_MSG, dmdType);
          ValidationUtils.addEntry(ip.getValidationReport(), ValidationConstants.UNKNOWN_DESCRIPTIVE_METADATA_TYPE,
              ValidationEntry.LEVEL.WARN, SETTING_METADATA_TYPE_PREFIX + dmdType, ip.getBasePath(), filePath);
        }

        final IPDescriptiveMetadata descriptiveMetadata = new IPDescriptiveMetadata(mdRef.getID(),
            metadataFile.get(), dmdType, dmdVersion);
        descriptiveMetadata.setCreateDate(mdRef.getCREATED());
        if (representation == null) {
          ip.addDescriptiveMetadata(descriptiveMetadata);
        } else {
          representation.addDescriptiveMetadata(descriptiveMetadata);
        }
      } else if (IPConstants.PRESERVATION.equalsIgnoreCase(metadataType)) {
        final IPMetadata preservationMetadata = new IPMetadata(metadataFile.get());
        preservationMetadata.setCreateDate(mdRef.getCREATED());
        preservationMetadata.setMetadataType(MetadataType.MetadataTypeEnum.valueOf(mdRef.getMDTYPE()));
        preservationMetadata.setId(mdRef.getID());
        if (representation == null) {
          ip.addPreservationMetadata(preservationMetadata);
        } else {
          representation.addPreservationMetadata(preservationMetadata);
        }
      } else if (IPConstants.RIGHTS.equalsIgnoreCase(metadataType)) {
        final IPMetadata rightsMetadata = new IPMetadata(metadataFile.get());
        rightsMetadata.setCreateDate(mdRef.getCREATED());
        rightsMetadata.setMetadataType(MetadataType.MetadataTypeEnum.valueOf(mdRef.getMDTYPE()));
        rightsMetadata.setId(mdRef.getID());
        if (representation == null) {
          ip.addRightsMetadata(rightsMetadata);
        } else {
          representation.addRightsMetadata(rightsMetadata);
        }
      } else if (IPConstants.OTHER.equalsIgnoreCase(metadataType)) {
        final IPMetadata otherMetadata = new IPMetadata(metadataFile.get());
        otherMetadata.setCreateDate(mdRef.getCREATED());
        if (representation == null) {
          ip.addOtherMetadata(otherMetadata);
        } else {
          representation.addOtherMetadata(otherMetadata);
        }
      }
    }
  }

  protected Optional<IPFileInterface> validateFile(final IPInterface ip, final Path filePath, final FileType fileType,
                                                   final List<String> fileRelativeFolders) {
    return Utils.validateFile(ip, filePath, fileRelativeFolders, fileType.getCHECKSUM(), fileType.getCHECKSUMTYPE(),
        fileType.getID());
  }

  protected Optional<IPFileInterface> validateMetadataFile(final IPInterface ip, final Path filePath,
                                                           final MdRef mdRef, final List<String> fileRelativeFolders) {
    return Utils.validateFile(ip, filePath, fileRelativeFolders, mdRef.getCHECKSUM(), mdRef.getCHECKSUMTYPE(),
        mdRef.getID());
  }

  protected IPInterface processFile(final IPInterface ip, final DivType div, final String folder,
                                    final Path basePath) {
    if (div != null && div.getFptr() != null) {
      for (final Fptr fptr : div.getFptr()) {
        final Object object = fptr.getFILEID();
        if (object instanceof FileGrpType fileGrp) {
          for (final FileType fileType : fileGrp.getFile()) {
            if (fileType.getFLocat() != null) {
              final FLocat fLocat = fileType.getFLocat().get(0);
              final String href = Utils.extractedRelativePathFromHref(fLocat.getHref());
              final Path filePath = basePath.resolve(href);

              if (Files.exists(filePath)) {
                final List<String> fileRelativeFolders = Utils.getFileRelativeFolders(basePath.resolve(folder),
                    filePath);
                final Optional<IPFileInterface> file = validateFile(ip, filePath, fileType, fileRelativeFolders);

                if (file.isPresent()) {
                  if (IPConstants.SCHEMAS.equalsIgnoreCase(folder)) {
                    ValidationUtils.addInfo(ip.getValidationReport(),
                        ValidationConstants.SCHEMA_FILE_FOUND_WITH_MATCHING_CHECKSUMS, ip.getBasePath(), filePath);
                    ip.addSchema(file.get());
                  } else if (IPConstants.DOCUMENTATION.equalsIgnoreCase(folder)) {
                    ValidationUtils.addInfo(ip.getValidationReport(),
                        ValidationConstants.DOCUMENTATION_FILE_FOUND_WITH_MATCHING_CHECKSUMS, ip.getBasePath(),
                        filePath);
                    ip.addDocumentation(file.get());
                  } else if (IPConstants.SUBMISSION.equalsIgnoreCase(folder) && ip instanceof AIP aip) {
                    ValidationUtils.addInfo(ip.getValidationReport(),
                        ValidationConstants.SUBMISSION_FILE_FOUND_WITH_MATCHING_CHECKSUMS, ip.getBasePath(), filePath);
                    aip.addSubmission(file.get());
                  }
                }
              } else {
                if (IPConstants.SCHEMAS.equalsIgnoreCase(folder)) {
                  ValidationUtils.addIssue(ip.getValidationReport(), ValidationConstants.SCHEMA_FILE_NOT_FOUND,
                      ValidationEntry.LEVEL.ERROR, div, ip.getBasePath(), filePath);
                } else if (IPConstants.DOCUMENTATION.equalsIgnoreCase(folder)) {
                  ValidationUtils.addIssue(ip.getValidationReport(), ValidationConstants.DOCUMENTATION_FILE_NOT_FOUND,
                      ValidationEntry.LEVEL.ERROR, div, ip.getBasePath(), filePath);
                } else if (IPConstants.SUBMISSION.equalsIgnoreCase(folder)) {
                  ValidationUtils.addIssue(ip.getValidationReport(), ValidationConstants.SUBMISSION_FILE_NOT_FOUND,
                      ValidationEntry.LEVEL.ERROR, div, ip.getBasePath(), filePath);
                }
              }
            }
          }
        }
      }
    }

    return ip;
  }

  protected void processRepresentationAgents(final MetsWrapper representationMetsWrapper,
                                             final IPRepresentation representation) {
    addAgentsToMETS(representationMetsWrapper.getMets(), null, representation);
  }

  protected void processRepresentationFiles(final IPInterface ip, final MetsWrapper representationMetsWrapper,
                                            final IPRepresentation representation, final Path representationBasePath)
      throws IPException {

    if (representationMetsWrapper.getDataDiv() != null && representationMetsWrapper.getDataDiv().getFptr() != null) {
      for (final Fptr fptr : representationMetsWrapper.getDataDiv().getFptr()) {
        final Object object = fptr.getFILEID();
        if (object instanceof FileGrpType fileGrp) {
          for (final FileType fileType : fileGrp.getFile()) {
            if (fileType != null && fileType.getFLocat() != null) {
              final FLocat fLocat = fileType.getFLocat().get(0);
              final String href = Utils.extractedRelativePathFromHref(fLocat.getHref());
              final Path filePath = representationBasePath.resolve(href);

              // Verify that when protocol is file:/// the file is inside the SIP or not
              if (filePath.startsWith(representationBasePath)) {
                // treat as a SIP (generic behaviour)
                if (Files.exists(filePath)) {
                  final List<String> fileRelativeFolders = Utils
                      .getFileRelativeFolders(representationBasePath.resolve(IPConstants.DATA), filePath);
                  final Optional<IPFileInterface> file = validateFile(ip, filePath, fileType, fileRelativeFolders);

                  if (file.isPresent()) {
                    representation.addFile(file.get());
                    ValidationUtils.addInfo(ip.getValidationReport(),
                        ValidationConstants.REPRESENTATION_FILE_FOUND_WITH_MATCHING_CHECKSUMS, ip.getBasePath(),
                        filePath);
                  }
                } else {
                  // treat as a SIP shallow
                  final Optional<IPFileInterface> ipFileInterface = validateFileShallow(ip, fLocat, filePath, fileType,
                      Collections.emptyList());
                  ipFileInterface.ifPresent(representation::addFile);
                }
              } else {
                // treat as a SIP shallow
                final Optional<IPFileInterface> ipFileInterface = validateFileShallow(ip, fLocat, filePath, fileType,
                    Collections.emptyList());
                ipFileInterface.ifPresent(representation::addFile);
              }
            } else {
              ValidationUtils.addIssue(ip.getValidationReport(), ValidationConstants.REPRESENTATION_FILE_HAS_NO_FLOCAT,
                  ValidationEntry.LEVEL.ERROR, fileType, ip.getBasePath(), representationMetsWrapper.getMetsPath());
            }
          }
        }
      }

      for (final DivType subDiv : representationMetsWrapper.getDataDiv().getDiv()) {
        final List<String> subDivRelativePath = new ArrayList<>();
        subDivRelativePath.add(subDiv.getLABEL());
        processRepresentationFilesSubDivs(ip, representationMetsWrapper, representation, representationBasePath, subDiv,
            subDivRelativePath);
      }

      // post-process validations
      if (representation.getData().isEmpty()) {
        ValidationUtils.addIssue(ip.getValidationReport(), ValidationConstants.REPRESENTATION_HAS_NO_FILES,
            ValidationEntry.LEVEL.WARN, representationMetsWrapper.getDataDiv(), ip.getBasePath(),
            representationMetsWrapper.getMetsPath());
      }
    }
  }

  protected void processRepresentationFilesSubDivs(final IPInterface ip, final MetsWrapper representationMetsWrapper,
                                                   final IPRepresentation representation,
                                                   final Path representationBasePath, final DivType div,
                                                   final List<String> relativePath) throws IPException {

    final List<Fptr> fptrs = div.getFptr();
    if (fptrs != null && !fptrs.isEmpty()) {
      for (final Fptr fptr : fptrs) {
        final Object object = fptr.getFILEID();
        if (object instanceof FileGrpType fileGrp) {
          for (FileType fileType : fileGrp.getFile()) {
            if (fileType != null && fileType.getFLocat() != null) {
              final FLocat fLocat = fileType.getFLocat().get(0);
              final String href = Utils.extractedRelativePathFromHref(fLocat.getHref());
              final Path filePath = representationBasePath.resolve(href);

              // Verify that when protocol is file:/// the file is inside the SIP or not
              if (filePath.startsWith(representationBasePath)) {
                // treat as a SIP (generic behaviour)
                if (Files.exists(filePath)) {
                  final List<String> fileRelativeFolders = Utils
                      .getFileRelativeFolders(representationBasePath.resolve(IPConstants.DATA), filePath);
                  final Optional<IPFileInterface> file = validateFile(ip, filePath, fileType, fileRelativeFolders);

                  if (file.isPresent()) {
                    representation.addFile(file.get());
                    ValidationUtils.addInfo(ip.getValidationReport(),
                        ValidationConstants.REPRESENTATION_FILE_FOUND_WITH_MATCHING_CHECKSUMS, ip.getBasePath(),
                        filePath);
                  }
                } else {
                  // treat as a SIP shallow
                  final Optional<IPFileInterface> ipFileInterface = validateFileShallow(ip, fLocat, filePath, fileType,
                      relativePath);
                  ipFileInterface.ifPresent(representation::addFile);
                }
              } else {
                // treat as a SIP shallow
                final Optional<IPFileInterface> ipFileInterface = validateFileShallow(ip, fLocat, filePath, fileType,
                    relativePath);
                ipFileInterface.ifPresent(representation::addFile);
              }
            } else {
              ValidationUtils.addIssue(ip.getValidationReport(), ValidationConstants.REPRESENTATION_FILE_HAS_NO_FLOCAT,
                  ValidationEntry.LEVEL.ERROR, fileType, ip.getBasePath(), representationMetsWrapper.getMetsPath());
            }
          }
        }
      }
    } else if (div.getDiv().isEmpty()) {
      // This is a empty folder, add an empty folder representation in form of a
      // IPFileShallow
      representation.addFile(IPFileShallow.createEmptyFolder(relativePath));
    }

    for (final DivType subDiv : div.getDiv()) {
      final List<String> subDivRelativePath = new ArrayList<>(relativePath);
      subDivRelativePath.add(subDiv.getLABEL());
      processRepresentationFilesSubDivs(ip, representationMetsWrapper, representation, representationBasePath, subDiv,
          subDivRelativePath);
    }

  }

  private Optional<IPFileInterface> validateFileShallow(final IPInterface ip, final FLocat fLocat,
                                                        final Path filePath, final FileType fileType,
                                                        final List<String> relativeFolders) {
    final Optional<IPFileInterface> file;

    if (URI.create(fLocat.getHref()).getScheme() != null) {
      file = Optional.of(new IPFileShallow(URI.create(fLocat.getHref()), fileType, relativeFolders));
    } else {
      ValidationUtils.addIssue(ip.getValidationReport(), ValidationConstants.REPRESENTATION_SCHEME_NOT_FOUND,
          ValidationEntry.LEVEL.ERROR, ip.getBasePath(), filePath);
      file = Optional.empty();
    }

    return file;
  }

  protected IPInterface processSchemasMetadata(final MetsWrapper metsWrapper, final IPInterface ip,
                                               final Path basePath) {
    return processFile(ip, metsWrapper.getSchemasDiv(), IPConstants.SCHEMAS, basePath);
  }

  protected IPInterface processDocumentationMetadata(final MetsWrapper metsWrapper, final IPInterface ip,
                                                     final Path basePath) {
    return processFile(ip, metsWrapper.getDocumentationDiv(), IPConstants.DOCUMENTATION, basePath);
  }

  protected IPInterface processAncestors(final MetsWrapper metsWrapper, final IPInterface ip) {
    final Mets mets = metsWrapper.getMets();

    if (mets.getStructMap() != null && !mets.getStructMap().isEmpty()) {
      ip.setAncestors(metsGenerator.extractAncestorsFromStructMap(mets));
    }

    return ip;
  }

  protected IPInterface processSubmissionMetadata(final MetsWrapper metsWrapper, final IPInterface ip,
                                                  final Path basePath) {
    return processFile(ip, metsWrapper.getSubmissionsDiv(), IPConstants.SUBMISSION, basePath);
  }

}
