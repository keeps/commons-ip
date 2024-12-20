/**
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE file at the root of the source
 * tree and available online at
 *
 * https://github.com/keeps/commons-ip
 */
package org.roda_project.commons_ip.model.impl.eark;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Collections;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.junit.platform.commons.support.ReflectionSupport;
import org.roda_project.commons_ip.model.AIP;
import org.roda_project.commons_ip.model.IPConstants;
import org.roda_project.commons_ip.model.IPRepresentation;
import org.roda_project.commons_ip.model.ParseException;
import org.roda_project.commons_ip.model.impl.BasicAIP;
import org.roda_project.commons_ip.utils.IPException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Test class for {@link EARKAIP}.
 * 
 * @author Rui Castro (rui.castro@gmail.com)
 */
public class EARKAIPTest {

  private static final Logger LOGGER = LoggerFactory.getLogger(EARKAIPTest.class);

  private void copyResourceDirectoryToTemp(String resourceDir, Path tempDir) {
        ReflectionSupport.findAllResourcesInPackage(resourceDir, r -> true).forEach(r -> {
          System.out.println(r.getName());
          Files.createDirectories(tempDir, null);
        });  
  }

  @Test
  public void writesAIPToDir(@TempDir Path inputPath)
    throws IOException, URISyntaxException, ParseException, InterruptedException, IPException {
    
    copyResourceDirectoryToTemp("/aip/eark/IDec234adb-fe77-4a60-bdac-c41c21fd68ed", inputPath);
    
    AIP aip = EARKAIP.parse(inputPath);
    Assertions.assertTrue(aip.isValid());

    Path outputPath = Files.createTempDirectory("aip-output");

    Path aipPath = aip.build(outputPath);

    Assertions.assertTrue(Files.isDirectory(aipPath));
    Assertions.assertTrue(Files.isRegularFile(aipPath.resolve("METS.xml")));

    Assertions.assertEquals(2, aip.getRepresentations().size());
    if (aip.getRepresentations().size() == 1) {
      Assertions.assertTrue(Files.isDirectory(aipPath.resolve(IPConstants.REPRESENTATIONS)));

      IPRepresentation rep1 = aip.getRepresentations().get(0);
      Path rep1Path = aipPath.resolve(IPConstants.REPRESENTATIONS).resolve(rep1.getRepresentationID());
      Assertions.assertTrue(Files.isRegularFile(rep1Path.resolve("METS.xml")));
      Assertions.assertTrue(Files.isDirectory(rep1Path.resolve(IPConstants.METADATA)));
      Assertions.assertTrue(Files.isDirectory(rep1Path.resolve(IPConstants.SCHEMAS)));
      Assertions.assertTrue(Files.isDirectory(rep1Path.resolve(IPConstants.DOCUMENTATION)));
      Path rep1DataPath = rep1Path.resolve(IPConstants.DATA);
      Assertions.assertTrue(Files.isDirectory(rep1DataPath));
      Assertions.assertTrue(Files.isRegularFile(rep1DataPath.resolve("sakila-data.sql")));
      Assertions.assertTrue(Files.isRegularFile(rep1DataPath.resolve("sakila-schema.sql")));

      IPRepresentation rep2 = aip.getRepresentations().get(1);
      Path rep2Path = aipPath.resolve(IPConstants.REPRESENTATIONS).resolve(rep2.getRepresentationID());
      Assertions.assertTrue(Files.isRegularFile(rep2Path.resolve("METS.xml")));
      Assertions.assertTrue(Files.isDirectory(rep2Path.resolve(IPConstants.METADATA)));
      Assertions.assertTrue(Files.isDirectory(rep2Path.resolve(IPConstants.SCHEMAS)));
      Assertions.assertTrue(Files.isDirectory(rep2Path.resolve(IPConstants.DOCUMENTATION)));
      Path rep2DataPath = rep2Path.resolve(IPConstants.DATA);
      Assertions.assertTrue(Files.isDirectory(rep2DataPath));
      Assertions.assertTrue(Files.isRegularFile(rep2DataPath.resolve("sakila.siard2")));
    }

    Assertions.assertTrue(Files.isDirectory(aipPath.resolve(IPConstants.METADATA)));
    Assertions.assertTrue(Files.isDirectory(aipPath.resolve(IPConstants.SCHEMAS)));
    Assertions.assertTrue(Files.isDirectory(aipPath.resolve(IPConstants.DOCUMENTATION)));
    Assertions.assertTrue(Files.isDirectory(aipPath.resolve(IPConstants.SUBMISSION)));

    FileUtils.deleteDirectory(outputPath.toFile());
  }

  @Test
  public void writesOnlyMets()
    throws URISyntaxException, ParseException, IOException, InterruptedException, IPException {
    URI resource = getClass().getResource("/").toURI();
    final Path inputPath = Paths.get(resource).resolve("aip").resolve("eark")
      .resolve("IDec234adb-fe77-4a60-bdac-c41c21fd68ed");
    AIP aip = EARKAIP.parse(inputPath);
    Path outputPath = Files.createTempDirectory("aip-output");

    Path aipPath = new EARKAIP(aip).build(outputPath, true);

    Assertions.assertTrue(Files.isDirectory(aipPath));
    Assertions.assertTrue(Files.isRegularFile(aipPath.resolve("METS.xml")));

    Assertions.assertEquals(2, aip.getRepresentations().size());
    if (aip.getRepresentations().size() == 2) {
      Assertions.assertTrue(Files.isDirectory(aipPath.resolve(IPConstants.REPRESENTATIONS)));
      IPRepresentation rep = aip.getRepresentations().get(0);
      Path repPath = aipPath.resolve(IPConstants.REPRESENTATIONS).resolve(rep.getRepresentationID());
      Assertions.assertTrue(Files.isRegularFile(repPath.resolve("METS.xml")));
      Assertions.assertFalse(Files.isDirectory(repPath.resolve(IPConstants.METADATA)));
      Assertions.assertFalse(Files.isDirectory(repPath.resolve(IPConstants.SCHEMAS)));
      Assertions.assertFalse(Files.isDirectory(repPath.resolve(IPConstants.DOCUMENTATION)));
    }

    Assertions.assertFalse(Files.isDirectory(aipPath.resolve(IPConstants.METADATA)));
    Assertions.assertFalse(Files.isDirectory(aipPath.resolve(IPConstants.SCHEMAS)));
    Assertions.assertFalse(Files.isDirectory(aipPath.resolve(IPConstants.DOCUMENTATION)));
    Assertions.assertFalse(Files.isDirectory(aipPath.resolve(IPConstants.SUBMISSION)));

    FileUtils.deleteDirectory(outputPath.toFile());
  }

  @Test
  public void writesToExistingDir() throws IOException, InterruptedException, IPException {
    Path outputPath = Files.createTempDirectory("aip-output");

    Path aipPath = outputPath.resolve("5408d7a3-9efd-4616-9609-7f335d976489");
    Files.createDirectory(aipPath);
    aipPath.resolve(".placeholder").toFile().createNewFile();

    new EARKAIP(new BasicAIP("5408d7a3-9efd-4616-9609-7f335d976489")).build(outputPath);

    FileUtils.deleteDirectory(outputPath.toFile());
  }

}
