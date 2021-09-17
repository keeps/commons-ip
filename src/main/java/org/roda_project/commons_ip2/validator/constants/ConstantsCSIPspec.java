package org.roda_project.commons_ip2.validator.constants;

/**
 * @author João Gomes <jgomes@keep.pt>
 */
public class ConstantsCSIPspec {
  private ConstantsCSIPspec() {
  }

  /* Errors and ID's for constants in validation of sip path */
  public static final String VALIDATION_REPORT_PATH_DOES_NOT_EXIST_ID = "Path";
  public static final String VALIDATION_REPORT_PATH_DOES_NOT_EXIST_DETAIL = "The path does not exist or is not a directory or archive files";
  public static final String VALIDATION_REPORT_METS_NOT_FOUND_ID = "METS";
  public static final String VALIDATION_REPORT_METS_NOT_FOUND_DETAIL = "Root METS.xml not found";

  /* Use of the METS root element (element mets) */

  /* CSIPSTR0 - Invalid METS Schema */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIPSTR0_ID = "CSIPSTR0";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIPSTR0_NAME = "METS Schema";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIPSTR0_LOCATION = "";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIPSTR0_DESCRIPTION = "Validation of METS Schema";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIPSTR0_CARDINALITY = "";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIPSTR0_LEVEL = "MUST";

  /* CSIPSTR1 */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIPSTR1_ID = "CSIPSTR1";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIPSTR1_NAME = "CSIP Information Package folder structure";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIPSTR1_LOCATION = "";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIPSTR1_DESCRIPTION = "Any Information Package MUST be included within a single physical root folder (known as the “Information Package root folder”). For packages presented in an archive format, see CSIPSTR3, the archive MUST unpack to a single root folder.";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIPSTR1_CARDINALITY = "";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIPSTR1_LEVEL = "MUST";

  /* CSIPSTR2 */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIPSTR2_ID = "CSIPSTR2";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIPSTR2_NAME = "CSIP Information Package folder structure";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIPSTR2_LOCATION = "";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIPSTR2_DESCRIPTION = "The Information Package root folder SHOULD be named with the ID or name of the Information Package, that is the value of the package METS.xml’s root <mets> element’s @OBJID attribute.";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIPSTR2_CARDINALITY = "";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIPSTR2_LEVEL = "SHOULD";

  /* CSIPSTR3 */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIPSTR3_ID = "CSIPSTR3";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIPSTR3_NAME = "CSIP Information Package folder structure";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIPSTR3_LOCATION = "";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIPSTR3_DESCRIPTION = "The Information Package root folder MAY be compressed (for example by using TAR or ZIP). Which specific compression format to use needs to be stated in the Submission Agreement.";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIPSTR3_CARDINALITY = "";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIPSTR3_LEVEL = "MAY";

  /* CSIPSTR4 */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIPSTR4_ID = "CSIPSTR4";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIPSTR4_NAME = "CSIP Information Package folder structure";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIPSTR4_LOCATION = "";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIPSTR4_DESCRIPTION = "The Information Package root folder MUST include a file named METS.xml. This file MUST contain metadata that identifies the package, provides a high-level package description, and describes its structure, including pointers to constituent representations.";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIPSTR4_CARDINALITY = "";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIPSTR4_LEVEL = "MUST";

  /* CSIPSTR5 */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIPSTR5_ID = "CSIPSTR5";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIPSTR5_NAME = "CSIP Information Package folder structure";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIPSTR5_LOCATION = "";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIPSTR5_DESCRIPTION = "The Information Package root folder SHOULD include a folder named metadata, which SHOULD include metadata relevant to the whole package.";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIPSTR5_CARDINALITY = "";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIPSTR5_LEVEL = "SHOULD";

  /* CSIPSTR6 */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIPSTR6_ID = "CSIPSTR6";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIPSTR6_NAME = "CSIP Information Package folder structure";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIPSTR6_LOCATION = "";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIPSTR6_DESCRIPTION = "If preservation metadata are available, they SHOULD be included in sub-folder preservation.";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIPSTR6_CARDINALITY = "";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIPSTR6_LEVEL = "SHOULD";

  /* CSIPSTR7 */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIPSTR7_ID = "CSIPSTR7";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIPSTR7_NAME = "CSIP Information Package folder structure";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIPSTR7_LOCATION = "";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIPSTR7_DESCRIPTION = "If descriptive metadata are available, they SHOULD be included in sub-folder descriptive.";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIPSTR7_CARDINALITY = "";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIPSTR7_LEVEL = "SHOULD";

  /* CSIPSTR8 */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIPSTR8_ID = "CSIPSTR8";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIPSTR8_NAME = "CSIP Information Package folder structure";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIPSTR8_LOCATION = "";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIPSTR8_DESCRIPTION = "If any other metadata are available, they MAY be included in separate sub-folders, for example an additional folder named other.";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIPSTR8_CARDINALITY = "";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIPSTR8_LEVEL = "MAY";

  /* CSIPSTR9 */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIPSTR9_ID = "CSIPSTR9";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIPSTR9_NAME = "CSIP Information Package folder structure";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIPSTR9_LOCATION = "";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIPSTR9_DESCRIPTION = "The Information Package folder SHOULD include a folder named representations.";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIPSTR9_CARDINALITY = "";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIPSTR9_LEVEL = "SHOULD";

  /* CSIPSTR10 */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIPSTR10_ID = "CSIPSTR10";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIPSTR10_NAME = "CSIP Information Package folder structure";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIPSTR10_LOCATION = "";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIPSTR10_DESCRIPTION = "The representations folder SHOULD include a sub-folder for each individual representation (i.e. the “representation folder”). Each representation folder should have a string name that is unique within the package scope. For example the name of the representation and/or its creation date might be good candidates as a representation sub-folder name.";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIPSTR10_CARDINALITY = "";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIPSTR10_LEVEL = "SHOULD";

  /* CSIPSTR11 */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIPSTR11_ID = "CSIPSTR11";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIPSTR11_NAME = "CSIP Information Package folder structure";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIPSTR11_LOCATION = "";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIPSTR11_DESCRIPTION = "The representation folder SHOULD include a sub-folder named data which MAY include all data constituting the representation.";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIPSTR11_CARDINALITY = "";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIPSTR11_LEVEL = "SHOULD";

  /* CSIPSTR12 */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIPSTR12_ID = "CSIPSTR12";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIPSTR12_NAME = "CSIP Information Package folder structure";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIPSTR12_LOCATION = "";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIPSTR12_DESCRIPTION = "The representation folder SHOULD include a metadata file named METS.xml which includes information about the identity and structure of the representation and its components. The recommended best practice is to always have a METS.xml in the representation folder.";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIPSTR12_CARDINALITY = "";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIPSTR12_LEVEL = "SHOULD";

  /* CSIPSTR13 */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIPSTR13_ID = "CSIPSTR13";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIPSTR13_NAME = "CSIP Information Package folder structure";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIPSTR13_LOCATION = "";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIPSTR13_DESCRIPTION = "The representation folder SHOULD include a sub-folder named metadata which MAY include all metadata about the specific representation.";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIPSTR13_CARDINALITY = "";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIPSTR13_LEVEL = "SHOULD";

  /* CSIPSTR14 */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIPSTR14_ID = "CSIPSTR14";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIPSTR14_NAME = "CSIP Information Package folder structure";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIPSTR14_LOCATION = "";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIPSTR14_DESCRIPTION = "The Information Package MAY be extended with additional sub-folders.";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIPSTR14_CARDINALITY = "";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIPSTR14_LEVEL = "MAY";

  /* CSIPSTR15 */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIPSTR15_ID = "CSIPSTR15";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIPSTR15_NAME = "CSIP Information Package folder structure";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIPSTR15_LOCATION = "";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIPSTR15_DESCRIPTION = "We recommend including all XML schema documents for any structured metadata within package. These schema documents SHOULD be placed in a sub-folder called schemas within the Information Package root folder and/or the representation folder.";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIPSTR15_CARDINALITY = "";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIPSTR15_LEVEL = "SHOULD";

  /* CSIPSTR16 */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIPSTR16_ID = "CSIPSTR16";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIPSTR16_NAME = "CSIP Information Package folder structure";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIPSTR16_LOCATION = "";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIPSTR16_DESCRIPTION = "We recommend including any supplementary documentation for the package or a specific representation within the package. Supplementary documentation SHOULD be placed in a sub-folder called documentation within the Information Package root folder and/or the representation folder.";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIPSTR16_CARDINALITY = "";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIPSTR16_LEVEL = "SHOULD";

  /* CSIP1 */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP1_ID = "CSIP1";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP1_NAME = "Package Identifier";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP1_LOCATION = "mets/@OBJID";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP1_DESCRIPTION = "The mets/@OBJID attribute is mandatory, its value is a string identifier for "
    + "the METS document. For the package METS document, this should be the "
    + "name/ID of the package, i.e. the name of the package root folder. For a "
    + "representation level METS document this value records the name/ID of the "
    + "representation, i.e. the name of the top-level representation folder.";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP1_CARDINALITY = "1..1";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP1_LEVEL = "MUST";

  /* CSIP2 */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP2_ID = "CSIP2";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP2_NAME = "Content Category";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP2_LOCATION = "mets/@TYPE";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP2_DESCRIPTION = "The mets/@TYPE attribute MUST be used to declare the category of the "
    + "content held in the package, e.g. book, journal, stereograph, video, etc.. "
    + "Legal values are defined in a fixed vocabulary. When the content category "
    + "used falls outside of the defined vocabulary the mets/@TYPE value must be "
    + "set to “OTHER” and the specific value declared in mets/@csip:OTHERTYPE. "
    + "The vocabulary will develop under the curation of the DILCIS Board as "
    + " additional content information type specifications are produced.See also: Content Category";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP2_CARDINALITY = "0..1";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP2_LEVEL = "MUST";

  /* CSIP 3 */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP3_ID = "CSIP3";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP3_NAME = "Other Content Category";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP3_LOCATION = "mets[@TYPE=’OTHER’]/@csip:OTHERTYPE";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP3_DESCRIPTION = "When the mets/@TYPE attribute has the value “OTHER” the "
    + "mets/@csip:OTHERTYPE attribute MUST be used to declare the content "
    + "category of the package/representation.See also: Content Category ";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP3_CARDINALITY = "0..1";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP3_LEVEL = "SHOULD";

  /* CSIP 4 */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP4_ID = "CSIP4";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP4_NAME = "Content Information Type Specification";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP4_LOCATION = "mets/@csip:CONTENTINFORMATIONTYPE";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP4_DESCRIPTION = "Used to declare the Content Information Type Specification used when "
    + "creating the package. Legal values are defined in a fixed vocabulary. The "
    + "attribute is mandatory for representation level METS documents. The "
    + "vocabulary will evolve under the care of the DILCIS Board as additional "
    + "Content Information Type Specifications are developed.See also: Content " + "information type specification";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP4_CARDINALITY = "0..1";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP4_LEVEL = "SHOULD";

  /* CSIP 5 */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP5_ID = "CSIP5";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP5_NAME = "Other Content Information Type Specification";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP5_LOCATION = "mets[@csip:CONTENTINFORMATIONTYPE=’OTHER’]/@csip:OTHERCONTENTINFORMATIONTYPE";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP5_DESCRIPTION = "When the mets/@csip:CONTENTINFORMATIONTYPE has the value "
    + "'OTHER' the mets/@csip:OTHERCONTENTINFORMATIONTYPE must state " + "the content information type. ";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP5_CARDINALITY = "0..1";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP5_LEVEL = "MAY";

  /* CSIP 6 */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP6_ID = "CSIP6";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP6_NAME = "METS Profile";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP6_LOCATION = "mets/@PROFILE";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP6_DESCRIPTION = "The URL of the METS profile that the information package conforms with. ";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP6_CARDINALITY = "1..1";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP6_LEVEL = "MUST";

  /* Use of the METS header (element metsHdr) */
  /* CSIP117 */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP117_ID = "CSIP117";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP117_NAME = "Package header";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP117_LOCATION = "mets/metsHdr";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP117_DESCRIPTION = "General element for describing the package.";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP117_CARDINALITY = "1..1";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP117_LEVEL = "MUST";

  /* CSIP7 */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP7_ID = "CSIP7";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP7_NAME = "Package creation date";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP7_LOCATION = "mets/metsHdr/@CREATEDATE";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP7_DESCRIPTION = "mets/metsHdr/@CREATEDATE records the date the package was created.";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP7_CARDINALITY = "1..1";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP7_LEVEL = "MUST";

  /* CSIP8 */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP8_ID = "CSIP8";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP8_NAME = "Package last modification date";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP8_LOCATION = "mets/metsHdr/@LASTMODDATE";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP8_DESCRIPTION = "mets/metsHdr/@LASTMODDATE is mandatory when the package has been modified.";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP8_CARDINALITY = "0..1";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP8_LEVEL = "SHOULD";

  /* CSIP9 */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP9_ID = "CSIP9";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP9_NAME = "OAIS Package type information";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP9_LOCATION = "mets/metsHdr/@csip:OAISPACKAGETYPE";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP9_DESCRIPTION = "mets/metsHdr/@csip:OAISPACKAGETYPE is an additional CSIP attribute that declares the type of the IP.See also: OAIS Package type";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP9_CARDINALITY = "1..1";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP9_LEVEL = "MUST";

  /* CSIP10 */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP10_ID = "CSIP10";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP10_NAME = "Agent";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP10_LOCATION = "mets/metsHdr/agent";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP10_DESCRIPTION = "A mandatory agent element records the software used to create the package. Other uses of agents may be described in any local implementations that extend the profile.";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP10_CARDINALITY = "1..n";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP10_LEVEL = "MUST";

  /* CSIP11 */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP11_ID = "CSIP11";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP11_NAME = "Agent role";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP11_LOCATION = "mets/metsHdr/agent[@ROLE=’CREATOR’]";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP11_DESCRIPTION = "The mandatory agent element MUST have a @ROLE attribute with the value 'CREATOR'";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP11_CARDINALITY = "1..1";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP11_LEVEL = "MUST";

  /* CSIP12 */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP12_ID = "CSIP12";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP12_NAME = "Agent type";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP12_LOCATION = "mets/metsHdr/agent[@TYPE=’OTHER’]";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP12_DESCRIPTION = "The mandatory agent element MUST have a @TYPE attribute with the value 'OTHER'.";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP12_CARDINALITY = "1..1";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP12_LEVEL = "MUST";

  /* CSIP13 */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP13_ID = "CSIP13";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP13_NAME = "Agent other type";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP13_LOCATION = "mets/metsHdr/agent[@OTHERTYPE=’SOFTWARE’]";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP13_DESCRIPTION = "The mandatory agent element MUST have a @OTHERTYPE attribute with the value “SOFTWARE”.See also: Other agent type";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP13_CARDINALITY = "1..1";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP13_LEVEL = "MUST";

  /* CSIP14 */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP14_ID = "CSIP14";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP14_NAME = "Agent name";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP14_LOCATION = "mets/metsHdr/agent/name";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP14_DESCRIPTION = "The mandatory agent’s name element records the name of the software tool used to create the IP.";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP14_CARDINALITY = "1..1";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP14_LEVEL = "MUST";

  /* CSIP15 */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP15_ID = "CSIP15";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP15_NAME = "Agent additional information";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP15_LOCATION = "mets/metsHdr/agent/note";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP15_DESCRIPTION = "The mandatory agent’s note element records the version of the tool used to create the IP.";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP15_CARDINALITY = "1..1";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP15_LEVEL = "MUST";

  /* CSIP16 */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP16_ID = "CSIP16";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP16_NAME = "Classification of the agent additional information";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP16_LOCATION = "mets/metsHdr/agent/note[@csip:NOTETYPE=’SOFTWARE VERSION’]";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP16_DESCRIPTION = "The mandatory agent element’s note child has a @csip:NOTETYPE attribute with a fixed value of “SOFTWARE VERSION”.See also: Note type";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP16_CARDINALITY = "1..1";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP16_LEVEL = "MUST";

  /* Use of the METS descriptive metadata section (element dmdSec) */
  /* CSIP17 */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP17_ID = "CSIP17";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP17_NAME = "Descriptive metadata";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP17_LOCATION = "mets/dmdSec";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP17_DESCRIPTION = "Must be used if descriptive metadata for the package content is available. "
    + "Each descriptive metadata section ( <dmdSec> ) contains a single description "
    + "and must be repeated for multiple descriptions, when available. It is "
    + "possible to transfer metadata in a package using just the descriptive "
    + "metadata section and/or administrative metadata section.";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP17_CARDINALITY = "0..n";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP17_LEVEL = "SHOULD";

  /* CSIP18 */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP18_ID = "CSIP18";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP18_NAME = "Descriptive metadata identifier";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP18_LOCATION = "mets/dmdSec/@ID";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP18_DESCRIPTION = "An xml:id identifier for the descriptive metadata section ( <dmdSec> ) used for internal package references. It must be unique within the package.";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP18_CARDINALITY = "1..1";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP18_LEVEL = "MUST";

  /* CSIP19 */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP19_ID = "CSIP19";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP19_NAME = "Descriptive metadata creation date";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP19_LOCATION = "mets/dmdSec/@CREATED";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP19_DESCRIPTION = "Creation date of the descriptive metadata in this section.";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP19_CARDINALITY = "1..1";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP19_LEVEL = "MUST";

  /* CSIP20 */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP20_ID = "CSIP20";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP20_NAME = "Status of the descriptive metadata";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP20_LOCATION = "mets/dmdSec/@STATUS";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP20_DESCRIPTION = "Indicates the status of the package using a fixed vocabulary.See also: dmdSec status";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP20_CARDINALITY = "0..1";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP20_LEVEL = "SHOULD";

  /* CSIP21 */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP21_ID = "CSIP21";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP21_NAME = "Reference to the document with the descriptive metadata";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP21_LOCATION = "mets/dmdSec/mdRef";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP21_DESCRIPTION = "Reference to the descriptive metadata file located in the “metadata” section of the IP.";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP21_CARDINALITY = "0..1";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP21_LEVEL = "SHOULD";

  /* CSIP22 */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP22_ID = "CSIP22";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP22_NAME = "Type of locator";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP22_LOCATION = "mets/dmdSec/mdRef";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP22_DESCRIPTION = "The locator type is always used with the value “URL” from the vocabulary in the attribute.";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP22_CARDINALITY = "1..1";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP22_LEVEL = "MUST";

  /* CSIP23 */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP23_ID = "CSIP23";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP23_NAME = "Type of link";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP23_LOCATION = "mets/dmdSec/mdRef[@xlink:type=’simple’]";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP23_DESCRIPTION = "Attribute used with the value “simple”. Value list is maintained by the xlink standard.";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP23_CARDINALITY = "1..1";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP23_LEVEL = "MUST";

  /* CSIP24 */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP24_ID = "CSIP24";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP24_NAME = "Resource location";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP24_LOCATION = "mets/dmdSec/mdRef/@xlink:href";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP24_DESCRIPTION = "The actual location of the resource. This specification recommends recording a URL type filepath in this attribute.";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP24_CARDINALITY = "1..1";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP24_LEVEL = "MUST";

  /* CSIP25 */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP25_ID = "CSIP25";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP25_NAME = "Type of metadata";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP25_LOCATION = "mets/dmdSec/mdRef/@MDTYPE";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP25_DESCRIPTION = "Specifies the type of metadata in the referenced file. Values are taken from the list provided by the METS.";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP25_CARDINALITY = "1..1";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP25_LEVEL = "MUST";

  /* CSIP26 */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP26_ID = "CSIP26";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP26_NAME = "File mime type";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP26_LOCATION = "mets/dmdSec/mdRef/@MIMETYPE";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP26_DESCRIPTION = "The IANA mime type of the referenced file.See also: IANA media types";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP26_CARDINALITY = "1..1";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP26_LEVEL = "MUST";

  /* CSIP27 */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP27_ID = "CSIP27";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP27_NAME = "File size";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP27_LOCATION = "mets/dmdSec/mdRef/@SIZE";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP27_DESCRIPTION = "Size of the referenced file in bytes.";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP27_CARDINALITY = "1..1";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP27_LEVEL = "MUST";

  /* CSIP28 */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP28_ID = "CSIP28";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP28_NAME = "File creation date";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP28_LOCATION = "mets/dmdSec/mdRef/@CREATED";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP28_DESCRIPTION = "The creation date of the referenced file.";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP28_CARDINALITY = "1..1";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP28_LEVEL = "MUST";

  /* CSIP29 */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP29_ID = "CSIP29";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP29_NAME = "File checksum";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP29_LOCATION = "mets/dmdSec/mdRef/@CHECKSUM";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP29_DESCRIPTION = "The checksum of the referenced file.";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP29_CARDINALITY = "1..1";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP29_LEVEL = "MUST";

  /* CSIP30 */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP30_ID = "CSIP30";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP30_NAME = "File checksum type";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP30_LOCATION = "mets/dmdSec/mdRef/@CHECKSUMTYPE";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP30_DESCRIPTION = "The type of checksum following the value list present in the METS-standard which has been used for calculating the checksum for the referenced file.";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP30_CARDINALITY = "1..1";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP30_LEVEL = "MUST";

  /* Use of the METS administrative metadata section (element amdSec) */
  /* CSIP31 */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP31_ID = "CSIP31";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP31_NAME = "Administrative metadata";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP31_LOCATION = "mets/amdSec";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP31_DESCRIPTION = "If administrative / preservation metadata is available, it must be described "
    + "using the administrative metadata section ( <amdSec> ) element. All "
    + "administrative metadata is present in a single <amdSec> element. It is "
    + "possible to transfer metadata in a package using just the descriptive "
    + "metadata section and/or administrative metadata section.";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP31_CARDINALITY = "0..1";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP31_LEVEL = "SHOULD";

  /* CSIP32 */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP32_ID = "CSIP32";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP32_NAME = "Digital provenance metadata";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP32_LOCATION = "mets/amdSec/digiprovMD";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP32_DESCRIPTION = "For recording information about preservation the standard PREMIS is used. "
    + "It is mandatory to include one <digiprovMD> element for each piece of "
    + "PREMIS metadata. The use if PREMIS in METS is following the "
    + "recommendations in the 2017 version of PREMIS in METS Guidelines.";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP32_CARDINALITY = "0..n";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP32_LEVEL = "SHOULD";

  /* CSIP33 */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP33_ID = "CSIP33";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP33_NAME = "Digital provenance metadata identifier";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP33_LOCATION = "mets/amdSec/digiprovMD/@ID";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP33_DESCRIPTION = "An xml:id identifier for the digital provenance metadata section "
    + "mets/amdSec/digiprovMD used for internal package references. It must " + "be unique within the package.";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP33_CARDINALITY = "1..1";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP33_LEVEL = "MUST";

  /* CSIP34 */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP34_ID = "CSIP34";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP34_NAME = "Status of the digital provenance metadata";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP34_LOCATION = "mets/amdSec/digiprovMD/@STATUS";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP34_DESCRIPTION = "Indicates the status of the package using a fixed vocabulary.See also: "
    + "dmdSec status";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP34_CARDINALITY = "0..1";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP34_LEVEL = "SHOULD";

  /* CSIP35 */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP35_ID = "CSIP35";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP35_NAME = "Reference to the document with the digital provenance metadata";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP35_LOCATION = "mets/amdSec/digiprovMD/mdRef";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP35_DESCRIPTION = "Reference to the digital provenance metadata file stored in the “metadata” "
    + "section of the IP.";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP35_CARDINALITY = "0..1";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP35_LEVEL = "SHOULD";

  /* CSIP36 */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP36_ID = "CSIP36";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP36_NAME = "Reference to the document with the digital provenance metadata";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP36_LOCATION = "mets/amdSec/digiprovMD/mdRef[@LOCTYPE=’URL’]";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP36_DESCRIPTION = "The locator type is always used with the value “URL” from the vocabulary in the attribute.";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP36_CARDINALITY = "1..1";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP36_LEVEL = "MUST";

  /* CSIP37 */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP37_ID = "CSIP37";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP37_NAME = "Type of link";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP37_LOCATION = "mets/amdSec/digiprovMD/mdRef[@xlink:type=’simple’]";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP37_DESCRIPTION = "The actual location of the resource. This specification recommends "
    + "recording a URL type filepath within this attribute.";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP37_CARDINALITY = "1..1";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP37_LEVEL = "MUST";

  /* CSIP38 */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP38_ID = "CSIP38";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP38_NAME = "Resource location";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP38_LOCATION = "mets/amdSec/digiprovMD/mdRef/@xlink:href";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP38_DESCRIPTION = "The actual location of the resource. This specification recommends "
    + "recording a URL type filepath within this attribute.";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP38_CARDINALITY = "1..1";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP38_LEVEL = "MUST";

  /* CSIP39 */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP39_ID = "CSIP39";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP39_NAME = "Type of metadata";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP39_LOCATION = "mets/amdSec/digiprovMD/mdRef/@MDTYPE";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP39_DESCRIPTION = "Specifies the type of metadata in the referenced file. Values are taken from "
    + "the list provided by the METS.";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP39_CARDINALITY = "1..1";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP39_LEVEL = "MUST";

  /* CSIP40 */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP40_ID = "CSIP40";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP40_NAME = "File mime type";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP40_LOCATION = "mets/amdSec/digiprovMD/mdRef/@MIMETYPE";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP40_DESCRIPTION = "The IANA mime type for the referenced file.See also: IANA media types";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP40_CARDINALITY = "1..1";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP40_LEVEL = "MUST";

  /* CSIP41 */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP41_ID = "CSIP41";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP41_NAME = "File size";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP41_LOCATION = "mets/amdSec/digiprovMD/mdRef/@SIZE";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP41_DESCRIPTION = "Size of the referenced file in bytes.";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP41_CARDINALITY = "1..1";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP41_LEVEL = "MUST";

  /* CSIP42 */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP42_ID = "CSIP42";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP42_NAME = "File creation date";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP42_LOCATION = "mets/amdSec/digiprovMD/mdRef/@CREATED";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP42_DESCRIPTION = "Creation date of the referenced file.";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP42_CARDINALITY = "1..1";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP42_LEVEL = "MUST";

  /* CSIP43 */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP43_ID = "CSIP43";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP43_NAME = "File checksum";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP43_LOCATION = "mets/amdSec/digiprovMD/mdRef/@CHECKSUM";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP43_DESCRIPTION = "The checksum of the referenced file.";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP43_CARDINALITY = "1..1";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP43_LEVEL = "MUST";

  /* CSIP44 */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP44_ID = "CSIP44";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP44_NAME = "File checksum type";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP44_LOCATION = "mets/amdSec/digiprovMD/mdRef/@CHECKSUMTYPE";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP44_DESCRIPTION = "The type of checksum following the value list present in the METS-standard "
    + "which has been used for calculating the checksum for the referenced file.";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP44_CARDINALITY = "1..1";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP44_LEVEL = "MUST";

  /* CSIP45 */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP45_ID = "CSIP45";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP45_NAME = "Rights metadata";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP45_LOCATION = "mets/amdSec/rightsMD";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP45_DESCRIPTION = "A simple rights statement may be used to describe general permissions for "
    + "the package. Individual representations should state their specific rights in "
    + "their representation METS file. Available standards include "
    + "RightsStatements.org , Europeana rights statements info , METS Rights "
    + "Schema created and maintained by the METS Board, the rights part of "
    + "PREMIS as well as own local rights statements in use.";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP45_CARDINALITY = "0..1";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP45_LEVEL = "MAY";

  /* CSIP46 */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP46_ID = "CSIP46";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP46_NAME = "Rights metadata identifier";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP46_LOCATION = "mets/amdSec/rightsMD/@ID";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP46_DESCRIPTION = "An xml:id identifier for the rights metadata section ( <rightsMD> ) used for internal package references. It must be unique within the package.";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP46_CARDINALITY = "1..1";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP46_LEVEL = "MUST";

  /* CSIP47 */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP47_ID = "CSIP47";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP47_NAME = "Status of the rights metadata";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP47_LOCATION = "mets/amdSec/rightsMD/@STATUS";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP47_DESCRIPTION = "Indicates the status of the package using a fixed vocabulary.See also: dmdSec status";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP47_CARDINALITY = "0..1";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP47_LEVEL = "SHOULD";

  /* CSIP48 */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP48_ID = "CSIP48";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP48_NAME = "Reference to the document with the rights metadata";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP48_LOCATION = "mets/amdSec/rightsMD/mdRef";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP48_DESCRIPTION = "Reference to the rights metadata file stored in the “metadata” section of the IP.";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP48_CARDINALITY = "0..1";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP48_LEVEL = "SHOULD";

  /* CSIP49 */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP49_ID = "CSIP49";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP49_NAME = "Type of locator";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP49_LOCATION = "mets/amdSec/rightsMD/mdRef[@LOCTYPE=’URL’]";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP49_DESCRIPTION = "The locator type is always used with the value “URL” from the vocabulary in the attribute.";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP49_CARDINALITY = "1..1";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP49_LEVEL = "MUST";

  /* CSIP50 */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP50_ID = "CSIP50";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP50_NAME = "Type of locator";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP50_LOCATION = "mets/amdSec/rightsMD/mdRef[@xlink:type=’simple’]";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP50_DESCRIPTION = "Attribute used with the value “simple”. Value list is maintained by the xlink standard.";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP50_CARDINALITY = "1..1";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP50_LEVEL = "MUST";

  /* CSIP51 */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP51_ID = "CSIP51";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP51_NAME = "Resource location";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP51_LOCATION = "mets/amdSec/rightsMD/mdRef/@xlink:href";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP51_DESCRIPTION = "The actual location of the resource. We recommend recording a URL type filepath within this attribute.";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP51_CARDINALITY = "1..1";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP51_LEVEL = "MUST";

  /* CSIP52 */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP52_ID = "CSIP52";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP52_NAME = "Type of metadata";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP52_LOCATION = "mets/amdSec/rightsMD/mdRef/@MDTYPE";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP52_DESCRIPTION = "Specifies the type of metadata in the referenced file. Value is taken from the list provided by the METS.";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP52_CARDINALITY = "1..1";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP52_LEVEL = "MUST";

  /* CSIP53 */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP53_ID = "CSIP53";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP53_NAME = "File mime type";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP53_LOCATION = "mets/amdSec/rightsMD/mdRef/@MIMETYPE";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP53_DESCRIPTION = "The IANA mime type for the referenced file.See also: IANA media types";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP53_CARDINALITY = "1..1";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP53_LEVEL = "MUST";

  /* CSIP54 */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP54_ID = "CSIP54";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP54_NAME = "File size";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP54_LOCATION = "mets/amdSec/rightsMD/mdRef/@SIZE";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP54_DESCRIPTION = "Size of the referenced file in bytes.";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP54_CARDINALITY = "1..1";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP54_LEVEL = "MUST";

  /* CSIP55 */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP55_ID = "CSIP55";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP55_NAME = "File creation date";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP55_LOCATION = "mets/amdSec/rightsMD/mdRef/@CREATED";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP55_DESCRIPTION = "Creation date of the referenced file.";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP55_CARDINALITY = "1..1";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP55_LEVEL = "MUST";

  /* CSIP56 */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP56_ID = "CSIP56";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP56_NAME = "File checksum";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP56_LOCATION = "mets/amdSec/rightsMD/mdRef/@CHECKSUM";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP56_DESCRIPTION = "The checksum of the referenced file.";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP56_CARDINALITY = "1..1";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP56_LEVEL = "MUST";

  /* CSIP57 */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP57_ID = "CSIP57";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP57_NAME = "File checksum type";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP57_LOCATION = "mets/amdSec/rightsMD/mdRef/@CHECKSUMTYPE";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP57_DESCRIPTION = "The type of checksum following the value list present in the METS-standard which has been used for calculating the checksum for the referenced file.";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP57_CARDINALITY = "1..1";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP57_LEVEL = "MUST";

  /* Use of the METS file section (element fileSec) */
  /* CSIP58 */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP58_ID = "CSIP58";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP58_NAME = "File section";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP58_LOCATION = "mets/fileSec";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP58_DESCRIPTION = "The transferred content is placed in the file section in different file group "
    + "elements, described in other requirements. Only a single file section "
    + "( <fileSec> ) element should be present. It is possible to transfer just "
    + "descriptive metadata and/or administrative metadata without files placed in " + "this section.";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP58_CARDINALITY = "0..1";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP58_LEVEL = "SHOULD";

  /* CSIP59 */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP59_ID = "CSIP59";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP59_NAME = "File section identifier";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP59_LOCATION = "mets/fileSec/@ID";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP59_DESCRIPTION = "An xml:id identifier for the file section used for internal package references. It must be unique within the package.";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP59_CARDINALITY = "1..1";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP59_LEVEL = "MUST";

  /* CSIP60 */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP60_ID = "CSIP60";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP60_NAME = "Documentation file group";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP60_LOCATION = "mets/fileSec/fileGrp[@USE=’Documentation’]";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP60_DESCRIPTION = "All documentation pertaining to the transferred content is placed in one or more file group elements with mets/fileSec/fileGrp/@USE attribute value “Documentation”.";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP60_CARDINALITY = "1..n";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP60_LEVEL = "MUST";

  /* CSIP113 */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP113_ID = "CSIP113";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP113_NAME = "Schema file group";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP113_LOCATION = "mets/fileSec/fileGrp[@USE=’Schemas’]";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP113_DESCRIPTION = "All XML schemas used in the information package should be referenced from "
    + "one or more file groups with mets/fileSec/fileGrp/@USE attribute value "
    + "“Schemas”.See also: File group names";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP113_CARDINALITY = "1..n";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP113_LEVEL = "MUST";

  /* CSIP114 */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP114_ID = "CSIP114";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP114_NAME = "Representations file group";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP114_LOCATION = "mets/fileSec/fileGrp[@USE=’Representations’]";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP114_DESCRIPTION = "A pointer to the METS document describing the representation or pointers to "
    + "the content being transferred must be present in one or more file groups "
    + "with mets/fileSec/fileGrp/@USE attribute value “Representations”.See " + "also: File group names";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP114_CARDINALITY = "1..n";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP114_LEVEL = "MUST";

  /* CSIP61 */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP61_ID = "CSIP61";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP61_NAME = "Reference to administrative metadata";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP61_LOCATION = "mets/fileSec/fileGrp/@ADMID";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP61_DESCRIPTION = "If administrative metadata has been provided at file group "
    + "mets/fileSec/fileGrp level this attribute refers to its administrative " + "metadata section by ID.";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP61_CARDINALITY = "0..1";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP61_LEVEL = "MAY";

  /* CSIP62 */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP62_ID = "CSIP62";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP62_NAME = "Content Information Type Specification";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP62_LOCATION = "mets/fileSec/fileGrp[@USE=’Representations’]/@csip:CONTENTINFORMATIONTYPE";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP62_DESCRIPTION = "An added attribute which states the name of the content information type "
    + "specification used to create the package. The vocabulary will evolve under "
    + "the curation of the DILCIS Board as additional content information type "
    + "specifications are developed. This attribute is mandatory when the "
    + "mets/fileSec/fileGrp/@USE attribute value is “Representations”. When "
    + "the “Package type” value is “Mixed” and/or the file group describes a "
    + "“Representation”, then this element states the content information type "
    + "specification used for the file group.See also: Content information type " + "specification";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP62_CARDINALITY = "0..1";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP62_LEVEL = "SHOULD";

  /* CSIP63 */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP63_ID = "CSIP63";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP63_NAME = "Other Content Information Type Specification";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP63_LOCATION = "mets/fileSec/fileGrp[@csip:CONTENTINFORMATIONTYPE=’OTHER’]/@csip:OTHERCONTENTINFORMATIONTYPE";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP63_DESCRIPTION = "When the mets/fileSec/fileGrp/@csip:CONTENTINFORMATIONTYPE "
    + "attribute has the value “OTHER” the " + "mets/fileSec/fileGrp/@csip:OTHERCONTENTINFORMATIONTYPE must "
    + "state a value for the Content Information Type Specification used.";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP63_CARDINALITY = "0..1";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP63_LEVEL = "MAY";

  /* CSIP64 */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP64_ID = "CSIP64";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP64_NAME = "Description of the use of the file group";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP64_LOCATION = "mets/fileSec/fileGrp/@USE";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP64_DESCRIPTION = "The value in the mets/fileSec/fileGrp/@USE is the name of the whole "
    + "folder structure to the data, e.g 'Documentation', 'Schemas',"
    + "“Representations/preingest” or “Representations/submission/data”.";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP64_CARDINALITY = "1..1";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP64_LEVEL = "MUST";

  /* CSIP65 */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP65_ID = "CSIP65";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP65_NAME = "File group identifier";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP65_LOCATION = "mets/fileSec/fileGrp/@ID";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP65_DESCRIPTION = "An xml:id identifier for the file group used for internal package references.It must be unique within the package.";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP65_CARDINALITY = "1..1";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP65_LEVEL = "MUST";

  /* CSIP66 */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP66_ID = "CSIP66";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP66_NAME = "File";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP66_LOCATION = "mets/fileSec/fileGrp/file";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP66_DESCRIPTION = "The file group ( <fileGrp> ) contains the file elements which describe the file objects.";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP66_CARDINALITY = "1..n";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP66_LEVEL = "MUST";

  /* CSIP67 */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP67_ID = "CSIP67";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP67_NAME = "File identifier";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP67_LOCATION = "mets/fileSec/fileGrp/file/@ID";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP67_DESCRIPTION = "A unique xml:id identifier for this file across the package.";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP67_CARDINALITY = "1..1";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP67_LEVEL = "MUST";

  /* CSIP68 */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP68_ID = "CSIP68";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP68_NAME = "File mimetype";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP68_LOCATION = "mets/fileSec/fileGrp/file/@MIMETYPE";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP68_DESCRIPTION = "The IANA mime type for the referenced file.See also: IANA media types";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP68_CARDINALITY = "1..1";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP68_LEVEL = "MUST";

  /* CSIP69 */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP69_ID = "CSIP69";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP69_NAME = "File size";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP69_LOCATION = "mets/fileSec/fileGrp/file/@SIZE";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP69_DESCRIPTION = "Size of the referenced file in bytes.";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP69_CARDINALITY = "1..1";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP69_LEVEL = "MUST";

  /* CSIP70 */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP70_ID = "CSIP70";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP70_NAME = "File creation date";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP70_LOCATION = "mets/fileSec/fileGrp/file/@CREATED";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP70_DESCRIPTION = "Creation date of the referenced file.";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP70_CARDINALITY = "1..1";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP70_LEVEL = "MUST";

  /* CSIP71 */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP71_ID = "CSIP71";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP71_NAME = "File checksum";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP71_LOCATION = "mets/fileSec/fileGrp/file/@CHECKSUM";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP71_DESCRIPTION = "The checksum of the referenced file.";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP71_CARDINALITY = "1..1";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP71_LEVEL = "MUST";

  /* CSIP72 */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP72_ID = "CSIP72";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP72_NAME = "File checksum type";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP72_LOCATION = "mets/fileSec/fileGrp/file/@CHECKSUMTYPE";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP72_DESCRIPTION = "The type of checksum following the value list present in the METS-standard "
    + "which has been used for calculating the checksum for the referenced file.";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP72_CARDINALITY = "1..1";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP72_LEVEL = "MUST";

  /* CSIP73 */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP73_ID = "CSIP73";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP73_NAME = "File original identfication";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP73_LOCATION = "mets/fileSec/fileGrp/file/@OWNERID";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP73_DESCRIPTION = "If an identifier for the file was supplied by the owner it can be recorded in "
    + "this attribute.";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP73_CARDINALITY = "0..1";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP73_LEVEL = "MAY";

  /* CSIP74 */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP74_ID = "CSIP74";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP74_NAME = "File reference to administrative metadata";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP74_LOCATION = "mets/fileSec/fileGrp/file/@ADMID";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP74_DESCRIPTION = "If administrative metadata has been provided for the file this attribute refers "
    + "to the file’s administrative metadata by ID.";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP74_CARDINALITY = "0..1";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP74_LEVEL = "MAY";

  /* CSIP75 */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP75_ID = "CSIP75";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP75_NAME = "File reference to descriptive metadata";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP75_LOCATION = "mets/fileSec/fileGrp/file/@DMDID";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP75_DESCRIPTION = "If descriptive metadata has been provided per file this attribute refers to the "
    + "file’s descriptive metadata by ID.";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP75_CARDINALITY = "0..1";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP75_LEVEL = "MAY";

  /* CSIP76 */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP76_ID = "CSIP76";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP76_NAME = "File locator reference";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP76_LOCATION = "mets/fileSec/fileGrp/file/FLocat";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP76_DESCRIPTION = "The location of each external file must be defined by the file location "
    + "<FLocat> element using the same rules as for referencing metadata files. "
    + "All references to files should be made using the XLink href attribute and the "
    + "file protocol using the relative location of the file.";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP76_CARDINALITY = "0..1";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP76_LEVEL = "MAY";

  /* CSIP77 */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP77_ID = "CSIP77";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP77_NAME = "Type of locator";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP77_LOCATION = "mets/fileSec/fileGrp/file/FLocat[@LOCTYPE=’URL’]";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP77_DESCRIPTION = "The locator type is always used with the value “URL” from the vocabulary in the attribute.";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP77_CARDINALITY = "1..1";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP77_LEVEL = "MUST";

  /* CSIP78 */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP78_ID = "CSIP78";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP78_NAME = "Type of link";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP78_LOCATION = "mets/fileSec/fileGrp/file/FLocat[@xlink:type=’simple’]";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP78_DESCRIPTION = "Attribute used with the value “simple”. Value list is maintained by the xlink standard.";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP78_CARDINALITY = "1..1";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP78_LEVEL = "MUST";

  /* CSIP79 */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP79_ID = "CSIP79";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP79_NAME = "Resource location";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP79_LOCATION = "mets/fileSec/fileGrp/file/FLocat/@xlink:href";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP79_DESCRIPTION = "The actual location of the resource. We recommend recording a URL type filepath within this attribute.";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP79_CARDINALITY = "1..1";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP79_LEVEL = "MUST";

  /* Use of the METS structural map (<structMap>) */
  /* CSIP80 */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP80_ID = "CSIP80";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP80_NAME = "Structural description of the package";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP80_LOCATION = "mets/structMap";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP80_DESCRIPTION = "The structural map <structMap> element is the only mandatory element in "
    + "the METS. The <structMap> in the CSIP describes the highest logical "
    + "structure of the IP. Each METS file must include ONE structural map "
    + "<structMap> element used exactly as described here. Institutions can add "
    + "their own additional custom structural maps as separate <structMap> " + "sections.";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP80_CARDINALITY = "1..n";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP80_LEVEL = "MUST";

  /* CSIP81 */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP81_ID = "CSIP81";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP81_NAME = "Type of structural description";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP81_LOCATION = "mets/structMap[@TYPE=’PHYSICAL’]";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP81_DESCRIPTION = "The mets/structMap/@TYPE attribute must take the value 'PHYSICAL' "
    + "from the vocabulary.See also: Structural map typing";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP81_CARDINALITY = "1..1";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP81_LEVEL = "MUST";

  /* CSIP82 */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP82_ID = "CSIP82";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP82_NAME = "Name of the structural description";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP82_LOCATION = "mets/structMap[@LABEL=’CSIP’]";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP82_DESCRIPTION = "The mets/structMap/@LABEL attribute value is set to “CSIP” from the "
    + "vocabulary.See also: Structural map label";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP82_CARDINALITY = "1..1";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP82_LEVEL = "MUST";

  /* CSIP83 */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP83_ID = "CSIP83";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP83_NAME = "mets/structMap[@LABEL=’CSIP’]/@ID";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP83_LOCATION = "mets/structMap[@LABEL=’CSIP’]/@ID";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP83_DESCRIPTION = "An xml:id identifier for the structural description (structMap) used for "
    + "internal package references. It must be unique within the package.";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP83_CARDINALITY = "1..1";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP83_LEVEL = "MUST";

  /* CSIP84 */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP84_ID = "CSIP84";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP84_NAME = "Main structural division";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP84_LOCATION = "mets/structMap[@LABEL=’CSIP’]/div";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP84_DESCRIPTION = "The structural map comprises a single division.";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP84_CARDINALITY = "1..1";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP84_LEVEL = "MUST";

  /* CSIP85 */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP85_ID = "CSIP85";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP85_NAME = "Main structural division identifier";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP85_LOCATION = "mets/structMap[@LABEL=’CSIP’]/div/@ID";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP85_DESCRIPTION = "Mandatory, xml:id identifier must be unique within the package.";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP85_CARDINALITY = "1..1";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP85_LEVEL = "MUST";

  /* CSIP86 */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP86_ID = "CSIP86";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP86_NAME = "Main structural division label";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP86_LOCATION = "mets/structMap[@LABEL=’CSIP’]/div/@LABEL";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP86_DESCRIPTION = "The package’s top-level structural division <div> element’s @LABEL "
    + "attribute value must be identical to the package identifier, i.e. the same "
    + "value as the mets/@OBJID attribute.";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP86_CARDINALITY = "1..1";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP86_LEVEL = "MUST";

  /* CSIP88 */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP88_ID = "CSIP88";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP88_NAME = "Metadata division";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP88_LOCATION = "mets/structMap[@LABEL='CSIP']/div/div[@LABEL='Metadata']";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP88_DESCRIPTION = "The metadata referenced in the administrative and/or descriptive metadata section is described in the structural map with one sub division."
    + "When the transfer consists of only administrative and/or descriptive metadata this is the only sub division that occurs.";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP88_CARDINALITY = "1..1";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP88_LEVEL = "MUST";

  /* CSIP89 */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP89_ID = "CSIP89";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP89_NAME = "Metadata division identifier";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP89_LOCATION = "mets/structMap[@LABEL='CSIP']/div/div[@LABEL='Metadata']/@ID";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP89_DESCRIPTION = "Mandatory, xml:id identifier must be unique within the package.";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP89_CARDINALITY = "1..1";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP89_LEVEL = "MUST";

  /* CSIP90 */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP90_ID = "CSIP90";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP90_NAME = "Metadata division label";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP90_LOCATION = "mets/structMap[@LABEL='CSIP']/div/div[@LABEL='Metadata']";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP90_DESCRIPTION = "The metadata division <div> element’s @LABEL attribute value must be “Metadata”. "
    + "See also: File group names";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP90_CARDINALITY = "1..1";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP90_LEVEL = "MUST";

  /* CSIP91 */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP91_ID = "CSIP91";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP91_NAME = "Metadata division administrative metadata referencing";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP91_LOCATION = "mets/structMap[@LABEL='CSIP']/div/div[@LABEL='Metadata']/@ADMID";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP91_DESCRIPTION = "When there is administrative metadata and the amdSec is present, all administrative metadata MUST be referenced via the administrative sections different identifiers."
    + "All of the <amdSec> identifiers are listed in a single @ADMID using spaces as delimiters.";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP91_CARDINALITY = "0..1";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP91_LEVEL = "SHOULD";

  /* CSIP92 */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP92_ID = "CSIP92";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP92_NAME = "Metadata division descriptive metadata referencing";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP92_LOCATION = "mets/structMap[@LABEL='CSIP']/div/div[@LABEL='Metadata']/@DMDID";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP92_DESCRIPTION = "When there are descriptive metadata and one or more dmdSec is present, all descriptive metadata MUST be referenced via the descriptive section identifiers. "
    + "Every <dmdSec> identifier is listed in a single @DMDID attribute using spaces as delimiters.";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP92_CARDINALITY = "0..1";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP92_LEVEL = "SHOULD";

  /* CSIP93 */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP93_ID = "CSIP93";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP93_NAME = "Documentation division";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP93_LOCATION = "mets/structMap[@LABEL='CSIP']/div/div[@LABEL='Documentation']";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP93_DESCRIPTION = "The documentation referenced in the file section file groups is described in the structural map with one sub division.";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP93_CARDINALITY = "0..1";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP93_LEVEL = "SHOULD";

  /* CSIP94 */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP94_ID = "CSIP94";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP94_NAME = "Documentation division identifier";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP94_LOCATION = "mets/structMap[@LABEL='CSIP']/div/div[@LABEL='Documentation']/@ID";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP94_DESCRIPTION = "Mandatory, xml:id identifier must be unique within the package.";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP94_CARDINALITY = "1..1";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP94_LEVEL = "MUST";

  /* CSIP95 */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP95_ID = "CSIP95";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP95_NAME = "Documentation division label";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP95_LOCATION = "mets/structMap[@LABEL='CSIP']/div/div[@LABEL='Documentation']";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP95_DESCRIPTION = "The documentation division <div> element in the package uses the value 'Documentation' from the vocabulary as the value for the @LABEL attribute. "
    + "See also: File group names";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP95_CARDINALITY = "1..1";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP95_LEVEL = "MUST";

  /* CSIP96 */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP96_ID = "CSIP96";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP96_NAME = "Documentation file referencing";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP96_LOCATION = "mets/structMap[@LABEL='CSIP']/div/div[@LABEL='Documentation']/fptr";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP96_DESCRIPTION = "All file groups containing documentation described in the package are referenced via the relevant file group identifiers. There MUST be one file group reference per <fptr> element.";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP96_CARDINALITY = "0..n";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP96_LEVEL = "MUST";

  /* CSIP116 */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP116_ID = "CSIP116";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP116_NAME = "Documentation file group reference pointer";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP116_LOCATION = "mets/structMap[@LABEL='CSIP']/div/div[@LABEL='Documentation']/fptr/@FILEID";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP116_DESCRIPTION = "A reference, by ID, to the “Documentation” file group. "
    + "Related to the requirements CSIP60 which describes the “Documentation” file group and CSIP65 which describes the file group identifier.";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP116_CARDINALITY = "1..1";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP116_LEVEL = "MUST";

  /* CSIP97 */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP97_ID = "CSIP97";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP97_NAME = "Schema division";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP97_LOCATION = "mets/structMap[@LABEL='CSIP']/div/div[@LABEL='Schemas']";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP97_DESCRIPTION = "The schemas referenced in the file section file groups are described in the structural map within a single sub-division.";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP97_CARDINALITY = "0..1";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP97_LEVEL = "SHOULD";

  /* CSIP98 */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP98_ID = "CSIP98";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP98_NAME = "Schema division identifier";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP98_LOCATION = "mets/structMap[@LABEL='CSIP']/div/div[@LABEL='Schemas']/@ID";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP98_DESCRIPTION = "Mandatory, xml:id identifier must be unique within the package.";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP98_CARDINALITY = "1..1";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP98_LEVEL = "MUST";

  /* CSIP99 */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP99_ID = "CSIP99";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP99_NAME = "Schema division label";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP99_LOCATION = "mets/structMap[@LABEL='CSIP']/div/div[@LABEL='Schemas']";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP99_DESCRIPTION = "The schema division <div> element’s @LABEL attribute has the value “Schemas” from the vocabulary. "
    + "See also: File group names";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP99_CARDINALITY = "1..1";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP99_LEVEL = "MUST";

  /* CSIP100 */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP100_ID = "CSIP100";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP100_NAME = "Schema file reference";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP100_LOCATION = "mets/structMap[@LABEL='CSIP']/div/div[@LABEL='Schemas']/fptr";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP100_DESCRIPTION = "All file groups containing schemas described in the package are referenced via the relevant file group identifiers. One file group reference per fptr-element";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP100_CARDINALITY = "0..n";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP100_LEVEL = "MUST";

  /* CSIP118 */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP118_ID = "CSIP118";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP118_NAME = "Schema file group reference";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP118_LOCATION = "mets/structMap[@LABEL='CSIP']/div/div[@LABEL='Schemas']/fptr/@FILEID";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP118_DESCRIPTION = "The pointer to the identifier for the “Schema” file group. "
    + "Related to the requirements CSIP113 which describes the “Schema” file group and CSIP65 which describes the file group identifier.";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP118_CARDINALITY = "1..1";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP118_LEVEL = "MUST";

  /* CSIP101 */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP101_ID = "CSIP101";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP101_NAME = "Content division";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP101_LOCATION = "mets/structMap[@LABEL='CSIP']/div/div[@LABEL='Representations']";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP101_DESCRIPTION = "When no representations are present the content referenced in the file section file group with @USE attribute value “Representations” is described in the structural map as a single sub division.";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP101_CARDINALITY = "0..1";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP101_LEVEL = "SHOULD";

  /* CSIP102 */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP102_ID = "CSIP102";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP102_NAME = "Content division identifier";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP102_LOCATION = "mets/structMap[@LABEL='CSIP']/div/div[@LABEL='Representations']/@ID";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP102_DESCRIPTION = "Mandatory, xml:id identifier must be unique within the package.";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP102_CARDINALITY = "1..1";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP102_LEVEL = "MUST";

  /* CSIP103 */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP103_ID = "CSIP103";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP103_NAME = "Content division label";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP103_LOCATION = "mets/structMap[@LABEL='CSIP']/div/div[@LABEL='Representations']";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP103_DESCRIPTION = "The package’s content division <div> element must have the @LABEL attribute value “Representations”, taken from the vocabulary. See also: File group names";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP103_CARDINALITY = "1..1";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP103_LEVEL = "MUST";

  /* CSIP104 */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP104_ID = "CSIP104";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP104_NAME = "Content division file references";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP104_LOCATION = "mets/structMap[@LABEL='CSIP']/div/div[@LABEL='Representations']/fptr";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP104_DESCRIPTION = "All file groups containing content described in the package are referenced via the relevant file group identifiers. One file group reference per fptr-element.";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP104_CARDINALITY = "0..n";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP104_LEVEL = "MUST";

  /* CSIP119 */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP119_ID = "CSIP119";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP119_NAME = "Content division file group references";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP119_LOCATION = "mets/structMap[@LABEL='CSIP']/div/div[@LABEL='Representations']/fptr/@FILEID";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP119_DESCRIPTION = "The pointer to the identifier for the “Representations” file group. "
    + "Related to the requirements CSIP114 which describes the “Representations” file group and CSIP65 which describes the file group identifier.";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP119_CARDINALITY = "1..1";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP119_LEVEL = "MUST";

  /* CSIP105 */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP105_ID = "CSIP105";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP105_NAME = "Representation division";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP105_LOCATION = "mets/structMap[@LABEL='CSIP']/div/div";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP105_DESCRIPTION = "When a package consists of multiple representations, each described by a representation level METS.xml document, there is a discrete representation div element for each representation. "
    + "Each representation div references the representation level METS.xml document, documenting the structure of the package and its constituent representations.";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP105_CARDINALITY = "0..n";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP105_LEVEL = "SHOULD";

  /* CSIP106 */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP106_ID = "CSIP106";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP106_NAME = "Representations division identifier";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP106_LOCATION = "mets/structMap[@LABEL='CSIP']/div/div/@ID";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP106_DESCRIPTION = "Mandatory, xml:id identifier must be unique within the package.";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP106_CARDINALITY = "1..1";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP106_LEVEL = "MUST";

  /* CSIP107 */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP107_ID = "CSIP107";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP107_NAME = "Representations division label";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP107_LOCATION = "mets/structMap[@LABEL='CSIP']/div/div/@LABEL";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP107_DESCRIPTION = "The package’s representation division <div> element @LABEL attribute value must be the path to the representation level METS document. "
    + "This requirement gives the same value to be used as the requirement named “File group identifier” (CSIP64) "
    + "See also: File group names";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP107_CARDINALITY = "1..1";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP107_LEVEL = "MUST";

  /* CSIP108 */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP108_ID = "CSIP108";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP108_NAME = "Representations division file referencing";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP108_LOCATION = "mets/structMap[@LABEL='CSIP']/div/div/mptr/@xlink:title";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP108_DESCRIPTION = "The file group containing the files described in the package are referenced via the relevant file group identifier. "
    + "Related to the requirements CSIP114 which describes the “Representations” file group and CSIP65 which describes the file group identifier.";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP108_CARDINALITY = "1..1";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP108_LEVEL = "MUST";

  /* CSIP109 */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP109_ID = "CSIP109";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP109_NAME = "Representation METS pointer";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP109_LOCATION = "mets/structMap[@LABEL='CSIP']/div/div/mptr";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP109_DESCRIPTION = "The division <div> of the specific representation includes one occurrence of the METS pointer <mptr> element, pointing to the appropriate representation METS file.";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP109_CARDINALITY = "1..1";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP109_LEVEL = "MUST";

  /* CSIP110 */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP110_ID = "CSIP110";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP110_NAME = "Resource location";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP110_LOCATION = "mets/structMap/div/div/mptr/@xlink:href";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP110_DESCRIPTION = "The actual location of the resource. We recommend recording a URL type filepath within this attribute.";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP110_CARDINALITY = "1..1";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP110_LEVEL = "MUST";

  /* CSIP111 */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP111_ID = "CSIP111";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP111_NAME = "Type of link";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP111_LOCATION = "mets/structMap/div/div/mptr[@xlink:type='simple']";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP111_DESCRIPTION = "Attribute used with the value “simple”. Value list is maintained by the xlink standard.";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP111_CARDINALITY = "1..1";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP111_LEVEL = "MUST";

  /* CSIP112 */
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP112_ID = "CSIP112";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP112_NAME = "Type of locator";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP112_LOCATION = "mets/structMap/div/div/mptr[@LOCTYPE='URL']";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP112_DESCRIPTION = "The locator type is always used with the value “URL” from the vocabulary in the attribute.";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP112_CARDINALITY = "1..1";
  public static final String VALIDATION_REPORT_SPECIFICATION_CSIP112_LEVEL = "MUST";

  /*
   * Get Name, Location, Description, Cardinality and Level for given
   * specification ID
   */
  public static String getSpecificationName(String id) {
    if (id.equals("CSIPSTR0")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIPSTR0_NAME;
    }
    if (id.equals("CSIPSTR1")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIPSTR1_NAME;
    }
    if (id.equals("CSIPSTR2")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIPSTR2_NAME;
    }
    if (id.equals("CSIPSTR3")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIPSTR3_NAME;
    }
    if (id.equals("CSIPSTR4")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIPSTR4_NAME;
    }
    if (id.equals("CSIPSTR5")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIPSTR5_NAME;
    }
    if (id.equals("CSIPSTR6")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIPSTR6_NAME;
    }
    if (id.equals("CSIPSTR7")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIPSTR7_NAME;
    }
    if (id.equals("CSIPSTR8")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIPSTR8_NAME;
    }
    if (id.equals("CSIPSTR9")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIPSTR9_NAME;
    }
    if (id.equals("CSIPSTR10")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIPSTR10_NAME;
    }
    if (id.equals("CSIPSTR11")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIPSTR11_NAME;
    }
    if (id.equals("CSIPSTR12")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIPSTR12_NAME;
    }
    if (id.equals("CSIPSTR13")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIPSTR13_NAME;
    }
    if (id.equals("CSIPSTR14")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIPSTR14_NAME;
    }
    if (id.equals("CSIPSTR15")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIPSTR15_NAME;
    }
    if (id.equals("CSIPSTR16")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIPSTR16_NAME;
    }
    if (id.equals("CSIP1")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP1_NAME;
    }
    if (id.equals("CSIP2")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP2_NAME;
    }
    if (id.equals("CSIP3")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP3_NAME;
    }
    if (id.equals("CSIP4")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP4_NAME;
    }
    if (id.equals("CSIP5")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP5_NAME;
    }
    if (id.equals("CSIP6")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP6_NAME;
    }
    if (id.equals("CSIP117")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP117_NAME;
    }
    if (id.equals("CSIP7")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP7_NAME;
    }
    if (id.equals("CSIP8")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP8_NAME;
    }
    if (id.equals("CSIP9")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP9_NAME;
    }
    if (id.equals("CSIP10")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP10_NAME;
    }
    if (id.equals("CSIP11")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP11_NAME;
    }
    if (id.equals("CSIP12")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP12_NAME;
    }
    if (id.equals("CSIP13")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP13_NAME;
    }
    if (id.equals("CSIP14")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP14_NAME;
    }
    if (id.equals("CSIP15")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP15_NAME;
    }
    if (id.equals("CSIP16")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP16_NAME;
    }
    if (id.equals("CSIP17")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP17_NAME;
    }
    if (id.equals("CSIP18")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP18_NAME;
    }
    if (id.equals("CSIP19")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP19_NAME;
    }
    if (id.equals("CSIP20")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP20_NAME;
    }
    if (id.equals("CSIP21")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP21_NAME;
    }
    if (id.equals("CSIP22")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP22_NAME;
    }
    if (id.equals("CSIP23")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP23_NAME;
    }
    if (id.equals("CSIP24")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP24_NAME;
    }
    if (id.equals("CSIP25")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP25_NAME;
    }
    if (id.equals("CSIP26")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP26_NAME;
    }
    if (id.equals("CSIP27")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP27_NAME;
    }
    if (id.equals("CSIP28")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP28_NAME;
    }
    if (id.equals("CSIP29")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP29_NAME;
    }
    if (id.equals("CSIP30")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP30_NAME;
    }
    if (id.equals("CSIP31")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP31_NAME;
    }
    if (id.equals("CSIP32")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP32_NAME;
    }
    if (id.equals("CSIP33")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP33_NAME;
    }
    if (id.equals("CSIP34")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP34_NAME;
    }
    if (id.equals("CSIP35")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP35_NAME;
    }
    if (id.equals("CSIP36")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP36_NAME;
    }
    if (id.equals("CSIP37")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP37_NAME;
    }
    if (id.equals("CSIP38")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP38_NAME;
    }
    if (id.equals("CSIP39")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP39_NAME;
    }
    if (id.equals("CSIP40")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP40_NAME;
    }
    if (id.equals("CSIP41")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP41_NAME;
    }
    if (id.equals("CSIP42")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP42_NAME;
    }
    if (id.equals("CSIP43")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP43_NAME;
    }
    if (id.equals("CSIP44")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP44_NAME;
    }
    if (id.equals("CSIP45")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP45_NAME;
    }
    if (id.equals("CSIP46")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP46_NAME;
    }
    if (id.equals("CSIP47")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP47_NAME;
    }
    if (id.equals("CSIP48")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP48_NAME;
    }
    if (id.equals("CSIP49")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP49_NAME;
    }
    if (id.equals("CSIP50")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP50_NAME;
    }
    if (id.equals("CSIP51")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP51_NAME;
    }
    if (id.equals("CSIP52")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP52_NAME;
    }
    if (id.equals("CSIP53")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP53_NAME;
    }
    if (id.equals("CSIP54")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP54_NAME;
    }
    if (id.equals("CSIP55")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP55_NAME;
    }
    if (id.equals("CSIP56")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP56_NAME;
    }
    if (id.equals("CSIP57")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP57_NAME;
    }
    if (id.equals("CSIP58")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP58_NAME;
    }
    if (id.equals("CSIP59")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP59_NAME;
    }
    if (id.equals("CSIP60")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP60_NAME;
    }
    if (id.equals("CSIP113")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP113_NAME;
    }
    if (id.equals("CSIP114")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP114_NAME;
    }
    if (id.equals("CSIP61")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP61_NAME;
    }
    if (id.equals("CSIP62")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP62_NAME;
    }
    if (id.equals("CSIP63")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP63_NAME;
    }
    if (id.equals("CSIP64")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP64_NAME;
    }
    if (id.equals("CSIP65")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP65_NAME;
    }
    if (id.equals("CSIP66")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP66_NAME;
    }
    if (id.equals("CSIP67")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP67_NAME;
    }
    if (id.equals("CSIP68")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP68_NAME;
    }
    if (id.equals("CSIP69")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP69_NAME;
    }
    if (id.equals("CSIP70")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP70_NAME;
    }
    if (id.equals("CSIP71")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP71_NAME;
    }
    if (id.equals("CSIP72")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP72_NAME;
    }
    if (id.equals("CSIP73")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP73_NAME;
    }
    if (id.equals("CSIP74")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP74_NAME;
    }
    if (id.equals("CSIP75")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP75_NAME;
    }
    if (id.equals("CSIP76")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP76_NAME;
    }
    if (id.equals("CSIP77")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP77_NAME;
    }
    if (id.equals("CSIP78")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP78_NAME;
    }
    if (id.equals("CSIP79")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP79_NAME;
    }
    if (id.equals("CSIP80")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP80_NAME;
    }
    if (id.equals("CSIP81")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP81_NAME;
    }
    if (id.equals("CSIP82")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP82_NAME;
    }
    if (id.equals("CSIP83")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP83_NAME;
    }
    if (id.equals("CSIP84")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP84_NAME;
    }
    if (id.equals("CSIP85")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP85_NAME;
    }
    if (id.equals("CSIP86")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP86_NAME;
    }
    if (id.equals("CSIP88")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP88_NAME;
    }
    if (id.equals("CSIP89")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP89_NAME;
    }
    if (id.equals("CSIP90")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP90_NAME;
    }
    if (id.equals("CSIP91")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP91_NAME;
    }
    if (id.equals("CSIP92")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP92_NAME;
    }
    if (id.equals("CSIP93")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP93_NAME;
    }
    if (id.equals("CSIP94")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP94_NAME;
    }
    if (id.equals("CSIP95")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP95_NAME;
    }
    if (id.equals("CSIP96")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP96_NAME;
    }
    if (id.equals("CSIP116")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP116_NAME;
    }
    if (id.equals("CSIP97")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP97_NAME;
    }
    if (id.equals("CSIP98")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP98_NAME;
    }
    if (id.equals("CSIP99")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP99_NAME;
    }
    if (id.equals("CSIP100")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP100_NAME;
    }
    if (id.equals("CSIP118")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP118_NAME;
    }
    if (id.equals("CSIP101")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP101_NAME;
    }
    if (id.equals("CSIP102")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP102_NAME;
    }
    if (id.equals("CSIP103")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP103_NAME;
    }
    if (id.equals("CSIP104")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP104_NAME;
    }
    if (id.equals("CSIP119")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP119_NAME;
    }
    if (id.equals("CSIP105")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP105_NAME;
    }
    if (id.equals("CSIP106")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP106_NAME;
    }
    if (id.equals("CSIP107")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP107_NAME;
    }
    if (id.equals("CSIP108")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP108_NAME;
    }
    if (id.equals("CSIP109")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP109_NAME;
    }
    if (id.equals("CSIP110")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP110_NAME;
    }
    if (id.equals("CSIP111")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP111_NAME;
    }
    if (id.equals("CSIP112")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP112_NAME;
    }
    return "Not Defined";
  }

  public static String getSpecificationLocation(String id) {
    if (id.equals("CSIPSTR0")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIPSTR0_LOCATION;
    }
    if (id.equals("CSIPSTR1")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIPSTR1_LOCATION;
    }
    if (id.equals("CSIPSTR2")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIPSTR2_LOCATION;
    }
    if (id.equals("CSIPSTR3")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIPSTR3_LOCATION;
    }
    if (id.equals("CSIPSTR4")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIPSTR4_LOCATION;
    }
    if (id.equals("CSIPSTR5")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIPSTR5_LOCATION;
    }
    if (id.equals("CSIPSTR6")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIPSTR6_LOCATION;
    }
    if (id.equals("CSIPSTR7")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIPSTR7_LOCATION;
    }
    if (id.equals("CSIPSTR8")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIPSTR8_LOCATION;
    }
    if (id.equals("CSIPSTR9")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIPSTR9_LOCATION;
    }
    if (id.equals("CSIPSTR10")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIPSTR10_LOCATION;
    }
    if (id.equals("CSIPSTR11")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIPSTR11_LOCATION;
    }
    if (id.equals("CSIPSTR12")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIPSTR12_LOCATION;
    }
    if (id.equals("CSIPSTR13")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIPSTR13_LOCATION;
    }
    if (id.equals("CSIPSTR14")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIPSTR14_LOCATION;
    }
    if (id.equals("CSIPSTR15")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIPSTR15_LOCATION;
    }
    if (id.equals("CSIPSTR16")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIPSTR16_LOCATION;
    }
    if (id.equals("CSIP1")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP1_LOCATION;
    }
    if (id.equals("CSIP2")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP2_LOCATION;
    }
    if (id.equals("CSIP3")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP3_LOCATION;
    }
    if (id.equals("CSIP4")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP4_LOCATION;
    }
    if (id.equals("CSIP5")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP5_LOCATION;
    }
    if (id.equals("CSIP6")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP6_LOCATION;
    }
    if (id.equals("CSIP117")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP117_LOCATION;
    }
    if (id.equals("CSIP7")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP7_LOCATION;
    }
    if (id.equals("CSIP8")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP8_LOCATION;
    }
    if (id.equals("CSIP9")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP9_LOCATION;
    }
    if (id.equals("CSIP10")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP10_LOCATION;
    }
    if (id.equals("CSIP11")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP11_LOCATION;
    }
    if (id.equals("CSIP12")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP12_LOCATION;
    }
    if (id.equals("CSIP13")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP13_LOCATION;
    }
    if (id.equals("CSIP14")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP14_LOCATION;
    }
    if (id.equals("CSIP15")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP15_LOCATION;
    }
    if (id.equals("CSIP16")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP16_LOCATION;
    }
    if (id.equals("CSIP17")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP17_LOCATION;
    }
    if (id.equals("CSIP18")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP18_LOCATION;
    }
    if (id.equals("CSIP19")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP19_LOCATION;
    }
    if (id.equals("CSIP20")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP20_LOCATION;
    }
    if (id.equals("CSIP21")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP21_LOCATION;
    }
    if (id.equals("CSIP22")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP22_LOCATION;
    }
    if (id.equals("CSIP23")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP23_LOCATION;
    }
    if (id.equals("CSIP24")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP24_LOCATION;
    }
    if (id.equals("CSIP25")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP25_LOCATION;
    }
    if (id.equals("CSIP26")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP26_LOCATION;
    }
    if (id.equals("CSIP27")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP27_LOCATION;
    }
    if (id.equals("CSIP28")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP28_LOCATION;
    }
    if (id.equals("CSIP29")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP29_LOCATION;
    }
    if (id.equals("CSIP30")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP30_LOCATION;
    }
    if (id.equals("CSIP31")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP31_LOCATION;
    }
    if (id.equals("CSIP32")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP32_LOCATION;
    }
    if (id.equals("CSIP33")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP33_LOCATION;
    }
    if (id.equals("CSIP34")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP34_LOCATION;
    }
    if (id.equals("CSIP35")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP35_LOCATION;
    }
    if (id.equals("CSIP36")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP36_LOCATION;
    }
    if (id.equals("CSIP37")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP37_LOCATION;
    }
    if (id.equals("CSIP38")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP38_LOCATION;
    }
    if (id.equals("CSIP39")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP39_LOCATION;
    }
    if (id.equals("CSIP40")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP40_LOCATION;
    }
    if (id.equals("CSIP41")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP41_LOCATION;
    }
    if (id.equals("CSIP42")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP42_LOCATION;
    }
    if (id.equals("CSIP43")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP43_LOCATION;
    }
    if (id.equals("CSIP44")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP44_LOCATION;
    }
    if (id.equals("CSIP45")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP45_LOCATION;
    }
    if (id.equals("CSIP46")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP46_LOCATION;
    }
    if (id.equals("CSIP47")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP47_LOCATION;
    }
    if (id.equals("CSIP48")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP48_LOCATION;
    }
    if (id.equals("CSIP49")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP49_LOCATION;
    }
    if (id.equals("CSIP50")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP50_LOCATION;
    }
    if (id.equals("CSIP51")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP51_LOCATION;
    }
    if (id.equals("CSIP52")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP52_LOCATION;
    }
    if (id.equals("CSIP53")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP53_LOCATION;
    }
    if (id.equals("CSIP54")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP54_LOCATION;
    }
    if (id.equals("CSIP55")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP55_LOCATION;
    }
    if (id.equals("CSIP56")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP56_LOCATION;
    }
    if (id.equals("CSIP57")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP57_LOCATION;
    }
    if (id.equals("CSIP58")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP58_LOCATION;
    }
    if (id.equals("CSIP59")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP59_LOCATION;
    }
    if (id.equals("CSIP60")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP60_LOCATION;
    }
    if (id.equals("CSIP113")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP113_LOCATION;
    }
    if (id.equals("CSIP114")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP114_LOCATION;
    }
    if (id.equals("CSIP61")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP61_LOCATION;
    }
    if (id.equals("CSIP62")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP62_LOCATION;
    }
    if (id.equals("CSIP63")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP63_LOCATION;
    }
    if (id.equals("CSIP64")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP64_LOCATION;
    }
    if (id.equals("CSIP65")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP65_LOCATION;
    }
    if (id.equals("CSIP66")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP66_LOCATION;
    }
    if (id.equals("CSIP67")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP67_LOCATION;
    }
    if (id.equals("CSIP68")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP68_LOCATION;
    }
    if (id.equals("CSIP69")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP69_LOCATION;
    }
    if (id.equals("CSIP70")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP70_LOCATION;
    }
    if (id.equals("CSIP71")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP71_LOCATION;
    }
    if (id.equals("CSIP72")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP72_LOCATION;
    }
    if (id.equals("CSIP73")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP73_LOCATION;
    }
    if (id.equals("CSIP74")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP74_LOCATION;
    }
    if (id.equals("CSIP75")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP75_LOCATION;
    }
    if (id.equals("CSIP76")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP76_LOCATION;
    }
    if (id.equals("CSIP77")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP77_LOCATION;
    }
    if (id.equals("CSIP78")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP78_LOCATION;
    }
    if (id.equals("CSIP79")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP79_LOCATION;
    }
    if (id.equals("CSIP80")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP80_LOCATION;
    }
    if (id.equals("CSIP81")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP81_LOCATION;
    }
    if (id.equals("CSIP82")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP82_LOCATION;
    }
    if (id.equals("CSIP83")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP83_LOCATION;
    }
    if (id.equals("CSIP84")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP84_LOCATION;
    }
    if (id.equals("CSIP85")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP85_LOCATION;
    }
    if (id.equals("CSIP86")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP86_LOCATION;
    }
    if (id.equals("CSIP88")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP88_LOCATION;
    }
    if (id.equals("CSIP89")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP89_LOCATION;
    }
    if (id.equals("CSIP90")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP90_LOCATION;
    }
    if (id.equals("CSIP91")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP91_LOCATION;
    }
    if (id.equals("CSIP92")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP92_LOCATION;
    }
    if (id.equals("CSIP93")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP93_LOCATION;
    }
    if (id.equals("CSIP94")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP94_LOCATION;
    }
    if (id.equals("CSIP95")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP95_LOCATION;
    }
    if (id.equals("CSIP96")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP96_LOCATION;
    }
    if (id.equals("CSIP116")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP116_LOCATION;
    }
    if (id.equals("CSIP97")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP97_LOCATION;
    }
    if (id.equals("CSIP98")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP98_LOCATION;
    }
    if (id.equals("CSIP99")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP99_LOCATION;
    }
    if (id.equals("CSIP100")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP100_LOCATION;
    }
    if (id.equals("CSIP118")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP118_LOCATION;
    }
    if (id.equals("CSIP101")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP101_LOCATION;
    }
    if (id.equals("CSIP102")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP102_LOCATION;
    }
    if (id.equals("CSIP103")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP103_LOCATION;
    }
    if (id.equals("CSIP104")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP104_LOCATION;
    }
    if (id.equals("CSIP119")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP119_LOCATION;
    }
    if (id.equals("CSIP105")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP105_LOCATION;
    }
    if (id.equals("CSIP106")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP106_LOCATION;
    }
    if (id.equals("CSIP107")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP107_LOCATION;
    }
    if (id.equals("CSIP108")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP108_LOCATION;
    }
    if (id.equals("CSIP109")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP109_LOCATION;
    }
    if (id.equals("CSIP110")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP110_LOCATION;
    }
    if (id.equals("CSIP111")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP111_LOCATION;
    }
    if (id.equals("CSIP112")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP112_LOCATION;
    }
    return "Not Defined";
  }

  public static String getSpecificationDescription(String id) {
    if (id.equals("CSIPSTR0")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIPSTR0_DESCRIPTION;
    }
    if (id.equals("CSIPSTR1")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIPSTR1_DESCRIPTION;
    }
    if (id.equals("CSIPSTR2")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIPSTR2_DESCRIPTION;
    }
    if (id.equals("CSIPSTR3")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIPSTR3_DESCRIPTION;
    }
    if (id.equals("CSIPSTR4")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIPSTR4_DESCRIPTION;
    }
    if (id.equals("CSIPSTR5")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIPSTR5_DESCRIPTION;
    }
    if (id.equals("CSIPSTR6")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIPSTR6_DESCRIPTION;
    }
    if (id.equals("CSIPSTR7")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIPSTR7_DESCRIPTION;
    }
    if (id.equals("CSIPSTR8")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIPSTR8_DESCRIPTION;
    }
    if (id.equals("CSIPSTR9")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIPSTR9_DESCRIPTION;
    }
    if (id.equals("CSIPSTR10")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIPSTR10_DESCRIPTION;
    }
    if (id.equals("CSIPSTR11")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIPSTR11_DESCRIPTION;
    }
    if (id.equals("CSIPSTR12")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIPSTR12_DESCRIPTION;
    }
    if (id.equals("CSIPSTR13")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIPSTR13_DESCRIPTION;
    }
    if (id.equals("CSIPSTR14")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIPSTR14_DESCRIPTION;
    }
    if (id.equals("CSIPSTR15")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIPSTR15_DESCRIPTION;
    }
    if (id.equals("CSIPSTR16")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIPSTR16_DESCRIPTION;
    }
    if (id.equals("CSIP1")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP1_DESCRIPTION;
    }
    if (id.equals("CSIP2")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP2_DESCRIPTION;
    }
    if (id.equals("CSIP3")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP3_DESCRIPTION;
    }
    if (id.equals("CSIP4")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP4_DESCRIPTION;
    }
    if (id.equals("CSIP5")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP5_DESCRIPTION;
    }
    if (id.equals("CSIP6")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP6_DESCRIPTION;
    }
    if (id.equals("CSIP117")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP117_DESCRIPTION;
    }
    if (id.equals("CSIP7")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP7_DESCRIPTION;
    }
    if (id.equals("CSIP8")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP8_DESCRIPTION;
    }
    if (id.equals("CSIP9")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP9_DESCRIPTION;
    }
    if (id.equals("CSIP10")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP10_DESCRIPTION;
    }
    if (id.equals("CSIP11")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP11_DESCRIPTION;
    }
    if (id.equals("CSIP12")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP12_DESCRIPTION;
    }
    if (id.equals("CSIP13")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP13_DESCRIPTION;
    }
    if (id.equals("CSIP14")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP14_DESCRIPTION;
    }
    if (id.equals("CSIP15")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP15_DESCRIPTION;
    }
    if (id.equals("CSIP16")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP16_DESCRIPTION;
    }
    if (id.equals("CSIP17")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP17_DESCRIPTION;
    }
    if (id.equals("CSIP18")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP18_DESCRIPTION;
    }
    if (id.equals("CSIP19")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP19_DESCRIPTION;
    }
    if (id.equals("CSIP20")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP20_DESCRIPTION;
    }
    if (id.equals("CSIP21")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP21_DESCRIPTION;
    }
    if (id.equals("CSIP22")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP22_DESCRIPTION;
    }
    if (id.equals("CSIP23")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP23_DESCRIPTION;
    }
    if (id.equals("CSIP24")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP24_DESCRIPTION;
    }
    if (id.equals("CSIP25")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP25_DESCRIPTION;
    }
    if (id.equals("CSIP26")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP26_DESCRIPTION;
    }
    if (id.equals("CSIP27")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP27_DESCRIPTION;
    }
    if (id.equals("CSIP28")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP28_DESCRIPTION;
    }
    if (id.equals("CSIP29")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP29_DESCRIPTION;
    }
    if (id.equals("CSIP30")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP30_DESCRIPTION;
    }
    if (id.equals("CSIP31")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP31_DESCRIPTION;
    }
    if (id.equals("CSIP32")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP32_DESCRIPTION;
    }
    if (id.equals("CSIP33")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP33_DESCRIPTION;
    }
    if (id.equals("CSIP34")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP34_DESCRIPTION;
    }
    if (id.equals("CSIP35")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP35_DESCRIPTION;
    }
    if (id.equals("CSIP36")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP36_DESCRIPTION;
    }
    if (id.equals("CSIP37")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP37_DESCRIPTION;
    }
    if (id.equals("CSIP38")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP38_DESCRIPTION;
    }
    if (id.equals("CSIP39")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP39_DESCRIPTION;
    }
    if (id.equals("CSIP40")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP40_DESCRIPTION;
    }
    if (id.equals("CSIP41")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP41_DESCRIPTION;
    }
    if (id.equals("CSIP42")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP42_DESCRIPTION;
    }
    if (id.equals("CSIP43")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP43_DESCRIPTION;
    }
    if (id.equals("CSIP44")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP44_DESCRIPTION;
    }
    if (id.equals("CSIP45")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP45_DESCRIPTION;
    }
    if (id.equals("CSIP46")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP46_DESCRIPTION;
    }
    if (id.equals("CSIP47")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP47_DESCRIPTION;
    }
    if (id.equals("CSIP48")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP48_DESCRIPTION;
    }
    if (id.equals("CSIP49")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP49_DESCRIPTION;
    }
    if (id.equals("CSIP50")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP50_DESCRIPTION;
    }
    if (id.equals("CSIP51")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP51_DESCRIPTION;
    }
    if (id.equals("CSIP52")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP52_DESCRIPTION;
    }
    if (id.equals("CSIP53")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP53_DESCRIPTION;
    }
    if (id.equals("CSIP54")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP54_DESCRIPTION;
    }
    if (id.equals("CSIP55")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP55_DESCRIPTION;
    }
    if (id.equals("CSIP56")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP56_DESCRIPTION;
    }
    if (id.equals("CSIP57")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP57_DESCRIPTION;
    }
    if (id.equals("CSIP58")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP58_DESCRIPTION;
    }
    if (id.equals("CSIP59")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP59_DESCRIPTION;
    }
    if (id.equals("CSIP60")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP60_DESCRIPTION;
    }
    if (id.equals("CSIP113")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP113_DESCRIPTION;
    }
    if (id.equals("CSIP114")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP114_DESCRIPTION;
    }
    if (id.equals("CSIP61")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP61_DESCRIPTION;
    }
    if (id.equals("CSIP62")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP62_DESCRIPTION;
    }
    if (id.equals("CSIP63")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP63_DESCRIPTION;
    }
    if (id.equals("CSIP64")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP64_DESCRIPTION;
    }
    if (id.equals("CSIP65")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP65_DESCRIPTION;
    }
    if (id.equals("CSIP66")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP66_DESCRIPTION;
    }
    if (id.equals("CSIP67")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP67_DESCRIPTION;
    }
    if (id.equals("CSIP68")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP68_DESCRIPTION;
    }
    if (id.equals("CSIP69")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP69_DESCRIPTION;
    }
    if (id.equals("CSIP70")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP70_DESCRIPTION;
    }
    if (id.equals("CSIP71")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP71_DESCRIPTION;
    }
    if (id.equals("CSIP72")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP72_DESCRIPTION;
    }
    if (id.equals("CSIP73")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP73_DESCRIPTION;
    }
    if (id.equals("CSIP74")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP74_DESCRIPTION;
    }
    if (id.equals("CSIP75")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP75_DESCRIPTION;
    }
    if (id.equals("CSIP76")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP76_DESCRIPTION;
    }
    if (id.equals("CSIP77")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP77_DESCRIPTION;
    }
    if (id.equals("CSIP78")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP78_DESCRIPTION;
    }
    if (id.equals("CSIP79")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP79_DESCRIPTION;
    }
    if (id.equals("CSIP80")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP80_DESCRIPTION;
    }
    if (id.equals("CSIP81")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP81_DESCRIPTION;
    }
    if (id.equals("CSIP82")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP82_DESCRIPTION;
    }
    if (id.equals("CSIP83")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP83_DESCRIPTION;
    }
    if (id.equals("CSIP84")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP84_DESCRIPTION;
    }
    if (id.equals("CSIP85")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP85_DESCRIPTION;
    }
    if (id.equals("CSIP86")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP86_DESCRIPTION;
    }
    if (id.equals("CSIP88")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP88_DESCRIPTION;
    }
    if (id.equals("CSIP89")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP89_DESCRIPTION;
    }
    if (id.equals("CSIP90")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP90_DESCRIPTION;
    }
    if (id.equals("CSIP91")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP91_DESCRIPTION;
    }
    if (id.equals("CSIP92")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP92_DESCRIPTION;
    }
    if (id.equals("CSIP93")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP93_DESCRIPTION;
    }
    if (id.equals("CSIP94")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP94_DESCRIPTION;
    }
    if (id.equals("CSIP95")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP95_DESCRIPTION;
    }
    if (id.equals("CSIP96")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP96_DESCRIPTION;
    }
    if (id.equals("CSIP116")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP116_DESCRIPTION;
    }
    if (id.equals("CSIP97")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP97_DESCRIPTION;
    }
    if (id.equals("CSIP98")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP98_DESCRIPTION;
    }
    if (id.equals("CSIP99")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP99_DESCRIPTION;
    }
    if (id.equals("CSIP100")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP100_DESCRIPTION;
    }
    if (id.equals("CSIP118")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP118_DESCRIPTION;
    }
    if (id.equals("CSIP101")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP101_DESCRIPTION;
    }
    if (id.equals("CSIP102")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP102_DESCRIPTION;
    }
    if (id.equals("CSIP103")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP103_DESCRIPTION;
    }
    if (id.equals("CSIP104")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP104_DESCRIPTION;
    }
    if (id.equals("CSIP119")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP119_DESCRIPTION;
    }
    if (id.equals("CSIP105")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP105_DESCRIPTION;
    }
    if (id.equals("CSIP106")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP106_DESCRIPTION;
    }
    if (id.equals("CSIP107")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP107_DESCRIPTION;
    }
    if (id.equals("CSIP108")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP108_DESCRIPTION;
    }
    if (id.equals("CSIP109")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP109_DESCRIPTION;
    }
    if (id.equals("CSIP110")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP110_DESCRIPTION;
    }
    if (id.equals("CSIP111")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP111_DESCRIPTION;
    }
    if (id.equals("CSIP112")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP112_DESCRIPTION;
    }
    return "Not Defined";
  }

  public static String getSpecificationCardinality(String id) {
    if (id.equals("CSIPSTR0")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIPSTR0_CARDINALITY;
    }
    if (id.equals("CSIPSTR1")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIPSTR1_CARDINALITY;
    }
    if (id.equals("CSIPSTR2")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIPSTR2_CARDINALITY;
    }
    if (id.equals("CSIPSTR3")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIPSTR3_CARDINALITY;
    }
    if (id.equals("CSIPSTR4")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIPSTR4_CARDINALITY;
    }
    if (id.equals("CSIPSTR5")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIPSTR5_CARDINALITY;
    }
    if (id.equals("CSIPSTR6")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIPSTR6_CARDINALITY;
    }
    if (id.equals("CSIPSTR7")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIPSTR7_CARDINALITY;
    }
    if (id.equals("CSIPSTR8")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIPSTR8_CARDINALITY;
    }
    if (id.equals("CSIPSTR9")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIPSTR9_CARDINALITY;
    }
    if (id.equals("CSIPSTR10")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIPSTR10_CARDINALITY;
    }
    if (id.equals("CSIPSTR11")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIPSTR11_CARDINALITY;
    }
    if (id.equals("CSIPSTR12")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIPSTR12_CARDINALITY;
    }
    if (id.equals("CSIPSTR13")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIPSTR13_CARDINALITY;
    }
    if (id.equals("CSIPSTR14")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIPSTR14_CARDINALITY;
    }
    if (id.equals("CSIPSTR15")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIPSTR15_CARDINALITY;
    }
    if (id.equals("CSIPSTR16")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIPSTR16_CARDINALITY;
    }
    if (id.equals("CSIP1")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP1_CARDINALITY;
    }
    if (id.equals("CSIP2")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP2_CARDINALITY;
    }
    if (id.equals("CSIP3")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP3_CARDINALITY;
    }
    if (id.equals("CSIP4")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP4_CARDINALITY;
    }
    if (id.equals("CSIP5")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP5_CARDINALITY;
    }
    if (id.equals("CSIP6")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP6_CARDINALITY;
    }
    if (id.equals("CSIP117")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP117_CARDINALITY;
    }
    if (id.equals("CSIP7")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP7_CARDINALITY;
    }
    if (id.equals("CSIP8")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP8_CARDINALITY;
    }
    if (id.equals("CSIP9")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP9_CARDINALITY;
    }
    if (id.equals("CSIP10")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP10_CARDINALITY;
    }
    if (id.equals("CSIP11")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP11_CARDINALITY;
    }
    if (id.equals("CSIP12")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP12_CARDINALITY;
    }
    if (id.equals("CSIP13")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP13_CARDINALITY;
    }
    if (id.equals("CSIP14")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP14_CARDINALITY;
    }
    if (id.equals("CSIP15")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP15_CARDINALITY;
    }
    if (id.equals("CSIP16")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP16_CARDINALITY;
    }
    if (id.equals("CSIP17")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP17_CARDINALITY;
    }
    if (id.equals("CSIP18")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP18_CARDINALITY;
    }
    if (id.equals("CSIP19")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP19_CARDINALITY;
    }
    if (id.equals("CSIP20")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP20_CARDINALITY;
    }
    if (id.equals("CSIP21")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP21_CARDINALITY;
    }
    if (id.equals("CSIP22")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP22_CARDINALITY;
    }
    if (id.equals("CSIP23")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP23_CARDINALITY;
    }
    if (id.equals("CSIP24")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP24_CARDINALITY;
    }
    if (id.equals("CSIP25")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP25_CARDINALITY;
    }
    if (id.equals("CSIP26")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP26_CARDINALITY;
    }
    if (id.equals("CSIP27")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP27_CARDINALITY;
    }
    if (id.equals("CSIP28")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP28_CARDINALITY;
    }
    if (id.equals("CSIP29")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP29_CARDINALITY;
    }
    if (id.equals("CSIP30")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP30_CARDINALITY;
    }
    if (id.equals("CSIP31")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP31_CARDINALITY;
    }
    if (id.equals("CSIP32")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP32_CARDINALITY;
    }
    if (id.equals("CSIP33")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP33_CARDINALITY;
    }
    if (id.equals("CSIP34")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP34_CARDINALITY;
    }
    if (id.equals("CSIP35")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP35_CARDINALITY;
    }
    if (id.equals("CSIP36")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP36_CARDINALITY;
    }
    if (id.equals("CSIP37")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP37_CARDINALITY;
    }
    if (id.equals("CSIP38")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP38_CARDINALITY;
    }
    if (id.equals("CSIP39")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP39_CARDINALITY;
    }
    if (id.equals("CSIP40")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP40_CARDINALITY;
    }
    if (id.equals("CSIP41")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP41_CARDINALITY;
    }
    if (id.equals("CSIP42")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP42_CARDINALITY;
    }
    if (id.equals("CSIP43")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP43_CARDINALITY;
    }
    if (id.equals("CSIP44")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP44_CARDINALITY;
    }
    if (id.equals("CSIP45")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP45_CARDINALITY;
    }
    if (id.equals("CSIP46")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP46_CARDINALITY;
    }
    if (id.equals("CSIP47")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP47_CARDINALITY;
    }
    if (id.equals("CSIP48")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP48_CARDINALITY;
    }
    if (id.equals("CSIP49")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP49_CARDINALITY;
    }
    if (id.equals("CSIP50")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP50_CARDINALITY;
    }
    if (id.equals("CSIP51")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP51_CARDINALITY;
    }
    if (id.equals("CSIP52")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP52_CARDINALITY;
    }
    if (id.equals("CSIP53")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP53_CARDINALITY;
    }
    if (id.equals("CSIP54")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP54_CARDINALITY;
    }
    if (id.equals("CSIP55")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP55_CARDINALITY;
    }
    if (id.equals("CSIP56")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP56_CARDINALITY;
    }
    if (id.equals("CSIP57")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP57_CARDINALITY;
    }
    if (id.equals("CSIP58")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP58_CARDINALITY;
    }
    if (id.equals("CSIP59")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP59_CARDINALITY;
    }
    if (id.equals("CSIP60")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP60_CARDINALITY;
    }
    if (id.equals("CSIP113")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP113_CARDINALITY;
    }
    if (id.equals("CSIP114")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP114_CARDINALITY;
    }
    if (id.equals("CSIP61")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP61_CARDINALITY;
    }
    if (id.equals("CSIP62")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP62_CARDINALITY;
    }
    if (id.equals("CSIP63")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP63_CARDINALITY;
    }
    if (id.equals("CSIP64")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP64_CARDINALITY;
    }
    if (id.equals("CSIP65")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP65_CARDINALITY;
    }
    if (id.equals("CSIP66")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP66_CARDINALITY;
    }
    if (id.equals("CSIP67")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP67_CARDINALITY;
    }
    if (id.equals("CSIP68")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP68_CARDINALITY;
    }
    if (id.equals("CSIP69")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP69_CARDINALITY;
    }
    if (id.equals("CSIP70")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP70_CARDINALITY;
    }
    if (id.equals("CSIP71")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP71_CARDINALITY;
    }
    if (id.equals("CSIP72")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP72_CARDINALITY;
    }
    if (id.equals("CSIP73")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP73_CARDINALITY;
    }
    if (id.equals("CSIP74")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP74_CARDINALITY;
    }
    if (id.equals("CSIP75")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP75_CARDINALITY;
    }
    if (id.equals("CSIP76")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP76_CARDINALITY;
    }
    if (id.equals("CSIP77")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP77_CARDINALITY;
    }
    if (id.equals("CSIP78")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP78_CARDINALITY;
    }
    if (id.equals("CSIP79")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP79_CARDINALITY;
    }
    if (id.equals("CSIP80")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP80_CARDINALITY;
    }
    if (id.equals("CSIP81")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP81_CARDINALITY;
    }
    if (id.equals("CSIP82")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP82_CARDINALITY;
    }
    if (id.equals("CSIP83")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP83_CARDINALITY;
    }
    if (id.equals("CSIP84")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP84_CARDINALITY;
    }
    if (id.equals("CSIP85")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP85_CARDINALITY;
    }
    if (id.equals("CSIP86")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP86_CARDINALITY;
    }
    if (id.equals("CSIP88")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP88_CARDINALITY;
    }
    if (id.equals("CSIP89")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP89_CARDINALITY;
    }
    if (id.equals("CSIP90")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP90_CARDINALITY;
    }
    if (id.equals("CSIP91")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP91_CARDINALITY;
    }
    if (id.equals("CSIP92")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP92_CARDINALITY;
    }
    if (id.equals("CSIP93")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP93_CARDINALITY;
    }
    if (id.equals("CSIP94")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP94_CARDINALITY;
    }
    if (id.equals("CSIP95")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP95_CARDINALITY;
    }
    if (id.equals("CSIP96")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP96_CARDINALITY;
    }
    if (id.equals("CSIP116")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP116_CARDINALITY;
    }
    if (id.equals("CSIP97")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP97_CARDINALITY;
    }
    if (id.equals("CSIP98")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP98_CARDINALITY;
    }
    if (id.equals("CSIP99")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP99_CARDINALITY;
    }
    if (id.equals("CSIP100")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP100_CARDINALITY;
    }
    if (id.equals("CSIP118")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP118_CARDINALITY;
    }
    if (id.equals("CSIP101")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP101_CARDINALITY;
    }
    if (id.equals("CSIP102")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP102_CARDINALITY;
    }
    if (id.equals("CSIP103")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP103_CARDINALITY;
    }
    if (id.equals("CSIP104")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP104_CARDINALITY;
    }
    if (id.equals("CSIP119")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP119_CARDINALITY;
    }
    if (id.equals("CSIP105")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP105_CARDINALITY;
    }
    if (id.equals("CSIP106")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP106_CARDINALITY;
    }
    if (id.equals("CSIP107")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP107_CARDINALITY;
    }
    if (id.equals("CSIP108")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP108_CARDINALITY;
    }
    if (id.equals("CSIP109")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP109_CARDINALITY;
    }
    if (id.equals("CSIP110")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP110_CARDINALITY;
    }
    if (id.equals("CSIP111")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP111_CARDINALITY;
    }
    if (id.equals("CSIP112")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP112_CARDINALITY;
    }
    return "Not Defined";
  }

  public static String getSpecificationLevel(String id) {
    if (id.equals("CSIPSTR0")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIPSTR0_LEVEL;
    }
    if (id.equals("CSIPSTR1")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIPSTR1_LEVEL;
    }
    if (id.equals("CSIPSTR2")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIPSTR2_LEVEL;
    }
    if (id.equals("CSIPSTR3")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIPSTR3_LEVEL;
    }
    if (id.equals("CSIPSTR4")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIPSTR4_LEVEL;
    }
    if (id.equals("CSIPSTR5")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIPSTR5_LEVEL;
    }
    if (id.equals("CSIPSTR6")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIPSTR6_LEVEL;
    }
    if (id.equals("CSIPSTR7")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIPSTR7_LEVEL;
    }
    if (id.equals("CSIPSTR8")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIPSTR8_LEVEL;
    }
    if (id.equals("CSIPSTR9")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIPSTR9_LEVEL;
    }
    if (id.equals("CSIPSTR10")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIPSTR10_LEVEL;
    }
    if (id.equals("CSIPSTR11")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIPSTR11_LEVEL;
    }
    if (id.equals("CSIPSTR12")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIPSTR12_LEVEL;
    }
    if (id.equals("CSIPSTR13")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIPSTR13_LEVEL;
    }
    if (id.equals("CSIPSTR14")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIPSTR14_LEVEL;
    }
    if (id.equals("CSIPSTR15")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIPSTR15_LEVEL;
    }
    if (id.equals("CSIPSTR16")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIPSTR16_LEVEL;
    }
    if (id.equals("CSIP1")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP1_LEVEL;
    }
    if (id.equals("CSIP2")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP2_LEVEL;
    }
    if (id.equals("CSIP3")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP3_LEVEL;
    }
    if (id.equals("CSIP4")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP4_LEVEL;
    }
    if (id.equals("CSIP5")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP5_LEVEL;
    }
    if (id.equals("CSIP6")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP6_LEVEL;
    }
    if (id.equals("CSIP117")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP117_LEVEL;
    }
    if (id.equals("CSIP7")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP7_LEVEL;
    }
    if (id.equals("CSIP8")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP8_LEVEL;
    }
    if (id.equals("CSIP9")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP9_LEVEL;
    }
    if (id.equals("CSIP10")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP10_LEVEL;
    }
    if (id.equals("CSIP11")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP11_LEVEL;
    }
    if (id.equals("CSIP12")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP12_LEVEL;
    }
    if (id.equals("CSIP13")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP13_LEVEL;
    }
    if (id.equals("CSIP14")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP14_LEVEL;
    }
    if (id.equals("CSIP15")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP15_LEVEL;
    }
    if (id.equals("CSIP16")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP16_LEVEL;
    }
    if (id.equals("CSIP17")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP17_LEVEL;
    }
    if (id.equals("CSIP18")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP18_LEVEL;
    }
    if (id.equals("CSIP19")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP19_LEVEL;
    }
    if (id.equals("CSIP20")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP20_LEVEL;
    }
    if (id.equals("CSIP21")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP21_LEVEL;
    }
    if (id.equals("CSIP22")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP22_LEVEL;
    }
    if (id.equals("CSIP23")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP23_LEVEL;
    }
    if (id.equals("CSIP24")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP24_LEVEL;
    }
    if (id.equals("CSIP25")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP25_LEVEL;
    }
    if (id.equals("CSIP26")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP26_LEVEL;
    }
    if (id.equals("CSIP27")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP27_LEVEL;
    }
    if (id.equals("CSIP28")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP28_LEVEL;
    }
    if (id.equals("CSIP29")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP29_LEVEL;
    }
    if (id.equals("CSIP30")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP30_LEVEL;
    }
    if (id.equals("CSIP31")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP31_LEVEL;
    }
    if (id.equals("CSIP32")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP32_LEVEL;
    }
    if (id.equals("CSIP33")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP33_LEVEL;
    }
    if (id.equals("CSIP34")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP34_LEVEL;
    }
    if (id.equals("CSIP35")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP35_LEVEL;
    }
    if (id.equals("CSIP36")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP36_LEVEL;
    }
    if (id.equals("CSIP37")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP37_LEVEL;
    }
    if (id.equals("CSIP38")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP38_LEVEL;
    }
    if (id.equals("CSIP39")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP39_LEVEL;
    }
    if (id.equals("CSIP40")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP40_LEVEL;
    }
    if (id.equals("CSIP41")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP41_LEVEL;
    }
    if (id.equals("CSIP42")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP42_LEVEL;
    }
    if (id.equals("CSIP43")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP43_LEVEL;
    }
    if (id.equals("CSIP44")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP44_LEVEL;
    }
    if (id.equals("CSIP45")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP45_LEVEL;
    }
    if (id.equals("CSIP46")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP46_LEVEL;
    }
    if (id.equals("CSIP47")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP47_LEVEL;
    }
    if (id.equals("CSIP48")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP48_LEVEL;
    }
    if (id.equals("CSIP49")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP49_LEVEL;
    }
    if (id.equals("CSIP50")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP50_LEVEL;
    }
    if (id.equals("CSIP51")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP51_LEVEL;
    }
    if (id.equals("CSIP52")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP52_LEVEL;
    }
    if (id.equals("CSIP53")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP53_LEVEL;
    }
    if (id.equals("CSIP54")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP54_LEVEL;
    }
    if (id.equals("CSIP55")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP55_LEVEL;
    }
    if (id.equals("CSIP56")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP56_LEVEL;
    }
    if (id.equals("CSIP57")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP57_LEVEL;
    }
    if (id.equals("CSIP58")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP58_LEVEL;
    }
    if (id.equals("CSIP59")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP59_LEVEL;
    }
    if (id.equals("CSIP60")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP60_LEVEL;
    }
    if (id.equals("CSIP113")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP113_LEVEL;
    }
    if (id.equals("CSIP114")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP114_LEVEL;
    }
    if (id.equals("CSIP61")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP61_LEVEL;
    }
    if (id.equals("CSIP62")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP62_LEVEL;
    }
    if (id.equals("CSIP63")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP63_LEVEL;
    }
    if (id.equals("CSIP64")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP64_LEVEL;
    }
    if (id.equals("CSIP65")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP65_LEVEL;
    }
    if (id.equals("CSIP66")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP66_LEVEL;
    }
    if (id.equals("CSIP67")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP67_LEVEL;
    }
    if (id.equals("CSIP68")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP68_LEVEL;
    }
    if (id.equals("CSIP69")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP69_LEVEL;
    }
    if (id.equals("CSIP70")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP70_LEVEL;
    }
    if (id.equals("CSIP71")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP71_LEVEL;
    }
    if (id.equals("CSIP72")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP72_LEVEL;
    }
    if (id.equals("CSIP73")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP73_LEVEL;
    }
    if (id.equals("CSIP74")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP74_LEVEL;
    }
    if (id.equals("CSIP75")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP75_LEVEL;
    }
    if (id.equals("CSIP76")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP76_LEVEL;
    }
    if (id.equals("CSIP77")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP77_LEVEL;
    }
    if (id.equals("CSIP78")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP78_LEVEL;
    }
    if (id.equals("CSIP79")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP79_LEVEL;
    }
    if (id.equals("CSIP80")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP80_LEVEL;
    }
    if (id.equals("CSIP81")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP81_LEVEL;
    }
    if (id.equals("CSIP82")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP82_LEVEL;
    }
    if (id.equals("CSIP83")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP83_LEVEL;
    }
    if (id.equals("CSIP84")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP84_LEVEL;
    }
    if (id.equals("CSIP85")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP85_LEVEL;
    }
    if (id.equals("CSIP86")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP86_LEVEL;
    }
    if (id.equals("CSIP88")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP88_LEVEL;
    }
    if (id.equals("CSIP89")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP89_LEVEL;
    }
    if (id.equals("CSIP90")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP90_LEVEL;
    }
    if (id.equals("CSIP91")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP91_LEVEL;
    }
    if (id.equals("CSIP92")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP92_LEVEL;
    }
    if (id.equals("CSIP93")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP93_LEVEL;
    }
    if (id.equals("CSIP94")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP94_LEVEL;
    }
    if (id.equals("CSIP95")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP95_LEVEL;
    }
    if (id.equals("CSIP96")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP96_LEVEL;
    }
    if (id.equals("CSIP116")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP116_LEVEL;
    }
    if (id.equals("CSIP97")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP97_LEVEL;
    }
    if (id.equals("CSIP98")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP98_LEVEL;
    }
    if (id.equals("CSIP99")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP99_LEVEL;
    }
    if (id.equals("CSIP100")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP100_LEVEL;
    }
    if (id.equals("CSIP118")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP118_LEVEL;
    }
    if (id.equals("CSIP101")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP101_LEVEL;
    }
    if (id.equals("CSIP102")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP102_LEVEL;
    }
    if (id.equals("CSIP103")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP103_LEVEL;
    }
    if (id.equals("CSIP104")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP104_LEVEL;
    }
    if (id.equals("CSIP119")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP119_LEVEL;
    }
    if (id.equals("CSIP105")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP105_LEVEL;
    }
    if (id.equals("CSIP106")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP106_LEVEL;
    }
    if (id.equals("CSIP107")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP107_LEVEL;
    }
    if (id.equals("CSIP108")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP108_LEVEL;
    }
    if (id.equals("CSIP109")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP109_LEVEL;
    }
    if (id.equals("CSIP110")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP110_LEVEL;
    }
    if (id.equals("CSIP111")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP111_LEVEL;
    }
    if (id.equals("CSIP112")) {
      return VALIDATION_REPORT_SPECIFICATION_CSIP112_LEVEL;
    }
    return "Not Defined";
  }
}
