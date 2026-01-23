package org.toxsoft.skf.modbus.lib.cfg.bridge.impl;

import static org.toxsoft.core.tslib.av.metainfo.IAvMetaConstants.*;
import static org.toxsoft.skf.modbus.lib.l10n.ISkfModbusLibSharedResources.*;

import org.toxsoft.core.tslib.av.opset.*;
import org.toxsoft.core.tslib.bricks.strid.coll.*;
import org.toxsoft.core.tslib.bricks.strid.coll.impl.*;
import org.toxsoft.core.tslib.bricks.validator.*;
import org.toxsoft.core.tslib.bricks.validator.impl.*;
import org.toxsoft.core.tslib.bricks.validator.std.*;
import org.toxsoft.core.tslib.coll.*;
import org.toxsoft.core.tslib.coll.impl.*;
import org.toxsoft.core.tslib.coll.primtypes.*;
import org.toxsoft.core.tslib.utils.errors.*;
import org.toxsoft.skf.modbus.lib.cfg.bridge.*;
import org.toxsoft.skf.modbus.lib.cfg.device.*;

/**
 * {@link IMbBridgeCfg} implementation.
 *
 * @author hazard157
 */
public class MbBridgeCfg
    extends MbXxxCfg<MbBridgeCfg>
    implements IMbBridgeCfg {

  private final IStridablesListEdit<IMbBusCfg>   busCfgs     = new StridablesList<>();
  private final IStridablesListEdit<MbDeviceCfg> usedDevices = new StridablesList<>();

  /**
   * Constructor.
   *
   * @param aId String - the bus ID (IDpath)
   * @param aParams {@link IOptionSet} - {@link #params()} initial values
   * @throws TsNullArgumentRtException any argument = <code>null</code>
   * @throws TsIllegalArgumentRtException ID is not an IDpath
   */
  public MbBridgeCfg( String aId, IOptionSet aParams ) {
    super( null, aId, aParams, IStringList.EMPTY );
  }

  // ------------------------------------------------------------------------------------
  // IMbBridgeCfg
  //

  @Override
  public IStridablesList<IMbBusCfg> listBusCfgs() {
    return busCfgs;
  }

  @Override
  public IStridablesListEdit<IMbBusRtuCfg> listBusRtuCfgs() {
    IStridablesListEdit<IMbBusRtuCfg> llCfgs = new StridablesList<>();
    for( IMbBusCfg c : busCfgs ) {
      if( c.isRtu() ) {
        llCfgs.add( (IMbBusRtuCfg)c );
      }
    }
    return llCfgs;
  }

  @Override
  public IStridablesListEdit<IMbBusTcpCfg> listBusTcpCfgs() {
    IStridablesListEdit<IMbBusTcpCfg> llCfgs = new StridablesList<>();
    for( IMbBusCfg c : busCfgs ) {
      if( !c.isRtu() ) {
        llCfgs.add( (IMbBusTcpCfg)c );
      }
    }
    return llCfgs;
  }

  @Override
  public ValidationResult canAddBus( String aBusId, boolean aIsRtu, IOptionSet aParams ) {
    TsNullArgumentRtException.checkNulls( aBusId, aParams );
    if( busCfgs.hasKey( aBusId ) ) {
      return ValidationResult.error( FMT_ERR_MBBUS_ALREADY_EXISTS, aBusId );
    }
    String name = aParams.getStr( TSID_NAME, DEFAULT_NAME );
    ValidationResult vr = NameStringValidator.VALIDATOR.validate( name );
    return vr;
  }

  @Override
  public IMbBusRtuCfg addBusRtu( String aBusId, IOptionSet aParams ) {
    TsValidationFailedRtException.checkError( canAddBus( aBusId, true, aParams ) );
    IMbBusRtuCfg bus = new MbBusRtuCfg( this, aBusId, aParams, IStringList.EMPTY );
    busCfgs.add( bus );
    return bus;
  }

  @Override
  public IMbBusTcpCfg addBusTcp( String aBusId, IOptionSet aParams ) {
    TsValidationFailedRtException.checkError( canAddBus( aBusId, false, aParams ) );
    IMbBusTcpCfg bus = new MbBusTcpCfg( this, aBusId, aParams, IStringList.EMPTY );
    busCfgs.add( bus );
    return bus;
  }

  @Override
  public ValidationResult canRemoveBus( String aBusId ) {
    TsNullArgumentRtException.checkNull( aBusId );
    IMbBusCfg bus = busCfgs.findByKey( aBusId );
    if( bus != null && !bus.listNodeCfgs().isEmpty() ) {
      return ValidationResult.warn( FMT_WARN_BUS_HAS_NODES, bus.id() );
    }
    return ValidationResult.SUCCESS;
  }

  @Override
  public void removeBus( String aBusId ) {
    TsValidationFailedRtException.checkError( canRemoveBus( aBusId ) );
    if( usedDevices.removeByKey( aBusId ) != null ) {
      genericChangeEventer().fireChangeEvent();
    }
  }

  @Override
  public IStridablesList<MbDeviceCfg> listUsedDevices() {
    return usedDevices;
  }

  @Override
  public IList<IMbNodeCfg> listDeviceUsers( String aDeviceId ) {
    TsNullArgumentRtException.checkNull( aDeviceId );
    IListBasicEdit<IMbNodeCfg> llCfgs = new SortedElemLinkedBundleList<>();
    for( IMbBusCfg bus : listBusCfgs() ) {
      for( IMbNodeCfg node : bus.listNodeCfgs() ) {
        if( node.deviceId().equals( aDeviceId ) ) {
          llCfgs.add( node );
        }
      }
    }
    return llCfgs;
  }

  @Override
  public void putDevice( MbDeviceCfg aDevice ) {
    TsNullArgumentRtException.checkNull( aDevice );
    usedDevices.add( aDevice );
    genericChangeEventer().fireChangeEvent();
  }

  @Override
  public void removeDevice( String aDeviceId ) {
    IList<IMbNodeCfg> llNodes = listDeviceUsers( aDeviceId );
    TsIllegalStateRtException.checkFalse( llNodes.isEmpty() );
    if( usedDevices.removeByKey( aDeviceId ) != null ) {
      genericChangeEventer().fireChangeEvent();
    }
  }

}
