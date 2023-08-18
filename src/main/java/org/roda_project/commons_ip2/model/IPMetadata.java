/**
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE file at the root of the source
 * tree and available online at
 *
 * https://github.com/keeps/commons-ip
 */
package org.roda_project.commons_ip2.model;

import java.io.Serializable;
import java.util.Optional;

import javax.xml.datatype.XMLGregorianCalendar;

import org.roda_project.commons_ip2.model.MetadataType.MetadataTypeEnum;
import org.roda_project.commons_ip2.utils.Utils;

public class IPMetadata implements Serializable {
  private static final long serialVersionUID = 3697420963728707487L;

  private String id;
  private Optional<XMLGregorianCalendar> createDate;
  private IPFileInterface metadata;
  private MetadataType metadataType = new MetadataType(MetadataTypeEnum.OTHER);
  private MetadataStatus metadataStatus = MetadataStatus.CURRENT;

  public IPMetadata() {
    // empty constructor for serialization purposes
  }

  public IPMetadata(IPFileInterface metadata) {
    id = Utils.generateRandomAndPrefixedUUID();
    createDate = Utils.getCurrentTime();
    this.metadata = metadata;
    metadataType = new MetadataType(MetadataTypeEnum.OTHER);
  }

  public IPMetadata(IPFileInterface metadata, MetadataType metadataType) {
    this.id = Utils.generateRandomAndPrefixedUUID();
    createDate = Utils.getCurrentTime();
    this.metadata = metadata;
    this.metadataType = metadataType;
  }

  public IPMetadata(String id, IPFileInterface metadata, MetadataType metadataType) {
    this.id = id;
    createDate = Utils.getCurrentTime();
    this.metadata = metadata;
    this.metadataType = metadataType;
  }

  public String getId() {
    return id;
  }

  public IPMetadata setId(String id) {
    this.id = id;
    return this;
  }

  public Optional<XMLGregorianCalendar> getCreateDate() {
    return createDate;
  }

  public IPMetadata setCreateDate(XMLGregorianCalendar createDate) {
    this.createDate = Optional.ofNullable(createDate);
    return this;
  }

  public IPFileInterface getMetadata() {
    return metadata;
  }

  public IPMetadata setMetadata(IPFileInterface metadata) {
    this.metadata = metadata;
    return this;
  }

  public MetadataType getMetadataType() {
    return metadataType;
  }

  public IPMetadata setMetadataType(MetadataType metadataType) {
    this.metadataType = metadataType;
    return this;
  }

  public IPMetadata setMetadataType(MetadataTypeEnum metadataTypeEnum) {
    this.metadataType = new MetadataType(metadataTypeEnum);
    return this;
  }

  public MetadataStatus getMetadataStatus() {
    return metadataStatus;
  }

  public IPMetadata setMetadataStatus(MetadataStatus metadataStatus) {
    this.metadataStatus = metadataStatus;
    return this;
  }

  @Override
  public String toString() {
    return "IPMetadata [id=" + id + ", createDate=" + createDate + ", metadata=" + metadata + ", metadataType="
      + metadataType + ", metadataStatus=" + metadataStatus + "]";
  }

}
