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
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.xml.datatype.XMLGregorianCalendar;

import org.roda_project.commons_ip.utils.Utils;

public class IPRepresentation {
  private String representationID;
  private String objectID;
  private Optional<XMLGregorianCalendar> createDate;
  private Optional<XMLGregorianCalendar> modificationDate;
  private RepresentationContentType contentType;
  private String description;
  private List<IPAgent> agents;
  private List<IPDescriptiveMetadata> descriptiveMetadata;
  private List<IPMetadata> preservationMetadata;
  private List<IPMetadata> otherMetadata;
  private List<IPFile> data;
  private List<IPFile> schemas;
  private List<IPFile> documentation;

  public IPRepresentation() {
    this.representationID = "ID" + UUID.randomUUID().toString();
    this.objectID = representationID;
    this.createDate = Utils.getCurrentTime();
    this.contentType = RepresentationContentType.getMIXED();
    this.description = "";
    this.agents = new ArrayList<IPAgent>();
    this.descriptiveMetadata = new ArrayList<IPDescriptiveMetadata>();
    this.preservationMetadata = new ArrayList<IPMetadata>();
    this.otherMetadata = new ArrayList<IPMetadata>();
    this.data = new ArrayList<IPFile>();
    this.schemas = new ArrayList<IPFile>();
    this.documentation = new ArrayList<IPFile>();
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

  public void setObjectID(String objectID) {
    this.objectID = objectID;
  }

  public RepresentationContentType getContentType() {
    return contentType;
  }

  public void setContentType(RepresentationContentType contentType) {
    this.contentType = contentType;
  }

  public Optional<XMLGregorianCalendar> getCreateDate() {
    return createDate;
  }

  public void setCreateDate(XMLGregorianCalendar createDate) {
    this.createDate = Optional.ofNullable(createDate);
  }

  public Optional<XMLGregorianCalendar> getModificationDate() {
    return modificationDate;
  }

  public void setModificationDate(XMLGregorianCalendar modificationDate) {
    this.modificationDate = Optional.ofNullable(modificationDate);
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getDescription() {
    return description;
  }

  public List<IPAgent> getAgents() {
    return agents;
  }

  public void addAgent(IPAgent agent) {
    agents.add(agent);
  }

  public List<IPDescriptiveMetadata> getDescriptiveMetadata() {
    return descriptiveMetadata;
  }

  public void addDescriptiveMetadata(IPDescriptiveMetadata metadata) {
    descriptiveMetadata.add(metadata);
  }

  public List<IPMetadata> getPreservationMetadata() {
    return preservationMetadata;
  }

  public void addPreservationMetadata(IPMetadata metadata) {
    preservationMetadata.add(metadata);
  }

  public List<IPMetadata> getOtherMetadata() {
    return otherMetadata;
  }

  public void addOtherMetadata(IPMetadata metadata) {
    otherMetadata.add(metadata);
  }

  public List<IPFile> getData() {
    return data;
  }

  public void addFile(IPFile ipFile) {
    data.add(ipFile);
  }

  public void addFile(Path filePath, List<String> folders) {
    data.add(new IPFile(filePath, folders));
  }

  public List<IPFile> getSchemas() {
    return schemas;
  }

  public void addSchema(IPFile schema) {
    this.schemas.add(schema);
  }

  public List<IPFile> getDocumentation() {
    return documentation;
  }

  public void addDocumentation(IPFile documentation) {
    this.documentation.add(documentation);
  }

  @Override
  public String toString() {
    return "IPRepresentation [representationID=" + representationID + ", objectID=" + objectID + ", createDate="
      + createDate + ", modificationDate=" + modificationDate + ", contentType=" + contentType + ", description="
      + description + ", agents=" + agents + ", descriptiveMetadata=" + descriptiveMetadata + ", preservationMetadata="
      + preservationMetadata + ", otherMetadata=" + otherMetadata + ", data=" + data + ", schemas=" + schemas
      + ", documentation=" + documentation + "]";
  }

}
