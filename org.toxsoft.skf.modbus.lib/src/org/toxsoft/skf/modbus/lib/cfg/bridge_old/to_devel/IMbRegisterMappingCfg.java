package org.toxsoft.skf.modbus.lib.cfg.bridge_old.to_devel;

import org.toxsoft.core.tslib.gw.gwid.*;

public interface IMbRegisterMappingCfg {

  int regNo();

  IGwidList mappedGwids();

  // TODO translator type/kind/ ID ?

  // TODO translator parameters

}
