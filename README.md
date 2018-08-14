# E-ARK IP manipulation java library

API to manipulate OAIS Information Packages of different formats: E-ARK, BagIt, Hungarian type 4 SIP. 

The E-ARK Information Packages are maintained by the Digital Information LifeCycle Interoperability Standards Board (DILCIS Board).  DILCIS Board is an international group of experts committed to maintain and sustain maintain a set of interoperability specifications which allow for the transfer, long-term preservation, and reuse of digital information regardless of the origin or type of the information.

More specifically, the DILCIS Board maintains specifications initially developed within the E-ARK Project (02.2014 - 01.2017): 

- Common Specification for Information Packages
- E-ARK Submission Information Package (SIP)
- E-ARK Archival Information Package (AIP)
- E-ARK Dissemination Information Package (DIP)

The DILCIS Board collaborates closely with the Swiss Federal Archives in regard to the maintenance of the SIARD (Software Independent Archiving of Relational Databases) specification. 

For more information about the E-ARK Information Packages specifications, please visit http://www.dilcis.eu/

## Installation

### Requirements

* Java (>= 1.8)
* Maven (>= 2.2)


## Usage

### Add Commons IP to your Java project

* Using maven

1. Add the following repository

  ```xml
  <repository>
    <id>KEEPS-Artifacts</id>
    <name>KEEP Artifacts-releases</name>
    <url>http://artifactory.keep.pt/keep</url>
  </repository>
  ```
1. Add the following dependency

  ```xml
  <dependency>
    <groupId>org.roda-project</groupId>
    <artifactId>commons-ip</artifactId>
    <version>1.0.3</version>
  </dependency>
  ```

* Not using maven, download [Commons IP latest jar](http://artifactory.keep.pt/keep/org/roda-project/commons-ip/1.0.3/commons-ip-1.0.3.jar), each of Commons IP dependencies (see pom.xml to know which dependencies/versions) and add them to your project classpath.


### Write some code

* Create a full E-ARK SIP

```java
// 1) instantiate E-ARK SIP object
SIP sip = new EARKSIP("SIP_1", IPContentType.getMIXED());

// 1.1) set optional human-readable description
sip.setDescription("A full E-ARK SIP");

// 1.2) add descriptive metadata (SIP level)
IPDescriptiveMetadata metadataDescriptiveDC = new IPDescriptiveMetadata(
new IPFile(Paths.get("src/test/resources/eark/metadata_descriptive_dc.xml")),
new MetadataType(MetadataTypeEnum.DC), null);
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

// 1.7) set optional RODA related information about ancestors
sip.setAncestors(Arrays.asList("b6f24059-8973-4582-932d-eb0b2cb48f28"));

// 1.8) add an agent (SIP level)
IPAgent agent = new IPAgent("Agent Name", "OTHER", "OTHER ROLE", CreatorType.INDIVIDUAL, "OTHER TYPE");
sip.addAgent(agent);

// 1.9) add a representation (status will be set to the default value, i.e.,
// ORIGINAL)
IPRepresentation representation1 = new IPRepresentation("representation 1");
sip.addRepresentation(representation1);

// 1.9.1) add a file to the representation
IPFile representationFile = new IPFile(Paths.get("src/test/resources/eark/documentation.pdf"));
representationFile.setRenameTo("data.pdf");
representation1.addFile(representationFile);

// 1.9.2) add a file to the representation and put it inside a folder
// called 'def' which is inside a folder called 'abc'
IPFile representationFile2 = new IPFile(Paths.get("src/test/resources/eark/documentation.pdf"));
representationFile2.setRelativeFolders(Arrays.asList("abc", "def"));
representation1.addFile(representationFile2);

// 1.10) add a representation & define its status
IPRepresentation representation2 = new IPRepresentation("representation 2");
representation2.setStatus(new RepresentationStatus(REPRESENTATION_STATUS_NORMALIZED));
sip.addRepresentation(representation2);

// 1.10.1) add a file to the representation
IPFile representationFile3 = new IPFile(Paths.get("src/test/resources/eark/documentation.pdf"));
representationFile3.setRenameTo("data3.pdf");
representation2.addFile(representationFile3);

// 2) build SIP, providing an output directory
Path zipSIP = sip.build(tempFolder);
```
**Note:** SIP implements the Observer Pattern. This way, if one wants to be notified of SIP build progress, one just needs to implement SIPObserver interface and register itself in the SIP. Something like (just presenting some of the events):

```java
public class WhoWantsToBuildSIPAndBeNotified implements SIPObserver{

  public void buildSIP(){
    ...
    SIP sip = new EARKSIP("SIP_1", IPContentType.getMIXED());
    sip.addObserver(this);
    ...
  }

  @Override
  public void sipBuildPackagingStarted(int totalNumberOfFiles) {
    ...
  }

  @Override
  public void sipBuildPackagingCurrentStatus(int numberOfFilesAlreadyProcessed) {
    ...
  }
}
```



* Parse a full E-ARK SIP

```java
// 1) invoke static method parse and that's it
SIP earkSIP = EARKSIP.parse(zipSIP);
```

## Contributing

1. Fork it!
2. Create your feature branch: `git checkout -b my-new-feature`
3. Commit your changes: `git commit -am 'Add some feature'`
4. Push to the branch: `git push origin my-new-feature`
5. Submit a pull request :D

## Credits

- Hélder Silva (KEEP SOLUTIONS)

## License

LGPLv3
