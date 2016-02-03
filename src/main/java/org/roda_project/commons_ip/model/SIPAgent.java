/**
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE file at the root of the source
 * tree and available online at
 *
 * https://github.com/keeps/commons-ip
 */
package org.roda_project.commons_ip.model;

import org.roda_project.commons_ip.utils.METSEnums.CreatorType;

public class SIPAgent {
  private String name;
  private String role;
  private CreatorType type;
  private String otherRole;
  private String otherType;

  public SIPAgent(String name, String role, String otherRole, CreatorType type, String otherType) {
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

  @Override
  public String toString() {
    return "SIPAgent [name=" + name + ", role=" + role + ", type=" + type + ", otherRole=" + otherRole + ", otherType="
      + otherType + "]";
  }

}
