package org.toxsoft.skf.modbus.gui.glib.bridge_old;

import org.toxsoft.core.tslib.bricks.events.change.*;
import org.toxsoft.core.tslib.bricks.strid.coll.*;
import org.toxsoft.core.tslib.bricks.strid.coll.impl.*;
import org.toxsoft.core.tslib.bricks.validator.*;
import org.toxsoft.core.tslib.coll.*;
import org.toxsoft.core.tslib.coll.impl.*;
import org.toxsoft.core.tslib.utils.errors.*;
import org.toxsoft.skf.modbus.lib.cfg.bridge.*;

/**
 * {@link IMbBridgeCfgsEditorEnvironment} implementation.
 *
 * @author hazard157
 */
public class MbBridgeCfgsEditorEnvironment
    implements IMbBridgeCfgsEditorEnvironment {

  private final GenericChangeEventer              genericChangeEventer;
  private final IStridablesListEdit<IMbBridgeCfg> bridgeCfgs = new StridablesList<>();

  /**
   * List of nodes represents current content of {@link #bridgeCfgs}.
   * <p>
   * On every change this reference is reset to <code>null</code> indicating it need refresh in {@link #nodesList()}.
   */
  private IList<MbNodePath> nodesList = new SortedElemLinkedBundleList<>();

  /**
   * Constructor.
   */
  public MbBridgeCfgsEditorEnvironment() {
    genericChangeEventer = new GenericChangeEventer( this ) {
      @Override
      protected void whenChanged() {
        nodesList = null;
      }
    };
  }

  // ------------------------------------------------------------------------------------
  //
  //

  @Override
  public IStridablesList<IMbBridgeCfg> bridgeCfgs() {
    return bridgeCfgs;
  }

  @Override
  public IList<MbNodePath> nodesList() {
    if( nodesList == null ) {
      IListEdit<MbNodePath> llTmp = new ElemLinkedBundleList<>();
      for( IMbBridgeCfg bridge : bridgeCfgs ) {
        llTmp.add( MbNodePath.ofBridge( bridge.id() ) );
        for( IMbBusCfg bus : bridge.listBusCfgs() ) {
          llTmp.add( MbNodePath.ofBus( bridge.id(), bus.id() ) );
          for( IMbNodeCfg node : bus.listNodeCfgs() ) {
            llTmp.add( MbNodePath.ofNode( bridge.id(), bus.id(), node.id() ) );
          }
        }
      }
      nodesList = new SortedElemLinkedBundleList<>( llTmp );
    }
    return nodesList;
  }

  @SuppressWarnings( "unchecked" )
  @Override
  public <T> T find( MbNodePath aPath ) {
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
  public <T> T find( MbNodePath aPath, Class<T> aClass ) {
    TsNullArgumentRtException.checkNulls( aPath, aClass );
    return aClass.cast( find( aPath ) );
  }

  // ------------------------------------------------------------------------------------
  // IMbBridgeCfgsEditorEnvironment
  //

  @Override
  public IGenericChangeEventer genericChangeEventer() {
    return genericChangeEventer;
  }

  @Override
  public void remove( MbNodePath aPath ) {
    // TODO Auto-generated method stub

  }

  @Override
  public ITsValidationSupport<IMbBridgeCfgsEditorValidator> svs() {
    // TODO Auto-generated method stub
    return null;
  }

}
