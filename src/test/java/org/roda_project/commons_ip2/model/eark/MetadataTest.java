/**
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE file at the root of the source
 * tree and available online at
 * <p>
 * https://github.com/keeps/commons-ip
 */
package org.roda_project.commons_ip2.model.eark;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.roda_project.commons_ip.utils.IPException;
import org.roda_project.commons_ip2.model.IPContentInformationType;
import org.roda_project.commons_ip2.model.IPContentType;
import org.roda_project.commons_ip2.model.IPDescriptiveMetadata;
import org.roda_project.commons_ip2.model.IPFile;
import org.roda_project.commons_ip2.model.IPMetadata;
import org.roda_project.commons_ip2.model.IPRepresentation;
import org.roda_project.commons_ip2.model.MetadataStatus;
import org.roda_project.commons_ip2.model.MetadataType;
import org.roda_project.commons_ip2.model.MetadataType.MetadataTypeEnum;
import org.roda_project.commons_ip2.model.SIP;
import org.roda_project.commons_ip2.model.impl.eark.EARKSIP;
import org.roda_project.commons_ip2.utils.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MetadataTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(MetadataTest.class);
    private static Path tempFolder;

    @BeforeClass
    public static void setup() throws Exception {
        tempFolder = Files.createTempDirectory("metadata-test");
    }

    @AfterClass
    public static void cleanup() throws Exception {
        Utils.deletePath(tempFolder);
    }

    @Test
    public void testIPMetadataCreation() {
        // Test IPMetadata constructors and getters/setters
        IPFile metadataFile = new IPFile(Paths.get("src/test/resources/data/descriptive.txt"));

        // Test empty constructor (used for serialization)
        IPMetadata emptyMetadata = new IPMetadata();
        assertNotNull(emptyMetadata);

        // Test with only file
        IPMetadata metadataWithFile = new IPMetadata(metadataFile);
        assertNotNull(metadataWithFile);
        assertNotNull(metadataWithFile.getId());
        assertEquals(metadataFile, metadataWithFile.getMetadata());
        assertEquals(MetadataTypeEnum.OTHER, metadataWithFile.getMetadataType().getType());
        Assert.assertEquals(MetadataStatus.CURRENT, metadataWithFile.getMetadataStatus());

        // Test with file and type
        MetadataType dcType = new MetadataType(MetadataTypeEnum.DC);
        IPMetadata metadataWithFileAndType = new IPMetadata(metadataFile, dcType);
        assertEquals(dcType, metadataWithFileAndType.getMetadataType());

        // Test with ID, file and type
        String id = "test-id-123";
        IPMetadata metadataWithIdFileAndType = new IPMetadata(id, metadataFile, dcType);
        assertEquals(id, metadataWithIdFileAndType.getId());

        // Test setters
        IPMetadata metadata = new IPMetadata();
        metadata.setId("custom-id");
        metadata.setMetadata(metadataFile);
        metadata.setMetadataType(MetadataTypeEnum.PREMIS);
        metadata.setMetadataStatus(MetadataStatus.SUPERSEDED);

        if (Utils.getCurrentTime().isPresent()) {
            metadata.setCreateDate(Utils.getCurrentTime().get());
        }

        assertEquals("custom-id", metadata.getId());
        assertEquals(metadataFile, metadata.getMetadata());
        assertEquals(MetadataTypeEnum.PREMIS, metadata.getMetadataType().getType());
        assertEquals(MetadataStatus.SUPERSEDED, metadata.getMetadataStatus());

        // Test toString method
        assertNotNull(metadata.toString());
    }

    @Test
    public void testIPDescriptiveMetadataCreation() {
        // Test IPDescriptiveMetadata constructors and getters/setters
        IPFile metadataFile = new IPFile(Paths.get("src/test/resources/data/descriptive.txt"));
        MetadataType dcType = new MetadataType(MetadataTypeEnum.DC);
        String version = "1.0";

        // Test with ID, file, type and version
        String id = "desc-metadata-id";
        IPDescriptiveMetadata descriptiveWithAll = new IPDescriptiveMetadata(id, metadataFile, dcType, version);
        assertEquals(id, descriptiveWithAll.getId());
        assertEquals(metadataFile, descriptiveWithAll.getMetadata());
        assertEquals(dcType, descriptiveWithAll.getMetadataType());
        assertEquals(version, descriptiveWithAll.getMetadataVersion());

        // Test with file, type and version
        IPDescriptiveMetadata descriptiveWithoutId = new IPDescriptiveMetadata(metadataFile, dcType, version);
        assertNotNull(descriptiveWithoutId.getId());
        assertEquals(version, descriptiveWithoutId.getMetadataVersion());

        // Test setter
        String newVersion = "2.0";
        descriptiveWithoutId.setMetadataVersion(newVersion);
        assertEquals(newVersion, descriptiveWithoutId.getMetadataVersion());

        // Test toString method
        assertNotNull(descriptiveWithoutId.toString());
    }

    @Test
    public void testMetadataTypeEnum() {
        // Test MetadataType constructors and getters/setters

        // Test with enum value
        MetadataType dcType = new MetadataType(MetadataTypeEnum.DC);
        assertEquals(MetadataTypeEnum.DC, dcType.getType());
        assertEquals("", dcType.getOtherType());
        assertEquals("DC", dcType.asString());

        // Test with string value that matches enum
        MetadataType modsType = new MetadataType("MODS");
        assertEquals(MetadataTypeEnum.MODS, modsType.getType());

        // Test with string value that doesn't match enum
        String customType = "CUSTOM_TYPE";
        MetadataType customMetadataType = new MetadataType(customType);
        assertEquals(MetadataTypeEnum.OTHER, customMetadataType.getType());
        assertEquals(customType, customMetadataType.getOtherType());
        assertEquals(customType, customMetadataType.asString());

        // Test setter
        String newCustomType = "NEW_CUSTOM_TYPE";
        customMetadataType.setOtherType(newCustomType);
        assertEquals(newCustomType, customMetadataType.getOtherType());

        // Test toString method
        assertNotNull(customMetadataType.toString());

        // Test equals and hashCode
        MetadataType dcType2 = new MetadataType(MetadataTypeEnum.DC);
        assertEquals(dcType, dcType2);
        assertEquals(dcType.hashCode(), dcType2.hashCode());

        MetadataType customType1 = new MetadataType("CUSTOM");
        MetadataType customType2 = new MetadataType("CUSTOM");
        assertEquals(customType1, customType2);
    }

    @Test
    public void testMetadataStatus() {
        // Test MetadataStatus enum and parse methods

        // Test parse with valid value
        assertEquals(MetadataStatus.CURRENT, MetadataStatus.parse("CURRENT"));
        assertEquals(MetadataStatus.SUPERSEDED, MetadataStatus.parse("SUPERSEDED"));

        // Test parse with invalid value (should return default CURRENT)
        assertEquals(MetadataStatus.CURRENT, MetadataStatus.parse("INVALID"));

        // Test parse with null (should return default CURRENT)
        assertEquals(MetadataStatus.CURRENT, MetadataStatus.parse(null));

        // Test parse with custom default
        assertEquals(MetadataStatus.SUPERSEDED, MetadataStatus.parse("INVALID", MetadataStatus.SUPERSEDED));
        assertEquals(MetadataStatus.SUPERSEDED, MetadataStatus.parse(null, MetadataStatus.SUPERSEDED));
    }

    @Test
    public void testAddMetadataToIP() throws IPException {
        // Create a simple SIP
        SIP sip = new EARKSIP("SIP_ID_1", IPContentType.getMIXED(), IPContentInformationType.getMIXED(), "2.1.0");

        // Create metadata files
        IPFile descriptiveFile = new IPFile(Paths.get("src/test/resources/data/descriptive.txt"));
        IPFile technicalFile = new IPFile(Paths.get("src/test/resources/data/technical.txt"));
        IPFile rightsFile = new IPFile(Paths.get("src/test/resources/data/rights.txt"));
        IPFile sourceFile = new IPFile(Paths.get("src/test/resources/data/source.txt"));

        // Add descriptive metadata
        IPDescriptiveMetadata descriptive = new IPDescriptiveMetadata(descriptiveFile,
                new MetadataType(MetadataTypeEnum.DC), "1.0");
        sip.addDescriptiveMetadata(descriptive);
        assertEquals(1, sip.getDescriptiveMetadata().size());
        assertEquals(descriptive, sip.getDescriptiveMetadata().get(0));

        // Add preservation metadata
        IPMetadata preservation = new IPMetadata(descriptiveFile)
                .setMetadataType(MetadataTypeEnum.PREMIS);
        sip.addPreservationMetadata(preservation);
        assertEquals(1, sip.getPreservationMetadata().size());
        assertEquals(preservation, sip.getPreservationMetadata().get(0));

        // Add technical metadata
        IPMetadata technical = new IPMetadata(technicalFile)
                .setMetadataType(MetadataTypeEnum.NISOIMG);
        sip.addTechnicalMetadata(technical);
        assertEquals(1, sip.getTechnicalMetadata().size());
        assertEquals(technical, sip.getTechnicalMetadata().get(0));

        // Add source metadata
        IPMetadata source = new IPMetadata(sourceFile, new MetadataType(MetadataTypeEnum.DC));
        sip.addSourceMetadata(source);
        assertEquals(1, sip.getSourceMetadata().size());
        assertEquals(source, sip.getSourceMetadata().get(0));

        // Add rights metadata
        IPMetadata rights = new IPMetadata(rightsFile, new MetadataType(MetadataTypeEnum.DC));
        sip.addRightsMetadata(rights);
        assertEquals(1, sip.getRightsMetadata().size());
        assertEquals(rights, sip.getRightsMetadata().get(0));

        // Add other metadata
        MetadataType otherType = new MetadataType(MetadataTypeEnum.OTHER).setOtherType("CUSTOM_TYPE");
        IPMetadata other = new IPMetadata(descriptiveFile, otherType);
        sip.addOtherMetadata(other);
        assertEquals(1, sip.getOtherMetadata().size());
        assertEquals(other, sip.getOtherMetadata().get(0));
    }

    @Test
    public void testAddMetadataToRepresentation() throws IPException {
        // Create a simple SIP
        SIP sip = new EARKSIP("SIP_ID_1", IPContentType.getMIXED(), IPContentInformationType.getMIXED(), "2.1.0");

        // Create a representation
        IPRepresentation representation = new IPRepresentation("representation-1");
        sip.addRepresentation(representation);

        // Create metadata files
        IPFile descriptiveFile = new IPFile(Paths.get("src/test/resources/data/descriptive.txt"));
        IPFile technicalFile = new IPFile(Paths.get("src/test/resources/data/technical.txt"));
        IPFile rightsFile = new IPFile(Paths.get("src/test/resources/data/rights.txt"));
        IPFile sourceFile = new IPFile(Paths.get("src/test/resources/data/source.txt"));

        // Add descriptive metadata to representation
        IPDescriptiveMetadata descriptive = new IPDescriptiveMetadata(descriptiveFile,
                new MetadataType(MetadataTypeEnum.DC), "1.0");
        sip.addDescriptiveMetadataToRepresentation("representation-1", descriptive);
        assertEquals(1, representation.getDescriptiveMetadata().size());
        assertEquals(descriptive, representation.getDescriptiveMetadata().get(0));

        // Add preservation metadata to representation
        IPMetadata preservation = new IPMetadata(descriptiveFile)
                .setMetadataType(MetadataTypeEnum.PREMIS);
        sip.addPreservationMetadataToRepresentation("representation-1", preservation);
        assertEquals(1, representation.getPreservationMetadata().size());
        assertEquals(preservation, representation.getPreservationMetadata().get(0));

        // Add technical metadata directly to representation
        IPMetadata technical = new IPMetadata(technicalFile)
                .setMetadataType(MetadataTypeEnum.NISOIMG);
        representation.addTechnicalMetadata(technical);
        assertEquals(1, representation.getTechnicalMetadata().size());
        assertEquals(technical, representation.getTechnicalMetadata().get(0));

        // Add source metadata directly to representation
        IPMetadata source = new IPMetadata(sourceFile, new MetadataType(MetadataTypeEnum.DC));
        representation.addSourceMetadata(source);
        assertEquals(1, representation.getSourceMetadata().size());
        assertEquals(source, representation.getSourceMetadata().get(0));

        // Add rights metadata to representation
        IPMetadata rights = new IPMetadata(rightsFile, new MetadataType(MetadataTypeEnum.DC));
        sip.addRightsMetadataToRepresentation("representation-1", rights);
        assertEquals(1, representation.getRightsMetadata().size());
        assertEquals(rights, representation.getRightsMetadata().get(0));

        // Add other metadata to representation
        MetadataType otherType = new MetadataType(MetadataTypeEnum.OTHER).setOtherType("CUSTOM_TYPE");
        IPMetadata other = new IPMetadata(descriptiveFile, otherType);
        sip.addOtherMetadataToRepresentation("representation-1", other);
        assertEquals(1, representation.getOtherMetadata().size());
        assertEquals(other, representation.getOtherMetadata().get(0));
    }

    @Test
    public void testOtherType() {
        // Create a metadata with OTHER type
        IPFile metadataFile = new IPFile(Paths.get("src/test/resources/data/descriptive.txt"));

        // Using the otherType in the MetadataType constructor
        MetadataType customType = new MetadataType("CUSTOM");
        IPMetadata metadata = new IPMetadata(metadataFile, customType);

        assertEquals(MetadataTypeEnum.OTHER, metadata.getMetadataType().getType());
        assertEquals("CUSTOM", metadata.getMetadataType().getOtherType());
        assertEquals("CUSTOM", metadata.getMetadataType().asString());

        // Using the setOtherType method
        MetadataType otherType = new MetadataType(MetadataTypeEnum.OTHER);
        otherType.setOtherType("ANOTHER_CUSTOM");

        IPMetadata anotherMetadata = new IPMetadata(metadataFile, otherType);
        assertEquals(MetadataTypeEnum.OTHER, anotherMetadata.getMetadataType().getType());
        assertEquals("ANOTHER_CUSTOM", anotherMetadata.getMetadataType().getOtherType());
        assertEquals("ANOTHER_CUSTOM", anotherMetadata.getMetadataType().asString());
    }
}