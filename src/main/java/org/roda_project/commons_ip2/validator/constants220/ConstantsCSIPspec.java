package org.roda_project.commons_ip2.validator.constants220;

/** {@author João Gomes <jgomes@keep.pt>}. */
public final class ConstantsCSIPspec {
  private ConstantsCSIPspec() {
    // do nothing
  }

  /* Use of the METS root element (element mets) */

  /* CSIPSTR0 - Invalid METS Schema */
  /**
   * Constant specification id "CSIP0".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP0_ID = "CSIP0";

  /**
   * Constant specification name for id "CSIP0".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP0_NAME = "METS Schema";

  /**
   * Constant specification location for id "CSIP0".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP0_LOCATION = "";

  /**
   * Constant specification description for id "CSIP0".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP0_DESCRIPTION = "Validation of METS Schema";

  /**
   * Constant specification cardinality for id "CSIP0".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP0_CARDINALITY = "";

  /**
   * Constant specification level for id "CSIP0".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP0_LEVEL = "MUST";

  /* CSIPSTR1 */

  /**
   * Constant specification id "CSIPSTR1".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIPSTR1_ID = "CSIPSTR1";

  /**
   * Constant specification name for id "CSIPSTR1".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIPSTR1_NAME = "CSIP Information Package "
    + "folder structure";

  /**
   * Constant specification location for id "CSIPSTR1".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIPSTR1_LOCATION = "";

  /**
   * Constant specification description for id "CSIPSTR1".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIPSTR1_DESCRIPTION = "Any Information Package "
    + "MUST be included within a single physical root folder "
    + "(known as the “Information Package root folder”). For packages presented "
    + "in an archive format, see CSIPSTR3, the archive MUST unpack to a single root folder.";

  /**
   * Constant specification cardinality for id "CSIPSTR1".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIPSTR1_CARDINALITY = "";

  /**
   * Constant specification level for id "CSIPSTR1".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIPSTR1_LEVEL = "MUST";

  /* CSIPSTR2 */

  /**
   * Constant specification id "CSIPSTR2".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIPSTR2_ID = "CSIPSTR2";

  /**
   * Constant specification name for id "CSIPSTR2".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIPSTR2_NAME = "CSIP Information "
    + "Package folder structure";

  /**
   * Constant specification location for id "CSIPSTR2".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIPSTR2_LOCATION = "";

  /**
   * Constant specification description for id "CSIPSTR2".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIPSTR2_DESCRIPTION = "The Information Package root "
    + "folder SHOULD be named with the ID or name "
    + "of the Information Package, that is the value of the package METS.xml’s "
    + "root <mets> element’s @OBJID attribute.";

  /**
   * Constant specification cardinality for id "CSIPSTR2".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIPSTR2_CARDINALITY = "";

  /**
   * Constant specification level for id "CSIPSTR2".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIPSTR2_LEVEL = "SHOULD";

  /* CSIPSTR3 */

  /**
   * Constant specification id "CSIPSTR3".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIPSTR3_ID = "CSIPSTR3";

  /**
   * Constant specification name for id "CSIPSTR3".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIPSTR3_NAME = "CSIP Information Package "
    + "folder structure";

  /**
   * Constant specification location for id "CSIPSTR3".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIPSTR3_LOCATION = "";

  /**
   * Constant specification description for id "CSIPSTR3".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIPSTR3_DESCRIPTION = "The Information Package "
    + "root folder MAY be compressed " + "(for example by using TAR or ZIP). "
    + "Which specific compression format to use " + "needs to be stated in the Submission Agreement.";

  /**
   * Constant specification cardinality for id "CSIPSTR3".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIPSTR3_CARDINALITY = "";

  /**
   * Constant specification level for id "CSIPSTR3".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIPSTR3_LEVEL = "MAY";

  /* CSIPSTR4 */

  /**
   * Constant specification id "CSIPSTR4".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIPSTR4_ID = "CSIPSTR4";

  /**
   * Constant specification name for id "CSIPSTR4".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIPSTR4_NAME = "CSIP Information "
    + "Package folder structure";

  /**
   * Constant specification location for id "CSIPSTR4".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIPSTR4_LOCATION = "";

  /**
   * Constant specification description for id "CSIPSTR4".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIPSTR4_DESCRIPTION = "The Information Package root "
    + "folder MUST include a file named METS.xml. " + "This file MUST contain metadata that identifies the package, "
    + "provides a high-level package description, "
    + "and describes its structure, including pointers to constituent representations.";

  /**
   * Constant specification cardinality for id "CSIPSTR4".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIPSTR4_CARDINALITY = "";

  /**
   * Constant specification level for id "CSIPSTR4".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIPSTR4_LEVEL = "MUST";

  /* CSIPSTR5 */

  /**
   * Constant specification id "CSIPSTR5".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIPSTR5_ID = "CSIPSTR5";

  /**
   * Constant specification name for id "CSIPSTR5".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIPSTR5_NAME = "CSIP Information "
    + "Package folder structure";

  /**
   * Constant specification location for id "CSIPSTR5".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIPSTR5_LOCATION = "";

  /**
   * Constant specification description for id "CSIPSTR5".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIPSTR5_DESCRIPTION = "The Information "
    + "Package root folder " + "SHOULD include a folder named metadata, "
    + "which SHOULD include metadata relevant to the whole package.";

  /**
   * Constant specification cardinality for id "CSIPSTR5".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIPSTR5_CARDINALITY = "";

  /**
   * Constant specification level for id "CSIPSTR5".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIPSTR5_LEVEL = "SHOULD";

  /* CSIPSTR6 */

  /**
   * Constant specification id "CSIPSTR6".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIPSTR6_ID = "CSIPSTR6";

  /**
   * Constant specification name for id "CSIPSTR6".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIPSTR6_NAME = "CSIP Information "
    + "Package folder structure";

  /**
   * Constant specification location for id "CSIPSTR6".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIPSTR6_LOCATION = "";

  /**
   * Constant specification description for id "CSIPSTR6".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIPSTR6_DESCRIPTION = "If preservation "
    + "metadata are available, they " + "SHOULD be included in sub-folder preservation.";

  /**
   * Constant specification cardinality for id "CSIPSTR6".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIPSTR6_CARDINALITY = "";

  /**
   * Constant specification level for id "CSIPSTR6".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIPSTR6_LEVEL = "SHOULD";

  /* CSIPSTR7 */

  /**
   * Constant specification id "CSIPSTR7".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIPSTR7_ID = "CSIPSTR7";

  /**
   * Constant specification name for id "CSIPSTR7".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIPSTR7_NAME = "CSIP Information "
    + "Package folder structure";

  /**
   * Constant specification location for id "CSIPSTR7".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIPSTR7_LOCATION = "";

  /**
   * Constant specification description for id "CSIPSTR7".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIPSTR7_DESCRIPTION = "If descriptive "
    + "metadata are available, " + "they SHOULD be included in sub-folder descriptive.";

  /**
   * Constant specification cardinality for id "CSIPSTR7".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIPSTR7_CARDINALITY = "";

  /**
   * Constant specification level for id "CSIPSTR7".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIPSTR7_LEVEL = "SHOULD";

  /* CSIPSTR8 */

  /**
   * Constant specification id "CSIPSTR8".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIPSTR8_ID = "CSIPSTR8";

  /**
   * Constant specification name for id "CSIPSTR8".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIPSTR8_NAME = "CSIP Information "
    + "Package folder structure";

  /**
   * Constant specification location for id "CSIPSTR8".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIPSTR8_LOCATION = "";

  /**
   * Constant specification description for id "CSIPSTR8".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIPSTR8_DESCRIPTION = "If any other metadata "
    + "are available, " + "they MAY be included in "
    + "separate sub-folders, for example an additional folder named other.";

  /**
   * Constant specification cardinality for id "CSIPSTR8".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIPSTR8_CARDINALITY = "";

  /**
   * Constant specification level for id "CSIPSTR8".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIPSTR8_LEVEL = "MAY";

  /* CSIPSTR9 */

  /**
   * Constant specification id "CSIPSTR9".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIPSTR9_ID = "CSIPSTR9";

  /**
   * Constant specification name for id "CSIPSTR9".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIPSTR9_NAME = "CSIP Information "
    + "Package folder structure";

  /**
   * Constant specification location for id "CSIPSTR9".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIPSTR9_LOCATION = "";

  /**
   * Constant specification description for id "CSIPSTR9".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIPSTR9_DESCRIPTION = "The Information Package folder "
    + "SHOULD include a folder named representations.";

  /**
   * Constant specification cardinality for id "CSIPSTR9".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIPSTR9_CARDINALITY = "";

  /**
   * Constant specification level for id "CSIPSTR9".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIPSTR9_LEVEL = "SHOULD";

  /* CSIPSTR10 */

  /**
   * Constant specification id "CSIPSTR10".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIPSTR10_ID = "CSIPSTR10";

  /**
   * Constant specification name for id "CSIPSTR10".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIPSTR10_NAME = "CSIP Information "
    + "Package folder structure";

  /**
   * Constant specification location for id "CSIPSTR10".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIPSTR10_LOCATION = "";

  /**
   * Constant specification description for id "CSIPSTR10".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIPSTR10_DESCRIPTION = "The representations folder "
    + "SHOULD include a sub-folder " + "for each individual representation "
    + "(i.e. the “representation folder”). Each representation folder should "
    + "have a string name that is unique within the package scope. "
    + "For example the name of the representation and/or its creation date might "
    + "be good candidates as a representation sub-folder name.";

  /**
   * Constant specification cardinality for id "CSIP10".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIPSTR10_CARDINALITY = "";

  /**
   * Constant specification level for id "CSIP10".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIPSTR10_LEVEL = "SHOULD";

  /* CSIPSTR11 */

  /**
   * Constant specification id "CSIPSTR11".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIPSTR11_ID = "CSIPSTR11";

  /**
   * Constant specification name for id "CSIPSTR11".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIPSTR11_NAME = "CSIP Information "
    + "Package folder structure";

  /**
   * Constant specification location for id "CSIPSTR11".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIPSTR11_LOCATION = "";

  /**
   * Constant specification description for id "CSIPSTR11".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIPSTR11_DESCRIPTION = "The representation folder "
    + "SHOULD include a sub-folder " + "named data which MAY include all data constituting the representation.";

  /**
   * Constant specification cardinality for id "CSIPSTR11".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIPSTR11_CARDINALITY = "";

  /**
   * Constant specification level for id "CSIPSTR11".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIPSTR11_LEVEL = "SHOULD";

  /* CSIPSTR12 */

  /**
   * Constant specification id "CSIPSTR12".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIPSTR12_ID = "CSIPSTR12";

  /**
   * Constant specification name for id "CSIPSTR12".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIPSTR12_NAME = "CSIP Information "
    + "Package folder structure";

  /**
   * Constant specification location for id "CSIPSTR12".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIPSTR12_LOCATION = "";

  /**
   * Constant specification description for id "CSIPSTR12".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIPSTR12_DESCRIPTION = "The representation folder "
    + "SHOULD include a metadata " + "file named METS.xml which includes information about the identity and structure "
    + "of the representation and its components. " + "The recommended best practice is to always have a "
    + "METS.xml in the representation folder.";

  /**
   * Constant specification cardinality for id "CSIPSTR12".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIPSTR12_CARDINALITY = "";

  /**
   * Constant specification level for id "CSIPSTR12".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIPSTR12_LEVEL = "SHOULD";

  /* CSIPSTR13 */

  /**
   * Constant specification id "CSIPSTR13".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIPSTR13_ID = "CSIPSTR13";

  /**
   * Constant specification name for id "CSIPSTR13".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIPSTR13_NAME = "CSIP Information "
    + "Package folder structure";

  /**
   * Constant specification location for id "CSIPSTR13".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIPSTR13_LOCATION = "";

  /**
   * Constant specification description for id "CSIPSTR13".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIPSTR13_DESCRIPTION = "The representation folder "
    + "SHOULD include a sub-folder "
    + "named metadata which MAY include all metadata about the specific representation.";

  /**
   * Constant specification cardinality for id "CSIPSTR13".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIPSTR13_CARDINALITY = "";

  /**
   * Constant specification level for id "CSIPSTR13".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIPSTR13_LEVEL = "SHOULD";

  /* CSIPSTR14 */

  /**
   * Constant specification id "CSIPSTR14".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIPSTR14_ID = "CSIPSTR14";

  /**
   * Constant specification name for id "CSIPSTR14".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIPSTR14_NAME = "CSIP Information "
    + "Package folder structure";

  /**
   * Constant specification location for id "CSIPSTR14".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIPSTR14_LOCATION = "";

  /**
   * Constant specification description for id "CSIPSTR14".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIPSTR14_DESCRIPTION = "The Information Package MAY be "
    + "extended with additional sub-folders.";

  /**
   * Constant specification cardinality for id "CSIPSTR14".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIPSTR14_CARDINALITY = "";

  /**
   * Constant specification level for id "CSIPSTR14".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIPSTR14_LEVEL = "MAY";

  /* CSIPSTR15 */

  /**
   * Constant specification id "CSIPSTR15".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIPSTR15_ID = "CSIPSTR15";

  /**
   * Constant specification name for id "CSIPSTR15".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIPSTR15_NAME = "CSIP Information "
    + "Package folder structure";

  /**
   * Constant specification location for id "CSIPSTR15".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIPSTR15_LOCATION = "";

  /**
   * Constant specification description for id "CSIPSTR15".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIPSTR15_DESCRIPTION = "We recommend including all "
    + "XML schema documents " + "for any structured metadata within package. "
    + "These schema documents SHOULD be placed in a sub-folder " + "called schemas within the Information Package "
    + "root folder and/or the representation folder.";

  /**
   * Constant specification cardinality for id "CSIPSTR15".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIPSTR15_CARDINALITY = "";

  /**
   * Constant specification level for id "CSIPSTR15".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIPSTR15_LEVEL = "SHOULD";

  /* CSIPSTR16 */

  /**
   * Constant specification id "CSIPSTR16".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIPSTR16_ID = "CSIPSTR16";

  /**
   * Constant specification name for id "CSIPSTR16".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIPSTR16_NAME = "CSIP Information "
    + "Package folder structure";

  /**
   * Constant specification location for id "CSIPSTR16".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIPSTR16_LOCATION = "";

  /**
   * Constant specification description for id "CSIPSTR16".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIPSTR16_DESCRIPTION = "We recommend including any "
    + "supplementary documentation for the " + "package or a specific representation within the package. "
    + "Supplementary documentation SHOULD be placed in a sub-folder called "
    + "documentation within the Information Package root " + "folder and/or the representation folder.";

  /**
   * Constant specification cardinality for id "CSIPSTR16".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIPSTR16_CARDINALITY = "";

  /**
   * Constant specification level for id "CSIPSTR16".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIPSTR16_LEVEL = "SHOULD";

  /* CSIP1 */

  /**
   * Constant specification id "CSIP1".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP1_ID = "CSIP1";

  /**
   * Constant specification name for id "CSIP1".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP1_NAME = "Package Identifier";

  /**
   * Constant specification location for id "CSIP1".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP1_LOCATION = "mets/@OBJID";

  /**
   * Constant specification description for id "CSIP1".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP1_DESCRIPTION = "The mets/@OBJID attribute is mandatory, "
    + "its value is a string identifier for the METS document. " + "For the package METS document following CSIPSTR2, "
    + "this should be the name/ID of the package, i.e. the name of the "
    + "package root folder. For a representation level METS document this "
    + "value records the name/ID of the representation following CSIPSTR10, "
    + "i.e. the name of the top-level representation folder. Observe that the "
    + "name/ID used in the mets/@OBJID attribute might not be possible to directly "
    + "transform into a folder name due to limitations in the used file system. "
    + "If there are illegal characters present there are different character mapping "
    + "techniques to use to transform the mets/@OBJID into a valid folder name.";


  /**
   * Constant specification cardinality for id "CSIP1".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP1_CARDINALITY = "1..1";

  /**
   * Constant specification level for id "CSIP1".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP1_LEVEL = "MUST";

  /* CSIP2 */

  /**
   * Constant specification id "CSIP2".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP2_ID = "CSIP2";

  /**
   * Constant specification name for id "CSIP2".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP2_NAME = "Content Category";

  /**
   * Constant specification location for id "CSIP2".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP2_LOCATION = "mets/@TYPE";

  /**
   * Constant specification description for id "CSIP2".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP2_DESCRIPTION = "The mets/@TYPE attribute MUST be "
    + "used to declare the category of the "
    + "content held in the package, e.g. book, journal, stereograph, video, etc.. "
    + "Legal values are defined in a fixed vocabulary. When the content category "
    + "used falls outside of the defined vocabulary the mets/@TYPE value must be "
    + "set to “OTHER” and the specific value declared in mets/@csip:OTHERTYPE. "
    + "The vocabulary will develop under the curation of the DILCIS Board as "
    + " additional content information type specifications are produced.";

  /**
   * Constant specification cardinality for id "CSIP2".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP2_CARDINALITY = "0..1";

  /**
   * Constant specification level for id "CSIP2".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP2_LEVEL = "MUST";

  /* CSIP 3 */
  /**
   * Constant specification id "CSIP3".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP3_ID = "CSIP3";

  /**
   * Constant specification name for id "CSIP3".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP3_NAME = "Other Content Category";

  /**
   * Constant specification location for id "CSIP3".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP3_LOCATION = "mets[@TYPE=’OTHER’]/@csip:OTHERTYPE";

  /**
   * Constant specification description for id "CSIP3".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP3_DESCRIPTION = "When the mets/@TYPE attribute "
    + "has the value “OTHER” the mets/@csip:OTHERTYPE attribute MUST be used to declare "
    + "the content category of the package/representation. The value can "
    + "either be “OTHER” or any other string that are not present in the "
    + "vocabulary used in the mets/@TYPE attribute.";

  /**
   * Constant specification cardinality for id "CSIP3".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP3_CARDINALITY = "0..1";

  /**
   * Constant specification level for id "CSIP3".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP3_LEVEL = "SHOULD";

  /* CSIP 4 */

  /**
   * Constant specification id "CSIP4".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP4_ID = "CSIP4";

  /**
   * Constant specification name for id "CSIP4".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP4_NAME = "Content Information Type Specification";

  /**
   * Constant specification location for id "CSIP4".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP4_LOCATION = "mets/@csip:CONTENTINFORMATIONTYPE";

  /**
   * Constant specification description for id "CSIP4".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP4_DESCRIPTION = "Used to declare the Content " +
    "Information Type Specification used when creating the package. " +
    "Legal values are defined in a fixed vocabulary. The attribute " +
    "is mandatory for representation level METS documents. The " +
    "vocabulary will evolve under the care of the DILCIS Board " +
    "as additional Content Information Type Specifications are developed.";

  /**
   * Constant specification cardinality for id "CSIP4".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP4_CARDINALITY = "0..1";

  /**
   * Constant specification level for id "CSIP4".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP4_LEVEL = "SHOULD";

  /* CSIP 5 */

  /**
   * Constant specification id "CSIP5".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP5_ID = "CSIP5";

  /**
   * Constant specification name for id "CSIP5".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP5_NAME = "Other Content "
    + "Information Type Specification";

  /**
   * Constant specification location for id "CSIP5".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP5_LOCATION = "mets[@csip:"
    + "CONTENTINFORMATIONTYPE=’OTHER’]" + "/@csip:OTHERCONTENTINFORMATIONTYPE";

  /**
   * Constant specification description for id "CSIP5".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP5_DESCRIPTION = "When the mets"
    + "/@csip:CONTENTINFORMATIONTYPE has the value " + "'OTHER' the mets/@csip:OTHERCONTENTINFORMATIONTYPE must state "
    + "the content information type. ";

  /**
   * Constant specification cardinality for id "CSIP5".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP5_CARDINALITY = "0..1";

  /**
   * Constant specification level for id "CSIP5".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP5_LEVEL = "MAY";

  /* CSIP 6 */

  /**
   * Constant specification id "CSIP6".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP6_ID = "CSIP6";

  /**
   * Constant specification name for id "CSIP6".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP6_NAME = "METS Profile";

  /**
   * Constant specification location for id "CSIP6".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP6_LOCATION = "mets/@PROFILE";

  /**
   * Constant specification description for id "CSIP6".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP6_DESCRIPTION = "The URL of the METS profile that "
    + "the information package conforms with. ";

  /**
   * Constant specification cardinality for id "CSIP6".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP6_CARDINALITY = "1..1";

  /**
   * Constant specification level for id "CSIP6".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP6_LEVEL = "MUST";

  /* Use of the METS header (element metsHdr) */
  /* CSIP117 */

  /**
   * Constant specification id "CSIP117".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP117_ID = "CSIP117";

  /**
   * Constant specification name for id "CSIP117".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP117_NAME = "Package header";

  /**
   * Constant specification location for id "CSIP117".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP117_LOCATION = "mets/metsHdr";

  /**
   * Constant specification description for id "CSIP117".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP117_DESCRIPTION = "General element for "
    + "describing the package.";

  /**
   * Constant specification cardinality for id "CSIP117".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP117_CARDINALITY = "1..1";

  /**
   * Constant specification level for id "CSIP117".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP117_LEVEL = "MUST";

  /* CSIP7 */

  /**
   * Constant specification id "CSIP7".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP7_ID = "CSIP7";

  /**
   * Constant specification name for id "CSIP7".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP7_NAME = "Package creation date";

  /**
   * Constant specification location for id "CSIP7".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP7_LOCATION = "mets/metsHdr/@CREATEDATE";

  /**
   * Constant specification description for id "CSIP7".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP7_DESCRIPTION = "mets/metsHdr/@CREATEDATE records the "
    + "date the package was created.";

  /**
   * Constant specification cardinality for id "CSIP7".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP7_CARDINALITY = "1..1";

  /**
   * Constant specification level for id "CSIP7".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP7_LEVEL = "MUST";

  /* CSIP8 */

  /**
   * Constant specification id "CSIP8".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP8_ID = "CSIP8";

  /**
   * Constant specification name for id "CSIP8".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP8_NAME = "Package last modification date";

  /**
   * Constant specification location for id "CSIP8".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP8_LOCATION = "mets/metsHdr/@LASTMODDATE";

  /**
   * Constant specification description for id "CSIP8".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP8_DESCRIPTION = "mets/metsHdr/@LASTMODDATE "
    + "is mandatory " + "when the package has been modified.";

  /**
   * Constant specification cardinality for id "CSIP8".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP8_CARDINALITY = "0..1";

  /**
   * Constant specification level for id "CSIP8".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP8_LEVEL = "SHOULD";

  /* CSIP9 */

  /**
   * Constant specification id "CSIP9".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP9_ID = "CSIP9";

  /**
   * Constant specification name for id "CSIP9".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP9_NAME = "OAIS Package type information";

  /**
   * Constant specification location for id "CSIP9".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP9_LOCATION = "mets/metsHdr/@csip:OAISPACKAGETYPE";

  /**
   * Constant specification description for id "CSIP9".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP9_DESCRIPTION = "mets/metsHdr/@csip:OAISPACKAGETYPE "
    + "is an additional " + "CSIP attribute that declares the type of the IP.";

  /**
   * Constant specification cardinality for id "CSIP9".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP9_CARDINALITY = "1..1";

  /**
   * Constant specification level for id "CSIP9".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP9_LEVEL = "MUST";

  /* CSIP10 */

  /**
   * Constant specification id "CSIP10".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP10_ID = "CSIP10";

  /**
   * Constant specification name for id "CSIP10".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP10_NAME = "Agent";

  /**
   * Constant specification location for id "CSIP10".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP10_LOCATION = "mets/metsHdr/agent";

  /**
   * Constant specification description for id "CSIP10".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP10_DESCRIPTION = "A mandatory agent element records " +
    "the software used to create the package. Other uses " +
    "of agents may be described in any local implementations " +
    "that extend the profile. It is possible according to METS " +
    "to use more note elements but in this implementation " +
    "for this agent describing the software used it is one " +
    "mandatory note for describing the version of the software.";

  /**
   * Constant specification cardinality for id "CSIP10".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP10_CARDINALITY = "1..n";

  /**
   * Constant specification level for id "CSIP10".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP10_LEVEL = "MUST";

  /* CSIP11 */

  /**
   * Constant specification id "CSIP11".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP11_ID = "CSIP11";

  /**
   * Constant specification name for id "CSIP11".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP11_NAME = "Agent role";

  /**
   * Constant specification location for id "CSIP11".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP11_LOCATION = "mets/metsHdr/agent[@ROLE=’CREATOR’]";

  /**
   * Constant specification description for id "CSIP11".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP11_DESCRIPTION = "The mandatory agent element MUST "
    + "have a @ROLE attribute with the value 'CREATOR'";

  /**
   * Constant specification cardinality for id "CSIP11".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP11_CARDINALITY = "1..1";

  /**
   * Constant specification level for id "CSIP11".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP11_LEVEL = "MUST";

  /* CSIP12 */

  /**
   * Constant specification id "CSIP12".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP12_ID = "CSIP12";

  /**
   * Constant specification name for id "CSIP12".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP12_NAME = "Agent type";

  /**
   * Constant specification location for id "CSIP12".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP12_LOCATION = "mets/metsHdr/agent[@TYPE=’OTHER’]";

  /**
   * Constant specification description for id "CSIP12".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP12_DESCRIPTION = "The mandatory agent element "
    + "MUST have a @TYPE attribute with the value 'OTHER'.";

  /**
   * Constant specification cardinality for id "CSIP12".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP12_CARDINALITY = "1..1";

  /**
   * Constant specification level for id "CSIP12".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP12_LEVEL = "MUST";

  /* CSIP13 */

  /**
   * Constant specification id "CSIP13".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP13_ID = "CSIP13";

  /**
   * Constant specification name for id "CSIP13".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP13_NAME = "Agent other type";

  /**
   * Constant specification location for id "CSIP13".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP13_LOCATION = "mets/metsHdr/agent"
    + "[@OTHERTYPE=’SOFTWARE’]";

  /**
   * Constant specification description for id "CSIP13".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP13_DESCRIPTION = "The mandatory agent element MUST "
    + "have a @OTHERTYPE attribute with the value “SOFTWARE”.";

  /**
   * Constant specification cardinality for id "CSIP13".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP13_CARDINALITY = "1..1";

  /**
   * Constant specification level for id "CSIP13".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP13_LEVEL = "MUST";

  /* CSIP14 */

  /**
   * Constant specification id "CSIP14".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP14_ID = "CSIP14";

  /**
   * Constant specification name for id "CSIP14".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP14_NAME = "Agent name";

  /**
   * Constant specification location for id "CSIP14".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP14_LOCATION = "mets/metsHdr/agent/name";

  /**
   * Constant specification description for id "CSIP14".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP14_DESCRIPTION = "The mandatory agent’s "
    + "name element records " + "the name of the software tool used to create the IP.";

  /**
   * Constant specification cardinality for id "CSIP14".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP14_CARDINALITY = "1..1";

  /**
   * Constant specification level for id "CSIP14".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP14_LEVEL = "MUST";

  /* CSIP15 */

  /**
   * Constant specification id "CSIP15".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP15_ID = "CSIP15";

  /**
   * Constant specification name for id "CSIP15".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP15_NAME = "Agent additional information";

  /**
   * Constant specification location for id "CSIP15".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP15_LOCATION = "mets/metsHdr/agent/note";

  /**
   * Constant specification description for id "CSIP15".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP15_DESCRIPTION = "The mandatory agent’s note element "
    + "records the version of the tool used to create the IP.";

  /**
   * Constant specification cardinality for id "CSIP15".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP15_CARDINALITY = "1..1";

  /**
   * Constant specification level for id "CSIP15".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP15_LEVEL = "MUST";

  /* CSIP16 */

  /**
   * Constant specification id "CSIP16".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP16_ID = "CSIP16";

  /**
   * Constant specification name for id "CSIP16".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP16_NAME = "Classification of the "
    + "agent additional information";

  /**
   * Constant specification location for id "CSIP16".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP16_LOCATION = "mets/metsHdr/agent/note"
    + "[@csip:NOTETYPE=’SOFTWARE VERSION’]";

  /**
   * Constant specification description for id "CSIP16".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP16_DESCRIPTION = "The mandatory agent "
    + "element’s note child has a " + "@csip:NOTETYPE attribute with a fixed value of “SOFTWARE VERSION”.";

  /**
   * Constant specification cardinality for id "CSIP16".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP16_CARDINALITY = "1..1";

  /**
   * Constant specification level for id "CSIP16".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP16_LEVEL = "MUST";

  /* Use of the METS descriptive metadata section (element dmdSec) */
  /* CSIP17 */

  /**
   * Constant specification id "CSIP17".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP17_ID = "CSIP17";

  /**
   * Constant specification name for id "CSIP17".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP17_NAME = "Descriptive metadata";

  /**
   * Constant specification location for id "CSIP17".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP17_LOCATION = "mets/dmdSec";

  /**
   * Constant specification description for id "CSIP17".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP17_DESCRIPTION = "Must be used if descriptive "
    + "metadata " + "for the package content is available. "
    + "Each descriptive metadata section ( <dmdSec> ) contains a single description "
    + "and must be repeated for multiple descriptions, when available. It is "
    + "possible to transfer metadata in a package using just the descriptive "
    + "metadata section and/or administrative metadata section.";

  /**
   * Constant specification cardinality for id "CSIP17".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP17_CARDINALITY = "0..n";

  /**
   * Constant specification level for id "CSIP17".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP17_LEVEL = "SHOULD";

  /* CSIP18 */

  /**
   * Constant specification id "CSIP18".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP18_ID = "CSIP18";

  /**
   * Constant specification name for id "CSIP18".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP18_NAME = "Descriptive metadata identifier";

  /**
   * Constant specification location for id "CSIP18".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP18_LOCATION = "mets/dmdSec/@ID";

  /**
   * Constant specification description for id "CSIP18".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP18_DESCRIPTION = "An xml:id identifier for the " +
    "descriptive metadata section (<dmdSec>) used for internal " +
    "references within the XML-document. It must be unique within the XML-document.";
  /**
   * Constant specification cardinality for id "CSIP18".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP18_CARDINALITY = "1..1";

  /**
   * Constant specification leve for id "CSIP18".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP18_LEVEL = "MUST";

  /* CSIP19 */

  /**
   * Constant specification id "CSIP19".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP19_ID = "CSIP19";

  /**
   * Constant specification name for id "CSIP19".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP19_NAME = "Descriptive metadata creation date";

  /**
   * Constant specification location for id "CSIP19".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP19_LOCATION = "mets/dmdSec/@CREATED";

  /**
   * Constant specification description for id "CSIP19".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP19_DESCRIPTION = "Creation date of the "
    + "descriptive metadata in this section.";

  /**
   * Constant specification cardinality for id "CSIP19".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP19_CARDINALITY = "1..1";

  /**
   * Constant specification level for id "CSIP19".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP19_LEVEL = "MUST";

  /* CSIP20 */

  /**
   * Constant specification id "CSIP20".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP20_ID = "CSIP20";

  /**
   * Constant specification name for id "CSIP20".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP20_NAME = "Status of the descriptive metadata";

  /**
   * Constant specification location for id "CSIP20".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP20_LOCATION = "mets/dmdSec/@STATUS";

  /**
   * Constant specification description for id "CSIP17".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP20_DESCRIPTION = "Indicates the status of the "
    + "package using a fixed vocabulary.";

  /**
   * Constant specification cardinality for id "CSIP20".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP20_CARDINALITY = "0..1";

  /**
   * Constant specification level for id "CSIP20".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP20_LEVEL = "SHOULD";

  /* CSIP21 */

  /**
   * Constant specification id "CSIP21".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP21_ID = "CSIP21";

  /**
   * Constant specification name for id "CSIP21".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP21_NAME = "Reference to the "
    + "document with the descriptive metadata";

  /**
   * Constant specification location for id "CSIP21".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP21_LOCATION = "mets/dmdSec/mdRef";

  /**
   * Constant specification description for id "CSIP21".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP21_DESCRIPTION = "Reference to the "
    + "descriptive metadata " + "file located in the “metadata” section of the IP.";

  /**
   * Constant specification cardinality for id "CSIP21".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP21_CARDINALITY = "0..1";

  /**
   * Constant specification level for id "CSIP21".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP21_LEVEL = "SHOULD";

  /* CSIP22 */

  /**
   * Constant specification id "CSIP22".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP22_ID = "CSIP22";

  /**
   * Constant specification name for id "CSIP22".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP22_NAME = "Type of locator";

  /**
   * Constant specification location for id "CSIP22".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP22_LOCATION = "mets/dmdSec/mdRef";

  /**
   * Constant specification description for id "CSIP22".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP22_DESCRIPTION = "The locator type is always used "
    + "with the value “URL” from the vocabulary in the attribute.";

  /**
   * Constant specification cardinality for id "CSIP22".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP22_CARDINALITY = "1..1";

  /**
   * Constant specification level for id "CSIP22".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP22_LEVEL = "MUST";

  /* CSIP23 */

  /**
   * Constant specification id "CSIP23".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP23_ID = "CSIP23";

  /**
   * Constant specification name for id "CSIP23".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP23_NAME = "Type of link";

  /**
   * Constant specification location for id "CSIP23".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP23_LOCATION = "mets/dmdSec/mdRef"
    + "[@xlink:type=’simple’]";

  /**
   * Constant specification description for id "CSIP23".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP23_DESCRIPTION = "Attribute used with "
    + "the value “simple”. " + "Value list is maintained by the xlink standard.";

  /**
   * Constant specification cardinality for id "CSIP23".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP23_CARDINALITY = "1..1";

  /**
   * Constant specification level for id "CSIP23".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP23_LEVEL = "MUST";

  /* CSIP24 */

  /**
   * Constant specification id "CSIP24".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP24_ID = "CSIP24";

  /**
   * Constant specification name for id "CSIP24".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP24_NAME = "Resource location";

  /**
   * Constant specification location for id "CSIP24".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP24_LOCATION = "mets/dmdSec/mdRef/@xlink:href";

  /**
   * Constant specification description for id "CSIP24".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP24_DESCRIPTION = "The actual location of "
    + "the resource. " + "This specification recommends recording a URL type filepath in this attribute.";

  /**
   * Constant specification cardinality for id "CSIP24".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP24_CARDINALITY = "1..1";

  /**
   * Constant specification level for id "CSIP24".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP24_LEVEL = "MUST";

  /* CSIP25 */

  /**
   * Constant specification id "CSIP25".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP25_ID = "CSIP25";

  /**
   * Constant specification name for id "CSIP25".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP25_NAME = "Type of metadata";

  /**
   * Constant specification location for id "CSIP25".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP25_LOCATION = "mets/dmdSec/mdRef/@MDTYPE";

  /**
   * Constant specification description for id "CSIP25".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP25_DESCRIPTION = "Specifies the type of "
    + "metadata in the " + "referenced file. Values are taken from the list provided by the METS.";

  /**
   * Constant specification cardinality for id "CSIP25".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP25_CARDINALITY = "1..1";

  /**
   * Constant specification level for id "CSIP25".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP25_LEVEL = "MUST";

  /* CSIP26 */

  /**
   * Constant specification id "CSIP26".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP26_ID = "CSIP26";

  /**
   * Constant specification name for id "CSIP26".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP26_NAME = "File mime type";

  /**
   * Constant specification location for id "CSIP26".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP26_LOCATION = "mets/dmdSec/mdRef/@MIMETYPE";

  /**
   * Constant specification description for id "CSIP26".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP26_DESCRIPTION = "The mime type of the referenced file.\n" +
    "It is a strong recommendation to use a qualified list like the IANA Media Type list.\n" +
    "Another list can be used following a documented agreement between the sender and the receiver.";

  /**
   * Constant specification cardinality for id "CSIP26".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP26_CARDINALITY = "1..1";

  /**
   * Constant specification level for id "CSIP26".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP26_LEVEL = "MUST";

  /* CSIP27 */

  /**
   * Constant specification id "CSIP27".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP27_ID = "CSIP27";

  /**
   * Constant specification name for id "CSIP27".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP27_NAME = "File size";

  /**
   * Constant specification location for id "CSIP27".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP27_LOCATION = "mets/dmdSec/mdRef/@SIZE";

  /**
   * Constant specification description for id "CSIP17".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP27_DESCRIPTION = "Size of the "
    + "referenced file in bytes.";

  /**
   * Constant specification cardinality for id "CSIP27".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP27_CARDINALITY = "1..1";

  /**
   * Constant specification level for id "CSIP27".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP27_LEVEL = "MUST";

  /* CSIP28 */

  /**
   * Constant specification id "CSIP28".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP28_ID = "CSIP28";

  /**
   * Constant specification name for id "CSIP28".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP28_NAME = "File creation date";

  /**
   * Constant specification location for id "CSIP28".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP28_LOCATION = "mets/dmdSec/mdRef/@CREATED";

  /**
   * Constant specification description for id "CSIP28".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP28_DESCRIPTION = "The creation date "
    + "of the referenced file.";

  /**
   * Constant specification cardinality for id "CSIP28".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP28_CARDINALITY = "1..1";

  /**
   * Constant specification level for id "CSIP28".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP28_LEVEL = "MUST";

  /* CSIP29 */

  /**
   * Constant specification id "CSIP29".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP29_ID = "CSIP29";

  /**
   * Constant specification name for id "CSIP29".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP29_NAME = "File checksum";

  /**
   * Constant specification location for id "CSIP29".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP29_LOCATION = "mets/dmdSec/mdRef/@CHECKSUM";

  /**
   * Constant specification description for id "CSIP29".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP29_DESCRIPTION = "The checksum of "
    + "the referenced file.";

  /**
   * Constant specification cardinality for id "CSIP29".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP29_CARDINALITY = "1..1";

  /**
   * Constant specification level for id "CSIP29".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP29_LEVEL = "MUST";

  /* CSIP30 */

  /**
   * Constant specification id "CSIP30".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP30_ID = "CSIP30";

  /**
   * Constant specification name for id "CSIP30".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP30_NAME = "File checksum type";

  /**
   * Constant specification location for id "CSIP30".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP30_LOCATION = "mets/dmdSec/mdRef/@CHECKSUMTYPE";

  /**
   * Constant specification description for id "CSIP30".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP30_DESCRIPTION = "The type of "
    + "checksum following the value list " + "present in the METS-standard which has been "
    + "used for calculating the checksum for the referenced file.";

  /**
   * Constant specification cardinality for id "CSIP30".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP30_CARDINALITY = "1..1";

  /**
   * Constant specification level for id "CSIP30".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP30_LEVEL = "MUST";

  /* Use of the METS administrative metadata section (element amdSec) */
  /* CSIP31 */

  /**
   * Constant specification id "CSIP31".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP31_ID = "CSIP31";

  /**
   * Constant specification name for id "CSIP31".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP31_NAME = "Administrative metadata";

  /**
   * Constant specification location for id "CSIP31".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP31_LOCATION = "mets/amdSec";

  /**
   * Constant specification description for id "CSIP31".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP31_DESCRIPTION = "If administrative "
    + "/ preservation metadata " + "is available, it must be described "
    + "using the administrative metadata section ( <amdSec> ) element. All "
    + "administrative metadata is present in a single <amdSec> element. It is "
    + "possible to transfer metadata in a package using just the descriptive "
    + "metadata section and/or administrative metadata section.";

  /**
   * Constant specification cardinality for id "CSIP31".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP31_CARDINALITY = "0..1";

  /**
   * Constant specification level for id "CSIP31".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP31_LEVEL = "SHOULD";

  /* CSIP32 */

  /**
   * Constant specification id "CSIP32".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP32_ID = "CSIP32";

  /**
   * Constant specification name for id "CSIP32".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP32_NAME = "Digital provenance metadata";

  /**
   * Constant specification location for id "CSIP32".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP32_LOCATION = "mets/amdSec/digiprovMD";

  /**
   * Constant specification description for id "CSIP32".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP32_DESCRIPTION = "For recording information about "
    + "preservation the standard PREMIS is used. "
    + "It is mandatory to include one <digiprovMD> element for each piece of "
    + "PREMIS metadata. The use if PREMIS in METS is following the "
    + "recommendations in the 2017 version of PREMIS in METS Guidelines.";

  /**
   * Constant specification cardinality for id "CSIP32".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP32_CARDINALITY = "0..n";

  /**
   * Constant specification level for id "CSIP32".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP32_LEVEL = "SHOULD";

  /* CSIP33 */

  /**
   * Constant specification id "CSIP33".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP33_ID = "CSIP33";

  /**
   * Constant specification name for id "CSIP33".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP33_NAME = "Digital provenance metadata identifier";

  /**
   * Constant specification location for id "CSIP33".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP33_LOCATION = "mets/amdSec/digiprovMD/@ID";

  /**
   * Constant specification description for id "CSIP33".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP33_DESCRIPTION = "An xml:id identifier for the " +
    "digital provenance metadata section mets/amdSec/digiprovMD " +
    "used for internal references within the XML-document. It must be unique within the XML-document.";

  /**
   * Constant specification cardinality for id "CSIP33".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP33_CARDINALITY = "1..1";

  /**
   * Constant specification level for id "CSIP33".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP33_LEVEL = "MUST";

  /* CSIP34 */

  /**
   * Constant specification id "CSIP34".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP34_ID = "CSIP34";

  /**
   * Constant specification name for id "CSIP34".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP34_NAME = "Status of the digital provenance metadata";

  /**
   * Constant specification location for id "CSIP34".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP34_LOCATION = "mets/amdSec/digiprovMD/@STATUS";

  /**
   * Constant specification description for id "CSIP34".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP34_DESCRIPTION = "Indicates the status of the " +
    "package using a fixed vocabulary.";

  /**
   * Constant specification cardinality for id "CSIP34".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP34_CARDINALITY = "0..1";

  /**
   * Constant specification level for id "CSIP34".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP34_LEVEL = "SHOULD";

  /* CSIP35 */

  /**
   * Constant specification id "CSIP35".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP35_ID = "CSIP35";

  /**
   * Constant specification name for id "CSIP35".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP35_NAME = "Reference to the "
    + "document with the digital provenance metadata";

  /**
   * Constant specification location for id "CSIP35".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP35_LOCATION = "mets/amdSec/digiprovMD/mdRef";

  /**
   * Constant specification description for id "CSIP35".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP35_DESCRIPTION = "Reference to the "
    + "digital provenance metadata " + "file stored in the “metadata” " + "section of the IP.";

  /**
   * Constant specification cardinality for id "CSIP35".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP35_CARDINALITY = "0..1";

  /**
   * Constant specification level for id "CSIP35".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP35_LEVEL = "SHOULD";

  /* CSIP36 */

  /**
   * Constant specification id "CSIP36".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP36_ID = "CSIP36";

  /**
   * Constant specification name for id "CSIP36".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP36_NAME = "Reference to the document with "
    + "the digital provenance metadata";

  /**
   * Constant specification location for id "CSIP36".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP36_LOCATION = "mets/amdSec/digiprovMD"
    + "/mdRef[@LOCTYPE=’URL’]";

  /**
   * Constant specification description for id "CSIP36".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP36_DESCRIPTION = "The locator type "
    + "is always used with the " + "value “URL” from the vocabulary in the attribute.";

  /**
   * Constant specification cardinality for id "CSIP36".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP36_CARDINALITY = "1..1";

  /**
   * Constant specification level for id "CSIP36".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP36_LEVEL = "MUST";

  /* CSIP37 */

  /**
   * Constant specification id "CSIP37".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP37_ID = "CSIP37";

  /**
   * Constant specification name for id "CSIP37".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP37_NAME = "Type of link";

  /**
   * Constant specification location for id "CSIP37".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP37_LOCATION = "mets/amdSec/digiprovMD"
    + "/mdRef[@xlink:type=’simple’]";

  /**
   * Constant specification description for id "CSIP37".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP37_DESCRIPTION = "The actual location of the resource. "
    + "This specification recommends " + "recording a URL type filepath within this attribute.";

  /**
   * Constant specification cardinality for id "CSIP37".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP37_CARDINALITY = "1..1";

  /**
   * Constant specification level for id "CSIP37".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP37_LEVEL = "MUST";

  /* CSIP38 */

  /**
   * Constant specification id "CSIP38".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP38_ID = "CSIP38";

  /**
   * Constant specification name for id "CSIP38".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP38_NAME = "Resource location";

  /**
   * Constant specification location for id "CSIP38".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP38_LOCATION = "mets/amdSec"
    + "/digiprovMD/mdRef/@xlink:href";

  /**
   * Constant specification description for id "CSIP38".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP38_DESCRIPTION = "The actual location "
    + "of the resource. " + "This specification recommends " + "recording a URL type filepath within this attribute.";

  /**
   * Constant specification cardinality for id "CSIP38".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP38_CARDINALITY = "1..1";

  /**
   * Constant specification level for id "CSIP38".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP38_LEVEL = "MUST";

  /* CSIP39 */

  /**
   * Constant specification id "CSIP39".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP39_ID = "CSIP39";

  /**
   * Constant specification name for id "CSIP39".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP39_NAME = "Type of metadata";

  /**
   * Constant specification location for id "CSIP39".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP39_LOCATION = "mets/amdSec/digiprovMD/mdRef/@MDTYPE";

  /**
   * Constant specification description for id "CSIP39".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP39_DESCRIPTION = "Specifies the type "
    + "of metadata in the " + "referenced file. Values are taken from " + "the list provided by the METS.";

  /**
   * Constant specification cardinality for id "CSIP39".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP39_CARDINALITY = "1..1";

  /**
   * Constant specification level for id "CSIP39".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP39_LEVEL = "MUST";

  /* CSIP40 */

  /**
   * Constant specification id "CSIP40".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP40_ID = "CSIP40";

  /**
   * Constant specification name for id "CSIP40".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP40_NAME = "File mime type";

  /**
   * Constant specification location for id "CSIP40".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP40_LOCATION = "mets/amdSec/digiprovMD/mdRef/@MIMETYPE";

  /**
   * Constant specification description for id "CSIP40".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP40_DESCRIPTION = "The mime type of the referenced" +
    " file.\n" + "It is a strong recommendation to use a qualified " +
    "list like the IANA Media Type list.\n" +
    "Another list can be used following a documented agreement between the sender and the receiver.";

  /**
   * Constant specification cardinality for id "CSIP40".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP40_CARDINALITY = "1..1";

  /**
   * Constant specification level for id "CSIP40".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP40_LEVEL = "MUST";

  /* CSIP41 */

  /**
   * Constant specification id "CSIP41".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP41_ID = "CSIP41";

  /**
   * Constant specification name for id "CSIP41".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP41_NAME = "File size";

  /**
   * Constant specification location for id "CSIP41".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP41_LOCATION = "mets/amdSec/digiprovMD/mdRef/@SIZE";

  /**
   * Constant specification description for id "CSIP41".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP41_DESCRIPTION = "Size of the "
    + "referenced file in bytes.";

  /**
   * Constant specification cardinality for id "CSIP41".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP41_CARDINALITY = "1..1";

  /**
   * Constant specification level for id "CSIP41".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP41_LEVEL = "MUST";

  /* CSIP42 */

  /**
   * Constant specification id "CSIP42".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP42_ID = "CSIP42";

  /**
   * Constant specification name for id "CSIP42".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP42_NAME = "File creation date";

  /**
   * Constant specification location for id "CSIP42".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP42_LOCATION = "mets/amdSec/digiprovMD/mdRef/@CREATED";

  /**
   * Constant specification description for id "CSIP42".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP42_DESCRIPTION = "Creation date "
    + "of the referenced file.";

  /**
   * Constant specification cardinality for id "CSIP42".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP42_CARDINALITY = "1..1";

  /**
   * Constant specification level for id "CSIP42".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP42_LEVEL = "MUST";

  /* CSIP43 */

  /**
   * Constant specification id "CSIP43".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP43_ID = "CSIP43";

  /**
   * Constant specification name for id "CSIP43".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP43_NAME = "File checksum";

  /**
   * Constant specification location for id "CSIP43".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP43_LOCATION = "mets/amdSec/digiprovMD/mdRef/@CHECKSUM";

  /**
   * Constant specification description for id "CSIP43".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP43_DESCRIPTION = "The checksum "
    + "of the referenced file.";

  /**
   * Constant specification cardinality for id "CSIP43".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP43_CARDINALITY = "1..1";

  /**
   * Constant specification level for id "CSIP43".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP43_LEVEL = "MUST";

  /* CSIP44 */

  /**
   * Constant specification id "CSIP44".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP44_ID = "CSIP44";

  /**
   * Constant specification name for id "CSIP44".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP44_NAME = "File checksum type";

  /**
   * Constant specification location for id "CSIP44".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP44_LOCATION = "mets/amdSec"
    + "/digiprovMD/mdRef/@CHECKSUMTYPE";

  /**
   * Constant specification description for id "CSIP44".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP44_DESCRIPTION = "The type " + "of checksum following "
    + "the value list present" + "in the METS-standard " + "which has been used for calculating "
    + "the checksum for the referenced file.";

  /**
   * Constant specification cardinality for id "CSIP44".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP44_CARDINALITY = "1..1";

  /**
   * Constant specification level for id "CSIP44".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP44_LEVEL = "MUST";

  /* CSIP45 */

  /**
   * Constant specification id "CSIP45".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP45_ID = "CSIP45";

  /**
   * Constant specification name for id "CSIP45".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP45_NAME = "Rights metadata";

  /**
   * Constant specification location for id "CSIP45".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP45_LOCATION = "mets/amdSec/rightsMD";

  /**
   * Constant specification description for id "CSIP45".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP45_DESCRIPTION = "A simple rights statement may be "
    + "used to describe general permissions for "
    + "the package. Individual representations should state their specific rights in "
    + "their representation METS file. Available standards include "
    + "RightsStatements.org , Europeana rights statements info , METS Rights "
    + "Schema created and maintained by the METS Board, the rights part of "
    + "PREMIS as well as own local rights statements in use.";

  /**
   * Constant specification cardinality for id "CSIP45".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP45_CARDINALITY = "0..1";

  /**
   * Constant specification level for id "CSIP45".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP45_LEVEL = "MAY";

  /* CSIP46 */

  /**
   * Constant specification id "CSIP46".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP46_ID = "CSIP46";

  /**
   * Constant specification name for id "CSIP46".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP46_NAME = "Rights metadata identifier";

  /**
   * Constant specification location for id "CSIP46".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP46_LOCATION = "mets/amdSec/rightsMD/@ID";

  /**
   * Constant specification description for id "CSIP46".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP46_DESCRIPTION = "An xml:id identifier for the " +
    "rights metadata section (<rightsMD>) used for internal " +
    "references within the XML-document. It must be unique within the XML-document.";

  /**
   * Constant specification cardinality for id "CSIP46".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP46_CARDINALITY = "1..1";

  /**
   * Constant specification level for id "CSIP46".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP46_LEVEL = "MUST";

  /* CSIP47 */

  /**
   * Constant specification id "CSIP47".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP47_ID = "CSIP47";

  /**
   * Constant specification name for id "CSIP47".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP47_NAME = "Status of the rights metadata";

  /**
   * Constant specification location for id "CSIP47".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP47_LOCATION = "mets/amdSec/rightsMD/@STATUS";

  /**
   * Constant specification description for id "CSIP47".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP47_DESCRIPTION = "Indicates the status of "
    + "the package using a fixed vocabulary.";

  /**
   * Constant specification cardinality for id "CSIP47".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP47_CARDINALITY = "0..1";

  /**
   * Constant specification level for id "CSIP47".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP47_LEVEL = "SHOULD";

  /* CSIP48 */
  /**
   * Constant specification id "CSIP48".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP48_ID = "CSIP48";

  /**
   * Constant specification name for id "CSIP48".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP48_NAME = "Reference to the document "
    + "with the rights metadata";

  /**
   * Constant specification location for id "CSIP48".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP48_LOCATION = "mets/amdSec/rightsMD/mdRef";

  /**
   * Constant specification description for id "CSIP48".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP48_DESCRIPTION = "Reference to the rights metadata "
    + "file stored in the “metadata” section of the IP.";

  /**
   * Constant specification cardinality for id "CSIP48".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP48_CARDINALITY = "0..1";

  /**
   * Constant specification level for id "CSIP48".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP48_LEVEL = "SHOULD";

  /* CSIP49 */

  /**
   * Constant specification id "CSIP49".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP49_ID = "CSIP49";

  /**
   * Constant specification name for id "CSIP49".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP49_NAME = "Type of locator";

  /**
   * Constant specification location for id "CSIP49".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP49_LOCATION = "mets/amdSec/rightsMD"
    + "/mdRef[@LOCTYPE=’URL’]";

  /**
   * Constant specification description for id "CSIP49".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP49_DESCRIPTION = "The locator type is always used "
    + "with the value “URL” from the vocabulary in the attribute.";

  /**
   * Constant specification cardinality for id "CSIP49".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP49_CARDINALITY = "1..1";

  /**
   * Constant specification level for id "CSIP49".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP49_LEVEL = "MUST";

  /* CSIP50 */

  /**
   * Constant specification id "CSIP50".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP50_ID = "CSIP50";

  /**
   * Constant specification name for id "CSIP50".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP50_NAME = "Type of locator";

  /**
   * Constant specification location for id "CSIP50".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP50_LOCATION = "mets/amdSec/rightsMD"
    + "/mdRef[@xlink:type=’simple’]";

  /**
   * Constant specification description for id "CSIP50".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP50_DESCRIPTION = "Attribute used "
    + "with the value “simple”. " + "Value list is maintained by the xlink standard.";

  /**
   * Constant specification cardinality for id "CSIP50".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP50_CARDINALITY = "1..1";

  /**
   * Constant specification level for id "CSIP50".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP50_LEVEL = "MUST";

  /* CSIP51 */
  /**
   * Constant specification id "CSIP51".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP51_ID = "CSIP51";

  /**
   * Constant specification name for id "CSIP51".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP51_NAME = "Resource location";

  /**
   * Constant specification location for id "CSIP51".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP51_LOCATION = "mets/amdSec/rightsMD"
    + "/mdRef/@xlink:href";

  /**
   * Constant specification description for id "CSIP51".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP51_DESCRIPTION = "The actual location "
    + "of the resource. " + "We recommend recording a URL type filepath within this attribute.";

  /**
   * Constant specification cardinality for id "CSIP51".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP51_CARDINALITY = "1..1";

  /**
   * Constant specification level for id "CSIP51".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP51_LEVEL = "MUST";

  /* CSIP52 */

  /**
   * Constant specification id "CSIP52".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP52_ID = "CSIP52";

  /**
   * Constant specification name for id "CSIP52".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP52_NAME = "Type of metadata";

  /**
   * Constant specification location for id "CSIP52".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP52_LOCATION = "mets/amdSec/rightsMD/mdRef/@MDTYPE";

  /**
   * Constant specification description for id "CSIP52".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP52_DESCRIPTION = "Specifies the type of "
    + "metadata in the referenced file. " + "Value is taken from the list provided by the METS.";

  /**
   * Constant specification cardinality for id "CSIP52".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP52_CARDINALITY = "1..1";

  /**
   * Constant specification level for id "CSIP52".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP52_LEVEL = "MUST";

  /* CSIP53 */
  /**
   * Constant specification id "CSIP53".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP53_ID = "CSIP53";

  /**
   * Constant specification name for id "CSIP53".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP53_NAME = "File mime type";

  /**
   * Constant specification location for id "CSIP53".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP53_LOCATION = "mets/amdSec/"
    + "rightsMD/mdRef/@MIMETYPE";

  /**
   * Constant specification description for id "CSIP53".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP53_DESCRIPTION = "The mime type of the " +
    "referenced file.\n" + "It is a strong recommendation to use a qualified list like the IANA Media Type list.\n" +
    "Another list can be used following a documented agreement between the sender and the receiver.";

  /**
   * Constant specification cardinality for id "CSIP53".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP53_CARDINALITY = "1..1";

  /**
   * Constant specification level for id "CSIP53".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP53_LEVEL = "MUST";

  /* CSIP54 */

  /**
   * Constant specification id "CSIP54".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP54_ID = "CSIP54";

  /**
   * Constant specification name for id "CSIP54".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP54_NAME = "File size";

  /**
   * Constant specification location for id "CSIP54".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP54_LOCATION = "mets/amdSec/rightsMD/mdRef/@SIZE";

  /**
   * Constant specification description for id "CSIP54".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP54_DESCRIPTION = "Size of the referenced "
    + "file in bytes.";

  /**
   * Constant specification cardinality for id "CSIP54".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP54_CARDINALITY = "1..1";

  /**
   * Constant specification level for id "CSIP54".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP54_LEVEL = "MUST";

  /* CSIP55 */

  /**
   * Constant specification id "CSIP55".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP55_ID = "CSIP55";

  /**
   * Constant specification name for id "CSIP55".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP55_NAME = "File creation date";

  /**
   * Constant specification location for id "CSIP55".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP55_LOCATION = "mets/amdSec/rightsMD/mdRef/@CREATED";

  /**
   * Constant specification description for id "CSIP55".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP55_DESCRIPTION = "Creation date "
    + "of the referenced file.";

  /**
   * Constant specification cardinality for id "CSIP55".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP55_CARDINALITY = "1..1";

  /**
   * Constant specification level for id "CSIP55".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP55_LEVEL = "MUST";

  /* CSIP56 */

  /**
   * Constant specification id "CSIP56".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP56_ID = "CSIP56";

  /**
   * Constant specification name for id "CSIP56".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP56_NAME = "File checksum";

  /**
   * Constant specification location for id "CSIP56".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP56_LOCATION = "mets/amdSec/rightsMD/mdRef/@CHECKSUM";

  /**
   * Constant specification description for id "CSIP56".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP56_DESCRIPTION = "The checksum "
    + "of the referenced file.";

  /**
   * Constant specification cardinality for id "CSIP56".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP56_CARDINALITY = "1..1";

  /**
   * Constant specification level for id "CSIP56".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP56_LEVEL = "MUST";

  /* CSIP57 */

  /**
   * Constant specification id "CSIP57".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP57_ID = "CSIP57";

  /**
   * Constant specification name for id "CSIP57".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP57_NAME = "File checksum type";

  /**
   * Constant specification location for id "CSIP57".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP57_LOCATION = "mets/amdSec"
    + "/rightsMD/mdRef/@CHECKSUMTYPE";

  /**
   * Constant specification description for id "CSIP57".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP57_DESCRIPTION = "The type of checksum following "
    + "the value list present in the " + "METS-standard which has been used for "
    + "calculating the checksum for the referenced file.";

  /**
   * Constant specification cardinality for id "CSIP57".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP57_CARDINALITY = "1..1";

  /**
   * Constant specification level for id "CSIP57".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP57_LEVEL = "MUST";

  /* Use of the METS file section (element fileSec) */
  /* CSIP58 */

  /**
   * Constant specification id "CSIP58".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP58_ID = "CSIP58";

  /**
   * Constant specification name for id "CSIP58".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP58_NAME = "File section";

  /**
   * Constant specification location for id "CSIP58".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP58_LOCATION = "mets/fileSec";

  /**
   * Constant specification description for id "CSIP58".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP58_DESCRIPTION = "The transferred content is "
    + "placed in the file section in different file group "
    + "elements, described in other requirements. Only a single file section "
    + "( <fileSec> ) element should be present. It is possible to transfer just "
    + "descriptive metadata and/or administrative metadata without files placed in " + "this section.";

  /**
   * Constant specification cardinality for id "CSIP58".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP58_CARDINALITY = "0..1";

  /**
   * Constant specification level for id "CSIP58".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP58_LEVEL = "SHOULD";

  /* CSIP59 */

  /**
   * Constant specification id "CSIP59".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP59_ID = "CSIP59";

  /**
   * Constant specification name for id "CSIP59".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP59_NAME = "File section identifier";

  /**
   * Constant specification location for id "CSIP59".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP59_LOCATION = "mets/fileSec/@ID";

  /**
   * Constant specification description for id "CSIP59".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP59_DESCRIPTION = "An xml:id identifier for the " +
    "file section used for internal references within the XML-document. " +
    "It must be unique within the XML-document.";

  /**
   * Constant specification cardinality for id "CSIP59".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP59_CARDINALITY = "1..1";

  /**
   * Constant specification level for id "CSIP59".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP59_LEVEL = "MUST";

  /* CSIP60 */

  /**
   * Constant specification id "CSIP60".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP60_ID = "CSIP60";

  /**
   * Constant specification name for id "CSIP60".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP60_NAME = "Documentation file group";

  /**
   * Constant specification location for id "CSIP60".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP60_LOCATION = "mets/fileSec"
    + "/fileGrp[@USE=’Documentation’]";

  /**
   * Constant specification description for id "CSIP60".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP60_DESCRIPTION = "All documentation pertaining "
    + "to the transferred content is placed " + "in one or more file group elements "
    + "with mets/fileSec/fileGrp/@USE attribute value “Documentation”.";

  /**
   * Constant specification cardinality for id "CSIP60".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP60_CARDINALITY = "1..n";

  /**
   * Constant specification level for id "CSIP60".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP60_LEVEL = "MUST";

  /* CSIP113 */

  /**
   * Constant specification id "CSIP113".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP113_ID = "CSIP113";

  /**
   * Constant specification name for id "CSIP113".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP113_NAME = "Schema file group";

  /**
   * Constant specification location for id "CSIP113".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP113_LOCATION = "mets/fileSec/fileGrp[@USE=’Schemas’]";

  /**
   * Constant specification description for id "CSIP113".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP113_DESCRIPTION = "All XML schemas used "
    + "in the information package should be referenced from "
    + "one or more file groups with mets/fileSec/fileGrp/@USE attribute value “Schemas”.";

  /**
   * Constant specification cardinality for id "CSIP113".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP113_CARDINALITY = "1..n";

  /**
   * Constant specification level for id "CSIP113".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP113_LEVEL = "MUST";

  /* CSIP114 */

  /**
   * Constant specification id "CSIP114".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP114_ID = "CSIP114";

  /**
   * Constant specification name for id "CSIP114".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP114_NAME = "Representations file group";

  /**
   * Constant specification location for id "CSIP114".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP114_LOCATION = "mets/fileSec"
    + "/fileGrp[@USE=’Representations’]";

  /**
   * Constant specification description for id "CSIP114".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP114_DESCRIPTION = "A pointer to the METS document " +
    "describing the representation or pointers to " +
    "the content being transferred must be present in one " +
    "or more file groups with mets/fileSec/fileGrp/@USE " +
    "attribute value starting with “Representations” followed " +
    "by the path to the folder where the representation level " +
    "METS document is placed. For example “Representation/submission” and “Representation/ingest”.";

  /**
   * Constant specification cardinality for id "CSIP114".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP114_CARDINALITY = "1..n";

  /**
   * Constant specification level for id "CSIP114".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP114_LEVEL = "MUST";

  /* CSIP61 */
  /**
   * Constant specification id "CSIP61".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP61_ID = "CSIP61";

  /**
   * Constant specification name for id "CSIP61".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP61_NAME = "Reference to administrative metadata";

  /**
   * Constant specification location for id "CSIP61".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP61_LOCATION = "mets/fileSec/fileGrp/@ADMID";

  /**
   * Constant specification description for id "CSIP61".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP61_DESCRIPTION = "If administrative metadata "
    + "has been provided at file group " + "mets/fileSec/fileGrp level this attribute refers to its administrative "
    + "metadata section by ID.";

  /**
   * Constant specification cardinality for id "CSIP61".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP61_CARDINALITY = "0..1";

  /**
   * Constant specification level for id "CSIP61".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP61_LEVEL = "MAY";

  /* CSIP62 */

  /**
   * Constant specification id "CSIP62".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP62_ID = "CSIP62";

  /**
   * Constant specification name for id "CSIP62".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP62_NAME = "Content Information Type Specification";

  /**
   * Constant specification location for id "CSIP62".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP62_LOCATION = "mets/fileSec/fileGrp"
    + "[@USE=’Representations’]/@csip:CONTENTINFORMATIONTYPE";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP62_DESCRIPTION = "An added attribute "
    + "which states the name of the content information type "
    + "specification used to create the package. The vocabulary will evolve under "
    + "the curation of the DILCIS Board as additional content information type "
    + "specifications are developed. This attribute is mandatory when the "
    + "mets/fileSec/fileGrp/@USE attribute value is “Representations”. When "
    + "the “Package type” value is “Mixed” and/or the file group describes a "
    + "“Representation”, then this element states the content information type "
    + "specification used for the file group.";

  /**
   * Constant specification cardinality for id "CSIP62".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP62_CARDINALITY = "0..1";

  /**
   * Constant specification level for id "CSIP62".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP62_LEVEL = "SHOULD";

  /* CSIP63 */

  /**
   * Constant specification id "CSIP63".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP63_ID = "CSIP63";

  /**
   * Constant specification name for id "CSIP63".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP63_NAME = "Other Content "
    + "Information Type Specification";

  /**
   * Constant specification location for id "CSIP63".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP63_LOCATION = "mets/fileSec"
    + "/fileGrp[@csip:CONTENTINFORMATIONTYPE=’OTHER’]" + "/@csip:OTHERCONTENTINFORMATIONTYPE";

  /**
   * Constant specification description for id "CSIP63".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP63_DESCRIPTION = "When the mets/fileSec"
    + "/fileGrp/@csip:CONTENTINFORMATIONTYPE " + "attribute has the value “OTHER” the "
    + "mets/fileSec/fileGrp/@csip:OTHERCONTENTINFORMATIONTYPE must "
    + "state a value for the Content Information Type Specification used.";

  /**
   * Constant specification cardinality for id "CSIP63".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP63_CARDINALITY = "0..1";

  /**
   * Constant specification level for id "CSIP63".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP63_LEVEL = "MAY";

  /* CSIP64 */
  /**
   * Constant specification id "CSIP64".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP64_ID = "CSIP64";

  /**
   * Constant specification name for id "CSIP64".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP64_NAME = "Description of the use of the file group";

  /**
   * Constant specification location for id "CSIP64".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP64_LOCATION = "mets/fileSec/fileGrp/@USE";

  /**
   * Constant specification description for id "CSIP64".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP64_DESCRIPTION = "The value in the "
    + "mets/fileSec/fileGrp/@USE is the name of the whole "
    + "folder structure to the data, e.g 'Documentation', 'Schemas',"
    + "“Representations/preingest” or “Representations/submission/data”.";

  /**
   * Constant specification cardinality for id "CSIP64".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP64_CARDINALITY = "1..1";

  /**
   * Constant specification level for id "CSIP64".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP64_LEVEL = "MUST";

  /* CSIP65 */

  /**
   * Constant specification id "CSIP65".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP65_ID = "CSIP65";

  /**
   * Constant specification name for id "CSIP65".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP65_NAME = "File group identifier";

  /**
   * Constant specification location for id "CSIP65".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP65_LOCATION = "mets/fileSec/fileGrp/@ID";

  /**
   * Constant specification description for id "CSIP65".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP65_DESCRIPTION = "An xml:id identifier for " +
    "the file group used for internal references within " +
    "the XML-document. It must be unique within the XML-document.";

  /**
   * Constant specification cardinality for id "CSIP65".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP65_CARDINALITY = "1..1";

  /**
   * Constant specification level for id "CSIP65".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP65_LEVEL = "MUST";

  /* CSIP66 */
  /**
   * Constant specification id "CSIP66".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP66_ID = "CSIP66";

  /**
   * Constant specification name for id "CSIP66".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP66_NAME = "File";

  /**
   * Constant specification location for id "CSIP66".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP66_LOCATION = "mets/fileSec/fileGrp/file";

  /**
   * Constant specification description for id "CSIP66".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP66_DESCRIPTION = "The file group ( <fileGrp> ) "
    + "contains the file elements which describe the file objects.";

  /**
   * Constant specification cardinality for id "CSIP66".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP66_CARDINALITY = "1..n";

  /**
   * Constant specification level for id "CSIP66".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP66_LEVEL = "MUST";

  /* CSIP67 */
  /**
   * Constant specification id "CSIP67".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP67_ID = "CSIP67";

  /**
   * Constant specification name for id "CSIP67".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP67_NAME = "File identifier";

  /**
   * Constant specification location for id "CSIP67".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP67_LOCATION = "mets/fileSec/fileGrp/file/@ID";

  /**
   * Constant specification description for id "CSIP67".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP67_DESCRIPTION = "A unique xml:id identifier "
    + "for this file within the XML-document.";

  /**
   * Constant specification cardinality for id "CSIP67".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP67_CARDINALITY = "1..1";

  /**
   * Constant specification level for id "CSIP67".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP67_LEVEL = "MUST";

  /* CSIP68 */

  /**
   * Constant specification id "CSIP68".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP68_ID = "CSIP68";

  /**
   * Constant specification name for id "CSIP68".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP68_NAME = "File mimetype";

  /**
   * Constant specification location for id "CSIP68".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP68_LOCATION = "mets/fileSec/fileGrp/file/@MIMETYPE";

  /**
   * Constant specification description for id "CSIP68".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP68_DESCRIPTION = "The mime type of the referenced " +
    "file.\n" + "It is a strong recommendation to use a qualified list like the IANA Media Type list.\n" +
    "Another list can be used following a documented agreement between the sender and the receiver.";

  /**
   * Constant specification cardinality for id "CSIP68".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP68_CARDINALITY = "1..1";

  /**
   * Constant specification level for id "CSIP68".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP68_LEVEL = "MUST";

  /* CSIP69 */

  /**
   * Constant specification id "CSIP69".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP69_ID = "CSIP69";

  /**
   * Constant specification name for id "CSIP69".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP69_NAME = "File size";

  /**
   * Constant specification location for id "CSIP69".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP69_LOCATION = "mets/fileSec/fileGrp/file/@SIZE";

  /**
   * Constant specification description for id "CSIP69".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP69_DESCRIPTION = "Size of the "
    + "referenced file in bytes.";

  /**
   * Constant specification cardinality for id "CSIP69".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP69_CARDINALITY = "1..1";

  /**
   * Constant specification level for id "CSIP69".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP69_LEVEL = "MUST";

  /* CSIP70 */

  /**
   * Constant specification id "CSIP70".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP70_ID = "CSIP70";

  /**
   * Constant specification name for id "CSIP70".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP70_NAME = "File creation date";

  /**
   * Constant specification location for id "CSIP70".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP70_LOCATION = "mets/fileSec/fileGrp/file/@CREATED";

  /**
   * Constant specification description for id "CSIP70".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP70_DESCRIPTION = "Creation date "
    + "of the referenced file.";

  /**
   * Constant specification cardinality for id "CSIP70".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP70_CARDINALITY = "1..1";

  /**
   * Constant specification level for id "CSIP70".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP70_LEVEL = "MUST";

  /* CSIP71 */

  /**
   * Constant specification id "CSIP71".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP71_ID = "CSIP71";

  /**
   * Constant specification name for id "CSIP71".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP71_NAME = "File checksum";

  /**
   * Constant specification location for id "CSIP71".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP71_LOCATION = "mets/fileSec/fileGrp/file/@CHECKSUM";

  /**
   * Constant specification description for id "CSIP71".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP71_DESCRIPTION = "The checksum of "
    + "the referenced file.";
  /**
   * Constant specification cardinality for id "CSIP71".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP71_CARDINALITY = "1..1";

  /**
   * Constant specification level for id "CSIP71".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP71_LEVEL = "MUST";

  /* CSIP72 */

  /**
   * Constant specification id "CSIP72".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP72_ID = "CSIP72";

  /**
   * Constant specification name for id "CSIP72".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP72_NAME = "File checksum type";
  /**
   * Constant specification location for id "CSIP72".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP72_LOCATION = "mets/fileSec/"
    + "fileGrp/file/@CHECKSUMTYPE";

  /**
   * Constant specification name for id "CSIP72".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP72_DESCRIPTION = "The type of checksum following "
    + "the value list present in the METS-standard "
    + "which has been used for calculating the checksum for the referenced file.";

  /**
   * Constant specification cardinality for id "CSIP72".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP72_CARDINALITY = "1..1";

  /**
   * Constant specification level for id "CSIP72".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP72_LEVEL = "MUST";

  /* CSIP73 */

  /**
   * Constant specification id "CSIP73".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP73_ID = "CSIP73";

  /**
   * Constant specification name for id "CSIP73".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP73_NAME = "File original identfication";

  /**
   * Constant specification name for id "CSIP73".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP73_LOCATION = "mets/fileSec/fileGrp/file/@OWNERID";

  /**
   * Constant specification description for id "CSIP73".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP73_DESCRIPTION = "If an identifier for the file "
    + "was supplied by the owner it can be recorded in " + "this attribute.";

  /**
   * Constant specification cardinality for id "CSIP73".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP73_CARDINALITY = "0..1";

  /**
   * Constant specification level for id "CSIP73".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP73_LEVEL = "MAY";

  /* CSIP74 */

  /**
   * Constant specification id "CSIP74".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP74_ID = "CSIP74";

  /**
   * Constant specification name for id "CSIP74".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP74_NAME = "File reference to administrative metadata";

  /**
   * Constant specification location for id "CSIP74".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP74_LOCATION = "mets/fileSec/fileGrp/file/@ADMID";

  /**
   * Constant specification description for id "CSIP74".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP74_DESCRIPTION = "If administrative metadata "
    + "has been provided for the file this attribute refers " + "to the file’s administrative metadata by ID.";

  /**
   * Constant specification cardinality for id "CSIP74".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP74_CARDINALITY = "0..1";

  /**
   * Constant specification level for id "CSIP74".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP74_LEVEL = "MAY";

  /* CSIP75 */

  /**
   * Constant specification id "CSIP75".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP75_ID = "CSIP75";

  /**
   * Constant specification name for id "CSIP75".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP75_NAME = "File reference to descriptive metadata";

  /**
   * Constant specification location for id "CSIP75".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP75_LOCATION = "mets/fileSec/fileGrp/file/@DMDID";

  /**
   * Constant specification description for id "CSIP75".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP75_DESCRIPTION = "If descriptive metadata "
    + "has been provided per file this attribute refers to the " + "file’s descriptive metadata by ID.";

  /**
   * Constant specification cardinality for id "CSIP75".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP75_CARDINALITY = "0..1";

  /**
   * Constant specification level for id "CSIP75".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP75_LEVEL = "MAY";

  /* CSIP76 */

  /**
   * Constant specification id "CSIP76".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP76_ID = "CSIP76";

  /**
   * Constant specification name for id "CSIP76".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP76_NAME = "File locator reference";

  /**
   * Constant specification location for id "CSIP76".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP76_LOCATION = "mets/fileSec/fileGrp/file/FLocat";

  /**
   * Constant specification description for id "CSIP76".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP76_DESCRIPTION = "The location of each "
    + "external file must be defined by the file location "
    + "<FLocat> element using the same rules as for referencing metadata files. "
    + "All references to files should be made using the XLink href attribute and the "
    + "file protocol using the relative location of the file.";

  /**
   * Constant specification cardinality for id "CSIP76".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP76_CARDINALITY = "0..1";

  /**
   * Constant specification level for id "CSIP76".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP76_LEVEL = "MAY";

  /* CSIP77 */

  /**
   * Constant specification id "CSIP77".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP77_ID = "CSIP77";

  /**
   * Constant specification name for id "CSIP77".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP77_NAME = "Type of locator";

  /**
   * Constant specification location for id "CSIP77".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP77_LOCATION = "mets/fileSec"
    + "/fileGrp/file/FLocat[@LOCTYPE=’URL’]";

  /**
   * Constant specification description for id "CSIP77".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP77_DESCRIPTION = "The locator type is always "
    + "used with the value “URL” from the vocabulary in the attribute.";

  /**
   * Constant specification cardinality for id "CSIP77".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP77_CARDINALITY = "1..1";

  /**
   * Constant specification level for id "CSIP77".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP77_LEVEL = "MUST";

  /* CSIP78 */

  /**
   * Constant specification id "CSIP78".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP78_ID = "CSIP78";

  /**
   * Constant specification name for id "CSIP78".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP78_NAME = "Type of link";

  /**
   * Constant specification location for id "CSIP78".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP78_LOCATION = "mets/fileSec"
    + "/fileGrp/file/FLocat[@xlink:type=’simple’]";

  /**
   * Constant specification description for id "CSIP78".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP78_DESCRIPTION = "Attribute used with "
    + "the value “simple”. Value list is maintained by the xlink standard.";

  /**
   * Constant specification cardinality for id "CSIP78".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP78_CARDINALITY = "1..1";

  /**
   * Constant specification level for id "CSIP78".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP78_LEVEL = "MUST";

  /* CSIP79 */

  /**
   * Constant specification id "CSIP79".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP79_ID = "CSIP79";

  /**
   * Constant specification name for id "CSIP79".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP79_NAME = "Resource location";

  /**
   * Constant specification location for id "CSIP79".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP79_LOCATION = "mets/fileSec"
    + "/fileGrp/file/FLocat/@xlink:href";

  /**
   * Constant specification description for id "CSIP79".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP79_DESCRIPTION = "The actual "
    + "location of the resource. " + "We recommend recording a URL type filepath within this attribute.";

  /**
   * Constant specification cardinality for id "CSIP79".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP79_CARDINALITY = "1..1";

  /**
   * Constant specification level for id "CSIP79".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP79_LEVEL = "MUST";

  /* Use of the METS structural map (<structMap>) */
  /* CSIP80 */

  /**
   * Constant specification id "CSIP80".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP80_ID = "CSIP80";

  /**
   * Constant specification name for id "CSIP80".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP80_NAME = "Structural description of the package";

  /**
   * Constant specification location for id "CSIP80".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP80_LOCATION = "mets/structMap";

  /**
   * Constant specification description for id "CSIP80".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP80_DESCRIPTION = "The structural map "
    + "<structMap> element is the only mandatory element in "
    + "the METS. The <structMap> in the CSIP describes the highest logical "
    + "structure of the IP. Each METS file must include ONE structural map "
    + "<structMap> element used exactly as described here. Institutions can add "
    + "their own additional custom structural maps as separate <structMap> " + "sections.";

  /**
   * Constant specification cardinality for id "CSIP80".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP80_CARDINALITY = "1..n";

  /**
   * Constant specification level for id "CSIP80".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP80_LEVEL = "MUST";

  /* CSIP81 */

  /**
   * Constant specification id "CSIP81".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP81_ID = "CSIP81";

  /**
   * Constant specification name for id "CSIP81".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP81_NAME = "Type of structural description";

  /**
   * Constant specification location for id "CSIP81".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP81_LOCATION = "mets/structMap[@TYPE=’PHYSICAL’]";

  /**
   * Constant specification description for id "CSIP81".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP81_DESCRIPTION = "The mets/structMap/@TYPE "
    + "attribute must take the value 'PHYSICAL' " + "from the vocabulary.";

  /**
   * Constant specification cardinality for id "CSIP81".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP81_CARDINALITY = "1..1";

  /**
   * Constant specification level for id "CSIP81".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP81_LEVEL = "MUST";

  /* CSIP82 */

  /**
   * Constant specification id "CSIP82".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP82_ID = "CSIP82";

  /**
   * Constant specification name for id "CSIP82".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP82_NAME = "Name of the structural description";

  /**
   * Constant specification location for id "CSIP82".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP82_LOCATION = "mets/structMap[@LABEL=’CSIP’]";

  /**
   * Constant specification description for id "CSIP82".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP82_DESCRIPTION = "The mets/structMap/@LABEL " +
    "attribute value is set to “CSIP” from the vocabulary.\n" +
    "This requirement identifies the CSIP compliant structural map <structMap> element.";

  /**
   * Constant specification cardinality for id "CSIP82".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP82_CARDINALITY = "1..1";

  /**
   * Constant specification level for id "CSIP82".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP82_LEVEL = "MUST";

  /* CSIP83 */

  /**
   * Constant specification id "CSIP83".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP83_ID = "CSIP83";

  /**
   * Constant specification name for id "CSIP83".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP83_NAME = "mets/structMap[@LABEL=’CSIP’]/@ID";

  /**
   * Constant specification location for id "CSIP83".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP83_LOCATION = "mets/structMap[@LABEL=’CSIP’]/@ID";

  /**
   * Constant specification description for id "CSIP83".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP83_DESCRIPTION = "An xml:id identifier for " +
    "the structural description (structMap) used for " +
    "internal references within the XML-document. It must be unique within the XML-document.";

  /**
   * Constant specification cardinality for id "CSIP83".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP83_CARDINALITY = "1..1";

  /**
   * Constant specification level for id "CSIP83".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP83_LEVEL = "MUST";

  /* CSIP84 */

  /**
   * Constant specification id "CSIP84".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP84_ID = "CSIP84";

  /**
   * Constant specification name for id "CSIP84".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP84_NAME = "Main structural division";

  /**
   * Constant specification level for id "CSIP84".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP84_LOCATION = "mets/structMap[@LABEL=’CSIP’]/div";

  /**
   * Constant specification description for id "CSIP84".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP84_DESCRIPTION = "The structural map "
    + "comprises a single division.";

  /**
   * Constant specification cardinality for id "CSIP84".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP84_CARDINALITY = "1..1";

  /**
   * Constant specification level for id "CSIP84".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP84_LEVEL = "MUST";

  /* CSIP85 */

  /**
   * Constant specification id "CSIP85".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP85_ID = "CSIP85";

  /**
   * Constant specification name for id "CSIP85".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP85_NAME = "Main structural division identifier";

  /**
   * Constant specification location for id "CSIP85".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP85_LOCATION = "mets/structMap[@LABEL=’CSIP’]/div/@ID";

  /**
   * Constant specification description for id "CSIP85".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP85_DESCRIPTION = "Mandatory, xml:id "
    + "identifier must be unique within the XML-document.";

  /**
   * Constant specification cardinality for id "CSIP85".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP85_CARDINALITY = "1..1";

  /**
   * Constant specification level for id "CSIP85".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP85_LEVEL = "MUST";


  /* CSIP88 */
  /**
   * Constant specification id "CSIP88".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP88_ID = "CSIP88";

  /**
   * Constant specification name for id "CSIP88".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP88_NAME = "Metadata division";

  /**
   * Constant specification location for id "CSIP88".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP88_LOCATION = "mets/structMap[@LABEL='CSIP']"
    + "/div/div[@LABEL='Metadata']";

  /**
   * Constant specification description for id "CSIP88".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP88_DESCRIPTION = "The metadata referenced "
    + "in the administrative and/or descriptive "
    + "metadata section is described in the structural map with one sub division."
    + "When the transfer consists of only administrative and/or descriptive metadata "
    + "this is the only sub division that occurs.";

  /**
   * Constant specification cardinality for id "CSIP88".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP88_CARDINALITY = "1..1";

  /**
   * Constant specification level for id "CSIP88".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP88_LEVEL = "MUST";

  /* CSIP89 */

  /**
   * Constant specification id "CSIP89".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP89_ID = "CSIP89";

  /**
   * Constant specification name for id "CSIP89".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP89_NAME = "Metadata division identifier";

  /**
   * Constant specification location for id "CSIP89".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP89_LOCATION = "mets/structMap[@LABEL='CSIP']"
    + "/div/div[@LABEL='Metadata']/@ID";

  /**
   * Constant specification description for id "CSIP89".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP89_DESCRIPTION = "Mandatory, xml:id identifier "
    + "must be unique within the XML-document.";

  /**
   * Constant specification cardinality for id "CSIP89".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP89_CARDINALITY = "1..1";

  /**
   * Constant specification level for id "CSIP89".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP89_LEVEL = "MUST";

  /* CSIP90 */

  /**
   * Constant specification id "CSIP90".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP90_ID = "CSIP90";

  /**
   * Constant specification name for id "CSIP90".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP90_NAME = "Metadata division label";

  /**
   * Constant specification location for id "CSIP90".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP90_LOCATION = "mets/structMap[@LABEL='CSIP']"
    + "/div/div[@LABEL='Metadata']";

  /**
   * Constant specification description for id "CSIP90".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP90_DESCRIPTION = "The metadata division <div> "
    + "element’s @LABEL attribute value must be “Metadata”. ";

  /**
   * Constant specification cardinality for id "CSIP90".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP90_CARDINALITY = "1..1";

  /**
   * Constant specification level for id "CSIP90".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP90_LEVEL = "MUST";

  /* CSIP91 */

  /**
   * Constant specification id "CSIP91".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP91_ID = "CSIP91";

  /**
   * Constant specification name for id "CSIP91".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP91_NAME = "Metadata division "
    + "administrative metadata referencing";

  /**
   * Constant specification location for id "CSIP91".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP91_LOCATION = "mets/structMap[@LABEL='CSIP'"
    + "/div/div[@LABEL='Metadata']/@ADMID";

  /**
   * Constant specification description for id "CSIP91".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP91_DESCRIPTION = "When there "
    + "is administrative metadata " + "and the amdSec is present, " + "all administrative metadata "
    + "MUST be referenced via the administrative sections different identifiers."
    + "All of the <amdSec> identifiers are listed in a " + "single @ADMID using spaces as delimiters.";

  /**
   * Constant specification cardinality for id "CSIP91".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP91_CARDINALITY = "0..1";

  /**
   * Constant specification level for id "CSIP91".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP91_LEVEL = "SHOULD";

  /* CSIP92 */

  /**
   * Constant specification id "CSIP92".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP92_ID = "CSIP92";

  /**
   * Constant specification name for id "CSIP92".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP92_NAME = "Metadata division descriptive "
    + "metadata referencing";

  /**
   * Constant specification location for id "CSIP92".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP92_LOCATION = "mets/structMap[@LABEL='CSIP']"
    + "/div/div[@LABEL='Metadata']/@DMDID";

  /**
   * Constant specification description for id "CSIP92".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP92_DESCRIPTION = "When there are descriptive "
    + "metadata and one or more dmdSec is present, "
    + "all descriptive metadata MUST be referenced via the descriptive section identifiers. "
    + "Every <dmdSec> identifier is listed in a single " + "@DMDID attribute using spaces as delimiters.";

  /**
   * Constant specification cardinality for id "CSIP92".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP92_CARDINALITY = "0..1";

  /**
   * Constant specification level for id "CSIP92".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP92_LEVEL = "SHOULD";

  /* CSIP93 */

  /**
   * Constant specification id "CSIP93".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP93_ID = "CSIP93";

  /**
   * Constant specification name for id "CSIP93".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP93_NAME = "Documentation division";

  /**
   * Constant specification location for id "CSIP93".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP93_LOCATION = "mets/structMap[@LABEL='CSIP']"
    + "/div/div[@LABEL='Documentation']";

  /**
   * Constant specification description for id "CSIP93".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP93_DESCRIPTION = "The documentation referenced "
    + "in the file section file groups " + "is described in the structural map with one sub division.";

  /**
   * Constant specification cardinality for id "CSIP93".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP93_CARDINALITY = "0..1";

  /**
   * Constant specification level for id "CSIP93".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP93_LEVEL = "SHOULD";

  /* CSIP94 */

  /**
   * Constant specification id "CSIP94".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP94_ID = "CSIP94";

  /**
   * Constant specification name for id "CSIP94".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP94_NAME = "Documentation division identifier";

  /**
   * Constant specification location for id "CSIP94".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP94_LOCATION = "mets/structMap[@LABEL='CSIP']"
    + "/div/div[@LABEL='Documentation']/@ID";

  /**
   * Constant specification description for id "CSIP94".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP94_DESCRIPTION = "Mandatory, xml:id identifier "
    + "must be unique within the XML-document.";

  /**
   * Constant specification cardinality for id "CSIP94".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP94_CARDINALITY = "1..1";

  /**
   * Constant specification level for id "CSIP94".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP94_LEVEL = "MUST";

  /* CSIP95 */

  /**
   * Constant specification id "CSIP95".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP95_ID = "CSIP95";

  /**
   * Constant specification name for id "CSIP95".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP95_NAME = "Documentation division label";

  /**
   * Constant specification location for id "CSIP95".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP95_LOCATION = "mets/structMap[@LABEL='CSIP']"
    + "/div/div[@LABEL='Documentation']";

  /**
   * Constant specification description for id "CSIP95".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP95_DESCRIPTION = "The documentation division "
    + "<div> element in the package uses the value "
    + "'Documentation' from the vocabulary as the value for the @LABEL attribute.";

  /**
   * Constant specification cardinality for id "CSIP95".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP95_CARDINALITY = "1..1";

  /**
   * Constant specification level for id "CSIP95".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP95_LEVEL = "MUST";

  /* CSIP96 */

  /**
   * Constant specification id "CSIP96".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP96_ID = "CSIP96";

  /**
   * Constant specification name for id "CSIP96".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP96_NAME = "Documentation file referencing";

  /**
   * Constant specification location for id "CSIP96".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP96_LOCATION = "mets/structMap[@LABEL='CSIP']"
    + "/div/div[@LABEL='Documentation']/fptr";

  /**
   * Constant specification description for id "CSIP96".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP96_DESCRIPTION = "All file groups containing "
    + "documentation described in the package are " + "referenced via the relevant file group identifiers. "
    + "There SHOULD be one file group reference per <fptr> element.";

  /**
   * Constant specification cardinality for id "CSIP96".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP96_CARDINALITY = "0..n";

  /**
   * Constant specification level for id "CSIP96".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP96_LEVEL = "SHOULD";

  /* CSIP116 */

  /**
   * Constant specification id "CSIP116".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP116_ID = "CSIP116";

  /**
   * Constant specification name for id "CSIP116".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP116_NAME = "Documentation file "
    + "group reference pointer";

  /**
   * Constant specification location for id "CSIP116".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP116_LOCATION = "mets/structMap[@LABEL='CSIP']"
    + "/div/div[@LABEL='Documentation']/fptr/@FILEID";

  /**
   * Constant specification description for id "CSIP116".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP116_DESCRIPTION = "A reference, by ID, "
    + "to the “Documentation” file group. " + "Related to the requirements CSIP60 which describes the "
    + "“Documentation” file group and CSIP65 which describes the file group identifier.";

  /**
   * Constant specification cardinality for id "CSIP116".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP116_CARDINALITY = "1..1";

  /**
   * Constant specification level for id "CSIP116".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP116_LEVEL = "MUST";

  /* CSIP97 */

  /**
   * Constant specification id "CSIP97".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP97_ID = "CSIP97";

  /**
   * Constant specification name for id "CSIP97".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP97_NAME = "Schema division";

  /**
   * Constant specification location for id "CSIP97".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP97_LOCATION = "mets/structMap[@LABEL='CSIP']"
    + "/div/div[@LABEL='Schemas']";

  /**
   * Constant specification description for id "CSIP97".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP97_DESCRIPTION = "The schemas referenced "
    + "in the file section " + "file groups are described in the structural map within a single sub-division.";

  /**
   * Constant specification cardinality for id "CSIP97".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP97_CARDINALITY = "0..1";

  /**
   * Constant specification level for id "CSIP97".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP97_LEVEL = "SHOULD";

  /* CSIP98 */

  /**
   * Constant specification id "CSIP98".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP98_ID = "CSIP98";

  /**
   * Constant specification name for id "CSIP98".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP98_NAME = "Schema division identifier";

  /**
   * Constant specification location for id "CSIP98".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP98_LOCATION = "mets/structMap[@LABEL='CSIP']"
    + "/div/div[@LABEL='Schemas']/@ID";

  /**
   * Constant specification description for id "CSIP98".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP98_DESCRIPTION = "Mandatory, xml:id identifier "
    + "must be unique within the XML-document.";

  /**
   * Constant specification cardinality for id "CSIP98".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP98_CARDINALITY = "1..1";

  /**
   * Constant specification level for id "CSIP98".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP98_LEVEL = "MUST";

  /* CSIP99 */

  /**
   * Constant specification id "CSIP99".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP99_ID = "CSIP99";

  /**
   * Constant specification name for id "CSIP99".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP99_NAME = "Schema division label";

  /**
   * Constant specification location for id "CSIP99".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP99_LOCATION = "mets/structMap[@LABEL='CSIP']"
    + "/div/div[@LABEL='Schemas']";

  /**
   * Constant specification description for id "CSIP99".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP99_DESCRIPTION = "The schema division "
    + "<div> element’s @LABEL " + "attribute has the value “Schemas” from the vocabulary. ";

  /**
   * Constant specification cardinality for id "CSIP99".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP99_CARDINALITY = "1..1";

  /**
   * Constant specification level for id "CSIP99".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP99_LEVEL = "MUST";

  /* CSIP100 */

  /**
   * Constant specification id "CSIP100".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP100_ID = "CSIP100";

  /**
   * Constant specification name for id "CSIP100".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP100_NAME = "Schema file reference";

  /**
   * Constant specification location for id "CSIP100".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP100_LOCATION = "mets/structMap[@LABEL='CSIP']"
    + "/div/div[@LABEL='Schemas']/fptr";

  /**
   * Constant specification description for id "CSIP100".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP100_DESCRIPTION = "All file groups containing schemas "
    + "described in the package are referenced " + "via the relevant file group identifiers. "
    + "One file group reference per fptr-element";

  /**
   * Constant specification cardinality for id "CSIP100".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP100_CARDINALITY = "0..n";

  /**
   * Constant specification level for id "CSIP100".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP100_LEVEL = "SHOULD";

  /* CSIP118 */

  /**
   * Constant specification id "CSIP118".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP118_ID = "CSIP118";

  /**
   * Constant specification name for id "CSIP118".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP118_NAME = "Schema file group reference";

  /**
   * Constant specification location for id "CSIP118".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP118_LOCATION = "mets/structMap[@LABEL='CSIP']"
    + "/div/div[@LABEL='Schemas']/fptr/@FILEID";

  /**
   * Constant specification description for id "CSIP118".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP118_DESCRIPTION = "The pointer to the "
    + "identifier for the “Schema” file group. " + "Related to the requirements CSIP113 which describes the “Schema” "
    + "file group and CSIP65 which describes the file group identifier.";

  /**
   * Constant specification cardinality for id "CSIP118".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP118_CARDINALITY = "1..1";

  /**
   * Constant specification level for id "CSIP118".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP118_LEVEL = "MUST";

  /* CSIP101 */

  /**
   * Constant specification id "CSIP101".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP101_ID = "CSIP101";

  /**
   * Constant specification name for id "CSIP101".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP101_NAME = "Content division";

  /**
   * Constant specification location for id "CSIP101".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP101_LOCATION = "mets/structMap[@LABEL='CSIP']"
    + "/div/div[@LABEL='Representations']";

  /**
   * Constant specification description for id "CSIP101".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP101_DESCRIPTION = "When no "
    + "representations are present the content " + "referenced in the file section file "
    + "group with @USE attribute value "
    + "“Representations” is described in the structural map as a single sub division.";

  /**
   * Constant specification cardinality for id "CSIP101".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP101_CARDINALITY = "0..1";

  /**
   * Constant specification level for id "CSIP101".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP101_LEVEL = "SHOULD";

  /* CSIP102 */

  /**
   * Constant specification id "CSIP102".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP102_ID = "CSIP102";

  /**
   * Constant specification name for id "CSIP102".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP102_NAME = "Content division identifier";

  /**
   * Constant specification location for id "CSIP102".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP102_LOCATION = "mets/structMap[@LABEL='CSIP']"
    + "/div/div[@LABEL='Representations']/@ID";

  /**
   * Constant specification description for id "CSIP102".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP102_DESCRIPTION = "Mandatory, xml:id "
    + "identifier must be unique within the XML-document.";

  /**
   * Constant specification cardinality for id "CSIP102".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP102_CARDINALITY = "1..1";

  /**
   * Constant specification level for id "CSIP102".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP102_LEVEL = "MUST";

  /* CSIP103 */

  /**
   * Constant specification id "CSIP103".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP103_ID = "CSIP103";

  /**
   * Constant specification name for id "CSIP103".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP103_NAME = "Content division label";

  /**
   * Constant specification location for id "CSIP103".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP103_LOCATION = "mets/structMap[@LABEL='CSIP']"
    + "/div/div[@LABEL='Representations']";

  /**
   * Constant specification description for id "CSIP103".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP103_DESCRIPTION = "The package’s content division"
    + " <div> element must have the " + "@LABEL attribute value “Representations”, taken from the vocabulary.";

  /**
   * Constant specification cardinality for id "CSIP103".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP103_CARDINALITY = "1..1";

  /**
   * Constant specification level for id "CSIP103".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP103_LEVEL = "MUST";

  /* CSIP104 */

  /**
   * Constant specification id "CSIP104".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP104_ID = "CSIP104";

  /**
   * Constant specification name for id "CSIP104".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP104_NAME = "Content division file references";

  /**
   * Constant specification location for id "CSIP104".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP104_LOCATION = "mets/structMap[@LABEL='CSIP']"
    + "/div/div[@LABEL='Representations']/fptr";

  /**
   * Constant specification description for id "CSIP104".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP104_DESCRIPTION = "All file groups containing content"
    + " described in the package are referenced "
    + "via the relevant file group identifiers. One file group reference per fptr-element.";

  /**
   * Constant specification cardinality for id "CSIP104".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP104_CARDINALITY = "0..n";

  /**
   * Constant specification level for id "CSIP104".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP104_LEVEL = "SHOULD";

  /* CSIP119 */

  /**
   * Constant specification id "CSIP119".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP119_ID = "CSIP119";

  /**
   * Constant specification name for id "CSIP119".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP119_NAME = "Content division file group references";

  /**
   * Constant specification location for id "CSIP119".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP119_LOCATION = "mets/structMap[@LABEL='CSIP']"
    + "/div/div[@LABEL='Representations']/fptr/@FILEID";

  /**
   * Constant specification description for id "CSIP119".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP119_DESCRIPTION = "The pointer to the identifier "
    + "for the “Representations” file group. " + "Related to the requirements CSIP114 which describes the "
    + "“Representations” file group and CSIP65 which describes the file group identifier.";

  /**
   * Constant specification cardinality for id "CSIP119".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP119_CARDINALITY = "1..1";

  /**
   * Constant specification level for id "CSIP119".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP119_LEVEL = "MUST";

  /* CSIP105 */

  /**
   * Constant specification id "CSIP105".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP105_ID = "CSIP105";

  /**
   * Constant specification name for id "CSIP105".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP105_NAME = "Representation division";

  /**
   * Constant specification location for id "CSIP105".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP105_LOCATION = "mets/structMap[@LABEL='CSIP']/div/div";

  /**
   * Constant specification description for id "CSIP105".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP105_DESCRIPTION = "When a package consists of "
    + "multiple representations, each described by a representation " + "level METS.xml document,"
    + " there is a discrete representation div element for each representation. "
    + "Each representation div references the representation level METS.xml document, "
    + "documenting the structure of the package and its constituent representations.";

  /**
   * Constant specification cardinality for id "CSIP105".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP105_CARDINALITY = "0..n";

  /**
   * Constant specification level for id "CSIP105".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP105_LEVEL = "SHOULD";

  /* CSIP106 */

  /**
   * Constant specification id "CSIP106".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP106_ID = "CSIP106";

  /**
   * Constant specification name for id "CSIP106".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP106_NAME = "Representations division identifier";

  /**
   * Constant specification location for id "CSIP106".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP106_LOCATION = "mets/structMap"
    + "[@LABEL='CSIP']/div/div/@ID";

  /**
   * Constant specification description for id "CSIP106".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP106_DESCRIPTION = "Mandatory, xml:id "
    + "identifier must be unique within the XML-document.";

  /**
   * Constant specification cardinality for id "CSIP106".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP106_CARDINALITY = "1..1";

  /**
   * Constant specification level for id "CSIP106".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP106_LEVEL = "MUST";

  /* CSIP107 */

  /**
   * Constant specification id "CSIP107".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP107_ID = "CSIP107";

  /**
   * Constant specification name for id "CSIP107".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP107_NAME = "Representations division label";

  /**
   * Constant specification location for id "CSIP107".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP107_LOCATION = "mets/structMap"
    + "[@LABEL='CSIP']/div/div/@LABEL";

  /**
   * Constant specification description for id "CSIP107".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP107_DESCRIPTION = "The package’s representation " +
    "division <div> element @LABEL attribute value must be the path to the " +
    "representation level METS document starting with the value “Representations” " +
    "followed by the main folder name for example “Representations/submission” and “Representations/ingest”.\n" +
    "This requirement gives the same value to be used as the requirement " +
    "named “Description of the use of the file group” (CSIP64)";

  /**
   * Constant specification cardinality for id "CSIP107".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP107_CARDINALITY = "1..1";

  /**
   * Constant specification level for id "CSIP107".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP107_LEVEL = "MUST";

  /* CSIP108 */

  /**
   * Constant specification id "CSIP108".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP108_ID = "CSIP108";

  /**
   * Constant specification name for id "CSIP108".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP108_NAME = "Representations division file referencing";

  /**
   * Constant specification location for id "CSIP108".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP108_LOCATION = "mets/structMap[@LABEL='CSIP']"
    + "/div/div/mptr/@xlink:title";

  /**
   * Constant specification description for id "CSIP108".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP108_DESCRIPTION = "The file group containing "
    + "the files described in the package are " + "referenced via the relevant file group identifier. "
    + "Related to the requirements CSIP114 which describes the “Representations” "
    + "file group and CSIP65 which describes the file group identifier.";

  /**
   * Constant specification cardinality for id "CSIP108".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP108_CARDINALITY = "1..1";

  /**
   * Constant specification level for id "CSIP108".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP108_LEVEL = "MUST";

  /* CSIP109 */

  /**
   * Constant specification id "CSIP109".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP109_ID = "CSIP109";

  /**
   * Constant specification name for id "CSIP109".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP109_NAME = "Representation METS pointer";

  /**
   * Constant specification location for id "CSIP109".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP109_LOCATION = "mets/structMap"
    + "[@LABEL='CSIP']/div/div/mptr";

  /**
   * Constant specification description for id "CSIP109".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP109_DESCRIPTION = "The division <div> "
    + "of the specific representation includes one occurrence of the "
    + "METS pointer <mptr> element, pointing to the appropriate representation METS file.";

  /**
   * Constant specification cardinality for id "CSIP109".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP109_CARDINALITY = "1..1";

  /**
   * Constant specification level for id "CSIP109".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP109_LEVEL = "MUST";

  /* CSIP110 */

  /**
   * Constant specification id "CSIP110".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP110_ID = "CSIP110";

  /**
   * Constant specification name for id "CSIP110".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP110_NAME = "Resource location";

  /**
   * Constant specification location for id "CSIP110".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP110_LOCATION = "mets/structMap/div"
    + "/div/mptr/@xlink:href";

  /**
   * Constant specification description for id "CSIP110".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP110_DESCRIPTION = "The actual location "
    + "of the resource. We recommend recording a " + "URL type filepath within this attribute.";

  /**
   * Constant specification cardinality for id "CSIP110".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP110_CARDINALITY = "1..1";

  /**
   * Constant specification level for id "CSIP110".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP110_LEVEL = "MUST";

  /* CSIP111 */

  /**
   * Constant specification id "CSIP111".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP111_ID = "CSIP111";

  /**
   * Constant specification name for id "CSIP111".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP111_NAME = "Type of link";

  /**
   * Constant specification location for id "CSIP111".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP111_LOCATION = "mets/structMap/div/div"
    + "/mptr[@xlink:type='simple']";

  /**
   * Constant specification description for id "CSIP111".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP111_DESCRIPTION = "Attribute used with the value "
    + "“simple”. Value list is maintained by the xlink standard.";

  /**
   * Constant specification cardinality for id "CSIP111".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP111_CARDINALITY = "1..1";

  /**
   * Constant specification level for id "CSIP111".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP111_LEVEL = "MUST";

  /* CSIP112 */

  /**
   * Constant specification id "CSIP112".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP112_ID = "CSIP112";

  /**
   * Constant specification name for id "CSIP112".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP112_NAME = "Type of locator";

  /**
   * Constant specification location for id "CSIP112".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP112_LOCATION = "mets/structMap/div"
    + "/div/mptr[@LOCTYPE='URL']";

  /**
   * Constant specification description for id "CSIP112".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP112_DESCRIPTION = "The locator type is always used"
    + " with the value “URL” from the vocabulary in the attribute.";

  /**
   * Constant specification cardinality for id "CSIP112".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP112_CARDINALITY = "1..1";

  /**
   * Constant specification level for id "CSIP112".
   */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP112_LEVEL = "MUST";

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
    if (VALIDATION_REPORT_SPECIFICATION_CSIP0_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP0_NAME;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIPSTR1_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIPSTR1_NAME;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIPSTR2_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIPSTR2_NAME;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIPSTR3_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIPSTR3_NAME;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIPSTR4_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIPSTR4_NAME;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIPSTR5_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIPSTR5_NAME;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIPSTR6_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIPSTR6_NAME;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIPSTR7_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIPSTR7_NAME;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIPSTR8_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIPSTR8_NAME;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIPSTR9_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIPSTR9_NAME;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIPSTR10_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIPSTR10_NAME;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIPSTR11_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIPSTR11_NAME;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIPSTR12_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIPSTR12_NAME;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIPSTR13_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIPSTR13_NAME;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIPSTR14_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIPSTR14_NAME;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIPSTR15_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIPSTR15_NAME;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIPSTR16_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIPSTR16_NAME;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP1_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP1_NAME;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP2_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP2_NAME;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP3_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP3_NAME;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP4_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP4_NAME;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP5_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP5_NAME;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP6_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP6_NAME;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP117_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP117_NAME;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP7_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP7_NAME;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP8_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP8_NAME;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP9_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP9_NAME;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP10_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP10_NAME;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP11_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP11_NAME;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP12_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP12_NAME;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP13_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP13_NAME;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP14_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP14_NAME;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP15_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP15_NAME;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP16_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP16_NAME;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP17_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP17_NAME;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP18_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP18_NAME;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP19_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP19_NAME;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP20_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP20_NAME;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP21_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP21_NAME;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP22_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP22_NAME;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP23_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP23_NAME;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP24_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP24_NAME;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP25_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP25_NAME;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP26_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP26_NAME;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP27_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP27_NAME;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP28_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP28_NAME;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP29_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP29_NAME;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP30_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP30_NAME;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP31_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP31_NAME;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP32_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP32_NAME;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP33_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP33_NAME;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP34_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP34_NAME;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP35_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP35_NAME;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP36_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP36_NAME;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP37_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP37_NAME;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP38_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP38_NAME;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP39_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP39_NAME;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP40_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP40_NAME;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP41_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP41_NAME;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP42_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP42_NAME;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP43_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP43_NAME;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP44_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP44_NAME;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP45_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP45_NAME;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP46_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP46_NAME;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP47_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP47_NAME;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP48_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP48_NAME;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP49_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP49_NAME;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP50_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP50_NAME;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP51_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP51_NAME;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP52_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP52_NAME;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP53_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP53_NAME;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP54_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP54_NAME;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP55_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP55_NAME;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP56_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP56_NAME;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP57_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP57_NAME;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP58_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP58_NAME;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP59_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP59_NAME;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP60_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP60_NAME;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP113_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP113_NAME;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP114_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP114_NAME;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP61_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP61_NAME;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP62_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP62_NAME;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP63_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP63_NAME;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP64_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP64_NAME;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP65_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP65_NAME;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP66_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP66_NAME;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP67_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP67_NAME;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP68_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP68_NAME;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP69_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP69_NAME;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP70_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP70_NAME;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP71_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP71_NAME;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP72_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP72_NAME;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP73_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP73_NAME;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP74_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP74_NAME;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP75_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP75_NAME;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP76_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP76_NAME;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP77_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP77_NAME;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP78_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP78_NAME;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP79_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP79_NAME;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP80_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP80_NAME;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP81_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP81_NAME;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP82_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP82_NAME;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP83_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP83_NAME;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP84_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP84_NAME;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP85_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP85_NAME;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP88_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP88_NAME;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP89_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP89_NAME;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP90_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP90_NAME;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP91_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP91_NAME;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP92_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP92_NAME;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP93_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP93_NAME;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP94_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP94_NAME;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP95_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP95_NAME;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP96_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP96_NAME;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP116_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP116_NAME;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP97_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP97_NAME;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP98_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP98_NAME;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP99_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP99_NAME;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP100_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP100_NAME;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP118_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP118_NAME;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP101_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP101_NAME;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP102_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP102_NAME;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP103_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP103_NAME;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP104_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP104_NAME;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP119_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP119_NAME;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP105_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP105_NAME;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP106_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP106_NAME;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP107_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP107_NAME;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP108_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP108_NAME;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP109_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP109_NAME;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP110_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP110_NAME;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP111_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP111_NAME;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP112_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP112_NAME;
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
    if (VALIDATION_REPORT_SPECIFICATION_CSIP0_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP0_LOCATION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIPSTR1_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIPSTR1_LOCATION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIPSTR2_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIPSTR2_LOCATION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIPSTR3_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIPSTR3_LOCATION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIPSTR4_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIPSTR4_LOCATION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIPSTR5_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIPSTR5_LOCATION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIPSTR6_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIPSTR6_LOCATION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIPSTR7_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIPSTR7_LOCATION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIPSTR8_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIPSTR8_LOCATION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIPSTR9_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIPSTR9_LOCATION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIPSTR10_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIPSTR10_LOCATION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIPSTR11_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIPSTR11_LOCATION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIPSTR12_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIPSTR12_LOCATION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIPSTR13_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIPSTR13_LOCATION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIPSTR14_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIPSTR14_LOCATION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIPSTR15_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIPSTR15_LOCATION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIPSTR16_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIPSTR16_LOCATION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP1_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP1_LOCATION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP2_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP2_LOCATION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP3_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP3_LOCATION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP4_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP4_LOCATION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP5_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP5_LOCATION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP6_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP6_LOCATION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP117_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP117_LOCATION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP7_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP7_LOCATION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP8_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP8_LOCATION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP9_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP9_LOCATION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP10_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP10_LOCATION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP11_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP11_LOCATION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP12_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP12_LOCATION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP13_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP13_LOCATION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP14_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP14_LOCATION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP15_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP15_LOCATION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP16_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP16_LOCATION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP17_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP17_LOCATION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP18_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP18_LOCATION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP19_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP19_LOCATION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP20_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP20_LOCATION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP21_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP21_LOCATION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP22_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP22_LOCATION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP23_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP23_LOCATION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP24_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP24_LOCATION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP25_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP25_LOCATION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP26_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP26_LOCATION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP27_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP27_LOCATION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP28_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP28_LOCATION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP29_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP29_LOCATION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP30_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP30_LOCATION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP31_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP31_LOCATION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP32_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP32_LOCATION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP33_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP33_LOCATION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP34_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP34_LOCATION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP35_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP35_LOCATION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP36_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP36_LOCATION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP37_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP37_LOCATION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP38_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP38_LOCATION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP39_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP39_LOCATION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP40_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP40_LOCATION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP41_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP41_LOCATION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP42_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP42_LOCATION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP43_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP43_LOCATION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP44_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP44_LOCATION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP45_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP45_LOCATION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP46_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP46_LOCATION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP47_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP47_LOCATION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP48_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP48_LOCATION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP49_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP49_LOCATION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP50_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP50_LOCATION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP51_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP51_LOCATION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP52_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP52_LOCATION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP53_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP53_LOCATION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP54_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP54_LOCATION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP55_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP55_LOCATION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP56_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP56_LOCATION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP57_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP57_LOCATION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP58_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP58_LOCATION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP59_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP59_LOCATION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP60_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP60_LOCATION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP113_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP113_LOCATION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP114_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP114_LOCATION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP61_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP61_LOCATION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP62_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP62_LOCATION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP63_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP63_LOCATION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP64_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP64_LOCATION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP65_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP65_LOCATION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP66_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP66_LOCATION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP67_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP67_LOCATION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP68_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP68_LOCATION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP69_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP69_LOCATION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP70_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP70_LOCATION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP71_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP71_LOCATION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP72_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP72_LOCATION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP73_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP73_LOCATION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP74_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP74_LOCATION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP75_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP75_LOCATION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP76_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP76_LOCATION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP77_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP77_LOCATION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP78_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP78_LOCATION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP79_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP79_LOCATION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP80_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP80_LOCATION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP81_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP81_LOCATION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP82_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP82_LOCATION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP83_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP83_LOCATION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP84_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP84_LOCATION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP85_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP85_LOCATION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP88_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP88_LOCATION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP89_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP89_LOCATION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP90_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP90_LOCATION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP91_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP91_LOCATION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP92_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP92_LOCATION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP93_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP93_LOCATION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP94_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP94_LOCATION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP95_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP95_LOCATION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP96_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP96_LOCATION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP116_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP116_LOCATION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP97_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP97_LOCATION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP98_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP98_LOCATION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP99_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP99_LOCATION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP100_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP100_LOCATION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP118_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP118_LOCATION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP101_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP101_LOCATION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP102_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP102_LOCATION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP103_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP103_LOCATION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP104_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP104_LOCATION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP119_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP119_LOCATION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP105_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP105_LOCATION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP106_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP106_LOCATION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP107_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP107_LOCATION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP108_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP108_LOCATION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP109_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP109_LOCATION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP110_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP110_LOCATION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP111_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP111_LOCATION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP112_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP112_LOCATION;
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
    if (VALIDATION_REPORT_SPECIFICATION_CSIP0_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP0_DESCRIPTION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIPSTR1_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIPSTR1_DESCRIPTION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIPSTR2_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIPSTR2_DESCRIPTION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIPSTR3_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIPSTR3_DESCRIPTION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIPSTR4_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIPSTR4_DESCRIPTION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIPSTR5_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIPSTR5_DESCRIPTION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIPSTR6_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIPSTR6_DESCRIPTION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIPSTR7_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIPSTR7_DESCRIPTION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIPSTR8_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIPSTR8_DESCRIPTION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIPSTR9_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIPSTR9_DESCRIPTION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIPSTR10_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIPSTR10_DESCRIPTION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIPSTR11_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIPSTR11_DESCRIPTION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIPSTR12_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIPSTR12_DESCRIPTION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIPSTR13_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIPSTR13_DESCRIPTION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIPSTR14_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIPSTR14_DESCRIPTION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIPSTR15_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIPSTR15_DESCRIPTION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIPSTR16_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIPSTR16_DESCRIPTION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP1_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP1_DESCRIPTION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP2_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP2_DESCRIPTION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP3_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP3_DESCRIPTION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP4_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP4_DESCRIPTION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP5_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP5_DESCRIPTION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP6_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP6_DESCRIPTION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP117_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP117_DESCRIPTION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP7_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP7_DESCRIPTION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP8_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP8_DESCRIPTION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP9_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP9_DESCRIPTION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP10_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP10_DESCRIPTION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP11_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP11_DESCRIPTION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP12_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP12_DESCRIPTION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP13_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP13_DESCRIPTION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP14_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP14_DESCRIPTION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP15_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP15_DESCRIPTION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP16_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP16_DESCRIPTION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP17_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP17_DESCRIPTION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP18_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP18_DESCRIPTION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP19_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP19_DESCRIPTION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP20_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP20_DESCRIPTION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP21_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP21_DESCRIPTION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP22_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP22_DESCRIPTION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP23_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP23_DESCRIPTION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP24_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP24_DESCRIPTION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP25_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP25_DESCRIPTION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP26_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP26_DESCRIPTION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP27_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP27_DESCRIPTION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP28_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP28_DESCRIPTION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP29_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP29_DESCRIPTION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP30_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP30_DESCRIPTION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP31_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP31_DESCRIPTION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP32_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP32_DESCRIPTION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP33_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP33_DESCRIPTION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP34_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP34_DESCRIPTION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP35_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP35_DESCRIPTION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP36_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP36_DESCRIPTION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP37_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP37_DESCRIPTION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP38_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP38_DESCRIPTION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP39_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP39_DESCRIPTION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP40_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP40_DESCRIPTION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP41_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP41_DESCRIPTION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP42_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP42_DESCRIPTION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP43_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP43_DESCRIPTION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP44_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP44_DESCRIPTION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP45_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP45_DESCRIPTION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP46_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP46_DESCRIPTION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP47_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP47_DESCRIPTION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP48_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP48_DESCRIPTION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP49_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP49_DESCRIPTION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP50_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP50_DESCRIPTION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP51_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP51_DESCRIPTION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP52_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP52_DESCRIPTION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP53_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP53_DESCRIPTION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP54_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP54_DESCRIPTION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP55_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP55_DESCRIPTION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP56_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP56_DESCRIPTION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP57_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP57_DESCRIPTION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP58_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP58_DESCRIPTION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP59_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP59_DESCRIPTION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP60_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP60_DESCRIPTION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP113_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP113_DESCRIPTION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP114_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP114_DESCRIPTION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP61_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP61_DESCRIPTION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP62_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP62_DESCRIPTION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP63_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP63_DESCRIPTION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP64_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP64_DESCRIPTION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP65_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP65_DESCRIPTION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP66_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP66_DESCRIPTION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP67_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP67_DESCRIPTION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP68_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP68_DESCRIPTION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP69_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP69_DESCRIPTION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP70_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP70_DESCRIPTION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP71_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP71_DESCRIPTION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP72_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP72_DESCRIPTION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP73_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP73_DESCRIPTION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP74_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP74_DESCRIPTION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP75_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP75_DESCRIPTION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP76_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP76_DESCRIPTION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP77_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP77_DESCRIPTION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP78_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP78_DESCRIPTION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP79_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP79_DESCRIPTION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP80_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP80_DESCRIPTION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP81_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP81_DESCRIPTION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP82_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP82_DESCRIPTION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP83_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP83_DESCRIPTION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP84_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP84_DESCRIPTION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP85_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP85_DESCRIPTION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP88_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP88_DESCRIPTION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP89_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP89_DESCRIPTION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP90_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP90_DESCRIPTION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP91_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP91_DESCRIPTION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP92_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP92_DESCRIPTION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP93_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP93_DESCRIPTION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP94_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP94_DESCRIPTION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP95_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP95_DESCRIPTION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP96_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP96_DESCRIPTION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP116_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP116_DESCRIPTION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP97_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP97_DESCRIPTION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP98_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP98_DESCRIPTION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP99_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP99_DESCRIPTION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP100_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP100_DESCRIPTION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP118_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP118_DESCRIPTION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP101_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP101_DESCRIPTION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP102_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP102_DESCRIPTION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP103_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP103_DESCRIPTION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP104_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP104_DESCRIPTION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP119_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP119_DESCRIPTION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP105_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP105_DESCRIPTION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP106_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP106_DESCRIPTION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP107_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP107_DESCRIPTION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP108_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP108_DESCRIPTION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP109_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP109_DESCRIPTION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP110_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP110_DESCRIPTION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP111_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP111_DESCRIPTION;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP112_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP112_DESCRIPTION;
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
    if (VALIDATION_REPORT_SPECIFICATION_CSIP0_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP0_CARDINALITY;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIPSTR1_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIPSTR1_CARDINALITY;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIPSTR2_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIPSTR2_CARDINALITY;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIPSTR3_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIPSTR3_CARDINALITY;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIPSTR4_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIPSTR4_CARDINALITY;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIPSTR5_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIPSTR5_CARDINALITY;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIPSTR6_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIPSTR6_CARDINALITY;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIPSTR7_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIPSTR7_CARDINALITY;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIPSTR8_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIPSTR8_CARDINALITY;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIPSTR9_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIPSTR9_CARDINALITY;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIPSTR10_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIPSTR10_CARDINALITY;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIPSTR11_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIPSTR11_CARDINALITY;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIPSTR12_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIPSTR12_CARDINALITY;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIPSTR13_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIPSTR13_CARDINALITY;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIPSTR14_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIPSTR14_CARDINALITY;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIPSTR15_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIPSTR15_CARDINALITY;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIPSTR16_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIPSTR16_CARDINALITY;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP1_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP1_CARDINALITY;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP2_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP2_CARDINALITY;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP3_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP3_CARDINALITY;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP4_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP4_CARDINALITY;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP5_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP5_CARDINALITY;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP6_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP6_CARDINALITY;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP117_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP117_CARDINALITY;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP7_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP7_CARDINALITY;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP8_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP8_CARDINALITY;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP9_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP9_CARDINALITY;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP10_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP10_CARDINALITY;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP11_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP11_CARDINALITY;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP12_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP12_CARDINALITY;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP13_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP13_CARDINALITY;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP14_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP14_CARDINALITY;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP15_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP15_CARDINALITY;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP16_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP16_CARDINALITY;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP17_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP17_CARDINALITY;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP18_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP18_CARDINALITY;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP19_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP19_CARDINALITY;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP20_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP20_CARDINALITY;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP21_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP21_CARDINALITY;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP22_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP22_CARDINALITY;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP23_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP23_CARDINALITY;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP24_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP24_CARDINALITY;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP25_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP25_CARDINALITY;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP26_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP26_CARDINALITY;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP27_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP27_CARDINALITY;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP28_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP28_CARDINALITY;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP29_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP29_CARDINALITY;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP30_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP30_CARDINALITY;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP31_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP31_CARDINALITY;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP32_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP32_CARDINALITY;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP33_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP33_CARDINALITY;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP34_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP34_CARDINALITY;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP35_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP35_CARDINALITY;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP36_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP36_CARDINALITY;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP37_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP37_CARDINALITY;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP38_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP38_CARDINALITY;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP39_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP39_CARDINALITY;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP40_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP40_CARDINALITY;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP41_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP41_CARDINALITY;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP42_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP42_CARDINALITY;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP43_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP43_CARDINALITY;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP44_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP44_CARDINALITY;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP45_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP45_CARDINALITY;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP46_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP46_CARDINALITY;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP47_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP47_CARDINALITY;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP48_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP48_CARDINALITY;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP49_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP49_CARDINALITY;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP50_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP50_CARDINALITY;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP51_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP51_CARDINALITY;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP52_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP52_CARDINALITY;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP53_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP53_CARDINALITY;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP54_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP54_CARDINALITY;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP55_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP55_CARDINALITY;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP56_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP56_CARDINALITY;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP57_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP57_CARDINALITY;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP58_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP58_CARDINALITY;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP59_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP59_CARDINALITY;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP60_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP60_CARDINALITY;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP113_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP113_CARDINALITY;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP114_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP114_CARDINALITY;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP61_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP61_CARDINALITY;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP62_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP62_CARDINALITY;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP63_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP63_CARDINALITY;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP64_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP64_CARDINALITY;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP65_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP65_CARDINALITY;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP66_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP66_CARDINALITY;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP67_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP67_CARDINALITY;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP68_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP68_CARDINALITY;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP69_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP69_CARDINALITY;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP70_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP70_CARDINALITY;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP71_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP71_CARDINALITY;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP72_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP72_CARDINALITY;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP73_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP73_CARDINALITY;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP74_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP74_CARDINALITY;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP75_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP75_CARDINALITY;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP76_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP76_CARDINALITY;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP77_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP77_CARDINALITY;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP78_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP78_CARDINALITY;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP79_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP79_CARDINALITY;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP80_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP80_CARDINALITY;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP81_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP81_CARDINALITY;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP82_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP82_CARDINALITY;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP83_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP83_CARDINALITY;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP84_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP84_CARDINALITY;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP85_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP85_CARDINALITY;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP88_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP88_CARDINALITY;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP89_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP89_CARDINALITY;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP90_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP90_CARDINALITY;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP91_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP91_CARDINALITY;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP92_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP92_CARDINALITY;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP93_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP93_CARDINALITY;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP94_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP94_CARDINALITY;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP95_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP95_CARDINALITY;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP96_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP96_CARDINALITY;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP116_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP116_CARDINALITY;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP97_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP97_CARDINALITY;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP98_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP98_CARDINALITY;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP99_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP99_CARDINALITY;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP100_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP100_CARDINALITY;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP118_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP118_CARDINALITY;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP101_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP101_CARDINALITY;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP102_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP102_CARDINALITY;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP103_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP103_CARDINALITY;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP104_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP104_CARDINALITY;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP119_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP119_CARDINALITY;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP105_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP105_CARDINALITY;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP106_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP106_CARDINALITY;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP107_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP107_CARDINALITY;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP108_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP108_CARDINALITY;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP109_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP109_CARDINALITY;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP110_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP110_CARDINALITY;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP111_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP111_CARDINALITY;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP112_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP112_CARDINALITY;
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
    if (VALIDATION_REPORT_SPECIFICATION_CSIP0_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP0_LEVEL;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIPSTR1_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIPSTR1_LEVEL;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIPSTR2_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIPSTR2_LEVEL;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIPSTR3_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIPSTR3_LEVEL;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIPSTR4_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIPSTR4_LEVEL;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIPSTR5_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIPSTR5_LEVEL;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIPSTR6_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIPSTR6_LEVEL;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIPSTR7_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIPSTR7_LEVEL;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIPSTR8_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIPSTR8_LEVEL;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIPSTR9_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIPSTR9_LEVEL;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIPSTR10_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIPSTR10_LEVEL;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIPSTR11_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIPSTR11_LEVEL;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIPSTR12_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIPSTR12_LEVEL;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIPSTR13_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIPSTR13_LEVEL;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIPSTR14_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIPSTR14_LEVEL;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIPSTR15_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIPSTR15_LEVEL;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIPSTR16_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIPSTR16_LEVEL;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP1_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP1_LEVEL;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP2_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP2_LEVEL;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP3_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP3_LEVEL;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP4_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP4_LEVEL;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP5_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP5_LEVEL;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP6_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP6_LEVEL;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP117_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP117_LEVEL;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP7_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP7_LEVEL;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP8_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP8_LEVEL;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP9_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP9_LEVEL;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP10_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP10_LEVEL;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP11_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP11_LEVEL;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP12_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP12_LEVEL;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP13_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP13_LEVEL;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP14_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP14_LEVEL;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP15_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP15_LEVEL;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP16_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP16_LEVEL;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP17_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP17_LEVEL;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP18_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP18_LEVEL;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP19_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP19_LEVEL;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP20_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP20_LEVEL;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP21_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP21_LEVEL;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP22_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP22_LEVEL;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP23_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP23_LEVEL;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP24_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP24_LEVEL;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP25_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP25_LEVEL;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP26_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP26_LEVEL;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP27_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP27_LEVEL;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP28_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP28_LEVEL;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP29_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP29_LEVEL;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP30_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP30_LEVEL;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP31_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP31_LEVEL;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP32_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP32_LEVEL;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP33_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP33_LEVEL;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP34_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP34_LEVEL;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP35_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP35_LEVEL;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP36_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP36_LEVEL;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP37_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP37_LEVEL;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP38_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP38_LEVEL;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP39_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP39_LEVEL;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP40_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP40_LEVEL;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP41_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP41_LEVEL;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP42_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP42_LEVEL;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP43_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP43_LEVEL;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP44_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP44_LEVEL;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP45_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP45_LEVEL;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP46_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP46_LEVEL;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP47_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP47_LEVEL;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP48_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP48_LEVEL;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP49_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP49_LEVEL;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP50_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP50_LEVEL;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP51_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP51_LEVEL;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP52_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP52_LEVEL;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP53_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP53_LEVEL;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP54_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP54_LEVEL;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP55_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP55_LEVEL;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP56_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP56_LEVEL;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP57_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP57_LEVEL;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP58_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP58_LEVEL;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP59_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP59_LEVEL;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP60_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP60_LEVEL;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP113_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP113_LEVEL;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP114_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP114_LEVEL;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP61_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP61_LEVEL;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP62_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP62_LEVEL;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP63_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP63_LEVEL;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP64_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP64_LEVEL;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP65_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP65_LEVEL;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP66_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP66_LEVEL;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP67_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP67_LEVEL;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP68_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP68_LEVEL;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP69_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP69_LEVEL;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP70_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP70_LEVEL;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP71_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP71_LEVEL;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP72_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP72_LEVEL;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP73_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP73_LEVEL;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP74_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP74_LEVEL;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP75_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP75_LEVEL;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP76_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP76_LEVEL;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP77_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP77_LEVEL;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP78_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP78_LEVEL;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP79_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP79_LEVEL;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP80_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP80_LEVEL;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP81_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP81_LEVEL;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP82_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP82_LEVEL;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP83_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP83_LEVEL;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP84_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP84_LEVEL;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP85_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP85_LEVEL;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP88_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP88_LEVEL;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP89_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP89_LEVEL;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP90_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP90_LEVEL;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP91_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP91_LEVEL;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP92_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP92_LEVEL;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP93_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP93_LEVEL;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP94_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP94_LEVEL;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP95_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP95_LEVEL;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP96_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP96_LEVEL;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP116_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP116_LEVEL;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP97_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP97_LEVEL;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP98_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP98_LEVEL;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP99_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP99_LEVEL;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP100_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP100_LEVEL;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP118_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP118_LEVEL;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP101_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP101_LEVEL;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP102_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP102_LEVEL;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP103_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP103_LEVEL;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP104_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP104_LEVEL;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP119_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP119_LEVEL;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP105_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP105_LEVEL;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP106_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP106_LEVEL;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP107_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP107_LEVEL;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP108_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP108_LEVEL;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP109_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP109_LEVEL;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP110_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP110_LEVEL;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP111_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP111_LEVEL;
    }
    if (VALIDATION_REPORT_SPECIFICATION_CSIP112_ID.equals(id)) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP112_LEVEL;
    }
    return NOT_DEFINED;
  }
}
