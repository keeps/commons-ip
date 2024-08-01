package org.roda_project.commons_ip2.validator.components.sipMetsRootComponent.sipMetsHdrComponent;

import java.util.List;

import org.roda_project.commons_ip2.mets_v1_12.beans.MetsType;
import org.roda_project.commons_ip2.validator.reporter.ReporterDetails;
import org.roda_project.commons_ip2.validator.state.MetsValidatorState;
import org.roda_project.commons_ip2.validator.utils.Message;

/**
 * @author Carlos Afonso <cafonso@keep.pt>
 */
public class SipMetsHdrValidator220 extends SipMetsHdrValidator {
  @Override
  protected String getSIPVersion() {
    return "SIP-2.2.0";
  }

  /*
   * metsHdr/altRecordID An optional reference code indicating where in the
   * archival hierarchy the package shall be placed in the OAIS. @TYPE is used
   * with the value “REFERENCECODE”. Example: FM 12-2387/12726, 2007-09-19See
   * also: Alternative record ID’s
   */
  @Override
  protected ReporterDetails validateSIP7(final MetsValidatorState metsValidatorState, final MetsType.MetsHdr metsHdr) {
    final List<MetsType.MetsHdr.AltRecordID> altRecordIDS = metsHdr.getAltRecordID();
    int count = 0;
    if (altRecordIDS != null && !altRecordIDS.isEmpty()) {
      for (MetsType.MetsHdr.AltRecordID altRecordID : altRecordIDS) {
        final String type = altRecordID.getTYPE();
        if ("REFERENCECODE".equals(type)) {
          count++;
        }
      }
      if (count > 1) {
        return new ReporterDetails(getSIPVersion(),
          Message.createErrorMessage("Can't have more than one metsHdr/altRecordID of the type REFERENCECODE",
            metsValidatorState.getMetsName(), metsValidatorState.isRootMets()),
          false, false);
      }
    }
    return new ReporterDetails();
  }

}
