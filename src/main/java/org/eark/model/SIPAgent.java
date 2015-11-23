package org.eark.model;

import org.eark.utils.METSEnums.CreatorType;

public class SIPAgent {
	private String name;
	private String role;
	private CreatorType type;
	private String otherRole;
	private String otherType;
	
	public SIPAgent(String name, String role, CreatorType type, String otherRole, String otherType) {
		this.name = name;
		this.role = role;
		this.type = type;
		this.otherRole = otherRole;
		this.otherType = otherType;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public CreatorType getType() {
		return type;
	}
	public void setType(CreatorType type) {
		this.type = type;
	}
	public String getOtherRole() {
		return otherRole;
	}
	public void setOtherRole(String otherRole) {
		this.otherRole = otherRole;
	}
	public String getOtherType() {
		return otherType;
	}
	public void setOtherType(String otherType) {
		this.otherType = otherType;
	}
}
