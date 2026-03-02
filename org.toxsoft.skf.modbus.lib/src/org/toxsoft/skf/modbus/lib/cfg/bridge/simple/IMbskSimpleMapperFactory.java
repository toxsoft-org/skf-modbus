package org.toxsoft.skf.modbus.lib.cfg.bridge.simple;

import org.toxsoft.core.tslib.av.metainfo.*;
import org.toxsoft.core.tslib.av.opset.*;
import org.toxsoft.core.tslib.bricks.strid.*;
import org.toxsoft.core.tslib.bricks.strid.coll.*;

/**
 * The {@link IMbskSimpleMapper} factory.
 *
 * @author hazard157
 */
public interface IMbskSimpleMapperFactory
    extends IStridableParameterized {

  /**
   * Returns the option definitions used as creation arguments in {@link #create(IOptionSet)}.
   *
   * @return {@link IStridablesList}&lt;{@link IDataDef}&gt; - option definitions
   */
  IStridablesList<IDataDef> optionDefs();

  /**
   * Creates instance.
   *
   * @param aParams {@link IOptionSet} - the creation parameters as configured by the user
   * @return {@link AbstractMbskSimpleMapper} - created instance
   */
  AbstractMbskSimpleMapper create( IOptionSet aParams );

}
