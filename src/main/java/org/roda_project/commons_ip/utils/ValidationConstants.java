/**
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE file at the root of the source
 * tree and available online at
 *
 * https://github.com/keeps/commons-ip
 */
package org.roda_project.commons_ip.utils;

import org.roda_project.commons_ip.model.IPConstants;

public final class ValidationConstants {
  // Mets related
  public static final String METS_FILE_NOT_FOUND = "METS file was not found in SIP.";
  public static final String METS_NOT_VALID = "METS file is not valid.";
  public static final String METS_IS_VALID = "METS file is valid.";
  public static final String METS_HAS_SEVERAL_STRUCT_MAPS = "METS file has several struct maps.";
  public static final String METS_HAS_ONLY_ONE_STRUCT_MAP = "METS file has one struct map.";

  // Main mets related
  public static final String MAIN_METS_FILE_FOUND = "Main METS.xml file was found.";
  public static final String MAIN_METS_FILE_NOT_FOUND = "Main METS.xml file was not found.";
  public static final String MAIN_METS_NOT_VALID = "Main METS.xml file is not valid.";
  public static final String MAIN_METS_IS_VALID = "Main METS.xml file is valid.";
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
