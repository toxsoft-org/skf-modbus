package org.toxsoft.skf.modbus.gui.m5.device;

import static org.toxsoft.core.tsgui.m5.IM5Constants.*;
import static org.toxsoft.core.tslib.av.impl.AvUtils.*;
import static org.toxsoft.core.tslib.av.metainfo.IAvMetaConstants.*;
import static org.toxsoft.skf.modbus.gui.l10n.ISkfModbusGuiSharedResources.*;
import static org.toxsoft.uskat.core.ISkHardConstants.*;

import org.toxsoft.core.tsgui.m5.*;
import org.toxsoft.core.tsgui.m5.model.*;
import org.toxsoft.core.tsgui.m5.model.impl.*;
import org.toxsoft.core.tsgui.m5.std.fields.*;
import org.toxsoft.core.tsgui.valed.api.*;
import org.toxsoft.core.tslib.av.metainfo.*;
import org.toxsoft.core.tslib.coll.*;
import org.toxsoft.core.tslib.coll.impl.*;
import org.toxsoft.skf.modbus.gui.e4.services.*;
import org.toxsoft.skf.modbus.lib.cfg.device.*;

/**
 * M5-model of {@link MbDeviceCfg}.
 * <p>
 * Lifecycle manager expects {@link MbDeviceCfg} as a master object so it allows only listing of existing device
 * registers, no other CRUD operation are allowed. An instance of FIXME LM-name? must be created manually to edit
 * register configurations.
 *
 * @author hazard157
 */
public class MbDeviceCfgM5Model
    extends M5Model<MbDeviceCfg> {

  /**
   * The model ID.
   */
  public static final String MODEL_ID = SK_ID + ".m5.skf.modbus.ModbusDeviceCfg"; //$NON-NLS-1$

  /**
   * ID of field {@link #REGISTERS}
   */
  public static final String FID_REGISTERS = "Registers"; //$NON-NLS-1$

  /**
   * Field {@link MbDeviceCfg#id()}
   */
  public final IM5AttributeFieldDef<MbDeviceCfg> ID = new M5StdFieldDefId<>( //
      TSID_NAME, STR_MODBUS_DEVICE_CFG_ID, //
      TSID_DESCRIPTION, STR_MODBUS_DEVICE_CFG_ID_D, //
      M5_OPID_FLAGS, avInt( M5FF_COLUMN ) //
  );

  /**
   * Field {@link MbDeviceCfg#registerCfs()}
   */
  public final IM5MultiModownFieldDef<MbDeviceCfg, MbRegisterCfg> REGISTERS =
      new M5MultiModownFieldDef<>( FID_REGISTERS, MbRegisterCfgM5Model.MODEL_ID ) {

        @Override
        protected void doInit() {
          setNameAndDescription( STR_MODBUS_DEVICE_CFG_REGISTERS, STR_MODBUS_DEVICE_CFG_REGISTERS_D );
          setFlags( M5FF_DETAIL );
          params().setBool( IValedControlConstants.OPDEF_NO_FIELD_LABEL, true );
        }

        protected IList<MbRegisterCfg> doGetFieldValue( MbDeviceCfg aEntity ) {
          return aEntity.registerCfs().values();
        }

      };

  /**
   * Constructor.
   */
  public MbDeviceCfgM5Model() {
    super( MODEL_ID, MbDeviceCfg.class );
    setNameAndDescription( STR_M5M_MODBUS_DEVICE_CFG, STR_M5M_MODBUS_DEVICE_CFG_D );
    IListEdit<IM5FieldDef<MbDeviceCfg, ?>> llFields = new ElemArrayList<>();
    llFields.add( ID );
    for( IDataDef d : IMbDeviceCfgConstants.MB_DEV_ALL_OPDEFS ) {
      M5StdFieldDefParamAttr<MbDeviceCfg> fdef = new M5StdFieldDefParamAttr<>( d );
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
    llFields.add( REGISTERS );
    addFieldDefs( llFields );
    setPanelCreator( new MbDeviceCfgM5PanelCreator() );
  }

  @Override
  protected IM5LifecycleManager<MbDeviceCfg> doCreateLifecycleManager( Object aMaster ) {
    return new MbDeviceCfgM5LifecycleManager( this, IModbusDeviceCfgRegistry.class.cast( aMaster ) );
  }

}
