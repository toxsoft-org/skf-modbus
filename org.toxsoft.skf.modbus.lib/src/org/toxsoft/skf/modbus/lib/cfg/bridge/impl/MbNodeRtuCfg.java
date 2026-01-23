package org.toxsoft.skf.modbus.lib.cfg.bridge.impl;

import static org.toxsoft.skf.modbus.lib.l10n.ISkfModbusLibSharedResources.*;
import static org.toxsoft.skf.modbus.lib.mbspec.IModbusSpecificationConstants.*;

import org.toxsoft.core.tslib.av.opset.*;
import org.toxsoft.core.tslib.bricks.validator.*;
import org.toxsoft.core.tslib.bricks.validator.impl.*;
import org.toxsoft.core.tslib.coll.primtypes.*;
import org.toxsoft.core.tslib.coll.primtypes.impl.*;
import org.toxsoft.skf.modbus.lib.cfg.bridge.*;

/**
 * {@link IMbNodeRtuCfg} implementation.
 *
 * @author hazard157
 */
class MbNodeRtuCfg
    extends MbNodeCfg<MbBusRtuCfg>
    implements IMbNodeRtuCfg {

  private static final String OPID_DEV_ADDRESS = "RtDevAddress"; //$NON-NLS-1$

  private static final IStringList FIXED_OP_IDS = new StringArrayList( //
      OPID_DEV_ADDRESS //
  );

  public MbNodeRtuCfg( MbBusRtuCfg aParent, String aNodeId, String aDeviceId, IOptionSet aParams ) {
    super( aParent, aNodeId, aDeviceId, aParams, FIXED_OP_IDS );
  }

  // ------------------------------------------------------------------------------------
  // IMbNodeRtuCfg
  //

  @Override
  public int getRtuDeviceAddress() {
    return params().getInt( OPID_DEV_ADDRESS, MB_DEVICE_ADDRESS_RANGE.minValue() );
  }

  // ------------------------------------------------------------------------------------
  // IMbNodeRtuCfg
  //

  @Override
  public ValidationResult canSetRtuDeviceAddress( int aDevAddress ) {
    if( !MB_DEVICE_ADDRESS_RANGE.isInRange( aDevAddress ) ) {
      return ValidationResult.error( FMT_ERR_INV_RTU_ADDR, Integer.valueOf( aDevAddress ),
          MB_DEVICE_ADDRESS_RANGE.toString() );
    }
    for( IMbNodeRtuCfg n : parent().listNodeCfgs() ) {
      if( n.getRtuDeviceAddress() == aDevAddress ) {
        return ValidationResult.error( FMT_ERR_RTU_ADDR_EXISTS, n.id(), Integer.valueOf( aDevAddress ) );
      }
    }
    return ValidationResult.SUCCESS;
  }

  @Override
  public void setDeviceAddress( int aDevAddress ) {
    TsValidationFailedRtException.checkError( canSetRtuDeviceAddress( aDevAddress ) );
    if( getRtuDeviceAddress() != aDevAddress ) {
      params().setInt( OPID_DEV_ADDRESS, aDevAddress );
      genericChangeEventer().fireChangeEvent();
    }
  }

}
