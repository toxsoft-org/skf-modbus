package org.toxsoft.skf.modbus.lib.conv_old;

import org.toxsoft.core.tslib.bricks.strid.more.*;
import org.toxsoft.skf.modbus.lib.cfg.device.conv.*;
import org.toxsoft.skf.modbus.lib.conv.*;

/**
 * {@link IMbConverterFactoryRegistry} implementation.
 *
 * @author hazard157
 */
final class MbConverterFactoryRegistry
    extends StridablesRegisrty<IMbConverterFactory>
    implements IMbConverterFactoryRegistry {

  /**
   * Constructor.
   */
  MbConverterFactoryRegistry() {
    super( IMbConverterFactory.class );
    // TODO add built-in converters
  }

}
