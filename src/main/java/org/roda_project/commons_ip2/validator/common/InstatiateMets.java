package org.roda_project.commons_ip2.validator.common;

import java.io.InputStream;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.roda_project.commons_ip2.mets_v1_12.beans.Mets;
import org.roda_project.commons_ip2.model.IPConstants;
import org.roda_project.commons_ip2.utils.METSUtils;
import org.roda_project.commons_ip2.utils.ResourceResolver;
import org.xml.sax.SAXException;

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
   * @throws JAXBException
   *           if some schema error occurs.
   * @throws SAXException
   *           if some parse error occurs.
   */
  public Mets instatiateMetsFile() throws JAXBException, SAXException {
    final JAXBContext jaxbContext = JAXBContext.newInstance(Mets.class);
    final Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
    final SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
    factory.setResourceResolver(new ResourceResolver());
    final InputStream metsSchemaInputStream = METSUtils.class
      .getResourceAsStream(IPConstants.SCHEMA_METS_RELATIVE_PATH_FROM_RESOURCES);
    final Source metsSchemaSource = new StreamSource(metsSchemaInputStream);
    final Schema schema = factory.newSchema(metsSchemaSource);
    jaxbUnmarshaller.setSchema(schema);
    return (Mets) jaxbUnmarshaller.unmarshal(stream);
  }
}
