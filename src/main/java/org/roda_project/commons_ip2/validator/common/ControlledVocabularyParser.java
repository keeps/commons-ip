package org.roda_project.commons_ip2.validator.common;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.roda_project.commons_ip2.validator.handlers.ControlledVocabularyHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;

/**
 * @author João Gomes <jgomes@keep.pt>
 */
public class ControlledVocabularyParser {
  private static final Logger LOGGER = LoggerFactory.getLogger(ControlledVocabularyParser.class);

  public static List<String> parse(String resourcePath) throws IOException, SAXException, ParserConfigurationException {
    SAXParserFactory factory = SAXParserFactory.newInstance();
    List<String> data = new ArrayList<>();
      SAXParser saxParser = factory.newSAXParser();
      ControlledVocabularyHandler controlledVocabularyHandler = new ControlledVocabularyHandler("Term", data);
      InputStream stream = ClassLoader.getSystemResourceAsStream(resourcePath);
      saxParser.parse(stream, controlledVocabularyHandler);
      return data;
  }
}
