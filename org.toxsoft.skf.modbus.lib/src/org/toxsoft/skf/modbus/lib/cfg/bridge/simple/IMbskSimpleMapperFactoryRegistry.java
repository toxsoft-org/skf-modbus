package org.toxsoft.skf.modbus.lib.cfg.bridge.simple;

import org.toxsoft.core.tslib.bricks.strid.more.*;

/**
 * The registry of factories {@link IMbskSimpleMapperFactory}.
 *
 * @author hazard157
 */
public interface IMbskSimpleMapperFactoryRegistry
    extends IStridablesRegisrty<IMbskSimpleMapperFactory> {

  /**
   * The registry singleton.
   */
  IMbskSimpleMapperFactoryRegistry INSTANCE = new Mb2SkSimpleMapperFactoryRegistry();

}
