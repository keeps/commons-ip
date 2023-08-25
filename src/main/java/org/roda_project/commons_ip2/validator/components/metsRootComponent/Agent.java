package org.roda_project.commons_ip2.validator.components.metsRootComponent;

import org.roda_project.commons_ip2.mets_v1_12.beans.MetsType;

/**
 * @author Carlos Afonso <cafonso@keep.pt>
 */
public class Agent {

  private String name;
  private Note note;
  private String id;
  private String role;
  private String otherrole;
  private String type;
  private String othertype;

  /**
   * Gets the value of the name property.
   *
   * @return possible object is {@link String }
   *
   */
  public String getName() {
    return name;
  }

  /**
   * Sets the value of the name property.
   *
   * @param value
   *          allowed object is {@link String }
   *
   */
  public void setName(String value) {
    this.name = value;
  }

  /**
   * Gets the value of the note property.
   *
   * <p>
   * This accessor method returns a reference to the live list, not a snapshot.
   * Therefore any modification you make to the returned list will be present
   * inside the JAXB object. This is why there is not a <CODE>set</CODE> method
   * for the note property.
   *
   * <p>
   * For example, to add a new item, do as follows:
   * 
   * <pre>
   * getNote().add(newItem);
   * </pre>
   *
   *
   * <p>
   * Objects of the following type(s) are allowed in the list
   * {@link MetsType.MetsHdr.Agent.Note }
   *
   *
   */
  public Note getNote() {
    return this.note;
  }

  /**
   * Gets the value of the id property.
   *
   * @return possible object is {@link String }
   *
   */
  public String getID() {
    return id;
  }

  /**
   * Sets the value of the id property.
   *
   * @param value
   *          allowed object is {@link String }
   *
   */
  public void setId(String value) {
    this.id = value;
  }

  /**
   * Gets the value of the role property.
   *
   * @return possible object is {@link String }
   *
   */
  public String getRole() {
    return role;
  }

  /**
   * Sets the value of the role property.
   *
   * @param value
   *          allowed object is {@link String }
   *
   */
  public void setRole(String value) {
    this.role = value;
  }

  public void setNote(Note note) {
    this.note=note;
  }

  /**
   * Gets the value of the otherrole property.
   *
   * @return possible object is {@link String }
   *
   */
  public String getOtherRole() {
    return otherrole;
  }

  /**
   * Sets the value of the otherrole property.
   *
   * @param value
   *          allowed object is {@link String }
   *
   */
  public void setOtherRole(String value) {
    this.otherrole = value;
  }

  /**
   * Gets the value of the type property.
   *
   * @return possible object is {@link String }
   *
   */
  public String getTYPE() {
    return type;
  }

  /**
   * Sets the value of the type property.
   *
   * @param value
   *          allowed object is {@link String }
   *
   */
  public void setTYPE(String value) {
    this.type = value;
  }

  /**
   * Gets the value of the othertype property.
   *
   * @return possible object is {@link String }
   *
   */
  public String getOTHERTYPE() {
    return othertype;
  }

  /**
   * Sets the value of the othertype property.
   *
   * @param value
   *          allowed object is {@link String }
   *
   */
  public void setOTHERTYPE(String value) {
    this.othertype = value;
  }

}