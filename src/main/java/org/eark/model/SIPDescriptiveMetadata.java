package org.eark.model;

import java.nio.file.Path;

import org.eark.utils.METSEnums.MetadataType;

public class SIPDescriptiveMetadata extends SIPMetadata{
	
	public SIPDescriptiveMetadata(Path metadata, Path schema, MetadataType metadataType){
		super(metadata, schema);
		this.metadataType = metadataType;
	}
	private MetadataType metadataType;

	public MetadataType getMetadataType() {
		return metadataType;
	}

	public void setMetadataType(MetadataType metadataType) {
		this.metadataType = metadataType;
	}
}
