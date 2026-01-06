package org.toxsoft.skf.modbus.gui.mbedit;

import org.toxsoft.skf.modbus.lib.cfg.device.*;

/**
 * An editor (builder) of the immutable {@link MbDeviceCfg}.
 *
 * @author hazard157
 */
public interface IModbusDeviceCfgEditor {

  

  /**
   * Builds an instance of configuration {@link MbDeviceCfg}.
   *
   * @return {@link MbDeviceCfg} - built instance
   */
  MbDeviceCfg build();

}
