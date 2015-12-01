/**
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE file at the root of the source
 * tree and available online at
 *
 * https://github.com/keeps/commons-ip
 */
package org.roda_project.commons_ip.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.BasicFileAttributes;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.DatatypeConverter;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class Utils {
  private static FileSystem createZipFileSystem(Path zip, boolean create) throws IOException {

    // final URI uri = URI.create("jar:file:" + zip.toUri().getPath());
    final URI uri = URI.create("jar:file:" + zip.toUri().getPath().replaceAll(" ", "%20"));

    final Map<String, String> env = new HashMap<String, String>();
    if (create) {
      env.put("create", "true");
    }
    return FileSystems.newFileSystem(uri, env);
  }

  public static void unzip(Path zip, final Path dest) throws IOException {

    // if the destination doesn't exist, create it
    if (Files.notExists(dest)) {
      Files.createDirectories(dest);
    }

    try (FileSystem zipFileSystem = createZipFileSystem(zip, false)) {
      final Path root = zipFileSystem.getPath("/");

      // walk the zip file tree and copy files to the destination
      Files.walkFileTree(root, new SimpleFileVisitor<Path>() {
        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
          final Path destFile = Paths.get(dest.toString(), file.toString());
          Files.copy(file, destFile, StandardCopyOption.REPLACE_EXISTING);
          return FileVisitResult.CONTINUE;
        }

        @Override
        public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
          final Path dirToCreate = Paths.get(dest.toString(), dir.toString());
          if (Files.notExists(dirToCreate)) {
            Files.createDirectory(dirToCreate);
          }
          return FileVisitResult.CONTINUE;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static String calculateChecksum(InputStream is, String algorithm)
    throws NoSuchAlgorithmException, IOException {
    MessageDigest digester = MessageDigest.getInstance(algorithm);
    byte[] block = new byte[4096];
    int length;
    while ((length = is.read(block)) > 0) {
      digester.update(block, 0, length);
    }

    return DatatypeConverter.printHexBinary(digester.digest());
  }

  public static List<String> validateXML(Path xmlFile, Path schemaFile) throws IOException, SAXException {
    InputStream schemaStream = Files.newInputStream(schemaFile);
    Source xmlSource = new StreamSource(Files.newInputStream(xmlFile));
    SchemaFactory schemaFactory = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");
    Schema schema = schemaFactory.newSchema(new StreamSource(schemaStream));
    Validator validator = schema.newValidator();
    SAXSimpleErrorHandler errorHandler = new SAXSimpleErrorHandler();
    validator.setErrorHandler(errorHandler);
    try {
      validator.validate(xmlSource);
      List<SAXParseException> errors = errorHandler.getErrors();
    } catch (SAXException se) {

    }
    List<String> errors = new ArrayList<String>();
    if (errorHandler.getErrors().size() > 0) {
      for (SAXParseException spe : errorHandler.getErrors()) {
        errors.add(spe.getMessage());
      }
    }
    return errors;
  }

  public static XMLGregorianCalendar getCurrentCalendar() throws DatatypeConfigurationException {
    GregorianCalendar gcal = new GregorianCalendar();
    gcal.setTime(new Date());
    XMLGregorianCalendar xgcal = DatatypeFactory.newInstance().newXMLGregorianCalendar(gcal);
    return xgcal;
  }

  public static void addSchemaLocationToPath(Path metadata, String schemaLocation) {
    try {
      DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
      DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
      Document document = documentBuilder.parse(Files.newInputStream(metadata));
      document.getDocumentElement().setAttribute("xsi:schemaLocation", schemaLocation);
      ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
      Source xmlSource = new DOMSource(document);
      Result outputTarget = new StreamResult(outputStream);
      TransformerFactory.newInstance().newTransformer().transform(xmlSource, outputTarget);
      InputStream is = new ByteArrayInputStream(outputStream.toByteArray());
      Files.copy(is, metadata, StandardCopyOption.REPLACE_EXISTING);
    } catch (SAXException e) {
      e.printStackTrace();
    } catch (ParserConfigurationException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } catch (TransformerConfigurationException e) {
      e.printStackTrace();
    } catch (TransformerException e) {
      e.printStackTrace();
    } catch (TransformerFactoryConfigurationError e) {
      e.printStackTrace();
    }
  }

  public static void addFileToZip(Path zipPath, Path path, String internalPath) throws IOException {
    URI p = zipPath.toUri();
    URI uri = URI.create("jar:" + p);
    Map<String, String> env = new HashMap<>();
    env.put("create", Files.exists(zipPath) ? "false" : "true");
    try (FileSystem zipfs = FileSystems.newFileSystem(uri, env)) {
      Path internalTargetPath = zipfs.getPath(internalPath);
      Files.createDirectories(internalTargetPath.getParent());
      Files.copy(path, internalTargetPath, StandardCopyOption.REPLACE_EXISTING);
    }
  }

  public static Path getCopyFromZip(Path zipPath, String path) throws IOException {
    Path copy = Files.createTempFile("schema", ".xsd");
    URI p = zipPath.toUri();
    URI uri = URI.create("jar:" + p);
    Map<String, String> env = new HashMap<>();
    env.put("create", Files.exists(zipPath) ? "false" : "true");
    try (FileSystem zipfs = FileSystems.newFileSystem(uri, env)) {
      Files.copy(zipfs.getPath(path), copy, StandardCopyOption.REPLACE_EXISTING);
    }
    return copy;
  }
}
