package org.roda_project.commons_ip2.model.impl.eark;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.roda_project.commons_ip.model.ParseException;
import org.roda_project.commons_ip.utils.IPException;
import org.roda_project.commons_ip2.model.IPFileInterface;
import org.roda_project.commons_ip2.model.SIP;
import org.roda_project.commons_ip2.utils.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * {@author João Gomes <jgomes@keep.pt>}.
 */
public class ParseEARK2STest {
  private static final String REPRESENTATION_STATUS_NORMALIZED = "NORMALIZED";
  private static final Logger LOGGER = LoggerFactory.getLogger(ParseEARK2STest.class);

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
  public void buildEARKSip2withFolders() throws IPException, InterruptedException, ParseException {
    LOGGER.info("Creating EARK SIP 2");
    Path zipSIPS = Paths.get("src/test/resources/SIP-S/shallowFileFolderAndEmptyFolder.zip");
    EARKSIP earksip = new EARKSIP();
    // 1) invoke static method parse and that's it
    SIP earkSIP = earksip.parse(zipSIPS, tempFolder);

    List<IPFileInterface> files = earkSIP.getRepresentations().get(0).getData();
    Assert.assertEquals(4, files.size());
    LOGGER.info("Done creating full E-ARK SIP-S");
  }
}
