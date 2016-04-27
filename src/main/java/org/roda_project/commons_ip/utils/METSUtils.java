/**
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE file at the root of the source
 * tree and available online at
 *
 * https://github.com/keeps/commons-ip
 */
package org.roda_project.commons_ip.utils;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;
import javax.xml.bind.Unmarshaller;

import org.roda_project.commons_ip.mets_v1_11.beans.Mets;

public final class METSUtils {

  private METSUtils() {

  }

  public static Mets instantiateMETSFromFile(Path metsFile) throws JAXBException {
    JAXBContext jaxbContext = JAXBContext.newInstance(Mets.class);
    Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
    return (Mets) jaxbUnmarshaller.unmarshal(metsFile.toFile());
  }

  public static Path marshallMETS(Mets mets, Path tempMETSFile, boolean rootMETS)
    throws JAXBException, IOException, PropertyException, SIPException {
    JAXBContext context = JAXBContext.newInstance(Mets.class);
    Marshaller m = context.createMarshaller();
    m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
    if (rootMETS) {
      m.setProperty(Marshaller.JAXB_SCHEMA_LOCATION,
        "http://www.loc.gov/METS/ schemas/IP.xsd http://www.w3.org/1999/xlink schemas/xlink.xsd");
    } else {
      m.setProperty(Marshaller.JAXB_SCHEMA_LOCATION,
        "http://www.loc.gov/METS/ ../../schemas/IP.xsd http://www.w3.org/1999/xlink ../../schemas/xlink.xsd");
    }
    OutputStream metsOutputStream = Files.newOutputStream(tempMETSFile);
    m.marshal(mets, metsOutputStream);
    metsOutputStream.close();

    return tempMETSFile;
  }
}
