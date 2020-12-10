/**
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE file at the root of the source
 * tree and available online at
 *
 * https://github.com/keeps/commons-ip
 */
package org.roda_project.commons_ip2.model;

import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.xml.datatype.XMLGregorianCalendar;

import org.roda_project.commons_ip.model.ParseException;
import org.roda_project.commons_ip.utils.IPEnums.IPStatus;
import org.roda_project.commons_ip.utils.IPEnums.IPType;
import org.roda_project.commons_ip.utils.IPException;
import org.roda_project.commons_ip.utils.ZipEntryInfo;
import org.roda_project.commons_ip2.model.impl.IPConfig;

public interface IPInterface {

  IP setId(String id);

  String getId();

  IP setIds(List<String> ids);

  List<String> getIds();

  IP setProfile(String profile);

  String getProfile();

  IP setType(IPType type);

  String getType();

  IP setContentType(IPContentType contentType);

  IPContentType getContentType();

  IP setContentInformationType(IPContentInformationType contentInformationType);

  IPContentInformationType getContentInformationType();

  IP setStatus(IPStatus status);

  IPStatus getStatus();

  IP setCreateDate(XMLGregorianCalendar date);

  Optional<XMLGregorianCalendar> getCreateDate();

  IP setModificationDate(XMLGregorianCalendar date);

  Optional<XMLGregorianCalendar> getModificationDate();

  IP setBasePath(Path basePath);

  Path getBasePath();

  IP setAncestors(List<String> ancestors);

  List<String> getAncestors();

  IP setDescription(String description);

  String getDescription();

  IP addAgent(IPAgent agent);

  IP addDescriptiveMetadata(IPDescriptiveMetadata descriptiveMetadata) throws IPException;

  IP addPreservationMetadata(IPMetadata preservationMetadata) throws IPException;

  IP addOtherMetadata(IPMetadata otherMetadata) throws IPException;

  IP addRepresentation(IPRepresentation representation) throws IPException;

  IP addSchema(IPFile schema);

  IP addDocumentation(IPFile documentation);

  IP addAgentToRepresentation(String representationID, IPAgent agent) throws IPException;

  IP addDescriptiveMetadataToRepresentation(String representationID, IPDescriptiveMetadata descriptiveMetadata)
    throws IPException;

  IP addPreservationMetadataToRepresentation(String representationID, IPMetadata preservationMetadata)
    throws IPException;

  IP addOtherMetadataToRepresentation(String representationID, IPMetadata otherMetadata) throws IPException;

  IP addFileToRepresentation(String representationID, IPFile file) throws IPException;

  IP addSchemaToRepresentation(String representationID, IPFile schema) throws IPException;

  IP addDocumentationToRepresentation(String representationID, IPFile documentation) throws IPException;

  List<IPAgent> getAgents();

  List<IPDescriptiveMetadata> getDescriptiveMetadata();

  List<IPMetadata> getPreservationMetadata();

  List<IPMetadata> getOtherMetadata();

  List<IPRepresentation> getRepresentations();

  List<IPFile> getSchemas();

  List<IPFile> getDocumentation();

  Map<String, ZipEntryInfo> getZipEntries();

  ValidationReport getValidationReport();

  boolean isValid();

  IPHeader getHeader();

  IPConfig getIPConfig();

  IP setIPConfig(IPConfig ipConfig);

  Path build(Path destinationDirectory, IPConfig ipConfig) throws IPException, InterruptedException;

  Path build(Path destinationDirectory, boolean onlyManifest, IPConfig ipConfig)
    throws IPException, InterruptedException;

  Path build(Path destinationDirectory, String fileNameWithoutExtension, IPConfig ipConfig)
    throws IPException, InterruptedException;

  Path build(Path destinationDirectory, String fileNameWithoutExtension, boolean onlyManifest, IPConfig ipConfig)
    throws IPException, InterruptedException;

  static IPInterface parse(Path source, IPConfig ipConfig) throws ParseException {
    throw new ParseException("One must implement static method parse in a concrete class");
  }

  static IPInterface parse(Path source, Path destinationDirectory, IPConfig ipConfig) throws ParseException {
    throw new ParseException("One must implement static method parse in a concrete class");
  }

}