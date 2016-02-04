/**
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE file at the root of the source
 * tree and available online at
 *
 * https://github.com/keeps/commons-ip
 */
package org.roda_project.commons_ip.utils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.UUID;

import javax.xml.bind.DatatypeConverter;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.roda_project.commons_ip.mets_v1_11.beans.MdSecType.MdRef;
import org.roda_project.commons_ip.model.IPConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class Utils {
  private static final Logger LOGGER = LoggerFactory.getLogger(Utils.class);

  private Utils() {
  }

  public static String generateRandomId() {
    return UUID.randomUUID().toString();
  }

  public static String extractedRelativePathFromHref(MdRef mdref) {
    return extractedRelativePathFromHref(mdref.getHref());
  }

  public static String extractedRelativePathFromHref(String href) {
    String res = href;
    if (res.startsWith(IPConstants.METS_FILE_URI_PREFIX)) {
      res = res.replace(IPConstants.METS_FILE_URI_PREFIX, "");
    }
    return res;
  }

  /**
   * Deletes a directory/file
   * 
   * @param path
   *          path to the directory/file that will be deleted. in case of a
   *          directory, if not empty, everything in it will be deleted as well.
   *
   * @throws IOException
   */
  public static void deletePath(Path path) throws IOException {
    if (path == null) {
      return;
    }

    try {
      Files.delete(path);

    } catch (DirectoryNotEmptyException e) {
      LOGGER.debug("Directory is not empty. Going to delete its content as well.");
      try {
        Files.walkFileTree(path, new SimpleFileVisitor<Path>() {
          @Override
          public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
            Files.delete(file);
            return FileVisitResult.CONTINUE;
          }

          @Override
          public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
            Files.delete(dir);
            return FileVisitResult.CONTINUE;
          }

        });
      } catch (IOException e1) {
        throw e1;
      }
    } catch (IOException e) {
      throw e;
    }
  }

  /**
   * Calculates checksum, closing the inputstream in the end.
   */
  public static String calculateChecksum(InputStream is, String algorithm)
    throws NoSuchAlgorithmException, IOException {
    MessageDigest digester = MessageDigest.getInstance(algorithm);
    byte[] block = new byte[4096];
    int length;
    while ((length = is.read(block)) > 0) {
      digester.update(block, 0, length);
    }

    is.close();

    return DatatypeConverter.printHexBinary(digester.digest());
  }

  public static XMLGregorianCalendar getCurrentCalendar() throws DatatypeConfigurationException {
    GregorianCalendar gcal = new GregorianCalendar();
    gcal.setTime(new Date());
    XMLGregorianCalendar calendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(gcal);
    return calendar;
  }

}
