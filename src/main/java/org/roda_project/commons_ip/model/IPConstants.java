/**
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE file at the root of the source
 * tree and available online at
 *
 * https://github.com/keeps/commons-ip
 */
package org.roda_project.commons_ip.model;

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
  public static final String METS_TYPE_PHYSICAL = "physical";
  public static final String METS_PATH_SEPARATOR = "/";
  public static final String METS_REPRESENTATION_TYPE_PART_1 = "representation";

  public static final String METS_ORIGINAL = "original";
  public static final String METS_TYPE_AGGREGATION = "aggregation";
  public static final String METS_TYPE_DOCUMENTATION = "documentation";
  public static final String METS_TYPE_RECORDGRP = "recordgrp";
  public static final String METS_LABEL_DOKU = "Doku";

  // IP structure related
  public static final String METADATA = "metadata";
  public static final String METADATA_FOLDER = METADATA + METS_PATH_SEPARATOR;
  public static final String DESCRIPTIVE = "descriptive";
  public static final String DESCRIPTIVE_FOLDER = METADATA_FOLDER + DESCRIPTIVE + METS_PATH_SEPARATOR;
  public static final String PRESERVATION = "preservation";
  public static final String PRESERVATION_FOLDER = METADATA_FOLDER + PRESERVATION + METS_PATH_SEPARATOR;
  public static final String OTHER = "other";
  public static final String OTHER_FOLDER = METADATA_FOLDER + OTHER + METS_PATH_SEPARATOR;
  public static final String REPRESENTATIONS = "representations";
  public static final String REPRESENTATIONS_FOLDER = REPRESENTATIONS + METS_PATH_SEPARATOR;
  public static final String DATA = "data";
  public static final String DATA_FOLDER = DATA + METS_PATH_SEPARATOR;
  public static final String SCHEMAS = "schemas";
  public static final String SCHEMAS_FOLDER = SCHEMAS + METS_PATH_SEPARATOR;
  public static final String DOCUMENTATION = "documentation";
  public static final String DOCUMENTATION_FOLDER = DOCUMENTATION + METS_PATH_SEPARATOR;
  public static final String SUBMISSION = "submission";
  public static final String SUBMISSION_FOLDER = SUBMISSION + METS_PATH_SEPARATOR;

  public static final String CONTENT_FOLDER = "content";
  public static final String HEADER_FOLDER = "header";
  public static final String METADATA_FILE = "metadata.xml";
  public static final String HUNGARIAN_METADATA_FILE = HEADER_FOLDER + "/" + METADATA_FILE;

  // misc
  public static final String ZIP_PATH_SEPARATOR = "/";
  public static final String CHECKSUM_MD5_ALGORITHM = "MD5";
  public static final String CHECKSUM_SHA_1_ALGORITHM = "SHA1";
  public static final String CHECKSUM_SHA_256_ALGORITHM = "SHA-256";
  public static final String CHECKSUM_ALGORITHM = CHECKSUM_SHA_256_ALGORITHM;

  // E-ARK
  public static final String E_ARK_STRUCTURAL_MAP = "E-ARK structural map";
  public static final String E_ARK_FILES_ROOT = "E-ARK files root";
  public static final String E_ARK_FILES_REPRESENTATION = "E-ARK files representation ";

  // RODA
  public static final String RODA_STRUCTURAL_MAP = "RODA structural map";
  public static final String RODA_DIV_LABEL = "RODA";
  public static final String RODA_ANCESTORS_DIV_LABEL = "Ancestors";

  /** Private empty constructor */
  private IPConstants() {

  }

}
