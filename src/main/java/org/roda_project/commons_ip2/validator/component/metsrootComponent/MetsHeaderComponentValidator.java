package org.roda_project.commons_ip2.validator.component.metsrootComponent;

import org.roda_project.commons_ip2.validator.component.ValidatorComponentImpl;
import org.roda_project.commons_ip2.validator.handlers.MetsComponentHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

/**
 * @author João Gomes <jgomes@keep.pt>
 */

public class MetsHeaderComponentValidator extends ValidatorComponentImpl {
    private static final Logger LOGGER = LoggerFactory.getLogger(MetsHeaderComponentValidator.class);

    private final String MODULE_NAME;
    private HashMap<String,String> data;

    public MetsHeaderComponentValidator(String module_name) {
        MODULE_NAME = module_name;
        this.data = new HashMap<>();
    }

    @Override
    public boolean validate() throws SAXException, ParserConfigurationException, IOException {
        boolean valid = true;

        /* Parse metsHdr element in METS.xml  */
        MetsComponentHandler handler = new MetsComponentHandler("metsHdr", data);
        InputStream stream = zipManager.getMetsRootInputStream(path);
        getSAXParser().parse(stream, handler);
        return false;
    }

    @Override
    public void clean() {
        zipManager.closeZipFile();
    }
}
