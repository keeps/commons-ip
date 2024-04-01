package org.roda_project.commons_ip2.validator.common;

import java.io.InputStream;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.roda_project.commons_ip2.cli.model.exception.UnmarshallerException;
import org.roda_project.commons_ip2.mets_v1_12.beans.Mets;
import org.roda_project.commons_ip2.model.IPConstants;
import org.roda_project.commons_ip2.utils.METSUtils;
import org.roda_project.commons_ip2.utils.ResourceResolver;
import org.xml.sax.SAXException;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;

/** {@author João Gomes <jgomes@keep.pt>}. */
public class InstatiateMets {
  /**
   * {@link InputStream}.
   */
  private final InputStream stream;

  /**
   * Constructor that sets the {@link InputStream}.
   *
   * @param stream
   *          {@link InputStream}.
   */
  public InstatiateMets(final InputStream stream) {
    this.stream = stream;
  }

  /**
   * Creates the {@link Mets} object from METS file.
   *
   * @return the {@link Mets} object.
   * @throws UnmarshallerException
   *           if some schema or parse error occurs.
   */
  public Mets instatiateMetsFile(String file) throws UnmarshallerException {
    try {
      org.glassfish.jaxb.runtime.v2.JAXBContextFactory contextFactory = new org.glassfish.jaxb.runtime.v2.JAXBContextFactory();
      JAXBContext jaxbContext = contextFactory.createContext(new Class[] {Mets.class}, null);
      final Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
      final SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
      factory.setResourceResolver(new ResourceResolver());
      final InputStream metsSchemaInputStream = METSUtils.class
        .getResourceAsStream(IPConstants.SCHEMA_METS_RELATIVE_PATH_FROM_RESOURCES);
      final Source metsSchemaSource = new StreamSource(metsSchemaInputStream);
      final Schema schema = factory.newSchema(metsSchemaSource);
      jaxbUnmarshaller.setSchema(schema);
      return (Mets) jaxbUnmarshaller.unmarshal(stream);
    } catch (JAXBException | SAXException e) {
      throw new UnmarshallerException("An error occured during the unmarshalling process on file " + file + ". "
        + (e.getMessage() != null ? e.getMessage() : e.getCause()));
    }
  }
}
