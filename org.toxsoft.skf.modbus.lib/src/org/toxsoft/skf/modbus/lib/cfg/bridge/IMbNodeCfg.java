package org.toxsoft.skf.modbus.lib.cfg.bridge;

import org.toxsoft.core.tslib.av.utils.*;
import org.toxsoft.core.tslib.bricks.events.change.*;
import org.toxsoft.core.tslib.bricks.strid.*;
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
    extends IStridableParameterized, IParameterizedBatchEdit, IGenericChangeEventCapable, Comparable<IMbNodeCfg> {

  /**
   * Returns the parent (owner) bus.
   *
   * @return {@link IMbBusCfg} - the parent
   */
  IMbBusCfg parent();

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

  // TODO read / write request sending options: periodical? by request?
  // TODO =may be different requests for different register(s) ?
  // TODO maybe define register pool to be asked at once?

  // IMbDeviceMappingCfg mappingCfg();

}
