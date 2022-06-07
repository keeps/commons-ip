package org.roda_project.commons_ip2.validator.constants;

/** {@author João Gomes <jgomes@keep.pt>}. */
public final class ConstantsSIPspec {
  private ConstantsSIPspec() {
  }

  /* Extended use of the METS root element (element mets) */

  /* SIP1 */
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP1_ID = "SIP1";
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP1_NAME = "Package name";
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP1_LOCATION = "mets/@LABEL";
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP1_DESCRIPTION = "An optional short text describing the contents of the package, "
    + "e.g. “Accounting records of 2017”.";
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP1_CARDINALITY = "0..1";
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP1_LEVEL = "MAY";

  /* SIP2 */
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP2_ID = "SIP2";
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP2_NAME = "METS Profile";
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP2_LOCATION = "mets/@PROFILE";
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP2_DESCRIPTION = "The value is set to “https://earksip.dilcis.eu/profile/E-ARK-SIP.xml”.";
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP2_CARDINALITY = "1..1";
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP2_LEVEL = "MUST";

  /* Extended use of the METS header (element metsHdr) */

  /* SIP3 */
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP3_ID = "SIP3";
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP3_NAME = "Package status";
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP3_LOCATION = "metsHdr/@RECORDSTATUS";
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP3_DESCRIPTION = "A way of indicating the status of the package and to instruct the OAIS on how "
    + "to properly handle the package. If not set, the expected behaviour is equal to " + "NEW.";
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP3_CARDINALITY = "0..1";
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP3_LEVEL = "MAY";

  /* SIP4 */
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP4_ID = "SIP4";
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP4_NAME = "OAIS Package type information";
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP4_LOCATION = "metsHdr/@csip:OAISPACKAGETYPE";
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP4_DESCRIPTION = "@csip:OAISPACKAGETYPE is used with the value “SIP”.";
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP4_CARDINALITY = "1..1";
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP4_LEVEL = "MUST";

  /* SIP5 */
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP5_ID = "SIP5";
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP5_NAME = "Submission agreement";
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP5_LOCATION = "metsHdr/altRecordID";
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP5_DESCRIPTION = "A reference to the Submission Agreement associated with the package. "
    + "@TYPE is used with the value “SUBMISSIONAGREEMENT”. Example: RA " + "13-2011/5329; 2012-04-12 Example: "
    + "http://submissionagreement.kb.se/dnr331-1144-2011/20120711/ Note: It is "
    + "recommended to use a machine-readable format for a better description of a "
    + "submission agreement. For example, the submission agreement developed " + "by Docuteam GmbH "
    + "http://www.loc.gov/standards/mets/profiles/00000041.xml";
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP5_CARDINALITY = "0..1";
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP5_LEVEL = "MAY";

  /* SIP6 */
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP6_ID = "SIP6";
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP6_NAME = "Previous Submission agreement";
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP6_LOCATION = "metsHdr/altRecordID";
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP6_DESCRIPTION = "An optional reference to a previous submission agreement(s) which the\n"
    + "information may have belonged to. @TYPE is used with the value "
    + "“PREVIOUSSUBMISSIONAGREEMENT”. Example: FM 12-2387/12726, " + "2007-09-19 Example: "
    + "http://submissionagreement.kb.se/dnr331-1144-2011/20120711/ Note: It is "
    + "recommended to use a machine-readable format for a better description of a "
    + "submission agreement. For example, the submission agreement developed " + "by Docuteam GmbH "
    + "http://www.loc.gov/standards/mets/profiles/00000041.xml";
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP6_CARDINALITY = "0..*";
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP6_LEVEL = "MAY";

  /* SIP7 */
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP7_ID = "SIP7";
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP7_NAME = "Archival reference code";
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP7_LOCATION = "metsHdr/altRecordID";
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP7_DESCRIPTION = "An optional reference code indicating where in the archival hierarchy the "
    + "package shall be placed in the OAIS. @TYPE is used with the value "
    + "“REFERENCECODE”. Example: FM 12-2387/12726, 2007-09-19";
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP7_CARDINALITY = "1..1";
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP7_LEVEL = "MAY";

  /* SIP8 */
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP8_ID = "SIP8";
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP8_NAME = " Previous archival reference code";
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP8_LOCATION = "metsHdr/altRecordID";
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP8_DESCRIPTION = "In cases where the SIP originates from other institutions maintaining a "
    + "reference code structure, this element can be used to record these reference "
    + "codes and therefore support the provenance of the package when a whole "
    + "archival description is not submitted with the submission. @TYPE is used "
    + "with the value “PREVIOUSREFERENCECODE”. Example: " + "SE/FM/123/123.1/123.1.3";
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP8_CARDINALITY = "0..*";
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP8_LEVEL = "MAY";

  /* SIP9 */
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP9_ID = "SIP9";
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP9_NAME = "Archival creator agent";
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP9_LOCATION = "metsHdr/agent";
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP9_DESCRIPTION = "A wrapper element that enables to encode the name of the organisation or "
    + "person that originally created the data being transferred. Please note that "
    + "this might be different from the organisation which has been charged with "
    + "preparing and sending the SIP to the archives.";
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP9_CARDINALITY = "0..1";
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP9_LEVEL = "MAY";

  /* SIP10 */
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP10_ID = "SIP10";
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP10_NAME = "Archival creator agent role";
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP10_LOCATION = "metsHdr/agent/@ROLE";
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP10_DESCRIPTION = "The role of the person(s) or institution(s) responsible for the document/collection.";
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP10_CARDINALITY = "1..1";
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP10_LEVEL = "MUST";

  /* SIP11 */
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP11_ID = "SIP11";
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP11_NAME = "Archival creator agent type";
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP11_LOCATION = "metsHdr/agent/@TYPE";
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP11_DESCRIPTION = "The type of the archival creator agent is “ORGANIZATION” or “INDIVIDUAL”";
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP11_CARDINALITY = "1..1";
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP11_LEVEL = "MUST";

  /* SIP12 */
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP12_ID = "SIP12";
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP12_NAME = "Archival creator agent name";
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP12_LOCATION = "metsHdr/agent/name";
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP12_DESCRIPTION = "The name of the organisation(s) that originally created the data being "
    + "transferred. Please note that this might be different from the organisation "
    + "which has been charged with preparing and sending the SIP to the archives.";
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP12_CARDINALITY = "0..*";
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP12_LEVEL = "MAY";

  /* SIP13 */
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP13_ID = "SIP13";
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP13_NAME = "Archival creator agent additional information";
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP13_LOCATION = "metsHdr/agent/note";
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP13_DESCRIPTION = "The archival creator agent has a note providing a "
    + "unique identification code for the archival creator.";
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP13_CARDINALITY = "0..1";
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP13_LEVEL = "MAY";

  /* SIP14 */
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP14_ID = "SIP14";
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP14_NAME = "Classification of the archival creator agent additional information";
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP14_LOCATION = "metsHdr/agent/note/@csip:NOTETYPE";
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP14_DESCRIPTION = "The archival creator agent note is typed with the value of “IDENTIFICATIONCODE”.";
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP14_CARDINALITY = "1..1";
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP14_LEVEL = "MUST";

  /* SIP15 */
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP15_ID = "SIP15";
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP15_NAME = "Submitting agent";
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP15_LOCATION = "metsHdr/agent";
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP15_DESCRIPTION = "The name of the organisation or person submitting the package to the archive";
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP15_CARDINALITY = "1..1";
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP15_LEVEL = "MUST";

  /* SIP16 */
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP16_ID = "SIP16";
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP16_NAME = "Submitting agent role";
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP16_LOCATION = "metsHdr/agent/@ROLE";
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP16_DESCRIPTION = "The role of the person(s) or institution(s) "
    + "responsible for creating and/or submitting the package";
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP16_CARDINALITY = "1..1";
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP16_LEVEL = "MUST";

  /* SIP17 */
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP17_ID = "SIP17";
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP17_NAME = "Submitting agent type";
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP17_LOCATION = "metsHdr/agent/@TYPE";
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP17_DESCRIPTION = "The type of the submitting agent is “ORGANIZATION” or “INDIVIDUAL”.";
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP17_CARDINALITY = "1..1";
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP17_LEVEL = "MUST";

  /* SIP18 */
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP18_ID = "SIP18";
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP18_NAME = "Submitting agent name";
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP18_LOCATION = "metsHdr/agent/name";
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP18_DESCRIPTION = "Name of the organisation submitting the package to the archive.";
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP18_CARDINALITY = "1..1";
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP18_LEVEL = "MAY";

  /* SIP19 */
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP19_ID = "SIP19";
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP19_NAME = "Submitting agent additional information";
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP19_LOCATION = "metsHdr/agent/note";
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP19_DESCRIPTION = "The submitting agent has a note providing a "
    + "unique identification code for the archival creator.";
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP19_CARDINALITY = "0..1";
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP19_LEVEL = "MAY";

  /* SIP20 */
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP20_ID = "SIP20";
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP20_NAME = "Classification of the submitting agent additional information";
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP20_LOCATION = "metsHdr/agent/note/@csip:NOTETYPE";
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP20_DESCRIPTION = "The submitting agent note is typed with the value of “IDENTIFICATIONCODE”.";
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP20_CARDINALITY = "1..1";
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP20_LEVEL = "MUST";

  /* SIP21 */
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP21_ID = "SIP21";
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP21_NAME = "Contact person agent";
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP21_LOCATION = "metsHdr/agent";
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP21_DESCRIPTION = "Contact person for the submission.";
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP21_CARDINALITY = "0..*";
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP21_LEVEL = "MAY";

  /* SIP22 */
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP22_ID = "SIP22";
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP22_NAME = "Contact person agent role";
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP22_LOCATION = "metsHdr/agent/@ROLE";
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP22_DESCRIPTION = "The role of the contact person is “CREATOR”";
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP22_CARDINALITY = "1..1";
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP22_LEVEL = "MUST";

  /* SIP23 */
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP23_ID = "SIP23";
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP23_NAME = "Contact person agent type";
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP23_LOCATION = "metsHdr/agent/@TYPE";
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP23_DESCRIPTION = "The type of the contact person agent is “INDIVIDUAL”";
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP23_CARDINALITY = "1..1";
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP23_LEVEL = "MUST";

  /* SIP24 */
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP24_ID = "SIP24";
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP24_NAME = "Contact person agent name";
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP24_LOCATION = "metsHdr/agent/name";
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP24_DESCRIPTION = "Name of the contact person";
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP24_CARDINALITY = "1..1";
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP24_LEVEL = "MUST";

  /* SIP25 */
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP25_ID = "SIP25";
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP25_NAME = "Contact person agent additional information";
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP25_LOCATION = "metsHdr/agent/note";
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP25_DESCRIPTION = "The submitting agent has one or more notes giving the contact information.";
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP25_CARDINALITY = "0..*";
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP25_LEVEL = "MAY";

  /* SIP26 */
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP26_ID = "SIP26";
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP26_NAME = "Preservation agent";
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP26_LOCATION = "metsHdr/agent";
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP26_DESCRIPTION = "The organisation or person that preserves the package";
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP26_CARDINALITY = "0..1";
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP26_LEVEL = "MAY";

  /* SIP27 */
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP27_ID = "SIP27";
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP27_NAME = "Preservation agent role";
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP27_LOCATION = "metsHdr/agent/@ROLE";
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP27_DESCRIPTION = "The role of the preservation agent is “PRESERVATION”.";
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP27_CARDINALITY = "1..1";
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP27_LEVEL = "MUST";

  /* SIP28 */
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP28_ID = "SIP28";
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP28_NAME = "Preservation agent type";
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP28_LOCATION = "metsHdr/agent/@TYPE";
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP28_DESCRIPTION = "The type of the submitting agent is “ORGANIZATION”";
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP28_CARDINALITY = "1..1";
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP28_LEVEL = "MUST";

  /* SIP29 */
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP29_ID = "SIP29";
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP29_NAME = "Preservation agent name";
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP29_LOCATION = "metsHdr/agent/name";
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP29_DESCRIPTION = "Name of the organisation preserving the package.";
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP29_CARDINALITY = "1..1";
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP29_LEVEL = "MAY";

  /* SIP30 */
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP30_ID = "SIP30";
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP30_NAME = "Preservation agent additional information";
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP30_LOCATION = "metsHdr/agent/note";
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP30_DESCRIPTION = "The preservation agent has a note providing a "
    + "unique identification code for the archival creator.";
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP30_CARDINALITY = "0..1";
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP30_LEVEL = "MAY";

  /* SIP31 */
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP31_ID = "SIP31";
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP31_NAME = "Classification of the preservation agent additional information";
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP31_LOCATION = "metsHdr/agent/note/@csip:NOTETYPE";
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP31_DESCRIPTION = "The preservation agent note is typed with the value of “IDENTIFICATIONCODE”.";
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP31_CARDINALITY = "1..1";
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP31_LEVEL = "MUST";

  /* Extended use of the METS file section (element fileSec) */

  /* SIP32 */
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP32_ID = "SIP32";
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP32_NAME = "File format name";
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP32_LOCATION = "fileSec/fileGrp/file/@sip:FILEFORMATNAME";
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP32_DESCRIPTION = "An optional attribute may be used if the MIMETYPE is not sufficient for the "
    + "purposes of processing the information package. Example: "
    + "“Extensible Markup Language” Example: “PDF/A” Example: “ISO/IEC 26300:2006”";
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP32_CARDINALITY = "0..1";
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP32_LEVEL = "MAY";

  /* SIP33 */
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP33_ID = "SIP33";
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP33_NAME = "File format version";
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP33_LOCATION = "fileSec/fileGrp/file/@sip:FILEFORMATVERSION";
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP33_DESCRIPTION = "The version of the file format when the use of PREMIS "
    + "has not been agreed upon in the submission agreement. Example: “1.0”";
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP33_CARDINALITY = "0..1";
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP33_LEVEL = "MAY";

  /* SIP34 */
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP34_ID = "SIP34";
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP34_NAME = "File format registry";
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP34_LOCATION = "fileSec/fileGrp/file/@sip:FILEFORMATREGISTRY";
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP34_DESCRIPTION = "The name of the format registry used to identify the file format when the use of "
    + "PREMIS has not been agreed upon in the submission agreement. Example:“PRONOM”";
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP34_CARDINALITY = "0..1";
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP34_LEVEL = "MAY";

  /* SIP35 */
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP35_ID = "SIP35";
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP35_NAME = "File format registry key";
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP35_LOCATION = "fileSec/fileGrp/file/@sip:FILEFORMATKEY";
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP35_DESCRIPTION = "Key of the file format in the registry when use of PREMIS "
    + "has not been agreed upon in the submission agreement. Example: “fmt/101”";
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP35_CARDINALITY = "0..1";
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP35_LEVEL = "MAY";

  /**
   * Get the name of the requirement of given id.
   *
   * @param id
   *          {@link String} id of the requirement
   * @return the {@link String} name of the requirement.
   */
  public static String getSpecificationName(String id) {
    if (id.equals("SIP1")) {
      return VALIDATION_REPORT_SPECIFICATION_SIP1_NAME;
    }
    if (id.equals("SIP2")) {
      return VALIDATION_REPORT_SPECIFICATION_SIP2_NAME;
    }
    if (id.equals("SIP3")) {
      return VALIDATION_REPORT_SPECIFICATION_SIP3_NAME;
    }
    if (id.equals("SIP4")) {
      return VALIDATION_REPORT_SPECIFICATION_SIP4_NAME;
    }
    if (id.equals("SIP5")) {
      return VALIDATION_REPORT_SPECIFICATION_SIP5_NAME;
    }
    if (id.equals("SIP6")) {
      return VALIDATION_REPORT_SPECIFICATION_SIP6_NAME;
    }
    if (id.equals("SIP7")) {
      return VALIDATION_REPORT_SPECIFICATION_SIP7_NAME;
    }
    if (id.equals("SIP8")) {
      return VALIDATION_REPORT_SPECIFICATION_SIP8_NAME;
    }
    if (id.equals("SIP9")) {
      return VALIDATION_REPORT_SPECIFICATION_SIP9_NAME;
    }
    if (id.equals("SIP10")) {
      return VALIDATION_REPORT_SPECIFICATION_SIP10_NAME;
    }
    if (id.equals("SIP11")) {
      return VALIDATION_REPORT_SPECIFICATION_SIP11_NAME;
    }
    if (id.equals("SIP12")) {
      return VALIDATION_REPORT_SPECIFICATION_SIP12_NAME;
    }
    if (id.equals("SIP13")) {
      return VALIDATION_REPORT_SPECIFICATION_SIP13_NAME;
    }
    if (id.equals("SIP14")) {
      return VALIDATION_REPORT_SPECIFICATION_SIP14_NAME;
    }
    if (id.equals("SIP15")) {
      return VALIDATION_REPORT_SPECIFICATION_SIP15_NAME;
    }
    if (id.equals("SIP16")) {
      return VALIDATION_REPORT_SPECIFICATION_SIP16_NAME;
    }
    if (id.equals("SIP17")) {
      return VALIDATION_REPORT_SPECIFICATION_SIP17_NAME;
    }
    if (id.equals("SIP18")) {
      return VALIDATION_REPORT_SPECIFICATION_SIP18_NAME;
    }
    if (id.equals("SIP19")) {
      return VALIDATION_REPORT_SPECIFICATION_SIP19_NAME;
    }
    if (id.equals("SIP20")) {
      return VALIDATION_REPORT_SPECIFICATION_SIP20_NAME;
    }
    if (id.equals("SIP21")) {
      return VALIDATION_REPORT_SPECIFICATION_SIP21_NAME;
    }
    if (id.equals("SIP22")) {
      return VALIDATION_REPORT_SPECIFICATION_SIP22_NAME;
    }
    if (id.equals("SIP23")) {
      return VALIDATION_REPORT_SPECIFICATION_SIP23_NAME;
    }
    if (id.equals("SIP24")) {
      return VALIDATION_REPORT_SPECIFICATION_SIP24_NAME;
    }
    if (id.equals("SIP25")) {
      return VALIDATION_REPORT_SPECIFICATION_SIP25_NAME;
    }
    if (id.equals("SIP26")) {
      return VALIDATION_REPORT_SPECIFICATION_SIP26_NAME;
    }
    if (id.equals("SIP27")) {
      return VALIDATION_REPORT_SPECIFICATION_SIP27_NAME;
    }
    if (id.equals("SIP28")) {
      return VALIDATION_REPORT_SPECIFICATION_SIP28_NAME;
    }
    if (id.equals("SIP29")) {
      return VALIDATION_REPORT_SPECIFICATION_SIP29_NAME;
    }
    if (id.equals("SIP30")) {
      return VALIDATION_REPORT_SPECIFICATION_SIP30_NAME;
    }
    if (id.equals("SIP31")) {
      return VALIDATION_REPORT_SPECIFICATION_SIP31_NAME;
    }
    if (id.equals("SIP32")) {
      return VALIDATION_REPORT_SPECIFICATION_SIP32_NAME;
    }
    if (id.equals("SIP33")) {
      return VALIDATION_REPORT_SPECIFICATION_SIP33_NAME;
    }
    if (id.equals("SIP34")) {
      return VALIDATION_REPORT_SPECIFICATION_SIP34_NAME;
    }
    if (id.equals("SIP35")) {
      return VALIDATION_REPORT_SPECIFICATION_SIP35_NAME;
    }
    return "Not Defined";
  }

  /**
   * Get the location of the requirement of given id.
   *
   * @param id
   *          {@link String} id of the requirement
   * @return the {@link String} location of the requirement.
   */
  public static String getSpecificationLocation(String id) {
    if (id.equals("SIP1")) {
      return VALIDATION_REPORT_SPECIFICATION_SIP1_LOCATION;
    }
    if (id.equals("SIP2")) {
      return VALIDATION_REPORT_SPECIFICATION_SIP2_LOCATION;
    }
    if (id.equals("SIP3")) {
      return VALIDATION_REPORT_SPECIFICATION_SIP3_LOCATION;
    }
    if (id.equals("SIP4")) {
      return VALIDATION_REPORT_SPECIFICATION_SIP4_LOCATION;
    }
    if (id.equals("SIP5")) {
      return VALIDATION_REPORT_SPECIFICATION_SIP5_LOCATION;
    }
    if (id.equals("SIP6")) {
      return VALIDATION_REPORT_SPECIFICATION_SIP6_LOCATION;
    }
    if (id.equals("SIP7")) {
      return VALIDATION_REPORT_SPECIFICATION_SIP7_LOCATION;
    }
    if (id.equals("SIP8")) {
      return VALIDATION_REPORT_SPECIFICATION_SIP8_LOCATION;
    }
    if (id.equals("SIP9")) {
      return VALIDATION_REPORT_SPECIFICATION_SIP9_LOCATION;
    }
    if (id.equals("SIP10")) {
      return VALIDATION_REPORT_SPECIFICATION_SIP10_LOCATION;
    }
    if (id.equals("SIP11")) {
      return VALIDATION_REPORT_SPECIFICATION_SIP11_LOCATION;
    }
    if (id.equals("SIP12")) {
      return VALIDATION_REPORT_SPECIFICATION_SIP12_LOCATION;
    }
    if (id.equals("SIP13")) {
      return VALIDATION_REPORT_SPECIFICATION_SIP13_LOCATION;
    }
    if (id.equals("SIP14")) {
      return VALIDATION_REPORT_SPECIFICATION_SIP14_LOCATION;
    }
    if (id.equals("SIP15")) {
      return VALIDATION_REPORT_SPECIFICATION_SIP15_LOCATION;
    }
    if (id.equals("SIP16")) {
      return VALIDATION_REPORT_SPECIFICATION_SIP16_LOCATION;
    }
    if (id.equals("SIP17")) {
      return VALIDATION_REPORT_SPECIFICATION_SIP17_LOCATION;
    }
    if (id.equals("SIP18")) {
      return VALIDATION_REPORT_SPECIFICATION_SIP18_LOCATION;
    }
    if (id.equals("SIP19")) {
      return VALIDATION_REPORT_SPECIFICATION_SIP19_LOCATION;
    }
    if (id.equals("SIP20")) {
      return VALIDATION_REPORT_SPECIFICATION_SIP20_LOCATION;
    }
    if (id.equals("SIP21")) {
      return VALIDATION_REPORT_SPECIFICATION_SIP21_LOCATION;
    }
    if (id.equals("SIP22")) {
      return VALIDATION_REPORT_SPECIFICATION_SIP22_LOCATION;
    }
    if (id.equals("SIP23")) {
      return VALIDATION_REPORT_SPECIFICATION_SIP23_LOCATION;
    }
    if (id.equals("SIP24")) {
      return VALIDATION_REPORT_SPECIFICATION_SIP24_LOCATION;
    }
    if (id.equals("SIP25")) {
      return VALIDATION_REPORT_SPECIFICATION_SIP25_LOCATION;
    }
    if (id.equals("SIP26")) {
      return VALIDATION_REPORT_SPECIFICATION_SIP26_LOCATION;
    }
    if (id.equals("SIP27")) {
      return VALIDATION_REPORT_SPECIFICATION_SIP27_LOCATION;
    }
    if (id.equals("SIP28")) {
      return VALIDATION_REPORT_SPECIFICATION_SIP28_LOCATION;
    }
    if (id.equals("SIP29")) {
      return VALIDATION_REPORT_SPECIFICATION_SIP29_LOCATION;
    }
    if (id.equals("SIP30")) {
      return VALIDATION_REPORT_SPECIFICATION_SIP30_LOCATION;
    }
    if (id.equals("SIP31")) {
      return VALIDATION_REPORT_SPECIFICATION_SIP31_LOCATION;
    }
    if (id.equals("SIP32")) {
      return VALIDATION_REPORT_SPECIFICATION_SIP32_LOCATION;
    }
    if (id.equals("SIP33")) {
      return VALIDATION_REPORT_SPECIFICATION_SIP33_LOCATION;
    }
    if (id.equals("SIP34")) {
      return VALIDATION_REPORT_SPECIFICATION_SIP34_LOCATION;
    }
    if (id.equals("SIP35")) {
      return VALIDATION_REPORT_SPECIFICATION_SIP35_LOCATION;
    }
    return "Not Defined";
  }

  /**
   * Get the description of the requirement of given id.
   *
   * @param id
   *          {@link String} id of the requirement
   * @return the {@link String} description of the requirement.
   */
  public static String getSpecificationDescription(String id) {
    if (id.equals("SIP1")) {
      return VALIDATION_REPORT_SPECIFICATION_SIP1_DESCRIPTION;
    }
    if (id.equals("SIP2")) {
      return VALIDATION_REPORT_SPECIFICATION_SIP2_DESCRIPTION;
    }
    if (id.equals("SIP3")) {
      return VALIDATION_REPORT_SPECIFICATION_SIP3_DESCRIPTION;
    }
    if (id.equals("SIP4")) {
      return VALIDATION_REPORT_SPECIFICATION_SIP4_DESCRIPTION;
    }
    if (id.equals("SIP5")) {
      return VALIDATION_REPORT_SPECIFICATION_SIP5_DESCRIPTION;
    }
    if (id.equals("SIP6")) {
      return VALIDATION_REPORT_SPECIFICATION_SIP6_DESCRIPTION;
    }
    if (id.equals("SIP7")) {
      return VALIDATION_REPORT_SPECIFICATION_SIP7_DESCRIPTION;
    }
    if (id.equals("SIP8")) {
      return VALIDATION_REPORT_SPECIFICATION_SIP8_DESCRIPTION;
    }
    if (id.equals("SIP9")) {
      return VALIDATION_REPORT_SPECIFICATION_SIP9_DESCRIPTION;
    }
    if (id.equals("SIP10")) {
      return VALIDATION_REPORT_SPECIFICATION_SIP10_DESCRIPTION;
    }
    if (id.equals("SIP11")) {
      return VALIDATION_REPORT_SPECIFICATION_SIP11_DESCRIPTION;
    }
    if (id.equals("SIP12")) {
      return VALIDATION_REPORT_SPECIFICATION_SIP12_DESCRIPTION;
    }
    if (id.equals("SIP13")) {
      return VALIDATION_REPORT_SPECIFICATION_SIP13_DESCRIPTION;
    }
    if (id.equals("SIP14")) {
      return VALIDATION_REPORT_SPECIFICATION_SIP14_DESCRIPTION;
    }
    if (id.equals("SIP15")) {
      return VALIDATION_REPORT_SPECIFICATION_SIP15_DESCRIPTION;
    }
    if (id.equals("SIP16")) {
      return VALIDATION_REPORT_SPECIFICATION_SIP16_DESCRIPTION;
    }
    if (id.equals("SIP17")) {
      return VALIDATION_REPORT_SPECIFICATION_SIP17_DESCRIPTION;
    }
    if (id.equals("SIP18")) {
      return VALIDATION_REPORT_SPECIFICATION_SIP18_DESCRIPTION;
    }
    if (id.equals("SIP19")) {
      return VALIDATION_REPORT_SPECIFICATION_SIP19_DESCRIPTION;
    }
    if (id.equals("SIP20")) {
      return VALIDATION_REPORT_SPECIFICATION_SIP20_DESCRIPTION;
    }
    if (id.equals("SIP21")) {
      return VALIDATION_REPORT_SPECIFICATION_SIP21_DESCRIPTION;
    }
    if (id.equals("SIP22")) {
      return VALIDATION_REPORT_SPECIFICATION_SIP22_DESCRIPTION;
    }
    if (id.equals("SIP23")) {
      return VALIDATION_REPORT_SPECIFICATION_SIP23_DESCRIPTION;
    }
    if (id.equals("SIP24")) {
      return VALIDATION_REPORT_SPECIFICATION_SIP24_DESCRIPTION;
    }
    if (id.equals("SIP25")) {
      return VALIDATION_REPORT_SPECIFICATION_SIP25_DESCRIPTION;
    }
    if (id.equals("SIP26")) {
      return VALIDATION_REPORT_SPECIFICATION_SIP26_DESCRIPTION;
    }
    if (id.equals("SIP27")) {
      return VALIDATION_REPORT_SPECIFICATION_SIP27_DESCRIPTION;
    }
    if (id.equals("SIP28")) {
      return VALIDATION_REPORT_SPECIFICATION_SIP28_DESCRIPTION;
    }
    if (id.equals("SIP29")) {
      return VALIDATION_REPORT_SPECIFICATION_SIP29_DESCRIPTION;
    }
    if (id.equals("SIP30")) {
      return VALIDATION_REPORT_SPECIFICATION_SIP30_DESCRIPTION;
    }
    if (id.equals("SIP31")) {
      return VALIDATION_REPORT_SPECIFICATION_SIP31_DESCRIPTION;
    }
    if (id.equals("SIP32")) {
      return VALIDATION_REPORT_SPECIFICATION_SIP32_DESCRIPTION;
    }
    if (id.equals("SIP33")) {
      return VALIDATION_REPORT_SPECIFICATION_SIP33_DESCRIPTION;
    }
    if (id.equals("SIP34")) {
      return VALIDATION_REPORT_SPECIFICATION_SIP34_DESCRIPTION;
    }
    if (id.equals("SIP35")) {
      return VALIDATION_REPORT_SPECIFICATION_SIP35_DESCRIPTION;
    }
    return "Not Defined";
  }

  /**
   * Get the cardinality of the requirement of given id.
   *
   * @param id
   *          {@link String} id of the requirement
   * @return the {@link String} cardinality of the requirement.
   */
  public static String getSpecificationCardinality(String id) {
    if (id.equals("SIP1")) {
      return VALIDATION_REPORT_SPECIFICATION_SIP1_CARDINALITY;
    }
    if (id.equals("SIP2")) {
      return VALIDATION_REPORT_SPECIFICATION_SIP2_CARDINALITY;
    }
    if (id.equals("SIP3")) {
      return VALIDATION_REPORT_SPECIFICATION_SIP3_CARDINALITY;
    }
    if (id.equals("SIP4")) {
      return VALIDATION_REPORT_SPECIFICATION_SIP4_CARDINALITY;
    }
    if (id.equals("SIP5")) {
      return VALIDATION_REPORT_SPECIFICATION_SIP5_CARDINALITY;
    }
    if (id.equals("SIP6")) {
      return VALIDATION_REPORT_SPECIFICATION_SIP6_CARDINALITY;
    }
    if (id.equals("SIP7")) {
      return VALIDATION_REPORT_SPECIFICATION_SIP7_CARDINALITY;
    }
    if (id.equals("SIP8")) {
      return VALIDATION_REPORT_SPECIFICATION_SIP8_CARDINALITY;
    }
    if (id.equals("SIP9")) {
      return VALIDATION_REPORT_SPECIFICATION_SIP9_CARDINALITY;
    }
    if (id.equals("SIP10")) {
      return VALIDATION_REPORT_SPECIFICATION_SIP10_CARDINALITY;
    }
    if (id.equals("SIP11")) {
      return VALIDATION_REPORT_SPECIFICATION_SIP11_CARDINALITY;
    }
    if (id.equals("SIP12")) {
      return VALIDATION_REPORT_SPECIFICATION_SIP12_CARDINALITY;
    }
    if (id.equals("SIP13")) {
      return VALIDATION_REPORT_SPECIFICATION_SIP13_CARDINALITY;
    }
    if (id.equals("SIP14")) {
      return VALIDATION_REPORT_SPECIFICATION_SIP14_CARDINALITY;
    }
    if (id.equals("SIP15")) {
      return VALIDATION_REPORT_SPECIFICATION_SIP15_CARDINALITY;
    }
    if (id.equals("SIP16")) {
      return VALIDATION_REPORT_SPECIFICATION_SIP16_CARDINALITY;
    }
    if (id.equals("SIP17")) {
      return VALIDATION_REPORT_SPECIFICATION_SIP17_CARDINALITY;
    }
    if (id.equals("SIP18")) {
      return VALIDATION_REPORT_SPECIFICATION_SIP18_CARDINALITY;
    }
    if (id.equals("SIP19")) {
      return VALIDATION_REPORT_SPECIFICATION_SIP19_CARDINALITY;
    }
    if (id.equals("SIP20")) {
      return VALIDATION_REPORT_SPECIFICATION_SIP20_CARDINALITY;
    }
    if (id.equals("SIP21")) {
      return VALIDATION_REPORT_SPECIFICATION_SIP21_CARDINALITY;
    }
    if (id.equals("SIP22")) {
      return VALIDATION_REPORT_SPECIFICATION_SIP22_CARDINALITY;
    }
    if (id.equals("SIP23")) {
      return VALIDATION_REPORT_SPECIFICATION_SIP23_CARDINALITY;
    }
    if (id.equals("SIP24")) {
      return VALIDATION_REPORT_SPECIFICATION_SIP24_CARDINALITY;
    }
    if (id.equals("SIP25")) {
      return VALIDATION_REPORT_SPECIFICATION_SIP25_CARDINALITY;
    }
    if (id.equals("SIP26")) {
      return VALIDATION_REPORT_SPECIFICATION_SIP26_CARDINALITY;
    }
    if (id.equals("SIP27")) {
      return VALIDATION_REPORT_SPECIFICATION_SIP27_CARDINALITY;
    }
    if (id.equals("SIP28")) {
      return VALIDATION_REPORT_SPECIFICATION_SIP28_CARDINALITY;
    }
    if (id.equals("SIP29")) {
      return VALIDATION_REPORT_SPECIFICATION_SIP29_CARDINALITY;
    }
    if (id.equals("SIP30")) {
      return VALIDATION_REPORT_SPECIFICATION_SIP30_CARDINALITY;
    }
    if (id.equals("SIP31")) {
      return VALIDATION_REPORT_SPECIFICATION_SIP31_CARDINALITY;
    }
    if (id.equals("SIP32")) {
      return VALIDATION_REPORT_SPECIFICATION_SIP32_CARDINALITY;
    }
    if (id.equals("SIP33")) {
      return VALIDATION_REPORT_SPECIFICATION_SIP33_CARDINALITY;
    }
    if (id.equals("SIP34")) {
      return VALIDATION_REPORT_SPECIFICATION_SIP34_CARDINALITY;
    }
    if (id.equals("SIP35")) {
      return VALIDATION_REPORT_SPECIFICATION_SIP35_CARDINALITY;
    }
    return "Not Defined";
  }

  /**
   * Get the level of the requirement of given id.
   *
   * @param id
   *          {@link String} id of the requirement
   * @return the {@link String} level of the requirement.
   */
  public static String getSpecificationLevel(String id) {
    if (id.equals("SIP1")) {
      return VALIDATION_REPORT_SPECIFICATION_SIP1_LEVEL;
    }
    if (id.equals("SIP2")) {
      return VALIDATION_REPORT_SPECIFICATION_SIP2_LEVEL;
    }
    if (id.equals("SIP3")) {
      return VALIDATION_REPORT_SPECIFICATION_SIP3_LEVEL;
    }
    if (id.equals("SIP4")) {
      return VALIDATION_REPORT_SPECIFICATION_SIP4_LEVEL;
    }
    if (id.equals("SIP5")) {
      return VALIDATION_REPORT_SPECIFICATION_SIP5_LEVEL;
    }
    if (id.equals("SIP6")) {
      return VALIDATION_REPORT_SPECIFICATION_SIP6_LEVEL;
    }
    if (id.equals("SIP7")) {
      return VALIDATION_REPORT_SPECIFICATION_SIP7_LEVEL;
    }
    if (id.equals("SIP8")) {
      return VALIDATION_REPORT_SPECIFICATION_SIP8_LEVEL;
    }
    if (id.equals("SIP9")) {
      return VALIDATION_REPORT_SPECIFICATION_SIP9_LEVEL;
    }
    if (id.equals("SIP10")) {
      return VALIDATION_REPORT_SPECIFICATION_SIP10_LEVEL;
    }
    if (id.equals("SIP11")) {
      return VALIDATION_REPORT_SPECIFICATION_SIP11_LEVEL;
    }
    if (id.equals("SIP12")) {
      return VALIDATION_REPORT_SPECIFICATION_SIP12_LEVEL;
    }
    if (id.equals("SIP13")) {
      return VALIDATION_REPORT_SPECIFICATION_SIP13_LEVEL;
    }
    if (id.equals("SIP14")) {
      return VALIDATION_REPORT_SPECIFICATION_SIP14_LEVEL;
    }
    if (id.equals("SIP15")) {
      return VALIDATION_REPORT_SPECIFICATION_SIP15_LEVEL;
    }
    if (id.equals("SIP16")) {
      return VALIDATION_REPORT_SPECIFICATION_SIP16_LEVEL;
    }
    if (id.equals("SIP17")) {
      return VALIDATION_REPORT_SPECIFICATION_SIP17_LEVEL;
    }
    if (id.equals("SIP18")) {
      return VALIDATION_REPORT_SPECIFICATION_SIP18_LEVEL;
    }
    if (id.equals("SIP19")) {
      return VALIDATION_REPORT_SPECIFICATION_SIP19_LEVEL;
    }
    if (id.equals("SIP20")) {
      return VALIDATION_REPORT_SPECIFICATION_SIP20_LEVEL;
    }
    if (id.equals("SIP21")) {
      return VALIDATION_REPORT_SPECIFICATION_SIP21_LEVEL;
    }
    if (id.equals("SIP22")) {
      return VALIDATION_REPORT_SPECIFICATION_SIP22_LEVEL;
    }
    if (id.equals("SIP23")) {
      return VALIDATION_REPORT_SPECIFICATION_SIP23_LEVEL;
    }
    if (id.equals("SIP24")) {
      return VALIDATION_REPORT_SPECIFICATION_SIP24_LEVEL;
    }
    if (id.equals("SIP25")) {
      return VALIDATION_REPORT_SPECIFICATION_SIP25_LEVEL;
    }
    if (id.equals("SIP26")) {
      return VALIDATION_REPORT_SPECIFICATION_SIP26_LEVEL;
    }
    if (id.equals("SIP27")) {
      return VALIDATION_REPORT_SPECIFICATION_SIP27_LEVEL;
    }
    if (id.equals("SIP28")) {
      return VALIDATION_REPORT_SPECIFICATION_SIP28_LEVEL;
    }
    if (id.equals("SIP29")) {
      return VALIDATION_REPORT_SPECIFICATION_SIP29_LEVEL;
    }
    if (id.equals("SIP30")) {
      return VALIDATION_REPORT_SPECIFICATION_SIP30_LEVEL;
    }
    if (id.equals("SIP31")) {
      return VALIDATION_REPORT_SPECIFICATION_SIP31_LEVEL;
    }
    if (id.equals("SIP32")) {
      return VALIDATION_REPORT_SPECIFICATION_SIP32_LEVEL;
    }
    if (id.equals("SIP33")) {
      return VALIDATION_REPORT_SPECIFICATION_SIP33_LEVEL;
    }
    if (id.equals("SIP34")) {
      return VALIDATION_REPORT_SPECIFICATION_SIP34_LEVEL;
    }
    if (id.equals("SIP35")) {
      return VALIDATION_REPORT_SPECIFICATION_SIP35_LEVEL;
    }
    return "Not Defined";
  }
}
