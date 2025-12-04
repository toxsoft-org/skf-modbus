package org.toxsoft.skf.modbus.gui;

import org.eclipse.e4.core.contexts.*;
import org.toxsoft.core.tsgui.bricks.quant.*;
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
  }

}
