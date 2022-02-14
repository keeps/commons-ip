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
import org.roda_project.commons_ip.utils.IPEnums;
import org.roda_project.commons_ip.utils.IPEnums.IPStatus;
import org.roda_project.commons_ip.utils.IPEnums.IPType;
import org.roda_project.commons_ip.utils.IPException;
import org.roda_project.commons_ip.utils.ZipEntryInfo;

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

  IP addSchema(IPFileInterface schema);

  IP addDocumentation(IPFileInterface documentation);

  IP addAgentToRepresentation(String representationID, IPAgent agent) throws IPException;

  IP addDescriptiveMetadataToRepresentation(String representationID, IPDescriptiveMetadata descriptiveMetadata)
    throws IPException;

  IP addPreservationMetadataToRepresentation(String representationID, IPMetadata preservationMetadata)
    throws IPException;

  IP addOtherMetadataToRepresentation(String representationID, IPMetadata otherMetadata) throws IPException;

  IP addFileToRepresentation(String representationID, IPFileInterface file) throws IPException;

  IP addSchemaToRepresentation(String representationID, IPFileInterface schema) throws IPException;

  IP addDocumentationToRepresentation(String representationID, IPFileInterface documentation) throws IPException;

  List<IPAgent> getAgents();

  List<IPDescriptiveMetadata> getDescriptiveMetadata();

  List<IPMetadata> getPreservationMetadata();

  List<IPMetadata> getOtherMetadata();

  List<IPRepresentation> getRepresentations();

  List<IPFileInterface> getSchemas();

  List<IPFileInterface> getDocumentation();

  Map<String, ZipEntryInfo> getZipEntries();

  ValidationReport getValidationReport();

  boolean isValid();

  IPHeader getHeader();

  /**
   * @param destinationDirectory
   *          directory where the SIP will be placed into
   * @throws InterruptedException
   */
  Path build(Path destinationDirectory) throws IPException, InterruptedException;

  Path build(Path destinationDirectory, boolean onlyManifest) throws IPException, InterruptedException;

  Path build(Path destinationDirectory, String fileNameWithoutExtension) throws IPException, InterruptedException;

  Path build(Path destinationDirectory, String fileNameWithoutExtension, IPEnums.SipType sipType)
    throws IPException, InterruptedException;

  Path build(Path destinationDirectory, String fileNameWithoutExtension, boolean onlyManifest)
    throws IPException, InterruptedException;

  Path build(Path destinationDirectory, String fileNameWithoutExtension, boolean onlyManifest, IPEnums.SipType sipType)
    throws IPException, InterruptedException;

  static IPInterface parse(Path source) throws ParseException {
    throw new ParseException("One must implement static method parse in a concrete class");
  }

  static IPInterface parse(Path source, Path destinationDirectory) throws ParseException {
    throw new ParseException("One must implement static method parse in a concrete class");
  }

}