package org.roda_project.commons_ip2.validator.handlers;

import java.util.HashMap;
import java.util.Map;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * @author João Gomes <jgomes@keep.pt>
 */

public class MetsHandler extends DefaultHandler {
  private final String section;
  private final String element;
  private final Map<String, String> data;
  private boolean indexFound;
  private boolean sectionFound;
  private final StringBuilder tmp = new StringBuilder();

  public MetsHandler(String section, String element, Map<String, String> data) {
    this.section = section;
    this.element = element;
    this.data = data;
  }

  @Override
  public void startElement(String uri, String localName, String qName, Attributes attributes) {
    if (qName.equals(section)) {
      sectionFound = true;
    }

    if (qName.equals(element) && sectionFound) {
      String id = null;
      String type = null;
      String href = null;
      for (int i = 0; i < attributes.getLength(); i++) {
        String attrUri = attributes.getURI(i);
        String attName = attributes.getLocalName(i);
        String attQName = attributes.getQName(i);

        String value = attributes.getValue(i);
        if (attName.equals("ID")) {
          id = value;
        }
        if (attrUri.equals("http://www.w3.org/1999/xlink") && attName.equals("href")) {
          href = value;
        }
        if (attrUri.equals("http://www.w3.org/1999/xlink") && attName.equals("type")) {
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
  public void endElement(String uri, String localName, String qName) throws SAXException {
    if (qName.equals(section)) {
      sectionFound = false;
    }
  }

  @Override
  public void characters(char[] ch, int start, int length) {
    if (indexFound) {
      tmp.append(ch, start, length);
    }
  }

}
