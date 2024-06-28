/**
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE file at the root of the source
 * tree and available online at
 *
 * https://github.com/keeps/commons-ip
 */
package org.roda_project.commons_ip2.model;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.xml.datatype.XMLGregorianCalendar;

import org.roda_project.commons_ip2.utils.Utils;

public class IPRepresentation {
  private String representationID;
  private String objectID;
  private Optional<XMLGregorianCalendar> createDate;
  private Optional<XMLGregorianCalendar> modificationDate;

  // maps to mets/@type
  private IPContentType contentType;
  private IPContentInformationType contentInformationType;
  private RepresentationStatus status;
  private String description;
  private final List<IPAgent> agents;
  private final List<IPDescriptiveMetadata> descriptiveMetadata;
  private final List<IPMetadata> preservationMetadata;
  private final List<IPMetadata> otherMetadata;
  private final List<IPFileInterface> data;
  private final List<IPFileInterface> schemas;
  private final List<IPFileInterface> documentation;

  public IPRepresentation() {
    this.representationID = Utils.generateRandomAndPrefixedUUID();
    this.objectID = representationID;
    this.createDate = Utils.getCurrentTime();
    this.contentType = IPContentType.getMIXED();
    this.contentInformationType = IPContentInformationType.getMIXED();
    this.status = RepresentationStatus.getORIGINAL();
    this.description = "";
    this.agents = new ArrayList<>();
    this.descriptiveMetadata = new ArrayList<>();
    this.preservationMetadata = new ArrayList<>();
    this.otherMetadata = new ArrayList<>();
    this.data = new ArrayList<>();
    this.schemas = new ArrayList<>();
    this.documentation = new ArrayList<>();
  }

  public IPRepresentation(String representationID) {
    this();
    this.representationID = representationID;
    this.objectID = representationID;
  }

  public String getRepresentationID() {
    return representationID;
  }

  public String getObjectID() {
    return objectID;
  }

  public IPRepresentation setObjectID(String objectID) {
    this.objectID = objectID;
    return this;
  }

  public IPContentType getContentType() {
    return contentType;
  }

  public IPRepresentation setContentType(IPContentType contentType) {
    this.contentType = contentType;
    return this;
  }

  public IPContentInformationType getContentInformationType() {
    return contentInformationType;
  }

  public void setContentInformationType(IPContentInformationType contentInformationType) {
    this.contentInformationType = contentInformationType;
  }

  public RepresentationStatus getStatus() {
    return status;
  }

  public IPRepresentation setStatus(RepresentationStatus status) {
    this.status = status;
    return this;
  }

  public Optional<XMLGregorianCalendar> getCreateDate() {
    return createDate;
  }

  public IPRepresentation setCreateDate(XMLGregorianCalendar createDate) {
    this.createDate = Optional.ofNullable(createDate);
    return this;
  }

  public Optional<XMLGregorianCalendar> getModificationDate() {
    return modificationDate;
  }

  public IPRepresentation setModificationDate(XMLGregorianCalendar modificationDate) {
    this.modificationDate = Optional.ofNullable(modificationDate);
    return this;
  }

  public IPRepresentation setDescription(String description) {
    this.description = description;
    return this;
  }

  public String getDescription() {
    return description;
  }

  public List<IPAgent> getAgents() {
    return agents;
  }

  public IPRepresentation addAgent(IPAgent agent) {
    agents.add(agent);
    return this;
  }

  public List<IPDescriptiveMetadata> getDescriptiveMetadata() {
    return descriptiveMetadata;
  }

  public IPRepresentation addDescriptiveMetadata(IPDescriptiveMetadata metadata) {
    descriptiveMetadata.add(metadata);
    return this;
  }

  public List<IPMetadata> getPreservationMetadata() {
    return preservationMetadata;
  }

  public IPRepresentation addPreservationMetadata(IPMetadata metadata) {
    preservationMetadata.add(metadata);
    return this;
  }

  public List<IPMetadata> getOtherMetadata() {
    return otherMetadata;
  }

  public IPRepresentation addOtherMetadata(IPMetadata metadata) {
    otherMetadata.add(metadata);
    return this;
  }

  public List<IPFileInterface> getData() {
    return data;
  }

  public IPRepresentation addFile(IPFileInterface ipFile) {
    data.add(ipFile);
    return this;
  }

  public IPRepresentation addFile(Path filePath, List<String> folders) {
    data.add(new IPFile(filePath, folders));
    return this;
  }

  public List<IPFileInterface> getSchemas() {
    return schemas;
  }

  public IPRepresentation addSchema(IPFileInterface schema) {
    this.schemas.add(schema);
    return this;
  }

  public List<IPFileInterface> getDocumentation() {
    return documentation;
  }

  public IPRepresentation addDocumentation(IPFileInterface documentation) {
    this.documentation.add(documentation);
    return this;
  }

  @Override
  public String toString() {
    return "IPRepresentation [representationID=" + representationID + ", objectID=" + objectID + ", createDate="
      + createDate + ", modificationDate=" + modificationDate + ", contentType=" + contentType
      + ", contentInformationType=" + contentInformationType + ", status=" + status + ", description=" + description
      + ", agents=" + agents + ", descriptiveMetadata=" + descriptiveMetadata + ", preservationMetadata="
      + preservationMetadata + ", otherMetadata=" + otherMetadata + ", data=" + data + ", schemas=" + schemas
      + ", documentation=" + documentation + "]";
  }

}
