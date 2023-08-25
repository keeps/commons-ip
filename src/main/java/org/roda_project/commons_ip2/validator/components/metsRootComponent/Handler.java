package org.roda_project.commons_ip2.validator.components.metsRootComponent;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;

import org.roda_project.commons_ip2.validator.common.ZipManager;
import org.roda_project.commons_ip2.validator.utils.XMLUtils;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
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

  public static Boolean getHeader(Path xmlMetsFile, String path) {
    NodeList nodes;
    String finalPath = (new StringBuilder()).append(path).append("METS.xml").toString();
    ZipManager zipFileManagerStrategy = new ZipManager();
    try (InputStream is = zipFileManagerStrategy.getZipInputStream(xmlMetsFile, finalPath)) {
      nodes = (NodeList) XMLUtils.getXPathResult(is, "/mets:mets/mets:metsHdr", XPathConstants.NODESET, "metadata");
    } catch (IOException | ParserConfigurationException | SAXException | XPathExpressionException e) {
      throw new RuntimeException(e);
    }

    int length = nodes.item(0).getAttributes().getLength();

    if (length == 0) {
      return false;
    }

    return true;
  }

  public static List<Agent> getAgents(Path xmlMetsFile, String path) {
    NodeList nodes;
    String finalPath = (new StringBuilder()).append(path).append("METS.xml").toString();
    ZipManager zipFileManagerStrategy = new ZipManager();
    try (InputStream is = zipFileManagerStrategy.getZipInputStream(xmlMetsFile, finalPath)) {
      nodes = (NodeList) XMLUtils.getXPathResult(is, "/mets:mets/mets:metsHdr/mets:agent", XPathConstants.NODESET,
        "metadata");
    } catch (IOException | ParserConfigurationException | SAXException | XPathExpressionException e) {
      throw new RuntimeException(e);
    }

    int length = nodes.getLength();

    List list = new ArrayList<Agent>();

    for (int i = 0; i < length; i++) {

      Node node = nodes.item(i);
      Agent a = new Agent();
      Note note = new Note();

      a.setRole(node.getAttributes().getNamedItem("ROLE").getNodeValue());
      a.setTYPE(node.getAttributes().getNamedItem("TYPE").getNodeValue());
      a.setOTHERTYPE(node.getAttributes().getNamedItem("OTHERTYPE").getNodeValue());
      a.setName(XMLUtils.getChild((Element) node, "name"));
      note.setValue(XMLUtils.getChild((Element) node, "note"));
      note.setNOTETYPE(XMLUtils.getChildTextContext((Element) node, "note").toString());
      a.setNote(note);
      list.add(a);
    }

    return list;
  }
}
