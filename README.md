# RODA Commons IP 

The RODA Commons IP provides an API to manipulate Information Packages of different types: RODA, E-ARK, etc.

## Installation

### Requirements

* Java (>= 1.8)
* Maven (>= 2.2)


## Usage

### Add Commons IP to your Java project

* Using maven

1. Add the following repository
  
  ```
  <repository>
    <id>KEEPS-Artifacts</id>
    <name>KEEP Artifacts-releases</name>
    <url>http://artifactory.keep.pt/keep</url>
  </repository>
  ```
1. Add the following dependency
  
  ```
  <dependency>
    <groupId>org.roda-project</groupId>
    <artifactId>commons-ip</artifactId>
    <version>1.0.0-alpha4</version>
  </dependency>
  ```

* Not using maven, download [Commons IP latest jar](http://artifactory.keep.pt/keep/org/roda-project/commons-ip/1.0.0-alpha4/commons-ip-1.0.0-alpha4.jar), each of Commons IP dependencies (see pom.xml to know which dependencies/versions) and add them to your project classpath.


### Write some code

* Create a full E-ARK SIP
```java
// 1) instantiate E-ARK SIP object
SIP sip = new EARKSIP("SIP_1", ContentType.mixed, "RODA Commons IP");

// 1.1) set optional human-readable description
sip.setDescription("A full E-ARK SIP");

// 1.2) add descriptive metadata (SIP level)
IPDescriptiveMetadata metadataDescriptiveDC = new IPDescriptiveMetadata(
new IPFile(Paths.get("src/test/resources/eark/metadata_descriptive_dc.xml")), MetadataType.DC, null);
sip.addDescriptiveMetadata(metadataDescriptiveDC);

// 1.3) add preservation metadata (SIP level)
IPMetadata metadataPreservation = new IPMetadata(
new IPFile(Paths.get("src/test/resources/eark/metadata_preservation_premis.xml")));
sip.addPreservationMetadata(metadataPreservation);

// 1.4) add other metadata (SIP level)
IPFile metadataOtherFile = new IPFile(Paths.get("src/test/resources/eark/metadata_other.txt"));
// 1.4.1) optionally one may rename file final name
metadataOtherFile.setRenameTo("metadata_other_renamed.txt");
IPMetadata metadataOther = new IPMetadata(metadataOtherFile);
sip.addOtherMetadata(metadataOther);

// 1.5) add xml schema (SIP level)
sip.addSchema(new IPFile(Paths.get("src/test/resources/eark/schema.xsd")));

// 1.6) add documentation (SIP level)
sip.addDocumentation(new IPFile(Paths.get("src/test/resources/eark/documentation.pdf")));

// 1.7) set optional RODA related information parent id
sip.setParent("b6f24059-8973-4582-932d-eb0b2cb48f28");

// 1.8) add an agent (SIP level)
IPAgent agent = new IPAgent("Agent Name", "ROLE", "OTHER ROLE", CreatorType.INDIVIDUAL, "OTHER TYPE");
sip.addAgent(agent);

// 1.9) add a representation
IPRepresentation representation1 = new IPRepresentation("representation 1");
sip.addRepresentation(representation1);

// 1.9.1) add a file to the representation
IPFile representationFile = new IPFile(Paths.get("src/test/resources/eark/documentation.pdf"));
representationFile.setRenameTo("data.pdf");
representation1.addFile(representationFile);

// 1.9.2) add a file to the representation and put it inside a folder
// called 'abc' which has a folder inside called 'def'
IPFile representationFile2 = new IPFile(Paths.get("src/test/resources/eark/documentation.pdf"));
representationFile2.setRelativeFolders(Arrays.asList("abc", "def"));
representation1.addFile(representationFile2);

// 2) build SIP, providing an output directory
Path zipSIP = sip.build(tempFolder);
```

* Parse a full E-ARK SIP
```java
// 1) instantiate E-ARK parser
Parser earkParser = new EARKParser();
// 2) parse zip file to obtain an SIP
SIP sip = earkParser.parse(zipSIP);
```

## Contributing

1. Fork it! 
2. Create your feature branch: `git checkout -b my-new-feature`
3. Commit your changes: `git commit -am 'Add some feature'`
4. Push to the branch: `git push origin my-new-feature`
5. Submit a pull request :D

## History

TODO: Write history

## Credits

TODO: Write credits

## License

LGPLv3

