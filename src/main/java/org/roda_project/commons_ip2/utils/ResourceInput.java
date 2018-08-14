/**
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE file at the root of the source
 * tree and available online at
 *
 * https://github.com/keeps/commons-ip
 */
package org.roda_project.commons_ip2.utils;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.ls.LSInput;

public class ResourceInput implements LSInput {
  private static final Logger LOGGER = LoggerFactory.getLogger(ResourceInput.class.getName());
  private BufferedInputStream inputStream;
  private String publicId;
  private String systemId;

  public ResourceInput(String publicId, String sysId, InputStream input) {
    this.publicId = publicId;
    this.systemId = sysId;
    this.inputStream = new BufferedInputStream(input);
  }

  @Override
  public String getPublicId() {
    return publicId;
  }

  @Override
  public void setPublicId(String publicId) {
    this.publicId = publicId;
  }

  @Override
  public String getBaseURI() {
    return null;
  }

  @Override
  public InputStream getByteStream() {
    return null;
  }

  @Override
  public boolean getCertifiedText() {
    return false;
  }

  @Override
  public Reader getCharacterStream() {
    return null;
  }

  @Override
  public String getEncoding() {
    return null;
  }

  @Override
  public String getStringData() {
    synchronized (inputStream) {
      try {
        byte[] input = new byte[inputStream.available()];
        inputStream.read(input);
        return new String(input);
      } catch (IOException e) {
        LOGGER.error("Unable to get string", e);
        return null;
      } finally {
        try {
          inputStream.close();
        } catch (IOException e) {
          LOGGER.error("Error closing stream", e);
        }
      }
    }
  }

  @Override
  public void setBaseURI(String baseURI) {
    // do nothing
  }

  @Override
  public void setByteStream(InputStream byteStream) {
    // do nothing
  }

  @Override
  public void setCertifiedText(boolean certifiedText) {
    // do nothing
  }

  @Override
  public void setCharacterStream(Reader characterStream) {
    // do nothing
  }

  @Override
  public void setEncoding(String encoding) {
    // do nothing
  }

  @Override
  public void setStringData(String stringData) {
    // do nothing
  }

  @Override
  public String getSystemId() {
    return systemId;
  }

  @Override
  public void setSystemId(String systemId) {
    this.systemId = systemId;
  }

  public BufferedInputStream getInputStream() {
    return inputStream;
  }

  public void setInputStream(BufferedInputStream inputStream) {
    this.inputStream = inputStream;
  }
}
