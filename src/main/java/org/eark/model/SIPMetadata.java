package org.eark.model;

import java.nio.file.Path;

public class SIPMetadata {
	private Path metadata;
	private Path schema;
	
	public SIPMetadata(Path metadata, Path schema){
		this.metadata = metadata;
		this.schema = schema;
	}
	public Path getMetadata() {
		return metadata;
	}
	public void setMetadata(Path metadata) {
		this.metadata = metadata;
	}
	public Path getSchema() {
		return schema;
	}
	public void setSchema(Path schema) {
		this.schema = schema;
	}
	
	
}
