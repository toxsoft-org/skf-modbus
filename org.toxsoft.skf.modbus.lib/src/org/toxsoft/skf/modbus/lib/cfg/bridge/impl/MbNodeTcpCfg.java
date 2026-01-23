package org.toxsoft.skf.modbus.lib.cfg.bridge.impl;

import static org.toxsoft.skf.modbus.lib.l10n.ISkfModbusLibSharedResources.*;
import static org.toxsoft.skf.modbus.lib.mbspec.IModbusSpecificationConstants.*;

import org.toxsoft.core.tslib.av.opset.*;
import org.toxsoft.core.tslib.bricks.validator.*;
import org.toxsoft.core.tslib.bricks.validator.impl.*;
import org.toxsoft.core.tslib.coll.primtypes.*;
import org.toxsoft.core.tslib.coll.primtypes.impl.*;
import org.toxsoft.core.tslib.utils.errors.*;
import org.toxsoft.skf.modbus.lib.cfg.bridge.*;
import org.toxsoft.skf.modbus.lib.mbspec.*;

/**
 * {@link IMbNodeTcpCfg} implementation.
 *
 * @author hazard157
 */
class MbNodeTcpCfg
    extends MbNodeCfg<MbBusTcpCfg>
    implements IMbNodeTcpCfg {

  private static final String OPID_IP_ADDRESS    = "IpAddress"; //$NON-NLS-1$
  private static final String OPID_PORT_NO       = "PortNo";    //$NON-NLS-1$
  private static final String DEFAULT_IP_ADDTESS = "127.0.0.1"; //$NON-NLS-1$

  private static final IStringList FIXED_OP_IDS = new StringArrayList( //
      OPID_IP_ADDRESS, //
      OPID_PORT_NO //
  );

  public MbNodeTcpCfg( MbBusTcpCfg aParent, String aNodeId, String aDeviceId, IOptionSet aParams ) {
    super( aParent, aNodeId, aDeviceId, aParams, FIXED_OP_IDS );
  }

  // ------------------------------------------------------------------------------------
  // IMbNodeTcpCfg
  //

  @Override
  public String getTcpIpAddress() {
    return params().getStr( OPID_IP_ADDRESS, DEFAULT_IP_ADDTESS );
  }

  @Override
  public int getTcpPortNo() {
    return params().getInt( OPID_PORT_NO, MB_DEFAULT_PORT_NO );
  }

  // ------------------------------------------------------------------------------------
  // API
  //

  public void setTcpAddress( String aIpAddress, int aPortNo ) {
    TsNullArgumentRtException.checkNull( aIpAddress );
    IModbusSpecificationConstants.MB_TCP_PORT_RANGE.checkInRange( aPortNo );
    if( getTcpPortNo() != aPortNo && !aIpAddress.equals( getTcpIpAddress() ) ) {
      params().setStr( OPID_IP_ADDRESS, aIpAddress );
      params().setInt( OPID_PORT_NO, aPortNo );
      genericChangeEventer().fireChangeEvent();
    }
  }

  @Override
  public ValidationResult canSetDeviceAddress( String aIpAddress, int aPortNo ) {
    TsNullArgumentRtException.checkNull( aIpAddress );
    if( aIpAddress.isBlank() ) {
      return ValidationResult.error( MSG_ERR_BLANK_IP_ADDRESS );
    }
    if( !MB_TCP_PORT_RANGE.isInRange( aPortNo ) ) {
      return ValidationResult.error( MSG_ERR_INV_TCP_PORT_NO, Integer.valueOf( aPortNo ),
          MB_TCP_PORT_RANGE.toString() );
    }
    ValidationResult vr = ValidationResult.SUCCESS;
    for( IMbNodeTcpCfg n : parent().listNodeCfgs() ) {
      if( n.getTcpIpAddress().equalsIgnoreCase( aIpAddress ) ) {
        if( n.getTcpPortNo() == aPortNo ) {
          return ValidationResult.error( FMT_ERR_TCP_ADDR_EXISTS, n.id(), aIpAddress, Integer.valueOf( aPortNo ) );
        }
        vr = ValidationResult.firstNonOk( vr,
            ValidationResult.warn( FMT_WARN_MBNODE_DUR_IP_ADDR, n.id(), aIpAddress ) );
      }
    }
    return vr;
  }

  @Override
  public void setDeviceAddress( String aIpAddress, int aPortNo ) {
    TsValidationFailedRtException.checkError( canSetDeviceAddress( aIpAddress, aPortNo ) );
    if( getTcpPortNo() != aPortNo && !getTcpIpAddress().equals( aIpAddress ) ) {
      params().setStr( OPID_IP_ADDRESS, aIpAddress );
      params().setInt( OPID_PORT_NO, aPortNo );
      genericChangeEventer().fireChangeEvent();
    }
  }

}
