package org.roda_project.commons_ip2.validator.components.structuralMapComponent;

import org.junit.Assert;
import org.junit.Test;
import org.roda_project.commons_ip2.mets_v1_12.beans.DivType;
import org.roda_project.commons_ip2.mets_v1_12.beans.Mets;
import org.roda_project.commons_ip2.mets_v1_12.beans.StructMapType;
import org.roda_project.commons_ip2.validator.state.MetsValidatorState;

public class StructMapValidatorTest {

  private final StructMapValidator220 validator = new StructMapValidator220();

  @Test
  public void csip105PassesWhenRepresentationDivIsDirectChildOfPackageDiv() {
    DivType packageDiv = div("package-div", "package");
    packageDiv.getDiv().add(representationDiv());

    Assert.assertTrue(validator.validateCSIP105(rootMetsState(packageDiv)).isValid());
  }

  @Test
  public void csip105FailsWhenRepresentationDivIsNested() {
    DivType wrapperDiv = div("representations-div", "Representations");
    wrapperDiv.getDiv().add(representationDiv());
    DivType packageDiv = div("package-div", "package");
    packageDiv.getDiv().add(wrapperDiv);

    Assert.assertFalse(validator.validateCSIP105(rootMetsState(packageDiv)).isValid());
  }

  private static DivType representationDiv() {
    DivType representationDiv = div("rep-001-div", "Representations/rep-001");
    DivType.Mptr mptr = new DivType.Mptr();
    mptr.setLOCTYPE("URL");
    mptr.setHref("representations/rep-001/METS.xml");
    representationDiv.getMptr().add(mptr);
    return representationDiv;
  }

  private static DivType div(String id, String label) {
    DivType div = new DivType();
    div.setID(id);
    div.setLABEL(label);
    return div;
  }

  private static MetsValidatorState rootMetsState(DivType packageDiv) {
    StructMapType structMap = new StructMapType();
    structMap.setLABEL("CSIP");
    structMap.setDiv(packageDiv);
    Mets mets = new Mets();
    mets.getStructMap().add(structMap);

    MetsValidatorState state = new MetsValidatorState();
    state.setMets(mets);
    state.setMetsName("METS.xml");
    state.setIsRootMets(true);
    return state;
  }
}
