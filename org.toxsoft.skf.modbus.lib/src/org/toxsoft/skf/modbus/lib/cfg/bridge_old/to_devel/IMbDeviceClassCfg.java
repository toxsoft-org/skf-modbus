package org.toxsoft.skf.modbus.lib.cfg.bridge_old.to_devel;

import org.toxsoft.core.tslib.bricks.strid.*;
import org.toxsoft.uskat.core.api.sysdescr.dto.*;

/**
 * Class, created to reflect device I/O data to single SK-class.
 *
 * @author hazard157
 */
public interface IMbDeviceClassCfg
    extends IStridableParameterized {

  IDtoClassInfo classInfo();

  IMbDeviceMappingCfg mappingCfg();

}
