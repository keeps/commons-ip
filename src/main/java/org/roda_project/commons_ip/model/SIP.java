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

import org.roda_project.commons_ip.utils.SIPException;

public interface SIP {
  SIP setParent(String parentID);

  String getParentID();

  SIP setBasePath(Path basePath);

  Path getBasePath();

  SIP setDescription(String description);

  String getDescription();

  SIP addAgent(IPAgent agent);

  SIP addDescriptiveMetadata(IPDescriptiveMetadata descriptiveMetadata) throws SIPException;

  SIP addPreservationMetadata(IPMetadata preservationMetadata) throws SIPException;

  SIP addOtherMetadata(IPMetadata otherMetadata) throws SIPException;

  SIP addRepresentation(IPRepresentation representation) throws SIPException;

  SIP addSchema(IPFile schema);

  SIP addDocumentation(IPFile documentation);

  SIP addAgentToRepresentation(String representationID, IPAgent agent) throws SIPException;

  SIP addDescriptiveMetadataToRepresentation(String representationID, IPDescriptiveMetadata descriptiveMetadata)
    throws SIPException;

  SIP addPreservationMetadataToRepresentation(String representationID, IPMetadata preservationMetadata)
    throws SIPException;

  SIP addOtherMetadataToRepresentation(String representationID, IPMetadata otherMetadata) throws SIPException;

  SIP addFileToRepresentation(String representationID, IPFile file) throws SIPException;

  SIP addSchemaToRepresentation(String representationID, IPFile schema) throws SIPException;

  SIP addDocumentationToRepresentation(String representationID, IPFile documentation) throws SIPException;

  /**
   * @param destinationDirectory
   *          directory where the SIP will be placed into
   */
  Path build(Path destinationDirectory) throws SIPException;

  List<IPAgent> getAgents();

  List<IPDescriptiveMetadata> getDescriptiveMetadata();

  List<IPMetadata> getPreservationMetadata();

  List<IPMetadata> getOtherMetadata();

  List<IPRepresentation> getRepresentations();

  List<IPFile> getSchemas();

  List<IPFile> getDocumentation();

}