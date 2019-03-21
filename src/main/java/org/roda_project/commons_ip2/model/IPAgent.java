/**
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE file at the root of the source
 * tree and available online at
 *
 * https://github.com/keeps/commons-ip
 */
package org.roda_project.commons_ip2.model;

import java.io.Serializable;

import org.roda_project.commons_ip.utils.METSEnums.CreatorType;

public class IPAgent implements Serializable {
  private static final long serialVersionUID = 8060509412626688269L;

  private String name;
  private String role;
  private CreatorType type;
  private String otherRole;
  private String otherType;
  private String note;
  private IPAgentNoteTypeEnum noteType;

  public IPAgent() {
    this.name = "";
    this.role = "";
    this.type = CreatorType.OTHER;
    this.otherRole = "";
    this.otherType = "";
    this.note = "";
    this.noteType = IPAgentNoteTypeEnum.NOT_SET;
  }

  public IPAgent(String name, String role, String otherRole, CreatorType type, String otherType, String note,
    IPAgentNoteTypeEnum noteType) {
    this.name = name;
    this.role = role;
    this.type = type;
    this.otherRole = otherRole;
    this.otherType = otherType;
    this.note = note;
    this.noteType = noteType;
  }

  public String getName() {
    return name;
  }

  public IPAgent setName(String name) {
    this.name = name;
    return this;
  }

  public String getRole() {
    return role;
  }

  public IPAgent setRole(String role) {
    this.role = role;
    return this;
  }

  public CreatorType getType() {
    return type;
  }

  public IPAgent setType(CreatorType type) {
    this.type = type;
    return this;
  }

  public String getOtherRole() {
    return otherRole;
  }

  public IPAgent setOtherRole(String otherRole) {
    this.otherRole = otherRole;
    return this;
  }

  public String getOtherType() {
    return otherType;
  }

  public IPAgent setOtherType(String otherType) {
    this.otherType = otherType;
    return this;
  }

  public String getNote() {
    return note;
  }

  public IPAgent setNote(String note) {
    this.note = note;
    return this;
  }

  public IPAgentNoteTypeEnum getNoteType() {
    return noteType;
  }

  public IPAgent setNoteType(IPAgentNoteTypeEnum noteType) {
    this.noteType = noteType;
    return this;
  }

  @Override
  public String toString() {
    return "IPAgent [name=" + name + ", role=" + role + ", type=" + type + ", otherRole=" + otherRole + ", otherType="
      + otherType + ", note=" + note + "]";
  }

}
