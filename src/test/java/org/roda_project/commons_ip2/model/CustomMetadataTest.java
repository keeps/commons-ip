/**
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE file at the root of the source
 * tree and available online at
 *
 * https://github.com/keeps/commons-ip
 */
package org.roda_project.commons_ip2.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.roda_project.commons_ip.utils.IPEnums;
import org.roda_project.commons_ip.utils.IPException;
import org.roda_project.commons_ip2.cli.model.enums.WriteStrategyEnum;
import org.roda_project.commons_ip2.cli.utils.SIPBuilderUtils;
import org.roda_project.commons_ip2.model.impl.eark.EARKSIP;
import org.roda_project.commons_ip2.model.impl.eark.out.writers.strategy.WriteStrategy;
import org.roda_project.commons_ip2.utils.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CustomMetadataTest {
  private static final Logger LOGGER = LoggerFactory.getLogger(CustomMetadataTest.class);
  private static Path outputFolder;

  @BeforeClass
  public static void setup() throws Exception {
    // Create a relative output directory in the target folder
    outputFolder = Paths.get("target", "custom-test-output");
    if (!Files.exists(outputFolder)) {
      Files.createDirectories(outputFolder);
    }
    LOGGER.info("Test output will be saved to: {}", outputFolder.toAbsolutePath());
  }

  @AfterClass
  public static void cleanup() throws Exception {
    // Skip deletion to allow examining the files
    // Utils.deletePath(outputFolder);
    LOGGER.info("Test output files are available at: {}", outputFolder);
  }

  @Test
  public void testCustomMetadataTypes() throws IPException, InterruptedException {
    // Create a simple SIP
    SIP sip = new EARKSIP("CUSTOM_MD_SIP", IPContentType.getMIXED(), IPContentInformationType.getMIXED(), "2.1.0");
    sip.setDescription("SIP with custom metadata types");
    
    // Create custom metadata types
    MetadataType customType1 = new MetadataType(MetadataType.MetadataTypeEnum.OTHER).setOtherType("TEST1");
    MetadataType customType2 = new MetadataType(MetadataType.MetadataTypeEnum.OTHER).setOtherType("TEST2");
    
    // Create metadata files using classpath resources
    IPFile descriptiveFile = new IPFile(Paths.get("src", "test", "resources", "data", "descriptive.txt"));
    IPFile preservationFile = new IPFile(Paths.get("src", "test", "resources", "data", "preservation.txt"));
    
    // Add descriptive metadata with custom type at package level
    IPDescriptiveMetadata descriptiveMetadata = new IPDescriptiveMetadata(descriptiveFile, customType1, "1.0");
    sip.addDescriptiveMetadata(descriptiveMetadata);
    
    // Create a representation
    IPRepresentation representation = new IPRepresentation("representation-1");
    sip.addRepresentation(representation);
    
    // Add preservation metadata with custom type at representation level
    IPMetadata preservationMetadata = new IPMetadata(preservationFile, customType2);
    sip.addPreservationMetadataToRepresentation("representation-1", preservationMetadata);
    
    // Build the SIP with a fixed output location
    WriteStrategy writeStrategy = SIPBuilderUtils.getWriteStrategy(WriteStrategyEnum.ZIP, outputFolder);
    Path sipPath = sip.build(writeStrategy);
    
    // Verify the SIP was created
    assertTrue(Files.exists(sipPath));
    
    // Verify the metadata
    List<IPDescriptiveMetadata> descriptiveMetadataList = sip.getDescriptiveMetadata();
    assertEquals(1, descriptiveMetadataList.size());
    assertEquals("TEST1", descriptiveMetadataList.get(0).getMetadataType().asString());
    
    List<IPRepresentation> representations = sip.getRepresentations();
    assertEquals(1, representations.size());
    List<IPMetadata> repPreservationMetadata = representations.get(0).getPreservationMetadata();
    assertEquals(1, repPreservationMetadata.size());
    assertEquals("TEST2", repPreservationMetadata.get(0).getMetadataType().asString());
    
    // Output detailed information about the created files
    LOGGER.info("==============================================");
    LOGGER.info("SIP with custom metadata types created successfully");
    LOGGER.info("Output ZIP file: {}", sipPath);
    LOGGER.info("SIP structure:");
    LOGGER.info("- Package level descriptive metadata with type: {}", descriptiveMetadataList.get(0).getMetadataType().asString());
    LOGGER.info("- Representation '{}' preservation metadata with type: {}", 
        representations.get(0).getRepresentationID(), 
        repPreservationMetadata.get(0).getMetadataType().asString());
    LOGGER.info("==============================================");
  }
  
  @Test
  public void testAllMetadataTypes() throws IPException, InterruptedException {
    // Create a simple SIP
    SIP sip = new EARKSIP("ALL_METADATA_TYPES_SIP", IPContentType.getMIXED(), IPContentInformationType.getMIXED(), "2.1.0");
    sip.setDescription("SIP with all metadata types");
    
    // Create custom metadata types for each category
    MetadataType descriptiveType = new MetadataType(MetadataType.MetadataTypeEnum.OTHER).setOtherType("CUSTOM_DESC");
    MetadataType preservationType = new MetadataType(MetadataType.MetadataTypeEnum.OTHER).setOtherType("CUSTOM_PRES");
    MetadataType technicalType = new MetadataType(MetadataType.MetadataTypeEnum.OTHER).setOtherType("CUSTOM_TECH");
    MetadataType sourceType = new MetadataType(MetadataType.MetadataTypeEnum.OTHER).setOtherType("CUSTOM_SRC");
    MetadataType rightsType = new MetadataType(MetadataType.MetadataTypeEnum.OTHER).setOtherType("CUSTOM_RIGHTS");
    MetadataType otherType = new MetadataType(MetadataType.MetadataTypeEnum.OTHER).setOtherType("CUSTOM_OTHER");
    
    // Create metadata files using relative paths
    IPFile descriptiveFile = new IPFile(Paths.get("src", "test", "resources", "data", "descriptive.txt"));
    IPFile preservationFile = new IPFile(Paths.get("src", "test", "resources", "data", "preservation.txt"));
    IPFile technicalFile = new IPFile(Paths.get("src", "test", "resources", "data", "technical.txt"));
    IPFile sourceFile = new IPFile(Paths.get("src", "test", "resources", "data", "source.txt"));
    IPFile rightsFile = new IPFile(Paths.get("src", "test", "resources", "data", "rights.txt"));
    IPFile otherFile = new IPFile(Paths.get("src", "test", "resources", "data", "other.txt"));
    
    // Create a representation and add it to the SIP
    IPRepresentation representation = new IPRepresentation("representation-1");
    
    // Add technical and source metadata directly to the representation object
    representation.addTechnicalMetadata(new IPMetadata(technicalFile, technicalType));
    representation.addSourceMetadata(new IPMetadata(sourceFile, sourceType));
    
    // Add the representation to the SIP
    sip.addRepresentation(representation);
    
    // Add preservation, rights and other metadata to the representation through the SIP
    sip.addPreservationMetadataToRepresentation("representation-1", new IPMetadata(preservationFile, preservationType));
    sip.addRightsMetadataToRepresentation("representation-1", new IPMetadata(rightsFile, rightsType));
    sip.addOtherMetadataToRepresentation("representation-1", new IPMetadata(otherFile, otherType));
    
    // Add package-level metadata
    sip.addDescriptiveMetadata(new IPDescriptiveMetadata(descriptiveFile, descriptiveType, "1.0"));
    sip.addPreservationMetadata(new IPMetadata(preservationFile, preservationType));
    sip.addRightsMetadata(new IPMetadata(rightsFile, rightsType));
    
    // Build the SIP with a fixed output location
    WriteStrategy writeStrategy = SIPBuilderUtils.getWriteStrategy(WriteStrategyEnum.ZIP, outputFolder);
    Path sipPath = sip.build(writeStrategy);
    
    // Verify the SIP was created
    assertTrue(Files.exists(sipPath));
    
    // Verify all metadata
    List<IPDescriptiveMetadata> descriptiveMetadataList = sip.getDescriptiveMetadata();
    assertEquals(1, descriptiveMetadataList.size());
    assertEquals("CUSTOM_DESC", descriptiveMetadataList.get(0).getMetadataType().asString());
    
    List<IPMetadata> packagePreservationMetadata = sip.getPreservationMetadata();
    assertEquals(1, packagePreservationMetadata.size());
    assertEquals("CUSTOM_PRES", packagePreservationMetadata.get(0).getMetadataType().asString());
    
    List<IPMetadata> packageRightsMetadata = sip.getRightsMetadata();
    assertEquals(1, packageRightsMetadata.size());
    assertEquals("CUSTOM_RIGHTS", packageRightsMetadata.get(0).getMetadataType().asString());
    
    List<IPRepresentation> representations = sip.getRepresentations();
    assertEquals(1, representations.size());
    IPRepresentation rep = representations.get(0);
    
    List<IPMetadata> preservationMetadata = rep.getPreservationMetadata();
    assertEquals(1, preservationMetadata.size());
    assertEquals("CUSTOM_PRES", preservationMetadata.get(0).getMetadataType().asString());
    
    List<IPMetadata> technicalMetadata = rep.getTechnicalMetadata();
    assertEquals(1, technicalMetadata.size());
    assertEquals("CUSTOM_TECH", technicalMetadata.get(0).getMetadataType().asString());
    
    List<IPMetadata> sourceMetadata = rep.getSourceMetadata();
    assertEquals(1, sourceMetadata.size());
    assertEquals("CUSTOM_SRC", sourceMetadata.get(0).getMetadataType().asString());
    
    List<IPMetadata> rightsMetadata = rep.getRightsMetadata();
    assertEquals(1, rightsMetadata.size());
    assertEquals("CUSTOM_RIGHTS", rightsMetadata.get(0).getMetadataType().asString());
    
    List<IPMetadata> otherMetadata = rep.getOtherMetadata();
    assertEquals(1, otherMetadata.size());
    assertEquals("CUSTOM_OTHER", otherMetadata.get(0).getMetadataType().asString());
    
    // Output results
    LOGGER.info("==============================================");
    LOGGER.info("SIP with all metadata types created successfully");
    LOGGER.info("Output ZIP file: {}", sipPath);
    LOGGER.info("SIP structure with custom metadata types:");
    LOGGER.info("- Package level:");
    LOGGER.info("  - Descriptive metadata: {}", descriptiveMetadataList.get(0).getMetadataType().asString());
    LOGGER.info("  - Preservation metadata: {}", packagePreservationMetadata.get(0).getMetadataType().asString());
    LOGGER.info("  - Rights metadata: {}", packageRightsMetadata.get(0).getMetadataType().asString());
    LOGGER.info("- Representation level:");
    LOGGER.info("  - Preservation metadata: {}", preservationMetadata.get(0).getMetadataType().asString());
    LOGGER.info("  - Technical metadata: {}", technicalMetadata.get(0).getMetadataType().asString());
    LOGGER.info("  - Source metadata: {}", sourceMetadata.get(0).getMetadataType().asString());
    LOGGER.info("  - Rights metadata: {}", rightsMetadata.get(0).getMetadataType().asString());
    LOGGER.info("  - Other metadata: {}", otherMetadata.get(0).getMetadataType().asString());
    LOGGER.info("==============================================");
  }
  
  @Test
  public void testMixedMetadataTypes() throws IPException, InterruptedException {
    // Create a simple SIP
    SIP sip = new EARKSIP("MIXED_METADATA_TYPES_SIP", IPContentType.getMIXED(), IPContentInformationType.getMIXED(), "2.1.0");
    sip.setDescription("SIP with a mix of standard and custom metadata types");
    
    // Create custom metadata types (using the consistent enum with setter approach)
    MetadataType customDescriptiveType = new MetadataType(MetadataType.MetadataTypeEnum.OTHER).setOtherType("CUSTOM_DESC");
    MetadataType customPreservationType = new MetadataType(MetadataType.MetadataTypeEnum.OTHER).setOtherType("CUSTOM_PRES");
    MetadataType customTechnicalType = new MetadataType(MetadataType.MetadataTypeEnum.OTHER).setOtherType("CUSTOM_TECH");
    MetadataType customSourceType = new MetadataType(MetadataType.MetadataTypeEnum.OTHER).setOtherType("CUSTOM_SRC");
    MetadataType customRightsType = new MetadataType(MetadataType.MetadataTypeEnum.OTHER).setOtherType("CUSTOM_RIGHTS");
    
    // Create standard metadata types
    MetadataType dcType = new MetadataType(MetadataType.MetadataTypeEnum.DC);
    MetadataType premisType = new MetadataType(MetadataType.MetadataTypeEnum.PREMIS);
    MetadataType nisoImgType = new MetadataType(MetadataType.MetadataTypeEnum.NISOIMG);
    MetadataType modsType = new MetadataType(MetadataType.MetadataTypeEnum.MODS);
    MetadataType metsRightsType = new MetadataType(MetadataType.MetadataTypeEnum.METSRIGHTS);
    
    // Create metadata files for each type using relative paths
    IPFile descriptiveFile = new IPFile(Paths.get("src", "test", "resources", "data", "descriptive.txt"));
    IPFile dcFile = new IPFile(Paths.get("src", "test", "resources", "data", "dc.xml"));
    IPFile preservationFile = new IPFile(Paths.get("src", "test", "resources", "data", "preservation.txt"));
    IPFile premisFile = new IPFile(Paths.get("src", "test", "resources", "data", "premis.xml"));
    IPFile technicalFile = new IPFile(Paths.get("src", "test", "resources", "data", "technical.txt"));
    IPFile sourceFile = new IPFile(Paths.get("src", "test", "resources", "data", "source.txt"));
    IPFile rightsFile = new IPFile(Paths.get("src", "test", "resources", "data", "rights.txt"));
    IPFile otherFile = new IPFile(Paths.get("src", "test", "resources", "data", "other.txt"));
    
    // Create a representation
    IPRepresentation representation = new IPRepresentation("representation-1");
    
    // Add standard metadata to representation
    representation.addTechnicalMetadata(new IPMetadata(technicalFile, nisoImgType));
    representation.addSourceMetadata(new IPMetadata(sourceFile, modsType));
    
    // Add representation to SIP
    sip.addRepresentation(representation);
    
    // Add custom metadata to representation
    sip.addPreservationMetadataToRepresentation("representation-1", new IPMetadata(preservationFile, customPreservationType));
    sip.addRightsMetadataToRepresentation("representation-1", new IPMetadata(rightsFile, metsRightsType));
    sip.addOtherMetadataToRepresentation("representation-1", 
        new IPMetadata(otherFile, new MetadataType(MetadataType.MetadataTypeEnum.OTHER).setOtherType("CUSTOM_OTHER")));
    
    // Create another representation with mixed types
    IPRepresentation representation2 = new IPRepresentation("representation-2");
    sip.addRepresentation(representation2);
    
    // Add custom and standard metadata to second representation
    representation2.addTechnicalMetadata(new IPMetadata(technicalFile, customTechnicalType));
    representation2.addSourceMetadata(new IPMetadata(sourceFile, customSourceType));
    sip.addPreservationMetadataToRepresentation("representation-2", new IPMetadata(premisFile, premisType));
    sip.addRightsMetadataToRepresentation("representation-2", new IPMetadata(rightsFile, customRightsType));
    
    // Add package-level metadata - mix of custom and standard types
    sip.addDescriptiveMetadata(new IPDescriptiveMetadata(descriptiveFile, customDescriptiveType, "1.0"));
    sip.addDescriptiveMetadata(new IPDescriptiveMetadata(dcFile, dcType, "2.0"));
    sip.addPreservationMetadata(new IPMetadata(preservationFile, customPreservationType));
    sip.addPreservationMetadata(new IPMetadata(premisFile, premisType));
    sip.addRightsMetadata(new IPMetadata(rightsFile, metsRightsType));
    
    // Build the SIP with a fixed output location
    WriteStrategy writeStrategy = SIPBuilderUtils.getWriteStrategy(WriteStrategyEnum.ZIP, outputFolder);
    Path sipPath = sip.build(writeStrategy);
    
    // Verify the SIP was created
    assertTrue(Files.exists(sipPath));
    
    // Verify the metadata
    List<IPDescriptiveMetadata> descriptiveMetadataList = sip.getDescriptiveMetadata();
    assertEquals(2, descriptiveMetadataList.size());
    assertEquals("CUSTOM_DESC", descriptiveMetadataList.get(0).getMetadataType().asString());
    assertEquals("DC", descriptiveMetadataList.get(1).getMetadataType().asString());
    
    List<IPMetadata> packagePreservationMetadata = sip.getPreservationMetadata();
    assertEquals(2, packagePreservationMetadata.size());
    assertEquals("CUSTOM_PRES", packagePreservationMetadata.get(0).getMetadataType().asString());
    assertEquals("PREMIS", packagePreservationMetadata.get(1).getMetadataType().asString());
    
    List<IPMetadata> packageRightsMetadata = sip.getRightsMetadata();
    assertEquals(1, packageRightsMetadata.size());
    assertEquals("METSRIGHTS", packageRightsMetadata.get(0).getMetadataType().asString());
    
    // Verify representation 1 metadata
    List<IPRepresentation> representations = sip.getRepresentations();
    assertEquals(2, representations.size());
    IPRepresentation rep1 = representations.get(0);
    
    List<IPMetadata> rep1PreservationMetadata = rep1.getPreservationMetadata();
    assertEquals(1, rep1PreservationMetadata.size());
    assertEquals("CUSTOM_PRES", rep1PreservationMetadata.get(0).getMetadataType().asString());
    
    List<IPMetadata> rep1TechnicalMetadata = rep1.getTechnicalMetadata();
    assertEquals(1, rep1TechnicalMetadata.size());
    assertEquals("NISOIMG", rep1TechnicalMetadata.get(0).getMetadataType().asString());
    
    List<IPMetadata> rep1SourceMetadata = rep1.getSourceMetadata();
    assertEquals(1, rep1SourceMetadata.size());
    assertEquals("MODS", rep1SourceMetadata.get(0).getMetadataType().asString());
    
    List<IPMetadata> rep1RightsMetadata = rep1.getRightsMetadata();
    assertEquals(1, rep1RightsMetadata.size());
    assertEquals("METSRIGHTS", rep1RightsMetadata.get(0).getMetadataType().asString());
    
    // Verify representation 2 metadata
    IPRepresentation rep2 = representations.get(1);
    
    List<IPMetadata> rep2PreservationMetadata = rep2.getPreservationMetadata();
    assertEquals(1, rep2PreservationMetadata.size());
    assertEquals("PREMIS", rep2PreservationMetadata.get(0).getMetadataType().asString());
    
    List<IPMetadata> rep2TechnicalMetadata = rep2.getTechnicalMetadata();
    assertEquals(1, rep2TechnicalMetadata.size());
    assertEquals("CUSTOM_TECH", rep2TechnicalMetadata.get(0).getMetadataType().asString());
    
    List<IPMetadata> rep2SourceMetadata = rep2.getSourceMetadata();
    assertEquals(1, rep2SourceMetadata.size());
    assertEquals("CUSTOM_SRC", rep2SourceMetadata.get(0).getMetadataType().asString());
    
    List<IPMetadata> rep2RightsMetadata = rep2.getRightsMetadata();
    assertEquals(1, rep2RightsMetadata.size());
    assertEquals("CUSTOM_RIGHTS", rep2RightsMetadata.get(0).getMetadataType().asString());
    
    // Output results
    LOGGER.info("==============================================");
    LOGGER.info("SIP with mixed metadata types created successfully");
    LOGGER.info("Output ZIP file: {}", sipPath);
    LOGGER.info("==============================================");
  }
}