package org.toxsoft.skf.modbus.lib.cfg.bridge;

import org.toxsoft.core.tslib.av.opset.*;
import org.toxsoft.core.tslib.av.utils.*;
import org.toxsoft.core.tslib.bricks.events.change.*;
import org.toxsoft.core.tslib.bricks.strid.*;
import org.toxsoft.core.tslib.bricks.strid.coll.*;
import org.toxsoft.core.tslib.bricks.validator.*;
import org.toxsoft.core.tslib.bricks.validator.impl.*;
import org.toxsoft.core.tslib.coll.*;
import org.toxsoft.core.tslib.utils.errors.*;
import org.toxsoft.skf.modbus.lib.cfg.device.*;

/**
 * Configuration of the single MODBUS - USkat bridge.
 * <p>
 * Each USkat-<b><i>MODBUS Bridge</i></b> is a USkat program component running standalone or as a part of the other
 * USkat component. Each bridge servers several MOSBUS Buses, either TCP or RTU. TCP bus is an Ethernet network and RTU
 * is serial (RS-485) physical bus. Each <b><i>MOSBUS Bus</i></b> is described by the separate {@link IMbBusCfg} from
 * the list {@link #listBusCfgs()}, There are several <b><i>MODBUS Nodes</i></b> on each bus listed by
 * {@link IMbBusCfg#listNodeCfgs()}. Each node on bus represents an existing physical <b><i>MODBUS Device</i></b>. Node
 * contains reference to the device configuration - an ID {@link IMbNodeCfg#deviceId()}. Configuration of the device may
 * be found in registry {@link #listUsedDevices()}. The Several nodes representing same kind of device has the same
 * device ID while representing different physical devices of the same kind, Each Device has available <b><i>MODBUS
 * Registers</i></b> listed in {@link MbDeviceCfg#registerCfs()}. The main part of node configuration is
 * <b><i>MODBUS-USkat Mapping</i></b>. Mapping describes how data read from registers will converted to the USkat
 * entities and vice versa - how the USkat manipulates (writes) MODBUS registers. More about mapping see in
 * {@link IMbDeviceMappingCfg}.
 * <p>
 * Options of the {@link #params()} are listed in {@link IMbBridgeCfgConstants} with prefix <b>OPDEF_MB_BRI_</b>XXX.
 *
 * @author hazard157
 */
public interface IMbBridgeCfg
    extends IStridableParameterized, IParameterizedBatchEdit, IGenericChangeEventCapable {

  /**
   * Returns configuration of buses (with nodes list) connected to the bridge.
   *
   * @return {@link IStridablesList}&lt;{@link IMbBusCfg}&gt; - list of buses connected to the bridge
   */
  IStridablesList<IMbBusCfg> listBusCfgs();

  /**
   * Returns configuration of RTU buses (subset of {@link #listBusCfgs()}).
   *
   * @return {@link IStridablesList}&lt;{@link IMbBusCfg}&gt; - list of RTU buses
   */
  IStridablesListEdit<IMbBusRtuCfg> listBusRtuCfgs();

  /**
   * Returns configuration of TCP buses (subset of {@link #listBusCfgs()}).
   *
   * @return {@link IStridablesList}&lt;{@link IMbBusCfg}&gt; - list of TCPbuses
   */
  IStridablesListEdit<IMbBusTcpCfg> listBusTcpCfgs();

  /**
   * Checks if the MODBUS bus can be added to the bridge.
   *
   * @param aBusId String - the bus ID
   * @param aIsRtu boolean type of bus to add: <code>true</code> RTU bus, <code>false</code> - TCP bus
   * @param aParams {@link IOptionSet} - values of {@link IMbBusCfg#params()}
   * @return {@link ValidationResult} - the check result
   * @throws TsNullArgumentRtException any argument = <code>null</code>
   */
  ValidationResult canAddBus( String aBusId, boolean aIsRtu, IOptionSet aParams );

  /**
   * Creates and adds RTU bus to the bridge.
   *
   * @param aBusId String - the bus ID
   * @param aParams {@link IOptionSet} - values of {@link IMbBusCfg#params()}
   * @return {@link IMbBusRtuCfg} - created bus
   * @throws TsNullArgumentRtException any argument = <code>null</code>
   * @throws TsValidationFailedRtException failed {@link #canAddBus(String, boolean, IOptionSet)}
   */
  IMbBusRtuCfg addBusRtu( String aBusId, IOptionSet aParams );

  /**
   * Creates and adds TCP bus to the bridge.
   *
   * @param aBusId String - the bus ID
   * @param aParams {@link IOptionSet} - values of {@link IMbBusCfg#params()}
   * @return {@link IMbBusTcpCfg} - created bus
   * @throws TsNullArgumentRtException any argument = <code>null</code>
   * @throws TsValidationFailedRtException failed {@link #canAddBus(String, boolean, IOptionSet)}
   */
  IMbBusTcpCfg addBusTcp( String aBusId, IOptionSet aParams );

  /**
   * Checks if bus can be removed.
   *
   * @param aBusId String - ID of the bus to remove
   * @return {@link ValidationResult} - the check result
   * @throws TsNullArgumentRtException any argument = <code>null</code>
   */
  ValidationResult canRemoveBus( String aBusId );

  /**
   * Removes the bus from the bridge.
   *
   * @param aBusId String - ID of the bus to remove
   * @throws TsNullArgumentRtException any argument = <code>null</code>
   * @throws TsValidationFailedRtException failed {@link #canRemoveBus(String)}
   */
  void removeBus( String aBusId );

  /**
   * Returns descriptions of the physical devises connected to this bridge.
   * <p>
   * This list presents in bridge configuration to avoid dependency on the external registry which is not available in
   * runtime environment. However, when loading bridge configuration for editing it is good idea to check if devices
   * here math devices with the same IDs in the registry.
   *
   * @return {@link IStridablesList}&lt;{@link MbDeviceCfg}&gt; - list of device configuration
   */
  IStridablesList<MbDeviceCfg> listUsedDevices();

  /**
   * Determines which nodes uses the specified device.
   * <p>
   * If device is not in {@link #listUsedDevices()} then returns an empty list.
   *
   * @param aDeviceId String - ID of the device to search users
   * @return {@link IList}&lt;{@link IMbNodeCfg}&gt; - sorted list of nodes using the device
   * @throws TsNullArgumentRtException any argument = <code>null</code>
   */
  IList<IMbNodeCfg> listDeviceUsers( String aDeviceId );

  /**
   * Adds new or replaces existing device.
   *
   * @param aDevice {@link MbDeviceCfg} - the device to add
   * @throws TsNullArgumentRtException any argument = <code>null</code>
   */
  void putDevice( MbDeviceCfg aDevice );

  /**
   * Removes the device from used device list {@link #listUsedDevices()}.
   * <p>
   * If device is used (that is {@link #listDeviceUsers(String)} returns non-empty list) then throws an exception.
   * <p>
   * Does nothing if there is no such device in list.
   *
   * @param aDeviceId String - ID of the device to remove
   * @throws TsNullArgumentRtException any argument = <code>null</code>
   * @throws TsIllegalStateRtException can not remove used device
   */
  void removeDevice( String aDeviceId );

}
