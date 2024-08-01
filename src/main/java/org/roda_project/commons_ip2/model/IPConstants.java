/**
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE file at the root of the source
 * tree and available online at
 *
 * https://github.com/keeps/commons-ip
 */
package org.roda_project.commons_ip2.model;

import java.util.Arrays;
import java.util.List;

public final class IPConstants {

  public static final String SYSTEM_LINE_SEP = System.lineSeparator();

  // METS related
  public static final String METS_FILE_NAME = "METS";
  public static final String METS_FILE_EXTENSION = ".xml";
  public static final String METS_FILE = METS_FILE_NAME + METS_FILE_EXTENSION;
  public static final List<String> METS_FILE_PREFIXES_TO_ACCEPT = Arrays.asList("file://./", "file:");
  public static final String METS_TYPE_SIMPLE = "simple";
  public static final String METS_TYPE_PHYSICAL = "PHYSICAL";
  public static final String METS_PATH_SEPARATOR = "/";
  public static final String METS_REPRESENTATION_TYPE_PART_1 = "representation";

  public static final String METS_ORIGINAL = "original";
  public static final String METS_STATUS_CURRENT = "current";
  public static final String METS_GROUP_ID = "1";
  public static final String METS_TYPE_AGGREGATION = "aggregation";
  public static final String METS_TYPE_DOCUMENTATION = "documentation";
  public static final String METS_TYPE_RECORDGRP = "recordgrp";
  public static final String METS_LABEL_DOKU = "Doku";
  public static final String METS_LEVEL_OTHER = "otherlevel";
  public static final String METS_EAD_TYPE = "EAD";
  public static final String METS_EAD_VERSION = "2002";

  public static final String FOLDER_TEMPLATE_ID_FIELD = "id";
  public static final String FOLDER_TEMPLATE_FOLDER_FIELD = "folder";

  // IP structure related
  public static final String METADATA_WITH_FIRST_LETTER_CAPITAL = "Metadata";
  public static final String METADATA = "metadata";
  public static final String METADATA_FOLDER = METADATA + METS_PATH_SEPARATOR;
  public static final String DESCRIPTIVE = "descriptive";
  public static final String DESCRIPTIVE_FOLDER = METADATA_FOLDER + DESCRIPTIVE + METS_PATH_SEPARATOR;
  public static final String PRESERVATION = "preservation";
  public static final String PRESERVATION_FOLDER = METADATA_FOLDER + PRESERVATION + METS_PATH_SEPARATOR;
  public static final String OTHER_WITH_FIRST_LETTER_CAPITAL = "Other";
  public static final String OTHER = "other";
  public static final String OTHER_FOLDER = METADATA_FOLDER + OTHER + METS_PATH_SEPARATOR;
  public static final String REPRESENTATIONS_WITH_FIRST_LETTER_CAPITAL = "Representations";
  public static final String REPRESENTATIONS = "representations";
  public static final String REPRESENTATIONS_FOLDER = REPRESENTATIONS + METS_PATH_SEPARATOR;
  public static final String DATA_WITH_FIRST_LETTER_CAPITAL = "Data";
  public static final String DATA = "data";
  public static final String DATA_FOLDER = DATA + METS_PATH_SEPARATOR;
  public static final String SCHEMAS_WITH_FIRST_LETTER_CAPITAL = "Schemas";
  public static final String SCHEMAS = "schemas";
  public static final String SCHEMAS_FOLDER = SCHEMAS + METS_PATH_SEPARATOR;
  public static final String DOCUMENTATION_WITH_FIRST_LETTER_CAPITAL = "Documentation";
  public static final String DOCUMENTATION = "documentation";
  public static final String DOCUMENTATION_FOLDER = DOCUMENTATION + METS_PATH_SEPARATOR;
  public static final String SUBMISSION = "submission";
  public static final String SUBMISSION_FOLDER = SUBMISSION + METS_PATH_SEPARATOR;

  // misc
  public static final String ZIP_PATH_SEPARATOR = "/";
  public static final String CHECKSUM_MD5_ALGORITHM = "MD5";
  public static final String CHECKSUM_SHA_1_ALGORITHM = "SHA1";
  public static final String CHECKSUM_SHA_256_ALGORITHM = "SHA-256";
  public static final String CHECKSUM_ALGORITHM = CHECKSUM_SHA_256_ALGORITHM;

  // Common Specification (& E-ARK)
  public static final String COMMON_SPEC_STRUCTURAL_MAP = "CSIP";

  // SIP Specification
  public static final String SIP_SPEC_PROFILE = "https://earksip.dilcis.eu/profile/E-ARK-SIP.xml";

  // AIP Specification
  public static final String AIP_SPEC_PROFILE = "https://earkaip.dilcis.eu/profile/E-ARK-AIP.xml";

  // RODA
  public static final String RODA_STRUCTURAL_MAP = "RODA structural map";
  public static final String RODA_DIV_LABEL = "RODA";
  public static final String RODA_ANCESTORS_DIV_LABEL = "Ancestors";

  // Bagit
  public static final String BAGIT_PARENT = "parent";
  public static final String BAGIT_ID = "id";
  public static final String BAGIT_TITLE = "title";
  public static final String BAGIT_LEVEL = "level";
  public static final String BAGIT_ITEM_LEVEL = "item";
  public static final String BAGIT_DATA_FOLDER = "data";
  public static final String BAGIT_NAME = "name";
  public static final String BAGIT_FIELD = "field";
  public static final String BAGIT_METADATA = "metadata";
  public static final String BAGIT_VENDOR = "vendor";
  public static final String BAGIT_VENDOR_COMMONS_IP = "commons-ip";

  // Hungarian
  public static final String CONTENT_FOLDER = "content";
  public static final String HEADER_FOLDER = "header";
  public static final String METADATA_FILE = "metadata.xml";
  public static final String HUNGARIAN_METADATA_FILE = HEADER_FOLDER + "/" + METADATA_FILE;
  public static final String HUNGARIAN_DOCUMENTATION_TAG = "documentation";

  // FIXME 20190625 hsilva: this "fix" might introduce strange behaviors in
  // multi-threaded/multi-package type processing
  public static boolean METS_ENCODE_AND_DECODE_HREF = true;

  // XML SChemas
  public static final String SCHEMA_METS_FILENAME_WITH_VERSION = "mets1_12.xsd";
  public static final String SCHEMA_METS_RELATIVE_PATH_FROM_RESOURCES = METS_PATH_SEPARATOR + SCHEMAS + "2"
    + METS_PATH_SEPARATOR + SCHEMA_METS_FILENAME_WITH_VERSION;
  public static final String SCHEMA_XLINK_FILENAME = "xlink.xsd";
  public static final String SCHEMA_XLINK_RELATIVE_PATH_FROM_RESOURCES = METS_PATH_SEPARATOR + SCHEMAS + "2"
    + METS_PATH_SEPARATOR + SCHEMA_XLINK_FILENAME;
  public static final String SCHEMA_XLINK_URL = "http://www.loc.gov/standards/xlink/xlink.xsd";
  public static final String SCHEMA_EARK_CSIP_FILENAME = "DILCISExtensionMETS.xsd";
  public static final String SCHEMA_EARK_CSIP_RELATIVE_PATH_FROM_RESOURCES = METS_PATH_SEPARATOR + SCHEMAS + "2"
    + METS_PATH_SEPARATOR + SCHEMA_EARK_CSIP_FILENAME;
  public static final String SCHEMA_EARK_SIP_FILENAME = "DILCISExtensionSIPMETS.xsd";
  public static final String SCHEMA_EARK_SIP_RELATIVE_PATH_FROM_RESOURCES = METS_PATH_SEPARATOR + SCHEMAS + "2"
    + METS_PATH_SEPARATOR + SCHEMA_EARK_SIP_FILENAME;

  public static final String SIP_FILE_EXTENSION = ".zip";

  /** Private empty constructor */
  private IPConstants() {
    // do nothing
  }

}
