/**
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE file at the root of the source
 * tree and available online at
 *
 * https://github.com/keeps/commons-ip
 */
package org.roda_project.commons_ip.model;

import java.nio.file.Path;

import org.roda_project.commons_ip.utils.SIPException;

public interface SIP {

  SIP addAgent(SIPAgent sipAgent);

  SIP addMetadata(SIPMetadata sipMetadata) throws SIPException;

  SIP addDescriptiveMetadata(SIPDescriptiveMetadata descriptiveMetadata) throws SIPException;

  SIP addRepresentation(SIPRepresentation sipRepresentation) throws SIPException;

  SIP addAgentToRepresentation(String representationID, SIPAgent agent) throws SIPException;

  SIP addDataToRepresentation(String representationID, Path data) throws SIPException;

  SIP addPreservationToRepresentation(String representationID, SIPMetadata preservationMetadata) throws SIPException;

  Path build() throws SIPException;

}