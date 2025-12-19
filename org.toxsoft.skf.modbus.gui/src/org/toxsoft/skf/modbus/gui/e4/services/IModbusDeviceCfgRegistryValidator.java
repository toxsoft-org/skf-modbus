package org.toxsoft.skf.modbus.gui.e4.services;

import org.toxsoft.core.tslib.bricks.validator.*;
import org.toxsoft.core.tslib.utils.errors.*;
import org.toxsoft.skf.modbus.lib.cfg.device.*;

/**
 * {@link IModbusDeviceCfgRegistry} changes validator.
 *
 * @author hazard157
 */
public interface IModbusDeviceCfgRegistryValidator {

  /**
   * Checks if new new device configuration can be added.
   *
   * @param aCfg {@link ModbusDeviceCfg} - configuration to add
   * @return {@link ValidationResult} - the check result
   * @throws TsNullArgumentRtException any argument = <code>null</code>
   */
  ValidationResult canAddDeviceCfg( ModbusDeviceCfg aCfg );

  /**
   * Checks if existing device configuration can be replaced.
   *
   * @param aDevId String - device configuration ID to be replaced
   * @param aCfg {@link ModbusDeviceCfg} - new configuration
   * @return {@link ValidationResult} - the check result
   * @throws TsNullArgumentRtException any argument = <code>null</code>
   */
  ValidationResult canReplaceDeviceCfg( String aDevId, ModbusDeviceCfg aCfg );

  /**
   * Checks if device configuration can be removed.
   * <p>
   * Does nothing if there is no device with the specified ID.
   *
   * @param aDevId String - device configuration ID
   * @return {@link ValidationResult} - the check result
   * @throws TsNullArgumentRtException any argument = <code>null</code>
   */
  ValidationResult canRemoveDeviceCfg( String aDevId );
}
