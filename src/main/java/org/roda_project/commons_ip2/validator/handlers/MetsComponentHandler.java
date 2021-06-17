package org.roda_project.commons_ip2.validator.handlers;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.util.HashMap;

/**
 * @author João Gomes <jgomes@keep.pt>
 */
public class MetsComponentHandler extends DefaultHandler {
  private StringBuilder tmp = new StringBuilder();
  private final String tag;
  private boolean found;
  private HashMap<String,String> data;

  public MetsComponentHandler(final String tag, final HashMap<String,String> data) {
    this.tag = tag;
    this.data = data;
  }

  @Override
  public void startElement(String uri, String localName, String qName, Attributes attributes){
    if (qName.equals(tag)) {
      found = true;
      for(int i=0; i<attributes.getLength(); i++){
        String value = attributes.getValue(i);
        String name = attributes.getLocalName(i);
        data.put(name,value);
      }
    }
  }
}
