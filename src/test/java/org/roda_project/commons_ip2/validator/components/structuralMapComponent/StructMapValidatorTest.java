package org.roda_project.commons_ip2.validator.components.structuralMapComponent;

import org.junit.Assert;
import org.junit.Test;
import org.roda_project.commons_ip2.mets_v1_12.beans.DivType;
import org.roda_project.commons_ip2.mets_v1_12.beans.Mets;
import org.roda_project.commons_ip2.mets_v1_12.beans.MetsType;
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

  /*
   * CSIP101 - a package whose representations are described with slashed
   * "Representations/<name>" file groups must not be flagged, in either structural
   * convention (see issue #390).
   */

  @Test
  public void csip101PassesForCommonsIpConventionWithPerRepresentationMptrDivs() {
    // Convention A: how commons-ip itself writes multi-representation SIPs.
    // Slashed file groups + per-representation divs with mptr, no "Representations"
    // wrapper division and no representation fptr elements.
    MetsValidatorState state = multiRepresentationPerRepDivsState();
    Assert.assertTrue(validator.validateCSIP101(state).isValid());
  }

  @Test
  public void csip101PassesForRepresentationsWrapperWithFptrs() {
    // Convention B (issue #390): a "Representations" wrapper division whose fptr
    // elements point to the slashed "Representations/<name>" file groups.
    MetsValidatorState state = multiRepresentationWrapperState();
    Assert.assertTrue(validator.validateCSIP101(state).isValid());
  }

  @Test
  public void csip101PassesForFlatNoRepresentationCase() {
    // The case CSIP101 is actually about: a single plain "Representations" file
    // group described by a single "Representations" division.
    MetsType.FileSec.FileGrp flat = fileGrp("fg-rep", "Representations");
    DivType wrapper = div("rep-div", "Representations");
    wrapper.getFptr().add(fptr(flat));
    DivType packageDiv = div("package-div", "package");
    packageDiv.getDiv().add(wrapper);

    Assert.assertTrue(validator.validateCSIP101(stateWithFileGrps(packageDiv, flat)).isValid());
  }

  @Test
  public void csip101FailsWhenRepresentationFileGroupsHaveNoRepresentationDivision() {
    // Representation file groups exist but the structural map does not describe
    // them at all - this is the genuine inconsistency CSIP101 must still catch.
    MetsType.FileSec.FileGrp rep1 = fileGrp("fg-rep-001", "Representations/rep-001");
    DivType metadata = div("md-div", "Metadata");
    DivType packageDiv = div("package-div", "package");
    packageDiv.getDiv().add(metadata);

    Assert.assertFalse(validator.validateCSIP101(stateWithFileGrps(packageDiv, rep1)).isValid());
  }

  /* CSIP104 - one file group reference per fptr-element. */

  @Test
  public void csip104PassesForCommonsIpConventionWithoutFptrs() {
    // Convention A has no representation fptr elements, so there is nothing to
    // enforce and it must pass (commons-ip's own output).
    Assert.assertTrue(validator.validateCSIP104(multiRepresentationPerRepDivsState()).isValid());
  }

  @Test
  public void csip104PassesWhenFptrCountMatchesRepresentationFileGroups() {
    Assert.assertTrue(validator.validateCSIP104(multiRepresentationWrapperState()).isValid());
  }

  @Test
  public void csip104FailsWhenFptrCountDiffersFromRepresentationFileGroups() {
    MetsType.FileSec.FileGrp rep1 = fileGrp("fg-rep-001", "Representations/rep-001");
    MetsType.FileSec.FileGrp rep2 = fileGrp("fg-rep-002", "Representations/rep-002");
    // Two representation file groups but the wrapper references only one of them.
    DivType wrapper = div("rep-div", "Representations");
    wrapper.getFptr().add(fptr(rep1));
    DivType packageDiv = div("package-div", "package");
    packageDiv.getDiv().add(wrapper);

    Assert.assertFalse(validator.validateCSIP104(stateWithFileGrps(packageDiv, rep1, rep2)).isValid());
  }

  /* CSIP119 - each Representations fptr/@FILEID must resolve to a file group. */

  @Test
  public void csip119PassesWhenEveryFptrResolvesToARepresentationFileGroup() {
    Assert.assertTrue(validator.validateCSIP119(multiRepresentationWrapperState()).isValid());
  }

  @Test
  public void csip119PassesForCommonsIpConventionWithoutFptrs() {
    Assert.assertTrue(validator.validateCSIP119(multiRepresentationPerRepDivsState()).isValid());
  }

  @Test
  public void csip119FailsWhenASecondFptrDoesNotResolve() {
    // Guards against the "found is not reset per fptr" bug: the first fptr resolves,
    // the second does not, and the requirement must fail.
    MetsType.FileSec.FileGrp rep1 = fileGrp("fg-rep-001", "Representations/rep-001");
    MetsType.FileSec.FileGrp rep2 = fileGrp("fg-rep-002", "Representations/rep-002");
    MetsType.FileSec.FileGrp dangling = fileGrp("fg-rep-999", "Representations/rep-999");
    DivType wrapper = div("rep-div", "Representations");
    wrapper.getFptr().add(fptr(rep1));
    wrapper.getFptr().add(fptr(dangling));
    DivType packageDiv = div("package-div", "package");
    packageDiv.getDiv().add(wrapper);

    // dangling is not part of the file section.
    Assert.assertFalse(validator.validateCSIP119(stateWithFileGrps(packageDiv, rep1, rep2)).isValid());
  }

  @Test
  public void csip119FailsGracefullyWhenFptrFileidDoesNotResolve() {
    // A dangling @FILEID IDREF deserializes to null (and a reference to the wrong
    // element type to another class); the validator must report FAILED rather than
    // throw a ClassCastException/NullPointerException.
    MetsType.FileSec.FileGrp rep1 = fileGrp("fg-rep-001", "Representations/rep-001");
    DivType wrapper = div("rep-div", "Representations");
    DivType.Fptr dangling = new DivType.Fptr();
    dangling.setFILEID(null);
    wrapper.getFptr().add(dangling);
    DivType packageDiv = div("package-div", "package");
    packageDiv.getDiv().add(wrapper);

    Assert.assertFalse(validator.validateCSIP119(stateWithFileGrps(packageDiv, rep1)).isValid());
  }

  /* ---- builders for the two structural conventions ---- */

  private MetsValidatorState multiRepresentationPerRepDivsState() {
    MetsType.FileSec.FileGrp rep1 = fileGrp("fg-rep-001", "Representations/rep-001");
    MetsType.FileSec.FileGrp rep2 = fileGrp("fg-rep-002", "Representations/rep-002");
    DivType packageDiv = div("package-div", "package");
    packageDiv.getDiv().add(representationMptrDiv("rep-001-div", "Representations/rep-001"));
    packageDiv.getDiv().add(representationMptrDiv("rep-002-div", "Representations/rep-002"));
    return stateWithFileGrps(packageDiv, rep1, rep2);
  }

  private MetsValidatorState multiRepresentationWrapperState() {
    MetsType.FileSec.FileGrp rep1 = fileGrp("fg-rep-001", "Representations/rep-001");
    MetsType.FileSec.FileGrp rep2 = fileGrp("fg-rep-002", "Representations/rep-002");
    DivType wrapper = div("rep-div", "Representations");
    wrapper.getFptr().add(fptr(rep1));
    wrapper.getFptr().add(fptr(rep2));
    DivType packageDiv = div("package-div", "package");
    packageDiv.getDiv().add(wrapper);
    return stateWithFileGrps(packageDiv, rep1, rep2);
  }

  /* ---- low level bean builders ---- */

  private static DivType representationDiv() {
    return representationMptrDiv("rep-001-div", "Representations/rep-001");
  }

  private static DivType representationMptrDiv(String id, String label) {
    DivType representationDiv = div(id, label);
    DivType.Mptr mptr = new DivType.Mptr();
    mptr.setLOCTYPE("URL");
    mptr.setHref("representations/" + id + "/METS.xml");
    representationDiv.getMptr().add(mptr);
    return representationDiv;
  }

  private static DivType div(String id, String label) {
    DivType div = new DivType();
    div.setID(id);
    div.setLABEL(label);
    return div;
  }

  private static MetsType.FileSec.FileGrp fileGrp(String id, String use) {
    MetsType.FileSec.FileGrp fileGrp = new MetsType.FileSec.FileGrp();
    fileGrp.setID(id);
    fileGrp.setUSE(use);
    return fileGrp;
  }

  private static DivType.Fptr fptr(MetsType.FileSec.FileGrp target) {
    DivType.Fptr fptr = new DivType.Fptr();
    fptr.setFILEID(target);
    return fptr;
  }

  private static MetsValidatorState rootMetsState(DivType packageDiv) {
    return buildState(packageDiv, null);
  }

  private static MetsValidatorState stateWithFileGrps(DivType packageDiv, MetsType.FileSec.FileGrp... fileGrps) {
    MetsType.FileSec fileSec = new MetsType.FileSec();
    for (MetsType.FileSec.FileGrp fileGrp : fileGrps) {
      fileSec.getFileGrp().add(fileGrp);
    }
    return buildState(packageDiv, fileSec);
  }

  private static MetsValidatorState buildState(DivType packageDiv, MetsType.FileSec fileSec) {
    StructMapType structMap = new StructMapType();
    structMap.setLABEL("CSIP");
    structMap.setDiv(packageDiv);
    Mets mets = new Mets();
    mets.getStructMap().add(structMap);
    if (fileSec != null) {
      mets.setFileSec(fileSec);
    }

    MetsValidatorState state = new MetsValidatorState();
    state.setMets(mets);
    state.setMetsName("METS.xml");
    state.setIsRootMets(true);
    return state;
  }
}
