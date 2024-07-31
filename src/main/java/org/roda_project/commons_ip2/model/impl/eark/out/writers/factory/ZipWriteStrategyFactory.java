package org.roda_project.commons_ip2.model.impl.eark.out.writers.factory;

import org.roda_project.commons_ip2.model.impl.eark.out.writers.strategy.WriteStrategy;
import org.roda_project.commons_ip2.model.impl.eark.out.writers.strategy.ZipWriteStrategy;

/**
 * @author Miguel Guimarães <mguimaraes@keep.pt>
 */
public class ZipWriteStrategyFactory extends WriteStrategyFactory {
  @Override
  protected WriteStrategy createWriteStrategy() {
    return new ZipWriteStrategy();
  }
}
