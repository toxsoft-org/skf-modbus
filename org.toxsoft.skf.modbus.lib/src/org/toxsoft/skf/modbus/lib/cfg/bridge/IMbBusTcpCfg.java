package org.toxsoft.skf.modbus.lib.cfg.bridge;

import org.toxsoft.core.tslib.av.opset.*;
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
public interface IMbBusTcpCfg
    extends IMbBusCfg {

  /**
   * Returns all nodes on bus, that is all connected physical devices with MODBUS-USkat data mappings.
   *
   * @return {@link IStridablesList}&lt;{@link IMbNodeCfg}&gt; - all nodes on this bus
   */
  @Override
  IStridablesList<IMbNodeTcpCfg> listNodeCfgs();

  /**
   * Checks if node can be created and added to the bus.
   *
   * @param aNodeId String - the node ID
   * @param aDeviceId String - the ID of the device existing in {@link IMbBridgeCfg#listUsedDevices()}
   * @param aIpAddr String - an IP-address of the node
   * @param aPortNo int - the TCP port number
   * @param aParams {@link IOptionSet} - parameters
   * @return {@link ValidationResult} - the check result
   * @throws TsNullArgumentRtException any argument = <code>null</code>
   */
  ValidationResult canAddNode( String aNodeId, String aDeviceId, String aIpAddr, int aPortNo, IOptionSet aParams );

  /**
   * Creates node and added to the bus.
   *
   * @param aNodeId String - the node ID
   * @param aDeviceId String - the ID of the device existing in {@link IMbBridgeCfg#listUsedDevices()}
   * @param aIpAddr String - an IP-address of the node
   * @param aPortNo int - the TCP port number
   * @param aParams {@link IOptionSet} - parameters
   * @return {@link IMbNodeTcpCfg} - created node
   * @throws TsNullArgumentRtException any argument = <code>null</code>
   * @throws TsValidationFailedRtException failed {@link #canAddNode(String, String, String, int, IOptionSet)}
   */
  IMbNodeTcpCfg addNode( String aNodeId, String aDeviceId, String aIpAddr, int aPortNo, IOptionSet aParams );

}
