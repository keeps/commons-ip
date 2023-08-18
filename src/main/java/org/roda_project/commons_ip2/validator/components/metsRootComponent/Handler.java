package org.roda_project.commons_ip2.validator.components.metsRootComponent;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;

import org.roda_project.commons_ip2.validator.common.ZipManager;
import org.roda_project.commons_ip2.validator.utils.XMLUtils;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * @author Carlos Afonso <cafonso@keep.pt>
 */
public class Handler {
  public static String getNode(Path xmlMetsFile, String path, String xpathExpression, String data) {
    NodeList nodes;
    String finalPath = (new StringBuilder()).append(path).append("METS.xml").toString();
    ZipManager zipFileManagerStrategy = new ZipManager();
    try (InputStream is = zipFileManagerStrategy.getZipInputStream(xmlMetsFile, finalPath)) {
      nodes = (NodeList) XMLUtils.getXPathResult(is, xpathExpression, XPathConstants.NODESET, data);
    } catch (IOException | ParserConfigurationException | SAXException | XPathExpressionException e) {
      throw new RuntimeException(e);
    }

    if (nodes.getLength() == 0) {
      return null;
    }
    return nodes.item(0).getNodeValue();
  }

  public static void getHeader(Path xmlMetsFile, String path) {
    NodeList nodes;
    String finalPath = (new StringBuilder()).append(path).append("METS.xml").toString();
    ZipManager zipFileManagerStrategy = new ZipManager();
    try (InputStream is = zipFileManagerStrategy.getZipInputStream(xmlMetsFile, finalPath)) {
      nodes = (NodeList) XMLUtils.getXPathResult(is, "/mets:mets/mets:metsHdr", XPathConstants.NODESET, "metadata");
    } catch (IOException | ParserConfigurationException | SAXException | XPathExpressionException e) {
      throw new RuntimeException(e);
    }

  }
}
