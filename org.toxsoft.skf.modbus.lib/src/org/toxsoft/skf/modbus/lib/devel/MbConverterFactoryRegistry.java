package org.toxsoft.skf.modbus.lib.devel;

import org.toxsoft.core.tslib.bricks.strid.more.*;

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
