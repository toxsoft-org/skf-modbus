package org.toxsoft.skf.modbus.gui.m5;

import static org.toxsoft.core.tsgui.m5.IM5Constants.*;
import static org.toxsoft.core.tsgui.m5.gui.mpc.IMultiPaneComponentConstants.*;
import static org.toxsoft.core.tslib.av.impl.AvUtils.*;
import static org.toxsoft.core.tslib.av.metainfo.IAvMetaConstants.*;
import static org.toxsoft.skf.modbus.gui.l10n.ISkfModbusGuiSharedResources.*;
import static org.toxsoft.uskat.core.ISkHardConstants.*;

import org.toxsoft.core.tsgui.bricks.actions.asp.*;
import org.toxsoft.core.tsgui.bricks.ctx.*;
import org.toxsoft.core.tsgui.m5.*;
import org.toxsoft.core.tsgui.m5.gui.panels.*;
import org.toxsoft.core.tsgui.m5.gui.panels.std.*;
import org.toxsoft.core.tsgui.m5.model.*;
import org.toxsoft.core.tsgui.m5.model.impl.*;
import org.toxsoft.core.tsgui.m5.std.fields.*;
import org.toxsoft.core.tsgui.utils.layout.*;
import org.toxsoft.core.tsgui.valed.api.*;
import org.toxsoft.core.tslib.av.metainfo.*;
import org.toxsoft.core.tslib.coll.*;
import org.toxsoft.core.tslib.coll.impl.*;
import org.toxsoft.skf.modbus.gui.e4.services.*;
import org.toxsoft.skf.modbus.lib.cfg.device.*;

/**
 * M5-model of {@link ModbusDeviceCfg}.
 * <p>
 * Lifecycle manager expects {@link ModbusDeviceCfg} as a master object so it allows only listing of existing device
 * registers, no other CRUD operation are allowed. An instance of FIXME LM-name? must be created manually to edit
 * register configurations.
 *
 * @author hazard157
 */
public class ModbusDeviceCfgM5Model
    extends M5Model<ModbusDeviceCfg> {

  /**
   * The model ID.
   */
  public static final String MODEL_ID = SK_ID + ".m5.skf.modbus.ModbusDeviceCfg"; //$NON-NLS-1$

  /**
   * ID of field {@link #REGIISTERS}
   */
  public static final String FID_REGISTERS = "Registers"; //$NON-NLS-1$

  /**
   * Field {@link ModbusDeviceCfg#id()}
   */
  public final IM5AttributeFieldDef<ModbusDeviceCfg> ID = new M5StdFieldDefId<>( //
      TSID_NAME, STR_MODBUS_DEVICE_CFG_ID, //
      TSID_DESCRIPTION, STR_MODBUS_DEVICE_CFG_ID_D, //
      M5_OPID_FLAGS, avInt( M5FF_COLUMN ) //
  );

  /**
   * Field {@link ModbusDeviceCfg#registerCfs()}
   */
  public final IM5MultiModownFieldDef<ModbusDeviceCfg, ModbusRegisterCfg> REGIISTERS =
      new M5MultiModownFieldDef<>( FID_REGISTERS, ModbusRegisterCfgM5Model.MODEL_ID ) {

        @Override
        protected void doInit() {
          setNameAndDescription( STR_MODBUS_DEVICE_CFG_REGISTERS, STR_MODBUS_DEVICE_CFG_REGISTERS_D );
          setFlags( M5FF_DETAIL );
          params().setBool( IValedControlConstants.OPDEF_NO_FIELD_LABEL, true );
        }

        protected IList<ModbusRegisterCfg> doGetFieldValue( ModbusDeviceCfg aEntity ) {
          return aEntity.registerCfs().values();
        }

      };

  /**
   * Constructor.
   */
  public ModbusDeviceCfgM5Model() {
    super( MODEL_ID, ModbusDeviceCfg.class );
    setNameAndDescription( STR_M5M_MODBUS_DEVICE_CFG, STR_M5M_MODBUS_DEVICE_CFG_D );
    IListEdit<IM5FieldDef<ModbusDeviceCfg, ?>> llFields = new ElemArrayList<>();
    llFields.add( ID );
    for( IDataDef d : IModbusDeviceCfgConstants.ALL_DEVICE_OPDEFS ) {
      M5StdFieldDefParamAttr<ModbusDeviceCfg> fdef = new M5StdFieldDefParamAttr<>( d );
      switch( fdef.id() ) {
        case TSID_NAME: {
          fdef.setFlags( M5FF_COLUMN );
          break;
        }
        default: {
          fdef.setFlags( M5FF_DETAIL );
          break;
        }
      }
      llFields.add( fdef );
    }
    llFields.add( REGIISTERS );
    addFieldDefs( llFields );
    setPanelCreator( new M5DefaultPanelCreator<>() {

      @Override
      protected IM5CollectionPanel<ModbusDeviceCfg> doCreateCollViewerPanel( ITsGuiContext aContext,
          IM5ItemsProvider<ModbusDeviceCfg> aItemsProvider ) {
        OPDEF_DETAILS_PANE_PLACE.setValue( aContext.params(), avValobj( EBorderLayoutPlacement.EAST ) );
        return super.doCreateCollViewerPanel( aContext, aItemsProvider );
      }

      @Override
      protected IM5CollectionPanel<ModbusDeviceCfg> doCreateCollEditPanel( ITsGuiContext aContext,
          IM5ItemsProvider<ModbusDeviceCfg> aItemsProvider, IM5LifecycleManager<ModbusDeviceCfg> aLifecycleManager ) {
        IModbusDeviceCfgRegistry devRegistry = IModbusDeviceCfgRegistry.class.cast( aLifecycleManager.master() );
        CompoundTsActionSetProvider asp = new CompoundTsActionSetProvider();
        asp.addHandler( new AspModbusDeviceCollExpImp( aContext, devRegistry ) );
        IM5StandardPanelConstants.REFDEF_M5STD_PANEL_ACTIONS_ASP.setRef( aContext, asp );
        OPDEF_DETAILS_PANE_PLACE.setValue( aContext.params(), avValobj( EBorderLayoutPlacement.EAST ) );
        return super.doCreateCollEditPanel( aContext, aItemsProvider, aLifecycleManager );
      }

    } );
  }

  @Override
  protected IM5LifecycleManager<ModbusDeviceCfg> doCreateLifecycleManager( Object aMaster ) {
    return new ModbusDeviceCfgM5LifecycleManager( this, IModbusDeviceCfgRegistry.class.cast( aMaster ) );
  }

}
