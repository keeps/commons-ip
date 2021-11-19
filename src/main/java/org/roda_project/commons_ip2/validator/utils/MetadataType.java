package org.roda_project.commons_ip2.validator.utils;

/** {@author João Gomes <jgomes@keep.pt>}. */
public enum MetadataType {
  MARC {
    @Override
    public String toString() {
      return "MARC";
    }
  },
  MODS {
    @Override
    public String toString() {
      return "MODS";
    }
  },
  EAD {
    @Override
    public String toString() {
      return "EAD";
    }
  },
  DC {
    @Override
    public String toString() {
      return "DC";
    }
  },
  NISOIMG {
    @Override
    public String toString() {
      return "NISOIMG";
    }
  },
  LC_AV {
    @Override
    public String toString() {
      return "LC-AV";
    }
  },
  VRA {
    @Override
    public String toString() {
      return "VRA";
    }
  },
  TEIHDR {
    @Override
    public String toString() {
      return "TEIHDR";
    }
  },
  DDI {
    @Override
    public String toString() {
      return "DDI";
    }
  },
  FGDC {
    @Override
    public String toString() {
      return "FGDC";
    }
  },
  LOM {
    @Override
    public String toString() {
      return "LOM";
    }
  },
  PREMIS {
    @Override
    public String toString() {
      return "PREMIS";
    }
  },
  PREMIS_OBJECT {
    @Override
    public String toString() {
      return "PREMIS:OBJECT";
    }
  },
  PREMIS_AGENT {
    @Override
    public String toString() {
      return "PREMIS:AGENT";
    }
  },
  PREMIS_RIGHTS {
    @Override
    public String toString() {
      return "PREMIS:RIGHTS";
    }
  },
  PREMIS_EVENT {
    @Override
    public String toString() {
      return "PREMIS:EVENT";
    }
  },
  TEXTMD {
    @Override
    public String toString() {
      return "TEXTMD";
    }
  },
  METSRIGHTS {
    @Override
    public String toString() {
      return "METSRIGHTS";
    }
  },
  ISO_19115_2003_NAP {
    @Override
    public String toString() {
      return "ISO 19115:2003 NAP";
    }
  },
  EAC_CPF {
    @Override
    public String toString() {
      return "EAC-CPF";
    }
  },
  LIDO {
    @Override
    public String toString() {
      return "LIDO";
    }
  },
  OTHER {
    @Override
    public String toString() {
      return "OTHER";
    }
  },
}
