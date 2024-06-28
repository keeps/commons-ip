/**
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE file at the root of the source
 * tree and available online at
 *
 * https://github.com/keeps/commons-ip
 */
package org.roda_project.commons_ip.model;

import java.nio.file.Path;
import java.util.*;

import javax.xml.datatype.XMLGregorianCalendar;

import org.roda_project.commons_ip.utils.IPEnums.IPStatus;
import org.roda_project.commons_ip.utils.IPEnums.IPType;
import org.roda_project.commons_ip.utils.IPException;
import org.roda_project.commons_ip.utils.METSEnums.CreatorType;
import org.roda_project.commons_ip.utils.Utils;
import org.roda_project.commons_ip.utils.ZipEntryInfo;

public abstract class IP implements IPInterface {

  private List<String> ids;
  private String profile;
  private IPType type;
  private IPHeader header;

  private IPContentType contentType;
  private List<String> ancestors;

  private Path basePath;
  private String description;

  private List<IPDescriptiveMetadata> descriptiveMetadata;
  private List<IPMetadata> preservationMetadata;
  private List<IPMetadata> otherMetadata;
  private List<String> representationIds;
  private Map<String, IPRepresentation> representations;
  private List<IPFile> schemas;
  private List<IPFile> documentation;

  private final Map<String, ZipEntryInfo> zipEntries;
  private String checksumAlgorithm;

  private ValidationReport validationReport;

  public IP() {
    this.setId(Utils.generateRandomAndPrefixedUUID());
    this.profile = "http://www.eark-project.com/METS/IP.xml";
    this.type = IPType.SIP;
    this.header = new IPHeader();

    this.contentType = IPContentType.getMIXED();
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

  public IP(List<String> ipIds, IPType ipType) {
    super();
    this.setIds(ipIds);
    this.type = ipType;
    this.zipEntries = new LinkedHashMap<>();
    this.header = new IPHeader();
  }

  public IP(List<String> ipIds, IPType ipType, IPContentType contentType) {
    this(ipIds, ipType);
    this.contentType = contentType;
  }

  @Override
  public IP setId(String id) {
    this.ids = Collections.singletonList(id);
    return this;
  }
  public void setChecksumAlgorithm(final String checksumAlgorithm) {
    this.checksumAlgorithm = checksumAlgorithm;
  }
  public String getChecksumAlgorithm() {
    return this.checksumAlgorithm;
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
    return header.getCreateDate();
  }

  @Override
  public IP setCreateDate(XMLGregorianCalendar date) {
    header.setCreateDate(date);
    return this;
  }

  @Override
  public Optional<XMLGregorianCalendar> getModificationDate() {
    return header.getModificationDate();
  }

  @Override
  public IP setModificationDate(XMLGregorianCalendar date) {
    header.setModificationDate(date);
    return this;
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
    return header.getStatus();
  }

  @Override
  public IP setStatus(IPStatus status) {
    this.header.setStatus(status);
    return this;
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
    header.addAgent(sipAgent);
    return this;
  }

  @Override
  public IP addPreservationMetadata(IPMetadata sipMetadata) throws IPException {
    preservationMetadata.add(sipMetadata);
    return this;
  }

  @Override
  public IP addOtherMetadata(IPMetadata sipMetadata) throws IPException {
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
      throw new IPException("Representation already exists");
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
  public IP addAgentToRepresentation(String representationID, IPAgent agent) throws IPException {
    checkIfRepresentationExists(representationID);
    IPRepresentation rep = representations.get(representationID);
    rep.addAgent(agent);
    representations.put(representationID, rep);
    return this;
  }

  @Override
  public IP addDescriptiveMetadataToRepresentation(String representationID, IPDescriptiveMetadata descriptiveMetadata)
    throws IPException {
    checkIfRepresentationExists(representationID);
    IPRepresentation rep = representations.get(representationID);
    rep.addDescriptiveMetadata(descriptiveMetadata);
    representations.put(representationID, rep);
    return this;
  }

  @Override
  public IP addPreservationMetadataToRepresentation(String representationID, IPMetadata preservationMetadata)
    throws IPException {
    checkIfRepresentationExists(representationID);
    IPRepresentation rep = representations.get(representationID);
    rep.addPreservationMetadata(preservationMetadata);
    representations.put(representationID, rep);
    return this;
  }

  @Override
  public IP addOtherMetadataToRepresentation(String representationID, IPMetadata otherMetadata) throws IPException {
    checkIfRepresentationExists(representationID);
    IPRepresentation rep = representations.get(representationID);
    rep.addOtherMetadata(otherMetadata);
    representations.put(representationID, rep);
    return this;
  }

  @Override
  public IP addFileToRepresentation(String representationID, IPFile file) throws IPException {
    checkIfRepresentationExists(representationID);
    IPRepresentation rep = representations.get(representationID);
    rep.addFile(file);
    representations.put(representationID, rep);
    return this;
  }

  @Override
  public IP addSchemaToRepresentation(String representationID, IPFile schema) throws IPException {
    checkIfRepresentationExists(representationID);
    IPRepresentation rep = representations.get(representationID);
    rep.addSchema(schema);
    representations.put(representationID, rep);
    return this;
  }

  @Override
  public IP addDocumentationToRepresentation(String representationID, IPFile documentation) throws IPException {
    checkIfRepresentationExists(representationID);
    IPRepresentation rep = representations.get(representationID);
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
    List<IPRepresentation> representationsList = new ArrayList<>();
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

  public IPHeader getHeader() {
    return header;
  }

  public void setHeader(IPHeader hdr) {
    header = hdr;
  }

  @Override
  public boolean isValid() {
    return validationReport.isValid();
  }

  private void checkIfRepresentationExists(String representationID) throws IPException {
    if (!representations.containsKey(representationID)) {
      throw new IPException("Representation doesn't exist");
    }
  }

  public IPAgent addCreatorSoftwareAgent(String softwarename) {
    IPAgent creatorAgent = new IPAgent(softwarename, "CREATOR", null, CreatorType.OTHER, "SOFTWARE");
    header.addAgent(creatorAgent);
    return creatorAgent;
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

  public static IP parse(Path source) throws ParseException {
    throw new ParseException("One must implement static method parse in a concrete class");
  }

  public static IP parse(Path source, Path destinationDirectory) throws ParseException {
    throw new ParseException("One must implement static method parse in a concrete class");
  }

  @Override
  public Map<String, ZipEntryInfo> getZipEntries() {
    return zipEntries;
  }

  public abstract Set<String> getExtraChecksumAlgorithms();
}
