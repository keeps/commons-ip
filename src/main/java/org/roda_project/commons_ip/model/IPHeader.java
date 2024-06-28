/**
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE file at the root of the source
 * tree and available online at
 *
 * https://github.com/keeps/commons-ip
 */
package org.roda_project.commons_ip.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.xml.datatype.XMLGregorianCalendar;

import org.roda_project.commons_ip.utils.IPEnums;
import org.roda_project.commons_ip.utils.Utils;

public class IPHeader implements Serializable {
  private transient Optional<XMLGregorianCalendar> createDate;
  private transient Optional<XMLGregorianCalendar> modificationDate;
  private IPEnums.IPStatus status;
  private List<IPAgent> agents;
  private final List<IPAltRecordID> altRecordIDs;

  public IPHeader() {
    this.createDate = Utils.getCurrentTime();
    this.modificationDate = Optional.empty();
    this.status = IPEnums.IPStatus.NEW;
    this.altRecordIDs = new ArrayList<>();
    this.agents = new ArrayList<>();
  }

  public IPHeader(IPEnums.IPStatus status) {
    this.createDate = Utils.getCurrentTime();
    this.modificationDate = Optional.empty();
    this.status = status;
    this.altRecordIDs = new ArrayList<>();
    this.agents = new ArrayList<>();
  }

  public Optional<XMLGregorianCalendar> getCreateDate() {
    return createDate;
  }

  public IPHeader setCreateDate(XMLGregorianCalendar date) {
    this.createDate = Optional.ofNullable(date);
    return this;
  }

  public Optional<XMLGregorianCalendar> getModificationDate() {
    return modificationDate;
  }

  public IPHeader setModificationDate(XMLGregorianCalendar date) {
    this.modificationDate = Optional.ofNullable(date);
    return this;
  }

  public IPEnums.IPStatus getStatus() {
    return status;
  }

  public IPHeader setStatus(IPEnums.IPStatus status) {
    this.status = status;
    return this;
  }

  public List<IPAltRecordID> getAltRecordIDs() {
    return altRecordIDs;
  }

  public IPHeader addAltRecordID(IPAltRecordID altRecordID) {
    altRecordIDs.add(altRecordID);
    return this;
  }

  public List<IPAgent> getAgents() {
    return agents;
  }

  public IPHeader setAgents(List<IPAgent> agents) {
    this.agents = agents;
    return this;
  }

  public IPHeader addAgent(IPAgent sipAgent) {
    agents.add(sipAgent);
    return this;
  }

  @Override
  public String toString() {
    return "IPHeader [createDate=" + createDate + ", modificationDate=" + modificationDate + ", status=" + status
      + ", altRecordIDs=" + altRecordIDs + ", agents=" + agents + "]";
  }
}
