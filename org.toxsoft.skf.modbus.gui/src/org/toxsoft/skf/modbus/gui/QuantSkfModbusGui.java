package org.toxsoft.skf.modbus.gui;

import org.eclipse.e4.core.contexts.*;
import org.toxsoft.core.tsgui.bricks.quant.*;
import org.toxsoft.core.tsgui.m5.*;
import org.toxsoft.core.tsgui.valed.api.*;
import org.toxsoft.core.tslib.utils.valobj.*;
import org.toxsoft.skf.modbus.gui.glib.*;
import org.toxsoft.skf.modbus.gui.incub.*;
import org.toxsoft.skf.modbus.gui.m5.*;
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
    ModbusLibUtils.initialize();
    TsValobjUtils.registerKeeper( EResolveStrategy.KEEPER_ID, EResolveStrategy.KEEPER );
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
    m5.addModel( new ModbusRegisterCfgM5Model() );
    m5.addModel( new ModbusDeviceCfgM5Model() );
    m5.addModel( new ModbusFuncCodeM5Model() );
  }

}
