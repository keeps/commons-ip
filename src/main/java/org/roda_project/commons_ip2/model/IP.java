/**
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE file at the root of the source
 * tree and available online at
 *
 * https://github.com/keeps/commons-ip
 */
package org.roda_project.commons_ip2.model;

import java.nio.file.Path;
import java.util.*;

import javax.xml.datatype.XMLGregorianCalendar;

import org.roda_project.commons_ip.model.ParseException;
import org.roda_project.commons_ip.utils.IPEnums.IPStatus;
import org.roda_project.commons_ip.utils.IPEnums.IPType;
import org.roda_project.commons_ip.utils.IPException;
import org.roda_project.commons_ip.utils.METSEnums.CreatorType;
import org.roda_project.commons_ip.utils.ZipEntryInfo;
import org.roda_project.commons_ip2.utils.Utils;

public abstract class IP implements IPInterface {

  private List<String> ids;
  private String profile;
  private IPType type;
  private IPHeader header;

  // maps to mets/@type
  private IPContentType contentType;
  private IPContentInformationType contentInformationType;
  private List<String> ancestors;

  private Path basePath;
  private String description;

  private List<IPDescriptiveMetadata> descriptiveMetadata;
  private List<IPMetadata> preservationMetadata;
  private List<IPMetadata> otherMetadata;
  private List<String> representationIds;
  private Map<String, IPRepresentation> representations;
  private List<IPFileInterface> schemas;
  private List<IPFileInterface> documentation;

  private final Map<String, ZipEntryInfo> zipEntries;

  private ValidationReport validationReport;

  private String checksumAlgorithm;
  private boolean override;

  public IP() {
    this.setId(Utils.generateRandomAndPrefixedUUID());
    this.profile = "NOT_DEFINED";
    this.type = IPType.SIP;
    this.header = new IPHeader();

    this.contentType = IPContentType.getMIXED();
    this.contentInformationType = IPContentInformationType.getMIXED();
    this.ancestors = new ArrayList<>();

    this.description = "";
    this.checksumAlgorithm = "SHA-256";

    this.descriptiveMetadata = new ArrayList<>();
    this.preservationMetadata = new ArrayList<>();
    this.otherMetadata = new ArrayList<>();
    this.representationIds = new ArrayList<>();
    this.representations = new HashMap<>();
    this.schemas = new ArrayList<>();
    this.documentation = new ArrayList<>();

    this.validationReport = new ValidationReport();
    this.zipEntries = new LinkedHashMap<>();
  }

  public IP(final List<String> ipIds, final IPType ipType) {
    super();
    this.setIds(ipIds);
    this.type = ipType;
    this.zipEntries = new LinkedHashMap<>();
    this.header = new IPHeader();
  }

  public IP(final List<String> ipIds, final IPType ipType, final IPContentType contentType) {
    this(ipIds, ipType);
    this.contentType = contentType;
  }

  @Override
  public IP setId(final String id) {
    this.ids = Collections.singletonList(id);
    return this;
  }

  public void setChecksumAlgorithm(final String checksumAlgorithm) {
    this.checksumAlgorithm = checksumAlgorithm;
  }

  public void setOverride() {
    this.override = true;
  }

  public Boolean getOverride() {
    return override;
  }
  public String getChecksumAlgorithm() {
    return this.checksumAlgorithm;
  }

  @Override
  public String getId() {
    return ids.stream().findFirst().orElse("");
  }

  @Override
  public IP setIds(final List<String> ids) {
    this.ids = ids;
    return this;
  }

  @Override
  public List<String> getIds() {
    return ids;
  }

  @Override
  public IP setProfile(final String profile) {
    this.profile = profile;
    return this;
  }

  @Override
  public String getProfile() {
    return profile;
  }

  @Override
  public IP setType(final IPType type) {
    this.type = type;
    return this;
  }

  @Override
  public String getType() {
    return type.toString();
  }

  @Override
  public Optional<XMLGregorianCalendar> getCreateDate() {
    return header.getCreateDate();
  }

  @Override
  public IP setCreateDate(final XMLGregorianCalendar date) {
    header.setCreateDate(date);
    return this;
  }

  @Override
  public Optional<XMLGregorianCalendar> getModificationDate() {
    return header.getModificationDate();
  }

  @Override
  public IP setModificationDate(final XMLGregorianCalendar date) {
    header.setModificationDate(date);
    return this;
  }

  @Override
  public IP setContentType(final IPContentType contentType) {
    this.contentType = contentType;
    return this;
  }

  @Override
  public IPContentType getContentType() {
    return contentType;
  }

  @Override
  public IP setContentInformationType(final IPContentInformationType contentInformationType) {
    this.contentInformationType = contentInformationType;
    return this;
  }

  @Override
  public IPContentInformationType getContentInformationType() {
    return contentInformationType;
  }

  @Override
  public List<String> getAncestors() {
    return ancestors;
  }

  @Override
  public IP setAncestors(final List<String> ancestors) {
    this.ancestors = ancestors;
    return this;
  }

  @Override
  public IPStatus getStatus() {
    return header.getStatus();
  }

  @Override
  public IP setStatus(final IPStatus status) {
    this.header.setStatus(status);
    return this;
  }

  @Override
  public IP setBasePath(final Path basePath) {
    this.basePath = basePath;
    return this;
  }

  @Override
  public Path getBasePath() {
    return basePath;
  }

  @Override
  public IP setDescription(final String description) {
    this.description = description;
    return this;
  }

  @Override
  public String getDescription() {
    return description;
  }

  @Override
  public IP addAgent(final IPAgent sipAgent) {
    header.addAgent(sipAgent);
    return this;
  }

  @Override
  public IP addPreservationMetadata(final IPMetadata sipMetadata) throws IPException {
    preservationMetadata.add(sipMetadata);
    return this;
  }

  @Override
  public IP addOtherMetadata(final IPMetadata sipMetadata) throws IPException {
    otherMetadata.add(sipMetadata);
    return this;
  }

  @Override
  public IP addDescriptiveMetadata(final IPDescriptiveMetadata metadata) throws IPException {
    descriptiveMetadata.add(metadata);
    return this;
  }

  @Override
  public IP addRepresentation(final IPRepresentation sipRepresentation) throws IPException {
    final String representationId = sipRepresentation.getRepresentationID();
    if (representations.containsKey(representationId)) {
      throw new IPException("Representation already exists");
    } else {
      representationIds.add(representationId);
      representations.put(representationId, sipRepresentation);
      return this;
    }
  }

  @Override
  public IP addSchema(final IPFileInterface schema) {
    schemas.add(schema);
    return this;
  }

  @Override
  public IP addDocumentation(final IPFileInterface documentationPath) {
    documentation.add(documentationPath);
    return this;
  }

  @Override
  public IP addAgentToRepresentation(final String representationID, final IPAgent agent) throws IPException {
    checkIfRepresentationExists(representationID);
    final IPRepresentation rep = representations.get(representationID);
    rep.addAgent(agent);
    representations.put(representationID, rep);
    return this;
  }

  @Override
  public IP addDescriptiveMetadataToRepresentation(final String representationID,
    final IPDescriptiveMetadata descriptiveMetadata) throws IPException {
    checkIfRepresentationExists(representationID);
    final IPRepresentation rep = representations.get(representationID);
    rep.addDescriptiveMetadata(descriptiveMetadata);
    representations.put(representationID, rep);
    return this;
  }

  @Override
  public IP addPreservationMetadataToRepresentation(final String representationID,
    final IPMetadata preservationMetadata) throws IPException {
    checkIfRepresentationExists(representationID);
    final IPRepresentation rep = representations.get(representationID);
    rep.addPreservationMetadata(preservationMetadata);
    representations.put(representationID, rep);
    return this;
  }

  @Override
  public IP addOtherMetadataToRepresentation(final String representationID, final IPMetadata otherMetadata)
    throws IPException {
    checkIfRepresentationExists(representationID);
    final IPRepresentation rep = representations.get(representationID);
    rep.addOtherMetadata(otherMetadata);
    representations.put(representationID, rep);
    return this;
  }

  @Override
  public IP addFileToRepresentation(final String representationID, final IPFileInterface file) throws IPException {
    checkIfRepresentationExists(representationID);
    final IPRepresentation rep = representations.get(representationID);
    rep.addFile(file);
    representations.put(representationID, rep);
    return this;
  }

  @Override
  public IP addSchemaToRepresentation(final String representationID, final IPFileInterface schema) throws IPException {
    checkIfRepresentationExists(representationID);
    final IPRepresentation rep = representations.get(representationID);
    rep.addSchema(schema);
    representations.put(representationID, rep);
    return this;
  }

  @Override
  public IP addDocumentationToRepresentation(final String representationID, final IPFileInterface documentation)
    throws IPException {
    checkIfRepresentationExists(representationID);
    final IPRepresentation rep = representations.get(representationID);
    rep.addDocumentation(documentation);
    representations.put(representationID, rep);
    return this;
  }

  @Override
  public List<IPAgent> getAgents() {
    return header.getAgents();
  }

  @Override
  public List<IPDescriptiveMetadata> getDescriptiveMetadata() {
    return descriptiveMetadata;
  }

  @Override
  public List<IPMetadata> getPreservationMetadata() {
    return preservationMetadata;
  }

  @Override
  public List<IPMetadata> getOtherMetadata() {
    return otherMetadata;
  }

  @Override
  public List<IPRepresentation> getRepresentations() {
    final List<IPRepresentation> representationsList = new ArrayList<>();
    for (String representationId : representationIds) {
      representationsList.add(representations.get(representationId));
    }
    return representationsList;
  }

  @Override
  public List<IPFileInterface> getSchemas() {
    return schemas;
  }

  @Override
  public List<IPFileInterface> getDocumentation() {
    return documentation;
  }

  @Override
  public ValidationReport getValidationReport() {
    return validationReport;
  }

  public IPHeader getHeader() {
    return header;
  }

  public void setHeader(final IPHeader hdr) {
    header = hdr;
  }

  @Override
  public boolean isValid() {
    return validationReport.isValid();
  }

  private void checkIfRepresentationExists(final String representationID) throws IPException {
    if (!representations.containsKey(representationID)) {
      throw new IPException("Representation doesn't exist");
    }
  }

  public IPAgent addCreatorSoftwareAgent(final String softwareName, final String softwareVersion) {
    final IPAgent creatorAgent = new IPAgent(softwareName, "CREATOR", null, CreatorType.OTHER, "SOFTWARE",
      softwareVersion, IPAgentNoteTypeEnum.SOFTWARE_VERSION);
    header.addAgent(creatorAgent);
    return creatorAgent;
  }

  private IPAgent getSubmitterDefaultAgent() {
    return new IPAgent("Default submitter agent", "CREATOR", null, CreatorType.INDIVIDUAL, null, "1",
      IPAgentNoteTypeEnum.IDENTIFICATIONCODE);
  }

  /**
   * Adds a submitter agent to the SIP.
   * 
   * @param submitterAgentName
   *          the name of the submitter agent.
   * @param submitterAgentID
   *          the id of the submitter agent.
   * @return {@link IPAgent}
   */
  public IPAgent addSubmitterAgent(final String submitterAgentName, final String submitterAgentID) {
    final IPAgent submitterAgent;
    if (submitterAgentName == null && submitterAgentID != null) {
      submitterAgent = new IPAgent("Default submitter agent", "CREATOR", null, CreatorType.INDIVIDUAL, null,
        submitterAgentID, IPAgentNoteTypeEnum.IDENTIFICATIONCODE);
    } else if (submitterAgentName != null && submitterAgentID == null) {
      submitterAgent = new IPAgent(submitterAgentName, "CREATOR", null, CreatorType.INDIVIDUAL, null, "1",
        IPAgentNoteTypeEnum.IDENTIFICATIONCODE);
    } else if (submitterAgentName != null && submitterAgentID != null) {
      submitterAgent = new IPAgent(submitterAgentName, "CREATOR", null, CreatorType.INDIVIDUAL, null, submitterAgentID,
        IPAgentNoteTypeEnum.IDENTIFICATIONCODE);
    } else {
      submitterAgent = getSubmitterDefaultAgent();
    }

    header.addAgent(submitterAgent);
    return submitterAgent;
  }

  @Override
  public String toString() {
    return "IP [ids=" + ids + ", profile=" + profile + ", type=" + type + ", header=" + header + ", contentType="
      + contentType + ", ancestors=" + ancestors + ", basePath=" + basePath + ", description=" + description
      + ", descriptiveMetadata=" + descriptiveMetadata + ", preservationMetadata=" + preservationMetadata
      + ", otherMetadata=" + otherMetadata + ", representationIds=" + representationIds + ", representations="
      + representations + ", schemas=" + schemas + ", documentation=" + documentation + ", validationReport="
      + validationReport + "]";
  }

  public IP parse(final Path source) throws ParseException {
    throw new ParseException("One must implement static method parse in a concrete class");
  }

  public IP parse(final Path source, final Path destinationDirectory) throws ParseException {
    throw new ParseException("One must implement static method parse in a concrete class");
  }

  @Override
  public Map<String, ZipEntryInfo> getZipEntries() {
    return zipEntries;
  }

  public abstract Set<String> getExtraChecksumAlgorithms();
}
