package org.toxsoft.skf.modbus.lib.cfg.bridge;

import org.toxsoft.core.tslib.av.utils.*;
import org.toxsoft.core.tslib.bricks.events.change.*;
import org.toxsoft.core.tslib.bricks.strid.*;
import org.toxsoft.core.tslib.bricks.strid.coll.*;
import org.toxsoft.core.tslib.bricks.validator.*;
import org.toxsoft.core.tslib.bricks.validator.impl.*;
import org.toxsoft.core.tslib.utils.errors.*;

/**
 * Describes single MODBUS bus with all nodes 9devices with mappings).
 * <p>
 * Options of the {@link #params()} are listed in {@link IMbBridgeCfgConstants} with prefix <b>OPDEF_MB_BUS_</b>XXX.
 *
 * @author hazard157
 */
public interface IMbBusCfg
    extends IStridableParameterized, IParameterizedBatchEdit, IGenericChangeEventCapable {

  /**
   * Returns the parent (owner) bridge.
   *
   * @return {@link IMbBridgeCfg} - the parent
   */
  IMbBridgeCfg parent();

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
  IStridablesList<? extends IMbNodeCfg> listNodeCfgs();

  /**
   * Checks if node can be removed.
   *
   * @param aNodeId String - ID of node to remove
   * @return {@link ValidationResult} - the check result
   * @throws TsNullArgumentRtException any argument = <code>null</code>
   */
  ValidationResult canRemoveNode( String aNodeId );

  /**
   * Removes the node.
   *
   * @param aNodeId String - ID of node to remove
   * @throws TsNullArgumentRtException any argument = <code>null</code>
   * @throws TsValidationFailedRtException failed {@link #canRemoveNode(String)}
   */
  void removeNode( String aNodeId );

}
