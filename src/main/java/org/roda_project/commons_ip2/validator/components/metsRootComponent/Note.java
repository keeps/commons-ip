package org.roda_project.commons_ip2.validator.components.metsRootComponent;

import javax.xml.namespace.QName;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Carlos Afonso <cafonso@keep.pt>
 */
public class Note {

  private String value;
  private String notetype;
  private Map<QName, String> otherAttributes = new HashMap<QName, String>();

  /**
   * Gets the value of the value property.
   *
   * @return possible object is {@link String }
   *
   */
  public String getValue() {
    return value;
  }

  /**
   * Sets the value of the value property.
   *
   * @param value
   *          allowed object is {@link String }
   *
   */
  public void setValue(String value) {
    this.value = value;
  }

  /**
   * Gets the value of the notetype property.
   *
   * @return possible object is {@link String }
   *
   */
  public String getNOTETYPE() {
    return notetype;
  }

  /**
   * Sets the value of the notetype property.
   *
   * @param value
   *          allowed object is {@link String }
   *
   */
  public void setNOTETYPE(String value) {
    this.notetype = value;
  }

  /**
   * Gets a map that contains attributes that aren't bound to any typed property
   * on this class.
   *
   * <p>
   * the map is keyed by the name of the attribute and the value is the string
   * value of the attribute.
   *
   * the map returned by this method is live, and you can add new attribute by
   * updating the map directly. Because of this design, there's no setter.
   *
   *
   * @return always non-null
   */
  public Map<QName, String> getOtherAttributes() {
    return otherAttributes;
  }

}
