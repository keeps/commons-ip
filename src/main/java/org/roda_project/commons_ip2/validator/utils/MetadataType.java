package org.roda_project.commons_ip2.validator.utils;

/** {@author João Gomes <jgomes@keep.pt>}. */
public enum MetadataType {
  /**
   * metadata type MARC.
   */
  MARC {
    @Override
    public String toString() {
      return "MARC";
    }
  },
  /**
   * metadata type MODS.
   */
  MODS {
    @Override
    public String toString() {
      return "MODS";
    }
  },
  /**
   * metadata type EAD.
   */
  EAD {
    @Override
    public String toString() {
      return "EAD";
    }
  },
  /**
   * metadata type DC.
   */
  DC {
    @Override
    public String toString() {
      return "DC";
    }
  },
  /**
   * metadata type NISOIMG.
   */
  NISOIMG {
    @Override
    public String toString() {
      return "NISOIMG";
    }
  },
  /**
   * metadata type LC-AV.
   */
  LC_AV {
    @Override
    public String toString() {
      return "LC-AV";
    }
  },
  /**
   * metadata type VRA.
   */
  VRA {
    @Override
    public String toString() {
      return "VRA";
    }
  },
  /**
   * metadata type TEIHDR.
   */
  TEIHDR {
    @Override
    public String toString() {
      return "TEIHDR";
    }
  },
  /**
   * metadata type DDI.
   */
  DDI {
    @Override
    public String toString() {
      return "DDI";
    }
  },
  /**
   * metadata type FGDC.
   */
  FGDC {
    @Override
    public String toString() {
      return "FGDC";
    }
  },
  /**
   * metadata type LOM.
   */
  LOM {
    @Override
    public String toString() {
      return "LOM";
    }
  },
  /**
   * metadata type PREMIS.
   */
  PREMIS {
    @Override
    public String toString() {
      return "PREMIS";
    }
  },
  /**
   * metadata type PREMIS:OBJECT.
   */
  PREMIS_OBJECT {
    @Override
    public String toString() {
      return "PREMIS:OBJECT";
    }
  },
  /**
   * metadata type PREMIS:AGENT.
   */
  PREMIS_AGENT {
    @Override
    public String toString() {
      return "PREMIS:AGENT";
    }
  },
  /**
   * metadata type PREMIS:RIGHTS.
   */
  PREMIS_RIGHTS {
    @Override
    public String toString() {
      return "PREMIS:RIGHTS";
    }
  },
  /**
   * metadata type PREMIS:EVENT.
   */
  PREMIS_EVENT {
    @Override
    public String toString() {
      return "PREMIS:EVENT";
    }
  },
  /**
   * metadata type TEXTMD.
   */
  TEXTMD {
    @Override
    public String toString() {
      return "TEXTMD";
    }
  },
  /**
   * metadata type METSRIGHTS.
   */
  METSRIGHTS {
    @Override
    public String toString() {
      return "METSRIGHTS";
    }
  },
  /**
   * metadata type ISO 19115:2003 NAP.
   */
  ISO_19115_2003_NAP {
    @Override
    public String toString() {
      return "ISO 19115:2003 NAP";
    }
  },
  /**
   * metadata type EAC-CPF.
   */
  EAC_CPF {
    @Override
    public String toString() {
      return "EAC-CPF";
    }
  },
  /**
   * metadata type LIDO.
   */
  LIDO {
    @Override
    public String toString() {
      return "LIDO";
    }
  },
  /**
   * metadata type OTHER.
   */
  OTHER {
    @Override
    public String toString() {
      return "OTHER";
    }
  },
}
