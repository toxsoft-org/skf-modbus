package org.toxsoft.skf.modbus.lib.cfg.bridge_old;

import org.toxsoft.core.tslib.bricks.strid.*;
import org.toxsoft.core.tslib.bricks.strid.coll.*;

/**
 * Describes single MODBUS bus with all nodes 9devices with mappings).
 * <p>
 * Options of the {@link #params()} are listed in {@link IMbBridgeCfgConstants} with prefix <b>OPDEF_MB_BUS_</b>XXX.
 *
 * @author hazard157
 */
public interface IMbBusCfg
    extends IStridableParameterized {

  /**
   * Determines the MODBUS bus kind.
   * <p>
   * Actually returns value of the option {@link IMbBridgeCfgConstants#OPDEF_MB_BUS_IS_RTU}.
   *
   * @return boolean - bus kind <br>
   *         <b>true</b> - this is serial (RS-485) Modbus/RTU bus;<br>
   *         <b>false</b> - this is Ethernet Modbus/TCP bus.
   */
  boolean isRtu();

  /**
   * Returns all nodes on bus, that is all connected physical devices with MODBUS-USkat data mappings.
   *
   * @return {@link IStridablesList}&lt;{@link IMbNodeCfg}&gt; - all nodes on this bus
   */
  IStridablesList<IMbNodeCfg> listNodeCfgs();

}
