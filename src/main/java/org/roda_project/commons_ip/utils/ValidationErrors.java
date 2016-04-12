/**
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE file at the root of the source
 * tree and available online at
 *
 * https://github.com/keeps/commons-ip
 */
package org.roda_project.commons_ip.utils;

import org.roda_project.commons_ip.model.IPConstants;

public final class ValidationErrors {

  // public static final String NO_DATA_FOLDER = "No 'data' folder in SIP";
  // public static final String NO_METADATA_FOLDER = "No 'metadata' folder in
  // SIP";
  // public static final String UNKNOWN_METADATA_FOLDER = "Unknown metadata
  // folder in SIP";
  // public static final String FILE_NOT_VALID = "File is not valid";
  // public static final String BAD_CHECKSUM = "Checksum in METS.xml doesn't
  // match real checksum";
  // public static final String ERROR_COMPUTING_CHECKSUM = "Error computing
  // checksum";
  // public static final String ERROR_COMPUTING_CHECKSUM_NO_SUCH_ALGORYTHM =
  // "Error computing checksum: the algorythm provided is not recognized";
  // public static final String UNKNOWN_METADATA_TYPE = "The metadata type is
  // not known";
  // public static final String XML_NOT_VALID = "The XML file is not valid
  // according to schema";
  // public static final String ERROR_VALIDATING_SIP = "An error occurred while
  // validating SIP";
  // public static final String NO_STRUCT_MAP = "No struct map in METS.xml";
  // public static final String NO_LOCAT_FOR_FILE = "File specified in filegroup
  // have no FLocat element";
  // public static final String UNABLE_TO_UNZIP_SIP = "The path provided is a
  // file, but this tool is unable to extract it";
  // public static final String NO_HREF_IN_MDREF = "There is no href attribute
  // associated to the mdref";
  // public static final String DMDSEC_WITHOUT_MDREF = "There is a dmdsec
  // without a mdref element";
  // public static final String NO_VALID_LOCAT = "No valid locat found";
  // public static final String BAD_HREF = "The href associated with the file is
  // not in the format file://.XXXX";
  // public static final String NO_MDREF_IN_DMDSEC = "There is no mdref in
  // dmdsec";
  //
  // public static final String FILE_IN_METS_DOES_NOT_EXIST = "File in METS.xml
  // doesn't exist";
  // public static final String NO_E_ARK_STRUCT_MAP = "No E-ARK structural map
  // in METS.xml";
  // public static final String REPRESENTATION_NO_E_ARK_STRUCT_MAP = "No E-ARK
  // structural map in representation METS.xml";
  // public static final String EMPTY_REPRESENTATIONS_DIV = "METS.xml has a
  // representations DIV but no Mptr was found";

  // Main mets related
  public static final String MAIN_METS_FILE_NOT_FOUND = "Main METS.xml file was not found in SIP";
  public static final String MAIN_METS_NOT_VALID = "Main METS.xml file is not valid";

  // Representation mets related
  public static final String REPRESENTATION_METS_FILE_NOT_FOUND = "Representation METS.xml file is referenced in the main METS.xml but was not fond";
  public static final String REPRESENTATION_METS_NOT_VALID = "Representation METS.xml file is not valid";

  // Mets structmap related
  public static final String MAIN_METS_HAS_NO_E_ARK_STRUCT_MAP = "Main METS.xml file has no E-ARK structural map";
  public static final String REPRESENTATION_METS_HAS_NO_E_ARK_STRUCT_MAP = "Main METS.xml file has no E-ARK structural map";

  // Mets metadata related
  public static final String METADATA_FILE_NOT_FOUND = "Metadata file not found";
  public static final String DESCRIPTIVE_METADATA_FILE_NOT_FOUND = "Descriptive metadata file not found";
  public static final String PRESERVATION_METADATA_FILE_NOT_FOUND = "Preservation metadata file not found";
  public static final String OTHER_METADATA_FILE_NOT_FOUND = "Other metadata file not found";
  public static final String UNKNOWN_DESCRIPTIVE_METADATA_TYPE = "Descriptive metadata type is unknown";

  // Mets checksum related
  public static final String CHECKSUMS_DIFFER = "Checksum in METS.xml doesn't file checksum";
  public static final String ERROR_COMPUTING_CHECKSUM = "Error computing checksum";
  public static final String ERROR_COMPUTING_CHECKSUM_NO_SUCH_ALGORITHM = "Error computing checksum: the algorithm provided is not recognized";

  // Mets representation related
  public static final String REPRESENTATION_FILE_NOT_FOUND = "Representation file referenced in METS.xml not found";
  public static final String REPRESENTATION_FILE_HAS_NO_FLOCAT = "Representation file referenced in METS.xml has no FLocat element";

  /** Private empty constructor */
  private ValidationErrors() {

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

}
