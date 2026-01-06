package org.toxsoft.skf.modbus.gui.e4.services;

import org.toxsoft.core.tslib.bricks.events.change.*;
import org.toxsoft.core.tslib.bricks.strid.coll.*;
import org.toxsoft.core.tslib.bricks.validator.*;
import org.toxsoft.core.tslib.bricks.validator.impl.*;
import org.toxsoft.core.tslib.coll.basis.*;
import org.toxsoft.core.tslib.utils.errors.*;
import org.toxsoft.skf.modbus.lib.cfg.device.*;

/**
 * An editable database (registry) of all known MODBUS devices.
 * <p>
 * Usage:
 * <ul>
 * <li>create the instance of implementation class {@link ModbusDeviceCfgRegistry};</li>
 * <li>load content from external storage using {@link ModbusDeviceCfgRegistry#fillRegistry(IStridablesList)};</li>
 * <li>add listener to {@link #genericChangeEventer()} to save changed content;</li>
 * <li>place reference to {@link IModbusDeviceCfgRegistry} interface to the application context;</li>
 * <li>registry is ready to be used by this GUI library, including M5 models.</li>
 * </ul>
 *
 * @author hazard157
 */
public interface IModbusDeviceCfgRegistry
    extends ITsClearable, IGenericChangeEventCapable {

  /**
   * Returns all device configurations in the registry.
   *
   * @return {@link IStridablesList}&lt;{@link MbDeviceCfg}&gt; - list of device configuration
   */
  IStridablesList<MbDeviceCfg> list();

  /**
   * Adds new device configuration.
   *
   * @param aCfg {@link MbDeviceCfg} - configuration to add
   * @throws TsNullArgumentRtException any argument = <code>null</code>
   * @throws TsValidationFailedRtException failed
   *           {@link IModbusDeviceCfgRegistryValidator#canAddDeviceCfg(MbDeviceCfg)}
   */
  void addDeviceCfg( MbDeviceCfg aCfg );

  /**
   * Replaces existing device configuration.
   *
   * @param aDevId String - device configuration ID to be replaced
   * @param aCfg {@link MbDeviceCfg} - new configuration
   * @throws TsNullArgumentRtException any argument = <code>null</code>
   * @throws TsValidationFailedRtException failed
   *           {@link IModbusDeviceCfgRegistryValidator#canReplaceDeviceCfg(String, MbDeviceCfg)}
   */
  void replaceDeviceCfg( String aDevId, MbDeviceCfg aCfg );

  /**
   * Removes the device configuration.
   * <p>
   * Does nothing if there is no device with the specified ID.
   *
   * @param aDevId String - device configuration ID
   * @throws TsNullArgumentRtException any argument = <code>null</code>
   * @throws TsValidationFailedRtException failed {@link IModbusDeviceCfgRegistryValidator#canRemoveDeviceCfg(String)}
   */
  void removeDeviceCfg( String aDevId );

  // ------------------------------------------------------------------------------------
  // Service support

  /**
   * Returns the service validator.
   *
   * @return {@link ITsValidationSupport}&lt;{@link IModbusDeviceCfgRegistryValidator}&gt; - the service validator
   */
  ITsValidationSupport<IModbusDeviceCfgRegistryValidator> svs();

}
