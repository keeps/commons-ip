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

public class SIPRepresentation {
  private String representationID;
  private String objectID;
  private List<SIPAgent> agents;
  private List<Path> data;
  private List<SIPMetadata> administrativeMetadata;
  private List<SIPMetadata> otherMetadata;
  private List<SIPDescriptiveMetadata> descriptiveMetadata;

  public SIPRepresentation(String representationID) {
    this.representationID = representationID;
    this.objectID = representationID;
    this.agents = new ArrayList<SIPAgent>();
    this.data = new ArrayList<Path>();
    this.administrativeMetadata = new ArrayList<SIPMetadata>();
    this.otherMetadata = new ArrayList<SIPMetadata>();
    this.descriptiveMetadata = new ArrayList<SIPDescriptiveMetadata>();
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

  public List<SIPAgent> getAgents() {
    return agents;
  }

  public List<Path> getData() {
    return data;
  }

  public void addAgent(SIPAgent agent) {
    agents.add(agent);
  }

  public void addData(Path d) {
    data.add(d);
  }

  public void addAdministrativeMetadata(SIPMetadata metadata) {
    administrativeMetadata.add(metadata);
  }

  public void addOtherMetadata(SIPMetadata metadata) {
    otherMetadata.add(metadata);
  }

  public void addDescriptiveMetadata(SIPDescriptiveMetadata metadata) {
    descriptiveMetadata.add(metadata);
  }

  public List<SIPMetadata> getAdministrativeMetadata() {
    return administrativeMetadata;
  }

  public List<SIPMetadata> getOtherMetadata() {
    return otherMetadata;
  }

  public List<SIPDescriptiveMetadata> getDescriptiveMetadata() {
    return descriptiveMetadata;
  }

}
