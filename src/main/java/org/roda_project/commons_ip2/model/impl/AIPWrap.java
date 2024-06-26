/**
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE file at the root of the source
 * tree and available online at
 *
 * https://github.com/keeps/commons-ip
 */
package org.roda_project.commons_ip2.model.impl;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.commons.lang3.NotImplementedException;
import org.roda_project.commons_ip.utils.IPEnums;
import org.roda_project.commons_ip.utils.IPException;
import org.roda_project.commons_ip.utils.ZipEntryInfo;
import org.roda_project.commons_ip2.model.AIP;
import org.roda_project.commons_ip2.model.IPInterface;
import org.roda_project.commons_ip2.model.IPAgent;
import org.roda_project.commons_ip2.model.IPContentInformationType;
import org.roda_project.commons_ip2.model.IPContentType;
import org.roda_project.commons_ip2.model.IPDescriptiveMetadata;
import org.roda_project.commons_ip2.model.IPFileInterface;
import org.roda_project.commons_ip2.model.IPHeader;
import org.roda_project.commons_ip2.model.IPMetadata;
import org.roda_project.commons_ip2.model.IPRepresentation;
import org.roda_project.commons_ip2.model.ValidationReport;

/**
 * AIP decorator (wrapper).
 * 
 * @author Rui Castro (rui.castro@gmail.com)
 */
public class AIPWrap implements AIP {

  /**
   * The wrapped {@link AIP}.
   */
  private final AIP aip;

  /**
   * Constructor.
   * 
   * @param aip
   *          the {@link AIP} to wrap.
   */
  public AIPWrap(final AIP aip) {
    this.aip = aip;
  }

  @Override
  public String toString() {
    return aip.toString();
  }

  @Override
  public IPInterface setId(final String id) {
    return aip.setId(id);
  }

  @Override
  public String getId() {
    return aip.getId();
  }

  @Override
  public IPInterface setIds(final List<String> ids) {
    return aip.setIds(ids);
  }

  @Override
  public List<String> getIds() {
    return aip.getIds();
  }

  @Override
  public IPInterface setProfile(final String profile) {
    return aip.setProfile(profile);
  }

  @Override
  public String getProfile() {
    return aip.getProfile();
  }

  @Override
  public IPInterface setType(final IPEnums.IPType type) {
    return aip.setType(type);
  }

  @Override
  public String getType() {
    return aip.getType();
  }

  @Override
  public void setState(final String state) {
    aip.setState(state);
  }

  @Override
  public String getState() {
    return aip.getState();
  }

  @Override
  public IPInterface setContentType(final IPContentType contentType) {
    return aip.setContentType(contentType);
  }

  @Override
  public IPContentType getContentType() {
    return aip.getContentType();
  }

  @Override
  public IPInterface setContentInformationType(IPContentInformationType contentInformationType) {
    return aip.setContentInformationType(contentInformationType);
  }

  @Override
  public IPContentInformationType getContentInformationType() {
    return aip.getContentInformationType();
  }

  @Override
  public IPInterface setStatus(final IPEnums.IPStatus status) {
    return aip.setStatus(status);
  }

  @Override
  public IPEnums.IPStatus getStatus() {
    return aip.getStatus();
  }

  @Override
  public IPInterface setCreateDate(final XMLGregorianCalendar date) {
    return aip.setCreateDate(date);
  }

  @Override
  public Optional<XMLGregorianCalendar> getCreateDate() {
    return aip.getCreateDate();
  }

  @Override
  public IPInterface setModificationDate(final XMLGregorianCalendar date) {
    return aip.setModificationDate(date);
  }

  @Override
  public Optional<XMLGregorianCalendar> getModificationDate() {
    return aip.getModificationDate();
  }

  @Override
  public List<String> getAncestors() {
    return aip.getAncestors();
  }

  @Override
  public IPInterface setAncestors(final List<String> ancestors) {
    return aip.setAncestors(ancestors);
  }

  @Override
  public IPInterface setBasePath(final Path basePath) {
    return aip.setBasePath(basePath);
  }

  @Override
  public Path getBasePath() {
    return aip.getBasePath();
  }

  @Override
  public IPInterface setDescription(final String description) {
    return aip.setDescription(description);
  }

  @Override
  public String getDescription() {
    return aip.getDescription();
  }

  @Override
  public IPInterface addAgent(final IPAgent aipAgent) {
    return aip.addAgent(aipAgent);
  }

  @Override
  public IPInterface addPreservationMetadata(final IPMetadata metadata) throws IPException {
    return aip.addPreservationMetadata(metadata);
  }

  @Override
  public IPInterface addOtherMetadata(final IPMetadata metadata) throws IPException {
    return aip.addOtherMetadata(metadata);
  }

  @Override
  public IPInterface addDescriptiveMetadata(final IPDescriptiveMetadata metadata) throws IPException {
    return aip.addDescriptiveMetadata(metadata);
  }

  @Override
  public IPInterface addRepresentation(final IPRepresentation aipRepresentation) throws IPException {
    return aip.addRepresentation(aipRepresentation);
  }

  @Override
  public IPInterface addSchema(final IPFileInterface schema) {
    return aip.addSchema(schema);
  }

  @Override
  public AIP addSubmission(final IPFileInterface submission) {
    return aip.addSubmission(submission);
  }

  @Override
  public IPInterface addDocumentation(final IPFileInterface documentationPath) {
    return aip.addDocumentation(documentationPath);
  }

  @Override
  public IPInterface addAgentToRepresentation(final String representationID, final IPAgent agent) throws IPException {
    return aip.addAgentToRepresentation(representationID, agent);
  }

  @Override
  public IPInterface addDescriptiveMetadataToRepresentation(final String representationID,
    final IPDescriptiveMetadata descriptiveMetadata) throws IPException {
    return aip.addDescriptiveMetadataToRepresentation(representationID, descriptiveMetadata);
  }

  @Override
  public IPInterface addPreservationMetadataToRepresentation(final String representationID,
    final IPMetadata preservationMetadata) throws IPException {
    return aip.addPreservationMetadataToRepresentation(representationID, preservationMetadata);
  }

  @Override
  public IPInterface addOtherMetadataToRepresentation(final String representationID, final IPMetadata otherMetadata)
    throws IPException {
    return aip.addOtherMetadataToRepresentation(representationID, otherMetadata);
  }

  @Override
  public IPInterface addFileToRepresentation(final String representationID, final IPFileInterface file) throws IPException {
    return aip.addFileToRepresentation(representationID, file);
  }

  @Override
  public IPInterface addSchemaToRepresentation(final String representationID, final IPFileInterface schema) throws IPException {
    return aip.addSchemaToRepresentation(representationID, schema);
  }

  @Override
  public IPInterface addDocumentationToRepresentation(final String representationID, final IPFileInterface documentation)
    throws IPException {
    return aip.addDocumentationToRepresentation(representationID, documentation);
  }

  @Override
  public List<IPAgent> getAgents() {
    return aip.getAgents();
  }

  @Override
  public List<IPDescriptiveMetadata> getDescriptiveMetadata() {
    return aip.getDescriptiveMetadata();
  }

  @Override
  public List<IPMetadata> getPreservationMetadata() {
    return aip.getPreservationMetadata();
  }

  @Override
  public List<IPMetadata> getOtherMetadata() {
    return aip.getOtherMetadata();
  }

  @Override
  public List<IPRepresentation> getRepresentations() {
    return aip.getRepresentations();
  }

  @Override
  public List<IPFileInterface> getSchemas() {
    return aip.getSchemas();
  }

  @Override
  public List<IPFileInterface> getSubmissions() {
    return aip.getSubmissions();
  }

  @Override
  public List<IPFileInterface> getDocumentation() {
    return aip.getDocumentation();
  }

  @Override
  public Map<String, ZipEntryInfo> getZipEntries() {
    return aip.getZipEntries();
  }

  @Override
  public ValidationReport getValidationReport() {
    return aip.getValidationReport();
  }

  @Override
  public boolean isValid() {
    return aip.isValid();
  }

  @Override
  public IPHeader getHeader() {
    return aip.getHeader();
  }

  @Override
  public Path build(final Path destinationDirectory) throws IPException, InterruptedException, IOException {
    return aip.build(destinationDirectory);
  }

  @Override
  public Path build(final Path destinationDirectory, final boolean onlyManifest)
    throws IPException, InterruptedException, IOException {
    return aip.build(destinationDirectory, onlyManifest);
  }

  @Override
  public Path build(final Path destinationDirectory, final String fileNameWithoutExtension)
    throws IPException, InterruptedException, IOException {
    return aip.build(destinationDirectory, fileNameWithoutExtension);
  }

  @Override
  public Path build(Path destinationDirectory, String fileNameWithoutExtension, IPEnums.SipType sipType)
    throws IPException, InterruptedException, IOException {
    return aip.build(destinationDirectory, fileNameWithoutExtension);
  }

  @Override
  public Path build(final Path destinationDirectory, final String fileNameWithoutExtension, final boolean onlyManifest)
    throws IPException, InterruptedException, IOException {
    return aip.build(destinationDirectory, fileNameWithoutExtension, onlyManifest);
  }

  @Override
  public Path build(Path destinationDirectory, String fileNameWithoutExtension, boolean onlyManifest,
                    IPEnums.SipType sipType) throws IPException, InterruptedException, IOException {
    return aip.build(destinationDirectory, fileNameWithoutExtension, onlyManifest);
  }
}
