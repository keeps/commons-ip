/**
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE file at the root of the source
 * tree and available online at
 *
 * https://github.com/keeps/commons-ip
 */
package org.roda_project.commons_ip.parse.impl.eark;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.bind.JAXBException;

import org.apache.commons.lang3.StringUtils;
import org.roda_project.commons_ip.mets_v1_11.beans.DivType;
import org.roda_project.commons_ip.mets_v1_11.beans.DivType.Fptr;
import org.roda_project.commons_ip.mets_v1_11.beans.DivType.Mptr;
import org.roda_project.commons_ip.mets_v1_11.beans.FileType;
import org.roda_project.commons_ip.mets_v1_11.beans.FileType.FLocat;
import org.roda_project.commons_ip.mets_v1_11.beans.MdSecType.MdRef;
import org.roda_project.commons_ip.mets_v1_11.beans.Mets;
import org.roda_project.commons_ip.mets_v1_11.beans.MetsType.MetsHdr.Agent;
import org.roda_project.commons_ip.mets_v1_11.beans.StructMapType;
import org.roda_project.commons_ip.model.IPConstants;
import org.roda_project.commons_ip.model.IPDescriptiveMetadata;
import org.roda_project.commons_ip.model.IPFile;
import org.roda_project.commons_ip.model.IPMetadata;
import org.roda_project.commons_ip.model.IPRepresentation;
import org.roda_project.commons_ip.model.MetsWrapper;
import org.roda_project.commons_ip.model.ParseException;
import org.roda_project.commons_ip.model.SIP;
import org.roda_project.commons_ip.model.impl.eark.EARKMETSUtils;
import org.roda_project.commons_ip.model.impl.eark.EARKSIP;
import org.roda_project.commons_ip.parse.Parser;
import org.roda_project.commons_ip.utils.METSEnums.MetadataType;
import org.roda_project.commons_ip.utils.SIPException;
import org.roda_project.commons_ip.utils.Utils;
import org.roda_project.commons_ip.utils.ZIPUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EARKParser implements Parser {
  private static final Logger LOGGER = LoggerFactory.getLogger(EARKParser.class);

  @Override
  public SIP parse(Path source) throws ParseException {
    try {
      return parseEARKSIP(source);
    } catch (JAXBException | IOException | SIPException e) {
      throw new ParseException("Error parsing E-ARK SIP", e);
    }
  }

  private SIP parseEARKSIP(final Path source) throws JAXBException, IOException, SIPException, ParseException {
    Path sipPath = extractSIPIfInZipFormat(source);

    Path mainMETSFile = sipPath.resolve(IPConstants.METS_FILE);
    Mets mainMets = EARKMETSUtils.instantiateMETSFromFile(mainMETSFile);

    SIP sip = instantiateSIP(sipPath, mainMets);

    StructMapType structMap = getEARKStructMap(mainMets);

    MetsWrapper metsWrapper = preProcessStructMap(mainMets, structMap);

    processDescriptiveMetadata(metsWrapper, sip, null, sip.getBasePath());

    processOtherMetadata(metsWrapper, sip, null, sip.getBasePath());

    processPreservationMetadata(metsWrapper, sip, null, sip.getBasePath());

    processRepresentations(metsWrapper, sip, sip.getBasePath());

    processSchemasMetadata(metsWrapper, sip, sip.getBasePath());

    processDocumentationMetadata(metsWrapper, sip, sip.getBasePath());

    processParentId(metsWrapper, sip);

    return sip;
  }

  private Path extractSIPIfInZipFormat(final Path source) {
    Path sipPath = source;
    if (!Files.isDirectory(source)) {
      try {
        Path uncompressed = Files.createTempDirectory("unzipped");
        ZIPUtils.unzip(source, uncompressed);
        sipPath = uncompressed;
      } catch (IOException e) {
        LOGGER.error("Error unzipping file", e);
      }
    }

    return sipPath;
  }

  private SIP instantiateSIP(Path sipPath, Mets mets) throws SIPException {
    SIP sip = new EARKSIP(mets.getOBJID());
    addAgentsToMETS(mets, sip, null);
    sip.setBasePath(sipPath);
    return sip;
  }

  private Mets addAgentsToMETS(Mets mets, SIP sip, IPRepresentation representation) {
    if (mets.getMetsHdr() != null && mets.getMetsHdr().getAgent() != null) {
      for (Agent agent : mets.getMetsHdr().getAgent()) {
        if (representation == null) {
          sip.addAgent(EARKMETSUtils.createIPAgent(agent));
        } else {
          representation.addAgent(EARKMETSUtils.createIPAgent(agent));
        }
      }
    }

    return mets;
  }

  private StructMapType getEARKStructMap(Mets mets) throws ParseException {
    StructMapType res = null;
    for (StructMapType structMap : mets.getStructMap()) {
      if (StringUtils.equals(structMap.getLABEL(), IPConstants.E_ARK_STRUCTURAL_MAP)) {
        res = structMap;
        break;
      }
    }
    if (res == null) {
      throw new ParseException("Cannot find EARK structural map");
    }
    return res;
  }

  private MetsWrapper preProcessStructMap(Mets mets, StructMapType structMap) throws SIPException {
    MetsWrapper metsWrapper = new MetsWrapper(mets);

    DivType sipDiv = structMap.getDiv();
    if (sipDiv.getDiv() != null) {
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

    return metsWrapper;
  }

  private SIP processDescriptiveMetadata(MetsWrapper metsWrapper, SIP sip, IPRepresentation representation,
    Path basePath) throws SIPException {

    return processMetadata(sip, representation, metsWrapper.getDescriptiveMetadataDiv(), IPConstants.DESCRIPTIVE,
      basePath);
  }

  private SIP processOtherMetadata(MetsWrapper metsWrapper, SIP sip, IPRepresentation representation, Path basePath)
    throws SIPException {

    return processMetadata(sip, representation, metsWrapper.getOtherMetadataDiv(), IPConstants.OTHER, basePath);
  }

  private SIP processPreservationMetadata(MetsWrapper metsWrapper, SIP sip, IPRepresentation representation,
    Path basePath) throws SIPException {

    return processMetadata(sip, representation, metsWrapper.getPreservationMetadataDiv(), IPConstants.PRESERVATION,
      basePath);
  }

  private SIP processMetadata(SIP sip, IPRepresentation representation, DivType div, String metadataType, Path basePath)
    throws SIPException {
    if (div != null && div.getFptr() != null) {
      for (Fptr fptr : div.getFptr()) {
        MdRef mdRef = (MdRef) fptr.getFILEID();
        String href = Utils.extractedRelativePathFromHref(mdRef);
        Path filePath = basePath.resolve(href);
        List<String> fileRelativeFolders = getFileRelativeFolders(
          basePath.resolve(IPConstants.METADATA).resolve(metadataType), filePath);

        if (IPConstants.DESCRIPTIVE.equalsIgnoreCase(metadataType)) {
          MetadataType dmdType = MetadataType.OTHER;
          String dmdVersion = null;
          try {
            dmdType = MetadataType.valueOf(mdRef.getMDTYPE().toUpperCase());
            dmdVersion = mdRef.getMDTYPEVERSION();
            LOGGER.debug("Metadata type valid: " + dmdType.toString());
          } catch (NullPointerException | IllegalArgumentException e) {
            // do nothing and use already defined values for metadataType &
            // metadataVersion
            LOGGER.debug("Setting metadata type to {}", dmdType);
          }

          IPDescriptiveMetadata descriptiveMetadata = new IPDescriptiveMetadata(
            new IPFile(filePath, fileRelativeFolders), dmdType, dmdVersion);
          if (representation == null) {
            sip.addDescriptiveMetadata(descriptiveMetadata);
          } else {
            representation.addDescriptiveMetadata(descriptiveMetadata);
          }
        } else if (IPConstants.PRESERVATION.equalsIgnoreCase(metadataType)) {
          IPMetadata preservationMetadata = new IPMetadata(new IPFile(filePath, fileRelativeFolders));
          if (representation == null) {
            sip.addPreservationMetadata(preservationMetadata);
          } else {
            representation.addPreservationMetadata(preservationMetadata);
          }
        } else if (IPConstants.OTHER.equalsIgnoreCase(metadataType)) {
          IPMetadata otherMetadata = new IPMetadata(new IPFile(filePath, fileRelativeFolders));
          if (representation == null) {
            sip.addOtherMetadata(otherMetadata);
          } else {
            representation.addOtherMetadata(otherMetadata);
          }
        }
      }
    }

    return sip;
  }

  private SIP processFile(SIP sip, DivType div, String folder, Path basePath) throws SIPException {
    if (div != null && div.getFptr() != null) {
      for (Fptr fptr : div.getFptr()) {
        FileType fileType = (FileType) fptr.getFILEID();

        if (fileType.getFLocat() != null) {
          FLocat fLocat = fileType.getFLocat().get(0);
          String href = Utils.extractedRelativePathFromHref(fLocat.getHref());
          Path filePath = basePath.resolve(href);
          List<String> fileRelativeFolders = getFileRelativeFolders(basePath.resolve(folder), filePath);

          if (IPConstants.SCHEMAS.equalsIgnoreCase(folder)) {
            sip.addSchema(new IPFile(filePath, fileRelativeFolders));
          } else if (IPConstants.DOCUMENTATION.equalsIgnoreCase(folder)) {
            sip.addDocumentation(new IPFile(filePath, fileRelativeFolders));
          }
        }
      }
    }

    return sip;
  }

  private SIP processRepresentations(MetsWrapper metsWrapper, SIP sip, Path basePath)
    throws JAXBException, SIPException, ParseException {

    if (metsWrapper.getRepresentationsDiv() != null && metsWrapper.getRepresentationsDiv().getDiv() != null) {
      for (DivType representationDiv : metsWrapper.getRepresentationsDiv().getDiv()) {
        if (representationDiv.getMptr() != null) {
          // we can assume one and only one mets for each representation div
          Mptr mptr = representationDiv.getMptr().get(0);
          String href = Utils.extractedRelativePathFromHref(mptr.getHref());
          Path metsFilePath = basePath.resolve(href);
          Mets representationMets = EARKMETSUtils.instantiateMETSFromFile(metsFilePath);
          Path representationBasePath = metsFilePath.getParent();

          StructMapType representationStructMap = getEARKStructMap(representationMets);
          MetsWrapper representationMETSWrapper = preProcessStructMap(representationMets, representationStructMap);

          IPRepresentation representation = new IPRepresentation(representationDiv.getLABEL());
          sip.addRepresentation(representation);

          // process representation agents
          processRepresentationAgents(representationMETSWrapper, representation);

          // process files
          processRepresentationFiles(representationMETSWrapper, representation, representationBasePath);

          // process descriptive metadata
          processDescriptiveMetadata(representationMETSWrapper, sip, representation, representationBasePath);

          // process preservation metadata
          processPreservationMetadata(representationMETSWrapper, sip, representation, representationBasePath);

          // process other metadata
          processOtherMetadata(representationMETSWrapper, sip, representation, representationBasePath);

          // process schemas
          processSchemasMetadata(representationMETSWrapper, sip, representationBasePath);

          // process documentation
          processDocumentationMetadata(representationMETSWrapper, sip, representationBasePath);

        }
      }
    }

    return sip;

  }

  private void processRepresentationAgents(MetsWrapper representationMETSWrapper, IPRepresentation representation) {

    addAgentsToMETS(representationMETSWrapper.getMets(), null, representation);
  }

  private void processRepresentationFiles(MetsWrapper representationMETSWrapper, IPRepresentation representation,
    Path representationBasePath) throws SIPException {

    if (representationMETSWrapper.getDataDiv() != null && representationMETSWrapper.getDataDiv().getFptr() != null) {
      for (Fptr fptr : representationMETSWrapper.getDataDiv().getFptr()) {
        FileType fileType = (FileType) fptr.getFILEID();

        if (fileType.getFLocat() != null) {
          FLocat fLocat = fileType.getFLocat().get(0);
          String href = Utils.extractedRelativePathFromHref(fLocat.getHref());
          Path filePath = representationBasePath.resolve(href);
          List<String> fileRelativeFolders = getFileRelativeFolders(representationBasePath.resolve(IPConstants.DATA),
            filePath);

          representation.addFile(new IPFile(filePath, fileRelativeFolders));
        }
      }
    }

  }

  private SIP processSchemasMetadata(MetsWrapper metsWrapper, SIP sip, Path basePath) throws SIPException {

    return processFile(sip, metsWrapper.getSchemasDiv(), IPConstants.SCHEMAS, basePath);
  }

  private SIP processDocumentationMetadata(MetsWrapper metsWrapper, SIP sip, Path basePath) throws SIPException {

    return processFile(sip, metsWrapper.getDocumentationDiv(), IPConstants.DOCUMENTATION, basePath);
  }

  private SIP processParentId(MetsWrapper metsWrapper, SIP sip) {
    Mets mets = metsWrapper.getMets();
    if (mets.getStructMap() != null && !mets.getStructMap().isEmpty()) {
      String parentID = EARKMETSUtils.extractParentIDFromStructMap(mets);
      if (parentID != null) {
        sip.setParent(parentID);
      }
    }

    return sip;
  }

  private List<String> getFileRelativeFolders(Path basePath, Path filePath) {
    List<String> res = new ArrayList<>();
    Path relativize = basePath.relativize(filePath).getParent();
    if (relativize != null) {
      Iterator<Path> iterator = relativize.iterator();
      while (iterator.hasNext()) {
        res.add(iterator.next().toString());
      }
    }
    return res;
  }

}
