package org.roda_project.commons_ip2.model.impl.eark.out.writers.factory;

import java.nio.file.Path;

import org.roda_project.commons_ip2.model.impl.eark.out.writers.strategy.WriteStrategy;

/**
 * @author Miguel Guimarães <mguimaraes@keep.pt>
 */
public abstract class WriteStrategyFactory {

  public WriteStrategy create(Path buildPath) {
    WriteStrategy strategy = createWriteStrategy();
    strategy.setup(buildPath);
    return strategy;
  }

  protected abstract WriteStrategy createWriteStrategy();
}
