/**
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE file at the root of the source
 * tree and available online at
 *
 * https://github.com/keeps/commons-ip
 */
package org.roda_project.commons_ip2.utils;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

public class SAXSimpleErrorHandler extends DefaultHandler {
  List<SAXParseException> errors;

  public SAXSimpleErrorHandler() {
    errors = new ArrayList<>();
  }

  public List<SAXParseException> getErrors() {
    return errors;
  }

  public void setErrors(List<SAXParseException> errors) {
    this.errors = errors;
  }

  @Override
  public void warning(SAXParseException e) throws SAXException {
    errors.add(e);
  }

  @Override
  public void error(SAXParseException e) throws SAXException {
    errors.add(e);
  }

  @Override
  public void fatalError(SAXParseException e) throws SAXException {
    errors.add(e);
  }

}