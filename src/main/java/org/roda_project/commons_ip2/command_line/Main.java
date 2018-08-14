/**
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE file at the root of the source
 * tree and available online at
 *
 * https://github.com/keeps/commons-ip
 */
package org.roda_project.commons_ip2.command_line;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import org.apache.commons.io.IOUtils;
import org.roda_project.commons_ip2.model.ParseException;
import org.roda_project.commons_ip2.model.SIP;
import org.roda_project.commons_ip2.model.impl.eark.EARKSIP;
import org.roda_project.commons_ip2.utils.Utils;

public final class Main {
  private Main() {

  }

  public static void main(String[] args) throws ParseException, IOException {
    if (args.length == 3) {
      OutputStream outputStream = null;
      try {
        String sipPath = args[0];
        String validationPath = args[1];
        boolean deleteExtractedSIP = Boolean.parseBoolean(args[2]);
        Path validationFile = Paths.get(validationPath);
        outputStream = Files.newOutputStream(validationFile, StandardOpenOption.CREATE,
          StandardOpenOption.TRUNCATE_EXISTING);
        PrintWriter printWriter = new PrintWriter(outputStream);
        SIP sip = EARKSIP.parse(Paths.get(sipPath));
        printWriter.print(sip.getValidationReport().toHtml());
        printWriter.flush();
        printWriter.close();
        if (deleteExtractedSIP) {
          Utils.deletePath(sip.getBasePath());
        }
      } finally {
        IOUtils.closeQuietly(outputStream);
      }
    } else {
      // no arguments provided
    }
  }
}
