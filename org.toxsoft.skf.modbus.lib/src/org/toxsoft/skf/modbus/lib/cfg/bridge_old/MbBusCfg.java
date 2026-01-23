package org.toxsoft.skf.modbus.lib.cfg.bridge_old;

import org.toxsoft.core.tslib.bricks.strid.coll.*;
import org.toxsoft.core.tslib.bricks.strid.impl.*;

/**
 * {@link IMbBusCfg} editable implementation.
 *
 * @author hazard157
 */
public class MbBusCfg
    extends StridableParameterized
    implements IMbBusCfg {

  @Override
  public boolean isRtu() {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public IStridablesList<IMbNodeCfg> listNodeCfgs() {
    // TODO Auto-generated method stub
    return null;
  }

}
