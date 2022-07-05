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
public final class InformationPackageUtils {

  private InformationPackageUtils() {
    // do nothing
  }

  /**
   * Creates the details of the IP, to the report.
   *
   * @param name
   *          {@link String} name of the IP.
   * @param checksumAlg
   *          {@link String } algorithm to calculate the checksum of the IP.
   * @param ipPath
   *          {@link String} IP path.
   * @return {@link PackageDetails}
   * @throws IOException
   *           if some I/O error occurs.
   * @throws NoSuchAlgorithmException
   *           if the Cryptographic algorithm is not available
   */
  public static PackageDetails createPackageDetails(final String name, final String checksumAlg, final String ipPath)
    throws IOException, NoSuchAlgorithmException {
    final PackageDetails packageDetails = new PackageDetails();
    packageDetails.setName(name);
    final File file = Paths.get(ipPath).toFile();
    if (file.exists() && !file.isDirectory()) {
      final ChecksumAlg algorithim = ChecksumAlg.fromValue(checksumAlg);
      final Checksum checksum = new Checksum();
      checksum.setAlgorithm(algorithim);
      checksum.setValue(calculateChecksumPackage(ipPath, algorithim.toString()));
      packageDetails.addChecksumsItem(checksum);
    } else {
      final ChecksumAlg algorithim = ChecksumAlg.fromValue(checksumAlg);
      final Checksum checksum = new Checksum();
      checksum.setAlgorithm(algorithim);
      checksum.setValue("");
      packageDetails.addChecksumsItem(checksum);
    }
    return packageDetails;
  }

  private static String calculateChecksumPackage(final String ipPath, final String alg)
    throws IOException, NoSuchAlgorithmException {
    final InputStream stream = new FileInputStream(Paths.get(ipPath).toFile());
    final MessageDigest messageDigest = MessageDigest.getInstance(alg);
    final byte[] buffer = new byte[8192];
    int numOfBytesRead;
    while ((numOfBytesRead = stream.read(buffer)) > 0) {
      messageDigest.update(buffer, 0, numOfBytesRead);
    }
    final byte[] hash = messageDigest.digest();
    return DatatypeConverter.printHexBinary(hash);
  }

  /**
   * Set the profile details of the IP.
   *
   * @param name
   *          {@link String} name of the IP.
   * @param ipType
   *          {@link String} type of IP (SIP, AIP, DIP).
   * @param version
   *          {@link String} version.
   * @return {@link ProfileDetails}
   */
  public static ProfileDetails createProfileDetails(final String name, final String ipType, final String version) {
    final ProfileDetails profileDetails = new ProfileDetails();
    profileDetails.setName(name);
    profileDetails.setType(IpType.fromValue(ipType));
    profileDetails.setVersion(version);
    return profileDetails;
  }

  /**
   * Create a new Representation with the given name.
   * 
   * @param name
   *          {@link String}
   * @return {@link Representation}
   */
  public static Representation createRepresentation(final String name) {
    final Representation representation = new Representation();
    return representation.name(name);
  }
}
