/**
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE file at the root of the source
 * tree and available online at
 *
 * https://github.com/keeps/commons-ip
 */
package org.roda_project.commons_ip.utils;

import org.roda_project.commons_ip.model.IPConstants;
import org.roda_project.commons_ip2.model.ValidationEntry;
import org.roda_project.commons_ip2.model.ValidationEntry.TYPE;

public final class ValidationConstants {
  // Mets related
  public static final String METS_FILE_NOT_FOUND = "METS file was not found in SIP.";
  public static final String METS_NOT_VALID = "METS file is not valid.";
  public static final String METS_IS_VALID = "METS file is valid.";
  public static final String METS_HAS_SEVERAL_STRUCT_MAPS = "METS file has several struct maps.";
  public static final String METS_HAS_ONLY_ONE_STRUCT_MAP = "METS file has one struct map.";
  public static final String METS_AGENT_HAS_SEVERAL_NOTE_ENTRIES = "METS agent has several note entries. Only the first will be considered!";

  // Main mets related
  public static final String MAIN_METS_FILE_FOUND = "Main METS.xml file was found.";
  public static final String MAIN_METS_FILE_NOT_FOUND = "Main METS.xml file was not found.";
  public static final String MAIN_METS_NOT_VALID = "Main METS.xml file is not valid.";
  public static final String MAIN_METS_IS_VALID = "Main METS.xml file is valid.";
  public static final String MAIN_METS_FILE_FOUND_BUT_INSIDE_FOLDER_THAT_DOES_NOT_MATCH_OBJECT_ID = "Main METS.xml file was found but it is inside a folder that does not match object id.";
  public static final String MAIN_METS_NO_REPRESENTATIONS_FOUND = "No representations were found in main METS.xml file.";

  // Representation mets related
  public static final String REPRESENTATION_METS_FILE_FOUND = "Representation METS.xml file was found.";
  public static final String REPRESENTATION_METS_FILE_NOT_FOUND = "Representation METS.xml file is referenced in the main METS.xml but was not found.";
  public static final String REPRESENTATION_METS_NOT_VALID = "Representation METS.xml file is not valid.";
  public static final String REPRESENTATION_METS_IS_VALID = "Representation METS.xml file is valid.";
  public static final String REPRESENTATION_HAS_NO_FILES = "Representation has no files.";

  // Mets structmap related
  public static final String MAIN_METS_HAS_NO_E_ARK_STRUCT_MAP = "Main METS.xml file has no E-ARK structural map.";
  public static final String MAIN_METS_HAS_E_ARK_STRUCT_MAP = "Main METS.xml file has E-ARK structural map.";
  public static final String REPRESENTATION_METS_HAS_NO_E_ARK_STRUCT_MAP = "Representation METS.xml file has no E-ARK structural map.";
  public static final String REPRESENTATION_METS_HAS_E_ARK_STRUCT_MAP = "Representation METS.xml file has E-ARK structural map.";

  // Mets metadata related
  public static final String METADATA_FILE_FOUND = "Metadata file was found and file checksum matches METS checksum.";
  public static final String METADATA_FILE_NOT_FOUND = "Metadata file not found.";
  public static final String METADATA_FPTR_NOT_FOUND = "Metadata has no FPTR.";
  public static final String DESCRIPTIVE_METADATA_FILE_FOUND_WITH_MATCHING_CHECKSUMS = "Descriptive metadata file was found and file checksum matches METS checksum.";
  public static final String DESCRIPTIVE_METADATA_FILE_NOT_FOUND = "Descriptive metadata file not found.";
  public static final String DESCRIPTIVE_METADATA_FPTR_NOT_FOUND = "Descriptive metadata has no FPTR.";
  public static final String PRESERVATION_METADATA_FILE_FOUND_WITH_MATCHING_CHECKSUMS = "Preservation metadata file was found and file checksum matches METS checksum.";
  public static final String PRESERVATION_METADATA_FILE_NOT_FOUND = "Preservation metadata file not found.";
  public static final String PRESERVATION_METADATA_FPTR_NOT_FOUND = "Preservation metadata has no FPTR.";
  public static final String OTHER_METADATA_FILE_FOUND_WITH_MATCHING_CHECKSUMS = "Other metadata file was found and file checksum matches METS checksum.";
  public static final String OTHER_METADATA_FILE_NOT_FOUND = "Other metadata file not found.";
  public static final String OTHER_METADATA_FPTR_NOT_FOUND = "Other metadata has no FPTR.";
  public static final String UNKNOWN_DESCRIPTIVE_METADATA_TYPE = "Descriptive metadata type is unknown.";
  public static final String UNKNOWN_PRESERVATION_METADATA_TYPE = "Preservation metadata type is unknown.";

  // Mets checksum related
  public static final String CHECKSUM_NOT_SET = "Checksum in METS.xml is not set.";
  public static final String CHECKSUM_ALGORITHM_NOT_SET = "Checksum algorithm in METS.xml is not set.";
  public static final String CHECKSUMS_DIFFER = "Checksum in METS.xml doesn't match file checksum.";
  public static final String ERROR_COMPUTING_CHECKSUM = "Error computing checksum.";
  public static final String ERROR_COMPUTING_CHECKSUM_NO_SUCH_ALGORITHM = "Error computing checksum: the algorithm provided is not recognized.";

  // Mets representation related
  public static final String REPRESENTATION_FILE_FOUND_WITH_MATCHING_CHECKSUMS = "Representation file referenced in METS.xml was found and file checksum matches METS checksum.";
  public static final String REPRESENTATION_FILE_NOT_FOUND = "Representation file referenced in METS.xml not found.";
  public static final String REPRESENTATION_FILE_HAS_NO_FLOCAT = "Representation file referenced in METS.xml has no FLocat element.";

  // Mets schemas and documentation related
  public static final String SCHEMA_FILE_FOUND_WITH_MATCHING_CHECKSUMS = "Schema file referenced in METS.xml was found and file checksum matches METS checksum.";
  public static final String SCHEMA_FILE_NOT_FOUND = "Schema file referenced in METS.xml was not found.";
  public static final String DOCUMENTATION_FILE_FOUND_WITH_MATCHING_CHECKSUMS = "Documentation file referenced in METS.xml was found and file checksum matches METS checksum.";
  public static final String DOCUMENTATION_FILE_NOT_FOUND = "Documentation file referenced in METS.xml was not found.";
  public static final String SUBMISSION_FILE_FOUND_WITH_MATCHING_CHECKSUMS = "Submission file referenced in METS.xml was found and file checksum matches METS checksum.";
  public static final String SUBMISSION_FILE_NOT_FOUND = "Submission file referenced in METS.xml was not found.";

  public static final ValidationEntry CSIPSTR1 = new ValidationEntry(TYPE.STRUCTURAL, "CSIPSTR1",
    "Any Information Package MUST be included within a single physical root folder (known as the “Information Package root folder”). For packages presented in an archive format, see CSIPSTR3, the archive MUST unpack to a single root folder.");
  public static final ValidationEntry CSIPSTR2 = new ValidationEntry(TYPE.STRUCTURAL, "CSIPSTR2",
    "The Information Package root folder SHOULD be named with the ID or name of the Information Package, that is the value of the package METS.xml’s root <mets> element’s @OBJID attribute.");
  public static final ValidationEntry CSIPSTR3 = new ValidationEntry(TYPE.STRUCTURAL, "CSIPSTR3",
    "The Information Package root folder MAY be compressed (for example by using TAR or ZIP). Which specific compression format to use needs to be stated in the Submission Agreement.");
  public static final ValidationEntry CSIPSTR4 = new ValidationEntry(TYPE.STRUCTURAL, "CSIPSTR4",
    "The Information Package root folder MUST include a file named METS.xml. This file MUST contain metadata that identifies the package, provides a high-level package description, and describes its structure, including pointers to constituent representations.");
  public static final ValidationEntry CSIPSTR5 = new ValidationEntry(TYPE.STRUCTURAL, "CSIPSTR5",
    "The Information Package root folder SHOULD include a folder named metadata, which SHOULD include metadata relevant to the whole package.");
  public static final ValidationEntry CSIPSTR6 = new ValidationEntry(TYPE.STRUCTURAL, "CSIPSTR6",
    "If preservation metadata are available, they SHOULD be included in sub-folder preservation.");
  public static final ValidationEntry CSIPSTR7 = new ValidationEntry(TYPE.STRUCTURAL, "CSIPSTR7",
    "If descriptive metadata are available, they SHOULD be included in sub-folder descriptive.");
  public static final ValidationEntry CSIPSTR8 = new ValidationEntry(TYPE.STRUCTURAL, "CSIPSTR8",
    "If any other metadata are available, they MAY be included in separate sub-folders, for example an additional folder named other.");
  public static final ValidationEntry CSIPSTR9 = new ValidationEntry(TYPE.STRUCTURAL, "CSIPSTR9",
    "The Information Package folder SHOULD include a folder named representations.");
  public static final ValidationEntry CSIPSTR10 = new ValidationEntry(TYPE.STRUCTURAL, "CSIPSTR10",
    "The representations folder SHOULD include a sub-folder for each individual representation (i.e. the “representation folder”). Each representation folder should have a string name that is unique within the package scope. For example the name of the representation and/or its creation date might be good candidates as a representation sub-folder name.");
  public static final ValidationEntry CSIPSTR11 = new ValidationEntry(TYPE.STRUCTURAL, "CSIPSTR11",
    "The representation folder SHOULD include a sub-folder named data which MAY include all data constituting the representation.");
  public static final ValidationEntry CSIPSTR12 = new ValidationEntry(TYPE.STRUCTURAL, "CSIPSTR12",
    "The representation folder SHOULD include a metadata file named METS.xml which includes information about the identity and structure of the representation and its components. The recommended best practice is to always have a METS.xml in the representation folder.");
  public static final ValidationEntry CSIPSTR13 = new ValidationEntry(TYPE.STRUCTURAL, "CSIPSTR13",
    "The representation folder SHOULD include a sub-folder named metadata which MAY include all metadata about the specific representation.");
  public static final ValidationEntry CSIPSTR14 = new ValidationEntry(TYPE.STRUCTURAL, "CSIPSTR14",
    "The Information Package MAY be extended with additional sub-folders.");
  public static final ValidationEntry CSIPSTR15 = new ValidationEntry(TYPE.STRUCTURAL, "CSIPSTR15",
    "We recommend including all XML schema documents for any structured metadata within package. These schema documents SHOULD be placed in a sub-folder called schemas within the Information Package root folder and/or the representation folder.");
  public static final ValidationEntry CSIPSTR16 = new ValidationEntry(TYPE.STRUCTURAL, "CSIPSTR16",
    "We recommend including any supplementary documentation for the package or a specific representation within the package. Supplementary documentation SHOULD be placed in a sub-folder called documentation within the Information Package root folder and/or the representation folder.");

  public static final ValidationEntry CSIP1 = new ValidationEntry(TYPE.STRUCTURAL, "CSIP1",
    "The mets/@OBJID attribute is mandatory, its value is a string identifier for the METS document. For the package METS document, this should be the name/ID of the package, i.e. the name of the package root folder. For a representation level METS document this value records the name/ID of the representation, i.e. the name of the top-level representation folder.");
  public static final ValidationEntry CSIP2 = new ValidationEntry(TYPE.STRUCTURAL, "CSIP2",
    "The mets/@TYPE attribute MUST be used to declare the category of the content held in the package, e.g. book, journal, stereograph, video, etc.. Legal values are defined in a fixed vocabulary. When the content category used falls outside of the defined vocabulary the mets/@TYPE value must be set to “OTHER” and the specific value declared in mets/@csip:OTHERTYPE. The vocabulary will develop under the curation of the DILCIS Board as additional content information type specifications are produced.");
  public static final ValidationEntry CSIP3 = new ValidationEntry(TYPE.STRUCTURAL, "CSIP3",
    "When the mets/@TYPE attribute has the value “OTHER” the mets/@csip:OTHERTYPE attribute MUST be used to declare the content category of the package/representation.");
  public static final ValidationEntry CSIP4 = new ValidationEntry(TYPE.STRUCTURAL, "CSIP4",
    "Used to declare the Content Information Type Specification used when creating the package. Legal values are defined in a fixed vocabulary. The attribute is mandatory for representation level METS documents. The vocabulary will evolve under the care of the DILCIS Board as additional Content Information Type Specifications are developed.");
  public static final ValidationEntry CSIP5 = new ValidationEntry(TYPE.STRUCTURAL, "CSIP5",
    "When the mets/@csip:CONTENTINFORMATIONTYPE has the value “OTHER” the mets/@csip:OTHERCONTENTINFORMATIONTYPE must state the content information type.");
  public static final ValidationEntry CSIP6 = new ValidationEntry(TYPE.STRUCTURAL, "CSIP6",
    "The URL of the METS profile that the information package conforms with.");
  public static final ValidationEntry CSIP117 = new ValidationEntry(TYPE.STRUCTURAL, "CSIP117",
    "General element for describing the package.");
  public static final ValidationEntry CSIP7 = new ValidationEntry(TYPE.STRUCTURAL, "CSIP7",
    "mets/metsHdr/@CREATEDATE records the date the package was created.");
  public static final ValidationEntry CSIP8 = new ValidationEntry(TYPE.STRUCTURAL, "CSIP8",
    "mets/metsHdr/@LASTMODDATE is mandatory when the package has been modified.");
  public static final ValidationEntry CSIP9 = new ValidationEntry(TYPE.STRUCTURAL, "CSIP9",
    "mets/metsHdr/@csip:OAISPACKAGETYPE is an additional CSIP attribute that declares the type of the IP.");
  public static final ValidationEntry CSIP10 = new ValidationEntry(TYPE.STRUCTURAL, "CSIP10",
    "A mandatory agent element records the software used to create the package. Other uses of agents may be described in any local implementations that extend the profile.");
  public static final ValidationEntry CSIP11 = new ValidationEntry(TYPE.STRUCTURAL, "CSIP11",
    "The mandatory agent element MUST have a @ROLE attribute with the value “CREATOR”.");
  public static final ValidationEntry CSIP12 = new ValidationEntry(TYPE.STRUCTURAL, "CSIP12",
    "The mandatory agent element MUST have a @TYPE attribute with the value “OTHER”.");
  public static final ValidationEntry CSIP13 = new ValidationEntry(TYPE.STRUCTURAL, "CSIP13",
    "The mandatory agent element MUST have a @OTHERTYPE attribute with the value “SOFTWARE”.");
  public static final ValidationEntry CSIP14 = new ValidationEntry(TYPE.STRUCTURAL, "CSIP14",
    "The mandatory agent’s name element records the name of the software tool used to create the IP.");
  public static final ValidationEntry CSIP15 = new ValidationEntry(TYPE.STRUCTURAL, "CSIP15",
    "The mandatory agent’s note element records the version of the tool used to create the IP.");
  public static final ValidationEntry CSIP16 = new ValidationEntry(TYPE.STRUCTURAL, "CSIP16",
    "The mandatory agent element’s note child has a @csip:NOTETYPE attribute with a fixed value of “SOFTWARE VERSION”.");
  public static final ValidationEntry CSIP17 = new ValidationEntry(TYPE.STRUCTURAL, "CSIP17",
    "Must be used if descriptive metadata for the package content is available. Each descriptive metadata section (<dmdSec>) contains a single description and must be repeated for multiple descriptions, when available. It is possible to transfer metadata in a package using just the descriptive metadata section and/or administrative metadata section.");
  public static final ValidationEntry CSIP18 = new ValidationEntry(TYPE.STRUCTURAL, "CSIP18",
    "An xml:id identifier for the descriptive metadata section (<dmdSec>) used for internal package references. It must be unique within the package.");
  public static final ValidationEntry CSIP19 = new ValidationEntry(TYPE.STRUCTURAL, "CSIP19",
    "Creation date of the descriptive metadata in this section.");
  public static final ValidationEntry CSIP20 = new ValidationEntry(TYPE.STRUCTURAL, "CSIP20",
    "Indicates the status of the package using a fixed vocabulary.");
  public static final ValidationEntry CSIP21 = new ValidationEntry(TYPE.STRUCTURAL, "CSIP21",
    "Reference to the descriptive metadata file located in the “metadata” section of the IP.");
  public static final ValidationEntry CSIP22 = new ValidationEntry(TYPE.STRUCTURAL, "CSIP22",
    "The locator type is always used with the value “URL” from the vocabulary in the attribute.");
  public static final ValidationEntry CSIP23 = new ValidationEntry(TYPE.STRUCTURAL, "CSIP23",
    "Attribute used with the value “simple”. Value list is maintained by the xlink standard.");
  public static final ValidationEntry CSIP24 = new ValidationEntry(TYPE.STRUCTURAL, "CSIP24",
    "The actual location of the resource. This specification recommends recording a URL type filepath in this attribute.");
  public static final ValidationEntry CSIP25 = new ValidationEntry(TYPE.STRUCTURAL, "CSIP25",
    "Specifies the type of metadata in the referenced file. Values are taken from the list provided by the METS.");
  public static final ValidationEntry CSIP26 = new ValidationEntry(TYPE.STRUCTURAL, "CSIP26",
    "The IANA mime type of the referenced file.");
  public static final ValidationEntry CSIP27 = new ValidationEntry(TYPE.STRUCTURAL, "CSIP27",
    "Size of the referenced file in bytes.");
  public static final ValidationEntry CSIP28 = new ValidationEntry(TYPE.STRUCTURAL, "CSIP28",
    "The creation date of the referenced file.");
  public static final ValidationEntry CSIP29 = new ValidationEntry(TYPE.STRUCTURAL, "CSIP29",
    "The checksum of the referenced file.");
  public static final ValidationEntry CSIP30 = new ValidationEntry(TYPE.STRUCTURAL, "CSIP30",
    "The type of checksum following the value list present in the METS-standard which has been used for calculating the checksum for the referenced file.");
  public static final ValidationEntry CSIP31 = new ValidationEntry(TYPE.STRUCTURAL, "CSIP31",
    "If administrative / preservation metadata is available, it must be described using the administrative metadata section (<amdSec>) element. All administrative metadata is present in a single <amdSec> element. It is possible to transfer metadata in a package using just the descriptive metadata section and/or administrative metadata section.");
  public static final ValidationEntry CSIP32 = new ValidationEntry(TYPE.STRUCTURAL, "CSIP32",
    "For recording information about preservation the standard PREMIS is used. It is mandatory to include one <digiprovMD> element for each piece of PREMIS metadata. The use if PREMIS in METS is following the recommendations in the 2017 version of PREMIS in METS Guidelines.");
  public static final ValidationEntry CSIP33 = new ValidationEntry(TYPE.STRUCTURAL, "CSIP33",
    "An xml:id identifier for the digital provenance metadata section mets/amdSec/digiprovMD used for internal package references. It must be unique within the package.");
  public static final ValidationEntry CSIP34 = new ValidationEntry(TYPE.STRUCTURAL, "CSIP34",
    "Indicates the status of the package using a fixed vocabulary.");
  public static final ValidationEntry CSIP35 = new ValidationEntry(TYPE.STRUCTURAL, "CSIP35",
    "Reference to the digital provenance metadata file stored in the “metadata” section of the IP.");
  public static final ValidationEntry CSIP36 = new ValidationEntry(TYPE.STRUCTURAL, "CSIP36",
    "The locator type is always used with the value “URL” from the vocabulary in the attribute.");
  public static final ValidationEntry CSIP37 = new ValidationEntry(TYPE.STRUCTURAL, "CSIP37",
    "Attribute used with the value “simple”. Value list is maintained by the xlink standard.");
  public static final ValidationEntry CSIP38 = new ValidationEntry(TYPE.STRUCTURAL, "CSIP38",
    "The actual location of the resource. This specification recommends recording a URL type filepath within this attribute.");
  public static final ValidationEntry CSIP39 = new ValidationEntry(TYPE.STRUCTURAL, "CSIP39",
    "Specifies the type of metadata in the referenced file. Values are taken from the list provided by the METS.");
  public static final ValidationEntry CSIP40 = new ValidationEntry(TYPE.STRUCTURAL, "CSIP40",
    "The IANA mime type for the referenced file.");
  public static final ValidationEntry CSIP41 = new ValidationEntry(TYPE.STRUCTURAL, "CSIP41",
    "Size of the referenced file in bytes.");
  public static final ValidationEntry CSIP42 = new ValidationEntry(TYPE.STRUCTURAL, "CSIP42",
    "Creation date of the referenced file.");
  public static final ValidationEntry CSIP43 = new ValidationEntry(TYPE.STRUCTURAL, "CSIP43",
    "The checksum of the referenced file.");
  public static final ValidationEntry CSIP44 = new ValidationEntry(TYPE.STRUCTURAL, "CSIP44",
    "The type of checksum following the value list present in the METS-standard which has been used for calculating the checksum for the referenced file.");
  // FIXME CSIP45-57 are related to rightsMD, which we don't support
  public static final ValidationEntry CSIP58 = new ValidationEntry(TYPE.STRUCTURAL, "CSIP58",
    "The transferred content is placed in the file section in different file group elements, described in other requirements. Only a single file section (<fileSec>) element should be present. It is possible to transfer just descriptive metadata and/or administrative metadata without files placed in this section.");
  public static final ValidationEntry CSIP59 = new ValidationEntry(TYPE.STRUCTURAL, "CSIP59",
    "An xml:id identifier for the file section used for internal package references. It must be unique within the package.");
  public static final ValidationEntry CSIP60 = new ValidationEntry(TYPE.STRUCTURAL, "CSIP60",
    "All documentation pertaining to the transferred content is placed in one or more file group elements with mets/fileSec/fileGrp/@USE attribute value “Documentation”.");
  public static final ValidationEntry CSIP113 = new ValidationEntry(TYPE.STRUCTURAL, "CSIP113",
    "All XML schemas used in the information package should be referenced from one or more file groups with mets/fileSec/fileGrp/@USE attribute value “Schemas”.");

  public static final ValidationEntry CSIP64 = new ValidationEntry(TYPE.STRUCTURAL, "CSIP64",
    "The value in the mets/fileSec/fileGrp/@USE is the name of the whole folder structure to the data, e.g “Documentation”, “Schemas”, “Representations/preingest” or “Representations/submission/data”.");
  public static final ValidationEntry CSIP65 = new ValidationEntry(TYPE.STRUCTURAL, "CSIP65",
    "An xml:id identifier for the file group used for internal package references. It must be unique within the package.");

  /** Private empty constructor */
  private ValidationConstants() {
    // do nothing
  }

  public static String getMetadataFileNotFoundString(String metadataType) {
    String notFoundString = METADATA_FILE_NOT_FOUND;
    if (IPConstants.DESCRIPTIVE.equalsIgnoreCase(metadataType)) {
      notFoundString = DESCRIPTIVE_METADATA_FILE_NOT_FOUND;
    } else if (IPConstants.PRESERVATION.equalsIgnoreCase(metadataType)) {
      notFoundString = PRESERVATION_METADATA_FILE_NOT_FOUND;
    } else if (IPConstants.OTHER.equalsIgnoreCase(metadataType)) {
      notFoundString = OTHER_METADATA_FILE_NOT_FOUND;
    }
    return notFoundString;
  }

  public static String getMetadataFileFoundWithMatchingChecksumString(String metadataType) {
    String notFoundString = METADATA_FILE_FOUND;
    if (IPConstants.DESCRIPTIVE.equalsIgnoreCase(metadataType)) {
      notFoundString = DESCRIPTIVE_METADATA_FILE_FOUND_WITH_MATCHING_CHECKSUMS;
    } else if (IPConstants.PRESERVATION.equalsIgnoreCase(metadataType)) {
      notFoundString = PRESERVATION_METADATA_FILE_FOUND_WITH_MATCHING_CHECKSUMS;
    } else if (IPConstants.OTHER.equalsIgnoreCase(metadataType)) {
      notFoundString = OTHER_METADATA_FILE_FOUND_WITH_MATCHING_CHECKSUMS;
    }
    return notFoundString;
  }

  public static String getMetadataFileFptrNotFoundString(String metadataType) {
    String noFptrString = METADATA_FPTR_NOT_FOUND;
    if (IPConstants.DESCRIPTIVE.equalsIgnoreCase(metadataType)) {
      noFptrString = DESCRIPTIVE_METADATA_FPTR_NOT_FOUND;
    } else if (IPConstants.PRESERVATION.equalsIgnoreCase(metadataType)) {
      noFptrString = PRESERVATION_METADATA_FPTR_NOT_FOUND;
    } else if (IPConstants.OTHER.equalsIgnoreCase(metadataType)) {
      noFptrString = OTHER_METADATA_FPTR_NOT_FOUND;
    }
    return noFptrString;
  }

}
