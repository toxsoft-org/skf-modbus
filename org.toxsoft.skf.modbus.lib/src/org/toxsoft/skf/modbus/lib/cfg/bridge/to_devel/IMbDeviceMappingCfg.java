package org.toxsoft.skf.modbus.lib.cfg.bridge.to_devel;

import org.toxsoft.core.tslib.coll.*;
import org.toxsoft.core.tslib.coll.primtypes.*;

public interface IMbDeviceMappingCfg {

  IIntMap<IMbRegisterMappingCfg> registerMappings();

  IList<IMbMultiMappingCfg> multiMappings();

}
