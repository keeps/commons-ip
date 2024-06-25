package org.roda_project.commons_ip2.cli.model.enums;

/**
 * @author Miguel Guimarães <mguimaraes@keep.pt>
 */
public enum ChecksumAlgorithm {
  SHA3512("SHA3-512"), SHA384("SHA-384"), SHA("SHA"), SHA3384("SHA3-384"), SHA224("SHA-224"), SHA512("SHA-512"),
  SHA256("SHA-256"), MD2("MD2"), SHA3256("SHA3-256"), MD5("MD5"), SHA3224("SHA3-224");

  private final String type;

  ChecksumAlgorithm(String checksumAlgorithm) {
    this.type = checksumAlgorithm;
  }

  @Override
  public String toString() {
    return type;
  }
}
