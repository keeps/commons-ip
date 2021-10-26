package org.roda_project.commons_ip2.validator.reporter.pyipUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.xml.bind.DatatypeConverter;

import org.roda_project.commons_ip2.validator.pyipModel.Checksum;
import org.roda_project.commons_ip2.validator.pyipModel.ChecksumAlg;
import org.roda_project.commons_ip2.validator.pyipModel.IpType;
import org.roda_project.commons_ip2.validator.pyipModel.PackageDetails;
import org.roda_project.commons_ip2.validator.pyipModel.ProfileDetails;
import org.roda_project.commons_ip2.validator.pyipModel.Representation;

/**
 * @author João Gomes <jgomes@keep.pt>
 */
public class InformationPackageUtils {

  public static PackageDetails createPackageDetails(String name, String checksumAlg, String ipPath)
    throws IOException, NoSuchAlgorithmException {
    PackageDetails packageDetails = new PackageDetails();
    packageDetails.setName(name);
    ChecksumAlg algorithim = ChecksumAlg.fromValue(checksumAlg);
    Checksum checksum = new Checksum();
    checksum.setAlgorithm(algorithim);
    checksum.setValue(calculateChecksumPackage(ipPath, algorithim.toString()));
    packageDetails.addChecksumsItem(checksum);
    return packageDetails;
  }

  private static String calculateChecksumPackage(String ipPath, String alg)
    throws IOException, NoSuchAlgorithmException {
    InputStream stream = new FileInputStream(Paths.get(ipPath).toFile());
    MessageDigest messageDigest = MessageDigest.getInstance(alg);
    byte[] buffer = new byte[8192];
    int numOfBytesRead;
    while ((numOfBytesRead = stream.read(buffer)) > 0) {
      messageDigest.update(buffer, 0, numOfBytesRead);
    }
    byte[] hash = messageDigest.digest();
    String fileChecksum = DatatypeConverter.printHexBinary(hash);
    return fileChecksum;
  }

  public static ProfileDetails createProfileDetails(String name, String ipType, String version) {
    ProfileDetails profileDetails = new ProfileDetails();
    profileDetails.setName(name);
    profileDetails.setType(IpType.fromValue(ipType));
    profileDetails.setVersion(version);
    return profileDetails;
  }

  public static Representation createRepresentation(String name) {
    Representation representation = new Representation();
    return representation.name(name);
  }

}
