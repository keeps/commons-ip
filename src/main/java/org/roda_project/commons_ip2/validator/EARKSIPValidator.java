package org.roda_project.commons_ip2.validator;

import org.roda_project.commons_ip2.validator.common.ZipManager;
import org.roda_project.commons_ip2.validator.component.MetsComponentValidator;
import org.roda_project.commons_ip2.validator.handlers.MetsComponentHandler;
import org.roda_project.commons_ip2.validator.observer.ValidationObserver;
import org.roda_project.commons_ip2.validator.reporter.ValidationReporter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.zip.ZipEntry;

/**
 * @author João Gomes <jgomes@keep.pt>
 */

public class EARKSIPValidator {
  private static final Logger LOGGER = LoggerFactory.getLogger(EARKSIPValidator.class);
  private Path earksipPath;

  private ValidationReporter reporter;
  private ZipManager zipManager;
  private ValidationObserver observer;
  private HashMap<String,String> data;


  public EARKSIPValidator(Path earksipPath, Path reportPath){
    this.earksipPath = earksipPath.toAbsolutePath().normalize();
    reporter = new ValidationReporter(reportPath.toAbsolutePath().normalize());
    zipManager = new ZipManager();
    data = new HashMap<String,String>();
  }

  public boolean validate() {
    SAXParserFactory factory = SAXParserFactory.newInstance();
    try {
      SAXParser saxParser = factory.newSAXParser();
      MetsComponentHandler handler = new MetsComponentHandler("mets", data);
      InputStream stream = zipManager.getZipInputStream(earksipPath,"METS.xml");
      saxParser.parse(stream, handler);
      MetsComponentValidator metsComponentValidator = new MetsComponentValidator("CSIP",data,reporter);
      if(metsComponentValidator.validateMets()){
        reporter.componentValidationFinish("VALID");
      }
      else{
        reporter.componentValidationFinish("INVALID");
      }
      reporter.close();
    } catch (ParserConfigurationException | SAXException | IOException e){
      LOGGER.error("Could not parse file.", e);
    }
    return true;
  }
}
