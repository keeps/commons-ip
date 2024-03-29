//
// This file was generated by the Eclipse Implementation of JAXB, v4.0.3 
// See https://eclipse-ee4j.github.io/jaxb-ri 
// Any modifications to this file will be lost upon recompilation of the source schema. 
//


package org.roda_project.commons_ip.mets_v1_11.beans;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAnyAttribute;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlID;
import jakarta.xml.bind.annotation.XmlIDREF;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.adapters.CollapsedStringAdapter;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import javax.xml.namespace.QName;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * areaType: Complex Type for Area Linking
 * The area element provides for more sophisticated linking between a div element and content files representing that div, be they text, image, audio, or video files.  An area element can link a div to a point within a file, to a one-dimension segment of a file (e.g., text segment, image line, audio/video clip), or a two-dimensional section of a file 	(e.g, subsection of an image, or a subsection of the  video display of a video file.  The area element has no content; all information is recorded within its various attributes.
 *
 *
 * <p>Java class for areaType complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>{@code
 * <complexType name="areaType">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <attGroup ref="{http://www.loc.gov/METS/}ORDERLABELS"/>
 *       <attribute name="ID" type="{http://www.w3.org/2001/XMLSchema}ID" />
 *       <attribute name="FILEID" use="required" type="{http://www.w3.org/2001/XMLSchema}IDREF" />
 *       <attribute name="SHAPE">
 *         <simpleType>
 *           <restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *             <enumeration value="RECT"/>
 *             <enumeration value="CIRCLE"/>
 *             <enumeration value="POLY"/>
 *           </restriction>
 *         </simpleType>
 *       </attribute>
 *       <attribute name="COORDS" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       <attribute name="BEGIN" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       <attribute name="END" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       <attribute name="BETYPE">
 *         <simpleType>
 *           <restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *             <enumeration value="BYTE"/>
 *             <enumeration value="IDREF"/>
 *             <enumeration value="SMIL"/>
 *             <enumeration value="MIDI"/>
 *             <enumeration value="SMPTE-25"/>
 *             <enumeration value="SMPTE-24"/>
 *             <enumeration value="SMPTE-DF30"/>
 *             <enumeration value="SMPTE-NDF30"/>
 *             <enumeration value="SMPTE-DF29.97"/>
 *             <enumeration value="SMPTE-NDF29.97"/>
 *             <enumeration value="TIME"/>
 *             <enumeration value="TCF"/>
 *             <enumeration value="XPTR"/>
 *           </restriction>
 *         </simpleType>
 *       </attribute>
 *       <attribute name="EXTENT" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       <attribute name="EXTTYPE">
 *         <simpleType>
 *           <restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *             <enumeration value="BYTE"/>
 *             <enumeration value="SMIL"/>
 *             <enumeration value="MIDI"/>
 *             <enumeration value="SMPTE-25"/>
 *             <enumeration value="SMPTE-24"/>
 *             <enumeration value="SMPTE-DF30"/>
 *             <enumeration value="SMPTE-NDF30"/>
 *             <enumeration value="SMPTE-DF29.97"/>
 *             <enumeration value="SMPTE-NDF29.97"/>
 *             <enumeration value="TIME"/>
 *             <enumeration value="TCF"/>
 *           </restriction>
 *         </simpleType>
 *       </attribute>
 *       <attribute name="ADMID" type="{http://www.w3.org/2001/XMLSchema}IDREFS" />
 *       <attribute name="CONTENTIDS" type="{http://www.loc.gov/METS/}URIs" />
 *       <anyAttribute processContents='lax' namespace='##other'/>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "areaType")
public class AreaType {

  @XmlAttribute(name = "ID")
  @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
  @XmlID
  @XmlSchemaType(name = "ID")
  protected String id;
  @XmlAttribute(name = "FILEID", required = true)
  @XmlIDREF
  @XmlSchemaType(name = "IDREF")
  protected Object fileid;
  @XmlAttribute(name = "SHAPE")
  protected String shape;
  @XmlAttribute(name = "COORDS")
  protected String coords;
  @XmlAttribute(name = "BEGIN")
  protected String begin;
  @XmlAttribute(name = "END")
  protected String end;
  @XmlAttribute(name = "BETYPE")
  protected String betype;
  @XmlAttribute(name = "EXTENT")
  protected String extent;
  @XmlAttribute(name = "EXTTYPE")
  protected String exttype;
  @XmlAttribute(name = "ADMID")
  @XmlIDREF
  @XmlSchemaType(name = "IDREFS")
  protected List<Object> admid;
  @XmlAttribute(name = "CONTENTIDS")
  protected List<String> contentids;
  @XmlAttribute(name = "ORDER")
  protected BigInteger order;
  @XmlAttribute(name = "ORDERLABEL")
  protected String orderlabel;
  @XmlAttribute(name = "LABEL")
  protected String label;
  @XmlAnyAttribute
  private final Map<QName, String> otherAttributes = new HashMap<>();

  /**
   * Gets the value of the id property.
   *
   * @return possible object is
   * {@link String }
   */
  public String getID() {
    return id;
  }

  /**
   * Sets the value of the id property.
   *
   * @param value allowed object is
   *              {@link String }
   */
  public void setID(String value) {
    this.id = value;
  }

  /**
   * Gets the value of the fileid property.
   *
   * @return possible object is
   * {@link Object }
   */
  public Object getFILEID() {
    return fileid;
  }

  /**
   * Sets the value of the fileid property.
   *
   * @param value allowed object is
   *              {@link Object }
   */
  public void setFILEID(Object value) {
    this.fileid = value;
  }

  /**
   * Gets the value of the shape property.
   *
   * @return possible object is
   * {@link String }
   */
  public String getSHAPE() {
    return shape;
  }

  /**
   * Sets the value of the shape property.
   *
   * @param value allowed object is
   *              {@link String }
   */
  public void setSHAPE(String value) {
    this.shape = value;
  }

  /**
   * Gets the value of the coords property.
   *
   * @return possible object is
   * {@link String }
   */
  public String getCOORDS() {
    return coords;
  }

  /**
   * Sets the value of the coords property.
   *
   * @param value allowed object is
   *              {@link String }
   */
  public void setCOORDS(String value) {
    this.coords = value;
  }

  /**
   * Gets the value of the begin property.
   *
   * @return possible object is
   * {@link String }
   */
  public String getBEGIN() {
    return begin;
  }

  /**
   * Sets the value of the begin property.
   *
   * @param value allowed object is
   *              {@link String }
   */
  public void setBEGIN(String value) {
    this.begin = value;
  }

  /**
   * Gets the value of the end property.
   *
   * @return possible object is
   * {@link String }
   */
  public String getEND() {
    return end;
  }

  /**
   * Sets the value of the end property.
   *
   * @param value allowed object is
   *              {@link String }
   */
  public void setEND(String value) {
    this.end = value;
  }

  /**
   * Gets the value of the betype property.
   *
   * @return possible object is
   * {@link String }
   */
  public String getBETYPE() {
    return betype;
  }

  /**
   * Sets the value of the betype property.
   *
   * @param value allowed object is
   *              {@link String }
   */
  public void setBETYPE(String value) {
    this.betype = value;
  }

  /**
   * Gets the value of the extent property.
   *
   * @return possible object is
   * {@link String }
   */
  public String getEXTENT() {
    return extent;
  }

  /**
   * Sets the value of the extent property.
   *
   * @param value allowed object is
   *              {@link String }
   */
  public void setEXTENT(String value) {
    this.extent = value;
  }

  /**
   * Gets the value of the exttype property.
   *
   * @return possible object is
   * {@link String }
   */
  public String getEXTTYPE() {
    return exttype;
  }

  /**
   * Sets the value of the exttype property.
   *
   * @param value allowed object is
   *              {@link String }
   */
  public void setEXTTYPE(String value) {
    this.exttype = value;
  }

  /**
   * Gets the value of the admid property.
   *
   * <p>
   * This accessor method returns a reference to the live list,
   * not a snapshot. Therefore any modification you make to the
   * returned list will be present inside the Jakarta XML Binding object.
   * This is why there is not a {@code set} method for the admid property.
   *
   * <p>
   * For example, to add a new item, do as follows:
   * <pre>
   *    getADMID().add(newItem);
   * </pre>
   *
   *
   * <p>
   * Objects of the following type(s) are allowed in the list
   * {@link Object }
   *
   * @return The value of the admid property.
   */
  public List<Object> getADMID() {
    if (admid == null) {
      admid = new ArrayList<>();
    }
    return this.admid;
  }

  /**
   * Gets the value of the contentids property.
   *
   * <p>
   * This accessor method returns a reference to the live list,
   * not a snapshot. Therefore any modification you make to the
   * returned list will be present inside the Jakarta XML Binding object.
   * This is why there is not a {@code set} method for the contentids property.
   *
   * <p>
   * For example, to add a new item, do as follows:
   * <pre>
   *    getCONTENTIDS().add(newItem);
   * </pre>
   *
   *
   * <p>
   * Objects of the following type(s) are allowed in the list
   * {@link String }
   *
   * @return The value of the contentids property.
   */
  public List<String> getCONTENTIDS() {
    if (contentids == null) {
      contentids = new ArrayList<>();
    }
    return this.contentids;
  }

  /**
   * Gets the value of the order property.
   *
   * @return possible object is
   * {@link BigInteger }
   */
  public BigInteger getORDER() {
    return order;
  }

  /**
   * Sets the value of the order property.
   *
   * @param value allowed object is
   *              {@link BigInteger }
   */
  public void setORDER(BigInteger value) {
    this.order = value;
  }

  /**
   * Gets the value of the orderlabel property.
   *
   * @return possible object is
   * {@link String }
   */
  public String getORDERLABEL() {
    return orderlabel;
  }

  /**
   * Sets the value of the orderlabel property.
   *
   * @param value allowed object is
   *              {@link String }
   */
  public void setORDERLABEL(String value) {
    this.orderlabel = value;
  }

  /**
   * Gets the value of the label property.
   *
   * @return possible object is
   * {@link String }
   */
  public String getLABEL() {
    return label;
  }

  /**
   * Sets the value of the label property.
   *
   * @param value allowed object is
   *              {@link String }
   */
  public void setLABEL(String value) {
    this.label = value;
  }

  /**
   * Gets a map that contains attributes that aren't bound to any typed property on this class.
   *
   * <p>
   * the map is keyed by the name of the attribute and
   * the value is the string value of the attribute.
   * <p>
   * the map returned by this method is live, and you can add new attribute
   * by updating the map directly. Because of this design, there's no setter.
   *
   * @return always non-null
   */
  public Map<QName, String> getOtherAttributes() {
    return otherAttributes;
  }

}
