package org.roda_project.commons_ip2.validator.common;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.roda_project.commons_ip2.validator.handlers.ControlledVocabularyHandler;
import org.xml.sax.SAXException;

/** {@author João Gomes <jgomes@keep.pt>}. */
public final class ControlledVocabularyParser {

  private ControlledVocabularyParser() {
    // do nothing
  }

  /**
   * Parse tags Term from XML files.
   *
   * @param resourcePath
   *          the {@link String} to resource.
   * @return a {@link List} with all values from Term tag.
   * @throws IOException
   *           if some I/O error occurs.
   * @throws SAXException
   *           if some error occurs.
   * @throws ParserConfigurationException
   *           if some error occurs.
   */
  public static List<String> parse(final String resourcePath)
    throws IOException, SAXException, ParserConfigurationException {
    final SAXParserFactory factory = SAXParserFactory.newInstance();
    final List<String> data = new ArrayList<>();
    final SAXParser saxParser = factory.newSAXParser();
    final ControlledVocabularyHandler controlledVocabularyHandler = new ControlledVocabularyHandler("Term", data);
    final InputStream stream = ControlledVocabularyParser.class.getResourceAsStream(resourcePath);
    saxParser.parse(stream, controlledVocabularyHandler);
    return data;
  }
}
