package org.toxsoft.skf.modbus.gui.glib.bridge_old;

import static org.toxsoft.core.tsgui.m5.IM5Constants.*;
import static org.toxsoft.core.tslib.av.EAtomicType.*;
import static org.toxsoft.core.tslib.av.impl.AvUtils.*;
import static org.toxsoft.core.tslib.av.metainfo.IAvMetaConstants.*;
import static org.toxsoft.skf.modbus.gui.ISkfModbusGuiConstants.*;
import static org.toxsoft.skf.modbus.lib.ISkfModbusLibConstants.*;

import org.eclipse.swt.graphics.*;
import org.toxsoft.core.tsgui.graphics.icons.*;
import org.toxsoft.core.tsgui.m5.*;
import org.toxsoft.core.tsgui.m5.model.*;
import org.toxsoft.core.tsgui.m5.model.impl.*;
import org.toxsoft.core.tslib.av.*;
import org.toxsoft.core.tslib.bricks.strid.*;
import org.toxsoft.core.tslib.utils.errors.*;
import org.toxsoft.skf.modbus.lib.cfg.bridge.*;

/**
 * M5-model of the {@link MbNodePath}.
 * <p>
 * This model must be created and initialized as temporary model with {@link IM5Domain#initTemporaryModel(M5Model)}.
 *
 * @author hazard157
 */
class MbNodePathM5Model
    extends M5Model<MbNodePath> {

  /**
   * The model ID.
   */
  public static final String MODEL_ID = SKF_MB_M5_ID + ".MbNodePath"; //$NON-NLS-1$

  public static final String FID_KIND = "kind"; //$NON-NLS-1$

  public static final String FID_BRIDGE_ID = "bridgeId"; //$NON-NLS-1$
  public static final String FID_BUS_ID    = "busId";    //$NON-NLS-1$
  public static final String FID_NODE_ID   = "nodeId";   //$NON-NLS-1$
  public static final String FID_ENTITY    = "entity";   //$NON-NLS-1$

  public IM5AttributeFieldDef<MbNodePath> KIND = new M5AttributeFieldDef<>( FID_KIND, VALOBJ, //
      TSID_NAME, "Kind", //
      TSID_DESCRIPTION, "", //
      TSID_KEEPER_ID, EMbNodePathKind.KEEPER_ID, //
      TSID_DEFAULT_VALUE, avValobj( EMbNodePathKind.BRIDGE ) //
  ) {
    protected IAtomicValue doGetFieldValue( MbNodePath aEntity ) {
      return avValobj( aEntity.kind() );
    }
  };

  public final IM5AttributeFieldDef<MbNodePath> BRIDGE_ID = new M5AttributeFieldDef<>( FID_BRIDGE_ID, STRING, //
      TSID_NAME, "Bridge ID", //
      TSID_DESCRIPTION, "Bridge ID Desc", //
      M5_OPDEF_FLAGS, avInt( M5FF_INVARIANT ), //
      TSID_DEFAULT_VALUE, avStr( IStridable.NONE_ID ) //
  ) {

    protected IAtomicValue doGetFieldValue( MbNodePath aEntity ) {
      return aEntity.bridgeId() != null ? avStr( aEntity.bridgeId() ) : IAtomicValue.NULL;
    }

  };

  public final IM5AttributeFieldDef<MbNodePath> BUS_ID = new M5AttributeFieldDef<>( FID_BUS_ID, STRING, //
      TSID_NAME, "Bus ID", //
      TSID_DESCRIPTION, "Bus ID Desc", //
      M5_OPDEF_FLAGS, avInt( M5FF_INVARIANT ), //
      TSID_DEFAULT_VALUE, avStr( IStridable.NONE_ID ) //
  ) {

    protected IAtomicValue doGetFieldValue( MbNodePath aEntity ) {
      return aEntity.busId() != null ? avStr( aEntity.busId() ) : IAtomicValue.NULL;
    }

  };

  public final IM5AttributeFieldDef<MbNodePath> NODE_ID = new M5AttributeFieldDef<>( FID_NODE_ID, STRING, //
      TSID_NAME, "Node ID", //
      TSID_DESCRIPTION, "Node ID Desc", //
      M5_OPDEF_FLAGS, avInt( M5FF_INVARIANT ), //
      TSID_DEFAULT_VALUE, avStr( IStridable.NONE_ID ) //
  ) {

    protected IAtomicValue doGetFieldValue( MbNodePath aEntity ) {
      return aEntity.nodeId() != null ? avStr( aEntity.nodeId() ) : IAtomicValue.NULL;
    }

  };

  public final IM5FieldDef<MbNodePath, Object> ENTITY = new M5FieldDef<>( FID_ENTITY ) {

    @Override
    protected void doInit() {
      setNameAndDescription( "Bridge", "The Bridgedesc" );
      setFlags( M5FF_COLUMN | M5FF_HIDDEN );
    }

    protected Object doGetFieldValue( MbNodePath aEntity ) {
      return edEnv.find( aEntity );
    }

    protected String doGetFieldValueName( MbNodePath aEntity ) {
      Object rawEntity = edEnv.find( aEntity );
      switch( aEntity.kind() ) {
        case ROOT: {
          return aEntity.kind().nmName();
        }
        case BRIDGE: {
          if( rawEntity instanceof IMbBridgeCfg cfg ) {
            return cfg.nmName();
          }
          return aEntity.kind().nmName(); // must not happen
        }
        case BUS: {
          if( rawEntity instanceof IMbBusCfg cfg ) {
            return cfg.nmName();
          }
          return aEntity.kind().nmName(); // must not happen
        }
        case NODE: {
          if( rawEntity instanceof IMbNodeCfg cfg ) {
            return cfg.nmName();
          }
          return aEntity.kind().nmName(); // must not happen
        }
        default:
          throw new TsNotAllEnumsUsedRtException( aEntity.kind().id() );
      }
    }

    protected Image doGetFieldValueIcon( MbNodePath aEntity, EIconSize aIconSize ) {
      Object rawEntity = edEnv.find( aEntity );
      String iconId;
      switch( aEntity.kind() ) {
        case BUS: {
          if( rawEntity instanceof IMbBusCfg cfg ) {
            iconId = cfg.isRtu() ? ICONID_MB_BUS_RTU : ICONID_MB_BUS_TCP;
          }
          iconId = aEntity.kind().iconId(); // must not happen
          break;
        }
        case NODE: {
          if( rawEntity instanceof IMbNodeCfg cfg ) {
            iconId = cfg.isRtu() ? ICONID_MB_NODE_RTU : ICONID_MB_NODE_TCP;
          }
          iconId = aEntity.kind().iconId(); // must not happen
          break;
        }
        case BRIDGE:
        case ROOT:
        default:
          iconId = aEntity.kind().iconId();
          break;
      }
      return iconManager().loadStdIcon( iconId, aIconSize );
    }

  };

  private final IMbBridgeCfgsEditorEnvironment edEnv;

  /**
   * Constructor.
   *
   * @param aEnv {@link IMbBridgeCfgsEditorEnvironment} - the editing environment
   * @throws TsNullArgumentRtException any argument = <code>null</code>
   */
  public MbNodePathM5Model( IMbBridgeCfgsEditorEnvironment aEnv ) {
    super( MODEL_ID, MbNodePath.class );
    TsNullArgumentRtException.checkNull( aEnv );
    edEnv = aEnv;
  }

}
