/**
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE file at the root of the source
 * tree and available online at
 *
 * https://github.com/keeps/commons-ip
 */
package org.roda_project.commons_ip2.model;

import java.nio.file.Path;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

public class ValidationEntry {
  public enum LEVEL {
    ERROR, WARN, INFO
  }

  public enum TYPE {
    STRUCTURAL, CSIP, SCHEMA, SCHEMATRON
  }

  private TYPE type = TYPE.STRUCTURAL;
  private String id = "undefined";
  private LEVEL level;
  private String message;
  private String description;
  @JsonSerialize(using = ToStringSerializer.class)
  private List<Path> relatedItem;

  public ValidationEntry() {
    super();
  }

  public ValidationEntry(TYPE type, String id, String message) {
    super();
    this.type = type;
    this.id = id;
    this.message = message;
  }

  public TYPE getType() {
    return type;
  }

  public ValidationEntry setType(TYPE type) {
    this.type = type;
    return this;
  }

  public String getId() {
    return id;
  }

  public ValidationEntry setId(String id) {
    this.id = id;
    return this;
  }

  public LEVEL getLevel() {
    return level;
  }

  public ValidationEntry setLevel(LEVEL level) {
    this.level = level;
    return this;
  }

  public String getMessage() {
    return message;
  }

  public ValidationEntry setMessage(String message) {
    this.message = message;
    return this;
  }

  public String getDescription() {
    return description;
  }

  public ValidationEntry setDescription(String description) {
    this.description = description;
    return this;
  }

  public List<Path> getRelatedItem() {
    return relatedItem;
  }

  public ValidationEntry setRelatedItem(List<Path> relatedItem) {
    this.relatedItem = relatedItem;
    return this;
  }

  @Override
  public String toString() {
    return "ValidationEntry [type=" + type + ", id=" + id + ", level=" + level + ", message=" + message
      + ", description=" + description + ", relatedItem=" + relatedItem + "]";
  }

}
