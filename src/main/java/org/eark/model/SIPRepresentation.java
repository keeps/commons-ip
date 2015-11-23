package org.eark.model;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class SIPRepresentation {
	private String representationID;
	private String objectID;
	private String profile;
	private String type;
	private List<SIPAgent> agents;
	private List<Path> data;
	private List<SIPMetadata> preservationMetadata;
	
	
	public SIPRepresentation(String representationID, String objectID, String profile, String type) {
		this.representationID = representationID;
		this.objectID = objectID;
		this.profile = profile;
		this.type = type;
		this.agents = new ArrayList<SIPAgent>();
		this.data = new ArrayList<Path>();
		this.preservationMetadata = new ArrayList<SIPMetadata>();
	}
	public String getRepresentationID() {
		return representationID;
	}
	public void setRepresentationID(String representationID) {
		this.representationID = representationID;
	}
	public String getObjectID() {
		return objectID;
	}
	public void setObjectID(String objectID) {
		this.objectID = objectID;
	}
	public String getProfile() {
		return profile;
	}
	public void setProfile(String profile) {
		this.profile = profile;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public List<SIPAgent> getAgents() {
		return agents;
	}
	public void setAgents(List<SIPAgent> agents) {
		this.agents = agents;
	}
	public List<Path> getData() {
		return data;
	}
	public void setData(List<Path> data) {
		this.data = data;
	}
	public List<SIPMetadata> getPreservationMetadata() {
		return preservationMetadata;
	}
	public void setPreservationMetadata(List<SIPMetadata> preservationMetadata) {
		this.preservationMetadata = preservationMetadata;
	}
	
	
	
}
