/**
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE file at the root of the source
 * tree and available online at
 *
 * https://github.com/keeps/commons-ip
 */
package org.roda_project.commons_ip.utils;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.ls.LSInput;
import org.w3c.dom.ls.LSResourceResolver;

public class ResourceResolver implements LSResourceResolver {
  private static final Logger LOGGER = LoggerFactory.getLogger(ResourceResolver.class.getName());

  // systemId > path in the resources (classpath)
  private static final Map<String, String> internalResources = new HashMap<>();
  static {
    internalResources.put("http://www.loc.gov/standards/xlink/xlink.xsd", "schemas/xlink.xsd");
  }

  @Override
  public LSInput resolveResource(String type, String namespaceURI, String publicId, String systemId, String baseURI) {
    InputStream resourceAsStream = null;
    if (internalResources.containsKey(systemId)) {
      resourceAsStream = this.getClass().getClassLoader().getResourceAsStream(internalResources.get(systemId));
    } else {
      try {
        URL url = new URL(systemId);
        resourceAsStream = url.openStream();
      } catch (MalformedURLException e) {
        // the XSD's are expected to be in the root of the classpath
        resourceAsStream = this.getClass().getClassLoader().getResourceAsStream(systemId);
        // we use this catch exception to check if the systemID is a URL or a
        // file, that's why we don't re-throw it or LOGGER it
      } catch (IOException e) {
        LOGGER.error("Can't get file from URL", e);
      }
    }
    return new ResourceInput(publicId, systemId, resourceAsStream);
  }

}
