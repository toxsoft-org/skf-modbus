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
 * {@link IMbBusRtuCfg} implementation.
 *
 * @author hazard157
 */
class MbBusRtuCfg
    extends MbBusCfg
    implements IMbBusRtuCfg {

  public MbBusRtuCfg( MbBridgeCfg aParent, String aBusId, IOptionSet aParams, IStringList aFixeOpIds ) {
    super( aParent, aBusId, true, aParams, aFixeOpIds );
  }

  // ------------------------------------------------------------------------------------
  // IMbBusRtuCfg
  //

  @SuppressWarnings( "unchecked" )
  @Override
  public IStridablesList<IMbNodeRtuCfg> listNodeCfgs() {
    // safely cast - #addNode() guarantees that list contains only IMbNodeRtuCfg,
    return (IStridablesList<IMbNodeRtuCfg>)super.listNodeCfgs();
  }

  @Override
  public ValidationResult canAddNode( String aNodeId, String aDeviceId, int aRtuDevAddr, IOptionSet aParams ) {
    TsNullArgumentRtException.checkNulls( aNodeId, aDeviceId, aParams );
    ValidationResult vr = canAddNodeGeneric( aNodeId, aParams );
    if( vr.isError() ) {
      return vr;
    }
    if( !StridUtils.isValidIdPath( aNodeId ) ) {
      return ValidationResult.error( FMT_ERR_INV_MBNODE_ID, aDeviceId );
    }
    if( !MB_DEVICE_ADDRESS_RANGE.isInRange( aRtuDevAddr ) ) {
      return ValidationResult.error( FMT_ERR_INV_RTU_ADDR, Integer.valueOf( aRtuDevAddr ),
          MB_DEVICE_ADDRESS_RANGE.toString() );
    }
    for( IMbNodeRtuCfg n : listNodeCfgs() ) {
      if( n.getRtuDeviceAddress() == aRtuDevAddr ) {
        return ValidationResult.error( FMT_ERR_RTU_ADDR_EXISTS, n.id(), Integer.valueOf( aRtuDevAddr ) );
      }
    }
    if( !parent().parent().listUsedDevices().hasKey( aDeviceId ) ) {
      return ValidationResult.error( FMT_ERR_NO_SUCH_DEVICE_IN_BRIDGE, aDeviceId );
    }
    return vr;
  }

  @Override
  public IMbNodeRtuCfg addNode( String aNodeId, String aDeviceId, int aRtuDevAddr, IOptionSet aParams ) {
    TsValidationFailedRtException.checkError( canAddNode( aNodeId, aDeviceId, aRtuDevAddr, aParams ) );
    MbNodeRtuCfg nodeCfg = new MbNodeRtuCfg( this, aNodeId, aDeviceId, aParams );
    nodeCfg.setDeviceAddress( aRtuDevAddr );
    addNodeToChildren( nodeCfg );
    return nodeCfg;
  }

}
