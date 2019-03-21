/**
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE file at the root of the source
 * tree and available online at
 *
 * https://github.com/keeps/commons-ip
 */
package org.roda_project.commons_ip2.model;

import java.nio.file.Path;

import org.roda_project.commons_ip2.mets_v1_12.beans.DivType;
import org.roda_project.commons_ip2.mets_v1_12.beans.FileGrpType;
import org.roda_project.commons_ip2.mets_v1_12.beans.MdSecType;
import org.roda_project.commons_ip2.mets_v1_12.beans.Mets;

public class MetsWrapper {

  private Mets mets;
  private Path metsPath;

  private DivType mainDiv;
  private DivType metadataDiv;
  private DivType otherMetadataDiv;
  private DivType dataDiv;
  private DivType schemasDiv;
  private DivType submissionsDiv;
  private DivType documentationDiv;

  private FileGrpType mainFileGroup;

  private FileGrpType schemasFileGroup;
  private FileGrpType submissionFileGroup;
  private FileGrpType documentationFileGroup;
  private FileGrpType dataFileGroup;

  private MdSecType mainDmdSec;
  private MdSecType documentationDmdSec;

  public MetsWrapper(Mets mets, Path metsPath) {
    super();
    this.mets = mets;
    this.metsPath = metsPath;
  }

  public Mets getMets() {
    return mets;
  }

  public void setMets(Mets mets) {
    this.mets = mets;
  }

  public Path getMetsPath() {
    return metsPath;
  }

  public void setMetsPath(Path metsPath) {
    this.metsPath = metsPath;
  }

  public DivType getMainDiv() {
    return mainDiv;
  }

  public void setMainDiv(DivType mainDiv) {
    this.mainDiv = mainDiv;
  }

  public DivType getMetadataDiv() {
    return metadataDiv;
  }

  public void setMetadataDiv(DivType metadataDiv) {
    this.metadataDiv = metadataDiv;
  }

  public DivType getOtherMetadataDiv() {
    return otherMetadataDiv;
  }

  public void setOtherMetadataDiv(DivType otherMetadataDiv) {
    this.otherMetadataDiv = otherMetadataDiv;
  }

  public FileGrpType getSchemasFileGroup() {
    return schemasFileGroup;
  }

  public void setSchemasFileGroup(FileGrpType schemasFileGroup) {
    this.schemasFileGroup = schemasFileGroup;
  }

  public FileGrpType getSubmissionFileGroup() {
    return submissionFileGroup;
  }

  public void setSubmissionFileGroup(FileGrpType submissionFileGroup) {
    this.submissionFileGroup = submissionFileGroup;
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

  public FileGrpType getMainFileGroup() {
    return mainFileGroup;
  }

  public void setMainFileGroup(FileGrpType mainFileGroup) {
    this.mainFileGroup = mainFileGroup;
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

  public DivType getSubmissionsDiv() {
    return submissionsDiv;
  }

  public void setSubmissionsDiv(final DivType submissionsDiv) {
    this.submissionsDiv = submissionsDiv;
  }

  public MdSecType getMainDmdSec() {
    return mainDmdSec;
  }

  public void setMainDmdSec(MdSecType mainDmdSec) {
    this.mainDmdSec = mainDmdSec;
  }

  public MdSecType getDocumentationDmdSec() {
    return documentationDmdSec;
  }

  public void setDocumentationDmdSec(MdSecType documentationDmdSec) {
    this.documentationDmdSec = documentationDmdSec;
  }

}
