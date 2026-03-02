package org.toxsoft.skf.modbus.lib.cfg.bridge.simple;

import org.toxsoft.core.tslib.bricks.strid.more.*;

/**
 * {@link IMbskSimpleMapperFactoryRegistry} implementation.
 *
 * @author hazard157
 */
class Mb2SkSimpleMapperFactoryRegistry
    extends StridablesRegisrty<IMbskSimpleMapperFactory>
    implements IMbskSimpleMapperFactoryRegistry {

  /**
   * Constructor.
   */
  public Mb2SkSimpleMapperFactoryRegistry() {
    super( IMbskSimpleMapperFactory.class );
  }

}
