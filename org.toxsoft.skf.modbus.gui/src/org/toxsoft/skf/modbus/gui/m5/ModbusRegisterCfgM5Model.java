package org.toxsoft.skf.modbus.gui.m5;

import static org.toxsoft.core.tsgui.m5.IM5Constants.*;
import static org.toxsoft.core.tsgui.m5.gui.mpc.IMultiPaneComponentConstants.*;
import static org.toxsoft.core.tslib.av.EAtomicType.*;
import static org.toxsoft.core.tslib.av.impl.AvUtils.*;
import static org.toxsoft.core.tslib.av.metainfo.IAvMetaConstants.*;
import static org.toxsoft.skf.modbus.gui.l10n.ISkfModbusGuiSharedResources.*;
import static org.toxsoft.skf.modbus.lib.mbspec.IModbusSpecificationConstants.*;
import static org.toxsoft.uskat.core.ISkHardConstants.*;

import org.toxsoft.core.tsgui.bricks.ctx.*;
import org.toxsoft.core.tsgui.graphics.*;
import org.toxsoft.core.tsgui.m5.*;
import org.toxsoft.core.tsgui.m5.gui.panels.*;
import org.toxsoft.core.tsgui.m5.model.*;
import org.toxsoft.core.tsgui.m5.model.impl.*;
import org.toxsoft.core.tsgui.m5.std.fields.*;
import org.toxsoft.core.tsgui.utils.layout.*;
import org.toxsoft.core.tslib.av.*;
import org.toxsoft.core.tslib.av.metainfo.*;
import org.toxsoft.core.tslib.coll.*;
import org.toxsoft.core.tslib.coll.impl.*;
import org.toxsoft.skf.modbus.gui.glib.*;
import org.toxsoft.skf.modbus.lib.cfg.device.*;
import org.toxsoft.skf.modbus.lib.mbspec.*;

/**
 * M5-model of {@link ModbusRegisterCfg}.
 *
 * @author hazard157
 */
public class ModbusRegisterCfgM5Model
    extends M5Model<ModbusRegisterCfg> {

  /**
   * The model ID.
   */
  public static final String MODEL_ID = SK_ID + ".m5.skf.modbus.ModbusRegisterCfg"; //$NON-NLS-1$

  /**
   * ID of field {@link #REG_NO}
   */
  public static final String FID_REG_NO = "regNo"; //$NON-NLS-1$

  /**
   * ID of field {@link #KIND}
   */
  public static final String FID_KIND = "kind"; //$NON-NLS-1$

  /**
   * Field {@link ModbusRegisterCfg#regNo()}
   */
  public final IM5AttributeFieldDef<ModbusRegisterCfg> REG_NO = new M5AttributeFieldDef<>( FID_REG_NO, INTEGER, //
      TSID_NAME, STR_MODBUS_REGISTER_REG_NO, //
      TSID_DESCRIPTION, STR_MODBUS_REGISTER_REG_NO_D, //
      M5_OPDEF_FLAGS, avInt( M5FF_COLUMN ), //
      M5_OPDEF_COLUMN_ALIGN, avValobj( EHorAlignment.CENTER ), //
      TSID_MIN_INCLUSIVE, avInt( MB_REG_ADDRESS_RANGE.minValue() ), //
      TSID_MAX_INCLUSIVE, avInt( MB_REG_ADDRESS_RANGE.maxValue() ), //
      TSID_DEFAULT_VALUE, AV_0 //
  ) {

    protected IAtomicValue doGetFieldValue( ModbusRegisterCfg aEntity ) {
      return avInt( aEntity.regNo() );
    }

  };

  /**
   * Field {@link ModbusRegisterCfg#kind()}
   */
  public final IM5AttributeFieldDef<ModbusRegisterCfg> KIND = new M5AttributeFieldDef<>( FID_KIND, VALOBJ, //
      TSID_NAME, STR_MODBUS_REGISTER_KIND, //
      TSID_DESCRIPTION, STR_MODBUS_REGISTER_KIND_D, //
      M5_OPDEF_FLAGS, avInt( M5FF_COLUMN ), //
      TSID_KEEPER_ID, EModbusRegisterKind.KEEPER_ID, //
      TSID_DEFAULT_VALUE, avValobj( EModbusRegisterKind.HOLD_REG ) //
  ) {

    protected IAtomicValue doGetFieldValue( ModbusRegisterCfg aEntity ) {
      return avValobj( aEntity.kind() );
    }

  };

  /**
   * Constructor.
   */
  public ModbusRegisterCfgM5Model() {
    super( MODEL_ID, ModbusRegisterCfg.class );
    setNameAndDescription( STR_M5M_MODBUS_REGISTER_CFG, STR_M5M_MODBUS_REGISTER_CFG_D );
    IListEdit<IM5FieldDef<ModbusRegisterCfg, ?>> llFields = new ElemArrayList<>();
    llFields.add( REG_NO );
    llFields.add( KIND );
    for( IDataDef d : IModbusDeviceCfgConstants.ALL_REGISTER_OPDEFS ) {
      M5StdFieldDefParamAttr<ModbusRegisterCfg> fdef = new M5StdFieldDefParamAttr<>( d );
      switch( fdef.id() ) {
        case TSID_NAME: {
          fdef.setFlags( M5FF_COLUMN );
          break;
        }
        case IModbusDeviceCfgConstants.OPID_APPLICABLE_FUNCS: {
          fdef.setValedEditor( ValedAvValobjIntListMbFuncCodes.FACTORY_NAME );
          fdef.setFlags( M5FF_DETAIL );
          break;
        }
        default: {
          fdef.setFlags( M5FF_DETAIL );
          break;
        }
      }
      llFields.add( fdef );
    }
    addFieldDefs( llFields );
    setPanelCreator( new M5DefaultPanelCreator<>() {

      @Override
      protected IM5CollectionPanel<ModbusRegisterCfg> doCreateCollViewerPanel( ITsGuiContext aContext,
          IM5ItemsProvider<ModbusRegisterCfg> aItemsProvider ) {
        OPDEF_DETAILS_PANE_PLACE.setValue( aContext.params(), avValobj( EBorderLayoutPlacement.EAST ) );
        return super.doCreateCollViewerPanel( aContext, aItemsProvider );
      }

      @Override
      protected IM5CollectionPanel<ModbusRegisterCfg> doCreateCollEditPanel( ITsGuiContext aContext,
          IM5ItemsProvider<ModbusRegisterCfg> aItemsProvider,
          IM5LifecycleManager<ModbusRegisterCfg> aLifecycleManager ) {
        OPDEF_DETAILS_PANE_PLACE.setValue( aContext.params(), avValobj( EBorderLayoutPlacement.EAST ) );
        return super.doCreateCollEditPanel( aContext, aItemsProvider, aLifecycleManager );
      }

    } );
  }

  @Override
  protected IM5LifecycleManager<ModbusRegisterCfg> doCreateDefaultLifecycleManager() {
    return new ModbusRegisterCfgM5LifecycleManager( this );
  }

  @Override
  protected IM5LifecycleManager<ModbusRegisterCfg> doCreateLifecycleManager( Object aMaster ) {
    return getLifecycleManager( null );
  }

}
