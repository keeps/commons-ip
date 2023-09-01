package org.roda_project.commons_ip2.validator.components.metsRootComponent;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.namespace.QName;

/**
 * @author Carlos Afonso <cafonso@keep.pt>
 */
public class AmdSec {
  private List<MdSec> techMD;
  private List<MdSec> rightsMD;
  private List<MdSec> sourceMD;
  private List<MdSec> digiprovMD;
  private String id;
  private Map<QName, String> otherAttributes = new HashMap<QName, String>();

  public List<MdSec> getTechMD() {
    return techMD;
  }

  public void setTechMD(List<MdSec> techMD) {
    this.techMD.addAll(techMD);
  }

  public List<MdSec> getRightsMD() {
    return rightsMD;
  }

  public void setRightsMD(List<MdSec> rightsMD) {
    this.rightsMD.addAll(rightsMD);
  }

  public List<MdSec> getSourceMD() {
    return sourceMD;
  }

  public void setSourceMD(List<MdSec> sourceMD) {
    this.sourceMD.addAll(sourceMD);
  }

  public List<MdSec> getDigiprovMD() {
    return digiprovMD;
  }

  public void setDigiprovMD(List<MdSec> digiprovMD) {
    this.digiprovMD.addAll(digiprovMD);
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public Map<QName, String> getOtherAttributes() {
    return otherAttributes;
  }

  public void setOtherAttributes(Map<QName, String> otherAttributes) {
    this.otherAttributes = otherAttributes;
  }
}
