package org.toxsoft.skf.modbus.lib.cfg.bridge.impl;

import static org.toxsoft.skf.modbus.lib.l10n.ISkfModbusLibSharedResources.*;
import static org.toxsoft.skf.modbus.lib.mbspec.IModbusSpecificationConstants.*;

import org.toxsoft.core.tslib.av.opset.*;
import org.toxsoft.core.tslib.bricks.strid.coll.*;
import org.toxsoft.core.tslib.bricks.strid.impl.*;
import org.toxsoft.core.tslib.bricks.validator.*;
import org.toxsoft.core.tslib.bricks.validator.impl.*;
import org.toxsoft.core.tslib.coll.primtypes.*;
import org.toxsoft.core.tslib.utils.errors.*;
import org.toxsoft.skf.modbus.lib.cfg.bridge.*;

/**
 * {@link IMbBusTcpCfg} implementation.
 *
 * @author hazard157
 */
class MbBusTcpCfg
    extends MbBusCfg
    implements IMbBusTcpCfg {

  public MbBusTcpCfg( MbBridgeCfg aParent, String aBusId, IOptionSet aParams, IStringList aFixeOpIds ) {
    super( aParent, aBusId, false, aParams, aFixeOpIds );
  }

  // ------------------------------------------------------------------------------------
  // IMbBusTcpCfg
  //

  @SuppressWarnings( "unchecked" )
  @Override
  public IStridablesList<IMbNodeTcpCfg> listNodeCfgs() {
    // safely cast - #addNode() guarantees that list contains only IMbNodeTcpCfg,
    return (IStridablesList<IMbNodeTcpCfg>)super.listNodeCfgs();
  }

  @Override
  public ValidationResult canAddNode( String aNodeId, String aDeviceId, String aIpAddr, int aPortNo,
      IOptionSet aParams ) {
    TsNullArgumentRtException.checkNulls( aNodeId, aDeviceId, aParams );
    ValidationResult vr = canAddNodeGeneric( aNodeId, aParams );
    if( vr.isError() ) {
      return vr;
    }
    if( !StridUtils.isValidIdPath( aNodeId ) ) {
      return ValidationResult.error( FMT_ERR_INV_MBNODE_ID, aDeviceId );
    }
    if( aIpAddr.isBlank() ) {
      return ValidationResult.error( MSG_ERR_BLANK_IP_ADDRESS );
    }
    if( !MB_TCP_PORT_RANGE.isInRange( aPortNo ) ) {
      return ValidationResult.error( MSG_ERR_INV_TCP_PORT_NO, Integer.valueOf( aPortNo ),
          MB_TCP_PORT_RANGE.toString() );
    }
    for( IMbNodeTcpCfg n : listNodeCfgs() ) {
      if( n.getTcpIpAddress().equalsIgnoreCase( aIpAddr ) ) {
        if( n.getTcpPortNo() == aPortNo ) {
          return ValidationResult.error( FMT_ERR_TCP_ADDR_EXISTS, n.id(), aIpAddr, Integer.valueOf( aPortNo ) );
        }
        vr = ValidationResult.firstNonOk( vr, ValidationResult.warn( FMT_WARN_MBNODE_DUR_IP_ADDR, n.id(), aIpAddr ) );
      }
    }
    if( !parent().parent().listUsedDevices().hasKey( aDeviceId ) ) {
      return ValidationResult.error( FMT_ERR_NO_SUCH_DEVICE_IN_BRIDGE, aDeviceId );
    }
    return vr;
  }

  @Override
  public IMbNodeTcpCfg addNode( String aNodeId, String aDeviceId, String aIpAddr, int aPortNo, IOptionSet aParams ) {
    TsValidationFailedRtException.checkError( canAddNode( aNodeId, aDeviceId, aIpAddr, aPortNo, aParams ) );
    MbNodeTcpCfg nodeCfg = new MbNodeTcpCfg( this, aNodeId, aDeviceId, aParams );
    nodeCfg.setDeviceAddress( aIpAddr, aPortNo );
    addNodeToChildren( nodeCfg );
    return nodeCfg;
  }

}
