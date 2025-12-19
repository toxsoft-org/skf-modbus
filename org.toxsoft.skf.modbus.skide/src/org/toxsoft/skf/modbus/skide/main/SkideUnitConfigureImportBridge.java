package org.toxsoft.skf.modbus.skide.main;

import static org.toxsoft.core.tsgui.bricks.actions.ITsStdActionDefs.*;
import static org.toxsoft.core.tslib.av.metainfo.IAvMetaConstants.*;
import static org.toxsoft.skf.modbus.skide.ISkfModbusSkideConstants.*;
import static org.toxsoft.skf.modbus.skide.l10n.ISkfModbusSkideSharedResources.*;
import static org.toxsoft.skide.core.ISkideCoreConstants.*;
import static org.toxsoft.skide.core.api.ucateg.ISkideUnitCategoryConstants.*;

import org.toxsoft.core.tsgui.bricks.ctx.*;
import org.toxsoft.core.tslib.av.opset.impl.*;
import org.toxsoft.skide.core.api.*;
import org.toxsoft.skide.core.api.impl.*;

/**
 * SkiDE unit: unit Modbus bridge cfg - mapping of registeres and s5 entities.
 *
 * @author max
 */
public class SkideUnitConfigureImportBridge
    extends AbstractSkideUnit {

  /**
   * The plugin ID.
   */
  public static final String UNIT_ID = SKIDE_FULL_ID + ".unit.skf.modbus.import"; //$NON-NLS-1$

  SkideUnitConfigureImportBridge( ITsGuiContext aContext, AbstractSkidePlugin aCreator ) {
    super( UNIT_ID, OptionSetUtils.createOpSet( //
        TSID_NAME, STR_UNIT_SKF_MODBUS_IMPORT, //
        TSID_DESCRIPTION, STR_UNIT_SKF_MODBUS_IMPORT_D, //
        OPDEF_SKIDE_UNIT_CATEGORY, UCATEGID_EXTERNAL_SYSTEMS, //
        TSID_ICON_ID, ICONID_APP_MODBUS_INOUT //
    ), aContext, aCreator );
    unitActions().add( ACDEF_ABOUT );
  }

  @Override
  protected AbstractSkideUnitPanel doCreateUnitPanel( ITsGuiContext aContext ) {
    return new SkidePanelConfigureImportBridge( aContext, this );
  }

}
