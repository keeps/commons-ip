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
import org.roda_project.commons_ip.model.ParseException;
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

}
