package org.toxsoft.skf.modbus.lib.cfg.bridge_old;

import org.toxsoft.core.tslib.bricks.strid.*;
import org.toxsoft.core.tslib.bricks.strid.coll.*;
import org.toxsoft.skf.modbus.lib.cfg.bridge.to_devel.*;
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
    extends IStridableParameterized {

  /**
   * Returns configuration of buses (with nodes list) connected to the bridge.
   *
   * @return {@link IStridablesList}&lt;{@link IMbBusCfg}&gt; - list of buses connected to the bridge
   */
  IStridablesList<IMbBusCfg> listBusCfgs();

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

}
