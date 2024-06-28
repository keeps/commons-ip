/**
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE file at the root of the source
 * tree and available online at
 *
 * https://github.com/keeps/commons-ip
 */
package org.roda_project.commons_ip2.utils;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.roda_project.commons_ip.utils.ResourceInput;
import org.roda_project.commons_ip2.model.IPConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.ls.LSInput;
import org.w3c.dom.ls.LSResourceResolver;

public class ResourceResolver implements LSResourceResolver {
  private static final Logger LOGGER = LoggerFactory.getLogger(ResourceResolver.class.getName());

  // systemId > path in the resources (classpath)
  private static final Map<String, String> internalResources = new HashMap<>();
  static {
    internalResources.put(IPConstants.SCHEMA_XLINK_URL, IPConstants.SCHEMA_XLINK_FILENAME);
    internalResources.put(IPConstants.SCHEMA_METS_FILENAME_WITH_VERSION,
      IPConstants.SCHEMA_METS_RELATIVE_PATH_FROM_RESOURCES);
    internalResources.put(IPConstants.SCHEMA_EARK_CSIP_FILENAME,
      IPConstants.SCHEMA_EARK_CSIP_RELATIVE_PATH_FROM_RESOURCES);
    internalResources.put(IPConstants.SCHEMA_EARK_SIP_FILENAME,
      IPConstants.SCHEMA_EARK_SIP_RELATIVE_PATH_FROM_RESOURCES);
    internalResources.put(IPConstants.SCHEMA_XLINK_FILENAME, IPConstants.SCHEMA_XLINK_RELATIVE_PATH_FROM_RESOURCES);
  }

  @Override
  public LSInput resolveResource(String type, String namespaceURI, String publicId, String systemId, String baseURI) {
    InputStream resourceAsStream = null;
    if (internalResources.containsKey(systemId)) {
      resourceAsStream = this.getClass().getResourceAsStream(internalResources.get(systemId));
    } else {
      try {
        URL url = new URL(systemId);
        resourceAsStream = url.openStream();
      } catch (MalformedURLException e) {
        // the XSD's are expected to be in the root of the classpath
        resourceAsStream = this.getClass().getResourceAsStream(systemId);
        // we use this catch exception to check if the systemID is a URL or a
        // file, that's why we don't re-throw it or LOGGER it
      } catch (IOException e) {
        LOGGER.error("Can't get file from URL", e);
      }
    }
    return new ResourceInput(publicId, systemId, resourceAsStream);
  }

}
