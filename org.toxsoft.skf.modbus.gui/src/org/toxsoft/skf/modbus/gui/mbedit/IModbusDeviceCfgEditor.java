package org.toxsoft.skf.modbus.gui.mbedit;

import org.toxsoft.skf.modbus.lib.cfg.device.*;

/**
 * An editor (builder) of the immutable {@link ModbusDeviceCfg}.
 *
 * @author hazard157
 */
public interface IModbusDeviceCfgEditor {

  

  /**
   * Builds an instance of configuration {@link ModbusDeviceCfg}.
   *
   * @return {@link ModbusDeviceCfg} - built instance
   */
  ModbusDeviceCfg build();

}
