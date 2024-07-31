package org.roda_project.commons_ip2.model.impl.eark.out.writers.factory;

import org.roda_project.commons_ip2.model.impl.eark.out.writers.strategy.FolderWriteStrategy;
import org.roda_project.commons_ip2.model.impl.eark.out.writers.strategy.WriteStrategy;

/**
 * @author Miguel Guimarães <mguimaraes@keep.pt>
 */
public class FolderWriteStrategyFactory extends WriteStrategyFactory {
  @Override
  protected WriteStrategy createWriteStrategy() {
    return new FolderWriteStrategy();
  }
}
