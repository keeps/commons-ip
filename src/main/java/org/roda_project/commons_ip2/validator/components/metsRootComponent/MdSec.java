package org.roda_project.commons_ip2.validator.components.metsRootComponent;

/**
 * @author Carlos Afonso <cafonso@keep.pt>
 */
public class MdSec {
  private String id;
  private String groupid;
  private String created;
  private String status;
  private MdSec mdRef;
  private String loctype;
  private String linktype;
  private String href;
  private String mdtype;

  private String otherMdType;
  private String mimetype;
  private Long size;
  private String checksum;
  private String checksumtype;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getGroupid() {
    return groupid;
  }

  public void setGroupid(String groupid) {
    this.groupid = groupid;
  }

  public String getCreated() {
    return created;
  }

  public void setCreated(String created) {
    this.created = created;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public MdSec getMdRef() {
    return mdRef;
  }

  public void setMdRef(MdSec mdRef) {
    this.mdRef = mdRef;
  }

  public String getLoctype() {
    return loctype;
  }

  public void setLoctype(String loctype) {
    this.loctype = loctype;
  }

  public String getLinktype() {
    return linktype;
  }

  public void setLinktype(String linktype) {
    this.linktype = linktype;
  }

  public String getHref() {
    return href;
  }

  public void setHref(String href) {
    this.href = href;
  }

  public String getMdtype() {
    return mdtype;
  }

  public void setMdtype(String mdtype) {
    this.mdtype = mdtype;
  }

  public String getMimetype() {
    return mimetype;
  }

  public void setMimetype(String mimetype) {
    this.mimetype = mimetype;
  }

  public Long getSize() {
    return size;
  }

  public void setSize(Long size) {
    this.size = size;
  }

  public String getChecksum() {
    return checksum;
  }

  public void setChecksum(String checksum) {
    this.checksum = checksum;
  }

  public String getChecksumtype() {
    return checksumtype;
  }

  public void setChecksumtype(String checksumtype) {
    this.checksumtype = checksumtype;
  }

  public String getOtherMdType() {
    return otherMdType;
  }

  public void setOtherMdType(String otherMdType) {
    this.otherMdType = otherMdType;
  }

}
