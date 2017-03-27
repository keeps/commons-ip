package org.roda_project.commons_ip.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.xml.datatype.XMLGregorianCalendar;

import org.roda_project.commons_ip.utils.IPEnums;
import org.roda_project.commons_ip.utils.Utils;

public class IPHeader {
  private Optional<XMLGregorianCalendar> createDate;
  private Optional<XMLGregorianCalendar> modificationDate;
  private IPEnums.IPStatus status;
  private List<IPAgent> agents;
  private List<IPAltRecordID> altRecordIDs;

  public IPHeader() {
    this.createDate = Utils.getCurrentTime();
    this.modificationDate = Optional.empty();
    this.status = IPEnums.IPStatus.NEW;
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
