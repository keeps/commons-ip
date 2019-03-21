# Changelog 

## 2.0.0-alpha1 (2019-10-31)

* E-ARK SIP 2 support (E-ARK Common Specification 2.0.1 compliant)
* Fixed file detection not setting mimetype attribute in METS (when detection failed; see https://github.com/keeps/commons-ip/issues/30)

## 1.0.3 (2017-07-12)

* Hungarian SIP related fixes

## 1.0.2 (2017-07-05)

* Hungarian SIP related fixes

## 1.0.1 (2017-06-29)

* Hungarian SIP related fixes

## 1.0.0 (2017-06-22)

* Hungarian type 4 SIP fixes to the mdWrap/@id (https://github.com/keeps/roda-in/issues/302) and struct map to reflect folder structure (https://github.com/keeps/roda-in/issues/304)

## Alpha 30/31 (2017-06-08)

* Almost fully compliant with [Common Specification 1.0](http://dasboard.eu/images/Specifications/CS/Common_Specifications_for_IPs_v10.pdf) (issue #16)
* Fixed METS paths URL encoding (issue #27)

## Alpha 28/29 (2017-04-04)

* Added BagIt implementation (both build and parse)
* Added Hungarian type 4 SIP (just build)
* Added new values to IPStatus (issue #25).

## Alpha 26/27 (2016-12-22)

* Minor fixes/improvements (issue #21). 

## Alpha 25 (2016-12-14)

* Now E-ARK AIP is also supported
* Fixed bug that was happening when E-ARK SIP zip file had a different filename from the ID and was causing a validation error (when root folder exists; issue #19).

## Alpha 24 (2016-11-11)

* Now E-ARK SIP zip has a root folder with the sip id as name
* Now it's possible to define the `id` of the metadata file being packaged (needed to support updates in RODA)

## Alpha 22 & 23 (2016-10-31)

* Fixed how relative paths are stored in METS (as anyURI is sensitive to some chars, percentage encoding/decoding is needed)
* IDs in METS are now prefixed with `uuid-` (instead of `ID`)

## Alpha 17, 18, 19, 20 & 21 (2016-10-21)

* Relative paths in METS don't contain anymore any prefix (and, when doing parse, several prefixes are tested for removal in order to ensure backward compatibility).
* Removed unreachable code related to calculating checksums during zip or not (now it is always calculated during).
* Some information about the ZIP generation is available in IPs (e.g. for generating a report after an SIP to ZIP operation).
* Now IPs may have several IDs.
* Dependencies updated.
* Minor fixes.

## Alpha 16 (2016-08-10)

* Representations now have a status attribute (which will be stored in /mets/structMap[@LABEL="E-ARK structural map"]/div/@TYPE).
* All classes that extend IPMetadata have a type (MetadataType).
* Minor fixes.

## Alpha 15 (2016-07-05)

* An IP now has a status attribute (which will be stored in /mets/metsHdr/@RECORDSTATUS).
* IP parent information was replaced by ancestors information (more suitable for information organized hierarchically).

## Alpha 14 (2016-06-23)

* When loading an SIP, METS is validated againts its schema.
* Now all METS IDs are prefixed with the string 'ID'.

## Alpha 13 (2016-05-10)

* Now validation report can be exported to HTML (full and partial).

## Alpha 12 (2016-05-04)

* Improved validation report by making possible to add info entries (before only warn and error were the alternatives).
* Improved E-ARK SIP parsing by filling in validations and more warning/errors.
* Now IPs and Representations have created and modified date.
* IPContentType, RepresentationContentType and MetadataType are no longer just an Enum but instead a class (which has an ENUM but also the otherType field).

## Alpha 11 (2016-04-05)

* Improved E-ARK SIP generation time by doing checksum calculation during ZIP creation.

## Alpha 10 (2016-03-04)

* Now representations are ordered (i.e. when exporting into E-ARK SIP the order by which a representation was added to the SIP is respected).

## Alpha 9 (2016-02-25)

* __MetadataType__ enum now has support for other type (which will be set in OTHERMDTYPE METS attribute; can be used when selecting __MetadataType.OTHER__ enum value).

## Alpha 8 (2016-02-12)

* The __SIP.build__ method now throws _InterruptedException_ and correctly handles, in the rights places in the code, interruptions made to the thread executing the method. And, in that case, unneeded files are properly cleaned up/deleted.

## Alpha 7 (2016-02-11)

* Added more events to SIPObserver (events related to representations/representation processing which are done before SIP packaging, i.e., calculate checksum and other operations that might take awhile).

## Alpha 6 (2016-02-11)

* Removed Parser interface and EARKParser implementation: now this must be done in a concrete SIP class as the IP interface has a static method _parse_. This way, both _build_ and _parse_ code are located in the same class (take EARKSIP as an example).

## Alpha 5 (2016-02-11)

* Refactored code to better use inheritance and interfaces.
* Now SIP implements the Observer Pattern (SIP is observable and SIPObserver, well, you can figure that out).

## Alpha 4 (2016-02-09)

* Almost 100 % done with EARKSIP.build (SIP to ZIP) and EARKParser.parse (ZIP to SIP) Common Specification v0.13 compliant.

## Alpha 3 (2016-02-03)

* Going towards getting the commons-ip compliant with E-ARK Common Specification v0.13.
* Bug fixes (file leaks, etc.).
