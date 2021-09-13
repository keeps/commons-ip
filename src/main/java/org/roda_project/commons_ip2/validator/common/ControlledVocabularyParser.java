package org.roda_project.commons_ip2.validator.common;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
  private final List<String> data;
  private final String path;

  public ControlledVocabularyParser(String path, List<String> data) {
    this.path = path;
    this.data = data;
  }

  public List<String> getData() {
    return data;
  }

  public List<String> getFilteredData(String filter) {
    List<String> newData = new ArrayList<>();
    newData = data.stream().filter(d -> !d.equals(filter)).collect(Collectors.toList());
    return newData;
  }

  public void parse() {
    SAXParserFactory factory = SAXParserFactory.newInstance();
    try {
      SAXParser saxParser = factory.newSAXParser();
      ControlledVocabularyHandler controlledVocabularyHandler = new ControlledVocabularyHandler("Term", data);
      InputStream stream = new FileInputStream(path);
      saxParser.parse(stream, controlledVocabularyHandler);
    } catch (ParserConfigurationException | SAXException | IOException e) {
      LOGGER.error("Could not parse content category file.", e);
    }
  }
}
