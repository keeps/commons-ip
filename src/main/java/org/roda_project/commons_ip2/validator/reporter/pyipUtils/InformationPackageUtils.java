package org.roda_project.commons_ip2.validator.reporter.pyipUtils;

import java.io.File;
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

/** {@author João Gomes <jgomes@keep.pt>}. */
public class InformationPackageUtils {

  private InformationPackageUtils() {
    // do nothing
  }

  /**
   * Creates the details of the IP, to the report.
   *
   * @param name {@link String} name of the IP.
   * @param checksumAlg {@link String } algorithm to calculate the checksum of the IP.
   * @param ipPath {@link String} IP path.
   * @return {@link PackageDetails}
   * @throws IOException if some I/O error occurs.
   * @throws NoSuchAlgorithmException if the Cryptographic algorithm is not available
   */
  public static PackageDetails createPackageDetails(String name, String checksumAlg, String ipPath)
      throws IOException, NoSuchAlgorithmException {
    PackageDetails packageDetails = new PackageDetails();
    packageDetails.setName(name);
    File file = Paths.get(ipPath).toFile();
    if (file.exists() && !file.isDirectory()) {
      ChecksumAlg algorithim = ChecksumAlg.fromValue(checksumAlg);
      Checksum checksum = new Checksum();
      checksum.setAlgorithm(algorithim);
      checksum.setValue(calculateChecksumPackage(ipPath, algorithim.toString()));
      packageDetails.addChecksumsItem(checksum);
    } else {
      ChecksumAlg algorithim = ChecksumAlg.fromValue(checksumAlg);
      Checksum checksum = new Checksum();
      checksum.setAlgorithm(algorithim);
      checksum.setValue("");
      packageDetails.addChecksumsItem(checksum);
    }
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
    return DatatypeConverter.printHexBinary(hash);
  }

  /**
   * Set the profile details of the IP.
   *
   * @param name {@link String} name of the IP.
   * @param ipType {@link String} type of IP (SIP, AIP, DIP).
   * @param version {@link String} version.
   * @return {@link ProfileDetails}
   */
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
