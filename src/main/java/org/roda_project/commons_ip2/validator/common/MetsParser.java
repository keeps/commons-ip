package org.roda_project.commons_ip2.validator.common;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * @author João Gomes <jgomes@keep.pt>
 */
public class MetsParser {
  private static final Logger LOGGER = LoggerFactory.getLogger(MetsParser.class);

  public MetsParser() {
  }

  public void parse(DefaultHandler handler, InputStream stream) {
    SAXParserFactory factory = SAXParserFactory.newInstance();
    try {
      SAXParser saxParser = factory.newSAXParser();
      saxParser.parse(stream, handler);
    } catch (ParserConfigurationException | SAXException | IOException e) {
      LOGGER.error("Could not parse file.", e);
    }
  }
}
