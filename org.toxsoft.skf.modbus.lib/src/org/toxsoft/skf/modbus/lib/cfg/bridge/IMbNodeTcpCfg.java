package org.toxsoft.skf.modbus.lib.cfg.bridge;

import org.toxsoft.core.tslib.bricks.validator.*;
import org.toxsoft.core.tslib.bricks.validator.impl.*;
import org.toxsoft.core.tslib.utils.errors.*;
import org.toxsoft.skf.modbus.lib.mbspec.*;

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
public interface IMbNodeTcpCfg
    extends IMbNodeCfg {

  /**
   * Returns value of option {@link IMbBridgeCfgConstants#OPDEF_MB_NOD_TCP_IP_ADDRESS}.
   *
   * @return String - MODBUS Node IP address (either numerical or domain name)
   * @throws TsUnsupportedFeatureRtException node is on RTU bus, {@link #isRtu()} = <code>true</code>
   */
  String getTcpIpAddress();

  /**
   * Returns value of option {@link IMbBridgeCfgConstants#OPDEF_MB_NOD_TCP_PORT_NO}.
   * <p>
   * ALlowed port range is {@link IModbusSpecificationConstants#MB_TCP_PORT_RANGE}, default value is
   * {@link IModbusSpecificationConstants#MB_DEFAULT_PORT_NO}.
   *
   * @return int - TCP port number
   * @throws TsUnsupportedFeatureRtException node is on RTU bus, {@link #isRtu()} = <code>true</code>
   */
  int getTcpPortNo();

  /**
   * Checks if device address can be changed.
   *
   * @param aIpAddress String - an IP-address of the node
   * @param aPortNo int - the TCP port number
   * @return {@link ValidationResult} - the check result
   * @throws TsNullArgumentRtException any argument = <code>null</code>
   */
  ValidationResult canSetDeviceAddress( String aIpAddress, int aPortNo );

  /**
   * Sets the device address.
   *
   * @param aIpAddress String - an IP-address of the node
   * @param aPortNo int - the TCP port number
   * @throws TsNullArgumentRtException any argument = <code>null</code>
   * @throws TsValidationFailedRtException failed {@link #canSetDeviceAddress(String, int)}
   */
  void setDeviceAddress( String aIpAddress, int aPortNo );

}
