package org.roda_project.commons_ip2.validator.constants;

/** {@author João Gomes <jgomes@keep.pt>}. */
public final class ConstantsAIPspec {
  public static final String VALIDATION_REPORT_SPECIFICATION_AIP9_ID = "AIP9";
  public static final String VALIDATION_REPORT_SPECIFICATION_AIP9_NAME = "File ID";
  public static final String VALIDATION_REPORT_SPECIFICATION_AIP9_LOCATION = "file/@ID";
  public static final String VALIDATION_REPORT_SPECIFICATION_AIP9_DESCRIPTION =
      "Identifier of a file which is part of the AIP File identifier; "
          + "must be unique and start with the prefix ID";
  public static final String VALIDATION_REPORT_SPECIFICATION_AIP9_CARDINALITY = "0..1";
  public static final String VALIDATION_REPORT_SPECIFICATION_AIP9_LEVEL = "MUST";
  public static final String VALIDATION_REPORT_SPECIFICATION_AIP10_ID = "AIP10";
  public static final String VALIDATION_REPORT_SPECIFICATION_AIP10_NAME = "File ADMID";
  public static final String VALIDATION_REPORT_SPECIFICATION_AIP10_LOCATION = "file/@ADMID";
  public static final String VALIDATION_REPORT_SPECIFICATION_AIP10_DESCRIPTION =
      "Used to link it to relevant administrative metadata sections that "
          + "relate to the digital object described. "
          + "Can be a white space separated list of identifiers.";
  public static final String VALIDATION_REPORT_SPECIFICATION_AIP10_CARDINALITY = "0..n";
  public static final String VALIDATION_REPORT_SPECIFICATION_AIP10_LEVEL = "MAY";
  public static final String VALIDATION_REPORT_SPECIFICATION_AIP11_ID = "AIP11";
  public static final String VALIDATION_REPORT_SPECIFICATION_AIP11_NAME = "File CHECKSUMTYPE";
  public static final String VALIDATION_REPORT_SPECIFICATION_AIP11_LOCATION = "file/@CHECKSUMTYPE";
  public static final String VALIDATION_REPORT_SPECIFICATION_AIP11_DESCRIPTION =
      "Hash-sum calculator algorithm";
  public static final String VALIDATION_REPORT_SPECIFICATION_AIP11_CARDINALITY = "0..1";
  public static final String VALIDATION_REPORT_SPECIFICATION_AIP11_LEVEL = "MUST";
  public static final String VALIDATION_REPORT_SPECIFICATION_AIP12_ID = "AIP12";
  public static final String VALIDATION_REPORT_SPECIFICATION_AIP12_NAME = "File CHECKSUM";
  public static final String VALIDATION_REPORT_SPECIFICATION_AIP12_LOCATION = "file/@CHECKSUM";
  public static final String VALIDATION_REPORT_SPECIFICATION_AIP12_DESCRIPTION = "Hash-sum";
  public static final String VALIDATION_REPORT_SPECIFICATION_AIP12_CARDINALITY = "0..1";
  public static final String VALIDATION_REPORT_SPECIFICATION_AIP12_LEVEL = "MUST";
  public static final String VALIDATION_REPORT_SPECIFICATION_AIP13_ID = "AIP13";
  public static final String VALIDATION_REPORT_SPECIFICATION_AIP13_NAME = "File CREATED";
  public static final String VALIDATION_REPORT_SPECIFICATION_AIP13_LOCATION = "file/@CREATED";
  public static final String VALIDATION_REPORT_SPECIFICATION_AIP13_DESCRIPTION =
      "Date when the file entry was created.";
  public static final String VALIDATION_REPORT_SPECIFICATION_AIP13_CARDINALITY = "0..1";
  public static final String VALIDATION_REPORT_SPECIFICATION_AIP13_LEVEL = "MUST";
  public static final String VALIDATION_REPORT_SPECIFICATION_AIP14_ID = "AIP14";
  public static final String VALIDATION_REPORT_SPECIFICATION_AIP14_NAME = "File SIZE";
  public static final String VALIDATION_REPORT_SPECIFICATION_AIP14_LOCATION = "file/@SIZE";
  public static final String VALIDATION_REPORT_SPECIFICATION_AIP14_DESCRIPTION =
      "Size of the file in bytes.";
  public static final String VALIDATION_REPORT_SPECIFICATION_AIP14_CARDINALITY = "0..1";
  public static final String VALIDATION_REPORT_SPECIFICATION_AIP14_LEVEL = "MUST";
  public static final String VALIDATION_REPORT_SPECIFICATION_AIP15_ID = "AIP15";
  public static final String VALIDATION_REPORT_SPECIFICATION_AIP15_NAME = "File MIMETYPE";
  public static final String VALIDATION_REPORT_SPECIFICATION_AIP15_LOCATION = "file/@MIMETYPE";
  public static final String VALIDATION_REPORT_SPECIFICATION_AIP15_DESCRIPTION = "Mime-type.";
  public static final String VALIDATION_REPORT_SPECIFICATION_AIP15_CARDINALITY = "0..1";
  public static final String VALIDATION_REPORT_SPECIFICATION_AIP15_LEVEL = "MUST";

  private ConstantsAIPspec() {}

  /**
   * Get the name of the requirement of given id.
   *
   * @param id {@link String} id of the requirement
   * @return the {@link String} name of the requirement.
   */
  public static String getSpecificationName(String id) {
    if (id.equals("AIP9")) {
      return VALIDATION_REPORT_SPECIFICATION_AIP9_NAME;
    }
    if (id.equals("AIP10")) {
      return VALIDATION_REPORT_SPECIFICATION_AIP10_NAME;
    }
    if (id.equals("AIP11")) {
      return VALIDATION_REPORT_SPECIFICATION_AIP11_NAME;
    }
    if (id.equals("AIP12")) {
      return VALIDATION_REPORT_SPECIFICATION_AIP12_NAME;
    }
    if (id.equals("AIP13")) {
      return VALIDATION_REPORT_SPECIFICATION_AIP13_NAME;
    }
    if (id.equals("AIP14")) {
      return VALIDATION_REPORT_SPECIFICATION_AIP14_NAME;
    }
    if (id.equals("AIP15")) {
      return VALIDATION_REPORT_SPECIFICATION_AIP15_NAME;
    }
    return "Not Defined";
  }

  /**
   * Get the location of the requirement of given id.
   *
   * @param id {@link String} id of the requirement
   * @return the {@link String} location of the requirement.
   */
  public static String getSpecificationLocation(String id) {
    if (id.equals("AIP9")) {
      return VALIDATION_REPORT_SPECIFICATION_AIP9_LOCATION;
    }
    if (id.equals("AIP10")) {
      return VALIDATION_REPORT_SPECIFICATION_AIP10_LOCATION;
    }
    if (id.equals("AIP11")) {
      return VALIDATION_REPORT_SPECIFICATION_AIP11_LOCATION;
    }
    if (id.equals("AIP12")) {
      return VALIDATION_REPORT_SPECIFICATION_AIP12_LOCATION;
    }
    if (id.equals("AIP13")) {
      return VALIDATION_REPORT_SPECIFICATION_AIP13_LOCATION;
    }
    if (id.equals("AIP14")) {
      return VALIDATION_REPORT_SPECIFICATION_AIP14_LOCATION;
    }
    if (id.equals("AIP15")) {
      return VALIDATION_REPORT_SPECIFICATION_AIP15_LOCATION;
    }
    return "Not Defined";
  }

  /**
   * Get the description of the requirement of given id.
   *
   * @param id {@link String} id of the requirement
   * @return the {@link String} description of the requirement.
   */
  public static String getSpecificationDescription(String id) {
    if (id.equals("AIP9")) {
      return VALIDATION_REPORT_SPECIFICATION_AIP9_DESCRIPTION;
    }
    if (id.equals("AIP10")) {
      return VALIDATION_REPORT_SPECIFICATION_AIP10_DESCRIPTION;
    }
    if (id.equals("AIP11")) {
      return VALIDATION_REPORT_SPECIFICATION_AIP11_DESCRIPTION;
    }
    if (id.equals("AIP12")) {
      return VALIDATION_REPORT_SPECIFICATION_AIP12_DESCRIPTION;
    }
    if (id.equals("AIP13")) {
      return VALIDATION_REPORT_SPECIFICATION_AIP13_DESCRIPTION;
    }
    if (id.equals("AIP14")) {
      return VALIDATION_REPORT_SPECIFICATION_AIP14_DESCRIPTION;
    }
    if (id.equals("AIP15")) {
      return VALIDATION_REPORT_SPECIFICATION_AIP15_DESCRIPTION;
    }
    return "Not Defined";
  }

  /**
   * Get the cardinality of the requirement of given id.
   *
   * @param id {@link String} id of the requirement
   * @return the {@link String} cardinality of the requirement.
   */
  public static String getSpecificationCardinality(String id) {
    if (id.equals("AIP9")) {
      return VALIDATION_REPORT_SPECIFICATION_AIP9_CARDINALITY;
    }
    if (id.equals("AIP10")) {
      return VALIDATION_REPORT_SPECIFICATION_AIP10_CARDINALITY;
    }
    if (id.equals("AIP11")) {
      return VALIDATION_REPORT_SPECIFICATION_AIP11_CARDINALITY;
    }
    if (id.equals("AIP12")) {
      return VALIDATION_REPORT_SPECIFICATION_AIP12_CARDINALITY;
    }
    if (id.equals("AIP13")) {
      return VALIDATION_REPORT_SPECIFICATION_AIP13_CARDINALITY;
    }
    if (id.equals("AIP14")) {
      return VALIDATION_REPORT_SPECIFICATION_AIP14_CARDINALITY;
    }
    if (id.equals("AIP15")) {
      return VALIDATION_REPORT_SPECIFICATION_AIP15_CARDINALITY;
    }
    return "Not Defined";
  }

  /**
   * Get the level of the requirement of given id.
   *
   * @param id {@link String} id of the requirement
   * @return the {@link String} level of the requirement.
   */
  public static String getSpecificationLevel(String id) {
    if (id.equals("AIP9")) {
      return VALIDATION_REPORT_SPECIFICATION_AIP9_LEVEL;
    }
    if (id.equals("AIP10")) {
      return VALIDATION_REPORT_SPECIFICATION_AIP10_LEVEL;
    }
    if (id.equals("AIP11")) {
      return VALIDATION_REPORT_SPECIFICATION_AIP11_LEVEL;
    }
    if (id.equals("AIP12")) {
      return VALIDATION_REPORT_SPECIFICATION_AIP12_LEVEL;
    }
    if (id.equals("AIP13")) {
      return VALIDATION_REPORT_SPECIFICATION_AIP13_LEVEL;
    }
    if (id.equals("AIP14")) {
      return VALIDATION_REPORT_SPECIFICATION_AIP14_LEVEL;
    }
    if (id.equals("AIP15")) {
      return VALIDATION_REPORT_SPECIFICATION_AIP15_LEVEL;
    }
    return "Not Defined";
  }
}
