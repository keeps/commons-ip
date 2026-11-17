package org.roda_project.commons_ip2.validator.components.structuralMapComponent;

import org.junit.Assert;
import org.junit.Test;
import org.roda_project.commons_ip2.mets_v1_12.beans.DivType;
import org.roda_project.commons_ip2.mets_v1_12.beans.Mets;
import org.roda_project.commons_ip2.mets_v1_12.beans.MetsType;
import org.roda_project.commons_ip2.mets_v1_12.beans.StructMapType;
import org.roda_project.commons_ip2.validator.state.MetsValidatorState;

public class StructMapValidatorTest {

  private static final String REPRESENTATIONS = "Representations";
  private static final String REP_ONE_USE = "Representations/rep-001";
  private static final String REP_TWO_USE = "Representations/rep-002";
  private static final String FG_REP_ONE = "fg-rep-001";
  private static final String FG_REP_TWO = "fg-rep-002";
  private static final String REP_DIV_ID = "rep-div";
  private static final String REP_ONE_DIV_ID = "rep-001-div";
  private static final String PACKAGE_LABEL = "package";
  private static final String PACKAGE_DIV_ID = "package-div";

  private final StructMapValidator220 validator = new StructMapValidator220();

  @Test
  public void csip105PassesWhenRepresentationDivIsDirectChildOfPackageDiv() {
    final DivType packageDiv = div(PACKAGE_DIV_ID, PACKAGE_LABEL);
    packageDiv.getDiv().add(representationDiv());

    Assert.assertTrue(validator.validateCSIP105(rootMetsState(packageDiv)).isValid());
  }

  @Test
  public void csip105FailsWhenRepresentationDivIsNested() {
    final DivType wrapperDiv = div(REP_DIV_ID, REPRESENTATIONS);
    wrapperDiv.getDiv().add(representationDiv());
    final DivType packageDiv = div(PACKAGE_DIV_ID, PACKAGE_LABEL);
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
    final MetsValidatorState state = multiRepresentationPerRepDivsState();
    Assert.assertTrue(validator.validateCSIP101(state).isValid());
  }

  @Test
  public void csip101PassesForRepresentationsWrapperWithFptrs() {
    // Convention B (issue #390): a "Representations" wrapper division whose fptr
    // elements point to the slashed "Representations/<name>" file groups.
    final MetsValidatorState state = multiRepresentationWrapperState();
    Assert.assertTrue(validator.validateCSIP101(state).isValid());
  }

  @Test
  public void csip101PassesForFlatNoRepresentationCase() {
    // The case CSIP101 is actually about: a single plain "Representations" file
    // group described by a single "Representations" division.
    final MetsType.FileSec.FileGrp flat = fileGrp("fg-rep", REPRESENTATIONS);
    final DivType wrapper = div(REP_DIV_ID, REPRESENTATIONS);
    wrapper.getFptr().add(fptr(flat));
    final DivType packageDiv = div(PACKAGE_DIV_ID, PACKAGE_LABEL);
    packageDiv.getDiv().add(wrapper);

    Assert.assertTrue(validator.validateCSIP101(stateWithFileGrps(packageDiv, flat)).isValid());
  }

  @Test
  public void csip101FailsWhenRepresentationFileGroupsHaveNoRepresentationDivision() {
    // Representation file groups exist but the structural map does not describe
    // them at all - this is the genuine inconsistency CSIP101 must still catch.
    final MetsType.FileSec.FileGrp rep1 = fileGrp(FG_REP_ONE, REP_ONE_USE);
    final DivType metadata = div("md-div", "Metadata");
    final DivType packageDiv = div(PACKAGE_DIV_ID, PACKAGE_LABEL);
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
    final MetsType.FileSec.FileGrp rep1 = fileGrp(FG_REP_ONE, REP_ONE_USE);
    final MetsType.FileSec.FileGrp rep2 = fileGrp(FG_REP_TWO, REP_TWO_USE);
    // Two representation file groups but the wrapper references only one of them.
    final DivType wrapper = div(REP_DIV_ID, REPRESENTATIONS);
    wrapper.getFptr().add(fptr(rep1));
    final DivType packageDiv = div(PACKAGE_DIV_ID, PACKAGE_LABEL);
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
    final MetsType.FileSec.FileGrp rep1 = fileGrp(FG_REP_ONE, REP_ONE_USE);
    final MetsType.FileSec.FileGrp rep2 = fileGrp(FG_REP_TWO, REP_TWO_USE);
    final MetsType.FileSec.FileGrp dangling = fileGrp("fg-rep-999", "Representations/rep-999");
    final DivType wrapper = div(REP_DIV_ID, REPRESENTATIONS);
    wrapper.getFptr().add(fptr(rep1));
    wrapper.getFptr().add(fptr(dangling));
    final DivType packageDiv = div(PACKAGE_DIV_ID, PACKAGE_LABEL);
    packageDiv.getDiv().add(wrapper);

    // dangling is not part of the file section.
    Assert.assertFalse(validator.validateCSIP119(stateWithFileGrps(packageDiv, rep1, rep2)).isValid());
  }

  @Test
  public void csip119FailsGracefullyWhenFptrFileidDoesNotResolve() {
    // A dangling @FILEID IDREF deserializes to null (and a reference to the wrong
    // element type to another class); the validator must report FAILED rather than
    // throw a ClassCastException/NullPointerException.
    final MetsType.FileSec.FileGrp rep1 = fileGrp(FG_REP_ONE, REP_ONE_USE);
    final DivType wrapper = div(REP_DIV_ID, REPRESENTATIONS);
    final DivType.Fptr dangling = new DivType.Fptr();
    dangling.setFILEID(null);
    wrapper.getFptr().add(dangling);
    final DivType packageDiv = div(PACKAGE_DIV_ID, PACKAGE_LABEL);
    packageDiv.getDiv().add(wrapper);

    Assert.assertFalse(validator.validateCSIP119(stateWithFileGrps(packageDiv, rep1)).isValid());
  }

  /* ---- builders for the two structural conventions ---- */

  private MetsValidatorState multiRepresentationPerRepDivsState() {
    final MetsType.FileSec.FileGrp rep1 = fileGrp(FG_REP_ONE, REP_ONE_USE);
    final MetsType.FileSec.FileGrp rep2 = fileGrp(FG_REP_TWO, REP_TWO_USE);
    final DivType packageDiv = div(PACKAGE_DIV_ID, PACKAGE_LABEL);
    packageDiv.getDiv().add(representationMptrDiv(REP_ONE_DIV_ID, REP_ONE_USE));
    packageDiv.getDiv().add(representationMptrDiv("rep-002-div", REP_TWO_USE));
    return stateWithFileGrps(packageDiv, rep1, rep2);
  }

  private MetsValidatorState multiRepresentationWrapperState() {
    final MetsType.FileSec.FileGrp rep1 = fileGrp(FG_REP_ONE, REP_ONE_USE);
    final MetsType.FileSec.FileGrp rep2 = fileGrp(FG_REP_TWO, REP_TWO_USE);
    final DivType wrapper = div(REP_DIV_ID, REPRESENTATIONS);
    wrapper.getFptr().add(fptr(rep1));
    wrapper.getFptr().add(fptr(rep2));
    final DivType packageDiv = div(PACKAGE_DIV_ID, PACKAGE_LABEL);
    packageDiv.getDiv().add(wrapper);
    return stateWithFileGrps(packageDiv, rep1, rep2);
  }

  /* ---- low level bean builders ---- */

  private static DivType representationDiv() {
    return representationMptrDiv(REP_ONE_DIV_ID, REP_ONE_USE);
  }

  private static DivType representationMptrDiv(final String id, final String label) {
    final DivType representationDiv = div(id, label);
    final DivType.Mptr mptr = new DivType.Mptr();
    mptr.setLOCTYPE("URL");
    mptr.setHref("representations/" + id + "/METS.xml");
    representationDiv.getMptr().add(mptr);
    return representationDiv;
  }

  private static DivType div(final String id, final String label) {
    final DivType div = new DivType();
    div.setID(id);
    div.setLABEL(label);
    return div;
  }

  private static MetsType.FileSec.FileGrp fileGrp(final String id, final String use) {
    final MetsType.FileSec.FileGrp fileGrp = new MetsType.FileSec.FileGrp();
    fileGrp.setID(id);
    fileGrp.setUSE(use);
    return fileGrp;
  }

  private static DivType.Fptr fptr(final MetsType.FileSec.FileGrp target) {
    final DivType.Fptr fptr = new DivType.Fptr();
    fptr.setFILEID(target);
    return fptr;
  }

  private static MetsValidatorState rootMetsState(final DivType packageDiv) {
    return buildState(packageDiv, null);
  }

  private static MetsValidatorState stateWithFileGrps(final DivType packageDiv,
    final MetsType.FileSec.FileGrp... fileGrps) {
    final MetsType.FileSec fileSec = new MetsType.FileSec();
    for (final MetsType.FileSec.FileGrp fileGrp : fileGrps) {
      fileSec.getFileGrp().add(fileGrp);
    }
    return buildState(packageDiv, fileSec);
  }

  private static MetsValidatorState buildState(final DivType packageDiv, final MetsType.FileSec fileSec) {
    final StructMapType structMap = new StructMapType();
    structMap.setLABEL("CSIP");
    structMap.setDiv(packageDiv);
    final Mets mets = new Mets();
    mets.getStructMap().add(structMap);
    if (fileSec != null) {
      mets.setFileSec(fileSec);
    }

    final MetsValidatorState state = new MetsValidatorState();
    state.setMets(mets);
    state.setMetsName("METS.xml");
    state.setIsRootMets(true);
    return state;
  }
}
