package org.roda_project.commons_ip2.validator.common;

import org.roda_project.commons_ip2.mets_v1_12.beans.Mets;
import org.roda_project.commons_ip2.model.IPConstants;
import org.roda_project.commons_ip2.utils.METSUtils;
import org.roda_project.commons_ip2.utils.ResourceResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;

/**
 * @author João Gomes <jgomes@keep.pt>
 */
public class InstatiateMets {
    private static final Logger LOGGER = LoggerFactory.getLogger(InstatiateMets.class);
    private InputStream stream;

    public InstatiateMets(InputStream stream){
        this.stream = stream;
    }

    public Mets instatiateMetsFile() throws JAXBException, SAXException {
        JAXBContext jaxbContext = JAXBContext.newInstance(Mets.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        factory.setResourceResolver(new ResourceResolver());
        InputStream metsSchemaInputStream = METSUtils.class
                .getResourceAsStream(IPConstants.SCHEMA_METS_RELATIVE_PATH_FROM_RESOURCES);
        Source metsSchemaSource = new StreamSource(metsSchemaInputStream);
        Schema schema = factory.newSchema(metsSchemaSource);
        jaxbUnmarshaller.setSchema(schema);
        return (Mets) jaxbUnmarshaller.unmarshal(stream);
    }
}
