package org.eark.validation.impl.eark;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.eark.mets.beans.DivType;
import org.eark.mets.beans.DivType.Mptr;
import org.eark.mets.beans.FileType;
import org.eark.mets.beans.FileType.FLocat;
import org.eark.mets.beans.Mets;
import org.eark.mets.beans.MetsType.FileSec;
import org.eark.mets.beans.MetsType.FileSec.FileGrp;
import org.eark.mets.beans.StructMapType;
import org.eark.utils.Utils;
import org.eark.validation.Validator;
import org.eark.validation.model.ValidationIssue;
import org.eark.validation.model.ValidationReport;
import org.eark.validation.utils.ValidationErrors;
import org.eark.validation.utils.ValidationUtils;

public class EARKValidator implements Validator {

	@Override
	public ValidationReport isSIPValid(Path sip) {
		ValidationReport report = new ValidationReport();
		report.setValid(true);

		if (!Files.isDirectory(sip)) {
			try {
				Path uncompressed = Files.createTempDirectory("unzipped");
				Utils.unzip(sip, uncompressed);
				sip = uncompressed;
			} catch (IOException e) {
				report = ValidationUtils.addIssue(report, ValidationErrors.UNABLE_TO_UNZIP_SIP,
						ValidationIssue.LEVEL.ERROR, null, Arrays.asList(sip));
			}
		}
		Path mainMETSFile = sip.resolve("METS.xml");
		if (!Files.exists(mainMETSFile)) {
			report = ValidationUtils.addIssue(report, ValidationErrors.NO_MAIN_METS_FILE, ValidationIssue.LEVEL.ERROR,
					null, Arrays.asList(mainMETSFile));
		} else {
			try {
				Mets mainMets = processMetsXML(mainMETSFile);
				if (mainMets.getStructMap() == null || mainMets.getStructMap().size() == 0) {
					report = ValidationUtils.addIssue(report, ValidationErrors.NO_STRUCT_MAP,
							ValidationIssue.LEVEL.ERROR, null, Arrays.asList(mainMETSFile));
				} else {
					StructMapType structMap = mainMets.getStructMap().get(0);
					DivType packageDiv = structMap.getDiv();
					if (packageDiv.getDiv() != null) {
						DivType representationDiv = packageDiv.getDiv().get(0);
						if (representationDiv.getMptr() != null) {
							for (Mptr m : representationDiv.getMptr()) {
								if (m.getHref().startsWith("file://./")) {
									Path representationMets = sip.resolve(m.getHref().replace("file://./", ""));
									if (Files.exists(representationMets)) {
										System.out.println("Exists!");
										ValidationReport representationReport = isRepresentationValid(
												representationMets.getParent());
										if(!representationReport.isValid()){
											report.setValid(false);
										}
										if(representationReport.getIssues()!=null && representationReport.getIssues().size()>0){
											for(ValidationIssue vi : representationReport.getIssues()){
												report = ValidationUtils.addIssue(report, vi.getMessage(), vi.getLevel(), vi.getDescription(), vi.getRelatedItem());
											}
										}
									} else {
										System.out.println("Doesn't exist...");
									}
								} else {
									System.out.println("Doesn't start...");
								}

							}
						}
					}
				}
			} catch (JAXBException jax) {
				report = ValidationUtils.addIssue(report, ValidationErrors.MAIN_METS_NOT_VALID,
						ValidationIssue.LEVEL.ERROR, jax.getMessage(), Arrays.asList(mainMETSFile));
			}

		}
		return report;
	}

	private Mets processMetsXML(Path mainMETSFile) throws JAXBException {
		JAXBContext jaxbContext = JAXBContext.newInstance(Mets.class);
		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		return (Mets) jaxbUnmarshaller.unmarshal(mainMETSFile.toFile());
	}

	private ValidationReport isRepresentationValid(Path representationPath) {
		ValidationReport report = new ValidationReport();
		report.setValid(true);
		Path representationMetsPath = representationPath.resolve("METS.xml");
		try {
			Mets representationMets = processMetsXML(representationMetsPath);
			FileSec filesec = representationMets.getFileSec();
			if (filesec.getFileGrp() != null && filesec.getFileGrp().size() > 0) {
				for (FileGrp fileGrp : filesec.getFileGrp()) {
					if (fileGrp.getFile() != null && fileGrp.getFile().size() > 0) {
						for (FileType ft : fileGrp.getFile()) {
							String checksum = ft.getCHECKSUM();
							String checksumType = ft.getCHECKSUMTYPE();
							if (ft.getFLocat() == null || ft.getFLocat().size() == 0) {
								System.out.println("No FLocat");
								report = ValidationUtils.addIssue(report, ValidationErrors.NO_LOCAT_FOR_FILE,
										ValidationIssue.LEVEL.ERROR, "ID: " + ft.getID(), null);
							} else {
								for (FLocat locat : ft.getFLocat()) {
									if (locat.getHref().startsWith("file://./")) {
										Path filePath = representationPath
												.resolve(locat.getHref().replace("file://./", ""));
										try {
											String fileChecksum = Utils.calculateChecksum(
													Files.newInputStream(filePath), checksumType);
											if (!fileChecksum.equalsIgnoreCase(checksum)) {
												report = ValidationUtils.addIssue(report, ValidationErrors.BAD_CHECKSUM,
														ValidationIssue.LEVEL.ERROR, "File: "+filePath.toString()+ " Mets checksum:"+checksum+"; calculated checksum:"+fileChecksum, Arrays.asList(filePath));
											}
										} catch (NoSuchAlgorithmException nsae) {
											report = ValidationUtils.addIssue(report,
													ValidationErrors.ERROR_COMPUTING_CHECKSUM_NO_SUCH_ALGORYTHM,
													ValidationIssue.LEVEL.ERROR, null, Arrays.asList(filePath));
										} catch (IOException e) {
											e.printStackTrace();
											report = ValidationUtils.addIssue(report,
													ValidationErrors.ERROR_COMPUTING_CHECKSUM,
													ValidationIssue.LEVEL.ERROR, e.getMessage(),
													Arrays.asList(filePath));
										}
									}
								}
							}

						}
					}
				}
			}
		} catch (JAXBException jax) {
			report = ValidationUtils.addIssue(report, ValidationErrors.REPRESENTATION_METS_NOT_VALID,
					ValidationIssue.LEVEL.ERROR, "", Arrays.asList(representationMetsPath));
		}
		return report;
	}

}
