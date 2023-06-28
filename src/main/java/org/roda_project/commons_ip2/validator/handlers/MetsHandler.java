package org.roda_project.commons_ip2.validator.handlers;

import java.util.Map;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/** {@author João Gomes <jgomes@keep.pt>}. */
public class MetsHandler extends DefaultHandler {

  /**
   * Constant with the url of xml schema.
   */
  private static final String SCHEMA = "http://www.w3.org/1999/xlink";
  /**
   * The xml root tag.
   */
  private final String section;
  /**
   * the xml element.
   */
  private final String element;
  /**
   * {@link Map}.
   */
  private final Map<String, String> data;
  /**
   * the flag if the section was found.
   */
  private boolean sectionFound;
  /**
   * the flag if the index was found.
   */
  private boolean indexFound;
  /**
   * {@link StringBuilder}.
   */
  private final StringBuilder tmp = new StringBuilder();

  /**
   * Constructor of Handler to parse METS files.
   *
   * @param section
   *          the {@link String} to section to parse
   * @param element
   *          the {@link String} the element we want to get the value of
   * @param data
   *          the {@link Map} where the values are stored
   */
  public MetsHandler(final String section, final String element, final Map<String, String> data) {
    this.section = section;
    this.element = element;
    this.data = data;
  }

  @Override
  public void startElement(final String uri, final String localName, final String qName, final Attributes attributes) {
    if (localName.equals(section)) {
      sectionFound = true;
    }

    if (localName.equals(element) && sectionFound) {
      String id = null;
      String type = null;
      String href = null;
      for (int i = 0; i < attributes.getLength(); i++) {
        final String attrUri = attributes.getURI(i);
        final String attName = attributes.getLocalName(i);
        final String attQName = attributes.getQName(i);

        final String value = attributes.getValue(i);
        if (attName.equals("ID")) {
          id = value;
        }
        if (attrUri.equals(SCHEMA) && attName.equals("href")) {
          href = value;
        }
        if (attrUri.equals(SCHEMA) && attName.equals("type")) {
          type = value;
        }
      }
      if (id != null) {
        data.put(id, type);
      } else {
        data.put(href, type);
      }
    }
  }

  @Override
  public void endElement(final String uri, final String localName, final String qName) throws SAXException {
    if (localName.equals(section)) {
      sectionFound = false;
    }
  }

  @Override
  public void characters(final char[] ch, final int start, final int length) {
    if (indexFound) {
      tmp.append(ch, start, length);
    }
  }
}
