package org.toxsoft.skf.modbus.skide.e4.addons;

import org.eclipse.e4.core.contexts.*;
import org.toxsoft.core.tsgui.bricks.quant.*;
import org.toxsoft.core.tsgui.mws.bases.*;
import org.toxsoft.skf.modbus.gui.*;
import org.toxsoft.skf.modbus.gui.e4.services.*;
import org.toxsoft.skf.modbus.skide.*;
import org.toxsoft.skf.modbus.skide.Activator;
import org.toxsoft.skf.modbus.skide.main.*;
import org.toxsoft.skide.core.api.*;

/**
 * Plugin addon.
 *
 * @author max
 */
public class AddonSkfModbusSkide
    extends MwsAbstractAddon {

  /**
   * Constructor.
   */
  public AddonSkfModbusSkide() {
    super( Activator.PLUGIN_ID );
  }

  // ------------------------------------------------------------------------------------
  // MwsAbstractAddon
  //

  @Override
  protected void doRegisterQuants( IQuantRegistrator aQuantRegistrator ) {
    aQuantRegistrator.registerQuant( new QuantSkfModbusGui() );
  }

  @Override
  protected void initApp( IEclipseContext aAppContext ) {
    ISkideEnvironment skEnv = aAppContext.get( ISkideEnvironment.class );
    skEnv.pluginsRegistrator().registerPlugin( SkidePluginModbus.INSTANCE );
    // prepare MODBUS devices registry
    ModbusDeviceCfgRegistry devRegistry = new ModbusDeviceCfgRegistry();
    aAppContext.set( IModbusDeviceCfgRegistry.class, devRegistry );
    aAppContext.set( ModbusDeviceCfgRegistry.class, devRegistry ); // set class also to fill registry
  }

  @Override
  protected void initWin( IEclipseContext aWinContext ) {
    ISkfModbusSkideConstants.init( aWinContext );
  }

}
