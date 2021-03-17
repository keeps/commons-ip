/**
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE file at the root of the source
 * tree and available online at
 *
 * https://github.com/keeps/commons-ip
 */
package org.roda_project.commons_ip2.model.impl.eark;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.xml.bind.JAXBException;
import javax.xml.transform.stream.StreamSource;

import org.apache.commons.lang3.StringUtils;
import org.roda_project.commons_ip.model.ParseException;
import org.roda_project.commons_ip.utils.IPEnums;
import org.roda_project.commons_ip.utils.IPEnums.IPStatus;
import org.roda_project.commons_ip.utils.IPException;
import org.roda_project.commons_ip.utils.ValidationConstants;
import org.roda_project.commons_ip.utils.ZipEntryInfo;
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
import org.roda_project.commons_ip2.mets_v1_12.beans.StructMapType;
import org.roda_project.commons_ip2.model.AIP;
import org.roda_project.commons_ip2.model.IPConstants;
import org.roda_project.commons_ip2.model.IPContentType;
import org.roda_project.commons_ip2.model.IPDescriptiveMetadata;
import org.roda_project.commons_ip2.model.IPFile;
import org.roda_project.commons_ip2.model.IPHeader;
import org.roda_project.commons_ip2.model.IPInterface;
import org.roda_project.commons_ip2.model.IPMetadata;
import org.roda_project.commons_ip2.model.IPRepresentation;
//import org.roda_project.commons_ip2.model.IPRepresentation;
import org.roda_project.commons_ip2.model.MetadataType;
import org.roda_project.commons_ip2.model.MetsWrapper;
import org.roda_project.commons_ip2.model.RepresentationStatus;
import org.roda_project.commons_ip2.model.SIP;
import org.roda_project.commons_ip2.model.ValidationEntry;
import org.roda_project.commons_ip2.model.ValidationEntry.LEVEL;
import org.roda_project.commons_ip2.model.ValidationEntry.TYPE;
import org.roda_project.commons_ip2.model.impl.IPConfig;
import org.roda_project.commons_ip2.model.impl.ModelUtils;
import org.roda_project.commons_ip2.utils.METSUtils;
import org.roda_project.commons_ip2.utils.Utils;
import org.roda_project.commons_ip2.utils.ValidationUtils;
import org.roda_project.commons_ip2.utils.ZIPUtils;
import org.slf4j.Logger;
import org.xml.sax.SAXException;

import com.helger.schematron.pure.SchematronResourcePure;
import com.helger.schematron.svrl.jaxb.FailedAssert;
import com.helger.schematron.svrl.jaxb.SchematronOutputType;

public final class EARKUtils {
  protected static boolean VALIDATION_FAIL_IF_REPRESENTATION_METS_DOES_NOT_HAVE_TWO_PARTS = false;

  private EARKUtils() {
    // do nothing
  }

  protected static void addDescriptiveMetadataToZipAndMETS(Map<String, ZipEntryInfo> zipEntries,
    MetsWrapper metsWrapper, List<IPDescriptiveMetadata> descriptiveMetadata, String representationId,
    IPConfig ipConfig) throws IPException, InterruptedException {
    if (descriptiveMetadata != null && !descriptiveMetadata.isEmpty()) {
      for (IPDescriptiveMetadata dm : descriptiveMetadata) {
        if (Thread.interrupted()) {
          throw new InterruptedException();
        }
        IPFile file = dm.getMetadata();

        String descriptiveFilePath = IPConstants.DESCRIPTIVE_FOLDER
          + ModelUtils.getFoldersFromList(file.getRelativeFolders()) + file.getFileName();
        MdRef mdRef = EARKMETSUtils.addDescriptiveMetadataToMETS(metsWrapper, dm, descriptiveFilePath, ipConfig);

        if (representationId != null) {
          descriptiveFilePath = IPConstants.REPRESENTATIONS_FOLDER + representationId + IPConstants.ZIP_PATH_SEPARATOR
            + descriptiveFilePath;
        }
        ZIPUtils.addMdRefFileToZip(zipEntries, file.getPath(), descriptiveFilePath, mdRef);
      }
    }
  }

  protected static void addPreservationMetadataToZipAndMETS(Map<String, ZipEntryInfo> zipEntries,
    MetsWrapper metsWrapper, List<IPMetadata> preservationMetadata, String representationId, IPConfig ipConfig)
    throws IPException, InterruptedException {
    if (preservationMetadata != null && !preservationMetadata.isEmpty()) {
      for (IPMetadata pm : preservationMetadata) {
        if (Thread.interrupted()) {
          throw new InterruptedException();
        }
        IPFile file = pm.getMetadata();

        String preservationMetadataPath = IPConstants.PRESERVATION_FOLDER
          + ModelUtils.getFoldersFromList(file.getRelativeFolders()) + file.getFileName();
        MdRef mdRef = EARKMETSUtils.addPreservationMetadataToMETS(metsWrapper, pm, preservationMetadataPath, ipConfig);

        if (representationId != null) {
          preservationMetadataPath = IPConstants.REPRESENTATIONS_FOLDER + representationId
            + IPConstants.ZIP_PATH_SEPARATOR + preservationMetadataPath;
        }
        ZIPUtils.addMdRefFileToZip(zipEntries, file.getPath(), preservationMetadataPath, mdRef);
      }
    }
  }

  protected static void addOtherMetadataToZipAndMETS(Map<String, ZipEntryInfo> zipEntries, MetsWrapper metsWrapper,
    List<IPMetadata> otherMetadata, String representationId, IPConfig ipConfig)
    throws IPException, InterruptedException {
    if (otherMetadata != null && !otherMetadata.isEmpty()) {
      for (IPMetadata om : otherMetadata) {
        if (Thread.interrupted()) {
          throw new InterruptedException();
        }
        IPFile file = om.getMetadata();

        String otherMetadataPath = IPConstants.OTHER_FOLDER + ModelUtils.getFoldersFromList(file.getRelativeFolders())
          + file.getFileName();
        MdRef mdRef = EARKMETSUtils.addOtherMetadataToMETS(metsWrapper, om, otherMetadataPath, ipConfig);

        if (representationId != null) {
          otherMetadataPath = IPConstants.REPRESENTATIONS_FOLDER + representationId + IPConstants.ZIP_PATH_SEPARATOR
            + otherMetadataPath;
        }
        ZIPUtils.addMdRefFileToZip(zipEntries, file.getPath(), otherMetadataPath, mdRef);
      }
    }
  }

  protected static void addRepresentationsToZipAndMETS(IPInterface ip, List<IPRepresentation> representations,
    Map<String, ZipEntryInfo> zipEntries, MetsWrapper mainMETSWrapper, Path buildDir)
    throws IPException, InterruptedException {
    // representations
    if (representations != null && !representations.isEmpty()) {
      if (ip instanceof SIP) {
        ((SIP) ip).notifySipBuildRepresentationsProcessingStarted(representations.size());
      }
      for (IPRepresentation representation : representations) {
        if (Thread.interrupted()) {
          throw new InterruptedException();
        }
        String representationId = representation.getObjectID();
        // 20160407 hsilva: not being used by Common Specification v0.13

        IPHeader header = new IPHeader(IPEnums.IPStatus.NEW).setAgents(representation.getAgents());
        MetsWrapper representationMETSWrapper = EARKMETSUtils.generateMETS(representationId,
          representation.getDescription(), ip.getProfile(), false, Optional.empty(), null, header,
          mainMETSWrapper.getMets().getMetsHdr().getOAISPACKAGETYPE(), representation.getContentType(),
          representation.getContentInformationType(), ip.getIPConfig());
        representationMETSWrapper.getMainDiv().setTYPE(representation.getStatus().asString());

        // representation data
        addRepresentationDataFilesToZipAndMETS(ip, zipEntries, representationMETSWrapper, representation,
          representationId);

        // representation descriptive metadata
        addDescriptiveMetadataToZipAndMETS(zipEntries, representationMETSWrapper,
          representation.getDescriptiveMetadata(), representationId, ip.getIPConfig());

        // representation preservation metadata
        addPreservationMetadataToZipAndMETS(zipEntries, representationMETSWrapper,
          representation.getPreservationMetadata(), representationId, ip.getIPConfig());

        // representation other metadata
        addOtherMetadataToZipAndMETS(zipEntries, representationMETSWrapper, representation.getOtherMetadata(),
          representationId, ip.getIPConfig());

        // representation schemas
        addSchemasToZipAndMETS(zipEntries, representationMETSWrapper, representation.getSchemas(), representationId,
          ip.getIPConfig());

        // representation documentation
        addDocumentationToZipAndMETS(zipEntries, representationMETSWrapper, representation.getDocumentation(),
          representationId, ip.getIPConfig());

        // add representation METS to Zip file and to main METS file
        EARKMETSUtils.addRepresentationMETSToZipAndToMainMETS(zipEntries, mainMETSWrapper, representationId,
          representationMETSWrapper, IPConstants.REPRESENTATIONS_FOLDER + representationId
            + IPConstants.ZIP_PATH_SEPARATOR + IPConstants.METS_FILE,
          buildDir, ip.getIPConfig());
      }
      if (ip instanceof SIP) {
        ((SIP) ip).notifySipBuildRepresentationsProcessingEnded();
      }
    }
  }

  protected static void addRepresentationDataFilesToZipAndMETS(IPInterface ip, Map<String, ZipEntryInfo> zipEntries,
    MetsWrapper representationMETSWrapper, IPRepresentation representation, String representationId)
    throws IPException, InterruptedException {
    if (representation.getData() != null && !representation.getData().isEmpty()) {
      if (ip instanceof SIP) {
        ((SIP) ip).notifySipBuildRepresentationProcessingStarted(representation.getData().size());
      }
      int i = 0;
      for (IPFile file : representation.getData()) {
        if (Thread.interrupted()) {
          throw new InterruptedException();
        }

        String dataFilePath = IPConstants.DATA_FOLDER + ModelUtils.getFoldersFromList(file.getRelativeFolders())
          + file.getFileName();
        FileType fileType = EARKMETSUtils.addDataFileToMETS(representationMETSWrapper, dataFilePath, file.getPath(),
          ip.getIPConfig());

        dataFilePath = IPConstants.REPRESENTATIONS_FOLDER + representationId + IPConstants.ZIP_PATH_SEPARATOR
          + dataFilePath;
        ZIPUtils.addFileTypeFileToZip(zipEntries, file.getPath(), dataFilePath, fileType);

        i++;
        if (ip instanceof SIP) {
          ((SIP) ip).notifySipBuildRepresentationProcessingCurrentStatus(i);
        }
      }
      if (ip instanceof SIP) {
        ((SIP) ip).notifySipBuildRepresentationProcessingEnded();
      }
    }
  }

  protected static void addSchemasToZipAndMETS(Map<String, ZipEntryInfo> zipEntries, MetsWrapper metsWrapper,
    List<IPFile> schemas, String representationId, IPConfig ipConfig) throws IPException, InterruptedException {
    if (schemas != null && !schemas.isEmpty()) {
      for (IPFile schema : schemas) {
        if (Thread.interrupted()) {
          throw new InterruptedException();
        }

        String schemaFilePath = IPConstants.SCHEMAS_FOLDER + ModelUtils.getFoldersFromList(schema.getRelativeFolders())
          + schema.getFileName();
        FileType fileType = EARKMETSUtils.addSchemaFileToMETS(metsWrapper, schemaFilePath, schema.getPath(), ipConfig);

        if (representationId != null) {
          schemaFilePath = IPConstants.REPRESENTATIONS_FOLDER + representationId + IPConstants.ZIP_PATH_SEPARATOR
            + schemaFilePath;
        }
        ZIPUtils.addFileTypeFileToZip(zipEntries, schema.getPath(), schemaFilePath, fileType);
      }
    }
  }

  protected static void addDocumentationToZipAndMETS(Map<String, ZipEntryInfo> zipEntries, MetsWrapper metsWrapper,
    List<IPFile> documentation, String representationId, IPConfig ipConfig) throws IPException, InterruptedException {
    if (documentation != null && !documentation.isEmpty()) {
      for (IPFile doc : documentation) {
        if (Thread.interrupted()) {
          throw new InterruptedException();
        }

        String documentationFilePath = IPConstants.DOCUMENTATION_FOLDER
          + ModelUtils.getFoldersFromList(doc.getRelativeFolders()) + doc.getFileName();
        FileType fileType = EARKMETSUtils.addDocumentationFileToMETS(metsWrapper, documentationFilePath, doc.getPath(),
          ipConfig);

        if (representationId != null) {
          documentationFilePath = IPConstants.REPRESENTATIONS_FOLDER + representationId + IPConstants.ZIP_PATH_SEPARATOR
            + documentationFilePath;
        }
        ZIPUtils.addFileTypeFileToZip(zipEntries, doc.getPath(), documentationFilePath, fileType);
      }
    }
  }

  protected static void addDefaultSchemas(Logger logger, List<IPFile> schemas, Path buildDir)
    throws InterruptedException {
    try {
      if (Thread.interrupted()) {
        throw new InterruptedException();
      }
      Path earkCsipSchema = Utils.copyResourceFromClasspathToDir(EARKSIP.class, buildDir,
        IPConstants.SCHEMA_EARK_CSIP_FILENAME, IPConstants.SCHEMA_EARK_CSIP_RELATIVE_PATH_FROM_RESOURCES);
      schemas.add(new IPFile(earkCsipSchema, IPConstants.SCHEMA_EARK_CSIP_FILENAME));
      Path earkSipSchema = Utils.copyResourceFromClasspathToDir(EARKSIP.class, buildDir,
        IPConstants.SCHEMA_EARK_SIP_FILENAME, IPConstants.SCHEMA_EARK_SIP_RELATIVE_PATH_FROM_RESOURCES);
      schemas.add(new IPFile(earkSipSchema, IPConstants.SCHEMA_EARK_SIP_FILENAME));
      Path metsSchema = Utils.copyResourceFromClasspathToDir(EARKSIP.class, buildDir,
        IPConstants.SCHEMA_METS_FILENAME_WITH_VERSION, IPConstants.SCHEMA_METS_RELATIVE_PATH_FROM_RESOURCES);
      schemas.add(new IPFile(metsSchema, IPConstants.SCHEMA_METS_FILENAME_WITH_VERSION));
      Path xlinkSchema = Utils.copyResourceFromClasspathToDir(EARKSIP.class, buildDir,
        IPConstants.SCHEMA_XLINK_FILENAME, IPConstants.SCHEMA_XLINK_RELATIVE_PATH_FROM_RESOURCES);
      schemas.add(new IPFile(xlinkSchema, IPConstants.SCHEMA_XLINK_FILENAME));
    } catch (IOException e) {
      logger.error("Error while trying to add default schemas", e);
    }
  }

  protected static void addSubmissionsToZipAndMETS(final Map<String, ZipEntryInfo> zipEntries,
    final MetsWrapper metsWrapper, final List<IPFile> submissions, IPConfig ipConfig)
    throws IPException, InterruptedException {
    if (submissions != null && !submissions.isEmpty()) {
      for (IPFile submission : submissions) {
        if (Thread.interrupted()) {
          throw new InterruptedException();
        }
        final String submissionFilePath = IPConstants.SUBMISSION_FOLDER
          + ModelUtils.getFoldersFromList(submission.getRelativeFolders()) + submission.getFileName();
        final FileType fileType = EARKMETSUtils.addSubmissionFileToMETS(metsWrapper, submissionFilePath,
          submission.getPath(), ipConfig);
        ZIPUtils.addFileTypeFileToZip(zipEntries, submission.getPath(), submissionFilePath, fileType);
      }
    }
  }

  protected static MetsWrapper processMainMets(IPInterface ip, Path ipPath) {
    Path mainMETSFile = ipPath.resolve(IPConstants.METS_FILE);
    Mets mainMets = null;
    if (Files.exists(mainMETSFile)) {
      ValidationUtils.addInfo(ip.getValidationReport(), ValidationConstants.MAIN_METS_FILE_FOUND, ipPath, mainMETSFile);
      ValidationUtils.addInfo(ip.getValidationReport(), ValidationConstants.CSIPSTR4, ipPath, mainMETSFile);
      try {
        mainMets = METSUtils.instantiateMETSFromFile(mainMETSFile);
        if (!validMetsAttributes(ip, ipPath, mainMETSFile, mainMets)) {
          return null;
        }
        if (!validMetsHdr(ip, ipPath, mainMETSFile, mainMets)) {
          return null;
        }

        ip.setIds(Arrays.asList(mainMets.getOBJID().split(" ")));
        ip.setCreateDate(mainMets.getMetsHdr().getCREATEDATE());
        ip.setModificationDate(mainMets.getMetsHdr().getLASTMODDATE());
        ip.setStatus(IPStatus.parse(mainMets.getMetsHdr().getRECORDSTATUS()));
        setIPContentType(mainMets, ip);
        addAgentsToMETS(mainMets, ip, null);

        ValidationUtils.addInfo(ip.getValidationReport(), ValidationConstants.MAIN_METS_IS_VALID, ipPath, mainMETSFile);

        ValidationUtils.addIssueIfFalse(ip.getValidationReport(), ValidationConstants.CSIPSTR2,
          ipPath.getFileName().toString().equals(mainMets.getOBJID()),
          ip.getIPConfig().isStrictMode() ? LEVEL.ERROR : LEVEL.WARN, ip.getBasePath(), mainMETSFile);

        if (ip.getIPConfig().isSchematronValidation()) {
          try {
            validateXMLViaPureSchematron(ip, "mets_root_rules.xml", mainMETSFile);
            validateXMLViaPureSchematron(ip, "mets_hdr_rules.xml", mainMETSFile);
            validateXMLViaPureSchematron(ip, "mets_amd_rules.xml", mainMETSFile);
            validateXMLViaPureSchematron(ip, "mets_dmd_rules.xml", mainMETSFile);
            validateXMLViaPureSchematron(ip, "mets_file_rules.xml", mainMETSFile);
            validateXMLViaPureSchematron(ip, "mets_structmap_rules.xml", mainMETSFile);
          } catch (Exception e) {
            // FIXME
            e.printStackTrace();
          }
        }

      } catch (JAXBException | ParseException | SAXException e) {
        mainMets = null;
        ValidationUtils.addIssue(ip.getValidationReport(), ValidationConstants.MAIN_METS_NOT_VALID, LEVEL.ERROR, e,
          ip.getBasePath(), mainMETSFile);
      }
    } else {
      ValidationUtils.addIssue(ip.getValidationReport(), ValidationConstants.MAIN_METS_FILE_NOT_FOUND, LEVEL.ERROR,
        ip.getBasePath(), mainMETSFile);
      ValidationUtils.addIssue(ip.getValidationReport(), ValidationConstants.CSIPSTR4, LEVEL.ERROR, ip.getBasePath(),
        mainMETSFile);
    }
    return new MetsWrapper(mainMets, mainMETSFile);
  }

  private static boolean validMetsHdr(IPInterface ip, Path ipPath, Path metsPath, Mets mets) {
    MetsHdr metsHdr = mets.getMetsHdr();
    if (metsHdr == null) {
      ValidationUtils.addIssue(ip.getValidationReport(), ValidationConstants.CSIP117, LEVEL.ERROR, ip.getBasePath(),
        metsPath);
      return false;
    } else {
      ValidationUtils.addInfo(ip.getValidationReport(), ValidationConstants.CSIP117, ipPath, metsPath);

      // mets/metsHdr/@CREATEDATE
      ValidationUtils.addIssueIfNull(ip.getValidationReport(), ValidationConstants.CSIP7, metsHdr.getCREATEDATE(),
        LEVEL.ERROR, ip.getBasePath(), metsPath);

      // mets/metsHdr/@LASTMODDATE
      ValidationUtils.addIssueIfNull(ip.getValidationReport(), ValidationConstants.CSIP8, metsHdr.getLASTMODDATE(),
        LEVEL.WARN, ip.getBasePath(), metsPath);

      // mets/metsHdr/@csip:OAISPACKAGETYPE
      if (metsHdr.getOAISPACKAGETYPE() == null || "".equals(metsHdr.getOAISPACKAGETYPE())) {
        ValidationUtils.addIssue(ip.getValidationReport(), ValidationConstants.CSIP9, LEVEL.WARN, ip.getBasePath(),
          metsPath);
      } else {
        ValidationUtils.addInfo(ip.getValidationReport(), ValidationConstants.CSIP9, ipPath, metsPath);
      }

      // mets/metsHdr/agent
      List<Agent> agents = metsHdr.getAgent();
      if (agents == null || agents.isEmpty()) {
        ValidationUtils.addIssue(ip.getValidationReport(), ValidationConstants.CSIP10, LEVEL.ERROR, ip.getBasePath(),
          metsPath);
      } else {
        ValidationUtils.addInfo(ip.getValidationReport(), ValidationConstants.CSIP10, ipPath, metsPath);
        boolean foundSoftwareAgent = false;
        for (Agent agent : agents) {
          if ("CREATOR".equals(agent.getROLE()) && "OTHER".equals(agent.getTYPE())
            && "SOFTWARE".equals(agent.getOTHERTYPE())) {
            ValidationUtils.addInfo(ip.getValidationReport(), ValidationConstants.CSIP11, ipPath, metsPath);
            ValidationUtils.addInfo(ip.getValidationReport(), ValidationConstants.CSIP12, ipPath, metsPath);
            ValidationUtils.addInfo(ip.getValidationReport(), ValidationConstants.CSIP13, ipPath, metsPath);

            // mets/metsHdr/agent/name
            if (agent.getName() == null || "".equals(agent.getName())) {
              ValidationUtils.addIssue(ip.getValidationReport(), ValidationConstants.CSIP14, LEVEL.ERROR,
                ip.getBasePath(), metsPath);
            } else {
              ValidationUtils.addInfo(ip.getValidationReport(), ValidationConstants.CSIP14, ipPath, metsPath);
            }

            // mets/metsHdr/agent/note
            if (agent.getNote() == null || agent.getNote().size() != 1) {
              ValidationUtils.addIssue(ip.getValidationReport(), ValidationConstants.CSIP15, LEVEL.ERROR,
                ip.getBasePath(), metsPath);
            } else {
              ValidationUtils.addInfo(ip.getValidationReport(), ValidationConstants.CSIP15, ipPath, metsPath);

              // mets/metsHdr/agent/note[@csip:NOTETYPE='SOFTWARE VERSION']
              if ("SOFTWARE VERSION".equals(agent.getNote().get(0).getNOTETYPE())) {
                ValidationUtils.addInfo(ip.getValidationReport(), ValidationConstants.CSIP16, ipPath, metsPath);
              } else {
                ValidationUtils.addIssue(ip.getValidationReport(), ValidationConstants.CSIP16, LEVEL.ERROR,
                  ip.getBasePath(), metsPath);
              }
            }

            foundSoftwareAgent = true;
            break;
          }
        }
        if (!foundSoftwareAgent) {
          ValidationUtils.addIssue(ip.getValidationReport(), ValidationConstants.CSIP11, LEVEL.ERROR, ip.getBasePath(),
            metsPath);
          ValidationUtils.addIssue(ip.getValidationReport(), ValidationConstants.CSIP12, LEVEL.ERROR, ip.getBasePath(),
            metsPath);
          ValidationUtils.addIssue(ip.getValidationReport(), ValidationConstants.CSIP13, LEVEL.ERROR, ip.getBasePath(),
            metsPath);
        }
      }
    }

    return ip.getValidationReport().isValid();
  }

  private static boolean validMetsAttributes(IPInterface ip, Path ipPath, Path metsPath, Mets mets) {
    // mets/@OBJID
    if (mets.getOBJID() == null || "".equals(mets.getOBJID())) {
      ValidationUtils.addIssue(ip.getValidationReport(), ValidationConstants.CSIP1, LEVEL.ERROR, ip.getBasePath(),
        metsPath);
    } else {
      ValidationUtils.addInfo(ip.getValidationReport(), ValidationConstants.CSIP1, ipPath, metsPath);
    }

    // mets/@TYPE
    if (mets.getTYPE() == null || "".equals(mets.getTYPE())) {
      ValidationUtils.addIssue(ip.getValidationReport(), ValidationConstants.CSIP2, LEVEL.ERROR, ip.getBasePath(),
        metsPath);
    } else {
      ValidationUtils.addInfo(ip.getValidationReport(), ValidationConstants.CSIP2, ipPath, metsPath);

      // mets[@TYPE='OTHER']/@csip:OTHERTYPE
      if ("OTHER".equalsIgnoreCase(mets.getTYPE())) {
        if (mets.getOTHERTYPE() == null || "".equals(mets.getOTHERTYPE())) {
          ValidationUtils.addIssue(ip.getValidationReport(), ValidationConstants.CSIP3, LEVEL.ERROR, ip.getBasePath(),
            metsPath);
        } else {
          ValidationUtils.addInfo(ip.getValidationReport(), ValidationConstants.CSIP3, ipPath, metsPath);
        }
      }
    }

    // mets/@csip:CONTENTINFORMATIONTYPE
    if (mets.getCONTENTINFORMATIONTYPE() == null || "".equals(mets.getCONTENTINFORMATIONTYPE())) {
      ValidationUtils.addIssue(ip.getValidationReport(), ValidationConstants.CSIP4, LEVEL.WARN, ip.getBasePath(),
        metsPath);
    } else {
      ValidationUtils.addInfo(ip.getValidationReport(), ValidationConstants.CSIP4, ipPath, metsPath);

      // mets[@csip:CONTENTINFORMATIONTYPE='OTHER']/@csip:OTHERCONTENTINFORMATIONTYPE
      if ("OTHER".equalsIgnoreCase(mets.getCONTENTINFORMATIONTYPE())) {
        if (mets.getOTHERCONTENTINFORMATIONTYPE() == null || "".equals(mets.getOTHERCONTENTINFORMATIONTYPE())) {
          ValidationUtils.addIssue(ip.getValidationReport(), ValidationConstants.CSIP5, LEVEL.ERROR, ip.getBasePath(),
            metsPath);
        } else {
          ValidationUtils.addInfo(ip.getValidationReport(), ValidationConstants.CSIP5, ipPath, metsPath);
        }
      }
    }

    // mets/@PROFILE
    if (ip instanceof EARKSIP) {
      if (IPConstants.EARK_SIP_SPEC_PROFILE.equalsIgnoreCase(mets.getPROFILE())) {
        ValidationUtils.addInfo(ip.getValidationReport(), ValidationConstants.CSIP6, ipPath, metsPath);
      } else {
        // one last try to see if it is at least CSIP profile
        if (IPConstants.COMMON_SPEC_PROFILE.equalsIgnoreCase(mets.getPROFILE())) {
          ValidationUtils.addEntry(ip.getValidationReport(), ValidationConstants.CSIP6, LEVEL.WARN,
            "Was expecting '" + IPConstants.EARK_SIP_SPEC_PROFILE + "' but found '" + mets.getPROFILE() + "'",
            ip.getBasePath(), metsPath);
        } else {
          ValidationUtils.addEntry(ip.getValidationReport(), ValidationConstants.CSIP6, LEVEL.ERROR,
            "Was expecting '" + IPConstants.EARK_SIP_SPEC_PROFILE + "' but found '" + mets.getPROFILE() + "'",
            ip.getBasePath(), metsPath);
        }
      }
    } else {
      ValidationUtils.addIssue(ip.getValidationReport(), ValidationConstants.CSIP6, LEVEL.ERROR, ip.getBasePath(),
        metsPath);
    }

    return ip.getValidationReport().isValid();
  }

  private static void validateXMLViaPureSchematron(IPInterface ip, String schematronFile, Path xmlFile) {
    try {

      final SchematronResourcePure schematronResource = SchematronResourcePure
        .fromInputStream(EARKUtils.class.getResourceAsStream(IPConstants.SCHEMATRON_FOLDER + schematronFile));
      // final SchematronResourceSCH schematronResource = SchematronResourceSCH
      // .fromClassPath(IPConstants.SCHEMATRON_FOLDER + schematronFile);

      if (!schematronResource.isValidSchematron())
        throw new IllegalArgumentException("Invalid Schematron!");

      SchematronOutputType schematronValidation = schematronResource
        .applySchematronValidationToSVRL(new StreamSource(xmlFile.toFile()));

      if (schematronValidation != null) {
        for (final Object aObj : schematronValidation.getActivePatternAndFiredRuleAndFailedAssert()) {
          if (aObj instanceof FailedAssert) {
            FailedAssert failedAssert = (FailedAssert) aObj;
            String message = "";

            for (Object object : failedAssert.getText().getContent()) {
              message += object.toString() + " ";
            }

            ValidationUtils.addIssue(ip.getValidationReport(),
              new ValidationEntry(TYPE.SCHEMATRON, failedAssert.getId(), message),
              ("ERROR".equals(failedAssert.getRole())) ? LEVEL.ERROR : LEVEL.WARN, ip.getBasePath(), xmlFile);
          }
        }
      }

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  protected static void setIPContentType(Mets mets, IPInterface ip) throws ParseException {
    String oaisPackageType = mets.getMetsHdr().getOAISPACKAGETYPE();

    if (StringUtils.isBlank(oaisPackageType)) {
      throw new ParseException("METS 'OAISPACKAGETYPE' attribute does not contain any value");
    }

    try {
      IPEnums.IPType packageType = IPEnums.IPType.valueOf(oaisPackageType);

      if (ip instanceof SIP && IPEnums.IPType.SIP != packageType) {
        throw new ParseException(
          "METS 'OAISPACKAGETYPE' should have the value 'SIP' but it doesn't. Instead, it has '" + packageType + "'.");
      } else if (ip instanceof AIP && IPEnums.IPType.AIP != packageType) {
        throw new ParseException(
          "METS 'OAISPACKAGETYPE' should have the value 'AIP' but it doesn't. Instead, it has '" + packageType + "'.");
      }
    } catch (IllegalArgumentException e) {
      throw new ParseException("METS 'OAISPACKAGETYPE' attribute does not contain a valid package type");
    }

    String type = "".equals(mets.getOTHERCONTENTINFORMATIONTYPE()) ? mets.getCONTENTINFORMATIONTYPE()
      : mets.getOTHERCONTENTINFORMATIONTYPE();
    ip.setContentType(new IPContentType(type));
  }

  protected static Mets addAgentsToMETS(Mets mets, IPInterface ip, IPRepresentation representation) {
    if (mets.getMetsHdr() != null && mets.getMetsHdr().getAgent() != null) {
      for (Agent agent : mets.getMetsHdr().getAgent()) {
        if (representation == null) {
          ip.addAgent(EARKMETSUtils.createIPAgent(ip, agent));
        } else {
          representation.addAgent(EARKMETSUtils.createIPAgent(ip, agent));
        }
      }
    }

    return mets;
  }

  protected static MetsWrapper processRepresentationMets(IPInterface ip, Path representationMetsFile,
    IPRepresentation representation) {
    Path representationFolder = representationMetsFile.getParent();
    ValidationUtils.addIssueIfStringNotEqual(ip.getValidationReport(), ValidationConstants.CSIPSTR10,
      representationFolder.getFileName().toString(), representation.getRepresentationID(), LEVEL.ERROR,
      ip.getBasePath(), representationMetsFile.getParent());

    Path representationDataFolder = representationFolder.resolve(IPConstants.DATA);
    ValidationUtils.addIssueIfFalse(ip.getValidationReport(), ValidationConstants.CSIPSTR11,
      Files.isDirectory(representationDataFolder), LEVEL.WARN, ip.getBasePath(), representationDataFolder);

    Path representationMetadataFolder = representationFolder.resolve(IPConstants.METADATA);
    ValidationUtils.addIssueIfFalse(ip.getValidationReport(), ValidationConstants.CSIPSTR13,
      Files.isDirectory(representationMetadataFolder), LEVEL.WARN, ip.getBasePath(), representationMetadataFolder);

    Mets representationMets = null;
    if (Files.exists(representationMetsFile)) {
      ValidationUtils.addInfo(ip.getValidationReport(), ValidationConstants.REPRESENTATION_METS_FILE_FOUND,
        ip.getBasePath(), representationMetsFile);
      ValidationUtils.addInfo(ip.getValidationReport(), ValidationConstants.CSIPSTR12, ip.getBasePath(),
        representationMetsFile);

      try {
        representationMets = METSUtils.instantiateMETSFromFile(representationMetsFile);
        setRepresentationContentType(representationMets, representation);
        ValidationUtils.addInfo(ip.getValidationReport(), ValidationConstants.REPRESENTATION_METS_IS_VALID,
          ip.getBasePath(), representationMetsFile);
      } catch (JAXBException | ParseException | SAXException e) {
        representationMets = null;
        ValidationUtils.addIssue(ip.getValidationReport(), ValidationConstants.REPRESENTATION_METS_NOT_VALID,
          LEVEL.ERROR, e, ip.getBasePath(), representationMetsFile);
      }
    } else {
      ValidationUtils.addIssue(ip.getValidationReport(), ValidationConstants.REPRESENTATION_METS_FILE_NOT_FOUND,
        LEVEL.ERROR, ip.getBasePath(), representationMetsFile);
      ValidationUtils.addIssue(ip.getValidationReport(), ValidationConstants.CSIPSTR12, LEVEL.ERROR, ip.getBasePath(),
        representationDataFolder);
    }
    return new MetsWrapper(representationMets, representationMetsFile);
  }

  // FIXME review this
  protected static void setRepresentationContentType(Mets mets, IPRepresentation representation) throws ParseException {
    String contentType = mets.getCONTENTINFORMATIONTYPE();
    if (StringUtils.isBlank(contentType)) {
      throw new ParseException("METS 'CONTENTINFORMATIONTYPE' attribute does not contain any value");
    }

    if (!"".equals(mets.getOTHERCONTENTINFORMATIONTYPE())) {
      contentType = mets.getOTHERCONTENTINFORMATIONTYPE();
    }

    representation.setContentType(new IPContentType(contentType));
  }

  protected static IPInterface processRepresentations(MetsWrapper metsWrapper, IPInterface ip, Logger logger)
    throws IPException {

    ValidationUtils.addIssueIfFalse(ip.getValidationReport(), ValidationConstants.CSIPSTR9,
      Files.isDirectory(ip.getBasePath().resolve(IPConstants.REPRESENTATIONS)), LEVEL.WARN, ip.getBasePath(),
      ip.getBasePath().resolve(IPConstants.REPRESENTATIONS));

    if (metsWrapper.getMainDiv() != null && metsWrapper.getMainDiv().getDiv() != null) {
      for (DivType div : metsWrapper.getMainDiv().getDiv()) {
        if (div.getLABEL().startsWith(IPConstants.REPRESENTATIONS_WITH_FIRST_LETTER_CAPITAL)) {
          if (div.getMptr() != null && !div.getMptr().isEmpty()) {
            // we can assume one and only one mets for each representation div
            Mptr mptr = div.getMptr().get(0);
            String href = Utils.extractedRelativePathFromHref(mptr.getHref(), ip.getIPConfig().isEncodeDecodeHref());
            Path metsFilePath = ip.getBasePath().resolve(href);
            IPRepresentation representation = new IPRepresentation(metsFilePath.getParent().getFileName().toString());

            MetsWrapper representationMetsWrapper = processRepresentationMets(ip, metsFilePath, representation);

            if (representationMetsWrapper.getMets() != null) {
              Path representationBasePath = metsFilePath.getParent();

              StructMapType representationStructMap = getEARKStructMap(representationMetsWrapper, ip, false);
              if (representationStructMap != null) {

                preProcessStructMap(representationMetsWrapper, representationStructMap);
                preProcessFileSec(representationMetsWrapper, ip);

                representation.setStatus(new RepresentationStatus(representationMetsWrapper.getMainDiv().getTYPE()));
                ip.addRepresentation(representation);

                // process representation agents
                processRepresentationAgents(representationMetsWrapper, representation);

                // process files
                processRepresentationFiles(ip, representationMetsWrapper, representation, representationBasePath);

                // process descriptive metadata
                processDescriptiveMetadata(representationMetsWrapper, ip, logger, representation,
                  representationBasePath);

                // process preservation metadata
                processPreservationMetadata(representationMetsWrapper, ip, logger, representation,
                  representationBasePath);

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
      }

      // post-process validations
      if (ip.getRepresentations().isEmpty()) {
        ValidationUtils.addIssue(ip.getValidationReport(), ValidationConstants.MAIN_METS_NO_REPRESENTATIONS_FOUND,
          LEVEL.WARN, metsWrapper.getMainDiv(), ip.getBasePath(), metsWrapper.getMetsPath());
      }
    }

    return ip;

  }

  protected static StructMapType getEARKStructMap(MetsWrapper metsWrapper, IPInterface ip, boolean mainMets) {
    Mets mets = metsWrapper.getMets();
    StructMapType res = null;
    for (StructMapType structMap : mets.getStructMap()) {
      if (StringUtils.equals(structMap.getLABEL(), IPConstants.COMMON_SPEC_STRUCTURAL_MAP)) {
        res = structMap;
        break;
      }
    }

    ValidationUtils.addIssueIfNull(ip.getValidationReport(),
      mainMets ? ValidationConstants.MAIN_METS_HAS_NO_E_ARK_STRUCT_MAP
        : ValidationConstants.REPRESENTATION_METS_HAS_NO_E_ARK_STRUCT_MAP,
      res, LEVEL.ERROR, ip.getBasePath(), metsWrapper.getMetsPath());
    return res;
  }

  protected static void preProcessStructMap(MetsWrapper metsWrapper, StructMapType structMap) {

    DivType ipDiv = structMap.getDiv();
    if (ipDiv.getDiv() != null) {
      metsWrapper.setMainDiv(ipDiv);
      for (DivType firstLevel : ipDiv.getDiv()) {
        if (IPConstants.METADATA.equalsIgnoreCase(firstLevel.getLABEL()) && firstLevel.getDiv() != null) {
          metsWrapper.setMetadataDiv(firstLevel);
        } else if ((IPConstants.METADATA + "/" + IPConstants.OTHER).equalsIgnoreCase(firstLevel.getLABEL())) {
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

  protected static void preProcessFileSec(MetsWrapper metsWrapper, IPInterface ip) {
    FileSec fileSec = metsWrapper.getMets().getFileSec();
    ValidationUtils.addIssueIfNull(ip.getValidationReport(), ValidationConstants.CSIP58, fileSec, LEVEL.WARN,
      ip.getBasePath(), metsWrapper.getMetsPath());

    if (fileSec != null) {
      boolean hasData = false, hasSchemas = false, hasDocumentation = false, hasRepresentations = false;
      ValidationUtils.addIssueIfNull(ip.getValidationReport(), ValidationConstants.CSIP59, fileSec, LEVEL.ERROR,
        ip.getBasePath(), metsWrapper.getMetsPath());

      for (FileGrp fileGrp : fileSec.getFileGrp()) {
        String fileGrpID = fileGrp.getID();
        ValidationUtils.addIssueIfNull(ip.getValidationReport(), ValidationConstants.CSIP65, fileGrpID, LEVEL.ERROR,
          ip.getBasePath(), metsWrapper.getMetsPath());

        String fileGrpUse = fileGrp.getUSE();
        ValidationUtils.addIssueIfNull(ip.getValidationReport(), ValidationConstants.CSIP64, fileGrpUse, LEVEL.ERROR,
          fileGrp.getID());

        if (fileGrpUse == null) {
          continue;
        }

        switch (fileGrpUse) {
          case IPConstants.DATA_WITH_FIRST_LETTER_CAPITAL:
            // metsWrapper.setDataFileGroup(fileGrp);
            hasData = true;

            break;
          case IPConstants.SCHEMAS_WITH_FIRST_LETTER_CAPITAL:
            // metsWrapper.setSchemasFileGroup(fileGrp);
            hasSchemas = true;
            break;
          case IPConstants.DOCUMENTATION_WITH_FIRST_LETTER_CAPITAL:
            // metsWrapper.setDocumentationFileGroup(fileGrp);
            hasDocumentation = true;
            break;
          case IPConstants.REPRESENTATIONS_WITH_FIRST_LETTER_CAPITAL:
            // metsWrapper.getRepresentationsFileGroups().add(fileGrp);
            hasRepresentations = true;
            break;
          default:
            break;
        }
      }
    }
  }

  protected static IPInterface processDescriptiveMetadata(MetsWrapper metsWrapper, IPInterface ip, Logger logger,
    IPRepresentation representation, Path basePath) throws IPException {

    ValidationUtils.addIssueIfEmpty(ip.getValidationReport(), ValidationConstants.CSIP17,
      metsWrapper.getMets().getDmdSec(), LEVEL.WARN, ip.getBasePath(), metsWrapper.getMetsPath());

    return processMetadata(ip, logger, metsWrapper, representation, metsWrapper.getMetadataDiv(),
      IPConstants.DESCRIPTIVE, basePath);
  }

  protected static IPInterface processOtherMetadata(MetsWrapper metsWrapper, IPInterface ip, Logger logger,
    IPRepresentation representation, Path basePath) throws IPException {

    return processMetadata(ip, logger, metsWrapper, representation, metsWrapper.getOtherMetadataDiv(),
      IPConstants.OTHER, basePath);
  }

  protected static IPInterface processPreservationMetadata(MetsWrapper metsWrapper, IPInterface ip, Logger logger,
    IPRepresentation representation, Path basePath) throws IPException {

    ValidationUtils.addIssueIfEmpty(ip.getValidationReport(), ValidationConstants.CSIP31,
      metsWrapper.getMets().getAmdSec(), LEVEL.WARN, ip.getBasePath(), metsWrapper.getMetsPath());

    return processMetadata(ip, logger, metsWrapper, representation, metsWrapper.getMetadataDiv(),
      IPConstants.PRESERVATION, basePath);
  }

  protected static IPInterface processMetadata(IPInterface ip, Logger logger, MetsWrapper representationMetsWrapper,
    IPRepresentation representation, DivType div, String metadataType, Path basePath) throws IPException {
    if (div != null) {
      List<Object> objects = null;
      ValidationEntry csipstr = null;
      ValidationEntry csip = null;
      if (IPConstants.DESCRIPTIVE.equals(metadataType)) {
        objects = div.getDMDID();
        csipstr = ValidationConstants.CSIPSTR7;
        csip = ValidationConstants.CSIP18;
      } else if (IPConstants.OTHER.equals(metadataType)) {
        objects = div.getDMDID();
        csipstr = ValidationConstants.CSIPSTR8;
      } else if (IPConstants.PRESERVATION.equals(metadataType)) {
        objects = div.getADMID();
        csipstr = ValidationConstants.CSIPSTR6;
        csip = ValidationConstants.CSIP33;
      }

      if (objects != null && !objects.isEmpty()) {
        Path folder = ip.getBasePath().resolve(IPConstants.METADATA).resolve(metadataType);
        ValidationUtils.addIssueIfFalse(ip.getValidationReport(), csipstr, Files.isDirectory(folder), LEVEL.WARN,
          ip.getBasePath(), folder);

        for (Object obj : objects) {
          if (obj instanceof MdSecType) {
            MdSecType mdSecType = (MdSecType) obj;

            // i'm here
            if (csip != null) {
              ValidationUtils.addInfo(ip.getValidationReport(), csip, mdSecType.getID());
              if (IPConstants.DESCRIPTIVE.equals(metadataType)) {
                ValidationUtils.addIssueIfNull(ip.getValidationReport(), ValidationConstants.CSIP19,
                  mdSecType.getCREATED(), LEVEL.ERROR, mdSecType.getID());
                ValidationUtils.addIssueIfNull(ip.getValidationReport(), ValidationConstants.CSIP20,
                  mdSecType.getSTATUS(), LEVEL.ERROR, mdSecType.getID());
              } else if (IPConstants.PRESERVATION.equals(metadataType)) {
                // FIXME 20210209 hsilva: by one side CSIP specification states
                // that every preservation related information should be
                // recorded as digiprovMD but it also has requirements for
                // rightsMD and none for techMD and sourceMD
                ValidationUtils.addInfo(ip.getValidationReport(), ValidationConstants.CSIP32, mdSecType.getID());
                ValidationUtils.addInfo(ip.getValidationReport(), csip, mdSecType.getID());
                ValidationUtils.addIssueIfNull(ip.getValidationReport(), ValidationConstants.CSIP34,
                  mdSecType.getSTATUS(), LEVEL.WARN, mdSecType.getID());
              }
            }

            MdRef mdRef = mdSecType.getMdRef();
            if (mdRef != null) {
              ValidationUtils.addInfo(ip.getValidationReport(),
                IPConstants.DESCRIPTIVE.equals(metadataType) ? ValidationConstants.CSIP21 : ValidationConstants.CSIP35,
                mdSecType.getID());

              ValidationUtils.addIssueIfStringNotEqual(ip.getValidationReport(),
                IPConstants.DESCRIPTIVE.equals(metadataType) ? ValidationConstants.CSIP22 : ValidationConstants.CSIP36,
                mdRef.getLOCTYPE(), "URL", LEVEL.ERROR, mdRef.getID());
              ValidationUtils.addIssueIfStringNotEqual(ip.getValidationReport(),
                IPConstants.DESCRIPTIVE.equals(metadataType) ? ValidationConstants.CSIP23 : ValidationConstants.CSIP37,
                mdRef.getType(), "simple", LEVEL.ERROR, mdRef.getID());
              ValidationUtils.addIssueIfNull(ip.getValidationReport(),
                IPConstants.DESCRIPTIVE.equals(metadataType) ? ValidationConstants.CSIP24 : ValidationConstants.CSIP38,
                mdRef.getHref(), LEVEL.ERROR, mdRef.getID());
              ValidationUtils.addIssueIfNull(ip.getValidationReport(),
                IPConstants.DESCRIPTIVE.equals(metadataType) ? ValidationConstants.CSIP26 : ValidationConstants.CSIP40,
                mdRef.getMIMETYPE(), LEVEL.ERROR, mdRef.getID());

              String href = Utils.extractedRelativePathFromHref(mdRef, ip.getIPConfig().isEncodeDecodeHref());
              Path filePath = basePath.resolve(href);
              if (Files.exists(filePath)) {
                List<String> fileRelativeFolders = Utils
                  .getFileRelativeFolders(basePath.resolve(IPConstants.METADATA).resolve(metadataType), filePath);

                processMetadataFile(ip, logger, representation, metadataType, mdRef, filePath, fileRelativeFolders);
              } else {
                ValidationUtils.addIssue(ip.getValidationReport(),
                  ValidationConstants.getMetadataFileNotFoundString(metadataType), LEVEL.ERROR, ip.getBasePath(),
                  filePath);
              }
            } else {
              ValidationUtils.addIssue(ip.getValidationReport(),
                IPConstants.DESCRIPTIVE.equals(metadataType) ? ValidationConstants.CSIP21 : ValidationConstants.CSIP35,
                LEVEL.WARN, mdSecType.getID());
            }
          }
        }
      }
    }

    return ip;
  }

  protected static void processMetadataFile(IPInterface ip, Logger logger, IPRepresentation representation,
    String metadataType, MdRef mdRef, Path filePath, List<String> fileRelativeFolders) throws IPException {
    Optional<IPFile> metadataFile = validateMetadataFile(ip, filePath, mdRef, fileRelativeFolders);
    if (metadataFile.isPresent()) {
      ValidationUtils.addInfo(ip.getValidationReport(),
        ValidationConstants.getMetadataFileFoundWithMatchingChecksumString(metadataType), ip.getBasePath(), filePath);

      if (IPConstants.DESCRIPTIVE.equalsIgnoreCase(metadataType)) {
        ValidationUtils.addInfo(ip.getValidationReport(), ValidationConstants.CSIP29, ip.getBasePath(), filePath);
        ValidationUtils.addInfo(ip.getValidationReport(), ValidationConstants.CSIP30, ip.getBasePath(), filePath);

        MetadataType dmdType = getMetadataType(ip, logger, metadataType, mdRef, filePath);

        ValidationUtils.addIssueIfNotGreatorThan(ip.getValidationReport(), ValidationConstants.CSIP27, mdRef.getSIZE(),
          0L, LEVEL.ERROR, mdRef.getID());
        ValidationUtils.addIssueIfNull(ip.getValidationReport(), ValidationConstants.CSIP28, mdRef.getCREATED(),
          LEVEL.ERROR, mdRef.getID());

        IPDescriptiveMetadata descriptiveMetadata = new IPDescriptiveMetadata(mdRef.getID(), metadataFile.get(),
          dmdType, mdRef.getMDTYPEVERSION());
        descriptiveMetadata.setCreateDate(mdRef.getCREATED());
        if (representation == null) {
          ip.addDescriptiveMetadata(descriptiveMetadata);
        } else {
          representation.addDescriptiveMetadata(descriptiveMetadata);
        }
      } else if (IPConstants.PRESERVATION.equalsIgnoreCase(metadataType)) {
        ValidationUtils.addInfo(ip.getValidationReport(), ValidationConstants.CSIP43, ip.getBasePath(), filePath);
        ValidationUtils.addInfo(ip.getValidationReport(), ValidationConstants.CSIP44, ip.getBasePath(), filePath);

        MetadataType amdType = getMetadataType(ip, logger, metadataType, mdRef, filePath);

        ValidationUtils.addIssueIfNotGreatorThan(ip.getValidationReport(), ValidationConstants.CSIP41, mdRef.getSIZE(),
          0L, LEVEL.ERROR, mdRef.getID());
        ValidationUtils.addIssueIfNull(ip.getValidationReport(), ValidationConstants.CSIP42, mdRef.getCREATED(),
          LEVEL.ERROR, mdRef.getID());

        IPMetadata preservationMetadata = new IPMetadata(metadataFile.get(), amdType);
        preservationMetadata.setCreateDate(mdRef.getCREATED());
        if (representation == null) {
          ip.addPreservationMetadata(preservationMetadata);
        } else {
          representation.addPreservationMetadata(preservationMetadata);
        }
      } else if (IPConstants.OTHER.equalsIgnoreCase(metadataType)) {
        IPMetadata otherMetadata = new IPMetadata(metadataFile.get());
        otherMetadata.setCreateDate(mdRef.getCREATED());
        if (representation == null) {
          ip.addOtherMetadata(otherMetadata);
        } else {
          representation.addOtherMetadata(otherMetadata);
        }
      }
    } else {
      if (IPConstants.DESCRIPTIVE.equalsIgnoreCase(metadataType)) {
        ValidationUtils.addIssue(ip.getValidationReport(), ValidationConstants.CSIP29, LEVEL.ERROR, ip.getBasePath(),
          filePath);
        ValidationUtils.addIssue(ip.getValidationReport(), ValidationConstants.CSIP30, LEVEL.ERROR, ip.getBasePath(),
          filePath);
      } else if (IPConstants.PRESERVATION.equalsIgnoreCase(metadataType)) {
        ValidationUtils.addIssue(ip.getValidationReport(), ValidationConstants.CSIP43, LEVEL.ERROR, ip.getBasePath(),
          filePath);
        ValidationUtils.addIssue(ip.getValidationReport(), ValidationConstants.CSIP44, LEVEL.ERROR, ip.getBasePath(),
          filePath);
      }
    }
  }

  private static MetadataType getMetadataType(IPInterface ip, Logger logger, String metadataType, MdRef mdRef,
    Path filePath) {
    MetadataType dmdType = new MetadataType(mdRef.getMDTYPE().toUpperCase());
    try {
      if (StringUtils.isNotBlank(mdRef.getOTHERMDTYPE())) {
        dmdType.setOtherType(mdRef.getOTHERMDTYPE());
      }

      logger.debug("Metadata type valid: {}", dmdType);
      ValidationUtils.addInfo(ip.getValidationReport(),
        IPConstants.DESCRIPTIVE.equalsIgnoreCase(metadataType) ? ValidationConstants.CSIP25
          : ValidationConstants.CSIP39,
        mdRef.getID());
    } catch (NullPointerException | IllegalArgumentException e) {
      logger.debug("Setting metadata type to {}", dmdType);
      ValidationUtils.addEntry(ip.getValidationReport(),
        IPConstants.DESCRIPTIVE.equalsIgnoreCase(metadataType) ? ValidationConstants.UNKNOWN_DESCRIPTIVE_METADATA_TYPE
          : ValidationConstants.UNKNOWN_PRESERVATION_METADATA_TYPE,
        IPConstants.DESCRIPTIVE.equalsIgnoreCase(metadataType) ? LEVEL.ERROR : LEVEL.WARN,
        "Setting metadata type to " + dmdType, ip.getBasePath(), filePath);
      ValidationUtils.addIssue(ip.getValidationReport(),
        IPConstants.DESCRIPTIVE.equalsIgnoreCase(metadataType) ? ValidationConstants.CSIP25
          : ValidationConstants.CSIP39,
        IPConstants.DESCRIPTIVE.equalsIgnoreCase(metadataType) ? LEVEL.ERROR : LEVEL.WARN, mdRef.getID());
    }
    return dmdType;
  }

  protected static Optional<IPFile> validateFile(IPInterface ip, Path filePath, FileType fileType,
    List<String> fileRelativeFolders) {
    return Utils.validateFile(ip, filePath, fileRelativeFolders, fileType.getCHECKSUM(), fileType.getCHECKSUMTYPE(),
      fileType.getID());
  }

  protected static Optional<IPFile> validateMetadataFile(IPInterface ip, Path filePath, MdRef mdRef,
    List<String> fileRelativeFolders) {
    return Utils.validateFile(ip, filePath, fileRelativeFolders, mdRef.getCHECKSUM(), mdRef.getCHECKSUMTYPE(),
      mdRef.getID());
  }

  protected static IPInterface processFile(IPInterface ip, DivType div, String folder, Path basePath)
    throws IPException {
    if (div != null && div.getFptr() != null) {
      for (Fptr fptr : div.getFptr()) {
        Object object = fptr.getFILEID();
        if (object instanceof FileGrpType) {
          FileGrpType fileGrp = ((FileGrpType) object);
          for (FileType fileType : fileGrp.getFile()) {
            if (fileType.getFLocat() != null) {
              FLocat fLocat = fileType.getFLocat().get(0);
              String href = Utils.extractedRelativePathFromHref(fLocat.getHref(),
                ip.getIPConfig().isEncodeDecodeHref());
              Path filePath = basePath.resolve(href);

              if (Files.exists(filePath)) {
                List<String> fileRelativeFolders = Utils.getFileRelativeFolders(basePath.resolve(folder), filePath);
                Optional<IPFile> file = validateFile(ip, filePath, fileType, fileRelativeFolders);

                if (file.isPresent()) {
                  if (IPConstants.SCHEMAS.equalsIgnoreCase(folder)) {
                    ValidationUtils.addInfo(ip.getValidationReport(),
                      ValidationConstants.SCHEMA_FILE_FOUND_WITH_MATCHING_CHECKSUMS, ip.getBasePath(), filePath);
                    ValidationUtils.addInfo(ip.getValidationReport(), ValidationConstants.CSIPSTR15, ip.getBasePath(),
                      filePath);
                    ip.addSchema(file.get());
                  } else if (IPConstants.DOCUMENTATION.equalsIgnoreCase(folder)) {
                    ValidationUtils.addInfo(ip.getValidationReport(),
                      ValidationConstants.DOCUMENTATION_FILE_FOUND_WITH_MATCHING_CHECKSUMS, ip.getBasePath(), filePath);
                    ip.addDocumentation(file.get());
                  } else if (IPConstants.SUBMISSION.equalsIgnoreCase(folder) && ip instanceof AIP) {
                    ValidationUtils.addInfo(ip.getValidationReport(),
                      ValidationConstants.SUBMISSION_FILE_FOUND_WITH_MATCHING_CHECKSUMS, ip.getBasePath(), filePath);
                    ((AIP) ip).addSubmission(file.get());
                  }
                }
              } else {
                if (IPConstants.SCHEMAS.equalsIgnoreCase(folder)) {
                  ValidationUtils.addIssue(ip.getValidationReport(), ValidationConstants.SCHEMA_FILE_NOT_FOUND,
                    LEVEL.ERROR, div, ip.getBasePath(), filePath);
                  ValidationUtils.addIssue(ip.getValidationReport(), ValidationConstants.CSIPSTR15, LEVEL.ERROR, div,
                    ip.getBasePath(), filePath);

                } else if (IPConstants.DOCUMENTATION.equalsIgnoreCase(folder)) {
                  ValidationUtils.addIssue(ip.getValidationReport(), ValidationConstants.DOCUMENTATION_FILE_NOT_FOUND,
                    LEVEL.ERROR, div, ip.getBasePath(), filePath);
                } else if (IPConstants.SUBMISSION.equalsIgnoreCase(folder)) {
                  ValidationUtils.addIssue(ip.getValidationReport(), ValidationConstants.SUBMISSION_FILE_NOT_FOUND,
                    LEVEL.ERROR, div, ip.getBasePath(), filePath);
                }
              }
            }
          }
        }

      }
    }

    return ip;
  }

  protected static void processRepresentationAgents(MetsWrapper representationMetsWrapper,
    IPRepresentation representation) {
    addAgentsToMETS(representationMetsWrapper.getMets(), null, representation);
  }

  protected static void processRepresentationFiles(IPInterface ip, MetsWrapper representationMetsWrapper,
    IPRepresentation representation, Path representationBasePath) throws IPException {

    if (representationMetsWrapper.getDataDiv() != null && representationMetsWrapper.getDataDiv().getFptr() != null) {
      for (Fptr fptr : representationMetsWrapper.getDataDiv().getFptr()) {
        Object object = fptr.getFILEID();
        if (object instanceof FileGrpType) {
          FileGrpType fileGrp = ((FileGrpType) object);
          for (FileType fileType : fileGrp.getFile()) {
            if (fileType != null && fileType.getFLocat() != null) {
              FLocat fLocat = fileType.getFLocat().get(0);
              String href = Utils.extractedRelativePathFromHref(fLocat.getHref(),
                ip.getIPConfig().isEncodeDecodeHref());
              Path filePath = representationBasePath.resolve(href);
              if (Files.exists(filePath)) {
                List<String> fileRelativeFolders = Utils
                  .getFileRelativeFolders(representationBasePath.resolve(IPConstants.DATA), filePath);
                Optional<IPFile> file = validateFile(ip, filePath, fileType, fileRelativeFolders);

                if (file.isPresent()) {
                  representation.addFile(file.get());
                  ValidationUtils.addInfo(ip.getValidationReport(),
                    ValidationConstants.REPRESENTATION_FILE_FOUND_WITH_MATCHING_CHECKSUMS, ip.getBasePath(), filePath);
                }
              } else {
                ValidationUtils.addIssue(ip.getValidationReport(), ValidationConstants.REPRESENTATION_FILE_NOT_FOUND,
                  LEVEL.ERROR, ip.getBasePath(), filePath);
              }
            } else {
              ValidationUtils.addIssue(ip.getValidationReport(), ValidationConstants.REPRESENTATION_FILE_HAS_NO_FLOCAT,
                LEVEL.ERROR, fileType, ip.getBasePath(), representationMetsWrapper.getMetsPath());
            }
          }
        }
      }

      // post-process validations
      if (representation.getData().isEmpty()) {
        ValidationUtils.addIssue(ip.getValidationReport(), ValidationConstants.REPRESENTATION_HAS_NO_FILES, LEVEL.WARN,
          representationMetsWrapper.getDataDiv(), ip.getBasePath(), representationMetsWrapper.getMetsPath());
      }
    }

  }

  protected static IPInterface processSchemasMetadata(MetsWrapper metsWrapper, IPInterface ip, Path basePath)
    throws IPException {
    return processFile(ip, metsWrapper.getSchemasDiv(), IPConstants.SCHEMAS, basePath);
  }

  protected static IPInterface processDocumentationMetadata(MetsWrapper metsWrapper, IPInterface ip, Path basePath)
    throws IPException {
    return processFile(ip, metsWrapper.getDocumentationDiv(), IPConstants.DOCUMENTATION, basePath);
  }

  protected static IPInterface processAncestors(MetsWrapper metsWrapper, IPInterface ip) {
    Mets mets = metsWrapper.getMets();

    if (mets.getStructMap() != null && !mets.getStructMap().isEmpty()) {
      ip.setAncestors(EARKMETSUtils.extractAncestorsFromStructMap(mets, ip.getIPConfig()));
    }

    return ip;
  }

  protected static IPInterface processSubmissionMetadata(final MetsWrapper metsWrapper, final IPInterface ip,
    final Path basePath) throws IPException {
    return processFile(ip, metsWrapper.getSubmissionsDiv(), IPConstants.SUBMISSION, basePath);
  }

  public static void processMetadata(MetsWrapper metsWrapper, SIP sip) {
    if (Files.isDirectory(sip.getBasePath().resolve(IPConstants.METADATA))) {
      ValidationUtils.addInfo(sip.getValidationReport(), ValidationConstants.CSIPSTR5, sip.getBasePath(),
        sip.getBasePath().resolve(IPConstants.METADATA));
    } else {
      ValidationUtils.addIssue(sip.getValidationReport(), ValidationConstants.CSIPSTR5, LEVEL.WARN, sip.getBasePath(),
        sip.getBasePath().resolve(IPConstants.METADATA));
    }
  }

  public static void processSourcePath(SIP sip, Path source, Path destinationDirectory) throws ParseException {
    if (Files.isDirectory(source)) {
      ValidationUtils.addInfo(sip.getValidationReport(), ValidationConstants.CSIPSTR1, source, null);
      sip.setBasePath(source);
    } else {
      Path sipPath = ZIPUtils.extractIPIfInZipFormat(source, destinationDirectory);
      ValidationUtils.addInfo(sip.getValidationReport(), ValidationConstants.CSIPSTR3, source, null);
      sip.setBasePath(sipPath);
    }
  }

}
