package org.toxsoft.skf.modbus.gui.m5;

import static org.toxsoft.core.tsgui.bricks.actions.ITsStdActionDefs.*;
import static org.toxsoft.core.tslib.utils.TsLibUtils.*;
import static org.toxsoft.skf.modbus.gui.l10n.ISkfModbusGuiSharedResources.*;

import java.io.*;

import org.toxsoft.core.tsgui.bricks.actions.asp.*;
import org.toxsoft.core.tsgui.bricks.ctx.*;
import org.toxsoft.core.tsgui.bricks.ctx.impl.*;
import org.toxsoft.core.tsgui.dialogs.*;
import org.toxsoft.core.tsgui.rcp.utils.*;
import org.toxsoft.core.tslib.bricks.strid.coll.*;
import org.toxsoft.core.tslib.bricks.strid.coll.impl.*;
import org.toxsoft.core.tslib.coll.*;
import org.toxsoft.core.tslib.utils.errors.*;
import org.toxsoft.core.tslib.utils.logs.impl.*;
import org.toxsoft.skf.modbus.gui.e4.services.*;
import org.toxsoft.skf.modbus.gui.incub.*;
import org.toxsoft.skf.modbus.lib.cfg.device.*;

/**
 * ASP: actions to import/export modbus devices.
 *
 * @author hazard157
 */
class AspModbusDeviceCollExpImp
    extends MethodPerActionTsActionSetProvider
    implements ITsGuiContextable {

  private final ITsGuiContext            tsContext;
  private final IModbusDeviceCfgRegistry devRegistry;

  private String lastReadPath  = EMPTY_STRING;
  private String lastWritePath = EMPTY_STRING;

  AspModbusDeviceCollExpImp( ITsGuiContext aContext, IModbusDeviceCfgRegistry aDeviceRegistry ) {
    TsNullArgumentRtException.checkNulls( aContext, aDeviceRegistry );
    tsContext = aContext;
    devRegistry = aDeviceRegistry;
    defineAction( ACDEF_FILE_IMPORT, this::importCfgs );
    defineAction( ACDEF_FILE_EXPORT, this::exportCfgs );
  }

  void importCfgs() {
    File file = TsRcpDialogUtils.askFileOpen( getShell(), lastReadPath );
    if( file == null ) {
      return;
    }
    // read configurations from the file
    IList<ModbusDeviceCfg> rawList;
    try {
      rawList = ModbusDeviceCfg.KEEPER.readColl( file );
      lastReadPath = file.getAbsolutePath();
      if( lastWritePath.isBlank() ) {
        lastWritePath = lastReadPath;
      }
    }
    catch( Exception ex ) {
      LoggerUtils.errorLogger().error( ex );
      TsDialogUtils.error( getShell(), FMT_ERR_READING_MODBUS_DEV_CFG_COLL_FILE, ex.getLocalizedMessage() ); //
      return;
    }
    // FIXME here we need StridablesCollImportGui
    AbstractStridablesCollImportGui<ModbusDeviceCfg> importGui =
        new AbstractStridablesCollImportGui<>( new TsGuiContext( tsContext ) ) {

          @Override
          protected void doImportElements( IStridablesList<ModbusDeviceCfg> aColl ) {

            // TODO Auto-generated method stub

          }
        };
    importGui.prepare( new StridablesList<>( rawList ), devRegistry.list() );
    importGui.run();
  }

  void exportCfgs() {
    File file = TsRcpDialogUtils.askFileSave( getShell(), lastWritePath );
    if( file == null ) {
      return;
    }
    try {
      ModbusDeviceCfg.KEEPER.writeColl( file, devRegistry.list(), true );
      lastWritePath = file.getAbsolutePath();
      if( lastReadPath.isBlank() ) {
        lastReadPath = lastWritePath;
      }
    }
    catch( Exception ex ) {
      LoggerUtils.errorLogger().error( ex );
      TsDialogUtils.error( getShell(), FMT_ERR_WRITING_MODBUS_DEV_CFG_COLL_FILE, ex.getLocalizedMessage() ); //
      return;
    }
    TsDialogUtils.info( getShell(), FMT_MB_DEV_CFGS_EXPORTED_TO_FILE, Integer.valueOf( devRegistry.list().size() ),
        file.getAbsolutePath() );
  }

  // ------------------------------------------------------------------------------------
  // ITsGuiContextable
  //

  @Override
  public ITsGuiContext tsContext() {
    return tsContext;
  }

}
