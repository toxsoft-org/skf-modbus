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
public interface IMbNodeRtuCfg
    extends IMbNodeCfg {

  @Override
  IMbBusRtuCfg parent();

  /**
   * Returns value of option {@link IMbBridgeCfgConstants#OPDEF_MB_NOD_RTU_DEV_ADDR}.
   *
   * @return int - node (slave device) number on serial bus, in range 1..255
   * @throws TsUnsupportedFeatureRtException node is on TCP bus, {@link #isRtu()} = <code>false</code>
   */
  int getRtuDeviceAddress();

  /**
   * Checks if device address can be set.
   * <p>
   * Checks:
   * <ul>
   * <li>address is in range {@link IModbusSpecificationConstants#MB_DEVICE_ADDRESS_RANGE};</li>
   * <li>no other node on the bus {@link #parent()} has the same address.</li>
   * </ul>
   *
   * @param aDevAddress int - device address on the bus
   * @return {@link ValidationResult} - the check result
   */
  ValidationResult canSetRtuDeviceAddress( int aDevAddress );

  /**
   * Sets the device address.
   *
   * @param aDevAddress int - device address on the bus
   * @throws TsValidationFailedRtException failed {@link #canSetRtuDeviceAddress(int)}
   */
  void setDeviceAddress( int aDevAddress );

}
