package org.toxsoft.skf.modbus.lib.conv_old;

import org.toxsoft.core.tslib.bricks.strid.more.*;
import org.toxsoft.skf.modbus.lib.cfg.device.conv.*;
import org.toxsoft.skf.modbus.lib.conv.*;

/**
 * The registry of the converter factories.
 * <p>
 * There is a single static #INSTANCE} of the registry.
 *
 * @author hazard157
 */
public sealed interface IMbConverterFactoryRegistry
    extends IStridablesRegisrty<IMbConverterFactory>
    permits MbConverterFactoryRegistry {

  /**
   * Singleton instance of the interface.
   */
  IMbConverterFactoryRegistry INSTANCE = new MbConverterFactoryRegistry();

}
