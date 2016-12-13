/**
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE file at the root of the source
 * tree and available online at
 *
 * https://github.com/keeps/commons-ip
 */
package org.roda_project.commons_ip.model.impl.roda;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.junit.Assert;
import org.junit.Test;
import org.roda_project.commons_ip.model.AIP;
import org.roda_project.commons_ip.model.IPRepresentation;
import org.roda_project.commons_ip.model.ParseException;

/**
 * Test class for {@link RodaFolderAIP}.
 * 
 * @author Rui Castro (rui.castro@gmail.com)
 */
public class RodaFolderAIPTest {

  @Test
  public void readsAIPFromFolder() throws URISyntaxException, ParseException {
    URI resource = getClass().getResource("/").toURI();
    final Path inputPath = Paths.get(resource).resolve("aip").resolve("roda")
      .resolve("5408d7a3-9efd-4616-9609-7f335d976489");
    AIP aip = RodaFolderAIP.parse(inputPath);
    Assert.assertTrue(aip.isValid());

    Assert.assertEquals("5408d7a3-9efd-4616-9609-7f335d976489", aip.getId());

    Assert.assertEquals(1, aip.getDescriptiveMetadata().size());
    Assert.assertEquals(10, aip.getPreservationMetadata().size());
    Assert.assertEquals(0, aip.getOtherMetadata().size());
    Assert.assertEquals(3, aip.getSchemas().size());
    Assert.assertEquals(1, aip.getSubmissions().size());

    Assert.assertEquals(1, aip.getRepresentations().size());
    if (aip.getRepresentations().size() == 1) {
      IPRepresentation rep = aip.getRepresentations().get(0);
      Assert.assertEquals(0, rep.getDescriptiveMetadata().size());
      Assert.assertEquals(2, rep.getPreservationMetadata().size());
      Assert.assertEquals(1, rep.getOtherMetadata().size());
      Assert.assertEquals(0, rep.getSchemas().size());
      Assert.assertEquals(0, rep.getDocumentation().size());
    }
  }

}
