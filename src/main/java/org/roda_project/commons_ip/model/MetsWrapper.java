/**
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE file at the root of the source
 * tree and available online at
 *
 * https://github.com/keeps/commons-ip
 */
package org.roda_project.commons_ip.model;

import org.roda_project.commons_ip.mets_v1_11.beans.DivType;
import org.roda_project.commons_ip.mets_v1_11.beans.FileGrpType;
import org.roda_project.commons_ip.mets_v1_11.beans.Mets;

public class MetsWrapper {

  private Mets mets;
  private DivType descriptiveMetadataDiv;
  private DivType preservationMetadataDiv;
  private DivType otherMetadataDiv;
  private DivType representationsDiv;
  private DivType dataDiv;
  private DivType schemasDiv;
  private DivType documentationDiv;

  private FileGrpType representationsFileGroup;
  private FileGrpType schemasFileGroup;
  private FileGrpType documentationFileGroup;
  private FileGrpType dataFileGroup;

  public MetsWrapper(Mets mets) {
    super();
    this.mets = mets;
  }

  public Mets getMets() {
    return mets;
  }

  public void setMets(Mets mets) {
    this.mets = mets;
  }

  public DivType getDescriptiveMetadataDiv() {
    return descriptiveMetadataDiv;
  }

  public void setDescriptiveMetadataDiv(DivType descriptiveMetadataDiv) {
    this.descriptiveMetadataDiv = descriptiveMetadataDiv;
  }

  public DivType getPreservationMetadataDiv() {
    return preservationMetadataDiv;
  }

  public void setPreservationMetadataDiv(DivType preservationMetadataDiv) {
    this.preservationMetadataDiv = preservationMetadataDiv;
  }

  public DivType getOtherMetadataDiv() {
    return otherMetadataDiv;
  }

  public void setOtherMetadataDiv(DivType otherMetadataDiv) {
    this.otherMetadataDiv = otherMetadataDiv;
  }

  public DivType getRepresentationsDiv() {
    return representationsDiv;
  }

  public void setRepresentationsDiv(DivType representationsDiv) {
    this.representationsDiv = representationsDiv;
  }

  public FileGrpType getRepresentationsFileGroup() {
    return representationsFileGroup;
  }

  public void setRepresentationsFileGroup(FileGrpType representationsFileGroup) {
    this.representationsFileGroup = representationsFileGroup;
  }

  public FileGrpType getSchemasFileGroup() {
    return schemasFileGroup;
  }

  public void setSchemasFileGroup(FileGrpType schemasFileGroup) {
    this.schemasFileGroup = schemasFileGroup;
  }

  public FileGrpType getDocumentationFileGroup() {
    return documentationFileGroup;
  }

  public void setDocumentationFileGroup(FileGrpType documentationFileGroup) {
    this.documentationFileGroup = documentationFileGroup;
  }

  public FileGrpType getDataFileGroup() {
    return dataFileGroup;
  }

  public void setDataFileGroup(FileGrpType dataFileGroup) {
    this.dataFileGroup = dataFileGroup;
  }

  public DivType getDataDiv() {
    return dataDiv;
  }

  public void setDataDiv(DivType dataDiv) {
    this.dataDiv = dataDiv;
  }

  public DivType getSchemasDiv() {
    return schemasDiv;
  }

  public void setSchemasDiv(DivType schemasDiv) {
    this.schemasDiv = schemasDiv;
  }

  public DivType getDocumentationDiv() {
    return documentationDiv;
  }

  public void setDocumentationDiv(DivType documentationDiv) {
    this.documentationDiv = documentationDiv;
  }

}
