package org.roda_project.commons_ip2.validator.handlers;

import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/** {@author João Gomes <jgomes@keep.pt>}. */
public class ControlledVocabularyHandler extends DefaultHandler {
  /**
   * The xml tag.
   */
  private final String tag;
  /**
   * The {@link List}.
   */
  private final List<String> data;
  /**
   * the flag if was found or not.
   */
  private boolean indexFound;
  /**
   * {@link StringBuilder}.
   */
  private final StringBuilder tmp = new StringBuilder();

  /**
   * The constructor.
   * 
   * @param tag
   *          the xml tag.
   * @param data
   *          {@link List}.
   */
  public ControlledVocabularyHandler(final String tag, final List<String> data) {
    this.tag = tag;
    this.data = data;
  }

  @Override
  public void startElement(final String uri, final String localName, final String qName, final Attributes attributes) {
    if (qName.equals(tag)) {
      indexFound = true;
      tmp.setLength(0);
    }
  }

  @Override
  public void endElement(final String uri, final String localName, final String qName) throws SAXException {
    if (qName.equals(tag)) {
      data.add(tmp.toString());
      indexFound = false;
    }
  }

  @Override
  public void characters(final char[] ch, final int start, final int length) {
    if (indexFound) {
      tmp.append(ch, start, length);
    }
  }
}
