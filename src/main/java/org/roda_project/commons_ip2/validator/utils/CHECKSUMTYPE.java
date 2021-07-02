package org.roda_project.commons_ip2.validator.utils;

/**
 * @author João Gomes <jgomes@keep.pt>
 */
public enum CHECKSUMTYPE {
    HAVAL{
        public String toString(){
            return "HAVAL";
        }
    },
    MD5{
    public String toString(){
        return "MD5";
    }
    },
    SHA_1{
        public String toString(){
            return "SHA-1";
        }
    },
    SHA_256{
        public String toString(){
            return "SHA-256";
        }
    },
    SHA_384{
        public String toString(){
            return "SHA-384";
        }
    },
    SHA_512{
        public String toString(){
            return "SHA-512";
        }
    },
    TIGER{
        public String toString(){
            return "TIGER";
        }
    },
    WHIRLPOOL_4{
        public String toString(){
            return "WHIRLPOOL 4";
        }
    }
}
