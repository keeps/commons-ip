package org.roda_project.commons_ip2.validator.reporter;

import java.util.Comparator;

/** {@author João Gomes <jgomes@keep.pt>}. */
public class RequirementsComparator implements Comparator<String> {
  private int compareInt(final int c1, final int c2) {
    if (c1 < c2) {
      return -1;
    } else {
      if (c1 > c2) {
        return 1;
      }
      return 0;
    }
  }

  private int calculateWeight(final String o) {
    int c;

    if (o.startsWith("CSIPSTR")) {
      c = 1000;
      c += Integer.parseInt(o.substring("CSIPSTR".length()));
    } else if (o.startsWith("CSIP")) {
      c = 2000;
      c += Integer.parseInt(o.substring("CSIP".length()));
    } else if (o.startsWith("SIP")) {
      c = 4000;
      c += Integer.parseInt(o.substring("SIP".length()));
    } else if (o.startsWith("AIP")) {
      c = 4000;
      c += Integer.parseInt(o.substring("AIP".length()));
    } else {
      c = 9000;
    }
    return c;
  }

  @Override
  public int compare(final String o1, final String o2) {
    return compareInt(calculateWeight(o1), calculateWeight(o2));
  }
}
