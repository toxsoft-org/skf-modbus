package org.toxsoft.skf.modbus.skide.main;

import static org.toxsoft.core.tslib.av.metainfo.IAvMetaConstants.*;
import static org.toxsoft.skf.modbus.skide.ISkfModbusSkideConstants.*;
import static org.toxsoft.skf.modbus.skide.l10n.ISkfModbusSkideSharedResources.*;
import static org.toxsoft.skide.core.ISkideCoreConstants.*;

import org.toxsoft.core.tsgui.bricks.ctx.*;
import org.toxsoft.core.tslib.av.opset.impl.*;
import org.toxsoft.core.tslib.bricks.strid.coll.*;
import org.toxsoft.skide.core.api.*;

/**
 * SkIDE plugin: provides units to configure USkat application communication to the MODBUS data import/export.
 *
 * @author max
 */
public class SkidePluginBridgeCfgModbus
    extends AbstractSkidePlugin {

  /**
   * The plugin ID.
   */
  public static final String SKIDE_PLUGIN_ID = SKIDE_FULL_ID + ".plugin.skf.modbus"; //$NON-NLS-1$

  /**
   * The singleton instance.
   */
  public static final AbstractSkidePlugin INSTANCE = new SkidePluginBridgeCfgModbus();

  SkidePluginBridgeCfgModbus() {
    super( SKIDE_PLUGIN_ID, OptionSetUtils.createOpSet( //
        TSID_NAME, STR_PLUGIN_SKF_MODBUS, //
        TSID_DESCRIPTION, STR_PLUGIN_SKF_MODBUS_D, //
        TSID_ICON_ID, ICONID_APP_MODBUS_EDITOR //
    ) );
  }

  @Override
  protected void doCreateUnits( ITsGuiContext aContext, IStridablesListEdit<ISkideUnit> aUnitsList ) {
    aUnitsList.add( new SkideUnitBridgeCfgModbusSkMapping( aContext, this ) );
  }

}
