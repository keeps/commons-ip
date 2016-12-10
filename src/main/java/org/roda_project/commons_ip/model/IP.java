/**
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE file at the root of the source
 * tree and available online at
 *
 * https://github.com/keeps/commons-ip
 */
package org.roda_project.commons_ip.model;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.xml.datatype.XMLGregorianCalendar;

import org.roda_project.commons_ip.utils.IPEnums.IPStatus;
import org.roda_project.commons_ip.utils.IPEnums.IPType;
import org.roda_project.commons_ip.utils.IPException;
import org.roda_project.commons_ip.utils.METSEnums.CreatorType;
import org.roda_project.commons_ip.utils.SIPException;
import org.roda_project.commons_ip.utils.Utils;
import org.roda_project.commons_ip.utils.ZipEntryInfo;

public abstract class IP implements IPInterface {

  private List<String> ids;
  private String profile;
  private IPType type;
  private Optional<XMLGregorianCalendar> createDate;
  private Optional<XMLGregorianCalendar> modificationDate;
  private IPContentType contentType;
  private IPStatus status;
  private List<String> ancestors;

  private Path basePath;
  private String description;

  private List<IPAgent> agents;
  private List<IPDescriptiveMetadata> descriptiveMetadata;
  private List<IPMetadata> preservationMetadata;
  private List<IPMetadata> otherMetadata;
  private List<String> representationIds;
  private Map<String, IPRepresentation> representations;
  private List<IPFile> schemas;
  private List<IPFile> documentation;

  private List<ZipEntryInfo> zipEntries;

  private ValidationReport validationReport;

  public IP() {
    this.setId(Utils.generateRandomAndPrefixedUUID());
    this.profile = "http://www.eark-project.com/METS/IP.xml";
    this.type = IPType.SIP;
    this.createDate = Utils.getCurrentTime();
    this.contentType = IPContentType.getMIXED();
    this.status = IPStatus.NEW;
    this.ancestors = new ArrayList<>();

    this.description = "";

    this.agents = new ArrayList<IPAgent>();
    this.descriptiveMetadata = new ArrayList<IPDescriptiveMetadata>();
    this.preservationMetadata = new ArrayList<IPMetadata>();
    this.otherMetadata = new ArrayList<IPMetadata>();
    this.representationIds = new ArrayList<String>();
    this.representations = new HashMap<String, IPRepresentation>();
    this.schemas = new ArrayList<IPFile>();
    this.documentation = new ArrayList<IPFile>();

    this.validationReport = new ValidationReport();
    this.zipEntries = new ArrayList<ZipEntryInfo>();
  }

  public IP(List<String> ipIds, IPType ipType) {
    super();
    this.setIds(ipIds);
    this.type = ipType;
    this.zipEntries = new ArrayList<ZipEntryInfo>();
  }

  public IP(List<String> ipIds, IPType ipType, IPContentType contentType, String creator) {
    this(ipIds, ipType);
    this.contentType = contentType;

    IPAgent creatorAgent = new IPAgent(creator, "CREATOR", null, CreatorType.OTHER, "SOFTWARE");
    this.agents.add(creatorAgent);
  }

  @Override
  public IP setId(String id) {
    this.ids = Arrays.asList(id);
    return this;
  }

  @Override
  public String getId() {
    return ids.stream().findFirst().orElse("");
  }

  @Override
  public IP setIds(List<String> ids) {
    this.ids = ids;
    return this;
  }

  @Override
  public List<String> getIds() {
    return ids;
  }

  @Override
  public IP setProfile(String profile) {
    this.profile = profile;
    return this;
  }

  @Override
  public String getProfile() {
    return profile;
  }

  @Override
  public IP setType(IPType type) {
    this.type = type;
    return this;
  }

  @Override
  public String getType() {
    return type.toString();
  }

  @Override
  public Optional<XMLGregorianCalendar> getCreateDate() {
    return createDate;
  }

  @Override
  public void setCreateDate(XMLGregorianCalendar date) {
    this.createDate = Optional.ofNullable(date);
  }

  @Override
  public Optional<XMLGregorianCalendar> getModificationDate() {
    return modificationDate;
  }

  @Override
  public void setModificationDate(XMLGregorianCalendar date) {
    this.modificationDate = Optional.ofNullable(date);
  }

  @Override
  public IP setContentType(IPContentType contentType) {
    this.contentType = contentType;
    return this;
  }

  @Override
  public IPContentType getContentType() {
    return contentType;
  }

  @Override
  public List<String> getAncestors() {
    return ancestors;
  }

  @Override
  public IP setAncestors(List<String> ancestors) {
    this.ancestors = ancestors;
    return this;
  }

  @Override
  public IPStatus getStatus() {
    return status;
  }

  @Override
  public void setStatus(IPStatus status) {
    this.status = status;
  }

  @Override
  public IP setBasePath(Path basePath) {
    this.basePath = basePath;
    return this;
  }

  @Override
  public Path getBasePath() {
    return basePath;
  }

  @Override
  public IP setDescription(String description) {
    this.description = description;
    return this;
  }

  @Override
  public String getDescription() {
    return description;
  }

  @Override
  public IP addAgent(IPAgent sipAgent) {
    agents.add(sipAgent);
    return this;
  }

  @Override
  public IP addPreservationMetadata(IPMetadata sipMetadata) throws SIPException {
    preservationMetadata.add(sipMetadata);
    return this;
  }

  @Override
  public IP addOtherMetadata(IPMetadata sipMetadata) throws SIPException {
    otherMetadata.add(sipMetadata);
    return this;
  }

  @Override
  public IP addDescriptiveMetadata(IPDescriptiveMetadata metadata) throws IPException {
    descriptiveMetadata.add(metadata);
    return this;
  }

  @Override
  public IP addRepresentation(IPRepresentation sipRepresentation) throws IPException {
    String representationId = sipRepresentation.getRepresentationID();
    if (representations.containsKey(representationId)) {
      throw new SIPException("Representation already exists");
    } else {
      representationIds.add(representationId);
      representations.put(representationId, sipRepresentation);
      return this;
    }
  }

  @Override
  public IP addSchema(IPFile schema) {
    schemas.add(schema);
    return this;
  }

  @Override
  public IP addDocumentation(IPFile documentationPath) {
    documentation.add(documentationPath);
    return this;
  }

  @Override
  public IP addAgentToRepresentation(String representationID, IPAgent agent) throws SIPException {
    checkIfRepresentationExists(representationID);
    IPRepresentation rep = representations.get(representationID);
    rep.addAgent(agent);
    representations.put(representationID, rep);
    return this;
  }

  @Override
  public IP addDescriptiveMetadataToRepresentation(String representationID, IPDescriptiveMetadata descriptiveMetadata)
    throws SIPException {
    checkIfRepresentationExists(representationID);
    IPRepresentation rep = representations.get(representationID);
    rep.addDescriptiveMetadata(descriptiveMetadata);
    representations.put(representationID, rep);
    return this;
  }

  @Override
  public IP addPreservationMetadataToRepresentation(String representationID, IPMetadata preservationMetadata)
    throws SIPException {
    checkIfRepresentationExists(representationID);
    IPRepresentation rep = representations.get(representationID);
    rep.addPreservationMetadata(preservationMetadata);
    representations.put(representationID, rep);
    return this;
  }

  @Override
  public IP addOtherMetadataToRepresentation(String representationID, IPMetadata otherMetadata) throws SIPException {
    checkIfRepresentationExists(representationID);
    IPRepresentation rep = representations.get(representationID);
    rep.addOtherMetadata(otherMetadata);
    representations.put(representationID, rep);
    return this;
  }

  @Override
  public IP addFileToRepresentation(String representationID, IPFile file) throws SIPException {
    checkIfRepresentationExists(representationID);
    IPRepresentation rep = representations.get(representationID);
    rep.addFile(file);
    representations.put(representationID, rep);
    return this;
  }

  @Override
  public IP addSchemaToRepresentation(String representationID, IPFile schema) throws SIPException {
    checkIfRepresentationExists(representationID);
    IPRepresentation rep = representations.get(representationID);
    rep.addSchema(schema);
    representations.put(representationID, rep);
    return this;
  }

  @Override
  public IP addDocumentationToRepresentation(String representationID, IPFile documentation) throws SIPException {
    checkIfRepresentationExists(representationID);
    IPRepresentation rep = representations.get(representationID);
    rep.addDocumentation(documentation);
    representations.put(representationID, rep);
    return this;
  }

  @Override
  public List<IPAgent> getAgents() {
    return agents;
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
    List<IPRepresentation> representationsList = new ArrayList<IPRepresentation>();
    for (String representationId : representationIds) {
      representationsList.add(representations.get(representationId));
    }
    return representationsList;
  }

  @Override
  public List<IPFile> getSchemas() {
    return schemas;
  }

  @Override
  public List<IPFile> getDocumentation() {
    return documentation;
  }

  @Override
  public ValidationReport getValidationReport() {
    return validationReport;
  }

  @Override
  public boolean isValid() {
    return validationReport.isValid();
  }

  private void checkIfRepresentationExists(String representationID) throws SIPException {
    if (!representations.containsKey(representationID)) {
      throw new SIPException("Representation doesn't exist");
    }
  }

  @Override
  public String toString() {
    return "IP [ids=" + ids + ", profile=" + profile + ", type=" + type + ", createDate=" + createDate
      + ", modificationDate=" + modificationDate + ", contentType=" + contentType + ", status=" + status
      + ", ancestors=" + ancestors + ", basePath=" + basePath + ", description=" + description + ", agents=" + agents
      + ", descriptiveMetadata=" + descriptiveMetadata + ", preservationMetadata=" + preservationMetadata
      + ", otherMetadata=" + otherMetadata + ", representationIds=" + representationIds + ", representations="
      + representations + ", schemas=" + schemas + ", documentation=" + documentation + ", validationReport="
      + validationReport + "]";
  }

  public static IP parse(Path source) throws ParseException {
    throw new ParseException("One must implement static method parse in a concrete class");
  }

  public static IP parse(Path source, Path destinationDirectory) throws ParseException {
    throw new ParseException("One must implement static method parse in a concrete class");
  }

  public List<ZipEntryInfo> getZipEntries() {
    return zipEntries;
  }
}
