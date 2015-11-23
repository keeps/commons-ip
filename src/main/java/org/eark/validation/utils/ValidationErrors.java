package org.eark.validation.utils;

public final class ValidationErrors {
  public static final String NO_DATA_FOLDER = "No 'data' folder in SIP";
  public static final String NO_METADATA_FOLDER = "No 'metadata' folder in SIP";
  public static final String UNKNOWN_METADATA_FOLDER = "Unknown metadata folder in SIP";
  public static final String NO_MAIN_METS_FILE = "No main METS.xml file in SIP";
  public static final String MAIN_METS_NOT_VALID = "Main METS.xml file is not valid";
  public static final String FILE_NOT_VALID = "File is not valid";
  public static final String FILE_IN_METS_DOES_NOT_EXIST = "File in METS.xml doesn't exist";
  public static final String BAD_CHECKSUM = "Checksum in METS.xml doesn't match real checksum";
  public static final String ERROR_COMPUTING_CHECKSUM = "Error computing checksum";
  public static final String ERROR_COMPUTING_CHECKSUM_NO_SUCH_ALGORYTHM = "Error computing checksum: the algorythm provided is not recognized";
  public static final String UNKNOWN_METADATA_TYPE = "The metadata type is not known";
  public static final String XML_NOT_VALID = "The XML file is not valid according to schema";
  public static final String ERROR_VALIDATING_SIP = "An error occurred while validating SIP";
  public static final String NO_STRUCT_MAP = "No struct map in METS.xml";
  public static final String NO_LOCAT_FOR_FILE = "File specified in filegroup have no FLocat element";
  public static final String REPRESENTATION_METS_NOT_VALID = "The METS of the representation is not valid";
  public static final String UNABLE_TO_UNZIP_SIP = "The path provided is a file, but this tool is unable to extract it";

}
