//
// This file was generated by the Eclipse Implementation of JAXB, v4.0.3 
// See https://eclipse-ee4j.github.io/jaxb-ri 
// Any modifications to this file will be lost upon recompilation of the source schema. 
//


package org.roda_project.commons_ip2.mets_v1_12.beans;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAnyAttribute;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlID;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.adapters.CollapsedStringAdapter;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import javax.xml.namespace.QName;
import java.util.HashMap;
import java.util.Map;


/**
 * structMapType: Complex Type for Structural Maps
 * The structural map (structMap) outlines a hierarchical structure for the original object being encoded, using a series of nested div elements.
 *
 *
 * <p>Java class for structMapType complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>{@code
 * <complexType name="structMapType">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element name="div" type="{http://www.loc.gov/METS/}divType"/>
 *       </sequence>
 *       <attribute name="ID" type="{http://www.w3.org/2001/XMLSchema}ID" />
 *       <attribute name="TYPE" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       <attribute name="LABEL" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       <anyAttribute processContents='lax' namespace='##other'/>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "structMapType", propOrder = {
    "div"
})
public class StructMapType {

  @XmlElement(required = true)
  protected DivType div;
  @XmlAttribute(name = "ID")
  @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
  @XmlID
  @XmlSchemaType(name = "ID")
  protected String id;
  @XmlAttribute(name = "TYPE")
  protected String type;
  @XmlAttribute(name = "LABEL")
  protected String label;
  @XmlAnyAttribute
  private final Map<QName, String> otherAttributes = new HashMap<>();

  /**
   * Gets the value of the div property.
   *
   * @return possible object is
   * {@link DivType }
   */
  public DivType getDiv() {
    return div;
  }

  /**
   * Sets the value of the div property.
   *
   * @param value allowed object is
   *              {@link DivType }
   */
  public void setDiv(DivType value) {
    this.div = value;
  }

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
   * Gets the value of the type property.
   *
   * @return possible object is
   * {@link String }
   */
  public String getTYPE() {
    return type;
  }

  /**
   * Sets the value of the type property.
   *
   * @param value allowed object is
   *              {@link String }
   */
  public void setTYPE(String value) {
    this.type = value;
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
