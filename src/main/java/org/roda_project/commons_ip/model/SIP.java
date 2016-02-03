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

  SIP addAgent(SIPAgent sipAgent);

  SIP addDescriptiveMetadata(SIPDescriptiveMetadata descriptiveMetadata) throws SIPException;

  SIP addAdministrativeMetadata(SIPMetadata sipMetadata) throws SIPException;

  SIP addOtherMetadata(SIPMetadata sipMetadata) throws SIPException;

  SIP addRepresentation(SIPRepresentation sipRepresentation) throws SIPException;

  SIP addSchema(Path schemaPath);

  SIP addDocumentation(Path documentationPath);

  SIP addAgentToRepresentation(String representationID, SIPAgent agent) throws SIPException;

  SIP addDescriptiveMetadataToRepresentation(String representationID, SIPDescriptiveMetadata descriptiveMetadata)
    throws SIPException;

  SIP addAdministrativeMetadataToRepresentation(String representationID, SIPMetadata preservationMetadata)
    throws SIPException;

  SIP addOtherMetadataToRepresentation(String representationID, SIPMetadata otherMetadata) throws SIPException;

  SIP addFileToRepresentation(String representationID, Path file, List<String> folders) throws SIPException;

  SIP addDocumentationToRepresentation(String representationID, Path documentationPath) throws SIPException;

  SIP addSchemaToRepresentation(String representationID, Path schemaPath) throws SIPException;

  /**
   * @param destinationDirectory
   *          directory where the SIP will be placed into
   */
  Path build(Path destinationDirectory) throws SIPException;

  List<SIPAgent> getAgents();

  List<SIPDescriptiveMetadata> getDescriptiveMetadata();

  List<SIPMetadata> getAdministrativeMetadata();

  List<SIPMetadata> getOtherMetadata();

  List<SIPRepresentation> getRepresentations();

  List<Path> getSchemas();

  List<Path> getDocumentation();

}