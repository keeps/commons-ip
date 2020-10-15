/**
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE file at the root of the source
 * tree and available online at
 *
 * https://github.com/keeps/commons-ip
 */
package org.roda_project.commons_ip.utils;

import org.apache.commons.lang3.tuple.Pair;
import org.roda_project.commons_ip.model.IPConstants;

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

  public static final Pair<String, String> CSIPSTR1 = Pair.of("CSIPSTR1",
    "Any Information Package MUST be included within a single physical root folder (known as the “Information Package root folder”). For packages presented in an archive format, see CSIPSTR3, the archive MUST unpack to a single root folder.");
  public static final Pair<String, String> CSIPSTR2 = Pair.of("CSIPSTR2",
    "The Information Package root folder SHOULD be named with the ID or name of the Information Package, that is the value of the package METS.xml’s root <mets> element’s @OBJID attribute.");
  public static final Pair<String, String> CSIPSTR3 = Pair.of("CSIPSTR3",
    "The Information Package root folder MAY be compressed (for example by using TAR or ZIP). Which specific compression format to use needs to be stated in the Submission Agreement.");
  public static final Pair<String, String> CSIPSTR4 = Pair.of("CSIPSTR4",
    "The Information Package root folder MUST include a file named METS.xml. This file MUST contain metadata that identifies the package, provides a high-level package description, and describes its structure, including pointers to constituent representations.");
  public static final Pair<String, String> CSIPSTR5 = Pair.of("CSIPSTR5",
    "The Information Package root folder SHOULD include a folder named metadata, which SHOULD include metadata relevant to the whole package.");
  public static final Pair<String, String> CSIPSTR6 = Pair.of("CSIPSTR6",
    "If preservation metadata are available, they SHOULD be included in sub-folder preservation.");
  public static final Pair<String, String> CSIPSTR7 = Pair.of("CSIPSTR7",
    "If descriptive metadata are available, they SHOULD be included in sub-folder descriptive.");
  public static final Pair<String, String> CSIPSTR8 = Pair.of("CSIPSTR8",
    "If any other metadata are available, they MAY be included in separate sub-folders, for example an additional folder named other.");
  public static final Pair<String, String> CSIPSTR9 = Pair.of("CSIPSTR9",
    "The Information Package folder SHOULD include a folder named representations.");
  public static final Pair<String, String> CSIPSTR10 = Pair.of("CSIPSTR10",
    "The representations folder SHOULD include a sub-folder for each individual representation (i.e. the “representation folder”). Each representation folder should have a string name that is unique within the package scope. For example the name of the representation and/or its creation date might be good candidates as a representation sub-folder name.");
  public static final Pair<String, String> CSIPSTR11 = Pair.of("CSIPSTR11",
    "The representation folder SHOULD include a sub-folder named data which MAY include all data constituting the representation.");
  public static final Pair<String, String> CSIPSTR12 = Pair.of("CSIPSTR12",
    "The representation folder SHOULD include a metadata file named METS.xml which includes information about the identity and structure of the representation and its components. The recommended best practice is to always have a METS.xml in the representation folder.");
  public static final Pair<String, String> CSIPSTR13 = Pair.of("CSIPSTR13",
    "The representation folder SHOULD include a sub-folder named metadata which MAY include all metadata about the specific representation.");
  public static final Pair<String, String> CSIPSTR14 = Pair.of("CSIPSTR14",
    "The Information Package MAY be extended with additional sub-folders.");
  public static final Pair<String, String> CSIPSTR15 = Pair.of("CSIPSTR15",
    "We recommend including all XML schema documents for any structured metadata within package. These schema documents SHOULD be placed in a sub-folder called schemas within the Information Package root folder and/or the representation folder.");
  public static final Pair<String, String> CSIPSTR16 = Pair.of("CSIPSTR16",
    "We recommend including any supplementary documentation for the package or a specific representation within the package. Supplementary documentation SHOULD be placed in a sub-folder called documentation within the Information Package root folder and/or the representation folder.");

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
