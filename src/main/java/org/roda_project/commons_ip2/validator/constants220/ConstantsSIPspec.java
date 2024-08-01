package org.roda_project.commons_ip2.validator.constants220;

/** {@author João Gomes <jgomes@keep.pt>}. */
public final class ConstantsSIPspec {
  private ConstantsSIPspec() {
    // do nothing
  }

  /* Extended use of the METS root element (element mets) */

  /* SIP1 */
  /**
   * Constant specification id "SIP1".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP1_ID = "SIP1";

  /**
   * Constant specification name for id "SIP1".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP1_NAME = "Package name";

  /**
   * Constant specification location for id "SIP1".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP1_LOCATION = "mets/@LABEL";

  /**
   * Constant specification description for id "SIP1".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP1_DESCRIPTION = "An optional short text describing "
    + "the contents of the package, " + "e.g. “Accounting records of 2017”.";

  /**
   * Constant specification cardinality for id "SIP1".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP1_CARDINALITY = "0..1";

  /**
   * Constant specification level for id "SIP1".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP1_LEVEL = "MAY";

  /* SIP2 */

  /**
   * Constant specification id "SIP2".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP2_ID = "SIP2";

  /**
   * Constant specification name for id "SIP2".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP2_NAME = "METS Profile";

  /**
   * Constant specification location for id "SIP2".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP2_LOCATION = "mets/@PROFILE";

  /**
   * Constant specification description for id "SIP2".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP2_DESCRIPTION = "The value is set to "
    + "“https://earksip.dilcis.eu/profile/E-ARK-SIP-v2-2-0.xml”.";

  /**
   * Constant specification cardinality for id "SIP2".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP2_CARDINALITY = "1..1";

  /**
   * Constant specification level for id "SIP2".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP2_LEVEL = "MUST";

  /* Extended use of the METS header (element metsHdr) */

  /* SIP3 */

  /**
   * Constant specification id "SIP3".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP3_ID = "SIP3";

  /**
   * Constant specification name for id "SIP3".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP3_NAME = "Package status";

  /**
   * Constant specification location for id "SIP3".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP3_LOCATION = "metsHdr/@RECORDSTATUS";

  /**
   * Constant specification description for id "SIP3".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP3_DESCRIPTION = "A way of indicating the status of the "
    + "package and to instruct the OAIS on how "
    + "to properly handle the package. If not set, the expected behaviour is equal to " + "NEW.";

  /**
   * Constant specification cardinality for id "SIP3".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP3_CARDINALITY = "0..1";

  /**
   * Constant specification level for id "SIP3".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP3_LEVEL = "MAY";

  /* SIP4 */

  /**
   * Constant specification id "SIP4".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP4_ID = "SIP4";

  /**
   * Constant specification name for id "SIP4".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP4_NAME = "OAIS Package type information";

  /**
   * Constant specification location for id "SIP4".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP4_LOCATION = "metsHdr/@csip:OAISPACKAGETYPE";

  /**
   * Constant specification description for id "SIP4".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP4_DESCRIPTION = "@csip:OAISPACKAGETYPE is used "
    + "with the value “SIP”.";

  /**
   * Constant specification cardinality for id "SIP4".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP4_CARDINALITY = "1..1";

  /**
   * Constant specification level for id "SIP4".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP4_LEVEL = "MUST";

  /* SIP5 */
  /**
   * Constant specification id "SIP5".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP5_ID = "SIP5";

  /**
   * Constant specification name for id "SIP5".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP5_NAME = "Submission agreement";

  /**
   * Constant specification location for id "SIP5".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP5_LOCATION = "metsHdr/altRecordID";

  /**
   * Constant specification description for id "SIP5".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP5_DESCRIPTION = "A reference to the Submission "
    + "Agreement associated with the package. " + "@TYPE is used with the value “SUBMISSIONAGREEMENT”. Example: RA "
    + "13-2011/5329; 2012-04-12 Example: " + "http://submissionagreement.kb.se/dnr331-1144-2011/20120711/ Note: It is "
    + "recommended to use a machine-readable format for a better description of a "
    + "submission agreement. For example, " + "the submission agreement developed " + "by Docuteam GmbH "
    + "http://www.loc.gov/standards/mets/profiles/00000041.xml";

  /**
   * Constant specification cardinality for id "SIP5".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP5_CARDINALITY = "0..1";

  /**
   * Constant specification level for id "SIP5".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP5_LEVEL = "MAY";

  /* SIP6 */

  /**
   * Constant specification id "SIP6".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP6_ID = "SIP6";

  /**
   * Constant specification name for id "SIP6".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP6_NAME = "Previous Submission agreement";

  /**
   * Constant specification location for id "SIP6".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP6_LOCATION = "metsHdr/altRecordID";

  /**
   * Constant specification description for id "SIP6".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP6_DESCRIPTION = "An optional reference to a previous "
    + "submission agreement(s) which the\n" + "information may have belonged to. @TYPE is used with the value "
    + "“PREVIOUSSUBMISSIONAGREEMENT”. Example: FM 12-2387/12726, " + "2007-09-19 Example: "
    + "http://submissionagreement.kb.se/dnr331-1144-2011/20120711/ Note: It is "
    + "recommended to use a machine-readable format for a better description of a "
    + "submission agreement. For example, the submission agreement developed " + "by Docuteam GmbH "
    + "http://www.loc.gov/standards/mets/profiles/00000041.xml";

  /**
   * Constant specification cardinality for id "SIP6".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP6_CARDINALITY = "0..*";

  /**
   * Constant specification level for id "SIP6".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP6_LEVEL = "MAY";

  /* SIP7 */

  /**
   * Constant specification id "SIP7".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP7_ID = "SIP7";

  /**
   * Constant specification name for id "SIP7".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP7_NAME = "Archival reference code";

  /**
   * Constant specification location for id "SIP7".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP7_LOCATION = "metsHdr/altRecordID";

  /**
   * Constant specification description for id "SIP7".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP7_DESCRIPTION = "An optional reference code "
    + "indicating where in the archival hierarchy the "
    + "package shall be placed in the OAIS. @TYPE is used with the value "
    + "“REFERENCECODE”. Example: FM 12-2387/12726, 2007-09-19";

  /**
   * Constant specification cardinality for id "SIP7".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP7_CARDINALITY = "0..1";

  /**
   * Constant specification level for id "SIP7".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP7_LEVEL = "MAY";

  /* SIP8 */
  /**
   * Constant specification id "SIP8".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP8_ID = "SIP8";

  /**
   * Constant specification name for id "SIP8".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP8_NAME = " Previous archival reference code";

  /**
   * Constant specification location for id "SIP8".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP8_LOCATION = "metsHdr/altRecordID";

  /**
   * Constant specification description for id "SIP8".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP8_DESCRIPTION = "In cases where the SIP originates "
    + "from other institutions maintaining a "
    + "reference code structure, this element can be used to record these reference "
    + "codes and therefore support the provenance of the package when a whole "
    + "archival description is not submitted with the submission. @TYPE is used "
    + "with the value “PREVIOUSREFERENCECODE”. Example: " + "SE/FM/123/123.1/123.1.3";

  /**
   * Constant specification cardinality for id "SIP8".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP8_CARDINALITY = "0..*";

  /**
   * Constant specification level for id "SIP8".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP8_LEVEL = "MAY";

  /* SIP9 */

  /**
   * Constant specification id "SIP9".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP9_ID = "SIP9";

  /**
   * Constant specification name for id "SIP9".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP9_NAME = "Archival creator agent";

  /**
   * Constant specification location for id "SIP9".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP9_LOCATION = "metsHdr/agent";

  /**
   * Constant specification description for id "SIP9".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP9_DESCRIPTION = "A wrapper element that enables "
    + "to encode the name of the organisation or "
    + "person that originally created the data being transferred. Please note that "
    + "this might be different from the organisation which has been charged with "
    + "preparing and sending the SIP to the archives.";

  /**
   * Constant specification cardinality for id "SIP9".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP9_CARDINALITY = "0..1";

  /**
   * Constant specification level for id "SIP9".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP9_LEVEL = "MAY";

  /* SIP10 */
  /**
   * Constant specification id "SIP10".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP10_ID = "SIP10";

  /**
   * Constant specification name for id "SIP10".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP10_NAME = "Archival creator agent role";

  /**
   * Constant specification location for id "SIP10".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP10_LOCATION = "metsHdr/agent/@ROLE";

  /**
   * Constant specification description for id "SIP10".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP10_DESCRIPTION = "The role of the person(s) "
    + "or institution(s) responsible for the document/collection.";

  /**
   * Constant specification cardinality for id "SIP10".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP10_CARDINALITY = "1..1";

  /**
   * Constant specification level for id "SIP10".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP10_LEVEL = "MUST";

  /* SIP11 */

  /**
   * Constant specification id "SIP11".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP11_ID = "SIP11";

  /**
   * Constant specification name for id "SIP11".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP11_NAME = "Archival creator agent type";

  /**
   * Constant specification location for id "SIP11".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP11_LOCATION = "metsHdr/agent/@TYPE";

  /**
   * Constant specification description for id "SIP11".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP11_DESCRIPTION = "The type of the "
    + "archival creator agent is" + " “ORGANIZATION” or “INDIVIDUAL”";

  /**
   * Constant specification cardinality for id "SIP11".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP11_CARDINALITY = "1..1";

  /**
   * Constant specification level for id "SIP11".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP11_LEVEL = "MUST";

  /* SIP12 */

  /**
   * Constant specification id "SIP12".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP12_ID = "SIP12";

  /**
   * Constant specification name for id "SIP12".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP12_NAME = "Archival creator agent name";

  /**
   * Constant specification location for id "SIP12".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP12_LOCATION = "metsHdr/agent/name";

  /**
   * Constant specification description for id "SIP12".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP12_DESCRIPTION = "The name of the organisation(s) "
    + "that riginally created the data being "
    + "transferred. Please note that this might be different from the organisation "
    + "which has been charged with preparing and sending the SIP to the archives.";

  /**
   * Constant specification cardinality for id "SIP12".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP12_CARDINALITY = "1..1";

  /**
   * Constant specification level for id "SIP12".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP12_LEVEL = "MAY";

  /* SIP13 */

  /**
   * Constant specification id "SIP13".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP13_ID = "SIP13";

  /**
   * Constant specification name for id "SIP13".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP13_NAME = "Archival creator agent "
    + "additional information";

  /**
   * Constant specification location for id "SIP12".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP13_LOCATION = "metsHdr/agent/note";

  /**
   * Constant specification description for id "SIP13".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP13_DESCRIPTION = "The archival creator "
    + "agent has a note providing a " + "unique identification code for the archival creator.";

  /**
   * Constant specification cardinality for id "SIP12".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP13_CARDINALITY = "0..1";

  /**
   * Constant specification level for id "SIP12".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP13_LEVEL = "MAY";

  /* SIP14 */

  /**
   * Constant specification id "SIP14".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP14_ID = "SIP14";

  /**
   * Constant specification name for id "SIP14".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP14_NAME = "Classification of the archival creator"
    + " agent additional information";

  /**
   * Constant specification location for id "SIP14".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP14_LOCATION = "metsHdr/agent/note/@csip:NOTETYPE";

  /**
   * Constant specification description for id "SIP14".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP14_DESCRIPTION = "The archival creator agent note is "
    + "typed with the value of “IDENTIFICATIONCODE”.";

  /**
   * Constant specification cardinality for id "SIP14".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP14_CARDINALITY = "1..1";

  /**
   * Constant specification level for id "SIP14".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP14_LEVEL = "MUST";

  /* SIP15 */
  /**
   * Constant specification id "SIP15".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP15_ID = "SIP15";

  /**
   * Constant specification name for id "SIP15".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP15_NAME = "Submitting agent";

  /**
   * Constant specification location for id "SIP15".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP15_LOCATION = "metsHdr/agent";

  /**
   * Constant specification description for id "SIP15".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP15_DESCRIPTION = "A wrapper element that enables to "
    + "encode the name of the organisation or person submitting the package to the archive.";

  /**
   * Constant specification cardinality for id "SIP15".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP15_CARDINALITY = "1..1";

  /**
   * Constant specification level for id "SIP15".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP15_LEVEL = "MUST";

  /* SIP16 */

  /**
   * Constant specification id "SIP16".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP16_ID = "SIP16";

  /**
   * Constant specification name for id "SIP16".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP16_NAME = "Submitting agent role";

  /**
   * Constant specification location for id "SIP16".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP16_LOCATION = "metsHdr/agent/@ROLE";

  /**
   * Constant specification description for id "SIP16".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP16_DESCRIPTION = "The role of the person(s) or "
    + "institution(s) " + "responsible for creating and/or submitting the package";

  /**
   * Constant specification cardinality for id "SIP16".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP16_CARDINALITY = "1..1";

  /**
   * Constant specification level for id "SIP16".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP16_LEVEL = "MUST";

  /* SIP17 */

  /**
   * Constant specification id "SIP17".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP17_ID = "SIP17";

  /**
   * Constant specification name for id "SIP17".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP17_NAME = "Submitting agent type";

  /**
   * Constant specification location for id "SIP17".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP17_LOCATION = "metsHdr/agent/@TYPE";

  /**
   * Constant specification description for id "SIP17".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP17_DESCRIPTION = "The type of the submitting agent"
    + " is “ORGANIZATION” or “INDIVIDUAL”.";

  /**
   * Constant specification cardinality for id "SIP17".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP17_CARDINALITY = "1..1";

  /**
   * Constant specification level for id "SIP17".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP17_LEVEL = "MUST";

  /* SIP18 */
  /**
   * Constant specification id "SIP18".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP18_ID = "SIP18";

  /**
   * Constant specification name for id "SIP18".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP18_NAME = "Submitting agent name";

  /**
   * Constant specification location for id "SIP18".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP18_LOCATION = "metsHdr/agent/name";

  /**
   * Constant specification description for id "SIP18".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP18_DESCRIPTION = "Name of the organisation "
    + "submitting the package to the archive.";

  /**
   * Constant specification cardinality for id "SIP18".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP18_CARDINALITY = "1..1";

  /**
   * Constant specification level for id "SIP18".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP18_LEVEL = "MUST";

  /* SIP19 */

  /**
   * Constant specification id "SIP19".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP19_ID = "SIP19";

  /**
   * Constant specification name for id "SIP19".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP19_NAME = "Submitting agent additional information";

  /**
   * Constant specification location for id "SIP19".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP19_LOCATION = "metsHdr/agent/note";

  /**
   * Constant specification description for id "SIP19".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP19_DESCRIPTION = "The submitting agent has a note "
    + "providing a unique identification code for the submitter.";

  /**
   * Constant specification cardinality for id "SIP19".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP19_CARDINALITY = "0..1";

  /**
   * Constant specification level for id "SIP19".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP19_LEVEL = "MAY";

  /* SIP20 */
  /**
   * Constant specification id "SIP20".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP20_ID = "SIP20";

  /**
   * Constant specification name for id "SIP20".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP20_NAME = "Classification of the submitting "
    + "agent additional information";

  /**
   * Constant specification location for id "SIP20".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP20_LOCATION = "metsHdr/agent/note/@csip:NOTETYPE";

  /**
   * Constant specification description for id "SIP20".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP20_DESCRIPTION = "The submitting agent note is "
    + "typed with the value of “IDENTIFICATIONCODE”.";

  /**
   * Constant specification cardinality for id "SIP20".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP20_CARDINALITY = "1..1";

  /**
   * Constant specification description for id "SIP20".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP20_LEVEL = "MUST";

  /* SIP21 */

  /**
   * Constant specification id "SIP21".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP21_ID = "SIP21";

  /**
   * Constant specification name for id "SIP21".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP21_NAME = "Contact person agent";

  /**
   * Constant specification location for id "SIP21".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP21_LOCATION = "metsHdr/agent";

  /**
   * Constant specification description for id "SIP21".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP21_DESCRIPTION = "A wrapper element that enables "
    + "to encode the name of the contact person for the submission.";

  /**
   * Constant specification cardinality for id "SIP21".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP21_CARDINALITY = "0..*";

  /**
   * Constant specification level for id "SIP21".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP21_LEVEL = "MAY";

  /* SIP22 */

  /**
   * Constant specification id "SIP22".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP22_ID = "SIP22";

  /**
   * Constant specification name for id "SIP22".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP22_NAME = "Contact person agent role";

  /**
   * Constant specification description for id "SIP22".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP22_LOCATION = "metsHdr/agent/@ROLE";

  /**
   * Constant specification description for id "SIP22".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP22_DESCRIPTION = "The role of the contact "
    + "person is “CREATOR”";

  /**
   * Constant specification cardinality for id "SIP22".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP22_CARDINALITY = "1..1";

  /**
   * Constant specification level for id "SIP22".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP22_LEVEL = "MUST";

  /* SIP23 */

  /**
   * Constant specification id "SIP23".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP23_ID = "SIP23";

  /**
   * Constant specification name for id "SIP23".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP23_NAME = "Contact person agent type";

  /**
   * Constant specification location for id "SIP23".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP23_LOCATION = "metsHdr/agent/@TYPE";

  /**
   * Constant specification description for id "SIP23".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP23_DESCRIPTION = "The type of the contact person "
    + "agent is “INDIVIDUAL”";

  /**
   * Constant specification cardinality for id "SIP23".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP23_CARDINALITY = "1..1";

  /**
   * Constant specification level for id "SIP23".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP23_LEVEL = "MUST";

  /* SIP24 */

  /**
   * Constant specification id "SIP24".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP24_ID = "SIP24";

  /**
   * Constant specification name for id "SIP24".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP24_NAME = "Contact person agent name";

  /**
   * Constant specification location for id "SIP24".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP24_LOCATION = "metsHdr/agent/name";

  /**
   * Constant specification description for id "SIP24".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP24_DESCRIPTION = "Name of the contact person";

  /**
   * Constant specification cardinality for id "SIP24".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP24_CARDINALITY = "1..1";

  /**
   * Constant specification level for id "SIP24".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP24_LEVEL = "MUST";

  /* SIP25 */

  /**
   * Constant specification id "SIP25".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP25_ID = "SIP25";

  /**
   * Constant specification name for id "SIP25".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP25_NAME = "Contact person agent additional information";

  /**
   * Constant specification location for id "SIP25".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP25_LOCATION = "metsHdr/agent/note";

  /**
   * Constant specification description for id "SIP25".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP25_DESCRIPTION = "The submitting agent "
    + "has one or more notes" + " giving the contact information.";

  /**
   * Constant specification cardinality for id "SIP25".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP25_CARDINALITY = "0..*";

  /**
   * Constant specification level for id "SIP25".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP25_LEVEL = "MAY";

  /* SIP26 */
  /**
   * Constant specification id "SIP26".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP26_ID = "SIP26";

  /**
   * Constant specification name for id "SIP26".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP26_NAME = "Preservation agent";

  /**
   * Constant specification location for id "SIP26".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP26_LOCATION = "metsHdr/agent";

  /**
   * Constant specification description for id "SIP26".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP26_DESCRIPTION = "A wrapper element that enables to" +
    " encode the name of the organisation or person that preserves the package.";

  /**
   * Constant specification cardinality for id "SIP26".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP26_CARDINALITY = "0..1";

  /**
   * Constant specification level for id "SIP26".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP26_LEVEL = "MAY";

  /* SIP27 */

  /**
   * Constant specification id "SIP27".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP27_ID = "SIP27";

  /**
   * Constant specification name for id "SIP27".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP27_NAME = "Preservation agent role";

  /**
   * Constant specification location for id "SIP27".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP27_LOCATION = "metsHdr/agent/@ROLE";

  /**
   * Constant specification description for id "SIP27".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP27_DESCRIPTION = "The role of the preservation "
    + "agent is “PRESERVATION”.";

  /**
   * Constant specification cardinality for id "SIP27".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP27_CARDINALITY = "1..1";

  /**
   * Constant specification level for id "SIP27".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP27_LEVEL = "MUST";

  /* SIP28 */

  /**
   * Constant specification id "SIP28".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP28_ID = "SIP28";

  /**
   * Constant specification name for id "SIP28".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP28_NAME = "Preservation agent type";

  /**
   * Constant specification location for id "SIP28".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP28_LOCATION = "metsHdr/agent/@TYPE";

  /**
   * Constant specification description for id "SIP28".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP28_DESCRIPTION = "The type of the submitting "
    + "agent is “ORGANIZATION”";

  /**
   * Constant specification cardinality for id "SIP28".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP28_CARDINALITY = "1..1";

  /**
   * Constant specification level for id "SIP28".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP28_LEVEL = "MUST";

  /* SIP29 */

  /**
   * Constant specification id "SIP29".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP29_ID = "SIP29";

  /**
   * Constant specification name for id "SIP29".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP29_NAME = "Preservation agent name";

  /**
   * Constant specification location for id "SIP29".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP29_LOCATION = "metsHdr/agent/name";

  /**
   * Constant specification description for id "SIP29".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP29_DESCRIPTION = "Name of the organisation "
    + "preserving the package.";

  /**
   * Constant specification cardinality for id "SIP29".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP29_CARDINALITY = "1..1";

  /**
   * Constant specification level for id "SIP29".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP29_LEVEL = "MUST";

  /* SIP30 */
  /**
   * Constant specification id "SIP30".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP30_ID = "SIP30";

  /**
   * Constant specification name for id "SIP30".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP30_NAME = "Preservation agent additional information";

  /**
   * Constant specification location for id "SIP30".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP30_LOCATION = "metsHdr/agent/note";

  /**
   * Constant specification description for id "SIP30".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP30_DESCRIPTION = "The preservation agent "
    + "has a note providing a " + "unique identification code for the archival creator.";

  /**
   * Constant specification cardinality for id "SIP30".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP30_CARDINALITY = "0..1";

  /**
   * Constant specification level for id "SIP30".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP30_LEVEL = "MAY";

  /* SIP31 */
  /**
   * Constant specification id "SIP31".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP31_ID = "SIP31";

  /**
   * Constant specification name for id "SIP31".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP31_NAME = "Classification of the preservation "
    + "agent additional information";

  /**
   * Constant specification location for id "SIP31".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP31_LOCATION = "metsHdr/agent/note/@csip:NOTETYPE";

  /**
   * Constant specification description for id "SIP31".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP31_DESCRIPTION = "The preservation agent note is "
    + "typed with the value of “IDENTIFICATIONCODE”.";

  /**
   * Constant specification cardinality for id "SIP31".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP31_CARDINALITY = "1..1";

  /**
   * Constant specification level for id "SIP31".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP31_LEVEL = "MUST";

  /* Extended use of the METS file section (element fileSec) */

  /* SIP32 */

  /**
   * Constant specification id "SIP32".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP32_ID = "SIP32";

  /**
   * Constant specification name for id "SIP32".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP32_NAME = "File format name";

  /**
   * Constant specification location for id "SIP32".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP32_LOCATION = "fileSec/fileGrp"
    + "/file/@sip:FILEFORMATNAME";

  /**
   * Constant specification description for id "SIP32".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP32_DESCRIPTION = "An optional attribute may be used "
    + "if the MIMETYPE is not sufficient for the " + "purposes of processing the information package. Example: "
    + "“Extensible Markup Language” Example: “PDF/A” Example: “ISO/IEC 26300:2006”";

  /**
   * Constant specification cardinality for id "SIP32".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP32_CARDINALITY = "0..1";

  /**
   * Constant specification level for id "SIP32".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP32_LEVEL = "MAY";

  /* SIP33 */

  /**
   * Constant specification id "SIP33".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP33_ID = "SIP33";

  /**
   * Constant specification name for id "SIP33".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP33_NAME = "File format version";

  /**
   * Constant specification location for id "SIP33".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP33_LOCATION = "fileSec/fileGrp/file"
    + "/@sip:FILEFORMATVERSION";

  /**
   * Constant specification description for id "SIP33".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP33_DESCRIPTION = "The version of the file "
    + "format when the use of PREMIS " + "has not been agreed upon in the submission agreement. Example: “1.0”";

  /**
   * Constant specification cardinality for id "SIP33".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP33_CARDINALITY = "0..1";

  /**
   * Constant specification level for id "SIP33".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP33_LEVEL = "MAY";

  /* SIP34 */

  /**
   * Constant specification id "SIP34".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP34_ID = "SIP34";

  /**
   * Constant specification name for id "SIP34".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP34_NAME = "File format registry";

  /**
   * Constant specification location for id "SIP34".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP34_LOCATION = "fileSec/fileGrp/file"
    + "/@sip:FILEFORMATREGISTRY";

  /**
   * Constant specification description for id "SIP34".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP34_DESCRIPTION = "The name of the "
    + "format registry used to " + "identify the file format when the use of "
    + "PREMIS has not been agreed upon in the submission agreement. Example:“PRONOM”";

  /**
   * Constant specification cardinality for id "SIP34".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP34_CARDINALITY = "0..1";

  /**
   * Constant specification level for id "SIP34".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP34_LEVEL = "MAY";

  /* SIP35 */

  /**
   * Constant specification id "SIP35".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP35_ID = "SIP35";

  /**
   * Constant specification name for id "SIP35".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP35_NAME = "File format registry key";

  /**
   * Constant specification location for id "SIP35".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP35_LOCATION = "fileSec/fileGrp/file/@sip:FILEFORMATKEY";

  /**
   * Constant specification description for id "SIP35".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP35_DESCRIPTION = "Key of the file format "
    + "in the registry when use of PREMIS "
    + "has not been agreed upon in the submission agreement. Example: “fmt/101”";

  /**
   * Constant specification cardinality for id "SIP35".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP35_CARDINALITY = "0..1";

  /**
   * Constant specification level for id "SIP35".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_SIP35_LEVEL = "MAY";

  /**
   * Constant "Not Defined".
   */
  public static final String NOT_DEFINED = "Not Defined";

  /**
   * Get the name of the requirement of given id.
   *
   * @param id
   *          {@link String} id of the requirement
   * @return the {@link String} name of the requirement.
   */
  public static String getSpecificationName(final String id) {
    if (VALIDATION_REPORT_SPECIFICATION_SIP1_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_SIP1_NAME;
    }
    if (VALIDATION_REPORT_SPECIFICATION_SIP2_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_SIP2_NAME;
    }
    if (VALIDATION_REPORT_SPECIFICATION_SIP3_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_SIP3_NAME;
    }
    if (VALIDATION_REPORT_SPECIFICATION_SIP4_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_SIP4_NAME;
    }
    if (VALIDATION_REPORT_SPECIFICATION_SIP5_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_SIP5_NAME;
    }
    if (VALIDATION_REPORT_SPECIFICATION_SIP6_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_SIP6_NAME;
    }
    if (VALIDATION_REPORT_SPECIFICATION_SIP7_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_SIP7_NAME;
    }
    if (VALIDATION_REPORT_SPECIFICATION_SIP8_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_SIP8_NAME;
    }
    if (VALIDATION_REPORT_SPECIFICATION_SIP9_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_SIP9_NAME;
    }
    if (VALIDATION_REPORT_SPECIFICATION_SIP10_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_SIP10_NAME;
    }
    if (VALIDATION_REPORT_SPECIFICATION_SIP11_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_SIP11_NAME;
    }
    if (VALIDATION_REPORT_SPECIFICATION_SIP12_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_SIP12_NAME;
    }
    if (VALIDATION_REPORT_SPECIFICATION_SIP13_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_SIP13_NAME;
    }
    if (VALIDATION_REPORT_SPECIFICATION_SIP14_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_SIP14_NAME;
    }
    if (VALIDATION_REPORT_SPECIFICATION_SIP15_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_SIP15_NAME;
    }
    if (VALIDATION_REPORT_SPECIFICATION_SIP16_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_SIP16_NAME;
    }
    if (VALIDATION_REPORT_SPECIFICATION_SIP17_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_SIP17_NAME;
    }
    if (VALIDATION_REPORT_SPECIFICATION_SIP18_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_SIP18_NAME;
    }
    if (VALIDATION_REPORT_SPECIFICATION_SIP19_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_SIP19_NAME;
    }
    if (VALIDATION_REPORT_SPECIFICATION_SIP20_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_SIP20_NAME;
    }
    if (VALIDATION_REPORT_SPECIFICATION_SIP21_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_SIP21_NAME;
    }
    if (VALIDATION_REPORT_SPECIFICATION_SIP22_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_SIP22_NAME;
    }
    if (VALIDATION_REPORT_SPECIFICATION_SIP23_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_SIP23_NAME;
    }
    if (VALIDATION_REPORT_SPECIFICATION_SIP24_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_SIP24_NAME;
    }
    if (VALIDATION_REPORT_SPECIFICATION_SIP25_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_SIP25_NAME;
    }
    if (VALIDATION_REPORT_SPECIFICATION_SIP26_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_SIP26_NAME;
    }
    if (VALIDATION_REPORT_SPECIFICATION_SIP27_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_SIP27_NAME;
    }
    if (VALIDATION_REPORT_SPECIFICATION_SIP28_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_SIP28_NAME;
    }
    if (VALIDATION_REPORT_SPECIFICATION_SIP29_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_SIP29_NAME;
    }
    if (VALIDATION_REPORT_SPECIFICATION_SIP30_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_SIP30_NAME;
    }
    if (VALIDATION_REPORT_SPECIFICATION_SIP31_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_SIP31_NAME;
    }
    if (VALIDATION_REPORT_SPECIFICATION_SIP32_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_SIP32_NAME;
    }
    if (VALIDATION_REPORT_SPECIFICATION_SIP33_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_SIP33_NAME;
    }
    if (VALIDATION_REPORT_SPECIFICATION_SIP34_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_SIP34_NAME;
    }
    if (VALIDATION_REPORT_SPECIFICATION_SIP35_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_SIP35_NAME;
    }
    return NOT_DEFINED;
  }

  /**
   * Get the location of the requirement of given id.
   *
   * @param id
   *          {@link String} id of the requirement
   * @return the {@link String} location of the requirement.
   */
  public static String getSpecificationLocation(final String id) {
    if (VALIDATION_REPORT_SPECIFICATION_SIP1_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_SIP1_LOCATION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_SIP2_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_SIP2_LOCATION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_SIP3_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_SIP3_LOCATION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_SIP4_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_SIP4_LOCATION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_SIP5_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_SIP5_LOCATION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_SIP6_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_SIP6_LOCATION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_SIP7_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_SIP7_LOCATION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_SIP8_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_SIP8_LOCATION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_SIP9_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_SIP9_LOCATION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_SIP10_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_SIP10_LOCATION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_SIP11_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_SIP11_LOCATION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_SIP12_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_SIP12_LOCATION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_SIP13_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_SIP13_LOCATION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_SIP14_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_SIP14_LOCATION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_SIP15_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_SIP15_LOCATION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_SIP16_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_SIP16_LOCATION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_SIP17_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_SIP17_LOCATION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_SIP18_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_SIP18_LOCATION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_SIP19_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_SIP19_LOCATION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_SIP20_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_SIP20_LOCATION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_SIP21_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_SIP21_LOCATION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_SIP22_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_SIP22_LOCATION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_SIP23_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_SIP23_LOCATION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_SIP24_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_SIP24_LOCATION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_SIP25_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_SIP25_LOCATION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_SIP26_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_SIP26_LOCATION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_SIP27_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_SIP27_LOCATION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_SIP28_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_SIP28_LOCATION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_SIP29_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_SIP29_LOCATION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_SIP30_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_SIP30_LOCATION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_SIP31_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_SIP31_LOCATION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_SIP32_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_SIP32_LOCATION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_SIP33_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_SIP33_LOCATION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_SIP34_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_SIP34_LOCATION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_SIP35_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_SIP35_LOCATION;
    }
    return NOT_DEFINED;
  }

  /**
   * Get the description of the requirement of given id.
   *
   * @param id
   *          {@link String} id of the requirement
   * @return the {@link String} description of the requirement.
   */
  public static String getSpecificationDescription(final String id) {
    if (VALIDATION_REPORT_SPECIFICATION_SIP1_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_SIP1_DESCRIPTION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_SIP2_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_SIP2_DESCRIPTION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_SIP3_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_SIP3_DESCRIPTION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_SIP4_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_SIP4_DESCRIPTION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_SIP5_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_SIP5_DESCRIPTION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_SIP6_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_SIP6_DESCRIPTION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_SIP7_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_SIP7_DESCRIPTION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_SIP8_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_SIP8_DESCRIPTION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_SIP9_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_SIP9_DESCRIPTION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_SIP10_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_SIP10_DESCRIPTION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_SIP11_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_SIP11_DESCRIPTION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_SIP12_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_SIP12_DESCRIPTION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_SIP13_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_SIP13_DESCRIPTION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_SIP14_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_SIP14_DESCRIPTION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_SIP15_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_SIP15_DESCRIPTION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_SIP16_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_SIP16_DESCRIPTION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_SIP17_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_SIP17_DESCRIPTION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_SIP18_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_SIP18_DESCRIPTION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_SIP19_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_SIP19_DESCRIPTION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_SIP20_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_SIP20_DESCRIPTION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_SIP21_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_SIP21_DESCRIPTION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_SIP22_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_SIP22_DESCRIPTION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_SIP23_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_SIP23_DESCRIPTION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_SIP24_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_SIP24_DESCRIPTION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_SIP25_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_SIP25_DESCRIPTION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_SIP26_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_SIP26_DESCRIPTION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_SIP27_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_SIP27_DESCRIPTION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_SIP28_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_SIP28_DESCRIPTION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_SIP29_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_SIP29_DESCRIPTION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_SIP30_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_SIP30_DESCRIPTION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_SIP31_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_SIP31_DESCRIPTION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_SIP32_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_SIP32_DESCRIPTION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_SIP33_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_SIP33_DESCRIPTION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_SIP34_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_SIP34_DESCRIPTION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_SIP35_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_SIP35_DESCRIPTION;
    }
    return NOT_DEFINED;
  }

  /**
   * Get the cardinality of the requirement of given id.
   *
   * @param id
   *          {@link String} id of the requirement
   * @return the {@link String} cardinality of the requirement.
   */
  public static String getSpecificationCardinality(final String id) {
    if (VALIDATION_REPORT_SPECIFICATION_SIP1_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_SIP1_CARDINALITY;
    }
    if (VALIDATION_REPORT_SPECIFICATION_SIP2_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_SIP2_CARDINALITY;
    }
    if (VALIDATION_REPORT_SPECIFICATION_SIP3_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_SIP3_CARDINALITY;
    }
    if (VALIDATION_REPORT_SPECIFICATION_SIP4_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_SIP4_CARDINALITY;
    }
    if (VALIDATION_REPORT_SPECIFICATION_SIP5_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_SIP5_CARDINALITY;
    }
    if (VALIDATION_REPORT_SPECIFICATION_SIP6_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_SIP6_CARDINALITY;
    }
    if (VALIDATION_REPORT_SPECIFICATION_SIP7_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_SIP7_CARDINALITY;
    }
    if (VALIDATION_REPORT_SPECIFICATION_SIP8_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_SIP8_CARDINALITY;
    }
    if (VALIDATION_REPORT_SPECIFICATION_SIP9_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_SIP9_CARDINALITY;
    }
    if (VALIDATION_REPORT_SPECIFICATION_SIP10_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_SIP10_CARDINALITY;
    }
    if (VALIDATION_REPORT_SPECIFICATION_SIP11_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_SIP11_CARDINALITY;
    }
    if (VALIDATION_REPORT_SPECIFICATION_SIP12_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_SIP12_CARDINALITY;
    }
    if (VALIDATION_REPORT_SPECIFICATION_SIP13_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_SIP13_CARDINALITY;
    }
    if (VALIDATION_REPORT_SPECIFICATION_SIP14_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_SIP14_CARDINALITY;
    }
    if (VALIDATION_REPORT_SPECIFICATION_SIP15_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_SIP15_CARDINALITY;
    }
    if (VALIDATION_REPORT_SPECIFICATION_SIP16_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_SIP16_CARDINALITY;
    }
    if (VALIDATION_REPORT_SPECIFICATION_SIP17_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_SIP17_CARDINALITY;
    }
    if (VALIDATION_REPORT_SPECIFICATION_SIP18_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_SIP18_CARDINALITY;
    }
    if (VALIDATION_REPORT_SPECIFICATION_SIP19_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_SIP19_CARDINALITY;
    }
    if (VALIDATION_REPORT_SPECIFICATION_SIP20_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_SIP20_CARDINALITY;
    }
    if (VALIDATION_REPORT_SPECIFICATION_SIP21_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_SIP21_CARDINALITY;
    }
    if (VALIDATION_REPORT_SPECIFICATION_SIP22_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_SIP22_CARDINALITY;
    }
    if (VALIDATION_REPORT_SPECIFICATION_SIP23_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_SIP23_CARDINALITY;
    }
    if (VALIDATION_REPORT_SPECIFICATION_SIP24_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_SIP24_CARDINALITY;
    }
    if (VALIDATION_REPORT_SPECIFICATION_SIP25_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_SIP25_CARDINALITY;
    }
    if (VALIDATION_REPORT_SPECIFICATION_SIP26_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_SIP26_CARDINALITY;
    }
    if (VALIDATION_REPORT_SPECIFICATION_SIP27_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_SIP27_CARDINALITY;
    }
    if (VALIDATION_REPORT_SPECIFICATION_SIP28_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_SIP28_CARDINALITY;
    }
    if (VALIDATION_REPORT_SPECIFICATION_SIP29_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_SIP29_CARDINALITY;
    }
    if (VALIDATION_REPORT_SPECIFICATION_SIP30_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_SIP30_CARDINALITY;
    }
    if (VALIDATION_REPORT_SPECIFICATION_SIP31_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_SIP31_CARDINALITY;
    }
    if (VALIDATION_REPORT_SPECIFICATION_SIP32_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_SIP32_CARDINALITY;
    }
    if (VALIDATION_REPORT_SPECIFICATION_SIP33_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_SIP33_CARDINALITY;
    }
    if (VALIDATION_REPORT_SPECIFICATION_SIP34_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_SIP34_CARDINALITY;
    }
    if (VALIDATION_REPORT_SPECIFICATION_SIP35_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_SIP35_CARDINALITY;
    }
    return NOT_DEFINED;
  }

  /**
   * Get the level of the requirement of given id.
   *
   * @param id
   *          {@link String} id of the requirement
   * @return the {@link String} level of the requirement.
   */
  public static String getSpecificationLevel(final String id) {
    if (VALIDATION_REPORT_SPECIFICATION_SIP1_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_SIP1_LEVEL;
    }
    if (VALIDATION_REPORT_SPECIFICATION_SIP2_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_SIP2_LEVEL;
    }
    if (VALIDATION_REPORT_SPECIFICATION_SIP3_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_SIP3_LEVEL;
    }
    if (VALIDATION_REPORT_SPECIFICATION_SIP4_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_SIP4_LEVEL;
    }
    if (VALIDATION_REPORT_SPECIFICATION_SIP5_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_SIP5_LEVEL;
    }
    if (VALIDATION_REPORT_SPECIFICATION_SIP6_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_SIP6_LEVEL;
    }
    if (VALIDATION_REPORT_SPECIFICATION_SIP7_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_SIP7_LEVEL;
    }
    if (VALIDATION_REPORT_SPECIFICATION_SIP8_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_SIP8_LEVEL;
    }
    if (VALIDATION_REPORT_SPECIFICATION_SIP9_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_SIP9_LEVEL;
    }
    if (VALIDATION_REPORT_SPECIFICATION_SIP10_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_SIP10_LEVEL;
    }
    if (VALIDATION_REPORT_SPECIFICATION_SIP11_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_SIP11_LEVEL;
    }
    if (VALIDATION_REPORT_SPECIFICATION_SIP12_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_SIP12_LEVEL;
    }
    if (VALIDATION_REPORT_SPECIFICATION_SIP13_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_SIP13_LEVEL;
    }
    if (VALIDATION_REPORT_SPECIFICATION_SIP14_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_SIP14_LEVEL;
    }
    if (VALIDATION_REPORT_SPECIFICATION_SIP15_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_SIP15_LEVEL;
    }
    if (VALIDATION_REPORT_SPECIFICATION_SIP16_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_SIP16_LEVEL;
    }
    if (VALIDATION_REPORT_SPECIFICATION_SIP17_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_SIP17_LEVEL;
    }
    if (VALIDATION_REPORT_SPECIFICATION_SIP18_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_SIP18_LEVEL;
    }
    if (VALIDATION_REPORT_SPECIFICATION_SIP19_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_SIP19_LEVEL;
    }
    if (VALIDATION_REPORT_SPECIFICATION_SIP20_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_SIP20_LEVEL;
    }
    if (VALIDATION_REPORT_SPECIFICATION_SIP21_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_SIP21_LEVEL;
    }
    if (VALIDATION_REPORT_SPECIFICATION_SIP22_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_SIP22_LEVEL;
    }
    if (VALIDATION_REPORT_SPECIFICATION_SIP23_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_SIP23_LEVEL;
    }
    if (VALIDATION_REPORT_SPECIFICATION_SIP24_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_SIP24_LEVEL;
    }
    if (VALIDATION_REPORT_SPECIFICATION_SIP25_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_SIP25_LEVEL;
    }
    if (VALIDATION_REPORT_SPECIFICATION_SIP26_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_SIP26_LEVEL;
    }
    if (VALIDATION_REPORT_SPECIFICATION_SIP27_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_SIP27_LEVEL;
    }
    if (VALIDATION_REPORT_SPECIFICATION_SIP28_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_SIP28_LEVEL;
    }
    if (VALIDATION_REPORT_SPECIFICATION_SIP29_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_SIP29_LEVEL;
    }
    if (VALIDATION_REPORT_SPECIFICATION_SIP30_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_SIP30_LEVEL;
    }
    if (VALIDATION_REPORT_SPECIFICATION_SIP31_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_SIP31_LEVEL;
    }
    if (VALIDATION_REPORT_SPECIFICATION_SIP32_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_SIP32_LEVEL;
    }
    if (VALIDATION_REPORT_SPECIFICATION_SIP33_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_SIP33_LEVEL;
    }
    if (VALIDATION_REPORT_SPECIFICATION_SIP34_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_SIP34_LEVEL;
    }
    if (VALIDATION_REPORT_SPECIFICATION_SIP35_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_SIP35_LEVEL;
    }
    return NOT_DEFINED;
  }
}
