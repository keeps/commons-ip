package org.roda_project.commons_ip2.cli.utils.CLI;

import org.apache.commons.lang3.StringUtils;
import org.roda_project.commons_ip2.validator.constants.Constants;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author Miguel Guimarães <mguimaraes@keep.pt>
 */
public final class ValidateCommandUtils {

  public static Path obtainReportPath(final Path sipPath, final String reportPathDir) {
    Path reportPath;
    final LocalDateTime localDateTime = LocalDateTime.now();
    final String date = localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

    final String reportName = sipPath.getFileName() + "_validation-report_" + date + ".json";

    Path path = Paths.get(reportPathDir);
    if (StringUtils.isNotBlank(reportPathDir) && Files.exists(path)) {
      reportPath = path.resolve(reportName);
    } else {
      reportPath = sipPath.normalize().toAbsolutePath().getParent().resolve(reportName);
    }

    return reportPath;
  }

  public static OutputStream createReportOutputStream(final Path reportPath) throws IOException {
    final Path outputFile = createReportFile(reportPath);
    OutputStream outputStream = null;
    if (outputFile != null) {
      outputStream = new BufferedOutputStream(new FileOutputStream(outputFile.toFile()));
    }
    return outputStream;
  }

  private static Path createReportFile(final Path reportPath) throws IOException {
    Path outputFile = reportPath;
    if (outputFile.toFile().exists()) {
      Files.deleteIfExists(outputFile);
    }
    try {
      Files.createFile(outputFile);
    } catch (final IOException e) {
      outputFile = Files.createTempFile(Constants.VALIDATION_REPORT_PREFIX, ".json");
    }
    return outputFile;
  }

  private ValidateCommandUtils() {
  }
}
