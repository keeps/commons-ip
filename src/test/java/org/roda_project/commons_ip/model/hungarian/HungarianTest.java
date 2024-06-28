/**
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE file at the root of the source
 * tree and available online at
 *
 * https://github.com/keeps/commons-ip
 */
package org.roda_project.commons_ip.model.hungarian;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.roda_project.commons_ip.model.IPConstants;
import org.roda_project.commons_ip.model.IPContentType;
import org.roda_project.commons_ip.model.IPDescriptiveMetadata;
import org.roda_project.commons_ip.model.IPFile;
import org.roda_project.commons_ip.model.IPRepresentation;
import org.roda_project.commons_ip.model.MetadataType;
import org.roda_project.commons_ip.model.MetadataType.MetadataTypeEnum;
import org.roda_project.commons_ip.model.ParseException;
import org.roda_project.commons_ip.model.RepresentationStatus;
import org.roda_project.commons_ip.model.SIP;
import org.roda_project.commons_ip.model.impl.hungarian.HungarianSIP;
import org.roda_project.commons_ip.utils.IPException;
import org.roda_project.commons_ip.utils.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

/**
 * Unit tests for EARK Information Packages (SIP, AIP and DIP)
 */
public class HungarianTest {
  private static final Logger LOGGER = LoggerFactory.getLogger(HungarianTest.class);
  private static final String REPRESENTATION_STATUS_NORMALIZED = "NORMALIZED";
  private static final String SIP_ID = "SIP_1";
  private static Path tempFolder;

  @BeforeClass
  public static void setup() throws IOException {
    tempFolder = Files.createTempDirectory("temp");
  }

  @AfterClass
  public static void cleanup() throws Exception {
    Utils.deletePath(tempFolder);
  }

  @Test
  public void buildAndParseHungarianSIP() throws IPException, ParseException, InterruptedException, IOException,
    SAXException, ParserConfigurationException, XPathExpressionException {
    LOGGER.info("Creating full Hungarian SIP");
    createFullHungarianSIP();
    LOGGER.info("Done creating full Hungarian SIP");
    testZIPAndTxtContent();
    LOGGER.info("Done testing full Hungarian SIP");
  }

  private void testZIPAndTxtContent()
    throws IOException, SAXException, ParserConfigurationException, XPathExpressionException {
    Path zipPath = tempFolder.resolve(SIP_ID + ".zip");
    Path txtPath = tempFolder.resolve(SIP_ID + ".txt");

      Assert.assertTrue(Files.exists(zipPath) && Files.exists(txtPath));
    Assert.assertEquals(Files.lines(txtPath).count(), 6);

    try (ZipFile zipFile = new ZipFile(zipPath.toFile())) {
      Enumeration<? extends ZipEntry> entries = zipFile.entries();
      int metadataFileCounter = 0;
      int documentationPdfCounter = 0;

      while (entries.hasMoreElements()) {
        ZipEntry entry = entries.nextElement();
        if (entry.getName().contains(IPConstants.METADATA_FILE)) {
          metadataFileCounter++;
          try (InputStream stream = zipFile.getInputStream(entry)) {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            Document metsContent = factory.newDocumentBuilder().parse(stream);
            XPathFactory xPathfactory = XPathFactory.newInstance();
            XPath xpath = xPathfactory.newXPath();

            XPathExpression typeExpression = xpath.compile("//mdWrap/@MDTYPE");
            String mdType = (String) typeExpression.evaluate(metsContent, XPathConstants.STRING);

            XPathExpression versionExpression = xpath.compile("//mdWrap/@MDTYPEVERSION");
            String mdVersion = (String) versionExpression.evaluate(metsContent, XPathConstants.STRING);

            Assert.assertEquals(mdType, MetadataTypeEnum.EAD.toString());
            Assert.assertEquals(mdVersion, "2002");
          }
        } else if (entry.getName().contains("documentation.pdf")) {
          documentationPdfCounter++;
        }
      }

      Assert.assertEquals(metadataFileCounter, 1);
      Assert.assertEquals(documentationPdfCounter, 2);
    }
  }

  private Path createFullHungarianSIP() throws IPException, InterruptedException {
    // instantiate Hungarian SIP object
    SIP sip = new HungarianSIP(SIP_ID, IPContentType.getMIXED());
    sip.addCreatorSoftwareAgent("RODA Commons IP");

    // set optional human-readable description
    sip.setDescription("A full Hungarian SIP");

    // add descriptive metadata
    IPDescriptiveMetadata metadataDescriptiveEAD = new IPDescriptiveMetadata(
      new IPFile(Paths.get("src/test/resources/eark/metadata_descriptive_ead2002.xml")),
      new MetadataType(MetadataTypeEnum.EAD), "2002");
    sip.addDescriptiveMetadata(metadataDescriptiveEAD);

    // add documentation
    sip.addDocumentation(new IPFile(Paths.get("src/test/resources/eark/documentation.pdf")));

    // add a representation
    IPRepresentation representation1 = new IPRepresentation("representation 1");
    sip.addRepresentation(representation1);

    // add a file to the representation
    IPFile representationFile = new IPFile(Paths.get("src/test/resources/eark/documentation.pdf"));
    representationFile.setRenameTo("data.pdf");
    representation1.addFile(representationFile);

    // add a file to the representation and put it inside a folder
    // called 'abc' which has a folder inside called 'def'
    IPFile representationFile2 = new IPFile(Paths.get("src/test/resources/eark/documentation.pdf"));
    representationFile2.setRelativeFolders(Arrays.asList("abc", "def"));
    representation1.addFile(representationFile2);

    // add a representation & define its status
    IPRepresentation representation2 = new IPRepresentation("representation 2");
    representation2.setStatus(new RepresentationStatus(REPRESENTATION_STATUS_NORMALIZED));
    sip.addRepresentation(representation2);

    // add a file to the representation
    IPFile representationFile3 = new IPFile(Paths.get("src/test/resources/eark/documentation.pdf"));
    representationFile3.setRenameTo("data3.pdf");
    representation2.addFile(representationFile3);

    // build SIP, providing an output directory
    return sip.build(tempFolder);
  }

}
