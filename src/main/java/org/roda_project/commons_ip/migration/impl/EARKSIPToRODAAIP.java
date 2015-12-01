package org.roda_project.commons_ip.migration.impl;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;

import javax.xml.bind.JAXBException;

import org.roda_project.commons_ip.mets_v1_11.beans.AmdSecType;
import org.roda_project.commons_ip.mets_v1_11.beans.DivType;
import org.roda_project.commons_ip.mets_v1_11.beans.DivType.Fptr;
import org.roda_project.commons_ip.mets_v1_11.beans.FileType;
import org.roda_project.commons_ip.mets_v1_11.beans.FileType.FLocat;
import org.roda_project.commons_ip.mets_v1_11.beans.MdSecType;
import org.roda_project.commons_ip.mets_v1_11.beans.MdSecType.MdRef;
import org.roda_project.commons_ip.mets_v1_11.beans.Mets;
import org.roda_project.commons_ip.mets_v1_11.beans.StructMapType;
import org.roda_project.commons_ip.migration.MigrationException;
import org.roda_project.commons_ip.migration.Migrator;
import org.roda_project.commons_ip.migration.utils.RODAUtils;
import org.roda_project.commons_ip.model.impl.METSUtils;
import org.roda_project.commons_ip.utils.Utils;
import org.roda_project.commons_ip.validation.impl.EARKValidator;
import org.roda_project.commons_ip.validation.model.ValidationReport;

public class EARKSIPToRODAAIP implements Migrator {
  public Path convert(Path source) throws MigrationException {
    EARKValidator validator = new EARKValidator();
    ValidationReport report = validator.isSIPValid(source);
    // if (report.isValid()) {
    try {
      Path converted = earkSipToRodaAip(source);
      return converted;
    } catch (JAXBException | IOException e) {
      throw new MigrationException(e.getMessage(), e);
    }
    // } else {
    // throw new MigrationException();
    // }

  }

  private Path earkSipToRodaAip(Path source) throws JAXBException, IOException {
    Path dst = Files.createTempDirectory("rodaAIP");

    if (!Files.isDirectory(source)) {
      try {
        Path uncompressed = Files.createTempDirectory("unzipped");
        Utils.unzip(source, uncompressed);
        source = uncompressed;
      } catch (IOException e) {
        System.out.println("Error unzipping file: " + e.getMessage());
      }
    }
    Path mainMETSFile = source.resolve("METS.xml");
    Mets mainMets = METSUtils.processMetsXML(mainMETSFile);

    if (mainMets.getDmdSec() != null && mainMets.getDmdSec().size() > 0) {
      for (MdSecType mdsec : mainMets.getDmdSec()) {
        MdRef mdref = mdsec.getMdRef();
        String href = mdref.getHref();
        if (href.startsWith("file://.")) {
          href = href.replace("file://.", "");
        }
        Path filePath = source.resolve(href);
        RODAUtils.addDescriptiveMetadata(dst, filePath);
        System.out.println("Processing metadata: " + filePath.toString());
      }
    }
    if (mainMets.getAmdSec() != null && mainMets.getAmdSec().size() > 0) {
      for (AmdSecType amdsec : mainMets.getAmdSec()) {
        if (amdsec.getDigiprovMD() != null && amdsec.getDigiprovMD().size() > 0) {
          for (MdSecType digiProv : amdsec.getDigiprovMD()) {
            MdRef mdref = digiProv.getMdRef();
            String href = mdref.getHref();
            if (href.startsWith("file://.")) {
              href = href.replace("file://.", "");
            }
            Path filePath = source.resolve(href);
            RODAUtils.addPreservationMetadata(dst, filePath);
            System.out.println("Processing metadata: " + filePath.toString());
          }
        }
      }
    }
    Path representationsPath = source.resolve("representations");
    if (Files.exists(representationsPath)) {
      DirectoryStream.Filter<Path> filter = new DirectoryStream.Filter<Path>() {
        @Override
        public boolean accept(Path file) throws IOException {
          return (Files.isDirectory(file));
        }
      };
      try (DirectoryStream<Path> stream = Files.newDirectoryStream(representationsPath, filter)) {
        for (Path path : stream) {
          System.out.println("Processing: " + path.toString());
          Path representationMets = path.resolve("METS.xml");
          processRepresentation(dst, representationMets, path.getFileName().toString());
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    return dst;
  }

  private void processRepresentation(Path dst, Path representationMetsPath, String representationID)
    throws JAXBException {
    System.out.println("Processing representation: " + representationMetsPath.toAbsolutePath().toString());
    Path representationPath = representationMetsPath.getParent();
    try {
      Mets representationMets = METSUtils.processMetsXML(representationMetsPath);
      StructMapType structMap = representationMets.getStructMap().get(0);
      DivType packageDiv = structMap.getDiv();
      if (packageDiv.getDiv() != null && packageDiv.getDiv().size() > 0) {
        for (DivType dt : packageDiv.getDiv()) {
          if (dt.getDivTypeLabel() != null) {
            if (dt.getDivTypeLabel().equalsIgnoreCase("Content")) {
              if (dt.getFptr() != null && dt.getFptr().size() > 0) {
                for (Fptr f : dt.getFptr()) {
                  FileType ft = (FileType) f.getFILEID();
                  Path p = extractPathFromFile(ft, representationPath);
                  System.out.println("DATA:" + p.toString());
                  RODAUtils.addRepresentationData(dst, p, representationID);
                }
              }
            } else if (dt.getDivTypeLabel().equalsIgnoreCase("Schemas")) {
              if (dt.getFptr() != null && dt.getFptr().size() > 0) {
                for (Fptr f : dt.getFptr()) {
                  FileType ft = (FileType) f.getFILEID();
                  Path p = extractPathFromFile(ft, representationPath);
                  System.out.println("SCHEMA:" + p.toString());
                  RODAUtils.addRepresentationSchema(dst, p, representationID);
                }
              }
            } else if (dt.getDivTypeLabel().equalsIgnoreCase("Metadata")) {
              if (dt.getFptr() != null && dt.getFptr().size() > 0) {
                for (Fptr f : dt.getFptr()) {
                  FileType ft = (FileType) f.getFILEID();
                  Path p = extractPathFromFile(ft, representationPath);
                  System.out.println("P:" + p.toString());
                  RODAUtils.addRepresentationMetadata(dst, p, representationID);
                }
              }
            }
          }
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private Path extractPathFromFile(FileType ft, Path representationPath) {
    Path p = null;
    if (ft.getFLocat() != null && ft.getFLocat().size() > 0) {
      for (FLocat flocat : ft.getFLocat()) {
        System.out.println("LOCAT: " + flocat.getHref());
        if (flocat.getHref() != null && flocat.getHref().startsWith("file://.")) {
          String href = flocat.getHref();
          href = href.substring(7);
          p = representationPath.resolve(flocat.getHref().replace("file://.", ""));
          System.out.println("FP: " + p.toString());
        }
      }
    }
    return p;
  }

}
