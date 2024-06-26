/**
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE file at the root of the source
 * tree and available online at
 *
 * https://github.com/keeps/commons-ip
 */
package org.roda_project.commons_ip2.model;

import java.io.IOException;
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

  IPInterface setId(String id);

  String getId();

  IPInterface setIds(List<String> ids);

  List<String> getIds();

  IPInterface setProfile(String profile);

  String getProfile();

  IPInterface setType(IPType type);

  String getType();

  IPInterface setContentType(IPContentType contentType);

  IPContentType getContentType();

  IPInterface setContentInformationType(IPContentInformationType contentInformationType);

  IPContentInformationType getContentInformationType();

  IPInterface setStatus(IPStatus status);

  IPStatus getStatus();

  IPInterface setCreateDate(XMLGregorianCalendar date);

  Optional<XMLGregorianCalendar> getCreateDate();

  IPInterface setModificationDate(XMLGregorianCalendar date);

  Optional<XMLGregorianCalendar> getModificationDate();

  IPInterface setBasePath(Path basePath);

  Path getBasePath();

  IPInterface setAncestors(List<String> ancestors);

  List<String> getAncestors();

  IPInterface setDescription(String description);

  String getDescription();

  IPInterface addAgent(IPAgent agent);

  IPInterface addDescriptiveMetadata(IPDescriptiveMetadata descriptiveMetadata) throws IPException;

  IPInterface addPreservationMetadata(IPMetadata preservationMetadata) throws IPException;

  IPInterface addOtherMetadata(IPMetadata otherMetadata) throws IPException;

  IPInterface addRepresentation(IPRepresentation representation) throws IPException;

  IPInterface addSchema(IPFileInterface schema);

  IPInterface addDocumentation(IPFileInterface documentation);

  IPInterface addAgentToRepresentation(String representationID, IPAgent agent) throws IPException;

  IPInterface addDescriptiveMetadataToRepresentation(String representationID, IPDescriptiveMetadata descriptiveMetadata)
    throws IPException;

  IPInterface addPreservationMetadataToRepresentation(String representationID, IPMetadata preservationMetadata)
    throws IPException;

  IPInterface addOtherMetadataToRepresentation(String representationID, IPMetadata otherMetadata) throws IPException;

  IPInterface addFileToRepresentation(String representationID, IPFileInterface file) throws IPException;

  IPInterface addSchemaToRepresentation(String representationID, IPFileInterface schema) throws IPException;

  IPInterface addDocumentationToRepresentation(String representationID, IPFileInterface documentation) throws IPException;

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
  Path build(Path destinationDirectory) throws IPException, InterruptedException, IOException;

  Path build(Path destinationDirectory, boolean onlyManifest) throws IPException, InterruptedException, IOException;

  Path build(Path destinationDirectory, String fileNameWithoutExtension) throws IPException, InterruptedException, IOException;

  Path build(Path destinationDirectory, String fileNameWithoutExtension, IPEnums.SipType sipType)
    throws IPException, InterruptedException, IOException;

  Path build(Path destinationDirectory, String fileNameWithoutExtension, boolean onlyManifest,
                    IPEnums.SipType sipType) throws IPException, InterruptedException, IOException;

  Path build(Path destinationDirectory, String fileNameWithoutExtension, boolean onlyManifest)
    throws IPException, InterruptedException, IOException;

  static IPInterface parse(Path source) throws ParseException {
    throw new ParseException("One must implement static method parse in a concrete class");
  }

  static IPInterface parse(Path source, Path destinationDirectory) throws ParseException {
    throw new ParseException("One must implement static method parse in a concrete class");
  }

}
