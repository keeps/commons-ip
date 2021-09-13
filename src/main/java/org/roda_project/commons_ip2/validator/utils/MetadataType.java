package org.roda_project.commons_ip2.validator.utils;

/**
 * @author João Gomes <jgomes@keep.pt>
 */
public enum MetadataType {
  MARC {
    public String toString() {
      return "MARC";
    }
  },
  MODS {
    public String toString() {
      return "MODS";
    }
  },
  EAD {
    public String toString() {
      return "EAD";
    }
  },
  DC {
    public String toString() {
      return "DC";
    }
  },
  NISOIMG {
    public String toString() {
      return "NISOIMG";
    }
  },
  LC_AV {
    public String toString() {
      return "LC-AV";
    }
  },
  VRA {
    public String toString() {
      return "VRA";
    }
  },
  TEIHDR {
    public String toString() {
      return "TEIHDR";
    }
  },
  DDI {
    public String toString() {
      return "DDI";
    }
  },
  FGDC {
    public String toString() {
      return "FGDC";
    }
  },
  LOM {
    public String toString() {
      return "LOM";
    }
  },
  PREMIS {
    public String toString() {
      return "PREMIS";
    }
  },
  PREMIS_OBJECT {
    public String toString() {
      return "PREMIS:OBJECT";
    }
  },
  PREMIS_AGENT {
    public String toString() {
      return "PREMIS:AGENT";
    }
  },
  PREMIS_RIGHTS {
    public String toString() {
      return "PREMIS:RIGHTS";
    }
  },
  PREMIS_EVENT {
    public String toString() {
      return "PREMIS:EVENT";
    }
  },
  TEXTMD {
    public String toString() {
      return "TEXTMD";
    }
  },
  METSRIGHTS {
    public String toString() {
      return "METSRIGHTS";
    }
  },
  ISO_19115_2003_NAP {
    public String toString() {
      return "ISO 19115:2003 NAP";
    }
  },
  EAC_CPF {
    public String toString() {
      return "EAC-CPF";
    }
  },
  LIDO {
    public String toString() {
      return "LIDO";
    }
  },
  OTHER {
    public String toString() {
      return "OTHER";
    }
  },
}
