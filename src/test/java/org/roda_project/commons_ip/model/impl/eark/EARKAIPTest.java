package org.roda_project.commons_ip.model.impl.eark;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.Test;
import org.roda_project.commons_ip.model.AIP;
import org.roda_project.commons_ip.model.IPConstants;
import org.roda_project.commons_ip.model.IPRepresentation;
import org.roda_project.commons_ip.model.ParseException;
import org.roda_project.commons_ip.model.impl.roda.RodaFolderAIP;
import org.roda_project.commons_ip.utils.IPException;

/**
 * Test class for {@link EARKAIP}.
 * 
 * @author Rui Castro (rui.castro@gmail.com)
 */
public class EARKAIPTest {

  @Test
  public void writesAIPToDir()
    throws IOException, URISyntaxException, ParseException, InterruptedException, IPException {
    URI resource = getClass().getResource("/").toURI();
    final Path inputPath = Paths.get(resource).resolve("aip").resolve("eark")
      .resolve("IDec234adb-fe77-4a60-bdac-c41c21fd68ed");
    AIP aip = EARKAIP.parse(inputPath);
    Assert.assertTrue(aip.isValid());
    Path outputPath = Files.createTempDirectory("aip-output");
    Path aipPath = aip.build(outputPath);
    Assert.assertTrue(Files.isDirectory(aipPath));
    Assert.assertTrue(Files.isRegularFile(aipPath.resolve("METS.xml")));
    FileUtils.deleteDirectory(outputPath.toFile());
  }

  @Test
  public void writesOnlyMets()
    throws URISyntaxException, ParseException, IOException, InterruptedException, IPException {
    URI resource = getClass().getResource("/").toURI();
    final Path inputPath = Paths.get(resource).resolve("aip").resolve("roda")
      .resolve("5408d7a3-9efd-4616-9609-7f335d976489");
    AIP aip = RodaFolderAIP.parse(inputPath);
    Path outputPath = Files.createTempDirectory("aip-output");

    Path aipPath = new EARKAIP(aip).build(outputPath, true);

    Assert.assertTrue(Files.isDirectory(aipPath));
    Assert.assertTrue(Files.isRegularFile(aipPath.resolve("METS.xml")));

    Assert.assertEquals(1, aip.getRepresentations().size());
    if (aip.getRepresentations().size() == 1) {
      Assert.assertTrue(Files.isDirectory(aipPath.resolve(IPConstants.REPRESENTATIONS)));
      IPRepresentation rep = aip.getRepresentations().get(0);
      Path repPath = aipPath.resolve(IPConstants.REPRESENTATIONS).resolve(rep.getRepresentationID());
      Assert.assertTrue(Files.isRegularFile(repPath.resolve("METS.xml")));
      Assert.assertFalse(Files.isDirectory(repPath.resolve(IPConstants.METADATA)));
      Assert.assertFalse(Files.isDirectory(repPath.resolve(IPConstants.SCHEMAS)));
      Assert.assertFalse(Files.isDirectory(repPath.resolve(IPConstants.DOCUMENTATION)));
    }

    Assert.assertFalse(Files.isDirectory(aipPath.resolve(IPConstants.METADATA)));
    Assert.assertFalse(Files.isDirectory(aipPath.resolve(IPConstants.SCHEMAS)));
    Assert.assertFalse(Files.isDirectory(aipPath.resolve(IPConstants.DOCUMENTATION)));

    FileUtils.deleteDirectory(outputPath.toFile());
  }

}
