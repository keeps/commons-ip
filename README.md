# E-ARK IP manipulation java library

API to manipulate Information Packages of different formats: RODA, E-ARK, etc.

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
    <version>1.0.0-alpha19</version>
  </dependency>
  ```

* Not using maven, download [Commons IP latest jar](http://artifactory.keep.pt/keep/org/roda-project/commons-ip/1.0.0-alpha19/commons-ip-1.0.0-alpha19.jar), each of Commons IP dependencies (see pom.xml to know which dependencies/versions) and add them to your project classpath.


### Write some code

* Create a full E-ARK SIP
```java
// 1) instantiate E-ARK SIP object
SIP sip = new EARKSIP("SIP_1", IPContentType.getMIXED(), "RODA Commons IP");

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
// called 'abc' which has a folder inside called 'def'
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
    SIP sip = new EARKSIP("SIP_1", IPContentType.getMIXED(), "RODA Commons IP");
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

## History

#### Alpha 16 (2016-08-10)

* Representations now have a status attribute (which will be stored in /mets/structMap[@LABEL="E-ARK structural map"]/div/@TYPE).
* All classes that extend IPMetadata have a type (MetadataType).
* Minor fixes.

#### Alpha 15 (2016-07-05)

* An IP now has a status attribute (which will be stored in /mets/metsHdr/@RECORDSTATUS).
* IP parent information was replaced by ancestors information (more suitable for information organized hierarchically).

#### Alpha 14 (2016-06-23)

* When loading an SIP, METS is validated againts its schema.
* Now all METS IDs are prefixed with the string 'ID'.

#### Alpha 13 (2016-05-10)

* Now validation report can be exported to HTML (full and partial).

#### Alpha 12 (2016-05-04)

* Improved validation report by making possible to add info entries (before only warn and error were the alternatives).
* Improved E-ARK SIP parsing by filling in validations and more warning/errors.
* Now IPs and Representations have created and modified date.
* IPContentType, RepresentationContentType and MetadataType are no longer just an Enum but instead a class (which has an ENUM but also the otherType field).

#### Alpha 11 (2016-04-05)

* Improved E-ARK SIP generation time by doing checksum calculation during ZIP creation.

#### Alpha 10 (2016-03-04)

* Now representations are ordered (i.e. when exporting into E-ARK SIP the order by which a representation was added to the SIP is respected).

#### Alpha 9 (2016-02-25)

* __MetadataType__ enum now has support for other type (which will be set in OTHERMDTYPE METS attribute; can be used when selecting __MetadataType.OTHER__ enum value).

#### Alpha 8 (2016-02-12)

* The __SIP.build__ method now throws _InterruptedException_ and correctly handles, in the rights places in the code, interruptions made to the thread executing the method. And, in that case, unneeded files are properly cleaned up/deleted.

#### Alpha 7 (2016-02-11)

* Added more events to SIPObserver (events related to representations/representation processing which are done before SIP packaging, i.e., calculate checksum and other operations that might take awhile).

#### Alpha 6 (2016-02-11)

* Removed Parser interface and EARKParser implementation: now this must be done in a concrete SIP class as the IP interface has a static method _parse_. This way, both _build_ and _parse_ code are located in the same class (take EARKSIP as an example).

#### Alpha 5 (2016-02-11)

* Refactored code to better use inheritance and interfaces.
* Now SIP implements the Observer Pattern (SIP is observable and SIPObserver, well, you can figure that out).

#### Alpha 4 (2016-02-09)

* Almost 100 % done with EARKSIP.build (SIP to ZIP) and EARKParser.parse (ZIP to SIP) Common Specification v0.13 compliant.

#### Alpha 3 (2016-02-03)

* Going towards getting the commons-ip compliant with E-ARK Common Specification v0.13.
* Bug fixes (file leaks, etc.).

## Credits

TODO: Write credits

## License

LGPLv3
