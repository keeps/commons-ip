package org.roda_project.commons_ip2.validator.utils;

/** {@author João Gomes <jgomes@keep.pt>}. */
public enum CHECKSUMTYPE {
  /**
   * Checksum type HAVAL.
   */
  HAVAL {
    /**
     * Method toString.
     * @return {@link String}
     */
    public String toString() {
      return "HAVAL";
    }
  },
  /**
   * Checksum type MD5.
   */
  MD5 {
    /**
     * Method toString.
     * @return {@link String}
     */
    public String toString() {
      return "MD5";
    }
  },
  /**
   * Checksum type SHA-1.
   */
  SHA_1 {
    /**
     * Method toString.
     * @return {@link String}
     */
    public String toString() {
      return "SHA-1";
    }
  },
  /**
   * Checksum type SHA-256.
   */
  SHA_256 {
    /**
     * Method toString.
     * @return {@link String}
     */
    public String toString() {
      return "SHA-256";
    }
  },
  /**
   * Checksum type SHA-384.
   */
  SHA_384 {
    /**
     * Method toString.
     * @return {@link String}
     */
    public String toString() {
      return "SHA-384";
    }
  },
  /**
   * Checksum type SHA-512.
   */
  SHA_512 {
    /**
     * Method toString.
     * @return {@link String}
     */
    public String toString() {
      return "SHA-512";
    }
  },
  /**
   * Checksum type TIGER.
   */
  TIGER {
    /**
     * Method toString.
     * @return {@link String}
     */
    public String toString() {
      return "TIGER";
    }
  },
  /**
   * Checksum type WHIRLPOOL 4.
   */
  WHIRLPOOL_4 {
    /**
     * Method toString.
     * @return {@link String}
     */
    public String toString() {
      return "WHIRLPOOL 4";
    }
  }
}
