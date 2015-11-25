package org.eark.model;

import java.nio.file.Path;

import org.eark.utils.SIPException;

public interface SIP {

	void addAgent(SIPAgent sipAgent);

	void addMetadata(SIPMetadata sipMetadata) throws SIPException;

	void addDescriptiveMetadata(SIPDescriptiveMetadata descriptiveMetadata) throws SIPException;

	void addRepresentation(SIPRepresentation sipRepresentation) throws SIPException;

	void addAgentToRepresentation(String representationID, SIPAgent agent) throws SIPException;

	void addDataToRepresentation(String representationID, Path data) throws SIPException;

	void addPreservationToRepresentation(String representationID, SIPMetadata preservationMetadata) throws SIPException;

	Path createZip() throws SIPException;

}