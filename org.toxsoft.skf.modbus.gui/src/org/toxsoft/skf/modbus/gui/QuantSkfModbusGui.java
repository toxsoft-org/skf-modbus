package org.toxsoft.skf.modbus.gui;

import org.eclipse.e4.core.contexts.*;
import org.toxsoft.core.tsgui.bricks.quant.*;
import org.toxsoft.core.tsgui.m5.*;
import org.toxsoft.core.tsgui.valed.api.*;
import org.toxsoft.skf.modbus.gui.glib.device.*;
import org.toxsoft.skf.modbus.gui.m5.device.*;
import org.toxsoft.skf.modbus.lib.*;

/**
 * The library quant.
 *
 * @author hazard157
 */
public class QuantSkfModbusGui
    extends AbstractQuant {

  /**
   * Constructor.
   */
  public QuantSkfModbusGui() {
    super( QuantSkfModbusGui.class.getSimpleName() );
    SkfModbusLibUtils.initialize();
    // FIXME TsValobjUtils.registerKeeper( EMbNodePathKind.KEEPER_ID, EMbNodePathKind.KEEPER );
  }

  // ------------------------------------------------------------------------------------
  // AbstractQuant
  //

  @Override
  protected void doInitApp( IEclipseContext aAppContext ) {
    // nop
  }

  @Override
  protected void doInitWin( IEclipseContext aWinContext ) {
    ISkfModbusGuiConstants.init( aWinContext );
    // VALEDs
    IValedControlFactoriesRegistry vcfReg = aWinContext.get( IValedControlFactoriesRegistry.class );
    vcfReg.registerFactory( ValedIntListMbFuncCodes.FACTORY );
    vcfReg.registerFactory( ValedAvValobjIntListMbFuncCodes.FACTORY );
    // M5
    IM5Domain m5 = aWinContext.get( IM5Domain.class );
    m5.addModel( new MbRegisterCfgM5Model() );
    m5.addModel( new MbDeviceCfgM5Model() );
    m5.addModel( new MbFuncCodeM5Model() );

    // FIXME m5.addModel( new MbBridgeCfgM5Model() );
    // FIXME m5.addModel( new MbBusCfgM5Model() );
    // FIXME m5.addModel( new MbNodeCfgM5Model() );
  }

}
