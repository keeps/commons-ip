/**
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE file at the root of the source
 * tree and available online at
 *
 * https://github.com/keeps/commons-ip
 */
package org.roda_project.commons_ip.model;

import java.nio.file.Path;
import java.util.List;

import org.roda_project.commons_ip.utils.IPEnums.IPType;
import org.roda_project.commons_ip.utils.SIPException;

public interface IPInterface {

  IP setId(String id);

  String getId();

  IP setProfile(String profile);

  String getProfile();

  IP setType(IPType type);

  String getType();

  IP setContentType(IPContentType contentType);

  IPContentType getContentType();

  IP setBasePath(Path basePath);

  Path getBasePath();

  IP setParent(String parentID);

  String getParentID();

  IP setDescription(String description);

  String getDescription();

  IP addAgent(IPAgent agent);

  IP addDescriptiveMetadata(IPDescriptiveMetadata descriptiveMetadata) throws SIPException;

  IP addPreservationMetadata(IPMetadata preservationMetadata) throws SIPException;

  IP addOtherMetadata(IPMetadata otherMetadata) throws SIPException;

  IP addRepresentation(IPRepresentation representation) throws SIPException;

  IP addSchema(IPFile schema);

  IP addDocumentation(IPFile documentation);

  IP addAgentToRepresentation(String representationID, IPAgent agent) throws SIPException;

  IP addDescriptiveMetadataToRepresentation(String representationID, IPDescriptiveMetadata descriptiveMetadata)
    throws SIPException;

  IP addPreservationMetadataToRepresentation(String representationID, IPMetadata preservationMetadata)
    throws SIPException;

  IP addOtherMetadataToRepresentation(String representationID, IPMetadata otherMetadata) throws SIPException;

  IP addFileToRepresentation(String representationID, IPFile file) throws SIPException;

  IP addSchemaToRepresentation(String representationID, IPFile schema) throws SIPException;

  IP addDocumentationToRepresentation(String representationID, IPFile documentation) throws SIPException;

  List<IPAgent> getAgents();

  List<IPDescriptiveMetadata> getDescriptiveMetadata();

  List<IPMetadata> getPreservationMetadata();

  List<IPMetadata> getOtherMetadata();

  List<IPRepresentation> getRepresentations();

  List<IPFile> getSchemas();

  List<IPFile> getDocumentation();

  /**
   * @param destinationDirectory
   *          directory where the SIP will be placed into
   * @throws InterruptedException
   */
  Path build(Path destinationDirectory) throws SIPException, InterruptedException;
  
  Path build(Path destinationDirectory, String fileNameWithoutExtension) throws SIPException, InterruptedException;

  static IP parse(Path source) throws ParseException {
    throw new ParseException("One must implement static method parse in a concrete class");
  }

  static SIP parse(Path source, Path destinationDirectory) throws ParseException {
    throw new ParseException("One must implement static method parse in a concrete class");
  }

}