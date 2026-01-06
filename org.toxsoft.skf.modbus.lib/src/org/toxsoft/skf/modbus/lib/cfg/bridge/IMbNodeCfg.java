package org.toxsoft.skf.modbus.lib.cfg.bridge;

import org.toxsoft.core.tslib.bricks.strid.*;
import org.toxsoft.core.tslib.utils.errors.*;
import org.toxsoft.skf.modbus.lib.cfg.bridge.to_devel.*;
import org.toxsoft.skf.modbus.lib.cfg.device.*;

/**
 * Describes the node on MODBUS bus representing single physical MODBUS device.
 * <p>
 * Options of the {@link #params()} are listed in {@link IMbBridgeCfgConstants} with prefix <b>OPDEF_MB_NOD_</b>XXX.
 * <p>
 * {@link #params()} contains the node address on the bus. Depending on the bus type {@link IMbBusCfg#isRtu()}, option
 * constants starts with wither <code>OPDEF_MB_NOD<b><i>_TCP_</i></b>XXX</code> or
 * <code>OPDEF_MB_NOD<b><i>_RTU_</i></b>XXX</code>.
 *
 * @author hazard157
 */
public interface IMbNodeCfg
    extends IStridableParameterized {

  /**
   * Returns the ID of the physical device from the registry {@link IMbBridgeCfg#listUsedDevices()}.
   * <p>
   * The ID determines the concrete manufacturer and model of the device, hence determines configuration of the MODBUS
   * registers found in {@link MbDeviceCfg#registerCfs()}.
   *
   * @return String - the device ID
   */
  String deviceId();

  /**
   * Determines the MODBUS bus kind.
   * <p>
   * Returns the same value as the owner bus {@link IMbBusCfg#isRtu()}.
   *
   * @return boolean - bus kind <br>
   *         <b>true</b> - this is serial (RS-485) Modbus/RTU bus;<br>
   *         <b>false</b> - this is Ethernet Modbus/TCP bus.
   */
  boolean isRtu();

  /**
   * Returns value of option {@link IMbBridgeCfgConstants#OPDEF_MB_NOD_TCP_IP_ADDRESS}.
   *
   * @return String - MODBUS Node IP address (either numerical or domain name)
   * @throws TsUnsupportedFeatureRtException node is on RTU bus, {@link #isRtu()} = <code>true</code>
   */
  String getTcpIpAddress();

  /**
   * Returns value of option {@link IMbBridgeCfgConstants#OPDEF_MB_NOD_TCP_PORT_NO}.
   *
   * @return int - TCP port number in range 1..65535, usually 502
   * @throws TsUnsupportedFeatureRtException node is on RTU bus, {@link #isRtu()} = <code>true</code>
   */
  int getTcpPortNo();

  /**
   * Returns value of option {@link IMbBridgeCfgConstants#OPDEF_MB_NOD_RTU_DEV_ADDR}.
   *
   * @return int - node (slave device) number on serial bus, in range 1..255
   * @throws TsUnsupportedFeatureRtException node is on TCP bus, {@link #isRtu()} = <code>false</code>
   */
  int getRtuDeviceAddress();

  // TODO read / write request sending options: periodical? by request?
  // TODO =may be different requests for different register(s) ?
  // TODO maybe define register pool to be asked at once?

  IMbDeviceMappingCfg mappingCfg();

}
