package org.roda_project.commons_ip2.utils;

import org.apache.commons.logging.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Carlos Afonso <cafonso@keep.pt>
 */
public class LogSystem {

  private LogSystem() {
    //empty constructor
  }

  private static final Logger LOGGER = LoggerFactory.getLogger(LogSystem.class);
  public static final String UNKNOWN = "unknown";

  private static HashMap<String, String> getOperatingSystemInfo() {
    LinkedHashMap<String, String> result = new LinkedHashMap<>();

    result.put("Operating system", System.getProperty("os.name", UNKNOWN));
    result.put("Architecture", System.getProperty("os.arch", UNKNOWN));
    result.put("Version", System.getProperty("os.version", UNKNOWN));
    result.put("Java vendor", System.getProperty("java.vendor", UNKNOWN));
    result.put("Java version", System.getProperty("java.version", UNKNOWN));
    result.put("Java class version", System.getProperty("java.class.version", UNKNOWN));
    // Charset.defaultCharset() is bugged on java version 5 and fixed on java 6
    result.put("Default Charset reported by java", Charset.defaultCharset().toString());
    result.put("Default Charset used by StreamWriter", getDefaultCharSet());
    result.put("file.encoding property", System.getProperty("file.encoding"));

    return result;
  }

  private static String getDefaultCharSet() {
    OutputStreamWriter dummyWriter = new OutputStreamWriter(new ByteArrayOutputStream());
    return dummyWriter.getEncoding();
  }

  public static void logOperatingSystemInfo() {
    for (Map.Entry<String, String> entry : getOperatingSystemInfo().entrySet()) {
      LOGGER.debug("{}: {}",entry.getKey(), entry.getValue());
    }
  }
}
