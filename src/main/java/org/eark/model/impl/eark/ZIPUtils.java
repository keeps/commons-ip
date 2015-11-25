package org.eark.model.impl.eark;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

import org.eark.model.SIPDescriptiveMetadata;
import org.eark.model.SIPMetadata;
import org.eark.utils.SIPException;
import org.eark.utils.Utils;

public class ZIPUtils {

	public static void addMetadataToZip(Path zipPath, SIPMetadata dm) throws SIPException {
		try {
			String metadataPath = "/metadata/" + dm.getMetadata().getFileName().toString();
			if (dm.getSchema() != null) {
				Path source = dm.getMetadata();
				String schemaPath = "/schemas/" + dm.getSchema().getFileName().toString();
				Utils.addFileToZip(zipPath, dm.getSchema(), schemaPath);
				Path temp = Files.createTempFile("temp", ".xml");
				Files.copy(dm.getMetadata(), temp, StandardCopyOption.REPLACE_EXISTING);
				Utils.addSchemaLocationToPath(temp, ".." + schemaPath);
				Files.copy(temp, source, StandardCopyOption.REPLACE_EXISTING);
				dm.setMetadata(source);
			}
			Utils.addFileToZip(zipPath, dm.getMetadata(), metadataPath);
		} catch (IOException e) {
			throw new SIPException("Error adding metadata to SIP: " + e.getMessage(), e);
		}
		
	}

	public static void addDescriptiveMetadataToZip(Path zipPath, SIPDescriptiveMetadata dm, String descriptiveMetadataPath) throws SIPException {
		
		try {
			if (dm.getSchema() != null) {
				Path source = dm.getMetadata();
				String schemaPath = "/schemas/" + dm.getSchema().getFileName().toString();
				Utils.addFileToZip(zipPath, dm.getSchema(), schemaPath);
				Path temp = Files.createTempFile("temp", ".xml");
				Files.copy(dm.getMetadata(), temp, StandardCopyOption.REPLACE_EXISTING);
				Utils.addSchemaLocationToPath(temp, "../.." + schemaPath);
				Files.copy(temp, source, StandardCopyOption.REPLACE_EXISTING);
				dm.setMetadata(source);
			}
			Utils.addFileToZip(zipPath, dm.getMetadata(), descriptiveMetadataPath);
		} catch (IOException e) {
			throw new SIPException("Error adding descriptive metadata to ZIP", e);
		}
		
	}

	public static void createRepresentationFolder(Path zipPath, String representationID) throws SIPException{
		try {
			Path temp = Files.createTempDirectory("rep");
			String representationPath = "/representations/" + representationID;
			Utils.addFileToZip(zipPath, temp, representationPath);
		} catch (IOException e) {
			throw new SIPException("Errpr creating representation folder in zip", e);
		}
		
	}

	public static void addDataToRepresentation(Path zipPath, Path dataFile, String dataFilePath) throws SIPException {
		try {
			Utils.addFileToZip(zipPath, dataFile, dataFilePath);
		} catch (IOException e) {
			throw new SIPException("Error adding file to zip (" + dataFile.toString() + ")", e);
		}
		
	}

	public static void addPreservationMetadataToRepresentation(Path zipPath, String preservationFilePath, SIPMetadata metadata) throws SIPException {
		try {
			if (metadata.getSchema() != null) {
				String schemaPath = "/schemas/" + metadata.getSchema().getFileName().toString();
				Utils.addFileToZip(zipPath, metadata.getSchema(), schemaPath);
				Path temp = Files.createTempFile("temp", ".xml");
				Files.copy(metadata.getMetadata(), temp, StandardCopyOption.REPLACE_EXISTING);
				Utils.addSchemaLocationToPath(temp, "../../../.." + schemaPath);
				Files.copy(temp, metadata.getMetadata(), StandardCopyOption.REPLACE_EXISTING);
			}
			Utils.addFileToZip(zipPath, metadata.getMetadata(), preservationFilePath);
		} catch (IOException e) {
			throw new SIPException("Error adding representation preservation metadata to zip", e);
		}
		
	}

}
