package org.eark.model.impl.eark;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.XMLGregorianCalendar;

import org.eark.mets.beans.AmdSecType;
import org.eark.mets.beans.DivType;
import org.eark.mets.beans.DivType.Fptr;
import org.eark.mets.beans.DivType.Mptr;
import org.eark.mets.beans.FileType;
import org.eark.mets.beans.FileType.FLocat;
import org.eark.mets.beans.MdSecType;
import org.eark.mets.beans.MdSecType.MdRef;
import org.eark.mets.beans.Mets;
import org.eark.mets.beans.MetsType.FileSec;
import org.eark.mets.beans.MetsType.FileSec.FileGrp;
import org.eark.mets.beans.MetsType.MetsHdr;
import org.eark.mets.beans.MetsType.MetsHdr.Agent;
import org.eark.mets.beans.MetsType.MetsHdr.MetsDocumentID;
import org.eark.mets.beans.StructMapType;
import org.eark.model.SIP;
import org.eark.model.SIPAgent;
import org.eark.model.SIPDescriptiveMetadata;
import org.eark.model.SIPMetadata;
import org.eark.model.SIPRepresentation;
import org.eark.utils.METSEnums.LocType;
import org.eark.utils.SIPException;
import org.eark.utils.Utils;

public class EARKSIP implements SIP {
	Mets mainMets;
	Path zipPath;
	Map<String, EARKRepresentation> representations;

	public EARKSIP(String objectID, String profile, String type) throws SIPException {
		representations = new HashMap<String, EARKRepresentation>();
		zipPath = Paths.get(UUID.randomUUID().toString() + ".zip");

		mainMets = new Mets();
		mainMets.setOBJID(objectID);
		mainMets.setPROFILE(profile);
		mainMets.setTYPE(type);
		MetsHdr header = new MetsHdr();
		try {
			XMLGregorianCalendar cal = Utils.getCurrentCalendar();
			header.setCREATEDATE(cal);
			header.setLASTMODDATE(cal);
		} catch (DatatypeConfigurationException dce) {
			throw new SIPException("Error getting current calendar", dce);
		}
		MetsDocumentID metsID = new MetsDocumentID();
		metsID.setValue("METS.xml");
		header.setMetsDocumentID(metsID);
		mainMets.setMetsHdr(header);

		// empty amdsec
		AmdSecType amdsec = new AmdSecType();
		amdsec.setID(UUID.randomUUID().toString());
		mainMets.getAmdSec().add(amdsec);

		// empty filesec
		FileSec filesec = new FileSec();
		filesec.setID(UUID.randomUUID().toString());
		mainMets.setFileSec(filesec);

		StructMapType structMap = new StructMapType();
		DivType packageDiv = new DivType();
		packageDiv.setLabel("Package");
		packageDiv.setID("packageDiv");
		DivType representationMetadataDiv = new DivType();
		representationMetadataDiv.setID("representationMetadataDiv");
		representationMetadataDiv.setLabel("Representation Metadata");
		packageDiv.getDiv().add(representationMetadataDiv);
		structMap.setDiv(packageDiv);
		mainMets.getStructMap().add(structMap);
	}

	/* (non-Javadoc)
	 * @see org.eark.model.impl.eark.SIP#addAgent(org.eark.model.SIPAgent)
	 */
	@Override
	public void addAgent(SIPAgent sipAgent) {
		Agent agent = new Agent();
		agent.setROLE(sipAgent.getRole());
		agent.setTYPE(sipAgent.getType().toString());
		agent.setName(sipAgent.getName());
		agent.setOTHERROLE(sipAgent.getOtherRole());
		agent.setOTHERTYPE(sipAgent.getOtherType());
		MetsHdr hdr = mainMets.getMetsHdr();
		hdr.getAgent().add(agent);
		mainMets.setMetsHdr(hdr);
	}

	/* (non-Javadoc)
	 * @see org.eark.model.impl.eark.SIP#addMetadata(org.eark.model.SIPMetadata)
	 */
	@Override
	public void addMetadata(SIPMetadata sipMetadata) throws SIPException {
		try {
			String metadataPath = "/metadata/" + sipMetadata.getMetadata().getFileName().toString();
			if (sipMetadata.getSchema() != null) {
				Path source = sipMetadata.getMetadata();
				String schemaPath = "/schemas/" + sipMetadata.getSchema().getFileName().toString();
				Utils.addFileToZip(zipPath, sipMetadata.getSchema(), schemaPath);
				Path temp = Files.createTempFile("temp", ".xml");
				Files.copy(sipMetadata.getMetadata(), temp, StandardCopyOption.REPLACE_EXISTING);
				Utils.addSchemaLocationToPath(temp, ".." + schemaPath);
				Files.copy(temp, source,StandardCopyOption.REPLACE_EXISTING);
				sipMetadata.setMetadata(source);
			}

			Utils.addFileToZip(zipPath, sipMetadata.getMetadata(), metadataPath);

		} catch (IOException e) {
			throw new SIPException(e.getMessage(), e);
		}
	}

	/* (non-Javadoc)
	 * @see org.eark.model.impl.eark.SIP#addDescriptiveMetadata(org.eark.model.SIPDescriptiveMetadata)
	 */
	@Override
	public void addDescriptiveMetadata(SIPDescriptiveMetadata descriptiveMetadata) throws SIPException {

		String descriptiveMetadataPath = "/metadata/descriptive/"
				+ descriptiveMetadata.getMetadata().getFileName().toString();
		try {
			if (descriptiveMetadata.getSchema() != null) {
				Path source = descriptiveMetadata.getMetadata();
				String schemaPath = "/schemas/" + descriptiveMetadata.getSchema().getFileName().toString();
				Utils.addFileToZip(zipPath, descriptiveMetadata.getSchema(), schemaPath);
				Path temp = Files.createTempFile("temp", ".xml");
				Files.copy(descriptiveMetadata.getMetadata(), temp, StandardCopyOption.REPLACE_EXISTING);
				Utils.addSchemaLocationToPath(temp, "../.." + schemaPath);
				Files.copy(temp, source, StandardCopyOption.REPLACE_EXISTING);
				descriptiveMetadata.setMetadata(source);
			}
			Utils.addFileToZip(zipPath, descriptiveMetadata.getMetadata(), descriptiveMetadataPath);
		} catch (IOException e) {
			throw new SIPException("Error adding descriptive metadata to ZIP", e);
		}

		MdRef mdref = new MdRef();
		try {
			mdref.setCHECKSUM(
					Utils.calculateChecksum(Files.newInputStream(descriptiveMetadata.getMetadata()), "SHA-256"));
		} catch (NoSuchAlgorithmException nsae) {
			throw new SIPException("Error calculating checskum: the algorythm provided is not recognized", nsae);
		} catch (IOException ioe) {
			throw new SIPException("Error calculation checksum", ioe);
		}
		mdref.setCHECKSUMTYPE("SHA-256");
		try {
			mdref.setCREATED(Utils.getCurrentCalendar());
		} catch (DatatypeConfigurationException dce) {
			throw new SIPException("Error getting current calendar", dce);
		}
		mdref.setLOCTYPE(LocType.URL.toString());
		mdref.setMDTYPE(descriptiveMetadata.getMetadataType().toString());
		mdref.setMIMETYPE("text/xml");
		try {
			mdref.setSIZE(Files.size(descriptiveMetadata.getMetadata()));
		} catch (IOException e) {
			throw new SIPException("Error calculating file size", e);
		}
		mdref.setHref(descriptiveMetadataPath);
		mdref.setType("simple");

		MdSecType dmdsec = new MdSecType();
		dmdsec.setID("DMD" + mainMets.getDmdSec().size());
		dmdsec.setMdRef(mdref);
		mainMets.getDmdSec().add(dmdsec);
	}

	/* (non-Javadoc)
	 * @see org.eark.model.impl.eark.SIP#addRepresentation(org.eark.model.SIPRepresentation)
	 */
	@Override
	public void addRepresentation(SIPRepresentation sipRepresentation) throws SIPException {
		if (representations.containsKey(sipRepresentation.getRepresentationID())) {
			throw new SIPException("Representation already exists", null);
		}
		try {
			Path temp = Files.createTempDirectory("rep");
			String representationPath = "/representations/" + sipRepresentation.getRepresentationID();
			Utils.addFileToZip(zipPath, temp, representationPath);
		} catch (IOException e) {
			throw new SIPException("Errpr creating representation folder in zip", e);
		}

		EARKRepresentation r = new EARKRepresentation(
				zipPath.resolve("representations").resolve(sipRepresentation.getRepresentationID()), sipRepresentation);
		representations.put(sipRepresentation.getRepresentationID(), r);

		if (sipRepresentation.getAgents() != null && sipRepresentation.getAgents().size() > 0) {
			for (SIPAgent agent : sipRepresentation.getAgents()) {
				addAgentToRepresentation(sipRepresentation.getRepresentationID(), agent);
			}
		}
		if (sipRepresentation.getData() != null && sipRepresentation.getData().size() > 0) {
			for (Path data : sipRepresentation.getData()) {
				addDataToRepresentation(sipRepresentation.getRepresentationID(), data);
			}
		}
		if (sipRepresentation.getPreservationMetadata() != null
				&& sipRepresentation.getPreservationMetadata().size() > 0) {
			for (SIPMetadata metadata : sipRepresentation.getPreservationMetadata()) {
				addPreservationToRepresentation(sipRepresentation.getRepresentationID(), metadata);
			}
		}
	}

	/* (non-Javadoc)
	 * @see org.eark.model.impl.eark.SIP#addAgentToRepresentation(java.lang.String, org.eark.model.SIPAgent)
	 */
	@Override
	public void addAgentToRepresentation(String representationID, SIPAgent agent) throws SIPException {
		if (!representations.containsKey(representationID)) {
			throw new SIPException("Representation doesn't exist", null);
		}
		EARKRepresentation rep = representations.get(representationID);
		rep.addAgent(agent);
	}

	/* (non-Javadoc)
	 * @see org.eark.model.impl.eark.SIP#addDataToRepresentation(java.lang.String, java.nio.file.Path)
	 */
	@Override
	public void addDataToRepresentation(String representationID, Path data) throws SIPException {
		if (!representations.containsKey(representationID)) {
			throw new SIPException("Representation doesn't exist", null);
		}
		EARKRepresentation rep = representations.get(representationID);
		rep.addDataFile(data);
	}

	/* (non-Javadoc)
	 * @see org.eark.model.impl.eark.SIP#addPreservationToRepresentation(java.lang.String, org.eark.model.SIPMetadata)
	 */
	@Override
	public void addPreservationToRepresentation(String representationID, SIPMetadata preservationMetadata)
			throws SIPException {
		if (!representations.containsKey(representationID)) {
			throw new SIPException("Representation doesn't exist", null);
		}
		EARKRepresentation rep = representations.get(representationID);
		rep.addPreservationMetadata(preservationMetadata.getMetadata(), preservationMetadata.getSchema());
	}

	/* (non-Javadoc)
	 * @see org.eark.model.impl.eark.SIP#createZip()
	 */
	@Override
	public Path createZip() {
		try {
			JAXBContext context = JAXBContext.newInstance(Mets.class);
			Marshaller m = context.createMarshaller();

			if (representations != null && representations.size() > 0) {
				for (Map.Entry<String, EARKRepresentation> entry : representations.entrySet()) {
					String representationMetsPath = "/representations/" + entry.getKey() + "/METS.xml";
					Path temp = Files.createTempFile("METS", ".xml");
					entry.getValue().saveMETSTo(temp);
					Utils.addFileToZip(zipPath, temp, representationMetsPath);

					StructMapType structMap = new StructMapType();
					DivType packageDiv = new DivType();
					packageDiv.setLabel("Package");
					packageDiv.setID("packageDiv");
					DivType representationMetadataDiv = new DivType();
					representationMetadataDiv.setID("representationMetadataDiv");
					representationMetadataDiv.setLabel("Representation Metadata");

					Mptr mptr = new Mptr();
					mptr.setLOCTYPE(LocType.URL.toString());
					mptr.setHref("file://." + representationMetsPath);
					mainMets.getStructMap().get(0).getDiv().getDiv().get(0).getMptr().add(mptr);

				}
			}

			Path temp = Files.createTempFile("METS", ".xml");
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			m.marshal(mainMets, Files.newOutputStream(temp));
			Utils.addFileToZip(zipPath, temp, "/METS.xml");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return zipPath;
	}

	class EARKRepresentation {
		Mets representationMETS;
		Path representationFolder;
		String representationID;

		public EARKRepresentation(Path representationPath, SIPRepresentation representation) throws SIPException {
			this.representationID = representation.getRepresentationID();
			representationFolder = representationPath;

			representationMETS = new Mets();
			representationMETS.setOBJID(representation.getObjectID());
			representationMETS.setPROFILE(representation.getProfile());
			representationMETS.setTYPE(representation.getType());
			MetsHdr header = new MetsHdr();
			try {
				XMLGregorianCalendar cal = Utils.getCurrentCalendar();
				header.setCREATEDATE(Utils.getCurrentCalendar());
				header.setLASTMODDATE(Utils.getCurrentCalendar());
			} catch (DatatypeConfigurationException dce) {
				throw new SIPException("Error getting current calendar", dce);
			}
			representationMETS.setMetsHdr(header);

			AmdSecType amdsec = new AmdSecType();
			amdsec.setID(UUID.randomUUID().toString());
			representationMETS.getAmdSec().add(amdsec);

			FileSec filesec = new FileSec();
			filesec.setID(UUID.randomUUID().toString());

			FileGrp generalFileGroup = new FileGrp();
			generalFileGroup.setUSE("general filegroup");
			generalFileGroup.setID(UUID.randomUUID().toString());
			filesec.getFileGrp().add(generalFileGroup);
			FileGrp schemaFileGroup = new FileGrp();
			schemaFileGroup.setUSE("schema group");
			schemaFileGroup.setID(UUID.randomUUID().toString());
			filesec.getFileGrp().add(schemaFileGroup);

			representationMETS.setFileSec(filesec);

			StructMapType structMap = new StructMapType();
			DivType packageDiv = new DivType();
			packageDiv.setLabel("Package");
			packageDiv.setID("packageDiv");
			DivType contentDiv = new DivType();
			contentDiv.setID("contentDiv");
			contentDiv.setLabel("Content");
			packageDiv.getDiv().add(contentDiv);
			DivType metadataDiv = new DivType();
			metadataDiv.setID("metadataDiv");
			metadataDiv.setLabel("Metadata");
			packageDiv.getDiv().add(metadataDiv);
			DivType schemasDiv = new DivType();
			schemasDiv.setID("schemasDiv");
			schemasDiv.setLabel("Schemas");
			packageDiv.getDiv().add(schemasDiv);
			structMap.setDiv(packageDiv);
			representationMETS.getStructMap().add(structMap);
		}

		public void saveMETSTo(Path temp) throws JAXBException, IOException {
			JAXBContext context = JAXBContext.newInstance(Mets.class);
			Marshaller m = context.createMarshaller();
			m.marshal(representationMETS, Files.newOutputStream(temp));

		}

		public void addAgent(SIPAgent sipAgent) {
			Agent agent = new Agent();
			agent.setROLE(sipAgent.getRole());
			agent.setTYPE(sipAgent.getType().toString());
			agent.setName(sipAgent.getName());
			agent.setOTHERROLE(sipAgent.getOtherRole());
			agent.setOTHERTYPE(sipAgent.getOtherType());
			MetsHdr hdr = representationMETS.getMetsHdr();
			hdr.getAgent().add(agent);
			representationMETS.setMetsHdr(hdr);
		}

		public void addDataFile(Path dataFile) throws SIPException {
			String dataFilePath = "/representations/" + representationID + "/data/" + dataFile.getFileName().toString();
			try {
				Utils.addFileToZip(zipPath, dataFile, dataFilePath);
			} catch (IOException e) {
				throw new SIPException("Error adding file to zip (" + dataFile.toString() + ")", e);
			}
			FileType ft = new FileType();
			try {
				ft.setCHECKSUM(Utils.calculateChecksum(Files.newInputStream(dataFile), "SHA-256"));
			} catch (IOException e) {
				throw new SIPException("Error calculating checksum for file" + dataFile.toString(), e);
			} catch (NoSuchAlgorithmException e) {
				throw new SIPException(
						"Error calculating checksum for file " + dataFile.toString() + " (no such algorithm)", e);
			}
			ft.setCHECKSUMTYPE("SHA-256");
			try {
				ft.setMIMETYPE(Files.probeContentType(dataFile));
			} catch (IOException e) {
				throw new SIPException("Error probing content-type(" + dataFile.toString() + ")", e);
			}
			try {
				ft.setCREATED(Utils.getCurrentCalendar());
			} catch (DatatypeConfigurationException e) {
				throw new SIPException("Error getting curent calendar (" + dataFile.toString() + ")", e);
			}
			try {
				ft.setSIZE(Files.size(dataFile));
			} catch (IOException e) {
				throw new SIPException("Error getting file size (" + dataFile.toString() + ")", e);
			}
			ft.setID(UUID.randomUUID().toString());
			FLocat locat = new FLocat();
			locat.setType("simple");
			locat.setLOCTYPE("URL");
			locat.setHref("file://./data/" + dataFile.getFileName().toString());
			ft.getFLocat().add(locat);
			representationMETS.getFileSec().getFileGrp().get(0).getFile().add(ft);

			Fptr fptr = new Fptr();
			fptr.setFILEID(ft);
			representationMETS.getStructMap().get(0).getDiv().getDiv().get(0).getFptr().add(fptr);

		}

		public void addPreservationMetadata(Path preservationMetadata, Path schema) throws SIPException {
			String preservationFilePath = "/representations/" + representationID + "/metadata/preservation/"
					+ preservationMetadata.getFileName().toString();
			try {
				if (schema != null) {
					String schemaPath = "/schemas/" + schema.getFileName().toString();
					Utils.addFileToZip(zipPath, schema, schemaPath);
					Path temp = Files.createTempFile("temp", ".xml");
					Files.copy(preservationMetadata, temp, StandardCopyOption.REPLACE_EXISTING);
					Utils.addSchemaLocationToPath(temp, "../../../.." + schemaPath);
					Files.copy(temp, preservationMetadata, StandardCopyOption.REPLACE_EXISTING);
				}
				Utils.addFileToZip(zipPath, preservationMetadata, preservationFilePath);
			} catch (IOException e) {
				throw new SIPException("Error adding representation preservation metadata to zip", e);
			}
			FileType ft = new FileType();
			try {
				ft.setCHECKSUM(Utils.calculateChecksum(Files.newInputStream(preservationMetadata), "SHA-256"));
			} catch (IOException e) {
				throw new SIPException("Error calculating checksum for representation preservation metadata", e);
			} catch (NoSuchAlgorithmException e) {
				throw new SIPException(
						"Error calculating checksum for representation preservation metadata (no such algorithm)", e);

			}
			ft.setCHECKSUMTYPE("SHA-256");
			try {
				ft.setMIMETYPE(Files.probeContentType(preservationMetadata));
			} catch (IOException e) {
				throw new SIPException("Error probing file content (" + preservationMetadata.toString() + ")", e);
			}
			try {
				ft.setCREATED(Utils.getCurrentCalendar());
			} catch (DatatypeConfigurationException dce) {
				throw new SIPException("Error getting current calendar", dce);
			}
			try {
				ft.setSIZE(Files.size(preservationMetadata));
			} catch (IOException e) {
				throw new SIPException("Error getting file size (" + preservationMetadata.toString() + ")", e);
			}
			ft.setID(UUID.randomUUID().toString());
			FLocat locat = new FLocat();
			locat.setType("URL");
			locat.setHref("file://./metadata/preservation/" + preservationMetadata.getFileName().toString());
			ft.getFLocat().add(locat);
			representationMETS.getFileSec().getFileGrp().get(0).getFile().add(ft);

			Fptr fptr = new Fptr();
			fptr.setFILEID(ft);
			representationMETS.getStructMap().get(0).getDiv().getDiv().get(1).getFptr().add(fptr);

		}
		public Mets getRoot() {
			return representationMETS;
		}

	}
}
