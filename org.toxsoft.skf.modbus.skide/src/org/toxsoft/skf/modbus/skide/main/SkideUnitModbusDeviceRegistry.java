package org.toxsoft.skf.modbus.skide.main;

import static org.toxsoft.core.tsgui.bricks.actions.ITsStdActionDefs.*;
import static org.toxsoft.core.tslib.av.metainfo.IAvMetaConstants.*;
import static org.toxsoft.skf.modbus.skide.ISkfModbusSkideConstants.*;
import static org.toxsoft.skf.modbus.skide.l10n.ISkfModbusSkideSharedResources.*;
import static org.toxsoft.skide.core.ISkideCoreConstants.*;
import static org.toxsoft.skide.core.api.ucateg.ISkideUnitCategoryConstants.*;

import org.toxsoft.core.tsgui.bricks.ctx.*;
import org.toxsoft.core.tslib.av.opset.impl.*;
import org.toxsoft.core.tslib.bricks.strid.coll.*;
import org.toxsoft.core.tslib.bricks.strid.coll.impl.*;
import org.toxsoft.core.tslib.coll.*;
import org.toxsoft.core.tslib.utils.logs.impl.*;
import org.toxsoft.core.txtproj.lib.storage.*;
import org.toxsoft.skf.modbus.gui.e4.services.*;
import org.toxsoft.skf.modbus.lib.cfg.device.*;
import org.toxsoft.skide.core.api.*;
import org.toxsoft.skide.core.api.impl.*;

/**
 * SkiDE unit: unit Modbus bridge cfg - mapping of registeres and s5 entities.
 *
 * @author hazard157
 */
public class SkideUnitModbusDeviceRegistry
    extends AbstractSkideUnit {

  /**
   * The plugin ID.
   */
  public static final String UNIT_ID = SKIDE_FULL_ID + ".unit.skf.modbus.devices"; //$NON-NLS-1$

  private static final String SECTID_MODBUS_DEVICE_REGISTRY = "ModebusDeviceConfigurationsRegistry"; //$NON-NLS-1$

  SkideUnitModbusDeviceRegistry( ITsGuiContext aContext, AbstractSkidePlugin aCreator ) {
    super( UNIT_ID, OptionSetUtils.createOpSet( //
        TSID_NAME, STR_UNIT_SKF_MODBUS_DEVICES, //
        TSID_DESCRIPTION, STR_UNIT_SKF_MODBUS_DEVICES_D, //
        OPDEF_SKIDE_UNIT_CATEGORY, UCATEGID_EXTERNAL_SYSTEMS, //
        TSID_ICON_ID, ICONID_APP_MODBUS_DEVICE //
    ), aContext, aCreator );
    unitActions().add( ACDEF_ABOUT );

    // load known device configurations from from storage
    ModbusDeviceCfgRegistry devRegistry = tsContext().get( ModbusDeviceCfgRegistry.class );
    IKeepablesStorage unitStorage = plEnv().unitStorage( UNIT_ID );
    try {
      IList<ModbusDeviceCfg> llDevCfgs = unitStorage.readColl( SECTID_MODBUS_DEVICE_REGISTRY, ModbusDeviceCfg.KEEPER );
      IStridablesList<ModbusDeviceCfg> slDevCfgs = new StridablesList<>( llDevCfgs );
      devRegistry.fillRegistry( slDevCfgs );
    }
    catch( Exception ex ) {
      LoggerUtils.errorLogger().error( ex );
    }
    // listen to changes in registry and save to storage
    devRegistry.genericChangeEventer().addListener( aSource -> //
    unitStorage.writeColl( SECTID_MODBUS_DEVICE_REGISTRY, devRegistry.list(), ModbusDeviceCfg.KEEPER, true ) //
    );
  }

  @Override
  protected AbstractSkideUnitPanel doCreateUnitPanel( ITsGuiContext aContext ) {
    return new SkidePanelModbusDevicesRegistry( aContext, this );
  }

}
