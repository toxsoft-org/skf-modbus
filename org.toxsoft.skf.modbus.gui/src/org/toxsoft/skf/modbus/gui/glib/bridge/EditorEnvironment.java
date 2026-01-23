package org.toxsoft.skf.modbus.gui.glib.bridge;

import static org.toxsoft.core.tslib.av.metainfo.IAvMetaConstants.*;
import static org.toxsoft.skf.modbus.gui.l10n.ISkfModbusGuiSharedResources.*;

import org.toxsoft.core.tsgui.mws.services.currentity.*;
import org.toxsoft.core.tslib.av.opset.*;
import org.toxsoft.core.tslib.bricks.events.change.*;
import org.toxsoft.core.tslib.bricks.strid.coll.*;
import org.toxsoft.core.tslib.bricks.strid.coll.impl.*;
import org.toxsoft.core.tslib.bricks.strid.coll.notifier.*;
import org.toxsoft.core.tslib.bricks.strid.impl.*;
import org.toxsoft.core.tslib.bricks.validator.*;
import org.toxsoft.core.tslib.bricks.validator.impl.*;
import org.toxsoft.core.tslib.bricks.validator.std.*;
import org.toxsoft.core.tslib.coll.helpers.*;
import org.toxsoft.core.tslib.utils.errors.*;
import org.toxsoft.skf.modbus.lib.cfg.bridge.*;
import org.toxsoft.skf.modbus.lib.cfg.bridge.impl.*;

class EditorEnvironment
    implements IEditorEnvironment {

  private final INotifierStridablesListEdit<IMbBridgeCfg> bridgeCfgs      =
      new NotifierStridablesListEditWrapper<>( new StridablesList<>() );
  private final IListReorderer<IMbBridgeCfg>              bridgeReorderer = new ListReorderer<>( bridgeCfgs );
  private final ICurrentEntityService<MbTreePath>         cesBridge       = new CurrentEntityService<>();

  private final GenericChangeEventer genericChangeEventer;

  public EditorEnvironment() {
    genericChangeEventer = new GenericChangeEventer( this );
    bridgeCfgs.addCollectionChangeListener( genericChangeEventer );
  }

  // ------------------------------------------------------------------------------------
  // IEditorEnvironment
  //

  @Override
  public IStridablesList<IMbBridgeCfg> bridgeCfgs() {
    return bridgeCfgs();
  }

  @Override
  public IListReorderer<IMbBridgeCfg> bridgeCfgsReorderer() {
    return bridgeReorderer;
  }

  @Override
  public ValidationResult canAddBridge( String aBridgeId, IOptionSet aParams ) {
    TsNullArgumentRtException.checkNulls( aBridgeId, aParams );
    if( !StridUtils.isValidIdPath( aBridgeId ) ) {
      return ValidationResult.error( MSG_ERR_INV_BRIDGE_ID );
    }
    if( bridgeCfgs.hasKey( aBridgeId ) ) {
      return ValidationResult.error( FMT_ERR_DUP_BRIDGE_ID, aBridgeId );
    }
    String name = aParams.getStr( TSID_NAME, DEFAULT_NAME );
    return NameStringValidator.VALIDATOR.validate( name );
  }

  @Override
  public IMbBridgeCfg addBridge( String aBridgeId, IOptionSet aParams ) {
    TsValidationFailedRtException.checkError( canAddBridge( aBridgeId, aParams ) );
    MbBridgeCfg bridge = new MbBridgeCfg( aBridgeId, aParams );
    bridge.genericChangeEventer().addListener( genericChangeEventer );
    bridgeCfgs.add( bridge );
    return bridge;
  }

  @Override
  public ValidationResult canRemoveBridge( String aBridgeId ) {
    TsNullArgumentRtException.checkNull( aBridgeId );
    IMbBridgeCfg bridge = bridgeCfgs.findByKey( aBridgeId );
    if( bridge != null ) {
      if( !bridge.listBusCfgs().isEmpty() ) {
        return ValidationResult.warn( FMT_WARN_BRIDGE_HAS_BUSES, aBridgeId );
      }
    }
    return ValidationResult.SUCCESS;
  }

  @Override
  public void removeBridge( String aBridgeId ) {
    TsValidationFailedRtException.checkError( canRemoveBridge( aBridgeId ) );
    IMbBridgeCfg removed = bridgeCfgs.removeByKey( aBridgeId );
    if( removed != null ) {
      removed.genericChangeEventer().removeListener( genericChangeEventer );
    }
  }

  @SuppressWarnings( "unchecked" )
  @Override
  public <T> T find( MbTreePath aPath ) {
    switch( aPath.kind() ) {
      case ROOT: {
        return null;
      }
      case BRIDGE: {
        return (T)bridgeCfgs.findByKey( aPath.bridgeId() );
      }
      case BUS: {
        IMbBridgeCfg bridge = bridgeCfgs.findByKey( aPath.bridgeId() );
        if( bridge != null ) {
          return (T)bridge.listBusCfgs().findByKey( aPath.busId() );
        }
        return null;
      }
      case NODE: {
        IMbBridgeCfg bridge = bridgeCfgs.findByKey( aPath.bridgeId() );
        if( bridge != null ) {
          IMbBusCfg bus = bridge.listBusCfgs().findByKey( aPath.busId() );
          if( bus != null ) {
            return (T)bus.listNodeCfgs().findByKey( aPath.nodeId() );
          }
        }
        return null;
      }
      default:
        throw new TsNotAllEnumsUsedRtException( aPath.kind().id() );
    }
  }

  @Override
  public ICurrentEntityService<MbTreePath> cesBridge() {
    return cesBridge;
  }

  // ------------------------------------------------------------------------------------
  // IGenericChangeEventCapable
  //

  @Override
  public IGenericChangeEventer genericChangeEventer() {
    return genericChangeEventer;
  }

}
