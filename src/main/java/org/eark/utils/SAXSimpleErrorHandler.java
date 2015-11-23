package org.eark.utils;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

public class SAXSimpleErrorHandler extends DefaultHandler {
  List<SAXParseException> errors;

  public SAXSimpleErrorHandler() {
    errors = new ArrayList<SAXParseException>();
  }

  public void warning(SAXParseException e) throws SAXException {
    errors.add(e);
  }

  public void error(SAXParseException e) throws SAXException {
    errors.add(e);
  }

  public void fatalError(SAXParseException e) throws SAXException {
    errors.add(e);
  }

  public List<SAXParseException> getErrors() {
    return errors;
  }

  public void setErrors(List<SAXParseException> errors) {
    this.errors = errors;
  }

}